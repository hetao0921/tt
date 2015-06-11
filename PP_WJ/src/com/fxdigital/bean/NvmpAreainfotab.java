package com.fxdigital.bean;

/**
 * NvmpAreainfotab entity. @author MyEclipse Persistence Tools
 */

public class NvmpAreainfotab implements java.io.Serializable {

	// Fields

	private Long id;
	private String areaId;
	private String areaName;
	private String areaParentId;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpAreainfotab() {
	}

	/** minimal constructor */
	public NvmpAreainfotab(String areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}

	/** full constructor */
	public NvmpAreainfotab(String areaId, String areaName, String areaParentId,
			String centerId) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaParentId = areaParentId;
		this.centerId = centerId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaParentId() {
		return this.areaParentId;
	}

	public void setAreaParentId(String areaParentId) {
		this.areaParentId = areaParentId;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}