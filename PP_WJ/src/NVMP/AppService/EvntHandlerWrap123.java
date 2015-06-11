package NVMP.AppService;

import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.Hashtable;

public class EvntHandlerWrap123 {
	public Object o;
	public Object Context;
	public Method Method;

	public void EventHandler(java.lang.String TerminalId,
			java.lang.String CommanderId, java.lang.Boolean IsOnline) {
		try {
			Dictionary<String, Object> EventParams = new Hashtable<String, Object>();
			EventParams.put("TerminalId", TerminalId);
			EventParams.put("CommanderId", CommanderId);
			EventParams.put("IsOnline", IsOnline);
			Object[] HandlerParams = { EventParams, Context };
			Method.invoke(o, HandlerParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SetCaller(Method Method, Object o, Object Context) {
		this.Method = Method;
		this.o = o;
		this.Context = Context;
	}
}
