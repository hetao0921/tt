package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MqQueueuplinktab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mq_queueuplinktab", catalog = "nvmp")
public class MqQueueuplinktab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerId;
	private String centerIp;
	private Integer centerPort;
	private String flag;

	// Constructors

	/** default constructor */
	public MqQueueuplinktab() {
	}

	/** full constructor */
	public MqQueueuplinktab(String centerId, String centerIp,
			Integer centerPort, String flag) {
		this.centerId = centerId;
		this.centerIp = centerIp;
		this.centerPort = centerPort;
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

	@Column(name = "CenterID", nullable = false, length = 64)
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "CenterIP", nullable = false, length = 32)
	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	@Column(name = "CenterPort", nullable = false)
	public Integer getCenterPort() {
		return this.centerPort;
	}

	public void setCenterPort(Integer centerPort) {
		this.centerPort = centerPort;
	}

	@Column(name = "Flag", nullable = false, length = 8)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}