package fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.bean.SyncRegistercenterinfossync;
import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.db.ConnDo;

import fxdigital.db.RegisterCenter;

/**
 * 中心注册信息同步表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class RegisterCentersDao{
	

	@SuppressWarnings("unchecked")
	public List<RegisterCenter> query(){
		String map=ConvertMapUtil.map(SyncRegistercenterinfossync.class);
		String sql = "select "+map+" from SyncRegistercenterinfossync";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			List<RegisterCenter> re = new ArrayList<RegisterCenter>();
			for(Object o : list){
				Map<String, Object> data = (Map<String, Object>)o;
				System.err.println("SyncRegistercenterinfossync1 "+data);
				RegisterCenter ci = new RegisterCenter();
				ci.setCenterID((String)data.get("centerid"));
				ci.setCenterIP((String)data.get("centerip"));
				ci.setChannelName((String)data.get("channelname"));
				ci.setRegisterTime(Timestamp.valueOf(data.get("registertime").toString()));
				ci.setServerID((String)data.get("serverid"));
				ci.setSyncID((String)data.get("syncid"));
				re.add(ci);
			}
			return re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public RegisterCenter query(String centerID){
		String map=ConvertMapUtil.map(SyncRegistercenterinfossync.class);
		String sql = "select "+map+" from SyncRegistercenterinfossync where centerId='"+centerID+"'";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			Map<String, Object> data = (Map<String, Object>)list.get(0);
			System.err.println("SyncRegistercenterinfossync2 "+data);
			RegisterCenter ci = new RegisterCenter();
			ci.setCenterID((String)data.get("centerid"));
			ci.setCenterIP((String)data.get("centerip"));
			ci.setChannelName((String)data.get("channelname"));
			ci.setRegisterTime((Timestamp)data.get("registertime"));
			ci.setServerID((String)data.get("serverid"));
			ci.setSyncID((String)data.get("syncid"));
			return ci;
		}
	}
	
	public int delete(String centerID){
		String sql = "delete from SyncRegistercenterinfossync where centerId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{centerID});
	}
	
	public int deleteFromSyncID(String syncID){
		String sql = "delete from SyncRegistercenterinfossync where syncId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{syncID});
	}
	
	public int insert(String preMailboxID,RegisterCenter rci){
		SyncRegistercenterinfossync sr=new SyncRegistercenterinfossync();
		sr.setCenterId(rci.getCenterID());
		sr.setCenterIp(rci.getCenterIP());
		sr.setChannelName(rci.getChannelName());
		sr.setServerId(rci.getServerID());
		sr.setRegisterTime(rci.getRegisterTime());
		sr.setSyncId(preMailboxID);
		
		ConnDo.getConnDo().save(sr);
		return 1;
	}
	
	public int update(RegisterCenter rci,String syncID){
		String sql = "update SyncRegistercenterinfossync set centerIp=?,channelName=?,serverId=?,registerTime=?,syncId=? where centerId=?";
		ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{
				rci.getCenterIP(),
				rci.getChannelName(),
				rci.getServerID(),
				rci.getRegisterTime(),
				syncID,
				rci.getCenterID()
		});
		 return 1;
	}
	
}
