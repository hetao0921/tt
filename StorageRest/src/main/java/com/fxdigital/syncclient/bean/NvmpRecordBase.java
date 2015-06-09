package com.fxdigital.syncclient.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordBase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_record_base", catalog = "")
public class NvmpRecordBase implements java.io.Serializable {

	// Fields

	private String nvmpBaseUuid;
	private Boolean nvmpIsRecording;
	private Timestamp nvmpStartTime;
	private Timestamp nvmpStopTime;
	private String nvmpRtspUrl;
	


	// Constructors

	/** default constructor */
	public NvmpRecordBase() {
	}

	/** minimal constructor */
	public NvmpRecordBase(String nvmpBaseUuid) {
		this.nvmpBaseUuid = nvmpBaseUuid;
	}

	/** full constructor */
	public NvmpRecordBase(String nvmpBaseUuid, Boolean nvmpIsRecording,
			Timestamp nvmpStartTime, Timestamp nvmpStopTime, String nvmpRtspUrl) {
		this.nvmpBaseUuid = nvmpBaseUuid;
		this.nvmpIsRecording = nvmpIsRecording;
		this.nvmpStartTime = nvmpStartTime;
		this.nvmpStopTime = nvmpStopTime;
		this.nvmpRtspUrl = nvmpRtspUrl;
	}

	// Property accessors
	@Id
	@Column(name = "nvmp_base_uuid", unique = true, nullable = false, length = 64)
	public String getNvmpBaseUuid() {
		return this.nvmpBaseUuid;
	}

	public void setNvmpBaseUuid(String nvmpBaseUuid) {
		this.nvmpBaseUuid = nvmpBaseUuid;
	}

	@Column(name = "nvmp_is_recording")
	public Boolean getNvmpIsRecording() {
		return this.nvmpIsRecording;
	}

	public void setNvmpIsRecording(Boolean nvmpIsRecording) {
		this.nvmpIsRecording = nvmpIsRecording;
	}

	@Column(name = "nvmp_start_time", length = 19)
	public Timestamp getNvmpStartTime() {
		return this.nvmpStartTime;
	}

	public void setNvmpStartTime(Timestamp nvmpStartTime) {
		this.nvmpStartTime = nvmpStartTime;
	}

	@Column(name = "nvmp_stop_time", length = 19)
	public Timestamp getNvmpStopTime() {
		return this.nvmpStopTime;
	}

	public void setNvmpStopTime(Timestamp nvmpStopTime) {
		this.nvmpStopTime = nvmpStopTime;
	}

	@Column(name = "nvmp_rtsp_url", length = 2000)
	public String getNvmpRtspUrl() {
		return this.nvmpRtspUrl;
	}

	public void setNvmpRtspUrl(String nvmpRtspUrl) {
		this.nvmpRtspUrl = nvmpRtspUrl;
	}

}