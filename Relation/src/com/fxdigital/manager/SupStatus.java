package com.fxdigital.manager;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author fucz
 * @version 2014-7-14
 */
public class SupStatus {
	
	private String serverID;
	private String serverIP;
	private String serverName;
	private String mqIP;
	private int mqPort;
	private String mqPostAddress;
	private String status;
	private Timestamp applyTime;
	
	
	public String toString(){
		return JSON.toJSONString(this);
	}


	public String getServerID() {
		return serverID;
	}


	public void setServerID(String serverID) {
		this.serverID = serverID;
	}


	public String getServerIP() {
		return serverIP;
	}


	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getMqIP() {
		return mqIP;
	}


	public void setMqIP(String mqIP) {
		this.mqIP = mqIP;
	}


	public int getMqPort() {
		return mqPort;
	}


	public void setMqPort(int mqPort) {
		this.mqPort = mqPort;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getApplyTime() {
		return applyTime;
	}


	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}


	public String getMqPostAddress() {
		return mqPostAddress;
	}


	public void setMqPostAddress(String mqPostAddress) {
		this.mqPostAddress = mqPostAddress;
	}
	
}
