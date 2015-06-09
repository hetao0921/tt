package fxdigital.syncserver.business;

import java.util.HashMap;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Msg;
import fxdigital.util.Log4jUtil;

public class OldSyncServer {
	

	public void getServerVersion(OldMessage oldMessage) {
		Msg msg = oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		String receiveId = null;
		String sessionid = Params.get("sessionid").toString();
		String centerid = Params.get("centerid").toString();
		String ip = null;
		if (null != Params.get("ip")) {
			ip = Params.get("ip").toString();
		}
		// 老服务器发来同步消息
		Log4jUtil.info(this.getClass(), "是否是新服务器： " + Params.get("centertype"));
		if (null == Params.get("centertype")) {
			Log4jUtil.info(this.getClass(), "老服务器发来同步消息" + centerid);
			String oldversion = OldSyncServerUtil.getInstance().getOldUpVersionInfo(centerid);
			String oldUrl = "DBSynchronization.ServerQueueSendNowVerson";
			// 参数：
			HashMap<String, Object> oldhp = new HashMap<String, Object>();
			oldhp.put("sessionid", sessionid);
			oldhp.put("centerid", centerid);
			oldhp.put("uuid", centerid);
			oldhp.put("ip", ip);
			oldhp.put("versions", oldversion);

			Msg oldm = new Msg();
			oldm.set_Url(oldUrl);
			oldm.AddParams(oldhp);
			byte[] data = oldMessage.getData();
			OldSyncServerUtil.getInstance().sendOldMessage(oldm, data, centerid);

		} else if (Params.get("centertype").toString().equals("new")) {
			Log4jUtil.info(this.getClass(), "新服务器发来同步消息" + centerid);
			String localUrl = "DBSynchronization.NewServerQueueSendNowVerson";
			String version = OldSyncServerUtil.getInstance().getNewUpVersionInfo();

			Log4jUtil.info(this.getClass(), "上级服务器获取的版本字符串:   " + version);
			// 参数：
			HashMap<String, Object> hp = new HashMap<String, Object>();
			hp.put("sessionid", sessionid);
			hp.put("centerid", centerid);
			hp.put("uuid", centerid);
			hp.put("ip", ip);
			hp.put("versions", version);

			Msg m = new Msg();
			m.set_Url(localUrl);
			m.AddParams(hp);
			byte[] data = oldMessage.getData();
			OldSyncServerUtil.getInstance().sendOldMessage(m, data, centerid);
		}
	}

	public void getServerData(OldMessage oldMessage) {
		Msg msg = oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		String receiveId = null;
		String sessionid = Params.get("sessionid").toString();
		String versions = Params.get("versions").toString();
		String localcenterid = Params.get("centerid").toString();
		String[] ver = versions.split("}");
		if (null != ver) {
			for (int i = 0; i < ver.length; i++) {
				String centerid = ver[i].substring(1).split(":")[0];
				String version = ver[i].substring(1).split(":")[1];
				// String centername = ver[i].substring(1).split(":")[2];
				// String ip = ver[i].substring(1).split(":")[3];
				byte[] data = OldSyncServerUtil.getInstance().getUpXmlInfo(centerid, version);
				String localUrl = "DBSynchronization.ServerDownLoadOver";
				// 参数：
				HashMap<String, Object> hp = new HashMap<String, Object>();
				hp.put("sessionid", centerid);
				hp.put("centerid", centerid);
				// hp.put("ip", ip);
				hp.put("uuid", centerid);
				hp.put("verson", version);

				Msg m = new Msg();
				m.set_Url(localUrl);
				m.AddParams(hp);
				Log4jUtil.info(this.getClass(), "发送版本数据" +centerid+": "+ version);
				// jMSSendDao.sendLocalMessage(destination, m, data,
				// localcenterid);

				OldSyncServerUtil.getInstance().sendOldMessage(m, data, localcenterid);
			}
		}
	}

	public void clientServerQueueSend(OldMessage oldMessage) {
		Msg msg = oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		String receiveId = null;
		String sessionid = Params.get("sessionid").toString();
		String ip = null;
		if (null != Params.get("ip")) {
			ip = Params.get("ip").toString();
		}
		Integer verson = null;
		String uuid = Params.get("uuid").toString();
		String centerid = Params.get("centerid").toString();
		String centername = Params.get("sessionid").toString();
		verson = Integer.parseInt(Params.get("versions").toString());
		// System.out.println("下载中心   :" + centerid + "下载版本: " + verson);
		Log4jUtil.info(this.getClass(), "从下级拉到的下载中心   :" + centerid +" : "+ centername + "下载版本: "
				+ verson);
		byte[] data = oldMessage.getData();
		try {
			OldSyncServerUtil.getInstance().writeLocalxml(verson, centerid, ip, ip,
					uuid, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.info(this.getClass(), "处理下载信息出错" + e);
		}
	}

}
