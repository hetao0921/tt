package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class TimeCorrectionDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage SetWaitTime(java.lang.Integer wait) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetWaitTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("wait", wait); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetSystemTime(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetSystemTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetSystemTime(java.lang.String ClientId) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GetSystemTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalTimeEdit(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GrobalTimeEdit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetWaitTime_Copy(java.lang.Integer wait) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetWaitTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("wait", wait); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetSystemTime_Copy(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetSystemTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetSystemTime_Copy(java.lang.String ClientId) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GetSystemTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalTimeEdit_Copy(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GrobalTimeEdit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }