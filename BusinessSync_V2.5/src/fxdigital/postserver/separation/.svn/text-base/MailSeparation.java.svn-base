package fxdigital.postserver.separation;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.rpc.Mail;

/**
 * 邮件分发器
 * @author fucz
 * @version 2014-7-1
 */
@Component
public class MailSeparation implements IReciveHandler{
	
	private static final Logger log = Logger.getLogger(MailSeparation.class);
	
	private Map<String,ISeparater> separaters;

	@Override
	public boolean handler(Mail mail) {
		return separate(mail);
	}
	
	@PostConstruct
	public void init(){
		separaters = new HashMap<String,ISeparater>();
	}
	
	/**
	 * 注册分发器
	 * @param sendMode 发送模式
	 * @param separater 分发器
	 */
	public void register(String sendMode,ISeparater separater){
		separaters.put(sendMode, separater);
	}
	
	//根据发送模式分发邮件
	private boolean separate(Mail mail){
		ISeparater separater = separaters.get(mail.getSendMode());
		if(separater == null){
			log.error(mail.getSendMode()+"发送模式的组件未加载！销毁消息："+mail);
			return true;
		}
		return separater.handle(mail);
	}
	
}
