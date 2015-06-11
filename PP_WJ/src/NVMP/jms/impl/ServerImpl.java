package NVMP.jms.impl;

import javax.jms.JMSException;
import NVMP.jms.ctrl.IMessageHander;

import NVMP.jms.ctrl.QueueConsole;
import NVMP.jms.ctrl.TopConsole;
import NVMP.jms.rpc.Message;

/**
 * 在同步数据的时候，用来做主机的 主要功能是： 发布消息 
 * queue_server通道的发送者
 * 
 * */
public class ServerImpl{

	private QueueConsole recive_QueueConsole;
	private QueueConsole sned_QueueConsole;

	public ServerImpl(String userid) throws JMSException {
		recive_QueueConsole = new QueueConsole(userid, "clientQueue");
		sned_QueueConsole = new QueueConsole(userid, "serverQueue");
	}
	
	public void AddListener(IMessageHander im) {
		recive_QueueConsole.addNoFilterListener(im);
	}
	

	public void QueueSend(String userid,Message mg,byte[] data) throws JMSException {
		sned_QueueConsole.SendMessage(userid,mg,data);
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
