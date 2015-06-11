package com.hibernate.bean;

/**
 * SyncServerinfosync entity. @author MyEclipse Persistence Tools
 */

public class SyncServerinfosync implements java.io.Serializable {

	// Fields

	private Integer id;
	private String serverId;
	private String serverIp;
	private String serverName;
	private String mqIp;
	private Integer mqPort;
	private String syncId;

	// Constructors

	/** default constructor */
	public SyncServerinfosync() {
	}

	/** full constructor */
	public SyncServerinfosync(String serverId, String serverIp,
			String serverName, String mqIp, Integer mqPort, String syncId) {
		this.serverId = serverId;
		this.serverIp = serverIp;
		this.serverName = serverName;
		this.mqIp = mqIp;
		this.mqPort = mqPort;
		this.syncId = syncId;
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

	public String getSyncId() {
		return this.syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

}