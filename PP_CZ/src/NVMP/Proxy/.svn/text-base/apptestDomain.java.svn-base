package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class apptestDomain  implements ReturnDo { 
static public class EventHandler {public Object MessageToEvent(java.lang.String client,java.lang.String who,java.lang.Integer v,java.lang.Boolean b) 
 {return null; 
}
}
static public class FunResultHandler {public Object MessageToResult() 
 {return null; 
}
}
private IRunTime run;
public apptestDomain(IRunTime run)
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
if (EventURL.equals( "apptestDomain.ISend.OnMessageTo")) 
 { 
  String client =  retValue.get("client").toString(); 
 String who =  retValue.get("who").toString(); 
 Integer v;
if(retValue.get("v").toString().equals("")) { 
v = null;  
 } else { 
v = Integer.valueOf(retValue.get("v").toString()); 
 }
 Boolean b;
if(retValue.get("b").toString().equals("")) { 
b = null ; 
 } else { 
b = Boolean.valueOf(retValue.get("b").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.MessageToEvent(client,who,v,b ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "apptestDomain.SendMessage.MessageTo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.MessageToResult( ); 
 } 
}
}
  public void MessageTo(java.lang.String client,java.lang.String who,java.lang.Integer v,java.lang.Boolean b) 
 { 
  String url = "apptestDomain.SendMessage.MessageTo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("client", client); 
Params.put("who", who); 
Params.put("v", v); 
Params.put("b", b); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 