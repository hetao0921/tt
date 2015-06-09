package com.fxdigital.storage.service.parameters;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
@Component
public class RtspParameter {
	


	ConcurrentMap<String,ConcurrentMap<String,AtomicInteger>> rtspMap;
	
	ConcurrentMap<String,AtomicInteger> paraMap;

	public ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>> getRtspMap() {
		if(null==rtspMap){
			rtspMap=new ConcurrentHashMap();
		}
		return rtspMap;
	}

	public void setRtspMap(
			ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>> rtspMap) {
		this.rtspMap = rtspMap;
	}

	public ConcurrentMap<String, AtomicInteger> getParaMap() {
		if(null==paraMap){
			paraMap=new ConcurrentHashMap();
		}
		return paraMap;
	}

	public void setParaMap(ConcurrentMap<String, AtomicInteger> paraMap) {
		this.paraMap = paraMap;
	}


	
	
	
	
}
