package com.fxdigital.tcp.util.message;

import java.io.File;

import com.fxdigital.tcp.util.FileMsg;

public class FileTransportMessage extends MysqlMessage {

	/** 将传输的文件名 **/
	private String filename;
	
	/** 文件发送路径 **/
	private String sendFilePath;
	
	/** 文件接收路径 **/
	private String receiveFilePath;
	
	/** 将传输的文件的byte数组  **/
	private byte[] fileContent;
	
	
	public FileTransportMessage(FileMsg msg) {
		super(MessageConst.MESSAGE_TYPE_FILE_TRANSPORT, msg);
		
		this.sendFilePath = msg.getSendFilePath();
		
		if (sendFilePath != null && !sendFilePath.equals(""))
			this.filename = (new File(sendFilePath)).getName();
		
		this.receiveFilePath = msg.getReceiveFilePath();
		
		setLast(false);
	}

	public String getSendFilePath() {
		return sendFilePath;
	}

	public void setSendFilePath(String sendFilePath) {
		this.sendFilePath = sendFilePath;
	}

	public String getReceiveFilePath() {
		return receiveFilePath;
	}

	public void setReceiveFilePath(String receiveFilePath) {
		this.receiveFilePath = receiveFilePath;
	}

	public String getFilename() {
		return filename;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}


}
