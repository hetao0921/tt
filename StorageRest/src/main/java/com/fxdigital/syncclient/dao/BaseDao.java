package com.fxdigital.syncclient.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.util.ConvertBean;
import com.fxdigital.syncclient.util.FxHashMap;
import com.fxdigital.syncclient.util.HibernateNVMPSessionFactory;

@Repository
public class BaseDao {
	private static Logger logger = Logger.getLogger(BaseDao.class);
	/**
	 * Autowired 自动装配 相当于get() set()
	 */
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	

	public Connection currConn;

	/**
	 * gerCurrentSession 会自动关闭session，使用的是当前的session事务
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
		//		return HibernateNVMPSessionFactory.getSession();
	}

	/**
	 * openSession 需要手动关闭session 意思是打开一个新的session
	 * 
	 * @return
	 */
	public Session getNewSession() {
		return sessionFactory.openSession();
		//		return HibernateNVMPSessionFactory.getSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	/**
	 * 根据 id 查询信息
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object load(Class c, String id) {
		Session session = getSession();
		return session.get(c, id);
	}

	/**
	 * 获取所有信息
	 * 
	 * @param c
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getAllList(Class c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();
	}

	/**
	 * 获取总数量
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Long getTotalCount(Class c) {
		Session session = getNewSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	/**
	 * 保存
	 * 
	 * @param bean
	 * 
	 */
	public void save(Object bean) {
		try {
			Session session = getSession();
			session.save(bean);
			/*
			 * Session session = getNewSession(); session.save(bean);
			 * session.flush(); session.clear(); session.close();
			 */
		} catch (Exception e) {
			logger.info("save实体类出错" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 更新
	 * 
	 * @param bean
	 * 
	 */
	public void update(Object bean) {
		Session session = getNewSession();
		session.update(bean);
		session.flush();
		session.clear();
		session.close();
	}

	/**
	 * 删除
	 * 
	 * @param bean
	 * 
	 */
	public void delete(Object bean) {
		Session session = getNewSession();
		session.delete(bean);
		session.flush();
		session.clear();
		session.close();
	}

	/**
	 * 根据ID删除
	 * 
	 * @param c
	 *            类
	 * 
	 * @param id
	 *            ID
	 * 
	 */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String id) {
		Session session = getNewSession();
		Object obj = session.get(c, id);
		session.delete(obj);
		flush();
		clear();
	}

	/**
	 * 批量删除
	 * 
	 * @param c
	 *            类
	 * 
	 * @param ids
	 *            ID 集合
	 * 
	 */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		for (String id : ids) {
			Object obj = getSession().get(c, id);
			if (obj != null) {
				getSession().delete(obj);
			}
		}
	}

	public List getAllList(String hql) {
		Session session = getSession();
		List<Map<String, Object>> list = session.createQuery(hql).list();
		return list;
	}

	public void deleteOrUpdate(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

	public void executeSql(String sql) {
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	public void batchExecuteSql(String[] sql) {
		Session session = getSession();
		for (String string : sql) {
			Query query = session.createSQLQuery(string);
			query.executeUpdate();
		}
	}

	public List<Object[]> querySql(String sql) {
		Session session = getSession();

		Query query = session.createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object> querySqlList(String sql) {
		Session session = getSession();

		Query query = session.createSQLQuery(sql);
		List<Object> list = query.list();
		return list;
	}

	public List<Object> queryObjectList(String sql, String paraStr) {
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setParameter(0, paraStr);
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap<String, String>> executeQuery(String hql) {
		Session session = getSession();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

		Query query = session.createQuery(hql);
		List<HashMap<String, Object>> data = query.list();
		for (HashMap<String, Object> map : data) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			for (String key : map.keySet()) {
				tmp.put(key, map.get(key) == null ? null : map.get(key)
						.toString());
			}
			HashMap fxMap = new FxHashMap();
			fxMap.putAll(tmp);
			result.add(fxMap);
		}

		return result;
	}

	public int executeUpdate(String hql) {
		Session session = getSession();
		int result = -1;
		result = session.createQuery(hql).executeUpdate();
		return result;
	}

	public List<Map<String, Object>> executeQueryToObjectList(String hql) {
		Session session = getSession();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Query query = session.createQuery(hql);
		List<Object> data = query.list();
		for (Object o : data) {
			result.add(ConvertBean.convertBean(o, false));
		}

		return result;
	}

	public List<String[]> executeQueryStrArray(String hql) {

		List<String[]> list = new ArrayList<String[]>();
		Session session = getSession();

		Query query = session.createQuery(hql);
		List<Object> data = query.list();
		for (Object o : data) {
			list.add(ConvertBean.convertBean(o));
		}

		return list;

	}

	public Connection getCurrConn() {

		return currConn;
	}

	public List<Map<String, Object>> executeQueryMarkList(final String hql) {
		Session session = getNewSession();
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		session.doWork(new Work() {
			public void execute(Connection conn) {
				Statement st = null;
				java.sql.ResultSet rs = null;
				try {
					st = conn.createStatement();
					rs = st.executeQuery(hql);
					System.out.println("查询结果集rs: " + rs);
					Map<String, Object> map = null;
					while (rs.next()) {
						try {
							map = new HashMap<String, Object>();
							map.put("nvmp_mark_uuid",
									rs.getString("nvmp_mark_uuid"));
							map.put("nvmp_base_uuid",
									rs.getString("nvmp_base_uuid"));
							map.put("nvmp_mark_name",
									rs.getString("nvmp_mark_name"));
							map.put("nvmp_start_time",
									rs.getTimestamp("nvmp_start_time"));
							map.put("nvmp_end_time",
									rs.getTimestamp("nvmp_end_time"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							logger.info("select table nvmp_record_mark error :"
									+ e);
						}
						result.add(map);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
                    try {
                    	if(null!=rs)
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.info("close rs error"+e);
					}
                    
                    try {
                    	if(null!=st)
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.info("close st error"+e);
					}
				}

			}
		});
		session.close();
		return result;
	}

}
