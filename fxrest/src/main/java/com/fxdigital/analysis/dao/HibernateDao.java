package com.fxdigital.analysis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.fxdigital.analysis.bean.CenterRelation;
import com.fxdigital.analysis.bean.RelatedCenter;

@Repository
public class HibernateDao extends BaseDao{
	private Log logger=LogFactory.getLog(HibernateDao.class);
	
	/**
	 * 查询所有中心
	 */
    public List<Map<String,Object>> getAllCenter() {
    	String hql = "select new Map(centerId as centerId,centerName as centerName,centerIp as centerIp) from NvmpCenterinfosynctab" ;
    	logger.info("查询所有中心:"+hql);
    	return getAllList(hql);
    }
    
    public List<Map<String,Object>> getAllEqualityCenter(String type){
    	String hql="select new Map(ei.setId as setId,ei.setCenterId as centerId,em.mapCenterId as mapCenterId) from EqualityInfotab ei,EqualitymapingInfotab em,NvmpCenterinfotab nc where ei.setCenterId=nc.centerId and ei.type='"+type+"' and ei.setId=em.equalityInfotab.setId ";
    	logger.info("查询本中心的平级中心对应的中心:"+hql);
    	return getAllList(hql);
    }
    
    public void deleteQuality(String setId){
/*    	String hql="delete from EqualityInfotab ei,EqualitymapingInfotab em,NvmpCenterinfotab nc where  ei.setId=em.equalityInfotab.setId";
    	deleteOrUpdate(hql);*/
    	
    }
    
	public List<CenterRelation> getCenterRelationbycenterid(String[] centerIds,String relationType){
		List<CenterRelation>  list=new ArrayList<CenterRelation>();
		List<Map> listCenterrelation=new ArrayList<Map>();
		String centerId=getString(centerIds);
		String sql="select new Map(relationId as relationId,relationName as relationName,relationType as relationType,centerId as centerId,createTime as createTime)from NvmpCenterrelationtab nc where nc.centerId in "+centerId;
		
		String hql="select new Map(relationId as relationId,relatedCenterId as relatedCenterId,relatedCenterName as relatedCenterName) from NvmpCenterrelationmembertab nc where nc.centerId in "+centerId;
		if(!"all".equals(relationType.toLowerCase())){
			sql=sql+" and nc.relationType='"+relationType+"'";
			hql=hql+" and nc.relationType='"+relationType+"'";
		}
		
		
		
		listCenterrelation=getAllList(sql);
		List<Map> listStr=getAllList(hql);
		for (Map map : listCenterrelation) {
			String relationId=map.get("relationId")==null?"":map.get("relationId").toString();
			CenterRelation relation=new CenterRelation();
			relation.setCenterId(map.get("centerId")==null?"":map.get("centerId").toString());
			relation.setRelationId(map.get("relationId")==null?"":map.get("relationId").toString());
			relation.setRelationName(map.get("relationName")==null?"":map.get("relationName").toString());
			relation.setRelationType(map.get("relationType")==null?"":map.get("relationType").toString());
			relation.setCreateTime(map.get("createTime")==null?"":map.get("createTime").toString());
			List<RelatedCenter> relatedCenters=new ArrayList<RelatedCenter>(); 
			for (Map map2 : listStr) {
				String relationId2=map2.get("relationId")==null?"":map2.get("relationId").toString();
				if(relationId.equals(relationId2)){
				RelatedCenter relatedCenter=new RelatedCenter();
				relatedCenter.setCenterId(map2.get("relatedCenterId").toString());
				relatedCenter.setCenterName(map2.get("relatedCenterName")==null?"":map2.get("relatedCenterName").toString());
				relatedCenters.add(relatedCenter);
				}
			}
			relation.setRelatedCenters(relatedCenters);
			list.add(relation);
		}
		return list;
	}
	
	public List<CenterRelation> getCenterRelationbyRelationId(String[] relatedCenterIDs,String relationType){
		List<CenterRelation>  list=new ArrayList<CenterRelation>();
		List<Map> listCenterrelation=new ArrayList<Map>();
		
		String relationId=getString(relatedCenterIDs);
		String hql1="select distinct  RelationID  from nvmp_centerrelationmembertab nc where nc.RelatedCenterID in "+relationId;
		if(!"all".equals(relationType.toLowerCase())){
			hql1=hql1+" and RelationType='"+relationType+"'";
		}
		List<Object> relationIds=querySqlList(hql1);
		if(relationIds==null||relationIds.size()==0){
			return list;
		}
		relationId="(";
		for (Object object : relationIds) {
			relationId=relationId+"'"+object.toString()+"',";
			}
		relationId=relationId.substring(0,relationId.length()-1);
		relationId=relationId+")";
		
		String sql="select new Map(relationId as relationId,relationName as relationName,relationType as relationType,centerId as centerId,createTime as createTime)from NvmpCenterrelationtab nc where nc.relationId in "+relationId;
		String hql="select new Map(relationId as relationId,relatedCenterId as relatedCenterId,relatedCenterName as relatedCenterName) from NvmpCenterrelationmembertab nc where nc.relationId in "+relationId;
		listCenterrelation=getAllList(sql);
		List<Map> listStr=getAllList(hql);
		for (Map map : listCenterrelation) {
			CenterRelation relation=new CenterRelation();
			String relationId1=map.get("relationId")==null?"":map.get("relationId").toString();
			
			relation.setCenterId(map.get("centerId")==null?"":map.get("centerId").toString());
			relation.setRelationId(map.get("relationId")==null?"":map.get("relationId").toString());
			relation.setRelationName(map.get("relationName")==null?"":map.get("relationName").toString());
			relation.setRelationType(map.get("relationType")==null?"":map.get("relationType").toString());
			relation.setCreateTime(map.get("createTime")==null?"":map.get("createTime").toString());
			List<RelatedCenter> relatedCenters=new ArrayList<RelatedCenter>(); 
			for (Map map2 : listStr) {
				RelatedCenter relatedCenter=new RelatedCenter();
				String relationId2=map2.get("relationId")==null?"":map2.get("relationId").toString();
				if(relationId1.equals(relationId2)){
				relatedCenter.setCenterId(map2.get("relatedCenterId").toString());
				relatedCenter.setCenterName(map2.get("relatedCenterName")==null?"":map2.get("relatedCenterName").toString());
				relatedCenters.add(relatedCenter);
				}
			}
			relation.setRelatedCenters(relatedCenters);
			list.add(relation);
		}
		return list;
	}
	
	public String getString(String[] ids){
		String centerId=" (";
		for (String  str : ids) {
			centerId=centerId+"'"+str+"',";
		}
		centerId=centerId.substring(0,centerId.length()-1);
		centerId=centerId+")";
		return centerId;
	}
}
