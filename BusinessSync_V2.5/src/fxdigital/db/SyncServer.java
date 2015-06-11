package fxdigital.db;

/**
 * 同步服务器
 * @author fucz
 * @version 2014-7-17
 */
public class SyncServer {
	
	private String serverID;
	private String serverIP;
	private String serverName;
	private SyncMq mq;
	
	public String getServerID() {
		return serverID;
	}
	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public SyncMq getMq() {
		return mq;
	}
	public void setMq(SyncMq mq) {
		this.mq = mq;
	}
	
}
