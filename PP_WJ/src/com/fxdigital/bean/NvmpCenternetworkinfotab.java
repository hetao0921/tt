package com.fxdigital.bean;

/**
 * NvmpCenternetworkinfotab entity. @author MyEclipse Persistence Tools
 */

public class NvmpCenternetworkinfotab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String netWorkNodeId;
	private String netWorkNodeIp;
	private Integer netWorkNodePort;
	private Integer isParentFlag;
	private Integer isControlFlag;
	private String centerId;

	// Constructors

	/** default constructor */
	public NvmpCenternetworkinfotab() {
	}

	/** full constructor */
	public NvmpCenternetworkinfotab(String netWorkNodeId, String netWorkNodeIp,
			Integer netWorkNodePort, Integer isParentFlag,
			Integer isControlFlag, String centerId) {
		this.netWorkNodeId = netWorkNodeId;
		this.netWorkNodeIp = netWorkNodeIp;
		this.netWorkNodePort = netWorkNodePort;
		this.isParentFlag = isParentFlag;
		this.isControlFlag = isControlFlag;
		this.centerId = centerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNetWorkNodeId() {
		return this.netWorkNodeId;
	}

	public void setNetWorkNodeId(String netWorkNodeId) {
		this.netWorkNodeId = netWorkNodeId;
	}

	public String getNetWorkNodeIp() {
		return this.netWorkNodeIp;
	}

	public void setNetWorkNodeIp(String netWorkNodeIp) {
		this.netWorkNodeIp = netWorkNodeIp;
	}

	public Integer getNetWorkNodePort() {
		return this.netWorkNodePort;
	}

	public void setNetWorkNodePort(Integer netWorkNodePort) {
		this.netWorkNodePort = netWorkNodePort;
	}

	public Integer getIsParentFlag() {
		return this.isParentFlag;
	}

	public void setIsParentFlag(Integer isParentFlag) {
		this.isParentFlag = isParentFlag;
	}

	public Integer getIsControlFlag() {
		return this.isControlFlag;
	}

	public void setIsControlFlag(Integer isControlFlag) {
		this.isControlFlag = isControlFlag;
	}

	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

}