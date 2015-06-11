package com.hxht.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.hxht.util.StringUtils;

 




public class LogConfig {
	
	  public static void init(String prefix, String file) {
		    if (!StringUtils.isStrEmpty(file)){
		      file = "WEB-INF/classes/log4j.properties";
		    }
		    String strfile = prefix + file;
		    if(prefix != null) {
		      PropertyConfigurator.configure(strfile);
		    }
		    Log logger = LogFactory.getLog(LogConfig.class);
		    logger.info("******************Load log4j config finish.");
		  }

}
