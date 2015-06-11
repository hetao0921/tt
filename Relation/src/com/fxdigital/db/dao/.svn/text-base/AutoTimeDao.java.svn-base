package com.fxdigital.db.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.bean.SyncDataSet;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;
import com.hibernate.db.Hibernate;

/**
 * sync_date_set
 * @author hxht
 * @version 2014-8-1
 */
@Repository
public class AutoTimeDao {

	public String query(){
		String map=ConvertMapUtil.map(SyncDataSet.class);
		String sql = "select "+map+" from SyncDataSet";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		return  (list.get(0).get("autotime"));
	}
	public int updateAutotime(String autotime){
		int bak=0;
		String sqls ="delete from SyncDataSet";
		ConnDo.getConnDo().executeUpdate(sqls);
		
		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);

		
//		String sqlss = "insert into nvmp.sync_data_set(autotime,setdate) values(?,?)";
		SyncDataSet set=new SyncDataSet();
		set.setAutotime(Integer.parseInt(autotime));
		set.setSetdate(dateNowStr);
//		bak= jdbcTemplate.update(sqlss, new Object[]{Integer.parseInt(autotime),dateNowStr});
//		System.out.println("jdbcTemplate执行完inssert后返回的bak值："+bak);
		ConnDo.getConnDo().save(set);
		return 1;
	}
}
