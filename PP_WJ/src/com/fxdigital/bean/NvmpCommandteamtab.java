package com.fxdigital.bean;

/**
 * NvmpCommandteamtab entity. @author MyEclipse Persistence Tools
 */

public class NvmpCommandteamtab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String teamId;
	private String teamName;
	private String teamDesc;
	private Integer teamType;
	private Integer conferenceStatus;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpCommandteamtab() {
	}

	/** minimal constructor */
	public NvmpCommandteamtab(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}

	/** full constructor */
	public NvmpCommandteamtab(String teamId, String teamName, String teamDesc,
			Integer teamType, Integer conferenceStatus, String centerId) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamDesc = teamDesc;
		this.teamType = teamType;
		this.conferenceStatus = conferenceStatus;
		this.centerId = centerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeamId() {
		return this.teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDesc() {
		return this.teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public Integer getTeamType() {
		return this.teamType;
	}

	public void setTeamType(Integer teamType) {
		this.teamType = teamType;
	}

	public Integer getConferenceStatus() {
		return this.conferenceStatus;
	}

	public void setConferenceStatus(Integer conferenceStatus) {
		this.conferenceStatus = conferenceStatus;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}