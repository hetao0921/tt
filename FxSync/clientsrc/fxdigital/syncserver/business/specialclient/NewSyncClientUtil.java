package fxdigital.syncserver.business.specialclient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.business.MessageInfo;
import fxdigital.syncserver.business.SyncServerBusiness;
import fxdigital.syncserver.business.hibernate.bean.DataIncrementVersion;
import fxdigital.syncserver.business.hibernate.bean.DataOperateRecord;
import fxdigital.syncserver.business.hibernate.bean.DataSource;
import fxdigital.syncserver.business.hibernate.bean.DataVersion;
import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;
import fxdigital.syncserver.business.hibernate.bean.SyncDataVersion;
import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataOperateRecordDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.syncserver.business.hibernate.dao.DataVersionDao;
import fxdigital.syncserver.business.hibernate.dao.LocalCenterDao;
import fxdigital.syncserver.business.hibernate.dao.SyncDataVersionDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class NewSyncClientUtil {
	LocalCenterDao localCenterDao=new LocalCenterDao();
	
	public static NewSyncClientUtil newSyncClientUtil=null;
	public static NewSyncClientUtil getInstance(){
		if(null==newSyncClientUtil){
			newSyncClientUtil=new NewSyncClientUtil();
			
		}
		return newSyncClientUtil;
	}
	
	public void processNewUpLoadMsg(OldMessage oldMessage,String versions) {
		NvmpCenterinfotab localCenter = localCenterDao.query();
		String centerInfo = localCenter.getCenterId();
		String version = processUpLoadMsg(versions);
		// System.out.println("组装后要下载的中心" + version);
		Log4jUtil.info(this.getClass(),"组装后要下载的中心" + version);

		if (version.equals("")) {
			String reason = "没有最新版本，本次自动同步无数据。";
			return;
		}

		String receiveId = null;

		String url = "DBSynchronization.NewClientQueueGetData";
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		String centerid = String.valueOf(centerInfo);
		String ip = String.valueOf(localCenter.getCenterIp());
		hp.put("sessionid", centerid);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", centerid);
		hp.put("versions", version);

		Msg m = new Msg();
		m.set_Url(url);
		m.AddParams(hp);
		byte[] data=oldMessage.getData();
//		jMSSendDao.sendMessage(serverdestination, m, data, receiveId);
		sendOldMessage(m, data, centerid);
		Log4jUtil.info(this.getClass(),"组装信息发送到中心成功" + version);

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
			Log4jUtil.info(this.getClass(),"传递过来的全量数据:upAllList" + upAllList);
			Log4jUtil.info(this.getClass(),"传递过来的全量数据:localAllList" + localAllList);
			List<HashMap<String, String>> allUpList = processAllInfo(upAllList,
					localAllList);
			// 处理增量
			Log4jUtil.info(this.getClass(),"传递过来的增量数据:upIncrementList" + upIncrementList);
			Log4jUtil.info(this.getClass(),"传递过来的增量数据:localIncrementList" + localIncrementList);
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
		Log4jUtil.info(this.getClass(),"将本级全量数据推送给上级。。。");
		// 处理全量
		List<HashMap<String, String>> allUpList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> allLoadList = FileUtil.getInstance()
				.MergeLoadInfo(upAllList, localAllList);
		if (null != allLoadList && allLoadList.size() > 0) {
			for (int i = 0; i < allLoadList.size(); i++) {
				int clientVersion = Integer.valueOf(allLoadList.get(i).get(
						"clientversion"));
				int serverVersion = Integer.valueOf(allLoadList.get(i).get(
						"serverversion"));
				Log4jUtil.info(this.getClass(),"本级中心客户端版本:" + clientVersion + " 服务器端版本: "
						+ serverVersion);
				if (clientVersion < serverVersion) {
					allUpList.add(allLoadList.get(i));
				} else if (clientVersion > serverVersion) {
					HashMap<String, String> map = allLoadList.get(i);
					String centerid = map.get("centerid");
					String version = map.get("clientversion");
					String centername = map.get("centername");
					String ip = map.get("centerip");
					Log4jUtil.info(this.getClass(),"本级同步服务器上文件发送给上级 centerid:" + centerid
							+" "+ centername +" "+ip + "version: " + version);
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
						xml = FileUtil.getInstance().readFileByLines(
								fileaddress);
						oldxml = FileUtil.getInstance().readFileByLines(
								oldfileaddress);

						Log4jUtil.info(this.getClass(),"本级给上级的下载中心:" + centerid + "    下载xml文件: "
								+ fileaddress + "    下载oldxml文件: "
								+ oldfileaddress);
					}
					try {
						// 发送新版本数据
						HashMap<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("oldXml", oldxml);
						resultMap.put("newXml", xml);
						resultMap.put("incrementXml", null);
						Log4jUtil.info(this.getClass(),"本级给上级的新下载中心:" + centerid + " "
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
			Log4jUtil.info(this.getClass(),"本级需要的中心数据： " + allUpList);

		}

		return allUpList;
	}

	public List<HashMap<String, String>> processIncrementInfo(
			List<HashMap<String, String>> upIncrementList,
			List<HashMap<String, String>> localIncrementList) {
		// 处理增量
		Log4jUtil.info(this.getClass(),"将本级增量数据推送给上级。。。");
		List<HashMap<String, String>> incrementUpList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> incrementLoadList = FileUtil
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
						Log4jUtil.info(this.getClass(),"本级同步服务器上文件发送给上级 centerid:" + centerid
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
							incrementXml = FileUtil.getInstance()
									.readFileByLines(fileaddress);
							Log4jUtil.info(this.getClass(),"本级给上级的下载中心:" + centerid
									+ "    下载incrementXml文件: " + fileaddress);
						}
						try {
							// 发送新版本数据
							HashMap<String, String> resultMap = new HashMap<String, String>();
							resultMap.put("oldXml", null);
							resultMap.put("newXml", null);
							resultMap.put("incrementXml", incrementXml);
							Log4jUtil.info(this.getClass(),"本级给上级的新下载中心:" + centerid + " "
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
			Log4jUtil.info(this.getClass(),"本级需要的中心数据： " + incrementUpList);
		}
		return incrementUpList;
	}
	
	
	public void ClientServerQueueOldSend(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.SendOldQueueString";
		String receiveId = null;
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
//		jMSSendDao.sendMessage(serverdestination, m, data, centerid);
		sendOldMessage(m, data, centerid);
	}
	
	
	  public void sendOldMessage(Msg m,byte[] data,String receiveId){
	        OldMessage oldMessage=new OldMessage();
	      	 oldMessage.setData(data);
	      	 oldMessage.setM(m);
	      	 oldMessage.setReceiveId(receiveId);
	      	 SpecialClient.getSender().send(oldMessage);
	       }


	public void writeNewIncrementXml(Integer version, String centerid,
			String centername, String centerip, String uuid, byte[] data) {
//		List<HashMap<String, String>> list=DataVersionDao.getInstance().getExistSource(centerid, version);
//		if(list!=null&&list.size()>0){
//			Log4jUtil.info(this.getClass(),"中心信息" + centerid+":"+version+"已经存在，不需要同步。");
//			return;
//		}
		Log4jUtil.info(this.getClass(),"@@@@@@@@@@@@" + centerid);
		String allXml = Encoding.byteToString(data);
		HashMap<String, String> map = JSONObject.parseObject(allXml,
				HashMap.class);
		Log4jUtil.info(this.getClass(),"map ---" + map);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		String incrementXml = map.get("incrementXml");
		List<String> pathlist = FileUtil.readPathXML();
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
			// Log4jUtil.info(this.getClass(),"==============="+System.getProperty("user.dir"));
			path = pathlist.get(0) + ":/sync" + centerid + version + ".xml";
			basePath = pathlist.get(0) + ":/sync";
			incrementBasePath = pathlist.get(0) + ":/increment";
		}
		newPath = path;
		oldPath = FileUtil.getOldPath(basePath, centerid, version);
		// 老版本数据已经插入,现插入新版本数据
		Log4jUtil.info(this.getClass(),"newXml-------" + newXml);
		Log4jUtil.info(this.getClass(),"oldXml-------" + oldXml);
		Log4jUtil.info(this.getClass(),"incrementXml-------" + incrementXml);
		if ((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml))) {
			if (null != newXml && !("").equals(newXml)) {
				Log4jUtil.info(this.getClass(),"文件newPath写入地址:" + newPath);
				FileUtil.wirteXml(newPath, newXml);
			} else {
				newPath = "";
			}
			if (null != oldXml && !("").equals(oldXml)) {
				Log4jUtil.info(this.getClass(),"文件oldPath写入地址:" + oldPath);
				Log4jUtil.info(this.getClass(),"文件oldXml写入内容:" + oldXml);
				FileUtil.wirteXml(oldPath, oldXml);
			} else {
				oldPath = "";
			}

			// 修改操作记录data_operate_record
			DataOperateRecord dataOperateRecord = new DataOperateRecord();
			dataOperateRecord.setOperate("同步");
			Date d = new Date();
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
//			dataSource.setOldfileaddress(oldPath);
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
			String incrementPath = FileUtil.getOldPath(incrementBasePath, centerid,
					Integer.valueOf(incrementVersion));
			FileUtil.wirteXml(incrementPath, xml);
			Date d = new Date();
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


}
