package fxdigital.postserver.contentdispose.handlers.dbsync.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hibernate.bean.DataOperateRecord;

import fxdigital.postserver.contentdispose.handlers.dbsync.db.DBConn;


/**
 * @author  het
 *数据操作记录访问层
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
public class DataOperateRecordDao {

	private static final Logger logger = Logger.getLogger(DataOperateRecordDao.class);
	public static DataOperateRecordDao dataOperateRecordDao = null;

	/**
	 * 实例化对象
	 * 
	 */
	public static DataOperateRecordDao getInstance() {
		if (null == dataOperateRecordDao) {
			dataOperateRecordDao = new DataOperateRecordDao();
		}
		return dataOperateRecordDao;
	}

	/**
	 * 插入下载的记录
	 * 
	 */
	public void insert(DataOperateRecord dataOperateRecord) {

		try {
			DBConn.getDBConn().save(dataOperateRecord);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 获取所有的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getAllRecord() {
		DBConn db = DBConn.getDBConn();
		String hql="from DataOperateRecord";
		String[] ss = db.executeQuery(hql).get(
				0);
		return ss;
	}
	
	/**
	 * 获取所有的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public List<HashMap<String, String>> getAllOperateRecord() {
		DBConn db = DBConn.getDBConn();
        String sql="select new Map(uuID as uuID,sessionID as sessionID,operate as operate,operateIp as operateIp,operateTime as operateTime,centerId as centerId,centerName as centerName,errorInfo as errorInfo) from DataOperateRecord";
		List<HashMap<String, String>> list =db.executeQuerySql(sql);
		return list;
	}

	/**
	 * 获取下载时的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getOneRecord(String operate) {
		DBConn db = DBConn.getDBConn();
		String hql=
				"from DataOperateRecord where operate = '" + operate
				+ "'";
		String[] ss = db.executeQuery(hql).get(0);
		return ss;
	}


}
