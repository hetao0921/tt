package com.hibernate.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

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
	public List<HashMap<String, String>> createQuery(String sqlName ) {
		log.info(sqlName);
		List<HashMap<String, String>> list =new ArrayList<HashMap<String,String>>();
		// LinkedList<String[]> lnkd = new LinkedList<String[]>();
		Session session=HibernateJmsClientSessionFactory.getSession();
		try {
			list= session.createQuery(sqlName).list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return list;
	}
	
	public List<Object[]> createQueryObjectArray(String sql){
		List<Object[]> list=new ArrayList<Object[]>();
		Session session=HibernateJmsClientSessionFactory.getSession();
		try {
			list= session.createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return list;
	}
	
	public boolean savenvmp(Object obj){
		boolean flag=true;
		Session session=HibernateNVMPSessionFactory.getSession();
		Transaction tran=null;
		try {
			tran=session.beginTransaction();
		session.save(obj);
		tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return flag;
	}
	
	public boolean deleteOrUpdate(String hql,String[] arg){
		log.info(hql+"--"+arg);
		boolean flag=true;
		Session session=HibernateJmsClientSessionFactory.getSession();
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
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return flag;
	
		
		
	}
}
