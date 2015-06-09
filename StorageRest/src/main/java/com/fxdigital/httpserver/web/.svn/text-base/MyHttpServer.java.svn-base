package com.fxdigital.httpserver.web;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.httpserver.handler.SearchHandler;
import com.fxdigital.httpserver.handler.StartHandler;
import com.fxdigital.httpserver.handler.StopHandler;
import com.fxdigital.rest.web.SearchController;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
@Component
public class MyHttpServer {
	
	
	
	private static final Logger logger = Logger.getLogger(MyHttpServer.class);

	@Autowired
	private StartHandler startHandler;
	
	@Autowired
	private StopHandler stopHandler;
	
	@Autowired
	private SearchHandler searchHandler;
	

	
	//启动服务，监听来自客户端的请求  
    public void httpserverService() throws IOException {  
    	int listenPort=8090;
        HttpServerProvider provider = HttpServerProvider.provider();  
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(listenPort), 100);//监听端口8090,能同时接 受100个请求  
        httpserver.createContext("/StorageRest/startrecord", startHandler);   
        httpserver.createContext("/StorageRest/stoprecord", stopHandler); 
        httpserver.createContext("/StorageRest/searchrecord", searchHandler); 
        httpserver.setExecutor(null);  
        httpserver.start();  
        logger.info("server started,the port is "+listenPort);  
    }  
}
