package fxdigital.syncserver.business.specialclient;

import java.util.HashMap;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.business.SyncServerBusiness;
import fxdigital.util.Log4jUtil;

public class OldSyncClient {


	public void serverQueueSendNowVerson(OldMessage oldMessage) {
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		Log4jUtil.info(this.getClass(),"接收到反馈回来的版本。。。");
		String versions = Params.get("versions").toString();
		String sessionid = Params.get("sessionid").toString();
		// System.out.println("hehehhe" + versions);
		Log4jUtil.info(this.getClass(),"接收到反馈回来的版本" + versions);
		OldSyncClientUtil.getInstance().processLoadMsg(oldMessage,versions);
		
	}

	public void serverUpLoadOver(OldMessage oldMessage) {
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		// System.out.println("上传完成。。。");
		String uuid = Params.get("uuid").toString();
		Integer verson = Integer.valueOf(Params.get("verson").toString());
		Log4jUtil.info(this.getClass(),"回馈的版本号：" + verson);
		Log4jUtil.info(this.getClass(),"解锁完毕");
		String upFlag = "0";
		Log4jUtil.info(this.getClass(),"上传完成。。。");
		
	}

	public void serverDownLoadOver(OldMessage oldMessage) {
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();

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
		Log4jUtil.info(this.getClass(),"下载中心   :" + centerid + "下载版本: " + verson);
		try {
			OldSyncClientUtil.getInstance().writeLocalxml(verson, centerid, centername, ip, uuid, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.info(this.getClass(),"处理下载信息出错" + e);
		}	
	}
	
	
}
