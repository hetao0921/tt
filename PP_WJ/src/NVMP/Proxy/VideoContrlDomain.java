package NVMP.Proxy;

import java.util.HashMap;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

public class VideoContrlDomain implements ReturnDo {
	static public class EventHandler {
		public Object PlayFailedEvent(java.lang.String DevicId,
				java.lang.Integer CameraIndex, java.lang.Integer type,
				java.lang.String Context, java.lang.String Reason) {
			return null;
		}

		public Object StopStreamFowardEvent(java.lang.String ClientId,
				java.lang.String DeviceId, java.lang.String DeviceIP,
				java.lang.Integer CameraIndex, java.lang.String VideoServerId,
				java.lang.Integer VideoServerChannel, java.lang.Integer type,
				java.lang.String Reason) {
			return null;
		}

		public Object StartStreamFowardEvent(java.lang.String ClientId,
				java.lang.String DeviceId, java.lang.Integer CameraIndex,
				java.lang.String VideoServerIP,
				java.lang.Integer VideoServerChannel, java.lang.String user,
				java.lang.String Password, java.lang.Integer Port,
				java.lang.Integer DeviceType, java.lang.Integer DeviceSubType,
				java.lang.Integer NetLinkType,
				java.lang.Integer NetLinkSubType,
				java.lang.Integer NetLinkMode, java.lang.String Context,
				java.lang.Integer flag) {
			return null;
		}

		public Object ControlDisplayVideoEvent(java.lang.String DeviceId,
				java.lang.Integer CameraIndex, java.lang.String VideoServerIP,
				java.lang.Integer VideoServerChannel, java.lang.String user,
				java.lang.String Password, java.lang.Integer Port,
				java.lang.Integer DeviceType, java.lang.Integer DeviceSubType,
				java.lang.Integer NetLinkType,
				java.lang.Integer NetLinkSubType,
				java.lang.Integer NetLinkMode, java.lang.String Context,
				java.lang.Integer flag) {
			return null;
		}

		public Object onSearchVidoPlayEvent(java.lang.String clientID,
				java.lang.String clientIP, java.lang.String deviceId,
				java.lang.String deviceIP, java.lang.Integer linkedMode,
				java.lang.Integer cameraIndex, java.lang.String sourceIP,
				java.lang.Integer netPort,
				java.lang.Integer videoServerChannel, java.lang.String context) {
			return null;
		}

		public Object onGetALlRouteEvent(java.lang.String type,
				java.lang.String clientid, java.lang.String clientip,
				java.lang.String deviceid, java.lang.String deviceip,
				java.lang.Integer devicechannel, java.lang.String routemap,
				java.lang.Integer lev, java.lang.String sourceip,
				java.lang.Integer sourcechannel, java.lang.String forwardid,
				java.lang.String sourceuuid, java.lang.String realroutemap,
				java.lang.String uuid, java.lang.Integer sendflag,
				java.lang.String sendid, java.lang.String context) {
			return null;
		}

		public Object GetClientPlayEvent(java.lang.String client,
				java.lang.String deviceid, java.lang.Integer channel,
				java.lang.String context) {
			return null;
		}

		public Object StartPreFowardEvent(java.lang.String deviceId,
				java.lang.Integer index, java.lang.String context) {
			return null;
		}

		public Object StopPreFowardEvent(java.lang.String deviceId,
				java.lang.Integer deviceIdChannel, java.lang.String context,
				java.lang.Integer flag) {
			return null;
		}

		public Object refreshPreFowardEvent(java.lang.String deviceid) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object SetClientOnLineResult() {
			return null;
		}

		public Object RequestStopVideoResult() {
			return null;
		}

		public Object GobalVideoLiveResult() {
			return null;
		}

		public Object GobalVideoLiveBackResult() {
			return null;
		}

		public Object getClientPlayResult() {
			return null;
		}

		public Object SetLimitNumResult() {
			return null;
		}

		public Object RequestPlayVideo_bk_testResult() {
			return null;
		}

		public Object RequestPlayVideoResult() {
			return null;
		}

		public Object StreamFowardSuccessResult() {
			return null;
		}

		public Object GrobalRequestPlayVideoResult() {
			return null;
		}

		public Object GrobalRequestStopVideoResult() {
			return null;
		}

