package com.fxdigital.onvif.ctrl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RtspInfo {
	private HashMap<String,ChannelInfo> map;
	private int port;
	
	public int getPort() {
		return port;
	}

	public RtspInfo() {
		map = new HashMap<String,ChannelInfo>();
	}
	
	public void setPort(int port) {
		
		this.port = port;
	}
	
	public void addUri(String name,int id,String uri) {
		ChannelInfo ci =	map.get(name);
		if(ci == null) {
			ci = new ChannelInfo(name);
			map.put(ci.getName(), ci);
		}
		ci.addURL(id, uri);
		
	}
	
	public HashMap<String,ChannelInfo>  getRtspInfo() {
		return map;
	}
}
