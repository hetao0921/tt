package com.sqlite.pojo;

public class DeviceStatus extends BasePO{

	public int did;                         //��� 
	public String devcieId;                 //�豸���
	public String devName;                  //�豸���
	public String devIp;                    //�豸IP
	public String devMask;
	public String devGate;
	public String devMAC;
	public int devPort;
	public String userName;
	public String password;
	public int devType;
	public int devSubType;
	public String devModel;
	public String devSN;
	public String devVer;
	public String areaID;
	public int cameraNum;
	public int alarmPointNum;
	public int outPutPointNum;
	public int isDisable;
	public String registerTime;
	public String switchSvrID;
	public String centerID;
	public int deviceStatus;                //�û��Ƿ�������
	
	
	public DeviceStatus() {
		super();
	}
	public DeviceStatus(int did, String devcieId, String devIp,int status) {
		this.did = did;
		this.devcieId = devcieId;
		this.devIp = devIp;
		this.deviceStatus = status;
	}
	public DeviceStatus(int did, String devcieId, String devName, String devIp,
			String devMask, String devGate, String devMAC, int devPort,
			String userName, String password, int devType, int devSubType,
			String devModel, String devSN, String devVer, String areaID,
			int cameraNum, int alarmPointNum, int outPutPointNum,
			int isDisable, String registerTime, String switchSvrID,
			String centerID, int deviceStatus) {
		super();
		this.did = did;
		this.devcieId = devcieId;
		this.devName = devName;
		this.devIp = devIp;
		this.devMask = devMask;
		this.devGate = devGate;
		this.devMAC = devMAC;
		this.devPort = devPort;
		this.userName = userName;
		this.password = password;
		this.devType = devType;
		this.devSubType = devSubType;
		this.devModel = devModel;
		this.devSN = devSN;
		this.devVer = devVer;
		this.areaID = areaID;
		this.cameraNum = cameraNum;
		this.alarmPointNum = alarmPointNum;
		this.outPutPointNum = outPutPointNum;
		this.isDisable = isDisable;
		this.registerTime = registerTime;
		this.switchSvrID = switchSvrID;
		this.centerID = centerID;
		this.deviceStatus = deviceStatus;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDevcieId() {
		return devcieId;
	}
	public void setDevcieId(String devcieId) {
		this.devcieId = devcieId;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDevIp() {
		return devIp;
	}
	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}
	public String getDevMask() {
		return devMask;
	}
	public void setDevMask(String devMask) {
		this.devMask = devMask;
	}
	public String getDevGate() {
		return devGate;
	}
	public void setDevGate(String devGate) {
		this.devGate = devGate;
	}
	public String getDevMAC() {
		return devMAC;
	}
	public void setDevMAC(String devMAC) {
		this.devMAC = devMAC;
	}
	public int getDevPort() {
		return devPort;
	}
	public void setDevPort(int devPort) {
		this.devPort = devPort;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDevType() {
		return devType;
	}
	public void setDevType(int devType) {
		this.devType = devType;
	}
	public int getDevSubType() {
		return devSubType;
	}
	public void setDevSubType(int devSubType) {
		this.devSubType = devSubType;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	public String getDevSN() {
		return devSN;
	}
	public void setDevSN(String devSN) {
		this.devSN = devSN;
	}
	public String getDevVer() {
		return devVer;
	}
	public void setDevVer(String devVer) {
		this.devVer = devVer;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public int getCameraNum() {
		return cameraNum;
	}
	public void setCameraNum(int cameraNum) {
		this.cameraNum = cameraNum;
	}
	public int getAlarmPointNum() {
		return alarmPointNum;
	}
	public void setAlarmPointNum(int alarmPointNum) {
		this.alarmPointNum = alarmPointNum;
	}
	public int getOutPutPointNum() {
		return outPutPointNum;
	}
	public void setOutPutPointNum(int outPutPointNum) {
		this.outPutPointNum = outPutPointNum;
	}
	public int getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getSwitchSvrID() {
		return switchSvrID;
	}
	public void setSwitchSvrID(String switchSvrID) {
		this.switchSvrID = switchSvrID;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
	public int getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
	
}
