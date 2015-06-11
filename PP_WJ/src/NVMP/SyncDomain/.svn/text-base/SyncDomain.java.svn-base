package NVMP.SyncDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

/**
 * 同步信息的组件，用来进行数据同步。
 * 
 * */
public class SyncDomain implements IBusinessDomain {

	private static IAppRuntime _AppRuntime = null;
	private SyncBusiness _SyncBusiness;
 
	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	@Override
	public boolean DomainEntry(IAppRuntime AppRuntime) {
		// TODO Auto-generated method stub
		_AppRuntime = AppRuntime;

		_SyncBusiness = new SyncBusiness();
		AppRuntime.RegisterObject(this.GetDomainName(), _SyncBusiness, "");

		return true;
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
		return "SyncDomain";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "SyncDomain";
	}

	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
