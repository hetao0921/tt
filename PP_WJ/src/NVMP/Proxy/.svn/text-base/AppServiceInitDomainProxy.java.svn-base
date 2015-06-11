package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class AppServiceInitDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage SetCenterLev(java.lang.Integer lev) 
 { 
  String url = "AppServiceInitDomain.ServerInitHandle.SetCenterLev"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("lev", lev); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetCenterLev_Copy(java.lang.Integer lev) 
 { 
  String url = "AppServiceInitDomain.ServerInitHandle.SetCenterLev"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("lev", lev); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }