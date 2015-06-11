package Runtime.impl;

import java.util.HashMap;

import Runtime.IRunTime;
import Runtime.ReturnDo;

public class proxyTest implements ReturnDo {
   
	static public class EventHandler {
		//ž÷ÖÖÊÂŒþµÄŒò»¯°æ±Ÿ
		//ÊÂŒþÊÇœáÎ²ŒÓÉÏEvent
		
		public Object CommanderLoginOkEvent(String TerminalId,String CommanderId, boolean IsOnline) {
			 			return null;
		}
		
		
	}
	
	static public class FunResultHandler {
		//ž÷ÖÖ·œ·šµÄŒò»¯°æ 
		//·œ·šÊÇœáÎ²ŒÓÉÏResult

	}
	
	public EventHandler eventhandler;
	public FunResultHandler funhandler;
		
	public void setEventhand(EventHandler eventhandler) {
		this.eventhandler = eventhandler;
	}

	public void setFunhand(FunResultHandler funhandler) {
		this.funhandler = funhandler;
	}
	
	private IRunTime run;
   
    
    private proxyTest(IRunTime run)
    {  
        run = run;
        //Í¬Ê±°ÑŽúÀíÀà×¢²áœøÈ¥Runtime
        ((RunTime)run).registerProxy(this.getClass().getSimpleName(), this);
        
    }

    
    
    
    
    public void ReturnEvent(HashMap<String,Object> retValue,String EventURL)
    {  
    
    	if (EventURL.equals("CommandDomain.CommanderBusiness.SetCommanderLoginOk"))
        {

            String TerminalId = "TerminalId";
            String CommanderId = "CommanderId";
            boolean IsOnline = false;
            
            this.setEventhand(new EventHandler(){public Object CommanderLoginOkEvent(String TerminalId,String CommanderId, boolean IsOnline) {
		 		System.out.println("123123123123123");	
            	return null;}});
            
            
            eventhandler.CommanderLoginOkEvent(TerminalId, CommanderId, IsOnline);
        }
    }
    
    
    
    public void ReturnFunction(HashMap<String,Object> retValue,String EventURL)
    {  
    	
    
    	if (EventURL.equals("CommandDomain.CommanderBusiness.SetCommanderLoginOk"))
        {

            String TerminalId = "TerminalId";
            String CommanderId = "CommanderId";
            boolean IsOnline = false;
            
            this.setEventhand(new EventHandler(){public Object CommanderLoginOkEvent(String TerminalId,String CommanderId, boolean IsOnline) {
		 		System.out.println("123123123123123");	
            	return null;}});
            
            
            eventhandler.CommanderLoginOkEvent(TerminalId, CommanderId, IsOnline);
        }
    	
    	
    	
    }
  
    
    public void SetCommanderLoginOk(String TerminalId, String CommanderId, String TerminalIP, boolean IsOnline)
    {
        String url = "CommandDomain.CommanderBusiness.SetCommanderLoginOk";
        HashMap<String, Object> Params = new HashMap<String, Object> ();
        Params.put("TerminalId", TerminalId);
        Params.put("CommanderId", CommanderId);
        Params.put("TerminalIP", TerminalIP);
        Params.put("IsOnline", IsOnline);
        run.Invoke(url, Params, null, null);
    }
    
    
    
    
}
