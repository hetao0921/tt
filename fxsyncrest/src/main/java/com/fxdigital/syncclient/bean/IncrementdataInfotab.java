package com.fxdigital.syncclient.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IncrementdataInfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "incrementdata_infotab", catalog = "nvmp")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "incrementsql", nullable = false, length = 30000)
	public String getIncrementsql() {
		return this.incrementsql;
	}

	public void setIncrementsql(String incrementsql) {
		this.incrementsql = incrementsql;
	}

	@Column(name = "businesstype", nullable = false)
	public String getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	@Column(name = "inserttime", nullable = false, length = 19)
	public Timestamp getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Timestamp inserttime) {
		this.inserttime = inserttime;
	}

}