package com.fxdigital.httpserver.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.httpserver.handler.util.HandlerUtil;
import com.fxdigital.rest.web.StartController;
import com.fxdigital.rest.web.manager.impl.ControllerManager;
import com.fxdigital.storage.app.impl.AppServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
@Controller
public class StartHandler extends BaseHandler implements Runnable{  
	
	private static final Logger logger = Logger.getLogger(StartHandler.class);
	
	@Autowired
	private ControllerManager controllerManager;
	
	
	private AppServer appServer;
	
	
	public void handle(HttpExchange httpExchange, String flag) throws IOException { 
		HandlerUtil handlerUtil=new HandlerUtil();
		String requestStr=handlerUtil.processInput(httpExchange);
        logger.info("client requestStr:"+requestStr);
        String responseMsg = startRecord(requestStr);   //响应信息   
        handlerUtil.processOutput(httpExchange,responseMsg);                    
          
    }  
    
    
    
    public String startRecord(String name){
    	logger.info("开始录像参数 : "+name);
		HashMap<String,String> result=new HashMap<String,String>();
		UUID uuid = UUID.randomUUID();
		String markUUid=uuid.toString();
		JSONObject jsonObject=null;
		try{
			 jsonObject=JSONObject.parseObject(name);
		}catch(Exception e){
			logger.info("转换json出错 ："+e);
		}
		String rtspUrl=jsonObject.getString("rtsp");
		
		String mark=jsonObject.getString("mark");
        ConcurrentMap<String,String> parameterMap=new ConcurrentHashMap();
        parameterMap.put("rstpurl", rtspUrl);
        parameterMap.put("mark", mark);
        parameterMap.put("markUUid", markUUid);
        ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> operateMap=controllerManager.putParameterMap("start", parameterMap);
        logger.info("调用应用层");
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
