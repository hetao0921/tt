package NVMP.Proxy;

import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;

public class MatrixDomainProxy {
	private static IAppRuntime _AppRuntime = null;

	public void load(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
	}

	static public IMessage SendMatrixOnline(java.lang.String MatrixId,
			java.lang.Boolean IsOnline) {
		String url = "MatrixDomain.IpMatrixBusiness.SendMatrixOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("MatrixId", MatrixId);
		Params.put("IsOnline", IsOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SetScreenSplitNum(java.lang.String IpMatrixId,
			java.lang.Integer TVIndex, java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SetScreenSplitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage IpMatrixPlayVideo(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage ResponeIpMatrixPlayVideo(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage GetMatrixState(java.lang.String ClientId,
			java.lang.String IpMatrixId) {
		String url = "MatrixDomain.IpMatrixBusiness.GetMatrixState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SendScreenInfo(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SendScreenInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SendPalyVideoInfo(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage SendMatrixOnline_Copy(java.lang.String MatrixId,
			java.lang.Boolean IsOnline) {
		String url = "MatrixDomain.IpMatrixBusiness.SendMatrixOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("MatrixId", MatrixId);
		Params.put("IsOnline", IsOnline);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage SetScreenSplitNum_Copy(java.lang.String IpMatrixId,
			java.lang.Integer TVIndex, java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SetScreenSplitNum";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage IpMatrixPlayVideo_Copy(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage ResponeIpMatrixPlayVideo_Copy(
			java.lang.String ClientId, java.lang.String IpMatrixId,
			java.lang.Integer TVIndex, java.lang.Integer pos,
			java.lang.String DeviceId, java.lang.Integer CameraIndex,
			java.lang.Boolean IsOK, java.lang.String sInfo) {
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage GetMatrixState_Copy(java.lang.String ClientId,
			java.lang.String IpMatrixId) {
		String url = "MatrixDomain.IpMatrixBusiness.GetMatrixState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage SendScreenInfo_Copy(java.lang.String ClientId,
			java.lang.String IpMatrixId, java.lang.Integer TVIndex,
			java.lang.Integer SplitNum) {
		String url = "MatrixDomain.IpMatrixBusiness.SendScreenInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("IpMatrixId", IpMatrixId);
		Params.put("TVIndex", TVIndex);
		Params.put("SplitNum", SplitNum);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage SendPalyVideoInfo_Copy(java.lang.String ClientId,
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
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}
}