package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataSelfSource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_self_source", catalog = "jms_client")
public class DataSelfSource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uuid;
	private String uuname;
	private Integer version;
	private String startdate;
	private String enddate;
	private Integer flag;

	// Constructors

	/** default constructor */
	public DataSelfSource() {
	}

	/** full constructor */
	public DataSelfSource(String uuid, String uuname, Integer version,
			String startdate, String enddate, Integer flag) {
		this.uuid = uuid;
		this.uuname = uuname;
		this.version = version;
		this.startdate = startdate;
		this.enddate = enddate;
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

	@Column(name = "uuid", length = 45)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "uuname", length = 45)
	public String getUuname() {
		return this.uuname;
	}

	public void setUuname(String uuname) {
		this.uuname = uuname;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "startdate", length = 45)
	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate", length = 45)
	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}