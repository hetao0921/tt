package NVMP.FxVideoStoreDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

/**
 * 视频存储业务
 * @author fx-zzw
 *
 */
public class FxVideoStoreDomain implements IBusinessDomain {
	private static IAppRuntime _AppRuntime = null;
	
	private ClientVideoStore clientVideoStore;
	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	@Override
	public boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		clientVideoStore = new ClientVideoStore();
		_AppRuntime.RegisterObject(GetDomainName(), clientVideoStore, "");
		_AppRuntime.RegisterObject(GetDomainName(), new  ServerVideoStore(), "");
		return false;
	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		//客户端下线事件
		if (type.equals("S") && state.equals("0")) {
			clientVideoStore.offLineCar(Sessionid);
		}
		
	}

	@Override
	public void DomainUnload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return "FxVideoStoreDomain";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "FxVideoStoreDomain";
	}

	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
