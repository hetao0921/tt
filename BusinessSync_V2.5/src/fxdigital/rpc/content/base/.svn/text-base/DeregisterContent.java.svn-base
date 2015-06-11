package fxdigital.rpc.content.base;

import fxdigital.db.RegisterCenter;
import fxdigital.rpc.AbstractContent;
import fxdigital.rpc.contenttype.base.DeregisterType;

/**
 * 中心注销的消息体
 * 
 * @author fucz
 * @version 2014-7-24
 */
public class DeregisterContent extends AbstractContent{
	
	private RegisterCenter registerCenterInfo;//注销的中心信息

	public RegisterCenter getRegisterCenterInfo() {
		return registerCenterInfo;
	}
	public void setRegisterCenterInfo(RegisterCenter registerCenterInfo) {
		this.registerCenterInfo = registerCenterInfo;
	}
	@Override
	public String getType() {
		return DeregisterType.TYPE;
	}

	
}
