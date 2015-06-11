package fxdigital.postserver.contentdispose.handlers.command;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.RegisterCenter;
import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.BusinessContent;
import fxdigital.rpc.contenttype.BusinessType;
import fxdigital.rpc.sendmode.BroadcastMode;

/**
 * 广播的业务消息处理
 * @author fucz
 * @version 2014-7-8
 */
@Component
public class BroadcastBusinessHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(BroadcastBusinessHandler.class);

	@Override
	public boolean handle(String strContent) {
		log.info("收到广播的业务消息："+strContent);
		BusinessContent content = JSON.parseObject(strContent, BusinessContent.class);
		List<RegisterCenter> rcis = dbManager.getAllLocalRegisterCenter();
		if(rcis == null){
			log.warn("没有中心注册在本级MQ，丢弃消息："+strContent);
			return true;
		}else{
			for(RegisterCenter rci:rcis){
				if(!rci.getCenterID().equals(content.getSender())){
					if(!innerTransmitter.sendIn(rci.getChannelName(), content)){
						log.error("业务信息发送到中心【"+rci.getCenterID()+"】注册的通道失败！打回消息："
									+strContent);
						return false;
					}
				}
			}
			return true;
		}
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(BroadcastMode.MODE,BusinessType.TYPE);
	}

}
