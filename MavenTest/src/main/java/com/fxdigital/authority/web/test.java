/**
 * 
 */
package com.fxdigital.authority.web;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lizehua
 *
 */
public class test {
	public static void main(String[] args) {
	//	System.out.println(System.getProperty("java.endorsed.dirs")); 
  	  ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	  BeanFactory factory = (BeanFactory) context;    
	  CreateAuthData test = (CreateAuthData)factory.getBean("test");
//	  test.create(10000, 5, 1001);
	 long start=System.currentTimeMillis();
	 for (int i = 0; i < 20; i++) {
		
//		 test.queryAuth("1031", "fe007867-1040-495f-b2d3-df30a59e2b81");
		 test.queryAuth("11", "1");
	}
	  long end=System.currentTimeMillis();
	  System.out.println(end-start);
	}

}
