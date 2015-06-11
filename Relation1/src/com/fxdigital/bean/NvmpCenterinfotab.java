package com.fxdigital.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpCenterinfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_centerinfotab", catalog = "nvmp")
public class NvmpCenterinfotab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String centerId;
	private String centerName;
	private String centerDesc;
	private String centerIp;
	private String centerMask;
	private String centerGate;
	private Integer centerPort;
	private String loginUserName;
	private String loginPwd;
	private String dataBaseIp;
	private String dataBaseUser;
	private String dataBasePwd;
	private String parentCenterIp;
	private Integer parentCenterPort;
	private String domainName;
	private String dnsip;
	private String centerVar;
	private Integer linkMode;
	private String syncServerIp;
	private Integer syncServerPort;
	private String routeMode;

	// Constructors

	/** default constructor */
	public NvmpCenterinfotab() {
	}

	/** minimal constructor */
	public NvmpCenterinfotab(String centerId, String centerName) {
		this.centerId = centerId;
		this.centerName = centerName;
	}

	/** full constructor */
	public NvmpCenterinfotab(String centerId, String centerName,
			String centerDesc, String centerIp, String centerMask,
			String centerGate, Integer centerPort, String loginUserName,
			String loginPwd, String dataBaseIp, String dataBaseUser,
			String dataBasePwd, String parentCenterIp,
			Integer parentCenterPort, String domainName, String dnsip,
			String centerVar, Integer linkMode, String syncServerIp,
			Integer syncServerPort, String routeMode) {
		this.centerId = centerId;
		this.centerName = centerName;
		this.centerDesc = centerDesc;
		this.centerIp = centerIp;
		this.centerMask = centerMask;
		this.centerGate = centerGate;
		this.centerPort = centerPort;
		this.loginUserName = loginUserName;
		this.loginPwd = loginPwd;
		this.dataBaseIp = dataBaseIp;
		this.dataBaseUser = dataBaseUser;
		this.dataBasePwd = dataBasePwd;
		this.parentCenterIp = parentCenterIp;
		this.parentCenterPort = parentCenterPort;
		this.domainName = domainName;
		this.dnsip = dnsip;
		this.centerVar = centerVar;
		this.linkMode = linkMode;
		this.syncServerIp = syncServerIp;
		this.syncServerPort = syncServerPort;
		this.routeMode = routeMode;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CenterID", nullable = false, length = 64)
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "CenterName", nullable = false, length = 64)
	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	@Column(name = "CenterDesc", length = 256)
	public String getCenterDesc() {
		return this.centerDesc;
	}

	public void setCenterDesc(String centerDesc) {
		this.centerDesc = centerDesc;
	}

	@Column(name = "CenterIP", length = 32)
	public String getCenterIp() {
		return this.centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	@Column(name = "CenterMask", length = 32)
	public String getCenterMask() {
		return this.centerMask;
	}

	public void setCenterMask(String centerMask) {
		this.centerMask = centerMask;
	}

	@Column(name = "CenterGate", length = 32)
	public String getCenterGate() {
		return this.centerGate;
	}

	public void setCenterGate(String centerGate) {
		this.centerGate = centerGate;
	}

	@Column(name = "CenterPort")
	public Integer getCenterPort() {
		return this.centerPort;
	}

	public void setCenterPort(Integer centerPort) {
		this.centerPort = centerPort;
	}

	@Column(name = "LoginUserName", length = 32)
	public String getLoginUserName() {
		return this.loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	@Column(name = "LoginPwd", length = 32)
	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	@Column(name = "DataBaseIP", length = 32)
	public String getDataBaseIp() {
		return this.dataBaseIp;
	}

	public void setDataBaseIp(String dataBaseIp) {
		this.dataBaseIp = dataBaseIp;
	}

	@Column(name = "DataBaseUser", length = 32)
	public String getDataBaseUser() {
		return this.dataBaseUser;
	}

	public void setDataBaseUser(String dataBaseUser) {
		this.dataBaseUser = dataBaseUser;
	}

	@Column(name = "DataBasePwd", length = 32)
	public String getDataBasePwd() {
		return this.dataBasePwd;
	}

	public void setDataBasePwd(String dataBasePwd) {
		this.dataBasePwd = dataBasePwd;
	}

	@Column(name = "ParentCenterIP", length = 32)
	public String getParentCenterIp() {
		return this.parentCenterIp;
	}

	public void setParentCenterIp(String parentCenterIp) {
		this.parentCenterIp = parentCenterIp;
	}

	@Column(name = "ParentCenterPort")
	public Integer getParentCenterPort() {
		return this.parentCenterPort;
	}

	public void setParentCenterPort(Integer parentCenterPort) {
		this.parentCenterPort = parentCenterPort;
	}

	@Column(name = "DomainName", length = 128)
	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Column(name = "DNSIP", length = 32)
	public String getDnsip() {
		return this.dnsip;
	}

	public void setDnsip(String dnsip) {
		this.dnsip = dnsip;
	}

	@Column(name = "CenterVar", length = 32)
	public String getCenterVar() {
		return this.centerVar;
	}

	public void setCenterVar(String centerVar) {
		this.centerVar = centerVar;
	}

	@Column(name = "LinkMode")
	public Integer getLinkMode() {
		return this.linkMode;
	}

	public void setLinkMode(Integer linkMode) {
		this.linkMode = linkMode;
	}

	@Column(name = "SyncServerIP", length = 32)
	public String getSyncServerIp() {
		return this.syncServerIp;
	}

	public void setSyncServerIp(String syncServerIp) {
		this.syncServerIp = syncServerIp;
	}

	@Column(name = "SyncServerPort")
	public Integer getSyncServerPort() {
		return this.syncServerPort;
	}

	public void setSyncServerPort(Integer syncServerPort) {
		this.syncServerPort = syncServerPort;
	}

	@Column(name = "RouteMode", length = 16)
	public String getRouteMode() {
		return this.routeMode;
	}

	public void setRouteMode(String routeMode) {
		this.routeMode = routeMode;
	}

}