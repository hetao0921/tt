package fxdigital.postserver.separation.separaters;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.RegisterCenter;
import fxdigital.postserver.separation.AbstractSeparater;
import fxdigital.rpc.IContent;
import fxdigital.rpc.Mail;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点模式的邮件分发器
 * @author fucz
 * @version 2014-7-8
 */
@Component
public class NormalModeSeparater extends AbstractSeparater{
	
	private static final Logger log = Logger.getLogger(NormalModeSeparater.class);

	@Override
	public boolean handle(Mail mail) {
		if(mail.isIpMail()){
			return contentHandler.handle(mail);
		}
		IContent content = JSON.parseObject(mail.getContent(), IContent.class);
		RegisterCenter rci = dbManager.getSyncRegisterCenter(content.getReceiver());
		String destMailboxID = mail.getDestMailboxID();
		if(destMailboxID == null){
			if(rci == null){
				log.warn("没有找到目的邮箱，向上转发邮件！");
				//邮件目的地是上级节点，向上转发此邮件
				return outerTransmitter.sendOut(mail);
			}else{
				destMailboxID = rci.getServerID();
			}
		}
		if(destMailboxID.equals(localCenterID)){
			//邮件目的地是本节点，处理邮件
			return contentHandler.handle(mail);
		}else{
			//邮件目的地是下级节点，向下转发此邮件
			return outerTransmitter.sendOut(mail);
		}
	}

	@Override
	public String getMode() {
		return NormalMode.MODE;
	}
	
}
