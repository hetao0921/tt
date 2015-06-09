package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.Table;


@Component
public class TableTestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Table> query(){
		String sql = "select * from nvmp.tabletest";
		List<Table> tables = new ArrayList<Table>();
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				Map<String, Object> data = (Map<String, Object>) list.get(i);
				Table table = new Table();
				table.setId((String)data.get("id"));
				table.setName((String)data.get("name"));
				table.setPort((Integer)data.get("port"));
				tables.add(table);
			}
		}
		return tables;
	}
}
