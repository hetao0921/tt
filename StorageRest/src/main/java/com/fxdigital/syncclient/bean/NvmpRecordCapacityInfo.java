package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordCapacityInfo entity. @author MyEclipse Persistence Tools
 */

public class NvmpRecordCapacityInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer videorate;
	private Integer videotime;
	private Integer videochannel;

	// Constructors

	/** default constructor */
	public NvmpRecordCapacityInfo() {
	}

	/** full constructor */
	public NvmpRecordCapacityInfo(Integer videorate, Integer videotime,
			Integer videochannel) {
		this.videorate = videorate;
		this.videotime = videotime;
		this.videochannel = videochannel;
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

	@Column(name = "videorate")
	public Integer getVideorate() {
		return this.videorate;
	}

	public void setVideorate(Integer videorate) {
		this.videorate = videorate;
	}

	@Column(name = "videotime")
	public Integer getVideotime() {
		return this.videotime;
	}

	public void setVideotime(Integer videotime) {
		this.videotime = videotime;
	}

	@Column(name = "videochannel")
	public Integer getVideochannel() {
		return this.videochannel;
	}

	public void setVideochannel(Integer videochannel) {
		this.videochannel = videochannel;
	}

}