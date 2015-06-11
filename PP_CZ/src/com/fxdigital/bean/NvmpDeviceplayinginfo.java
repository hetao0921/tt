package com.fxdigital.bean;

/**
 * NvmpDeviceplayinginfo entity. @author MyEclipse Persistence Tools
 */

public class NvmpDeviceplayinginfo implements java.io.Serializable {

	// Fields

	private NvmpDeviceplayinginfoId id;
	private Integer bitrate;
	private Integer playingCount;

	// Constructors

	/** default constructor */
	public NvmpDeviceplayinginfo() {
	}

	/** full constructor */
	public NvmpDeviceplayinginfo(NvmpDeviceplayinginfoId id, Integer bitrate,
			Integer playingCount) {
		this.id = id;
		this.bitrate = bitrate;
		this.playingCount = playingCount;
	}

	// Property accessors

	public NvmpDeviceplayinginfoId getId() {
		return this.id;
	}

	public void setId(NvmpDeviceplayinginfoId id) {
		this.id = id;
	}

	public Integer getBitrate() {
		return this.bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

	public Integer getPlayingCount() {
		return this.playingCount;
	}

	public void setPlayingCount(Integer playingCount) {
		this.playingCount = playingCount;
	}

}