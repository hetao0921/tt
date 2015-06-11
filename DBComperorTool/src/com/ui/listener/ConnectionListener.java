package com.ui.listener;

import java.sql.Connection;

import com.db.base.JDBCConnection;

public class ConnectionListener {
	private String hostname;
	private String port;
	private String username;
	private String password;
	
	
	public ConnectionListener(String hostname,String port,String username,String password){
		this.hostname=hostname;
		this.port=port;
		this.username=username;
		this.password=password;
	}
	
	
	public Connection getConnection(){
		Connection conn=null;
		 JDBCConnection mysqlConn=new JDBCConnection(hostname,port,username,password);
		 conn=mysqlConn.getConnection();
		return conn;
		
	}
}
