import com.alibaba.fastjson.JSON;

import fxdigital.client.BasicTelecomImpl;
import fxdigital.client.IMessageListener;
import fxdigital.rpc.IContent;
import fxdigital.rpc.content.BusinessContent;


/**
 * 
 * @author fucz
 * @version 2014-7-18
 */
public class Client implements IMessageListener{

	@Override
	public boolean handle(IContent content) {
		BusinessContent bc = (BusinessContent) content;
		System.out.println(JSON.toJSON(bc));
		return true;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		BasicTelecomImpl bt = new BasicTelecomImpl(
				"fcz@001", "192.168.1.116", "192.168.1.116", 61616);
		bt.registerInSyncServer();
		bt.setListener(client);
//		BusinessContent content = new BusinessContent();
//		content.setContent("test");
//		content.setReceiver("lzh@001");
//		content.setSender("fcz@001");
//		bt.send(NormalMode.MODE, content);
	}

	@Override
	public IContent getContentSimple() {
		return new BusinessContent();
	}

}
