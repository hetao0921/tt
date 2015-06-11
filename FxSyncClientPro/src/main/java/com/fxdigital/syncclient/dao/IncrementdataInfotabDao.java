package com.fxdigital.syncclient.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public class IncrementdataInfotabDao extends BaseDao{
	
	


	
	public List<Map<String, Object>> queryIncrementData(){
		String hql = "from IncrementdataInfotab order by id asc";
		return executeQueryToObjectList(hql);
	}
	
	public int deleteIncrementdataID(String id){
		
		String sql = "delete from IncrementdataInfotab where id='"+id+"'";
		return executeUpdate(sql);
	}
}
