package com.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hibernate {
	
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(Hibernate.class);
	private static Hibernate hibernate =null;
	
	


	/**
	 * 根据SQL语句，返回查询结果
	 * 
	 * @param aim
	 * @return
	 */
	public List<HashMap<String, String>> createQuery(String hql ) {
		Session session = sessionFactory.getCurrentSession();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Query query = session.createQuery(hql);
			List<HashMap<String, Object>> data = query.list();
			for(HashMap<String,Object> map : data){
				HashMap<String,String> tmp = new HashMap<String,String>();
				for(String key : map.keySet()){
					tmp.put(key, map.get(key)==null?null:map.get(key).toString());
				}
				HashMap fxMap = new FxHashMap();
				fxMap.putAll(tmp);
				result.add(fxMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
}
	
	public List<Object[]> createQueryObjectArray(String sql){
		List<Object[]> list=new ArrayList<Object[]>();
		Session session=sessionFactory.getCurrentSession();
		try {
			list= session.createQuery(sql).list();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}
		return list;
	}
	public List<HashMap<String, Object>> createQueryList(String sql){
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> result=new ArrayList<HashMap<String, Object>>();
		Session session=sessionFactory.getCurrentSession();
		try {
			list= session.createQuery(sql).list();
			for(HashMap<String,Object> map : list){
				HashMap<String,Object> tmp = new HashMap<String,Object>();
				for(String key : map.keySet()){
					tmp.put(key, map.get(key)==null?null:map.get(key));
				}
				HashMap fxMap = new FxOHashMap();
				fxMap.putAll(tmp);
				result.add(fxMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}
		return list;
	}
	
	
	
	public boolean save(Object obj){
		boolean flag=true;
		Session session=sessionFactory.getCurrentSession();
		try {
		session.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			flag=false;
		}
		return flag;
	}
	
	public int deleteOrUpdate(String hql,Object[] arg){
		log.info(hql+"--"+arg);
		int result=0;
		Session session=sessionFactory.getCurrentSession();
		try {
		Query query=session.createQuery(hql);
		if(arg!=null){
		for (int i = 0; i < arg.length; i++) {
//			query.setString(i, arg[i]);
			query.setParameter(i, arg[i]);
		}
		}
		result=query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			}
		return result;
	
		
		
	}
	
	public boolean updateObject(Object obj){

		boolean flag=true;
		Session session=sessionFactory.getCurrentSession();
		try {
		session.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			flag=false;
			}
		return flag;
	
	}
}
