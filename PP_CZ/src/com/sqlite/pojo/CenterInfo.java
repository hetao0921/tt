/**
 * 中心信息表对象
 * gj
 * 2012.2.8
 */
package com.sqlite.pojo;

public class CenterInfo {
 
	private int id;
	private String centerId;
	private String centerName;
	private String centerDesc;
	private String centerIp;
	private String centerMask;
	private String centerGate;
	private int centerPort;
	private String loginUserName;
	private String loginPwd;
	private String dataBaseIp;             //数据库IP
	private String dataBaseUser;           //数据库用户名
	private String dataBasePwd;            //数据库密码
	private String parentCenterIP;         //上级中心IP
	private int parentCenterPort;
	private String domainName;             //域名
	private String dnsIp;                  //DNS地址
	private String centerVar;              //版本
	private int linkMode;                  //连接模式
	private String syncServerIP;
	private int syncServerPort;
	private String routeMode;              //通信模式
	public CenterInfo() {
		super();
	}
	public CenterInfo(int id, String centerId, String centerName,
			String centerDesc, String centerIp, String centerMask,
			String centerGate, int centerPort, String loginUserName,
			String loginPwd, String dataBaseIp, String dataBaseUser,
			String dataBasePwd, String parentCenterIP, int parentCenterPort,
			String domainName, String dnsIp, String centerVar, int linkMode,
			String syncServerIP, int syncServerPort, String routeMode) {
		super();
		this.id = id;
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
		this.parentCenterIP = parentCenterIP;
		this.parentCenterPort = parentCenterPort;
		this.domainName = domainName;
		this.dnsIp = dnsIp;
		this.centerVar = centerVar;
		this.linkMode = linkMode;
		this.syncServerIP = syncServerIP;
		this.syncServerPort = syncServerPort;
		this.routeMode = routeMode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterDesc() {
		return centerDesc;
	}
	public void setCenterDesc(String centerDesc) {
		this.centerDesc = centerDesc;
	}
	public String getCenterIp() {
		return centerIp;
	}
	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}
	public String getCenterMask() {
		return centerMask;
	}
	public void setCenterMask(String centerMask) {
		this.centerMask = centerMask;
	}
	public String getCenterGate() {
		return centerGate;
	}
	public void setCenterGate(String centerGate) {
		this.centerGate = centerGate;
	}
	public int getCenterPort() {
		return centerPort;
	}
	public void setCenterPort(int centerPort) {
		this.centerPort = centerPort;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getDataBaseIp() {
		return dataBaseIp;
	}
	public void setDataBaseIp(String dataBaseIp) {
		this.dataBaseIp = dataBaseIp;
	}
	public String getDataBaseUser() {
		return dataBaseUser;
	}
	public void setDataBaseUser(String dataBaseUser) {
		this.dataBaseUser = dataBaseUser;
	}
	public String getDataBasePwd() {
		return dataBasePwd;
	}
	public void setDataBasePwd(String dataBasePwd) {
		this.dataBasePwd = dataBasePwd;
	}
	public String getParentCenterIP() {
		return parentCenterIP;
	}
	public void setParentCenterIP(String parentCenterIP) {
		this.parentCenterIP = parentCenterIP;
	}
	public int getParentCenterPort() {
		return parentCenterPort;
	}
	public void setParentCenterPort(int parentCenterPort) {
		this.parentCenterPort = parentCenterPort;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDnsIp() {
		return dnsIp;
	}
	public void setDnsIp(String dnsIp) {
		this.dnsIp = dnsIp;
	}
	public String getCenterVar() {
		return centerVar;
	}
	public void setCenterVar(String centerVar) {
		this.centerVar = centerVar;
	}
	public int getLinkMode() {
		return linkMode;
	}
	public void setLinkMode(int linkMode) {
		this.linkMode = linkMode;
	}
	public String getSyncServerIP() {
		return syncServerIP;
	}
	public void setSyncServerIP(String syncServerIP) {
		this.syncServerIP = syncServerIP;
	}
	public int getSyncServerPort() {
		return syncServerPort;
	}
	public void setSyncServerPort(int syncServerPort) {
		this.syncServerPort = syncServerPort;
	}
	public String getRouteMode() {
		return routeMode;
	}
	public void setRouteMode(String routeMode) {
		this.routeMode = routeMode;
	}
}
