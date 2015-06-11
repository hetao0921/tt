package com.fxdigital.bean;

import java.sql.Timestamp;

/**
 * NvmpVideodevtab entity. @author MyEclipse Persistence Tools
 */

public class NvmpVideodevtab implements java.io.Serializable {

	// Fields

	private Long id;
	private String deviceId;
	private String devname;
	private String devIp;
	private String devMask;
	private String devGate;
	private String devMac;
	private Integer devPort;
	private String userName;
	private String password;
	private Integer devType;
	private Integer devSubType;
	private Integer devModel;
	private String devSn;
	private String devVer;
	private String areaId;
	private Integer cameraNum;
	private Integer alarmPointNum;
	private Integer outPutPointNum;
	private Integer isDisable;
	private Timestamp registerTime;
	private String switchSvrId;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpVideodevtab() {
	}

	/** minimal constructor */
	public NvmpVideodevtab(String deviceId, String devname) {
		this.deviceId = deviceId;
		this.devname = devname;
	}

	/** full constructor */
	public NvmpVideodevtab(String deviceId, String devname, String devIp,
			String devMask, String devGate, String devMac, Integer devPort,
			String userName, String password, Integer devType,
			Integer devSubType, Integer devModel, String devSn, String devVer,
			String areaId, Integer cameraNum, Integer alarmPointNum,
			Integer outPutPointNum, Integer isDisable, Timestamp registerTime,
			String switchSvrId, String centerId) {
		this.deviceId = deviceId;
		this.devname = devname;
		this.devIp = devIp;
		this.devMask = devMask;
		this.devGate = devGate;
		this.devMac = devMac;
		this.devPort = devPort;
		this.userName = userName;
		this.password = password;
		this.devType = devType;
		this.devSubType = devSubType;
		this.devModel = devModel;
		this.devSn = devSn;
		this.devVer = devVer;
		this.areaId = areaId;
		this.cameraNum = cameraNum;
		this.alarmPointNum = alarmPointNum;
		this.outPutPointNum = outPutPointNum;
		this.isDisable = isDisable;
		this.registerTime = registerTime;
		this.switchSvrId = switchSvrId;
		this.centerId = centerId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDevname() {
		return this.devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}

	public String getDevIp() {
		return this.devIp;
	}

	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}

	public String getDevMask() {
		return this.devMask;
	}

	public void setDevMask(String devMask) {
		this.devMask = devMask;
	}

	public String getDevGate() {
		return this.devGate;
	}

	public void setDevGate(String devGate) {
		this.devGate = devGate;
	}

	public String getDevMac() {
		return this.devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}

	public Integer getDevPort() {
		return this.devPort;
	}

	public void setDevPort(Integer devPort) {
		this.devPort = devPort;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDevType() {
		return this.devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	public Integer getDevSubType() {
		return this.devSubType;
	}

	public void setDevSubType(Integer devSubType) {
		this.devSubType = devSubType;
	}

	public Integer getDevModel() {
		return this.devModel;
	}

	public void setDevModel(Integer devModel) {
		this.devModel = devModel;
	}

	public String getDevSn() {
		return this.devSn;
	}

	public void setDevSn(String devSn) {
		this.devSn = devSn;
	}

	public String getDevVer() {
		return this.devVer;
	}

	public void setDevVer(String devVer) {
		this.devVer = devVer;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getCameraNum() {
		return this.cameraNum;
	}

	public void setCameraNum(Integer cameraNum) {
		this.cameraNum = cameraNum;
	}

	public Integer getAlarmPointNum() {
		return this.alarmPointNum;
	}

	public void setAlarmPointNum(Integer alarmPointNum) {
		this.alarmPointNum = alarmPointNum;
	}

	public Integer getOutPutPointNum() {
		return this.outPutPointNum;
	}

	public void setOutPutPointNum(Integer outPutPointNum) {
		this.outPutPointNum = outPutPointNum;
	}

	public Integer getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getSwitchSvrId() {
		return this.switchSvrId;
	}

	public void setSwitchSvrId(String switchSvrId) {
		this.switchSvrId = switchSvrId;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}