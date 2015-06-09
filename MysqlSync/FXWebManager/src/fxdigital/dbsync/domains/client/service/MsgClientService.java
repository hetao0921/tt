package fxdigital.dbsync.domains.client.service;

import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hibernate.bean.DataNativeSource;

import fxdigital.client.BasicTelecomImpl;
import fxdigital.client.IMessageListener;
import fxdigital.dbsync.domains.client.dao.DataNativeRecordSourceDao;
import fxdigital.dbsync.domains.client.dao.DataNativeSourceDao;
import fxdigital.dbsync.domains.client.dao.DataOperateDao;
import fxdigital.dbsync.domains.client.dao.DataOperateRecordDao;
import fxdigital.dbsync.domains.client.dao.DataSelfSourceDao;
import fxdigital.dbsync.domains.client.dao.SyncDataInfoDao;
import fxdigital.dbsync.domains.client.db.JdbcImpl;
import fxdigital.dbsync.domains.client.db.JdbcToXml;
import fxdigital.dbsync.domains.client.pojo.DBSyncContent;
import fxdigital.dbsync.domains.client.pojo.MsgType;
import fxdigital.rpc.IContent;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * @author het 客户端消息处理类 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.service
 */
public class MsgClientService implements IMessageListener {
	private static Logger logger = Logger.getLogger(MsgClientService.class);
	private static MsgClientService msgService = null;
	private static BasicTelecomImpl bt = null;

	private MsgClientService() {
	}

	public static MsgClientService getInstance() {
		if (msgService == null) {
			msgService = new MsgClientService();
		}
		return msgService;
	}

	int oldResetVersion = 0;
	String selfVersion = "-1";
	String selfServerVersion = "-1";
	String nextVersion = "-1";
	// -1上传中，0上传成功，1上传失败
	String upBackState = "-1";
	int upState = -1;
	String loadState = "-1";
	String loadBackState = "-1";
	String resetBackState = "-1";
	List<HashMap<String, String>> clientLoadList = null;
	List<HashMap<String, String>> serverLoadList = null;
	List<HashMap<String, String>> tempLoadList = null;
	List<HashMap<String, String>> lastLoadList = null;
	List<HashMap<String, String>> clientResetList = null;
	List<HashMap<String, String>> serverResetList = null;
	List<HashMap<String, String>> lastResetList = null;
	List<HashMap<String, String>> serverLogsList = null;

