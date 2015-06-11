package NVMP.DeviceManage.Implement;

public class CanmelChannel extends BaseChannel {

	private int motionStaus;
	private int videoLost;

	public int getMotionStaus() {
		return motionStaus;
	}

	public void setMotionStaus(int motionStaus) {
		this.motionStaus = motionStaus;
	}

	public int getVideoLost() {
		return videoLost;
	}

	public void setVideoLost(int videoLost) {
		this.videoLost = videoLost;
	}

	@Override
	public String getType() {
		return "Canmel";
	}

}
