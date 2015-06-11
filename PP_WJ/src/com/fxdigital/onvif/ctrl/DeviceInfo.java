package com.fxdigital.onvif.ctrl;

/**
 * 
 * 设备类
 * @author hxht
 *
 */
public class DeviceInfo {

	private String deviceName; // 设备名称
	private String deviceLocation; // 硬件地址
	private String serialNumber; // 设备序列号
	private String firmwareVersion; // 固件版本

	private String deviceModel; // 设备模型
	private String deviceManufacturer;// 设备制造商
	
	private String deviceURI; //设备onvif服务地址
	
	private String macAddr;// mac地址
	

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getDeviceURI() {
		return deviceURI;
	}

	public void setDeviceURI(String deviceURI) {
		this.deviceURI = deviceURI;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
