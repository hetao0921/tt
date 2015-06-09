package com.hibernate.db;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class StudentOperate {
	private String DBIP;
    //在Hibernate中，所有的操作都是通过Session完成
    //此Session不同于JSP的Session
    private Session session=null;
    //在构造方法之中实例化session对象
    public StudentOperate(){
/*       // 找到Hibernate配置
       Configuration config=new Configuration().configure();
       //从配置中取出SessionFactory
       SessionFactory factory=config.buildSessionFactory();
       //从SessionFactory中取出一个Session
       this.session=factory.openSession();*/
    	
//    	Configuration config = new Configuration().addFile("hibernate.cfg.xml");
//    	DBIP=getDBIP();
    	
    	
//    	Properties property=new Properties();
//    	property.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//    	property.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/nvmp");
//    	property.setProperty("hibernate.connection.username", "admin");
//    	property.setProperty("hibernate.connection.password", "111");
//    	property.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//    	property.setProperty("c3p0.min_size", "5");
//    	property.setProperty("c3p0._max_size", "20");
//    	property.setProperty("c3p0.timeout", "180");
//    	property.setProperty("c3p0.max_statements", "");
//    	property.setProperty("hibernate.show_sql", "true");
//    	property.setProperty("hibernate.format_sql", "true");
//    	
//       Configuration config=new Configuration().configure();
//      SessionFactory factory=config.buildSessionFactory();
//       //从SessionFactory中取出一个Session
       this.session=HibernateJmsClientSessionFactory.getSession();
       
    }

   

    //所有操作都是由session进行的
    //向数据库中增加数据
//    public void insert(NvmpCenterinfotab student){
//       //开始事务
//       Transaction tran=this.session.beginTransaction();
//
//       //执行语句
//       this.session.save(student);
//        SQLQuery query= session.createSQLQuery("");
//    List<NvmpCenterinfotab> list=   session.getNamedQuery("queryCenterInfo").list();
//    for (NvmpCenterinfotab nvmpCenterinfotab : list) {
//    	int id=nvmpCenterinfotab.getId();
//    	System.out.println(id);
//	}
//       //提交事务
//       tran.commit();
//    }
    
    public void exePro(){
//    	session.createSQLQuery(queryString);
    }
//    public void query(){
//    	
////    	   List<NvmpCenterinfotab> list=   session.getNamedQuery("queryCenterInfo").list();
//    	NvmpCenterinfotab center=	(NvmpCenterinfotab) session.get(NvmpCenterinfotab.class, 1);
//    	System.out.println(center.getCenterId());
//    	
////    	String hql="select nv.* from nvmp_centerinfotab nv";
////    	  Query q=session.createSQLQuery(hql).addEntity("nv",NvmpCenterinfotab.class);
////    	  List<NvmpCenterinfotab>   list=q.list();
//    	  
//    	  
////        for (NvmpCenterinfotab nvmpCenterinfotab : list) {
////      	int id=nvmpCenterinfotab.getId();
////      	System.out.println("------"+id);
////  	}
//    }
    
//    public void queryBySQL(){
//    	    	String hql="select {nv.*}  from nvmp_centerinfotab nv where 1=1";
// 	  Query q=session.createSQLQuery(hql).addEntity("nv",NvmpCenterinfotab.class);
//  	  List<NvmpCenterinfotab>   list=q.list();
//  	  
//  	  
//      for (NvmpCenterinfotab nvmpCenterinfotab : list) {
//    	int id=nvmpCenterinfotab.getId();
//    	System.out.println("------"+id);
//	}
//   }
   
    //数组
    public void queryList(){
    	String sql="select nv.*  from nvmp_centerinfotab nv where 1=1";
    	List list=session.createSQLQuery(sql).list();
    	for (Object object : list) {
			
		}
    }
    
    
//    public void queryByNamedQuery(){
//    	List<NvmpCenterinfotab> list=   session.getNamedQuery("queryCenterInfo").list();
//        for (NvmpCenterinfotab nvmpCenterinfotab : list) {
//        	int id=nvmpCenterinfotab.getId();
//        	System.out.println("------"+id);
//    	}
//    }
    
    public void queryByHQL(){
  List<Map<String,String>> list=   session.getNamedQuery("Info").list();
  for (Map objects : list) {
	System.out.println(objects);
}
/*  for (NvmpCenterinfotab nvmpCenterinfotab : list) {
  	int id=nvmpCenterinfotab.getId();
  	System.out.println(id);
	}*/
    }
    //map
    public void queryMapList(){
    	String sql="select nv.*  from nvmp_centerinfotab nv where 1=1";
    List<Map> list=	session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    for (Map map : list) {
		System.out.println(map.get("CenterID"));
	}
    
    
    }
    
    public static void main(String[] args) {
//    	NvmpCenterinfotab center=new NvmpCenterinfotab();
//    	center.setCenterId("1232");
//    	center.setCenterName("--");
//    	center.setCenterIp("192.168.1.24");
    	StudentOperate op=new StudentOperate();
//    	op.insert(center);
//    	op.queryByNamedQuery();
    	op.queryByHQL();
//    	op.queryBySQL();
//    	op.queryList();
//    	op.queryMapList();
    	
//    	op.queryByHQL();
	}
    
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
	
	
}
