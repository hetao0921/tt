package com.ui.listener;

import com.db.base.JDBCConnection;

public class TestListener{
	
	private String hostname;
	private String port;
	private String username;
	private String password;
	
	
	public TestListener(String hostname,String port,String username,String password){
		this.hostname=hostname;
		this.port=port;
		this.username=username;
		this.password=password;
	}


	
	public int TestDB(){
		 System.out.println("aaa"+hostname+port+username+password);
		 JDBCConnection mysqlConn=new JDBCConnection(hostname,port,username,password);
	     int flag=mysqlConn.testConnection();
	     return flag;
	}

}
