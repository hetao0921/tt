package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class IRunTimeInfoProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage SeverLogin(java.lang.String _ClientId,java.lang.Boolean IsOnline) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SeverLogin"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("_ClientId", _ClientId); 
Params.put("IsOnline", IsOnline); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RegionClient(java.lang.String _ClientId) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.RegionClient"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("_ClientId", _ClientId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetFowardVideoInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.String DestID,java.lang.String DestIP,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetFowardVideoInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("DestID", DestID); 
Params.put("DestIP", DestIP); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ClientSetVideoInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.ClientSetVideoInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetVideoFlowInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.Integer SourceIndex,java.lang.String Flow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetVideoFlowInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIndex", SourceIndex); 
Params.put("Flow", Flow); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetNetFlowInfo(java.lang.String ClientId,java.lang.String InFlow,java.lang.String OutFlow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetNetFlowInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("InFlow", InFlow); 
Params.put("OutFlow", OutFlow); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SeverLogin_Copy(java.lang.String _ClientId,java.lang.Boolean IsOnline) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SeverLogin"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("_ClientId", _ClientId); 
Params.put("IsOnline", IsOnline); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RegionClient_Copy(java.lang.String _ClientId) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.RegionClient"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("_ClientId", _ClientId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetFowardVideoInfo_Copy(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.String DestID,java.lang.String DestIP,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetFowardVideoInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("DestID", DestID); 
Params.put("DestIP", DestIP); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ClientSetVideoInfo_Copy(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.ClientSetVideoInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetVideoFlowInfo_Copy(java.lang.String ClientId,java.lang.String SourceId,java.lang.Integer SourceIndex,java.lang.String Flow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetVideoFlowInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIndex", SourceIndex); 
Params.put("Flow", Flow); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetNetFlowInfo_Copy(java.lang.String ClientId,java.lang.String InFlow,java.lang.String OutFlow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetNetFlowInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("InFlow", InFlow); 
Params.put("OutFlow", OutFlow); 
Params.put("Name", Name); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }