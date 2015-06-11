package fxdigital.client;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import fxdigital.db.RegisterCenter;
import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.rpc.IContent;
import fxdigital.rpc.Mail;
import fxdigital.rpc.content.base.RegisterContent;
import fxdigital.rpc.contenttype.base.RegisterType;
import fxdigital.rpc.sendmode.NormalMode;
import fxdigital.util.ArgsUtil;
import fxdigital.util.Log4jUtil;

/**
 * 同步服务器的客户端实现类
 * @author fucz
 * @version 2014-7-16
 */
public class BasicTelecomImpl implements IBasicTelecom,IReciveHandler{
	
	private IMessageListener listener;
	private Class<? extends IContent> contentClass;
	
	private BaseSender sender;//发送者
	private BaseReciver receiver;//接收者
	private String centerID;
	private String centerIP;
	private String serverIP;
	private int serverPort;
	private String postAddress;
	private String channelName;
	
	@SuppressWarnings("unused")
	private BasicTelecomImpl(){
	}
	
	/**
	 * 同步服务器的客户端
	 * @param centerID 中心ID
	 * @param centerIP 中心IP
	 * @param serverIP 同步MQ的IP
	 * @param serverPort 同步MQ的端口
	 */
	public BasicTelecomImpl(String centerID,String centerIP,
			String serverIP,int serverPort){
		this.centerID = centerID;
		this.centerIP = centerIP;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.postAddress = ArgsUtil.getPostAddress();
		
		sender = new BaseSender(serverIP,serverPort, false);
	}
	
	/**
	 * 注册到同步服务器
	 * @return true：注册成功
	 * 			false：注册失败
	 */
	public boolean registerInSyncServer(){
		Log4jUtil.info(this.getClass(),"发送注册消息到同步服务器...");
		Mail mail = createRegisterMail(centerID,centerIP,channelName);
		if(sender.sendMessage(postAddress, mail)){
			Log4jUtil.info(this.getClass(),"注册消息发送成功！");
			return true;
		}else{
			Log4jUtil.warn(this.getClass(),"注册消息发送失败！");
			return false;
		}
	}
	
	@Override
	public boolean handler(Mail message) {
		IContent content = JSON.parseObject(message.getContent(), contentClass);
		return notifyListener(content);
	}

	@Override
	public void setListener(IMessageListener listener) {
		this.listener = listener;
		this.contentClass = listener.getContentSimple().getClass();
		channelName = centerID + "_" + contentClass.getSimpleName();
		receiver = new BaseReciver(true,serverIP,serverPort,true,channelName);
		receiver.start(this);
	}

	@Override
	public boolean notifyListener(IContent content) {
		return listener.handle(content);
	}

	@Override
	public boolean send(String sendMode,IContent content) {
		Mail mail = createMail(sendMode,content);
		return sender.sendMessage(postAddress, mail);
	}
	
	//创建消息邮件
	private Mail createMail(String sendMode,IContent content){
		Mail mail = new Mail();
		content.setSender(centerID);
		if(StringUtils.isNullOrEmpty(content.getReceiver())){
			mail.setIpMail(true);
		}
		mail.setContent(JSON.toJSONString(content));
		mail.setContentType(content.getType());
		mail.setSendMode(sendMode);
		return mail;
	}
	
	//创建注册邮件
	private Mail createRegisterMail(String centerID,
			String centerIp,String channelName){
		//注册具体信息
		RegisterCenter rci = new RegisterCenter();
		rci.setCenterID(centerID);
		rci.setCenterIP(centerIp);
		rci.setChannelName(channelName);
		rci.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		
		//注册内容
		RegisterContent registerContent = new RegisterContent();
		registerContent.setRegisterCenterInfo(rci);
		registerContent.setSender(centerID);
		
		//注册邮件
		Mail mail = new Mail();
		mail.setContent(JSON.toJSONString(registerContent));
		mail.setContentType(RegisterType.TYPE);
		mail.setSendMode(NormalMode.MODE);
		mail.setIpMail(true);
		return mail;
	}

}
