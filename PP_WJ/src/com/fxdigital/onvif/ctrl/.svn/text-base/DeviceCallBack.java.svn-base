package com.fxdigital.onvif.ctrl;

public class DeviceCallBack implements IDeviceCallBack {

	@Override
	public void onSearchDevice(long deviceHandle) {
		// TODO Auto-generated method stub
		System.out.println("deviceHandle = " + (int)deviceHandle);
		if((int)deviceHandle < 0)
			return;
		DeviceInfo di = new DeviceInfo();
		DeviceCtrl.Instance().getDeviceInfo(deviceHandle, di);
		System.out.println("-----------------------设备信息-----------------------");	
		System.out.println("DeviceName: "+di.getDeviceName().toString());
		System.out.println("Manufacturer: "+di.getDeviceManufacturer().toString());
		System.out.println("Location: "+di.getDeviceLocation().toString());
		System.out.println("Model: "+di.getDeviceModel().toString());
		System.out.println("URI: "+di.getDeviceURI().toString());
		System.out.println("FirmwareVersion: "+di.getFirmwareVersion().toString());
		System.out.println("MacAddr: "+di.getMacAddr().toString());	
		System.out.println("SerialNumber: "+di.getSerialNumber().toString());
	}

}
