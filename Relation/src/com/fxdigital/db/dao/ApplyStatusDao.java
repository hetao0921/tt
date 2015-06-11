package com.fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.bean.SyncApplystatus;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;
import com.hibernate.db.Hibernate;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-10
 */
@Repository
public class ApplyStatusDao{

	
	@SuppressWarnings("unchecked")
	public String query(int status){
		String map=ConvertMapUtil.map(SyncApplystatus.class);
		String sql = "select "+map+" from SyncApplystatus where statusId='"+status+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		if(list != null && list.size() == 1){
			Map<String, String> data = list.get(0);
			return (String)data.get("StatusDesc");
		}else{
			return "0";
		}
	}
	
	@SuppressWarnings("unchecked")
	public Integer query(String status){
		String map=ConvertMapUtil.map(SyncApplystatus.class);
		String sql = "select "+map+" from SyncApplystatus where statusDesc='"+status+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		if(list != null && list.size() == 1){
			Map<String, String> data =  list.get(0);
			return Integer.parseInt(data.get("StatusID"));
		}else{
			return 0;
		}
	}
	
}
