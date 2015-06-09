package fxdigital.syncserver.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.syncserver.business.hibernate.dao.DataVersionDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class NewSyncServer {
	
	
	public void NewClientQueueGetData(OldMessage oldMessage){
	    Msg msg=oldMessage.getM();
	    HashMap<String, Object> Params = msg.GetParams();
		String receiveId = null;
		String sessionid = Params.get("sessionid").toString();
		String centerid = Params.get("centerid").toString();
		String ip = null;
		if (null != Params.get("ip")) {
			ip = Params.get("ip").toString();
		}
		String versions = Params.get("versions").toString();
		String localcenterid = Params.get("centerid").toString();
		processSubData(localcenterid, versions);
		
	}
	
	
    public void SendOldQueueString(OldMessage oldMessage){
  	    Msg msg=oldMessage.getM();
  	    HashMap<String, Object> Params = msg.GetParams();
 			String receiveId = null;
 			String sessionid = Params.get("sessionid").toString();
 			String ip = null;
 			if (null != Params.get("ip")) {
 				ip = Params.get("ip").toString();
 			}

			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centername = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			verson = Integer.parseInt(Params.get("versions").toString());
			// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
			Log4jUtil.info(this.getClass(),"下载中心   :" + centerid + "下载版本: " + verson);
			byte[] data=oldMessage.getData();
			try {
				NewSyncServerUtil.getInstance().writeNewIncrementXml(verson, centerid, centername, ip, uuid,
						data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log4jUtil.info(this.getClass(),"处理下载信息出错" + e);
			}

		
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
		NewSyncServerUtil.getInstance().sendOldMessage(m, newData, localcenterid);
//		jMSSendDao.sendLocalMessage(destination, m, newData, localcenterid);
	}


	public void sendOldQueueString(OldMessage oldMessage) {

  	    Msg msg=oldMessage.getM();
  	    HashMap<String, Object> Params = msg.GetParams();
 			String receiveId = null;
 			String sessionid = Params.get("sessionid").toString();
 			Log4jUtil.info(this.getClass(),"从上级获取的下载中心IP   :"+Params.get("ip"));
 			String ip = null;
 			if (null != Params.get("ip")) {
 				ip = Params.get("ip").toString();
 			}

			// System.out.println("下载完成。。。");
			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centername = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			verson = Integer.parseInt(Params.get("versions").toString());
			// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
			Log4jUtil.info(this.getClass(),"从上级获取的下载中心   :" + centerid +" 中心名称: "+centername+ "下载版本: " + verson);
			byte[] data=oldMessage.getData();
			try {
				NewSyncServerUtil.getInstance().writeNewIncrementXml(verson, centerid, centername, ip, uuid,
						data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log4jUtil.info(this.getClass(),"处理下载信息出错" + e);
			}

		
     
		
	}
}
