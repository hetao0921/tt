package com.fxdigital.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.db.dao.MenuInfoDao;
import com.fxdigital.db.dao.TableTestDao;
import com.fxdigital.db.pojo.MenuInfo;
import com.fxdigital.db.pojo.Table;

/**
 * 
 * @author fucz
 * @version 2014-8-12
 */
@Component
public class DbManager {

	@Autowired
	private TableTestDao tableTestDao;
	@Autowired
	private MenuInfoDao menuInfoDao;

	public List<MenuInfo> getMenuInfo() {
		return menuInfoDao.query();
	}
	
	public boolean addMenu(MenuInfo menu){
		int num = menuInfoDao.insert(menu);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean delMenu(int id){
		int num = menuInfoDao.delete(id);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public List<Table> getTables(){
		return tableTestDao.query();
	}
	
}
