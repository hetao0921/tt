package com.fxdigital.bean;

import java.sql.Timestamp;

/**
 * IncrementdataInfotab entity. @author MyEclipse Persistence Tools
 */

public class IncrementdataInfotab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String incrementsql;
	private String businesstype;
	private Timestamp inserttime;

	// Constructors

	/** default constructor */
	public IncrementdataInfotab() {
	}

	/** full constructor */
	public IncrementdataInfotab(String incrementsql, String businesstype,
			Timestamp inserttime) {
		this.incrementsql = incrementsql;
		this.businesstype = businesstype;
		this.inserttime = inserttime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIncrementsql() {
		return this.incrementsql;
	}

	public void setIncrementsql(String incrementsql) {
		this.incrementsql = incrementsql;
	}

	public String getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public Timestamp getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Timestamp inserttime) {
		this.inserttime = inserttime;
	}

}