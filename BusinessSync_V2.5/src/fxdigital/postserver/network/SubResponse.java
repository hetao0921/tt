package fxdigital.postserver.network;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.DbManager;
import fxdigital.db.SupStatus;
import fxdigital.db.SyncMq;
import fxdigital.db.SyncServer;
import fxdigital.postserver.contentdispose.ContentHandler;

/**
 * 下级响应上级的反馈信息
 * @author fucz
 * @version 2014-7-14
 */
@Component
public class SubResponse {
	
	private static final Logger log = Logger.getLogger(SubResponse.class);
	
	@Autowired
	private DbManager dbManager;
	@Autowired
	private ContentHandler contentHandler;
	
	/**
	 * 下级响应上级的反馈信息
	 * @param supStatus 上级的反馈信息
	 * @return true：响应成功
	 * 			false：响应失败
	 */
	public boolean response(SupStatus supStatus){
		SupStatus oldSupStatus = dbManager.getSupStatus(supStatus.getMqIP());
		if(oldSupStatus == null){
			log.warn("不存在此上级："+supStatus+"，销毁消息！");
			return true;
		}
		if(supStatus.getStatus() == NetworkMonitor.AGREEING){
			if(oldSupStatus.getStatus() == NetworkMonitor.APPLIED){
				return receiveAgreement(supStatus);
			}else{
				log.warn("此申请上级信息【"+oldSupStatus+"】已被处理！销毁消息！");
				return true;
			}
		}else if(supStatus.getStatus() == NetworkMonitor.REJECTING){
			if(oldSupStatus.getStatus() == NetworkMonitor.APPLIED){
				return receiveRejection(supStatus);
			}else{
				log.warn("此申请上级信息【"+oldSupStatus+"】已被处理！销毁消息！");
				return true;
			}
		}else{
			if(oldSupStatus.getStatus() != NetworkMonitor.DELETED){
				return receiveDeletion(supStatus);
			}else{
				log.warn("此申请上级信息【"+oldSupStatus+"】已被删除！销毁消息！");
				return true;
			}
		}
	}
	
	//下级收到上级的同意反馈
	private boolean receiveAgreement(SupStatus supStatus){
		log.info("上级反馈--同意申请："+supStatus);
		dbManager.updateSupStatus(supStatus);
		dbManager.changeSupStatus(supStatus.getMqIP(), NetworkMonitor.AGREED);
		dbManager.addLocalAndSupRelation(supStatus);
		SyncServer syncServer = new SyncServer();
		syncServer.setServerID(supStatus.getServerID());
		syncServer.setServerIP(supStatus.getServerIP());
		syncServer.setServerName(supStatus.getServerName());
		SyncMq mq = new SyncMq();
		mq.setIp(supStatus.getMqIP());
		mq.setPort(supStatus.getMqPort());
		mq.setPostAddress(supStatus.getMqPostAddress());
		syncServer.setMq(mq);
		dbManager.addSyncServer(syncServer);
		return contentHandler.sendRelationMail();
	}
	
	//下级收到上级的拒绝反馈
	private boolean receiveRejection(SupStatus supStatus){
		log.info("上级反馈--拒绝申请："+supStatus);
		return dbManager.changeSupStatus(supStatus.getMqIP(),
				NetworkMonitor.REJECTED);
	}
	
	//下级被上级删除
	private boolean receiveDeletion(SupStatus supStatus){
		log.info("上级反馈--删除下级："+supStatus);
		SupStatus oldSupStatus = dbManager.getSupStatus(supStatus.getMqIP());
		dbManager.changeSupStatus(supStatus.getMqIP(),NetworkMonitor.DELETED);
		if(oldSupStatus.getStatus() == NetworkMonitor.AGREED){
			dbManager.deleteSupAndSubRelation(dbManager.getLocalCenter().getId());
			dbManager.deleteSyncServer(supStatus.getServerID());
		}
		return true;
	}
	
}
