package com.fxdigital.syncclient.util;

import java.io.File;
import java.io.FileInputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 读取数据库配置文件
 * @author hxht
 *
 */
public class ReadDBConfig {
	
	public static final String IP = getDBParameter("IP");
	public static final String DBType = getDBParameter("DBType");
	public static final String SN = getDBParameter("SN");
	public static final String PN = getDBParameter("PN");
	public static final String Port = getDBParameter("Port");
	public static final String Dialect = getDBParameter("Dialect");
	public static final String Driver = getDBParameter("Driver");
	public static final String Datasource = getDBParameter("Datasource");
	
	private static final String Default_IP = "127.0.0.1";
	private static final String Default_DBType = "mysql";
	private static final String Default_SN = "admin";
	private static final String Default_PN = "111";
	private static final String Default_Port = "3306";
	private static final String Default_Dialect = "org.hibernate.dialect.MySQLDialect";
	private static final String Default_Driver = "com.mysql.jdbc.Driver";
	private static final String Default_Datasource = "java://comp/env/jdbc/mysql";
	
	private static boolean isDefault = false;

	private static Element initDBParameters(){
		isDefault = false;
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/DB/DBSettings.xml";
		} else {
			path = "D:\\fxconf\\DB\\DBSettings.xml";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				return doc.getRootElement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(path+"文件不存在");
			isDefault = true;
		}
		return null;
	}
	
	/**
	 * 获得数据库参数
	 * @param key("IP":IP;"DBType":类型;"SN":用户名;"PN":密码;
	 * 				"Port":端口;"Dialect":方言包;"Driver":驱动类)
	 * @return
	 */
	private static String getDBParameter(String key) {
		if("IP".equalsIgnoreCase(key)){
			String ip = getDBIP();
			if(ip == null)
				return Default_IP;
			else
				return ip;
		}
		String parameter = null;
		try{
			Element root = initDBParameters();
			parameter = root.getChild(key).getTextTrim();
			if(isDefault){
				if("DBType".equalsIgnoreCase(key))
					return Default_DBType;
				if("SN".equalsIgnoreCase(key))
					return Default_SN;
				if("PN".equalsIgnoreCase(key))
					return Default_PN;
				if("Port".equalsIgnoreCase(key))
					return Default_Port;
				if("Dialect".equalsIgnoreCase(key))
					return Default_Dialect;
				if("Driver".equalsIgnoreCase(key))
					return Default_Driver;
				if("Datasource".equalsIgnoreCase(key))
					return Default_Datasource;
			}else if(parameter == null){
				System.out.println("不存在的数据库参数："+key+",数据库连接初始化失败！");
				//退出
				System.exit(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return parameter;
	}

	private static String getDBIP() {
		String path = "";
		String ip = null;
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(path+"文件不存在");
		}
		return ip;
	}
}
