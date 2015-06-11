package com.hxht.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Element;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-11
 */
public class XmlUtil {

	/**
	 * 解析XML
	 * 根据类的属性来解析XML {@link #parse(Class, Element)}}
	 * @see	hxht.util.XmlUtil#parse
	 * @param clazz
	 * @param element
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public static <T> T parse2(Class<T> clazz, Element element) throws Exception {
		T obj = clazz.newInstance();
		Field[] fs = clazz.getDeclaredFields();
		for (Field field : fs) {
			String value = element.elementText(field.getName());
			if (null == value)
				continue;
			Class type = field.getType();
			Method m = clazz.getMethod(getSet(field.getName()), type);
			if (String.class == type) {
				m.invoke(obj, value);
			} else if (Integer.class == type || int.class == type) {
				m.invoke(obj, new Integer(value));
			} else if (Long.class == type || long.class == type) {
				m.invoke(obj, new Long(value));
			} else if (Date.class == type) {
				m.invoke(obj, parseDate(value));
			} else {
				throw new RuntimeException("Unsupport data type.");
			}
		}
		return obj;
	}

	/**
	 * 解析XML
	 * 根据XML的节点来设置值
	 * @param clazz
	 * @param element
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T parse(Class<T> clazz, Element element) throws Exception {
		T obj = clazz.newInstance();
		List<Element> eles = element.elements();
		for (Element ele : eles) {
			String name = ele.getName();
			String value = ele.getText();
			if (null == value)
				continue;
			Field f = clazz.getDeclaredField(name);
			Class type = f.getType();
			Method m = clazz.getMethod(getSet(name), type);
			if (String.class == type) {
				m.invoke(obj, value);
			} else if (Integer.class == type || int.class == type) {
				m.invoke(obj, new Integer(value));
			} else if (Long.class == type || long.class == type) {
				m.invoke(obj, new Long(value));
			} else if (Date.class == type) {
				m.invoke(obj, parseDate(value));
			} else {
				throw new RuntimeException("Unsupport data type.");
			}
		}
		return obj;
	}
	
	private static Date parseDate(String str) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str.replace('T', ' ').replace("+08:00", ""));
	}
	
	private static String getSet(String name) {
		return "set" + Character.toUpperCase(name.charAt(0))
				+ name.substring(1);
	}
}
