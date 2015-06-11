package fxdigital.postserver.outertransmition.tansmitters;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.postserver.outertransmition.AbstractTransmitter;
import fxdigital.rpc.IContent;
import fxdigital.rpc.Mail;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点发送邮件
 * @author fucz
 * @version 2014-7-9
 */
@Component
public class NormalTransmitter extends AbstractTransmitter{
	
	private static final Logger log = Logger.getLogger(NormalTransmitter.class);

	@Override
	public boolean sendOut(Mail mail) {
		IContent content = JSON.parseObject(mail.getContent(), IContent.class);
		String destCenterID = content.getReceiver();
		String destMailboxID = mail.getDestMailboxID();
		String nextMailboxID = null;
		if(destMailboxID != null){
			nextMailboxID = outerTransmitter.calRoute(destMailboxID,false);
		}else{
			nextMailboxID = outerTransmitter.calRoute(destCenterID,true);
		}
		if(nextMailboxID == null){
			return true;
		}else{
			mail.setPreMailboxID(localCenterID);
			mail.setNextMailboxID(nextMailboxID);
		}
		log.info("发送点对点的邮件："+mail);
		if(!exchange.exchange(mail)){
			log.error("发送到【"+nextMailboxID+"】失败，打回邮件："+mail);
			return false;
		}
		return true;
	}

	@Override
	public String getMode() {
		return NormalMode.MODE;
	}

}
