package fxdigital.dbsync.domains.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.db.dao.MqServerInfoDao;
import com.fxdigital.util.ConfigUtil;

@Component
public class AutoServer {
	private static Logger logger = Logger.getLogger(AutoServer.class);
	
	//默认定时的时间
	private static String TIME_AUTODOWN_DELAY="1000";
	private static String TIME_AUTODOWN_INTEVAL="300000";
	
	// 得到所有下载信息
	List<HashMap<String, String>> serverversion = null;
	@Autowired
	MqServerInfoDao mqServerInfoDao;

	@PostConstruct
	public void afterInit() {
		logger.info("auto down load start!!!");
		int autodowndelay = Integer.valueOf(null == ConfigUtil
				.getString("autodown.delay") ? TIME_AUTODOWN_DELAY : ConfigUtil
				.getString("autodown.delay"));
		int autodowninteval = Integer.valueOf(null == ConfigUtil
				.getString("autodonw.inteval") ? TIME_AUTODOWN_INTEVAL : ConfigUtil
				.getString("autodonw.inteval"));

		logger.info("autodowndelay" + autodowndelay);
		logger.info("autodowninteval" + autodowninteval);

//		Timer timer = new Timer();
//		timer.schedule(new TimerTask(), autodowndelay, autodowninteval);

	}

	class TimerTask extends java.util.TimerTask {

		@Override
		public void run() {
			logger.info("start timertask...");
			// 下载版本
			//startAutoDown();
		}

	}

	public void startAutoDown() {
		int ncount = 1;
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		logger.info("AutoDown获取本中心CenterId：" + centerinfo.get(0));
		// session = request.getSession();
		// session.removeAttribute("downlockid");
		// session.setAttribute("downlockid", downlock[0]);
		// 发送获取本地版本命令
		List<HashMap<String, String>> localversion = MsgClientService
				.getInstance().getLoadVersionInfo(centerinfo);
		while (true) {
			if (ncount < 5) {
				serverversion = MsgClientService.getInstance()
						.getMergeLoadVersion();
				//logger.info("------------------aaa"+serverversion);
				if (null != serverversion&&serverversion.size()>0) {
					// 如果存在新版本，则进行下载
					String isdown = MsgClientService.getInstance()
							.sendLoadCommand(serverversion, centerinfo);
					logger.info("0 有锁 1无锁2有下载数据3无下载数据");
					logger.info("autodown status isdown:" + isdown);
					break;
				} else {
					ncount++;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					logger.info("无下载数据。。。。");
				}
			} else {
				logger.info("autodown timeout...");
				break;
			}
		}

	}
}
