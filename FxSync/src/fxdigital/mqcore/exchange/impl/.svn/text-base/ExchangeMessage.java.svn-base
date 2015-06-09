package fxdigital.mqcore.exchange.impl;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.IExchangeMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.BaseHeader;
import fxdigital.mqcore.util.ByteArrayUtil;
import fxdigital.mqcore.util.Encoding;
import fxdigital.mqcore.util.Msg;
import fxdigital.mqcore.util.Protocol;

public class ExchangeMessage implements IExchangeMessage {

	@Override
	public BytesMessage exchange(BytesMessage bmg, Msg m, byte[] data, String receiveId) throws JMSException {
		byte[] body = Encoding.StringToByte(m.Serilize());

		BaseHeader Header = new BaseHeader();
		Header.setType(Protocol.RpcMessage);
		Header.setPriority((byte) 4);
		Header.setBodyLength(body == null ? 0 : body.length);

		// 下面就是把 包头 包体 数据， 合并成为一个byte[];
		byte[] array = ByteArrayUtil.Sum(Header.getData(), body, data);
		bmg.setStringProperty("receiveId", receiveId);
		bmg.setIntProperty("Length", array.length);
		bmg.writeBytes(array);
		return bmg;
	}


	@Override
	public TextMessage exchange(TextMessage message, Mail mail) throws JMSException {
		// TODO Auto-generated method stub
		message.setText(JSONObject.toJSONString(mail));
		return message;
	}

}
