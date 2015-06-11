package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.MenuInfo;
import com.fxdigital.db.pojo.MenuSyncInfo;
import com.hibernate.bean.WebMenutablesync;
import com.hibernate.db.ConnDo;


@Component
public class MenuSyncInfoDao {
	private static Logger logger = Logger.getLogger(MenuSyncInfoDao.class);
	
	public List<MenuSyncInfo> query(){
		String hql = "from WebMenutablesync order by id asc";
		List<MenuSyncInfo> menuInfos = new ArrayList<MenuSyncInfo>();
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				HashMap<String, String> data = list.get(i);
				MenuSyncInfo menuInfo = new MenuSyncInfo();
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
	
	public int insert(MenuSyncInfo menu){
		WebMenutablesync web = new WebMenutablesync();
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
		String hql = "delete from WebMenutablesync where menuId='"+menuID+"'";
		return ConnDo.getConnDo().executeUpdate(hql);
	}
}
