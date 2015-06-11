package NVMP.jms.ctrl;

import java.util.HashMap;

import javax.jms.JMSException;

public interface MessageSendHandler {
	public void QueueSend(String userid, String url,
			HashMap<String, Object> hp, byte[] data) throws JMSException;

	public void TopPush(String url, HashMap<String, Object> hp, byte[] data);

}
