package fxdigital.mqcore.exchange.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.exchange.IExchangeService;
import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.BaseHeader;
import fxdigital.mqcore.util.BaseProtocol;
import fxdigital.mqcore.util.ByteArrayUtil;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.mqcore.util.Protocol;
import fxdigital.syncserver.app.impl.AppServer;
import fxdigital.syncserver.business.UpLoad;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MessageChannelName;
import fxdigital.util.MsgType;
import fxdigital.util.RequestType;

/**
 * 接收和发送消息的中间处理层， 将消息抛给应用层，将应用层的消息取回处理,分为客户端请求、新服务器请求、老服务器请求
 * 
 * @author lizehua
 * 
 */
public class ExchangeService implements IExchangeService {
	private IServiceListener serviceListerner;
	
	static PostConfig config=null;
	BaseSender baseSender = null; 
	ExchangeMessage ex=null;
	//第一侦听通道
	//第二个
	//3
	
	
	//消息发送队列工具


	public ExchangeService(IServiceListener listener) {
		serviceListerner = listener;
		ex=new ExchangeMessage();
	}

	public void init(PostConfig config) {
		// 按照app传入信息，创建监听通道,把自己作为监听者传入
		this.config=config;
		ClientMessageHandler clientMessageHandler = new ClientMessageHandler(
				this);
		clientMessageHandler.start();
		
		OldMessageHandler oldMessageHandler = new OldMessageHandler(
				this);
		oldMessageHandler.start();
		
	}

	/**
	 * 收到上传下载消息，侦听后发送给中心侦听队列
	 */
	public void send() {
		baseSender = new BaseSender(config.getPostIp(), config.getPostPort(), false);
		Mail mail = new Mail();
		mail.setContent("hehe");
		baseSender.sendMessage(MessageChannelName.LOCAL_POST_CHANNEL, mail,ex);

	}

	/**
	 * 同步服务器之间联级时，发给本级
	 */
	public void oldSend() {
		BaseSender baseSender = new BaseSender("192.168.1.55", 61616, false);
		String oldxml = "<test>";
		String receiveId = "receiveFlag";

		String url = "DBSynchronization.SendOldQueueString";
		// 参数 ：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("centerid", "centerid");
		hp.put("sessionid", "sessionid");
		hp.put("ip", "ip");
		hp.put("uuid", "uuid");
		hp.put("versions", "versions");

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);

		byte[] data = Encoding.StringToByte(oldxml);
		baseSender.sendMessage(MessageChannelName.UP_CLIENT_CHANNEL, m,
				data, receiveId,ex);

	}

	@Override
	public boolean handler(String msgFlag, Message message) {
		Mail mail=Mail4Message.createMsg(message);
		return serviceListerner.onhandler(msgFlag, mail);
	}

	@Override
	public boolean oldHandler(String msgFlag, Message message) {
		OldMessage oldMessage=OldMessage4Message.createOldMsg(message);
		return serviceListerner.onOldHandler(msgFlag, oldMessage);
	}

	public void send(Mail message) {
		// TODO Auto-generated method stub
		Log4jUtil.info(ExchangeService.class, "发送新消息到客户端");
		Log4jUtil.info(ExchangeService.class, "config"+config.getPostIp()+config.getPostPort());
		if(null==baseSender){
			baseSender = new BaseSender(config.getPostIp(), config.getPostPort(), false);
		}
		String centerid=message.getDestMailboxID();
		Log4jUtil.info(ExchangeService.class,"channelName"+ MessageChannelName.getPostClientChannel(centerid));
		Log4jUtil.info(ExchangeService.class,"发送消息给:IP地址"+ config.getPostIp()+" 端口： "+config.getPostPort()+" 队列名： "+MessageChannelName.getPostClientChannel(centerid));
		baseSender.sendMessage(MessageChannelName.getPostClientChannel(centerid), message,ex);
	}

	public void send(OldMessage message) {
		// TODO Auto-generated method stub
		System.out.println("config" + config.getPostIp());	
		
		if(null==baseSender){
			baseSender = new BaseSender(config.getPostIp(), config.getPostPort(), false);
		}
		Log4jUtil.info(ExchangeService.class,"发送消息给:IP地址"+ config.getPostIp()+" 端口： "+config.getPostPort()+" 队列名： "+MessageChannelName.UP_SERVER_CHANNEL);
		baseSender.sendMessage(MessageChannelName.UP_SERVER_CHANNEL, message.getM(),
				message.getData(), message.getReceiveId(),ex);
	}

	public PostConfig getConfig(){
		return config;
	}

}
