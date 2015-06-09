package com.fxdigital.mysqlsync.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
	private static final Logger logger = Logger.getLogger(LogUtil.class);
	
    static {
		String confPath;
		String basePath=System.getProperty("user.dir");
		if (System.getProperty("os.name").equals("Linux")) //操作系统名称
		{
			confPath =basePath+ "/resources/log4j.properties";
		}
		else
		{
//			logger.info("==============="+System.getProperty("user.dir"));
			confPath = basePath+"\\resources\\log4j.properties";
		}
			  
	    try {
	    	logger.info("-------------"+confPath);
    	    PropertyConfigurator.configure(confPath);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
	
    private static Logger filelog = Logger.getLogger("File");
	private static Logger Consolelog = Logger.getLogger("Console");
	private static Logger Businesslog = Logger.getLogger(LogUtil.class);
	private static Logger ERRORlog = Logger.getLogger("ERRORlog");
	private static Logger VideoInfo = Logger.getLogger("VideoInfo");
	private static Logger SessionInfo = Logger.getLogger("SessionInfo");
	private static Logger DebugInfo = Logger.getLogger("DebugInfo");
	private static Logger DebugInfoRecive = Logger.getLogger("DebugInfoRecive");
	private static Logger DeviceManage = Logger.getLogger("DeviceManage");
	private static Logger TestInfo = Logger.getLogger("TestInfo");
	private static Logger ConnectInfo = Logger.getLogger("ConnectInfo");
	private static Logger DisposeInfo = Logger.getLogger("DisposeInfo");

	
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
		filelog.debug(message);
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
//		filelog.debug(message);
		Businesslog.info(message);	
	}
	public static void BusinessError(Object message){
		filelog.error(message);
		Businesslog.error(message);	
		ERRORlog.error(message);
	}
	
	
	public static void connectError(Object message){
		ConnectInfo.error(message);
	}
	

	public static void debug(Object message) {
		filelog.debug(message);
		Consolelog.debug(message);

	}

	public  static void info(Object message) {
		filelog.info(message);
		Consolelog.info(message);
	}

	public static void error(Object message) {

		filelog.error(message);
		Consolelog.error(message);
		ERRORlog.error(message);
	}

	public static void warn(Object message) {
		filelog.warn(message);
		Consolelog.warn(message);
	}

	public static void fatal(Object message) {
		filelog.fatal(message);
		Consolelog.fatal(message);
	}	
	
	public static void except(Exception e) {
		SessionInfo.error(e, e);
	}
	
	public static void sessionException(Exception e) {
		SessionInfo.error(e, e);
	}
	
	public static void performanceMonitor(Object message){
		DisposeInfo.debug(message);
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
	    //¶ÔÎÄŒþ²Ù×÷£¬œøÐÐ±£Žæµ±Ç°µÄlevŒ¶±ð
			Properties property = new Properties();
			property.load(new FileInputStream("E:\\log4j.properties"));
//			log4j.logger.Console=debug,appender1
//			log4j.logger.File=error,appender2
			
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
	
	public static void main(String[] args) {
		//LogUtil.Businesslog.info("aa");
		logger.info("bbb");
	}
	
}
