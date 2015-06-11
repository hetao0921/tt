package NVMP.jms.ctrl;

import javax.jms.JMSException;

import NVMP.jms.base.BaseHeader;
import NVMP.jms.base.TopConn;
import NVMP.jms.rpc.Message;
import NVMP.jms.util.ByteArrayUtil;
import NVMP.jms.util.Encoding;

/***
 * @version 2.0 将推送级别下降到等级4，这样的话，Q那边的发送级别相对较高。
 * 
 * */
public class TopConsole {
	private TopConn tc;

	/**
	 * @param clientid
	 *            客服端id
	 * @param topname
	 *            通道名称
	 * */
	public TopConsole(String clientid, String topname) throws JMSException {
		tc = new TopConn(clientid, topname);
	}

	/**
	 * 异常不在模型内捕捉，抛到外面去
	 * */
	private void SendMessage(byte[] array, String receiveId)
			throws JMSException {
		tc.publishMsg(array, receiveId);
	}

	/**
	 * @param type
	 *            这个类型之的是处理类型，是决定那边数据处理的
	 * @param Priority
	 *            优先顺序
	 * @param Option
	 *            选项
	 * @param body
	 *            业务处理方式
	 * @param data
	 *            用来处理的数据
	 * @throws JMSException
	 * */
	public void SendMessage(String receiveId, int Type, int Priority,
			short Option, byte[] body, byte[] data) throws JMSException {
		BaseHeader Header = new BaseHeader();
		Header.setType((short) Type);
		Header.setPriority((byte) Priority);
		Header.setBodyLength(body == null ? 0 : body.length);

		// 下面就是把 包头 包体 数据， 合并成为一个byte[];
		byte[] array = ByteArrayUtil.Sum(Header.getData(), body, data);
		SendMessage(array, receiveId);
	}

	/**
	 * 正常调用发送信息
	 * 
	 * */
	public void SendMessage(String receiveId, Message m, byte[] data)
			throws JMSException {
		byte[] body = Encoding.StringToByte(m.Serilize());

		BaseHeader Header = new BaseHeader();
		Header.setType(Protocol.RpcMessage);
		Header.setPriority((byte) 4);
		Header.setBodyLength(body == null ? 0 : body.length);

		// 下面就是把 包头 包体 数据， 合并成为一个byte[];
		byte[] array = ByteArrayUtil.Sum(Header.getData(), body, data);
		SendMessage(array, receiveId);
	}

	public void addListener(String name, IMessageHander im) {
		MessageListener tml = new MessageListener();
		tml.setIh(im);
		try {
			tc.regListen(name, tml);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() throws JMSException {
		tc.start();
	}

	public void stop() throws JMSException {
		tc.stop();
	}

	public void Close() {

		tc.Close();
	}

}
