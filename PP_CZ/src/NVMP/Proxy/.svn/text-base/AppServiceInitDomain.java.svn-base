package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class AppServiceInitDomain  implements ReturnDo { 
static public class EventHandler {}
static public class FunResultHandler {public Object SetCenterLevResult() 
 {return null; 
}
}
private IRunTime run;
public AppServiceInitDomain(IRunTime run)
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
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "AppServiceInitDomain.ServerInitHandle.SetCenterLev")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetCenterLevResult( ); 
 } 
}
}
  public void SetCenterLev(java.lang.Integer lev) 
 { 
  String url = "AppServiceInitDomain.ServerInitHandle.SetCenterLev"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("lev", lev); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 