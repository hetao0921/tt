package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class AwareDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage StartSyncVideo(java.lang.String SrcSessionId,java.lang.String name,java.lang.String orgName,java.lang.String DestSessionId,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.StartSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SrcSessionId", SrcSessionId); 
Params.put("name", name); 
Params.put("orgName", orgName); 
Params.put("DestSessionId", DestSessionId); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RequestSyncVideo(java.lang.String userId,java.lang.String SessionId) 
 { 
  String url = "AwareDomain.ServiceAware.RequestSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("userId", userId); 
Params.put("SessionId", SessionId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SendSyncVideo(java.lang.String DestSessionId,java.lang.String SrcSessionid,java.lang.String DeviceId,java.lang.Integer index,java.lang.Integer regionIndex,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.SendSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestSessionId", DestSessionId); 
Params.put("SrcSessionid", SrcSessionid); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
Params.put("regionIndex", regionIndex); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage StartSyncVideo_Copy(java.lang.String SrcSessionId,java.lang.String name,java.lang.String orgName,java.lang.String DestSessionId,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.StartSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SrcSessionId", SrcSessionId); 
Params.put("name", name); 
Params.put("orgName", orgName); 
Params.put("DestSessionId", DestSessionId); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RequestSyncVideo_Copy(java.lang.String userId,java.lang.String SessionId) 
 { 
  String url = "AwareDomain.ServiceAware.RequestSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("userId", userId); 
Params.put("SessionId", SessionId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SendSyncVideo_Copy(java.lang.String DestSessionId,java.lang.String SrcSessionid,java.lang.String DeviceId,java.lang.Integer index,java.lang.Integer regionIndex,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.SendSyncVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestSessionId", DestSessionId); 
Params.put("SrcSessionid", SrcSessionid); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
Params.put("regionIndex", regionIndex); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }