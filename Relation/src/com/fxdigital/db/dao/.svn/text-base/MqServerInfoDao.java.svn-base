package com.fxdigital.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hibernate.bean.CenterMqserverinfo;
import com.hibernate.db.ConnDo;

@Component
public class MqServerInfoDao {
	
	public List<Map<String, Object>> queryCenterID(){
		String hql = "from NvmpCenterinfotab";
		return ConnDo.getConnDo().executeQueryToObjectList(hql);
	}
	
	public List<Map<String, Object>> querySyncInfo(){
		String hql = "from CenterMqserverinfo";
		return ConnDo.getConnDo().executeQueryToObjectList(hql);
	}
	
	public int updateSyncinfo(String syncip,String syncport){
		int bak=0;
		String hql ="delete from CenterMqserverinfo";
		ConnDo.getConnDo().executeUpdate(hql);
		
		CenterMqserverinfo server = new CenterMqserverinfo();
		server.setMqIp(syncip);
		server.setMqPort(Integer.parseInt(syncport));
		if(ConnDo.getConnDo().save(server))
			bak = 1;
		else
			bak = -1;
		System.out.println("jdbcTemplate执行完inssert后返回的bak值："+bak);
	
		return bak;
	}
}
