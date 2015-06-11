package fxdigital.postserver.contentdispose;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import fxdigital.db.DbManager;
import fxdigital.db.RegisterCenter;
import fxdigital.postserver.outertransmition.OuterTransmitter;
import fxdigital.rpc.IContent;
import fxdigital.rpc.Mail;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.base.DeregisterContent;
import fxdigital.rpc.content.base.RelationContent;
import fxdigital.rpc.contenttype.base.DeregisterType;
import fxdigital.rpc.contenttype.base.RelationType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 消息内容分发器
 * @author fucz
 * @version 2014-7-1
 */
@Component
public class ContentHandler {
	
	private static final Logger log = Logger.getLogger(ContentHandler.class);
	
	@Autowired
	private DbManager dbManager;
	@Autowired
	protected OuterTransmitter outerTransmitter;
	private String localMailboxID;
	private Map<String,String> deregisters = new HashMap<String,String>();
	private Map<ModeAndType,IHandler> handlers;
	
	public Map<String,String> getDeregisters(){
		return deregisters;
	}
	
	@PostConstruct
	public void init(){
		localMailboxID = dbManager.getLocalCenter().getId();
		handlers = new HashMap<ModeAndType,IHandler>();
	}
	
	/**
	 * 注册消息处理器
	 * @param modeAndType 消息的类型和发送模式
	 * @param handler 消息处理器
	 */
	public void register(ModeAndType modeAndType,IHandler handler){
		handlers.put(modeAndType, handler);
	}
	
	/**
	 * 分发消息内容
	 * @param mail 邮件
	 * @return true：消息处理成功
	 * 			false：消息处理失败，返回MQ通道
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public boolean handle(Mail mail){
		ModeAndType mat = null;
		for(ModeAndType temp : handlers.keySet()){
			if(temp.getMode().equals(mail.getSendMode())
					&& temp.getType().equals(mail.getContentType())){
				mat = temp;
				break;
			}
		}
		if(mat == null){
			log.error(mail.getSendMode()+"发送模式的"
						+mail.getContentType()+"消息类型模块未加载！销毁消息："+mail);
			return true;
		}
		IContent content = JSON.parseObject(mail.getContent(), IContent.class);
		String receiver = content.getReceiver();
		if(!StringUtils.isNullOrEmpty(receiver)
				&& deregisters.containsKey(receiver)){
			log.warn("目标通道已被注销！销毁消息："+mail);
			return true;
		}
		return handlers.get(mat).handle(mail.getContent());
	}
	
	/**
	 * 发送同步级联数据
	 * @return true：发送成功
	 * 			false：发送失败
	 */
	public boolean sendRelationMail(){
		String superID = dbManager.getSuperID(localMailboxID);
		if(superID != null){
			log.info("向上级同步级联数据！");
			return outerTransmitter.sendOut(createRelationMail(superID));
		}else{
			return true;
		}
	}
	
	/**
	 * 创建同步级联邮件
	 * @param superID 上级同步服务器ID
	 * @return 同步级联邮件
	 */
	public Mail createRelationMail(String superID){
		Mail mail = new Mail();
		RelationContent content = new RelationContent();
		content.setSyncServers(dbManager.getAllSyncServers());
		content.setServerRelations(dbManager.getAllServerRelations());
		content.setRegisterCenters(dbManager.getAllRegisterCenters());
		content.setSyncID(localMailboxID);
		mail.setContent(JSON.toJSONString(content));
		mail.setContentType(RelationType.TYPE);
		mail.setSendMode(NormalMode.MODE);
		mail.setSrcMailboxID(localMailboxID);
		mail.setDestMailboxID(superID);
		return mail;
	}
	
	/**
	 * 创建中心注销邮件
	 * @param dest 消息目的地
	 * @param registerCenter 中心注销信息
	 * @return 中心注销邮件
	 */
	public Mail createDeregisterMail(String dest,RegisterCenter registerCenter){
		Mail mail = new Mail();
		DeregisterContent content = new DeregisterContent();
		content.setRegisterCenterInfo(registerCenter);
		mail.setContent(JSON.toJSONString(content));
		mail.setContentType(DeregisterType.TYPE);
		mail.setDestMailboxID(dest);
		mail.setSrcMailboxID(localMailboxID);
		mail.setSendMode(NormalMode.MODE);
		return mail;
	}
	
}
