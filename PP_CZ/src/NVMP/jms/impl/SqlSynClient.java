package NVMP.jms.impl;

import javax.jms.JMSException;

import NVMP.jms.ctrl.QueueConsole;
import NVMP.jms.rpc.Message;
import NVMP.jms.util.Encoding;

/**
 * 只是调用发送，在clientSend的通道进行发送。
 * */
public class SqlSynClient {

	private QueueConsole sned_QueueConsole;
	private String userid;

	public String getUserid() {
		return userid;
	}

	public SqlSynClient(String userid) throws JMSException {

		this.userid = userid;
		boolean flag = true;
		while (flag) {
			try {
				sned_QueueConsole = new QueueConsole(userid, "clientQueue");
				flag = false;
			} catch (Exception e) {
				System.out.println("====与jms服务器连接失败，3秒重新来过");
				flag = true;
				try {
					Thread.sleep(3000);
				} catch (Exception e1) {

				}
			}
		}

	}

	private void SendMessage(String receiveId, Message m, byte[] data)
			throws JMSException {
		sned_QueueConsole.SendMessage(receiveId, m, data);
	}

	public void SendSqlMessage(Integer seq, String sql) throws JMSException {

		Message m = new Message();
		m.AddParam("userid", userid);
		m.AddParam("seq", seq);
		m.set_Operator(userid);
		m.set_Url("DBSynchronization.ClientQueueSend");

		SendMessage(null, m, Encoding.StringToByte(sql));

	}

	public void SendCommand(String cmd, String args) throws JMSException {
		Message m = new Message();
		m.AddParam("cmd", cmd);
		m.AddParam("userid", userid);
		m.set_Operator(userid);
		m.set_Url("DBSynchronization.ClientCommand");

		SendMessage(null, m, Encoding.StringToByte(args));
	}

	public void Close() {

		sned_QueueConsole.close();
	}

}
