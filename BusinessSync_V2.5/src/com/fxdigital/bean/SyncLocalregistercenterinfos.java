package com.fxdigital.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncLocalregistercenterinfos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_localregistercenterinfos", catalog = "nvmp")
public class SyncLocalregistercenterinfos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerId;
	private String centerIp;
	private String channelName;
	private Timestamp registerTime;

	// Constructors

	/** default constructor */
	public SyncLocalregistercenterinfos() {
	}

	/** full constructor */
	public SyncLocalregistercenterinfos(String centerId, String centerIp,
			String channelName, Timestamp registerTime) {
		this.centerId = centerId;
		this.centerIp = centerIp;
		this.channelName = channelName;
		this.registerTime = registerTime;
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

	@Column(name = "RegisterTime", length = 19)
	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

}