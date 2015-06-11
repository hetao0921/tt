package com.fxdigital.bean;

/**
 * NvmpOriginalbitrateId entity. @author MyEclipse Persistence Tools
 */

public class NvmpOriginalbitrateId implements java.io.Serializable {

	// Fields

	private String deviceId;
	private Integer channel;

	// Constructors

	/** default constructor */
	public NvmpOriginalbitrateId() {
	}

	/** full constructor */
	public NvmpOriginalbitrateId(String deviceId, Integer channel) {
		this.deviceId = deviceId;
		this.channel = channel;
	}

	// Property accessors

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getChannel() {
		return this.channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NvmpOriginalbitrateId))
			return false;
		NvmpOriginalbitrateId castOther = (NvmpOriginalbitrateId) other;

		return ((this.getDeviceId() == castOther.getDeviceId()) || (this
				.getDeviceId() != null && castOther.getDeviceId() != null && this
				.getDeviceId().equals(castOther.getDeviceId())))
				&& ((this.getChannel() == castOther.getChannel()) || (this
						.getChannel() != null && castOther.getChannel() != null && this
						.getChannel().equals(castOther.getChannel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDeviceId() == null ? 0 : this.getDeviceId().hashCode());
		result = 37 * result
				+ (getChannel() == null ? 0 : this.getChannel().hashCode());
		return result;
	}

}