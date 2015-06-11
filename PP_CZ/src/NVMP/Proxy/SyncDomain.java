package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class SyncDomain  implements ReturnDo { 
static public class EventHandler {public Object onGetStateEventEvent(java.lang.String type,java.lang.Integer flag,java.lang.String userid,java.lang.String ip) 
 {return null; 
}
public Object onGetSelfDataEventEvent(java.lang.Integer version,java.lang.String uploadDate,java.lang.Integer flag) 
 {return null; 
}
public Object onGetNativeDataEventEvent(java.lang.String versions) 
 {return null; 
}
public Object onGetServerDataEventEvent(java.lang.String versions) 
 {return null; 
}
public Object onFailEventEvent(java.lang.String operate,java.lang.String reason) 
 {return null; 
}
}
static public class FunResultHandler {public Object getUpLoadStateResult() 
 {return null; 
}
public Object getDownLoadStateResult() 
 {return null; 
}
public Object getSelfDataResult() 
 {return null; 
}
public Object getNativeDataResult() 
 {return null; 
}
public Object getServerDataVersionResult() 
 {return null; 
}
public Object clearNativeDataResult() 
 {return null; 
}
public Object syncUpLoadResult() 
 {return null; 
}
public Object syncDownLoadResult() 
 {return null; 
}
}
private IRunTime run;
public SyncDomain(IRunTime run)
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
if (EventURL.equals( "SyncDomain.ISyncEvent.onGetStateEvent")) 
 { 
  String type =  retValue.get("type").toString(); 
 Integer flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null;  
 } else { 
flag = Integer.valueOf(retValue.get("flag").toString()); 
 }
 String userid =  retValue.get("userid").toString(); 
 String ip =  retValue.get("ip").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetStateEventEvent(type,flag,userid,ip ); 
 } 
}
if (EventURL.equals( "SyncDomain.ISyncEvent.onGetSelfDataEvent")) 
 { 
  Integer version;
if(retValue.get("version").toString().equals("")) { 
version = null;  
 } else { 
version = Integer.valueOf(retValue.get("version").toString()); 
 }
 String uploadDate =  retValue.get("uploadDate").toString(); 
 Integer flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null;  
 } else { 
flag = Integer.valueOf(retValue.get("flag").toString()); 
 }
if(this.eventhandler!=null) {
 this.eventhandler.onGetSelfDataEventEvent(version,uploadDate,flag ); 
 } 
}
if (EventURL.equals( "SyncDomain.ISyncEvent.onGetNativeDataEvent")) 
 { 
  String versions =  retValue.get("versions").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetNativeDataEventEvent(versions ); 
 } 
}
if (EventURL.equals( "SyncDomain.ISyncEvent.onGetServerDataEvent")) 
 { 
  String versions =  retValue.get("versions").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onGetServerDataEventEvent(versions ); 
 } 
}
if (EventURL.equals( "SyncDomain.ISyncEvent.onFailEvent")) 
 { 
  String operate =  retValue.get("operate").toString(); 
 String reason =  retValue.get("reason").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.onFailEventEvent(operate,reason ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "SyncDomain.SyncBusiness.getUpLoadState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getUpLoadStateResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.getDownLoadState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getDownLoadStateResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.getSelfData")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getSelfDataResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.getNativeData")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getNativeDataResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.getServerDataVersion")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getServerDataVersionResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.clearNativeData")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.clearNativeDataResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.syncUpLoad")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.syncUpLoadResult( ); 
 } 
}
if (EventURL.equals( "SyncDomain.SyncBusiness.syncDownLoad")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.syncDownLoadResult( ); 
 } 
}
}
  public void getUpLoadState(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getUpLoadState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void getDownLoadState(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getDownLoadState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void getSelfData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getSelfData"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void getNativeData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getNativeData"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void getServerDataVersion(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.getServerDataVersion"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void clearNativeData(java.lang.String Sessionid) 
 { 
  String url = "SyncDomain.SyncBusiness.clearNativeData"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
run.Invoke(url, Params, null, null); 
 } 
   public void syncUpLoad(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncUpLoad"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
run.Invoke(url, Params, null, null); 
 } 
   public void syncDownLoad(java.lang.String Sessionid,java.lang.String userid) 
 { 
  String url = "SyncDomain.SyncBusiness.syncDownLoad"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("Sessionid", Sessionid); 
Params.put("userid", userid); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 