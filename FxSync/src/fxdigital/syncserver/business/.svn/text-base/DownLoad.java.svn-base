package fxdigital.syncserver.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.syncserver.business.hibernate.dao.DataVersionDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MsgType;

public class DownLoad {
	
	
	
	
	public void initDownLoad(DBSyncContent content){
		Log4jUtil.info(DownLoad.class,"处理下载初始化数据 flag=12");
		content.setFlag(MsgType.Load_INIT_SC);
		List<HashMap<String, String>> list = DataVersionDao.getInstance()
				.getNoOneSource(content.getCenterid());
		content.setList(list);
		send(content);
	}
	
	public  void send(DBSyncContent content){
		Mail message=new Mail();
		message.setDestMailboxID(content.getCenterid());
		message.setContent(JSONObject.toJSONString(content));
		UpLoadBusiness.getSender().send(message);
	}
	
	public void processAutoDownLoad(DBSyncContent content){
		Log4jUtil.info(DownLoad.class,"处理下载命令flag=301");
		List<HashMap<String, String>> lastLoadList = null;
		List<HashMap<String, String>> serverLoadList = DataVersionDao.getInstance().getNoOneSource(
				content.getCenterid());
		Log4jUtil.info(DownLoad.class,"自动下载1  " + "clientLoadList  ");
		List<HashMap<String, String>> clientLoadList = content.getList();
		List<HashMap<String, String>> incrementClientList = content
				.getSecondList();
		Log4jUtil.info(DownLoad.class,"自动下载2  " + "clientLoadList  ");
		content.setFlag(MsgType.AUTO_LOAD_SC);
		List<HashMap<String, String>> tempLoadList = null;
		if (serverLoadList != null) {
			tempLoadList = new ArrayList<HashMap<String, String>>();
			Log4jUtil.info(DownLoad.class,"receive  " + "clientLoadList  " + clientLoadList);
			Log4jUtil.info(DownLoad.class,"receive  " + "serverLoadList  " + serverLoadList);
			tempLoadList = DownLoadUtil.getInstance().mergeMap(
					clientLoadList, tempLoadList);
			Log4jUtil.info(DownLoad.class,"receive  " + "tempLoadList1  " + tempLoadList);
			tempLoadList = DownLoadUtil.getInstance().mergeServerMap(
					serverLoadList, tempLoadList);
			Log4jUtil.info(DownLoad.class,"receive  " + "tempLoadList2  " + tempLoadList);
		     lastLoadList = new ArrayList<HashMap<String, String>>();
			Log4jUtil.info(DownLoad.class,"receive  " + "lastLoadList1  " + lastLoadList);
			lastLoadList = DownLoadUtil.getInstance().getAllMap(
					tempLoadList, clientLoadList, serverLoadList);
			Log4jUtil.info(DownLoad.class,"receive  " + "lastLoadList2  " + lastLoadList);
			lastLoadList = DownLoadUtil.getInstance().getLastLoadInfos(lastLoadList);
			Log4jUtil.info(DownLoad.class,"receive  " + "lastLoadList3  " + lastLoadList);
		}
		Log4jUtil.info(DownLoad.class,"自动下载3  " + "clientLoadList  ");
		lastLoadList=downAllServerData(lastLoadList);
		
		List<HashMap<String, String>> serverIncrementList = DataIncrementVersionDao
				.getInstance().getNoOneSource(content.getCenterid());
		serverIncrementList=downIncrementServerData(incrementClientList,serverIncrementList);
		Log4jUtil.info(DownLoad.class,"自动下载4  " + "clientLoadList  ");
		content.setList(lastLoadList);
		content.setSecondList(serverIncrementList);
		send(content);
	
	}
	
	
	//下载所有全量数据
	public List<HashMap<String, String>> downAllServerData(List<HashMap<String, String>> lastLoadList){
		List<HashMap<String, String>> lastAllList=null;
		if (null != lastLoadList && lastLoadList.size() > 0) {
			lastAllList=new ArrayList<HashMap<String, String>>();
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
					Log4jUtil.info(DownLoad.class,"下载文件地址centerid   " + clientCenterid
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
						Log4jUtil.info(DownLoad.class,"下载文件地址fileaddress   " + fileaddress);
						Log4jUtil.info(DownLoad.class,"下载文件地址oldfileaddress   "
								+ oldfileaddress);
						FileUtil fileUtil=new FileUtil();
						xml = fileUtil.readFileByLines(
								fileaddress);
						oldXml = fileUtil.readFileByLines(
								oldfileaddress);
						// xmlMap.put("xml", xml);

						Log4jUtil.info(DownLoad.class,"下载中心:" + clientCenterid
								+ "    下载xml文件: " + xml
								+ "    下载oldxml文件: " + oldXml);
					}
					map.put("oldXml", oldXml);
					map.put("newXml", xml);
					String json = JSONObject.toJSONString(map);
					lastLoadList.get(i).put("xml", json);
					lastAllList.add(lastLoadList.get(i));
				}
			
			}
		}
		Log4jUtil.info(DownLoad.class,"需要下载的全量版本: "+lastAllList);
		return lastAllList;
	}
	
	
	public List<HashMap<String, String>> downIncrementServerData(List<HashMap<String, String>> incrementClientList,List<HashMap<String, String>> serverIncrementList){
		// 得到增量的xml
		String serverIncrementVersion = "0";
		List<HashMap<String, String>> lastIncrementList=null;
		Log4jUtil.info(DownLoad.class,"客户端的增量版本: "+incrementClientList);
		Log4jUtil.info(DownLoad.class,"服务器端的增量版本: "+serverIncrementList);
		if (null != serverIncrementList && serverIncrementList.size() > 0) {
			lastIncrementList=new ArrayList<HashMap<String, String>>();
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
				Log4jUtil.info(DownLoad.class,"服务器的增量: "+" "+ serverIncrementList.get(i)
						.get("centerip")+" "+clientIncrementVersion+" "+serverIncrementVersion);
				if (Integer.valueOf(clientIncrementVersion) < Integer
						.valueOf(serverIncrementVersion)) {
					HashMap<String, String> map = new HashMap<String, String>();
					DownLoadUtil downUtil=new DownLoadUtil();
					List<HashMap<String, String>> incrementList = downUtil.getIncrementXml(
							centerid, clientIncrementVersion,
							serverIncrementVersion);
					map.put("incrementXml",
							JSONObject.toJSONString(incrementList));
					String json = JSONObject.toJSONString(map);
					serverIncrementList.get(i).put("xml", json);
					lastIncrementList.add(serverIncrementList.get(i));
				}
			}
		}
		Log4jUtil.info(DownLoad.class,"需要下载的增量版本: "+lastIncrementList);
		return lastIncrementList;
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
	
	//处理下载操作
	public void processDownCommand(DBSyncContent content){
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
		send(content);
	}
}
