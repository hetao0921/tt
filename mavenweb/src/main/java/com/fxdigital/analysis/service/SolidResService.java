package com.fxdigital.analysis.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.analysis.bean.Center;
import com.fxdigital.analysis.bean.Resources;
import com.fxdigital.analysis.dao.GateResourceDao;

@Service
public class SolidResService {
	private static Log logger=LogFactory.getLog(SolidResService.class);
	@Autowired
	private GateResourceDao grDao;
	public List<Resources> getResources(){
		logger.info("come into queryresources function!");
		List<Resources> resources=grDao.queryResources();
		return resources;
	}
	//根据中心查找所有资源
	public List<Resources> getResbyCenterid(String[] centerids){
		logger.info("come into queryResbyCenterid function!");
		List<Resources> resources=grDao.queryResbyCenterid(centerids);
		return resources;

	}
	//根据类型查所有资源
	public List<Resources> getResbyResType(String[] resourcetypes){
		logger.info("come into queryResbyResType function!resourcetype="+resourcetypes[0]);
		List<Resources> resources=grDao.queryResbyResType(resourcetypes);
		return resources;
	}
	//根据中心和类型查特定资源
	public List<Resources> getResbyAll(String[] centerids,String[] resourcetypes){
		logger.info("come into queryResbyAll function!resourcetype="+resourcetypes[0]);
		List<Resources> resources=grDao.queryResbyAll(centerids, resourcetypes);
		return resources;
	}
	//检查中心级联表信息
	public List<Center> getCenterNet(){
		logger.info("come into queryCenterNet function!");
		List<Center> centers=grDao.queryCenterNet();
		return centers;
	}
	//获取本级中心级联表信息
	public Center getSefCenterNet(){
		logger.info("come into queryCenterNet function!");
		Center center=grDao.querySefCenterNet();
		return center;
	}
	
}
