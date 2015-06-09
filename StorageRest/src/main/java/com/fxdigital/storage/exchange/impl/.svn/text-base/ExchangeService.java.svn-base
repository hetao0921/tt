package com.fxdigital.storage.exchange.impl;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.impl.NIOReactor;
import com.fxdigital.storage.exchange.IExchangeService;
import com.fxdigital.storage.service.impl.StartService;
import com.fxdigital.storage.service.impl.StopService;
@Component
public class ExchangeService implements IExchangeService {

	@Autowired
	private StartService startService;
	@Autowired
	private StopService stopService;


	ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String, String>>> operateMap;

	/**
	 * 开始任务
	 * @param controllerManager
	 */
	public void start(ControllerManager controllerManager) {
		operateMap = controllerManager.getOperateMap();
		if (null != operateMap) {
			ConcurrentLinkedQueue<ConcurrentMap<String, String>> operateQueue=operateMap.get("start");
			if(null!=operateQueue&&operateQueue.size()>0){
				startService.start(operateQueue);
			}
		}

	}

	/**
	 * 停止任务
	 * @param controllerManager
	 */
	public void stop(ControllerManager controllerManager) {
		Assert.notNull(controllerManager, "controllerManager must be set!");  
		operateMap = controllerManager.getOperateMap();
		if (null != operateMap) {
			ConcurrentLinkedQueue<ConcurrentMap<String, String>> operateQueue=operateMap.get("stop");
			if(null!=operateQueue&&operateQueue.size()>0){
				stopService.stop(operateQueue);
			}
		}
	}
	
	

	/**
	 * 初始化,将需要停止的视频修复,并停止
	 */
	public void initStop(){
		stopService.initStopFile();
	}
	
	
	

}
