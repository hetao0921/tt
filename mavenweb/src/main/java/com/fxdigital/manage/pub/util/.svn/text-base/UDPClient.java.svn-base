package com.fxdigital.manage.pub.util;

/////////////NIO写UDP----client
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Notice;

public class UDPClient extends Thread {
	private Log logger=LogFactory.getLog(UDPClient.class);
	private String host;
	private int port;
	private  String json;

	public UDPClient(String host, int port,String json) {
		this.host = host;
		this.port=port;
		this.json=json;
		new Thread(this).start();
	}

	public void run() {
		// 构造一个数据报Socket
		DatagramChannel dc = null;
		try {
			dc = DatagramChannel.open();
			logger.info("发送UDP消息  host:"+host+"--port:"+port);
			SocketAddress address = new InetSocketAddress(host, port);
			dc.connect(address);
			logger.info("UDP 消息 json"+json);
			byte[] b =  json.getBytes("utf-8");
			ByteBuffer bb = ByteBuffer.allocate(b.length);
			bb.clear();
			bb.put(b);
			bb.flip();
			int num = dc.send(bb, address);
			dc.close();
		} catch (Exception e) {
			logger.error("发送UDP消息错误",e);
		}
		
	
	

	
	
		
	}

	
	public static void send(Notice notice) {
		String host = "127.0.0.1";// args[0];
		int port = 1901;
		String json=JSONObject.toJSONString(notice);
		System.out.println("UDP 发送数据:"+json);
		new UDPClient(host, port, json);
	}

}
