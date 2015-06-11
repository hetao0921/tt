package fxdigital.postserver.contentdispose.handlers.dbsync.autoserver;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.connection.SingleConnectionFactory;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.mqcore.util.BaseHeader;
import com.fxdigital.mqcore.util.BaseProtocol;
import com.fxdigital.mqcore.util.ByteArrayUtil;
import com.fxdigital.mqcore.util.Encoding;
import com.fxdigital.mqcore.util.Protocol;
import com.hibernate.bean.DataIncrementVersion;
import com.hibernate.bean.DataOperateRecord;
import com.hibernate.bean.DataSource;
import com.hibernate.bean.DataVersion;
import com.hibernate.bean.SyncDataVersion;

import fxdigital.db.LocalCenter;
import fxdigital.db.dao.JMSSendDao;
import fxdigital.db.dao.LocalCenterDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataIncrementVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataOperateRecordDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataSourceDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.pojo.MessageInfo;
import fxdigital.postserver.contentdispose.handlers.dbsync.service.LoadMessage;
import fxdigital.postserver.contentdispose.handlers.dbsync.service.MsgServerService;

public class ReceiverListener implements MessageListener {
	private Log logger = LogFactory.getLog(ReceiverListener.class);

	@Autowired
	private SingleConnectionFactory connectionFactory;

	private byte[] data = null;
	String upFlag = "-1";
	String loadFlag = "-1";

	String receiveFlag = "receiveFlag";

	MsgServerService msgServerService = null;

	// @Autowired
	// private Sync2PDao sync2PDao;

	// @Autowired
	// private SyncDao syncDao;

	@Autowired
	private JMSSendDao jMSSendDao;
	@Autowired
	LocalCenterDao localCenterDao;

	@Autowired
	@Qualifier("receiveQueue")
	private Destination destination;

	@Autowired
	@Qualifier("targetName")
	private Destination serverdestination;

	// 启动时侦听上级nvmp.serverQueue队列
	@PostConstruct
	public void startListen() {
		try {
			LocalCenter localCenter = localCenterDao.query();
			String flag = "";
			if (null != localCenter) {

				flag = "receiveId='" + localCenter.getId() + "'";

			}
			// flag= "" ;
			// flag="receiveId='receiveFlag'";
			// System.out.println("消息过滤标识" + flag);
			logger.info("消息过滤标识" + flag + "  "
					+ connectionFactory.getTargetConnectionFactory());

			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session
					.createConsumer(destination, flag);
			consumer.setMessageListener(this);
			// System.out.println("侦听开始。。。");
			logger.info("侦听开始。。。");
		} catch (JMSException e) {
			e.printStackTrace();
			logger.info("启动侦听出错:" + e);
		}
	}

