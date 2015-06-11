package fxdigital.mqcore.exchange.impl;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.exchange.IExchangeService;
import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.exchange.rpc.PostConfig;
import fxdigital.mqcore.util.MessageChannelName;


/**
 * 接收和发送消息的中间处理层， 将消息抛给应用层，将应用层的消息取回处理,分为客户端请求、新服务器请求、老服务器请求
 * 
 * @author lizehua
 * 
 */
public class ExchangeService implements IExchangeService {
	private static Log logger=LogFactory.getLog(ExchangeService.class);
	private IServiceListener serviceListerner;
	
	static PostConfig config=null;
	BaseSender baseSender = null; 
	ExchangeMessage ex=null;
	//第一侦听通道
	//第二个
	//3
	
	
	//消息发送队列工具


	public ExchangeService(IServiceListener listener) {
		serviceListerner = listener;
		ex=new ExchangeMessage();
	}

	public void init(PostConfig config) {
		// 按照app传入信息，创建监听通道,把自己作为监听者传入
		this.config=config;
		ClientMessageHandler clientMessageHandler = new ClientMessageHandler(
				this);
		clientMessageHandler.start();
		

		
	}



	@Override
	public boolean handler(String msgFlag, Message message) {
		logger.info("收到消息，发送给应用层。");
		Mail mail=Mail4Message.createMsg(message);
		return serviceListerner.onhandler(msgFlag, mail);

	}



	public boolean send(Mail message) {
		// TODO Auto-generated method stub
		logger.info( "发送新消息到客户端");
		logger.info( "config"+config.getPostIp()+config.getPostPort());
		if(null==baseSender){
			baseSender = new BaseSender(config.getPostIp(), config.getPostPort(), false);
		}
	
		logger.info("发送消息给:IP地址"+ config.getPostIp()+" 端口： "+config.getPostPort()+" 队列名： "+message.getSendChannel());
		return baseSender.sendMessage(message.getSendChannel(), message,ex);
	}



	public PostConfig getConfig(){
		return config;
	}

	@Override
	public boolean oldHandler(String msgFlag, Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
