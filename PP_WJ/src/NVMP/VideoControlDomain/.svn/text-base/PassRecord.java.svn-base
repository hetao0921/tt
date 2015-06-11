package NVMP.VideoControlDomain;

/**
 * 用来记录带路由，稍后肯定要通过我这里进行
 * 
 * */
public class PassRecord {
	private String uuid;

	private String ClientId; // 点播人ID
	private String DeviceId; // 设备ID
	private Integer CameraIndex; // 设备通道号
	private Integer lev; // 点播的级别
	private Route route; //路由啊 
	
	
	
	private boolean sendFlag =  false;//发出标记,如果处于等待，默认为false;
	
	private String clientIP;
	
	
	public String getClientIP() {
		return clientIP;
	}
	
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	
	
	public boolean isSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(boolean sendFlag) {
		this.sendFlag = sendFlag;
	}

	private boolean Deleteflag; //是否删除标示 

	public PassRecord(String uuid, String ClientId, String DeviceId,
			Integer CameraIndex, Integer lev, Route route) {
		this.uuid = uuid;
		this.ClientId = ClientId;
		this.DeviceId = DeviceId;
		this.CameraIndex = CameraIndex;
		this.lev = lev;
		this.route = route;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getClientId() {
		return ClientId;
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public Integer getCameraIndex() {
		return CameraIndex;
	}

	public void setCameraIndex(Integer cameraIndex) {
		CameraIndex = cameraIndex;
	}

	public Integer getLev() {
		return lev;
	}

	public void setLev(Integer lev) {
		this.lev = lev;
	}

	public boolean isDeleteflag() {
		return Deleteflag;
	}

	public void setDeleteflag(boolean deleteflag) {
		Deleteflag = deleteflag;
	}

}
