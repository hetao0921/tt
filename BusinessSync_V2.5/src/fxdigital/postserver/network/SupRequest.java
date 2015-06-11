package fxdigital.postserver.network;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.db.SubStatus;
import fxdigital.db.SupStatus;
import fxdigital.postserver.contentdispose.ContentHandler;
import fxdigital.postserver.exchange.Exchange;
import fxdigital.rpc.Mail;
import fxdigital.rpc.content.base.NetworkContent;
import fxdigital.rpc.contenttype.base.NetworkType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 上级审批下级的申请
 * @author fucz
 * @version 2014-7-14
 */
@Component
public class SupRequest {
	
	private static final Logger log = Logger.getLogger(SupRequest.class);
	
	@Autowired
	private Exchange exchange;
	@Autowired
	private DbManager dbManager;
	@Autowired
	private ContentHandler contentHandler;
	
	/**
	 * 上级审批下级的申请
	 * @param subStatus 下级的申请信息
	 * @return true：审批成功
	 * 			false：审批失败
	 */
	public boolean request(SubStatus subStatus){
		if(subStatus.getStatus() == NetworkMonitor.AGREEING){
			if(agree(subStatus)){
				dbManager.changeSubStatus(subStatus.getServerID(),
						NetworkMonitor.AGREED);
				return true;
			}else{
				return false;
			}
		}else if(subStatus.getStatus() == NetworkMonitor.REJECTING){
			if(reject(subStatus)){
				dbManager.changeSubStatus(subStatus.getServerID(),
						NetworkMonitor.REJECTED);
				return true;
			}else{
				return false;
			}
		}else{
			if(delete(subStatus)){
				dbManager.changeSubStatus(subStatus.getServerID(),
						NetworkMonitor.DELETED);
				return true;
			}else{
				return false;
			}
		}
	}
	
	//上级同意下级的申请
	private boolean agree(SubStatus subStatus){
		if(Exchange.getAimConnectNum(subStatus.getMqIP()) < 1){
			log.info("同意下级的申请："+subStatus);
		}
		Mail mail = createSupRequestMail(subStatus,NetworkMonitor.AGREEING);
		return exchange.sendIpMail(mail,
				subStatus.getMqIP(),
				subStatus.getMqPort(),
				subStatus.getMqPostAddress());
	}
	
	//上级拒绝下级的申请
	private boolean reject(SubStatus subStatus){
		if(Exchange.getAimConnectNum(subStatus.getMqIP()) < 1){
			log.info("拒绝下级的申请："+subStatus);
		}
		Mail mail = createSupRequestMail(subStatus,NetworkMonitor.REJECTING);
		return exchange.sendIpMail(mail,
				subStatus.getMqIP(),
				subStatus.getMqPort(),
				subStatus.getMqPostAddress());
	}
	
	//上级删除下级
	private boolean delete(SubStatus subStatus){
		if(Exchange.getAimConnectNum(subStatus.getMqIP()) < 1){
			log.info("删除下级："+subStatus);
		}
		dbManager.deleteSupAndSubRelation(subStatus.getServerID());
		dbManager.deleteSyncServer(subStatus.getServerID());
		contentHandler.sendRelationMail();
		Mail mail = createSupRequestMail(subStatus,NetworkMonitor.DELETING);
		return exchange.sendIpMail(mail,
				subStatus.getMqIP(),
				subStatus.getMqPort(),
				subStatus.getMqPostAddress());
	}
	
	//创建申请邮件
	private Mail createSupRequestMail(SubStatus subStatus,int status){
		LocalCenter local = dbManager.getLocalCenter();
		SupStatus supStatus = new SupStatus();
		supStatus.setServerID(local.getId());
		supStatus.setServerIP(local.getIp());
		supStatus.setServerName(local.getName());
		supStatus.setMqIP(local.getSyncServerIP());
		supStatus.setMqPort(local.getSyncServerPort());
		supStatus.setMqPostAddress(local.getSyncServerPostAddress());
		supStatus.setApplyTime(subStatus.getApplyTime());
		supStatus.setStatus(status);
		NetworkContent networkContent = new NetworkContent();
		networkContent.setSupStatus(supStatus);
		networkContent.setStatus(status);
		Mail mail = new Mail();
		mail.setContent(JSON.toJSONString(networkContent));
		mail.setContentType(NetworkType.TYPE);
		mail.setSendMode(NormalMode.MODE);
		mail.setIpMail(true);
		return mail;
	}
	
}
