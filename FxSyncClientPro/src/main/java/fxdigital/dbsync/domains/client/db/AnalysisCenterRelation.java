package fxdigital.dbsync.domains.client.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.analysis.bean.CenterRelation;
import com.fxdigital.syncclient.analysis.bean.Notice;
import com.fxdigital.syncclient.analysis.bean.RelatedCenter;
import com.fxdigital.syncclient.util.JdbcImpl;



public class AnalysisCenterRelation implements Analysis{
	private static Log logger=LogFactory.getLog(AnalysisCenterRelation.class);
	@Override
	public boolean analysis(String xml, String centerid, Notice notice) {
		boolean result=true;
		try {
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
		
			result=	JdbcImpl.getJdbcImpl().excuteSql(sql);
		} catch (Exception e) {
			logger.error("解析中心关系错误", e);
			result=false;
		}
//		UDPClient.send(notice);
		return result;
	}

}
