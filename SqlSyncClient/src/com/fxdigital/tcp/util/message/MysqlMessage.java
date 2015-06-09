package com.fxdigital.tcp.util.message;
import com.fxdigital.tcp.util.IMsg;
import com.fxdigital.tcp.util.Msg;


public class MysqlMessage extends BasicMessage {

	/** mysql消息的 主服务器ip **/
	private String masterIp;
	
	/** mysql消息的 从服务器ip **/
	private String slaveIp;

	
	public String getMasterIp() {
		return masterIp;
	}

	public void setMasterIp(String masterIp) {
		this.masterIp = masterIp;
	}

	public String getSlaveIp() {
		return slaveIp;
	}

	public void setSlaveIp(String slaveIp) {
		this.slaveIp = slaveIp;
	}

	
	public MysqlMessage(int type, IMsg msg) {
		super(msg.getFlag(), type, "");
		
		if (msg != null){
			slaveIp = msg.getSlaveIp();
			masterIp = msg.getMasterIp();
		}
	}

}
