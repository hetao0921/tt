package NVMP.jms.base;

import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.jms.BytesMessage;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import NVMP.jms.util.JNDIUtil;

public class QueueConn {
	private QueueConnection conn;
	private QueueSession queuesession;
	private Queue queue;
	private QueueSender queueSender;
	private QueueReceiver queueReceiver;
 
	/**
	 * 初始化，传入客服端id,通道名称
	 * */
	public QueueConn(String queuename) throws JMSException {
		conn = JNDIUtil.getQueueConnection();
		queue = JNDIUtil.lookupQueue(queuename);
		queuesession = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queueSender = queuesession.createSender(queue);
	}

	/**
	 * 进行消息发送
	 * 
	 * @param strArray
	 *            格式是 名称：内容
	 * */
	public void sendMsg(byte[] array, String receiveId)
			throws JMSException {
		// BytesMessage
		BytesMessage bmg = queuesession.createBytesMessage();
      
		// ObjectMessage msg = queuesession.createObjectMessage();
        bmg.setStringProperty("receiveId", receiveId);
        bmg.setIntProperty("Length", array.length);
		bmg.writeBytes(array);

		
		queueSender.send(bmg, DeliveryMode.PERSISTENT, 4, 0);

		
	

	}
	

	/***
	 * 注册侦听
	 * 
	 * @throws JMSException
	 * */
	public void regListen(String filter, MessageListener ml)
			throws JMSException {
		if (filter == null)
			queueReceiver = queuesession.createReceiver(queue);
		else
			queueReceiver = queuesession.createReceiver(queue, filter);
		queueReceiver.setMessageListener(ml);
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
			JNDIUtil.again();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
