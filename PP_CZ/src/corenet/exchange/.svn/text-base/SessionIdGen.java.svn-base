package corenet.exchange;

import corenet.netbase.Interface.*;
import org.misc.*;

//C# TO JAVA CONVERTER TODO TASK: Delegates are not available in Java:
//public delegate void OnExchangeServerMessageEvent(IChannel Channel,IMessage Message);

public class SessionIdGen
{
	private static long SessionId = 0; 

	public static String GetSessionId(IChannel Channel)
	{
		RefObject<Long> tempRef_SessionId = new RefObject<Long>(SessionId);
		/**
		 * by zzw 
		 * 
		 * System.Threading.Interlocked.Increment 用法不明，屏蔽先 
		 * 
		 * **/
		
	
		//long id = System.Threading.Interlocked.Increment(tempRef_SessionId);
		long id = ++tempRef_SessionId.argvalue;
		SessionId = tempRef_SessionId.argvalue;
		return (new Long(id)).toString();		
	}

}
