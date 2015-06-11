package com.hibernate.db;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import fxdigital.postserver.appserver.PostServer;


/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateNVMPSessionFactory {

    /** 
     * Location of hibernate.cfg.xml file.
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current session.   
     */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static org.hibernate.SessionFactory sessionFactory;
    private static final Logger log = Logger.getLogger(HibernateNVMPSessionFactory.class);
    private static Configuration configuration = new Configuration();
    private static ServiceRegistry serviceRegistry; 

	static {
    	try {
    		String path=HibernateNVMPSessionFactory.class.getResource("").getPath();
    		path=path+File.separator+"hibernate.cfg.xml";
			configuration.configure("com/hibernate/db/hibernate.cfg.xml");
			configuration.addProperties(getExtraProperties());
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
    }
    private HibernateNVMPSessionFactory() {
    }
    
    //从其他配置文件获得数据库参数
    private static Properties getExtraProperties(){
    	String url = "jdbc:"+ReadDBConfig.DBType+"://"
    					+ReadDBConfig.IP+":"+ReadDBConfig.Port+"/nvmp?useUnicode=true&characterEncoding=UTF-8";
    	Properties property=new Properties();
    	property.setProperty("hibernate.dialect", ReadDBConfig.Dialect);
    	property.setProperty("hibernate.connection.url", url);
    	property.setProperty("hibernate.connection.username", ReadDBConfig.SN);
    	property.setProperty("hibernate.connection.password", ReadDBConfig.PN);
    	property.setProperty("hibernate.connection.driver_class", ReadDBConfig.Driver);
    	return property;
    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

        return session;
    }

	/**
     *  Rebuild hibernate session factory
     *
     */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     *
     */
	public static org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
     *  return hibernate configuration
     *
     */
	public static Configuration getConfiguration() {
		return configuration;
	}

	
	public static void main(String[] args) {
		HibernateNVMPSessionFactory h=new HibernateNVMPSessionFactory();
		Properties p=h.getExtraProperties();
		System.out.println(p.getProperty("hibernate.connection.url"));
	}
	
}