package com.hibernate.bean;

/**
 * SyncApplystatus entity. @author MyEclipse Persistence Tools
 */

public class SyncApplystatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer statusId;
	private String statusDesc;

	// Constructors

	/** default constructor */
	public SyncApplystatus() {
	}

	/** full constructor */
	public SyncApplystatus(Integer statusId, String statusDesc) {
		this.statusId = statusId;
		this.statusDesc = statusDesc;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}