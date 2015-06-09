package com.fxdigital.bean;

import java.util.ArrayList;
import java.util.List;


public class Device {

	private List<Index> indexList;
	private List<Quality> qualityList;
	public Device() {
		super();
	}
	public Device(List<Index> indexList, List<Quality> qualityList) {
		super();
		this.indexList = indexList;
		this.qualityList = qualityList;
	}
	public List<Index> getIndexList() {
		if(indexList==null)
			indexList = new ArrayList<Index>();
		return indexList;
	}
	public void setIndexList(List<Index> indexList) {
		this.indexList = indexList;
	}
	public List<Quality> getQualityList() {
		if(qualityList==null)
			qualityList = new ArrayList<Quality>();
		return qualityList;
	}
	public void setQualityList(List<Quality> qualityList) {
		this.qualityList = qualityList;
	}
	public Quality getQuality(String qname){
		if(qualityList!=null){
			for(Quality q:qualityList){
				if(q.getQname().equals(qname))
					return q;
			}
		}
		return null;
	}
}
