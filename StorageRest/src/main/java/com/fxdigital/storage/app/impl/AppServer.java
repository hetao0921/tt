package com.fxdigital.storage.app.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.httpserver.handler.StartHandler;
import com.fxdigital.httpserver.handler.StopHandler;
import com.fxdigital.httpserver.web.MyHttpServer;
import com.fxdigital.rest.web.SearchController;
import com.fxdigital.rest.web.StartController;
import com.fxdigital.rest.web.StopController;
import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.IAppServer;
import com.fxdigital.storage.app.ITimer;
import com.fxdigital.storage.exchange.impl.ExchangeService;
import com.fxdigital.storage.service.impl.HintTask;
import com.fxdigital.storage.service.impl.OnTimeTask;
import com.fxdigital.storage.service.impl.SetCapacityService;
import com.fxdigital.storage.service.impl.SetService;
import com.fxdigital.syncclient.util.BundlerProperties;

@Component
public class AppServer implements IAppServer {

	private static final Logger logger = Logger.getLogger(AppServer.class);

	@Autowired
	private ExchangeService exchangeService;

	@Autowired
	private ControllerManager controllerManager;

	@Autowired
	private StartController startController;

	@Autowired
	private StopController stopController;

	@Autowired
	private SearchController searchController;
	
	
	@Autowired
	private StartHandler startHandler;
	
	@Autowired
	private StopHandler stopHandler;

	@Autowired
	private OnTimeTask onTimeTask;
	
	@Autowired
	private SetService setService;
	
	@Autowired
	private HintTask hintTask;
	
	@Autowired
	private MyHttpServer myHttpServer;
	
	@Autowired
	private SetCapacityService setCapacityService;

	NIOReactor r = null;
	NIOReactor hintReactor = null;
	
	public AppServer() {
		logger.info("new ok");
	}
	
	
	/**
	 * 初始化 将相关的操作注册到当前应用 启动一个线程来处理当前所有操作
	 */
	@PostConstruct
	public void init() {
		logger.info("开始进行init初始化");
		
		boolean flag=setCapacityService.isEnough();
		if(!flag){
			logger.info("当前服务器存储空间不够，无法启动");
			return;
		}
		
//		startController.regApp(this);
//		stopController.regApp(this);
		
		startHandler.regApp(this);
		stopHandler.regApp(this);
		
		
		onTimeTask.regApp(this);
		hintTask.regApp(this);

		// reactor模型初始化
		r = (NIOReactor) NIOReactor.defaultReactor();
		
		// 初始化一个reactor模型用来进行hint
		// hintReactor=(NIOReactor) NIOReactor.defaultReactor();

		 HintClass hintClass=new HintClass();
		 new Thread(hintClass).start();
		

		// 初始化时，关闭所有需要关闭的文件
		 initStop();
		// 定时任务启动，一个小时后更换文件
		initTimeTask();
		
		initStorageInfo();

        //启动httpServer侦听
		try {
			myHttpServer.httpserverService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("启动httpServer失败"+e);
		}
	}
	
	
	/**
	 * 
	 */
	public void initStorageInfo(){
		logger.info("初始化存储服务器信息");
		setService.initStorageInfo();
	}

	/**
	 * 初始化转换文件任务
	 */
	public void initTimeTask() {
		logger.info("转换文件任务启动，"+getOnTime()+"分钟一次,下一次转换文件时间"
				+ new Timestamp(System.currentTimeMillis() + getOnTime() * 60 * 1000));
		ITimer t1 = r.newTimer(onTimeTask);
		t1.schedule(getOnTime() * 60 * 1000);
	}

	/**
	 * rest事件触发
	 * 
	 * @param runnable
	 */
	public void eventOn(Runnable runnable) {
		logger.info("触发事件");
		ITimer t1 = r.newTimer(runnable);
		t1.schedule(1000);
	}

	/**
	 * 处理存储录像任务
	 */
	public void start() {
		exchangeService.start(controllerManager);
	}

	/**
	 * 处理录像停止任务
	 */
	public void stop() {
		exchangeService.stop(controllerManager);
	}

	public void initStop() {
		exchangeService.initStop();
	}

	/**
	 * 将hint任务加到当前rector中
	 * @param runnable
	 */
	public void hintEvent(Runnable runnable) {
		hintReactor.runInReactor(runnable);
	}

	/**
	 * 启动一个线程来进行文件转换
	 * 
	 * @author Administrator
	 * 
	 */
	class HintClass implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			hintReactor = (NIOReactor) NIOReactor.defaultReactor();
		}

	}

	/**
	 * 从配置文件取得当前切换文件的时间间隔
	 * @return
	 */
	public int getOnTime() {
		BundlerProperties bundlerProperties = new BundlerProperties();
		Properties pps = bundlerProperties.getPropertie("ontime.properties");
		return Integer.valueOf((String) pps.get("uptatefile.interval"));
	}

}
