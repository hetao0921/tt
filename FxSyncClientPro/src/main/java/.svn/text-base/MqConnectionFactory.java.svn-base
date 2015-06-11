package fxdigital.mqcore.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 根据不同的连接，产生对应连接工厂
 * 
 * @author hxht
 * 
 */

public class MqConnectionFactory {
	private static Log logger = LogFactory.getLog(MqConnectionFactory.class);

	private static Map<MqConnectionInfo, ConnectionFactory> map = new ConcurrentHashMap<MqConnectionInfo, ConnectionFactory>();

	static ConnectionFactory getConnectionFactory(MqConnectionInfo info) {

		ConnectionFactory factory = map.get(info);
		if (factory == null) {
			String connStr = String
					.format("failover:(tcp://%s:%d)?initialReconnectDelay=3000&maxReconnectAttempts=-1",
							info.ip, info.port);
			
//			String connStr = String
//					.format("failover:(tcp://%s:%d)",
//							info.ip, info.port);
			logger.info("connStr :" + connStr);
			ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(
					connStr);
			
			
			if (info.synFlag) {
				connFactory.setUseAsyncSend(false);
				// connFactory.setSendAcksAsync(false);
				connFactory.setDispatchAsync(false);
			} else {
				connFactory.setUseAsyncSend(true);// 发送异步
				connFactory.setDispatchAsync(true);
			}
//			PooledConnectionFactory pcf = new PooledConnectionFactory();
//			pcf.setConnectionFactory(connFactory);
//			pcf.setMaxConnections(100);
//			factory = pcf;
			RedeliveryPolicy policy = new RedeliveryPolicy();
			policy.setMaximumRedeliveries(-1);
			policy.setMaximumRedeliveryDelay(10000);
			policy.setUseExponentialBackOff(true);
			
//			policy.setMaximumRedeliveryDelay(maximumRedeliveryDelay)
			
			connFactory.setRedeliveryPolicy(policy);
			
//			PooledConnectionFactory pooledFactory = new PooledConnectionFactory(  
//					connFactory);
//			pooledFactory.setIdleTimeout(10000);
//			
//			factory = pooledFactory;
			factory = connFactory;
			map.put(info, factory);
		}
		return factory;
	}

}
