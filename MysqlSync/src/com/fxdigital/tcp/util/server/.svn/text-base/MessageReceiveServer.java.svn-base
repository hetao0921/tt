package com.fxdigital.tcp.util.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.fxdigital.tcp.util.Msg;


public class MessageReceiveServer {
	/** 默认端口号 1984 **/
	public static final int PORT = 1984;

	/** 30秒后超时 */
	private static final int IDELTIMEOUT = 30;

	/** 15秒发送一次心跳包 */
	private static final int HEARTBEATRATE = 15;

	public static final Logger LOG = Logger.getLogger(MessageReceiveServer.class);
	
	private static NioSocketAcceptor accept = new NioSocketAcceptor();

	public static void main(String[] args) throws Exception {
		startMessageReceiveServer();
	}

	public static void startMessageReceiveServer() throws IOException {

		// 服务端的实例
		
		
		//设置会话的	读缓冲区大小为1024字节，
		//			超时为30秒
//        accept.getSessionConfig().setReadBufferSize(1024);
        accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,  
                IDELTIMEOUT);  
		
		// 添加filter，codec为序列化方式。这里为对象序列化方式，即表示传递的是对象。
		accept.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		// 添加filter，日志信息
		accept.getFilterChain().addLast("logging", new LoggingFilter());
		// 添加filter，线程池
		accept.getFilterChain().addLast("executor", 
				new ExecutorFilter());
		
		// 添加filter，心跳检测
		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl(); 
		KeepAliveRequestTimeoutHandler heartBeatHandler = new   
                              KeepAliveRequestTimeoutHandlerImpl();  
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,  
              IdleStatus.BOTH_IDLE, heartBeatHandler);  
          
        //设置是否forward到下一个filter  
        heartBeat.setForwardEvent(true);  
        //设置心跳频率  
        heartBeat.setRequestInterval(HEARTBEATRATE);  
		accept.getFilterChain().addLast("keepAlive", heartBeat);
		
		// 设置服务端的handler
		accept.setHandler(new MessageReceiveHandler());
		// 绑定ip
		accept.bind(new InetSocketAddress(PORT));
		
		LOG.info("mysql sync server started.");
	}
	
	public static void sendMessage(Msg msg, String ipAddress) {
		Iterator<IoSession> sessions = accept.getManagedSessions().values().iterator();
		IoSession session;
		while (sessions.hasNext()) {
			session = sessions.next();
			String ip = ( (InetSocketAddress)session.getRemoteAddress() )
					.getAddress().getHostAddress();
			if (ip.equals(ipAddress)) {
				
				session.write(msg);
			}
		}
		
	}
}
