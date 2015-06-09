package com.fxdigital.tcp.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 字符型消息实体类
 *
 */
public class Msg  implements Serializable,IMsg{
	private String flag;
	private String masterip;
	private String slaveip;
	private String username;
	private String password;
	private String ip;
	private String logfile;
	private int logpos;
	private Map<String, String> properties = new HashMap<String, String>();
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMasterIp() {
		return masterip;
	}
	public void setMasterip(String masterip) {
		this.masterip = masterip;
	}
	public String getSlaveIp() {
		return slaveip;
	}
	public void setSlaveip(String slaveip) {
		this.slaveip = slaveip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogfile() {
		return logfile;
	}
	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}
	public int getLogpos() {
		return logpos;
	}
	public void setLogpos(int logpos) {
		this.logpos = logpos;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	

}
