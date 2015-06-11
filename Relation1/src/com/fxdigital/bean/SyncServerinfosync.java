package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncServerinfosync entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_serverinfosync", catalog = "nvmp")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ServerID")
	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Column(name = "ServerIP")
	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(name = "ServerName")
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Column(name = "MqIP")
	public String getMqIp() {
		return this.mqIp;
	}

	public void setMqIp(String mqIp) {
		this.mqIp = mqIp;
	}

	@Column(name = "MqPort")
	public Integer getMqPort() {
		return this.mqPort;
	}

	public void setMqPort(Integer mqPort) {
		this.mqPort = mqPort;
	}

	@Column(name = "SyncID")
	public String getSyncId() {
		return this.syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

}