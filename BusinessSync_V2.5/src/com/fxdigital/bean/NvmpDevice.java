package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_device", catalog = "nvmp")
public class NvmpDevice implements java.io.Serializable {

	// Fields

	private String deviceType;
	private String deviceName;
	private String deviceSn;

	// Constructors

	/** default constructor */
	public NvmpDevice() {
	}

	/** full constructor */
	public NvmpDevice(String deviceName, String deviceSn) {
		this.deviceName = deviceName;
		this.deviceSn = deviceSn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DeviceType", unique = true, nullable = false, length = 32)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "DeviceName", nullable = false, length = 64)
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "DeviceSN", nullable = false, length = 64)
	public String getDeviceSn() {
		return this.deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

}