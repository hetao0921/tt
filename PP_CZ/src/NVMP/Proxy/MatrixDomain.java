package NVMP.Proxy;

import java.util.HashMap;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

public class MatrixDomain implements ReturnDo {
	static public class EventHandler {
		public Object SetScreenSplitNumEvent(java.lang.Integer TVIndex,
				java.lang.Integer SplitNum) {
			return null;
		}

		public Object IpMatrixPlayVideoEvent(java.lang.String ClientId,
				java.lang.String IpMatrixId, java.lang.Integer TVIndex,
				java.lang.Integer pos, java.lang.String DeviceId,
				java.lang.Integer CameraIndex, java.lang.Boolean IsStart) {
			return null;
		}

		public Object ResponeIpMatrixPlayVideoEvent(java.lang.String ClientId,
				java.lang.String IpMatrixId, java.lang.Integer TVIndex,
				java.lang.Integer pos, java.lang.String DeviceId,
				java.lang.Integer CameraIndex, java.lang.Boolean IsOK,
				java.lang.String sInfo) {
			return null;
		}

		public Object IpMatrixOnlineEvent(java.lang.String IpMatrixId,
				java.lang.Boolean IsOnline) {
			return null;
		}

		public Object GetMatrixStateEvent(java.lang.String ClientId) {
			return null;
		}

		public Object ScreenInfoEvent(java.lang.String IpMatrixId,
				java.lang.Integer TVIndex, java.lang.Integer SplitNum) {
			return null;
		}

		public Object PalyVideoInfoEvent(java.lang.String IpMatrixId,
				java.lang.Integer TVIndex, java.lang.Integer pos,
				java.lang.Boolean PlayVido, java.lang.String DeviceId,
				java.lang.Integer CameraIndx) {
			return null;
		}

		public Object PsuhIpMatrixPlayVideoEvent(java.lang.String clientId,
				java.lang.String ipMatrixId, java.lang.Integer tVIndex,
				java.lang.Integer pos, java.lang.String deviceId,
				java.lang.Integer cameraIndex, java.lang.Boolean isStart,
				java.lang.String videoIP, java.lang.String user,
				java.lang.String password, java.lang.Integer port,
				java.lang.Integer deviceType, java.lang.Integer deviceSubType,
				java.lang.Integer netLinkType,
				java.lang.Integer netLinkSubType, java.lang.Integer netLinkMode) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object SendMatrixOnlineResult() {
			return null;
		}

		public Object SetScreenSplitNumResult() {
			return null;
		}

		public Object IpMatrixPlayVideoResult() {
			return null;
		}

		public Object ResponeIpMatrixPlayVideoResult() {
			return null;
		}

		public Object GetMatrixStateResult() {
			return null;
		}

		public Object SendScreenInfoResult() {
			return null;
		}

		public Object SendPalyVideoInfoResult() {
			return null;
		}
	}

	private IRunTime run;

	public MatrixDomain(IRunTime run) {
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
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnSetScreenSplitNum")) {
			Integer TVIndex;
			if (retValue.get("TVIndex").toString().equals("")) {
				TVIndex = null;
			} else {
				TVIndex = Integer.valueOf(retValue.get("TVIndex").toString());
			}
			Integer SplitNum;
			if (retValue.get("SplitNum").toString().equals("")) {
				SplitNum = null;
			} else {
				SplitNum = Integer.valueOf(retValue.get("SplitNum").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.SetScreenSplitNumEvent(TVIndex, SplitNum);
			}
		}
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnIpMatrixPlayVideo")) {
			String ClientId = retValue.get("ClientId").toString();
			String IpMatrixId = retValue.get("IpMatrixId").toString();
			Integer TVIndex;
			if (retValue.get("TVIndex").toString().equals("")) {
				TVIndex = null;
			} else {
				TVIndex = Integer.valueOf(retValue.get("TVIndex").toString());
			}
			Integer pos;
			if (retValue.get("pos").toString().equals("")) {
				pos = null;
			} else {
				pos = Integer.valueOf(retValue.get("pos").toString());
			}
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			Boolean IsStart;
			if (retValue.get("IsStart").toString().equals("")) {
				IsStart = null;
			} else {
				IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.IpMatrixPlayVideoEvent(ClientId, IpMatrixId,
						TVIndex, pos, DeviceId, CameraIndex, IsStart);
			}
		}
		if (EventURL
				.equals("MatrixDomain.IMatrixEvent.OnResponeIpMatrixPlayVideo")) {
			String ClientId = retValue.get("ClientId").toString();
			String IpMatrixId = retValue.get("IpMatrixId").toString();
			Integer TVIndex;
			if (retValue.get("TVIndex").toString().equals("")) {
				TVIndex = null;
			} else {
				TVIndex = Integer.valueOf(retValue.get("TVIndex").toString());
			}
			Integer pos;
			if (retValue.get("pos").toString().equals("")) {
				pos = null;
			} else {
				pos = Integer.valueOf(retValue.get("pos").toString());
			}
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			Boolean IsOK;
			if (retValue.get("IsOK").toString().equals("")) {
				IsOK = null;
			} else {
				IsOK = Boolean.valueOf(retValue.get("IsOK").toString());
			}
			String sInfo = retValue.get("sInfo").toString();
			if (this.eventhandler != null) {
				this.eventhandler.ResponeIpMatrixPlayVideoEvent(ClientId,
						IpMatrixId, TVIndex, pos, DeviceId, CameraIndex, IsOK,
						sInfo);
			}
		}
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnIpMatrixOnline")) {
			String IpMatrixId = retValue.get("IpMatrixId").toString();
			Boolean IsOnline;
			if (retValue.get("IsOnline").toString().equals("")) {
				IsOnline = null;
			} else {
				IsOnline = Boolean.valueOf(retValue.get("IsOnline").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.IpMatrixOnlineEvent(IpMatrixId, IsOnline);
			}
		}
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnGetMatrixState")) {
			String ClientId = retValue.get("ClientId").toString();
			if (this.eventhandler != null) {
				this.eventhandler.GetMatrixStateEvent(ClientId);
			}
		}
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnScreenInfo")) {
			String IpMatrixId = retValue.get("IpMatrixId").toString();
			Integer TVIndex;
			if (retValue.get("TVIndex").toString().equals("")) {
				TVIndex = null;
			} else {
				TVIndex = Integer.valueOf(retValue.get("TVIndex").toString());
			}
			Integer SplitNum;
			if (retValue.get("SplitNum").toString().equals("")) {
				SplitNum = null;
			} else {
				SplitNum = Integer.valueOf(retValue.get("SplitNum").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler
						.ScreenInfoEvent(IpMatrixId, TVIndex, SplitNum);
			}
		}
		if (EventURL.equals("MatrixDomain.IMatrixEvent.OnPalyVideoInfo")) {
			String IpMatrixId = retValue.get("IpMatrixId").toString();
			Integer TVIndex;
			if (retValue.get("TVIndex").toString().equals("")) {
				TVIndex = null;
			} else {
				TVIndex = Integer.valueOf(retValue.get("TVIndex").toString());
			}
			Integer pos;
			if (retValue.get("pos").toString().equals("")) {
				pos = null;
			} else {
				pos = Integer.valueOf(retValue.get("pos").toString());
			}
			Boolean PlayVido;
			if (retValue.get("PlayVido").toString().equals("")) {
				PlayVido = null;
			} else {
				PlayVido = Boolean.valueOf(retValue.get("PlayVido").toString());
			}
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndx;
			if (retValue.get("CameraIndx").toString().equals("")) {
				CameraIndx = null;
			} else {
				CameraIndx = Integer.valueOf(retValue.get("CameraIndx")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.PalyVideoInfoEvent(IpMatrixId, TVIndex, pos,
						PlayVido, DeviceId, CameraIndx);
			}
		}
		if (EventURL
				.equals("MatrixDomain.IMatrixEvent.OnPsuhIpMatrixPlayVideo")) {
			String clientId = retValue.get("clientId").toString();
			String ipMatrixId = retValue.get("ipMatrixId").toString();
			Integer tVIndex;
			if (retValue.get("tVIndex").toString().equals("")) {
				tVIndex = null;
			} else {
				tVIndex = Integer.valueOf(retValue.get("tVIndex").toString());
			}
			Integer pos;
			if (retValue.get("pos").toString().equals("")) {
				pos = null;
			} else {
				pos = Integer.valueOf(retValue.get("pos").toString());
			}
			String deviceId = retValue.get("deviceId").toString();
			Integer cameraIndex;
			if (retValue.get("cameraIndex").toString().equals("")) {
				cameraIndex = null;
			} else {
				cameraIndex = Integer.valueOf(retValue.get("cameraIndex")
						.toString());
			}
			Boolean isStart;
			if (retValue.get("isStart").toString().equals("")) {
				isStart = null;
			} else {
				isStart = Boolean.valueOf(retValue.get("isStart").toString());
			}
			String videoIP = retValue.get("videoIP").toString();
			String user = retValue.get("user").toString();
			String password = retValue.get("password").toString();
			Integer port;
			if (retValue.get("port").toString().equals("")) {
				port = null;
			} else {
				port = Integer.valueOf(retValue.get("port").toString());
			}
			Integer deviceType;
			if (retValue.get("deviceType").toString().equals("")) {
				deviceType = null;
			} else {
				deviceType = Integer.valueOf(retValue.get("deviceType")
						.toString());
			}
			Integer deviceSubType;
			if (retValue.get("deviceSubType").toString().equals("")) {
				deviceSubType = null;
			} else {
				deviceSubType = Integer.valueOf(retValue.get("deviceSubType")
						.toString());
			}
			Integer netLinkType;
			if (retValue.get("netLinkType").toString().equals("")) {
				netLinkType = null;
			} else {
				netLinkType = Integer.valueOf(retValue.get("netLinkType")
						.toString());
			}
			Integer netLinkSubType;
			if (retValue.get("netLinkSubType").toString().equals("")) {
				netLinkSubType = null;
			} else {
				netLinkSubType = Integer.valueOf(retValue.get("netLinkSubType")
						.toString());
			}
			Integer netLinkMode;
			if (retValue.get("netLinkMode").toString().equals("")) {
				netLinkMode = null;
			} else {
				netLinkMode = Integer.valueOf(retValue.get("netLinkMode")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler
						.PsuhIpMatrixPlayVideoEvent(clientId, ipMatrixId,
								tVIndex, pos, deviceId, cameraIndex, isStart,
								videoIP, user, password, port, deviceType,
								deviceSubType, netLinkType, netLinkSubType,
								netLinkMode);
			}
		}
	}

	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.SendMatrixOnline")) {
			if (this.funhandler != null) {
				this.funhandler.SendMatrixOnlineResult();
			}
		}
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.SetScreenSplitNum")) {
			if (this.funhandler != null) {
				this.funhandler.SetScreenSplitNumResult();
			}
		}
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.IpMatrixPlayVideo")) {
			if (this.funhandler != null) {
				this.funhandler.IpMatrixPlayVideoResult();
			}
		}
		if (EventURL
				.equals("MatrixDomain.IpMatrixBusiness.ResponeIpMatrixPlayVideo")) {
			if (this.funhandler != null) {
				this.funhandler.ResponeIpMatrixPlayVideoResult();
			}
		}
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.GetMatrixState")) {
			if (this.funhandler != null) {
				this.funhandler.GetMatrixStateResult();
			}
		}
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.SendScreenInfo")) {
			if (this.funhandler != null) {
				this.funhandler.SendScreenInfoResult();
			}
		}
		if (EventURL.equals("MatrixDomain.IpMatrixBusiness.SendPalyVideoInfo")) {
			if (this.funhandler != null) {
				this.funhandler.SendPalyVideoInfoResult();
			}
		}
	}

	public void SendMatrixOnline(java.lang.String MatrixId,
			java.lang.Boolean IsOnline) {
		String url = "MatrixDomain.IpMatrixBusiness.SendMatrixOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("MatrixId", MatrixId);
		Params.put("IsOnline", IsOnline);
		run.Invoke(url, Params, null, null);
	}

	public void SetScreenSplitNum(java.lang.String IpMatrixId,
			java.lang.Integer TVIndex, java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SetScreenSplitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		run.Invoke(url, Params, null, null);
	}

	public void IpMatrixPlayVideo(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer pos, java.lang.String DeviceId,
			java.lang.Integer CameraIndex, java.lang.Boolean IsStart) {
		String url = "MatrixDomain.IpMatrixBusiness.IpMatrixPlayVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("pos", pos);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("IsStart", IsStart);
		run.Invoke(url, Params, null, null);
	}

	public void ResponeIpMatrixPlayVideo(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer pos, java.lang.String DeviceId,
			java.lang.Integer CameraIndex, java.lang.Boolean IsOK,
			java.lang.String sInfo) {
		String url = "MatrixDomain.IpMatrixBusiness.ResponeIpMatrixPlayVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("pos", pos);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("IsOK", IsOK);
		Params.put("sInfo", sInfo);
		run.Invoke(url, Params, null, null);
	}

	public void GetMatrixState(java.lang.String ClientId,
			java.lang.String IpMatrixId) {
		String url = "MatrixDomain.IpMatrixBusiness.GetMatrixState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		run.Invoke(url, Params, null, null);
	}

	public void SendScreenInfo(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SendScreenInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		run.Invoke(url, Params, null, null);
	}

	public void SendPalyVideoInfo(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer pos, java.lang.Boolean PlayVido,
			java.lang.String DeviceId, java.lang.Integer CameraIndx) {
		String url = "MatrixDomain.IpMatrixBusiness.SendPalyVideoInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("pos", pos);
		Params.put("PlayVido", PlayVido);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndx", CameraIndx);
		run.Invoke(url, Params, null, null);
	}
}
