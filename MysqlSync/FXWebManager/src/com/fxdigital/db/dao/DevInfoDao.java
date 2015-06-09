package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.DevInfoPojo;

/**
 * 
 * @author hxht
 * @version 2014-9-15
 */
@Component
public class DevInfoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<DevInfoPojo> query(){
		String sql = "select * from nvmp.web_groupconf";
		List<DevInfoPojo> results = new ArrayList<DevInfoPojo>();
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				Map<String, Object> data = (Map<String, Object>) list.get(i);
				DevInfoPojo dip = new DevInfoPojo();
				dip.setId(Long.parseLong(data.get("ID").toString()));
				dip.setName((String)data.get("Name"));
				dip.setUrl((String)data.get("URL"));
				dip.setDesc((String)data.get("_Desc"));
				results.add(dip);
			}
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public DevInfoPojo query(long id){
		String sql = "select * from nvmp.web_groupconf where ID=?";
		List<?> list = jdbcTemplate.queryForList(sql,id);
		if(list != null && list.size() == 1){
			Map<String, Object> data = (Map<String, Object>) list.get(0);
			DevInfoPojo dip = new DevInfoPojo();
			dip.setId(Long.parseLong(data.get("ID").toString()));
			dip.setName((String)data.get("Name"));
			dip.setUrl((String)data.get("URL"));
			dip.setDesc((String)data.get("_Desc"));
			return dip;
		}else{
			return null;
		}
	}
	
	public int insert(DevInfoPojo dev){
		String sql = "insert into nvmp.web_groupconf(Name,URL,_Desc) values(?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{
				dev.getName(),
				dev.getUrl(),
				dev.getDesc()
		});
	}
	
	public int update(DevInfoPojo dev){
		String sql = "update nvmp.web_groupconf set Name=?,URL=?,_Desc=? where ID=?";
		return jdbcTemplate.update(sql, new Object[]{
				dev.getName(),
				dev.getUrl(),
				dev.getDesc(),
				dev.getId()
		});
	}
	
	public int delete(long id){
		String sql = "delete from nvmp.web_groupconf where ID=?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}
	
}
