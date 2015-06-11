package com.fxdigital.syncclient.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.DataOperateDao;
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
public class UpLoad {

	private static Logger logger = Logger.getLogger(UpLoad.class);

	@Autowired
	private DataSelfSourceDao dataSelfSourceDao;
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private DataOperateDao dataOperateDao;
	@Autowired
	private DataOperateRecordDao dataOperateRecordDao;
	
	

	String selfServerVersion = "-1";
	String nextVersion = "-1";
	String selfVersion = "-1";
	
	String upBackState = "-1";
	int upState = -1;
	String loadBackState="-1";

	/**
	 * 获取版本信息 当前版本，服务器端版本，上传后版本 上传初始化
	 * 
	 * @return
	 */
	public List<String> getUpVersionInfo() {
		LocalCenter centerInfo = localCenterDao.queryInfo();
		selfServerVersion = "-1";
		nextVersion = "-1";
		// 获取本机版本
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> selfList = dataSelfSourceDao
				.getAllSelfSource();

		if (selfList.size() > 0 && null != selfList) {
			selfVersion = (null == selfList.get(0).get("version")) ? "1"
					: selfList.get(0).get("version");
		} else {
			selfVersion = "1";
		}
		list.add(selfVersion);
		// 获取服务器上版本
		list.add(selfServerVersion);
		DBSyncContent content = new DBSyncContent();
		String centerid = null;
		String centername = null;
		String centerip = null;
		if (null != centerInfo) {
			centerid = centerInfo.getId();
			centername = centerInfo.getName();
			centerip = centerInfo.getIp();
		}
		content.setSender(centerid);
		content.setCenterid(centerid);

		content.setCentername(centername);
		content.setCenterip(centerip);

		// 处理上传初始化信息 flag=10
		content.setFlag(MsgType.UP_INIT_CS);

		String json = JSON.toJSONString(content);
		logger.info("getUpVersionInfo" + "   " + "json" + "   " + json);
		Mail message = new Mail();
		message.setContent(json);
		message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
		if (AppServer.getInstance().send(message)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送获取版本号成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}
		// 计算下一个版本
		int selfNextVersion = 0;
		list.add(String.valueOf(selfNextVersion));

		return list;
	}

	/**
	 * 得到服务器端数据版本
	 * 
	 * @return
	 */
	public String getNextVersion() {
		int selfNextVersion = 0;
		
		logger.info("selfVersion   " + selfVersion);
		logger.info("selfServerVersion   " + selfServerVersion);
		selfNextVersion = Integer.valueOf(selfVersion) > Integer
				.valueOf(selfServerVersion) ? Integer.valueOf(selfVersion) + 1
				: Integer.valueOf(selfServerVersion) + 1;

		nextVersion = String.valueOf(selfNextVersion);
		logger.info("nextVersion    " + nextVersion);
		return nextVersion;
	}
	
	
	public List<String> getUpVersionList(){
		List<String> list=null;
		selfServerVersion = "-1";
		selfServerVersion=getUpVersion();
		logger.info("getUpVersionList    " + selfServerVersion);
		if(!selfServerVersion.equals("-1")){
			list =new ArrayList<String>(); 
			String nextVersion=getNextVersion();
			list.add(selfVersion);
			list.add(selfServerVersion);
			list.add(nextVersion);
		}
		return list;
	}
	
	public String getUpVersion(){
		selfServerVersion = "-1";
		nextVersion = "-1";
		int count=20;
		int i=0;
		while(i<count){
			selfServerVersion=VersionHandler.getServerVersion();
			logger.info("getUpVersion    " + selfServerVersion);
			if(selfServerVersion.equals("-1")){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}else{
				break;
			}
		}
		return selfServerVersion;
	}
	
