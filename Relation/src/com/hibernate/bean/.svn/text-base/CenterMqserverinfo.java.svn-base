package com.hibernate.bean;

/**
 * CenterMqserverinfo entity. @author MyEclipse Persistence Tools
 */

public class CenterMqserverinfo implements java.io.Serializable,IArrayPoJo {

	// Fields

	private Integer id;
	private String mqIp;
	private Integer mqPort;

	public String[] toStringArray() {
 		String[] result = new String[3];
		result[0] = id.toString();
		result[1] = mqIp.toString();
		result[2] = mqPort.toString();
		return result;
 	}
	
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMqIp() {
		return this.mqIp;
	}

	public void setMqIp(String mqIp) {
		this.mqIp = mqIp;
	}

	public Integer getMqPort() {
		return this.mqPort;
	}

	public void setMqPort(Integer mqPort) {
		this.mqPort = mqPort;
	}

}