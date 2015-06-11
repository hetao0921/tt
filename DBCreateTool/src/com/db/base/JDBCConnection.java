package com.db.base;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class JDBCConnection extends ConnectionBase {

	private static final Logger logger = Logger.getLogger(JDBCConnection.class);
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";

	public static String DBURL = "jdbc:mysql://localhost:3306/";

	public static String username = "root";

	public static String password = "";

	public String hostname;

	public String port;

	public String database;

	public JDBCConnection(String hostname, String port, String username,
			String password) {
		if ("".equals(hostname) || null == hostname || (".").equals(hostname)) {
			hostname = "localhost";
		}
		if ("".equals(username) || null == username) {
			username="admin";
		}
		if ("".equals(password) || null == password) {
			password="111";
		}
		this.DBURL = "jdbc:mysql://" + hostname + ":" + port + "/nvmp";
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		// testConnection();
	}

	public JDBCConnection(String hostname, String port, String username,
			String password, String database) {
		if ("".equals(hostname) || null == hostname || (".").equals(hostname)) {
			hostname = "localhost";
		}
		if ("".equals(username) || null == username) {
			username="admin";
		}
		if ("".equals(password) || null == password) {
			password="111";
		}
		if ("".equals(database) || null == database) {
			password="nvmp";
		}
		this.DBURL = "jdbc:mysql://" + hostname + ":" + port + "/" + database
				+ "";
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		// testConnection();
	}

	public Connection getConnection() {
		Connection con = null; // 表示数据库的连接对象
		try {
			Class.forName(DBDRIVER); // 1、使用CLASS 类加载驱动程序
			logger.info("数据库连接字符串: " + DBURL);
			con = DriverManager.getConnection(DBURL, username, password); // 2、连接数据库
		} catch (Exception e) {
			System.out.println(e);
			logger.info("数据库连接获取失败" + e);
		}
		return con;
	}

	public String getTestStr() {
		String result = "";
		result = "/usr/local/mysql/support-files/mysql.server stop\n";
		result += "/usr/local/mysql/support-files/mysql.server start\n";
		result += "echo 'exit' | /usr/local/mysql/bin/mysql -h" + hostname
				+ " -P" + port + " -u" + username + " -p" + password + "";
		System.out.println("测试数据库连接字符串： " + result);
		return result;
	}

	

}
