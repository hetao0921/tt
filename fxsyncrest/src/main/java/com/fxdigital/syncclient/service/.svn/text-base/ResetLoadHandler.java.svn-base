package com.fxdigital.syncclient.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MsgType;
import fxdigital.mqcore.util.RequestType;
import fxdigital.syncserver.app.ImessageHandler;

@Service
public class ResetLoadHandler implements ImessageHandler{
	private static Log logger=LogFactory.getLog(ResetLoadHandler.class);
	@Autowired
	private ResetLoad resetLoad;
	
	@Override
	public boolean handler(String strFlag, IMessage message) {
		Mail mail = (Mail)message;
		boolean returnflag=false;
		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
			String strContent = mail.getContent();
			DBSyncContent content = JSON.parseObject(strContent,
					DBSyncContent.class);
			String flag = content.getFlag();
			if (flag.equals(MsgType.Load_RESET_SECOND_SC)) {
				logger.info("收到还原命令返回来消息"+strContent);
				//upLoad.processUpResult(content);
				resetLoad.processResetData(content);
				returnflag=true;
			}
		}
		return returnflag;
	}

}
