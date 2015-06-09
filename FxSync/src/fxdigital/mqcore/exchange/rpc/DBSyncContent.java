package fxdigital.mqcore.exchange.rpc;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author fucz
 * @version 2014-7-11
 */
public class DBSyncContent{
	protected String content;
	protected String sender;
	protected String receiver;
	
	
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

	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

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


	
	
}
