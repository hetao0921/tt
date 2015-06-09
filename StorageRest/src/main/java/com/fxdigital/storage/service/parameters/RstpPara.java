package com.fxdigital.storage.service.parameters;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
@Component
public class RstpPara {

	public ConcurrentLinkedQueue<RstpPara> rstpParaQueue;
	public String baseUUId;
	public String rstpurl;
	public AtomicInteger start;
	public AtomicInteger stop;
	public AtomicInteger handler;
	
	
	
	public String getBaseUUId() {
		return baseUUId;
	}
	public void setBaseUUId(String baseUUId) {
		this.baseUUId = baseUUId;
	}
	public String getRstpurl() {
		return rstpurl;
	}
	public void setRstpurl(String rstpurl) {
		this.rstpurl = rstpurl;
	}
	public AtomicInteger getStart() {
		return start;
	}
	public void setStart(AtomicInteger start) {
		this.start = start;
	}
	public AtomicInteger getStop() {
		return stop;
	}
	public void setStop(AtomicInteger stop) {
		this.stop = stop;
	}
	public AtomicInteger getHandler() {
		return handler;
	}
	public void setHandler(AtomicInteger handler) {
		this.handler = handler;
	}
	public ConcurrentLinkedQueue<RstpPara> getRstpParaQueue() {
		if(null==rstpParaQueue){
			rstpParaQueue=new ConcurrentLinkedQueue();
		}
		return rstpParaQueue;
	}
	public void setRstpParaQueue(ConcurrentLinkedQueue<RstpPara> rstpParaQueue) {
		rstpParaQueue = rstpParaQueue;
	}
	
	

	


}
