package com.fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fxdigital.manager.SubStatus;
import com.hibernate.bean.SyncSubapply;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;
import com.hibernate.db.Hibernate;

import fxdigital.util.ArgsUtil;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-10
 */
@Repository
public class SubApplyDao{
	

	@Autowired
	private ApplyStatusDao applyStatusDao;
	
	@SuppressWarnings("unchecked")
	public List<SubStatus> query(){
		String map=ConvertMapUtil.map(SyncSubapply.class);
		String sql = "from SyncSubapply";
		List<Map<String,Object>> list =ConnDo.getConnDo().executeQueryToObjectList(sql) ;
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID".toLowerCase()));
				subStatus.setServerIP((String)data.get("ServerIP".toLowerCase()));
				subStatus.setServerName((String)data.get("ServerName".toLowerCase()));
				subStatus.setMqIP((String)data.get("MqIP".toLowerCase()));
				subStatus.setMqPort(Integer.parseInt(null==data.get("MqPort".toLowerCase())?"0":data.get("MqPort".toLowerCase()).toString()));				
				String status = applyStatusDao.query(Integer.parseInt(null==data.get("Status".toLowerCase())?"0":data.get("Status".toLowerCase()).toString()));
				subStatus.setStatus(status);
				subStatus.setApplyTime((Timestamp)data.get("ApplyTime".toLowerCase()));
				subStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				substatuses.add(subStatus);
			}
			return substatuses;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SubStatus> query(String id){
		String map=ConvertMapUtil.map(SyncSubapply.class);
		String sql = "from SyncSubapply where serverId='"+id+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID".toLowerCase()));
				subStatus.setServerIP((String)data.get("ServerIP".toLowerCase()));
				subStatus.setServerName((String)data.get("ServerName".toLowerCase()));
				subStatus.setMqIP((String)data.get("MqIP".toLowerCase()));
				subStatus.setMqPort(Integer.parseInt(null==data.get("MqPort".toLowerCase())?"0":data.get("MqPort".toLowerCase()).toString()));
				String status = applyStatusDao.query(Integer.parseInt(null==data.get("Status".toLowerCase())?"0":data.get("Status".toLowerCase()).toString()));
				subStatus.setStatus(status);
				subStatus.setApplyTime((Timestamp)data.get("ApplyTime".toLowerCase()));
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
		String sql = "from SyncSubapply where serverIp='"+ip+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		List<SubStatus> substatuses = new ArrayList<SubStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SubStatus subStatus = new SubStatus();
				subStatus.setServerID((String)data.get("ServerID".toLowerCase()));
				subStatus.setServerIP((String)data.get("ServerIP".toLowerCase()));
				subStatus.setServerName((String)data.get("ServerName".toLowerCase()));
				subStatus.setMqIP((String)data.get("MqIP".toLowerCase()));
				subStatus.setMqPort(Integer.parseInt(null==data.get("MqPort".toLowerCase())?"0":data.get("MqPort".toLowerCase()).toString()));
				String status = applyStatusDao.query(Integer.parseInt(null==data.get("Status".toLowerCase())?"0":data.get("Status".toLowerCase()).toString()));
				subStatus.setStatus(status);
				subStatus.setApplyTime((Timestamp)data.get("ApplyTime".toLowerCase()));
				subStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				substatuses.add(subStatus);
			}
			return substatuses;
		}else{
			return null;
		}
	}
	
	public int update(String id,Timestamp time,String status){
		int int_status = applyStatusDao.query(status);
		
		String sql = "update SyncSubapply set status='"+int_status+"' where serverId='"+id+"' and applyTime='"+time+"'";
		return ConnDo.getConnDo().executeUpdate(sql);
		 
	}
	
}
