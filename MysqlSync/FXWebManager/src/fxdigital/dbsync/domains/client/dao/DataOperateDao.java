package fxdigital.dbsync.domains.client.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hibernate.bean.DataOperate;

import fxdigital.dbsync.domains.client.db.DBConn;



/**
 * @author  het
 * 本地操作互斥锁数据访问层
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataOperateDao {
	private static Logger logger = Logger.getLogger(DataOperateDao.class);
	public static DataOperateDao dataOperateDao = null;

	/**
	 * 实例化对象
	 * 
	 */
	public static DataOperateDao getInstance() {
		if (null == dataOperateDao) {
			dataOperateDao = new DataOperateDao();
		}
		return dataOperateDao;
	}

	/**
	 * 插入下载的记录
	 * 
	 */
	public void insert(DataOperate dataOperate) {
		try {
			DBConn.getDBConn().save(dataOperate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 获取上传锁信息
	 * 
	 * @return
	 */
	public String[] getMsg(String operate) {

		DBConn db = DBConn.getDBConn();
		String sql = "from DataOperate where operate = '" + operate
				+ "'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			DataOperate dataOperate=new DataOperate();
			dataOperate.setOperate(operate);
			dataOperate.setFlag(0);
			try {
				db.save(dataOperate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return db.executeQuery(sql).get(0);
		} else {
			return db.executeQuery(sql).get(0);
		}

	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLockAll() {

		DBConn db = DBConn.getDBConn();
		String sql = "update DataOperate set flag=0";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("解锁失败");
		}

	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public String unLockOne(String operate, String lockid) {
		DBConn db = DBConn.getDBConn();
		String result = null;
		int intlockid = Integer.valueOf(lockid);
		String searchsql = "from DataOperate where operate='" + operate
				+ "' and id='" + intlockid + "'";
		logger.info(searchsql);
		int num = db.executeQuery(searchsql).size();
		if (num > 0) {
			try {
				String sql1="delete from DataOperate where operate='" + operate
				+ "'";
				db.updateInfo(sql1);
				DataOperate dataOperate=new DataOperate();
				dataOperate.setOperate(operate);
				dataOperate.setFlag(0);
				
				db.save(dataOperate);
				result = "解锁成功";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "解锁失败";
				logger.info("解锁失败");
			}
		} else {
			result = "此锁已经被解除!";
		}
		return result;
	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public String unLockOne(String operate) {
		DBConn db = DBConn.getDBConn();
		String result = null;
		String searchsql = "from DataOperate where operate='" + operate
				+ "'";
		logger.info(searchsql);
		int num = db.executeQuery(searchsql).size();
		if (num > 0) {
			try {
				String sql1="update DataOperate set flag=0 where operate='" + operate
				+ "'";
				db.updateInfo(sql1);
				result = "解锁成功";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "解锁失败";
				logger.info("解锁失败");
			}
		} else {
			try {
				DataOperate dataOperate=new DataOperate();
				dataOperate.setOperate(operate);
				dataOperate.setFlag(0);
				
				db.save(dataOperate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "解锁失败";
				logger.info("解锁失败");
			}
		}
		return result;
	}
	
	/**
	 * 给上传下载上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void lockAll() {

		DBConn db = DBConn.getDBConn();
		String sql = "update DataOperate set flag=1";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("解锁失败");
		}

	}

	/**
	 * 给上传或者下载上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void lockOne(String operate) {
		DBConn db = DBConn.getDBConn();
		String sql = "update DataOperate set flag=1 where operate='" + operate
				+ "'";
		logger.info(sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("加锁失败");
		}
	}

	public static void main(String[] args) {
		DataOperateDao.getInstance().lockOne("上传");
		// DataOperateDao.getInstance().lockAll();
	}

}
