package fxdigital.dbsync.domains.client.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.analysis.bean.Notice;
import com.fxdigital.syncclient.analysis.bean.ResourceGroup;
import com.fxdigital.syncclient.util.JdbcImpl;
import com.fxdigital.syncclient.util.UDPClient;

public class AnalysisGroup implements Analysis {
	private static Log logger=LogFactory.getLog(AnalysisGroup.class);
	@Override
	public boolean analysis(String json,String centerid,Notice notice) {
		boolean result=true;
		try{
			ResourceGroup group=JSONObject.parseObject(json, ResourceGroup.class);
		String[] sql =CreateSql.createInsertDynamicGroupSql(group);
		result=	JdbcImpl.getJdbcImpl().excuteSql(sql);
		UDPClient.send(notice);
		}catch(Exception e){
			result=false;
			logger.error("解析动态分组错误", e);
		}
		
		return result;
	}

}
