package fxdigital.dbsync.domains.client.pojo;
/**
 * @author  het
 * 数据操作互斥锁实体类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.pojo
 */
public class DataOperate {

	int ID;
	String operate;
	int flag;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	


}
