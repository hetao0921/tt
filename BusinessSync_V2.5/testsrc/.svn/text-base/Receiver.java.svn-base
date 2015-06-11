import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.rpc.Mail;


/**
 * 
 * @author fucz
 * @version 2014-6-30
 */
public class Receiver implements IReciveHandler{
	
	public static BaseReciver receiver;

	public static void main(String[] args) {
		receiver = new BaseReciver(
				true,"192.168.1.116",61616,true,"test");
		receiver.start(new Receiver());
	}

	@Override
	public boolean handler(Mail message) {
		System.out.println(message.getContent());
		//receiver.distroy();
		return true;
	}

}
