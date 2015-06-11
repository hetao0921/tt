package com.fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxdigital.manager.RegisterCenter;
import com.hibernate.bean.SyncLocalregistercenterinfos;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;


/**
 * 本级注册的中心表
 * @author fucz
 * @version 2014-6-30
 */
@Repository
public class LocalRegisterCentersDao{
	

	
	public boolean isExist(String centerID){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "select "+map+" from SyncLocalregistercenterinfos where centerId='"+centerID+"'";
		
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		if(list == null || list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public RegisterCenter query(String centerID){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "from SyncLocalregistercenterinfos where centerId='"+centerID+"'";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			RegisterCenter ci = new RegisterCenter();
			Map<String, Object> data = (Map<String, Object>)list.get(0);
			ci.setCenterID((String)data.get("CenterID".toLowerCase()));
			ci.setCenterIP((String)data.get("CenterIP".toLowerCase()));
			ci.setChannelName((String)data.get("ChannelName".toLowerCase()));
			ci.setRegisterTime((Timestamp)data.get("RegisterTime".toLowerCase()));
			return ci;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RegisterCenter> query(){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "from SyncLocalregistercenterinfos";
		List<Map<String,Object>> list = ConnDo.getConnDo().executeQueryToObjectList(sql);
		List<RegisterCenter> rcis = new ArrayList<RegisterCenter>();
		if(list == null || list.size() == 0){
			return null;
		}else{
			for(Object o : list){
				Map<String, Object> data = (Map<String, Object>) o;
				RegisterCenter ci = new RegisterCenter();
				ci.setCenterID((String)data.get("CenterID".toLowerCase()));
				ci.setCenterIP((String)data.get("CenterIP".toLowerCase()));
				ci.setChannelName((String)data.get("ChannelName".toLowerCase()));
				ci.setRegisterTime((Timestamp)data.get("RegisterTime".toLowerCase()));
				rcis.add(ci);
			}
			
		}
		return rcis;
	}
	
	public int update(RegisterCenter rci){
		String sql = "update SyncLocalregistercenterinfos set centerIp='"+rci.getCenterIP()+"',channelName='"+rci.getChannelName()+"',registerTime='"+rci.getRegisterTime()+"' where centerId='"+rci.getCenterID()+"'";
		return ConnDo.getConnDo().executeUpdate(sql);
	}
	
	public int insert(RegisterCenter rci){
		SyncLocalregistercenterinfos ss=new SyncLocalregistercenterinfos();
		ss.setCenterId(rci.getCenterID());
		ss.setCenterIp(rci.getCenterIP());
		ss.setChannelName(rci.getChannelName());
		ss.setRegisterTime(rci.getRegisterTime());
		ConnDo.getConnDo().save(ss);
		return 1;
		
	}
	
	public int delete(String centerID){
		String sql = "delete from SyncLocalregistercenterinfos where centerId="+centerID;
		return ConnDo.getConnDo().executeUpdate(sql);
	}
	
}
