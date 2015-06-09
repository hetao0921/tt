package com.fxdigital.rest.web;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.storage.service.impl.SetService;

@Controller
public class SetController {
	
	
	private static final Logger logger = Logger.getLogger(SetController.class);
	
	@Autowired
	private SetService setService;
	
	
	@RequestMapping(value = { "/setstorage" }, method = RequestMethod.POST)
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
        
        String storageIp="192.168.1.2";
        int storagePort=8001;
        setService.save(storageIp, storagePort);
      
        logger.info("启动一次录像存储业务，相关参数 ："+parameterMap);
		return markUUid;
	}
}
