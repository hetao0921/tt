package com.ui.listener;

import java.sql.Connection;

import com.db.base.JDBCConnection;

public class DatatableListener {
	private String hostname;
	private String port;
	private String username;
	private String password;
	
	private String database;
	
	
	public DatatableListener(String hostname,String port,String username,String password,String database){
		this.hostname=hostname;
		this.port=port;
		this.username=username;
		this.password=password;
		this.database=database;
	}
	
	public Connection getConnection(){
		Connection conn=null;
		 JDBCConnection mysqlConn=new JDBCConnection(hostname,port,username,password,database);
		 conn=mysqlConn.getConnection();
		return conn;
		
	}
}
