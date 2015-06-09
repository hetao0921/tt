package com.fxdigital.syncclient.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public class IncrementdataInfotabDao extends BaseDao{
	
	private static Logger logger = Logger.getLogger(IncrementdataInfotabDao.class);


	
	public List<Map<String, Object>> queryIncrementData(){
		String hql = "select * from nvmp.incrementdata_infotab order by id asc";
		List<Map<String, Object>> list=null;
		try{
		 list=executeQueryBlobList(hql);
		}catch(Exception e){
			logger.info("select table IncrementdataInfotab error :"+e);
//			System.exit(0);
		}
		return list;
	}
	
	public int deleteIncrementdataID(String id){
		
		String sql = "delete from IncrementdataInfotab where id='"+id+"'";
		return executeUpdate(sql);
	}
}
