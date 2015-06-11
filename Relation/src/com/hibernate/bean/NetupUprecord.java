package com.hibernate.bean;

/**
 * NetupUprecord entity. @author MyEclipse Persistence Tools
 */

public class NetupUprecord implements java.io.Serializable {

	// Fields

	private Long id;
	private String deviceid;
	private String softtype;
	private String softversion;
	private String softcurversion;
	private String filelen;
	private String publishdate;
	private String operatetype;
	private String operatetatus;
	private String fileid;
	private String lockfile;
	private String issend;
	private String softname;
	private String upgradedate;

	// Constructors

	/** default constructor */
	public NetupUprecord() {
	}

	/** minimal constructor */
	public NetupUprecord(String deviceid, String softtype, String softversion) {
		this.deviceid = deviceid;
		this.softtype = softtype;
		this.softversion = softversion;
	}

	/** full constructor */
	public NetupUprecord(String deviceid, String softtype, String softversion,
			String softcurversion, String filelen, String publishdate,
			String operatetype, String operatetatus, String fileid,
			String lockfile, String issend, String softname, String upgradedate) {
		this.deviceid = deviceid;
		this.softtype = softtype;
		this.softversion = softversion;
		this.softcurversion = softcurversion;
		this.filelen = filelen;
		this.publishdate = publishdate;
		this.operatetype = operatetype;
		this.operatetatus = operatetatus;
		this.fileid = fileid;
		this.lockfile = lockfile;
		this.issend = issend;
		this.softname = softname;
		this.upgradedate = upgradedate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public String getSoftversion() {
		return this.softversion;
	}

	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public String getSoftcurversion() {
		return this.softcurversion;
	}

	public void setSoftcurversion(String softcurversion) {
		this.softcurversion = softcurversion;
	}

	public String getFilelen() {
		return this.filelen;
	}

	public void setFilelen(String filelen) {
		this.filelen = filelen;
	}

	public String getPublishdate() {
		return this.publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	public String getOperatetatus() {
		return this.operatetatus;
	}

	public void setOperatetatus(String operatetatus) {
		this.operatetatus = operatetatus;
	}

	public String getFileid() {
		return this.fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getLockfile() {
		return this.lockfile;
	}

	public void setLockfile(String lockfile) {
		this.lockfile = lockfile;
	}

	public String getIssend() {
		return this.issend;
	}

	public void setIssend(String issend) {
		this.issend = issend;
	}

	public String getSoftname() {
		return this.softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	public String getUpgradedate() {
		return this.upgradedate;
	}

	public void setUpgradedate(String upgradedate) {
		this.upgradedate = upgradedate;
	}

}