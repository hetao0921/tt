package fxdigital.db;

/**
 * 本级服务器信息
 * 
 * @author fucz
 * @version 2014-7-24
 */
public class LocalCenter {
	private String id;
	private String ip;
	private String name;
	private int port;
	private String syncServerIP;
	private int syncServerPort;
	private String syncServerPostAddress;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getSyncServerIP() {
		return syncServerIP;
	}
	public void setSyncServerIP(String syncServerIP) {
		this.syncServerIP = syncServerIP;
	}
	public int getSyncServerPort() {
		return syncServerPort;
	}
	public void setSyncServerPort(int syncServerPort) {
		this.syncServerPort = syncServerPort;
	}
	public String getSyncServerPostAddress() {
		return syncServerPostAddress;
	}
	public void setSyncServerPostAddress(String syncServerPostAddress) {
		this.syncServerPostAddress = syncServerPostAddress;
	}
	
}
