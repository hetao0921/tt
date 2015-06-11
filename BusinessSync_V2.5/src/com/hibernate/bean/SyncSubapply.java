package com.hibernate.bean;

import java.sql.Timestamp;

/**
 * SyncSubapply entity. @author MyEclipse Persistence Tools
 */

public class SyncSubapply implements java.io.Serializable ,IArrayPoJo{

	// Fields

	private Integer id;
	private String serverId;
	private String serverIp;
	private String serverName;
	private String mqIp;
	private Integer mqPort;
	private Integer status;
	private Timestamp applyTime;
	
	
	@Override
	public String[] toStringArray() {
		String[] result = new String[8];
		result[0] = id.toString();
		result[1] = serverId.toString();
		result[2] = serverIp.toString();
		result[3] = serverName.toString();
		result[4] = mqIp.toString();
		result[5] = mqPort.toString();
		result[6] = status.toString();
		result[7] = applyTime.toString();
		return result;
	}

	// Constructors

	/** default constructor */
	public SyncSubapply() {
	}

	/** full constructor */
	public SyncSubapply(String serverId, String serverIp, String serverName,
			String mqIp, Integer mqPort, Integer status, Timestamp applyTime) {
		this.serverId = serverId;
		this.serverIp = serverIp;
		this.serverName = serverName;
		this.mqIp = mqIp;
		this.mqPort = mqPort;
		this.status = status;
		this.applyTime = applyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getMqIp() {
		return this.mqIp;
	}

	public void setMqIp(String mqIp) {
		this.mqIp = mqIp;
	}

	public Integer getMqPort() {
		return this.mqPort;
	}

	public void setMqPort(Integer mqPort) {
		this.mqPort = mqPort;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

}