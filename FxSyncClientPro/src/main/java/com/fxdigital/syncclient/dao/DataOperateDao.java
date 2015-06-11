package com.fxdigital.syncclient.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.DataOperate;
import com.fxdigital.syncclient.util.ConvertMapUtil;

/**
 * @author het 本地操作互斥锁数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataOperateDao extends BaseDao {
	private static Logger logger = Logger.getLogger(DataOperateDao.class);

	/**
	 * 插入下载的记录
	 * 
	 */
	public void insert(DataOperate dataOperate) {
		save(dataOperate);
	}

	/**
	 * 获取上传锁信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getMsg(String operate) {
		String sql = ConvertMapUtil.map(DataOperate.class)
				+ " from DataOperate where operate = '" + operate + "'";
		int num = executeQuery(sql).size();
		if (num == 0) {
			DataOperate dataOperate = new DataOperate();
			dataOperate.setOperate(operate);
			dataOperate.setFlag(0);
			save(dataOperate);
			return executeQuery(sql).get(0);
		} else {
			return executeQuery(sql).get(0);
		}

	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLockAll() {
		String sql = "update DataOperate set flag=0";
		executeUpdate(sql);
	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public String unLockOne(String operate, String lockid) {
		String result = null;
		int intlockid = Integer.valueOf(lockid);
		String searchsql = ConvertMapUtil.map(DataOperate.class)
				+ " from DataOperate where operate='" + operate + "' and id='"
				+ intlockid + "'";
		logger.info(searchsql);
		int num = executeQuery(searchsql).size();
		if (num > 0) {

			String sql1 = "delete from DataOperate where operate='" + operate
					+ "'";
			executeUpdate(sql1);
			DataOperate dataOperate = new DataOperate();
			dataOperate.setOperate(operate);
			dataOperate.setFlag(0);

			save(dataOperate);
			result = "解锁成功";

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
		String result = null;
		String searchsql = ConvertMapUtil.map(DataOperate.class)
				+ " from DataOperate where operate='" + operate + "'";
		logger.info(searchsql);
		int num = executeQuery(searchsql).size();
		if (num > 0) {
			String sql1 = "update DataOperate set flag=0 where operate='"
					+ operate + "'";
			executeUpdate(sql1);
			result = "解锁成功";
		} else {

			DataOperate dataOperate = new DataOperate();
			dataOperate.setOperate(operate);
			dataOperate.setFlag(0);
			save(dataOperate);
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
		String sql = "update DataOperate set flag=1";
		executeUpdate(sql);

	}

	/**
	 * 给上传或者下载上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void lockOne(String operate) {
		String sql = "update DataOperate set flag=1 where operate='" + operate
				+ "'";
		logger.info(sql);
		executeUpdate(sql);
	}


}
