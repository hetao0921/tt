package com.fxdigital.bean;

/**
 * DataSource entity. @author MyEclipse Persistence Tools
 */

public class DataSource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerid;
	private String centername;
	private String centerip;
	private String uuid;
	private String updatetime;
	private String oldfileaddress;
	private String fileaddress;
	private Integer version;

	// Constructors

	/** default constructor */
	public DataSource() {
	}

	/** full constructor */
	public DataSource(String centerid, String centername, String centerip,
			String uuid, String updatetime, String oldfileaddress,
			String fileaddress, Integer version) {
		this.centerid = centerid;
		this.centername = centername;
		this.centerip = centerip;
		this.uuid = uuid;
		this.updatetime = updatetime;
		this.oldfileaddress = oldfileaddress;
		this.fileaddress = fileaddress;
		this.version = version;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public String getCenterip() {
		return this.centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getOldfileaddress() {
		return this.oldfileaddress;
	}

	public void setOldfileaddress(String oldfileaddress) {
		this.oldfileaddress = oldfileaddress;
	}

	public String getFileaddress() {
		return this.fileaddress;
	}

	public void setFileaddress(String fileaddress) {
		this.fileaddress = fileaddress;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}