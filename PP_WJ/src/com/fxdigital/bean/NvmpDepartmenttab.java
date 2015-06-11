package com.fxdigital.bean;

/**
 * NvmpDepartmenttab entity. @author MyEclipse Persistence Tools
 */

public class NvmpDepartmenttab implements java.io.Serializable {

	// Fields

	private Long id;
	private String departId;
	private String departName;
	private String departDesc;
	private String departParentId;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpDepartmenttab() {
	}

	/** minimal constructor */
	public NvmpDepartmenttab(String departId, String departName) {
		this.departId = departId;
		this.departName = departName;
	}

	/** full constructor */
	public NvmpDepartmenttab(String departId, String departName,
			String departDesc, String departParentId, String centerId) {
		this.departId = departId;
		this.departName = departName;
		this.departDesc = departDesc;
		this.departParentId = departParentId;
		this.centerId = centerId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartId() {
		return this.departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartDesc() {
		return this.departDesc;
	}

	public void setDepartDesc(String departDesc) {
		this.departDesc = departDesc;
	}

	public String getDepartParentId() {
		return this.departParentId;
	}

	public void setDepartParentId(String departParentId) {
		this.departParentId = departParentId;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}