package com.fxdigital.filesync.dao;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.db.DBConn;

public class UtilDao {
	
	private static final Logger logger = Logger.getLogger(UtilDao.class);

	/**
	 * 实例化对象
	 * 
	 */
	public static UtilDao utilDao = null;

	public static UtilDao getInstance() {
		if (null == utilDao) {
			utilDao = new UtilDao();
		}
		return utilDao;
	}
	
	
	/**
	 * 判断是否是主服务器
	 * 
	 */
	public int isMainServer(){
		DBConn db = DBConn.getDBConn();
		String sql="select flag from backup_infotab,nvmp_centerinfotab where backup_infotab.CenterIP=nvmp_centerinfotab.CenterIP";
		int tag=db.executeFlagQuery(sql);
		return tag;
	}

	
	
	public static void main(String[] args) {
		logger.info(UtilDao.getInstance().isMainServer());
	}
}
