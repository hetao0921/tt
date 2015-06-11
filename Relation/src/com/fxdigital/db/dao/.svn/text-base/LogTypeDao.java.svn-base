package com.fxdigital.db.dao;

import java.util.ArrayList;
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
public class LogTypeDao {
	
	public List<String> query(){
		String hql = "select new Map(typeName as typeName) from NvmpLogtypetab";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql);
		List<String> result = new ArrayList<String>();
		if(list != null && list.size() == 0){
			return result;
		}else{
			for(HashMap<String,String> map : list){
				result.add(map.get("TypeName"));
			}
			return result;
		}
	}
	
}
