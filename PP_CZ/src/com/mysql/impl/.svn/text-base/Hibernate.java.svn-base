package com.mysql.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Hibernate {
	private static Logger log = Logger.getLogger(Hibernate.class);
	private static Hibernate hibernate =null;
	private SessionFactory sessionFactory=null;
	private Hibernate(){
		
	}
	
	public static Hibernate getHibernate(){
		if(hibernate==null){
			hibernate=new Hibernate();
		}
		return hibernate;
	}
	


	/**
	 * 根据SQL语句，返回查询结果
	 * 
	 * @param aim
	 * @return
	 */
	public List<HashMap<String, String>> createQuery(String hql ) {
		Session session = HibernateSessionFactory.getSession();
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
		HibernateSessionFactory.closeSession();
		return result;
}
	
	public List<Object[]> createQueryObjectArray(String sql){
		List<Object[]> list=new ArrayList<Object[]>();
		Session session=HibernateSessionFactory.getSession();
		try {
			list= session.createQuery(sql).list();
			HibernateSessionFactory.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return list;
	}
	
	public boolean save(Object obj){
		boolean flag=true;
		Session session=HibernateSessionFactory.getSession();
		Transaction tran=null;
		try {
			tran=session.beginTransaction();
		session.save(obj);
		tran.commit();
		HibernateSessionFactory.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return flag;
	}
	
	public boolean deleteOrUpdate(String hql,String[] arg){
		log.info(hql+"--"+arg);
		boolean flag=true;
		Session session=HibernateSessionFactory.getSession();
		Transaction tran =null;
		try {
		 tran=session.beginTransaction();
		Query query=session.createQuery(hql);
		if(arg!=null){
		for (int i = 0; i < arg.length; i++) {
//			query.setString(i, arg[i]);
			query.setParameter(i, arg[i]);
		}
		}
		query.executeUpdate();
		tran.commit();
		HibernateSessionFactory.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return flag;
	
		
		
	}
	
	public boolean updateObject(Object obj){

		boolean flag=true;
		Session session=HibernateSessionFactory.getSession();
		Transaction tran=null;
		try {
			tran=session.beginTransaction();
		session.update(obj);
		tran.commit();
		HibernateSessionFactory.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return flag;
	
	}
}
