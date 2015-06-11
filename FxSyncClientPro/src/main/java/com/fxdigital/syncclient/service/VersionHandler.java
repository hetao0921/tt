package com.fxdigital.syncclient.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fxdigital.syncclient.bean.SyncVersion;
import com.fxdigital.syncclient.dao.VersionDao;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MsgType;
import fxdigital.mqcore.util.RequestType;
import fxdigital.syncserver.app.ImessageHandler;
@Component
public class VersionHandler implements ImessageHandler {
	private static Log logger=LogFactory.getLog(VersionHandler.class);
	private VersionDao versionDao;
	
	private static 	String selfServerVersion = "-1";
	@Override
	public boolean handler(String strFlag, IMessage message) {
		boolean returnFlag=false;
		Mail mail = (Mail)message;
		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
			String strContent = mail.getContent();
			DBSyncContent content = JSON.parseObject(strContent,
					DBSyncContent.class);
			String flag = content.getFlag();
			if (flag.equals(MsgType.UP_INIT_SC)) {
				logger
						.info( "收到上传操作的消息：" + strContent);
				logger.info("接收到处理上传初始化信息 flag=11");
				selfServerVersion=String.valueOf(content.getVersion());
				
				//处理
		/*		SyncVersion version=versionDao.getVersion();
				version.setServerVersion(content.getVersion());
				versionDao.save(version);*/
				returnFlag=true;
			}
		}
		return returnFlag;
	}
	
	



	public static String getServerVersion(){
		String flag=selfServerVersion;
		selfServerVersion="-1";
		return flag;
	}

}
