package com.fxdigital.mysqlsync.bundle;

/**
 * @author Administrator
 * mysql导入导出实体类
 *
 */
public class MySqlDumpBean {
	
	private String sendpath;
	private String receivepath;
	public String getSendpath() {
		return sendpath;
	}
	public void setSendpath(String sendpath) {
		this.sendpath = sendpath;
	}
	public String getReceivepath() {
		return receivepath;
	}
	public void setReceivepath(String receivepath) {
		this.receivepath = receivepath;
	}
	
	

}
