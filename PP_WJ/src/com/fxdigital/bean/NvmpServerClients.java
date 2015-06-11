package com.fxdigital.bean;

import java.sql.Timestamp;

/**
 * NvmpServerClients entity. @author MyEclipse Persistence Tools
 */

public class NvmpServerClients implements java.io.Serializable {

	// Fields

	private String clientId;
	private String clientIp;
	private Timestamp starttime;
	// Constructors

	/** default constructor */
	public NvmpServerClients() {
	}

	/** full constructor */
	public NvmpServerClients(String clientIp, Timestamp starttime) {
		this.clientIp = clientIp;
		this.starttime = starttime;
	}

	// Property accessors

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

}