package fxdigital.postserver.contentdispose.handlers.dbsync.pojo;

/**
 * @author  het
 *上传下载数据资源实体类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.pojo
 */
public class DataSource {
	String centerId;
	String centerName;
	String centerip;
	String uuID;
	String update;
	String fileAddress;
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
	
	

	public String getCenterip() {
		return centerip;
	}
	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}
	public String getUuID() {
		return uuID;
	}
	public void setUuID(String uuID) {
		this.uuID = uuID;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
