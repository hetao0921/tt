package com.fxdigital.tcp.util.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fxdigital.tcp.util.FileMsg;
import com.fxdigital.tcp.util.IMsg;
import com.fxdigital.tcp.util.Msg;
import com.fxdigital.tcp.util.message.BasicMessage;
import com.fxdigital.tcp.util.message.FileTransportMessage;
import com.fxdigital.tcp.util.message.MysqlSyncMessage;
import com.fxdigital.tcp.util.server.MessageReceiveServer;

public class MessageSendClient {

	/** 服务端默认端口 **/
	private static final int PORT = MessageReceiveServer.PORT;

	/** 30秒后超时 */
	private static final int IDELTIMEOUT = 30;

	/** 15秒发送一次心跳包 */
	private static final int HEARTBEATRATE = 15;
	
	private static NioSocketConnector connector = new NioSocketConnector();
	
	private static Map<String, IoSession> sessions = new HashMap<String, IoSession>();

	public static final Logger LOG = LoggerFactory
			.getLogger(MessageSendClient.class);

	public static void mysqlSyncStart(String ipString, IMsg message)
			throws Exception {
		// 判断连接的 服务端ip地址，是否是正常的ipv4地址
		if (!ipString.matches("(\\d{1,3}.){3}\\d{1,3}")) {
			LOG.warn("服务端ip地址格式错误：" + ipString);
			return;
		}

		// 判断发送的消息是否为空
		if (message == null || message.equals("")) {
			LOG.warn("message为空！");
			return;
		}

		IoSession session = getSession(ipString);
		
		if(null==session){
			throw new Exception();
		}
		
		LOG.info("client send begin");

		sendMessage(session, message);

		LOG.info("client send finished and wait success");
	}

	/**
	 * 根据上层传送过来的IMsg类型，进行不同的消息发送处理逻辑分支
	 * @param session 已经建立的会话session
	 * @param message 上层需要通信的消息封装体
	 */
	private static void sendMessage(IoSession session, IMsg message) {
		// TODO Auto-generated method stub
		BasicMessage sendMessage = null;
		
		// 传递消息开始
		
		if (message instanceof Msg) {
			// mysql主从备份设置消息
			sendMessage = new MysqlSyncMessage((Msg) message);
			session.write(sendMessage);
		} else if (message instanceof FileMsg){
			// mysql备份 .sql文件传输消息
			String sendFilePath = ((FileMsg) message).getSendFilePath();
			
			if (sendFilePath == null || sendFilePath.equals("")){
				LOG.warn("FileMsg's sendFilePath is empty!");
				return;
			}
			
			sendMessage = new FileTransportMessage((FileMsg)message);
			FileInputStream fis;
			try {
				fis = new FileInputStream(new File(sendFilePath));
				byte[] a = new byte[4 * 1024];
				
				int i =  fis.read(a, 0, a.length);
				if (i == -1) {
					LOG.warn("the file to be sended is null!" +
							"file path:" + sendFilePath);
					return ;
				}
				
				while (i != -1) {
					((FileTransportMessage) sendMessage).
						setFileContent(Arrays.copyOf(a, i));
					
					i =  fis.read(a, 0, a.length);
					// 如果当前已读到文件的末尾，则将该FileTransportMessage的
					// Last标识 设为 true
					if (i == -1) 
						sendMessage.setLast(true);
					
					session.write(sendMessage);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				LOG.warn(sendFilePath + " is not found!");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.warn(sendFilePath + " read error! " + e.getMessage());
			}
		}
	}

	static {
		// 客户端的实现
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.getFilterChain().addLast("logging", new LoggingFilter());
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
				IDELTIMEOUT);
//		// 添加filter，心跳检测
//		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
//		KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
//		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
//				IdleStatus.BOTH_IDLE, heartBeatHandler);
//
//		// 设置是否forward到下一个filter
//		heartBeat.setForwardEvent(true);
//		// 设置心跳频率
//		heartBeat.setRequestInterval(HEARTBEATRATE);
//		connector.getFilterChain().addLast("keepAlive", heartBeat);
		MessageSendClientHandler h = new MessageSendClientHandler();
		connector.setHandler(h);

		// 本句需要加上，否则无法调用下面的readFuture来从session中读取到服务端返回的信息。
		connector.getSessionConfig().setUseReadOperation(true);
		
	}
	
	/**
	 * 初始化客户端和服务端 之间的连接，并得到会话Session，返回。
	 * @param ipString
	 * @return
	 */
	private static IoSession getSession(String ipString) {
		// TODO Auto-generated method stub
		
		//当连接为激活，或者 会话为空、未连接状态，则进行连接
		IoSession session = sessions.get(ipString);
		if (session == null || !session.isConnected()) {
			
				ConnectFuture cf = connector.connect(new InetSocketAddress(ipString,
						PORT));
				
				// 等待连接成功
				cf.awaitUninterruptibly();
				session = cf.getSession();
				sessions.put(ipString, session);
		}
		
		return session;
	}
}
