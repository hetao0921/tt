package com.hibernate.bean;

/**
 * MqLocalcenterinfotab entity. @author MyEclipse Persistence Tools
 */

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

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	public Integer getCenterPort() {
		return this.centerPort;
	}

	public void setCenterPort(Integer centerPort) {
		this.centerPort = centerPort;
	}

}