package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class RemoteCloseDomain  implements ReturnDo { 
static public class EventHandler {public Object onDisposeRemoteCloseEvent(java.lang.String json,java.lang.String context,java.lang.String uuID) 
 {return null; 
}
public Object onNoticeClientEvent(java.lang.String returnMsg,java.lang.String context,java.lang.String uuID) 
 {return null; 
}
public Object onReturnStautsEvent(java.lang.String status,java.lang.String context,java.lang.String uuID) 
 {return null; 
}
}
static public class FunResultHandler {public Object remoteCloseResult() 
 {return null; 
}
public Object toVerifyRemoteCloseResult() 
 {return null; 
}
}
private IRunTime run;
public RemoteCloseDomain(IRunTime run)
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
if (EventURL.equals( "RemoteCloseDomain.RemoteCloseEvent.onDisposeRemoteClose")) 
 { 
  String json =  retValue.get("json").toString(); 
 String context =  retValue.get("context").toString(); 
 String uuID =  retValue.get("uuID").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onDisposeRemoteCloseEvent(json,context,uuID ); 
 } 
}
if (EventURL.equals( "RemoteCloseDomain.RemoteCloseEvent.onNoticeClient")) 
 { 
  String returnMsg =  retValue.get("returnMsg").toString(); 
 String context =  retValue.get("context").toString(); 
 String uuID =  retValue.get("uuID").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onNoticeClientEvent(returnMsg,context,uuID ); 
 } 
}
if (EventURL.equals( "RemoteCloseDomain.RemoteCloseEvent.onReturnStauts")) 
 { 
  String status =  retValue.get("status").toString(); 
 String context =  retValue.get("context").toString(); 
 String uuID =  retValue.get("uuID").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onReturnStautsEvent(status,context,uuID ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "RemoteCloseDomain.RemoteClose.remoteClose")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.remoteCloseResult( ); 
 } 
}
if (EventURL.equals( "RemoteCloseDomain.RemoteClose.toVerifyRemoteClose")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.toVerifyRemoteCloseResult( ); 
 } 
}
}
  public void remoteClose(java.lang.String json,java.lang.String context,java.lang.String uuID) 
 { 
  String url = "RemoteCloseDomain.RemoteClose.remoteClose"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("json", json); 
Params.put("context", context); 
Params.put("uuID", uuID); 
run.Invoke(url, Params, null, null); 
 } 
   public void toVerifyRemoteClose(java.lang.String clientID,java.lang.String context,java.lang.String uuID) 
 { 
  String url = "RemoteCloseDomain.RemoteClose.toVerifyRemoteClose"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("clientID", clientID); 
Params.put("context", context); 
Params.put("uuID", uuID); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 