package com.fxdigital.onvif.ctrl;

import java.util.HashMap;
import java.util.Map;

//用来存放预置点信息集合的
public class PrePointSet {
	private Map<String,PrePoint> map;
	
	public PrePointSet() {
		map = new HashMap<String,PrePoint>();
	}
	
	
	public void addPrePoint(String preName,String handle) {
		PrePoint p = new PrePoint();
		p.prePointName = preName;
		p.handle = handle;
		map.put(p.prePointName, p);
	}
	
	
}
