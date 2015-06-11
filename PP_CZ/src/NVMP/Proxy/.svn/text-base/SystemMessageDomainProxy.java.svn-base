package NVMP.Proxy;

import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;

public class SystemMessageDomainProxy {
	private static IAppRuntime _AppRuntime = null;

	public void load(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
	}

	static public IMessage GetCenterID(java.lang.String userid) {
		String url = "SystemMessageDomain.MessageManage.GetCenterID";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("userid", userid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GetCenterID_Copy(java.lang.String userid) {
		String url = "SystemMessageDomain.MessageManage.GetCenterID";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("userid", userid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}
}