package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.DevInfoPojo;
import com.hibernate.bean.WebGroupconf;
import com.hibernate.db.ConnDo;

/**
 * 
 * @author hxht
 * @version 2014-9-15
 */
@Component
public class DevInfoDao {
	
	public List<DevInfoPojo> query(){
		String hql = "from WebGroupconf";
		List<DevInfoPojo> results = new ArrayList<DevInfoPojo>();
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() >= 1){
			for( int i=0;i<list.size();i++ ){
				HashMap<String, String> data = list.get(i);
				DevInfoPojo dip = new DevInfoPojo();
				dip.setId(Long.parseLong(data.get("ID")));
				dip.setName((String)data.get("Name"));
				dip.setUrl((String)data.get("URL"));
				dip.setDesc((String)data.get("Descs"));
				results.add(dip);
			}
		}
		return results;
	}
	
	public DevInfoPojo query(long id){
		String hql = "from WebGroupconf where id="+id;
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql);
		if(list != null && list.size() == 1){
			Map<String, String> data = list.get(0);
			DevInfoPojo dip = new DevInfoPojo();
			dip.setId(Long.parseLong(data.get("ID")));
			dip.setName((String)data.get("Name"));
			dip.setUrl((String)data.get("URL"));
			dip.setDesc((String)data.get("Descs"));
			return dip;
		}else{
			return null;
		}
	}
	
	public int insert(DevInfoPojo dev){
		WebGroupconf wg = new WebGroupconf();
		wg.setId(dev.getId());
		wg.setName(dev.getName());
		wg.setUrl(dev.getUrl());
		wg.setDescs(dev.getDesc());
		if(ConnDo.getConnDo().save(wg))
			return 1;
		else
			return -1;
	}
	
	public int update(DevInfoPojo dev){
		String hql = "update WebGroupconf set name='%s',url='%s',descs='%s' where id=%d";
		hql = String.format(hql, dev.getName(),dev.getUrl(),dev.getDesc(),dev.getId());
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int delete(long id){
		String hql = "delete from WebGroupconf where id="+id;
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
}
