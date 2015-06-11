package com.fxdigital.analysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.CenterRelation;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.bean.RelatedCenter;
import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.manage.pub.util.UDPClient;
@Component
public class AnalysisCenterRelation implements Analysis{
	@Autowired
	private BaseDao baseDao;
	@Override
	public boolean analysis(String xml, String centerid, Notice notice) {
		boolean result=true;
		CenterRelation centerRelation=JSONObject.parseObject(xml,CenterRelation.class);
		String[] sql=null;
		String centerId=centerRelation.getCenterId();
		if(centerId==null||"".equals(centerId)){
			sql=new String[1];
			sql[0]="delete nvmp_centerrelationtab,nvmp_centerrelationmembertab from nvmp_centerrelationtab,nvmp_centerrelationmembertab where nvmp_centerrelationtab.RelationID =nvmp_centerrelationmembertab.RelationID and nvmp_centerrelationtab.RelationID='"+centerRelation.getRelationId()+"'";
		}else{
			List<RelatedCenter> list=centerRelation.getRelatedCenters();
			sql=new String[list.size()+2];
			sql[0]="delete nvmp_centerrelationtab,nvmp_centerrelationmembertab from nvmp_centerrelationtab,nvmp_centerrelationmembertab where nvmp_centerrelationtab.RelationID =nvmp_centerrelationmembertab.RelationID and nvmp_centerrelationtab.RelationID='"+centerRelation.getRelationId()+"'";
			sql[1]="insert into nvmp_centerrelationtab(RelationID,RelationType,CenterID,RelationName) values('"+centerRelation.getRelationId()+"','"+centerRelation.getRelationType()+"','"+centerRelation.getCenterId()+"','"+centerRelation.getRelationName()+"')";
			for (int i = 0; i < list.size(); i++) {
				sql[i+2]="insert into nvmp_centerrelationmembertab(RelationID,RelatedCenterID,RelationType,CenterID,RelatedCenterName) values('"+centerRelation.getRelationId()+"','"+list.get(i).getCenterId()+"','"+centerRelation.getRelationType()+"','"+centerRelation.getCenterId()+"','"+list.get(i).getCenterName()+"')";
			}
			
		}
		baseDao.batchExecuteSql(sql);
//		UDPClient.send(notice);
		return result;
	}

}
