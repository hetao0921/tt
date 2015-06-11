package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class TimeCorrectionDomain  implements ReturnDo { 
static public class EventHandler {public Object AnnounceTimeEvent(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 {return null; 
}
}
static public class FunResultHandler {public Object SetWaitTimeResult() 
 {return null; 
}
public Object SetSystemTimeResult() 
 {return null; 
}
public Object GetSystemTimeResult() 
 {return null; 
}
public Object GrobalTimeEditResult() 
 {return null; 
}
}
private IRunTime run;
public TimeCorrectionDomain(IRunTime run)
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
if (EventURL.equals( "TimeCorrectionDomain.ITimeManage.OnAnnounceTime")) 
 { 
  Integer year;
if(retValue.get("year").toString().equals("")) { 
year = null;  
 } else { 
year = Integer.valueOf(retValue.get("year").toString()); 
 }
 Integer month;
if(retValue.get("month").toString().equals("")) { 
month = null;  
 } else { 
month = Integer.valueOf(retValue.get("month").toString()); 
 }
 Integer day;
if(retValue.get("day").toString().equals("")) { 
day = null;  
 } else { 
day = Integer.valueOf(retValue.get("day").toString()); 
 }
 Integer hour;
if(retValue.get("hour").toString().equals("")) { 
hour = null;  
 } else { 
hour = Integer.valueOf(retValue.get("hour").toString()); 
 }
 Integer minute;
if(retValue.get("minute").toString().equals("")) { 
minute = null;  
 } else { 
minute = Integer.valueOf(retValue.get("minute").toString()); 
 }
 Integer second;
if(retValue.get("second").toString().equals("")) { 
second = null;  
 } else { 
second = Integer.valueOf(retValue.get("second").toString()); 
 }
if(this.eventhandler!=null) {
 this.eventhandler.AnnounceTimeEvent(year,month,day,hour,minute,second ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "TimeCorrectionDomain.TimeManage.SetWaitTime")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetWaitTimeResult( ); 
 } 
}
if (EventURL.equals( "TimeCorrectionDomain.TimeManage.SetSystemTime")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetSystemTimeResult( ); 
 } 
}
if (EventURL.equals( "TimeCorrectionDomain.TimeManage.GetSystemTime")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GetSystemTimeResult( ); 
 } 
}
if (EventURL.equals( "TimeCorrectionDomain.TimeManage.GrobalTimeEdit")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GrobalTimeEditResult( ); 
 } 
}
}
  public void SetWaitTime(java.lang.Integer wait) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetWaitTime"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("wait", wait); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetSystemTime(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.SetSystemTime"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
run.Invoke(url, Params, null, null); 
 } 
   public void GetSystemTime(java.lang.String ClientId) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GetSystemTime"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ClientId", ClientId); 
run.Invoke(url, Params, null, null); 
 } 
   public void GrobalTimeEdit(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer second) 
 { 
  String url = "TimeCorrectionDomain.TimeManage.GrobalTimeEdit"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("second", second); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 