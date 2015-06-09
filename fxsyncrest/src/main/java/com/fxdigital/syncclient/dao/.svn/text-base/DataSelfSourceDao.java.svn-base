package com.fxdigital.syncclient.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.DataOperate;
import com.fxdigital.syncclient.bean.DataSelfSource;
import com.fxdigital.syncclient.util.ConvertMapUtil;


/**
 * @author het 本地个人数据记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataSelfSourceDao extends BaseDao{
	private static Logger logger = Logger.getLogger(DataSelfSourceDao.class);
	
	/**
	 * 插入个人的记录
	 * 
	 */
	public void insert(DataSelfSource dataSelfSource) {
			save(dataSelfSource);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllSelfSource() {
		return executeQuery(ConvertMapUtil.map(DataSelfSource.class)
				+ " from DataSelfSource");
	}

	/**
	 * 获取所有的日志记录
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllSelfSourceRecord() {
		String sql =ConvertMapUtil.map(DataSelfSource.class)
				+ " from DataSelfSource order by id asc";
		return executeQuery(sql);

	}

	/**
	 * 删除本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteAllSelfSource() {
		String hql="delete from DataSelfSource";
		executeUpdate(hql);
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpState(String uuid, int state) {
		String sql = "update DataSelfSource set flag='" + state
				+ "' where uuid='" + uuid + "'";
		logger.info("updateUpState:" + sql);
		executeUpdate(sql);
		
	}

	public int getCurrentVersion(String centerId) {
		int version = 0;
		String sql = "select new Map(version as version) from DataSelfSource where uuid='"
				+ centerId + "'";
		logger.info("getCurrentVersion:" + sql);
		List<HashMap<String, String>> strList = null;
	    strList = executeQuery(sql);
		if (null != strList && strList.size() > 0) {
			version = Integer
					.valueOf(null == strList.get(0).get("version") ? strList
							.get(0).get("version".toUpperCase()) : strList.get(
							0).get("version"));
		}
		return version;
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpStartState(String uuid, int state, String upStartTime,
			String upEndTime) {
		String sql = "update DataSelfSource set flag='" + state
				+ "',startdate='" + upStartTime + "',enddate='" + upEndTime
				+ "' where uuid='" + uuid + "'";
		logger.info("updateUpState:" + sql);
		executeUpdate(sql);
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpEndStates(String uuid, int state, String upEndTime) {
		String sql = "update DataSelfSource set flag='" + state
				+ "',enddate='" + upEndTime + "' where uuid='" + uuid + "' ";
		logger.info("updateUpEndStates:" + sql);
	    executeUpdate(sql);
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpEndVesions(String uuid, int state, String upEndTime,
			int version) {
		String sql = "update DataSelfSource set flag='" + state
				+ "',enddate='" + upEndTime + "',version='" + version
				+ "' where uuid='" + uuid + "' ";
		logger.info("updateUpEndStates:" + sql);
		executeUpdate(sql);
		
	}

	public void updateVersion(String uuid, int version) {
		String sql = "update DataSelfSource set version='"
				+ version + "' where uuid='" + uuid + "' ";
		logger.info("updateVersion:" + sql);
		executeUpdate(sql);
	
	}

	public void recordAllStates(String centerid, String centername,
			String nextSelfVersion, int upState) {
		DataSelfSource dataSelfSource = new DataSelfSource();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String upStartTime = sdf.format(d);
		String upEndTime = sdf.format(d);
		dataSelfSource.setVersion(Integer.valueOf(nextSelfVersion));
		dataSelfSource.setUuid(centerid);
		dataSelfSource.setUuname(centername);
		dataSelfSource.setStartdate(upStartTime);
		dataSelfSource.setFlag(upState);
		dataSelfSource.setEnddate(upEndTime);
		save(dataSelfSource);
	}
	
	
}
