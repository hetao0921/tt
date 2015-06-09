package com.fxdigital.syncclient.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.NvmpRecordBase;
import com.fxdigital.syncclient.bean.NvmpRecordMark;
import com.fxdigital.syncclient.util.ConvertMapUtil;
@Component
public class NvmpRecordMarkDao  extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpRecordMarkDao.class);
	/**
	 * 保存对对象信息
	 * @param nvmpRecordMark
	 */
	public void insert(NvmpRecordMark nvmpRecordMark) {
		try {
			save(nvmpRecordMark);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	/**
	 * 获取所有对象信息
	 * @return
	 */
	public List<Object> queryAllObject(){
		String sql = "from NvmpRecordMark";
		return getAllList(sql);
	}
	
	
	/**
	 * 停止当前录像
	 * @param nvmpMarkUuid
	 * @param endTime
	 * @return
	 */
	public int updateEndTime(String nvmpMarkUuid,Timestamp endTime){
		String hql="update NvmpRecordMark set nvmpEndTime='"+endTime+"' where nvmpMarkUuid='"+nvmpMarkUuid+"'";
		return executeUpdate(hql);
	}
	
	
	/**
	 * 获取mark记录
	 * @param recordID
	 * @return
	 */
	public List<HashMap<String, String>> getnvmpRecordMarkList(String markUUid){
		String sql=ConvertMapUtil.map(NvmpRecordMark.class)
				+ " from NvmpRecordMark where nvmpMarkUuid='"+markUUid+"'";
		return executeQuery(sql);
	}
	
	
	public List<HashMap<String, String>> getnvmpMarkList(String baseUUid){
		String sql=ConvertMapUtil.map(NvmpRecordMark.class)
				+ " from NvmpRecordMark where nvmpBaseUuid='"+baseUUid+"'";
		return executeQuery(sql);
	}
	
	

}
