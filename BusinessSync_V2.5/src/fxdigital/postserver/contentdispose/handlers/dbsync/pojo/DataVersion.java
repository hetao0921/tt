package fxdigital.postserver.contentdispose.handlers.dbsync.pojo;
/**
 * @author  het
 *本地数据最新版本实体类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.pojo
 */
public class DataVersion {
	String centerId;
	String centerName;
	String centerIp;
	int version;
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
	
	public String getCenterIp() {
		return centerIp;
	}
	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	

}
