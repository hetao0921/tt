package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncServerinfosync;
import com.hibernate.db.ConnDo;

import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.util.ArgsUtil;

/**
 * 同步服务器同步表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class SyncServersDao{

	
	@SuppressWarnings("unchecked")
	public SyncServer query(String serverID){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "select "+map+" from SyncServerinfosync where serverId='"+serverID+"'";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		SyncServer syncServer = new SyncServer();
		Map<String, Object> data = (Map<String, Object>)list.get(0);
		
		System.err.println("list  "+list);
		
		syncServer.setServerID((String)data.get("serverid"));
		syncServer.setServerIP((String)data.get("serverip"));
		syncServer.setServerName((String)data.get("servername"));
		SyncMq syncMq = new SyncMq();
		syncMq.setIp((String)data.get("mqip"));
		syncMq.setPort(Integer.parseInt(null==data.get("mqport")?"0":data.get("mqport").toString()));
		syncMq.setPostAddress(ArgsUtil.getPostAddress());
		syncServer.setMq(syncMq);
		return syncServer;
	}
	
	@SuppressWarnings("unchecked")
	public List<SyncServer> query(){
		String map=ConvertMapUtil.map(SyncServerinfosync.class);
		String sql = "select "+map+" from SyncServerinfosync";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		List<SyncServer> servers = new ArrayList<SyncServer>();
		for(Object o : list){
			Map<String, Object> data = (Map<String, Object>)o;
			
			System.out.println("data  "+data);
			
			SyncServer syncServer = new SyncServer();
			syncServer.setServerID((String)data.get("serverid"));
			syncServer.setServerIP((String)data.get("serverip"));
			syncServer.setServerName((String)data.get("servername"));
			SyncMq syncMq = new SyncMq();
			syncMq.setIp((String)data.get("mqip"));
			syncMq.setPort(Integer.parseInt(null==data.get("mqport")?"0":data.get("mqport").toString()));
			syncMq.setPostAddress(ArgsUtil.getPostAddress());
			syncServer.setMq(syncMq);
			servers.add(syncServer);
		}
		return servers;
	}
	
	public int deleteSyncID(String syncID){
		String sql = "delete from SyncServerinfosync where syncId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{syncID});
	}
	
	public int deleteServerID(String serverID){
		String sql = "delete from SyncServerinfosync where serverId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{serverID});
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
