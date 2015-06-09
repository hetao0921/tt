package com.fxdigital.filesync.service;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.bean.FileSyncInfoBean;
import com.fxdigital.filesync.bundle.ResourceBundle;
import com.fxdigital.filesync.dao.UtilDao;
import com.fxdigital.filesync.service.ServerRun.Task;

public class ServerRun {
	private static final Logger logger = Logger
			.getLogger(FilesyncService.class);
	static FileSyncInfoBean fileSyncInfoBean = ResourceBundle.getFileSyncInfo();
	String flag;

	public Task getTask() {
		return new Task();
	}

	class Task extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			logger.info("start task...");
			FilesyncService filesyncService = new FilesyncService();
			filesyncService.startServer(fileSyncInfoBean.getMainserverpath());

		}

	}

	class SonThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			logger.info("MainSonThread start...");
			Timer timer = new Timer();
			timer.schedule(new ServerRun().new Task(),
					fileSyncInfoBean.getFilesyncdelay(),
					fileSyncInfoBean.getFilesyncinterval());
		}

	}

	public void changeServer(Task task, Timer timer) {
		logger.info("start change server...");
		// 取消定时器
		task.cancel();
		// 初始化配置文件到数据库
		// 服务器是主服务器
		if (UtilDao.getInstance().isMainServer() == 1) {
			FileSyncInfoBean fileSyncInfoBean = ResourceBundle
					.getFileSyncInfo();
			FilesyncService filesyncService = new FilesyncService();
			filesyncService.initConf(fileSyncInfoBean.getMainserverpath());
		}
		// 启动定时器进行扫描
		ServerRun changeServerRun = new ServerRun();
		Task changeTask = changeServerRun.getTask();
		timer.schedule(changeTask, fileSyncInfoBean.getFilesyncdelay(),
				fileSyncInfoBean.getFilesyncinterval());
	}

	public void startServer(Task task, Timer timer) {
		// 服务器是主服务器
		if (UtilDao.getInstance().isMainServer() == 1) {
			FileSyncInfoBean fileSyncInfoBean = ResourceBundle
					.getFileSyncInfo();
			FilesyncService filesyncService = new FilesyncService();
			filesyncService.initConf(fileSyncInfoBean.getMainserverpath());
		}
		// 启动定时器进行扫描
		timer.schedule(task, fileSyncInfoBean.getFilesyncdelay(),
				fileSyncInfoBean.getFilesyncinterval());
	}

	
	public void startFileSync(){
		ServerRun serverRun = new ServerRun();
		Task task = serverRun.getTask();
		Timer timer = new Timer();

		serverRun.startServer(task, timer);
	}
	
	public static void main(String[] args) {
		// FileSyncInfoBean fileSyncInfoBean = ResourceBundle.getFileSyncInfo();
		// FilesyncService filesyncService=new FilesyncService();
		// filesyncService.initConf(fileSyncInfoBean.getMainserverpath());

		// Thread mainSonTread=new Thread(new ServerRun().new SonThread());
		// mainSonTread.start();
		// String changeFlag="1";
		// if(changeFlag.equals("1")){
		// mainSonTread.destroy();
		// }
		// String mainFlag = "1";
		// ServerRun serverRun = new ServerRun();
		//
		// Task task = serverRun.getTask(mainFlag);
		// task.cancel();
		// Timer timer = new Timer();
		// timer.schedule(task, fileSyncInfoBean.getFilesyncdelay(),
		// fileSyncInfoBean.getFilesyncinterval());
		ServerRun serverRun = new ServerRun();
		Task task = serverRun.getTask();
		Timer timer = new Timer();

		serverRun.startServer(task, timer);

		try {
			Thread.sleep(360000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverRun.changeServer(task, timer);
	}

}
