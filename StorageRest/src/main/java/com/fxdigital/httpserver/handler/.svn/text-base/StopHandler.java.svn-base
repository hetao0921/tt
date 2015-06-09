package com.fxdigital.httpserver.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fxdigital.httpserver.handler.util.HandlerUtil;
import com.fxdigital.rest.web.StopController;
import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.impl.AppServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
@Controller
public class StopHandler extends BaseHandler implements Runnable {  
	private static final Logger logger = Logger.getLogger(StopHandler.class);
	@Autowired
	private ControllerManager controllerManager;
	
	private AppServer appServer;
	
	public void handle(HttpExchange httpExchange, String flag) throws IOException {
		HandlerUtil handlerUtil=new HandlerUtil();
		String requestStr=handlerUtil.processInput(httpExchange);
        logger.info("client requestStr:"+requestStr);
        String responseMsg =stopRecord(requestStr); 
        handlerUtil.processOutput(httpExchange,responseMsg);                                
    }  
    
    
    
    public String stopRecord(String mark){

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
