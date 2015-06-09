package com.fxdigital.syncclient.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordMark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_record_mark", catalog = "")
public class NvmpRecordMark implements java.io.Serializable {

	// Fields

	private String nvmpMarkUuid;
	private String nvmpBaseUuid;
	private String nvmpMarkName;
	private Timestamp nvmpStartTime;
	private Timestamp nvmpEndTime;

	
	// Constructors

	/** default constructor */
	public NvmpRecordMark() {
	}

	/** minimal constructor */
	public NvmpRecordMark(String nvmpMarkUuid) {
		this.nvmpMarkUuid = nvmpMarkUuid;
	}

	/** full constructor */
	public NvmpRecordMark(String nvmpMarkUuid, String nvmpBaseUuid,
			String nvmpMarkName, Timestamp nvmpStartTime, Timestamp nvmpEndTime) {
		this.nvmpMarkUuid = nvmpMarkUuid;
		this.nvmpBaseUuid = nvmpBaseUuid;
		this.nvmpMarkName = nvmpMarkName;
		this.nvmpStartTime = nvmpStartTime;
		this.nvmpEndTime = nvmpEndTime;
	}

	// Property accessors
	@Id
	@Column(name = "nvmp_mark_uuid", unique = true, nullable = false, length = 64)
	public String getNvmpMarkUuid() {
		return this.nvmpMarkUuid;
	}

	public void setNvmpMarkUuid(String nvmpMarkUuid) {
		this.nvmpMarkUuid = nvmpMarkUuid;
	}

	@Column(name = "nvmp_base_uuid", length = 64)
	public String getNvmpBaseUuid() {
		return this.nvmpBaseUuid;
	}

	public void setNvmpBaseUuid(String nvmpBaseUuid) {
		this.nvmpBaseUuid = nvmpBaseUuid;
	}

	@Column(name = "nvmp_mark_name", length = 2000)
	public String getNvmpMarkName() {
		return this.nvmpMarkName;
	}

	public void setNvmpMarkName(String nvmpMarkName) {
		this.nvmpMarkName = nvmpMarkName;
	}

	@Column(name = "nvmp_start_time", length = 19)
	public Timestamp getNvmpStartTime() {
		return this.nvmpStartTime;
	}

	public void setNvmpStartTime(Timestamp nvmpStartTime) {
		this.nvmpStartTime = nvmpStartTime;
	}

	@Column(name = "nvmp_end_time", length = 19)
	public Timestamp getNvmpEndTime() {
		return this.nvmpEndTime;
	}

	public void setNvmpEndTime(Timestamp nvmpEndTime) {
		this.nvmpEndTime = nvmpEndTime;
	}

}