package fxdigital.postserver.separation.separaters;

import org.springframework.stereotype.Component;

import fxdigital.postserver.separation.AbstractSeparater;
import fxdigital.rpc.Mail;
import fxdigital.rpc.sendmode.BroadcastMode;

/**
 * 广播模式的邮件分发器
 * @author fucz
 * @version 2014-7-8
 */
@Component
public class BroadcastModeSeparater extends AbstractSeparater{

	@Override
	public boolean handle(Mail mail) {
		//本级处理邮件
		if(!contentHandler.handle(mail)){
			return false;
		}
		//向外广播
		if(!outerTransmitter.sendOut(mail)){
			return false;
		}
		return true;
	}

	@Override
	public String getMode() {
		return BroadcastMode.MODE;
	}

}
