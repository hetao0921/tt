/**
 * 
 */
package com.fxdigital.analysis.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.hibernate.bean.EqualityInfotab;
import com.fxdigital.hibernate.bean.EqualitymapingInfotab;
import com.fxdigital.manage.pub.util.UDPClient;

/**
 * @author lizehua
 *
 */
@Service
public class SetService {
	private static Log logger=LogFactory.getLog(SetService.class);
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private com.fxdigital.analysis.dao.HibernateDao HibernateDao;
	@Autowired
	private SyncEquality syncEquality;
	@Autowired
	private AnalysisEqualtiy analysisEqualtiy;
	
	public HashMap<String,Object> getListCenterInfo(String type){
		 List<Map<String, Object>> centerList=HibernateDao.getAllCenter();
		 List<Map<String, Object>> equalityList=HibernateDao.getAllEqualityCenter(type);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("centerList", centerList);
		map.put("equalityList", equalityList);
		return map;
	}

	public void setQuality(String json,Notice notice){
		/*
		
		EqualityInfotab infotab=new EqualityInfotab();
		Set<EqualitymapingInfotab> equalitymapingInfotabs=new HashSet<EqualitymapingInfotab>();
		String flag=equality.getOperate();
		infotab.setType(flag);
		String enqualityId=equality.getSetId();
		infotab.setSetId(enqualityId);
		infotab.setSetCenterId(equality.getEqualityCenterId());
		List<String> list=equality.getMainCenters();
		for (String string : list) {
			EqualitymapingInfotab mapping=new EqualitymapingInfotab();
			mapping.setEqualityInfotab(infotab);
			mapping.setMapCenterId(string);
			equalitymapingInfotabs.add(mapping);
		}
		infotab.setEqualitymapingInfotabs(equalitymapingInfotabs);
		
		baseDao.save(infotab);
	*/
		syncEquality.sync(json, notice,"AnalysisEqualtiy","Equaltiy");
			analysisEqualtiy.analysis(json, "", notice);
			
			
	
	}
	
	public void deleteQuality(String setId){/*
		try {
		String sql=CreateSql.createdeleteEqualitySql(setId);
		baseDao.executeSql(sql);
	
			syncUtil.syncInsert(new String[]{sql});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("删除三位一体插入失败", e);
		}
	*/}
	
/*	public void updateQuality(Equality equality ){
		String[] sql=CreateSql.createUpdateEqualitySql(equality);
		baseDao.batchExecuteSql(sql);
	}*/
}
