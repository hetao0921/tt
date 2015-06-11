package fxdigital.postserver.contentdispose.handlers.dbsync.autoserver;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
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

import fxdigital.db.dao.JMSSendDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataIncrementVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataOperateRecordDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataSourceDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataVersionDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.pojo.MessageInfo;
import fxdigital.postserver.contentdispose.handlers.dbsync.service.LoadMessage;
import fxdigital.postserver.contentdispose.handlers.dbsync.service.MsgServerService;

public class LocalReceiverListener implements MessageListener {

	
	
	
	private Log logger = LogFactory.getLog(LocalReceiverListener.class);

	String receiveFlag = "receiveFlag";

	private byte[] data = null;

	MsgServerService msgServerService = null;

	@Autowired
	private SingleConnectionFactory localConnectionFactory;

	@Autowired
	@Qualifier("receiveQueue")
	private Destination destination;

	@Autowired
	private JMSSendDao jMSSendDao;

	@Autowired
	@Qualifier("targetName")
	private Destination serverdestination;

	// 启动时侦听本级nvmp.clientQueue队列
	@PostConstruct
	public void startListenLocal() {
		try {

			String flag = "";
			String orFlag = "";

			// flag = "receiveId='" + receiveFlag + "' OR '"+orFlag+"'";

			// flag= "" ;
			flag = "receiveId='receiveFlag'";
			// System.out.println("消息过滤标识" + flag);
			logger.info("消息过滤标识-----------" + flag + "  ");

			Connection connection = localConnectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session
					.createConsumer(serverdestination);
			consumer.setMessageListener(this);
			// System.out.println("侦听开始。。。");
			logger.info("侦听开始。。。");
		} catch (JMSException e) {
			e.printStackTrace();
			logger.info("启动侦听出错:" + e);
		}
	}

