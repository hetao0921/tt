package com.fxdigital.tcp.util.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {


	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";

	private static final Logger LOG = LoggerFactory
			.getLogger(MessageReceiveServer.class);

	@Override
	public boolean isRequest(IoSession session, Object message) {
		if (message.equals(HEARTBEATREQUEST)) {
			LOG.info("请求心跳包信息: " + message);
			return true;
		}
		return false;
	}

	@Override
	public boolean isResponse(IoSession session, Object message) {
		if (message.equals(HEARTBEATRESPONSE)) {
			LOG.info("响应心跳包信息: " + message);
			return true;
		}
		return false;
	}

	@Override
	public Object getRequest(IoSession session) {
		LOG.info("请求预设信息: " + HEARTBEATREQUEST);
		/** 返回预设语句 */
		return HEARTBEATREQUEST;
	}

	@Override
	public Object getResponse(IoSession session, Object request) {
		LOG.info("响应预设信息: " + HEARTBEATRESPONSE);
		/** 返回预设语句 */
		return HEARTBEATRESPONSE;
		// return null;
	}
}