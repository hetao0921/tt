package NVMP.Proxy;

import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;

public class FXStoreDomainProxy {
	private static IAppRuntime _AppRuntime = null;

	public void load(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
	}

	static public IMessage RequestStartRecord(java.lang.String ClientUserId,
			java.lang.String devid, java.lang.Integer channel,
			java.lang.Integer bitRate, java.lang.Integer recordMinute,
			java.lang.String memo) {
		String url = "FXStoreDomain.StoreBusiness.RequestStartRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("devid", devid);
		Params.put("channel", channel);
		Params.put("bitRate", bitRate);
		Params.put("recordMinute", recordMinute);
		Params.put("memo", memo);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestStartRecordSuccess(
			java.lang.String ClientUserId, java.lang.String devid,
			java.lang.Integer channel, java.lang.String memo,
			java.lang.String id, java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RequestStartRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("devid", devid);
		Params.put("channel", channel);
		Params.put("memo", memo);
		Params.put("id", id);
		Params.put("serverid", serverid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestStopRecord(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestStopRecordSuccess(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand, java.lang.Boolean flag,
			java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("flag", flag);
		Params.put("reason", reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestMarkRecord(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand,
			java.lang.String memo) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestMarkRecordSuccess(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand, java.lang.String memo,
			java.lang.Boolean flag, java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		Params.put("flag", flag);
		Params.put("reason", reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RecordServerLogin(java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RecordServerLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("serverid", serverid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage PlanRefurbish(java.lang.String client,
			java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanRefurbish";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("paln", paln);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage PlanStop(java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("paln", paln);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestSearch(java.lang.String clientID,
			java.lang.String uuid, java.lang.String startTime,
			java.lang.String endTime, java.lang.String storeServerID,
			java.lang.String centerID, java.lang.String departID,
			java.lang.String deviceID, java.lang.Integer channelIndex,
			java.lang.Integer searchType, java.lang.Integer storeRecordType,
			java.lang.String recordMark) {
		String url = "FXStoreDomain.StoreBusiness.RequestSearch";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("clientID", clientID);
		Params.put("uuid", uuid);
		Params.put("startTime", startTime);
		Params.put("endTime", endTime);
		Params.put("storeServerID", storeServerID);
		Params.put("centerID", centerID);
		Params.put("departID", departID);
		Params.put("deviceID", deviceID);
		Params.put("channelIndex", channelIndex);
		Params.put("searchType", searchType);
		Params.put("storeRecordType", storeRecordType);
		Params.put("recordMark", recordMark);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestSearchSucess(java.lang.String clientID,
			java.lang.String szRealDeviceIds,
			java.lang.Integer nRealDeviceChannel, java.lang.String searchType,
			java.lang.String sFile, java.lang.String sStartTime,
			java.lang.String sEndTime, java.lang.String vodIp,
			java.lang.Integer vodPort) {
		String url = "FXStoreDomain.StoreBusiness.RequestSearchSucess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("clientID", clientID);
		Params.put("szRealDeviceIds", szRealDeviceIds);
		Params.put("nRealDeviceChannel", nRealDeviceChannel);
		Params.put("searchType", searchType);
		Params.put("sFile", sFile);
		Params.put("sStartTime", sStartTime);
		Params.put("sEndTime", sEndTime);
		Params.put("vodIp", vodIp);
		Params.put("vodPort", vodPort);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return _AppRuntime.Dispatch(Message);
	}

	static public IMessage RequestStartRecord_Copy(
			java.lang.String ClientUserId, java.lang.String devid,
			java.lang.Integer channel, java.lang.Integer bitRate,
			java.lang.Integer recordMinute, java.lang.String memo) {
		String url = "FXStoreDomain.StoreBusiness.RequestStartRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("devid", devid);
		Params.put("channel", channel);
		Params.put("bitRate", bitRate);
		Params.put("recordMinute", recordMinute);
		Params.put("memo", memo);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestStartRecordSuccess_Copy(
			java.lang.String ClientUserId, java.lang.String devid,
			java.lang.Integer channel, java.lang.String memo,
			java.lang.String id, java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RequestStartRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("devid", devid);
		Params.put("channel", channel);
		Params.put("memo", memo);
		Params.put("id", id);
		Params.put("serverid", serverid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestStopRecord_Copy(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestStopRecordSuccess_Copy(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand, java.lang.Boolean flag,
			java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("flag", flag);
		Params.put("reason", reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestMarkRecord_Copy(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand, java.lang.String memo) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestMarkRecordSuccess_Copy(
			java.lang.String ClientUserId, java.lang.String serverid,
			java.lang.String hand, java.lang.String memo,
			java.lang.Boolean flag, java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		Params.put("flag", flag);
		Params.put("reason", reason);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RecordServerLogin_Copy(java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RecordServerLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("serverid", serverid);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage PlanRefurbish_Copy(java.lang.String client,
			java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanRefurbish";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("paln", paln);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage PlanStop_Copy(java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("paln", paln);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestSearch_Copy(java.lang.String clientID,
			java.lang.String uuid, java.lang.String startTime,
			java.lang.String endTime, java.lang.String storeServerID,
			java.lang.String centerID, java.lang.String departID,
			java.lang.String deviceID, java.lang.Integer channelIndex,
			java.lang.Integer searchType, java.lang.Integer storeRecordType,
			java.lang.String recordMark) {
		String url = "FXStoreDomain.StoreBusiness.RequestSearch";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("clientID", clientID);
		Params.put("uuid", uuid);
		Params.put("startTime", startTime);
		Params.put("endTime", endTime);
		Params.put("storeServerID", storeServerID);
		Params.put("centerID", centerID);
		Params.put("departID", departID);
		Params.put("deviceID", deviceID);
		Params.put("channelIndex", channelIndex);
		Params.put("searchType", searchType);
		Params.put("storeRecordType", storeRecordType);
		Params.put("recordMark", recordMark);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}

	static public IMessage RequestSearchSucess_Copy(java.lang.String clientID,
			java.lang.String szRealDeviceIds,
			java.lang.Integer nRealDeviceChannel, java.lang.String searchType,
			java.lang.String sFile, java.lang.String sStartTime,
			java.lang.String sEndTime, java.lang.String vodIp,
			java.lang.Integer vodPort) {
		String url = "FXStoreDomain.StoreBusiness.RequestSearchSucess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("clientID", clientID);
		Params.put("szRealDeviceIds", szRealDeviceIds);
		Params.put("nRealDeviceChannel", nRealDeviceChannel);
		Params.put("searchType", searchType);
		Params.put("sFile", sFile);
		Params.put("sStartTime", sStartTime);
		Params.put("sEndTime", sEndTime);
		Params.put("vodIp", vodIp);
		Params.put("vodPort", vodPort);
		BaseMessage Message = new BaseMessage();
		Message.AddParams(Params);
		Message.SetAction("Event");
		Message.SetObjURL(url);
		return Message;
	}
}