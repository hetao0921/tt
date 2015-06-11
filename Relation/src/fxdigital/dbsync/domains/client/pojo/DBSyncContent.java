package fxdigital.dbsync.domains.client.pojo;

import java.util.HashMap;
import java.util.List;

import fxdigital.rpc.AbstractContent;

/**
 * @author  het
 *消息实体类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.pojo
 */
public class DBSyncContent extends AbstractContent{

	int operateid;
	private int version;
	String flag;
	String xml;
	String centerid;
	String centername;
	String centerip;
	
	List<HashMap<String, String>> list;
	List<HashMap<String, String>> secondList;
	List<String> strlist;

	
	
	
	public int getOperateid() {
		return operateid;
	}

	public void setOperateid(int operateid) {
		this.operateid = operateid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getCenterid() {
		return centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getCentername() {
		return centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public List<HashMap<String, String>> getList() {
		return list;
	}

	public void setList(List<HashMap<String, String>> list) {
		this.list = list;
	}

	public List<String> getStrlist() {
		return strlist;
	}

	public void setStrlist(List<String> strlist) {
		this.strlist = strlist;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	
	public String getCenterip() {
		return centerip;
	}

	public void setCenterip(String centerip) {
		this.centerip = centerip;
	}

	public List<HashMap<String, String>> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<HashMap<String, String>> secondList) {
		this.secondList = secondList;
	}

	@Override
	public String getType() {
		return DBSyncType.TYPE;
	}
	
}
