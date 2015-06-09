package com.fxdigital.analysis.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.bean.ResourceGroup;
import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.manage.pub.util.UDPClient;
@Component
public class AnalysisGroup implements Analysis {
	private static Log logger=LogFactory.getLog(AnalysisGroup.class);
	@Autowired
	private BaseDao baseDao;
	@Override
	public boolean analysis(String json,String centerid,Notice notice) {
		boolean result=true;
	
			ResourceGroup group=JSONObject.parseObject(json, ResourceGroup.class);
		String[] sql =CreateSql.createInsertDynamicGroupSql(group);
		baseDao.batchExecuteSql(sql);
	notice.setGroupId(group.getGroupId());
		UDPClient.send(notice);
	
		
		return result;
	}

}
