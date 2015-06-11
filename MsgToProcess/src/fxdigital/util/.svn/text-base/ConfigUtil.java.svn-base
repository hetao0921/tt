package fxdigital.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



/**
 * 
 * @author <h1>Hoocoln<h1>
 * @time 2013-4-2
 */
public class ConfigUtil {
	private static ConcurrentMap<String,String> map = new ConcurrentHashMap<String, String>();

	
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
