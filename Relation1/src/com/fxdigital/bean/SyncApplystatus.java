package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncApplystatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_applystatus", catalog = "nvmp")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "StatusID")
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "StatusDesc")
	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}