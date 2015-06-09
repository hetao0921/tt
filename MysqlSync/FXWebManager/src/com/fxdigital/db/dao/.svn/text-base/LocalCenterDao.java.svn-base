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
public class LocalCenterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public String query(){
		String sql = "select * from nvmp.mq_localcenterinfotab";
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			Map<String, Object> data = (Map<String, Object>) list.get(0);
			return (String) data.get("CenterIP");
		}
		return "";
	}
	
	public boolean isExsit(String centerID){
		String sql = "select * from nvmp.mq_localcenterinfotab where CenterID = ?";
		List<?> list = jdbcTemplate.queryForList(sql,new Object[]{centerID});
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public int insert(String centerID, String centerIP){
		String sql = "insert into nvmp.mq_localcenterinfotab(CenterID,CenterIP) values(?,?)";
		return jdbcTemplate.update(sql,new Object[]{centerID,centerIP});
	}
	
	public int insert(String centerID, String centerIP, int centerPort){
		String sql = "insert into nvmp.mq_localcenterinfotab(CenterID,CenterIP,CenterPort) values(?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{centerID,centerIP,centerPort});
	}
	
	public int update(String centerID, String centerIP, int centerPort){
		String sql = "update nvmp.mq_localcenterinfotab set CenterID=?,CenterIP=?,CenterPort=?";
		return jdbcTemplate.update(sql,new Object[]{centerID,centerIP,centerPort});
	}
	
}
