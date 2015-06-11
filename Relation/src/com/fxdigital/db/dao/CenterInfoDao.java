package com.fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hibernate.db.ConnDo;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Component
public class CenterInfoDao {
	
	public String query(){
		String hql = "select new Map(centerId as centerId) from NvmpCenterinfotab";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql);
		if(list != null && list.size() >= 1){
			return list.get(0).get("CenterID");
		}else{
			return null;
		}
	}
	
}
