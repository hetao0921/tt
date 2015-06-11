package com.fxdigital.bean;

/**
 * NvmpCommanddevtab entity. @author MyEclipse Persistence Tools
 */

public class NvmpCommanddevtab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deviceId;
	private String deviceName;
	private String deviceDesc;
	private String devIp;
	private String devMac;
	private Integer workStatus;
	private Integer isOnline;
	private String clientUserId;
	private Integer commandStatus;
	private Integer conferenceStatus;
	private Integer consultationStatus;
	private Integer devModal;
	private String devVer;
	private Integer cameraNum;
	private String commandTeamId;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpCommanddevtab() {
	}

	/** minimal constructor */
	public NvmpCommanddevtab(String deviceId, String deviceName) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
	}

	/** full constructor */
	public NvmpCommanddevtab(String deviceId, String deviceName,
			String deviceDesc, String devIp, String devMac, Integer workStatus,
			Integer isOnline, String clientUserId, Integer commandStatus,
			Integer conferenceStatus, Integer consultationStatus,
			Integer devModal, String devVer, Integer cameraNum,
			String commandTeamId, String centerId) {
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.deviceDesc = deviceDesc;
		this.devIp = devIp;
		this.devMac = devMac;
		this.workStatus = workStatus;
		this.isOnline = isOnline;
		this.clientUserId = clientUserId;
		this.commandStatus = commandStatus;
		this.conferenceStatus = conferenceStatus;
		this.consultationStatus = consultationStatus;
		this.devModal = devModal;
		this.devVer = devVer;
		this.cameraNum = cameraNum;
		this.commandTeamId = commandTeamId;
		this.centerId = centerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceDesc() {
		return this.deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	public String getDevIp() {
		return this.devIp;
	}

	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}

	public String getDevMac() {
		return this.devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public Integer getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public Integer getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getClientUserId() {
		return this.clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Integer getCommandStatus() {
		return this.commandStatus;
	}

	public void setCommandStatus(Integer commandStatus) {
		this.commandStatus = commandStatus;
	}

	public Integer getConferenceStatus() {
		return this.conferenceStatus;
	}

	public void setConferenceStatus(Integer conferenceStatus) {
		this.conferenceStatus = conferenceStatus;
	}

	public Integer getConsultationStatus() {
		return this.consultationStatus;
	}

	public void setConsultationStatus(Integer consultationStatus) {
		this.consultationStatus = consultationStatus;
	}

	public Integer getDevModal() {
		return this.devModal;
	}

	public void setDevModal(Integer devModal) {
		this.devModal = devModal;
	}

	public String getDevVer() {
		return this.devVer;
	}

	public void setDevVer(String devVer) {
		this.devVer = devVer;
	}

	public Integer getCameraNum() {
		return this.cameraNum;
	}

	public void setCameraNum(Integer cameraNum) {
		this.cameraNum = cameraNum;
	}

	public String getCommandTeamId() {
		return this.commandTeamId;
	}

	public void setCommandTeamId(String commandTeamId) {
		this.commandTeamId = commandTeamId;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}