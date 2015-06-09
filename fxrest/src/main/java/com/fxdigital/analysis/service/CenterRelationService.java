package com.fxdigital.analysis.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.analysis.bean.CenterRelation;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.dao.HibernateDao;

@Service
public class CenterRelationService {
	private static Log logger=LogFactory.getLog(CenterRelationService.class);
	@Autowired
	private AnalysisCenterRelation Analysis;
	@Autowired
	private SyncEquality syncEquality;
	@Autowired
	private HibernateDao hibernateDao;
	public void addCenterRelation(String json,Notice notice){
		syncEquality.sync(json, notice,"AnalysisCenterRelation","CenterRelation");
			Analysis.analysis(json, "", notice);
			
	}
	
	public List<CenterRelation> getCenterRelationbycenterid(String[] centerIds,String relationType){
		List<CenterRelation> list=new ArrayList<CenterRelation>();
		list= hibernateDao.getCenterRelationbycenterid(centerIds, relationType);
		 return list;
		
	}
	
	public List<CenterRelation> getCenterRelationbyRelationId(String[] relatedCenterIDs,String relationType){
		List<CenterRelation> list=hibernateDao.getCenterRelationbyRelationId(relatedCenterIDs,relationType);
		 return list;
	}
}
