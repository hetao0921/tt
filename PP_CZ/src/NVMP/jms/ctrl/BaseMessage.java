package NVMP.jms.ctrl;

import java.util.HashMap;
/**
 * 传送消息的模板
 * */
public class BaseMessage {
	private HashMap<String, String> messageHp;
	private byte[] array;

	public BaseMessage() {
		messageHp = new HashMap<String, String>();
	}

	public HashMap<String, String> getMessageHp() {
		return messageHp;
	}

	public void setALlMessageProperty(HashMap<String, String> messageHp) {
		this.messageHp = messageHp;
	}

	public void addMessageProperty(String keyname, String value) {
		messageHp.put(keyname, value);
	}


	public byte[] getArray() {
		return array;
	}

	public void setArray(byte[] array) {
		this.array = array;
	}

}
