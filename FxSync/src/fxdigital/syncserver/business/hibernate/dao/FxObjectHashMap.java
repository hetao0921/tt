package fxdigital.syncserver.business.hibernate.dao;

import java.util.HashMap;

/**
 * 
 * @author hxht
 * @version 2014-10-29
 */
public class FxObjectHashMap<K,V> extends HashMap<String,Object>{
	
	private static final long serialVersionUID = 3008063792877404123L;

	public Object put(String key, Object value) {
		super.put(key.toLowerCase(), value);
		return super.get(key.toLowerCase());
	};
	
	@Override
	public Object get(Object key) {
		return super.get(key.toString().toLowerCase());
	}
	
}
