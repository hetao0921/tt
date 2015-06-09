package com.fxdigital.syncclient.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.bean.DataIncrementVersion;
import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.DataIncrementVersionDao;
import com.fxdigital.syncclient.dao.DataOperateRecordDao;
import com.fxdigital.syncclient.dao.DataSelfSourceDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;

import fxdigital.dbsync.domains.client.db.JdbcToXml;
import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MessageChannelName;
import fxdigital.mqcore.util.MsgType;
import fxdigital.syncserver.app.impl.AppServer;
@Service
public class ResetLoad {
	private static Logger logger = Logger.getLogger(ResetLoad.class);
	
	@Autowired
	private UpLoad upLoad;
	@Autowired
	private DataSelfSourceDao dataSelfSourceDao;
	@Autowired
	private LocalCenterDao localCenterDao;
	
	@Autowired
	private DataOperateRecordDao dataOperateRecordDao;
	
	@Autowired
	private DataIncrementVersionDao dataIncrementVersionDao;
	
    private String resetBackState="-1";
    
    int oldResetVersion = 0;
    
    String loadState="-1";
    
	List<HashMap<String, String>> clientResetList = null;
	List<HashMap<String, String>> serverResetList = null;
	List<HashMap<String, String>> lastResetList = null;
	
	/**
	 * 获取所有还原的服务器端版本号
	 * 
	 * @return
	 */
	public String getResetVersionInfo() {
		String restBack="-1";
		resetBackState="-1";
		clientResetList = null;
		serverResetList = null;
		lastResetList = null;
		LocalCenter centerInfo = localCenterDao.queryInfo();
		// 获取本地的版本号信息
		try {
			clientResetList = dataSelfSourceDao.
					getAllSelfSourceRecord();
		} catch (Exception e) {
			logger.info("getResetVersionInfo error" + e);
			return restBack;
		}

		String centerid = null;
		String centername = null;
		String centerip = null;
		if (centerInfo!=null) {
	
			centerid = centerInfo.getId();
			centername = centerInfo.getName();
			centerip = centerInfo.getIp();
		}

		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);
		content.setSender(centerid);
		content.setCenterid(centerid);

		content.setCentername(centername);
		content.setCenterip(centerip);


