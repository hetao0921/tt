package fxdigital.dbsync.domains.client.service;

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
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hibernate.bean.DataNativeSource;
import com.hibernate.bean.DataNativerecordSource;
import com.hibernate.bean.DataOperateRecord;
import com.hibernate.bean.DataSelfSource;

import fxdigital.dbsync.domains.client.dao.DataNativeRecordSourceDao;
import fxdigital.dbsync.domains.client.dao.DataNativeSourceDao;
import fxdigital.dbsync.domains.client.dao.DataOperateDao;
import fxdigital.dbsync.domains.client.dao.DataOperateRecordDao;
import fxdigital.dbsync.domains.client.dao.DataSelfSourceDao;
import fxdigital.dbsync.domains.client.db.JdbcImpl;
import fxdigital.dbsync.domains.client.db.JdbcToXml;
import fxdigital.dbsync.domains.client.pojo.DBSyncContent;
import fxdigital.dbsync.domains.client.pojo.MsgType;

/**
 * @author  het
 *上传信息处理类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.service
 */
@Component
public class UpMessage {
	private static Logger logger = Logger.getLogger(MsgClientService.class);
	String selfVersion = "-1";
	String selfServerVersion = "-1";
	String nextVersion = "-1";
	List<HashMap<String, String>> clientLoadList = null;
	List<HashMap<String, String>> serverLoadList = null;
	List<HashMap<String, String>> tempLoadList = null;
	List<HashMap<String, String>> lastLoadList = null;
	List<HashMap<String, String>> serverResetList = null;
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
		List<HashMap<String, String>> selfList = DataSelfSourceDao.getInstance()
				.getAllSelfSource();

		if (selfList.size() > 0) {
			selfVersion = (null==selfList.get(0).get("version"))?"0":selfList.get(0).get("version");
		} else {
			selfVersion = "0";
		}
		list.add(selfVersion);

		// 获取服务器上版本

		list.add(selfServerVersion);

		DBSyncContent content = new DBSyncContent();
		// List<String> strlist = new ArrayList<String>();
		// strlist.add("0");
		// strlist.add("2");
		// strlist.add("3");
		// content.setStrlist(strlist);

		// 常海发送
		// content.setSender("0800272E784D@001");
		// content.setCenterid("0800272E784D@001");

//		// 何涛发送
//		content.setSender("center@001");
//		content.setCenterid("center@001");
		
		String centerid=null;
		String centername=null;
		String centerip=null;
		if(centerInfo.size()>0){		
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0).entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();		
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}
		
		
		content.setSender(centerid);
		content.setCenterid(centerid);
		
		content.setCentername(centername);
		content.setCenterip(centerip);
		
		
		// 处理上传初始化信息 flag=10
		content.setFlag(MsgType.UP_INIT_CS);

		String json = JSON.toJSONString(content);
		System.out.println("SendUpCommand" + "   " + "json" + "   " + json);
