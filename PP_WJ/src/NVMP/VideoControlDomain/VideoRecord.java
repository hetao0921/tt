package NVMP.VideoControlDomain;

/**
 * 成功后相关信息有： 1、发送给客户端还是发给中心
 * 
 * 2、这次点播是否为转发，还是直接从设备过来。
 * 
 * 
 * 3、路由
 * */
public class VideoRecord {

	public String uuid;

	public String ClientId; // 点播人ID
	public String DeviceId; // 设备ID 
	public Integer CameraIndex; // 设备通道号

	public PalyRecord pr; // 点播所用信息

	public Integer sendFlag; // 如果是发送给客户端，则为 0 发送给中心为 1。
	public String sendId; // 根据发送对象，如果是中心，则为中心ID，如果为客户，则为客户ID

	public Integer fowardFlag;// 如果经过，则是 1，否者为 0
	public String fowardId;

	public Route centerMap; // 点播的路由。
	
	
	public Integer userLev; //点播成功后，我的级别。
	
	

	public Integer getUserLev() {
		return userLev;
	}

	public void setUserLev(Integer userLev) {
		this.userLev = userLev;
	}

	public VideoRecord(String clientId, String devid, Integer channel,
			Integer sendFlag, String sendId, Integer fowardFlag,
			String fowardId, Route centerMap, PalyRecord pr,String uuid) {
		this.ClientId = clientId;
		this.DeviceId = devid;
		this.CameraIndex = pr.getChannel();
		this.pr = pr;
		this.sendFlag = sendFlag;
		this.sendId = sendId;
		this.fowardFlag = fowardFlag;
		if(fowardId != null) {
		  this.fowardId = fowardId.trim();
		}
		this.centerMap = centerMap;
		this.uuid = uuid;

	}

	public VideoRecord(String uuid) {
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

	public PalyRecord getPr() {
		return pr;
	}

	public void setPr(PalyRecord pr) {
		this.pr = pr;
	}

	/**
	 * // 如果是发送给客户端，则为 0 发送给中心为 1。
	 * */
	public Integer getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(Integer sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public Integer getFowardFlag() {
		return fowardFlag;
	}

	public void setFowardFlag(Integer fowardFlag) {
		this.fowardFlag = fowardFlag;
	}

	public String getFowardId() {
		return fowardId;
	}

	public void setFowardId(String fowardId) {
		if(fowardId != null) {
			  this.fowardId = fowardId.trim();
			}
	}

	public Route getCenterMap() {
		return centerMap;
	}

	public void setCenterMap(Route centerMap) {
		this.centerMap = centerMap;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof VideoRecord)
				&& (((VideoRecord) obj).getUuid().equals(uuid));
	}
    
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return uuid.hashCode();
	}
	
	
}
