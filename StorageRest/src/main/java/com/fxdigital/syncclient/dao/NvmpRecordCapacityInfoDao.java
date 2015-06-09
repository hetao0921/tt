package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.bean.NvmpRecordCapacityInfo;

@Repository
public class NvmpRecordCapacityInfoDao  extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpRecordCapacityInfoDao.class);
	
	
	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 * 
	 * 
	 */
	public void saveNvmpRecordStorageInfo(NvmpRecordCapacityInfo nvmpRecordCapacityInfo) {

		try {
			save(nvmpRecordCapacityInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	
	
	public List<HashMap<String, String>> getSqlList(String sql){
		return executeQuery(sql);
		
	}
	
	
	
	public List<Object> queryAllObject(){
		String sql = "from NvmpRecordCapacityInfo";
		return getAllList(sql);
	}
	
	
	public int deleteAllObject(){
		String sql = "delete from NvmpRecordCapacityInfo";
	    deleteOrUpdate(sql);
		return 1;
	}

}
