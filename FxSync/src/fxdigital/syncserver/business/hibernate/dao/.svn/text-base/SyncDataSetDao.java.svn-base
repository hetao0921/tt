package fxdigital.syncserver.business.hibernate.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.syncserver.business.hibernate.bean.SyncDataSet;
/**
 * @author  het
 *同步数据时间设置访问层
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
public class SyncDataSetDao {
	private static final Logger logger = Logger.getLogger(SyncDataSetDao.class);
	public static SyncDataSetDao syncDataSetDao = null;

	public static SyncDataSetDao getInstance() {
		if (null == syncDataSetDao) {
			syncDataSetDao = new SyncDataSetDao();
		}
		return syncDataSetDao;
	}

	/**
	 * 插入同步的信息记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(SyncDataSet syncDataSet) {

		try {
			NVMPHibernate.getNVMPhibernate().save(syncDataSet);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
	
	
	
	/**
	 * 获得同步服务器之间的同步时间信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllServerSet() {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String hql="select new Map(centerid as centerid,centername as centername,autotime as autotime,setdate as setdate) from SyncDataSet";
		return db.executeQuery(hql);
	}
	
	
	
	/**
	 * 删除同步同步时间信息
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void delete(String centerid) {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String sql = "delete from SyncDataSet";
		try {
			db.updateNVMPInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除本地记录数据失败"+e);
		}

	}
	
	
	
}