//		if (send(json)) {
//			// log.info("发送成功！发送内容："+content);
//			System.out.println("发送成功！发送内容：" + content);
//		} else {
//			// log.error("发送失败！发送内容："+content);
//			System.out.println("发送失败！发送内容：" + content);
//		}

		// 计算下一个版本
		int selfNextVersion = 0;
		list.add(String.valueOf(selfNextVersion));
		System.out.println("selfVersion" + selfVersion);
		System.out.println("list" + list);

		return list;
	}

	/**
	 * 获取所有需要下载的本地的版本号
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadVersionInfo(List<Map<String, Object>> centerInfo) {
		// List<Object> resultList = new ArrayList<Object>();
		clientLoadList = null;
		serverLoadList = null;
		// 获取本地的版本号信息
		try {
			clientLoadList = DataNativeRecordSourceDao.getInstance()
					.getNativeRecordSource();
		} catch (Exception e) {
			logger.info("getLoadVersionInfo error"+e);
		}
		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);
		// 常海发送
		// content.setSender("0800272E784D@001");
		// content.setCenterid("0800272E784D@001");
		// 何涛发送
//		content.setSender("center@001");
//		content.setCenterid("center@001");

		String centerid=null;
		String centername=null;
		String centerip=null;
		if(centerInfo.size()>0){		
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0).entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();		
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}
		
		
		content.setSender(centerid);
		content.setCenterid(centerid);
		
		content.setCentername(centername);
		content.setCenterip(centerip);
		
		// 处理下载初始化信息 flag=12
		content.setFlag(MsgType.Load_INIT_CS);
		String json = JSON.toJSONString(content);
		System.out
				.println("getLoadVersionInfo" + "   " + "json" + "   " + json);
//		if (send(json)) {
//			// log.info("发送成功！发送内容："+content);
//			System.out.println("发送成功！发送内容：" + content);
//		} else {
//			// log.error("发送失败！发送内容："+content);
//			System.out.println("发送失败！发送内容：" + content);
//		}
		return clientLoadList;
	}

	/**
	 * 获取所有还原的服务器端版本号
	 * 
	 * @return
	 */
	public  void getResetVersionInfo() {
		// List<Object> resultList = new ArrayList<Object>();
		clientLoadList = null;
		serverLoadList = null;
		// 获取本地的版本号信息
		try {
			clientLoadList = DataNativeRecordSourceDao.getInstance()
					.getNativeRecordSource();
		} catch (Exception e) {
			logger.info("getResetVersionInfo error"+e);
			return;
		}
		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);
		// 常海发送
		// content.setSender("0800272E784D@001");
		// content.setCenterid("0800272E784D@001");
		// 何涛发送
		content.setSender("center@001");
		content.setCenterid("center@001");

		// 处理下载初始化信息 flag=12
		content.setFlag(MsgType.Load_RESET_CS);
		String json = JSON.toJSONString(content);
		System.out
				.println("getResetVersionInfo" + "   " + "json" + "   " + json);
