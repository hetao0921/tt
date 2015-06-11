package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataOperateRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_operate_record", catalog = "jms_client")
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "uuid", length = 45)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "operatorsessionid", length = 45)
	public String getOperatorsessionid() {
		return this.operatorsessionid;
	}

	public void setOperatorsessionid(String operatorsessionid) {
		this.operatorsessionid = operatorsessionid;
	}

	@Column(name = "operatorip", length = 45)
	public String getOperatorip() {
		return this.operatorip;
	}

	public void setOperatorip(String operatorip) {
		this.operatorip = operatorip;
	}

	@Column(name = "operatetime", length = 45)
	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "operate", length = 45)
	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	@Column(name = "centerid", length = 45)
	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	@Column(name = "operateinfo", length = 2000)
	public String getOperateinfo() {
		return this.operateinfo;
	}

	public void setOperateinfo(String operateinfo) {
		this.operateinfo = operateinfo;
	}

	@Column(name = "centername", length = 45)
	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	@Column(name = "errorinfo", length = 2000)
	public String getErrorinfo() {
		return this.errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

}