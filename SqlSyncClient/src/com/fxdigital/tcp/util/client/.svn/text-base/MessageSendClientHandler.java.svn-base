package com.fxdigital.tcp.util.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.fxdigital.tcp.util.Msg;
import com.fxdigital.tcp.util.ProcessMsg;
import com.fxdigital.tcp.util.message.BasicMessage;
import com.fxdigital.tcp.util.message.MessageConst;

public class MessageSendClientHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof BasicMessage) {
			// 服务端返回“success”的 Message，则表示消息处理成功
			if ( ((BasicMessage) message).getType() == MessageConst.MESSAGE_TYPE_REPONSE 
					&& ((BasicMessage) message).getMessage().equals("success")) {
				MessageSendClient.LOG.info("client send success!");
				session.close(true);
			} else {
				MessageSendClient.LOG.info("client send fail!");
				session.close(true);
			}
		} else if (message instanceof Msg) {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.receiveMsg((Msg)message);
		}
	}
}