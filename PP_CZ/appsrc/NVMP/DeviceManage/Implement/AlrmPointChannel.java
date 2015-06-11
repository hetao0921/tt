package NVMP.DeviceManage.Implement;

public class AlrmPointChannel extends BaseChannel {

	private int DeviceStatus;
	
	public int getDeviceStatus() {
		return DeviceStatus;
	}

	public void setDeviceStatus(int deviceStatus) {
		DeviceStatus = deviceStatus;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "AlrmPoint";
	}

}
