package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CenterMqserverinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "center_mqserverinfo", catalog = "nvmp")
public class CenterMqserverinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mqIp;
	private Integer mqPort;

	// Constructors

	/** default constructor */
	public CenterMqserverinfo() {
	}

	/** full constructor */
	public CenterMqserverinfo(String mqIp, Integer mqPort) {
		this.mqIp = mqIp;
		this.mqPort = mqPort;
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

	@Column(name = "mqIp", length = 20)
	public String getMqIp() {
		return this.mqIp;
	}

	public void setMqIp(String mqIp) {
		this.mqIp = mqIp;
	}

	@Column(name = "mqPort")
	public Integer getMqPort() {
		return this.mqPort;
	}

	public void setMqPort(Integer mqPort) {
		this.mqPort = mqPort;
	}

}