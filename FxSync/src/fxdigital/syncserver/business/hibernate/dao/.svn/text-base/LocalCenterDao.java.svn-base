package fxdigital.syncserver.business.hibernate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;

/**
 * nvmp_centerinfotab表
 * @author fucz
 * @version 2014-6-30
 */

public class LocalCenterDao{
	
	
	
	
	public NvmpCenterinfotab query(){
		String map=ConvertMapUtil.map(NvmpCenterinfotab.class);
		String sql = "select "+map+" from NvmpCenterinfotab";
		
		List<HashMap<String, String>> list  =NVMPHibernate.getNVMPhibernate().executeQuery(sql);
		NvmpCenterinfotab centerInfo = new NvmpCenterinfotab();
		if(list == null || list.size() != 1){
			centerInfo =  null;
		}
		Map<String, String> data = list.get(0);
		centerInfo.setCenterId((String)data.get("centerId"));
		centerInfo.setCenterIp((String)data.get("centerIp"));
		centerInfo.setCenterName((String)data.get("centerName"));
		centerInfo.setCenterPort(Integer.parseInt(data.get("centerPort")));
		centerInfo.setSyncServerIp((String)data.get("syncServerIp"));
		centerInfo.setSyncServerPort(Integer.parseInt(null==data.get("syncServerPort")?"0":data.get("SyncServerPort")));
		return centerInfo;
	}
	
	public int update(String centerID,String mqIP,int mqPort){
		String sql = "update NvmpCenterinfotab set syncServerIp=?,syncServerPort=? where centerId=?";
		NVMPHibernate.getNVMPhibernate().deleteOrUpdate(sql, new Object[]{mqIP,mqPort,centerID});
		 return 1;
	}
	
	
	public static void main(String[] args) {
		LocalCenterDao localCenterDao=new LocalCenterDao();
		NvmpCenterinfotab nvmpCenterinfotab=localCenterDao.query();
		System.out.println(nvmpCenterinfotab.getCenterId());
	}
	
}
