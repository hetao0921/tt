package com.fxdigital.rest.web;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.impl.AppServer;
@Controller
public class StopController implements Runnable{
	private static final Logger logger = Logger.getLogger(StopController.class);
	@Autowired
	private ControllerManager controllerManager;
	@Autowired
	private AppServer appServer;
	
	@RequestMapping(value = { "/stoprecord/{mark}" }, method = RequestMethod.DELETE)
	@ResponseBody
	public String upload(@PathVariable String mark){
//		JSONObject jsonObject=JSONObject.parseObject(mark);
//		String rtspUrl=jsonObject.getString("rtsp");
//		String markUUid=jsonObject.getString("markUUid");
		
		HashMap<String,String> result=new HashMap<String,String>();
        ConcurrentMap<String,String> parameterMap=new ConcurrentHashMap();
        parameterMap.put("rstpurl", "http//192.168.1.85/1");
        parameterMap.put("tt", mark);
        parameterMap.put("markUUid", mark);
        ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> operateMap=controllerManager.putParameterMap("stop", parameterMap);
        appServer.eventOn(this);
        logger.info("启动一次录像停止业务，相关参数 ："+parameterMap);
		return "index";
	}
	

	
	
	public void regApp(AppServer appServer){
		this.appServer=appServer;
		
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		appServer.stop();
	}
}
