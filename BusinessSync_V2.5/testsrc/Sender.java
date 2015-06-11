import com.alibaba.fastjson.JSON;

import fxdigital.mqcore.base.BaseSender;
import fxdigital.rpc.Mail;
import fxdigital.rpc.content.BusinessContent;


/**
 * 
 * @author fucz
 * @version 2014-6-30
 */
public class Sender {

	public static void main(String[] args) {
		
		Mail mail = new Mail();
		BusinessContent content = new BusinessContent();
		content.setContent("test");
		content.setBusinessName("fxdigital.middle.business.TestBusiness");
		content.setReceiver("217@001");
		
		content.setSender("center@001");
		mail.setContent(JSON.toJSONString(content));
		mail.setSendMode("NormalMode");
		mail.setContentType("BusinessType");
		mail.setNextMailboxID("217@001");
		BaseSender sender = new BaseSender("192.168.1.211", 61616, true);
		sender.sendMessage("Exchange", mail);
		
		
	
	}

}
