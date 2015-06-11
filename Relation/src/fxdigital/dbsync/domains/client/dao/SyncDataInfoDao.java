package fxdigital.dbsync.domains.client.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.dbsync.domains.client.db.DBConn;

/**
 * @author het 获取本地中心信息，连接MQ的信息的数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
public class SyncDataInfoDao {
	private static Logger logger = Logger.getLogger(DataSelfSourceDao.class);
	public static SyncDataInfoDao syncDataInfoDao = null;

	/**
	 * 实例化对象
	 * 
	 */
	public static SyncDataInfoDao getInstance() {
		if (null == syncDataInfoDao) {
			syncDataInfoDao = new SyncDataInfoDao();
		}
		return syncDataInfoDao;
	}

	public List<HashMap<String, String>> queryCenterID() {
		String sql = "select new Map(centerId as centerId,centerName as centerName,centerDesc as centerDesc,centerIp as centerIp,centerMask as centerMask,centerGate as centerGate,centerPort as centerPort,loginUserName as loginUserName,loginPwd as loginPwd,dataBaseIp as dataBaseIp,dataBaseUser as dataBaseUser,dataBasePwd as dataBasePwd,parentCenterIp as parentCenterIp,parentCenterPort as parentCenterPort,domainName as domainName,dnsip as dnsip,centerVar as centerVar,linkMode as linkMode,syncServerIp as syncServerIp,syncServerPort as syncServerPort,routeMode as routeMode) from NvmpCenterinfotab";
		List<HashMap<String, String>> list = DBConn.getDBConn()
				.executeQueryNVMPSql(sql);
		return list;
	}

	public List<HashMap<String, String>> querySyncInfo() {
		String sql = "select new Map(mqIp as mqIp,mqPort as mqPort) from CenterMqserverinfo";
		List<HashMap<String, String>> list = DBConn.getDBConn()
				.executeQueryNVMPSql(sql);
		return list;
	}

}
