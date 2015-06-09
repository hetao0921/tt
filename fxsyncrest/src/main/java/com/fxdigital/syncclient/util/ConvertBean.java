package com.fxdigital.syncclient.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.fxdigital.syncclient.dao.DataIncrementVersionDao;

public class ConvertBean {
	private static final Logger logger = Logger.getLogger(ConvertBean.class);
	public static HashMap convertBean(Object bean,boolean toString){
        Class type = bean.getClass();
        HashMap returnMap = new FxObjectHashMap();
        try{
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
        }catch(Exception e){
        	logger.error("转换出错",e);
        }
        return returnMap;
    }

	
	public static String[] convertBean(Object bean){
        Class type = bean.getClass();
        HashMap returnMap = new FxHashMap();
        String[] temp=null;
        try{
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        temp =new String[propertyDescriptors.length];
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
       
    }catch(Exception e){
    	logger.error("转换出错",e);
    }
        return temp;
}
}