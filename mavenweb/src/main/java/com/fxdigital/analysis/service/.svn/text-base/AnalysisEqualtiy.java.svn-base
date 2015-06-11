package com.fxdigital.analysis.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.manage.pub.util.UDPClient;

@Component
public class AnalysisEqualtiy implements Analysis {
	private static Log logger=LogFactory.getLog(AnalysisEqualtiy.class);
	@Autowired
	private BaseDao baseDao;
	@Override
	public boolean analysis(String json,String centerid,Notice notice) {
		boolean result=true;
		
		Equality equality=JSONObject.parseObject(json, Equality.class);
		String[] sql=null;
		String operate=equality.getOperate();
		if("1".equals(operate)||("2").equals(operate)){//1 添加和2 修改 都是先删除再插入
			 sql=new String[equality.getMainCenters().size()+2];
			 String setId=equality.getSetId();
			sql[0]="DELETE equality_infotab , equalitymaping_infotab from equality_infotab LEFT JOIN equalitymaping_infotab ON equality_infotab.setId=equalitymaping_infotab.setId and equality_infotab.setId='"+setId+"'";

			sql[1]="insert into equality_infotab(setId,setCenterId,type)values('"+equality.getSetId()+"','"+equality.getEqualityCenterId()+"','"+equality.getType()+"')";
			List<String> list=equality.getMainCenters();
			for (int i = 0; i < list.size(); i++) {
				sql[i+2]="insert into equalitymaping_infotab (setId,mapCenterId)values('"+equality.getSetId()+"','"+list.get(i)+"')";
			}
		}else{
			 sql=new String[0];
			 String setId=equality.getSetId();
				sql[0]="DELETE equality_infotab , equalitymaping_infotab from equality_infotab LEFT JOIN equalitymaping_infotab ON equality_infotab.setId=equalitymaping_infotab.setId and equality_infotab.setId='"+setId+"'";
		}
    
		baseDao.batchExecuteSql(sql);
		UDPClient.send(notice);
		
		return result;
	}

}
