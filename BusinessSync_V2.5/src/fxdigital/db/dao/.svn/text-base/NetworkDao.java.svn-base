package fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncNetworkinfo;
import com.hibernate.db.ConnDo;

import fxdigital.db.ServerRelation;

/**
 * 同步服务器关系表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class NetworkDao{
	

	
	@SuppressWarnings("unchecked")
	public List<ServerRelation> query(){
		String map=ConvertMapUtil.map(SyncNetworkinfo.class);
		String sql = "select "+map+" from SyncNetworkinfo";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		List<ServerRelation> serverRelations = new ArrayList<ServerRelation>();
		for(Object tp : list){
			Map<String, Object> data = (Map<String, Object>) tp;
			System.err.println("SyncNetworkinfo1 "+data);
			ServerRelation serverRelation = new ServerRelation();
			serverRelation.setSubID((String)data.get("subid"));
			serverRelation.setSubIP((String)data.get("subip"));
			serverRelation.setSuperID((String)data.get("supid"));
			serverRelation.setSuperIP((String)data.get("supip"));
			serverRelations.add(serverRelation);
		}
		return serverRelations;
	}
	
	@SuppressWarnings("unchecked")
	public String querySuperID(String subID){
		String map=ConvertMapUtil.map(SyncNetworkinfo.class);
		String sql = "select "+map+" from SyncNetworkinfo where subId='"+subID+"'";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		Map<String, Object> data = (Map<String, Object>) list.get(0);
		System.err.println("SyncNetworkinfo2 "+data);
		return (String)data.get("supid");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> querySubIDs(String superID){
		String map=ConvertMapUtil.map(SyncNetworkinfo.class);
		String sql = "select "+map+" from SyncNetworkinfo where supId='"+superID+"'";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() == 0){
			return null;
		}
		List<String> subIDs = new ArrayList<String>();
		for(Object o : list){
			Map<String, Object> data = (Map<String, Object>) o;
			System.err.println("SyncNetworkinfo3 "+data);
			subIDs.add((String)data.get("subid"));
		}
		return subIDs;
	}
	
	public int insert(String syncID,ServerRelation serverRelation){
		SyncNetworkinfo sn=new SyncNetworkinfo();
		sn.setSubId(serverRelation.getSubID());
		sn.setSubIp(serverRelation.getSubIP());
		sn.setSupId(serverRelation.getSuperID());
		sn.setSupIp(serverRelation.getSuperIP());
		sn.setSyncId(syncID);
		ConnDo.getConnDo().save(sn);
		return 1;
	}
	
	public int insert(String subID,String subIP,String supID,String supIP){
		SyncNetworkinfo sn=new SyncNetworkinfo();
		sn.setSubId(subID);
		sn.setSubIp(subIP);
		sn.setSupId(supID);
		sn.setSupIp(supIP);
		ConnDo.getConnDo().save(sn);
		return 1;
	}
	
	public int deleteSyncID(String syncID){
		String sql = "delete from SyncNetworkinfo where syncId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new String[]{syncID});
	}
	
	public int deleteSub(String subID){
		String sql = "delete from SyncNetworkinfo where subId=?";
		return ConnDo.getConnDo().deleteOrUpdate(sql, new String[]{subID});
	}
	
	public int clear(){
		String sql = "delete from SyncNetworkinfo";
		 try {
			 ConnDo.getConnDo().updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return 1;
	}
	
}
