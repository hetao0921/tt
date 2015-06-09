package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Component
public class LogTypeDao {
	
	//private static Logger log = Logger.getLogger(UserLogDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<String> query(){
		String sql = "select TypeName from nvmp.nvmp_logtypetab";
		List<?> list = jdbcTemplate.queryForList(sql);
		List<String> result = new ArrayList<String>();
		if(list == null || list.size() == 0){
			return result;
		}else{
			for(Object obj : list){
				Map<String,Object> row = (Map<String, Object>) obj;
				result.add((String)row.get("TypeName"));
			}
			return result;
		}
	}
	
}
