package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MqLocalcenterinfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mq_localcenterinfotab", catalog = "nvmp")
public class MqLocalcenterinfotab implements java.io.Serializable {

	// Fields

	private String centerId;
	private String centerIp;
	private Integer centerPort;

	// Constructors

	/** default constructor */
	public MqLocalcenterinfotab() {
	}

	/** full constructor */
	public MqLocalcenterinfotab(String centerId, String centerIp,
			Integer centerPort) {
		this.centerId = centerId;
		this.centerIp = centerIp;
		this.centerPort = centerPort;
	}

	// Property accessors
	@Id
	@Column(name = "CenterID", unique = true, nullable = false, length = 64)
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "CenterIP", nullable = false, length = 32)
	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	@Column(name = "CenterPort", nullable = false)
	public Integer getCenterPort() {
		return this.centerPort;
	}

	public void setCenterPort(Integer centerPort) {
		this.centerPort = centerPort;
	}

}