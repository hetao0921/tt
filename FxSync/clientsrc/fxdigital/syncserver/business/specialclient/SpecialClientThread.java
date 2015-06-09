package fxdigital.syncserver.business.specialclient;

import java.util.HashMap;
import java.util.List;

import fxdigital.syncserver.business.hibernate.dao.SyncDataSetDao;
import fxdigital.util.Log4jUtil;

public class SpecialClientThread {
	
	static SpecialClient specialClient=null;
	

	public static void startThead(){
		specialClient=new SpecialClient();
		specialClient.init();
		boolean flag=true;
		int time = getSetTime() * 1000;
		while (flag) {
			try {
				// logger.info("设置同步服务器时间" + time);
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			NewThread newThread = new NewThread();
//			newThread.start();
			Log4jUtil.info(SpecialClientThread.class, "每隔"+getSetTime()+"s发送一次同步数据请求");
			 specialClient.SendAutoSyncCommand();
		}
	}
	
	
	
	static class NewThread extends Thread {
		public void run() {
			Log4jUtil.info(this.getClass(), "每隔"+getSetTime()+"s发送一次同步数据请求");
		    specialClient.SendAutoSyncCommand();
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
	
	
	public static void main(String[] args) {
		SpecialClientThread.startThead();
	}
}
