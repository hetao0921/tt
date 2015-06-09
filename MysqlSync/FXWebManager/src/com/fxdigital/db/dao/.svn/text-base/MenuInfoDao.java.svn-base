package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.MenuInfo;

import fxdigital.dbsync.domains.client.service.MsgClientService;


@Component
public class MenuInfoDao {
	private static Logger logger = Logger.getLogger(MenuInfoDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<MenuInfo> query(){
		String sql = "select * from nvmp.web_menutable order by id asc";
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				Map<String, Object> data = (Map<String, Object>) list.get(i);
				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setId(Integer.parseInt((String)data.get("MenuID")));
				menuInfo.setPid(Integer.parseInt((String)data.get("MenuPID")));
				menuInfo.setName((String)data.get("MenuName"));
				menuInfo.setDiyUrl((String)data.get("Url"));
				menuInfos.add(menuInfo);
			}
		}
		logger.info("menu size"+menuInfos.size());
		return menuInfos;
	}
	
	public int insert(MenuInfo menu){
		String sql = "insert into nvmp.web_menutable(MenuID,MenuPID,MenuName,Url) values(?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{
				menu.getId(),
				menu.getPid(),
				menu.getName(),
				menu.getDiyUrl()
		});
	}
	
	public int delete(int menuID){
		String sql = "delete from nvmp.web_menutable where MenuID=?";
		return jdbcTemplate.update(sql, new Object[]{menuID});
	}
}
