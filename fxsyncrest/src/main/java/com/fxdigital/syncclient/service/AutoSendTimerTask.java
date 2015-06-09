package com.fxdigital.syncclient.service;

import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.IncrementdataInfotabDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;
import com.fxdigital.syncclient.util.ConfigUtil;

import fxdigital.mqcore.exchange.rpc.PostConfig;
import fxdigital.mqcore.util.MessageChannelName;
import fxdigital.syncserver.app.HandlerChain;
import fxdigital.syncserver.app.impl.AppServer;

@Component
public class AutoSendTimerTask {
	private static Logger logger = Logger.getLogger(AutoSendTimerTask.class);
	@Autowired
	private AutoIncrementUploadTask incrementUploadTask;
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private AutoDownLoadHandler downLoad;
	@Autowired
	private VersionHandler versionHandler;
	@Autowired
	private UpLoadHandler upLoadHandler;
	@Autowired
	private ResetLoadHandler resetLoadHandler;
	@Autowired
	private ResetVersionHandler resetVersionHandler;

	
	// 默认定时的时间
	private static String TIME_AUTODOWN_DELAY = "1000";
	private static String TIME_AUTODOWN_INTEVAL = "300000";

	
	public void beginInit(){
		LocalCenter localCenter  = localCenterDao.queryInfo();
		AppServer appServer=AppServer.getInstance();
		PostConfig config=new PostConfig();
		
		config.setPostPort(localCenter.getSyncServerPort());
		config.setPostIp(localCenter.getSyncServerIP());
		config.setPostChannelName(MessageChannelName.getPostClientChannel(localCenter.getId()));
		
		HandlerChain handlerChain=new HandlerChain();

			handlerChain.setMessageHandler(downLoad);
			handlerChain.setMessageHandler(versionHandler);
			handlerChain.setMessageHandler(upLoadHandler);
			handlerChain.setMessageHandler(resetLoadHandler);
			handlerChain.setMessageHandler(resetVersionHandler);
			
		logger.info("初始化");
		appServer.init(config, handlerChain);
		
		
/*		int autodowndelay = Integer.valueOf(null == ConfigUtil
				.getString("autodown.delay") ? TIME_AUTODOWN_DELAY : ConfigUtil
				.getString("autodown.delay"));
		int autodowninteval = Integer.valueOf(null == ConfigUtil
				.getString("autodown.inteval") ? TIME_AUTODOWN_INTEVAL
				: ConfigUtil.getString("autodown.inteval"));
		logger.info("localCenter begin.......");
	
		logger.info("localCenter end.......");*/
		
/*		Timer timer = new Timer();
		timer.schedule(incrementUploadTask, 1000, 30000);*/
	}

	

}
