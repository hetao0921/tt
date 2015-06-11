package fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fxdigital.bean.NvmpCenterinfotab;
import com.hibernate.ConvertMapUtil;
import com.hibernate.Hibernate;

import fxdigital.db.LocalCenter;
import fxdigital.util.ArgsUtil;

/**
 * nvmp_centerinfotab表
 * @author fucz
 * @version 2014-6-30
 */
@Repository
public class LocalCenterDao{
	
	@Autowired
	private Hibernate hibernate;
	
	@SuppressWarnings("unchecked")
	public LocalCenter query(){
		String map=ConvertMapUtil.map(NvmpCenterinfotab.class);
		String sql = "select "+map+" from NvmpCenterinfotab";
		List<HashMap<String,String>> list = hibernate.createQuery(sql);
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
		centerInfo.setSyncServerPort(Integer.parseInt(data.get("SyncServerPort".toLowerCase())));
		centerInfo.setSyncServerPostAddress(ArgsUtil.getPostAddress());
		return centerInfo;
	}
	
	public int update(String centerID,String mqIP,int mqPort){
		String sql = "update NvmpCenterinfotab set syncServerIp=?,syncServerPort=? where centerId=?";
		hibernate.deleteOrUpdate(sql, new Object[]{mqIP,mqPort,centerID});	
		return 1;
	}
	
	public int updateIpInfo(String centerIp,String centerGate,String centerMask){
		String sql = "update NvmpCenterinfotab set centerIp=?,centerGate=?,centerMask=?";
		hibernate.deleteOrUpdate(sql, new Object[]{centerIp,centerGate,centerMask});	
		return 1;
	}
	
}
