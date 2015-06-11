package com.hxht.message;

public class Message {
	
	private String MsgID;
	
	private String MsgReceiverIP;
	
	private String MsgReceiverPort;
	
	private String MsgContent;
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getMsgContent() {
		return MsgContent;
	}
	public void setMsgContent(String msgContent) {
		MsgContent = msgContent;
	}
	public String getMsgReceiverIP() {
		return MsgReceiverIP;
	}
	public void setMsgReceiverIP(String msgReceiverIP) {
		MsgReceiverIP = msgReceiverIP;
	}
	public String getMsgReceiverPort() {
		return MsgReceiverPort;
	}
	public void setMsgReceiverPort(String msgReceiverPort) {
		MsgReceiverPort = msgReceiverPort;
	}
	
	

}
