package fxdigital.syncserver.business.specialclient;

import java.util.HashMap;

import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.impl.PostConfig;
import fxdigital.mqcore.exchange.impl.SpecialExchangeService;
import fxdigital.mqcore.exchange.impl.UpConfig;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.mqcore.util.UpConfigBundle;
import fxdigital.syncserver.business.SyncServerBusiness;
import fxdigital.syncserver.business.hibernate.bean.NvmpCenterinfotab;
import fxdigital.syncserver.business.hibernate.dao.LocalCenterDao;
import fxdigital.util.Log4jUtil;
import fxdigital.util.RequestType;

public class SpecialClient implements IServiceListener{
	
	private SpecialExchangeService specialExchangeService;
	OldSyncClient oldSyncClient;
	NewSyncClient newSyncClient;
	private LocalCenterDao localCenterDao;
	UpConfig config=null;
	public static SpecialClient sender;
	
	public void init(){
		//启动侦听上级队列
		config=UpConfigBundle.getConfig();
		specialExchangeService.init(config);
		this.sender=new SpecialClient();
	}
	
	public SpecialClient() {
		localCenterDao =new LocalCenterDao();
		specialExchangeService = new SpecialExchangeService(this);
		oldSyncClient=new OldSyncClient();
		newSyncClient=new NewSyncClient();
	}
	
	
	public static void main(String[] args) {
		SpecialClient specialClient=new SpecialClient();
		specialClient.init();
	    specialClient.SendAutoSyncCommand();
		
		
	}
	
	
	public static SpecialClient getSender(){
		return sender;
	}

	
	public boolean SendAutoSyncCommand(){
		if(null!=config.getUpIp()&&!("").equals(config.getUpIp())){
			NvmpCenterinfotab localCenter=localCenterDao.query();
			OldMessage oldMessage=new OldMessage();
			String sessionid=null;
			String centerid=null;
			String ip=null;
			if(null!=localCenter){
				 ip=String.valueOf(localCenter.getCenterIp());
				 sessionid=String.valueOf(localCenter.getCenterName());
				 centerid=String.valueOf(localCenter.getCenterId());
			}
			String url = "DBSynchronization.ClientQueueNowVerson";
			byte[] data=Encoding.StringToByte("<test>");
			String receiveId="receiveFlag";
			
			// 参数:
			HashMap<String, Object> hp = new HashMap<String, Object>();
			hp.put("sessionid", sessionid);
			hp.put("centerid", centerid);
			hp.put("ip", ip);
			hp.put("centertype", "new");
			
			Msg m = new Msg();
			m.set_Url(url);
			m.AddParams(hp);
			oldMessage.setData(data);
			oldMessage.setM(m);
			oldMessage.setReceiveId(receiveId);
			send(oldMessage);
			return true;
		}else{
			Log4jUtil.error(this.getClass(),"上级服务器未设置！未成功发送同步指令！");
			return false;
		}
	}

	

	@Override
	public boolean onOldHandler(String msgFlag, IMessage message) {

		System.out.println("msgFlag" + msgFlag);	
		OldMessage oldMessage=(OldMessage)message;
		byte[] data=oldMessage.getData();
		Msg msg=oldMessage.getM();
		HashMap<String, Object> Params = msg.GetParams();
		if (msgFlag.equals(RequestType.OLDSERVER_REQUEST)) {
			Log4jUtil.info(SyncServerBusiness.class,"收到老同步服务器发过来的同步消息"+msg);
			Log4jUtil.info(SyncServerBusiness.class,"消息标识" + msg.get_Url());
			if(msg.get_Url().equals("DBSynchronization.ServerQueueSendNowVerson")){
				Log4jUtil.info(this.getClass(),"ServerQueueSendNowVerson");
				oldSyncClient.serverQueueSendNowVerson(oldMessage);
			}
			if(msg.get_Url().equals("DBSynchronization.NewServerQueueSendNowVerson")){
				Log4jUtil.info(this.getClass(),"NewServerQueueSendNowVerson");
				newSyncClient.newServerQueueSendNowVerson(oldMessage);
			}
			if(msg.get_Url().equals("DBSynchronization.ServerUpLoadOver")){
				Log4jUtil.info(this.getClass(),"ServerUpLoadOver");
				oldSyncClient.serverUpLoadOver(oldMessage);
			}
			if(msg.get_Url().equals("DBSynchronization.NewServerDownLoadOver")){
				Log4jUtil.info(this.getClass(),"NewServerDownLoadOver");
				newSyncClient.newServerDownLoadOver(oldMessage);
			}
			if(msg.get_Url().equals("DBSynchronization.ServerDownLoadOver")){
				Log4jUtil.info(this.getClass(),"ServerDownLoadOver");
				oldSyncClient.serverDownLoadOver(oldMessage);
			}
			
		}
		return true;
	
	}

	@Override
	public PostConfig getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void send(IMessage message) {
      if(message instanceof OldMessage) {
			specialExchangeService.send((OldMessage)message);
		}	
	}

	@Override
	public boolean onhandler(String msgFlag, IMessage message) {
		// TODO Auto-generated method stub
		return false;
	}
}
