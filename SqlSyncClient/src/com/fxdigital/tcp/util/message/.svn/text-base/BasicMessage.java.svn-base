package com.fxdigital.tcp.util.message;


public class BasicMessage implements Message {
	
	/** 消息类型 **/
	private int type;

	/** 具体消息 **/
	private String message;
	
	/** 标识一个逻辑操作的  最后一个Message **/
	private boolean isLast;
	
	/** 上层应用在交互消息时，使用flag来标识不同的消息 **/
	private String messageFlag;
	
	public BasicMessage(String flag, int type, String message) {
		this.messageFlag = flag;
		this.type = type;
		this.message = message;
		
		// 默认，一个Message就是一个逻辑操作过程
		setLast(true);
	}
	

	public int getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}


	public String getMessageFlag() {
		return messageFlag;
	}


	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}

}
