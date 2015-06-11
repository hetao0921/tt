package fxdigital.postoffice.exchange;

import java.util.HashMap;

import org.apache.log4j.Logger;

import fxdigital.db.DbManager;
import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.mqcore.base.BaseSender;
import fxdigital.mqcore.base.IReciveHandler;
import fxdigital.rpc.Mail;
import fxdigital.util.ArgsUtil;

public class ReReceiveHandler implements IReciveHandler {

	private static Logger logger = Logger.getLogger(ReReceiveHandler.class);

	String nextMailboxID;
	static String targetIp;
	static int targetport;
	static BaseSender bs;
	static DbManager dbManager;
	static HashMap<String, BaseSender> bsMap = new HashMap<String, BaseSender>();

	public ReReceiveHandler(String targetIp, int targetport,
			String nextMailboxID, DbManager dbManager) {
		this.nextMailboxID = nextMailboxID;
		this.targetIp = targetIp;
		this.targetport = targetport;
		this.dbManager = dbManager;
		bs = new BaseSender(targetIp, targetport, false);
		bsMap.put(nextMailboxID, bs);
	}

	public static BaseSender creator(String targetid) {

		if (null != bsMap.get(targetid)) {
			return bsMap.get(targetid);
		} else {
			SyncServer syncServer = new SyncServer();
			SyncMq syncMq = new SyncMq();
			syncServer = dbManager.getSyncServer(targetid);
			logger.info("syncServer" + syncServer);
			syncMq = syncServer.getMq();
			targetIp = syncMq.getIp();
			targetport = syncMq.getPort();
			bs = new BaseSender(targetIp, targetport, false);
			bsMap.put(targetid, bs);
			return bs;
		}
		
	}

	@Override
	public boolean handler(Mail message) {
		String targetName = ArgsUtil.getPostAddress();
		logger.info("nextMailboxID"+nextMailboxID);
		bs = ReReceiveHandler.creator(nextMailboxID);
		logger.info("向目标队列"+targetName+"发送消息"+message.getContent());
		bs.sendMessage(targetName, message);
		return true;
	}

}
