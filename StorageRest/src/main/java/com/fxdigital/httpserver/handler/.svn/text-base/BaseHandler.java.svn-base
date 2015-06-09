package com.fxdigital.httpserver.handler;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class BaseHandler implements HttpHandler {
	private static final Logger logger = Logger.getLogger(BaseHandler.class);

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		try {
		handle(httpExchange,"");
		} catch(Exception e) {
			logger.info("执行出错 "+e);
		}
	}
	
	public abstract void handle(HttpExchange httpExchange,String flag) throws IOException;
	

}
