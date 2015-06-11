package fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncLocalregistercenterinfos;
import com.hibernate.db.ConnDo;

import fxdigital.db.RegisterCenter;

/**
 * 本级注册的中心表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class LocalRegisterCentersDao{

	
	public boolean isExist(String centerID){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "select "+map+" from SyncLocalregistercenterinfos where centerId='"+centerID+"'";
		List<HashMap<String, String>> list  =ConnDo.getConnDo().executeQuery(sql);
		if(list == null || list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public RegisterCenter query(String centerID){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "select "+map+" from SyncLocalregistercenterinfos where centerId='"+centerID+"'";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			RegisterCenter ci = new RegisterCenter();
			Map<String, Object> data = (Map<String, Object>)list.get(0);
			System.err.println("SyncLocalregistercenterinfos "+data);
			ci.setCenterID((String)data.get("centerid"));
			ci.setCenterIP((String)data.get("centerip"));
			ci.setChannelName((String)data.get("channelname"));
			ci.setRegisterTime(Timestamp.valueOf(data.get("registertime").toString()));
			return ci;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RegisterCenter> query(){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "select "+map+" from SyncLocalregistercenterinfos";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		List<RegisterCenter> rcis = new ArrayList<RegisterCenter>();
		if(list == null || list.size() == 0){
			return null;
		}else{
			for(Object o : list){
				Map<String, Object> data = (Map<String, Object>) o;
				System.err.println("SyncLocalregistercenterinfos1 "+data);
				RegisterCenter ci = new RegisterCenter();
				ci.setCenterID((String)data.get("centerid"));
				ci.setCenterIP((String)data.get("centerip"));
				ci.setChannelName((String)data.get("channelname"));
				ci.setRegisterTime((Timestamp)data.get("registertime"));
				rcis.add(ci);
			}
			
		}
		return rcis;
	}
	
	public int update(RegisterCenter rci){
		String sql = "update SyncLocalregistercenterinfos set centerIp='"+rci.getCenterIP()+"',channelName='"+rci.getChannelName()+"',registerTime='"+rci.getRegisterTime()+"' where centerId='"+rci.getCenterID()+"'";
		return ConnDo.getConnDo().deleteOrUpdate(sql, null);
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
		String sql = "delete from SyncLocalregistercenterinfos where centerId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new String[]{centerID});
	}
	
}
