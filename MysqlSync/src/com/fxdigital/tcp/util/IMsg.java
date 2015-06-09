package com.fxdigital.tcp.util;

/**
 * @author Administrator
 * 消息类接口
 *
 */
public interface IMsg {
	String flag = null;
	
	public String getMasterIp();
	
	public String getSlaveIp();
	
	public String getFlag();
}
