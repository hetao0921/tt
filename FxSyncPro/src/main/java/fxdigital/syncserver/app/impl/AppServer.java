package fxdigital.syncserver.app.impl;

import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.impl.ExchangeService;
import fxdigital.mqcore.exchange.impl.PostConfig;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.syncserver.app.IAppServer;
import fxdigital.syncserver.app.IBusiness;
import fxdigital.util.Log4jUtil;

/**
 * 所有配置文件入口
 * 
 * @author lizehua
 * 
 */
public class AppServer implements IAppServer, IServiceListener {

	private ExchangeService exchangeService;
	IBusiness upLoadBusiness  =null;
	IBusiness syncServerBusiness  =null;
	PostConfig config=null;
//	@Override
//	public void send() {
//		// TODO Auto-generated method stub
//		exchangeService.send();
//	}
//
//	@Override
	public void oldSend() {
		// TODO Auto-generated method stub
		exchangeService.oldSend();
	}

	public AppServer() {
		exchangeService = new ExchangeService(this);
	}

	void init() {
//		// 加载我的业务集合，业务入口
//	    upLoadBusiness  = new UpLoadBusiness();
//	    syncServerBusiness=new SyncServerBusiness();
//		upLoadBusiness.regApp(this);
//		syncServerBusiness.regApp(this);
//		// 启动底层通讯
//		config=PostConfigBundle.getConfig();
//		exchangeService.init(config);
//		

	}


	public void setProperties(PostConfig config){
		 this.config=config;
	}
	
	public PostConfig getProperties(){
		 return config;
	}



	public static void main(String[] args) {
		AppServer appServer = new AppServer();
		appServer.init();
//		appServer.send();
//		appServer.oldSend();
		//启动一个线程启动特殊客户端
		SpecialThread specialThread=new SpecialThread();
		specialThread.start();
		
	
	}

	
	public boolean onhandler(String msgFlag,IMessage message) {
		System.out.println("msgFlag" + msgFlag);
		upLoadBusiness.OnMessage(msgFlag,message);
		return true;
	}
	
	
	
	public boolean onOldHandler(String msgFlag,IMessage message){
		System.out.println("msgFlag" + msgFlag);	
		syncServerBusiness.OnMessage(msgFlag, message);
		return true;
		
	}

	
	public void send(IMessage message) {
		if(message instanceof Mail){
			Log4jUtil.info(AppServer.class, "发送新消息到客户端");
			exchangeService.send((Mail)message);
		} else if(message instanceof OldMessage) {
			exchangeService.send((OldMessage)message);
		}	
	}
	
	static class SpecialThread extends Thread {
		public void run() {
//			SpecialClientThread.startThead();
		}
	}
	

}
