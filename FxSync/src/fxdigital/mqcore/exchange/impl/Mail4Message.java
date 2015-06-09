package fxdigital.mqcore.exchange.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.Mail;

public class Mail4Message {
	public static Mail createMsg(Message message) {
		Mail msg = null;
		try {
			msg = new Mail();
			TextMessage tm = (TextMessage) message;
			msg = JSONObject.parseObject(tm.getText(), Mail.class);

		} catch (JMSException e) {
			throw new RuntimeException("JMSException: ", e);
		}
		return msg;
	}
	
	public static TextMessage MailToTextMessage(Mail mail){
//		TextMessage message = new TextMessage();
//		message.setJMSExpiration(JMSEXPIRATION);
//		message.setText(JSONObject.toJSONString(mail));
//		return message;
		return null;
	}

	
}
