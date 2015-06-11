package NVMP.IpMatrixManage.Implement;


public class PlayVideo {

	private String ClientId;
	private Integer TVIndex;
	private Integer pos;
	private String DeviceId;
	private Integer CameraIndex;
	private String uuid; 
	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getClientId() {
		return ClientId;
	}

	public Integer getTVIndex() {
		return TVIndex;
	}

	public Integer getPos() {
		return pos;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public Integer getCameraIndex() {
		return CameraIndex;
	}

	public PlayVideo(String ClientId, Integer TVIndex, Integer pos,
			String DeviceId, Integer CameraIndex,String uuid) {
		this.ClientId = ClientId;
		this.TVIndex = TVIndex;
		this.pos = pos;
		this.DeviceId = DeviceId;
		this.CameraIndex = CameraIndex;
		this.uuid = uuid;
	};

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof PlayVideo))
			return false;
		PlayVideo pv = (PlayVideo) obj;
		return (pv.TVIndex.intValue() == this.TVIndex.intValue() ) && (pv.pos.intValue() == this.pos.intValue())
				&& (pv.DeviceId.equals(this.DeviceId))
				&& (pv.CameraIndex.equals(this.CameraIndex));
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return TVIndex.hashCode() + pos.hashCode() + DeviceId.hashCode()
				+ CameraIndex.hashCode();
	}

}