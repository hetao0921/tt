package com.sqlite.pojo;

import java.util.ArrayList;
import java.util.List;

public class VideoDevice {

	private DeviceStatus deviceStatus;      //设备对象 
	private List<VideoDevCH> channels;      //通道集合
	
	public VideoDevice() {
		super();
	}
	public VideoDevice(DeviceStatus deviceStatus, List<VideoDevCH> channels) {
		super();
		this.deviceStatus = deviceStatus;
		this.channels = channels;
	}
	public DeviceStatus getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(DeviceStatus deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public List<VideoDevCH> getChannels() {
		if(channels==null)
			channels = new ArrayList<VideoDevCH>();
		return channels;
	}
	public void setChannels(List<VideoDevCH> channels) {
		this.channels = channels;
	}
	
}
