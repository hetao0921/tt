package fxdigital.mqcore.exchange.impl;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;

import fxdigital.mqcore.exchange.rpc.OldMessage;
import fxdigital.mqcore.util.BaseHeader;
import fxdigital.mqcore.util.BaseProtocol;
import fxdigital.mqcore.util.ByteArrayUtil;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.mqcore.util.Protocol;

public class OldMessage4Message {


	public static OldMessage createOldMsg(Message message) {
		int n = 0;
		byte[] data = null;
		OldMessage oldMessage=null;
		Msg mg = null;
		if (message instanceof BytesMessage) {
			try {
				BytesMessage bmg = (BytesMessage) message;
				/**
				 * 将信息进行分解，3个部分，包头，包体 ，数据。
				 * */
				n = bmg.getIntProperty("Length");
				// 文本写入，接收大小 n
				byte[] array = new byte[n];
				bmg.readBytes(array);
				byte[] h = ByteArrayUtil.Trim(array, 0, BaseProtocol.HeadSize);
				if (h == null) {
					System.out.println("头文件解析错误，此次操作失败");
					mg = null;
				}
				BaseHeader header = new BaseHeader(h);
				if (!header.ValidateMagic()) {
					System.out.println("头文件校验错误，此次操作失败");
					mg = null;
				}
				// 如果是正常处理。
				if (header.getType() == Protocol.RpcMessage) {
					byte[] body = ByteArrayUtil.Trim(array,
							BaseProtocol.HeadSize, header.getBodyLength());
					oldMessage=new OldMessage();
					mg = new Msg(Encoding.byteToString(body));
					data = ByteArrayUtil.Trim(array, BaseProtocol.HeadSize
							+ header.getBodyLength(), array.length
							- BaseProtocol.HeadSize - header.getBodyLength());
					oldMessage.setData(data);
					oldMessage.setM(mg);
				}
				// 计算处理时间
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return oldMessage;
	}
}
