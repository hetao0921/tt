/**
 * 
 */
package com.fxdigital.authority.pub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author lizehua
 *
 */
public class InitSysParamServlet extends HttpServlet{
	  public InitSysParamServlet() {
		    super();
	
	  }
	  /**
	   * 初始化参数启动
	   */
	  public void init() throws ServletException {
	    super.init();
	    String file = getInitParameter("log4jfile");  
	    String prefix = getServletContext().getRealPath("/"); 

	    System.out.println("******************Load log4j config.");
	    System.out.println("******************the prefix is:" + prefix);
	    if (file==null){
	      file = "WEB-INF/classes/log4j.properties";
	    }
	    String strfile = prefix + file;
	    if(prefix != null) {
	      PropertyConfigurator.configure(strfile);
	    }
	    Log logger = LogFactory.getLog(InitSysParamServlet.class);
	    logger.info("******************Load log4j config finish.");
	  
	    
	
	    System.out.println("******************the map_log_control reset ok.");
	  }
	  
}
