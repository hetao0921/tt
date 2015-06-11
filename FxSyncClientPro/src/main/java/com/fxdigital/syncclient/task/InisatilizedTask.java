package com.fxdigital.syncclient.task;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class InisatilizedTask {
	
	private static final Logger log = Logger.getLogger(InisatilizedTask.class);

	private ApplicationContext context;
	private InstantiationTracingBeanPostProcessor instantiationTracingBeanPostProcessor;
	
	public static void main(String[] args) {
		InisatilizedTask inisatilizedTask=new InisatilizedTask();
		inisatilizedTask.run();
	}
	
	
	public void run(){	
		log.info("-------encoding" + System.getProperty("file.encoding"));
		context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		instantiationTracingBeanPostProcessor=(InstantiationTracingBeanPostProcessor) context.getBean("instantiationTracingBeanPostProcessor");	
		log.info("instantiationTracingBeanPostProcessor"+instantiationTracingBeanPostProcessor);
	}

}
