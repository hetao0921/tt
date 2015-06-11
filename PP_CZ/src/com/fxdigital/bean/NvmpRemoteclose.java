package com.fxdigital.bean;

/**
 * NvmpRemoteclose entity. @author MyEclipse Persistence Tools
 */

public class NvmpRemoteclose implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deviceId;
	private String remoteStatus;

	// Constructors

	/** default constructor */
	public NvmpRemoteclose() {
	}

	/** full constructor */
	public NvmpRemoteclose(String deviceId, String remoteStatus) {
		this.deviceId = deviceId;
		this.remoteStatus = remoteStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRemoteStatus() {
		return this.remoteStatus;
	}

	public void setRemoteStatus(String remoteStatus) {
		this.remoteStatus = remoteStatus;
	}

}