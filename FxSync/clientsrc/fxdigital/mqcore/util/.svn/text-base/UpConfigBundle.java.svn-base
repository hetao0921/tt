package fxdigital.mqcore.util;

import fxdigital.mqcore.exchange.impl.UpConfig;
import fxdigital.syncserver.business.hibernate.bean.SyncUpnetworkinfo;
import fxdigital.syncserver.business.hibernate.dao.SyncUpnetworkinfoDao;
import fxdigital.util.MessageChannelName;

public class UpConfigBundle {
	public static UpConfig getConfig(){
		UpConfig config=new UpConfig();
		SyncUpnetworkinfoDao syncUpnetworkinfoDao=new SyncUpnetworkinfoDao(); 
		SyncUpnetworkinfo syncUpnetworkinfo=syncUpnetworkinfoDao.query();
		if(null!=syncUpnetworkinfo){
			config.setUpIp(syncUpnetworkinfo.getSupIp());
			config.setUpPort(syncUpnetworkinfo.getSupPort());	
			config.setFilterString(syncUpnetworkinfo.getSubId());
		}
		config.setUpChannelName(MessageChannelName.UP_SERVER_CHANNEL);
		return config;
	}
}
