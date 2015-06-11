package fxdigital.mqcore.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BaseConnectionFactory {
	private static Log logger = LogFactory.getLog(BaseConnectionFactory.class);

	private static Map<MqConnectionInfo, BaseConnection> map = new ConcurrentHashMap<MqConnectionInfo, BaseConnection>();

	public static BaseConnection getConnection(String ip, int port,
			boolean synFlag) {
		MqConnectionInfo info = new MqConnectionInfo(ip, port, synFlag);
		BaseConnection baseConnection = map.get(info);
		if (baseConnection == null || !baseConnection.isActive()) {
			try {
				baseConnection = new BaseConnection(MqConnectionFactory
						.getConnectionFactory(info).createConnection(), true);
			} catch (JMSException e) {
				logger.error("获取连接失败", e);
				baseConnection = new BaseConnection(null, false);
			}
		}
		return baseConnection;

	}

}
