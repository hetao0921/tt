package fxdigital.postserver.outertransmition.tansmitters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import fxdigital.postserver.outertransmition.AbstractTransmitter;
import fxdigital.rpc.Mail;
import fxdigital.rpc.sendmode.BroadcastMode;

/**
 * 广播邮件
 * @author fucz
 * @version 2014-7-9
 */
@Component
public class BroadcastTransmitter extends AbstractTransmitter{
	
	private static final Logger log = Logger.getLogger(BroadcastTransmitter.class);

	@Override
	public boolean sendOut(Mail mail) {
		String preMailboxID = mail.getPreMailboxID();
		String superMailboxID = dbManager.getSuperID(localCenterID);
		List<String> allSubMailboxIDs = dbManager.getALlSubIDs(localCenterID);
		List<String> allMailboxIDs = new ArrayList<String>();
		allMailboxIDs.add(superMailboxID);
		if(allSubMailboxIDs != null){
			allMailboxIDs.addAll(allSubMailboxIDs);
		}
		allMailboxIDs.remove(preMailboxID);
		log.info("广播邮件："+mail);
		for(String next : allMailboxIDs){
			mail.setPreMailboxID(localCenterID);
			mail.setNextMailboxID(next);
			if(!exchange.exchange(mail)){
				log.error("发送到【"+next+"】失败，打回邮件："+mail);
				return false;
			}
		}
		return true;
	}

	@Override
	public String getMode() {
		return BroadcastMode.MODE;
	}

}
