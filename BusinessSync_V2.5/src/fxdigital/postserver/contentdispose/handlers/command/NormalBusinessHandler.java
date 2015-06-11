package fxdigital.postserver.contentdispose.handlers.command;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.RegisterCenter;
import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.rpc.Mail;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.BusinessContent;
import fxdigital.rpc.contenttype.BusinessType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点的业务消息处理
 * @author fucz
 * @version 2014-7-8
 */
@Component
public class NormalBusinessHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(NormalBusinessHandler.class);

	@Override
	public boolean handle(String strContent) {
		log.info("收到业务消息："+strContent);
		BusinessContent content = JSON.parseObject(strContent, BusinessContent.class);
		RegisterCenter rci = dbManager.getLocalRegisterCenterInfo(content.getReceiver());
		Mail mail = new Mail(JSON.toJSONString(content));
		mail.setContentType(BusinessType.TYPE);
		mail.setSendMode(NormalMode.MODE);
		mail.setSrcMailboxID(localCenterID);
		if(rci != null){
			if(!innerTransmitter.sendIn(rci.getChannelName(), content)){
				log.error("业务信息发送到中心【"+rci.getCenterID()+"】注册的通道失败！打回消息："
						+strContent);
				return false;
			}
			return true;
		}else{
			log.warn("没有ID为【"+content.getReceiver()+"】的注册中心，重新计算目的邮箱并发送！");
			return outerTransmitter.sendOut(mail);
		}
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(NormalMode.MODE,BusinessType.TYPE);
	}

}