	/**
	 * 发送上传命令 返回值: flag 2上传锁锁住 3上传锁没锁 0客户端不是最新版本 1客户端是最新版本
	 * 
	 * 记录上传过程中的状态 upState 2上传锁锁住 3上传锁没锁0客户端不是最新版本
	 * 1客户端是最新版本4正在组装xml5组装出错6正在发送7发送成功8发送失败9上传成功
	 * 
	 * @return
	 */
	public String sendUpCommand(List<String> strlist) {
		LocalCenter centerInfo = localCenterDao.queryInfo();
		logger.info("开始发送!");
		logger.info("strlist" + strlist);
		String flag = "-1";
		upBackState = "-1";
		upState = -1;
		String errorinfo = "";
		String operateinfo = "";
		loadBackState = "-1";
		String operate = "上传";
		logger.info("SendUpCommand" + "   " + "strlist" + "   " + strlist);
		String centerid = null;
		String centername = null;
		String centerip = null;
		int id = dataOperateRecordDao.getMaxId();
		String localSelfVersion = null;
		String remoteServerVersion = null;
		String nextSelfVersion = null;

		if (null != centerInfo) {
			centerid = centerInfo.getId();
			centername = centerInfo.getName();
			centerip = centerInfo.getIp();
		}
		HashMap<String, String> UpLock = getUpMsg();
		if (UpLock.get("flag").equals("1")) {
			// 上传被锁
			flag = "2";
			errorinfo = "上传被锁";
			operateinfo = centerid + ":" + Integer.valueOf(strlist.get(2))
					+ ":" + "上传被锁;";
			recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
		} else {
			// 可以上传
			flag = "3";
			if (strlist.size() > 0) {
				localSelfVersion = strlist.get(0);
				remoteServerVersion = strlist.get(1);
				nextSelfVersion = strlist.get(2);
				// 获取本机版本
				List<HashMap<String, String>> selfList = dataSelfSourceDao
						.getAllSelfSource();
				String localRealVersion = "-1";
				if (selfList.size() > 0) {
					localRealVersion = selfList.get(0).get("version");
				} else {
					localRealVersion = "0";
					// 修改本地资源记录
					dataSelfSourceDao.recordAllStates(centerid,
							centername, nextSelfVersion, upState);
				}

				if (Integer.valueOf(localSelfVersion) < Integer
						.valueOf(localRealVersion)) {
					flag = "0";
					errorinfo = "客户端版本号不是最新";
					operateinfo = centerid + ":"
							+ Integer.valueOf(nextSelfVersion) + ":"
							+ "版本不是最新;";
					recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
				} else {
					flag = "1";
					// 修改上传锁状态
					upLock();
					dataSelfSourceDao.deleteAllSelfSource();
					upState = 0;
					dataSelfSourceDao.recordAllStates(centerid,
							centername, nextSelfVersion, upState);
					// 获取本地同步数据
					String xml = null;
					try {
						upState = 4;
						xml = JdbcToXml.getInstance().writeTheXml(centerid);
						dataSelfSourceDao.recordAllStates(
								centerid, centername, nextSelfVersion, upState);
					} catch (SQLException e) {
						e.printStackTrace();
						upState = 5;
						upBackState = "1";
						errorinfo = "打包失败，失败原因" + e;
						dataSelfSourceDao.recordAllStates(
								centerid, centername, nextSelfVersion, upState);
						dataSelfSourceDao.updateVersion(centerid,
								Integer.valueOf(localRealVersion));
						return flag;
					}
					DBSyncContent content = new DBSyncContent();
					content.setStrlist(strlist);
					content.setXml(xml);
					content.setSender(centerid);
					content.setCenterid(centerid);

					content.setCentername(centername);
					content.setCenterip(centerip);
					// 发送标记 flag=0
					content.setFlag(MsgType.UP_COMMAND_CS);
					
				

					upState = 6;
					dataSelfSourceDao.recordAllStates(centerid,
							centername, nextSelfVersion, upState);

					operateinfo = centerid + ":"
							+ Integer.valueOf(nextSelfVersion) + ":" + "正在发送;";
					
					recordOperate(id, centerip, operate, centerid, operateinfo, centername, errorinfo);
					id = dataOperateRecordDao.getMaxId();
					content.setOperateid(id);
					String json = JSON.toJSONString(content);
					logger.info("SendUpCommand" + "   " + "json" + "   " + json);
					Mail message = new Mail();
					message.setContent(json);
					message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
					if (AppServer.getInstance().send(message)) {
						// log.info("发送成功！发送内容："+content);
						// 上传成功
						upState = 7;
						logger.info("发送成功！发送内容：" + content);
						dataSelfSourceDao.recordAllStates(
								centerid, centername, nextSelfVersion, upState);
					} else {
						// log.error("发送失败！发送内容："+content);
						// 上传失败
						upState = 8;
						upBackState = "1";
						errorinfo = "发送失败";
						logger.info("发送失败！发送内容：" + content);
						dataSelfSourceDao.recordAllStates(
								centerid, centername, nextSelfVersion, upState);
						dataSelfSourceDao.updateVersion(centerid,
								Integer.valueOf(localRealVersion));
					}

				}
			}
		}
		return flag;
	}
	
	
	/**
	 * 获取上传锁信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getUpMsg() {
		String operate = "上传";
		return dataOperateDao.getMsg(operate);
	}
	
	
	/**
	 * 给上传上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void upLock() {
		String operate = "上传";
		dataOperateDao.lockOne(operate);
	}

	
	
	public void processUpResult(DBSyncContent recontent){
		logger.info("接收到处理上传命令信息 flag=16");
		logger.info("接收上传命令信息" + recontent);
		String nextSelfVersion = recontent.getStrlist().get(2);
		logger.info("下一个版本" + nextSelfVersion);
		String centerid = recontent.getCenterid();
		String centername = recontent.getCentername();
		upState = 9;
		dataSelfSourceDao.recordAllStates(centerid,
				centername, nextSelfVersion, upState);
		upBackState = recontent.getContent();
		int operateid = recontent.getOperateid();
		String errorinfo = "";
		String operateinfo = centerid + ":" + nextSelfVersion + ":上传成功;";
		logger.info("当前操作id"+operateid);
		dataOperateRecordDao.updateOperateRecord(operateid,
				centerid, operateinfo, errorinfo);
		unUpLock();
	}
	
	/**
	 * 解除上传锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unUpLock() {

		String operate = "上传";
		dataOperateDao.unLockOne(operate);

	}
	
	
	/**
	 * 获取上传是否完成反馈
	 * 
	 * @return
	 */
	public String getUpBack() {
		return upBackState;

	}
	
	
	/**
	 * 获取上传反馈 * 记录上传过程中的状态 upState 2上传锁锁住 3上传锁没锁0客户端不是最新版本 1客户端是最新版本
	 * 4正在组装xml5组装出错6正在发送7发送成功8发送失败9上传成功
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getUpResult(String uuid,
			String operatorsessionid) {
		String str = "";
		List<HashMap<String, String>> selfList;
		try {
			selfList = dataSelfSourceDao.getAllSelfSourceRecord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("getUpResult error" + e);
			return null;
		}
		List<HashMap<String, String>> returnList = new ArrayList<HashMap<String, String>>();
		if (selfList.size() > 0) {
			for (int i = 0; i < selfList.size(); i++) {

				Iterator<Entry<String, String>> iterator = selfList.get(i)
						.entrySet().iterator();
				HashMap<String, String> hashmap = new HashMap<String, String>();
				while (iterator.hasNext()) {
					Entry<String, String> entry = iterator.next();
					hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
				}

				String flag = (String) hashmap.get("flag");
				logger.info("flag" + flag);
				if (flag.equals("0")) {
					str = "正在上传";
				} else if (flag.equals("4")) {
					str = "正在打包";
				} else if (flag.equals("5")) {
					str = "打包出错";
				} else if (flag.equals("6")) {
					str = "正在发送";
				} else if (flag.equals("7")) {
					str = "发送成功";
				} else if (flag.equals("8")) {
					str = "发送失败";
				} else if (flag.equals("9")) {
					str = "上传成功";
				} else {
					str = "未知错误";
				}
				hashmap.put("state", str);
				returnList.add(hashmap);
			}

		}
		return returnList;
	}
	
	public void recordOperate(int id,String centerip,String operate,String centerid,String operateinfo,String centername,String errorinfo){
		if (id == 0) {
			dataOperateRecordDao
					.insertInitOperateRecord(id, "", "", centerip,
							operate, centerid, operateinfo,
							centername, errorinfo);
			id = dataOperateRecordDao.getMaxId();
		} else {
			id = id + 1;
			dataOperateRecordDao.insertOperateRecord(
					id, "", "", centerip, operate, centerid,
					operateinfo, centername, errorinfo);
		}
	}
}
