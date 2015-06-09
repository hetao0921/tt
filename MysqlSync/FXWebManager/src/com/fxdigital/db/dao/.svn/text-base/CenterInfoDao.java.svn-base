package com.fxdigital.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Component
public class CenterInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public String query(){
		String sql = "select * from nvmp.nvmp_centerinfotab";
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			Map<String, Object> data = (Map<String, Object>)list.get(0);
			return (String) data.get("CenterID");
		}else{
			return null;
		}
	}
	
}
