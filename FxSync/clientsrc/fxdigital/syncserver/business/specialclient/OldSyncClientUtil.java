package fxdigital.syncserver.business.specialclient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;

import fxdigital.mqcore.exchange.impl.XMLConvert;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.business.hibernate.bean.DataOperateRecord;
import fxdigital.syncserver.business.hibernate.bean.DataSource;
import fxdigital.syncserver.business.hibernate.bean.DataVersion;
import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;
import fxdigital.syncserver.business.hibernate.bean.SyncDataVersion;
import fxdigital.syncserver.business.hibernate.dao.DataOperateRecordDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.syncserver.business.hibernate.dao.DataVersionDao;
import fxdigital.syncserver.business.hibernate.dao.LocalCenterDao;
import fxdigital.syncserver.business.hibernate.dao.SyncDataVersionDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class OldSyncClientUtil {
	LocalCenterDao localCenterDao=new LocalCenterDao();
	SpecialClient specialClient=new SpecialClient();
	XMLConvert xmlConvert=new XMLConvert();
	public static OldSyncClientUtil oldSyncClientUtil=null;
	public static OldSyncClientUtil getInstance(){
		if(null==oldSyncClientUtil){
			oldSyncClientUtil=new OldSyncClientUtil();
			
		}
		return oldSyncClientUtil;
	}
	
	public void processLoadMsg(OldMessage oldMessage,String versions) {

		String version = "";
		// 服务器端返回版本

		NvmpCenterinfotab localCenter = localCenterDao.query();
		String centerInfo = localCenter.getCenterId();
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getAllServerSource();

		String[] ver = versions.split("}");
		Log4jUtil.info(this.getClass(),"得到需要给上级的 -------" + ver);
		version = getUptoLocal(list, ver);

		processLocalFile(list, ver);

		// System.out.println("组装后要下载的中心" + version);
		Log4jUtil.info(this.getClass(),"组装后要下载的中心" + version);

		if (version.equals("")) {
			String reason = "没有最新版本，本次自动同步无数据。";
			return;
		}

		String receiveId = null;

		String url = "DBSynchronization.ClientQueueGetData";
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
//		jMSSendDao.sendMessage(serverdestination, m, data, receiveId);
		byte[] data=oldMessage.getData();
		sendOldMessage(m, data, centerid);
		Log4jUtil.info(this.getClass(),"组装信息发送到中心成功" + version);

	}
	
	
	public String getUptoLocal(List<HashMap<String, String>> list, String[] ver) {
		String version = "";
		Log4jUtil.info(this.getClass(),"ver length"+ver.length);
		for (int i = 0; i < ver.length; i++) {
			Log4jUtil.info(this.getClass(),"ver length"+ver[i]);
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
		Log4jUtil.info(this.getClass(),"得到要从服务器上下载的版本信息: " + version);
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
				Log4jUtil.info(this.getClass(),"本级给上级的map: " + map);
				String centerid = map.get("centerid");
				String version = map.get("version");
				String centername = map.get("centername");
				String ip = map.get("centerip");
				Log4jUtil.info(this.getClass(),"本级同步服务器上文件发送给上级 centerid:" + centerid
						+ "version: " + version);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getSourceAddress(centerid,
								String.valueOf(version));
				String xml = null;
				String oldxml = null;
				if (addressList.size() > 0) {
					// xmlMap=new HashMap<String, String>();
					String fileaddress = addressList.get(0).get("fileaddress");
					String oldfileaddress = addressList.get(0).get(
							"oldfileaddress");
					System.out.println("下载文件地址fileaddress   " + fileaddress);
					xml = FileUtil.getInstance()
							.readFileByLines(fileaddress);
//					oldxml = FileUtil.getInstance().readFileByLines(
//							oldfileaddress);
					

					Log4jUtil.info(this.getClass(),"本级给上级的下载中心:" + centerid + "    下载xml文件: "
							+ fileaddress + "    下载oldxml文件: " + oldfileaddress);

					// Log4jUtil.info(this.getClass(),"下载文件内容xml   " + xml);
					
					//将新文件转化成老文件 
					oldxml=xmlConvert.newConvertToOld(true, xml);

				}
				byte[] data = Encoding.StringToByte(oldxml);
				try {
					Log4jUtil.info(this.getClass(),"本级给上级的旧下载中心:" + centerid + " " + centername
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
	
	
	public static void main(String[] args) {
		XMLConvert xmlConvert=new XMLConvert();
		String xml = FileUtil.getInstance()
				.readFileByLines("d:/synclch@001493.xml");
		String oldxml=xmlConvert.newConvertToOld(true, xml);
		System.out.println(oldxml);
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
	
	
	/**
	 * 特殊客户端发送请求,服务器端接受该信息后，进行直接保存。 客户端发送同步数据,包含了中心ID，操作人ID，操作人IP和一个xml文件流
	 * 
	 * @throws JMSException
	 * */
	public void ClientServerQueueSend(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.ClientServerQueueSend";
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
					Log4jUtil.info(this.getClass(),"下载增量文件地址fileaddress   " + fileaddress);
					String xml = FileUtil.getInstance().readFileByLines(
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
		Log4jUtil.info(this.getClass(),"需要下载的增量信息：" + list);
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
	
	
	// 得到所有需要下载的增量版本
	public List<String> getIncrementVersion(String centerid, String version,
			String serverVersion) {
		Log4jUtil.info(this.getClass(),"客户端增量最大版本-----:" + centerid + "版本" + version);
		List<String> list = new ArrayList<String>();
		// 把增量的最大版本也传递过去
		if (null == version || ("").equals(version)) {
			version = "0";
		}
		for (int i = Integer.valueOf(version) + 1; i <= Integer
				.valueOf(serverVersion); i++) {
			list.add(String.valueOf(i));
		}
		Log4jUtil.info(this.getClass(),"所有需要下载增量版本-------:" + centerid + "版本 ---" + list);
		return list;
	}

	
    public void sendOldMessage(Msg m,byte[] data,String receiveId){
         OldMessage oldMessage=new OldMessage();
      	 oldMessage.setData(data);
      	 oldMessage.setM(m);
      	 oldMessage.setReceiveId(receiveId);
      	 SpecialClient.getSender().send(oldMessage);
       }


	public void writeLocalxml(Integer verson, String centerid,
			String centername, String centerip, String uuid, byte[] data) {

		// super.OnServerDownLoadOverEvent(verson, centerid, uuid, data);
		// //System.out.println("@@@@@@@@@@@@" + centerid);
		List<HashMap<String, String>> list=DataVersionDao.getInstance().getExistSource(centerid, verson);
		if(list!=null&&list.size()>0){
			Log4jUtil.info(this.getClass(),"中心信息" + centerid+":"+verson+"已经存在，不需要同步。");
			return;
		}
		Log4jUtil.info(this.getClass(),"@@@@@@@@@@@@" + centerid);
		String xml = Encoding.byteToString(data);
		xml=xmlConvert.oldConvertToNew(true,xml);
		String version = String.valueOf(verson);
		String path = "";
		String basePath = "";
		List<String> pathlist = FileUtil.readPathXML();
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			basePath = "/" + pathlist.get(1) + "/sync";
		     path = "/" + pathlist.get(1) + "/sync" + centerid + version
			 + ".xml";
		} else {
			// Log4jUtil.info(this.getClass(),"==============="+System.getProperty("user.dir"));
			basePath = pathlist.get(0) + ":/sync";
		    path = pathlist.get(0) + ":/sync" + centerid + version + ".xml";
		}
		//path = FileUtil.getOldPath(basePath, centerid, verson);
		FileUtil.wirteXml(path, xml);
		Log4jUtil.info(this.getClass(),"文件写入地址:" + path);
		Log4jUtil.info(this.getClass(),"文件写入内容:" + xml);

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
		dataSource.setFileaddress(path);
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
