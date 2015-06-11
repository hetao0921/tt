package NVMP.Proxy;

import java.util.HashMap;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

public class SystemMessageDomain implements ReturnDo {
	static public class EventHandler {
		public Object SendMessageEvent(java.lang.Integer flag,
				java.lang.String message) {
			return null;
		}

		public Object SendCenterIDEvent(java.lang.String centerSessiodId,
				java.lang.String devSessionId) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object GetCenterIDResult() {
			return null;
		}
	}

	private IRunTime run;

	public SystemMessageDomain(IRunTime run) {
		this.run = run;
		((RunTime) run).registerProxy(this.getClass().getSimpleName(), this);
	}

	public EventHandler eventhandler;
	public FunResultHandler funhandler;

	public void setEventhand(EventHandler eventhandler) {
		this.eventhandler = eventhandler;
	}

	public void setFunhand(FunResultHandler funhandler) {
		this.funhandler = funhandler;
	}

	public void ReturnEvent(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("SystemMessageDomain.IMessageManage.OnSendMessage")) {
			Integer flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Integer.valueOf(retValue.get("flag").toString());
			}
			String message = retValue.get("message").toString();
			if (this.eventhandler != null) {
				this.eventhandler.SendMessageEvent(flag, message);
			}
		}
		if (EventURL.equals("SystemMessageDomain.IMessageManage.SendCenterID")) {
			String centerSessiodId = retValue.get("centerSessiodId").toString();
			String devSessionId = retValue.get("devSessionId").toString();
			if (this.eventhandler != null) {
				this.eventhandler.SendCenterIDEvent(centerSessiodId,
						devSessionId);
			}
		}
	}

	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("SystemMessageDomain.MessageManage.GetCenterID")) {
			if (this.funhandler != null) {
				this.funhandler.GetCenterIDResult();
			}
		}
	}

	public void GetCenterID(java.lang.String userid) {
		String url = "SystemMessageDomain.MessageManage.GetCenterID";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("userid", userid);
		run.Invoke(url, Params, null, null);
	}
}
