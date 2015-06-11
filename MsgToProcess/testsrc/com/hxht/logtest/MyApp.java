package com.hxht.logtest;


import java.io.File;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class MyApp{
	
	 static Logger logger = Logger.getLogger("console");
	 
	 //String file = getInitParameter("log4jfile");  
	// String prefix = getServletContext().getRealPath("/");
	
	
	public MyApp(){
		super();
		logger.warn("log------------");
	
		
	}
	
	public void init(){
	   // super.init();
	    //String file = getInitParameter("log4jfile");  
	    
	    
	    //String prefix = getServletContext().getRealPath("/"); 
	    
	    //System.out.println(file);
		 
		 //System.out.println(prefix);
		 
		 System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));  
		  
	        System.out.println(MyApp.class.getClassLoader().getResource(""));  
	  
	        System.out.println(ClassLoader.getSystemResource(""));  
	        System.out.println(MyApp.class.getResource(""));  
	        System.out.println(MyApp.class.getResource("/"));
	        //Class�ļ�����·��
	        System.out.println(new File("/").getAbsolutePath());  
	        System.out.println(System.getProperty("user.dir"));  
	System.out.println(new File("C:\\ccc\\123").getParent()); //��ȡ��һ��Ŀ¼
	    
	   // LogConfig.init(prefix, file); //��ʼ��LOG4J
	  }
 
	
	
	public static void main(String[] args) {
		// BasicConfigurator replaced with PropertyConfigurator.
		PropertyConfigurator.configure("conf/log4j.properties");
		logger.info("Entering application.");
		// logger.debug("testing,testing");
		 logger.debug( " debug " );
		 logger.error( " error " );
		
		
		logger.info("Exiting application.");
		
		 MyApp a=new MyApp();
		
			a.init();
		
		
		 
		 
		 
	}

}
