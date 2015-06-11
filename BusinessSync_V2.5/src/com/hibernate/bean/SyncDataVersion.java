package com.hibernate.bean;

/**
 * SyncDataVersion entity. @author MyEclipse Persistence Tools
 */

public class SyncDataVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerid;
	private String centername;
	private String centerip;
	private Integer version;

	// Constructors

	/** default constructor */
	public SyncDataVersion() {
	}

	/** full constructor */
	public SyncDataVersion(String centerid, String centername, String centerip,
			Integer version) {
		this.centerid = centerid;
		this.centername = centername;
		this.centerip = centerip;
		this.version = version;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public String getCenterip() {
		return this.centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}