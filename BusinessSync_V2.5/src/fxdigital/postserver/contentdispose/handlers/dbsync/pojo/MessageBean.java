package fxdigital.postserver.contentdispose.handlers.dbsync.pojo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @author  het
 *消息实体类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.pojo
 */
public class MessageBean {
	String flag;
	String xml;
	String centerid;
	String centername;
	int version;
	List<HashMap<String, String>> list;
	List<String> strlist;
	
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
	
	
	
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	public static void main(String[] args) {
		MessageBean bean=new MessageBean();
		List<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map=new HashMap<String, String>();
		bean.setFlag("1");
		map.put("001", "002");
		int version=10;
		map.put("version", String.valueOf(version));
		list.add(map);
		
		
		bean.setList(list);
		
		
		
		System.out.println("flag     "+bean.getFlag());
		System.out.println("list     "+bean.getList());
		
		
		
		String json = JSON.toJSONString(bean);
		System.out.println("json------"+json);
		MessageBean bean2 = JSON.parseObject(json, MessageBean.class);
		
		
		
		System.out.println("bean2------"+bean2);
		
		 
		System.out.println("flag2------"+bean2.getFlag());
		System.out.println("list2------"+bean2.getList());
		
	
	}
}
