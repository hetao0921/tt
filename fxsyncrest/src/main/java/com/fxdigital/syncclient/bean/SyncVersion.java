package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_version", catalog = "jms_client")
public class SyncVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer localVersion;
	private Integer serverVersion;
	private Integer flag; //1表示正在上传，0，表示上传完成

	// Constructors

	/** default constructor */
	public SyncVersion() {
	}

	/** full constructor */
	public SyncVersion(Integer localVersion, Integer serverVersion, Integer flag) {
		this.localVersion = localVersion;
		this.serverVersion = serverVersion;
		this.flag = flag;
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

	@Column(name = "LocalVersion", nullable = false)
	public Integer getLocalVersion() {
		return this.localVersion;
	}

	public void setLocalVersion(Integer localVersion) {
		this.localVersion = localVersion;
	}

	@Column(name = "ServerVersion", nullable = false)
	public Integer getServerVersion() {
		return this.serverVersion;
	}

	public void setServerVersion(Integer serverVersion) {
		this.serverVersion = serverVersion;
	}

	@Column(name = "flag", nullable = false)
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}