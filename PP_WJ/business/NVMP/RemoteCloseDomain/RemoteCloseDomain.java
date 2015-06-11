/**
 * 
 */
package NVMP.RemoteCloseDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

/**
 * @author lizehua
 *
 */
public class RemoteCloseDomain implements IBusinessDomain{
	private static IAppRuntime _AppRuntime = null;
	private  RemoteClose remoteClose;
	
	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}
	@Override
	public boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		remoteClose=new RemoteClose();
		AppRuntime.RegisterObject(this.GetDomainName(), remoteClose, "");
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
		return "RemoteCloseDomain";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "RemoteCloseDomain";
	}

	@Override
	public List<IMessage> getCenterMessage() {
		return null;
	}

}
