package fxdigital.postserver.network;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.db.SubStatus;
import fxdigital.db.SupStatus;
import fxdigital.postserver.exchange.Exchange;
import fxdigital.rpc.Mail;
import fxdigital.rpc.content.base.NetworkContent;
import fxdigital.rpc.contenttype.base.NetworkType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 下级向上级申请
 * @author fucz
 * @version 2014-7-14
 */
@Component
public class SubRequest {
	
	private static final Logger log = Logger.getLogger(SubRequest.class);
	
	@Autowired
	private Exchange exchange;
	@Autowired
	private DbManager dbManager;
	
	/**
	 * 下级向上级申请
	 * @param supStatus 申请的上级信息
	 * @return true：申请成功
	 * 			false：申请失败
	 */
	public boolean request(SupStatus supStatus){
		if(supStatus.getStatus() == NetworkMonitor.APPLYING){
			if(apply(supStatus)){
				dbManager.changeSupStatus(supStatus.getMqIP(),
						NetworkMonitor.APPLIED);
				return true;
			}else{
				return false;
			}
		}else{
			if(cancel(supStatus)){
				dbManager.changeSupStatus(supStatus.getMqIP(),
						NetworkMonitor.CANCELED);
				return true;
			}else{
				return false;
			}
		}
	}
	
	//申请成为下级
	private boolean apply(SupStatus supStatus){
		if(Exchange.getAimConnectNum(supStatus.getMqIP()) < 1){
			log.info("申请成为下级："+supStatus);
		}
		Mail mail = createSubRequestMail(supStatus,NetworkMonitor.APPLYING);
		return exchange.sendIpMail(mail,
									supStatus.getMqIP(),
									supStatus.getMqPort(),
									supStatus.getMqPostAddress());
	}
	
	//取消申请
	private boolean cancel(SupStatus supStatus){
		if(Exchange.getAimConnectNum(supStatus.getMqIP()) < 1){
			log.info("撤销向上级的申请："+supStatus);
		}
		Mail mail = createSubRequestMail(supStatus,NetworkMonitor.CANCELING);
		return exchange.sendIpMail(mail,
									supStatus.getMqIP(),
									supStatus.getMqPort(),
									supStatus.getMqPostAddress());
	}
	
	//创建申请邮件
	private Mail createSubRequestMail(SupStatus supStatus,int status){
		LocalCenter local = dbManager.getLocalCenter();
		SubStatus subStatus = new SubStatus();
		subStatus.setServerID(local.getId());
		subStatus.setServerIP(local.getIp());
		subStatus.setServerName(local.getName());
		subStatus.setMqIP(local.getSyncServerIP());
		subStatus.setMqPort(local.getSyncServerPort());
		subStatus.setMqPostAddress(local.getSyncServerPostAddress());
		subStatus.setApplyTime(supStatus.getApplyTime());
		subStatus.setStatus(status);
		NetworkContent networkContent = new NetworkContent();
		networkContent.setSubStatus(subStatus);
		networkContent.setStatus(status);
		Mail mail = new Mail();
		mail.setContent(JSON.toJSONString(networkContent));
		mail.setContentType(NetworkType.TYPE);
		mail.setSendMode(NormalMode.MODE);
		mail.setIpMail(true);
		return mail;
	}
	
}
