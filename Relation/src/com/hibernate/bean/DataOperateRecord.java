package com.hibernate.bean;

/**
 * DataOperateRecord entity. @author MyEclipse Persistence Tools
 */

public class DataOperateRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uuid;
	private String operatorsessionid;
	private String operatorip;
	private String operatetime;
	private String operate;
	private String centerid;
	private String operateinfo;
	private String centername;
	private String errorinfo;

	// Constructors

	/** default constructor */
	public DataOperateRecord() {
	}

	/** full constructor */
	public DataOperateRecord(String uuid, String operatorsessionid,
			String operatorip, String operatetime, String operate,
			String centerid, String operateinfo, String centername,
			String errorinfo) {
		this.uuid = uuid;
		this.operatorsessionid = operatorsessionid;
		this.operatorip = operatorip;
		this.operatetime = operatetime;
		this.operate = operate;
		this.centerid = centerid;
		this.operateinfo = operateinfo;
		this.centername = centername;
		this.errorinfo = errorinfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOperatorsessionid() {
		return this.operatorsessionid;
	}

	public void setOperatorsessionid(String operatorsessionid) {
		this.operatorsessionid = operatorsessionid;
	}

	public String getOperatorip() {
		return this.operatorip;
	}

	public void setOperatorip(String operatorip) {
		this.operatorip = operatorip;
	}

	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getOperateinfo() {
		return this.operateinfo;
	}

	public void setOperateinfo(String operateinfo) {
		this.operateinfo = operateinfo;
	}

	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public String getErrorinfo() {
		return this.errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

}