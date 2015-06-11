package fxdigital.postserver.contentdispose.handlers.dbsync.db;

/**
 * @author  het
 *数据连接类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.db
 */
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hibernate.db.HibernateJmsServerSessionFactory;
import com.hibernate.db.HibernateNVMPSessionFactory;


public class DBConn {
//	static {
//		PropertyConfigurator.configure("conf/log4j.properties");
////		new ReadXml().read();
//	}
	
	
	
	private Logger logger = Logger.getLogger(DBConn.class);

	private static DBConn db = null;
	



	public static DBConn getDBConn() {
		if (db == null)
			db = new DBConn();
		return db;
	}

	/**
	 * ��ȡһ����ݿ�����
	 * 
	 * @return
	 */
	public Session getConn() {
		// jms_server
		Session session = HibernateJmsServerSessionFactory.getSession();
		return session;
	}

	public Session getConn2() {
		// nvmp
		Session session =  HibernateNVMPSessionFactory.getSession();
		return session;
	}

	public void updateInfo(String hql) throws Exception {

		logger.info(hql + "--");
		Session session = getConn();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
			tran.rollback();
		}finally{
			if(session!=null){
				HibernateJmsServerSessionFactory.closeSession();
			}
		}

	}

	public void updateNVMPInfo(String hql) throws Exception {

		logger.info(hql + "--");
		Session session = getConn2();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
			tran.rollback();
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}


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
			logger.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return flag;
	}
	
	
	
	
	
	public boolean save(Object obj){
		boolean flag=true;
		Session session=HibernateJmsServerSessionFactory.getSession();
		Transaction tran=null;
		try {
			tran=session.beginTransaction();
		session.save(obj);
		tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
			tran.rollback();
			flag=false;
		}finally{
			if(session!=null){
				HibernateJmsServerSessionFactory.closeSession();
			}
		}
		return flag;
	}
	
	public static void main(String[]args){
		DBConn d = new DBConn();
		String ip = d.getDBIP();
		System.out.println("ip:"+ip);
		
//		DBConn d = new DBConn();
//		List<String[]> list = d.executeQuery("select * from data_operate where operate = '上传'");
//		for(String[]ss :list){
//			for(String s :ss){
//				System.out.print(s+"\t");
//			}
//			System.out.println();
//		}
//		System.out.println("Connection:"+d.getConn());
	}
	
	
	private HashMap convertBeanToMap(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        HashMap returnMap = new FxHashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName.toString(), result.toString());
                } else {
                    returnMap.put(propertyName.toString(), "");
                }
            }
        }
        return returnMap;
    }
	
	public List<HashMap<String, String>> executeQueryToList(String hql){
		Session session = getConn();
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try{
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				result.add(convertBeanToMap(o));
			}
		}catch(Exception e){
			e.printStackTrace();
			result = null;
		}finally{
			if(session!=null){
				HibernateJmsServerSessionFactory.closeSession();
			}
		}
		return result;
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
	
	
	
	public List<String[]> executeQuery(String hql){

		List<String[]> list = new ArrayList<String[]>();
		Session session = getConn();
		try {
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				list.add(convertBean(o));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}finally{
			if(session!=null){
				HibernateJmsServerSessionFactory.closeSession();
			}
		}
		return list;

	}
	
	public List<String[]> executeQuery2(String hql){

		List<String[]> list = new ArrayList<String[]>();
		Session session = getConn2();
		try {
			list = session.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}finally{
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return list;

	}
	
	public List<String[]> executeQueryXml(String aim) throws SQLException {
		Session session = getConn2();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		//Connection conn = SessionFactoryUtils.getDataSource(HibernateNVMPSessionFactory.getSessionFactory()).getConnection();
		ConnectionProvider cp =((SessionFactoryImplementor)HibernateNVMPSessionFactory.getSessionFactory()).getConnectionProvider();  
		Connection conn=cp.getConnection(); 
		
		Statement st = conn.createStatement();
	    ResultSet resultSet = st.executeQuery(aim);
	    ResultSetMetaData remd = resultSet.getMetaData();
		 
		String[] sss = new String[remd.getColumnCount()];
		for (int i = 1; i <= remd.getColumnCount(); i++) {
			sss[i - 1] = remd.getColumnName(i);
		}
		lnkd.add(sss);
		sss = new String[remd.getColumnCount()];
		for (int i = 1; i <= remd.getColumnCount(); i++) {
			sss[i - 1] = remd.getColumnClassName(i);
		}
		lnkd.add(sss);
		while (resultSet.next()) {
			// HashMap<String, String> hm = new HashMap<String, String>();
			String[] ss = new String[remd.getColumnCount()];
			for (int i = 1; i <= remd.getColumnCount(); i++) {
				ss[i - 1] = resultSet.getString(i);
				//System.out.println("--------------aim " +aim +"return  "+ss[i - 1]);
				// hm.put(remd.getColumnName(i), resultSet.getString(i));
			}
			lnkd.add(ss);
		}
		resultSet.close();
		st.close();
		
		return lnkd;
	}
	
//	public void executeProc(String name) throws Exception{
//		CallableStatement stmt = getConn().prepareCall("{call "+name+"}");
//		stmt.setString(1, "");
//		stmt.registerOutParameter(2,Type.INTERNAL);
//		stmt.setInt(2, 0);
//		stmt.execute();
//		stmt.close();
//	}
	
	public List<HashMap<String, String>> executeQueryNVMPSql(String aim) {
		Session session =  getConn2();
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
	
	public boolean updateInfo(String[] sql) throws Exception {
		boolean result = false;
		Session session = getConn2();
		String errorSql="";
		String errorInfo="";
		Transaction tran = null;
		try {
			// 设置回滚点
			tran = session.beginTransaction();
			if (sql.length > 0) {
				for (int i = 0; i < sql.length; i++) {
					errorSql=sql[i];
					Query query=session.createSQLQuery(sql[i]);
					query.executeUpdate();
				}
			}
			tran.commit();
			result = true;
		} catch (Exception e) {
			try {
				HibernateNVMPSessionFactory.closeSession();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logger.info("the error sql :");
				logger.info(errorSql);
				logger.info(" insert error:  ");
				logger.info(e1);
			    errorInfo="the error sql :"+errorSql+" insert error:  "+e1;
				throw new Exception(errorInfo);
			}
			result = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("the error sql :");
			logger.info(errorSql);
			logger.info(" insert error:  ");
			logger.info(e);
		    errorInfo="the error sql :"+errorSql+" insert error:  "+e;
			throw new Exception(errorInfo);
		}finally{
			
			if(session!=null){
				HibernateNVMPSessionFactory.closeSession();
			}
		}
		return result;
	}

	
	public List<HashMap<String, String>> executeQuerySql(String aim) {
		Session session =  getConn();
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
				HibernateJmsServerSessionFactory.closeSession();
			}
		}
		return result;
}

	
	/**
	 * 获取mysql数据库IP
	 * @return
	 */
	private String getDBIP(){
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if(f.exists()){
			try{
				SAXBuilder builder = new SAXBuilder();
				Document doc =  builder.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ip;
	}
	
	
	/**
	 * 获取数据库类型
	 * 
	 * @return
	 */
	public static String getDBType() {
		String path = "";
		String type = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/DB/DBSettings.xml";
		} else {
			path = "E:\\Workspaces\\SyncWebb\\conf\\DBSettings.xml";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("DBType");
				type = appE.getValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return type;
	}
	
}
 