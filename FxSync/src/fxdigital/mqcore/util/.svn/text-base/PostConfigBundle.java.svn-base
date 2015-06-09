package fxdigital.mqcore.util;

import fxdigital.mqcore.exchange.impl.PostConfig;
import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;
import fxdigital.syncserver.business.hibernate.dao.LocalCenterDao;
import fxdigital.util.MessageChannelName;

public class PostConfigBundle {
	public static PostConfig getConfig(){
		PostConfig config=new PostConfig();
		LocalCenterDao localCenterDao=new LocalCenterDao();
		NvmpCenterinfotab NvmpCenterinfotab=localCenterDao.query(); 
		config.setPostIp(NvmpCenterinfotab.getSyncServerIp());
		config.setPostPort(NvmpCenterinfotab.getSyncServerPort());
		config.setPostChannelName(MessageChannelName.LOCAL_POST_CHANNEL);
		return config;
	}
}
