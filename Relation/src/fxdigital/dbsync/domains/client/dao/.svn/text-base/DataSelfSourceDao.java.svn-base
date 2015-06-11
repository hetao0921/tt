package fxdigital.dbsync.domains.client.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hibernate.bean.DataSelfSource;

import fxdigital.dbsync.domains.client.db.DBConn;


/**
 * @author het 本地个人数据记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataSelfSourceDao {
	private static Logger logger = Logger.getLogger(DataSelfSourceDao.class);
	public static DataSelfSourceDao dataSelfSourceDao = null;

	/**
	 * 实例化对象
	 * 
	 */
	public static DataSelfSourceDao getInstance() {
		if (null == dataSelfSourceDao) {
			dataSelfSourceDao = new DataSelfSourceDao();
		}
		return dataSelfSourceDao;
	}

	/**
	 * 插入个人的记录
	 * 
	 */
	public void insert(DataSelfSource dataSelfSource) {
		try {
			DBConn.getDBConn().save(dataSelfSource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllSelfSource() {
		DBConn db = DBConn.getDBConn();
		return db.executeQueryToList("from DataSelfSource");
	}

	/**
	 * 获取所有的日志记录
	 * 
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllSelfSourceRecord()
			throws Exception {
		DBConn db = DBConn.getDBConn();
		String sql = "from DataSelfSource order by id asc";
		return db.executeQueryToList(sql);

	}

	/**
	 * 删除本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteAllSelfSource() {
		DBConn db = DBConn.getDBConn();
		String hql="delete from DataSelfSource";
		try {
			db.updateInfo(hql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("delete error" + e);
		}
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpState(String uuid, int state) {
		DBConn db = DBConn.getDBConn();
		String sql = "update DataSelfSource set flag='" + state
				+ "' where uuid='" + uuid + "'";
		logger.info("updateUpState:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("updateUpState error:" + e);
		}
	}

	public int getCurrentVersion(String centerId) {
		int version = 0;
		DBConn db = DBConn.getDBConn();
		String sql = "select new Map(version as version) from DataSelfSource where uuid='"
				+ centerId + "'";
		logger.info("getCurrentVersion:" + sql);
		List<HashMap<String, String>> strList = null;
		try {
			strList = db.executeQuerySql(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("getCurrentVersion error:" + e);
		}
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
		DBConn db = DBConn.getDBConn();
		String sql = "update DataSelfSource set flag='" + state
				+ "',startdate='" + upStartTime + "',enddate='" + upEndTime
				+ "' where uuid='" + uuid + "'";
		logger.info("updateUpState:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("updateUpState error:" + e);
		}
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpEndStates(String uuid, int state, String upEndTime) {
		DBConn db = DBConn.getDBConn();
		String sql = "update DataSelfSource set flag='" + state
				+ "',enddate='" + upEndTime + "' where uuid='" + uuid + "' ";
		logger.info("updateUpEndStates:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("updateUpEndStates error:" + e);
		}
	}

	/**
	 * 修改上传状态
	 * 
	 * @return
	 */
	public void updateUpEndVesions(String uuid, int state, String upEndTime,
			int version) {
		DBConn db = DBConn.getDBConn();
		String sql = "update DataSelfSource set flag='" + state
				+ "',enddate='" + upEndTime + "',version='" + version
				+ "' where uuid='" + uuid + "' ";
		logger.info("updateUpEndStates:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("updateUpEndStates error:" + e);
		}
	}

	public void updateVersion(String uuid, int version) {
		DBConn db = DBConn.getDBConn();
		String sql = "update DataSelfSource set version='"
				+ version + "' where uuid='" + uuid + "' ";
		logger.info("updateVersion:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("updateVersion error:" + e);
		}
	}

	public void recordAllStates(String centerid, String centername,
			String nextSelfVersion, int upState) {
		DataSelfSource dataSelfSource = new DataSelfSource();
		Date d = new Date();
		logger.info(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String upStartTime = sdf.format(d);
		String upEndTime = sdf.format(d);
		dataSelfSource.setVersion(Integer.valueOf(nextSelfVersion));
		dataSelfSource.setUuid(centerid);
		dataSelfSource.setUuname(centername);
		dataSelfSource.setStartdate(upStartTime);
		dataSelfSource.setFlag(upState);
		dataSelfSource.setEnddate(upEndTime);
		DataSelfSourceDao.getInstance().insert(dataSelfSource);
	}
	
	
}
