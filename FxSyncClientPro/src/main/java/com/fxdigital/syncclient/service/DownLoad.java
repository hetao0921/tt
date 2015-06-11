package com.fxdigital.syncclient.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.bean.DataIncrementVersion;
import com.fxdigital.syncclient.bean.DataNativeSource;
import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.DataIncrementVersionDao;
import com.fxdigital.syncclient.dao.DataNativeRecordSourceDao;
import com.fxdigital.syncclient.dao.DataNativeSourceDao;
import com.fxdigital.syncclient.dao.DataOperateRecordDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;
import com.fxdigital.syncclient.dao.NvmpCenterinfosynctabDao;

import fxdigital.dbsync.domains.client.db.JdbcToXml;
import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MessageChannelName;
import fxdigital.mqcore.util.MsgType;
import fxdigital.syncserver.app.impl.AppServer;
@Service
public class DownLoad {
	private static Logger logger = Logger.getLogger(DownLoad.class);
	
	@Autowired
	private DataNativeRecordSourceDao dataNativeRecordSourceDao;
	@Autowired
	private DataNativeSourceDao dataNativeSourceDao;
	@Autowired
	private DataOperateRecordDao dataOperateRecordDao;
	@Autowired
	private DataIncrementVersionDao dataIncrementVersionDao;
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private NvmpCenterinfosynctabDao nvmpCenterinfosynctabDao;
	