	/**
	 * 获取版本信息 当前版本，服务器端版本，上传后版本 上传初始化
	 * 
	 * @return
	 */
	public List<String> getUpVersionInfo(List<Map<String, Object>> centerInfo) {

		selfServerVersion = "-1";
		nextVersion = "-1";

		// 获取本机版本
		List<String> list = new ArrayList<String>();
		List<String[]> selfList = DataSelfSourceDao.getInstance()
				.getAllSelfSource();

		if (selfList.size() > 0 && null != selfList) {
			selfVersion = (null == selfList.get(0)[3]) ? "1"
					: selfList.get(0)[3];
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
		if (centerInfo.size() > 0) {
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}

		BasicTelecomImpl bt = MsgClientService.getInstance()
				.getBasicTelecomImpl();

		content.setSender(centerid);
		content.setCenterid(centerid);

		content.setCentername(centername);
		content.setCenterip(centerip);

		// 处理上传初始化信息 flag=10
		content.setFlag(MsgType.UP_INIT_CS);

		String json = JSON.toJSONString(content);
		logger.info("SendUpCommand" + "   " + "json" + "   " + json);
		if (bt.send(NormalMode.MODE, content)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}

		// 计算下一个版本
		int selfNextVersion = 0;
		list.add(String.valueOf(selfNextVersion));
		logger.info("selfVersion" + selfVersion);
		return list;
	}

	/**
	 * 获取所有需要下载的本地的版本号
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadVersionInfo(
			List<Map<String, Object>> centerInfo) {
		clientLoadList = null;
		serverLoadList = null;
		tempLoadList = null;
		lastLoadList = null;
		// 获取本地的版本号信息
		try {
			clientLoadList = DataNativeSourceDao.getInstance()
					.getNativeSource();
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
		String centerid = null;
		String centername = null;
		String centerip = null;
		if (centerInfo.size() > 0) {
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}

		BasicTelecomImpl bt = MsgClientService.getInstance()
				.getBasicTelecomImpl();

		content.setSender(centerid);
		content.setCenterid(centerid);

		content.setCentername(centername);
		content.setCenterip(centerip);

		// 处理下载初始化信息 flag=12
		content.setFlag(MsgType.Load_INIT_CS);
		String json = JSON.toJSONString(content);
		logger.info("getLoadVersionInfo" + "   " + "json" + "   " + json);
		if (bt.send(NormalMode.MODE, content)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}
		return clientLoadList;
	}

	/**
	 * 获取所有还原的服务器端版本号
	 * 
	 * @return
	 */
	public void getResetVersionInfo(List<Map<String, Object>> centerInfo) {
		clientResetList = null;
		serverResetList = null;
		lastResetList = null;
		oldResetVersion = 0;
		// 获取本地的版本号信息
		try {
			clientResetList = DataSelfSourceDao.getInstance()
					.getAllSelfSourceRecord();
		} catch (Exception e) {
			logger.info("getResetVersionInfo error" + e);
			return;
		}

		String centerid = null;
		String centername = null;
		String centerip = null;
		if (centerInfo.size() > 0) {
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
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

		BasicTelecomImpl bt = MsgClientService.getInstance()
				.getBasicTelecomImpl();

		// 处理还原初始化信息 flag=12
		content.setFlag(MsgType.Load_RESET_CS);
		String json = JSON.toJSONString(content);
		logger.info("getResetVersionInfo" + "   " + "json" + "   " + json);
		if (bt.send(NormalMode.MODE, content)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}

	}

	/**
	 * 发送上传命令 返回值: flag 2上传锁锁住 3上传锁没锁 0客户端不是最新版本 1客户端是最新版本
	 * 
	 * 记录上传过程中的状态 upState 2上传锁锁住 3上传锁没锁0客户端不是最新版本
	 * 1客户端是最新版本4正在组装xml5组装出错6正在发送7发送成功8发送失败9上传成功
	 * 
	 * @return
	 */
	public String sendUpCommand(List<String> strlist,
			List<Map<String, Object>> centerInfo) {
		logger.info("开始发送!");
		logger.info("strlist" + strlist);
		String flag = "-1";
		upBackState = "-1";
		upState = -1;
		String errorinfo = "";
		int id = DataOperateRecordDao.getInstance().getMaxId();
		String operateinfo = "";
		loadBackState = "-1";
		String operate = "上传";
		logger.info("SendUpCommand" + "   " + "strlist" + "   " + strlist);
		String centerid = null;
		String centername = null;
		String centerip = null;

		String localSelfVersion = null;
		String remoteServerVersion = null;
		String nextSelfVersion = null;
		if (centerInfo.size() > 0) {
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}
		String[] UpLock = getUpMsg();
		if (UpLock[2].equals("1")) {
			// 上传被锁
			flag = "2";
			errorinfo = "上传被锁";

			operateinfo = centerid + ":" + Integer.valueOf(strlist.get(2))
					+ ":" + "上传被锁;";

			if (id == 0) {
				DataOperateRecordDao.getInstance().insertInitOperateRecord(id,
						"", "", centerip, operate, centerid, operateinfo,
						centername, errorinfo);
				id = DataOperateRecordDao.getInstance().getMaxId();
			} else {
				id = id + 1;
				DataOperateRecordDao.getInstance().insertOperateRecord(id, "",
						"", centerip, operate, centerid, operateinfo,
						centername, errorinfo);
			}
		} else {
			// 可以上传
			flag = "3";

			if (strlist.size() > 0) {
				localSelfVersion = strlist.get(0);
				remoteServerVersion = strlist.get(1);
				nextSelfVersion = strlist.get(2);
				// 获取本机版本
				List<String[]> selfList = DataSelfSourceDao.getInstance()
						.getAllSelfSource();
				String localRealVersion = "-1";
				if (selfList.size() > 0) {
					localRealVersion = selfList.get(0)[3];
				} else {
					localRealVersion = "0";
					// 修改本地资源记录
					DataSelfSourceDao.getInstance().recordAllStates(centerid,
							centername, nextSelfVersion, upState);
				}

				if (Integer.valueOf(localSelfVersion) < Integer
						.valueOf(localRealVersion)) {
					flag = "0";
					errorinfo = "客户端版本号不是最新";

					operateinfo = centerid + ":"
							+ Integer.valueOf(nextSelfVersion) + ":"
							+ "版本不是最新;";

					if (id == 0) {
						DataOperateRecordDao.getInstance()
								.insertInitOperateRecord(id, "", "", centerip,
										operate, centerid, operateinfo,
										centername, errorinfo);
						id = DataOperateRecordDao.getInstance().getMaxId();
					} else {
						id = id + 1;
						DataOperateRecordDao.getInstance().insertOperateRecord(
								id, "", "", centerip, operate, centerid,
								operateinfo, centername, errorinfo);
					}
				} else {
					flag = "1";

					// 修改上传锁状态
					upLock();
					DataSelfSourceDao.getInstance().deleteAllSelfSource();
					upState = 0;
					DataSelfSourceDao.getInstance().recordAllStates(centerid,
							centername, nextSelfVersion, upState);
					// 获取本地同步数据
					String xml = null;
					try {
						upState = 4;
						xml = JdbcToXml.getInstance().writeTheXml(centerid);
						DataSelfSourceDao.getInstance().recordAllStates(centerid,
								centername, nextSelfVersion, upState);
					} catch (SQLException e) {
						e.printStackTrace();
						upState = 5;
						upBackState = "1";
						errorinfo = "打包失败，失败原因" + e;
						DataSelfSourceDao.getInstance().recordAllStates(centerid,
								centername, nextSelfVersion, upState);
						DataSelfSourceDao.getInstance().updateVersion(centerid, Integer.valueOf(localRealVersion));
						return flag;
					}
				

					BasicTelecomImpl bt = MsgClientService.getInstance()
							.getBasicTelecomImpl();

					DBSyncContent content = new DBSyncContent();
					content.setStrlist(strlist);
					content.setXml(xml);
					content.setSender(centerid);
					content.setCenterid(centerid);

					content.setCentername(centername);
					content.setCenterip(centerip);

					content.setOperateid(id);
					// 发送标记 flag=0
					content.setFlag(MsgType.UP_COMMAND_CS);
					String json = JSON.toJSONString(content);
					logger.info("SendUpCommand" + "   " + "json" + "   " + json);

					upState = 6;
					DataSelfSourceDao.getInstance().recordAllStates(centerid,
							centername, nextSelfVersion, upState);

					operateinfo = centerid + ":"
							+ Integer.valueOf(nextSelfVersion) + ":" + "正在发送;";

					if (id == 0) {
						DataOperateRecordDao.getInstance()
								.insertInitOperateRecord(id, "", "", centerip,
										operate, centerid, operateinfo,
										centername, errorinfo);
						id = DataOperateRecordDao.getInstance().getMaxId();
					} else {
						id = id + 1;
						DataOperateRecordDao.getInstance().insertOperateRecord(
								id, "", "", centerip, operate, centerid,
								operateinfo, centername, errorinfo);
					}

					content.setOperateid(id);

					if (bt.send(NormalMode.MODE, content)) {
						// log.info("发送成功！发送内容："+content);
						// 上传成功
						upState = 7;
						logger.info("发送成功！发送内容：" + content);
						DataSelfSourceDao.getInstance().recordAllStates(centerid,
								centername, nextSelfVersion, upState);

					} else {
						// log.error("发送失败！发送内容："+content);
						// 上传失败
						upState = 8;
						upBackState = "1";
						errorinfo = "发送失败";
						logger.info("发送失败！发送内容：" + content);
						DataSelfSourceDao.getInstance().recordAllStates(centerid,
								centername, nextSelfVersion, upState);
						DataSelfSourceDao.getInstance().updateVersion(centerid, Integer.valueOf(localRealVersion));
					}


				}
			}
		}

		return flag;
	}

	/**
	 * 发送下载命令
	 * 
	 * @return 0 有锁 1无锁2有下载数据3无下载数据
	 */
	public String sendLoadCommand(List<HashMap<String, String>> list,
			List<Map<String, Object>> centerInfo) {
		String flag = "-1";
		loadState = "-1";
		int id = DataOperateRecordDao.getInstance().getMaxId();
		loadBackState = "-1";
		String operate = "下载";
		String errorinfo = null;
		String[] loadLock = getLoadMsg();
		String loadlockid = loadLock[0];
		String centerid = null;
		String centername = null;
		String centerip = null;
		if (centerInfo.size() > 0) {
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}
		if (loadLock[2].equals("1")) {
			flag = "0";
			errorinfo = "下载锁被锁住";

			String operateinfo = "::下载被锁;";
			if (id == 0) {
				DataOperateRecordDao.getInstance().insertInitOperateRecord(id,
						"", "", centerip, operate, centerid, operateinfo,
						centername, errorinfo);
				id = DataOperateRecordDao.getInstance().getMaxId();
			} else {
				id = id + 1;
				DataOperateRecordDao.getInstance().insertOperateRecord(id, "",
						"", centerip, operate, centerid, operateinfo,
						centername, errorinfo);
			}
			

		} else {
			flag = "1";

			List<HashMap<String, String>> loadList = null;
			if (null != list && list.size() > 0) {
				loadList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> realLoadMap = null;
				for (int i = 0; i < list.size(); i++) {
					realLoadMap = new HashMap<String, String>();
					int clientVersion = Integer.valueOf(list.get(i).get(
							"clientversion"));
					int serverVersion = Integer.valueOf(list.get(i).get(
							"serverversion"));
					if (clientVersion < serverVersion) {
						realLoadMap = list.get(i);
						loadList.add(realLoadMap);
					}
				}
			}

			if (loadList.size() > 0 && null != loadList) {

				flag = "2";
				loadState = "0";
				// 修改下载锁状态
				loadLock();
				DataNativeRecordSourceDao.getInstance().deleteLoadNativeRecord(
						loadList);
				DataNativeRecordSourceDao.getInstance().initRecordStates(
						loadList, loadState);

				BasicTelecomImpl bt = MsgClientService.getInstance()
						.getBasicTelecomImpl();

				DBSyncContent content = new DBSyncContent();

				content.setList(loadList);
				content.setSender(centerid);
				content.setCenterid(centerid);
				content.setCentername(centername);
				content.setCenterip(centerip);

				// 发送标记 flag=0
				content.setFlag(MsgType.Load_COMMAND_CS);

				String json = JSON.toJSONString(content);
				logger.info("SendLoadCommand" + "   " + "json" + "   " + json);
				loadState = "1";
				DataNativeRecordSourceDao.getInstance().initRecordStates(
						loadList, loadState);
				String operateinfo = "";
				for (int i = 0; i < loadList.size(); i++) {
					String centerId = loadList.get(i).get("centerid");
					int verSion = Integer.valueOf(null == (loadList.get(i)
							.get("serverversion")) ? "0" : loadList.get(i).get(
							"serverversion"));
					operateinfo += centerId + ":" + verSion + ":正在发送;";
				}

				if (id == 0) {
					DataOperateRecordDao.getInstance().insertInitOperateRecord(
							id, "", "", centerip, operate, centerid,
							operateinfo, centername, errorinfo);
					id = DataOperateRecordDao.getInstance().getMaxId();
				} else {
					id = id + 1;
					DataOperateRecordDao.getInstance().insertOperateRecord(id,
							"", "", centerip, operate, centerid, operateinfo,
							centername, errorinfo);
				}

				content.setOperateid(id);

				if (bt.send(NormalMode.MODE, content)) {
					// log.info("发送成功！发送内容："+content);
					logger.info("发送成功！发送内容：" + content);
					loadState = "2";
				} else {
					errorinfo = "发送失败!";
					loadBackState = "1";
					loadState = "3";
				}

				DataNativeRecordSourceDao.getInstance().initRecordStates(
						loadList, loadState);
			} else {
				flag = "3";
				logger.info(centerid + "无同步数据");
				errorinfo = "无同步数据";
				// 修改data_operate_record表
				String uuid = "";
				String sessionid = "";

				String operateinfo = "::无同步数据;";
				if (id == 0) {
					DataOperateRecordDao.getInstance().insertInitOperateRecord(
							id, "", "", centerip, operate, centerid,
							operateinfo, centername, errorinfo);
					id = DataOperateRecordDao.getInstance().getMaxId();
				} else {
					id = id + 1;
					DataOperateRecordDao.getInstance().insertOperateRecord(id,
							"", "", centerip, operate, centerid, operateinfo,
							centername, errorinfo);
				}

			}

		}

		return flag;
	}

	/**
	 * 获取下载状态命令
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadStateInfos(
			List<HashMap<String, String>> list) {
		List<HashMap<String, String>> loadList = null;
		if (null != list && list.size() > 0) {
			loadList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> realLoadMap = null;
			for (int i = 0; i < list.size(); i++) {
				realLoadMap = new HashMap<String, String>();
				int clientVersion = Integer.valueOf(list.get(i).get(
						"clientversion"));
				int serverVersion = Integer.valueOf(list.get(i).get(
						"serverversion"));
				if (clientVersion < serverVersion) {
					realLoadMap = list.get(i);
					loadList.add(realLoadMap);
				}
			}
		}
		List<HashMap<String, String>> loadStateList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> loadMap = null;
		for (int i = 0; i < loadList.size(); i++) {
			loadMap = new HashMap<String, String>();
			String centerid = loadList.get(i).get("centerid");
			List<HashMap<String, String>> stateList = null;
			try {
				stateList = DataNativeRecordSourceDao.getInstance()
						.getOneNativeRecordSource(centerid);
			} catch (Exception e) {
				logger.info("getLoadStateInfos error" + e);
				return null;
			}
			if (stateList.size() > 0 && stateList != null) {
				for (int j = 0; j < stateList.size(); j++) {
					loadMap = stateList.get(j);
					String str = getLoadState(loadMap);
					loadMap.put("state", str);
					loadStateList.add(loadMap);
				}
			}
		}
		return loadStateList;
	}

	/**
	 * 得到下载状态
	 * 
	 * @return
	 */
	public String getLoadState(HashMap<String, String> loadMap) {
		String str = null;
		String flag = loadMap.get("flag");
		// logger.info("flag" + flag);
		if (flag.equals("0")) {
			str = "正在下载";
		} else if (flag.equals("1")) {
			str = "正在发送";
		} else if (flag.equals("2")) {
			str = "发送成功";
		} else if (flag.equals("3")) {
			str = "发送失败";
		} else if (flag.equals("4")) {
			str = "接收数据包";
		} else if (flag.equals("5")) {
			str = "xml文件为空";
		} else if (flag.equals("6")) {
			str = "开始解析";
		} else if (flag.equals("7")) {
			str = "sql为空";
		} else if (flag.equals("8")) {
			str = "开始插入sql";
		} else if (flag.equals("9")) {
			str = "插入成功";
		} else if (flag.equals("10")) {
			str = "插入失败";
		} else if (flag.equals("11")) {
			str = "下载完成";
		} else {
			str = "未知信息";
		}
		return str;
	}

	/**
	 * 发送还原命令
	 * 
	 * @return
	 */
	public String sendResetCommand(String centerid, String centername,
			String version) {
		String errorinfo = null;
		String operate = "还原";
		String operateinfo = "";
		String resetFlag = "-1";
		resetBackState = "-1";
		int state = 0;
		int id = DataOperateRecordDao.getInstance().getMaxId();
		String centerip = null;

		oldResetVersion = DataSelfSourceDao.getInstance().getCurrentVersion(
				centerid);

		BasicTelecomImpl bt = MsgClientService.getInstance()
				.getBasicTelecomImpl();

		DBSyncContent content = new DBSyncContent();

		content.setCenterid(centerid);
		content.setSender(centerid);
		content.setCentername(centername);
		content.setVersion(Integer.valueOf(version));

		// 发送标记 flag=5
		content.setFlag(MsgType.Load_RESET_SECOND_CS);
		operateinfo = centerid + ":" + version + ":正在发送;";

		if (id == 0) {
			DataOperateRecordDao.getInstance().insertInitOperateRecord(id, "",
					"", centerip, operate, centerid, operateinfo, centername,
					errorinfo);
			id = DataOperateRecordDao.getInstance().getMaxId();
		} else {
			id = id + 1;
			DataOperateRecordDao.getInstance().insertOperateRecord(id, "", "",
					centerip, operate, centerid, operateinfo, centername,
					errorinfo);
		}

		content.setOperateid(id);
		// 正在发送
		state = 6;
		DataSelfSourceDao.getInstance().deleteAllSelfSource();
		DataSelfSourceDao.getInstance().recordAllStates(centerid, centername,
				version, state);

		logger.info("sendResetCommand" + "   " + "content" + "   " + content);
		if (bt.send(NormalMode.MODE, content)) {
			// log.info("发送成功！发送内容："+content);
			resetFlag = "0";
			state = 7;
			logger.info("发送成功！发送内容：" + content);
			DataSelfSourceDao.getInstance().recordAllStates(centerid,
					centername, version, state);
		} else {
			resetFlag = "1";
			resetBackState = "1";
			state = 8;
			errorinfo = "发送失败!";
			DataSelfSourceDao.getInstance().updateVersion(centerid,
					oldResetVersion);
		}

		return resetFlag;

	}

	/**
	 * 发送查看日志命令
	 * 
	 * @return
	 */
	public boolean sendViewLogsCommand(List<HashMap<String, String>> list) {
		String errorinfo = null;
		String centerid = null;
		String centername = null;
		String centerip = null;
		if (list.size() > 0) {
			BasicTelecomImpl bt = MsgClientService.getInstance()
					.getBasicTelecomImpl();

			DBSyncContent content = new DBSyncContent();
			centerid = list.get(0).get("centerid");
			centername = list.get(0).get("centername");
			centerip = list.get(0).get("centerip");
			String version = list.get(0).get("version");
			content.setCenterid(centerid);
			content.setCentername(centername);
			if (null != version && !("").equals(version)) {
				content.setVersion(Integer.valueOf(version));
			}

			// 何涛发送
			content.setSender("center@001");
			content.setCenterid("center@001");
			// 发送标记 flag=14
			content.setFlag(MsgType.VIEW_LOGS_CS);

			String json = JSON.toJSONString(content);
			logger.info("sendViewLogsCommand" + "   " + "json" + "   " + json);
			if (bt.send(NormalMode.MODE, content)) {
				// log.info("发送成功！发送内容："+content);
				logger.info("发送成功！发送内容：" + content);
			} else {
				errorinfo = "发送失败!";
			}
		} else {
			errorinfo = "无还原数据!";
		}
		// 修改data_operate_record表
		String uuid = "";
		int id = 0;
		String sessionid = "";
		String operate = "查看日志";
		String operateinfo = centerid + ":";
		DataOperateRecordDao.getInstance().insertOperateRecord(id, uuid,
				sessionid, centerip, operate, centerid, centername,
				operateinfo, errorinfo);
		return true;

	}

	/**
	 * 处理下载的数据 // 0:正在下载（非终态）1:正在发送（非终态） // 2：发送成功(非终态) 3：发送失败(非终态) //
	 * 4.正在解包（非终态） // 5:xml为空（终态）6:正在解析xml(非终态) // 7:sql为空（终态） //
	 * 8:正在插入数据（非终态）9:插入成功（终态） // 10插入失败（终态）11:下载完成（终态）
	 * 
	 * @return
	 */
	public void processLoadData(DBSyncContent recontent) {
		List<HashMap<String, String>> loadList = recontent.getList();
		int state = 0;
		String uuid = null;
		String operateinfo = "";
		String oneoperateinfo = "";
		String errorinfo = "";
		String centerId = recontent.getCenterid();
		int id = 0;
		if (loadList.size() > 0 && null != loadList) {
			for (int i = 0; i < loadList.size(); i++) {
				String centerid = loadList.get(i).get("centerid");
				String centername = loadList.get(i).get("centername");
				String centerip = loadList.get(i).get("centerip");
				String version = loadList.get(i).get("serverversion");
				String xml = loadList.get(i).get("xml");
				id = recontent.getOperateid();
				logger.info("processLoadData   " + "centerid   " + centerid);
				logger.info("processLoadData   " + "xml   " + xml);

				loadState = "4";
				int serverversion = Integer.valueOf(version);
				DataNativeRecordSourceDao.getInstance().recordAllStates(
						centerid, centername, centerip, serverversion, uuid,
						loadState);
				if (("").equals(xml) || null == xml) {
					logger.info("xml文件为空啊。。。");
					oneoperateinfo = "xml文件为空";
					errorinfo = "xml文件为空";
					loadState = "5";
					loadBackState = "1";
					DataNativeRecordSourceDao.getInstance().recordAllStates(
							centerid, centername, centerip, serverversion,
							uuid, loadState);
					operateinfo += centerid + ":" + version + ":"
							+ oneoperateinfo + ";";
					DataOperateRecordDao.getInstance().updateOperateRecord(id,
							centerId, operateinfo, errorinfo);
					return;
				} else {
					logger.info("处理xml开始啦！！！");
					loadState = "6";
					DataNativeRecordSourceDao.getInstance().recordAllStates(
							centerid, centername, centerip, serverversion,
							uuid, loadState);
					// xml = xml.replace("{", "<");
					// xml = xml.replace("}", ">");
					List<String> sqls = JdbcToXml.getInstance().xmlToInsert(
							xml, centerid);
					if (null == sqls) {
						oneoperateinfo = "sql语句为空";
						loadBackState = "1";
						loadState = "7";
						DataNativeRecordSourceDao.getInstance()
								.recordAllStates(centerid, centername,
										centerip, serverversion, uuid,
										loadState);
						operateinfo += centerid + ":" + version + ":"
								+ oneoperateinfo + ";";
						DataOperateRecordDao.getInstance().updateOperateRecord(
								id, centerId, operateinfo, errorinfo);
						return;
					} else {
						logger.info("数据开始插入......SQL的数目：" + sqls.size());
						loadState = "8";
						DataNativeRecordSourceDao.getInstance()
								.recordAllStates(centerid, centername,
										centerip, serverversion, uuid,
										loadState);
						boolean result = false;
						try {
							result = JdbcImpl.getJdbcImpl().updateSqls(sqls);
						} catch (Exception e) {
							oneoperateinfo = e.toString();
						}

						logger.info("数据全部执行完毕,返回结果：" + result);
						if (result) {
							// 插入成功
							oneoperateinfo = "插入成功";
							loadState = "9";
							DataNativeRecordSourceDao.getInstance()
									.recordAllStates(centerid, centername,
											centerip, serverversion, uuid,
											loadState);
						} else {
							// 插入失败
							errorinfo = "插入失败";
							// oneoperateinfo = "插入失败";
							loadState = "10";
							DataNativeRecordSourceDao.getInstance()
									.recordAllStates(centerid, centername,
											centerip, serverversion, uuid,
											loadState);
							operateinfo += centerid + ":" + version + ":"
									+ oneoperateinfo + ";";
							DataOperateRecordDao.getInstance()
									.updateOperateRecord(id, centerId,
											operateinfo, errorinfo);
							return;
						}
					}
				}
				loadState = "11";
				DataNativeRecordSourceDao.getInstance().recordAllStates(
						centerid, centername, centerip, serverversion, uuid,
						loadState);

				// 修改data_native_source表
				DataNativeSource dataNativeSource = new DataNativeSource();
				dataNativeSource.setCenterid(centerid);
				dataNativeSource.setCentername(centername);
				dataNativeSource.setCenterip(centerip);
				dataNativeSource.setVersion(Integer.valueOf(version));
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				dataNativeSource.setDownstartdate(dateNowStr);
				dataNativeSource.setDownenddate(dateNowStr);
				dataNativeSource.setUpdate(dateNowStr);
				dataNativeSource.setFlag(String.valueOf(state));
				dataNativeSource.setUuid(uuid);
				DataNativeSourceDao.getInstance().insert(dataNativeSource);
				oneoperateinfo = "下载成功";

				operateinfo += centerid + ":" + version + ":" + oneoperateinfo
						+ ";";
			}
			DataOperateRecordDao.getInstance().updateOperateRecord(id,
					centerId, operateinfo, errorinfo);
			loadBackState = "0";
		}
	}

	/**
	 * 获取本地所有的日志信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getClientLogsInfo(String centerid) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		String uuid = null;
		String sessionid = null;
		resultList = ViewLogs.getInstance().getClientLogs(uuid, sessionid,
				centerid);
		return resultList;

	}

	/**
	 * 获取服务器所有的日志信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getServerLogsInfo(String centerid) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		BasicTelecomImpl bt = MsgClientService.getInstance()
				.getBasicTelecomImpl();

		// 获取本地的版本号信息
		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);

		content.setSender(centerid);
		content.setCenterid(centerid);

		// 处理还原信息 flag=3
		content.setFlag(MsgType.VIEW_LOGS_CS);
		String json = JSON.toJSONString(content);
		logger.info("getResetVersionInfo" + "   " + "json" + "   " + json);
		if (bt.send(NormalMode.MODE, content)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}
		return resultList;

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
			selfList = DataSelfSourceDao.getInstance().getAllSelfSourceRecord();
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

	/**
	 * 获取还原反馈 * 记录还原过程中的状态 resetState 6正在发送7发送成功8发送失败10正在解包
	 * 11xml文件为空12开始解析xml13sql为空 14开始插入sql15插入成功16插入失败 17还原成功
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getResetResult(String uuid,
			String operatorsessionid) {
		String str = "";
		List<HashMap<String, String>> selfList = null;
		try {
			selfList = DataSelfSourceDao.getInstance().getAllSelfSourceRecord();
		} catch (Exception e) {
			logger.info("getResetResult error" + e);
			return null;
		}
		if (selfList.size() > 0) {
			for (int i = 0; i < selfList.size(); i++) {
				String flag = selfList.get(i).get("flag");
				logger.info("flag" + flag);
				if (flag.equals("6")) {
					str = "正在发送";
				} else if (flag.equals("7")) {
					str = "发送成功";
				} else if (flag.equals("8")) {
					str = "发送失败";
				} else if (flag.equals("10")) {
					str = "正在解包";
				} else if (flag.equals("11")) {
					str = "xml无数据";
				} else if (flag.equals("12")) {
					str = "开始解析xml";
				} else if (flag.equals("13")) {
					str = "无sql语句";
				} else if (flag.equals("14")) {
					str = "正在插入数据";
				} else if (flag.equals("15")) {
					str = "插入成功";
				} else if (flag.equals("16")) {
					str = "插入失败";
				} else if (flag.equals("17")) {
					str = "还原成功";
				} else {
					str = "未知错误";
				}
				selfList.get(i).put("resetState", str);
			}

		}
		return selfList;
	}

	/**
	 * 获取上传锁信息
	 * 
	 * @return
	 */
	public String[] getUpMsg() {
		String operate = "上传";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 获取下载锁信息
	 * 
	 * @return
	 */
	public String[] getLoadMsg() {
		String operate = "下载";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 处理服务器返回上传的版本数据
	 * 
	 * @return
	 */
	public void processUpMsg(String version) {
		selfServerVersion = version;
	}

	/**
	 * 得到服务器端数据版本
	 * 
	 * @return
	 */
	public String getServerVersion() {
		return selfServerVersion;
	}

	/**
	 * 得到服务器端返回的下载数据版本
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getMergeLoadVersion() {
		return lastLoadList;
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
	 * 得到服务器端返回的日志数据信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getSeverLogs() {
		return serverLogsList;
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

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLock() {
		DataOperateDao.getInstance().unLockAll();
	}

	/**
	 * 解除上传锁
	 * 
	 * @param uuid
	 * @return
	 */
	public String unUpLock(String uplockid) {

		String operate = "上传";
		String result = DataOperateDao.getInstance().unLockOne(operate,
				uplockid);
		return result;
	}

	/**
	 * 解除下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public String unLoadLock(String uplockid) {
		String operate = "下载";
		String result = DataOperateDao.getInstance().unLockOne(operate,
				uplockid);
		return result;
	}

	/**
	 * 给下载上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void loadLock() {
		String operate = "下载";
		DataOperateDao.getInstance().lockOne(operate);
	}

	/**
	 * 给上传上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void upLock() {
		String operate = "上传";
		DataOperateDao.getInstance().lockOne(operate);
	}

	/**
	 * 获取上传锁信息
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getUpLock() {
		String operate = "上传";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 获取下载锁信息
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getLoadLock() {
		String operate = "下载";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public void wirteXml(String address, String xml) {
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean handle(IContent content) {
		logger.info("收到消息：" + content);
		DBSyncContent recontent = (DBSyncContent) content;
		String flag = recontent.getFlag();
		logger.info("收到消息flag" + flag);
		// 处理上传初始化信息 flag=11
		if (flag.equals(MsgType.UP_INIT_SC)) {
			logger.info("接收到处理上传初始化信息 flag=11");
			selfServerVersion = String.valueOf(recontent.getVersion());
		}
		// 处理下载初始化信息 flag=13
		if (flag.equals(MsgType.Load_INIT_SC)) {
			logger.info("接收到处理下载初始化信息 flag=13");
			serverLoadList = recontent.getList();
			tempLoadList = new ArrayList<HashMap<String, String>>();
			if (serverLoadList != null) {
				logger.info("receive  " + "clientLoadList  " + clientLoadList);
				logger.info("receive  " + "serverLoadList  " + serverLoadList);
				tempLoadList = LoadMessage.getInstance().MergeMap(
						clientLoadList, tempLoadList);
				logger.info("receive  " + "tempLoadList1  " + tempLoadList);
				tempLoadList = LoadMessage.getInstance().MergeServerMap(
						serverLoadList, tempLoadList);
				logger.info("receive  " + "tempLoadList2  " + tempLoadList);
				lastLoadList = new ArrayList<HashMap<String, String>>();
				logger.info("receive  " + "lastLoadList1  " + lastLoadList);
				lastLoadList = LoadMessage.getInstance().getAllMap(
						tempLoadList, clientLoadList, serverLoadList);
				logger.info("receive  " + "lastLoadList2  " + lastLoadList);
				lastLoadList = getLastLoadInfos(lastLoadList);
				logger.info("receive  " + "lastLoadList3  " + lastLoadList);
			}
		}
		// 处理上传命令信息 flag=16
		if (flag.equals(MsgType.UP_COMMAND_SC)) {
			logger.info("接收到处理上传命令信息 flag=16");
			logger.info("接收上传命令信息" + recontent);
			String nextSelfVersion = recontent.getStrlist().get(2);
			logger.info("下一个版本" + nextSelfVersion);
			String centerid = recontent.getCenterid();
			String centername = recontent.getCentername();
			upState = 9;
			DataSelfSourceDao.getInstance().recordAllStates(centerid,
					centername, nextSelfVersion, upState);
			upBackState = recontent.getContent();
			int operateid = recontent.getOperateid();
			String errorinfo = "";
			String operateinfo = centerid + ":" + nextSelfVersion + ":上传成功;";
			DataOperateRecordDao.getInstance().updateOperateRecord(operateid,
					centerid, operateinfo, errorinfo);
			IUnLock.getInstance().unUpLock();
		}
		// 处理下载命令信息 flag=2
		if (flag.equals(MsgType.Load_COMMAND_SC)) {
			logger.info("接收到处理下载命令信息 flag=2");
			processLoadData(recontent);
			IUnLock.getInstance().unLoadLock();
		}
		// 处理还原命令信息 flag=4
		if (flag.equals(MsgType.Load_RESET_SC)) {
			logger.info("接收到处理还原命令信息 flag=4");
			serverResetList = recontent.getList();
		}
		// 处理还原命令信息 flag=6
		if (flag.equals(MsgType.Load_RESET_SECOND_SC)) {
			logger.info("接收到处理还原命令信息 flag=6");
			logger.info("接收到还原回来的消息" + recontent);
			processResetData(recontent);
		}

		// 处理查看日志信息 flag=15
		if (flag.equals(MsgType.VIEW_LOGS_SC)) {
			logger.info("接收到处理查看日志信息 flag=15");
			logger.info("接收回来的日志消息" + recontent);
			serverLogsList = recontent.getList();
		}

		return true;
	}

	/**
	 * 处理还原的数据
	 * 
	 * @return
	 */
	public void processResetData(DBSyncContent recontent) {

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
		DataSelfSourceDao.getInstance().recordAllStates(centerid, centername,
				version, state);
		if (("").equals(xml) || null == xml) {
			logger.info("xml文件为空啊。。。");
			state = 11;
			errorinfo = "xml文件为空";
			DataSelfSourceDao.getInstance().recordAllStates(centerid,
					centername, version, state);
			DataSelfSourceDao.getInstance().updateVersion(centerid,
					oldResetVersion);
			return;
		} else {
			logger.info("处理xml开始啦！！！");
			// xml = xml.replace("{", "<");
			// xml = xml.replace("}", ">");
			List<String> sqls = JdbcToXml.getInstance().xmlToInsert(xml,
					centerid);
			state = 12;
			DataSelfSourceDao.getInstance().recordAllStates(centerid,
					centername, version, state);
			if (sqls == null) {
				errorinfo = "sqls语句为空";
				state = 13;
				operateinfo = centerid + ":" + version + ":" + errorinfo + ";";
				DataSelfSourceDao.getInstance().recordAllStates(centerid,
						centername, version, state);
				DataOperateRecordDao.getInstance().updateOperateRecord(id,
						centerid, operateinfo, errorinfo);
				DataSelfSourceDao.getInstance().updateVersion(centerid,
						oldResetVersion);
				return;
			} else {
				logger.info("数据开始插入......SQL的数目：" + sqls.size());
				logger.info(sqls);
				state = 14;
				DataSelfSourceDao.getInstance().recordAllStates(centerid,
						centername, version, state);
				boolean result = false;
				try {
					result = JdbcImpl.getJdbcImpl().updateSqls(sqls);

				} catch (Exception e) {
					errorinfo = e.toString();
					operateinfo = centerid + ":" + version + ":" + errorinfo
							+ ";";
				}

				logger.info("数据全部执行完毕,返回结果：" + result);
				if (result) {
					state = 15;
					DataSelfSourceDao.getInstance().recordAllStates(centerid,
							centername, version, state);
				} else {
					resetBackState = "1";
					state = 16;
					// errorinfo="插入失败";
					// operateinfo = centerid + ":" + version + ":" + errorinfo+
					// ";";
					DataSelfSourceDao.getInstance().recordAllStates(centerid,
							centername, version, state);
					DataOperateRecordDao.getInstance().updateOperateRecord(id,
							centerid, operateinfo, errorinfo);
					DataSelfSourceDao.getInstance().updateVersion(centerid,
							oldResetVersion);
					return;
				}
			}
		}
		operateinfo = centerid + ":" + version + ":" + "还原成功" + ";";
		state = 17;
		resetBackState = "0";
		DataSelfSourceDao.getInstance().recordAllStates(centerid, centername,
				version, state);
		DataOperateRecordDao.getInstance().updateOperateRecord(id, centerid,
				operateinfo, errorinfo);
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
	 * 获取下载是否完成反馈
	 * 
	 * @return
	 */
	public String getLoadBack() {
		return loadBackState;

	}

	/**
	 * 获取还原是否完成反馈
	 * 
	 * @return
	 */
	public String getResetBack() {
		return resetBackState;

	}

	/**
	 * 增加是否可以下载字段
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLastLoadInfos(
			List<HashMap<String, String>> list) {

		if (list.size() > 0 && null != list) {

			for (int i = 0; i < list.size(); i++) {

				int clientVersion = Integer.valueOf(list.get(i).get(
						"clientversion"));
				int serverVersion = Integer.valueOf(list.get(i).get(
						"serverversion"));

				if (clientVersion < serverVersion) {
					// 可以下载
					list.get(i).put("isload", "1");
				} else {
					// 不能下载
					list.get(i).put("isload", "0");
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("centerid", "lch@001");
		map.put("clientversion", "10");
		map.put("serverversion", "12");
		map.put("flag", "2");
		list.add(map);
		MsgClientService msgClientService = new MsgClientService();
		list = msgClientService.getLoadStateInfos(list);
		logger.info("loadList" + list);

	}

	public BasicTelecomImpl getBasicTelecomImpl() {
		List<HashMap<String, String>> centerinfo = SyncDataInfoDao
				.getInstance().queryCenterID();
		List<HashMap<String, String>> syncInfo = SyncDataInfoDao.getInstance()
				.querySyncInfo();
		String initcenterid = null;
		String initcenterip = null;
		String initmqip = null;
		int initmqport = 0;
		if (centerinfo.size() > 0 && null != centerinfo) {
			Iterator<Entry<String, String>> iterator = centerinfo.get(0)
					.entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			initcenterid = (String) hashmap.get("CenterID".toLowerCase());
			initcenterip = (String) hashmap.get("CenterIP".toLowerCase());

		}
		if (syncInfo.size() > 0 && null != syncInfo) {

			Iterator<Entry<String, String>> iterator = syncInfo.get(0)
					.entrySet().iterator();
			HashMap<Object, Object> hashmap = new HashMap<Object, Object>();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			initmqip = (String) hashmap.get("MqIP".toLowerCase());
			initmqport = Integer.valueOf((String) hashmap.get("MqPort"
					.toLowerCase()));

			// initmqip = syncInfo.get(0).get("MqIP");
			// initmqport = Integer.valueOf(syncInfo.get(0).get("MqPort"));
		}

		MsgClientService msgService = MsgClientService.getInstance();
		if (null == bt) {
			logger.info("----------------listenr args----------------"+initcenterid+" "+initmqip);
			bt = new BasicTelecomImpl(initcenterid, initcenterip, initmqip,
					initmqport);
			// bt.registerInSyncServer();
			logger.info("----------------listenr start--------------------------");
			bt.setListener(msgService);
			return bt;
		} else {
			return bt;
		}

	}

	@Override
	public IContent getContentSimple() {
		return new DBSyncContent();
	}

}
