package NVMP.jms.ctrl;


import javax.jms.JMSException;

import NVMP.jms.base.BaseHeader;
import NVMP.jms.base.QueueConn;
import NVMP.jms.rpc.Message;
import NVMP.jms.util.ByteArrayUtil;
import NVMP.jms.util.Encoding;

public class QueueConsole {
	private QueueConn qc;
	private String clientID;

	public QueueConsole(String clientid, String queuename) throws JMSException {
		qc = new QueueConn(queuename);
		clientID = clientid;
	}

	
	//最原始的发送方式。
	private void SendMessage(byte[] array,String receiveId) throws JMSException {
		qc.sendMsg(array, receiveId);

	}
	
	
	
	/**
	 * @param type  这个类型之的是处理类型，是决定那边数据处理的
	 * @param Priority 优先顺序
	 * @param Option   选项
	 * @param body     业务处理方式
	 * @param data     用来处理的数据 
	 * @throws JMSException 
	 * */
	public void SendMessage(String receiveId,int Type, int Priority,
			short Option,byte[] body,byte[] data) throws JMSException {
		BaseHeader Header = new BaseHeader();
		Header.setType((short) Type);
		Header.setPriority((byte) Priority);
		Header.setBodyLength(body == null ? 0 : body.length);
		
		//下面就是把 包头 包体 数据， 合并成为一个byte[];
		byte[] array = ByteArrayUtil.Sum(Header.getData(),body,data);
		SendMessage(array,receiveId);
	}
	
	
	/**
	 * 正常调用发送信息
	 * 
	 * */
	public void SendMessage(String receiveId,Message m,byte[] data) throws JMSException {
		byte[] body =Encoding.StringToByte(m.Serilize());
		
		BaseHeader Header = new BaseHeader();
		Header.setType(Protocol.RpcMessage);
		Header.setPriority((byte) 4);
		Header.setBodyLength(body == null ? 0 : body.length);
		
		//下面就是把 包头 包体 数据， 合并成为一个byte[];
		byte[] array = ByteArrayUtil.Sum(Header.getData(),body,data);
		SendMessage(array,receiveId);	
	}
	
	

	public void addFilterListener(IMessageHander im) {
		MessageListener tml = new MessageListener();
		tml.setIh(im);
		try {
			qc.regListen("receiveId='" + clientID + "'", tml);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNoFilterListener(IMessageHander im) {
		MessageListener tml = new MessageListener();
		tml.setIh(im);
		try {
			qc.regListen(null, tml);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() throws JMSException {
		qc.start();
	}
	public void stop() throws JMSException {
		qc.stop();
	}

	public void close() {
		qc.Close();
		
	}
	
	
}
