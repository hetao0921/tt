package fxdigital.util;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 属性文件加载器
 * @author fucz
 * @version 2014-6-30
 */
public class ConfigUtil extends
		PropertyPlaceholderConfigurer {
	private static ConcurrentMap<String,String> map = new ConcurrentHashMap<String, String>();
	@Override
	protected void processProperties(ConfigurableListableBeanFactory arg0,
			Properties props) throws BeansException {
		super.processProperties(arg0, props);
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			map.put(keyStr, props.getProperty(keyStr));
		}
	}
	
	public static String getString(String key){
		return map.get(key);
	}
	
	public static String getString(String key,String value){
		String values = map.get(key);
		return null!=values?values:value;
	}
	
	public static Integer getInteger(String key){
		try {
			if(null==key || null==map.get(key)) return 0;
			return Integer.valueOf(map.get(key));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static Integer getInteger(String key,Integer value){
		try {
			if(null==key || null==map.get(key)) return value;
			return Integer.valueOf(map.get(key));
		} catch (Exception e) {
			return value;
		}
	}
}
