package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordMarkParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_record_mark_parameter", catalog = "")
public class NvmpRecordMarkParameter implements java.io.Serializable {

	// Fields

	private Integer nvmpParmeterId;
	private String nvmpMarkUuid;
	private String parameterKey;
	private String parameterValue;

	// Constructors

	/** default constructor */
	public NvmpRecordMarkParameter() {
	}

	/** full constructor */
	public NvmpRecordMarkParameter(String nvmpMarkUuid, String parameterKey,
			String parameterValue) {
		this.nvmpMarkUuid = nvmpMarkUuid;
		this.parameterKey = parameterKey;
		this.parameterValue = parameterValue;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "nvmp_parmeter_id", unique = true, nullable = false)
	public Integer getNvmpParmeterId() {
		return this.nvmpParmeterId;
	}

	public void setNvmpParmeterId(Integer nvmpParmeterId) {
		this.nvmpParmeterId = nvmpParmeterId;
	}

	@Column(name = "nvmp_mark_uuid", length = 64)
	public String getNvmpMarkUuid() {
		return this.nvmpMarkUuid;
	}

	public void setNvmpMarkUuid(String nvmpMarkUuid) {
		this.nvmpMarkUuid = nvmpMarkUuid;
	}

	@Column(name = "parameter_key")
	public String getParameterKey() {
		return this.parameterKey;
	}

	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}

	@Column(name = "parameter_value")
	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

}