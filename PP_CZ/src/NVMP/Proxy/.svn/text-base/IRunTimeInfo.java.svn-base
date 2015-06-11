package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class IRunTimeInfo  implements ReturnDo { 
static public class EventHandler {public Object SwitchSetVideoInfoEvent(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.String DestID,java.lang.String DestIP,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 {return null; 
}
public Object ClientSetVideoInfoEvent(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 {return null; 
}
public Object SetVideoFlowInfoEvent(java.lang.String ClientId,java.lang.String SourceId,java.lang.Integer SourceIndex,java.lang.String Flow,java.lang.String Name) 
 {return null; 
}
public Object SetNetFlowInfoEvent(java.lang.String ClientId,java.lang.String InFlow,java.lang.String OutFlow,java.lang.String Name) 
 {return null; 
}
}
static public class FunResultHandler {public Object SeverLoginResult() 
 {return null; 
}
public Object RegionClientResult() 
 {return null; 
}
public Object SetFowardVideoInfoResult() 
 {return null; 
}
public Object ClientSetVideoInfoResult() 
 {return null; 
}
public Object SetVideoFlowInfoResult() 
 {return null; 
}
public Object SetNetFlowInfoResult() 
 {return null; 
}
}
private IRunTime run;
public IRunTimeInfo(IRunTime run)
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
if (EventURL.equals( "IRunTimeInfo.IRunTimeInfoEvent.OnSwitchSetVideoInfo")) 
 { 
  String ClientId =  retValue.get("ClientId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 String SourceIP =  retValue.get("SourceIP").toString(); 
 Integer SourceIndex;
if(retValue.get("SourceIndex").toString().equals("")) { 
SourceIndex = null;  
 } else { 
SourceIndex = Integer.valueOf(retValue.get("SourceIndex").toString()); 
 }
 Integer ServerIndex;
if(retValue.get("ServerIndex").toString().equals("")) { 
ServerIndex = null;  
 } else { 
ServerIndex = Integer.valueOf(retValue.get("ServerIndex").toString()); 
 }
 String DestID =  retValue.get("DestID").toString(); 
 String DestIP =  retValue.get("DestIP").toString(); 
 Boolean IsAddOrDel;
if(retValue.get("IsAddOrDel").toString().equals("")) { 
IsAddOrDel = null ; 
 } else { 
IsAddOrDel = Boolean.valueOf(retValue.get("IsAddOrDel").toString());
}
 String Name =  retValue.get("Name").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.SwitchSetVideoInfoEvent(ClientId,SourceId,SourceIP,SourceIndex,ServerIndex,DestID,DestIP,IsAddOrDel,Name ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.IRunTimeInfoEvent.OnClientSetVideoInfo")) 
 { 
  String ClientId =  retValue.get("ClientId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 String SourceIP =  retValue.get("SourceIP").toString(); 
 Integer SourceIndex;
if(retValue.get("SourceIndex").toString().equals("")) { 
SourceIndex = null;  
 } else { 
SourceIndex = Integer.valueOf(retValue.get("SourceIndex").toString()); 
 }
 Integer ServerIndex;
if(retValue.get("ServerIndex").toString().equals("")) { 
ServerIndex = null;  
 } else { 
ServerIndex = Integer.valueOf(retValue.get("ServerIndex").toString()); 
 }
 Boolean IsAddOrDel;
if(retValue.get("IsAddOrDel").toString().equals("")) { 
IsAddOrDel = null ; 
 } else { 
IsAddOrDel = Boolean.valueOf(retValue.get("IsAddOrDel").toString());
}
 String Name =  retValue.get("Name").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.ClientSetVideoInfoEvent(ClientId,SourceId,SourceIP,SourceIndex,ServerIndex,IsAddOrDel,Name ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.IRunTimeInfoEvent.OnSetVideoFlowInfo")) 
 { 
  String ClientId =  retValue.get("ClientId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 Integer SourceIndex;
if(retValue.get("SourceIndex").toString().equals("")) { 
SourceIndex = null;  
 } else { 
SourceIndex = Integer.valueOf(retValue.get("SourceIndex").toString()); 
 }
 String Flow =  retValue.get("Flow").toString(); 
 String Name =  retValue.get("Name").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.SetVideoFlowInfoEvent(ClientId,SourceId,SourceIndex,Flow,Name ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.IRunTimeInfoEvent.OnSetNetFlowInfo")) 
 { 
  String ClientId =  retValue.get("ClientId").toString(); 
 String InFlow =  retValue.get("InFlow").toString(); 
 String OutFlow =  retValue.get("OutFlow").toString(); 
 String Name =  retValue.get("Name").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.SetNetFlowInfoEvent(ClientId,InFlow,OutFlow,Name ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.SeverLogin")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SeverLoginResult( ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.RegionClient")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.RegionClientResult( ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.SetFowardVideoInfo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetFowardVideoInfoResult( ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.ClientSetVideoInfo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ClientSetVideoInfoResult( ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.SetVideoFlowInfo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetVideoFlowInfoResult( ); 
 } 
}
if (EventURL.equals( "IRunTimeInfo.RunTimeInfo.SetNetFlowInfo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetNetFlowInfoResult( ); 
 } 
}
}
  public void SeverLogin(java.lang.String _ClientId,java.lang.Boolean IsOnline) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SeverLogin"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("_ClientId", _ClientId); 
Params.put("IsOnline", IsOnline); 
run.Invoke(url, Params, null, null); 
 } 
   public void RegionClient(java.lang.String _ClientId) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.RegionClient"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("_ClientId", _ClientId); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetFowardVideoInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.String DestID,java.lang.String DestIP,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetFowardVideoInfo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("DestID", DestID); 
Params.put("DestIP", DestIP); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
run.Invoke(url, Params, null, null); 
 } 
   public void ClientSetVideoInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.String SourceIP,java.lang.Integer SourceIndex,java.lang.Integer ServerIndex,java.lang.Boolean IsAddOrDel,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.ClientSetVideoInfo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIP", SourceIP); 
Params.put("SourceIndex", SourceIndex); 
Params.put("ServerIndex", ServerIndex); 
Params.put("IsAddOrDel", IsAddOrDel); 
Params.put("Name", Name); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetVideoFlowInfo(java.lang.String ClientId,java.lang.String SourceId,java.lang.Integer SourceIndex,java.lang.String Flow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetVideoFlowInfo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ClientId", ClientId); 
Params.put("SourceId", SourceId); 
Params.put("SourceIndex", SourceIndex); 
Params.put("Flow", Flow); 
Params.put("Name", Name); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetNetFlowInfo(java.lang.String ClientId,java.lang.String InFlow,java.lang.String OutFlow,java.lang.String Name) 
 { 
  String url = "IRunTimeInfo.RunTimeInfo.SetNetFlowInfo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ClientId", ClientId); 
Params.put("InFlow", InFlow); 
Params.put("OutFlow", OutFlow); 
Params.put("Name", Name); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 