		public Object GrobalRequestPlayVideoSuccessResult() {
			return null;
		}

		public Object StreamFowardErrorResult() {
			return null;
		}

		public Object GrobalStreamFowardErrorResult() {
			return null;
		}

		public Object FowardServerLoginResult() {
			return null;
		}

		public Object FowardServerlogoutResult() {
			return null;
		}

		public Object SetEncodeDeviceOnlineResult() {
			return null;
		}

		public Object GrobalBackwardStopVideoResult() {
			return null;
		}

		public Object RequestPlayVideoVer2Result() {
			return null;
		}

		public Object GrobalChangeUserLevResult() {
			return null;
		}

		public Object PreLoginResult() {
			return null;
		}

		public Object PreFowardErrorResult() {
			return null;
		}

		public Object PreFowardSuccessResult() {
			return null;
		}

		public Object refreshPreFowardResult(java.lang.Boolean Result) {
			return null;
		}

		public Object StreamFowardStopResult() {
			return null;
		}

		public Object StreamFowardSyncMessageResult() {
			return null;
		}

		public Object getDeviceFormURLResult(java.lang.String uuid,
				java.lang.String ClientId, java.lang.String DeviceId,
				java.lang.Integer CameraIndex,
				NVMP.VideoControlDomain.PalyRecord pr,
				java.lang.Integer sendFlag, java.lang.String sendId,
				java.lang.Integer fowardFlag, java.lang.String fowardId,
				NVMP.VideoControlDomain.Route centerMap,
				java.lang.Integer userLev) {
			return null;
		}

		public Object getURLFormDeviceResult(java.lang.String uuid,
				java.lang.String devid, java.lang.Integer channel,
				java.lang.String clientId,
				NVMP.VideoControlDomain.Route CenterMap,
				NVMP.VideoControlDomain.Route RealCenterMap,
				java.lang.Integer formFlag, java.lang.String formIP,
				java.lang.Integer formChannel, java.lang.String formFowardid,
				java.lang.Integer Port, java.lang.String user,
				java.lang.String Password, java.lang.Integer DeviceType,
				java.lang.Integer DeviceSubType, java.lang.Integer NetLinkType,
				java.lang.Integer NetLinkSubType,
				java.lang.Integer NetLinkMode, java.lang.Integer lev,
				boolean delfalg, java.lang.String clientIP) {
			return null;
		}
	}

	private IRunTime run;

