package fxdigital.syncserver.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.business.hibernate.bean.DataIncrementVersion;
import fxdigital.syncserver.business.hibernate.bean.DataOperateRecord;
import fxdigital.syncserver.business.hibernate.bean.DataSource;
import fxdigital.syncserver.business.hibernate.bean.DataVersion;
import fxdigital.syncserver.business.hibernate.bean.SyncDataVersion;
import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataOperateRecordDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.syncserver.business.hibernate.dao.DataVersionDao;
import fxdigital.syncserver.business.hibernate.dao.SyncDataVersionDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class NewSyncServerUtil {
	
	public static NewSyncServerUtil newSyncServerUtil=null;
	public static NewSyncServerUtil getInstance(){
		if(null==newSyncServerUtil){
			newSyncServerUtil=new NewSyncServerUtil();
			
		}
		return newSyncServerUtil;
	}
	
	
	// 处理下级传过来看版本信息
	public void processSubData(String localcenterid, String version) {
		if (null != version && !("").equals(version)) {
			MessageInfo messageInfo = JSONObject.parseObject(version,
					MessageInfo.class);
			List<HashMap<String, String>> allList = messageInfo.getAllList();
			List<HashMap<String, String>> incrementList = messageInfo
					.getIncrementList();
			Log4jUtil.info(this.getClass(),"拼接后的全量版本数据:" + allList);
			Log4jUtil.info(this.getClass(),"拼接后的增量版本数据:" + incrementList);
			processSubAllData(localcenterid, allList);
			processSubIncrementData(localcenterid, incrementList);

		}
	}
	
	public void processSubAllData(String localcenterid,
			List<HashMap<String, String>> allList) {
		if (null != allList && allList.size() > 0) {
			for (int i = 0; i < allList.size(); i++) {
				HashMap<String, String> map = allList.get(i);
				Log4jUtil.info(this.getClass(),"当前处理的下级全量版本数据：" + map);
				String centerid = map.get("centerid");
				String clientversion = map.get("clientversion");
				String serverversion = map.get("serverversion");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				Log4jUtil.info(this.getClass(),"本级同步服务器上文件发送给上级 centerid:" + centerid
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
					xml = FileUtil.getInstance()
							.readFileByLines(fileaddress);
					oldxml = FileUtil.getInstance().readFileByLines(
							oldfileaddress);

					Log4jUtil.info(this.getClass(),"本级给上级的下载中心:" + centerid + "    下载xml文件: "
							+ fileaddress + "    下载oldxml文件: " + oldfileaddress);
				}
				try {
					// 发送新版本数据
					HashMap<String, String> resultMap = new HashMap<String, String>();
					resultMap.put("oldXml", oldxml);
					resultMap.put("newXml", xml);
					resultMap.put("incrementXml", null);
					Log4jUtil.info(this.getClass(),"本级给上级的新下载中心:" + centerid + " " + centername
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
				Log4jUtil.info(this.getClass(),"当前处理的下级增量版本数据：" + map);
				String centerid = map.get("centerid");
				String clientversion = map.get("clientversion");
				String serverversion = map.get("serverversion");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				for (int k = Integer.valueOf(clientversion) + 1; k <= Integer
						.valueOf(serverversion); k++) {
					Log4jUtil.info(this.getClass(),"本级同步服务器上文件发送给上级 centerid:" + centerid
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
	
	
	public void sendNewDataInfo(String localcenterid, String centerid,
			String version, String centername, String ip, byte[] newData) {
		String localUrl = "DBSynchronization.NewServerDownLoadOver";
		String receiveId = null;
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
		Log4jUtil.info(this.getClass(),"发送版本数据" + version);
		sendOldMessage(m, newData, localcenterid);
//		jMSSendDao.sendLocalMessage(destination, m, newData, localcenterid);
	}

    public void sendOldMessage(Msg m,byte[] data,String receiveId){
    	 OldMessage oldMessage=new OldMessage();
      	 oldMessage.setData(data);
      	 oldMessage.setM(m);
      	 oldMessage.setReceiveId(receiveId);
      	 SyncServerBusiness.getSender().send(oldMessage);
       }
    
    
	public void writeNewIncrementXml(Integer version, String centerid,
			String centername, String centerip, String uuid, byte[] data) {
		List<HashMap<String, String>> list=DataVersionDao.getInstance().getExistSource(centerid, version);
//		if(list!=null&&list.size()>0){
//			Log4jUtil.info(this.getClass(),"中心信息" + centerid+":"+version+"已经存在，不需要同步。");
//			return;
//		}
		Log4jUtil.info(this.getClass(),"@@@@@@@@@@@@" + centerid);
		Log4jUtil.info(this.getClass(),"从上级获取的下载中心   :" + centerid +" 中心名称: "+centername+ "下载版本: " + version);
		String allXml = Encoding.byteToString(data);
		HashMap<String, String> map = JSONObject.parseObject(allXml,
				HashMap.class);
		Log4jUtil.info(this.getClass(),"map ---" + map);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		String incrementXml = map.get("incrementXml");
		List<String> pathlist = FileUtil.getInstance().readPathXML();
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
		oldPath = FileUtil.getInstance().getOldPath(basePath, centerid, version);
		// 老版本数据已经插入,现插入新版本数据
		Log4jUtil.info(this.getClass(),"newXml-------" + newXml);
		Log4jUtil.info(this.getClass(),"oldXml-------" + oldXml);
		Log4jUtil.info(this.getClass(),"incrementXml-------" + incrementXml);
		if ((null != newXml && !("").equals(newXml))
				|| (null != oldXml && !("").equals(oldXml))) {
			if (null != newXml && !("").equals(newXml)) {
				Log4jUtil.info(this.getClass(),"文件newPath写入地址:" + newPath);
				FileUtil.getInstance().wirteXml(newPath, newXml);
			} else {
				newPath = "";
			}
			if (null != oldXml && !("").equals(oldXml)) {
				Log4jUtil.info(this.getClass(),"文件oldPath写入地址:" + oldPath);
				Log4jUtil.info(this.getClass(),"文件oldXml写入内容:" + oldXml);
				FileUtil.getInstance().wirteXml(oldPath, oldXml);
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
			String incrementPath = FileUtil.getInstance().getOldPath(incrementBasePath, centerid,
					Integer.valueOf(incrementVersion));
			FileUtil.getInstance().wirteXml(incrementPath, xml);
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
