package fxdigital.postserver.contentdispose.handlers.dbsync.pojo;
/**
 * @author  het
 *上下级同步服务器间同步时间设置实体类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.pojo
 */
public class SyncDataSet {
	String centerId;
	String centerName;
	int autoTime;
	String setDate;
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
	public int getAutoTime() {
		return autoTime;
	}
	public void setAutoTime(int autoTime) {
		this.autoTime = autoTime;
	}
	public String getSetDate() {
		return setDate;
	}
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	
}
