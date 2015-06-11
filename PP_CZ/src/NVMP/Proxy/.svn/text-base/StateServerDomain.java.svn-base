package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class StateServerDomain  implements ReturnDo { 
static public class EventHandler {public Object onGetDeviceOnlineEvent(java.lang.String deviceid,java.lang.String devicename,java.lang.String deviceip,java.lang.Integer state,java.lang.String context) 
 {return null; 
}
public Object onGetCommandStateEvent(java.lang.String clientid,java.lang.String userID,java.lang.String userName,java.lang.String longinTime,java.lang.String locateIP,java.lang.String context) 
 {return null; 
}
public Object onGetCommandStateVideoEvent(java.lang.String clientid,java.lang.String sourceIP,java.lang.Integer sourceIndex,java.lang.String deviceID,java.lang.String deviceName,java.lang.Integer channelIndex,java.lang.String channelName,java.lang.String flowRate,java.lang.String startTime,java.lang.String context) 
 {return null; 
}
public Object onGetHostStateEvent(java.lang.String hostip,java.lang.Integer duration,java.lang.Integer memoryTotal,java.lang.Integer memoryUse,java.lang.Integer hddTotal,java.lang.Integer hddUse,java.lang.String cpu,java.lang.Integer netflowIn,java.lang.Integer netflowOut,java.lang.Integer tcpToal,java.lang.Integer udpToal,java.lang.String context) 
 {return null; 
}
public Object onGetHostStateTcpEvent(java.lang.String hostip,java.lang.String localip,java.lang.Integer localport,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.String context) 
 {return null; 
}
public Object onGetHostStateUdpEvent(java.lang.String hostip,java.lang.Integer udpport,java.lang.String context) 
 {return null; 
}
public Object onGetCenterVideoRouteStateEvent(java.lang.String centerid,java.lang.String type,java.lang.String clientid,java.lang.String clientip,java.lang.String deviceid,java.lang.String deviceip,java.lang.Integer devicechannel,java.lang.String routemap,java.lang.Integer lev,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String forwardid,java.lang.String sourceuuid,java.lang.String realroutemap,java.lang.String uuid,java.lang.String sendflag,java.lang.String sendid,java.lang.String context,java.lang.String devicename,java.lang.String routemapbyname,java.lang.String realmapbyname) 
 {return null; 
}
public Object onGetForwardStateInEvent(java.lang.String forwardid,java.lang.Integer localchannel,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String context) 
 {return null; 
}
public Object onGetForwardStateOutEvent(java.lang.String forwardid,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.Integer bitrate,java.lang.Integer localchannel,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String context) 
 {return null; 
}
}
static public class FunResultHandler {public Object getDeviceOnlineResult() 
 {return null; 
}
public Object getCommandStateResult() 
 {return null; 
}
public Object getHostStateResult() 
 {return null; 
}
public Object getCenterVideoRouteStateResult() 
 {return null; 
}
public Object getForwardStateResult() 
 {return null; 
}
public Object GlobalGetCommandStateResult() 
 {return null; 
}
public Object GlobalOnGetCommandStateResult() 
 {return null; 
}
public Object GlobalOnGetCommandStateVideoResult() 
 {return null; 
}
public Object GlobalGetHostStateResult() 
 {return null; 
}
public Object GlobalOnGetHostStateResult() 
 {return null; 
}
public Object GlobalOnGetHostStateTcpResult() 
 {return null; 
}
public Object GlobalOnGetHostStateUdpResult() 
 {return null; 
}
public Object GlobalGetCenterVideoRouteStateResult() 
 {return null; 
}
public Object GlobalOnGetCenterVideoRouteStateResult() 
 {return null; 
}
public Object GlobalGetForwardStateResult() 
 {return null; 
}
public Object GlobalOnGetForwardStateOutResult() 
 {return null; 
}
}
private IRunTime run;
public StateServerDomain(IRunTime run)
 { 
this.run = run; 
((RunTime)run).registerProxy(this.getClass().getSimpleName(), this);
}
public EventHandler eventhandler;
public FunResultHandler funhandler;
public void setEventhand(EventHandler eventhandler) {
this.eventhandler = eventhandler;
}
 public void setFunhand(FunResultHandler funhandler) {
this.funhandler = funhandler;
}
public void ReturnEvent(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetDeviceOnline")) 
 { 
  String deviceid =  retValue.get("deviceid").toString(); 
 String devicename =  retValue.get("devicename").toString(); 
 String deviceip =  retValue.get("deviceip").toString(); 
 Integer state;
if(retValue.get("state").toString().equals("")) { 
state = null;  
 } else { 
state = Integer.valueOf(retValue.get("state").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetDeviceOnlineEvent(deviceid,devicename,deviceip,state,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetCommandState")) 
 { 
  String clientid =  retValue.get("clientid").toString(); 
 String userID =  retValue.get("userID").toString(); 
 String userName =  retValue.get("userName").toString(); 
 String longinTime =  retValue.get("longinTime").toString(); 
 String locateIP =  retValue.get("locateIP").toString(); 
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetCommandStateEvent(clientid,userID,userName,longinTime,locateIP,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetCommandStateVideo")) 
 { 
  String clientid =  retValue.get("clientid").toString(); 
 String sourceIP =  retValue.get("sourceIP").toString(); 
 Integer sourceIndex;
if(retValue.get("sourceIndex").toString().equals("")) { 
sourceIndex = null;  
 } else { 
sourceIndex = Integer.valueOf(retValue.get("sourceIndex").toString()); 
 }
 String deviceID =  retValue.get("deviceID").toString(); 
 String deviceName =  retValue.get("deviceName").toString(); 
 Integer channelIndex;
if(retValue.get("channelIndex").toString().equals("")) { 
channelIndex = null;  
 } else { 
channelIndex = Integer.valueOf(retValue.get("channelIndex").toString()); 
 }
 String channelName =  retValue.get("channelName").toString(); 
 String flowRate =  retValue.get("flowRate").toString(); 
 String startTime =  retValue.get("startTime").toString(); 
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetCommandStateVideoEvent(clientid,sourceIP,sourceIndex,deviceID,deviceName,channelIndex,channelName,flowRate,startTime,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetHostState")) 
 { 
  String hostip =  retValue.get("hostip").toString(); 
 Integer duration;
if(retValue.get("duration").toString().equals("")) { 
duration = null;  
 } else { 
duration = Integer.valueOf(retValue.get("duration").toString()); 
 }
 Integer memoryTotal;
if(retValue.get("memoryTotal").toString().equals("")) { 
memoryTotal = null;  
 } else { 
memoryTotal = Integer.valueOf(retValue.get("memoryTotal").toString()); 
 }
 Integer memoryUse;
if(retValue.get("memoryUse").toString().equals("")) { 
memoryUse = null;  
 } else { 
memoryUse = Integer.valueOf(retValue.get("memoryUse").toString()); 
 }
 Integer hddTotal;
if(retValue.get("hddTotal").toString().equals("")) { 
hddTotal = null;  
 } else { 
hddTotal = Integer.valueOf(retValue.get("hddTotal").toString()); 
 }
 Integer hddUse;
if(retValue.get("hddUse").toString().equals("")) { 
hddUse = null;  
 } else { 
hddUse = Integer.valueOf(retValue.get("hddUse").toString()); 
 }
 String cpu =  retValue.get("cpu").toString(); 
 Integer netflowIn;
if(retValue.get("netflowIn").toString().equals("")) { 
netflowIn = null;  
 } else { 
netflowIn = Integer.valueOf(retValue.get("netflowIn").toString()); 
 }
 Integer netflowOut;
if(retValue.get("netflowOut").toString().equals("")) { 
netflowOut = null;  
 } else { 
netflowOut = Integer.valueOf(retValue.get("netflowOut").toString()); 
 }
 Integer tcpToal;
if(retValue.get("tcpToal").toString().equals("")) { 
tcpToal = null;  
 } else { 
tcpToal = Integer.valueOf(retValue.get("tcpToal").toString()); 
 }
 Integer udpToal;
if(retValue.get("udpToal").toString().equals("")) { 
udpToal = null;  
 } else { 
udpToal = Integer.valueOf(retValue.get("udpToal").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetHostStateEvent(hostip,duration,memoryTotal,memoryUse,hddTotal,hddUse,cpu,netflowIn,netflowOut,tcpToal,udpToal,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetHostStateTcp")) 
 { 
  String hostip =  retValue.get("hostip").toString(); 
 String localip =  retValue.get("localip").toString(); 
 Integer localport;
if(retValue.get("localport").toString().equals("")) { 
localport = null;  
 } else { 
localport = Integer.valueOf(retValue.get("localport").toString()); 
 }
 String remoteip =  retValue.get("remoteip").toString(); 
 Integer remoteport;
if(retValue.get("remoteport").toString().equals("")) { 
remoteport = null;  
 } else { 
remoteport = Integer.valueOf(retValue.get("remoteport").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetHostStateTcpEvent(hostip,localip,localport,remoteip,remoteport,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetHostStateUdp")) 
 { 
  String hostip =  retValue.get("hostip").toString(); 
 Integer udpport;
if(retValue.get("udpport").toString().equals("")) { 
udpport = null;  
 } else { 
udpport = Integer.valueOf(retValue.get("udpport").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetHostStateUdpEvent(hostip,udpport,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetCenterVideoRouteState")) 
 { 
  String centerid =  retValue.get("centerid").toString(); 
 String type =  retValue.get("type").toString(); 
 String clientid =  retValue.get("clientid").toString(); 
 String clientip =  retValue.get("clientip").toString(); 
 String deviceid =  retValue.get("deviceid").toString(); 
 String deviceip =  retValue.get("deviceip").toString(); 
 Integer devicechannel;
if(retValue.get("devicechannel").toString().equals("")) { 
devicechannel = null;  
 } else { 
devicechannel = Integer.valueOf(retValue.get("devicechannel").toString()); 
 }
 String routemap =  retValue.get("routemap").toString(); 
 Integer lev;
if(retValue.get("lev").toString().equals("")) { 
lev = null;  
 } else { 
lev = Integer.valueOf(retValue.get("lev").toString()); 
 }
 String sourceip =  retValue.get("sourceip").toString(); 
 Integer sourcechannel;
if(retValue.get("sourcechannel").toString().equals("")) { 
sourcechannel = null;  
 } else { 
sourcechannel = Integer.valueOf(retValue.get("sourcechannel").toString()); 
 }
 String forwardid =  retValue.get("forwardid").toString(); 
 String sourceuuid =  retValue.get("sourceuuid").toString(); 
 String realroutemap =  retValue.get("realroutemap").toString(); 
 String uuid =  retValue.get("uuid").toString(); 
 String sendflag =  retValue.get("sendflag").toString(); 
 String sendid =  retValue.get("sendid").toString(); 
 String context =  retValue.get("context").toString(); 
 String devicename =  retValue.get("devicename").toString(); 
 String routemapbyname =  retValue.get("routemapbyname").toString(); 
 String realmapbyname =  retValue.get("realmapbyname").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetCenterVideoRouteStateEvent(centerid,type,clientid,clientip,deviceid,deviceip,devicechannel,routemap,lev,sourceip,sourcechannel,forwardid,sourceuuid,realroutemap,uuid,sendflag,sendid,context,devicename,routemapbyname,realmapbyname ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetForwardStateIn")) 
 { 
  String forwardid =  retValue.get("forwardid").toString(); 
 Integer localchannel;
if(retValue.get("localchannel").toString().equals("")) { 
localchannel = null;  
 } else { 
localchannel = Integer.valueOf(retValue.get("localchannel").toString()); 
 }
 String sourceip =  retValue.get("sourceip").toString(); 
 Integer sourcechannel;
if(retValue.get("sourcechannel").toString().equals("")) { 
sourcechannel = null;  
 } else { 
sourcechannel = Integer.valueOf(retValue.get("sourcechannel").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetForwardStateInEvent(forwardid,localchannel,sourceip,sourcechannel,context ); 
 } 
}
if (EventURL.equals( "StateServerDomain.IStateMessage.onGetForwardStateOut")) 
 { 
  String forwardid =  retValue.get("forwardid").toString(); 
 String remoteip =  retValue.get("remoteip").toString(); 
 Integer remoteport;
if(retValue.get("remoteport").toString().equals("")) { 
remoteport = null;  
 } else { 
remoteport = Integer.valueOf(retValue.get("remoteport").toString()); 
 }
 Integer bitrate;
if(retValue.get("bitrate").toString().equals("")) { 
bitrate = null;  
 } else { 
bitrate = Integer.valueOf(retValue.get("bitrate").toString()); 
 }
 Integer localchannel;
if(retValue.get("localchannel").toString().equals("")) { 
localchannel = null;  
 } else { 
localchannel = Integer.valueOf(retValue.get("localchannel").toString()); 
 }
 String sourceip =  retValue.get("sourceip").toString(); 
 Integer sourcechannel;
if(retValue.get("sourcechannel").toString().equals("")) { 
sourcechannel = null;  
 } else { 
sourcechannel = Integer.valueOf(retValue.get("sourcechannel").toString()); 
 }
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetForwardStateOutEvent(forwardid,remoteip,remoteport,bitrate,localchannel,sourceip,sourcechannel,context ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "StateServerDomain.StateManage.getDeviceOnline")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getDeviceOnlineResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.getCommandState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getCommandStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.getHostState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getHostStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.getCenterVideoRouteState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getCenterVideoRouteStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.getForwardState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getForwardStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalGetCommandState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalGetCommandStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetCommandState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetCommandStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetCommandStateVideo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetCommandStateVideoResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalGetHostState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalGetHostStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetHostState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetHostStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetHostStateTcp")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetHostStateTcpResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetHostStateUdp")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetHostStateUdpResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalGetCenterVideoRouteState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalGetCenterVideoRouteStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetCenterVideoRouteState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetCenterVideoRouteStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalGetForwardState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalGetForwardStateResult( ); 
 } 
}
if (EventURL.equals( "StateServerDomain.StateManage.GlobalOnGetForwardStateOut")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GlobalOnGetForwardStateOutResult( ); 
 } 
}
}
  public void getDeviceOnline(java.lang.String sessionid,java.lang.String deviceid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getDeviceOnline"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("deviceid", deviceid); 
Params.put("context", context); 
run.Invoke(url, Params, null, null); 
 } 
   public void getCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCommandState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
run.Invoke(url, Params, null, null); 
 } 
   public void getHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getHostState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
run.Invoke(url, Params, null, null); 
 } 
   public void getCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getCenterVideoRouteState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
run.Invoke(url, Params, null, null); 
 } 
   public void getForwardState(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context) 
 { 
  String url = "StateServerDomain.StateManage.getForwardState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalGetCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCommandState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetCommandState(java.lang.String sessionid,java.lang.String clientid,java.lang.String userID,java.lang.String userName,java.lang.String longinTime,java.lang.String locateIP,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("clientid", clientid); 
Params.put("userID", userID); 
Params.put("userName", userName); 
Params.put("longinTime", longinTime); 
Params.put("locateIP", locateIP); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetCommandStateVideo(java.lang.String sessionid,java.lang.String clientid,java.lang.String sourceIP,java.lang.Integer sourceIndex,java.lang.String deviceID,java.lang.String deviceName,java.lang.Integer channelIndex,java.lang.String ChannelName,java.lang.String FlowRate,java.lang.String StartTime,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCommandStateVideo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalGetHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetHostState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetHostState(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer duration,java.lang.Integer memoryTotal,java.lang.Integer memoryUse,java.lang.Integer hddTotal,java.lang.Integer hddUse,java.lang.String cpu,java.lang.Integer netflowIn,java.lang.Integer netflowOut,java.lang.Integer tcpToal,java.lang.Integer udpToal,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetHostStateTcp(java.lang.String sessionid,java.lang.String hostip,java.lang.String localip,java.lang.Integer localport,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateTcp"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("localip", localip); 
Params.put("localport", localport); 
Params.put("remoteip", remoteip); 
Params.put("remoteport", remoteport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetHostStateUdp(java.lang.String sessionid,java.lang.String hostip,java.lang.Integer udpport,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetHostStateUdp"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("hostip", hostip); 
Params.put("udpport", udpport); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalGetCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetCenterVideoRouteState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("centerid", centerid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetCenterVideoRouteState(java.lang.String sessionid,java.lang.String centerid,java.lang.String type,java.lang.String clientid,java.lang.String clientip,java.lang.String deviceid,java.lang.String deviceip,java.lang.Integer devicechannel,java.lang.String routemap,java.lang.Integer lev,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String forwardid,java.lang.String sourceuuid,java.lang.String realroutemap,java.lang.String uuid,java.lang.String sendflag,java.lang.String sendid,java.lang.String context,java.lang.String devicename,java.lang.String routename,java.lang.String realroutename,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetCenterVideoRouteState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalGetForwardState(java.lang.String sessionid,java.lang.String forwardid,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalGetForwardState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("forwardid", forwardid); 
Params.put("context", context); 
Params.put("sourcecenterid", sourcecenterid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GlobalOnGetForwardStateOut(java.lang.String sessionid,java.lang.String forwardid,java.lang.String remoteip,java.lang.Integer remoteport,java.lang.Integer bitrate,java.lang.Integer localchannel,java.lang.String sourceip,java.lang.Integer sourcechannel,java.lang.String context,java.lang.String sourcecenterid) 
 { 
  String url = "StateServerDomain.StateManage.GlobalOnGetForwardStateOut"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
 } 
 