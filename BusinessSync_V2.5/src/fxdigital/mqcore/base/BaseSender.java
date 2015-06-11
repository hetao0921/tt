package fxdigital.mqcore.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import fxdigital.rpc.Mail;
import fxdigital.util.Log4jUtil;

//最原始的发送
public class BaseSender {
	private BaseConnection connection;
	private Session session;
	private String ip;
	private int port;
	private boolean syncFlag;
	//消息一分钟过期
	private static int JMSEXPIRATION=60000;

	private Map<String, MessageProducer> producerMap;

//	private static Logger log = Logger.getLogger(BaseSender.class);

	public BaseSender(String ip, int port, boolean syncflag) {
		this.ip = ip;
		this.port = port;
		this.syncFlag = syncflag;
		producerMap = new ConcurrentHashMap<String, MessageProducer>();

	}
	
	public boolean tryConnect(){
		BaseConnection conn = BaseConnectionFactory.
				getConnection(ip, port, syncFlag);
		return conn.isActive();
	}

	private BaseConnection getBaseConnection() {

		if (connection != null && connection.isActive()) {
		} else {
			connection = BaseConnectionFactory
					.getConnection(ip, port, syncFlag);

			try {
				if (connection.isActive()) {
					session = connection.getConnection().createSession(
							Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
					producerMap.clear();
				}
			} catch (JMSException e) {
				e.printStackTrace();
				connection.close();
			}
		}

		return connection;
	}

	private MessageProducer getProducer(String targetName) throws JMSException {
		MessageProducer messageProducer = null;
		if (getBaseConnection().isActive()) {
			messageProducer = producerMap.get(targetName);
			if (messageProducer == null) {
				Destination destination = session.createQueue(targetName);
				messageProducer = session.createProducer(destination);
				producerMap.put(targetName, messageProducer);
			}
		}
		return messageProducer;
	}

	public boolean sendMessage(String channalName, Mail msg) {
		MessageProducer messageProducer;
		boolean b = false;
		try {
			messageProducer = getProducer(channalName);
			if (messageProducer != null) {

				messageProducer.send(createMessage(msg));
//				messageProducer.setTimeToLive(JMSEXPIRATION);
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error(this.getClass(),"发送失败", e);
			connection.close();
		}
		return b;
	}

	private Message createMessage(Mail msg) throws JMSException {

		TextMessage message = session.createTextMessage();
//		message.setJMSExpiration(JMSEXPIRATION);
		message.setText(JSONObject.toJSONString(msg));
		return message;
	}

}
