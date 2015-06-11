package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.CenterRoute;

public interface CenterRouteDao {

	public CenterRoute getCenterRoute(String sourceId,String destId);
	
	public boolean addCenterRoute(CenterRoute center);
	
	public List<CenterRoute> getAllCenterRoute();
	public List<String> getALLDesc(String sourceId);
}
 