	List<HashMap<String, String>> clientLoadList = null;
	List<HashMap<String, String>> serverLoadList = null;
	List<HashMap<String, String>> tempLoadList = null;
	List<HashMap<String, String>> lastLoadList = null;
	
	
	
	
	/**
	 * 处理下载的数据 // 0:正在下载（非终态）1:正在发送（非终态） // 2：发送成功(非终态) 3：发送失败(非终态) //
	 * 4.正在解包（非终态） // 5:xml为空（终态）6:正在解析xml(非终态) // 7:sql为空（终态） //
	 * 8:正在插入数据（非终态）9:插入成功（终态） // 10插入失败（终态）11:下载完成（终态）
	 * 
	 * @return
	 */
	public void processLoadData(DBSyncContent recontent) {
		//得到所有全量数据
		List<HashMap<String, String>> loadList = recontent.getList();
		List<HashMap<String, String>> incrementList = recontent.getSecondList();
		int state = 0;
		String uuid = null;
		String operateinfo = "";
		String oneoperateinfo = "";
		String errorinfo = "";
		String loadState="-1";
		String loadBackState="-1";
		String centerId = recontent.getCenterid();
		int id  = recontent.getOperateid();
//		logger.info( "size  " + loadList.size()+"loadList---------" + loadList );
		if (null != loadList&&loadList.size() > 0) {
			for (int i = 0; i < loadList.size(); i++) {
				logger.info( "size  " + loadList.size()+"loadList---------" + loadList );
				
				String centerid = loadList.get(i).get("centerid");
				String centername =loadList.get(i).get("centername");
				String centerip = loadList.get(i).get("centerip");
				String version = loadList.get(i).get("serverversion");
				String allXml = loadList.get(i).get("xml");
				logger.info( "allXml  " +allXml);
				if ("".equals(allXml) || allXml == null) {
					logger.info("全量的xml全部为空啊。。。");
				}
				logger.info("开始处理中心centerid ---" + centerid+" i "+i);
				logger.info("allXml ---" + allXml);
				HashMap<String, String> map = JSONObject.parseObject(allXml,
						HashMap.class);
				logger.info("map ---" + map);
				String oldXml = map.get("oldXml");
				String newXml = map.get("newXml");
				
				logger.info("processLoadData   " + "centerid   " + centerid);
				logger.info("processLoadData   " + "oldXml   " + oldXml);
				logger.info("processLoadData   " + "newXml   " + newXml);
				loadState = "4";
				int serverversion = Integer.valueOf(version);
				dataNativeRecordSourceDao.recordAllStates(
						centerid, centername, centerip, serverversion, uuid,
						loadState);
				List<String> oldsqls = new ArrayList<String>();
				List<String> incrementsqls = new ArrayList<String>();
				boolean execResult = false;
				if (("").equals(newXml) || null == newXml) {
					logger.info("xml文件为空啊。。。");
					oneoperateinfo = "xml文件为空";
					errorinfo = "xml文件为空";
					loadState = "5";
					loadBackState = "1";
					dataNativeRecordSourceDao.recordAllStates(
							centerid, centername, centerip, serverversion,
							uuid, loadState);
					operateinfo += centerid + ":" + version + ":"
							+ oneoperateinfo + ";";
					dataOperateRecordDao.updateOperateRecord(id,
							centerId, operateinfo, errorinfo);
				} else {
					logger.info("处理xml开始啦！！！");
					loadState = "6";
					dataNativeRecordSourceDao.recordAllStates(
							centerid, centername, centerip, serverversion,
							uuid, loadState);
					boolean result = false;
					try {
						result = JdbcToXml.getInstance().xmlToInsert(newXml,
								centerid);
						// result = JdbcImpl.getJdbcImpl().updateSqls(sqls);
					} catch (Exception e) {
						oneoperateinfo = e.toString();
					}
					if(null==loadList.get(i).get("centername")||loadList.get(i).get("centername").equals("")){
						List<HashMap<String, String>> nvmpCenterList =nvmpCenterinfosynctabDao.getNvmpCenterinfosynctab(centerid);
					    centername = nvmpCenterList.get(0).get("centername");
					    centerip = nvmpCenterList.get(0).get("centerip");
					}
					logger.info("新全量数据全部执行完毕,返回结果：" + result);
					if (result) {
						// 插入成功
						oneoperateinfo = "插入成功";
						loadState = "9";
						dataNativeRecordSourceDao
								.recordAllStates(centerid, centername,
										centerip, serverversion, uuid,
										loadState);

						loadState = "11";
						dataNativeRecordSourceDao
								.recordAllStates(centerid, centername,
										centerip, serverversion, uuid,
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
						dataNativeSource.setUpdatetime(dateNowStr);
						dataNativeSource.setFlag(String.valueOf(state));
						dataNativeSource.setUuid(uuid);
						dataNativeSourceDao.insert(
								dataNativeSource);
						oneoperateinfo = "下载成功";
						operateinfo += centerid + ":" + version + ":"
								+ oneoperateinfo + ";";
					} else {
						// 插入失败
						errorinfo = "插入失败";
						// oneoperateinfo = "插入失败";
						loadState = "10";
						dataNativeRecordSourceDao
								.recordAllStates(centerid, centername,
										centerip, serverversion, uuid,
										loadState);
						operateinfo += centerid + ":" + version + ":"
								+ oneoperateinfo + ";";
						dataOperateRecordDao.updateOperateRecord(
								id, centerId, operateinfo, errorinfo);
						return;
					}
				}
			}
			
			logger.info("本次所有的全量xml处理结束。。。");
			dataOperateRecordDao.updateOperateRecord(id,
					centerId, operateinfo, errorinfo);
		}else{
			oneoperateinfo = "全量xml文件为空";
			errorinfo = "全量xml文件为空";
			operateinfo += centerId + ":" + "" + ":"
					+ oneoperateinfo + ";";
			dataOperateRecordDao.updateOperateRecord(id,
					centerId, operateinfo, errorinfo);
		}
		processIncrement(incrementList);
		loadBackState = "0";
	}
	
	
	public void processIncrement(List<HashMap<String,String>> incrementInfo){
		String loadState="-1";
		if(null!=incrementInfo&&incrementInfo.size()>0){
			for (int k = 0; k < incrementInfo.size(); k++) {
				if(null!=incrementInfo.get(k)
						.get("xml")&&!("").equals(incrementInfo.get(k)
						.get("xml"))){
					String allIncrementXml = incrementInfo.get(k)
							.get("xml").toString();
					HashMap<String, String> map = JSONObject.parseObject(allIncrementXml,
							HashMap.class);
					logger.info("map ---" + map);
					String incrementXml = map.get("incrementXml");
					List<JSONObject> incrementList = JSONObject.parseObject(
							incrementXml, List.class);
					if(null!=incrementList&&incrementList.size()>0){
						for(int i=0;i<incrementList.size();i++){
							logger.info("incrementList.get(k) "
									+ incrementList.get(i));
							String localcenterid = incrementList.get(i)
									.get("centerid").toString();
							String localversion = incrementList.get(i)
									.get("version").toString();
							String localcentername = incrementList.get(i)
									.get("centername").toString();
							String localcenterip = incrementList.get(i)
									.get("centerip").toString();
							String localIncrementXml = incrementList.get(i)
									.get("xml").toString();
							if (("").equals(localIncrementXml)
									|| null == localIncrementXml) {
								logger.info("localIncrementXml文件为空啊。。。");
							} else {
								logger.info("处理oldXml开始啦！！！");
								boolean execResult = JdbcToXml.getInstance().xmlToInsert(
										localIncrementXml, localcenterid);
								logger.info("本中心增量数据插入:"+localcenterid);
								if (execResult) {
									logger.info("插入localIncrementXml成功。。");
									// 修改数据版本data_version
									dataIncrementVersionDao.delete(
											localcenterid);
									DataIncrementVersion dataIncrementVersion = new DataIncrementVersion();
									dataIncrementVersion.setCenterid(localcenterid);
									dataIncrementVersion.setCenterip(localcenterip);
									dataIncrementVersion
											.setCentername(localcentername);
									dataIncrementVersion.setVersion(Integer
											.valueOf(localversion));
									dataIncrementVersionDao.insert(
											dataIncrementVersion);
								} else {
									logger.info("插入localIncrementXml失败。。");
									String errorinfo = "插入localIncrementXml失败。。";
									loadState = "10";
									return;
								}
							}						
						}
					}				
				}
			}
		}	
	}
	
	
	/**
	 * 获取所有需要下载的本地的版本号
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getOldLoadVersionInfo() {
		LocalCenter centerInfo = localCenterDao.queryInfo();
		clientLoadList = null;
		serverLoadList = null;
		tempLoadList = null;
		lastLoadList = null;
		// 获取本地的版本号信息
		try {
			clientLoadList = dataNativeSourceDao
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
		if (null != centerInfo) {
			centerid = centerInfo.getId();
			centername = centerInfo.getName();
			centerip = centerInfo.getIp();
		}



		content.setSender(centerid);
		content.setCenterid(centerid);

		content.setCentername(centername);
		content.setCenterip(centerip);

		// 处理下载初始化信息 flag=12
		content.setFlag(MsgType.Load_INIT_CS);
		String json = JSON.toJSONString(content);
		logger.info("getLoadVersionInfo" + "   " + "json" + "   " + json);
		Mail message = new Mail();
		message.setContent(json);
		message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
		if (AppServer.getInstance().send(message)) {
			// log.info("发送成功！发送内容："+content);
			logger.info("发送成功！发送内容：" + content);
		} else {
			// log.error("发送失败！发送内容："+content);
			logger.info("发送失败！发送内容：" + content);
		}
		return clientLoadList;
	}
	
	
	public void processInit(DBSyncContent recontent){
		serverLoadList = recontent.getList();
		tempLoadList = new ArrayList<HashMap<String, String>>();
		lastLoadList=null;
		if (serverLoadList != null) {
			logger.info("receive  " + "clientLoadList  " + clientLoadList);
			logger.info("receive  " + "serverLoadList  " + serverLoadList);
			tempLoadList =MergeMap(
					clientLoadList, tempLoadList);
			logger.info("receive  " + "tempLoadList1  " + tempLoadList);
			tempLoadList = MergeServerMap(
					serverLoadList, tempLoadList);
			logger.info("receive  " + "tempLoadList2  " + tempLoadList);
			lastLoadList = new ArrayList<HashMap<String, String>>();
			logger.info("receive  " + "lastLoadList1  " + lastLoadList);
			lastLoadList =getAllMap(
					tempLoadList, clientLoadList, serverLoadList);
			logger.info("receive  " + "lastLoadList2  " + lastLoadList);
			lastLoadList = getLastLoadInfos(lastLoadList);
			logger.info("receive  " + "lastLoadList3  " + lastLoadList);
		}
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
	
	
	/**
	 * 拼接两个list
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> getAllMap(List<HashMap<String,String>> allList,List<HashMap<String,String>> clientList,List<HashMap<String,String>> serverList){
		
		List<HashMap<String,String>> lastList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<allList.size();i++){
			HashMap<String,String> temp=new HashMap<String,String>();
			temp.put("centerid", allList.get(i).get("centerid"));
			//如果本地存在
			if(!allList.get(i).get("local").equals("")){
				int clientindex=Integer.valueOf(allList.get(i).get("local"));
				temp.put("clientversion", clientList.get(clientindex).get("version"));
				temp.put("centerid", clientList.get(clientindex).get("centerid"));
				temp.put("centername", clientList.get(clientindex).get("centername"));
				temp.put("centerip", clientList.get(clientindex).get("centerip"));
				temp.put("updatetime", clientList.get(clientindex).get("updatetime"));
				temp.put("flag", clientList.get(clientindex).get("flag"));
			}
			else{
				temp.put("clientversion", "0");
			}
			//如果远程服务器上存在
			if(!allList.get(i).get("remote").equals("")){
				int serverindex=Integer.valueOf(allList.get(i).get("remote"));
				temp.put("serverversion", serverList.get(serverindex).get("version"));
				temp.put("centerid", serverList.get(serverindex).get("centerid"));
				if(("").equals(temp.get("centername"))||null==temp.get("centername")){
					temp.put("centername", serverList.get(serverindex).get("centername"));
				}
				if(("").equals(temp.get("centerip"))||null==temp.get("centerip")){
				temp.put("centerip", serverList.get(serverindex).get("centerip"));
				}
			}
			else{
				temp.put("serverversion", "0");
			}
			
			lastList.add(temp);
		}
		return lastList;
	}
	
	/**
	 * 拼接客户端
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> MergeMap(List<HashMap<String,String>> clientList,List<HashMap<String,String>> tempList){
		
		for(int k=0;k<clientList.size();k++){
			int theIndex=getIndex(tempList,clientList.get(k));
			if(theIndex==-1){
				HashMap<String,String> tempmap=new HashMap<String,String>();
				tempmap.put("centerid", clientList.get(k).get("centerid"));
				tempmap.put("local", String.valueOf(k));
				tempmap.put("remote", "");
				tempList.add(tempmap);
				}else{
					tempList.get(theIndex).put("remote", String.valueOf(k));
			}
		}
		return tempList;
	}
	
	/**
	 * 拼接服务器端
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> MergeServerMap(List<HashMap<String,String>> clientList,List<HashMap<String,String>> tempList){
		
		for(int k=0;k<clientList.size();k++){
			int theIndex=getIndex(tempList,clientList.get(k));
			if(theIndex==-1){
				HashMap<String,String> tempmap=new HashMap<String,String>();
				tempmap.put("centerid", clientList.get(k).get("centerid"));
				tempmap.put("local", "");
				tempmap.put("remote", String.valueOf(k));
				tempList.add(tempmap);
				}else{
					tempList.get(theIndex).put("remote", String.valueOf(k));
			}
		}
		return tempList;
	}

	/**
	 * 是否存在当前map
	 * 
	 * 
	 * @param uuid
	 * @return
	 */
	public static int getIndex(List<HashMap<String,String>> clientList,HashMap<String,String> map2){
		int index=-1;
		for(int i=0;i<clientList.size();i++){
			if((clientList.get(i).get("centerid".toLowerCase())).equals(map2.get("centerid".toLowerCase()))){
				index=i;
			}
		}
		return index;
		
	}
	
	
	/**
	 * 得到服务器端返回的下载数据版本
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getMergeLoadVersion() {
		return lastLoadList;
	}
	
}
