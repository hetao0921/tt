package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import com.fxdigital.syncclient.bean.NvmpRecordMarkParameter;
import com.fxdigital.syncclient.util.ConvertMapUtil;
@Component
public class NvmpRecordMarkParameterDao  extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpRecordMarkParameterDao.class);
	public void insert(NvmpRecordMarkParameter nvmpRecordMarkParameter) {

		try {
			save(nvmpRecordMarkParameter);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}
/**
 *  (SELECT t.nvmp_mark_uuid ,
MAX(CASE parameter_key WHEN 'deviceid' THEN parameter_value ELSE 0 END ) deviceid,
MAX(CASE parameter_key WHEN 'channel' THEN parameter_value ELSE 0 END ) channel 
FROM nvmp_record_mark_parameter t
GROUP BY nvmp_mark_uuid)
 * @param parameterKey
 * @param parameterValue
 * @return
 */
	public List<Map<String, Object>> getnvmpRecordMarkParameterList(JSONObject jsonObj){
		SqlCreate sc = new SqlCreate();
		JSONObject jsonObj1 = jsonObj.getJSONObject("mark");
		String sql= sc.sqlCreate(jsonObj1);
		logger.info("search sql:"+sql);
		List<Map<String, Object>> list = executeQueryMarkList(sql);
		logger.info("RecordMarkParameter:"+list);
//		for (HashMap m : list){
//			logger.info(m);
//		}
		return list;
	}
}
