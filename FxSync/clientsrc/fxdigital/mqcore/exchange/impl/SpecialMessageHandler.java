package fxdigital.mqcore.exchange.impl;

import javax.jms.Message;

import fxdigital.mqcore.base.BaseFilterReciver;
import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MessageChannelName;
import fxdigital.util.RequestType;

public class SpecialMessageHandler implements IReciveHandler{
	
	private SpecialExchangeService specialExchangeServerice;
	public SpecialMessageHandler(SpecialExchangeService specialExchangeServerice) {
		this.specialExchangeServerice = specialExchangeServerice;
	}

	
	public void start(){
		UpConfig config=specialExchangeServerice.getConfig();
		System.out.println("config"+config);
		if(null!=config){
			String upIp=(null==config.getUpIp()?"null":config.getUpIp());
			if(!("null").equals(upIp)){
			int upPort=(0==config.getUpPort()?61616:config.getUpPort());
			String upChannelName=config.getUpChannelName();
			String fileterStr = "receiveId='" + config.getFilterString() + "'";
			System.out.println(" "+upIp+" "+upPort+" "+upChannelName);
			BaseFilterReciver receiver = new BaseFilterReciver(true,
						upIp, upPort,
						true,upChannelName,fileterStr);
				receiver.start(this);
				Log4jUtil.info(this.getClass(),"通道上级: "+upIp+" : "+ upPort +" :  "+upChannelName+"启动成功！过滤标识为："+fileterStr);
			}else{
				Log4jUtil.info(this.getClass(),"上级未设置，请先设置上级！启动侦听通道失败！");
			}
		}
		
	}

	public boolean handler(Message message) {
		// TODO Auto-generated method stub
		Log4jUtil.info(SpecialMessageHandler.class, "收到老同步服务器发过来的消息");
		return specialExchangeServerice.oldHandler(RequestType.OLDSERVER_REQUEST,message);
	}	

}
