package com.fxdigital.syncclient.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.NvmpRecordBase;
import com.fxdigital.syncclient.util.ConvertMapUtil;

@Component
public class NvmpRecordBaseDao extends BaseDao{
	
	private static final Logger logger = Logger.getLogger(NvmpRecordBaseDao.class);
	
	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 * 
	 * 
	 */
	public void saveNvmpRecordBase(NvmpRecordBase nvmpRecordBase) {

		try {
			save(nvmpRecordBase);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	

	
	public List<HashMap<String, String>> getSqlList(String sql){
		return executeQuery(sql);
		
	}
	
	

	
	public List<HashMap<String, String>> getnvmpRecordBaseList(String nvmpBaseUUId){
		String sql=ConvertMapUtil.map(NvmpRecordBase.class)
				+ " from NvmpRecordBase where nvmpBaseUuid='"+nvmpBaseUUId+"'";
		return executeQuery(sql);
	}
	
	
	
    /**
     * 
     * @param nvmpBaseUuid
     * @param endTime
     * @return
     */
	public int updateBaseRecord(String nvmpBaseUuid,Timestamp endTime){
		String hql="update NvmpRecordBase set nvmpStopTime='"+endTime+"',nvmpIsRecording=0 where nvmpBaseUuid='"+nvmpBaseUuid+"'";
		return executeUpdate(hql);
	}
}
