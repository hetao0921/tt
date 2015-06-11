package com.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataIncrementVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_increment_version", catalog = "jms_server")
public class DataIncrementVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerid;
	private String centername;
	private String centerip;
	private Integer version;

	// Constructors

	/** default constructor */
	public DataIncrementVersion() {
	}

	/** full constructor */
	public DataIncrementVersion(String centerid, String centername,
			String centerip, Integer version) {
		this.centerid = centerid;
		this.centername = centername;
		this.centerip = centerip;
		this.version = version;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "centerid", length = 45)
	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	@Column(name = "centername", length = 45)
	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	@Column(name = "centerip", length = 55)
	public String getCenterip() {
		return this.centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}