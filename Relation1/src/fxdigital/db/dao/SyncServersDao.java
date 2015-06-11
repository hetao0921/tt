package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fxdigital.bean.SyncServerinfosync;
import com.hibernate.ConvertMapUtil;
import com.hibernate.Hibernate;

import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.util.ArgsUtil;

/**
 * 同步服务器同步表
 * @author fucz
 * @version 2014-6-30
 */
@Repository
public class SyncServersDao{
	
	@Autowired
	private Hibernate hibernate;
	
	@SuppressWarnings("unchecked")
	public SyncServer query(String serverID){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "select "+map+" from SyncServerinfosync where serverId='"+serverID+"'";
		List<?> list =hibernate.createQueryList(sql) ;
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
		String sql = "select "+map+" from SyncServerinfosync where mqIp='"+mqIP+"'";
		List<?> list = hibernate.createQueryList(sql);
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
		String sql = "select "+map+" from SyncServerinfosync";
		List<?> list = hibernate.createQueryList(sql);
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
		return hibernate.deleteOrUpdate(sql, null);
	}
	
	public int deleteServerID(String serverID){
		String sql = "delete from SyncServerinfosync where serverId='"+serverID+"'";
		return hibernate.deleteOrUpdate(sql, null);
	}
	
	public int insert(String preServerID,SyncServer syncServer){
		SyncServerinfosync ss=new SyncServerinfosync();
		ss.setServerId(syncServer.getServerID());
		ss.setServerIp(syncServer.getServerIP());
		ss.setServerName(syncServer.getServerName());
		ss.setMqIp(syncServer.getMq().getIp());
		ss.setMqPort(syncServer.getMq().getPort());
		ss.setSyncId(preServerID);
		 hibernate.save(ss);
		 return 1;
	}
	
}
