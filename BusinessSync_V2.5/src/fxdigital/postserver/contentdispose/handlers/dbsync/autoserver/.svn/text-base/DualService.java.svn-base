/**
 * 
 */
package fxdigital.postserver.contentdispose.handlers.dbsync.autoserver;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.bean.SyncUpnetworkinfo;

import fxdigital.db.SyncMq;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DualDao;

/**
 * @author lizehua
 * 
 */
@Service
public class DualService {
	Log logger = LogFactory.getLog("DualService");

	@Autowired
	private DualDao dualDao;

	private String localBrokerURL;

	private String brokerURL;

	@PostConstruct
	public void getJMSBrokerURL() {
		logger.error("begin broker url........");
		HashMap<String, String> map = dualDao.queryJMS().get(0);
		SyncUpnetworkinfo syncUpnetworkinfo = new SyncUpnetworkinfo();
		syncUpnetworkinfo = dualDao.getUpMqInfo();
		String IP = map.get("syncServerIp") == null ? "" : map.get(
				"syncServerIp").toString();
		String port = map.get("syncServerPort") == null ? "" : map.get(
				"syncServerPort").toString();
		String upIP = null;
		int upPort = 0;
		if (null != syncUpnetworkinfo) {
			upIP = syncUpnetworkinfo.getSupIp();
			upPort = syncUpnetworkinfo.getSupPort();
		}

		logger.error("查询本地的jms地址和IP为： " + IP + " 端口： " + port);
		logger.error("查询上级的jms地址和IP为: " + upIP + " 端口： " + upPort);
		if ("".equals(IP) || "".equals(port)||null==upIP||upPort==0) {
			logger.error("查询的jms地址和IP为空");
			brokerURL = "failover:(tcp://" + IP.trim() + ":" + port.trim()
					+ ")";
			localBrokerURL = "failover:(tcp://" + IP.trim() + ":" + port.trim()
					+ ")";
		} else {
			String strUpPort=String.valueOf(upPort);
			brokerURL = "failover:(tcp://" + upIP.trim() + ":" + strUpPort.trim()
					+ ")";
			localBrokerURL =  "failover:(tcp://" + IP.trim() + ":" + port.trim()
					+ ")";
			logger.info("上级jms的连接地址:" + brokerURL);
			logger.info("本级jms的连接地址:" + localBrokerURL);

		}

	}

	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public String getLocalBrokerURL() {
		return localBrokerURL;
	}

	public void setLocalBrokerURL(String localBrokerURL) {
		this.localBrokerURL = localBrokerURL;
	}

}
