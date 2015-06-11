package NVMP.SystemMessageDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

public class SystemMessageDomain implements IBusinessDomain {

	
	private static IAppRuntime _AppRuntime = null;


	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	} 
	@Override
	public boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		try {

			AppRuntime.RegisterObject(this.GetDomainName(), new MessageManage(),
					"");

		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DomainUnload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return "系统消息管理";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "SystemMessageDomain";
	}
	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
