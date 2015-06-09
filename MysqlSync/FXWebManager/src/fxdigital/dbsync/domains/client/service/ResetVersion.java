package fxdigital.dbsync.domains.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import fxdigital.dbsync.domains.client.pojo.MessageBean;

/**
 * @author  het
 *还原版本信息处理类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.service
 */
public class ResetVersion {
	
	public static ResetVersion resetVersion=null;
	public static ResetVersion getInstance(){
		if(null==resetVersion){
			resetVersion=new ResetVersion();
		}
		return resetVersion;
	}
	
	public List<Map<String, String>> getServerVersion(){
		List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		String centerid="";
		MessageBean bean=new MessageBean();
		bean.setCenterid(centerid);
		
		String json=JSON.toJSONString(bean);
		
		
		return list;
	}
	
	public List<Map<String, String>> sendResetCommand(){
		List<Map<String, String>> list=new ArrayList<Map<String, String>>();
		return list;
	}

}
