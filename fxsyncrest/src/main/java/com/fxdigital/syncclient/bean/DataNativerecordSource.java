package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataNativerecordSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_nativerecord_source", catalog = "jms_client")
public class DataNativerecordSource implements java.io.Serializable {

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
	public DataNativerecordSource() {
	}

	/** full constructor */
	public DataNativerecordSource(String centerid, String centername,
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "centerid", length = 45)
	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	@Column(name = "centername", length = 45)
	public String getCentername() {
		return this.centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	@Column(name = "centerip", length = 55)
	public String getCenterip() {
		return this.centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "updatetime", length = 45)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "uuid", length = 45)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "downstartdate", length = 45)
	public String getDownstartdate() {
		return this.downstartdate;
	}

	public void setDownstartdate(String downstartdate) {
		this.downstartdate = downstartdate;
	}

	@Column(name = "downenddate", length = 45)
	public String getDownenddate() {
		return this.downenddate;
	}

	public void setDownenddate(String downenddate) {
		this.downenddate = downenddate;
	}

	@Column(name = "flag", length = 45)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}