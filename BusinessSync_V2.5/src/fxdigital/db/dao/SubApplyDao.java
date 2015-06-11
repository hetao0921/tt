package fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncSubapply;
import com.hibernate.db.ConnDo;

import fxdigital.db.SubStatus;
import fxdigital.util.ArgsUtil;

/**
 * 上级接收到的下级申请表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class SubApplyDao{
	

	
	@SuppressWarnings("unchecked")
	public List<SubStatus> query(){
		String map=ConvertMapUtil.map(SyncSubapply.class);
		String sql = "select "+map+" from SyncSubapply";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID"));
				subStatus.setServerIP((String)data.get("ServerIP"));
				subStatus.setServerName((String)data.get("ServerName"));
				subStatus.setMqIP((String)data.get("MqIP"));
				subStatus.setMqPort(Integer.parseInt(null==data.get("MqPort")?"0":data.get("MqPort").toString()));
				subStatus.setStatus(Integer.parseInt(null==data.get("Status")?"0":data.get("Status").toString()));
				subStatus.setApplyTime(Timestamp.valueOf((String)data.get("ApplyTime")));
				subStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				substatuses.add(subStatus);
			}
			return substatuses;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SubStatus> queryFromID(String id){
		String map=ConvertMapUtil.map(SyncSubapply.class);
		String sql = "select "+map+" from SyncSubapply where serverId='"+id+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID"));
				subStatus.setServerIP((String)data.get("ServerIP"));
				subStatus.setServerName((String)data.get("ServerName"));
				subStatus.setMqIP((String)data.get("MqIP"));
				subStatus.setMqPort(Integer.parseInt(null==data.get("MqPort")?"0":data.get("MqPort").toString()));
				subStatus.setStatus(Integer.parseInt(null==data.get("Status")?"0":data.get("Status").toString()));
				subStatus.setApplyTime(Timestamp.valueOf((String)data.get("ApplyTime")));
				subStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				substatuses.add(subStatus);
			}
			return substatuses;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SubStatus> queryFromIP(String ip){
		String map=ConvertMapUtil.map(SyncSubapply.class);
		String sql = "select "+map+" from SyncSubapply where serverIp='"+ip+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				
				
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID"));
				subStatus.setServerIP((String)data.get("ServerIP"));
				subStatus.setServerName((String)data.get("ServerName"));
				subStatus.setMqIP((String)data.get("MqIP"));
				subStatus.setMqPort(Integer.parseInt(null==data.get("Status")?"0":data.get("Status").toString()));
				subStatus.setStatus(Integer.parseInt(null==data.get("Status")?"0":data.get("Status").toString()));
				subStatus.setApplyTime(Timestamp.valueOf((String)data.get("ApplyTime")));
				subStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				substatuses.add(subStatus);
			}
			return substatuses;
		}else{
			return null;
		}
	}
	
	public int update(String id,Timestamp time,int status){
		String sql = "update SyncSubapply set status=? where serverId=? and applyTime=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{status,id,time});
	}
	
	public int insert(SubStatus subStatus){
		SyncSubapply ss=new SyncSubapply();
		ss.setServerId(subStatus.getServerID());
		ss.setServerIp(subStatus.getServerIP());
		ss.setServerName(subStatus.getServerName());
		ss.setMqIp(subStatus.getMqIP());
		ss.setMqPort(subStatus.getMqPort());
		ss.setStatus(subStatus.getStatus());
		ss.setApplyTime(subStatus.getApplyTime());
		
		
		ConnDo.getConnDo().save(ss);
		return 1;
	}
	
}
