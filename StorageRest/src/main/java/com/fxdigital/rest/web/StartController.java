package com.fxdigital.rest.web;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.impl.AppServer;

@Controller
public class StartController implements Runnable{
	
	private static final Logger logger = Logger.getLogger(StartController.class);
	
	@Autowired
	private ControllerManager controllerManager;
	
	@Autowired
	private AppServer appServer;
	
	@RequestMapping(value = { "/startrecord" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestBody String name){
		HashMap<String,String> result=new HashMap<String,String>();
		UUID uuid = UUID.randomUUID();
		String markUUid=uuid.toString();
		JSONObject jsonObject=JSONObject.parseObject(name);
		String rtspUrl=jsonObject.getString("rtsp");
		String mark=jsonObject.getString("mark");
        ConcurrentMap<String,String> parameterMap=new ConcurrentHashMap();
        parameterMap.put("rstpurl", rtspUrl);
        parameterMap.put("mark", mark);
        parameterMap.put("markUUid", markUUid);
        ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> operateMap=controllerManager.putParameterMap("start", parameterMap);
        appServer.eventOn(this);
      
        logger.info("启动一次录像存储业务，相关参数 ："+parameterMap);
		return markUUid;
	}
	
	

	public void regApp(AppServer appServer){
		this.appServer=appServer;	
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		appServer.start();
	}
}
