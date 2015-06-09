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
public class UpLoadHandler implements ImessageHandler{
	private static Log logger=LogFactory.getLog(UpLoadHandler.class);
	
	@Autowired
	private UpLoad upLoad;
	@Autowired
	private DownLoad downLoad;

	@Override
	public boolean handler(String strFlag, IMessage message) {
		Mail mail = (Mail)message;
		boolean returnflag=false;
		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
			String strContent = mail.getContent();
			DBSyncContent content = JSON.parseObject(strContent,
					DBSyncContent.class);
			String flag = content.getFlag();
			if (flag.equals(MsgType.UP_COMMAND_SC)) {
				logger.info("收到上传成功返回来的消息"+strContent);
				upLoad.processUpResult(content);
				returnflag=true;
			}
			// 处理下载初始化信息 flag=13
			if (flag.equals(MsgType.Load_INIT_SC)) {
				logger.info("接收到处理下载初始化信息 flag=13");
				downLoad.processInit(content);
			}
		}
		return returnflag;
	}
}
