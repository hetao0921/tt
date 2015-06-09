package com.fxdigital.tcp.util.message;

import com.fxdigital.tcp.util.Msg;


public class MysqlSyncMessage extends MysqlMessage {

	/** 数据库主从同步时的 用户名 **/
	private String username;
	
	/** 数据库主从同步时的 密码 **/
	private String password;
	
	/** 数据库主从同步时的 同步logfile名**/
	private String logfile;
	
	/** 数据库主从同步时的  同步log position **/
	private int logpos;
	
	public MysqlSyncMessage(Msg msg) {
		
		super(MessageConst.MESSAGE_TYPE_MYSQL_SYNC, msg);
		
		username = msg.getUsername();
		password = msg.getPassword();
		logfile = msg.getLogfile();
	    logpos = msg.getLogpos();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getLogfile() {
		return logfile;
	}

	public int getLogpos() {
		return logpos;
	}

	public void setLogpos(int logpos) {
		this.logpos = logpos;
	}
	
 
}
