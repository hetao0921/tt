package com.sqlite.pojo;

public class CommandDevice {

	private int id; 
	private String deviceId;
	private String deviceName;
	private String deviceDesc;
	private String devIp;
	private String devMAC;
	private int workStatus;
	private int isOnline;
	private String clientUserID;
	private int commandStatus;
	private int conferenceStatus;
	private int consultationStatus;
	private int devModal;
	private String devVer;
	private int cameraNum;
	private String commandTeamID;
	private String centerID;
	public CommandDevice() {
		super();
	}
	public CommandDevice(int id, String deviceId, String deviceName,
			String deviceDesc, String devIp, String devMAC, int workStatus,
			int isOnline, String clientUserID, int commandStatus,
			int conferenceStatus, int consultationStatus, int devModal,
			String devVer, int cameraNum, String commandTeamID, String centerID) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.deviceDesc = deviceDesc;
		this.devIp = devIp;
		this.devMAC = devMAC;
		this.workStatus = workStatus;
		this.isOnline = isOnline;
		this.clientUserID = clientUserID;
		this.commandStatus = commandStatus;
		this.conferenceStatus = conferenceStatus;
		this.consultationStatus = consultationStatus;
		this.devModal = devModal;
		this.devVer = devVer;
		this.cameraNum = cameraNum;
		this.commandTeamID = commandTeamID;
		this.centerID = centerID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceDesc() {
		return deviceDesc;
	}
	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	public String getDevIp() {
		return devIp;
	}
	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}
	public String getDevMAC() {
		return devMAC;
	}
	public void setDevMAC(String devMAC) {
		this.devMAC = devMAC;
	}
	public int getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getClientUserID() {
		return clientUserID;
	}
	public void setClientUserID(String clientUserID) {
		this.clientUserID = clientUserID;
	}
	public int getCommandStatus() {
		return commandStatus;
	}
	public void setCommandStatus(int commandStatus) {
		this.commandStatus = commandStatus;
	}
	public int getConferenceStatus() {
		return conferenceStatus;
	}
	public void setConferenceStatus(int conferenceStatus) {
		this.conferenceStatus = conferenceStatus;
	}
	public int getConsultationStatus() {
		return consultationStatus;
	}
	public void setConsultationStatus(int consultationStatus) {
		this.consultationStatus = consultationStatus;
	}
	public int getDevModal() {
		return devModal;
	}
	public void setDevModal(int devModal) {
		this.devModal = devModal;
	}
	public String getDevVer() {
		return devVer;
	}
	public void setDevVer(String devVer) {
		this.devVer = devVer;
	}
	public int getCameraNum() {
		return cameraNum;
	}
	public void setCameraNum(int cameraNum) {
		this.cameraNum = cameraNum;
	}
	public String getCommandTeamID() {
		return commandTeamID;
	}
	public void setCommandTeamID(String commandTeamID) {
		this.commandTeamID = commandTeamID;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
}
