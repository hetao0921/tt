package com.hibernate.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
@Component
public class LoadProperties {
	
	private  String jndiName;
	private  String dialect;
	public  LoadProperties(){
		 jndiName=ReadDBConfig.Datasource;
		 dialect=ReadDBConfig.Dialect;
		
		
		/*
		System.out.println("---------初始化配置文件");
		 HashMap<String,String> map=new HashMap<String,String>(); 
		 map.put("mysql", "c3p0.properties");
		 map.put("oscar", "c3p0-oscar.properties");
		 map.put("dm", "c3p0-DM.properties");
		String type=  ReadDBConfig.getDBParameter("DBType");
		String properties=map.get(type);
		if(properties==null){
			properties="c3p0.properties";
		}
		// 属性文件   
		// 获取当前类加载的根目录，如：/C:/Program Files/Apache/Tomcat 6.0/webapps/fee/WEB-INF/classes/  
		String path;
		FileInputStream fis = null;
		Properties props=new Properties();
		datasource=new HashMap<String, Object>();
		try {
			path = LoadProperties.class.getClassLoader().getResource("/").toURI().getPath();
			path=path.substring(0,path.lastIndexOf("classes"));
			// 把文件读入文件输入流，存入内存中  
			 fis= new FileInputStream(new File(path +properties ));     
			//加载文件流的属性     
			 props.load(fis);
				for (Object key : props.keySet()) {
					String keyStr = key.toString();
					datasource.put(keyStr, props.getProperty(keyStr));
				}
			fis.close();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}   
	
	*/}
	public String getJndiName() {
		return jndiName;
	}
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	
	

}
