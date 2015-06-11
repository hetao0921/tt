package fxdigital.postserver.contentdispose.handlers.base;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import fxdigital.db.RegisterCenter;
import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.base.DeregisterContent;
import fxdigital.rpc.contenttype.base.DeregisterType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 点对点中心注销消息处理
 * @author fucz
 * @version 2014-7-9
 */
@Component
public class NormalDeregisterHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(NormalDeregisterHandler.class);

	@Override
	public boolean handle(String strContent) {
		log.info("收到中心注销消息："+strContent);
		DeregisterContent content = JSON.parseObject(strContent, DeregisterContent.class);
		RegisterCenter rci = content.getRegisterCenterInfo();
		String centerID = rci.getCenterID();
		dbManager.deleteLocalRegisterCenterInfo(centerID);
		RegisterCenter syncRegisterCenter = dbManager.getSyncRegisterCenter(centerID);
		if(syncRegisterCenter != null
				&& StringUtils.isNullOrEmpty(syncRegisterCenter.getSyncID())){
			dbManager.deleteSyncRegisterCenter(centerID);
		}
		log.info("注销通道【"+rci.getChannelName()+"】！");
		contentHandler.getDeregisters().put(centerID, rci.getChannelName());
		return contentHandler.sendRelationMail();
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(NormalMode.MODE,DeregisterType.TYPE);
	}

}
