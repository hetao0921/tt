package fxdigital.syncserver.business;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import fxdigital.mqcore.exchange.impl.PostConfig;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Msg;
import fxdigital.syncserver.app.IAppServer;
import fxdigital.syncserver.app.IBusiness;
import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;
import fxdigital.syncserver.business.hibernate.bean.SyncUpnetworkinfo;
import fxdigital.syncserver.business.hibernate.dao.LocalCenterDao;
import fxdigital.syncserver.business.hibernate.dao.SyncUpnetworkinfoDao;
import fxdigital.util.Log4jUtil;
import fxdigital.util.RequestType;

public class SyncServerBusiness implements IBusiness{
	
	public static IAppServer sender ;
	OldSyncServer oldSyncServer=null;
	NewSyncServer newSyncServer=null;


	@Override
	public List<String> getHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void regApp(IAppServer appServer) {
		// TODO Auto-generated method stub
		sender = appServer;
		oldSyncServer=new OldSyncServer();
		newSyncServer=new NewSyncServer();
		
	}

	public static IAppServer getSender(){
		return sender;
		
	} 

	@Override
	public void OnMessage(String strFlag,IMessage message) {
		OldMessage oldMessage=(OldMessage)message;
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		Log4jUtil.info(SyncServerBusiness.class,"msg" + "同步级联收到消息");
		Log4jUtil.info(SyncServerBusiness.class,"msg" + msg.get_Url());
		if (strFlag.equals(RequestType.OLDSERVER_REQUEST)) {
			try {
				String srt2 = new String(data, "UTF-8");
				Log4jUtil.info(SyncServerBusiness.class,"oldxml" + srt2);
				Log4jUtil.info(SyncServerBusiness.class,"收到老同步服务器发过来的同步消息"+msg);
				Log4jUtil.info(SyncServerBusiness.class,"msg" + msg.get_Url());
				if(msg.get_Url().equals("DBSynchronization.ClientQueueNowVerson")){
					Log4jUtil.info(SyncServerBusiness.class,"获取本级服务器版本");
					oldSyncServer.getServerVersion(oldMessage);
				}
				if(msg.get_Url().equals("DBSynchronization.ClientQueueGetData")){
					Log4jUtil.info(SyncServerBusiness.class,"获取本级服务器版本");
					oldSyncServer.getServerData(oldMessage);
				}
				if(msg.get_Url().equals("DBSynchronization.ClientServerQueueSend")){
					Log4jUtil.info(SyncServerBusiness.class,"获取本级服务器版本");
					oldSyncServer.clientServerQueueSend(oldMessage);
				}
				if(msg.get_Url().equals("DBSynchronization.SendOldQueueString")){
					Log4jUtil.info(SyncServerBusiness.class,"获取本级服务器版本");
					newSyncServer.sendOldQueueString(oldMessage);
				}
				
				//处理新上级传递过来的同步信息
				if (msg.get_Url().equals("DBSynchronization.NewClientQueueGetData")) {
					Log4jUtil.info(SyncServerBusiness.class,"接收到反馈回来的版本。。。");
					Log4jUtil.info(SyncServerBusiness.class,"上级处理 " + msg.get_Url());
					String versions = Params.get("versions").toString();
					String localcenterid = Params.get("centerid").toString();
					newSyncServer.processSubData(localcenterid, versions);
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (strFlag.equals(RequestType.NEWSERVER_REQUEST)) {
			try {
				String srt2 = new String(data, "UTF-8");
				Log4jUtil.info(SyncServerBusiness.class,"oldxml" + srt2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log4jUtil.info(SyncServerBusiness.class,"msg" + msg.get_Url());
		}
		
	}


	
	
	
	

}
