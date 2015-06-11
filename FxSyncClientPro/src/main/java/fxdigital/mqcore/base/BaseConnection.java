package fxdigital.mqcore.base;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



class BaseConnection implements ExceptionListener {
	private static Log logger =LogFactory.getLog(BaseConnection.class);
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
	public void onException(JMSException e) {
		
		logger.error("连接异常",e);
		close();
	}

}
