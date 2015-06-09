package fxdigital.syncserver.business.specialclient;

import java.util.HashMap;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Msg;
import fxdigital.util.Log4jUtil;

public class NewSyncClient {
	
	public void newServerQueueSendNowVerson(OldMessage oldMessage) {
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		Log4jUtil.info(this.getClass(),"接收到反馈回来的版本。。。");
		String versions = Params.get("versions").toString();
		String sessionid = Params.get("sessionid").toString();
		// System.out.println("hehehhe" + versions);
		Log4jUtil.info(this.getClass(),"接收到反馈回来的版本" + versions);

		// the old process
		// processNewLoadMsg(versions);
		NewSyncClientUtil.getInstance().processNewUpLoadMsg(oldMessage,versions);
		
	}
	public void newServerDownLoadOver(OldMessage oldMessage) {
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();


		// System.out.println("下载完成。。。");
		Integer verson = null;
		String uuid = Params.get("uuid").toString();
		String centername = Params.get("sessionid").toString();
		String ip = Params.get("ip").toString();
		String centerid = Params.get("centerid").toString();
		verson = Integer.parseInt(Params.get("verson").toString());
		// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
		Log4jUtil.info(this.getClass(),"下载中心   :" + centerid +centername+ip+ "下载版本: " + verson);
		try {
			NewSyncClientUtil.getInstance().writeNewIncrementXml(verson, centerid, centername, ip, uuid,
					data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.info(this.getClass(),"处理下载信息出错" + e);
		}

	
		
	}

}
