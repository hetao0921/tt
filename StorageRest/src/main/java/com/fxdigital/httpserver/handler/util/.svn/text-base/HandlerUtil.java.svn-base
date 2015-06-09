package com.fxdigital.httpserver.handler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

public class HandlerUtil {
	private static final Logger logger = Logger.getLogger(HandlerUtil.class);

	
	public  String processInput(HttpExchange httpExchange) throws IOException{
		    String requestMethod=httpExchange.getRequestMethod();
		    String requestStr="";
		    if(requestMethod.equals("POST")){
			    InputStream in = httpExchange.getRequestBody(); //获得输入流  
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));  
		        String temp = null;  
		        while((temp = reader.readLine()) != null) {
		            requestStr+=temp;
		        }  
		        in.close();
		    }
		    if(requestMethod.equals("GET")){
		    	String requestURI=httpExchange.getRequestURI().toString();
		    	String[] tempURI=requestURI.split("\\?");
		    	requestStr= tempURI[tempURI.length-1];
		    }
		    if(requestMethod.equals("DELETE")){
		    	String requestURI=httpExchange.getRequestURI().toString();
		    	String[] tempURI=requestURI.split("/");
		    	requestStr= tempURI[tempURI.length-1];
		    }
	        logger.info("client requestStr:"+requestStr);
	        return requestStr;
	}
	
	
	public  HashMap<String,Object> processSearch(String requestStr){
		HashMap<String,Object> tempMap=new HashMap<String,Object>();
		String requestTemp=requestStr.replace("%20", " ");
		String[] paraStr=requestTemp.split("&");
		for(String str:paraStr){
			tempMap.put(str.split("=")[0], str.split("=")[1]);
		}
		
		return tempMap;
	}
	
	public  void processOutput(HttpExchange httpExchange,String responseMsg) throws IOException{
		    httpExchange.getResponseHeaders().set("content-type", "text/html;charset=UTF-8");
		    httpExchange.sendResponseHeaders(200, responseMsg.getBytes().length); //设置响应头属性及响应信息的长度
	       
	        OutputStream out = httpExchange.getResponseBody();  //获得输出流  
	        
	        out.write(responseMsg.getBytes());  
	        out.flush();  
	        out.close();
	        httpExchange.close(); 
	}
}
