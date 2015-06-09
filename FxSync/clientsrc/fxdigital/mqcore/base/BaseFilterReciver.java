package fxdigital.mqcore.base;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.util.Log4jUtil;

public class BaseFilterReciver implements MessageListener {

//	private static Logger log = Logger.getLogger(BaseReciver.class);
	private BaseConnection connection;
	private Session session;
	private MessageConsumer consumer;
	private String ip;
	private int port;
	private boolean syncFlag;
	private boolean autoflag;
	private IReciveHandler handler;
	private String name;
	private String receiveId;

	// 自动 true 手动接收 false;
	public BaseFilterReciver(boolean autoflag, String ip, int port, boolean syncflag,
			String name,String receiveId) {
		this.ip = ip;
		this.port = port;
		this.syncFlag = syncflag;
		this.autoflag = autoflag;
		this.name = name;
		this.receiveId=receiveId;
	}

	

	private BaseConnection getBaseConnection() {

		if (consumer != null && connection != null && connection.isActive()) {
		} else {
			connection = BaseConnectionFactory
					.getConnection(ip, port, syncFlag);

			try {
				if (connection.isActive()) {
					session = connection.getConnection().createSession(
							Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
					consumer = null;
				}
			} catch (JMSException e) {
				e.printStackTrace();
				connection.close();
			}
		}

		return connection;
	}

	private MessageConsumer getConsumer() throws JMSException {

		if (getBaseConnection().isActive()) {
			if (consumer == null) {
				Destination destination = session.createQueue(this.name);
				consumer = session.createConsumer(destination,receiveId);
			}
		}
		return consumer;
	}

	public boolean start(IReciveHandler handler) {
		boolean b = false;
		try {
			if (autoflag && getConsumer() != null) {
				this.handler = handler;
				consumer.setMessageListener(this);
				Log4jUtil.info(this.getClass(),"侦听通道 " + name + "启动  iP: " + ip);
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}
	
	public void distroy(){
		try {
			consumer.setMessageListener(null);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public boolean recive(IReciveHandler handler) {
		boolean b = false;
		try {
			if (!autoflag && getConsumer() != null) {
				Message message = consumer.receive(2000);
//				log.info("recive data");
				if (message != null) {
					b = handler.handler(message);
				}
				if (b)
					message.acknowledge();
				else {
					if (message != null) {
						session.recover();
					}
				}

			}
		} catch (Exception e) {
			Log4jUtil.error(this.getClass(),"recive data error : name: "+name, e);
			b = false;
		}
		return b;
	}

	private Mail createMsg(Message message) {
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

	@Override
	public void onMessage(Message message) {
//		Mail msg = createMsg(message);
		boolean b;

		b = handler.handler(message);
		try {
			if (b) {

				message.acknowledge();

			} else {
				Log4jUtil.error(this.getClass(),"业务通道 " + name + " 处理异常");
				session.recover();
			}
		} catch (JMSException e) {
			e.printStackTrace();
			Log4jUtil.error(this.getClass(),"业务通道 " + name + " 处理异常",e);
		}
	}

}
