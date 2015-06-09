package fxdigital.dbsync.domains.client.pojo;
/**
 * @author  het
 * 本地个人资源实体类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.pojo
 */
public class DataSelfSource {
	int ID;
	String uuID;
	String uuName;
	int version;
	String startDate;
	String endDate;
	int flag;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUuID() {
		return uuID;
	}
	public void setUuID(String uuID) {
		this.uuID = uuID;
	}
	
	
	
	public String getUuName() {
		return uuName;
	}
	public void setUuName(String uuName) {
		this.uuName = uuName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
	
}
