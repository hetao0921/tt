package fxdigital.postserver.contentdispose.handlers.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.db.RegisterCenter;
import fxdigital.db.ServerRelation;
import fxdigital.db.SyncServer;
import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.rpc.Mail;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.base.RelationContent;
import fxdigital.rpc.contenttype.base.RelationType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点级联同步数据处理
 * @author fucz
 * @version 2014-7-9
 */
@Component
public class NormalRelationHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(NormalRelationHandler.class);

	@Override
	public boolean handle(String strContent) {
		log.info("收到级联同步消息："+strContent);
		RelationContent content = JSON.parseObject(strContent, RelationContent.class);
		String preMailboxID = content.getSyncID();
		List<ServerRelation> mqNetworkInfos = content.getServerRelations();
		List<SyncServer> mailboxes = content.getSyncServers();
		List<RegisterCenter> registerCenterInfos = content.getRegisterCenters();
		dbManager.coverServerRelations(preMailboxID, mqNetworkInfos);
		dbManager.coverSyncServers(preMailboxID, mailboxes);
		List<RegisterCenter> deregisterCenters = findDeregisterCenter(
				preMailboxID,registerCenterInfos);
		log.info("检索到需要注销的中心："+JSON.toJSONString(deregisterCenters));
		for(RegisterCenter deregisterCenter : deregisterCenters){
			Mail deregisterMail = contentHandler.createDeregisterMail(
					deregisterCenter.getServerID(), deregisterCenter);
			if(!outerTransmitter.sendOut(deregisterMail)){
				return false;
			}
		}
		dbManager.coverRegisterCenters(preMailboxID, registerCenterInfos);
		return contentHandler.sendRelationMail();
	}
	
	private List<RegisterCenter> findDeregisterCenter(String preMailboxID,
			List<RegisterCenter> registerCenterInfos){
		List<RegisterCenter> deregisterCenters = new ArrayList<RegisterCenter>();
		if(registerCenterInfos != null){
			for(RegisterCenter rci : registerCenterInfos){
				RegisterCenter registerCenterInfo = dbManager.
						getSyncRegisterCenter(rci.getCenterID());
				if(registerCenterInfo != null && 
						rci.getRegisterTime().after(registerCenterInfo.getRegisterTime()) && 
						!rci.getServerID().equals(registerCenterInfo.getServerID())){
					deregisterCenters.add(registerCenterInfo);
				}
			}
		}
		return deregisterCenters;
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(NormalMode.MODE,RelationType.TYPE);
	}

}
