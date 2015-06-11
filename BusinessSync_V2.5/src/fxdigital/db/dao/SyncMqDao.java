package fxdigital.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.ConvertMapUtil;
import com.hibernate.bean.SyncSupapply;
import com.hibernate.db.ConnDo;

import fxdigital.db.SyncMq;
import fxdigital.util.ArgsUtil;

/**
 * 同步MQ表
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class SyncMqDao{

	
	@SuppressWarnings("unchecked")
	public SyncMq query(){
		String map=ConvertMapUtil.map(SyncSupapply.class);
		String sql = "select "+map+" from CenterMqserverinfo";
		List<Map<String, Object>> list  =ConnDo.getConnDo().executeQueryToObject(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		SyncMq syncMq = new SyncMq();
		Map<String, Object> data = (Map<String, Object>)list.get(0);
		
		syncMq.setIp((String)data.get("mqip"));
		syncMq.setPort((Integer)data.get("mqport"));
		syncMq.setPostAddress(ArgsUtil.getPostAddress());
		return syncMq;
	}
	
}
