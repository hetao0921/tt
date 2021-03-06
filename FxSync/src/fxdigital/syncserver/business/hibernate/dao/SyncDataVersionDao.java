package fxdigital.syncserver.business.hibernate.dao;

import org.apache.log4j.Logger;

import fxdigital.syncserver.business.hibernate.bean.SyncDataVersion;
/**
 * @author  het
 *所有数据版本记录访问层
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
public class SyncDataVersionDao {
	private static final Logger logger = Logger.getLogger(SyncDataVersionDao.class);
	public static SyncDataVersionDao syncDataVersionDao = null;

	public static SyncDataVersionDao getInstance() {
		if (null == syncDataVersionDao) {
			syncDataVersionDao = new SyncDataVersionDao();
		}
		return syncDataVersionDao;
	}
	
	/**
	 * 插入同步的资源的版本记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(SyncDataVersion syncDataVersion) {

		try {
			NVMPHibernate.getNVMPhibernate().save(syncDataVersion);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

}
