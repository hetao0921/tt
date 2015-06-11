package com.hibernate.bean;

/**
 * DataNativeSource entity. @author MyEclipse Persistence Tools
 */

public class DataNativeSource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerid;
	private String centername;
	private String centerip;
	private Integer version;
	private String updatetime;
	private String uuid;
	private String downstartdate;
	private String downenddate;
	private String flag;

	// Constructors

	/** default constructor */
	public DataNativeSource() {
	}

	/** full constructor */
	public DataNativeSource(String centerid, String centername,
			String centerip, Integer version, String updatetime, String uuid,
			String downstartdate, String downenddate, String flag) {
		this.centerid = centerid;
		this.centername = centername;
		this.centerip = centerip;
		this.version = version;
		this.updatetime = updatetime;
		this.uuid = uuid;
		this.downstartdate = downstartdate;
		this.downenddate = downenddate;
		this.flag = flag;
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDownstartdate() {
		return this.downstartdate;
	}

	public void setDownstartdate(String downstartdate) {
		this.downstartdate = downstartdate;
	}

	public String getDownenddate() {
		return this.downenddate;
	}

	public void setDownenddate(String downenddate) {
		this.downenddate = downenddate;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}