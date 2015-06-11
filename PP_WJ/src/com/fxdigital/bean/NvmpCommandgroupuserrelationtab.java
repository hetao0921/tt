package com.fxdigital.bean;

/**
 * NvmpCommandgroupuserrelationtab entity. @author MyEclipse Persistence Tools
 */

public class NvmpCommandgroupuserrelationtab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String commandGroupId;
	private String departId;
	private String userId;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpCommandgroupuserrelationtab() {
	}

	/** minimal constructor */
	public NvmpCommandgroupuserrelationtab(String commandGroupId) {
		this.commandGroupId = commandGroupId;
	}

	/** full constructor */
	public NvmpCommandgroupuserrelationtab(String commandGroupId,
			String departId, String userId, String centerId) {
		this.commandGroupId = commandGroupId;
		this.departId = departId;
		this.userId = userId;
		this.centerId = centerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommandGroupId() {
		return this.commandGroupId;
	}

	public void setCommandGroupId(String commandGroupId) {
		this.commandGroupId = commandGroupId;
	}

	public String getDepartId() {
		return this.departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}