	public void onMessage(Message message) {
		Msg msg = createMsg(message);
		String url = msg.get_Url();
		// System.out.println("服务器端反馈标识" + url);
		logger.info("服务器端反馈标识" + url);
		HashMap<String, Object> Params = msg.GetParams();
		if (url.equals("DBSynchronization.ServerQueueSendNowVerson")) {
			// System.out.println("接收到反馈回来的版本。。。");
			logger.info("接收到反馈回来的版本。。。");
			String versions = Params.get("versions").toString();
			String sessionid = Params.get("sessionid").toString();
			// System.out.println("hehehhe" + versions);
			logger.info("接收到反馈回来的版本" + versions);
			processLoadMsg(versions);
		}
		// 处理新服务器消息
		if (url.equals("DBSynchronization.NewServerQueueSendNowVerson")) {
			// System.out.println("接收到反馈回来的版本。。。");
			logger.info("接收到反馈回来的版本。。。");
			String versions = Params.get("versions").toString();
			String sessionid = Params.get("sessionid").toString();
			// System.out.println("hehehhe" + versions);
			logger.info("接收到反馈回来的版本" + versions);

			// the old process
			// processNewLoadMsg(versions);
			processNewUpLoadMsg(versions);

		}
		if (url.equals("DBSynchronization.ServerUpLoadOver")) {
			// System.out.println("上传完成。。。");
			String uuid = Params.get("uuid").toString();
			Integer verson = Integer.valueOf(Params.get("verson").toString());
			// System.out.println("回馈的版本号：" + verson);
			logger.info("回馈的版本号：" + verson);
			// sync2PDao.updateSelfSource(verson, uuid, 0);
			// String operate = "上传";
			// sync2PDao.unLock("", 0, operate);
			// System.out.println("解锁完毕");
			logger.info("解锁完毕");
			upFlag = "0";
			logger.info("上传完成。。。");
		}

		if (url.equals("DBSynchronization.ServerDownLoadOver")) {
			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centerid = null;
			String centername = null;
			String ip = null;
			if (null != Params.get("centerid")) {
				centerid = Params.get("centerid").toString();
			}
			if (null != Params.get("sessionid")) {
				centername = Params.get("sessionid").toString();
			}
			if (null != Params.get("ip")) {
				ip = Params.get("ip").toString();
			}

			verson = Integer.parseInt(Params.get("verson").toString());
			// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
			logger.info("下载中心   :" + centerid + "下载版本: " + verson);
			try {
				writeLocalxml(verson, centerid, centername, ip, uuid, data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("处理下载信息出错" + e);
			}

		}
		if (url.equals("DBSynchronization.NewServerDownLoadOver")) {

			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centername = Params.get("sessionid").toString();
			String ip = Params.get("ip").toString();
			String centerid = Params.get("centerid").toString();
			verson = Integer.parseInt(Params.get("verson").toString());
			// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
			logger.info("下载中心   :" + centerid + "下载版本: " + verson);
			try {
				writeNewIncrementXml(verson, centerid, centername, ip, uuid,
						data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("处理下载信息出错" + e);
			}

		}
	}

	public void writeNewIncrementXml(Integer version, String centerid,
			String centername, String centerip, String uuid, byte[] data) {

		logger.info("@@@@@@@@@@@@" + centerid);
		String allXml = Encoding.byteToString(data);
		HashMap<String, String> map = JSONObject.parseObject(allXml,
				HashMap.class);
		logger.info("map ---" + map);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		String incrementXml = map.get("incrementXml");
		msgServerService = new MsgServerService();
		List<String> pathlist = msgServerService.readPathXML();
		String path = "";
		String basePath = "";
		String oldPath = "";
		String incrementBasePath = "";
		String newPath = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/" + pathlist.get(1) + "/sync" + centerid + version
					+ ".xml";
			basePath = "/" + pathlist.get(1) + "/sync";
			incrementBasePath = "/" + pathlist.get(1) + "/increment";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = pathlist.get(0) + ":/sync" + centerid + version + ".xml";
			basePath = pathlist.get(0) + ":/sync";
			incrementBasePath = pathlist.get(0) + ":/increment";
		}
		newPath = path;
		oldPath = getOldPath(basePath, centerid, version);
		// 老版本数据已经插入,现插入新版本数据
		logger.info("newXml-------" + newXml);
		logger.info("oldXml-------" + oldXml);
		logger.info("incrementXml-------" + incrementXml);
		logger.info((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml)));
		if ((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml))) {
			if (null != newXml && !("").equals(newXml)) {
				logger.info("文件newPath写入地址:" + newPath);
				wirteXml(newPath, newXml);
			} else {
				newPath = "";
			}
			if (null != oldXml && !("").equals(oldXml)) {
				logger.info("文件oldPath写入地址:" + oldPath);
				logger.info("文件oldXml写入内容:" + oldXml);
				wirteXml(oldPath, oldXml);
			} else {
				oldPath = "";
			}

			// 修改操作记录data_operate_record
			DataOperateRecord dataOperateRecord = new DataOperateRecord();
			dataOperateRecord.setOperate("同步");
			Date d = new Date();
			logger.info("当前同步时间" + d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			dataSource.setFileaddress(newPath);
			dataSource.setOldfileaddress(oldPath);
			dataSource.setVersion(("".equals(version) || null == version) ? 0
					: Integer.valueOf(version));
			DataSourceDao.getInstance().insert(dataSource);
			// 修改数据版本data_version
			DataVersionDao.getInstance().delete(centerid);
			DataVersion dataVersion = new DataVersion();
			dataVersion.setCenterid(centerid);
			dataVersion.setCentername(centername);
			dataVersion.setCenterip(centerip);
			dataVersion.setVersion(("".equals(version) || null == version) ? 0
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
		if (null != incrementXml && !("").equals(incrementXml)) {
			int maxIncrementVersion = version;
			String incrementVersion = String.valueOf(version);
			int nextVersion = Integer.valueOf(incrementVersion);
			String xml = incrementXml;
			String incrementPath = getOldPath(incrementBasePath, centerid,
					Integer.valueOf(incrementVersion));
			wirteXml(incrementPath, xml);
			Date d = new Date();
			logger.info(d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			// 修改资源记录data_source
			DataSource dataSource = new DataSource();
			dataSource = new DataSource();
			dataSource.setCenterid(centerid);
			dataSource.setCentername(centername);
			dataSource.setCenterip(centerip);
			dataSource.setUpdatetime(dateNowStr);
			dataSource.setFileaddress(incrementPath);
			dataSource.setVersion(nextVersion);
			DataSourceDao.getInstance().insert(dataSource);
			SyncDataVersion syncDataVersion = new SyncDataVersion();
			// 修改同步数据版本sync_data_version
			syncDataVersion = new SyncDataVersion();
			syncDataVersion.setCenterid(centerid);
			syncDataVersion.setCentername(centername);
			syncDataVersion.setCenterip(centerip);
			syncDataVersion.setVersion(nextVersion);
			SyncDataVersionDao.getInstance().insert(syncDataVersion);

			// 修改数据版本data_version
			DataIncrementVersionDao.getInstance().delete(centerid);
			DataIncrementVersion dataIncrementVersion = new DataIncrementVersion();
			dataIncrementVersion.setCenterid(centerid);
			dataIncrementVersion.setCenterip(centerip);
			dataIncrementVersion.setCentername(centername);
			dataIncrementVersion.setVersion(maxIncrementVersion);
			DataIncrementVersionDao.getInstance().insert(dataIncrementVersion);
		}

	}

	public String getUpVersionInfo() {
		String versions = "";
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				versions += "{" + list.get(i).get("centerid") + ":"
						+ list.get(i).get("version") + ":"
						+ list.get(i).get("centername") + ":"
						+ list.get(i).get("centerip") + "}";
			}
		}
		return versions;
	}

	public byte[] getUpXmlInfo(String centerid, String version) {
		String xml = "";
		List<HashMap<String, String>> addressList = DataSourceDao.getInstance()
				.getSourceAddress(centerid, version);
		if (addressList.size() > 0) {
			// xmlMap=new HashMap<String, String>();
			String fileaddress = addressList.get(0).get("fileaddress");
			System.out.println("下载文件地址fileaddress   " + fileaddress);
			xml = LoadMessage.getInstance().readFileByLines(fileaddress);
			// logger.info("下载文件内容xml   " + xml);

		}
		byte[] data = Encoding.StringToByte(xml);
		return data;
	}

	public String getUpFlag() {
		// 上传标识:-1:正在上传 0:上传成功
		return upFlag;
	}

	public String getLoadFlag() {
		// 下载标识:-1:正在下载 0:下载成功
		return loadFlag;
	}

	public void processLoadMsg(String versions) {

		String version = "";
		// 服务器端返回版本

		LocalCenter localCenter = localCenterDao.query();
		String centerInfo = localCenter.getId();
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();

		String[] ver = versions.split("}");
		logger.info("得到需要给上级的 -------" + ver);
		version = getUptoLocal(list, ver);

		processLocalFile(list, ver);

		// System.out.println("组装后要下载的中心" + version);
		logger.info("组装后要下载的中心" + version);

		if (version.equals("")) {
			String reason = "没有最新版本，本次自动同步无数据。";
			return;
		}

		String receiveId = receiveFlag;

		String url = "DBSynchronization.ClientQueueGetData";
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		String centerid = String.valueOf(centerInfo);
		String ip = String.valueOf(localCenter.getIp());
		hp.put("sessionid", centerid);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", centerid);
		hp.put("versions", version);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		jMSSendDao.sendMessage(serverdestination, m, data, receiveId);
		logger.info("组装信息发送到中心成功" + version);

	}

	public void processNewLoadMsg(String versions) {

		String version = "";
		// 服务器端返回版本

		LocalCenter localCenter = localCenterDao.query();
		String centerInfo = localCenter.getId();
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();
		logger.info("服务器端发过的版本" + versions);
		String[] ver = versions.split("}");

		version = getNewUptoLocal(list, ver);

		processNewLocalFile(list, ver);

		// System.out.println("组装后要下载的中心" + version);
		logger.info("组装后要下载的中心" + version);

		if (version.equals("")) {
			String reason = "没有最新版本，本次自动同步无数据。";
			return;
		}

		String receiveId = receiveFlag;

		String url = "DBSynchronization.NewClientQueueGetData";
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		String centerid = String.valueOf(centerInfo);
		String ip = String.valueOf(localCenter.getIp());
		hp.put("sessionid", centerid);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", centerid);
		hp.put("versions", version);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		jMSSendDao.sendMessage(serverdestination, m, data, receiveId);
		logger.info("组装信息发送到中心成功" + version);

	}

	public void processNewUpLoadMsg(String versions) {
		LocalCenter localCenter = localCenterDao.query();
		String centerInfo = localCenter.getId();
		String version = processUpLoadMsg(versions);
		// System.out.println("组装后要下载的中心" + version);
		logger.info("组装后要下载的中心" + version);

		if (version.equals("")) {
			String reason = "没有最新版本，本次自动同步无数据。";
			return;
		}

		String receiveId = receiveFlag;

		String url = "DBSynchronization.NewClientQueueGetData";
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		String centerid = String.valueOf(centerInfo);
		String ip = String.valueOf(localCenter.getIp());
		hp.put("sessionid", centerid);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", centerid);
		hp.put("versions", version);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		jMSSendDao.sendMessage(serverdestination, m, data, receiveId);
		logger.info("组装信息发送到中心成功" + version);

	}

	public String processUpLoadMsg(String versions) {
		String version = "";
		// 服务器端返回版本
		if (null != versions && !("").equals(versions)) {
			MessageInfo messageInfo = JSONObject.parseObject(versions,
					MessageInfo.class);
			List<HashMap<String, String>> upAllList = messageInfo.getAllList();
			List<HashMap<String, String>> upIncrementList = messageInfo
					.getIncrementList();

			List<HashMap<String, String>> localAllList = DataVersionDao
					.getInstance().getAllServerSource();
			List<HashMap<String, String>> localIncrementList = DataIncrementVersionDao
					.getInstance().getAllServerSource();
			logger.info("传递过来的全量数据:upAllList" + upAllList);
			logger.info("传递过来的全量数据:localAllList" + localAllList);
			List<HashMap<String, String>> allUpList = processAllInfo(upAllList,
					localAllList);
			// 处理增量
			logger.info("传递过来的增量数据:upIncrementList" + upIncrementList);
			logger.info("传递过来的增量数据:localIncrementList" + localIncrementList);
			List<HashMap<String, String>> incrementUpList = processIncrementInfo(
					upIncrementList, localIncrementList);
			MessageInfo upMessageInfo = new MessageInfo();
			upMessageInfo.setAllList(allUpList);
			upMessageInfo.setIncrementList(incrementUpList);
			version = JSONObject.toJSONString(upMessageInfo);
		}
		return version;
	}

	public List<HashMap<String, String>> processAllInfo(
			List<HashMap<String, String>> upAllList,
			List<HashMap<String, String>> localAllList) {
		logger.info("将本级全量数据推送给上级。。。");
		// 处理全量
		List<HashMap<String, String>> allUpList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> allLoadList = LoadMessage.getInstance()
				.MergeLoadInfo(upAllList, localAllList);
		if (null != allLoadList && allLoadList.size() > 0) {
			for (int i = 0; i < allLoadList.size(); i++) {
				int clientVersion = Integer.valueOf(allLoadList.get(i).get(
						"clientversion"));
				int serverVersion = Integer.valueOf(allLoadList.get(i).get(
						"serverversion"));
				logger.info("本级中心客户端版本:" + clientVersion + " 服务器端版本: "
						+ serverVersion);
				if (clientVersion < serverVersion) {
					allUpList.add(allLoadList.get(i));
				} else if (clientVersion > serverVersion) {
					HashMap<String, String> map = allLoadList.get(i);
					String centerid = map.get("centerid");
					String version = map.get("clientversion");
					String centername = map.get("centername");
					String ip = map.get("centerip");
					logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
							+ "version: " + version);
					List<HashMap<String, String>> addressList = DataSourceDao
							.getInstance().getSourceAddress(centerid,
									String.valueOf(version));
					String xml = null;
					String oldxml = null;
					if (null != addressList && addressList.size() > 0) {
						// xmlMap=new HashMap<String, String>();
						String fileaddress = addressList.get(0).get(
								"fileaddress");
						String oldfileaddress = addressList.get(0).get(
								"oldfileaddress");
						System.out
								.println("下载文件地址fileaddress   " + fileaddress);
						xml = LoadMessage.getInstance().readFileByLines(
								fileaddress);
						oldxml = LoadMessage.getInstance().readFileByLines(
								oldfileaddress);

						logger.info("本级给上级的下载中心:" + centerid + "    下载xml文件: "
								+ fileaddress + "    下载oldxml文件: "
								+ oldfileaddress);
					}
					try {
						// 发送新版本数据
						HashMap<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("oldXml", oldxml);
						resultMap.put("newXml", xml);
						resultMap.put("incrementXml", null);
						logger.info("本级给上级的新下载中心:" + centerid + " "
								+ centername + " " + ip + " " + resultMap);
						String json = JSONObject.toJSONString(resultMap);
						byte[] newdata = Encoding.StringToByte(json);
						ClientServerQueueOldSend(centername, centerid, ip,
								centerid, version, newdata);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			logger.info("本级需要的中心数据： " + allUpList);

		}

		return allUpList;
	}

	public List<HashMap<String, String>> processIncrementInfo(
			List<HashMap<String, String>> upIncrementList,
			List<HashMap<String, String>> localIncrementList) {
		// 处理增量
		logger.info("将本级增量数据推送给上级。。。");
		List<HashMap<String, String>> incrementUpList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> incrementLoadList = LoadMessage
				.getInstance().MergeLoadInfo(upIncrementList,
						localIncrementList);
		int clientVersion = 0;
		int serverVersion = 0;
		if (null != incrementLoadList && incrementLoadList.size() > 0) {
			for (int i = 0; i < incrementLoadList.size(); i++) {
				clientVersion = Integer.valueOf(incrementLoadList.get(i).get(
						"clientversion"));
				serverVersion = Integer.valueOf(incrementLoadList.get(i).get(
						"serverversion"));
				if (clientVersion < serverVersion) {
					incrementUpList.add(incrementLoadList.get(i));
				} else if (clientVersion > serverVersion) {
					HashMap<String, String> map = incrementLoadList.get(i);
					String centerid = map.get("centerid");
					String centername = map.get("centername");
					String ip = map.get("centerip");
					for (int k = serverVersion + 1; k <= clientVersion; k++) {
						logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
								+ "version: " + k);
						List<HashMap<String, String>> addressList = DataSourceDao
								.getInstance().getIncrementServerSource(centerid,
										String.valueOf(k));
						String incrementXml = null;
						if (null != addressList && addressList.size() > 0) {
							// xmlMap=new HashMap<String, String>();
							String fileaddress = addressList.get(0).get(
									"fileaddress");
							System.out.println("下载文件地址fileaddress   "
									+ fileaddress);
							incrementXml = LoadMessage.getInstance()
									.readFileByLines(fileaddress);
							logger.info("本级给上级的下载中心:" + centerid
									+ "    下载incrementXml文件: " + fileaddress);
						}
						try {
							// 发送新版本数据
							HashMap<String, String> resultMap = new HashMap<String, String>();
							resultMap.put("oldXml", null);
							resultMap.put("newXml", null);
							resultMap.put("incrementXml", incrementXml);
							logger.info("本级给上级的新下载中心:" + centerid + " "
									+ centername + " " + ip + " " + resultMap);
							String json = JSONObject.toJSONString(resultMap);
							byte[] newdata = Encoding.StringToByte(json);
							ClientServerQueueOldSend(centername, centerid, ip,
									centerid, String.valueOf(k), newdata);
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
			logger.info("本级需要的中心数据： " + incrementUpList);
		}
		return incrementUpList;
	}

	public String getUptoLocal(List<HashMap<String, String>> list, String[] ver) {
		String version = "";
		logger.info("ver length"+ver.length);
		for (int i = 0; i < ver.length; i++) {
			logger.info("ver length"+ver[i]);
			if(!("").equals(ver[i])){
				String[] cenver = ver[i].substring(1).split(":");
				int index = getVersionIndex(list, cenver);
				if (index == -1
						|| Integer.valueOf(cenver[1]) > Integer.valueOf(list.get(
								index).get("version"))) {
					version += "{" + cenver[0] + ":" + cenver[1] + "}";
				}
			}
			
		}
		logger.info("得到要从服务器上下载的版本信息: " + version);
		return version;
	}

	public String getNewUptoLocal(List<HashMap<String, String>> list,
			String[] ver) {
		String version = "";
		logger.info("ver  " + ver);
		logger.info("list  " + list);
		for (int i = 0; i < ver.length; i++) {
			logger.info("ver[i]" + i + ver[i]);
			String[] cenver = ver[i].substring(1).split(":");
			int index = getVersionIndex(list, cenver);
			if (index == -1
					|| Integer.valueOf(cenver[1]) > Integer.valueOf(list.get(
							index).get("version"))) {
				if (cenver.length > 2) {
					// 拿到需要下载的所有全量版本（向服务器询问时需要询问增量版本）
					version += "{" + cenver[0] + ":" + cenver[1] + ":"
							+ cenver[2] + ":" + cenver[3] + ":" + cenver[4]
							+ ":" + "all" + "}";
				} else {
					version += "{" + cenver[0] + ":" + cenver[1] + "}";
				}

			} else if (list.get(index).get("version").equals(cenver[1])) {
				logger.info("相等版本，只看增量。。。。");
				// 拿到所有的增量版本，仅仅是增量版本
				String centerid = cenver[0];
				String incrementVersion = cenver[4];
				String localIncrementVersion = null;
				logger.info("服务器上全量版本:centerid: " + centerid + " 本地全量版本： "
						+ list.get(index).get("version") + " 服务器上全量版本: "
						+ cenver[1]);
				List<HashMap<String, String>> incrementList = DataIncrementVersionDao
						.getInstance().getOneVersion(centerid);
				if (null == incrementVersion || ("").equals(incrementVersion)) {
					incrementVersion = "0";
				}
				if (null != incrementList && incrementList.size() > 0) {
					localIncrementVersion = incrementList.get(0).get("version");
				} else {
					localIncrementVersion = "0";
				}
				logger.info("本地增量版本:centerid: " + centerid + " 本地增量版本： "
						+ localIncrementVersion + " 服务器上增量版本: "
						+ incrementVersion);
				if (Integer.valueOf(localIncrementVersion) < Integer
						.valueOf(incrementVersion)) {
					version += "{" + cenver[0] + ":" + cenver[1] + ":"
							+ cenver[2] + ":" + cenver[3] + ":"
							+ Integer.valueOf(localIncrementVersion) + ":"
							+ "increment" + "}";
				}

			}

		}
		logger.info("得到要从服务器上下载的版本信息: " + version);
		return version;
	}

	// 将本级同步服务器上文件发送给上级，让上级保存起来
	public void processLocalFile(List<HashMap<String, String>> list,
			String[] ver) {
		for (int k = 0; k < list.size(); k++) {
			HashMap<String, String> map = list.get(k);
			int index = getLocalIndex(ver, map);
			if (index == -1
					|| Integer.valueOf(map.get("version")) > Integer
							.valueOf(ver[index].substring(1).split(":")[1])) {
				logger.info("本级给上级的map: " + map);
				String centerid = map.get("centerid");
				String version = map.get("version");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
						+ "version: " + version);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getSourceAddress(centerid,
								String.valueOf(version));
				String xml = null;
				String oldxml = null;
				List<HashMap<String, String>> incrementList = null;
				// 得到增量的xml
				MsgServerService msgServerService = new MsgServerService();
				incrementList = msgServerService.getIncrementXml(centerid, "0",
						version);
				if (addressList.size() > 0) {
					// xmlMap=new HashMap<String, String>();
					String fileaddress = addressList.get(0).get("fileaddress");
					String oldfileaddress = addressList.get(0).get(
							"oldfileaddress");
					System.out.println("下载文件地址fileaddress   " + fileaddress);
					xml = LoadMessage.getInstance()
							.readFileByLines(fileaddress);
					oldxml = LoadMessage.getInstance().readFileByLines(
							oldfileaddress);

					logger.info("本级给上级的下载中心:" + centerid + "    下载xml文件: "
							+ fileaddress + "    下载oldxml文件: " + oldfileaddress
							+ "下载增量文件 ： " + incrementList);

					// logger.info("下载文件内容xml   " + xml);

				}
				byte[] data = Encoding.StringToByte(oldxml);
				try {
					logger.info("本级给上级的旧下载中心:" + centerid + " " + centername
							+ " " + ip + " " + oldxml);
					// 发送给老版本服务器
					ClientServerQueueSend(centername, centerid, ip, centerid,
							version, data);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 将本级同步服务器上文件发送给上级，让上级保存起来
	public void processNewLocalFile(List<HashMap<String, String>> list,
			String[] ver) {
		for (int k = 0; k < list.size(); k++) {
			HashMap<String, String> map = list.get(k);
			int index = getLocalIndex(ver, map);
			if (index == -1
					|| Integer.valueOf(map.get("version")) > Integer
							.valueOf(ver[index].substring(1).split(":")[1])) {
				logger.info("本级给上级的map: " + map);
				String centerid = map.get("centerid");
				String version = map.get("version");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
						+ "version: " + version);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getSourceAddress(centerid,
								String.valueOf(version));
				String xml = null;
				String oldxml = null;
				List<HashMap<String, String>> incrementList = null;
				// 得到增量的xml
				MsgServerService msgServerService = new MsgServerService();
				incrementList = msgServerService.getIncrementXml(centerid, "0",
						version);
				if (null != addressList && addressList.size() > 0) {
					// xmlMap=new HashMap<String, String>();
					String fileaddress = addressList.get(0).get("fileaddress");
					String oldfileaddress = addressList.get(0).get(
							"oldfileaddress");
					System.out.println("下载文件地址fileaddress   " + fileaddress);
					xml = LoadMessage.getInstance()
							.readFileByLines(fileaddress);
					oldxml = LoadMessage.getInstance().readFileByLines(
							oldfileaddress);

					logger.info("本级给上级的下载中心:" + centerid + "    下载xml文件: "
							+ fileaddress + "    下载oldxml文件: " + oldfileaddress
							+ "下载增量文件 ： " + incrementList);
				}
				try {
					// 发送新版本数据
					HashMap<String, String> resultMap = new HashMap<String, String>();
					String incrementJson = JSONObject
							.toJSONString(incrementList);
					resultMap.put("oldXml", oldxml);
					resultMap.put("newXml", xml);
					resultMap.put("incrementXml", incrementJson);
					logger.info("本级给上级的新下载中心:" + centerid + " " + centername
							+ " " + ip + " " + resultMap);
					String json = JSONObject.toJSONString(resultMap);
					byte[] newdata = Encoding.StringToByte(json);
					ClientServerQueueOldSend(centername, centerid, ip,
							centerid, version, newdata);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (Integer.valueOf(map.get("version")) == Integer
					.valueOf(ver[index].substring(1).split(":")[1])) {
				String centerid = map.get("centerid");
				String version = map.get("version");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
						+ "version: " + version);
				logger.info("本级给上级的map: " + map);
				String increment = ver[index].substring(1).split(":")[4];
				logger.info("上级的增量版本: " + increment);
				List<HashMap<String, String>> incrementList = DataIncrementVersionDao
						.getInstance().getOneVersion(centerid);
				String incrementVersion = "0";
				if (null != incrementList && incrementList.size() > 0) {
					incrementVersion = incrementList.get(0).get("version");
				}
				logger.info("本级的增量版本: " + incrementVersion);
				// 如果本级版本大于上级版本则做处理
				if (Integer.valueOf(incrementVersion) > Integer
						.valueOf(increment)) {

					List<HashMap<String, String>> localincrementList = null;
					// 得到增量的xml，从服务器版本-本级版本
					MsgServerService msgServerService = new MsgServerService();
					localincrementList = msgServerService.getIncrementXml(
							centerid, increment, incrementVersion);
					if (null != localincrementList
							&& localincrementList.size() > 0) {
						// 发送新版本数据
						HashMap<String, String> resultMap = new HashMap<String, String>();
						String incrementJson = JSONObject
								.toJSONString(localincrementList);
						resultMap.put("oldXml", null);
						resultMap.put("newXml", null);
						resultMap.put("incrementXml", incrementJson);
						String json = JSONObject.toJSONString(resultMap);
						byte[] newdata = Encoding.StringToByte(json);
						try {
							ClientServerQueueOldSend(centername, centerid, ip,
									centerid, version, newdata);
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
		}

	}

	/**
	 * 是否存在当前map
	 * 
	 * 
	 * @param uuid
	 * @return
	 */
	public int getLocalIndex(String[] ver, HashMap<String, String> map) {
		int index = -1;
		for (int i = 0; i < ver.length; i++) {
			if(!("").equals(ver[i])){
				String[] cenver = ver[i].substring(1).split(":");
				if (map.get("centerid").equals(cenver[0])) {
					index = i;
				}
			}
		}
		return index;
	}

	public int getVersionIndex(List<HashMap<String, String>> list,
			String[] cenver) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (cenver[0].equals(list.get(i).get("centerid"))) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * 特殊客户端发送请求,服务器端接受该信息后，进行直接保存。 客户端发送同步数据,包含了中心ID，操作人ID，操作人IP和一个xml文件流
	 * 
	 * @throws JMSException
	 * */
	public void ClientServerQueueSend(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.ClientServerQueueSend";
		String receiveId = receiveFlag;
		// 参数 ：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("centerid", centerid);
		hp.put("sessionid", sessionid);
		hp.put("ip", ip);
		hp.put("uuid", uuid);
		hp.put("versions", versions);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		jMSSendDao.sendMessage(serverdestination, m, data, centerid);
	}

	public void ClientServerQueueOldSend(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.SendOldQueueString";
		String receiveId = receiveFlag;
		// 参数 ：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("centerid", centerid);
		hp.put("sessionid", sessionid);
		hp.put("ip", ip);
		hp.put("uuid", uuid);
		hp.put("versions", versions);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		jMSSendDao.sendMessage(serverdestination, m, data, centerid);
	}

	private Msg createMsg(Message message) {

		int n = 0;
		data = null;
		Msg mg = null;
		if (message instanceof BytesMessage) {
			try {
				BytesMessage bmg = (BytesMessage) message;
				/**
				 * 将信息进行分解，3个部分，包头，包体 ，数据。
				 * */

				n = bmg.getIntProperty("Length");
				// 文本写入，接收大小 n

				byte[] array = new byte[n];
				bmg.readBytes(array);

				byte[] h = ByteArrayUtil.Trim(array, 0, BaseProtocol.HeadSize);
				if (h == null) {
					System.out.println("头文件解析错误，此次操作失败");
					mg = null;
				}

				BaseHeader header = new BaseHeader(h);
				if (!header.ValidateMagic()) {
					System.out.println("头文件校验错误，此次操作失败");
					mg = null;
				}

				// 如果是正常处理。
				if (header.getType() == Protocol.RpcMessage) {
					byte[] body = ByteArrayUtil.Trim(array,
							BaseProtocol.HeadSize, header.getBodyLength());

					mg = new Msg(Encoding.byteToString(body));
					data = ByteArrayUtil.Trim(array, BaseProtocol.HeadSize
							+ header.getBodyLength(), array.length
							- BaseProtocol.HeadSize - header.getBodyLength());

				}

				// 计算处理时间

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mg;
	}

	public SingleConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(SingleConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
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

	public void writeNewxml(Integer version, String centerid,
			String centername, String centerip, String uuid, byte[] data) {
		logger.info("@@@@@@@@@@@@" + centerid);
		String allXml = Encoding.byteToString(data);
		HashMap<String, String> map = JSONObject.parseObject(allXml,
				HashMap.class);
		logger.info("map ---" + map);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		String incrementXml = map.get("incrementXml");
		List<JSONObject> incrementList = JSONObject.parseObject(incrementXml,
				List.class);
		logger.info("incrementList ---" + incrementList);
		msgServerService = new MsgServerService();
		List<String> pathlist = msgServerService.readPathXML();
		String path = "";
		String basePath = "";
		String oldPath = "";
		String incrementBasePath = "";
		String newPath = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/" + pathlist.get(1) + "/sync" + centerid + version
					+ ".xml";
			basePath = "/" + pathlist.get(1) + "/sync";
			incrementBasePath = "/" + pathlist.get(1) + "/increment";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = pathlist.get(0) + ":/sync" + centerid + version + ".xml";
			basePath = pathlist.get(0) + ":/sync";
			incrementBasePath = pathlist.get(0) + ":/increment";
		}
		newPath = path;
		oldPath = getOldPath(basePath, centerid, version);
		// 老版本数据已经插入,现插入新版本数据
		logger.info("newXml-------" + newXml);
		logger.info("oldXml-------" + oldXml);
		logger.info("incrementList-------" + incrementList);
		logger.info((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml)));
		if ((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml))) {
			if (null != newXml && !("").equals(newXml)) {
				logger.info("文件newPath写入地址:" + newPath);
				wirteXml(newPath, newXml);
			} else {
				newPath = "";
			}
			if (null != oldXml && !("").equals(oldXml)) {
				logger.info("文件oldPath写入地址:" + oldPath);
				logger.info("文件oldXml写入内容:" + oldXml);
				wirteXml(oldPath, oldXml);
			} else {
				oldPath = "";
			}

			// 修改操作记录data_operate_record
			DataOperateRecord dataOperateRecord = new DataOperateRecord();
			dataOperateRecord.setOperate("同步");
			Date d = new Date();
			logger.info("当前同步时间" + d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			dataSource.setFileaddress(newPath);
			dataSource.setOldfileaddress(oldPath);
			dataSource.setVersion(("".equals(version) || null == version) ? 0
					: Integer.valueOf(version));
			DataSourceDao.getInstance().insert(dataSource);
			// 修改数据版本data_version
			DataVersionDao.getInstance().delete(centerid);
			DataVersion dataVersion = new DataVersion();
			dataVersion.setCenterid(centerid);
			dataVersion.setCentername(centername);
			dataVersion.setCenterip(centerip);
			dataVersion.setVersion(("".equals(version) || null == version) ? 0
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

		if (null != incrementList && incrementList.size() > 0) {
			int maxIncrementVersion = Integer.valueOf(incrementList
					.get(incrementList.size() - 1).get("version").toString());
			for (int i = 0; i < incrementList.size(); i++) {
				String incrementVersion = incrementList.get(i).get("version")
						.toString();
				int nextVersion = Integer.valueOf(incrementVersion);
				String xml = incrementList.get(i).get("xml").toString();
				String incrementPath = getOldPath(incrementBasePath, centerid,
						Integer.valueOf(incrementVersion));
				wirteXml(incrementPath, xml);
				Date d = new Date();
				logger.info(d);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				// 修改资源记录data_source
				DataSource dataSource = new DataSource();
				dataSource = new DataSource();
				dataSource.setCenterid(centerid);
				dataSource.setCentername(centername);
				dataSource.setCenterip(centerip);
				dataSource.setUpdatetime(dateNowStr);
				dataSource.setFileaddress(incrementPath);
				dataSource.setVersion(nextVersion);
				DataSourceDao.getInstance().insert(dataSource);
				SyncDataVersion syncDataVersion = new SyncDataVersion();
				// 修改同步数据版本sync_data_version
				syncDataVersion = new SyncDataVersion();
				syncDataVersion.setCenterid(centerid);
				syncDataVersion.setCentername(centername);
				syncDataVersion.setCenterip(centerip);
				syncDataVersion.setVersion(nextVersion);
				SyncDataVersionDao.getInstance().insert(syncDataVersion);
			}
			// 修改数据版本data_version
			DataIncrementVersionDao.getInstance().delete(centerid);
			DataIncrementVersion dataIncrementVersion = new DataIncrementVersion();
			dataIncrementVersion.setCenterid(centerid);
			dataIncrementVersion.setCenterip(centerip);
			dataIncrementVersion.setCentername(centername);
			dataIncrementVersion.setVersion(maxIncrementVersion);
			DataIncrementVersionDao.getInstance().insert(dataIncrementVersion);

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

	public void writeLocalxml(Integer verson, String centerid,
			String centername, String centerip, String uuid, byte[] data) {

		// super.OnServerDownLoadOverEvent(verson, centerid, uuid, data);
		// //System.out.println("@@@@@@@@@@@@" + centerid);
		logger.info("@@@@@@@@@@@@" + centerid);
		String xml = Encoding.byteToString(data);

		String version = String.valueOf(verson);
		String path = "";
		String basePath = "";
		msgServerService = new MsgServerService();
		List<String> pathlist = msgServerService.readPathXML();
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			basePath = "/" + pathlist.get(1) + "/sync";
			// path = "/" + pathlist.get(1) + "/sync" + centerid + version
			// + ".xml";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			basePath = pathlist.get(0) + ":/sync";
			// path = pathlist.get(0) + ":/sync" + centerid + version + ".xml";
		}
		path = getOldPath(basePath, centerid, verson);
		wirteXml(path, xml);
		logger.info("文件写入地址:" + path);
		logger.info("文件写入内容:" + xml);

		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("同步");
		Date d = new Date();
		logger.info("当前同步时间" + d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		dataSource.setOldfileaddress(path);
		dataSource.setVersion(("".equals(version) || null == version) ? 0
				: Integer.valueOf(version));
		DataSourceDao.getInstance().insert(dataSource);
		// 修改数据版本data_version
		DataVersionDao.getInstance().delete(centerid);
		DataVersion dataVersion = new DataVersion();
		dataVersion.setCenterid(centerid);
		dataVersion.setCentername(centername);
		dataVersion.setCenterip(centerip);
		dataVersion.setVersion(("".equals(version) || null == version) ? 0
				: Integer.valueOf(version));
		DataVersionDao.getInstance().insert(dataVersion);
		// 修改同步数据版本sync_data_version
		SyncDataVersion syncDataVersion = new SyncDataVersion();
		syncDataVersion.setCenterid(centerid);
		syncDataVersion.setCentername(centername);
		syncDataVersion.setCenterip(centerip);
		syncDataVersion.setVersion(("".equals(version) || null == version) ? 0
				: Integer.valueOf(version));
		SyncDataVersionDao.getInstance().insert(syncDataVersion);

	}

}
