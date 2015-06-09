package fxdigital.mqcore.exchange.impl;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.mqcore.exchange.rpc.PostConfig;
import fxdigital.mqcore.util.RequestType;


public class ClientMessageHandler implements IReciveHandler{
	private static Log logger=LogFactory.getLog(ClientMessageHandler.class);
	private ExchangeService exchangeServerice;
	public ClientMessageHandler(ExchangeService exchangeService) {
		exchangeServerice = exchangeService;
	}
	public void start(){
		PostConfig config=exchangeServerice.getConfig();
		logger.info("config:"+config.getPostChannelName());
		if(null!=config){
			String postIp=(null==config.getPostIp()?"localhost":config.getPostIp());
			int postPort=(0==config.getPostPort()?61616:config.getPostPort());
			String postChannelName=config.getPostChannelName();
			System.out.println(" "+postIp+" "+postPort+" "+postChannelName);
			BaseReciver receiver = new BaseReciver(true,
					postIp, postPort,
					true,postChannelName);
			receiver.start(this);
			logger.info("通道: "+postIp+" : "+ postPort +" :  "+postChannelName+"启动成功！");
		}else{
			logger.info("本级MQ设置失败！请重新设置");
		}
		
	}

	@Override
	public boolean handler(Message message) {
		// TODO Auto-generated method stub
		logger.info("收到新消息");
		return exchangeServerice.handler(RequestType.CLIENT_REQUEST,message);
	}

	

	
}
