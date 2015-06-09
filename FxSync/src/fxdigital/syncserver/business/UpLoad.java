package fxdigital.syncserver.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
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
import fxdigital.util.MsgType;

public class UpLoad {

	public void initUpLoad(DBSyncContent content) {
		Log4jUtil.info(UpLoad.class, "centerid" + content.getCenterid());
		Log4jUtil.info(UpLoad.class, "处理上传初始化数据 flag=10");
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getOneVersion(content.getCenterid());
		if (list.size() > 0 && null != list.get(0).get("version")) {
			content.setVersion(Integer.valueOf(list.get(0).get("version")));
			Log4jUtil.info(UpLoad.class, "当前中心：" + content.getCenterid()
					+ "服务器端版本：" + list.get(0).get("version"));
		}
		content.setFlag(MsgType.UP_INIT_SC);
		//发送给312客户端
		send(content);

	}
	public  void send(DBSyncContent content){
		Mail message=new Mail();
		message.setDestMailboxID(content.getCenterid());
		message.setContent(JSONObject.toJSONString(content));
		UpLoadBusiness.getSender().send(message);
	}
	
	public void processUpCommand(DBSyncContent content) {
		String xml = content.getXml();
		HashMap<String, String> map = JSONObject
				.parseObject(xml, HashMap.class);
		String oldXml = map.get("oldXml");
		String newXml = map.get("newXml");
		Log4jUtil.info(UpLoad.class,"oldXml----" + oldXml);
		Log4jUtil.info(UpLoad.class,"newXml----" + newXml);
		String centerid = content.getCenterid();
		String centername = content.getCentername();
		String centerip = content.getCenterip();
		List<String> list = content.getStrlist();
		int localSelfVersion = 0;
		int NextServerVersion = 0;
		int remoteSelfVersion =0;
		if (list.size() > 0) {
			localSelfVersion = Integer.valueOf(list.get(0).toString());
		    remoteSelfVersion = Integer.valueOf(list.get(1).toString());
			NextServerVersion = localSelfVersion > remoteSelfVersion ? localSelfVersion
					: remoteSelfVersion;
		}
		List<String> pathlist = FileUtil.readPathXML();
		String version = String.valueOf(content.getVersion());
		String path = "";
		String basePath = "";
		Log4jUtil.info(UpLoad.class, "本地保存版本"+localSelfVersion+"  "+remoteSelfVersion+" "+NextServerVersion);
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/" + pathlist.get(1) + "/sync" + centerid
					+ localSelfVersion + ".xml";
			basePath = "/" + pathlist.get(1) + "/sync";
		} else {
			path = pathlist.get(0) + ":/sync" + centerid + localSelfVersion
					+ ".xml";
			basePath = pathlist.get(0) + ":/sync";
		}
		String oldPath = FileUtil.getOldPath(basePath, centerid, localSelfVersion);
		Log4jUtil.info(UpLoad.class,"上传写文件目录：    " + path);
//		FileUtil.wirteXml(oldPath, oldXml);
		FileUtil.wirteXml(path, newXml);
		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("上传");
		Date d = new Date();
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
//		dataSource.setOldfileaddress(oldPath);
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
		Log4jUtil.info(UpLoad.class,"上传服务器端成功。。。");
		
		content.setFlag(MsgType.UP_COMMAND_SC);
		content.setContent("0");
		//发送给312客户端
		send(content);
	} 
	
	public void processIncrementUpCommand(DBSyncContent content){
		String centerid = content.getCenterid();
		// 查找当前版本信息，找到对应的版本
		int nextVersion = getNextIncrementVersion(centerid);
		processIncrementCommand(nextVersion, content);
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
	
	
	public void processIncrementCommand(int nextVersion, DBSyncContent content) {
		String xml = content.getXml();
		String centerid = content.getCenterid();
		String centername = content.getCentername();
		String centerip = content.getCenterip();
		List<String> pathlist = FileUtil.readPathXML();
		String basePath = "";
		String incrementPath = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			basePath = "/" + pathlist.get(1) + "/increment";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			basePath = pathlist.get(0) + ":/increment";
		}
		incrementPath = FileUtil.getOldPath(basePath, centerid, nextVersion);
		FileUtil.wirteXml(incrementPath, xml);
		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("上传");
		Date d = new Date();
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
}
