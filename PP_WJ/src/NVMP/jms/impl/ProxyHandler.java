package NVMP.jms.impl;

import java.util.HashMap;

import NVMP.jms.ctrl.MessageSendHandler;

public interface ProxyHandler {

	void ReturnEvent(HashMap<String, Object> getParams, String url, byte[] data);

	void SetSendHandler(MessageSendHandler ms);

}
