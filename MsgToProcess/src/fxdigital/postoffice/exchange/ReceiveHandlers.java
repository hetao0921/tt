package fxdigital.postoffice.exchange;

import org.apache.log4j.Logger;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.mqcore.base.BaseReciver;
import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.rpc.Mail;

public class ReceiveHandlers implements IReciveHandler {

	private String channelID;
	private boolean syncFlag;
	private String centerId;
	static String prefName="sync_";
	private String ip;
	private int port;
	
	BaseSender bs ;
	
	private DbManager dbManager;
	
	


	private static Logger logger = Logger.getLogger(ReceiveHandlers.class);

	public ReceiveHandlers(String channelID, boolean syncFlag,String centerId, DbManager dbManager,String ip,int port) {
		this.channelID = channelID;
		this.syncFlag = syncFlag;
		this.centerId=centerId;
		bs = new BaseSender(ip, port, false);
		this.dbManager=dbManager;
		this.ip=ip;
		this.port=port;
	}


	

	public boolean reReceive(String centerId,String targetId, Mail mail) {	
		
		String targetName=prefName+targetId;
		logger.info("------------------targetId"+targetId+"   centerId "+centerId);
			try{
				boolean flag = dbManager.isExist(targetId);
				logger.info("------------------flag"+flag);
				if (flag) {
					// return bs;
				} else {
					LocalCenter localCenter = new LocalCenter();
					localCenter = dbManager.getLocalCenter();
					String tempIp = localCenter.getSyncServerIP();
					int tempPort = localCenter.getSyncServerPort();
					String centerid = localCenter.getId();
					
					dbManager.insertLocalMqInfo(targetId, tempIp, tempPort);
					SendManager sh = new SendManager();
					
					
					SyncServer syncServer = new SyncServer();
					SyncMq syncMq = new SyncMq();
					syncServer = dbManager.getSyncServer(targetId);
					logger.info("syncServer"+syncServer);
					syncMq = syncServer.getMq();
					String mqTargeIp = syncMq.getIp();
					int mqTargetPort = syncMq.getPort();
					//logger.info("illllllllllllllp"+ mqTargeIp+"mqTargetPort"+mqTargetPort+"targetId"+targetId);
					ReReceiveHandler handler = new ReReceiveHandler(mqTargeIp,mqTargetPort ,
							targetId, dbManager);
					BaseReciver receiver = new BaseReciver(false, tempIp,
							tempPort, false, targetName);
					sh.startSender(receiver, handler);
                   
				}
				
				bs.sendMessage(targetName, mail);
			}
		 catch (Exception e) {
			e.printStackTrace();
			logger.error("消息发送到周转队列异常"+e);
		}	
		return true;

	}



	@Override
	public boolean handler(Mail mail) {
		logger.info("aaaaaaaaaaaaaaaaaaaaaa           " + centerId);
		String nextMailboxID = mail.getNextMailboxID();
		logger.info("向周转队列"+"center_"+nextMailboxID+"发送消息"+mail.getContent());
		reReceive(centerId,nextMailboxID, mail);
		return true;
	}

}
