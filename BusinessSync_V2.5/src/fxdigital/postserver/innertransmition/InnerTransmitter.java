package fxdigital.postserver.innertransmition;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.mqcore.base.BaseSender;
import fxdigital.postserver.contentdispose.handlers.command.NormalBusinessHandler;
import fxdigital.rpc.IContent;
import fxdigital.rpc.Mail;

/**
 * 消息发送到本级MQ
 * @author fucz
 * @version 2014-7-11
 */
@Component
public class InnerTransmitter {
	
	private static final Logger log = Logger.getLogger(NormalBusinessHandler.class);
	
	@Autowired
	private DbManager dbManager;
	private BaseSender sender;
	
	@PostConstruct
	public void init(){
		LocalCenter localCenter = dbManager.getLocalCenter();
		sender = new BaseSender(localCenter.getSyncServerIP(),localCenter.getSyncServerPort(),true);
	}
	
	/**
	 * 发送消息到本级MQ
	 * @param channalName 通道名称
	 * @param content 消息内容
	 * @return true：发送成功
	 * 			false：发送失败
	 */
	public boolean sendIn(String channalName,IContent content){
		Mail mail = new Mail();
		mail.setContent(JSON.toJSONString(content));
		log.info("发送到本级【"+channalName+"】通道的消息："+mail);
		return sender.sendMessage(channalName, mail);
	}
	
}
