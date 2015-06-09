package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.bean.DataIncrementVersion;
/**
 * @author  het
 *数据版本最新记录访问层
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
@Repository
public class DataIncrementVersionDao extends BaseDao{
	
	private static final Logger logger = Logger.getLogger(DataIncrementVersionDao.class);


	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataIncrementVersion dataIncrementVersion) {

		try {
			save(dataIncrementVersion);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 删除同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void delete(String centerid) {
		String sql = "delete from DataIncrementVersion where centerid='" + centerid
				+ "'";
		try {
			executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除本地记录数据失败"+e);
		}

	}

	/**
	 * 获得本机的所有同步信息版本情况
	 * 
	 * @return
	 */
	public List<String[]> getAllSource() {
		String hql="from DataIncrementVersion";
		return executeQueryStrArray(hql);
	}

	/**
	 * 获得本机的所有同步信息版本情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getAllServerSource() {
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version) from DataIncrementVersion";
		return executeQuery(hql);
	}
	


	/**
	 * 获得某一中心同步信息版本情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getOneVersion(String centerid) {
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version) from DataIncrementVersion where centerid='"
				+ centerid + "'";
		return executeQuery(hql);
	}

	/**
	 * 获得除某一中心同步信息版本情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getNoOneSource(String centerid) throws Exception {
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version) from DataIncrementVersion where centerid<>'"
				+ centerid + "'";
		return executeQuery(hql);
	}
	


}
