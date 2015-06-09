package fxdigital.util;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

/**
 * 日志工具
 * @author fucz
 * @version 2014-7-17
 */
public class Log4jUtil {
	
//	static{
//		new ReadXml().read();
//	}
//	
//	static class ReadXml{
//		public void read(){
//			//log4j初始化
//			try{
//				InputStream is=this.getClass().getResourceAsStream("/conf/log4j.properties");
//				Properties pp = new Properties();
//				try {
//					pp.load(is);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				PropertyConfigurator.configure(pp);
//			}catch(Exception e){
//				System.out.println("加载外部log4j配置文件...");
//				PropertyConfigurator.configure("conf/log4j.properties");
//			}
//		}
//	}
	
	public static void debug(Class<?> clazz,String msg){
		Logger.getLogger(clazz).debug(msg);
		printToConsole("DEBUG",clazz,msg);
	}
	public static void debug(Class<?> clazz,String msg,Throwable t){
		Logger.getLogger(clazz).debug(msg,t);
		printToConsole("DEBUG",clazz,msg,t);
	}
	
	
	public static void info(Class<?> clazz,String msg){
		Logger.getLogger(clazz).info(msg);
		printToConsole("INFO",clazz,msg);
	}
	public static void info(Class<?> clazz,String msg,Throwable t){
		Logger.getLogger(clazz).info(msg,t);
		printToConsole("INFO",clazz,msg,t);
	}
	
	
	public static void warn(Class<?> clazz,String msg){
		Logger.getLogger(clazz).warn(msg);
		printToConsole("WARN",clazz,msg);
	}
	public static void warn(Class<?> clazz,String msg,Throwable t){
		Logger.getLogger(clazz).warn(msg,t);
		printToConsole("WARN",clazz,msg,t);
	}
	
	
	public static void error(Class<?> clazz,String msg){
		Logger.getLogger(clazz).error(msg);
		printToConsole("ERROR",clazz,msg);
	}
	public static void error(Class<?> clazz,String msg,Throwable t){
		Logger.getLogger(clazz).error(msg,t);
		printToConsole("ERROR",clazz,msg,t);
	}
	
	
	@SuppressWarnings("unchecked")
	private static void printToConsole(String level,Class<?> clazz,String msg){
		Enumeration<Appender> e = Logger.getRootLogger().getAllAppenders();
		if(!e.hasMoreElements()){
			StringBuilder sb = new StringBuilder();
			sb.append(level).append(" [").append(clazz.getName()).append("]");
			sb.append(" - ").append(msg);
			System.out.println(sb.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void printToConsole(String level,
			Class<?> clazz,String msg,Throwable t){
		Enumeration<Appender> e = Logger.getRootLogger().getAllAppenders();
		if(!e.hasMoreElements()){
			StringBuilder sb = new StringBuilder();
			sb.append(level).append(" [").append(clazz.getName()).append("]");
			sb.append(" - ").append(msg);
			System.err.println(sb.toString());
			t.printStackTrace();
		}
	}
	
}
