package fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncSupapply;
import com.hibernate.db.ConnDo;

import fxdigital.db.SupStatus;
import fxdigital.util.ArgsUtil;

/**
 * 下级申请上级表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class SupApplyDao{
	

	
	@SuppressWarnings("unchecked")
	public List<SupStatus> query(){
		String map=ConvertMapUtil.map(SyncSupapply.class);
		String sql = "select "+map+" from SyncSupapply";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		List<SupStatus> supstatuses = new ArrayList<SupStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SupStatus supStatus = new SupStatus();
				supStatus.setServerID((String)data.get("ServerID"));
				supStatus.setServerIP((String)data.get("ServerIP"));
				supStatus.setServerName((String)data.get("ServerName"));
				supStatus.setMqIP((String)data.get("MqIP"));
				supStatus.setMqPort(Integer.valueOf((String)data.get("MqPort")));
				supStatus.setStatus(Integer.valueOf((String)data.get("Status")));
				supStatus.setApplyTime(Timestamp.valueOf((String)data.get("ApplyTime")));
				supStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				supstatuses.add(supStatus);
			}
			return supstatuses;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SupStatus> query(String ip){
		String map=ConvertMapUtil.map(SyncSupapply.class);
		String sql = "select "+map+" from SyncSupapply where mqIp='"+ip+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		List<SupStatus> supstatuses = new ArrayList<SupStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
			
				
				SupStatus supStatus = new SupStatus();
				supStatus.setServerID((String)data.get("ServerID"));
				supStatus.setServerIP((String)data.get("ServerIP"));
				supStatus.setServerName((String)data.get("ServerName"));
				supStatus.setMqIP((String)data.get("MqIP"));
				supStatus.setMqPort(Integer.valueOf((String)data.get("MqPort")));
				supStatus.setStatus(Integer.valueOf((String)data.get("Status")));
				supStatus.setApplyTime(Timestamp.valueOf((String)data.get("ApplyTime")));
				supStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				supstatuses.add(supStatus);
			}
			return supstatuses;
		}else{
			return null;
		}
	}
	
	public int update(String ip,Timestamp time,int status){
		String sql = "update SyncSupapply set status=? where mqIp=? and applyTime=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{status,ip,time});
	}
	
	public int update(SupStatus supStatus,Timestamp time){
		String sql = "update SyncSupapply set serverId=?,serverIp=?,serverName=? where mqIp=? and applyTime=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{
				supStatus.getServerID(),
				supStatus.getServerIP(),
				supStatus.getServerName(),
				supStatus.getMqIP(),
				time
		});
	}
	
	public int insert(SupStatus supStatus){
		SyncSupapply su=new SyncSupapply();
		su.setServerId(supStatus.getServerID());
		su.setServerIp(supStatus.getServerIP());
		su.setServerName(supStatus.getServerName());
		su.setMqIp(supStatus.getMqIP());
		su.setMqPort(supStatus.getMqPort());
		su.setStatus(supStatus.getStatus());
		su.setApplyTime(supStatus.getApplyTime());
		ConnDo.getConnDo().save(su);
		return 1;
		
	}
	
}
