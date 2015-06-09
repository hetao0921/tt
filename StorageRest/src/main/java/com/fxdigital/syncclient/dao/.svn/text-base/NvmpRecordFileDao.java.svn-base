package com.fxdigital.syncclient.dao;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.util.ConvertMapUtil;
@Component
public class NvmpRecordFileDao extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpRecordFileDao.class);
	
	public NvmpRecordFileDao(){
		logger.info("初始化NvmpRecordFileDao");
	}
	
	
	public void saveNvmpRecordFile(NvmpRecordFile nvmpRecordFile) {

		try {
			save(nvmpRecordFile);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	

	
	
	public List<Object> queryObjectList(String sql){
		return querySqlList(sql);
	}
	

	
	
	public List<Object> queryAllObject(){
		String sql = "from NvmpRecordFile";
		return getAllList(sql);
	}
	
	public List<Object> queryLastObject(){
		String sql = "from NvmpRecordFile order by nvmpEndTime asc";
		return getAllList(sql);
	}
	
	
	public int updateEndTime(int currentFileID,Timestamp endTime){
		String hql="update NvmpRecordFile set nvmpEndTime='"+endTime+"' where nvmpFileId="+currentFileID;
		return executeUpdate(hql);
	}
	
	public int updateHintFlag(int currentFileID){
		String hql="update NvmpRecordFile set nvmpFileHint=1 where nvmpFileId="+currentFileID;
		return executeUpdate(hql);
	}
	
	
	public List<HashMap<String, String>> getNvmpRecordFileList(String baseUUid){
		String sql=ConvertMapUtil.map(NvmpRecordFile.class)
				+ " from NvmpRecordFile where nvmpBaseUuid='"+baseUUid+"'";
		return executeQuery(sql);
	}
	

	public int deleteRecordFile(int fileID){
		String hql="delete from NvmpRecordFile where nvmpFileId='"+fileID+"'";
		deleteOrUpdate(hql);
		return 1;
	}
	
	
	public List<HashMap<String, String>> getNvmpRecordFileBaseID(int fileID){
		String sql=ConvertMapUtil.map(NvmpRecordFile.class)
				+ " from NvmpRecordFile where nvmpFileId='"+fileID+"'";
		return executeQuery(sql);
	}
	/**
	 * 过滤文件
	 * @param baseUUid
	 * @return 
	 */
	public List<HashMap<String, String>> filterFileList(String baseUUid,Timestamp mintime,Timestamp maxtime){
		String sql=ConvertMapUtil.map(NvmpRecordFile.class)
				+ " from NvmpRecordFile where nvmpBaseUuid='"+baseUUid+"' and nvmpStartTime>'"+mintime+"' and nvmpEndTime<'"+maxtime+"'";
		return executeQuery(sql);
	}
}
