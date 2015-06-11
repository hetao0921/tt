package NVMP.Proxy;

import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;

public class VideoContrlDomainProxy {
	private static IAppRuntime _AppRuntime = null;

	public void load(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
	}

	static public IMessage SetClientOnLine(java.lang.String ClientId,
			java.lang.Boolean isOnline) {
		String url = "VideoContrlDomain.VideoControl.SetClientOnLine";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("isOnline", isOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestStopVideo(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.RequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GobalVideoLive(java.lang.String CenterIDFrom,
			java.lang.String CenterIDTo, java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLive";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GobalVideoLiveBack(java.lang.String CenterIDFrom,
			java.lang.String CenterIDTo, java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLiveBack";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage getClientPlay(java.lang.String ClientID) {
		String url = "VideoContrlDomain.VideoControl.getClientPlay";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientID", ClientID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SetLimitNum(java.lang.Integer num) {
		String url = "VideoContrlDomain.VideoControl.SetLimitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("num", num);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestPlayVideo_bk_test(
			java.lang.String ClientUserId, java.lang.String DeviceId,
			java.lang.Integer Index, java.lang.String Context,
			java.lang.Integer NetLinkMode, java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideo_bk_test";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("userLev", userLev);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestPlayVideo(java.lang.String ClientUserId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage StreamFowardSuccess(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalRequestPlayVideo(
			java.lang.String ClientUserId, java.lang.String DeviceId,
			java.lang.Integer Index, java.lang.String Context,
			java.lang.Integer NetLinkMode, java.lang.Integer userLev,
			java.lang.String CenterMap, java.lang.String OCenterid,
			java.lang.String clientIP) {
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalRequestStopVideo(java.lang.String uuid,
			java.lang.String ocenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalRequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalRequestPlayVideoSuccess(
			java.lang.String ClientId, java.lang.String DeviceId,
			java.lang.Integer CameraIndex, java.lang.String Centerid,
			java.lang.String FowardId, java.lang.Integer flag,
			java.lang.String VideoServerIP,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage StreamFowardError(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String Reason, java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Reason", Reason);
		Params.put("Context", Context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalStreamFowardError(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage FowardServerLogin(java.lang.String FowardServerId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage FowardServerlogout(java.lang.String FowardServerId,
			java.lang.String FowardServerIP) {
		String url = "VideoContrlDomain.VideoControl.FowardServerlogout";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("FowardServerId", FowardServerId);
		Params.put("FowardServerIP", FowardServerIP);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SetEncodeDeviceOnline(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline) {
		String url = "VideoContrlDomain.VideoControl.SetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalBackwardStopVideo(java.lang.String uuid,
			java.lang.String ocenterid, java.lang.Integer state,
			java.lang.Integer errflag, java.lang.String errStr) {
		String url = "VideoContrlDomain.VideoControl.GrobalBackwardStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		Params.put("state", state);
		Params.put("errflag", errflag);
		Params.put("errStr", errStr);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestPlayVideoVer2(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context, java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideoVer2";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("userLev", userLev);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GrobalChangeUserLev(java.lang.String uuid,
			java.lang.String CenterMap, java.lang.Integer lev,
			java.lang.Boolean changflag, java.lang.String OCenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalChangeUserLev";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("CenterMap", CenterMap);
		Params.put("lev", lev);
		Params.put("changflag", changflag);
		Params.put("OCenterid", OCenterid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage PreLogin(java.lang.String ServerID,
			java.lang.Integer nums, java.lang.Integer usenums) {
		String url = "VideoContrlDomain.VideoControl.PreLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ServerID", ServerID);
		Params.put("nums", nums);
		Params.put("usenums", usenums);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage PreFowardError(java.lang.String DeviceId,
			java.lang.Integer DeviceIdChannel, java.lang.String Context,
			java.lang.String Reason) {
		String url = "VideoContrlDomain.VideoControl.PreFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("DeviceIdChannel", DeviceIdChannel);
		Params.put("Context", Context);
		Params.put("Reason", Reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage PreFowardSuccess(java.lang.String DeviceId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage refreshPreFoward(java.lang.String Deviceid) {
		String url = "VideoContrlDomain.VideoControl.refreshPreFoward";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("Deviceid", Deviceid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage StreamFowardStop(java.lang.String fowardid,
			java.lang.String ip, java.lang.Integer channel,
			java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("ip", ip);
		Params.put("channel", channel);
		Params.put("context", context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage StreamFowardSyncMessage(java.lang.String fowardid,
			java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardSyncMessage";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("context", context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage getDeviceFormURL(java.lang.String url,
			java.lang.Integer channel) {
		String urla = "VideoContrlDomain.VideoControl.getDeviceFormURL";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("url", url);
		Params.put("channel", channel);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(urla);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage getURLFormDevice(java.lang.String deviceid,
			java.lang.Integer channel) {
		String url = "VideoContrlDomain.VideoControl.getURLFormDevice";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("deviceid", deviceid);
		Params.put("channel", channel);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SetClientOnLine_Copy(java.lang.String ClientId,
			java.lang.Boolean isOnline) {
		String url = "VideoContrlDomain.VideoControl.SetClientOnLine";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("isOnline", isOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestStopVideo_Copy(java.lang.String ClientUserId,
			java.lang.String DeviceId, java.lang.Integer Index,
			java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.RequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GobalVideoLive_Copy(java.lang.String CenterIDFrom,
			java.lang.String CenterIDTo, java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLive";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GobalVideoLiveBack_Copy(
			java.lang.String CenterIDFrom, java.lang.String CenterIDTo,
			java.lang.String UUID) {
		String url = "VideoContrlDomain.VideoControl.GobalVideoLiveBack";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("CenterIDFrom", CenterIDFrom);
		Params.put("CenterIDTo", CenterIDTo);
		Params.put("UUID", UUID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage getClientPlay_Copy(java.lang.String ClientID) {
		String url = "VideoContrlDomain.VideoControl.getClientPlay";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientID", ClientID);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage SetLimitNum_Copy(java.lang.Integer num) {
		String url = "VideoContrlDomain.VideoControl.SetLimitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("num", num);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestPlayVideo_bk_test_Copy(
			java.lang.String ClientUserId, java.lang.String DeviceId,
			java.lang.Integer Index, java.lang.String Context,
			java.lang.Integer NetLinkMode, java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideo_bk_test";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("NetLinkMode", NetLinkMode);
		Params.put("userLev", userLev);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestPlayVideo_Copy(java.lang.String ClientUserId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage StreamFowardSuccess_Copy(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalRequestPlayVideo_Copy(
			java.lang.String ClientUserId, java.lang.String DeviceId,
			java.lang.Integer Index, java.lang.String Context,
			java.lang.Integer NetLinkMode, java.lang.Integer userLev,
			java.lang.String CenterMap, java.lang.String OCenterid,
			java.lang.String clientIP) {
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalRequestStopVideo_Copy(java.lang.String uuid,
			java.lang.String ocenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalRequestStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalRequestPlayVideoSuccess_Copy(
			java.lang.String ClientId, java.lang.String DeviceId,
			java.lang.Integer CameraIndex, java.lang.String Centerid,
			java.lang.String FowardId, java.lang.Integer flag,
			java.lang.String VideoServerIP,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage StreamFowardError_Copy(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.String Reason, java.lang.String Context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Reason", Reason);
		Params.put("Context", Context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalStreamFowardError_Copy(
			java.lang.String ClientId, java.lang.String DeviceId,
			java.lang.Integer CameraIndex, java.lang.String Reason,
			java.lang.String Context, java.lang.String OCenterid,
			java.lang.Integer type) {
		String url = "VideoContrlDomain.VideoControl.GrobalStreamFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("CameraIndex", CameraIndex);
		Params.put("Reason", Reason);
		Params.put("Context", Context);
		Params.put("OCenterid", OCenterid);
		Params.put("type", type);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage FowardServerLogin_Copy(
			java.lang.String FowardServerId, java.lang.String FowardServerIP,
			java.lang.String user, java.lang.String Password,
			java.lang.Integer Port, java.lang.Integer ChannelNums) {
		String url = "VideoContrlDomain.VideoControl.FowardServerLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("FowardServerId", FowardServerId);
		Params.put("FowardServerIP", FowardServerIP);
		Params.put("user", user);
		Params.put("Password", Password);
		Params.put("Port", Port);
		Params.put("ChannelNums", ChannelNums);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage FowardServerlogout_Copy(
			java.lang.String FowardServerId, java.lang.String FowardServerIP) {
		String url = "VideoContrlDomain.VideoControl.FowardServerlogout";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("FowardServerId", FowardServerId);
		Params.put("FowardServerIP", FowardServerIP);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage SetEncodeDeviceOnline_Copy(
			java.lang.String TerminalId, java.lang.String TerminalIP,
			java.lang.Boolean IsOnline) {
		String url = "VideoContrlDomain.VideoControl.SetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalBackwardStopVideo_Copy(java.lang.String uuid,
			java.lang.String ocenterid, java.lang.Integer state,
			java.lang.Integer errflag, java.lang.String errStr) {
		String url = "VideoContrlDomain.VideoControl.GrobalBackwardStopVideo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("ocenterid", ocenterid);
		Params.put("state", state);
		Params.put("errflag", errflag);
		Params.put("errStr", errStr);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestPlayVideoVer2_Copy(
			java.lang.String ClientUserId, java.lang.String DeviceId,
			java.lang.Integer Index, java.lang.String Context,
			java.lang.Integer userLev) {
		String url = "VideoContrlDomain.VideoControl.RequestPlayVideoVer2";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("DeviceId", DeviceId);
		Params.put("Index", Index);
		Params.put("Context", Context);
		Params.put("userLev", userLev);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GrobalChangeUserLev_Copy(java.lang.String uuid,
			java.lang.String CenterMap, java.lang.Integer lev,
			java.lang.Boolean changflag, java.lang.String OCenterid) {
		String url = "VideoContrlDomain.VideoControl.GrobalChangeUserLev";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("uuid", uuid);
		Params.put("CenterMap", CenterMap);
		Params.put("lev", lev);
		Params.put("changflag", changflag);
		Params.put("OCenterid", OCenterid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage PreLogin_Copy(java.lang.String ServerID,
			java.lang.Integer nums, java.lang.Integer usenums) {
		String url = "VideoContrlDomain.VideoControl.PreLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ServerID", ServerID);
		Params.put("nums", nums);
		Params.put("usenums", usenums);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage PreFowardError_Copy(java.lang.String DeviceId,
			java.lang.Integer DeviceIdChannel, java.lang.String Context,
			java.lang.String Reason) {
		String url = "VideoContrlDomain.VideoControl.PreFowardError";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("DeviceIdChannel", DeviceIdChannel);
		Params.put("Context", Context);
		Params.put("Reason", Reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage PreFowardSuccess_Copy(java.lang.String DeviceId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage refreshPreFoward_Copy(java.lang.String Deviceid) {
		String url = "VideoContrlDomain.VideoControl.refreshPreFoward";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("Deviceid", Deviceid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage StreamFowardStop_Copy(java.lang.String fowardid,
			java.lang.String ip, java.lang.Integer channel,
			java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("ip", ip);
		Params.put("channel", channel);
		Params.put("context", context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage StreamFowardSyncMessage_Copy(
			java.lang.String fowardid, java.lang.String context) {
		String url = "VideoContrlDomain.VideoControl.StreamFowardSyncMessage";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("fowardid", fowardid);
		Params.put("context", context);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage getDeviceFormURL_Copy(java.lang.String url,
			java.lang.Integer channel) {
		String urla = "VideoContrlDomain.VideoControl.getDeviceFormURL";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("url", url);
		Params.put("channel", channel);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(urla);
		return Message;
	}

	static public IMessage getURLFormDevice_Copy(java.lang.String deviceid,
			java.lang.Integer channel) {
		String url = "VideoContrlDomain.VideoControl.getURLFormDevice";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("deviceid", deviceid);
		Params.put("channel", channel);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}
}