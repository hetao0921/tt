package com.fxdigital.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncRegistercenterinfossync entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_registercenterinfossync", catalog = "nvmp")
public class SyncRegistercenterinfossync implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerId;
	private String centerIp;
	private String channelName;
	private String serverId;
	private Timestamp registerTime;
	private String syncId;

	// Constructors

	/** default constructor */
	public SyncRegistercenterinfossync() {
	}

	/** full constructor */
	public SyncRegistercenterinfossync(String centerId, String centerIp,
			String channelName, String serverId, Timestamp registerTime,
			String syncId) {
		this.centerId = centerId;
		this.centerIp = centerIp;
		this.channelName = channelName;
		this.serverId = serverId;
		this.registerTime = registerTime;
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

	@Column(name = "CenterID")
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "CenterIP")
	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	@Column(name = "ChannelName")
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "ServerID")
	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Column(name = "RegisterTime", length = 19)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "SyncID")
	public String getSyncId() {
		return this.syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

}