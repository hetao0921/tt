package fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.NvmpCenterinfotab;
import com.hibernate.db.ConnDo;

import fxdigital.db.LocalCenter;
import fxdigital.util.ArgsUtil;

/**
 * nvmp_centerinfotab表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class LocalCenterDao{
	
	
	
	
	public LocalCenter query(){
		String map=ConvertMapUtil.map(NvmpCenterinfotab.class);
		String sql = "select "+map+" from NvmpCenterinfotab";
		
		List<HashMap<String, String>> list  =ConnDo.getConnDo().executeQuery(sql);
		LocalCenter centerInfo = new LocalCenter();
		if(list == null || list.size() != 1){
			centerInfo =  null;
		}
		Map<String, String> data = list.get(0);
		System.err.println("NvmpCenterinfotab "+data);
		centerInfo.setId((String)data.get("centerId"));
		centerInfo.setIp((String)data.get("centerIp"));
		centerInfo.setName((String)data.get("centerName"));
		centerInfo.setPort(Integer.parseInt(data.get("centerPort")));
		centerInfo.setSyncServerIP((String)data.get("syncServerIp"));
		centerInfo.setSyncServerPort(Integer.parseInt(null==data.get("syncServerPort")?"0":data.get("SyncServerPort")));
		centerInfo.setSyncServerPostAddress(ArgsUtil.getPostAddress());
		return centerInfo;
	}
	
	public int update(String centerID,String mqIP,int mqPort){
		String sql = "update NvmpCenterinfotab set syncServerIp=?,syncServerPort=? where centerId=?";
		ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{mqIP,mqPort,centerID});
		 return 1;
	}
	
}
