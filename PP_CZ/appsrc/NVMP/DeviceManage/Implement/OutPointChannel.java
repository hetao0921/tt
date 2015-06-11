package NVMP.DeviceManage.Implement;

public class OutPointChannel extends BaseChannel {
	private int DeviceStatus;

	public int getDeviceStatus() {
		return DeviceStatus;
	}

	public void setDeviceStatus(int deviceStatus) {
		DeviceStatus = deviceStatus;
	}

	@Override
	public String getType() {

		return "OutPoint";
	}

}
