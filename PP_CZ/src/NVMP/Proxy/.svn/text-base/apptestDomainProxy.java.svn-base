package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class apptestDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage MessageTo(java.lang.String client,java.lang.String who,java.lang.Integer v,java.lang.Boolean b) 
 { 
  String url = "apptestDomain.SendMessage.MessageTo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("who", who); 
Params.put("v", v); 
Params.put("b", b); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage MessageTo_Copy(java.lang.String client,java.lang.String who,java.lang.Integer v,java.lang.Boolean b) 
 { 
  String url = "apptestDomain.SendMessage.MessageTo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("who", who); 
Params.put("v", v); 
Params.put("b", b); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }