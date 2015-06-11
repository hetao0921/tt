package NVMP.ClientValidateDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

/**
 * 客户端有效性验证模版注册类
 * 将客户端验证功能模块注册入服务器中
 * */
public class ClientValidateDomain implements IBusinessDomain  {
	
	private static IAppRuntime _AppRuntime = null;
	private ClientValidate clientValidate;
	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	@Override
	public boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		clientValidate = new ClientValidate();
		_AppRuntime.RegisterObject(this.GetDomainName(), clientValidate, "");
		return false;
	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		if (type.equals("S") && state.equals("0")
				&& !Sessionid.matches(".*@006$")) {
			clientValidate.clientQuit(Sessionid);
		}
		
	}

	@Override
	public void DomainUnload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return "客户端有效性验证";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "ClientValidateDomain";
	}

	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
