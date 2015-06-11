package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncUpnetworkinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_upnetworkinfo", catalog = "nvmp")
public class SyncUpnetworkinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String subId;
	private String subIp;
	private Integer subPort;
	private String supIp;
	private Integer supPort;

	// Constructors

	/** default constructor */
	public SyncUpnetworkinfo() {
	}

	/** full constructor */
	public SyncUpnetworkinfo(String subId, String subIp, Integer subPort,
			String supIp, Integer supPort) {
		this.subId = subId;
		this.subIp = subIp;
		this.subPort = subPort;
		this.supIp = supIp;
		this.supPort = supPort;
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

	@Column(name = "SubPort")
	public Integer getSubPort() {
		return this.subPort;
	}

	public void setSubPort(Integer subPort) {
		this.subPort = subPort;
	}

	@Column(name = "SupIP")
	public String getSupIp() {
		return this.supIp;
	}

	public void setSupIp(String supIp) {
		this.supIp = supIp;
	}

	@Column(name = "SupPort")
	public Integer getSupPort() {
		return this.supPort;
	}

	public void setSupPort(Integer supPort) {
		this.supPort = supPort;
	}

}