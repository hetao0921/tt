package fxdigital.mqcore.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;

import fxdigital.util.Log4jUtil;

public class BaseConnectionFactory {
//	private static Logger log = Logger.getLogger(BaseConnectionFactory.class);

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
				Log4jUtil.error(BaseConnectionFactory.class,"获取连接失败", e);
				baseConnection = new BaseConnection(null, false);
			}
		}
		return baseConnection;

	}

}
