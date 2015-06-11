package fxdigital.rpc.content.base;

import fxdigital.db.RegisterCenter;
import fxdigital.rpc.AbstractContent;
import fxdigital.rpc.contenttype.base.RegisterType;

/**
 * 中心注册消息体
 * 
 * @author fucz
 * @version 2014-7-24
 */
public class RegisterContent extends AbstractContent{
	
	private RegisterCenter registerCenterInfo;//注册的中心信息

	public RegisterCenter getRegisterCenterInfo() {
		return registerCenterInfo;
	}
	public void setRegisterCenterInfo(RegisterCenter registerCenterInfo) {
		this.registerCenterInfo = registerCenterInfo;
	}
	@Override
	public String getType() {
		return RegisterType.TYPE;
	}
	
}
