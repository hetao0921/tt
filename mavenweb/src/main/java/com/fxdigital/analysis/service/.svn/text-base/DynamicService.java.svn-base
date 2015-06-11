package com.fxdigital.analysis.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.bean.ResourceGroup;
import com.fxdigital.analysis.dao.GateResGroupDao;

@Service
public class DynamicService {
	private static Log logger=LogFactory.getLog(DynamicService.class);
	@Autowired
	private GateResGroupDao gateDao;
	@Autowired
	private AnalysisGroup analysisGroup;
	@Autowired
	private SyncEquality syncEquality;
	/**解析json
	 *  生成sql
	 *  插入数据库
	 *  通知
	 *  
	 *生成xml
	 *插入数据库
	 *  
	 *  
	 * 添加分群，并同步
	 * @param group
	 * @param groupJson
	 */
	public void  addGroup (String json,Notice notice){/*
		try {
		String[] sql =CreateSql.createInsertDynamicGroupSql(group);
		baseDao.batchExecuteSql(sql);
		String json=JSONObject.toJSONString(sql);
			String xml=JdbcToXml.assemblyIncrementXml(group.getCenterId(), json, "");
			logger.info("xml------:"+xml);
			String incrment=CreateSql.insertIncrementData(xml);
			logger.info("incrment------:"+incrment);
			baseDao.executeSql(incrment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	*/
		
		syncEquality.sync(json, notice,"AnalysisGroup","DynamicGroup");
		analysisGroup.analysis(json, "", notice);
	
		
		
	
	}
	
	public  void deleteGroup(String groupId,Notice notice){
		ResourceGroup group=new ResourceGroup();
		group.setGroupId(groupId);
		String json=JSONObject.toJSONString(group);
		syncEquality.sync(json, notice,"AnalysisGroup","Dynamic");
		analysisGroup.analysis(json, "", notice);
		
	}
	
	public List<ResourceGroup> getGroupinfo(){
		logger.info("come into getGroupinfo funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupinfo();
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupsByCenters(String[] centerids){
		logger.info("come into getGroupsByCenters funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupsByCenters(centerids);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByType(String[] grouptypes){
		logger.info("come into getGroupByType funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByType(grouptypes);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByAll(String[] centerids,String[] grouptypes){
		logger.info("come into getGroupByAll funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByAll(centerids, grouptypes);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupBygid(String[] groupids){
		logger.info("come into getGroupBygid funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupBygid(groupids);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByrid(String[] resourceids){
		logger.info("come into getGroupByrid funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByrid(resourceids);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByallrid(){
		logger.info("come into getGroupByallrid funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByallrid();
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByallrg(String[] resourceids,String[] grouptypes){
		logger.info("come into getGroupByallrg funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByallrg(resourceids, grouptypes);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByallutype(){
		logger.info("come into getGroupByallutype funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByallutype();
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByalluser(String[] userids){
		logger.info("come into getGroupByalluser funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByalluser(userids);
		return resourcegroup;
	}
	
	public List<ResourceGroup> getGroupByallutypes(String[] userids,String[] types){
		logger.info("come into getGroupByallutypes funtion!");
		List<ResourceGroup> resourcegroup=gateDao.queryGroupByallutypes(userids, types);
		return resourcegroup;
	}
}
