package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class AwareDomain  implements ReturnDo { 
static public class EventHandler {public Object onStartSyncVideoEvent(java.lang.String srcSessionId,java.lang.String name,java.lang.String orgName,java.lang.Boolean flag) 
 {return null; 
}
public Object onRequestSyncVideoEvent(java.lang.String userId,java.lang.String sessionId) 
 {return null; 
}
public Object onSendSyncVideoEvent(java.lang.String destSessionId,java.lang.String SrcSessionid,java.lang.String deviceId,java.lang.Integer index,java.lang.Integer regionIndex,java.lang.Boolean flag) 
 {return null; 
}
}
static public class FunResultHandler {public Object StartSyncVideoResult() 
 {return null; 
}
public Object RequestSyncVideoResult() 
 {return null; 
}
public Object SendSyncVideoResult() 
 {return null; 
}
}
private IRunTime run;
public AwareDomain(IRunTime run)
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
if (EventURL.equals( "AwareDomain.IServiceAware.onStartSyncVideo")) 
 { 
  String srcSessionId =  retValue.get("srcSessionId").toString(); 
 String name =  retValue.get("name").toString(); 
 String orgName =  retValue.get("orgName").toString(); 
 Boolean flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null ; 
 } else { 
flag = Boolean.valueOf(retValue.get("flag").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.onStartSyncVideoEvent(srcSessionId,name,orgName,flag ); 
 } 
}
if (EventURL.equals( "AwareDomain.IServiceAware.onRequestSyncVideo")) 
 { 
  String userId =  retValue.get("userId").toString(); 
 String sessionId =  retValue.get("sessionId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onRequestSyncVideoEvent(userId,sessionId ); 
 } 
}
if (EventURL.equals( "AwareDomain.IServiceAware.onSendSyncVideo")) 
 { 
  String destSessionId =  retValue.get("destSessionId").toString(); 
 String SrcSessionid =  retValue.get("SrcSessionid").toString(); 
 String deviceId =  retValue.get("deviceId").toString(); 
 Integer index;
if(retValue.get("index").toString().equals("")) { 
index = null;  
 } else { 
index = Integer.valueOf(retValue.get("index").toString()); 
 }
 Integer regionIndex;
if(retValue.get("regionIndex").toString().equals("")) { 
regionIndex = null;  
 } else { 
regionIndex = Integer.valueOf(retValue.get("regionIndex").toString()); 
 }
 Boolean flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null ; 
 } else { 
flag = Boolean.valueOf(retValue.get("flag").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.onSendSyncVideoEvent(destSessionId,SrcSessionid,deviceId,index,regionIndex,flag ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "AwareDomain.ServiceAware.StartSyncVideo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.StartSyncVideoResult( ); 
 } 
}
if (EventURL.equals( "AwareDomain.ServiceAware.RequestSyncVideo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.RequestSyncVideoResult( ); 
 } 
}
if (EventURL.equals( "AwareDomain.ServiceAware.SendSyncVideo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SendSyncVideoResult( ); 
 } 
}
}
  public void StartSyncVideo(java.lang.String SrcSessionId,java.lang.String name,java.lang.String orgName,java.lang.String DestSessionId,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.StartSyncVideo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SrcSessionId", SrcSessionId); 
Params.put("name", name); 
Params.put("orgName", orgName); 
Params.put("DestSessionId", DestSessionId); 
Params.put("flag", flag); 
run.Invoke(url, Params, null, null); 
 } 
   public void RequestSyncVideo(java.lang.String userId,java.lang.String SessionId) 
 { 
  String url = "AwareDomain.ServiceAware.RequestSyncVideo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("userId", userId); 
Params.put("SessionId", SessionId); 
run.Invoke(url, Params, null, null); 
 } 
   public void SendSyncVideo(java.lang.String DestSessionId,java.lang.String SrcSessionid,java.lang.String DeviceId,java.lang.Integer index,java.lang.Integer regionIndex,java.lang.Boolean flag) 
 { 
  String url = "AwareDomain.ServiceAware.SendSyncVideo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("DestSessionId", DestSessionId); 
Params.put("SrcSessionid", SrcSessionid); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
Params.put("regionIndex", regionIndex); 
Params.put("flag", flag); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 