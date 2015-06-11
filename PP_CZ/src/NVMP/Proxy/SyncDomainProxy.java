package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class SyncDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage getUpLoadState(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getUpLoadState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getDownLoadState(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getDownLoadState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getSelfData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getSelfData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getNativeData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getNativeData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getServerDataVersion(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getServerDataVersion"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage clearNativeData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.clearNativeData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage syncUpLoad(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncUpLoad"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage syncDownLoad(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncDownLoad"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getUpLoadState_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getUpLoadState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getDownLoadState_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getDownLoadState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getSelfData_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getSelfData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getNativeData_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getNativeData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getServerDataVersion_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getServerDataVersion"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage clearNativeData_Copy(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.clearNativeData"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage syncUpLoad_Copy(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncUpLoad"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage syncDownLoad_Copy(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncDownLoad"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }