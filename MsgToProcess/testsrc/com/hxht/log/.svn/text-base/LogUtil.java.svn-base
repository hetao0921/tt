package com.hxht.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {

	private static Logger filelog = Logger.getLogger("logFile");
	private static Logger Consolelog = Logger.getLogger("console");
	private static Logger Businesslog = Logger.getLogger("logFile");
	private static Logger ERRORlog = Logger.getLogger("errorFile");
	private static Logger VideoInfo = Logger.getLogger("logFile");
	private static Logger SessionInfo = Logger.getLogger("logFile");
	private static Logger DebugInfo = Logger.getLogger("logFile");
	private static Logger DebugInfoRecive = Logger.getLogger("logFile");
	private static Logger DeviceManage = Logger.getLogger("logFile");
	private static Logger TestInfo = Logger.getLogger("logFile");
	
	
	
	static {
		String confPath;
		if (System.getProperty("os.name").equals("Linux")) 
			confPath = "conf/log4j.properties";
		else
			confPath = "E:\\Workspaces\\eclipse\\ActiveMQ5.5\\conf\\log4j.properties";
			  
	    try {
    	    PropertyConfigurator.configure(confPath);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
	
//	private LogUtil() {
//	}
//
//	private static LogUtil logger;
//
//	// private static Logger logger = Logger.getLogger("s");
//
//	public static LogUtil getUtill() {
//		if (logger == null)
//			logger = new LogUtil();
//		return logger;
//	}
	
	
	public static void TestInfo(Object message) {
		TestInfo.debug( "uuid:"+message);
	}
	
	public static void DeviceManageInfo(Object message) {
		DeviceManage.debug(message);
	}
	
	
	public static void ReciveDebugInfo(Object message) {
		DebugInfoRecive.debug(message);
	}
	
	public static void DebugInfo(Object message) {
		DebugInfo.debug(message);
	}
	
	
	public static void VideoInfo(Object message){
		filelog.info(message);
		VideoInfo.info(message);
	}
	public static void SessionInfo(Object message){
		SessionInfo.info(message);
	}
	
	
	public static void BusinessDebug(Object message) {
		Businesslog.debug(message);
		filelog.debug(message);

	}
	
	
	public static void BusinessInfo(Object message){
		filelog.info(message);
		Businesslog.info(message);	
	}
	public static void BusinessError(Object message){
		filelog.error(message);
		Businesslog.error(message);	
		ERRORlog.error(message);
	}
	
	

	public static void debug(Object message) {
		filelog.debug(message);
//		Consolelog.debug(message);

	}

	public  static void info(Object message) {
		//System.out.println(message);
		filelog.info(message);
//		Consolelog.info(message);
	}

	public static void error(Object message) {
		//System.out.println(message);
		filelog.error(message);
//		Consolelog.error(message);
//		ERRORlog.error(message);
	}
	
	public static void error(Object message,Throwable e) {
		//System.out.println(message);
		filelog.error(message,e);
//		Consolelog.error(message);
//		ERRORlog.error(message);
	}

	public static void warn(Object message) {
		//System.out.println(message);
		filelog.warn(message);
//		Consolelog.warn(message);
	}

	public static void fatal(Object message) {
		filelog.fatal(message);
//		Consolelog.fatal(message);
	}	
	
	public static boolean changeLevForFileLog(String message) {
		boolean b = false;
		try {
		Level le = Level.toLevel(message);
		filelog.setLevel(le);
		} catch(Exception e) {
			b = false;
		}
		return b;
	}	
	
	public static boolean changeLevForConsolelog(String message) {
		boolean b = false;
		try {
		Level le = Level.toLevel(message);
		Consolelog.setLevel(le);
		} catch(Exception e) {
			b = false;
		}
		return b;
	}	
	
	public static boolean save() {
		boolean b = false;
		try {
			Properties property = new Properties();
			property.load(new FileInputStream("E:\\log4j.properties"));

			
			String str =property.getProperty("log4j.logger.Console");
			String[] ss = str.split(",");
			property.setProperty("log4j.logger.Console", str.replace(ss[0], Consolelog.getLevel().toString()));
			
			str =property.getProperty("log4j.logger.File");
			ss = str.split(",");
			property.setProperty("log4j.logger.File", str.replace(ss[0], filelog.getLevel().toString()));
			
			property.store(new FileOutputStream("E:\\log4j.properties"),"log4j.properties");
			
		} catch(Exception e) {
			b = false;
		}
		return b;
	}	
	
	
	
}