//		if (send(json)) {
//			// log.info("发送成功！发送内容："+content);
//			System.out.println("发送成功！发送内容：" + content);
//		} else {
//			// log.error("发送失败！发送内容："+content);
//			System.out.println("发送失败！发送内容：" + content);
//		}
		
	}
	
	



	/**
	 * 发送上传命令
	 * 
	 * @return
	 */
	public String sendUpCommand(List<String> strlist,List<Map<String, Object>> centerInfo) {
		System.out.println("开始发送!");
		System.out.println("strlist" + strlist);
		String flag = "-1";
		String errorinfo = "";
		// strlist = new ArrayList<String>();
		// strlist.add("7");
		// strlist.add("0");
		// strlist.add("8");
		System.out.println("SendUpCommand" + "   " + "strlist" + "   "
				+ strlist);
		HashMap<String, String> UpLock = getUpMsg();
		if (UpLock.get("flag").equals("1")) {
			//上传被锁
			flag = "2";
			errorinfo = "上传被锁!";
		} else {
			// 可以上传
			flag = "3";

			String localSelfVersion = null;
			String remoteServerVersion = null;
			String nextSelfVersion = null;
			
			if (strlist.size() > 0) {
				localSelfVersion = strlist.get(0);
				remoteServerVersion = strlist.get(1);
				nextSelfVersion = strlist.get(2);
				
				// 获取本机版本
				//List<String> list = new ArrayList<String>();
				List<HashMap<String, String>> selfList = DataSelfSourceDao.getInstance()
						.getAllSelfSource();
				String localRealVersion="-1";
				if(selfList.size()>0){
					 localRealVersion=selfList.get(0).get("version");	
				}
				else{
					 localRealVersion="0";
				}
				
				if(Integer.valueOf(localSelfVersion)<Integer.valueOf(localRealVersion)){	
					flag = "0";
					errorinfo = "客户端版本号不是最新!";
				}
				else{
					flag = "1";
					
					String centerid=null;
					String centername=null;
					String centerip=null;
					if(centerInfo.size()>0){		
						Iterator<Entry<String, Object>> iterator = centerInfo.get(0).entrySet().iterator();
						HashMap<String, Object> hashmap = new HashMap<String, Object>();
						while (iterator.hasNext()){
							Entry<String, Object> entry = iterator.next();		
							hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
						}
						centerid = (String) hashmap.get("CenterID".toLowerCase());
						centername = (String) hashmap.get("CenterName".toLowerCase());
						centerip = (String) hashmap.get("CenterIP".toLowerCase());
					}
					

					
					// 修改上传锁状态
					upLock();
					// 获取本地同步数据
					String xml = null;
					try {

						xml = JdbcToXml.getInstance().writeTheXml(centerid);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						logger.info("getLoadVersionInfo error"+e);
						return null;
					}
					DBSyncContent content = new DBSyncContent();
					content.setStrlist(strlist);
					content.setXml(xml);
					// 常海发送
					// content.setSender("0800272E784D@001");
					// content.setCenterid("0800272E784D@001");
					// 何涛发送
					
					content.setSender(centerid);
					content.setCenterid(centerid);
					
					content.setCentername(centername);
					content.setCenterip(centerip);
					
					// 发送标记 flag=0
					content.setFlag(MsgType.UP_COMMAND_CS);

					// content.setCenterid("het@001");
					String json = JSON.toJSONString(content);
					System.out.println("SendUpCommand" + "   " + "json" + "   " + json);
					int state = 0;
					
					// 修改本地资源记录
					DataSelfSource dataSelfSource = new DataSelfSource();
					// 正在上传
					dataSelfSource.setFlag(state);
					dataSelfSource.setVersion(Integer.valueOf(nextSelfVersion));
					Date d = new Date();
					System.out.println(d);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateNowStr = sdf.format(d);
					dataSelfSource.setUuid(centerid);
					dataSelfSource.setStartdate(dateNowStr);
					dataSelfSource.setEnddate(dateNowStr);
					DataSelfSourceDao.getInstance().insert(dataSelfSource);
//					if (send(json)) {
//						// log.info("发送成功！发送内容："+content);
//						// 上传成功
//						state = 3;
//						System.out.println("发送成功！发送内容：" + content);
//						// 解除上传锁
//						unUpLock();
//					} else {
//						// log.error("发送失败！发送内容："+content);
//						// 上传失败
//						state = 1;
//						errorinfo = "发送失败!";
//						System.out.println("发送失败！发送内容：" + content);
//					}
					DataSelfSourceDao.getInstance().updateUpState(centerid, state);
				
				}
			}
		}
		
		// 插入上传记录
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		// errorinfo
		dataOperateRecord.setOperate("上传");
		dataOperateRecord.setErrorinfo(errorinfo);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		
		
		return flag;
	}

	public boolean sendLoadCommand(List<HashMap<String, String>> list,List<Map<String, Object>> centerInfo) {
		boolean flag = false;
		String errorinfo = null;
		HashMap<String, String> UpLock = getLoadMsg();
		String uplockid=UpLock.get("id");
		String centerid=null;
		String centername=null;
		String centerip=null;
		if(centerInfo.size()>0){		
			Iterator<Entry<String, Object>> iterator = centerInfo.get(0).entrySet().iterator();
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			while (iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();		
				hashmap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			centerid = (String) hashmap.get("CenterID".toLowerCase());
			centername = (String) hashmap.get("CenterName".toLowerCase());
			centerip = (String) hashmap.get("CenterIP".toLowerCase());
		}
		if (UpLock.get("flag").equals("1")) {
			flag = false;
		} else {
			flag = true;
			// 修改下载锁状态
			loadLock();
			List<HashMap<String, String>> loadList = null;
			if (list.size() > 0) {
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

			if (loadList.size() > 0) {
				DBSyncContent content = new DBSyncContent();
				content.setList(loadList);
				// 常海发送
				// content.setSender("0800272E784D@001");
				// content.setCenterid("0800272E784D@001");
				// 何涛发送
				

				
				content.setSender(centerid);
				content.setCenterid(centerid);
				
				content.setCentername(centername);
				content.setCenterip(centerip);
				
				
				// 发送标记 flag=0
				content.setFlag(MsgType.Load_COMMAND_CS);

				String json = JSON.toJSONString(content);
				System.out.println("SendLoadCommand" + "   " + "json" + "   "
						+ json);
//				if (send(json)) {
//					// log.info("发送成功！发送内容："+content);
//					System.out.println("发送成功！发送内容：" + content);
//				} else {
//					errorinfo = "发送失败!";
//				}
			}else{
				errorinfo = "无同步数据!";
				unLoadLock(uplockid);
			}
			// 修改data_operate_record表
			DataOperateRecord dataOperateRecord = new DataOperateRecord();
			dataOperateRecord.setOperate("下载");
			dataOperateRecord.setOperatorip(centerip);
			dataOperateRecord.setErrorinfo(errorinfo);
			DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		}

		return flag;

	}

	public boolean sendResetCommand(List<HashMap<String, String>> list) {
		String errorinfo=null;
		if(list.size()>0){
			DBSyncContent content = new DBSyncContent();
			String centerid=list.get(0).get("centerid");
			String centername=list.get(0).get("centername");
			String version=list.get(0).get("version");
			content.setCenterid(centerid);
			content.setCentername(centername);
			content.setVersion(Integer.valueOf(version));
			
			// 何涛发送
			content.setSender("center@001");
			content.setCenterid("center@001");
			// 发送标记 flag=5
			content.setFlag(MsgType.Load_RESET_SECOND_CS);
			
			String json = JSON.toJSONString(content);
			System.out.println("sendResetCommand" + "   " + "json" + "   "
					+ json);
//			if (send(json)) {
//				// log.info("发送成功！发送内容："+content);
//				System.out.println("发送成功！发送内容：" + content);
//			} else {
//				errorinfo = "发送失败!";
//			}
		}else{
			errorinfo = "无还原数据!";
		}
		// 修改data_operate_record表
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("还原");
		dataOperateRecord.setErrorinfo(errorinfo);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		return true;
		
	}
	
	
	public boolean sendViewLogsCommand(List<HashMap<String, String>> list) {
		String errorinfo=null;
		if(list.size()>0){
			DBSyncContent content = new DBSyncContent();
			String centerid=list.get(0).get("centerid");
			String centername=list.get(0).get("centername");
			String version=list.get(0).get("version");
			content.setCenterid(centerid);
			content.setCentername(centername);
			if(null!=version&&!("").equals(version)){
				content.setVersion(Integer.valueOf(version));
			}
			
			
			// 何涛发送
			content.setSender("center@001");
			content.setCenterid("center@001");
			// 发送标记 flag=14
			content.setFlag(MsgType.VIEW_LOGS_CS);
			
			String json = JSON.toJSONString(content);
			System.out.println("sendViewLogsCommand" + "   " + "json" + "   "
					+ json);
//			if (send(json)) {
//				// log.info("发送成功！发送内容："+content);
//				System.out.println("发送成功！发送内容：" + content);
//			} else {
//				errorinfo = "发送失败!";
//			}
		}else{
			errorinfo = "无还原数据!";
		}
		// 修改data_operate_record表
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("查看日志");
		dataOperateRecord.setErrorinfo(errorinfo);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);
		return true;
		
	}
	
	/**
	 * 处理下载的数据
	 * 
	 * @return
	 */
	public void processLoadData(DBSyncContent recontent) {
		List<HashMap<String, String>> loadList = recontent.getList();
		int state = 0;
		String uuid = null;
		if (loadList.size() > 0) {
			for (int i = 0; i < loadList.size(); i++) {
				String centerid = loadList.get(i).get("centerid");
				String centername = loadList.get(i).get("centername");
				String version = loadList.get(i).get("serverversion");
				String xml = loadList.get(i).get("xml");
				System.out.println("processLoadData   " + "centerid   "
						+ centerid);
				System.out.println("processLoadData   " + "xml   " + xml);
				if (("").equals(xml) || null == xml) {
					System.out.println("xml文件为空啊。。。");
					state = 2;
				} else {
					System.out.println("处理xml开始啦！！！");
//					xml = xml.replace("{", "<");
//					xml = xml.replace("}", ">");
					List<String> sqls = JdbcToXml.getInstance().xmlToInsert(
							xml, centerid);
					if (sqls == null) {
						state = 2;
					} else {
						System.out.println("数据开始插入......SQL的数目：" + sqls.size());
						boolean result=false;
						try {
							result = JdbcImpl.getJdbcImpl()
									.updateSqls(sqls);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("数据全部执行完毕,返回结果：" + result);
						if (result)
							state = 0;
						else
							state = 1;
					}
				}
				if (state == 0) {

				} else {

				}

				// 修改data_native_source表
				DataNativeSource dataNativeSource = new DataNativeSource();
				dataNativeSource.setCenterid(centerid);
				dataNativeSource.setCentername(centername);
				dataNativeSource.setVersion(Integer.valueOf(version));
				// dataNativeSource.setDownStartDate(downstartdate);
				Date d = new Date();
				System.out.println(d);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				dataNativeSource.setDownenddate(dateNowStr);
				dataNativeSource.setUpdatetime(dateNowStr);
				dataNativeSource.setFlag(String.valueOf(state));
				dataNativeSource.setUuid(uuid);
				DataNativeSourceDao.getInstance().insert(dataNativeSource);

				// 修改data_nativerecord_source表
				DataNativeRecordSourceDao.getInstance()
						.deleteNativeRecordSource(centerid);
				DataNativerecordSource dataNativeRecordSource = new DataNativerecordSource();
				dataNativeRecordSource.setCenterid(centerid);
				dataNativeRecordSource.setCentername(centername);
				dataNativeRecordSource.setVersion(Integer.valueOf(version));
				// dataNativeRecordSource.setDownStartDate(downstartdate);
				Date d2 = new Date();
				System.out.println(d2);
				SimpleDateFormat sdf2 = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateNowStr2 = sdf.format(d2);
				dataNativeRecordSource.setDownenddate(dateNowStr2);
				dataNativeRecordSource.setUpdatetime(dateNowStr2);
				dataNativeRecordSource.setFlag(String.valueOf(state));
				dataNativeRecordSource.setUuid(uuid);
				DataNativeRecordSourceDao.getInstance().insert(
						dataNativeRecordSource);
			}

		}

//		// 如果下载完毕，就解除锁定
//		if (DataNativeSourceDao.getInstance().getNativeFlagNum() == 0) {
//			unLoadLock();
//			System.out.println("解锁完毕");
//		}

	}

	/**
	 * 处理下载的数据
	 * 
	 * @return
	 */
	public void processResetData(DBSyncContent recontent) {

		String centerid = recontent.getCenterid();
		String centername = recontent.getCentername();
		int version = recontent.getVersion();
		String xml = recontent.getXml();
		System.out.println("processResetData   " + "centerid   "
				+ centerid);
		System.out.println("processResetData   " + "xml   " + xml);
		int state=0;
		// 修改本地资源记录
		DataSelfSource dataSelfSource = new DataSelfSource();
		// 正在上传
		dataSelfSource.setFlag(state);
		dataSelfSource.setVersion(version);
		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		dataSelfSource.setUuid("center@001");
		dataSelfSource.setStartdate(dateNowStr);
		dataSelfSource.setEnddate(dateNowStr);
		DataSelfSourceDao.getInstance().insert(dataSelfSource);
		if (("").equals(xml) || null == xml) {
			System.out.println("xml文件为空啊。。。");
			state = 2;
		} else {
			System.out.println("处理xml开始啦！！！");
//			xml = xml.replace("{", "<");
//			xml = xml.replace("}", ">");
			List<String> sqls = JdbcToXml.getInstance().xmlToInsert(
					xml, centerid);
			if (sqls == null) {
				state = 2;
			} else {
				System.out.println("数据开始插入......SQL的数目：" + sqls.size());
				boolean result = false;
				try {
					result = JdbcImpl.getJdbcImpl()
							.updateSqls(sqls);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("数据全部执行完毕,返回结果：" + result);
				if (result)
					state = 0;
				else
					state = 2;
			}
		}
		if (state == 0) {

		} else {

		}
		DataSelfSourceDao.getInstance().updateUpState("center@001", state);
	}

	/**
	 * 获取所有需要还原的本地的版本号
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getResetVersionInfo(String centerid) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// 获取本地的版本号信息
		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);
		// 常海发送
		// content.setSender("0800272E784D@001");
		// content.setCenterid("0800272E784D@001");
		// 何涛发送
		content.setSender("center@001");
		content.setCenterid("center@001");

		// 处理还原信息 flag=3
		content.setFlag(MsgType.Load_RESET_CS);
		String json = JSON.toJSONString(content);
		System.out.println("getResetVersionInfo" + "   " + "json" + "   "
				+ json);
//		if (send(json)) {
//			// log.info("发送成功！发送内容："+content);
//			System.out.println("发送成功！发送内容：" + content);
//		} else {
//			// log.error("发送失败！发送内容："+content);
//			System.out.println("发送失败！发送内容：" + content);
//		}
		return resultList;
	}
	/**
	 * 获取本地所有的日志信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getClientLogsInfo(String centerid) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		String uuid=null;
		String sessionid=null;
		resultList=ViewLogs.getInstance().getClientLogs(uuid, sessionid,centerid);
		return resultList;
		
	}
	
	
	/**
	 * 获取服务器所有的日志信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getServerLogsInfo(String centerid) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// 获取本地的版本号信息
		DBSyncContent content = new DBSyncContent();
		List<String> strlist = new ArrayList<String>();
		strlist.add("0");
		strlist.add("2");
		strlist.add("3");
		content.setStrlist(strlist);
		// 常海发送
		// content.setSender("0800272E784D@001");
		// content.setCenterid("0800272E784D@001");
		// 何涛发送
		content.setSender("center@001");
		content.setCenterid("center@001");

		// 处理还原信息 flag=3
		content.setFlag(MsgType.VIEW_LOGS_CS);
		String json = JSON.toJSONString(content);
		System.out.println("getResetVersionInfo" + "   " + "json" + "   "
				+ json);
//		if (send(json)) {
//			// log.info("发送成功！发送内容："+content);
//			System.out.println("发送成功！发送内容：" + content);
//		} else {
//			// log.error("发送失败！发送内容："+content);
//			System.out.println("发送失败！发送内容：" + content);
//		}
		return resultList;
		
	}
	
	/**
	 * 获取上传反馈
	 * 
	 * @return
	 */
	public String getUpResult(String uuid, String operatorsessionid) {
		String str = "";
		List<HashMap<String, String>> selfList = DataSelfSourceDao.getInstance()
				.getAllSelfSource();
		if (selfList.size() > 0) {
			String flag = selfList.get(0).get("flag");
			System.out.println("flag" + flag);
			if (flag.equals("0")) {
				str = "正在上传，请稍等。。。";
			} else if (flag.equals("1")) {
				str = "上传失败，失败原因："
						+ DataOperateRecordDao.getInstance().getOneUserRecord(
								uuid, operatorsessionid)[5];
			} else if (flag.equals("2")) {
				str = "上传异常，异常建议：";
			} else if (flag.equals("3")) {
				str = "上传成功!";
			}
		}
		return str;
	}

	/**
	 * 获取上传是否完成反馈
	 * 
	 * @return
	 */
	public boolean getUpBack() {
		boolean flag = false;
		String upFlag = "0";
		List<HashMap<String, String>> selfList = DataSelfSourceDao.getInstance()
				.getAllSelfSource();
		if (selfList.size() > 0) {
			upFlag = selfList.get(0).get("flag");
			System.out.println("upFlag" + upFlag);
			// String:
			// 正在上传，请稍等。。。0
			// 上传失败，失败原因：1
			// 上传异常，异常建议：2
			// 上传成功!3
			if (upFlag.equals("3")) {
				flag = true;
			}
		}
		return flag;
	}
	
	
	/**
	 * 获取下载反馈
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadResult(String uuid, String operatorsessionid) {
		String str = "";
		List<HashMap<String, String>> loadList=null;
		try {
			loadList = DataNativeSourceDao.getInstance()
					.getAllNativeRecord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("getLoadResult error"+e);
			return null;
		}
		if (loadList.size() > 0) {
			
			for(int i=0;i<loadList.size();i++){
				
				String flag = loadList.get(i).get("flag");
				System.out.println("flag" + flag);
				if (flag.equals("0")) {
					str = "下载成功";
				} else if (flag.equals("1")) {
					str = "SQL执行出错失败，失败原因："
							+ DataOperateRecordDao.getInstance().getOneUserRecord(
									uuid, operatorsessionid)[5];
				} else if (flag.equals("2")) {
					str = "xml无数据！";
				} 
				loadList.get(i).put("loadinfo", str);
			}
		}
		return loadList;
	}


	/**
	 * 获取上传锁信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getUpMsg() {
		String operate = "上传";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	
	
	
	/**
	 * 获取下载锁信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getLoadMsg() {
		String operate = "下载";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 处理服务器返回数据
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
	public List<HashMap<String, String>> getSeverResetVersion() {
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
		// selfVersion="0";
		// selfServerVersion="2";
		System.out.println("selfVersion   " + selfVersion);
		System.out.println("selfServerVersion   " + selfServerVersion);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("----" + selfVersion != "-1"
				&& selfServerVersion != "-1");

		System.out.println("if");
		selfNextVersion = Integer.valueOf(selfVersion) > Integer
				.valueOf(selfServerVersion) ? Integer.valueOf(selfVersion) + 1
				: Integer.valueOf(selfServerVersion) + 1;
		nextVersion = String.valueOf(selfNextVersion);

		System.out.println("nextVersion    " + nextVersion);
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
	public void unUpLock(String uplockid) {

		String operate = "上传";
		DataOperateDao.getInstance().unLockOne(operate,uplockid);

	}

	/**
	 * 解除下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLoadLock(String loadlockid) {
		String operate = "下载";
		DataOperateDao.getInstance().unLockOne(operate,loadlockid);
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
	public HashMap<String, String> getUpLock() {
		String operate = "上传";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	/**
	 * 获取下载锁信息
	 * 
	 * @param uuid
	 * @return
	 */
	public HashMap<String, String> getLoadLock() {
		String operate = "下载";
		return DataOperateDao.getInstance().getMsg(operate);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("12");
		list.add("11");
		list.add("13");

		UpMessage ms = new UpMessage();
		ms.getNextVersion();

		/*
		 * UpMessage.getInstance().SendUpCommand(list);
		 * UpMessage.getInstance().getUpVersionInfo();
		 * System.out.println(UpMessage.getInstance().getUpBack());
		 * 
		 * UpMessage.getInstance().getUpResult();
		 */

	}

}
