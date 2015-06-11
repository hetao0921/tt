package com.fxdigital.analysis.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	private Logger logger = Logger.getLogger(BaseDao.class);

	/**
	 * Autowired 自动装配 相当于get() set()
	 */
	@Autowired
	protected SessionFactory sessionFactory;

	Connection conn = null;

	/**
	 * gerCurrentSession 会自动关闭session，使用的是当前的session事务
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * openSession 需要手动关闭session 意思是打开一个新的session
	 * 
	 * @return
	 */
	public Session getNewSession() {
		return sessionFactory.openSession();
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
			Session session = getNewSession();
			session.save(bean);
			session.flush();
			session.clear();
			session.close();
		} catch (Exception e) {
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

	public Connection getCurrConn() {
		try {
			if (null == conn || conn.isClosed()) {
				ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory)
						.getConnectionProvider();
				conn = cp.getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("getconnection error...." + e);
		}
		return conn;
	}

	public void executeBlobSql(String xml, String bussinessType, String sql) {
		Connection con = getCurrConn();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			byte[] prikey_dataBytes = xml.getBytes();
			ByteArrayInputStream bais2 = new ByteArrayInputStream(
					prikey_dataBytes);
			ps.setBinaryStream(1, bais2, prikey_dataBytes.length);
			// ps.setBytes(1, getbytefromobject(xml));
			ps.setString(2, bussinessType);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("insert table error " + sql + " , sql is" + sql
					+ "  error is " + e);
			System.exit(0);
			logger.info("the process is exit.");

		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != con) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private byte[] getbytefromobject(Object oo) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bs);
			oos.writeObject(oo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// create a objectoutputstream from bytearrayoutputstream

		return bs.toByteArray();
	}

}
