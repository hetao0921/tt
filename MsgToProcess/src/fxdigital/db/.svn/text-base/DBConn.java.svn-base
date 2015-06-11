package fxdigital.db;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hibernate.db.HibernateSessionFactory;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

public class DBConn {
	private Logger log = Logger.getLogger(DBConn.class);
	private static DBConn db = null;
	
	private Session session;

	DBConn() {
		session = HibernateSessionFactory.getSession();
	}

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



	public void updateInfo(String hql) throws Exception {

		log.info(hql + "--");
	    session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}

	}

	public void updateNVMPInfo(String hql) throws Exception {

		log.info(hql + "--");
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try {
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}


	}
	
	
	public boolean savenvmp(Object obj){
		boolean flag=true;
		Session session=HibernateSessionFactory.getSession();
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
				HibernateSessionFactory.closeSession();
			}
		}
		return flag;
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
	

	public List<String[]> executeQuery(String hql) {

		List<String[]> list = new ArrayList<String[]>();
		Session session = HibernateSessionFactory.getSession();
		try {
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				list.add(convertBean(o));
			}	
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

	public List<String[]> executeQuery2(String hql) {

		List<String[]> list = new ArrayList<String[]>();
		Session session = HibernateSessionFactory.getSession();
		try {
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				list.add(convertBean(o));
			}
			
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

//	public List<String[]> executeQueryXml(String aim) throws SQLException {
//		Connection conn = getConn2();
//		// LinkedList<HashMap<String, String>> lnkd = new
//		// LinkedList<HashMap<String, String>>();
//		LinkedList<String[]> lnkd = new LinkedList<String[]>();
//		java.sql.Statement st = conn.createStatement();
//		ResultSet resultSet = st.executeQuery(aim);
//		ResultSetMetaData remd = resultSet.getMetaData();
//		String[] sss = new String[remd.getColumnCount()];
//		for (int i = 1; i <= remd.getColumnCount(); i++) {
//			sss[i - 1] = remd.getColumnName(i);
//		}
//		lnkd.add(sss);
//		sss = new String[remd.getColumnCount()];
//		for (int i = 1; i <= remd.getColumnCount(); i++) {
//			sss[i - 1] = remd.getColumnClassName(i);
//		}
//		lnkd.add(sss);
//		while (resultSet.next()) {
//			// HashMap<String, String> hm = new HashMap<String, String>();
//			String[] ss = new String[remd.getColumnCount()];
//			for (int i = 1; i <= remd.getColumnCount(); i++) {
//				ss[i - 1] = resultSet.getString(i);
//				// hm.put(remd.getColumnName(i), resultSet.getString(i));
//			}
//			lnkd.add(ss);
//		}
//		resultSet.close();
//		st.close();
//		return lnkd;
//	}

	// public void executeProc(String name) throws Exception{
	// CallableStatement stmt = getConn().prepareCall("{call "+name+"}");
	// stmt.setString(1, "");
	// stmt.registerOutParameter(2,Type.INTERNAL);
	// stmt.setInt(2, 0);
	// stmt.execute();
	// stmt.close();
	// }
	public boolean updateInfo(String[] sql) {
		boolean result = false;
		PreparedStatement st = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;

		try {
			tran = session.beginTransaction();
			Query query = null;
			if (sql.length > 0) {
				for (int i = 0; i < sql.length; i++) {
					query = session.createQuery(sql[i]);
					query.executeUpdate();
				}
			}

			tran.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			tran.rollback();
			result = false;
		}finally{
			if(session!=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return result;
	}

	public List<HashMap<String, String>> executeQuerySql(String hql) {
		log.info(hql);
		List<HashMap<String, String>> list =new ArrayList<HashMap<String,String>>();
		// LinkedList<String[]> lnkd = new LinkedList<String[]>();
		Session session=HibernateSessionFactory.getSession();
		try {
			list= session.createQuery(hql).list();
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

	public List<HashMap<String, Object>> executeQueryInfo(String hql) {
		log.info(hql);
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		Session session=HibernateSessionFactory.getSession();
		try {
			list= session.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	
	public List<HashMap<String, String>> executeNVMPQuerySql(String aim) throws Exception {
		Session session =  HibernateSessionFactory.getSession();
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
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return result;
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
	
	@SuppressWarnings({ "unchecked" })
	public List<Map<String, Object>> executeQueryToObjectList(String hql){
		session = HibernateSessionFactory.getSession();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try{
			Query query = session.createQuery(hql);
			List<Object> data = query.list();
			for(Object o : data){
				result.add(convertBean(o,false));
			}
		}catch(Exception e){
			e.printStackTrace();
			result = null;
			System.err.println("---------errr session");
		}finally{
			System.err.println("---------close session");
			HibernateSessionFactory.closeSession();
		}
		return result;
	}
	
	
	/**
	 * 获取mysql数据库IP
	 * 
	 * @return
	 */
	private String getDBIP() {
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			} catch (Exception e) {
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
	public String getDBType() {
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

	public static void main(String[] args) {
		DBConn d = new DBConn();
		String ip = d.getDBIP();
		System.out.println("ip:" + ip);
		System.out.println("type:" + d.getDBType());
		// DBConn d = new DBConn();
		// List<String[]> list =
		// d.executeQuery("select * from data_operate where operate = '上传'");
		// for(String[]ss :list){
		// for(String s :ss){
		// System.out.print(s+"\t");
		// }
		// System.out.println();
		// }
		// System.out.println("Connection:"+d.getConn());

//		System.out.println("url:" + d.getUrl());
		
		
		
//		List<String[]> list=DBConn.getDBConn().executeQueryXml("select * from nvmp_commanddevtab");
//	    for(int i=0;i<list.size();i++){
//	    	System.out.println(list.get(i)[0]);
//	    }

	}
}