	@Override
	public void onMessage(Message message) {

		// 判断消息来源的新旧

		// TODO Auto-generated method stub
		Msg msg = createMsg(message);
		String url = msg.get_Url();
		// System.out.println("服务器端反馈标识" + url);
		logger.info("本级服务器端反馈标识----" + url);
		HashMap<String, Object> Params = msg.GetParams();

		// 上级处理
		if (url.equals("DBSynchronization.ClientQueueNowVerson")) {
			logger.info("上级服务器收到传递过来的消息，将所有版本信息发给下级");
			String receiveId = receiveFlag;
			String sessionid = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			String ip = null;
			if (null != Params.get("ip")) {
				ip = Params.get("ip").toString();
			}
			// 老服务器发来同步消息
			logger.info("是否是新服务器： " + Params.get("centertype"));
			if (null == Params.get("centertype")) {
				logger.info("老服务器发来同步消息" + centerid);
				String oldversion = getOldUpVersionInfo(centerid);
				String oldUrl = "DBSynchronization.ServerQueueSendNowVerson";
				// 参数：
				HashMap<String, Object> oldhp = new HashMap<String, Object>();
				oldhp.put("sessionid", sessionid);
				oldhp.put("centerid", centerid);
				oldhp.put("uuid", centerid);
				oldhp.put("ip", ip);
				oldhp.put("versions", oldversion);

				Msg oldm = new Msg();
				oldm.set_Url(oldUrl);
				oldm.AddParams(oldhp);
				jMSSendDao.sendLocalMessage(destination, oldm, data, centerid);

			} else if (Params.get("centertype").toString().equals("new")) {
				logger.info("新服务器发来同步消息" + centerid);
				String localUrl = "DBSynchronization.NewServerQueueSendNowVerson";
				// new old
				// the old process
				// String version = getUpVersionInfo();
				String version = getNewUpVersionInfo();

				logger.info("上级服务器获取的版本字符串:   " + version);
				// 参数：
				HashMap<String, Object> hp = new HashMap<String, Object>();
				hp.put("sessionid", sessionid);
				hp.put("centerid", centerid);
				hp.put("uuid", centerid);
				hp.put("ip", ip);
				hp.put("versions", version);

				Msg m = new Msg();
				m.set_Url(localUrl);
				m.AddParams(hp);
				jMSSendDao.sendLocalMessage(destination, m, data, centerid);
			}
		}
		// // 上级处理
		if (url.equals("DBSynchronization.ClientQueueGetData")) {
			logger.info("上级处理 " + url);
			String versions = Params.get("versions").toString();
			logger.info("接收到反馈回来的版本。。。" + versions);
			String localcenterid = Params.get("centerid").toString();
			String[] ver = versions.split("}");
			if (null != ver) {
				for (int i = 0; i < ver.length; i++) {
					String centerid = ver[i].substring(1).split(":")[0];
					String version = ver[i].substring(1).split(":")[1];
					// String centername = ver[i].substring(1).split(":")[2];
					// String ip = ver[i].substring(1).split(":")[3];
					byte[] data = getUpXmlInfo(centerid, version);
					String localUrl = "DBSynchronization.ServerDownLoadOver";
					String receiveId = receiveFlag;
					// 参数：
					HashMap<String, Object> hp = new HashMap<String, Object>();
					hp.put("sessionid", centerid);
					hp.put("centerid", centerid);
					// hp.put("ip", ip);
					hp.put("uuid", centerid);
					hp.put("verson", version);

					Msg m = new Msg();
					m.set_Url(localUrl);
					m.AddParams(hp);
					logger.info("发送版本数据" + version);
					jMSSendDao.sendLocalMessage(destination, m, data,
							localcenterid);

				}
			}

		}
		if (url.equals("DBSynchronization.NewClientQueueGetData")) {

			logger.info("接收到反馈回来的版本。。。");
			logger.info("上级处理 " + url);
			String versions = Params.get("versions").toString();
			String localcenterid = Params.get("centerid").toString();
			// String[] ver = versions.split("}");
			// if (null != ver) {
			// for (int i = 0; i < ver.length; i++) {
			// String centerid = ver[i].substring(1).split(":")[0];
			// String version = ver[i].substring(1).split(":")[1];
			// String centername = ver[i].substring(1).split(":")[2];
			// String ip = ver[i].substring(1).split(":")[3];
			// String incrementVersion = ver[i].substring(1).split(":")[4];
			// String datatype = ver[i].substring(1).split(":")[5];
			// // byte[] data = getUpXmlInfo(centerid, version);
			// byte[] newData = getNewXmlInfo(centerid, version,
			// centername, ip, incrementVersion, datatype);
			// sendNewDataInfo(localcenterid, centerid, version,
			// centername, ip, newData);
			//
			// }
			// }

			processSubData(localcenterid, versions);

		}

		// 上级处理
		if (url.equals("DBSynchronization.ClientServerQueueSend")) {
			logger.info("上级处理 " + url);
			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centerid = Params.get("centerid").toString();
			String centername = Params.get("sessionid").toString();
			String ip = Params.get("ip").toString();
			verson = Integer.parseInt(Params.get("versions").toString());
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
		// 上级处理
		if (url.equals("DBSynchronization.SendOldQueueString")) {
			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centername = Params.get("sessionid").toString();
			String ip = Params.get("ip").toString();
			String centerid = Params.get("centerid").toString();
			verson = Integer.parseInt(Params.get("versions").toString());
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

	public void sendNewDataInfo(String localcenterid, String centerid,
			String version, String centername, String ip, byte[] newData) {
		String localUrl = "DBSynchronization.NewServerDownLoadOver";
		String receiveId = receiveFlag;
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("sessionid", centername);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", centerid);
		hp.put("verson", version);

		Msg m = new Msg();
		m.set_Url(localUrl);
		m.AddParams(hp);
		logger.info("发送版本数据" + version);

		jMSSendDao.sendLocalMessage(destination, m, newData, localcenterid);
	}

	// 处理下级传过来看版本信息
	public void processSubData(String localcenterid, String version) {
		if (null != version && !("").equals(version)) {
			MessageInfo messageInfo = JSONObject.parseObject(version,
					MessageInfo.class);
			List<HashMap<String, String>> allList = messageInfo.getAllList();
			List<HashMap<String, String>> incrementList = messageInfo
					.getIncrementList();
			logger.info("拼接后的全量版本数据:" + allList);
			logger.info("拼接后的增量版本数据:" + incrementList);
			processSubAllData(localcenterid, allList);
			processSubIncrementData(localcenterid, incrementList);

		}
	}

	public void processSubAllData(String localcenterid,
			List<HashMap<String, String>> allList) {
		if (null != allList && allList.size() > 0) {
			for (int i = 0; i < allList.size(); i++) {
				HashMap<String, String> map = allList.get(i);
				logger.info("当前处理的下级全量版本数据：" + map);
				String centerid = map.get("centerid");
				String clientversion = map.get("clientversion");
				String serverversion = map.get("serverversion");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
						+ "version: " + clientversion);

				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getSourceAddress(centerid,
								serverversion);
				String xml = null;
				String oldxml = null;
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
							+ fileaddress + "    下载oldxml文件: " + oldfileaddress);
				}
				try {
					// 发送新版本数据
					HashMap<String, String> resultMap = new HashMap<String, String>();
					resultMap.put("oldXml", oldxml);
					resultMap.put("newXml", xml);
					resultMap.put("incrementXml", null);
					logger.info("本级给上级的新下载中心:" + centerid + " " + centername
							+ " " + ip + " " + resultMap);
					String json = JSONObject.toJSONString(resultMap);
					byte[] newdata = Encoding.StringToByte(json);
					sendNewDataInfo(localcenterid, centerid,serverversion,
							centername, ip, newdata);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public void processSubIncrementData(String localcenterid,
			List<HashMap<String, String>> incrementList) {
		if (null != incrementList && incrementList.size() > 0) {
			for (int i = 0; i < incrementList.size(); i++) {
				HashMap<String, String> map = incrementList.get(i);
				logger.info("当前处理的下级增量版本数据：" + map);
				String centerid = map.get("centerid");
				String clientversion = map.get("clientversion");
				String serverversion = map.get("serverversion");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				for (int k = Integer.valueOf(clientversion) + 1; k <= Integer
						.valueOf(serverversion); k++) {
					logger.info("本级同步服务器上文件发送给上级 centerid:" + centerid
							+ "version: " + clientversion);
					List<HashMap<String, String>> addressList = DataSourceDao
							.getInstance().getIncrementServerSource(centerid,
									String.valueOf(k));
					String incrementXml = null;
					if (null != addressList && addressList.size() > 0) {
						// xmlMap=new HashMap<String, String>();
						String fileaddress = addressList.get(0).get(
								"fileaddress");
						System.out
								.println("下载文件地址fileaddress   " + fileaddress);
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
						sendNewDataInfo(localcenterid, centerid, String.valueOf(k),
								centername, ip, newdata);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

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

	public String getUpVersionInfo() {
		String versions = "";
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String centerid = list.get(i).get("centerid");
				List<HashMap<String, String>> incrementList = DataIncrementVersionDao
						.getInstance().getOneVersion(centerid);
				String incrementVersion = "0";
				if (null != incrementList && incrementList.size() > 0) {
					incrementVersion = incrementList.get(0).get("version");
				}
				versions += "{" + list.get(i).get("centerid") + ":"
						+ list.get(i).get("version") + ":"
						+ list.get(i).get("centername") + ":"
						+ list.get(i).get("centerip") + ":" + incrementVersion
						+ "}";
			}
		}
		return versions;
	}

	public String getNewUpVersionInfo() {
		String versions = "";
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();
		List<HashMap<String, String>> incrementList = DataIncrementVersionDao
				.getInstance().getAllServerSource();
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setAllList(list);
		messageInfo.setIncrementList(incrementList);
		versions = JSONObject.toJSONString(messageInfo);
		return versions;
	}

	public String getOldUpVersionInfo(String centerid) {
		String versions = "";
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getNoOneSource(centerid);
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				versions += "{" + list.get(i).get("centerid") + ":"
						+ list.get(i).get("version") + ":" + "}";
			}
		}
		return versions;
	}

	public byte[] getUpXmlInfo(String centerid, String version) {
		String xml = "";
		String oldxml = null;
		List<HashMap<String, String>> incrementList = null;
		List<HashMap<String, String>> addressList = DataSourceDao.getInstance()
				.getSourceAddress(centerid, version);
		if (addressList.size() > 0) {
			// xmlMap=new HashMap<String, String>();
			String fileaddress = addressList.get(0).get("oldfileaddress");
			System.out.println("下载文件地址fileaddress   " + fileaddress);
			xml = LoadMessage.getInstance().readFileByLines(fileaddress);
		}
		byte[] data = Encoding.StringToByte(xml);
		return data;
	}

	public byte[] getNewXmlInfo(String centerid, String version,
			String centername, String ip, String incrementVersion,
			String datatype) {
		String xml = "";
		String json = "";
		String oldxml = null;
		List<HashMap<String, String>> incrementList = null;
		List<HashMap<String, String>> addressList = DataSourceDao.getInstance()
				.getSourceAddress(centerid, version);

		if (datatype.equals("all")) {
			if (null != addressList && addressList.size() > 0) {
				// xmlMap=new HashMap<String, String>();
				// 得到增量的xml
				MsgServerService msgServerService = new MsgServerService();
				incrementList = msgServerService.getIncrementXml(centerid, "0",
						version);
				String fileaddress = addressList.get(0).get("fileaddress");
				System.out.println("下载文件地址fileaddress   " + fileaddress);
				xml = LoadMessage.getInstance().readFileByLines(fileaddress);
				// logger.info("下载文件内容xml   " + xml);
				String oldfileaddress = addressList.get(0)
						.get("oldfileaddress");
				System.out.println("下载文件地址oldfileaddress   " + oldfileaddress);
				oldxml = LoadMessage.getInstance().readFileByLines(
						oldfileaddress);

				logger.info("本级给上级的下载中心:" + centerid + "    下载xml文件: "
						+ fileaddress + "    下载oldxml文件: " + oldfileaddress
						+ "下载增量文件 ： " + incrementList);

				// logger.info("下载文件内容xml   " + xml);

			}

			// 发送新版本数据
			HashMap<String, String> resultMap = new HashMap<String, String>();
			String incrementJson = JSONObject.toJSONString(incrementList);
			resultMap.put("oldXml", oldxml);
			resultMap.put("newXml", xml);
			resultMap.put("incrementXml", incrementJson);
			json = JSONObject.toJSONString(resultMap);
		} else {
			// 发送新版本数据
			// 得到增量的xml
			MsgServerService msgServerService = new MsgServerService();
			// 如果本地增量(incrementVersion)为零，则从全量开始计量
			// 否则从客户端增量-服务器端增量
			logger.info("客户端增量版本号为:centerid :" + centerid + " version: "
					+ version + " incrementVersion: " + incrementVersion);
			if (incrementVersion.equals("0")) {
				incrementList = msgServerService.getIncrementXml(centerid, "0",
						version);
			} else {
				incrementList = msgServerService.getIncrementXml(centerid,
						incrementVersion, version);
			}

			HashMap<String, String> resultMap = new HashMap<String, String>();
			String incrementJson = JSONObject.toJSONString(incrementList);
			resultMap.put("oldXml", null);
			resultMap.put("newXml", null);
			resultMap.put("incrementXml", incrementJson);
			json = JSONObject.toJSONString(resultMap);
		}
		// byte[] data = Encoding.StringToByte(xml);

		byte[] data = Encoding.StringToByte(json);
		return data;
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

	public SingleConnectionFactory getLocalconnectionFactory() {
		return localConnectionFactory;
	}

	public void setLocalconnectionFactory(
			SingleConnectionFactory localConnectionFactory) {
		this.localConnectionFactory = localConnectionFactory;
	}

}
