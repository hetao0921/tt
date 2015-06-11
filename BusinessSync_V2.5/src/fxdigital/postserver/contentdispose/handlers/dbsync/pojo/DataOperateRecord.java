package fxdigital.postserver.contentdispose.handlers.dbsync.pojo;

/**
 * @author  het
 *数据操作实体类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.pojo
 */

public class DataOperateRecord {

	  String uuID;
	  String sessionID;
	  String operate;
	  String operateIp;
	  String operateTime;
	  String centerId;
	  String centerName;
	  String errorInfo;
	public String getUuID() {
		return uuID;
	}
	public void setUuID(String uuID) {
		this.uuID = uuID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getOperateIp() {
		return operateIp;
	}
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
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
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	  
	  
	  


}