		// 处理还原初始化信息 flag=12
		content.setFlag(MsgType.Load_RESET_CS);
		String json = JSON.toJSONString(content);
		logger.info("getResetVersionInfo" + "   " + "json" + "   " + json);
		Mail message = new Mail();
		message.setContent(json);
		message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
		if (AppServer.getInstance().send(message)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
			restBack="0";
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
			restBack="1";
		}
       return restBack;
	}
	
	

	/**
	 * 发送还原命令
	 * 
	 * @return
	 */
	public String sendResetCommand(String centerid, String centername,
			String version) {
		resetBackState="-1";
		String errorinfo = null;
		String operate = "还原";
		String operateinfo = "";
		String resetFlag = "-1";
		int state = 0;
		int id = dataOperateRecordDao.getMaxId();
		String centerip = null;

		oldResetVersion = dataSelfSourceDao.getCurrentVersion(
				centerid);


		DBSyncContent content = new DBSyncContent();

		content.setCenterid(centerid);
		content.setSender(centerid);
		content.setCentername(centername);
		content.setVersion(Integer.valueOf(version));

		// 发送标记 flag=5
		content.setFlag(MsgType.Load_RESET_SECOND_CS);
		operateinfo = centerid + ":" + version + ":正在发送;";

		upLoad.recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
		id = dataOperateRecordDao.getMaxId();

		content.setOperateid(id);
		// 正在发送
		state = 6;
		dataSelfSourceDao.deleteAllSelfSource();
		dataSelfSourceDao.recordAllStates(centerid, centername,
				version, state);

		logger.info("sendResetCommand" + "   " + "content" + "   " + content);
		String json = JSON.toJSONString(content);
		Mail message = new Mail();
		message.setContent(json);
		message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
		if (AppServer.getInstance().send(message)) {
			// log.info("发送成功！发送内容："+content);
			resetFlag = "0";
			state = 7;
			logger.info("发送成功！发送内容：" + content);
			dataSelfSourceDao.recordAllStates(centerid,
					centername, version, state);
		} else {
			resetFlag = "1";
			resetBackState = "1";
			state = 8;
			errorinfo = "发送失败!";
			dataSelfSourceDao.updateVersion(centerid,
					oldResetVersion);
		}

		return resetFlag;

	}
	
	
	/**
	 * 处理还原的数据
	 * 
	 * @return
	 */
	public void processResetData(DBSyncContent recontent) {
        logger.info("接收到服务器传回来的还原数据。。。");
		String centerid = recontent.getCenterid();
		String centername = recontent.getCentername();
		int id = recontent.getOperateid();
		String version = String.valueOf(recontent.getVersion());
		String xml = recontent.getXml();
		String errorinfo = "";
		String operateinfo = "";
		logger.info("processResetData   " + "centerid   " + centerid);
		logger.info("processResetData   " + "xml   " + xml);
		int state = 10;
		// 正在上传
		dataSelfSourceDao.recordAllStates(centerid, centername,
				version, state);
		if (("").equals(xml) || null == xml) {
			logger.info("xml文件为空啊。。。");
			state = 11;
			errorinfo = "xml文件为空";
			dataSelfSourceDao.recordAllStates(centerid,
					centername, version, state);
			dataSelfSourceDao.updateVersion(centerid,
					oldResetVersion);
			return;
		} else {
			logger.info("处理xml开始啦！！！");
			// xml = xml.replace("{", "<");
			// xml = xml.replace("}", ">");
			
			logger.info("xml ---" + xml);
			HashMap<String, String> map = JSONObject.parseObject(xml,
					HashMap.class);
			logger.info("map ---" + map);
			String oldXml = map.get("oldXml");
			String newXml = map.get("newXml");
			String incrementXml = map.get("incrementXml");
			logger.info("oldXml ---" + oldXml);
			logger.info("newXml ---" + newXml);
			logger.info("incrementXml ---" + incrementXml);
			List<JSONObject> incrementList = JSONObject.parseObject(
					incrementXml, List.class);

			state = 14;
			dataSelfSourceDao.recordAllStates(centerid,
					centername, version, state);
			boolean newResult=false;
			if(null==newXml&&("").equals(newXml)){
				logger.info("newXml返回结果值为空：" + newXml);
				state = 15;
				dataSelfSourceDao.recordAllStates(centerid,
						centername, version, state);
				operateinfo = centerid + ":" + version + ":" + "还原成功" + ";";
				state = 17;
				resetBackState = "0";
				dataSelfSourceDao.recordAllStates(centerid, centername,
						version, state);
				dataOperateRecordDao.updateOperateRecord(id, centerid,
						operateinfo, errorinfo);
			}else{
				logger.info("newXml开始执行" + oldXml);
				try {
					newResult = JdbcToXml.getInstance().xmlToInsert(newXml, centerid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorinfo=e.toString();
				}
				logger.info("newXml数据全部执行完毕,返回结果：" + newResult);
				if(newResult){
					state = 15;
					dataSelfSourceDao.recordAllStates(centerid,
							centername, version, state);
					operateinfo = centerid + ":" + version + ":" + "还原成功" + ";";
					state = 17;
					resetBackState = "0";
					dataSelfSourceDao.recordAllStates(centerid, centername,
							version, state);
					dataOperateRecordDao.updateOperateRecord(id, centerid,
							operateinfo, errorinfo);
				}else{
					resetBackState = "1";
					state = 16;
					// errorinfo="插入失败";
					 operateinfo = centerid + ":" + version + ":" + errorinfo+
					";";
					 operateinfo.replace("'", "-");
					dataSelfSourceDao.recordAllStates(centerid,
							centername, version, state);
					dataOperateRecordDao.updateOperateRecord(id,
							centerid, operateinfo, errorinfo);
					dataSelfSourceDao.updateVersion(centerid,
							oldResetVersion);
				}
				
			}
			
	
		
            boolean execResult=false;
			if(null!=incrementList&&incrementList.size()>0){
				for(int k=0;k<incrementList.size();k++){

					logger.info("incrementList.get(k) "
							+ incrementList.get(k));
					String localcenterid = incrementList.get(k)
							.get("centerid").toString();
					String localversion = incrementList.get(k)
							.get("version").toString();
					String localcentername = incrementList.get(k)
							.get("centername").toString();
					String localcenterip = incrementList.get(k)
							.get("centerip").toString();
					String localIncrementXml = incrementList.get(k)
							.get("xml").toString();
					if (("").equals(localIncrementXml)
							|| null == localIncrementXml) {
						logger.info("localIncrementXml文件为空啊。。。");
					} else {
						logger.info("处理oldXml开始啦！！！");
						try {
							execResult = JdbcToXml.getInstance().xmlToInsert(
									localIncrementXml, centerid);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							logger.info("插入localIncrementXml失败,失败原因："+e.toString());
						}
						if (execResult) {
							logger.info("插入localIncrementXml成功。。");
							// 修改数据版本data_version
							dataIncrementVersionDao.delete(
									localcenterid);
							DataIncrementVersion dataIncrementVersion = new DataIncrementVersion();
							dataIncrementVersion.setCenterid(localcenterid);
							dataIncrementVersion.setCenterip(localcenterip);
							dataIncrementVersion
									.setCentername(localcentername);
							dataIncrementVersion.setVersion(Integer
									.valueOf(localversion));
							dataIncrementVersionDao.insert(
									dataIncrementVersion);
						} else {
							logger.info("插入localIncrementXml失败。。");
							errorinfo = "插入localIncrementXml失败。。";
							loadState = "10";
							return;
						}

					}
				
				}
			}
			
		}
		
	}
	
	
	public void processServerVersion(DBSyncContent content){
		serverResetList = content.getList();
	}

	
	/**
	 * 得到服务器端返回的还原数据版本
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLastResetVersion() {
		HashMap<String, String> clientMap = new HashMap<String, String>();
		if (null != clientResetList && clientResetList.size() > 0) {
			clientMap = clientResetList.get(0);
		}
		if (null != serverResetList && serverResetList.size() > 0) {
			for (int i = 0; i < serverResetList.size(); i++) {
				serverResetList.get(i).put(
						"clientversion",
						(clientMap.get("version") == null) ? "0" : clientMap
								.get("version"));
			}
		}
		return serverResetList;
	}
	
	
	
	/**
	 * 获取还原是否完成反馈
	 * 
	 * @return
	 */
	public String getResetBack() {
		return resetBackState;
	}
}
