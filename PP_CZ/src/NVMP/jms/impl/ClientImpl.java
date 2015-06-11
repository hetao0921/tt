package NVMP.jms.impl;

import javax.jms.JMSException;

import NVMP.jms.ctrl.IMessageHander;
import NVMP.jms.ctrl.QueueConsole;
import NVMP.jms.ctrl.TopConsole;
import NVMP.jms.rpc.Message;

/**
 * 在同步数据的时候，客户端 主要功能有： 1、发送数据 2、接收数据 3、接收公告
 * 
 * */
public class ClientImpl {
	private QueueConsole recive_QueueConsole;
	private QueueConsole sned_QueueConsole;
	private String clientid;
	
	public ClientImpl(String userid) throws JMSException {
		clientid = userid;
		recive_QueueConsole = new QueueConsole(userid, "serverQueue");
		sned_QueueConsole = new QueueConsole(userid, "clientQueue");
	}

	public void AddListener(IMessageHander im) {
		recive_QueueConsole.addFilterListener(im);
	}
	
	
	public void SendMessage(Message mg,byte[] data) throws JMSException {
		sned_QueueConsole.SendMessage(null, mg, data);
	}
	
	public void stop() throws JMSException {
		recive_QueueConsole.stop();
		sned_QueueConsole.stop();
	}
	
	public void start() throws JMSException {
		recive_QueueConsole.start();
		sned_QueueConsole.start();
	}	
	

}
