package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SyncDataSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sync_data_set", catalog = "nvmp")
public class SyncDataSet implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerid;
	private String centername;
	private Integer autotime;
	private String setdate;

	// Constructors

	/** default constructor */
	public SyncDataSet() {
	}

	/** full constructor */
	public SyncDataSet(String centerid, String centername, Integer autotime,
			String setdate) {
		this.centerid = centerid;
		this.centername = centername;
		this.autotime = autotime;
		this.setdate = setdate;
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

	@Column(name = "autotime")
	public Integer getAutotime() {
		return this.autotime;
	}

	public void setAutotime(Integer autotime) {
		this.autotime = autotime;
	}

	@Column(name = "setdate", length = 45)
	public String getSetdate() {
		return this.setdate;
	}

	public void setSetdate(String setdate) {
		this.setdate = setdate;
	}

}