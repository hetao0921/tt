package com.fxdigital.db.pojo;
/**
 * 
 * @author hxht
 *客户端升级访问记录信息
 */
public class SendUpResultInfo {
	  private String deviceID;//设备Id
	  private String softType;//软件类型
	  private String softOldVersion;//软件原始版本
	  private String fileID;//软件升级包唯一Id
	  private String softCurVersion;//软件升级版本
	  private String upgradeTime;//软件升级时间
	  private String status;//升级状态
	  private String upMode;//升级方式
	  
	public SendUpResultInfo() {
		super();
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getSoftType() {
		return softType;
	}

	public void setSoftType(String softType) {
		this.softType = softType;
	}

	public String getSoftOldVersion() {
		return softOldVersion;
	}

	public void setSoftOldVersion(String softOldVersion) {
		this.softOldVersion = softOldVersion;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getSoftCurVersion() {
		return softCurVersion;
	}

	public void setSoftCurVersion(String softCurVersion) {
		this.softCurVersion = softCurVersion;
	}

	public String getUpgradeTime() {
		return upgradeTime;
	}

	public void setUpgradeTime(String upgradeTime) {
		this.upgradeTime = upgradeTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpMode() {
		return upMode;
	}

	public void setUpMode(String upMode) {
		this.upMode = upMode;
	}
}
