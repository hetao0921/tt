package fxdigital.postserver.contentdispose.handlers.dbsync.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hibernate.bean.SyncUpnetworkinfo;

import fxdigital.db.dao.SyncUpnetworkinfoDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataInfoDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataSetDao;

/**
 * @author het 自动同步启动类 2014-7-30 BusinessSync
 *         fxdigital.postserver.contentdispose.handlers.dbsync.service
 */

public class AutoServer {
	private static Logger logger = Logger.getLogger(AutoServer.class);
	private static MsgServerService msgService = null;
    private static SyncUpnetworkinfo syncUpnetworkinfo;

	
	private static String source =null;
	private static String dest = null;
	public static void main(String[] args) {
		int a =AutoServer.getSetTime();
		System.out.println(a);
	}

	public static void start(MsgServerService msgServerService) {
		syncUpnetworkinfo=new SyncUpnetworkinfo();
		syncUpnetworkinfo=SyncUpnetworkinfoDao.getInstance().query();
		if(null!=syncUpnetworkinfo){
			source=syncUpnetworkinfo.getSupIp();
			dest=String.valueOf(syncUpnetworkinfo.getSupPort());
		}

		//logger.info("centerinfo"+networkinfo);
		
		msgService = msgServerService;
		boolean flag = false;
		logger.info("上级IP:" + source + ";上级端口:" + dest);
		if (null != source && !("").equals(source) && null != dest
				&& !("").equals(dest)) {
			flag = true;
		} else {
			flag = false;
		}
		logger.info("同步启动标识:" + flag);
		while (flag) {
			int time = getSetTime() * 1000;
			try {
				// logger.info("设置同步服务器时间" + time);
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			NewThread newThread = new NewThread();
			newThread.start();
		}
	}

	static class NewThread extends Thread {
		public void run() {
			//logger.info(getName());
			logger.info("上级IP:" + source + ";上级端口:" + dest);
			if (null != source && !("").equals(source) && null != dest
					&& !("").equals(dest)) {
				msgService.sendAutoSyncCommand(source, dest);
			}

		}
	}

	public static int getSetTime() {
		int time = 0;
		// 默认最短时间
		int minTime = 60;
		List<HashMap<String, String>> autoList = SyncDataSetDao.getInstance()
				.getAllServerSet();
		if (null != autoList && autoList.size() > 0
				&& autoList.get(0).get("autotime") != null) {
			int setTime = Integer.valueOf((null == autoList.get(0).get(
					"autotime") ? (null == autoList.get(0).get(
					"autotime".toUpperCase()) ? "0" : autoList.get(0).get(
					"autotime".toUpperCase())) : autoList.get(0)
					.get("autotime")));
			time = setTime > minTime ? setTime : minTime;
		} else {
			time = minTime;
		}

		return time;
	}

}
