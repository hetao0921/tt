package com.fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hibernate.db.ConnDo;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Component
public class UserDao {
	
	public String queryForName(String userID){
		String hql = "select new Map(ui.userNickName as userNickName) from NvmpUserinfotab ui,NvmpCenterinfotab ci where ui.userId='"+userID+"' and ui.centerId=ci.centerId";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			return list.get(0).get("UserNickName");
		}
	}
	
	public String queryForID(String userName){
		String hql = "select new Map(ui.userId as userId) from NvmpUserinfotab ui,NvmpCenterinfotab ci where ui.userNickName='"+userName+"' and ui.centerId=ci.centerId";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql);
		if(list == null || list.size() == 0){
			return null;
		}else{
			return list.get(0).get("UserID");
		}
	}
	
}
