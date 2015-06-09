package fxdigital.mqcore.base;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import fxdigital.util.Log4jUtil;

class BaseConnection implements ExceptionListener {
//	private static Logger log = Logger.getLogger(BaseConnection.class);
	private Connection connection;
	private boolean flag;

	BaseConnection(Connection connection, boolean b) {
		flag = b;
		if (flag) {
			this.connection = connection;
			try {
				connection.setExceptionListener(this);
				connection.start();
				flag = true;
			} catch (JMSException e) {
				e.printStackTrace();
				flag = false;
			}
		}
	}

	public boolean isActive() {
		return flag;
	}

	public void close() {
		if (flag) {
			flag = false;
			try {
				if (connection != null)
					connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void onException(JMSException arg0) {
		arg0.printStackTrace();
		Log4jUtil.error(this.getClass(),"连接收到异常", arg0);
		close();
	}

}
