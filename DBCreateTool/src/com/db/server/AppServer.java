package com.db.server;

import org.apache.log4j.PropertyConfigurator;

import com.db.service.GetExistInfo;

public class AppServer {
	static {
		PropertyConfigurator.configure("conf/log4j.properties");
		System.setProperty("file.encoding", "UTF-8");
//		new ReadXml().read();
	}
	

	
	public void run(){
		GetExistInfo getExistInfo=new GetExistInfo();
		getExistInfo.isExist();
	}

	
	
	public static void main(String[] args) {
		AppServer appServer=new AppServer();
		appServer.run();
	}
}
