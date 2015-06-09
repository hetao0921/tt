package com.fxdigital.rest.web.manager.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fxdigital.rest.web.StopController;
import com.fxdigital.rest.web.manager.IManager;

@Component
public class ControllerManager implements IManager{
	
	private static final Logger logger = Logger.getLogger(ControllerManager.class);

	
	public ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> operateMap;

	public ConcurrentLinkedQueue<ConcurrentMap<String,String>> operateQueue;
	
	public ConcurrentMap<String,String> parameterMap;
	
	
	public ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String, String>>> getOperateMap() {
		return operateMap;
	}

	public void setOperateMap(
			ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String, String>>> operateMap) {
		this.operateMap = operateMap;
	}
	
	
	public ConcurrentLinkedQueue<ConcurrentMap<String, String>> getOperateQueue() {
		return operateQueue;
	}

	public void setOperateQueue(
			ConcurrentLinkedQueue<ConcurrentMap<String, String>> operateQueue) {
		this.operateQueue = operateQueue;
	}

	public ConcurrentMap<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(ConcurrentMap<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
	
	
	public ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> createOperateMap(){
		if(null==operateMap){
			operateMap=new ConcurrentHashMap();
		}
		return operateMap;
	}
	
	
	public ConcurrentLinkedQueue<ConcurrentMap<String,String>> createOperateQueue(){
		if(null==operateQueue){
			operateQueue=new ConcurrentLinkedQueue();
		}
		return operateQueue;
	}
	
	
	public ConcurrentMap<String,String> createParameterMap(){
		if(null==parameterMap){
			parameterMap=new ConcurrentHashMap();
		}
		return parameterMap;
	}



	public ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> putParameterMap(String operate,ConcurrentMap<String,String> parameterMap){
        if(getOperateMap()==null){
        	operateMap=createOperateMap();
        	ConcurrentLinkedQueue<ConcurrentMap<String,String>> operateQueue=createOperateQueue();
        	operateQueue.add(parameterMap);
        	operateMap.put(operate,operateQueue);
        }else{
        	ConcurrentLinkedQueue<ConcurrentMap<String,String>> operateQueue=operateMap.get(operate);
        	if(operateQueue==null||operateQueue.size()==0){
        		operateMap=createOperateMap();
        		operateQueue=new ConcurrentLinkedQueue();
        		operateQueue.add(parameterMap);
        		operateMap.put(operate,operateQueue);
        	}else{
        		operateQueue.add(parameterMap);
        	}
        }
        
        return operateMap;
	}
	
}
