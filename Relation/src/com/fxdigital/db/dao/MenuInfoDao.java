package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.MenuInfo;
import com.hibernate.bean.WebMenutable;
import com.hibernate.db.ConnDo;


@Component
public class MenuInfoDao {
	private static Logger logger = Logger.getLogger(MenuInfoDao.class);
	
	public List<MenuInfo> query(){
		String hql = "from WebMenutable order by id asc";
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				HashMap<String, String> data = list.get(i);
				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setId(Integer.parseInt(data.get("MenuID")));
				menuInfo.setPid(Integer.parseInt(data.get("MenuPID")));
				menuInfo.setName(data.get("MenuName"));
				menuInfo.setDiyUrl(data.get("Url"));
				menuInfos.add(menuInfo);
			}
		}
		logger.info("menu size"+menuInfos.size());
		return menuInfos;
	}
	
	public int insert(MenuInfo menu){
		WebMenutable web = new WebMenutable();
		web.setMenuId(String.valueOf(menu.getId()));
		web.setMenuPid(String.valueOf(menu.getPid()));
		web.setMenuName(menu.getName());
		web.setUrl(menu.getDiyUrl());
		if(ConnDo.getConnDo().save(web))
			return 1;
		else
			return -1;
	}
	
	public int delete(int menuID){
		String hql = "delete from WebMenutable where menuId='"+menuID+"'";
		return ConnDo.getConnDo().executeUpdate(hql);
	}
}
