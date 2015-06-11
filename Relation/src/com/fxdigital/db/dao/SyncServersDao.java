package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fxdigital.manager.SyncMq;
import com.fxdigital.manager.SyncServer;
import com.hibernate.bean.SyncServerinfosync;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;
import com.hibernate.db.Hibernate;

import fxdigital.util.ArgsUtil;

/**
 * 同步服务器同步表
 * @author fucz
 * @version 2014-6-30
 */
@Repository
public class SyncServersDao{
	

	
	@SuppressWarnings("unchecked")
	public SyncServer query(String serverID){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "from SyncServerinfosync where serverId='"+serverID+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		SyncServer syncServer = new SyncServer();
		Map<String, Object> data = (Map<String, Object>)list.get(0);
		syncServer.setServerID((String)data.get("ServerID".toLowerCase()));
		syncServer.setServerIP((String)data.get("ServerIP".toLowerCase()));
		syncServer.setServerName((String)data.get("ServerName".toLowerCase()));
		SyncMq syncMq = new SyncMq();
		syncMq.setIp((String)data.get("MqIP".toLowerCase()));
		syncMq.setPort((Integer)data.get("MqPort".toLowerCase()));
		syncMq.setPostAddress(ArgsUtil.getPostAddress());
		syncServer.setMq(syncMq);
		return syncServer;
	}
	
	@SuppressWarnings("unchecked")
	public SyncServer queryFromMqIP(String mqIP){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "from SyncServerinfosync where mqIp='"+mqIP+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		SyncServer syncServer = new SyncServer();
		Map<String, Object> data = (Map<String, Object>)list.get(0);
		syncServer.setServerID((String)data.get("ServerID".toLowerCase()));
		syncServer.setServerIP((String)data.get("ServerIP".toLowerCase()));
		syncServer.setServerName((String)data.get("ServerName".toLowerCase()));
		SyncMq syncMq = new SyncMq();
		syncMq.setIp((String)data.get("MqIP".toLowerCase()));
		syncMq.setPort((Integer)data.get("MqPort".toLowerCase()));
		syncMq.setPostAddress(ArgsUtil.getPostAddress());
		syncServer.setMq(syncMq);
		return syncServer;
	}
	
	@SuppressWarnings("unchecked")
	public List<SyncServer> query(){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "from SyncServerinfosync";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		List<SyncServer> servers = new ArrayList<SyncServer>();
		for(Object o : list){
			Map<String, Object> data = (Map<String, Object>)o;
			SyncServer syncServer = new SyncServer();
			syncServer.setServerID((String)data.get("ServerID".toLowerCase()));
			syncServer.setServerIP((String)data.get("ServerIP".toLowerCase()));
			syncServer.setServerName((String)data.get("ServerName".toLowerCase()));
			SyncMq syncMq = new SyncMq();
			syncMq.setIp((String)data.get("MqIP".toLowerCase()));
			syncMq.setPort((Integer)data.get("MqPort".toLowerCase()));
			syncMq.setPostAddress(ArgsUtil.getPostAddress());
			syncServer.setMq(syncMq);
			servers.add(syncServer);
		}
		return servers;
	}
	
	public int deleteSyncID(String syncID){
		
		String sql = "delete from SyncServerinfosync where syncId='"+syncID+"'";
		return ConnDo.getConnDo().executeUpdate(sql);
	}
	
	public int deleteServerID(String serverID){
		String sql = "delete from SyncServerinfosync where serverId='"+serverID+"'";
		return ConnDo.getConnDo().executeUpdate(sql);
	}
	
	public int insert(String preServerID,SyncServer syncServer){
		SyncServerinfosync ss=new SyncServerinfosync();
		ss.setServerId(syncServer.getServerID());
		ss.setServerIp(syncServer.getServerIP());
		ss.setServerName(syncServer.getServerName());
		ss.setMqIp(syncServer.getMq().getIp());
		ss.setMqPort(syncServer.getMq().getPort());
		ss.setSyncId(preServerID);
		ConnDo.getConnDo().save(ss);
		 return 1;
	}
	
}
