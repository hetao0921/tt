package fxdigital.postserver.contentdispose.handlers.base;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import fxdigital.db.RegisterCenter;
import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.rpc.Mail;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.base.RegisterContent;
import fxdigital.rpc.contenttype.base.RegisterType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点的中心注册消息处理
 * @author fucz
 * @version 2014-7-9
 */
@Component
public class NormalRegisterHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(NormalRegisterHandler.class);
	
	@Override
	public boolean handle(String strContent) {
		log.info("收到中心注册消息："+strContent);
		RegisterContent content = JSON.parseObject(strContent, RegisterContent.class);
		RegisterCenter rci = content.getRegisterCenterInfo();
		if(StringUtils.isNullOrEmpty(rci.getServerID())){
			rci.setServerID(localCenterID);
		}
		//移除注销记录
		Map<String,String> deregisters = contentHandler.getDeregisters();
		if(deregisters.containsKey(rci.getCenterID())){
			deregisters.remove(rci.getCenterID());
			log.warn("移除注销记录："+rci.getCenterID());
		}
		if(dbManager.isLocalRegisterCenterExist(rci.getCenterID())){
			dbManager.updateLocalRegisterCenterInfo(rci);
		}else{
			dbManager.addLocalRegisterCenterInfo(rci);
		}
		if(dbManager.isSyncRegisterCenterExist(rci.getCenterID())){
			RegisterCenter syncRegisterCenter = dbManager.getSyncRegisterCenter(
					rci.getCenterID());
			if(rci.getRegisterTime().after(syncRegisterCenter.getRegisterTime())){
				dbManager.updateSyncRegisterCenter(rci,null);
				log.info("更新中心注册数据！");
				if(!syncRegisterCenter.getServerID().equals(rci.getServerID())){
					Mail deregisterMail = contentHandler.createDeregisterMail(
							syncRegisterCenter.getServerID(),rci);
					return outerTransmitter.sendOut(deregisterMail);
				}
				return contentHandler.sendRelationMail();
			}else{
				log.info("此注册消息不是最新的，丢弃！");
				return true;
			}
		}else{
			dbManager.addSyncRegisterCenter(rci);
			log.info("添加中心注册数据！");
			return contentHandler.sendRelationMail();
		}
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(NormalMode.MODE,RegisterType.TYPE);
	}

}
