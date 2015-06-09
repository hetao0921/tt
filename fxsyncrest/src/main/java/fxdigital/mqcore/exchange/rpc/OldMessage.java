package fxdigital.mqcore.exchange.rpc;

import fxdigital.mqcore.util.Msg;

public class OldMessage implements IMessage{
	Msg m=null;
	byte[] data=null;
	String receiveId=null;
	public Msg getM() {
		return m;
	}
	public void setM(Msg m) {
		this.m = m;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	
	
}
