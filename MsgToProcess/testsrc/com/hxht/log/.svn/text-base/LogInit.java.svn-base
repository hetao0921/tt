package com.hxht.log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class LogInit extends HttpServlet{
	
	private static final long serialVersionUID = -8031637087044731406L;
	
	public LogInit() {
	    super();
	  } 

	 public void init() throws ServletException {
		    super.init();
		    String file = getInitParameter("log4jfile");  
		    
		    
		    String prefix = getServletContext().getRealPath("/"); 
		    LogConfig.init(prefix, file); //��ʼ��LOG4J
		  }
	 
	 

}
