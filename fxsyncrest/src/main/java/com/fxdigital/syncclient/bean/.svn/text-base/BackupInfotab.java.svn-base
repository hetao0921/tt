package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BackupInfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "backup_infotab", catalog = "nvmp")
public class BackupInfotab implements java.io.Serializable {

	// Fields

	private String centerIp;
	private String centerName;
	private String centerId;
	private String flag;
	private String status;

	// Constructors

	/** default constructor */
	public BackupInfotab() {
	}

	/** minimal constructor */
	public BackupInfotab(String centerIp, String flag, String status) {
		this.centerIp = centerIp;
		this.flag = flag;
		this.status = status;
	}

	/** full constructor */
	public BackupInfotab(String centerIp, String centerName, String centerId,
			String flag, String status) {
		this.centerIp = centerIp;
		this.centerName = centerName;
		this.centerId = centerId;
		this.flag = flag;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "CenterIP", unique = true, nullable = false, length = 64)
	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	@Column(name = "CenterName", length = 64)
	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	@Column(name = "CenterID", length = 64)
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "Flag", nullable = false, length = 11)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "status", nullable = false, length = 11)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}