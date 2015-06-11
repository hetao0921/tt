package com.hibernate.db;

import java.util.HashMap;

/**
 * 
 * @author hxht
 * @version 2014-10-29
 */
public class FxHashMap<K,V> extends HashMap<String,String>{
	
	private static final long serialVersionUID = 3008063792877404123L;

	public String put(String key, String value) {
		super.put(key.toLowerCase(), value);
		return super.get(key.toLowerCase());
	};
	
	@Override
	public String get(Object key) {
		return super.get(key.toString().toLowerCase());
	}
	
}
