package fxdigital.mqcore.util;

import fxdigital.mqcore.exchange.impl.PostConfig;

public class PostConfigBundle {
	public static PostConfig getConfig(){
		PostConfig config=new PostConfig();
//		LocalCenterDao localCenterDao=new LocalCenterDao();
//		NvmpCenterinfotab NvmpCenterinfotab=localCenterDao.query(); 
//		config.setPostIp(NvmpCenterinfotab.getSyncServerIp());
//		config.setPostPort(NvmpCenterinfotab.getSyncServerPort());
//		config.setPostChannelName(MessageChannelName.LOCAL_POST_CHANNEL);
		return config;
	}
}
