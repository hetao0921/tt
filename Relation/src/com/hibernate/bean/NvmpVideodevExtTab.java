package com.hibernate.bean;

/**
 * NvmpVideodevExtTab entity. @author MyEclipse Persistence Tools
 */

public class NvmpVideodevExtTab implements java.io.Serializable {

	// Fields

	private String deviceid;
	private Integer netlinkmode;

	// Constructors

	/** default constructor */
	public NvmpVideodevExtTab() {
	}

	/** full constructor */
	public NvmpVideodevExtTab(Integer netlinkmode) {
		this.netlinkmode = netlinkmode;
	}

	// Property accessors

	public String getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public Integer getNetlinkmode() {
		return this.netlinkmode;
	}

	public void setNetlinkmode(Integer netlinkmode) {
		this.netlinkmode = netlinkmode;
	}

}