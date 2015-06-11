package fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fxdigital.bean.SyncLocalregistercenterinfos;
import com.hibernate.ConvertMapUtil;
import com.hibernate.Hibernate;

import fxdigital.db.RegisterCenter;

/**
 * 本级注册的中心表
 * @author fucz
 * @version 2014-6-30
 */
@Repository
public class LocalRegisterCentersDao{
	
	@Autowired
	private Hibernate hibernate;
	
	public boolean isExist(String centerID){
		String map=ConvertMapUtil.map(SyncLocalregistercenterinfos.class);
		String sql = "select "+map+" from SyncLocalregistercenterinfos where centerId='"+centerID+"'";
		
		List<?> list = hibernate.createQuery(sql);
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
		List<?> list = hibernate.createQueryList(sql);
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
		String sql = "select "+map+" from SyncLocalregistercenterinfos";
		List<?> list = hibernate.createQueryList(sql);
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
		return hibernate.deleteOrUpdate(sql, null);
	}
	
	public int insert(RegisterCenter rci){
		SyncLocalregistercenterinfos ss=new SyncLocalregistercenterinfos();
		ss.setCenterId(rci.getCenterID());
		ss.setCenterIp(rci.getCenterIP());
		ss.setChannelName(rci.getChannelName());
		ss.setRegisterTime(rci.getRegisterTime());
		hibernate.save(ss);
		return 1;
		
	}
	
	public int delete(String centerID){
		String sql = "delete from SyncLocalregistercenterinfos where centerId=?";
		return hibernate.deleteOrUpdate(sql, new String[]{centerID});
	}
	
}
