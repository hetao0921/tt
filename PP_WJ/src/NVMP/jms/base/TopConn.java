package NVMP.jms.base;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.jms.BytesMessage;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import NVMP.jms.util.JNDIUtil;

/**
 * 发布/预订模型,针对一个通道而言。
 * */
public class TopConn {
	private TopicConnection conn;
	private TopicSession topSession;
	private TopicPublisher publisher;
	private Topic topic;
	private MessageConsumer subConsumer;
	private String clientID; 

	/**
	 * 初始化，传入客服端id,通道名称
	 * */
	public TopConn(String clientid, String topname) throws JMSException {

		clientID = clientid;
		conn = JNDIUtil.getTopicConnection();
		conn.setClientID(clientID);
		topic = JNDIUtil.lookupTopic(topname);
		topSession = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		publisher = topSession.createPublisher(topic);

	}

	/**
	 * 进行消息发送
	 * 
	 * @param strArray
	 *            格式是 名称：内容
	 * */
	public void publishMsg(byte[] array, String receiveId) throws JMSException {
		// ObjectMessage msg = topSession.createObjectMessage();
		BytesMessage bmg = topSession.createBytesMessage();
		bmg.writeBytes(array);
		bmg.setIntProperty("Length", array.length);
		bmg.setStringProperty("receiveId", receiveId);

		publisher.publish(bmg, DeliveryMode.PERSISTENT, 4, 0);

	}

	/***
	 * 注册侦听
	 * 
	 * @throws JMSException
	 * */
	public void regListen(String name, MessageListener ml) throws JMSException {
		subConsumer = topSession.createDurableSubscriber(topic, name);
		subConsumer.setMessageListener(ml);
		start();
	}

	public void start() throws JMSException {
		conn.start();
	}

	public void stop() throws JMSException {
		conn.stop();
	}

	public void Close() {

		try {
			conn.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
