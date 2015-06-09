package com.fxdigital.db.dao;

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
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public String queryForName(String userID){
		String sql = "select UserNickName from nvmp.nvmp_userinfotab ui,nvmp.nvmp_centerinfotab ci where UserID=? and ui.CenterID=ci.CenterID";
		List<?> list = jdbcTemplate.queryForList(sql, new Object[]{userID});
		if(list == null || list.size() == 0){
			return null;
		}else{
			Map<String,Object> map = (Map<String, Object>) list.get(0);
			return (String) map.get("UserNickName");
		}
	}
	
	@SuppressWarnings("unchecked")
	public String queryForID(String userName){
		String sql = "select UserID from nvmp.nvmp_userinfotab ui,nvmp.nvmp_centerinfotab ci where UserNickName=? and ui.CenterID=ci.CenterID";
		List<?> list = jdbcTemplate.queryForList(sql, new Object[]{userName});
		if(list == null || list.size() == 0){
			return null;
		}else{
			Map<String,Object> map = (Map<String, Object>) list.get(0);
			return (String) map.get("UserID");
		}
	}
	
}
