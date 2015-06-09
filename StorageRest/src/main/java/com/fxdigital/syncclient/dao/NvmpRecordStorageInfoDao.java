package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.NvmpRecordStorageInfo;
@Component
public class NvmpRecordStorageInfoDao extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpRecordStorageInfoDao.class);
	
	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 * 
	 * 
	 */
	public void saveNvmpRecordStorageInfo(NvmpRecordStorageInfo nvmpRecordStorageInfo) {

		try {
			save(nvmpRecordStorageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	
	public List<HashMap<String, String>> getSqlList(String sql){
		return executeQuery(sql);
		
	}
	
	
	
	public List<Object> queryAllObject(){
		String sql = "from NvmpRecordStorageInfo";
		return getAllList(sql);
	}
	
	
	public int deleteAllObject(){
		String sql = "delete from NvmpRecordStorageInfo";
	    deleteOrUpdate(sql);
		return 1;
	}
}
