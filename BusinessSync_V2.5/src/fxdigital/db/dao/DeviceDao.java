package fxdigital.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.NvmpDevice;
import com.hibernate.db.ConnDo;

/**
 * nvmp_device表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class DeviceDao{
	
	
	@SuppressWarnings("unchecked")
	public String queryForDeviceSN(String deviceType){
		String map=ConvertMapUtil.map(NvmpDevice.class);
		String sql = "select "+map+ " from NvmpDevice where deviceType='"+deviceType+"'";
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		if(list != null && list.size() == 1){
			Map<String, String> data = list.get(0);
			System.err.println("NvmpDevice "+data);
			return (String)data.get("DeviceSN");
		}else{
			return null;
		}
	}
	
}
