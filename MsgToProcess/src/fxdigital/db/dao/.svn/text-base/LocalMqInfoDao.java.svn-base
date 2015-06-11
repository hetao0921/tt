package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hibernate.bean.SyncLocalmqinfo;

import fxdigital.db.DBConn;
import fxdigital.db.LocalMqInfo;

public class LocalMqInfoDao {

	private static Logger logger = Logger.getLogger(LocalMqInfoDao.class);
	public List<LocalMqInfo> query() {
		String sql = "from SyncLocalmqinfo";
		List<Map<String, Object>> localmqinfolist = DBConn.getDBConn()
				.executeQueryToObjectList(sql);
		
		List<LocalMqInfo> localMqList = null;
		if (localmqinfolist == null) {
			return null;
		} else {
			localMqList = new ArrayList<LocalMqInfo>();
			LocalMqInfo localMqInfo = null;
			for (int i = 0; i < localmqinfolist.size(); i++) {
				localMqInfo = new LocalMqInfo();
				Map<String, Object> data = localmqinfolist.get(i);
				localMqInfo.setCenterid(null==data.get("centerid")?null:data.get("centerid").toString());
				localMqInfo.setIp(null==data.get("ip")?null:data.get("ip").toString());
				localMqInfo.setPort(Integer.valueOf(null==data.get("port")?null:data.get("port").toString()));
				localMqList.add(localMqInfo);		
			}
		}
		return localMqList;
	}

	
	public void insert(String centerid, String ip, int port) {
		DBConn db = DBConn.getDBConn();
		SyncLocalmqinfo syncLocalmqinfo=new SyncLocalmqinfo();
		syncLocalmqinfo.setCenterid(centerid);
		syncLocalmqinfo.setIp(ip);
		syncLocalmqinfo.setPort(port);
		try {
			db.savenvmp(syncLocalmqinfo);
//			logger.info("添加数据SQL: "+sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("添加操作数据失败");
		}
	}


	public String getMQServerIP(String centerID) {
		String sql = "from SyncLocalmqinfo where centerid='"+centerID+"'";
		List<Map<String, Object>> localmqinfolist = DBConn.getDBConn()
		.executeQueryToObjectList(sql);
		String mqServerIP = null;
		if (null!=localmqinfolist  && localmqinfolist.size() == 1) {
			HashMap<String, Object> data = (HashMap<String, Object>) localmqinfolist.get(0);
			mqServerIP = null==data.get("ip")?null:data.get("ip").toString();
		}
		return mqServerIP;
	}

	public boolean isExist(String centerID) {
		String sql = "from SyncLocalmqinfo where centerid='"+centerID+"'";
		List<Map<String, Object>> localmqinfolist = DBConn.getDBConn()
		.executeQueryToObjectList(sql);
		if (localmqinfolist == null || localmqinfolist.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public static void main(String[] args) {
		LocalMqInfoDao localMqInfoDao=new LocalMqInfoDao();
		logger.info(localMqInfoDao.isExist("center@001"));
		logger.info(localMqInfoDao.getMQServerIP("center@001"));
		localMqInfoDao.insert("center1@001", "192.168.1.55", 16161);
		logger.info(localMqInfoDao.query());
	}
}
