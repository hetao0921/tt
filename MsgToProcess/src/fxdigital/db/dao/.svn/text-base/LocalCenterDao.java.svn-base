package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import fxdigital.db.DBConn;
import fxdigital.db.LocalCenter;
import fxdigital.util.ArgsUtil;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-10
 */

public class LocalCenterDao {
	private static Logger logger = Logger.getLogger(LocalCenterDao.class);

	public LocalCenter query() {
		String sql = "from NvmpCenterinfotab";
		List<Map<String, Object>> centerinfolist = new ArrayList<Map<String, Object>>();
		centerinfolist = DBConn.getDBConn().executeQueryToObjectList(sql);
		logger.info(centerinfolist.size() + "SQL: " + sql);
		if (centerinfolist == null || centerinfolist.size() != 1) {
			return null;
		}
		LocalCenter centerInfo = new LocalCenter();
		Map<String, Object> data = centerinfolist.get(0);
		centerInfo.setId(null==data.get("CenterID")?null:data.get("CenterID").toString());
		centerInfo.setIp(null==data.get("CenterIP")?null:data.get("CenterIP").toString());
		centerInfo.setName(null==data.get("CenterName")?null:data.get("CenterName").toString());

		centerInfo
				.setPort(Integer.valueOf(null==data.get("CenterPort")?null:data.get("CenterPort").toString()));
		centerInfo
				.setSyncServerIP(null==data.get("SyncServerIP")?null:data.get("SyncServerIP").toString());
		centerInfo.setSyncServerPort(Integer.valueOf(null==data.get("SyncServerPort")?null:data.get("SyncServerPort").toString()));
		centerInfo.setSyncServerPostAddress(ArgsUtil.getPostAddress());
		return centerInfo;

	}

	public static void main(String[] args) {
		LocalCenterDao localCenterDao = new LocalCenterDao();
		LocalCenter centerInfo = new LocalCenter();
		centerInfo = localCenterDao.query();
		logger.info(centerInfo.getId());
	}
}
