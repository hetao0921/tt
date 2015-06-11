package fxdigital.postoffice.exchange;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.mqcore.base.BaseReciver;

/**
 * 通讯管理
 * */
public class ExchangeServer {
	
	private String IP;
	private int Port;
	private String centerId;

	private DbManager dbManager;

	public ExchangeServer(String ip, int port, String centerId,
			DbManager dbManager) {
		this.IP = ip;
		this.Port = port;
		this.centerId = centerId;
		this.dbManager = dbManager;

	}

	public ExchangeServer() {

	}

	public void init(String targetName) {
		// 本地服务器监听
		createListenChannel(targetName, IP, Port);
	}

	public String getCenterid() {
		return centerId;
	}

	/**
	 * 创建侦听队列
	 * */

	private void createListenChannel(String name, String ip, int port) {
		// System.out.println(name + "local que and ip" + ip);
		LocalCenter localCenter = new LocalCenter();
		localCenter = dbManager.getLocalCenter();
		String tempIp = localCenter.getSyncServerIP();
		int tempPort = localCenter.getSyncServerPort();
		String centerid = localCenter.getId();
        
		
		ReceiveHandlers receivehandler = new ReceiveHandlers(name, false,
				centerid, dbManager, tempIp, tempPort);

		BaseReciver receiver = new BaseReciver(true, ip, port, false, name);
		receiver.start(receivehandler);
		
	}

}
