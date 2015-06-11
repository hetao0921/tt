package fxdigital.postoffice.exchange;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.db.LocalMqInfo;
import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.mqcore.base.BaseReciver;
import fxdigital.util.ArgsUtil;


public class ExchangeMsg {
	static {
		PropertyConfigurator.configure("conf/log4j.properties");
		System.setProperty("file.encoding", "UTF-8");
//		new ReadXml().read();
	}

	private static Logger logger = Logger.getLogger(ExchangeMsg.class);
	DbManager dbManager=new DbManager();
    static String prefName="sync_";


	public void run(){
		initSourceQueue();
		initQueue();
		
	}
	
	
	public void initQueue() {
		// 侦听本地所有的MQ队列
		List<LocalMqInfo> getcenterMqServerInfos = dbManager.getLocalMqInfo();
		SendManager sh =null;
		if (null!=getcenterMqServerInfos&&getcenterMqServerInfos.size() > 0) {
			for (int i = 0; i < getcenterMqServerInfos.size(); i++) {
				sh = new SendManager();
				LocalMqInfo quedata =  getcenterMqServerInfos
						.get(i);
				String initqueTempIp = (String) quedata.getIp();
				int initqueTempPort = Integer.valueOf(quedata.getPort());
				
				String nextMailboxID = quedata.getCenterid();
				SyncServer syncServer = new SyncServer();
				SyncMq syncMq = new SyncMq();
			
				logger.info("nextMailboxID"+nextMailboxID);
				
				syncServer = dbManager.getSyncServer(nextMailboxID);
			
				syncMq = syncServer.getMq();
				String mqTargeIp = syncMq.getIp();
				int mqTargetPort = syncMq.getPort();

				String queName = prefName + nextMailboxID;
				String targetName =ArgsUtil.getPostAddress();


				ReReceiveHandler handler = new ReReceiveHandler(mqTargeIp,
						mqTargetPort, nextMailboxID, dbManager);
				BaseReciver receiver = new BaseReciver(false, initqueTempIp,
						initqueTempPort, false, queName);
				sh.startSender(receiver, handler);
				
				logger.info("initQueue end");
				logger.info("Server{initqueTempIp[" + initqueTempIp
						+ "],initqueTempPort[" + initqueTempPort
						+ "],nextMailboxID[" + nextMailboxID + "]} start OK!");
			}

		}
	}
	
	
	
	public void initSourceQueue(){
		//
		LocalCenter localCenter = new LocalCenter();

		localCenter = dbManager.getLocalCenter();
		String mqIP = localCenter.getSyncServerIP();
		int mqPort = localCenter.getSyncServerPort();
		
		String centerId="";
		String send_channal_name="Exchange";
		//侦听主队列
		ExchangeServer channelServer = new ExchangeServer(mqIP, mqPort, centerId,dbManager);
		channelServer.init(send_channal_name);

		logger.info("initSourceQueue end");
		
		logger.info("Server{IP[" + mqIP + "],port[" + mqPort
				+ "],centerId[" + send_channal_name + "]} start OK!");
	}

	public boolean existSender(String centerid, String tempIp) {
		boolean flag = false;
		String Ip = dbManager.getMQServerIP(centerid);
		if (Ip.equals(tempIp))
			flag = true;
		else {
			flag = false;
		}
		return flag;
	}

	
	static class ReStartThread extends Thread {

		@Override
		public void run() {
            while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }

		}
	}
	
	
	public static void main(String[] args) {
		ExchangeMsg exchangeMsg=new ExchangeMsg();
		exchangeMsg.run();
		ReStartThread reStartThread=new ReStartThread();
		reStartThread.start();
	}
}
