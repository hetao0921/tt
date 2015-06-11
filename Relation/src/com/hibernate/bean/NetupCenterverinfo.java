package com.hibernate.bean;

/**
 * NetupCenterverinfo entity. @author MyEclipse Persistence Tools
 */

public class NetupCenterverinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deviceid;
	private String softtype;
	private String devicename;
	private String softversion;
	private String softoldversion;
	private String deviceip;
	private String upserverip;
	private String upserverport;
	private String upstate;
	private Integer iock;

	// Constructors

	/** default constructor */
	public NetupCenterverinfo() {
	}

	/** minimal constructor */
	public NetupCenterverinfo(String deviceid) {
		this.deviceid = deviceid;
	}

	/** full constructor */
	public NetupCenterverinfo(String deviceid, String softtype,
			String devicename, String softversion, String softoldversion,
			String deviceip, String upserverip, String upserverport,
			String upstate, Integer iock) {
		this.deviceid = deviceid;
		this.softtype = softtype;
		this.devicename = devicename;
		this.softversion = softversion;
		this.softoldversion = softoldversion;
		this.deviceip = deviceip;
		this.upserverip = upserverip;
		this.upserverport = upserverport;
		this.upstate = upstate;
		this.iock = iock;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getSofttype() {
		return this.softtype;
	}

	public void setSofttype(String softtype) {
		this.softtype = softtype;
	}

	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getSoftversion() {
		return this.softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public String getSoftoldversion() {
		return this.softoldversion;
	}

	public void setSoftoldversion(String softoldversion) {
		this.softoldversion = softoldversion;
	}

	public String getDeviceip() {
		return this.deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getUpserverip() {
		return this.upserverip;
	}

	public void setUpserverip(String upserverip) {
		this.upserverip = upserverip;
	}

	public String getUpserverport() {
		return this.upserverport;
	}

	public void setUpserverport(String upserverport) {
		this.upserverport = upserverport;
	}

	public String getUpstate() {
		return this.upstate;
	}

	public void setUpstate(String upstate) {
		this.upstate = upstate;
	}

	public Integer getIock() {
		return this.iock;
	}

	public void setIock(Integer iock) {
		this.iock = iock;
	}

}