package com.fxdigital.syncclient.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.DataIncrementVersionDao;
import com.fxdigital.syncclient.dao.DataNativeSourceDao;
import com.fxdigital.syncclient.dao.DataOperateDao;
import com.fxdigital.syncclient.dao.DataOperateRecordDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MessageChannelName;
import fxdigital.mqcore.util.MsgType;
import fxdigital.syncserver.app.impl.AppServer;

@Service
public class AutoGlobalUploadTask {
	private static final Logger logger = Logger
			.getLogger(AutoGlobalUploadTask.class);

	private String loadState;
	private String loadBackState;
	
	@Autowired
	private UpLoad upLoad;

	@Autowired
	private DataOperateDao dataOperateDao;
	@Autowired
	private DataNativeSourceDao dataNativeSourceDao;
	@Autowired
	private DataIncrementVersionDao dataIncrementVersionDao;
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private DataOperateRecordDao dataOperateRecordDao;
	@Autowired
	private BackupServer backupServer;
	// 发送自动下载消息
	public void run() {
		boolean isBackup=backupServer.isBackup();
		if(isBackup){
			logger.info("备用服务器，不启动自动下载。");
			return;
		}
		logger.info("开始进行自动下载");
		LocalCenter centerInfo = localCenterDao.queryInfo();
		getLoadVersionInfo(centerInfo);
	}

	/**
	 * 获取所有需要下载的本地的版本号
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadVersionInfo(
			LocalCenter centerInfo) {
		
		String flag = "-1";
		loadState = "-1";
		loadBackState = "-1";
		String operate = "下载";
		String errorinfo = null;
		int id = dataOperateRecordDao.getMaxId();
		List<HashMap<String, String>> clientLoadList = null;
		String operateinfo = "";

		HashMap<String, String> loadLock = getLoadMsg();
		String centerid = null;
		String centername = null;
		String centerip = null;
		if (null != centerInfo) {
			centerid = centerInfo.getId();
			centername = centerInfo.getName();
			centerip = centerInfo.getIp();
		}
		if (loadLock.get("flag").equals("1")) {
			logger.info("下载锁被锁住。。。");
			errorinfo = "下载锁被锁住";
			operateinfo = "::下载被锁;";
			upLoad.recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
		} else {
			flag = "1";
			// 获取本地的版本号信息
			try {
				clientLoadList = dataNativeSourceDao.getNativeSource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("getLoadVersionInfo error" + e);
				e.printStackTrace();
				return null;
			}
			DBSyncContent content = new DBSyncContent();
			List<String> strlist = new ArrayList<String>();
			strlist.add("0");
			strlist.add("2");
			strlist.add("3");
			content.setStrlist(strlist);
			loadState = "1";
			operateinfo += centerid + ":正在发送;";

			content.setSender(centerid);
			content.setCenterid(centerid);
			content.setList(clientLoadList);

			content.setCentername(centername);
			content.setCenterip(centerip);

			// 把增量的最大版本也传递过去
			List<HashMap<String, String>> incrementList = null;
			incrementList = dataIncrementVersionDao.getAllServerSource();

			content.setSecondList(incrementList);

			// 处理下载初始化信息 flag=302
			content.setFlag(MsgType.AUTO_LOAD_CS);
			upLoad.recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
			id = dataOperateRecordDao.getMaxId();
			content.setOperateid(id);
			String json = JSON.toJSONString(content);
			Mail message = new Mail();
			message.setContent(json);
			message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
			logger.info("AUTO_LOAD_CS" + "   " + "json" + "   " + json);
			if (AppServer.getInstance().send(message)) {
				logger.info("发送成功！发送内容：" + content);
				loadState = "2";
			} else {
				errorinfo = "发送失败!";
				loadBackState = "1";
				loadState = "3";
			}
		}
		return clientLoadList;
	}

	/**
	 * 获取下载锁信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getLoadMsg() {
		String operate = "下载";
		return dataOperateDao.getMsg(operate);
	}
}
