package fxdigital.mqcore.exchange.impl;

import javax.jms.Message;

import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.exchange.IExchangeService;
import fxdigital.mqcore.exchange.IServiceListener;
import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.syncserver.business.specialclient.SpecialClient;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MessageChannelName;

public class SpecialExchangeService implements IExchangeService {

	private IServiceListener serviceListerner;
	static UpConfig config = null;
	BaseSender baseSender = null;
	ExchangeMessage ex = null;

	public SpecialExchangeService(SpecialClient specialClient) {
		this.serviceListerner = specialClient;
		this.ex=new ExchangeMessage();
	}

	public void init(UpConfig config) {
		this.config = config;
		SpecialMessageHandler oldMessageHandler = new SpecialMessageHandler(this);
		oldMessageHandler.start();

	}

	@Override
	public boolean handler(String msgFlag, Message message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean oldHandler(String msgFlag, Message message) {
		OldMessage oldMessage=OldMessage4Message.createOldMsg(message);
		return serviceListerner.onOldHandler(msgFlag, oldMessage);
	}

	public void send(OldMessage message) {
		System.out.println("config" + config.getUpIp());
		if (null == baseSender) {
			baseSender = new BaseSender(config.getUpIp(), config.getUpPort(),
					false);
		}
		Log4jUtil.info(ExchangeService.class, "发送消息给:IP地址" + config.getUpIp()
				+ " 端口： " + config.getUpPort() + " 队列名： "
				+ MessageChannelName.UP_CLIENT_CHANNEL);
		baseSender.sendMessage(MessageChannelName.UP_CLIENT_CHANNEL,
				message.getM(), message.getData(), message.getReceiveId(), ex);
	}

	public UpConfig getConfig() {
		return config;
	}

}
