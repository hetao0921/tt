package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataOperate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_operate", catalog = "jms_client")
public class DataOperate implements java.io.Serializable {

	// Fields

	private Integer id;
	private String operate;
	private Integer flag;

	// Constructors

	/** default constructor */
	public DataOperate() {
	}

	/** full constructor */
	public DataOperate(String operate, Integer flag) {
		this.operate = operate;
		this.flag = flag;
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

	@Column(name = "operate", length = 45)
	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}