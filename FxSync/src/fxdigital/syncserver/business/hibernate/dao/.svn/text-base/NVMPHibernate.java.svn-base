package fxdigital.syncserver.business.hibernate.dao;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.alibaba.fastjson.JSON;

import fxdigital.syncserver.business.DownLoad;
import fxdigital.util.Log4jUtil;

public class NVMPHibernate {
	


	public static NVMPHibernate cd = new NVMPHibernate();
	
	private Session session;

	private NVMPHibernate() {
		session = HibernateNVMPSessionFactory.getSession();
	}

	public static NVMPHibernate getNVMPhibernate() {
		return cd;
	}

	public int executeQueryTran(String[] hqls) {
		session = HibernateNVMPSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		int result = -1;
		try{
			for(String hql : hqls){
				result = session.createQuery(hql).executeUpdate();
				if(result < 0){
					tx.rollback();
					return result;
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return -1;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}

	public String[][] executeQueryStr(String hql){
		return toArray(executeQuery(hql));
	}
	
	public String[][] executeQueryToArray(String hql){
		return toArray(executeQueryToList(hql));
	}
	
	public String[][] executeQueryToArray(String hql,int start,int limit){
		return toArray(executeQueryToList(hql));
	}
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private HashMap convertBean(Object bean,boolean toString)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        HashMap returnMap = new FxObjectHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                	if(toString)
                		returnMap.put(propertyName.toString(), result.toString());
                	else
                		returnMap.put(propertyName.toString(), result);
                } else {
                    returnMap.put(propertyName.toString(), "");
                }
            }
        }
        return returnMap;
    }
    
    public static String[][] toArray(List<HashMap<String, String>> list){
    	String[][] result = new String[0][0];
    	if(list != null && list.size() > 0){
    		result = new String[list.size()+1][list.get(0).size()];
    		int i = 0;
    		for(String key : list.get(0).keySet()){
    			result[0][i] = key;
    			i ++;
    		}
    		int row_num = 1;
    		for(HashMap<String, String> row : list){
    			int column_num = 0;
    			for(String value : row.values()){
    				result[row_num][column_num] = value;
    				column_num ++;
    			}
    			row_num ++;
    		}
    	}
    	return result;
    }

	@SuppressWarnings({ "unchecked" })
	public List<HashMap<String, String>> executeQueryToList(String hql){
		session = HibernateNVMPSessionFactory.getSession();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try{
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				result.add(convertBean(o,true));
			}
		}catch(Exception e){
			e.printStackTrace();
			result = null;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<HashMap<String, String>> executeQueryToList(String hql,int start,int limit){
		session = HibernateNVMPSessionFactory.getSession();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try{
			Query query = session.createQuery(hql);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<Object> data = query.list();
			for(Object o : data){
				result.add(convertBean(o,true));
			}
		}catch(Exception e){
			e.printStackTrace();
			result = null;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Map<String, Object>> executeQueryToObjectList(String hql){
		session = HibernateNVMPSessionFactory.getSession();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			System.err.println("List<Object> data  "+data);
			for(Object o : data){
				System.err.println("List<Object> o  "+JSON.toJSONString(o));
				result.add(convertBean(o,true));
			}
			System.err.println("List<Object> result  "+result);
		}catch(Exception e){
			e.printStackTrace();
			result = null;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap<String, String>> executeQuery(String hql){
		session = HibernateNVMPSessionFactory.getSession();
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
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> executeQueryToObject(String hql){
		session = HibernateNVMPSessionFactory.getSession();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			Query query = session.createQuery(hql);
			List<HashMap<String, Object>> data = query.list();
			for(HashMap<String,Object> map : data){
				HashMap<String,Object> tmp = new HashMap<String,Object>();
				for(String key : map.keySet()){
					tmp.put(key, map.get(key)==null?null:map.get(key));
				}
				result.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}

	public int executeUpdate(String hql) {
		session = HibernateNVMPSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		int result = -1;
		try{
			result = session.createQuery(hql).executeUpdate();
			if(result < 0){
				tx.rollback();
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}
	
	
	public int deleteOrUpdate(String hql,Object[] arg){
		session = HibernateNVMPSessionFactory.getSession();
		int result=0;
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
		result=query.executeUpdate();
		tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			tran.rollback();
			}finally{
				HibernateNVMPSessionFactory.closeSession();
			}
		return result;
	
		
		
	}
	

    public Integer executeProc(String procName,String param,Integer outParam){
    	session = HibernateNVMPSessionFactory.getSession();
    	Transaction tx = session.beginTransaction();
		int result = -1;
		try{
			SQLQuery query = session.createSQLQuery("{call "+procName+"(?)}");
			query.setString(0, param);
			result = query.executeUpdate();
			if(result < 0){
				tx.rollback();
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
		return result;
	}
    
    public boolean save(Object o){
    	session = HibernateNVMPSessionFactory.getSession();
    	Transaction tx = session.beginTransaction();
    	try {
    		session.save(o);
    		tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}
    	return true;
    }
    
    public String[][] executeQueryWithConn(String aim){
		java.sql.Statement st=null;
		ResultSet resultSet =null;
		String[][] al=null;
		try {
			SessionFactoryImplementor sf = (SessionFactoryImplementor)
					HibernateNVMPSessionFactory.getSessionFactory();
			Connection conn = sf.getConnectionProvider().getConnection();
			st = conn.createStatement();
			resultSet= st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			ArrayList<String[]> alist = new ArrayList<String[]>();
			String[] sAry = new String[remd.getColumnCount()];
			for (int i = 1; i <= remd.getColumnCount(); i++) {
				sAry[i - 1] = remd.getColumnName(i);
			}
			alist.add(sAry.clone());
			while (resultSet.next()) {

				for (int i = 1; i <= remd.getColumnCount(); i++) {
					sAry[i - 1] = resultSet.getString(i);
				}
				alist.add(sAry.clone());
			}

			al= new String[alist.size()][remd.getColumnCount()];
			int j = 0;
			for (String[] a : alist) {
				al[j++] = a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (resultSet !=null) {
				try {
					resultSet.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
		return al;
	}
	
	public void updateInfo(String hql) throws Exception {


		session = HibernateNVMPSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			tran.rollback();
		}finally{
			HibernateNVMPSessionFactory.closeSession();
		}

	}
    public List getAllList(Class c) {
    	List list = null;
    	try{
        String hql = "from " + c.getName();
        Session session =HibernateNVMPSessionFactory.getSession();
        list=session.createQuery(hql).list();
        }catch(Exception e){
        	Log4jUtil.info(this.getClass(),"新转换旧出错" + e);
        }finally{
        	if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
        }
        return list; 
    }
    
    
	public List<HashMap<String, String>> executeQueryNVMPSql(String aim) {
		session = HibernateNVMPSessionFactory.getSession();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Query query = session.createQuery(aim);
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
 finally {
			if (session != null) {
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return result;
}
	
	public List<HashMap<String, String>> executeQuerySql(String aim) {
		try{
			session = HibernateNVMPSessionFactory.getSession();
		}catch(Exception e){
			Log4jUtil.info(this.getClass(),"获取连接失败: "+e);
		}
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Query query = session.createQuery(aim);
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
		finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return result;
}
	
	public List<String[]> executeQuerySqls(String hql){

		List<String[]> list = new ArrayList<String[]>();
		session = HibernateNVMPSessionFactory.getSession();
		try {
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				list.add(convertBean(o));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return list;

	}
	
	
	private String[] convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        HashMap returnMap = new FxHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        String[] temp=new String[propertyDescriptors.length];
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName.toString(), result.toString());
                    temp[i]= result.toString();
                } else {
                    returnMap.put(propertyName.toString(), "");
                    temp[i]= "";
                }
            }
        }
        return temp;
    }
	
	
	public void updateNVMPInfo(String hql) throws Exception {

		session = HibernateNVMPSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}


	}
	
}
