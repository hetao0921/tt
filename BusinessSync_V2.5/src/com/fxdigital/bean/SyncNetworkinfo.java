package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncNetworkinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_networkinfo", catalog = "nvmp")
public class SyncNetworkinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String subId;
	private String subIp;
	private String supId;
	private String supIp;
	private String syncId;

	// Constructors

	/** default constructor */
	public SyncNetworkinfo() {
	}

	/** full constructor */
	public SyncNetworkinfo(String subId, String subIp, String supId,
			String supIp, String syncId) {
		this.subId = subId;
		this.subIp = subIp;
		this.supId = supId;
		this.supIp = supIp;
		this.syncId = syncId;
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

	@Column(name = "SubID")
	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	@Column(name = "SubIP")
	public String getSubIp() {
		return this.subIp;
	}

	public void setSubIp(String subIp) {
		this.subIp = subIp;
	}

	@Column(name = "SupID")
	public String getSupId() {
		return this.supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	@Column(name = "SupIP")
	public String getSupIp() {
		return this.supIp;
	}

	public void setSupIp(String supIp) {
		this.supIp = supIp;
	}

	@Column(name = "SyncID")
	public String getSyncId() {
		return this.syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

}