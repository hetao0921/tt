package com.fxdigital.tcp.util.server;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.fxdigital.tcp.util.FileMsg;
import com.fxdigital.tcp.util.IMsg;
import com.fxdigital.tcp.util.Msg;
import com.fxdigital.tcp.util.ProcessMsg;
import com.fxdigital.tcp.util.client.MessageSendClient;
import com.fxdigital.tcp.util.message.BasicMessage;
import com.fxdigital.tcp.util.message.FileTransportMessage;
import com.fxdigital.tcp.util.message.MessageConst;
import com.fxdigital.tcp.util.message.MysqlSyncMessage;

public class MessageReceiveHandler extends IoHandlerAdapter {

	private BufferedOutputStream out;

	private static final Log log = LogFactory
			.getLog(MessageReceiveHandler.class);

	// public void sessionOpened(IoSession session) throws Exception {
	// System.out.println("server open");
	// }

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.warn("exception caught: " + cause.getMessage());
		session.close(true);
		super.exceptionCaught(session, cause);
	}

	public void messageReceived(IoSession session, Object message) {
		log.info("server received");

		// try {
		if (message instanceof BasicMessage) {
			boolean isLast = ((BasicMessage) message).isLast();
			
			IMsg msg = null;
			if (message instanceof MysqlSyncMessage) {
			
			} else if (message instanceof FileTransportMessage) {
				FileTransportMessage fileMessage = (FileTransportMessage) message;

				try {
					if (out == null) {
						out = new BufferedOutputStream(new FileOutputStream(
								fileMessage.getReceiveFilePath()));
						out.write(fileMessage.getFileContent());
					} else {
						out.write(fileMessage.getFileContent());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.warn("服务端接收FileTransportMessages,写文件时出错！" + e.getMessage());
				}
			}

			//如果服务端收到的消息，为该逻辑操作的最后一个消息，并且上层服务应用将该消息成功处理，
			// 则向客户端发送一个"success"的BasicMessage,表示消息成功接收处理。
			if (isLast) {
				
				if (message instanceof FileTransportMessage) {
					try {
						out.flush();
						out.close();
						out = null;
						
						msg = convertFileTransportMessageToFileMsg(
								(FileTransportMessage) message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.warn("服务端接收FileTransportMessages,写文件时出错！" + e.getMessage());
					}
				} else if (message instanceof MysqlSyncMessage) {
					MysqlSyncMessage mysqlSyncMessage = (MysqlSyncMessage) message;

					 msg = convertMysqlSyncMessageToMsg(mysqlSyncMessage);
				}

				ProcessMsg.receiveMsg(msg);
//				if (ProcessMsg.receiveMsg(msg) == 0){
//					// 返回0，表示消息被成功处理，向客户端返回success消息
//					BasicMessage responseMessage = new BasicMessage(
//							"", MessageConst.MESSAGE_TYPE_REPONSE, "success");
//					session.write(responseMessage);
//				}
			}
		}
	}

	/**
	 * 将服务器收到的FileTransportMessage消息，保存完客户端发送过来的数据库同步.sql文件后，
	 * 将保存的.sql文件路径，通知给上层应用。
	 * @param message
	 * @return
	 */
	private FileMsg convertFileTransportMessageToFileMsg(
			FileTransportMessage message) {
		// TODO Auto-generated method stub
		FileMsg fileMsg = new FileMsg();
		fileMsg.setFlag(message.getMessageFlag());
		fileMsg.setMasterip(message.getMasterIp());
		fileMsg.setSlaveip(message.getSlaveIp());	
		fileMsg.setSendFilePath(message.getSendFilePath());
		fileMsg.setReceiveFilePath(message.getReceiveFilePath());
		
		return fileMsg;
	}

	/**
	 * 将服务端收到的MysqlSyncMessage消息，转化为上层使用的数据库同步消息Msg
	 * @param mysqlSyncMessage
	 * @return
	 */
	private Msg convertMysqlSyncMessageToMsg(MysqlSyncMessage mysqlSyncMessage) {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		msg.setMasterip(mysqlSyncMessage.getMasterIp());
		msg.setSlaveip(mysqlSyncMessage.getSlaveIp());
		msg.setUsername(mysqlSyncMessage.getUsername());
		msg.setPassword(mysqlSyncMessage.getPassword());
		msg.setLogfile(mysqlSyncMessage.getLogfile());
		msg.setLogpos(mysqlSyncMessage.getLogpos());
		msg.setFlag(mysqlSyncMessage.getMessageFlag());
		
		return msg;
	}

	public void sessionClosed(IoSession session) throws Exception {
		log.info("server session close");
	}
}