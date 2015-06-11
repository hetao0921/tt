package com.sqlite.dao;

import com.sqlite.pojo.CenterInfo;

public interface CenterInfoDao {

	public CenterInfo getCenterInfoById(String centerId);
	
	String getCenterIpByCenterId(String centerId);
} 
