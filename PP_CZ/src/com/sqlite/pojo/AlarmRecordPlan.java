package com.sqlite.pojo;

public class AlarmRecordPlan {

	private int id;                     //主键ID 
	private String deviceId;            //设备ID
	private int devChannel;             //设备通道
	private int channelType;            //通道类型
	private int planType;               //计划类型
	private String planXml;             //计划的详细内容
	private String centerId;            //中心ID
	private String sessionId;           //
	private String planDesc;            //计划描述
	
	public AlarmRecordPlan() {
		super();
	}
	public AlarmRecordPlan(int id, String deviceId, int devChannel,
			int channelType, int planType, String planXml, String centerId,
			String sessionId,String planDesc) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.devChannel = devChannel;
		this.channelType = channelType;
		this.planType = planType;
		this.planXml = planXml;
		this.centerId = centerId;
		this.sessionId = sessionId;
		this.planDesc = planDesc;
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
	public int getDevChannel() {
		return devChannel;
	}
	public void setDevChannel(int devChannel) {
		this.devChannel = devChannel;
	}
	public int getChannelType() {
		return channelType;
	}
	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}
	public int getPlanType() {
		return planType;
	}
	public void setPlanType(int planType) {
		this.planType = planType;
	}
	public String getPlanXml() {
		return planXml;
	}
	public void setPlanXml(String planXml) {
		this.planXml = planXml;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	
}
