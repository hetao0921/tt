package com.fxdigital.bean;

/**
 * NvmpVideodevattributetab entity. @author MyEclipse Persistence Tools
 */

public class NvmpVideodevattributetab implements java.io.Serializable {

	// Fields

	private String deviceId;
	private Integer adaptType;
	private String adaptAddress;
	private String adapterIp;

	// Constructors

	/** default constructor */
	public NvmpVideodevattributetab() {
	}

	/** full constructor */
	public NvmpVideodevattributetab(Integer adaptType, String adaptAddress,
			String adapterIp) {
		this.adaptType = adaptType;
		this.adaptAddress = adaptAddress;
		this.adapterIp = adapterIp;
	}

	// Property accessors

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getAdaptType() {
		return this.adaptType;
	}

	public void setAdaptType(Integer adaptType) {
		this.adaptType = adaptType;
	}

	public String getAdaptAddress() {
		return this.adaptAddress;
	}

	public void setAdaptAddress(String adaptAddress) {
		this.adaptAddress = adaptAddress;
	}

	public String getAdapterIp() {
		return this.adapterIp;
	}

	public void setAdapterIp(String adapterIp) {
		this.adapterIp = adapterIp;
	}

}