	public VideoContrlDomain(IRunTime run) {
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
		if (EventURL.equals("VideoContrlDomain.IVideoControl.OnPlayFailed")) {
			String DevicId = retValue.get("DevicId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			Integer type;
			if (retValue.get("type").toString().equals("")) {
				type = null;
			} else {
				type = Integer.valueOf(retValue.get("type").toString());
			}
			String Context = retValue.get("Context").toString();
			String Reason = retValue.get("Reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.PlayFailedEvent(DevicId, CameraIndex, type,
						Context, Reason);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.StopStreamFoward")) {
			String ClientId = retValue.get("ClientId").toString();
			String DeviceId = retValue.get("DeviceId").toString();
			String DeviceIP = retValue.get("DeviceIP").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			String VideoServerId = retValue.get("VideoServerId").toString();
			Integer VideoServerChannel;
			if (retValue.get("VideoServerChannel").toString().equals("")) {
				VideoServerChannel = null;
			} else {
				VideoServerChannel = Integer.valueOf(retValue.get(
						"VideoServerChannel").toString());
			}
			Integer type;
			if (retValue.get("type").toString().equals("")) {
				type = null;
			} else {
				type = Integer.valueOf(retValue.get("type").toString());
			}
			String Reason = retValue.get("Reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.StopStreamFowardEvent(ClientId, DeviceId,
						DeviceIP, CameraIndex, VideoServerId,
						VideoServerChannel, type, Reason);
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.IVideoControl.StartStreamFoward")) {
			String ClientId = retValue.get("ClientId").toString();
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			String VideoServerIP = retValue.get("VideoServerIP").toString();
			Integer VideoServerChannel;
			if (retValue.get("VideoServerChannel").toString().equals("")) {
				VideoServerChannel = null;
			} else {
				VideoServerChannel = Integer.valueOf(retValue.get(
						"VideoServerChannel").toString());
			}
			String user = retValue.get("user").toString();
			String Password = retValue.get("Password").toString();
			Integer Port;
			if (retValue.get("Port").toString().equals("")) {
				Port = null;
			} else {
				Port = Integer.valueOf(retValue.get("Port").toString());
			}
			Integer DeviceType;
			if (retValue.get("DeviceType").toString().equals("")) {
				DeviceType = null;
			} else {
				DeviceType = Integer.valueOf(retValue.get("DeviceType")
						.toString());
			}
			Integer DeviceSubType;
			if (retValue.get("DeviceSubType").toString().equals("")) {
				DeviceSubType = null;
			} else {
				DeviceSubType = Integer.valueOf(retValue.get("DeviceSubType")
						.toString());
			}
			Integer NetLinkType;
			if (retValue.get("NetLinkType").toString().equals("")) {
				NetLinkType = null;
			} else {
				NetLinkType = Integer.valueOf(retValue.get("NetLinkType")
						.toString());
			}
			Integer NetLinkSubType;
			if (retValue.get("NetLinkSubType").toString().equals("")) {
				NetLinkSubType = null;
			} else {
				NetLinkSubType = Integer.valueOf(retValue.get("NetLinkSubType")
						.toString());
			}
			Integer NetLinkMode;
			if (retValue.get("NetLinkMode").toString().equals("")) {
				NetLinkMode = null;
			} else {
				NetLinkMode = Integer.valueOf(retValue.get("NetLinkMode")
						.toString());
			}
			String Context = retValue.get("Context").toString();
			Integer flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Integer.valueOf(retValue.get("flag").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.StartStreamFowardEvent(ClientId, DeviceId,
						CameraIndex, VideoServerIP, VideoServerChannel, user,
						Password, Port, DeviceType, DeviceSubType, NetLinkType,
						NetLinkSubType, NetLinkMode, Context, flag);
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.IVideoControl.OnControlDisplayVideo")) {
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			String VideoServerIP = retValue.get("VideoServerIP").toString();
			Integer VideoServerChannel;
			if (retValue.get("VideoServerChannel").toString().equals("")) {
				VideoServerChannel = null;
			} else {
				VideoServerChannel = Integer.valueOf(retValue.get(
						"VideoServerChannel").toString());
			}
			String user = retValue.get("user").toString();
			String Password = retValue.get("Password").toString();
			Integer Port;
			if (retValue.get("Port").toString().equals("")) {
				Port = null;
			} else {
				Port = Integer.valueOf(retValue.get("Port").toString());
			}
			Integer DeviceType;
			if (retValue.get("DeviceType").toString().equals("")) {
				DeviceType = null;
			} else {
				DeviceType = Integer.valueOf(retValue.get("DeviceType")
						.toString());
			}
			Integer DeviceSubType;
			if (retValue.get("DeviceSubType").toString().equals("")) {
				DeviceSubType = null;
			} else {
				DeviceSubType = Integer.valueOf(retValue.get("DeviceSubType")
						.toString());
			}
			Integer NetLinkType;
			if (retValue.get("NetLinkType").toString().equals("")) {
				NetLinkType = null;
			} else {
				NetLinkType = Integer.valueOf(retValue.get("NetLinkType")
						.toString());
			}
			Integer NetLinkSubType;
			if (retValue.get("NetLinkSubType").toString().equals("")) {
				NetLinkSubType = null;
			} else {
				NetLinkSubType = Integer.valueOf(retValue.get("NetLinkSubType")
						.toString());
			}
			Integer NetLinkMode;
			if (retValue.get("NetLinkMode").toString().equals("")) {
				NetLinkMode = null;
			} else {
				NetLinkMode = Integer.valueOf(retValue.get("NetLinkMode")
						.toString());
			}
			String Context = retValue.get("Context").toString();
			Integer flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Integer.valueOf(retValue.get("flag").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlDisplayVideoEvent(DeviceId,
						CameraIndex, VideoServerIP, VideoServerChannel, user,
						Password, Port, DeviceType, DeviceSubType, NetLinkType,
						NetLinkSubType, NetLinkMode, Context, flag);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.onSearchVidoPlay")) {
			String clientID = retValue.get("clientID").toString();
			String clientIP = retValue.get("clientIP").toString();
			String deviceId = retValue.get("deviceId").toString();
			String deviceIP = retValue.get("deviceIP").toString();
			Integer linkedMode;
			if (retValue.get("linkedMode").toString().equals("")) {
				linkedMode = null;
			} else {
				linkedMode = Integer.valueOf(retValue.get("linkedMode")
						.toString());
			}
			Integer cameraIndex;
			if (retValue.get("cameraIndex").toString().equals("")) {
				cameraIndex = null;
			} else {
				cameraIndex = Integer.valueOf(retValue.get("cameraIndex")
						.toString());
			}
			String sourceIP = retValue.get("sourceIP").toString();
			Integer netPort;
			if (retValue.get("netPort").toString().equals("")) {
				netPort = null;
			} else {
				netPort = Integer.valueOf(retValue.get("netPort").toString());
			}
			Integer videoServerChannel;
			if (retValue.get("videoServerChannel").toString().equals("")) {
				videoServerChannel = null;
			} else {
				videoServerChannel = Integer.valueOf(retValue.get(
						"videoServerChannel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onSearchVidoPlayEvent(clientID, clientIP,
						deviceId, deviceIP, linkedMode, cameraIndex, sourceIP,
						netPort, videoServerChannel, context);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.onGetALlRoute")) {
			String type = retValue.get("type").toString();
			String clientid = retValue.get("clientid").toString();
			String clientip = retValue.get("clientip").toString();
			String deviceid = retValue.get("deviceid").toString();
			String deviceip = retValue.get("deviceip").toString();
			Integer devicechannel;
			if (retValue.get("devicechannel").toString().equals("")) {
				devicechannel = null;
			} else {
				devicechannel = Integer.valueOf(retValue.get("devicechannel")
						.toString());
			}
			String routemap = retValue.get("routemap").toString();
			Integer lev;
			if (retValue.get("lev").toString().equals("")) {
				lev = null;
			} else {
				lev = Integer.valueOf(retValue.get("lev").toString());
			}
			String sourceip = retValue.get("sourceip").toString();
			Integer sourcechannel;
			if (retValue.get("sourcechannel").toString().equals("")) {
				sourcechannel = null;
			} else {
				sourcechannel = Integer.valueOf(retValue.get("sourcechannel")
						.toString());
			}
			String forwardid = retValue.get("forwardid").toString();
			String sourceuuid = retValue.get("sourceuuid").toString();
			String realroutemap = retValue.get("realroutemap").toString();
			String uuid = retValue.get("uuid").toString();
			Integer sendflag;
			if (retValue.get("sendflag").toString().equals("")) {
				sendflag = null;
			} else {
				sendflag = Integer.valueOf(retValue.get("sendflag").toString());
			}
			String sendid = retValue.get("sendid").toString();
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetALlRouteEvent(type, clientid, clientip,
						deviceid, deviceip, devicechannel, routemap, lev,
						sourceip, sourcechannel, forwardid, sourceuuid,
						realroutemap, uuid, sendflag, sendid, context);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.OnGetClientPlay")) {
			String client = retValue.get("client").toString();
			String deviceid = retValue.get("deviceid").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.GetClientPlayEvent(client, deviceid, channel,
						context);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.StartPreFoward")) {
			String deviceId = retValue.get("deviceId").toString();
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.StartPreFowardEvent(deviceId, index, context);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.StopPreFoward")) {
			String deviceId = retValue.get("deviceId").toString();
			Integer deviceIdChannel;
			if (retValue.get("deviceIdChannel").toString().equals("")) {
				deviceIdChannel = null;
			} else {
				deviceIdChannel = Integer.valueOf(retValue.get(
						"deviceIdChannel").toString());
			}
			String context = retValue.get("context").toString();
			Integer flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Integer.valueOf(retValue.get("flag").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.StopPreFowardEvent(deviceId, deviceIdChannel,
						context, flag);
			}
		}
		if (EventURL.equals("VideoContrlDomain.IVideoControl.refreshPreFoward")) {
			String deviceid = retValue.get("deviceid").toString();
			if (this.eventhandler != null) {
				this.eventhandler.refreshPreFowardEvent(deviceid);
			}
		}
	}

	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("VideoContrlDomain.VideoControl.SetClientOnLine")) {
			if (this.funhandler != null) {
				this.funhandler.SetClientOnLineResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.RequestStopVideo")) {
			if (this.funhandler != null) {
				this.funhandler.RequestStopVideoResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.GobalVideoLive")) {
			if (this.funhandler != null) {
				this.funhandler.GobalVideoLiveResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GobalVideoLiveBack")) {
			if (this.funhandler != null) {
				this.funhandler.GobalVideoLiveBackResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.getClientPlay")) {
			if (this.funhandler != null) {
				this.funhandler.getClientPlayResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.SetLimitNum")) {
			if (this.funhandler != null) {
				this.funhandler.SetLimitNumResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.RequestPlayVideo_bk_test")) {
			if (this.funhandler != null) {
				this.funhandler.RequestPlayVideo_bk_testResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.RequestPlayVideo")) {
			if (this.funhandler != null) {
				this.funhandler.RequestPlayVideoResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.StreamFowardSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.StreamFowardSuccessResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalRequestPlayVideo")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalRequestPlayVideoResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalRequestStopVideo")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalRequestStopVideoResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalRequestPlayVideoSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalRequestPlayVideoSuccessResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.StreamFowardError")) {
			if (this.funhandler != null) {
				this.funhandler.StreamFowardErrorResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalStreamFowardError")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalStreamFowardErrorResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.FowardServerLogin")) {
			if (this.funhandler != null) {
				this.funhandler.FowardServerLoginResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.FowardServerlogout")) {
			if (this.funhandler != null) {
				this.funhandler.FowardServerlogoutResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.SetEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.SetEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalBackwardStopVideo")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalBackwardStopVideoResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.RequestPlayVideoVer2")) {
			if (this.funhandler != null) {
				this.funhandler.RequestPlayVideoVer2Result();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.GrobalChangeUserLev")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalChangeUserLevResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.PreLogin")) {
			if (this.funhandler != null) {
				this.funhandler.PreLoginResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.PreFowardError")) {
			if (this.funhandler != null) {
				this.funhandler.PreFowardErrorResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.PreFowardSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.PreFowardSuccessResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.refreshPreFoward")) {
			Boolean Result;
			if (retValue.get("Result").toString().equals("")) {
				Result = null;
			} else {
				Result = Boolean.valueOf(retValue.get("Result").toString());
			}
			if (this.funhandler != null) {
				this.funhandler.refreshPreFowardResult(Result);
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.StreamFowardStop")) {
			if (this.funhandler != null) {
				this.funhandler.StreamFowardStopResult();
			}
		}
		if (EventURL
				.equals("VideoContrlDomain.VideoControl.StreamFowardSyncMessage")) {
			if (this.funhandler != null) {
				this.funhandler.StreamFowardSyncMessageResult();
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.getDeviceFormURL")) {
			String uuid = retValue.get("uuid").toString();
			String ClientId = retValue.get("ClientId").toString();
			String DeviceId = retValue.get("DeviceId").toString();
			Integer CameraIndex;
			if (retValue.get("CameraIndex").toString().equals("")) {
				CameraIndex = null;
			} else {
				CameraIndex = Integer.valueOf(retValue.get("CameraIndex")
						.toString());
			}
			Integer sendFlag;
			if (retValue.get("sendFlag").toString().equals("")) {
				sendFlag = null;
			} else {
				sendFlag = Integer.valueOf(retValue.get("sendFlag").toString());
			}
			String sendId = retValue.get("sendId").toString();
			Integer fowardFlag;
			if (retValue.get("fowardFlag").toString().equals("")) {
				fowardFlag = null;
			} else {
				fowardFlag = Integer.valueOf(retValue.get("fowardFlag")
						.toString());
			}
			String fowardId = retValue.get("fowardId").toString();
			Integer userLev;
			if (retValue.get("userLev").toString().equals("")) {
				userLev = null;
			} else {
				userLev = Integer.valueOf(retValue.get("userLev").toString());
			}
			if (this.funhandler != null) {
				// this.funhandler.getDeviceFormURLResult(uuid,ClientId,DeviceId,CameraIndex,pr,sendFlag,sendId,fowardFlag,fowardId,centerMap,userLev
				// );
			}
		}
		if (EventURL.equals("VideoContrlDomain.VideoControl.getURLFormDevice")) {
			String uuid = retValue.get("uuid").toString();
			String devid = retValue.get("devid").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String clientId = retValue.get("clientId").toString();
			Integer formFlag;
			if (retValue.get("formFlag").toString().equals("")) {
				formFlag = null;
			} else {
				formFlag = Integer.valueOf(retValue.get("formFlag").toString());
			}
			String formIP = retValue.get("formIP").toString();
			Integer formChannel;
			if (retValue.get("formChannel").toString().equals("")) {
				formChannel = null;
			} else {
				formChannel = Integer.valueOf(retValue.get("formChannel")
						.toString());
			}
			String formFowardid = retValue.get("formFowardid").toString();
			Integer Port;
			if (retValue.get("Port").toString().equals("")) {
				Port = null;
			} else {
				Port = Integer.valueOf(retValue.get("Port").toString());
			}
			String user = retValue.get("user").toString();
			String Password = retValue.get("Password").toString();
			Integer DeviceType;
			if (retValue.get("DeviceType").toString().equals("")) {
				DeviceType = null;
			} else {
				DeviceType = Integer.valueOf(retValue.get("DeviceType")
						.toString());
			}
			Integer DeviceSubType;
			if (retValue.get("DeviceSubType").toString().equals("")) {
				DeviceSubType = null;
			} else {
				DeviceSubType = Integer.valueOf(retValue.get("DeviceSubType")
						.toString());
			}
			Integer NetLinkType;
			if (retValue.get("NetLinkType").toString().equals("")) {
				NetLinkType = null;
			} else {
				NetLinkType = Integer.valueOf(retValue.get("NetLinkType")
						.toString());
			}
			Integer NetLinkSubType;
			if (retValue.get("NetLinkSubType").toString().equals("")) {
				NetLinkSubType = null;
			} else {
				NetLinkSubType = Integer.valueOf(retValue.get("NetLinkSubType")
						.toString());
			}
			Integer NetLinkMode;
			if (retValue.get("NetLinkMode").toString().equals("")) {
				NetLinkMode = null;
			} else {
				NetLinkMode = Integer.valueOf(retValue.get("NetLinkMode")
						.toString());
			}
			Integer lev;
			if (retValue.get("lev").toString().equals("")) {
				lev = null;
			} else {
				lev = Integer.valueOf(retValue.get("lev").toString());
			}
			String clientIP = retValue.get("clientIP").toString();
			if (this.funhandler != null) {
				// this.funhandler.getURLFormDeviceResult(uuid,devid,channel,clientId,CenterMap,RealCenterMap,formFlag,formIP,formChannel,formFowardid,Port,user,Password,DeviceType,DeviceSubType,NetLinkType,NetLinkSubType,NetLinkMode,lev,delfalg,clientIP
				// );
			}
		}
	}

	public void SetClientOnLine(java.lang.String ClientId,
			java.lang.Boolean isOnline) {
		String url = "VideoContrlDomain.VideoControl.SetClientOnLine";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("isOnline", isOnline);
		run.Invoke(url, Params, null, null);
	}

	public void RequestStopVideo(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.RequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		run.Invoke(url, Params, null, null);
	}

	public void GobalVideoLive(java.lang.String CenterIDFrom,
			java.lang.String CenterIDTo, java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLive";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		run.Invoke(url, Params, null, null);
	}

	public void GobalVideoLiveBack(java.lang.String CenterIDFrom,
			java.lang.String CenterIDTo, java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLiveBack";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		run.Invoke(url, Params, null, null);
	}

	public void getClientPlay(java.lang.String ClientID) {
		String url = "VideoContrlDomain.VideoControl.getClientPlay";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientID", ClientID);
		run.Invoke(url, Params, null, null);
	}

	public void SetLimitNum(java.lang.Integer num) {
		String url = "VideoContrlDomain.VideoControl.SetLimitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("num", num);
		run.Invoke(url, Params, null, null);
	}

	public void RequestPlayVideo_bk_test(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context, java.lang.Integer NetLinkMode,
			java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideo_bk_test";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("userLev", userLev);
		run.Invoke(url, Params, null, null);
	}

	public void RequestPlayVideo(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context, java.lang.Integer NetLinkMode,
			java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("userLev", userLev);
		run.Invoke(url, Params, null, null);
	}

	public void StreamFowardSuccess(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String FowardId, java.lang.String VideoServerIP,
			java.lang.Integer VideoServerChannel, java.lang.String user,
			java.lang.String Password, java.lang.Integer Port,
			java.lang.Integer DeviceType, java.lang.Integer DeviceSubType,
			java.lang.Integer NetLinkType, java.lang.Integer NetLinkSubType,
			java.lang.Integer NetLinkMode, java.lang.String Context,
			java.lang.Integer flag) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("FowardId", FowardId);
		Params.put("VideoServerIP", VideoServerIP);
		Params.put("VideoServerChannel", VideoServerChannel);
		Params.put("user", user);
		Params.put("Password", Password);
		Params.put("Port", Port);
		Params.put("DeviceType", DeviceType);
		Params.put("DeviceSubType", DeviceSubType);
		Params.put("NetLinkType", NetLinkType);
		Params.put("NetLinkSubType", NetLinkSubType);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("Context", Context);
		Params.put("flag", flag);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalRequestPlayVideo(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context, java.lang.Integer NetLinkMode,
			java.lang.Integer userLev, java.lang.String CenterMap,
			java.lang.String OCenterid, java.lang.String clientIP) {
		String url = "VideoContrlDomain.VideoControl.GrobalRequestPlayVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("userLev", userLev);
		Params.put("CenterMap", CenterMap);
		Params.put("OCenterid", OCenterid);
		Params.put("clientIP", clientIP);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalRequestStopVideo(java.lang.String uuid,
			java.lang.String ocenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalRequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalRequestPlayVideoSuccess(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String Centerid, java.lang.String FowardId,
			java.lang.Integer flag, java.lang.String VideoServerIP,
			java.lang.Integer VideoServerChannel, java.lang.String user,
			java.lang.String Password, java.lang.Integer Port,
			java.lang.Integer DeviceType, java.lang.Integer DeviceSubType,
			java.lang.Integer NetLinkType, java.lang.Integer NetLinkSubType,
			java.lang.Integer NetLinkMode, java.lang.String Context,
			java.lang.String CenterMap, java.lang.String oCenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalRequestPlayVideoSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Centerid", Centerid);
		Params.put("FowardId", FowardId);
		Params.put("flag", flag);
		Params.put("VideoServerIP", VideoServerIP);
		Params.put("VideoServerChannel", VideoServerChannel);
		Params.put("user", user);
		Params.put("Password", Password);
		Params.put("Port", Port);
		Params.put("DeviceType", DeviceType);
		Params.put("DeviceSubType", DeviceSubType);
		Params.put("NetLinkType", NetLinkType);
		Params.put("NetLinkSubType", NetLinkSubType);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("Context", Context);
		Params.put("CenterMap", CenterMap);
		Params.put("oCenterid", oCenterid);
		run.Invoke(url, Params, null, null);
	}

	public void StreamFowardError(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String Reason, java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Reason", Reason);
		Params.put("Context", Context);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalStreamFowardError(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String Reason, java.lang.String Context,
			java.lang.String OCenterid, java.lang.Integer type) {
		String url = "VideoContrlDomain.VideoControl.GrobalStreamFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Reason", Reason);
		Params.put("Context", Context);
		Params.put("OCenterid", OCenterid);
		Params.put("type", type);
		run.Invoke(url, Params, null, null);
	}

	public void FowardServerLogin(java.lang.String FowardServerId,
			java.lang.String FowardServerIP, java.lang.String user,
			java.lang.String Password, java.lang.Integer Port,
			java.lang.Integer ChannelNums) {
		String url = "VideoContrlDomain.VideoControl.FowardServerLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("FowardServerId", FowardServerId);
		Params.put("FowardServerIP", FowardServerIP);
		Params.put("user", user);
		Params.put("Password", Password);
		Params.put("Port", Port);
		Params.put("ChannelNums", ChannelNums);
		run.Invoke(url, Params, null, null);
	}

	public void FowardServerlogout(java.lang.String FowardServerId,
			java.lang.String FowardServerIP) {
		String url = "VideoContrlDomain.VideoControl.FowardServerlogout";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("FowardServerId", FowardServerId);
		Params.put("FowardServerIP", FowardServerIP);
		run.Invoke(url, Params, null, null);
	}

	public void SetEncodeDeviceOnline(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline) {
		String url = "VideoContrlDomain.VideoControl.SetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalBackwardStopVideo(java.lang.String uuid,
			java.lang.String ocenterid, java.lang.Integer state,
			java.lang.Integer errflag, java.lang.String errStr) {
		String url = "VideoContrlDomain.VideoControl.GrobalBackwardStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		Params.put("state", state);
		Params.put("errflag", errflag);
		Params.put("errStr", errStr);
		run.Invoke(url, Params, null, null);
	}

	public void RequestPlayVideoVer2(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context, java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideoVer2";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("userLev", userLev);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalChangeUserLev(java.lang.String uuid,
			java.lang.String CenterMap, java.lang.Integer lev,
			java.lang.Boolean changflag, java.lang.String OCenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalChangeUserLev";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("CenterMap", CenterMap);
		Params.put("lev", lev);
		Params.put("changflag", changflag);
		Params.put("OCenterid", OCenterid);
		run.Invoke(url, Params, null, null);
	}

	public void PreLogin(java.lang.String ServerID, java.lang.Integer nums,
			java.lang.Integer usenums) {
		String url = "VideoContrlDomain.VideoControl.PreLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ServerID", ServerID);
		Params.put("nums", nums);
		Params.put("usenums", usenums);
		run.Invoke(url, Params, null, null);
	}

	public void PreFowardError(java.lang.String DeviceId,
			java.lang.Integer DeviceIdChannel, java.lang.String Context,
			java.lang.String Reason) {
		String url = "VideoContrlDomain.VideoControl.PreFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("DeviceIdChannel", DeviceIdChannel);
		Params.put("Context", Context);
		Params.put("Reason", Reason);
		run.Invoke(url, Params, null, null);
	}

	public void PreFowardSuccess(java.lang.String DeviceId,
			java.lang.Integer DeviceIdChannel, java.lang.String FowardId,
			java.lang.String VideoServerIP,
			java.lang.Integer VideoServerChannel, java.lang.String user,
			java.lang.String Password, java.lang.Integer Port,
			java.lang.Integer DeviceType, java.lang.Integer DeviceSubType,
			java.lang.Integer NetLinkType, java.lang.Integer NetLinkSubType,
			java.lang.Integer NetLinkMode, java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.PreFowardSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("DeviceIdChannel", DeviceIdChannel);
		Params.put("FowardId", FowardId);
		Params.put("VideoServerIP", VideoServerIP);
		Params.put("VideoServerChannel", VideoServerChannel);
		Params.put("user", user);
		Params.put("Password", Password);
		Params.put("Port", Port);
		Params.put("DeviceType", DeviceType);
		Params.put("DeviceSubType", DeviceSubType);
		Params.put("NetLinkType", NetLinkType);
		Params.put("NetLinkSubType", NetLinkSubType);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("Context", Context);
		run.Invoke(url, Params, null, null);
	}

	public void refreshPreFoward(java.lang.String Deviceid) {
		String url = "VideoContrlDomain.VideoControl.refreshPreFoward";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("Deviceid", Deviceid);
		run.Invoke(url, Params, null, null);
	}

	public void StreamFowardStop(java.lang.String fowardid,
			java.lang.String ip, java.lang.Integer channel,
			java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("ip", ip);
		Params.put("channel", channel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void StreamFowardSyncMessage(java.lang.String fowardid,
			java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardSyncMessage";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void getDeviceFormURL(java.lang.String url, java.lang.Integer channel) {
		String urla = "VideoContrlDomain.VideoControl.getDeviceFormURL";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("url", url);
		Params.put("channel", channel);
		run.Invoke(urla, Params, null, null);
	}

	public void getURLFormDevice(java.lang.String deviceid,
			java.lang.Integer channel) {
		String url = "VideoContrlDomain.VideoControl.getURLFormDevice";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("deviceid", deviceid);
		Params.put("channel", channel);
		run.Invoke(url, Params, null, null);
	}
}
