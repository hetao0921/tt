package com.fxdigital.db.pojo;

public class SendVerCheckpojo {
	 private String deviceId;
	 private String softType;
	 private String softVer;
	 private String clientIP;
	 private String deviceName;
	 private String upMode;
	 private String flag;
	 
	public SendVerCheckpojo() {
		super();
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSoftType() {
		return softType;
	}
	public void setSoftType(String softType) {
		this.softType = softType;
	}
	public String getSoftVer() {
		return softVer;
	}
	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getUpMode() {
		return upMode;
	}
	public void setUpMode(String upMode) {
		this.upMode = upMode;
	}
}
