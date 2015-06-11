package fxdigital.mqcore.exchange.impl;

import javax.jms.Message;

import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MessageChannelName;
import fxdigital.util.RequestType;

public class OldMessageHandler implements IReciveHandler{
	
	private ExchangeService exchangeServerice;
	public OldMessageHandler(ExchangeService exchangeService) {
		exchangeServerice = exchangeService;
	}
	public void start(){
		PostConfig config=exchangeServerice.getConfig();
		System.out.println("config"+config);
		if(null!=config){
			String postIp=(null==config.getPostIp()?"localhost":config.getPostIp());
			int postPort=(0==config.getPostPort()?61616:config.getPostPort());
			String postChannelName=MessageChannelName.UP_CLIENT_CHANNEL;
			System.out.println(" "+postIp+" "+postPort+" "+postChannelName);
			BaseReciver receiver = new BaseReciver(true,
					postIp, postPort,
					true,postChannelName);
			receiver.start(this);
			Log4jUtil.info(this.getClass(),"通道: "+postIp+" : "+ postPort +" :  "+postChannelName+"启动成功！");
		}else{
			Log4jUtil.info(this.getClass(),"本级MQ设置失败！请重新设置");
		}
		
	}
	

	public boolean handler(Message message) {
		// TODO Auto-generated method stub
		Log4jUtil.info(OldMessageHandler.class, "收到老同步服务器发过来的消息");
		return exchangeServerice.oldHandler(RequestType.OLDSERVER_REQUEST,message);
	}
	
}
