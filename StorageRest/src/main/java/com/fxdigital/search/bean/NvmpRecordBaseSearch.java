package com.fxdigital.search.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.bean.NvmpRecordMark;

/**
 * 
 * @author hxht
 *
 */
public class NvmpRecordBaseSearch implements java.io.Serializable {

	// Fields
	/** 检索结果录像id */
	private String nvmpBaseUuid;
	/** 是否正在录像 */
	private Boolean nvmpIsRecording;
	/** 录像开始时间 */
	private String nvmpStartTime;
	/** 录像结束时间 */
	private String nvmpStopTime;
	/** 录像rtsp流地址 */
	private String nvmpRtspUrl;
	/** 录像存储实体 */
	private NvmpStorageInfo nvmpStorageInfo;
	/** 录像检索文件列表*/
	private List<NvmpRecordFileSearch> nvmpRecordFileSearchList;
	/** 录像检索标记列表 */
	private List<NvmpRecordMarkSearch> nvmpRecordMarkSearchList;
	
	// Constructors

	/** default constructor */
	public NvmpRecordBaseSearch() {
	}

	/** minimal constructor */
	public NvmpRecordBaseSearch(String nvmpBaseUuid) {
		this.nvmpBaseUuid = nvmpBaseUuid;
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






	public String getNvmpStartTime() {
		return nvmpStartTime;
	}

	public void setNvmpStartTime(String nvmpStartTime) {
		this.nvmpStartTime = nvmpStartTime;
	}

	public String getNvmpStopTime() {
		return nvmpStopTime;
	}

	public void setNvmpStopTime(String nvmpStopTime) {
		this.nvmpStopTime = nvmpStopTime;
	}


	@Column(name = "nvmp_rtsp_url", length = 2000)
	public String getNvmpRtspUrl() {
		return this.nvmpRtspUrl;
	}

	public void setNvmpRtspUrl(String nvmpRtspUrl) {
		this.nvmpRtspUrl = nvmpRtspUrl;
	}



	public List<NvmpRecordFileSearch> getNvmpRecordFileSearchList() {
		return nvmpRecordFileSearchList;
	}

	public void setNvmpRecordFileSearchList(
			List<NvmpRecordFileSearch> nvmpRecordFileSearchList) {
		this.nvmpRecordFileSearchList = nvmpRecordFileSearchList;
	}

	public void setNvmpRecordFileSearchList(
			NvmpRecordFile nvmpRecordFile) {
		this.nvmpRecordFileSearchList = nvmpRecordFileSearchList;
	}

	public List<NvmpRecordMarkSearch> getNvmpRecordMarkSearchList() {
		return nvmpRecordMarkSearchList;
	}

	public void setNvmpRecordMarkSearchList(
			List<NvmpRecordMarkSearch> nvmpRecordMarkSearchList) {
		this.nvmpRecordMarkSearchList = nvmpRecordMarkSearchList;
	}

	public NvmpStorageInfo getNvmpStorageInfo() {
		return nvmpStorageInfo;
	}

	public void setNvmpStorageInfo(NvmpStorageInfo nvmpStorageInfo) {
		this.nvmpStorageInfo = nvmpStorageInfo;
	}






	
}