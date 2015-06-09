package fxdigital.syncserver.app.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.impl.ExchangeService;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.exchange.rpc.PostConfig;
import fxdigital.syncserver.app.HandlerChain;
import fxdigital.syncserver.app.IAppServer;
import fxdigital.syncserver.app.IDispatcher;
import fxdigital.syncserver.app.MessageDispatcher;


/**
 * 所有配置文件入口
 * 
 * @author lizehua
 * 
 */
public class AppServer implements IAppServer, IServiceListener {
	private static Log logger=LogFactory.getLog(AppServer.class);
	private ExchangeService exchangeService;
	IDispatcher dispatcher  =null;

	PostConfig config=null;
	private static AppServer appServer;

	private AppServer() {
		exchangeService = new ExchangeService(this);
	}
	public static AppServer getInstance(){
		if(appServer==null){
			appServer=new AppServer();
			
		} 
		
		return appServer;
	}

	public void init(PostConfig config,HandlerChain handlerChain) {
		this.config=config;
		// 加载我的业务集合，业务入口
		dispatcher  = new MessageDispatcher();
		// 启动底层通讯
		dispatcher.setHandler(handlerChain);
		exchangeService.init(config);
		

	}


	public void setProperties(PostConfig config){
		 this.config=config;
	}
	
	public PostConfig getProperties(){
		 return config;
	}




	@Override
	public boolean onhandler(String msgFlag,IMessage message) {
		System.out.println("msgFlag" + msgFlag);
		dispatcher.dispatcher(msgFlag,message);
		return true;
	}
	
	


	@Override
	public boolean send(IMessage message) {
			
			logger.info("发送新消息到客户端");
			return exchangeService.send((Mail)message);
		
	}

	@Override
	public boolean onOldHandler(String msgFlag, IMessage message) {
		// TODO Auto-generated method stub
		return false;
	}
	

	

}
