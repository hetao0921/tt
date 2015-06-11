package com.fxdigital.syncclient.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.DataOperate;
import com.fxdigital.syncclient.bean.DataOperateRecord;
import com.fxdigital.syncclient.util.ConvertMapUtil;

/**
 * @author het 本地操作记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataOperateRecordDao extends BaseDao {
	private static Logger logger = Logger.getLogger(DataOperateRecordDao.class);

	/**
	 * 插入下载的记录
	 * 
	 */
	public void insert(DataOperateRecord dataOperateRecord) {

		save(dataOperateRecord);

	}

	/**
	 * 更新操作的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public void updateOperateRecord(int id, String centerid,
			String operateinfo, String errorinfo) {
		String sql = "update DataOperateRecord set operateinfo='" + operateinfo
				+ "',errorinfo='" + errorinfo + "' where id='" + id
				+ "' and centerid='" + centerid + "'";
		logger.info("updateOperateRecord  " + sql);
		executeUpdate(sql);

	}

	/**
	 * 更改操作记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getAllRecord() {
		String hql = "from DataOperateRecord";
		String[] ss = executeQueryStrArray(hql).get(0);
		return ss;
	}

	/**
	 * 当前记录是否存在
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean getCurrentRecord(int id, String centerid) {
		String sql = "from DataOperateRecord where id='" + id
				+ "' and centerid='" + centerid + "'";
		String[] ss = null;
		if (executeQueryStrArray(sql).size() > 0
				&& null != executeQueryStrArray(sql)) {
			ss = executeQueryStrArray(sql).get(0);
		}
		logger.info("ss" + ss);

		if (null != ss) {
			return true;
		} else
			return false;

	}

	/**
	 * 获取最大记录ID
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public int getMaxId() {
		String hql = ConvertMapUtil.map(DataOperateRecord.class)
				+ " from DataOperateRecord";
		List<HashMap<String, String>> list = executeQuery(hql);
		HashMap<String, String> ss = null;
		int id = 0;
		if (list.size() > 0 && null != list) {
			String maxHql = "select new Map(max(id) as id) from DataOperateRecord";
			List<HashMap<String, String>> maxlist = null;
			try {
				maxlist = executeQuery(maxHql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("error-----------" + e);
			}
			ss = maxlist.get(0);
			logger.info("ss-----------" + ss);
			if (null != ss && null != ss.get("id")) {
				id = Integer.valueOf(ss.get("id"));
			}
		}
		return id;

	}

	/**
	 * 获取所有的日志记录
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllLogRecord(String centerid){
		String sql = "select new Map(uuid as uuid,operatorsessionid as operatorsessionid,operatorip as operatorip,operatetime as operatetime,operate as operate,centerid as centerid,operateinfo as operateinfo,centername as centername,errorinfo as errorinfo) from DataOperateRecord where centerid='"
				+ centerid + "' order by operatetime desc";
		logger.info("getAllLogRecord" + sql);
		return executeQuery(sql);

	}

	/**
	 * 获取下载时的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getOneUserRecord(String uuid, String operatorsessionid) {
		String hql = "from DataOperateRecord where uuid='" + uuid
				+ "' and operatorsessionid='" + operatorsessionid + "'";
		String[] ss = executeQueryStrArray(hql).get(0);
		return ss;
	}
	
	

	/**
	 * 获取下载时的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getOneRecord(String operate) {
		String hql = "from DataOperateRecord where operate = '" + operate + "'";
		String[] ss = executeQueryStrArray(hql).get(0);
		return ss;
	}

	/**
	 * 插入初始操作记录
	 * 
	 * @param uuid
	 * @return
	 */
	public void insertInitOperateRecord(int id, String uuid, String sessionid,
			String operatorip, String operate, String centerid,
			String operateinfo, String centername, String errorinfo) {

		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setUuid(uuid);
		dataOperateRecord.setOperatorsessionid(sessionid);
		dataOperateRecord.setOperatorip(operatorip);
		dataOperateRecord.setOperate(operate);
		dataOperateRecord.setCenterid(centerid);
		dataOperateRecord.setOperateinfo(operateinfo);
		dataOperateRecord.setCentername(centername);
		dataOperateRecord.setErrorinfo(errorinfo);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operatetime = sdf.format(d);
		dataOperateRecord.setOperatetime(operatetime);
		save(dataOperateRecord);

	}

	/**
	 * 插入操作记录
	 * 
	 * @param uuid
	 * @return
	 */
	public void insertOperateRecord(int id, String uuid, String sessionid,
			String operatorip, String operate, String centerid,
			String operateinfo, String centername, String errorinfo) {

		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setUuid(uuid);
		dataOperateRecord.setOperatorsessionid(sessionid);
		dataOperateRecord.setOperatorip(operatorip);
		dataOperateRecord.setOperate(operate);
		dataOperateRecord.setCenterid(centerid);
		dataOperateRecord.setOperateinfo(operateinfo);
		dataOperateRecord.setCentername(centername);
		dataOperateRecord.setErrorinfo(errorinfo);
		Date d = new Date();
		// logger.info(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operatetime = sdf.format(d);
		dataOperateRecord.setOperatetime(operatetime);
		// 如果当前记录存在
		logger.info("id------------" + id + "centerid" + centerid);
		if (getCurrentRecord(id, centerid)) {
			updateOperateRecord(id, centerid, operateinfo, errorinfo);
		}
		// 不存在
		else {
			save(dataOperateRecord);
		}

	}
}
