package fxdigital.syncserver.business.hibernate.bean;

/**
 * NvmpDevice entity. @author MyEclipse Persistence Tools
 */

public class NvmpDevice implements java.io.Serializable {

	// Fields

	private String deviceType;
	private String deviceName;
	private String deviceSn;

	// Constructors

	/** default constructor */
	public NvmpDevice() {
	}

	/** full constructor */
	public NvmpDevice(String deviceName, String deviceSn) {
		this.deviceName = deviceName;
		this.deviceSn = deviceSn;
	}

	// Property accessors

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceSn() {
		return this.deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

}