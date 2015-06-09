package com.fxdigital.tcp.util;

/**
 * @author Administrator
 * 文件消息实体类
 *
 */
public class FileMsg implements IMsg{
	private String sendFilePath;
	private String receiveFilePath;
    private String flag;
	private String masterip;
	private String slaveip;
	
	
	public String getSendFilePath() {
		return sendFilePath;
	}
	public void setSendFilePath(String sendFilePath) {
		this.sendFilePath = sendFilePath;
	}
	public String getReceiveFilePath() {
		return receiveFilePath;
	}
	public void setReceiveFilePath(String receiveFilePath) {
		this.receiveFilePath = receiveFilePath;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMasterIp() {
		return masterip;
	}
	public void setMasterip(String masterip) {
		this.masterip = masterip;
	}
	public String getSlaveIp() {
		return slaveip;
	}
	public void setSlaveip(String slaveip) {
		this.slaveip = slaveip;
	}
	
	
	
}
