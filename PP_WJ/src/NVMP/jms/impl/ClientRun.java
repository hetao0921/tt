package NVMP.jms.impl;

import java.util.HashMap;

import javax.jms.JMSException;

import NVMP.jms.ctrl.IMessageHander;
import NVMP.jms.ctrl.MessageSendHandler;
import NVMP.jms.rpc.Message;
import NVMP.jms.util.JNDIUtil;

public class ClientRun implements IMessageHander, MessageSendHandler {
	private ClientImpl ci;

	// 用来存储环境中所有注册的处理。
	HashMap<String, ProxyHandler> proxyHp;

	public void registerProxy(ProxyHandler obj) {
		proxyHp.put(obj.getClass().getSimpleName(), obj);
		obj.SetSendHandler(this);
	}

	public ClientRun(String name) throws JMSException {

		proxyHp = new HashMap<String, ProxyHandler>();

		ci = new ClientImpl(name);
		ci.AddListener(this);

//		JNDIUtil.again();

	}

	@Override
	public void QueueSend(String userid, String url,
			HashMap<String, Object> hp, byte[] data) throws JMSException {

		Message m = new Message();
		m.set_Url(url);
		m.AddParams(hp);
		ci.SendMessage(m, data);

	}

	@Override
	public void TopPush(String url, HashMap<String, Object> hp, byte[] data) {
		// TODO Auto-generated method stub
		System.out.println("很遗憾，客户端没这个功能。");
	}

	@Override
	public void ReceiveMessage(Message mg, byte[] data) {
		// TODO Auto-generated method stub

		// 根据message里面的相关信息，选择哪个注册。
		String url = mg.get_Url();
		String domainName = url.substring(0, url.indexOf("."));

		// System.out.println("domainName:" + domainName);
		ProxyHandler rd = this.proxyHp.get(domainName);
		if (rd != null)
			rd.ReturnEvent(mg.GetParams(), url, data);

	}

	public void start() throws JMSException {

		ci.start();

	}

	public void stop() throws JMSException {

		ci.stop();
	}

}
