package com.fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fxdigital.manager.SupStatus;
import com.hibernate.bean.SyncSupapply;
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
public class SupApplyDao{
	

	@Autowired
	private ApplyStatusDao applyStatusDao;
	
	@SuppressWarnings("unchecked")
	public List<SupStatus> query(){
		String map=ConvertMapUtil.map(SyncSupapply.class);
		String sql = "from SyncSupapply";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		List<SupStatus> supstatuses = new ArrayList<SupStatus>();
		System.err.println("list "+list);
		if(list != null && list.size() > 0){
			for(Map<String,Object> data : list){
				SupStatus supStatus = new SupStatus();
				supStatus.setServerID((String)data.get("ServerID".toLowerCase()));
				supStatus.setServerIP((String)data.get("ServerIP".toLowerCase()));
				supStatus.setServerName((String)data.get("ServerName".toLowerCase()));
				supStatus.setMqIP((String)data.get("MqIP".toLowerCase()));
				supStatus.setMqPort((Integer)data.get("MqPort".toLowerCase()));
				String status = applyStatusDao.query((Integer.valueOf((null==data.get("Status".toLowerCase())?"0":data.get("Status".toLowerCase())).toString())));
				supStatus.setStatus(status);
				supStatus.setApplyTime((Timestamp)data.get("ApplyTime".toLowerCase()));
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
		String sql = "from SyncSupapply where mqIp='"+ip+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		List<SupStatus> supstatuses = new ArrayList<SupStatus>();
		if(list != null && list.size() > 0){
			for(Object temp : list){
				Map<String, Object> data = (Map<String, Object>) temp;
				SupStatus supStatus = new SupStatus();
				supStatus.setServerID((String)data.get("ServerID".toLowerCase()));
				supStatus.setServerIP((String)data.get("ServerIP".toLowerCase()));
				supStatus.setServerName((String)data.get("ServerName".toLowerCase()));
				supStatus.setMqIP((String)data.get("MqIP".toLowerCase()));
				supStatus.setMqPort(Integer.valueOf((null==data.get("MqPort".toLowerCase())?"0":data.get("MqPort".toLowerCase())).toString()));
				String status = applyStatusDao.query(Integer.valueOf((null==data.get("Status".toLowerCase())?"0":data.get("Status".toLowerCase())).toString()));
				supStatus.setStatus(status);
				supStatus.setApplyTime(Timestamp.valueOf((null==data.get("ApplyTime".toLowerCase())?"0":data.get("ApplyTime".toLowerCase())).toString()));
				supStatus.setMqPostAddress(ArgsUtil.getPostAddress());
				supstatuses.add(supStatus);
			}
			return supstatuses;
		}else{
			return null;
		}
	}
	
	public int insert(SupStatus supStatus){
		Integer status = applyStatusDao.query(supStatus.getStatus());
		SyncSupapply ss=new SyncSupapply();
		ss.setMqIp(supStatus.getMqIP());
		ss.setMqPort(supStatus.getMqPort());
		ss.setStatus(status);
		ss.setApplyTime(supStatus.getApplyTime());
		ConnDo.getConnDo().save(ss);
		return 1;
	}
	
	public int update(String ip,Timestamp time,String status){
		
		Integer int_status = applyStatusDao.query(status);
		String sql = "update SyncSupapply set status="+int_status+" where mqIp='"+ip+"' and applyTime='"+time+"'";
		ConnDo.getConnDo().executeUpdate(sql);
		return 1;
	}
	
}
