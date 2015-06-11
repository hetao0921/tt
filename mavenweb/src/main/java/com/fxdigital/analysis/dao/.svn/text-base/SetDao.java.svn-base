/**
 * 
 */
package com.fxdigital.analysis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * @author lizehua
 *
 */
@Service
public class SetDao {/*
	  private Log logger = LogFactory.getLog(SetDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	*//**
	 * 查询所有跟本中心同级的服务器ID
	 * @return
	 *//*
	public List<Map<String,Object>> getListCenterInfo(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		String sql ="select distinct CenterID,NetWorkNodeIP from nvmp_centernetworkinfotab where NetWorkNodeID =(select distinct nn.NetWorkNodeID from nvmp_centerinfotab nc ,nvmp_centernetworkinfotab nn where nc.CenterID=nn.CenterID)";
		logger.info("查询所有跟本中心同级的服务器ID"+sql);
		list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	*//**
	 * 查询平级中心
	 *//*
	
	public List<Map<String,Object>> getEqualityCenterInfo(String EqualityCenterID){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		String sql ="select ne.EqualityCenterID EqualityCenterID,ne.MainCenterID MainCenterID from nvmp_equalitycenter ne,nvmp_centerinfotab nc where ne.LocalhostCenter=nc.CenterID ";
		if(!"".equals(EqualityCenterID)&&EqualityCenterID!=null){
			sql="  and ne.EqualityCenterID='"+EqualityCenterID;
		}
		logger.info("查询所有跟本中心同级的服务器ID"+sql);
		list=jdbcTemplate.queryForList(sql);
		return list;
	}
	
	public void juadge(String CenterID){
		
	}
	
	

*/}
