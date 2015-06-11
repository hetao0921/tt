package fxdigital.postserver.network;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.DbManager;
import fxdigital.db.SubStatus;
import fxdigital.db.SupStatus;

/**
 * 上级响应下级的申请
 * @author fucz
 * @version 2014-7-14
 */
@Component
public class SupResponse {
	
	private static final Logger log = Logger.getLogger(SupResponse.class);
	
	@Autowired
	private DbManager dbManager;
	
	/**
	 * 上级响应下级的申请
	 * @param subStatus 申请的下级信息
	 * @return true：响应成功
	 * 			false：响应失败
	 */
	public boolean response(SubStatus subStatus){
		SubStatus oldSubStatus = dbManager.getSubStatusFromID(subStatus.getServerID());
		if(subStatus.getStatus() == NetworkMonitor.APPLYING){
			if(oldSubStatus == null
					||(oldSubStatus != null
						&& oldSubStatus.getStatus() != NetworkMonitor.UNCHECKED)){
				return receiveApplication(subStatus);
			}else{
				log.warn("此下级【"+subStatus+"】已经申请过了！");
				return true;
			}
		}else{
			if(oldSubStatus != null
					&& oldSubStatus.getStatus() != NetworkMonitor.CANCELED
					&& oldSubStatus.getStatus() != NetworkMonitor.DELETED
					&& oldSubStatus.getStatus() != NetworkMonitor.DELETING){
				return receiveCancel(subStatus);
			}else{
				log.warn("此下级【"+subStatus+"】已经被取消或删除了！");
				return true;
			}
		}
	}
	
	//上级收到下级发来申请
	private boolean receiveApplication(SubStatus subStatus){
		log.info("下级发来申请："+subStatus);
		SupStatus sup = dbManager.getSupStatus(subStatus.getMqIP());
		String superID = dbManager.getSuperID(dbManager.getLocalCenter().getId());
		if(subStatus.getServerID().equals(superID)){
			log.warn("发来申请的下级已经是本级的上级，拒绝此申请！");
			subStatus.setStatus(NetworkMonitor.REJECTING);
			return dbManager.addSubStatus(subStatus);
		}
		if(sup != null
				&& (NetworkMonitor.APPLYING == sup.getStatus()
						|| NetworkMonitor.APPLIED == sup.getStatus()
						|| NetworkMonitor.AGREED == sup.getStatus())
				){
			log.warn("发来申请的下级已经或将是本级的上级，拒绝此申请！");
			subStatus.setStatus(NetworkMonitor.REJECTING);
			return dbManager.addSubStatus(subStatus);
		}
		subStatus.setStatus(NetworkMonitor.UNCHECKED);
		return dbManager.addSubStatus(subStatus);
	}
	
	//上级收到下级取消申请的信息
	private boolean receiveCancel(SubStatus subStatus){
		log.info("下级取消申请："+subStatus);
		return dbManager.changeSubStatus(subStatus.getServerID(),
				NetworkMonitor.CANCELED);
	}
	
}
