package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hibernate.bean.SyncServerinfosync;

import fxdigital.db.DBConn;
import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.util.ArgsUtil;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-10
 */

public class SyncServersDao{
	
	private static Logger logger = Logger.getLogger(SyncServersDao.class);
	
	public SyncServer query(String serverID){
		String sql = "from SyncServerinfosync where serverId='"+serverID+"'";
		List<Map<String, Object>> list = DBConn.getDBConn()
		.executeQueryToObjectList(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		SyncServer syncServer = new SyncServer();
		Map<String, Object> data =list.get(0);
		syncServer.setServerID(null==data.get("ServerID")?null:data.get("ServerID").toString());
		syncServer.setServerIP(null==data.get("ServerIP")?null:data.get("ServerIP").toString());
		syncServer.setServerName(null==data.get("ServerName")?null:data.get("ServerName").toString());
		SyncMq syncMq = new SyncMq();
		syncMq.setIp(null==data.get("MqIP")?null:data.get("MqIP").toString());
		syncMq.setPort(Integer.valueOf(null==data.get("MqPort")?null:data.get("MqPort").toString()));
		syncMq.setPostAddress(ArgsUtil.getPostAddress());
		syncServer.setMq(syncMq);
		return syncServer;
	}
	
	@SuppressWarnings("unchecked")
	public List<SyncServer> query(){
		String sql = "from SyncServerinfosync";
		List<Map<String, Object>> list = DBConn.getDBConn()
		.executeQueryToObjectList(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		List<SyncServer> servers = new ArrayList<SyncServer>();
		for(Object o : list){
			Map<String, Object> data = (Map<String, Object>)o;
			SyncServer syncServer = new SyncServer();
			syncServer.setServerID(null==data.get("ServerID")?null:data.get("ServerID").toString());
			syncServer.setServerIP(null==data.get("ServerIP")?null:data.get("ServerIP").toString());
			syncServer.setServerName(null==data.get("ServerName")?null:data.get("ServerName").toString());
			SyncMq syncMq = new SyncMq();
			syncMq.setIp(null==data.get("MqIP")?null:data.get("MqIP").toString());
			syncMq.setPort(Integer.valueOf(null==data.get("MqPort")?null:data.get("MqPort").toString()));
			syncMq.setPostAddress(ArgsUtil.getPostAddress());
			syncServer.setMq(syncMq);
			servers.add(syncServer);
		}
		return servers;
	}
	
	public void deleteSyncID(String syncID){
		DBConn db = DBConn.getDBConn();
		String sql = "delete from SyncServerinfosync where syncId='"+syncID+"'";
		try {
			db.updateNVMPInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除操作数据失败");
		}
	}
	
	public void deleteServerID(String serverID){
		DBConn db = DBConn.getDBConn();
		String sql = "delete from SyncServerinfosync where serverId='"+serverID+"'";
		try {
			db.updateNVMPInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除操作数据失败");
		}
	}
	
	public void insert(String preServerID,SyncServer syncServer){
		DBConn db = DBConn.getDBConn();
		SyncServerinfosync syncServerinfosync=new SyncServerinfosync();
		syncServerinfosync.setServerId(syncServer.getServerID());
		syncServerinfosync.setServerIp(syncServer.getServerIP());
		syncServerinfosync.setServerName(syncServer.getServerName());
		syncServerinfosync.setMqIp(syncServer.getMq().getIp());
		syncServerinfosync.setMqPort(syncServer.getMq().getPort());
		syncServerinfosync.setSyncId(preServerID);
		
		
		try {
			db.savenvmp(syncServerinfosync);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("添加操作数据失败");
		}
	}
	
	public static void main(String[] args) {
		SyncServersDao syncServersDao=new SyncServersDao();
		logger.info(syncServersDao.query());
	}
	
}
