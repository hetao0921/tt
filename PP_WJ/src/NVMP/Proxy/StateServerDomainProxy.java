package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class StateServerDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage getDeviceOnline(java.lang.String sessionid,java.lang.String deviceid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("deviceid", deviceid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getForwardState(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getForwardState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalGetCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String userID,java.lang.String userName,java.lang.String longinTime,java.lang.String locateIP,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("userID", userID); 
Params.put("userName", userName); 
Params.put("longinTime", longinTime); 
Params.put("locateIP", locateIP); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetCommandStateVideo(java.lang.String sessionid,java.lang.String clientid,java.lang.String sourceIP,java.lang.Integer sourceIndex,java.lang.String deviceID,java.lang.String deviceName,java.lang.Integer channelIndex,java.lang.String ChannelName,java.lang.String FlowRate,java.lang.String StartTime,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandStateVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("sourceIP", sourceIP); 
Params.put("sourceIndex", sourceIndex); 
Params.put("deviceID", deviceID); 
Params.put("deviceName", deviceName); 
Params.put("channelIndex", channelIndex); 
Params.put("ChannelName", ChannelName); 
Params.put("FlowRate", FlowRate); 
Params.put("StartTime", StartTime); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalGetHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer duration,java.lang.Integer memoryTotal,java.lang.Integer memoryUse,java.lang.Integer hddTotal,java.lang.Integer hddUse,java.lang.String cpu,java.lang.Integer netflowIn,java.lang.Integer netflowOut,java.lang.Integer tcpToal,java.lang.Integer udpToal,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("duration", duration); 
Params.put("memoryTotal", memoryTotal); 
Params.put("memoryUse", memoryUse); 
Params.put("hddTotal", hddTotal); 
Params.put("hddUse", hddUse); 
Params.put("cpu", cpu); 
Params.put("netflowIn", netflowIn); 
Params.put("netflowOut", netflowOut); 
Params.put("tcpToal", tcpToal); 
Params.put("udpToal", udpToal); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetHostStateTcp(java.lang.String sessionid,java.lang.String hostip,java.lang.String localip,java.lang.Integer localport,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateTcp"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("localip", localip); 
Params.put("localport", localport); 
Params.put("remoteip", remoteip); 
Params.put("remoteport", remoteport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetHostStateUdp(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer udpport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateUdp"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("udpport", udpport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalGetCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String type,java.lang.String clientid,java.lang.String clientip,java.lang.String deviceid,java.lang.String deviceip,java.lang.Integer devicechannel,java.lang.String routemap,java.lang.Integer lev,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String forwardid,java.lang.String sourceuuid,java.lang.String realroutemap,java.lang.String uuid,java.lang.String sendflag,java.lang.String sendid,java.lang.String context,java.lang.String devicename,java.lang.String routename,java.lang.String realroutename,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("type", type); 
Params.put("clientid", clientid); 
Params.put("clientip", clientip); 
Params.put("deviceid", deviceid); 
Params.put("deviceip", deviceip); 
Params.put("devicechannel", devicechannel); 
Params.put("routemap", routemap); 
Params.put("lev", lev); 
Params.put("sourceip", sourceip); 
Params.put("sourcechannel", sourcechannel); 
Params.put("forwardid", forwardid); 
Params.put("sourceuuid", sourceuuid); 
Params.put("realroutemap", realroutemap); 
Params.put("uuid", uuid); 
Params.put("sendflag", sendflag); 
Params.put("sendid", sendid); 
Params.put("context", context); 
Params.put("devicename", devicename); 
Params.put("routename", routename); 
Params.put("realroutename", realroutename); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalGetForwardState(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetForwardState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GlobalOnGetForwardStateOut(java.lang.String sessionid,java.lang.String forwardid,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.Integer bitrate,java.lang.Integer localchannel,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetForwardStateOut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("remoteip", remoteip); 
Params.put("remoteport", remoteport); 
Params.put("bitrate", bitrate); 
Params.put("localchannel", localchannel); 
Params.put("sourceip", sourceip); 
Params.put("sourcechannel", sourcechannel); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getDeviceOnline_Copy(java.lang.String sessionid,java.lang.String deviceid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("deviceid", deviceid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getCommandState_Copy(java.lang.String sessionid,java.lang.String clientid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getHostState_Copy(java.lang.String sessionid,java.lang.String hostip,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getCenterVideoRouteState_Copy(java.lang.String sessionid,java.lang.String centerid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getForwardState_Copy(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getForwardState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalGetCommandState_Copy(java.lang.String sessionid,java.lang.String clientid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetCommandState_Copy(java.lang.String sessionid,java.lang.String clientid,java.lang.String userID,java.lang.String userName,java.lang.String longinTime,java.lang.String locateIP,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("userID", userID); 
Params.put("userName", userName); 
Params.put("longinTime", longinTime); 
Params.put("locateIP", locateIP); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetCommandStateVideo_Copy(java.lang.String sessionid,java.lang.String clientid,java.lang.String sourceIP,java.lang.Integer sourceIndex,java.lang.String deviceID,java.lang.String deviceName,java.lang.Integer channelIndex,java.lang.String ChannelName,java.lang.String FlowRate,java.lang.String StartTime,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandStateVideo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("sourceIP", sourceIP); 
Params.put("sourceIndex", sourceIndex); 
Params.put("deviceID", deviceID); 
Params.put("deviceName", deviceName); 
Params.put("channelIndex", channelIndex); 
Params.put("ChannelName", ChannelName); 
Params.put("FlowRate", FlowRate); 
Params.put("StartTime", StartTime); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalGetHostState_Copy(java.lang.String sessionid,java.lang.String hostip,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetHostState_Copy(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer duration,java.lang.Integer memoryTotal,java.lang.Integer memoryUse,java.lang.Integer hddTotal,java.lang.Integer hddUse,java.lang.String cpu,java.lang.Integer netflowIn,java.lang.Integer netflowOut,java.lang.Integer tcpToal,java.lang.Integer udpToal,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("duration", duration); 
Params.put("memoryTotal", memoryTotal); 
Params.put("memoryUse", memoryUse); 
Params.put("hddTotal", hddTotal); 
Params.put("hddUse", hddUse); 
Params.put("cpu", cpu); 
Params.put("netflowIn", netflowIn); 
Params.put("netflowOut", netflowOut); 
Params.put("tcpToal", tcpToal); 
Params.put("udpToal", udpToal); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetHostStateTcp_Copy(java.lang.String sessionid,java.lang.String hostip,java.lang.String localip,java.lang.Integer localport,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateTcp"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("localip", localip); 
Params.put("localport", localport); 
Params.put("remoteip", remoteip); 
Params.put("remoteport", remoteport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetHostStateUdp_Copy(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer udpport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateUdp"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("udpport", udpport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalGetCenterVideoRouteState_Copy(java.lang.String sessionid,java.lang.String centerid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetCenterVideoRouteState_Copy(java.lang.String sessionid,java.lang.String centerid,java.lang.String type,java.lang.String clientid,java.lang.String clientip,java.lang.String deviceid,java.lang.String deviceip,java.lang.Integer devicechannel,java.lang.String routemap,java.lang.Integer lev,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String forwardid,java.lang.String sourceuuid,java.lang.String realroutemap,java.lang.String uuid,java.lang.String sendflag,java.lang.String sendid,java.lang.String context,java.lang.String devicename,java.lang.String routename,java.lang.String realroutename,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCenterVideoRouteState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("type", type); 
Params.put("clientid", clientid); 
Params.put("clientip", clientip); 
Params.put("deviceid", deviceid); 
Params.put("deviceip", deviceip); 
Params.put("devicechannel", devicechannel); 
Params.put("routemap", routemap); 
Params.put("lev", lev); 
Params.put("sourceip", sourceip); 
Params.put("sourcechannel", sourcechannel); 
Params.put("forwardid", forwardid); 
Params.put("sourceuuid", sourceuuid); 
Params.put("realroutemap", realroutemap); 
Params.put("uuid", uuid); 
Params.put("sendflag", sendflag); 
Params.put("sendid", sendid); 
Params.put("context", context); 
Params.put("devicename", devicename); 
Params.put("routename", routename); 
Params.put("realroutename", realroutename); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalGetForwardState_Copy(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetForwardState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GlobalOnGetForwardStateOut_Copy(java.lang.String sessionid,java.lang.String forwardid,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.Integer bitrate,java.lang.Integer localchannel,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetForwardStateOut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("remoteip", remoteip); 
Params.put("remoteport", remoteport); 
Params.put("bitrate", bitrate); 
Params.put("localchannel", localchannel); 
Params.put("sourceip", sourceip); 
Params.put("sourcechannel", sourcechannel); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }