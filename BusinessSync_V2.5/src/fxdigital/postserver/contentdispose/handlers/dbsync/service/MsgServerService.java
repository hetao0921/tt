package fxdigital.postserver.contentdispose.handlers.dbsync.service;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hibernate.bean.DataIncrementVersion;
import com.hibernate.bean.DataOperateRecord;
import com.hibernate.bean.DataSource;
import com.hibernate.bean.DataVersion;
import com.hibernate.bean.SyncDataVersion;

import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.postserver.contentdispose.handlers.dbsync.autoserver.JMSSendService;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataIncrementVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataOperateRecordDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataSourceDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.pojo.MsgType;
import fxdigital.rpc.Mail;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.DBSyncContent;
import fxdigital.rpc.contenttype.DBSyncType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * @author het 服务器端消息处理类 2014-7-30 BusinessSync
 *         fxdigital.postserver.contentdispose.handlers.dbsync.service
 */

@Component
public class MsgServerService extends AbstractHandler {
	private static Logger logger = Logger.getLogger(MsgServerService.class);
	@Autowired
	private JMSSendService jMSSendService;

	List<HashMap<String, String>> clientLoadList = null;
	List<HashMap<String, String>> serverLoadList = null;
	List<HashMap<String, String>> tempLoadList = null;
	List<HashMap<String, String>> lastLoadList = null;

	List<HashMap<String, String>> resetlist = null;

	public void afterInit() {
		logger.info("init start...");
		InitThread initThread = new InitThread(this);
		initThread.start();

	}

	class InitThread extends Thread {
		MsgServerService msgService = null;

		public InitThread(MsgServerService msgServerService) {
			msgService = msgServerService;
		}

		public void run() {
			AutoServer.start(msgService);
		}
	}

	/**
	 * 启动自动同步
	 * 
	 * @return
	 */
	public boolean sendAutoSyncCommand(String source, String dest) {
		// Mail mail = new Mail();
		//
		// DBSyncContent strcontent = new DBSyncContent();
		//
		// // 发送标记 flag=5
		// strcontent.setFlag(MsgType.AUTO_SYNC_S0S1);
		// List<HashMap<String, String>> list = DataVersionDao.getInstance()
		// .getAllServerSource();
		// strcontent.setList(list);
		// strcontent.setSender(source);
		// strcontent.setReceiver(dest);
		// String json = JSON.toJSONString(strcontent);
		// logger.info("sendAutoSyncCommand" + "   " + "json" + "   "
		// + json);
		//
		// mail.setContent(json);
		// mail.setSrcMailboxID(source);
		// mail.setDestMailboxID(dest);
		// mail.setContentType(DBSyncType.TYPE);
		// mail.setSendMode(NormalMode.MODE);
		// return outerTransmitter.sendOut(mail);

		return jMSSendService.SendAutoSyncCommand();

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

	public List<HashMap<String, String>> getIncrementXml(String centerid,
			String version, String serverVersion) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> incrementVersion = getIncrementVersion(centerid, version,
				serverVersion);
		if (null != incrementVersion && incrementVersion.size() > 0) {
			for (int i = 0; i < incrementVersion.size(); i++) {
				String localVersion = incrementVersion.get(i);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getIncrementServerSource(centerid, localVersion);
				if (null != addressList && addressList.size() > 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					String centerip = addressList.get(0).get("centerip");
					String centername = addressList.get(0).get("centername");
					String fileaddress = addressList.get(0).get("fileaddress");
					logger.info("下载增量文件地址fileaddress   " + fileaddress);
					String xml = LoadMessage.getInstance().readFileByLines(
							fileaddress);
					map.put("centerid", centerid);
					map.put("centerip", centerip);
					map.put("centername", centername);
					map.put("version", localVersion);
					map.put("xml", xml);
					list.add(map);
				}
			}
		}
		logger.info("需要下载的增量信息：" + list);
		return list;
	}

	public List<HashMap<String, String>> getResetIncrementXml(
			List<String> incrementVersion, String centerid) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (null != incrementVersion && incrementVersion.size() > 0) {
			for (int i = 0; i < incrementVersion.size(); i++) {
				String localVersion = incrementVersion.get(i);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getSourceAddress(centerid, localVersion);
				if (null != addressList && addressList.size() > 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					String centerip = addressList.get(0).get("centerip");
					String centername = addressList.get(0).get("centername");
					String fileaddress = addressList.get(0).get("fileaddress");
					logger.info("下载增量文件地址fileaddress   " + fileaddress);
					String xml = LoadMessage.getInstance().readFileByLines(
							fileaddress);
					map.put("centerid", centerid);
					map.put("centerip", centerip);
					map.put("centername", centername);
					map.put("version", localVersion);
					map.put("xml", xml);
					list.add(map);
				}
			}
		}
		return list;
	}

	public String getIncrementVersion(List<HashMap<String, String>> list,
			String centerid) {
		String incrementVersion = "0";
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String incrementCenterid = list.get(i).get("centerid");
				if (incrementCenterid.equals(centerid)) {
					incrementVersion = list.get(i).get("version");
				}
			}
		}
		return incrementVersion;

	}

	@Override
	public boolean handle(String strContent) {
		// TODO Auto-generated method stub
		logger.info("收到上传下载的消息：" + strContent);
		DBSyncContent content = JSON.parseObject(strContent,
				DBSyncContent.class);
		String flag = content.getFlag();
		logger.info("服务器端flag" + flag);
		logger.info("centerid" + content.getCenterid());
		// 处理上传初始化数据 flag=10
		if (flag.equals(MsgType.UP_INIT_CS)) {
			logger.info("处理上传初始化数据 flag=10");
			// logger.info("content.getCenterid()" + content.getCenterid());
			List<HashMap<String, String>> list = DataVersionDao.getInstance()
					.getOneVersion(content.getCenterid());
			if (list.size() > 0 && null != list.get(0).get("version")) {
				content.setVersion(Integer.valueOf(list.get(0).get("version")));
				logger.info("list.get(0)[4]" + list.get(0).get("version"));
			}
			// logger.info("list" + list);
			content.setFlag(MsgType.UP_INIT_SC);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		// 处理下载初始化数据 flag=12
		if (flag.equals(MsgType.Load_INIT_CS)) {
			logger.info("处理下载初始化数据 flag=12");
			content.setFlag(MsgType.Load_INIT_SC);
			List<HashMap<String, String>> list = DataVersionDao.getInstance()
					.getNoOneSource(content.getCenterid());
			content.setList(list);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		// 处理上传命令flag=0
		if (flag.equals(MsgType.UP_COMMAND_CS)) {
			logger.info("处理上传命令flag=0");
			logger.info("处理上传命令" + flag);
			processUpCommand(content);
			content.setFlag(MsgType.UP_COMMAND_SC);
			content.setContent("0");
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		if (flag.equals(MsgType.AUTO_LOAD_CS)) {

			logger.info("处理下载命令flag=301");
			serverLoadList = DataVersionDao.getInstance().getNoOneSource(
					content.getCenterid());
			clientLoadList = content.getList();
			List<HashMap<String, String>> incrementClientList = content
					.getSecondList();
			content.setFlag(MsgType.AUTO_LOAD_SC);
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
			lastLoadList=downAllServerData(lastLoadList);
			
			List<HashMap<String, String>> serverIncrementList = DataIncrementVersionDao
					.getInstance().getNoOneSource(content.getCenterid());
			serverIncrementList=downIncrementServerData(incrementClientList,serverIncrementList);
	
			content.setList(lastLoadList);
			content.setSecondList(serverIncrementList);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		// 处理下载命令flag=1
		if (flag.equals(MsgType.Load_COMMAND_CS)) {
			logger.info("处理下载命令flag=1");
			content.setFlag(MsgType.Load_COMMAND_SC);
			List<HashMap<String, String>> list = content.getList();
			list=downAllServerData(list);
			List<HashMap<String, String>> incrementClientList = content
					.getSecondList();
			List<HashMap<String, String>> serverIncrementList = DataIncrementVersionDao
					.getInstance().getNoOneSource(content.getCenterid());
			serverIncrementList=downIncrementServerData(incrementClientList,serverIncrementList);
			content.setList(list);
			content.setSecondList(serverIncrementList);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		// 处理还原命令flag=3
		if (flag.equals(MsgType.Load_RESET_CS)) {
			logger.info("处理还原命令flag=3");
			String centerid = content.getCenterid();
			String centerip = content.getCenterip();
			content.setFlag(MsgType.Load_RESET_SC);
			resetlist = DataSourceDao.getInstance().getResetServerSource(
					centerid);
			content.setList(resetlist);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
			logger.info("flag    " + MsgType.Load_RESET_CS);
		}
		// 处理还原命令flag=5
		if (flag.equals(MsgType.Load_RESET_SECOND_CS)) {
			logger.info("处理还原命令flag=5");
			String centerid = content.getCenterid();
			String centername = content.getCentername();
			String centerip = content.getCenterip();
			int version = content.getVersion();
			// 得到下一个全量版本
			String nextVersion = getNextAllVersion(resetlist,
					String.valueOf(version));
			logger.info("全量版本：centerid " + centerid + " 当前全量版本: " + version
					+ " 下一个全量版本： " + nextVersion);
			// 得到所有的增量版本
			List<String> resetIncrementVersionList = getResetIncrementVersion(
					centerid, version, nextVersion);
			logger.info("增量量版本：centerid " + centerid + " 所有增量版本: "
					+ resetIncrementVersionList);
			List<HashMap<String, String>> resetIncrementXml = getResetIncrementXml(
					resetIncrementVersionList, centerid);

			List<HashMap<String, String>> addressList = DataSourceDao
					.getInstance().getSourceAddress(centerid,
							String.valueOf(version));
			String newXml = null;
			String oldXml = null;
			if (null != addressList && addressList.size() > 0) {
				String newfileaddress = addressList.get(0).get("fileaddress");
				String oldfileaddress = addressList.get(0)
						.get("oldfileaddress");
				logger.info("下载文件地址newfileaddress   " + newfileaddress);
				newXml = LoadMessage.getInstance().readFileByLines(
						newfileaddress);
				oldXml = LoadMessage.getInstance().readFileByLines(
						oldfileaddress);
			}
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("oldXml", oldXml);
			map.put("newXml", newXml);
			map.put("incrementXml", JSONObject.toJSONString(resetIncrementXml));
			String json = JSONObject.toJSONString(map);
			logger.info("全部下载数据：centerid " + centerid + " 所有数据: " + json);
			content.setXml(json);
			// logger.info("xml----" + xml);
			content.setFlag(MsgType.Load_RESET_SECOND_SC);
			// logger.info("Load_RESET_SECOND_SC"
			// + MsgType.Load_RESET_SECOND_SC);

			// 修改操作记录data_operate_record
			DataOperateRecord dataOperateRecord = new DataOperateRecord();
			dataOperateRecord.setOperate("还原");
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			dataOperateRecord.setOperatetime(dateNowStr);
			dataOperateRecord.setCenterid(centerid);
			dataOperateRecord.setCentername(centername);
			dataOperateRecord.setOperatorip(centerip);
			DataOperateRecordDao.getInstance().insert(dataOperateRecord);

			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}

		// 处理同步服务器同步命令flag=7
		if (flag.equals(MsgType.AUTO_SYNC_S0S1)) {
			logger.info("处理同步服务器同步命令flag=7");
			Mail mail = new Mail();
			DBSyncContent strcontent = new DBSyncContent();

			strcontent.setFlag(MsgType.AUTO_SYNC_S1S0);
			List<List<HashMap<String, String>>> list = processAutoCommand(content);
			logger.info("得到要进行传递的列表信息:" + list);
			List<HashMap<String, String>> lastUplist = null;
			List<HashMap<String, String>> lastLowlist = null;
			if (list.size() > 0) {
				// 上级需要向下级要在信息
				// lastList.add(lastUplist);
				// 下级需要向上级要在信息
				// lastList.add(lastLowlist);
				lastUplist = list.get(0);
				lastLowlist = list.get(1);
				if (lastLowlist.size() > 0) {
					for (int i = 0; i < lastLowlist.size(); i++) {
						String centerid = lastLowlist.get(i).get("centerid");
						String version = lastLowlist.get(i).get("version");
						List<HashMap<String, String>> addressList = DataSourceDao
								.getInstance().getSourceAddress(centerid,
										String.valueOf(version));
						String xml = null;
						if (addressList.size() > 0) {
							// xmlMap=new HashMap<String, String>();
							String fileaddress = addressList.get(0).get(
									"fileaddress");
							logger.info("下载文件地址fileaddress   " + fileaddress);
							xml = LoadMessage.getInstance().readFileByLines(
									fileaddress);
							logger.info("下载文件内容xml   " + xml);
							lastLowlist.get(i).put("xml", xml);
						}

					}
				}
			}

			if ((null != lastUplist && lastUplist.size() > 0)
					|| (null != lastLowlist && lastLowlist.size() > 0)) {
				logger.info("lastUplist.size" + lastUplist.size());
				logger.info("lastLowlist.size" + lastLowlist.size());
				strcontent.setList(lastUplist);
				strcontent.setSecondList(lastLowlist);
				strcontent.setSender(content.getReceiver());
				strcontent.setReceiver(content.getSender());

				String json = JSON.toJSONString(strcontent);
				mail.setContent(json);
				mail.setSrcMailboxID(content.getReceiver());
				mail.setDestMailboxID(content.getSender());
				logger.info("源1:" + content.getReceiver() + "目标1:"
						+ content.getSender());
				mail.setContentType(DBSyncType.TYPE);
				mail.setSendMode(NormalMode.MODE);
				return outerTransmitter.sendOut(mail);
			}

		}
		// 处理同步服务器同步命令flag=8
		if (flag.equals(MsgType.AUTO_SYNC_S1S0)) {
			logger.info("处理同步服务器同步命令flag=8");
			Mail mail = new Mail();
			DBSyncContent strcontent = new DBSyncContent();

			strcontent.setFlag(MsgType.AUTO_SYNC_SECOND_S0S1);

			List<HashMap<String, String>> lastUplist = content.getList();
			logger.info("得到上级要进行传递的列表信息:" + lastUplist);
			List<HashMap<String, String>> lastLowlist = content.getSecondList();
			logger.info("得到下级要进行传递的列表信息:" + lastLowlist);

			if (null != lastUplist && lastUplist.size() > 0) {
				for (int i = 0; i < lastUplist.size(); i++) {
					String centerid = lastUplist.get(i).get("centerid");
					String version = lastUplist.get(i).get("version");
					List<HashMap<String, String>> addressList = DataSourceDao
							.getInstance().getSourceAddress(centerid,
									String.valueOf(version));
					String xml = null;
					if (addressList.size() > 0) {
						// xmlMap=new HashMap<String, String>();
						String fileaddress = addressList.get(0).get(
								"fileaddress");
						System.out
								.println("下载文件地址fileaddress   " + fileaddress);
						xml = LoadMessage.getInstance().readFileByLines(
								fileaddress);
						logger.info("下载文件内容xml   " + xml);
						lastUplist.get(i).put("xml", xml);
					}

				}

			}
			if (null != lastLowlist && lastLowlist.size() > 0) {
				writexml(lastLowlist);
			}
			if (null != lastUplist && lastUplist.size() > 0) {
				strcontent.setList(lastUplist);
				String json = JSON.toJSONString(strcontent);
				mail.setContent(json);
				mail.setSrcMailboxID(content.getReceiver());
				mail.setDestMailboxID(content.getSender());
				logger.info("源2:" + content.getReceiver() + "目标2:"
						+ content.getSender());
				mail.setContentType(DBSyncType.TYPE);
				mail.setSendMode(NormalMode.MODE);
				return outerTransmitter.sendOut(mail);
			}

		}
		// 处理同步服务器同步命令flag=9
		if (flag.equals(MsgType.AUTO_SYNC_SECOND_S0S1)) {
			logger.info("处理同步服务器同步命令flag=9");
			List<HashMap<String, String>> lastUplist = content.getList();
			if (null != lastUplist && lastUplist.size() > 0) {
				writexml(lastUplist);
			}
		}

		// 处理查看服务器日志flag=14
		if (flag.equals(MsgType.VIEW_LOGS_CS)) {
			logger.info("处理查看服务器日志flag=14");
			logger.info("查看服务器日志...");
			List<HashMap<String, String>> list = DataOperateRecordDao
					.getInstance().getAllOperateRecord();
			content.setFlag(MsgType.VIEW_LOGS_SC);
			content.setList(list);
			// TODO
			String channelName = content.getSender() + "_"
					+ content.getClass().getSimpleName();
			innerTransmitter.sendIn(channelName, content);
		}
		// 处理自动增长flag=300
		if (flag.equals(MsgType.INCREMENT_UP_CS)) {
			String centerid = content.getCenterid();
			// 查找当前版本信息，找到对应的版本
			int nextVersion = getNextIncrementVersion(centerid);
			processIncrementCommand(nextVersion, content);
		}
		return true;
	}

	// 得到客户端增量在服务器端的位置
	public int getClientIndex(String centerid,
			List<HashMap<String, String>> incrementClientList) {
		int index = -1;
		if (null != incrementClientList && incrementClientList.size() > 0) {
			for (int i = 0; i < incrementClientList.size(); i++) {
				if (incrementClientList.get(i).get("centerid").equals(centerid)) {
					index = i;
				}
			}
		}
		return index;
	}
	
	
	//下载所有全量数据
	public List<HashMap<String, String>> downAllServerData(List<HashMap<String, String>> lastLoadList){
		if (null != lastLoadList && lastLoadList.size() > 0) {
			for (int i = 0; i < lastLoadList.size(); i++) {
				String clientCenterid = lastLoadList.get(i).get("centerid");
				String serverVersion = (null == lastLoadList.get(i).get(
						"serverversion")) ? "0" : lastLoadList.get(i).get(
						"serverversion");
				String clientVersion = (null == lastLoadList.get(i).get(
						"clientversion")) ? "0" : lastLoadList.get(i).get(
						"clientversion");
				HashMap<String, String> map = new HashMap<String, String>();
				String xml = null;
				String oldXml = null;
				if (Integer.valueOf(clientVersion) < Integer
						.valueOf(serverVersion)) {
					logger.info("下载文件地址centerid   " + clientCenterid
							+ "版本：" + serverVersion);
					List<HashMap<String, String>> addressList = DataSourceDao
							.getInstance().getSourceAddress(clientCenterid,
									serverVersion);
					if (null != addressList && addressList.size() > 0) {
						// xmlMap=new HashMap<String, String>();
						String fileaddress = addressList.get(0).get(
								"fileaddress");
						String oldfileaddress = addressList.get(0).get(
								"oldfileaddress");
						logger.info("下载文件地址fileaddress   " + fileaddress);
						logger.info("下载文件地址oldfileaddress   "
								+ oldfileaddress);
						xml = LoadMessage.getInstance().readFileByLines(
								fileaddress);
						oldXml = LoadMessage.getInstance().readFileByLines(
								oldfileaddress);
						// xmlMap.put("xml", xml);

						logger.info("下载中心:" + clientCenterid
								+ "    下载xml文件: " + xml
								+ "    下载oldxml文件: " + oldXml);
					}
				}
				map.put("oldXml", oldXml);
				map.put("newXml", xml);
				String json = JSONObject.toJSONString(map);
				lastLoadList.get(i).put("xml", json);
			}
		}
		return lastLoadList;
	}
	
	
	
	public List<HashMap<String, String>> downIncrementServerData(List<HashMap<String, String>> incrementClientList,List<HashMap<String, String>> serverIncrementList){
		// 得到增量的xml
		String serverIncrementVersion = "0";
		logger.info("客户端的增量版本: "+incrementClientList);
		logger.info("服务器端的增量版本: "+serverIncrementList);
		if (null != serverIncrementList && serverIncrementList.size() > 0) {
			for (int i = 0; i < serverIncrementList.size(); i++) {
				String centerid = serverIncrementList.get(i)
						.get("centerid");
				serverIncrementVersion = serverIncrementList.get(i).get(
						"version");
				// 当前中心在客户端位置
				int index = getClientIndex(centerid, incrementClientList);
				String clientIncrementVersion = "0";
				if (index != -1) {
					clientIncrementVersion = incrementClientList.get(index)
							.get("version");
				}
				logger.info("服务器的增量: "+" "+ serverIncrementList.get(i)
						.get("centerip")+" "+clientIncrementVersion+" "+serverIncrementVersion);
				if (Integer.valueOf(clientIncrementVersion) < Integer
						.valueOf(serverIncrementVersion)) {
					HashMap<String, String> map = new HashMap<String, String>();
					List<HashMap<String, String>> incrementList = getIncrementXml(
							centerid, clientIncrementVersion,
							serverIncrementVersion);
					map.put("incrementXml",
							JSONObject.toJSONString(incrementList));
					String json = JSONObject.toJSONString(map);
					serverIncrementList.get(i).put("xml", json);
				}
			}
		}
		logger.info("需要下载的增量版本: "+serverIncrementList);
		return serverIncrementList;
	}

	public List<String> getResetIncrementVersion(String centerid, int version,
			String nextVersion) {
		List<String> list = new ArrayList<String>();
		// 如果下一版本为空
		if (null == nextVersion || ("").equals(nextVersion)) {
			list = getIncrementVersion(centerid, String.valueOf(version),
					nextVersion);
		} else {
			int intNextVersion = Integer.valueOf(nextVersion);
			for (int i = version + 1; i < intNextVersion; i++) {
				list.add(String.valueOf(i));
			}

		}
		return list;
	}

	// 由当前全量版本得到下一个全量版本
	public String getNextAllVersion(List<HashMap<String, String>> resetlist,
			String version) {
		String nextVersion = null;
		if (null != resetlist && resetlist.size() > 0) {
			for (int i = 0; i < resetlist.size(); i++) {
				HashMap<String, String> tempMap = resetlist.get(i);
				String tempVersion = tempMap.get("version");
				// 拿到当前全量版本的位置
				if (tempVersion.equals(version) && i != 0) {
					nextVersion = resetlist.get(i - 1).get("version");
				}
			}

		}
		return nextVersion;

	}

	// 得到所有需要下载的增量版本
	public List<String> getIncrementVersion(String centerid, String version,
			String serverVersion) {
		logger.info("客户端增量最大版本-----:" + centerid + "版本" + version);
		List<String> list = new ArrayList<String>();
		// 把增量的最大版本也传递过去
		if (null == version || ("").equals(version)) {
			version = "0";
		}
		for (int i = Integer.valueOf(version) + 1; i <= Integer
				.valueOf(serverVersion); i++) {
			list.add(String.valueOf(i));
		}
		logger.info("所有需要下载增量版本-------:" + centerid + "版本 ---" + list);
		return list;
	}

	public int getNextIncrementVersion(String centerid) {
		int incrementVersion = 0;
		List<HashMap<String, String>> incrementVersionInfo = DataIncrementVersionDao
				.getInstance().getOneVersion(centerid);
		if (incrementVersionInfo.size() == 0 || null == incrementVersionInfo) {
			incrementVersion = 0;
		} else {
			incrementVersion = Integer.valueOf(incrementVersionInfo.get(0).get(
					"version"));

		}

		incrementVersion += 1;
		return incrementVersion;
	}

	// 通过比较全量表和增量版本表
	// 得到最新的版本号
	public int getNextVersion(String centerid) {
		int version = 1;
		int incrementVersion = 0;
		int allVersion = 0;
		List<HashMap<String, String>> allVersionInfo = DataVersionDao
				.getInstance().getOneVersion(centerid);
		List<HashMap<String, String>> incrementVersionInfo = DataIncrementVersionDao
				.getInstance().getOneVersion(centerid);
		if (incrementVersionInfo.size() == 0 || null == incrementVersionInfo) {
			incrementVersion = 0;
		} else {
			incrementVersion = Integer.valueOf(incrementVersionInfo.get(0).get(
					"version"));
		}
		if (allVersionInfo.size() == 0 || null == allVersionInfo) {
			allVersion = 0;
		} else {
			allVersion = Integer.valueOf(allVersionInfo.get(0).get("version"));
		}

		version = incrementVersion > allVersion ? incrementVersion + 1
				: allVersion + 1;
		return version;
	}

	public void processIncrementCommand(int nextVersion, DBSyncContent content) {
		String xml = content.getXml();
		String centerid = content.getCenterid();
		String centername = content.getCentername();
		String centerip = content.getCenterip();
		List<String> pathlist = readPathXML();
		String basePath = "";
		String incrementPath = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			basePath = "/" + pathlist.get(1) + "/increment";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			basePath = pathlist.get(0) + ":/increment";
		}
		incrementPath = getOldPath(basePath, centerid, nextVersion);
		logger.info("上传写文件目录：    " + incrementPath);
		wirteXml(incrementPath, xml);
		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("上传");
		Date d = new Date();
		logger.info(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		dataOperateRecord.setOperatetime(dateNowStr);
		dataOperateRecord.setCenterid(centerid);
		dataOperateRecord.setOperatorip(centerip);
		dataOperateRecord.setCentername(centername);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		// 修改资源记录data_source
		DataSource dataSource = new DataSource();
		dataSource.setCenterid(centerid);
		dataSource.setCentername(centername);
		dataSource.setCenterip(centerip);
		dataSource.setUpdatetime(dateNowStr);
		dataSource.setFileaddress(incrementPath);
		dataSource.setVersion(nextVersion);
		DataSourceDao.getInstance().insert(dataSource);
		// 修改数据版本data_version
		DataIncrementVersionDao.getInstance().delete(centerid);
		DataIncrementVersion dataIncrementVersion = new DataIncrementVersion();
		dataIncrementVersion.setCenterid(centerid);
		dataIncrementVersion.setCenterip(centerip);
		dataIncrementVersion.setCentername(centername);
		dataIncrementVersion.setVersion(nextVersion);
		DataIncrementVersionDao.getInstance().insert(dataIncrementVersion);
		// 修改同步数据版本sync_data_version
		SyncDataVersion syncDataVersion = new SyncDataVersion();
		syncDataVersion.setCenterid(centerid);
		syncDataVersion.setCentername(centername);
		syncDataVersion.setCenterip(centerip);
		syncDataVersion.setVersion(nextVersion);
		SyncDataVersionDao.getInstance().insert(syncDataVersion);
	}

	/**
	 * 返回两个同步服务器之间需要同步的数据 例：下级数据 lowMap.put("centerid", "001");
	 * lowMap.put("version", "2"); lowMap2.put("centerid", "002");
	 * lowMap2.put("version", "2"); lowMap3.put("centerid", "003");
	 * lowMap3.put("version", "2"); 上级数据： upMap1.put("centerid", "001");
	 * upMap1.put("version", "3"); upMap2.put("centerid", "002");
	 * upMap2.put("version", "3"); upMap3.put("centerid", "003");
	 * upMap3.put("version", "1");
	 * 
	 * 返回数据集合： 上级需同步的集合[{centerid=003, version=2}] 下级需同步的集合[{centerid=001,
	 * version=3}, {centerid=002, version=3}]
	 * 
	 * @param uuid
	 * @return
	 */
	public List<List<HashMap<String, String>>> processAutoCommand(
			DBSyncContent content) {
		// 得到下级的所有数据的版本
		List<HashMap<String, String>> serverLowlist = content.getList();
		// 得到上级所有数据的版本
		List<HashMap<String, String>> serverUplist = DataVersionDao
				.getInstance().getAllServerSource();

		List<HashMap<String, String>> lastLowlist = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lastUplist = new ArrayList<HashMap<String, String>>();
		List<List<HashMap<String, String>>> lastList = new ArrayList<List<HashMap<String, String>>>();
		if (serverLowlist.size() > 0) {
			if (serverUplist.size() > 0) {
				// 看下级在上级存在不
				for (int i = 0; i < serverLowlist.size(); i++) {
					int index = LoadMessage.getInstance().getIndex(
							serverUplist, serverLowlist.get(i));
					logger.info("index   " + index);

					if (index == -1) {
						lastUplist.add(serverLowlist.get(i));
					} else {
						logger.info("下级版本"
								+ serverLowlist.get(i).get("version"));
						logger.info("上级版本"
								+ serverUplist.get(index).get("version"));
						if (Integer
								.valueOf(serverLowlist.get(i).get("version")) > Integer
								.valueOf(serverUplist.get(index).get("version"))) {
							logger.info("index   下级大");
							lastUplist.add(serverLowlist.get(i));
						}
					}
				}

				// 看上级在下级存在不
				for (int i = 0; i < serverUplist.size(); i++) {
					int index = LoadMessage.getInstance().getIndex(
							serverLowlist, serverUplist.get(i));
					if (index == -1) {
						lastLowlist.add(serverUplist.get(i));
					} else if (Integer.valueOf(serverLowlist.get(index).get(
							"version")) < Integer.valueOf(serverUplist.get(i)
							.get("version"))) {
						lastLowlist.add(serverUplist.get(i));
					}
				}
			} else {
				lastUplist = serverLowlist;
				logger.info("上级服务器上都没有数据!");
			}
		} else {
			if (serverUplist.size() > 0) {
				lastLowlist = serverUplist;
				logger.info("下级服务器上都没有数据!");
			} else {
				logger.info("服务器上都没有数据,无同步数据!");
			}
		}
		// 上级需要向下级要在信息
		lastList.add(lastUplist);
		// 下级需要向上级要在信息
		lastList.add(lastLowlist);
		return lastList;
	}

	public List<List<HashMap<String, String>>> processAutoCommand() {
		HashMap<String, String> lowMap = new HashMap<String, String>();
		HashMap<String, String> lowMap2 = new HashMap<String, String>();
		HashMap<String, String> lowMap3 = new HashMap<String, String>();

		lowMap.put("centerid", "001");
		lowMap.put("version", "2");
		lowMap2.put("centerid", "002");
		lowMap2.put("version", "2");
		lowMap3.put("centerid", "003");
		lowMap3.put("version", "2");

		// 得到下级的所有数据的版本
		List<HashMap<String, String>> serverLowlist = new ArrayList<HashMap<String, String>>();
		serverLowlist.add(lowMap);
		serverLowlist.add(lowMap2);
		serverLowlist.add(lowMap3);

		HashMap<String, String> upMap1 = new HashMap<String, String>();
		HashMap<String, String> upMap2 = new HashMap<String, String>();
		HashMap<String, String> upMap3 = new HashMap<String, String>();
		upMap1.put("centerid", "001");
		upMap1.put("version", "3");
		upMap2.put("centerid", "002");
		upMap2.put("version", "3");
		upMap3.put("centerid", "003");
		upMap3.put("version", "1");

		// 得到上级所有数据的版本
		List<HashMap<String, String>> serverUplist = new ArrayList<HashMap<String, String>>();
		serverUplist.add(upMap1);
		serverUplist.add(upMap2);
		serverUplist.add(upMap3);

		List<HashMap<String, String>> lastLowlist = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lastUplist = new ArrayList<HashMap<String, String>>();
		List<List<HashMap<String, String>>> lastList = new ArrayList<List<HashMap<String, String>>>();
		if (serverLowlist.size() > 0) {
			if (serverUplist.size() > 0) {
				// 看下级在上级存在不
				for (int i = 0; i < serverLowlist.size(); i++) {
					int index = LoadMessage.getInstance().getIndex(
							serverUplist, serverLowlist.get(i));
					logger.info("index   " + index);

					if (index == -1) {
						lastUplist.add(serverLowlist.get(i));
					} else {
						logger.info("下级版本"
								+ serverLowlist.get(i).get("version"));
						logger.info("上级版本"
								+ serverUplist.get(index).get("version"));
						if (Integer
								.valueOf(serverLowlist.get(i).get("version")) > Integer
								.valueOf(serverUplist.get(index).get("version"))) {
							logger.info("index   下级大");
							lastUplist.add(serverLowlist.get(i));
						}
					}
				}

				// 看上级在下级存在不
				for (int i = 0; i < serverUplist.size(); i++) {
					int index = LoadMessage.getInstance().getIndex(
							serverLowlist, serverUplist.get(i));
					if (index == -1) {
						lastLowlist.add(serverUplist.get(i));
					} else if (Integer.valueOf(serverLowlist.get(index).get(
							"version")) < Integer.valueOf(serverUplist.get(i)
							.get("version"))) {
						lastLowlist.add(serverUplist.get(i));
					}
				}
			} else {
				lastUplist = serverLowlist;
				logger.info("上级服务器上都没有数据!");
			}
		} else {
			if (serverUplist.size() > 0) {
				lastLowlist = serverUplist;
				logger.info("下级服务器上都没有数据!");
			} else {
				logger.info("服务器上都没有数据,无同步数据!");
			}
		}
		// 上级需要向下级要在信息
		lastList.add(lastUplist);
		// 下级需要向上级要在信息
		lastList.add(lastLowlist);
		return lastList;
	}

	public void writexml(List<HashMap<String, String>> list) {

		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String centerid = list.get(i).get("centerid");
				String centername = list.get(i).get("centername");
				String centerip = list.get(i).get("centerip");
				String version = list.get(i).get("version");
				String xml = list.get(i).get("xml");
				String path = "";
				List<String> pathlist = readPathXML();
				if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
				{
					path = "/" + pathlist.get(1) + "/sync" + centerid + version
							+ ".xml";
				} else {
					// logger.info("==============="+System.getProperty("user.dir"));
					path = pathlist.get(0) + ":/sync" + centerid + version
							+ ".xml";
				}
				wirteXml(path, xml);
				logger.info("文件写入地址:" + path);
				logger.info("文件写入内容:" + xml);

				// 修改操作记录data_operate_record
				DataOperateRecord dataOperateRecord = new DataOperateRecord();
				dataOperateRecord.setOperate("同步");
				Date d = new Date();
				logger.info("当前同步时间" + d);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				dataOperateRecord.setOperatetime(dateNowStr);
				dataOperateRecord.setOperatorip(centerip);
				dataOperateRecord.setCenterid(centerid);
				dataOperateRecord.setCentername(centername);
				DataOperateRecordDao.getInstance().insert(dataOperateRecord);
				// 修改资源记录data_source
				DataSource dataSource = new DataSource();
				dataSource.setCenterid(centerid);
				dataSource.setCentername(centername);
				dataSource.setCenterip(centerip);
				dataSource.setUpdatetime(dateNowStr);
				dataSource.setFileaddress(path);
				dataSource
						.setVersion(("".equals(version) || null == version) ? 0
								: Integer.valueOf(version));
				DataSourceDao.getInstance().insert(dataSource);
				// 修改数据版本data_version
				DataVersionDao.getInstance().delete(centerid);
				DataVersion dataVersion = new DataVersion();
				dataVersion.setCenterid(centerid);
				dataVersion.setCentername(centername);
				dataVersion.setCenterip(centerip);
				dataVersion
						.setVersion(("".equals(version) || null == version) ? 0
								: Integer.valueOf(version));
				DataVersionDao.getInstance().insert(dataVersion);
				// 修改同步数据版本sync_data_version
				SyncDataVersion syncDataVersion = new SyncDataVersion();
				syncDataVersion.setCenterid(centerid);
				syncDataVersion.setCentername(centername);
				syncDataVersion.setCenterip(centerip);
				syncDataVersion
						.setVersion(("".equals(version) || null == version) ? 0
								: Integer.valueOf(version));
				SyncDataVersionDao.getInstance().insert(syncDataVersion);
			}
		}

	}

	public String getOldPath(String baseRoot, String centerid,
			int localSelfVersion) {
		String oldPath = "";
		File file = new File(baseRoot);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		oldPath = baseRoot + "/sync" + centerid + localSelfVersion + ".xml";
		return oldPath;
	}

	public void processUpCommand(DBSyncContent content) {
		// logger.info("服务器端flag"+flag);
		String xml = content.getXml();
		HashMap<String, String> map = JSONObject
				.parseObject(xml, HashMap.class);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		logger.info("xml----" + xml);
		logger.info("oldXml----" + oldXml);
		logger.info("newXml----" + newXml);
		String centerid = content.getCenterid();
		String centername = content.getCentername();
		String centerip = content.getCenterip();
		List<String> list = content.getStrlist();
		int localSelfVersion = 0;
		int NextServerVersion = 0;
		if (list.size() > 0) {
			localSelfVersion = Integer.valueOf(list.get(0).toString());
			int remoteSelfVersion = Integer.valueOf(list.get(1).toString());
			NextServerVersion = localSelfVersion > remoteSelfVersion ? localSelfVersion
					: remoteSelfVersion;
			// String nextSelfVersion=list.get(2).toString();
		}
		List<String> pathlist = readPathXML();
		String version = String.valueOf(content.getVersion());
		String path = "";
		String basePath = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/" + pathlist.get(1) + "/sync" + centerid
					+ localSelfVersion + ".xml";
			// oldPath = "/" + pathlist.get(1) + "/sync/sync" + centerid
			// + localSelfVersion + ".xml";
			basePath = "/" + pathlist.get(1) + "/sync";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = pathlist.get(0) + ":/sync" + centerid + localSelfVersion
					+ ".xml";
			// oldPath = getOldPath(pathlist.get(0), centerid,
			// localSelfVersion);
			basePath = pathlist.get(0) + ":/sync";

		}
		String oldPath = getOldPath(basePath, centerid, localSelfVersion);
		logger.info("上传写文件目录：    " + path);
		wirteXml(oldPath, oldXml);
		wirteXml(path, newXml);
		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("上传");
		Date d = new Date();
		logger.info(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		dataOperateRecord.setOperatetime(dateNowStr);
		dataOperateRecord.setCenterid(centerid);
		dataOperateRecord.setOperatorip(centerip);
		dataOperateRecord.setCentername(centername);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		// 修改资源记录data_source
		DataSource dataSource = new DataSource();
		dataSource.setCenterid(centerid);
		dataSource.setCentername(centername);
		dataSource.setCenterip(centerip);
		dataSource.setUpdatetime(dateNowStr);
		dataSource.setOldfileaddress(oldPath);
		dataSource.setFileaddress(path);
		dataSource.setVersion(localSelfVersion);
		DataSourceDao.getInstance().insert(dataSource);
		// 修改数据版本data_version
		DataVersionDao.getInstance().delete(centerid);
		DataVersion dataVersion = new DataVersion();
		dataVersion.setCenterid(centerid);
		dataVersion.setCenterip(centerip);
		dataVersion.setCentername(centername);
		dataVersion.setVersion(localSelfVersion);
		DataVersionDao.getInstance().insert(dataVersion);
		// 修改同步数据版本sync_data_version
		SyncDataVersion syncDataVersion = new SyncDataVersion();
		syncDataVersion.setCenterid(centerid);
		syncDataVersion.setCentername(centername);
		syncDataVersion.setCenterip(centerip);
		syncDataVersion.setVersion(localSelfVersion);
		SyncDataVersionDao.getInstance().insert(syncDataVersion);
		logger.info("上传服务器端成功。。。");
	}

	@Override
	public ModeAndType getModeAndType() {
		// TODO Auto-generated method stub
		return new ModeAndType(NormalMode.MODE, DBSyncType.TYPE);
		// return null;
	}

	public List<String> readPathXML() {
		ArrayList<String> list = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/etc/fxconf/Sync/SyncDataSet.xml";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = "D:\\SyncDataSet.xml";
		}
		try {
			doc = read.read(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != doc) {
			Element root = doc.getRootElement();
			Element url = root.element("syncwindows");
			Element time = root.element("synclinux");
			// Element dbAdress = root.element("autowindows");
			list.add(url.getStringValue());
			list.add(time.getStringValue());
			// list.add(dbAdress.getStringValue());
			// logger.info(list.get(0));
			// logger.info(list.get(1));
			// logger.info(list.get(2));
		}

		return list;

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
		String xml = "this is the xml!";
	}
}
