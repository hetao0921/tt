package com.fxdigital.httpserver.app;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class startServer {
	
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
		System.setProperty("file.encoding", "UTF-8");
//		new ReadXml().read();
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("resources/applicationContext.xml");

//		ClassPathXmlApplicationContext resource= new ClassPathXmlApplicationContext(new String[]
//				{"src/main/resources/applicationContext.xml","src/main/resources/fxdigital-dao.xml","src/main/resources/fxdigital-service.xml"});
		
	}

}
