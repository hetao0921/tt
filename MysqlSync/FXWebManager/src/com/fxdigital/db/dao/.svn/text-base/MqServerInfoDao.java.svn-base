package com.fxdigital.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class MqServerInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> queryCenterID(){
		String sql = "select * from nvmp.nvmp_centerinfotab";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			return list;
	}
	public List<Map<String, Object>> querySyncInfo(){
		String sql = "select * from nvmp.center_mqserverinfo";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	public int updateSyncinfo(String syncip,String syncport){
		int bak=0;
		String sqls ="delete  from nvmp.center_mqserverinfo";
		jdbcTemplate.update(sqls);
		
		String sqlss = "insert into nvmp.center_mqserverinfo(MqIP,MqPort) values(?,?)";
		bak= jdbcTemplate.update(sqlss, new Object[]{syncip,Integer.parseInt(syncport)});
		System.out.println("jdbcTemplate执行完inssert后返回的bak值："+bak);
	
		return bak;
	}
}
