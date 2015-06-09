package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.bean.MqLocalcenterinfotab;
import com.fxdigital.syncclient.bean.NvmpCenterinfotab;
import com.fxdigital.syncclient.util.ArgsUtil;
import com.fxdigital.syncclient.util.ConvertMapUtil;



/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Repository
public class LocalCenterDao extends BaseDao {
	
	public String query(){
		String hql = "select new Map(centerIp as centerIp) from MqLocalcenterinfotab";
		List<HashMap<String,String>> list = executeQuery(hql);
		if(list != null && list.size() >= 1){
			return list.get(0).get("CenterIP");
		}
		return "";
	}
	
	public boolean isExsit(String centerID){
		String hql = "select new Map(centerIp as centerIp) from MqLocalcenterinfotab where centerId = '"+centerID+"'";
		List<HashMap<String,String>> list = executeQuery(hql);
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public int insert(String centerID, String centerIP){
		MqLocalcenterinfotab center = new MqLocalcenterinfotab();
		center.setCenterId(centerID);
		center.setCenterIp(centerIP);
		center.setCenterPort(61616);
		save(center);
		return 1;
		
	}
	
	public int insert(String centerID, String centerIP, int centerPort){
		MqLocalcenterinfotab center = new MqLocalcenterinfotab();
		center.setCenterId(centerID);
		center.setCenterIp(centerIP);
		center.setCenterPort(centerPort);
		save(center);
			return 1;
	}
	
	public int update(String centerID, String centerIP, int centerPort){
		String hql = "update MqLocalcenterinfotab set centerId='%s',centerIp='%s',centerPort=%d";
		hql = String.format(hql, centerID,centerIP,centerPort);
		return executeUpdate(hql);
	}
	

	
	@SuppressWarnings("unchecked")
	public LocalCenter queryInfo(){
		String map=ConvertMapUtil.assemblyMap(NvmpCenterinfotab.class);
		String sql = "select "+map+" from NvmpCenterinfotab";
		List<HashMap<String,String>> list =executeQuery(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		LocalCenter centerInfo = new LocalCenter();
		Map<String, String> data = list.get(0);
		centerInfo.setId(data.get("CenterID".toLowerCase()));
		centerInfo.setIp(data.get("CenterIP".toLowerCase()));
		centerInfo.setCenterGate(data.get("centerGate".toLowerCase()));
		centerInfo.setCenterMask(data.get("centerMask".toLowerCase()));
		centerInfo.setName((String)data.get("CenterName".toLowerCase()));
		centerInfo.setPort(Integer.parseInt(data.get("CenterPort".toLowerCase())));
		centerInfo.setSyncServerIP(data.get("SyncServerIP".toLowerCase()));
		centerInfo.setSyncServerPort(Integer.parseInt(null==data.get("SyncServerPort".toLowerCase())?"0":data.get("SyncServerPort".toLowerCase())));
		centerInfo.setSyncServerPostAddress(ArgsUtil.getPostAddress());
		return centerInfo;
	}
	
	public int updateIpInfo(String centerIp,String centerGate,String centerMask){
		String hql = "update NvmpCenterinfotab set centerIp='%s',centerGate='%s',centerMask='%s'";
		hql = String.format(hql, centerIp,centerGate,centerMask);
		executeUpdate(hql);	
		return 1;
	}
	
	public int updateSyncInfo(String centerID,String mqIP,int mqPort){
		String hql = "update NvmpCenterinfotab set syncServerIp='%s',syncServerPort=%d where centerId='%s'";
		hql = String.format(hql, mqIP,mqPort,centerID);
		executeUpdate(hql);	
		return 1;
	}

	public int update(String centerID, String centerIP){
		String hql = "update MqLocalcenterinfotab set centerId='%s',centerIp='%s'";
		hql = String.format(hql, centerID,centerIP);
		return executeUpdate(hql);
	}
	

}
