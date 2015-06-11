package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.UpLink;
import com.hibernate.bean.MqQueueuplinktab;
import com.hibernate.db.ConnDo;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Component
public class QueueUpLinkDao {
	
	public List<UpLink> query(){
		String hql = "from MqQueueuplinktab";
		List<UpLink> upLinks = new ArrayList<UpLink>();
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1){
			for(Map<String, String> data : list){
				UpLink ul = new UpLink();
				ul.setId(Long.parseLong(data.get("ID")));
				ul.setCenterIP(data.get("CenterIP"));
				String relation = data.get("Flag");
				if("up".equals(relation))
					ul.setRelation("上级ip");
				else
					ul.setRelation("下级ip");
				upLinks.add(ul);
			}
		}
		return upLinks;
	}
	
	public int queryForID(){
		String hql = "select new Map(id as id) from MqQueueuplinktab order by id desc";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql);
		if(list != null && list.size() >= 1){
			return Integer.parseInt(list.get(0).get("ID"));
		}
		return 0;
	}
	
	public int delete(String centerIP){
		String hql = "delete from MqQueueuplinktab where centerIp='"+centerIP+"'";
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public boolean isIDExsit(int id){
		String hql = "from MqQueueuplinktab where id = "+id;
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public boolean isIPExsit(String ip){
		String hql = "from MqQueueuplinktab where centerIp = '"+ip+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public int insert(String centerID,String centerIP,int centerPort,String relation){
		MqQueueuplinktab up = new MqQueueuplinktab();
		up.setCenterId(centerID);
		up.setCenterIp(centerIP);
		up.setCenterPort(centerPort);
		up.setFlag(relation);
		if(ConnDo.getConnDo().save(up))
			return 1;
		else
			return -1;
	}
	
	public int insert(String centerID,String centerIP,String relation){
		MqQueueuplinktab up = new MqQueueuplinktab();
		up.setCenterId(centerID);
		up.setCenterIp(centerIP);
		up.setFlag(relation);
		up.setCenterPort(61616);
		if(ConnDo.getConnDo().save(up))
			return 1;
		else
			return -1;
	}
	
	public int update(int id,String centerID,String centerIP,int centerPort){
		String hql = "update MqQueueuplinktab set centerIp='%s' where id=%d and centerId='%s' and centerPort=%d";
		hql = String.format(hql, centerIP,id,centerID,centerPort);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int update(int id,String centerIP,int centerPort){
		String hql = "update MqQueueuplinktab set centerIp='%s' where id=%d and centerPort=%d";
		hql = String.format(hql, centerIP,id,centerPort);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int update(int id,String centerID,String centerIP){
		String hql = "update MqQueueuplinktab set centerIp='%s' where id=%d and centerId='%s'";
		hql = String.format(hql, centerIP,id,centerID);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int update(int id,String centerIP){
		String hql = "update MqQueueuplinktab set centerIp='%s' where id=%d";
		hql = String.format(hql, centerIP,id);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
}
