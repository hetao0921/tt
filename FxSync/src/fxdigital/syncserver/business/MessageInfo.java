package fxdigital.syncserver.business;

import java.util.HashMap;
import java.util.List;

public class MessageInfo {
	
	List<HashMap<String, String>> allList=null;
	List<HashMap<String, String>> incrementList=null;
	public List<HashMap<String, String>> getAllList() {
		return allList;
	}
	public void setAllList(List<HashMap<String, String>> allList) {
		this.allList = allList;
	}
	public List<HashMap<String, String>> getIncrementList() {
		return incrementList;
	}
	public void setIncrementList(List<HashMap<String, String>> incrementList) {
		this.incrementList = incrementList;
	}
	
	

}
