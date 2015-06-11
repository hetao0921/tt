package NVMP.Proxy;

import java.util.HashMap;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

/**
 * <dt><b>存储服务实现的代理类。</b></dt>
 * <p>
 * </p>
 * <dt>此类提供 Runtime 下
 * 接口的默认实现。定义了一些标准行为，比如setEventhand和setFunhand方法。开发人员只需编写其嵌套类的实现类即可。</dt> <dt>
 * 存储服务包括:临时录像、计划录像。</dt> <dt>其中操作包括：开始录像、停止录像、打标、查询、刷新录像计划</dt>
 * <p>
 * </p>
 * <dt>此类同时提供给客户端和存储服务器操作。</dt>
 * */
public class FXStoreDomain implements ReturnDo {
	static public class EventHandler {
		public Object RecordFailedEvent(java.lang.String devid,
				java.lang.Integer channel, java.lang.String memo,
				java.lang.String reason) {
			return null;
		}

		public Object RecordSuccessEvent(java.lang.String id,
				java.lang.String serverid, java.lang.String devid,
				java.lang.Integer channel, java.lang.String memo) {
			return null;
		}

		public Object RequestStartRecordEvent(java.lang.String clientUserId,
				java.lang.String devid, java.lang.Integer channel,
				java.lang.Integer bitRate, java.lang.Integer recordMinute,
				java.lang.String memo) {
			return null;
		}

		public Object MarkRecordFailedEvent(java.lang.String id,
				java.lang.String serverid, java.lang.String memo,
				java.lang.String reason) {
			return null;
		}

		public Object MarkRecordSuccessEvent(java.lang.String id,
				java.lang.String serverid, java.lang.String memo) {
			return null;
		}

		public Object RequestMarkRecordEvent(java.lang.String clientUserId,
				java.lang.String serverid, java.lang.String hand,
				java.lang.String memo) {
			return null;
		}

		public Object RequestStopRecordEvent(java.lang.String clientUserId,
				java.lang.String serverid, java.lang.String hand) {
			return null;
		}

		public Object StopRecordSuccessEvent(java.lang.String serverid,
				java.lang.String hand, java.lang.Boolean flag,
				java.lang.String reason) {
			return null;
		}

		public Object PlanRefurbishEvent(java.lang.String paln) {
			return null;
		}

		public Object PlanStopEvent(java.lang.String paln) {
			return null;
		}

		public Object RefurbishEvent(java.lang.String reason) {
			return null;
		}

		public Object onRequestSearchEvent(java.lang.String clientID,
				java.lang.String uuid, java.lang.String startTime,
				java.lang.String endTime, java.lang.String storeServerID,
				java.lang.String centerID, java.lang.String departID,
				java.lang.String deviceID, java.lang.Integer channelIndex,
				java.lang.Integer searchType,
				java.lang.Integer storeRecordType, java.lang.String recordMark) {
			return null;
		}

		public Object onSearchSucessEvent(java.lang.String devName,
				java.lang.String channelName, java.lang.String searchType,
				java.lang.String sFile, java.lang.String sStartTime,
				java.lang.String sEndTime, java.lang.String vodIp,
				java.lang.Integer vodPort) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object RequestStartRecordResult() {
			return null;
		}

		public Object RequestStartRecordSuccessResult() {
			return null;
		}

		public Object RequestStopRecordResult() {
			return null;
		}

		public Object RequestStopRecordSuccessResult() {
			return null;
		}

		public Object RequestMarkRecordResult() {
			return null;
		}

		public Object RequestMarkRecordSuccessResult() {
			return null;
		}

		public Object RecordServerLoginResult() {
			return null;
		}

		public Object PlanRefurbishResult() {
			return null;
		}

		public Object PlanStopResult() {
			return null;
		}

		public Object RequestSearchResult() {
			return null;
		}

		public Object RequestSearchSucessResult() {
			return null;
		}
	}

	/**
	 * 运行时上下文环境
	 * */
	private IRunTime run;

	/**
	 * 创建一个FXStoreDomain对象
	 * 
	 * @param run
	 *            运行环境
	 * */
	public FXStoreDomain(IRunTime run) {
		this.run = run;
		((RunTime) run).registerProxy(this.getClass().getSimpleName(), this);
	}

	/**
	 * 策略模式的具体实现。提供事件处理机制。
	 * */
	public EventHandler eventhandler;
	/**
	 * 策略模式的具体实现。提供方法结果处理机制。
	 * */
	public FunResultHandler funhandler;

	public void setEventhand(EventHandler eventhandler) {
		this.eventhandler = eventhandler;
	}

	public void setFunhand(FunResultHandler funhandler) {
		this.funhandler = funhandler;
	}

	/**
	 * 策略模式的实现，提供事件回调处理方案。
	 * */
	public void ReturnEvent(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRecordFailed")) {
			String devid = retValue.get("devid").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String memo = retValue.get("memo").toString();
			String reason = retValue.get("reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RecordFailedEvent(devid, channel, memo,
						reason);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRecordSuccess")) {
			String id = retValue.get("id").toString();
			String serverid = retValue.get("serverid").toString();
			String devid = retValue.get("devid").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String memo = retValue.get("memo").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RecordSuccessEvent(id, serverid, devid,
						channel, memo);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRequestStartRecord")) {
			String clientUserId = retValue.get("clientUserId").toString();
			String devid = retValue.get("devid").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			Integer bitRate;
			if (retValue.get("bitRate").toString().equals("")) {
				bitRate = null;
			} else {
				bitRate = Integer.valueOf(retValue.get("bitRate").toString());
			}
			Integer recordMinute;
			if (retValue.get("recordMinute").toString().equals("")) {
				recordMinute = null;
			} else {
				recordMinute = Integer.valueOf(retValue.get("recordMinute")
						.toString());
			}
			String memo = retValue.get("memo").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RequestStartRecordEvent(clientUserId, devid,
						channel, bitRate, recordMinute, memo);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnMarkRecordFailed")) {
			String id = retValue.get("id").toString();
			String serverid = retValue.get("serverid").toString();
			String memo = retValue.get("memo").toString();
			String reason = retValue.get("reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.MarkRecordFailedEvent(id, serverid, memo,
						reason);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnMarkRecordSuccess")) {
			String id = retValue.get("id").toString();
			String serverid = retValue.get("serverid").toString();
			String memo = retValue.get("memo").toString();
			if (this.eventhandler != null) {
				this.eventhandler.MarkRecordSuccessEvent(id, serverid, memo);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRequestMarkRecord")) {
			String clientUserId = retValue.get("clientUserId").toString();
			String serverid = retValue.get("serverid").toString();
			String hand = retValue.get("hand").toString();
			String memo = retValue.get("memo").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RequestMarkRecordEvent(clientUserId,
						serverid, hand, memo);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRequestStopRecord")) {
			String clientUserId = retValue.get("clientUserId").toString();
			String serverid = retValue.get("serverid").toString();
			String hand = retValue.get("hand").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RequestStopRecordEvent(clientUserId,
						serverid, hand);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnStopRecordSuccess")) {
			String serverid = retValue.get("serverid").toString();
			String hand = retValue.get("hand").toString();
			Boolean flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Boolean.valueOf(retValue.get("flag").toString());
			}
			String reason = retValue.get("reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.StopRecordSuccessEvent(serverid, hand, flag,
						reason);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnPlanRefurbish")) {
			String paln = retValue.get("paln").toString();
			if (this.eventhandler != null) {
				this.eventhandler.PlanRefurbishEvent(paln);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnPlanStop")) {
			String paln = retValue.get("paln").toString();
			if (this.eventhandler != null) {
				this.eventhandler.PlanStopEvent(paln);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.OnRefurbish")) {
			String reason = retValue.get("reason").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RefurbishEvent(reason);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.onRequestSearch")) {
			String clientID = retValue.get("clientID").toString();
			String uuid = retValue.get("uuid").toString();
			String startTime = retValue.get("startTime").toString();
			String endTime = retValue.get("endTime").toString();
			String storeServerID = retValue.get("storeServerID").toString();
			String centerID = retValue.get("centerID").toString();
			String departID = retValue.get("departID").toString();
			String deviceID = retValue.get("deviceID").toString();
			Integer channelIndex;
			if (retValue.get("channelIndex").toString().equals("")) {
				channelIndex = null;
			} else {
				channelIndex = Integer.valueOf(retValue.get("channelIndex")
						.toString());
			}
			Integer searchType;
			if (retValue.get("searchType").toString().equals("")) {
				searchType = null;
			} else {
				searchType = Integer.valueOf(retValue.get("searchType")
						.toString());
			}
			Integer storeRecordType;
			if (retValue.get("storeRecordType").toString().equals("")) {
				storeRecordType = null;
			} else {
				storeRecordType = Integer.valueOf(retValue.get(
						"storeRecordType").toString());
			}
			String recordMark = retValue.get("recordMark").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onRequestSearchEvent(clientID, uuid,
						startTime, endTime, storeServerID, centerID, departID,
						deviceID, channelIndex, searchType, storeRecordType,
						recordMark);
			}
		}
		if (EventURL.equals("FXStoreDomain.IStoreEvent.onSearchSucess")) {
			String devName = retValue.get("devName").toString();
			String channelName = retValue.get("channelName").toString();
			String searchType = retValue.get("searchType").toString();
			String sFile = retValue.get("sFile").toString();
			String sStartTime = retValue.get("sStartTime").toString();
			String sEndTime = retValue.get("sEndTime").toString();
			String vodIp = retValue.get("vodIp").toString();
			Integer vodPort;
			if (retValue.get("vodPort").toString().equals("")) {
				vodPort = null;
			} else {
				vodPort = Integer.valueOf(retValue.get("vodPort").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler
						.onSearchSucessEvent(devName, channelName, searchType,
								sFile, sStartTime, sEndTime, vodIp, vodPort);
			}
		}
	}

	/**
	 * 策略模式的实现，提供方法回调处理方案。
	 * */
	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RequestStartRecord")) {
			if (this.funhandler != null) {
				this.funhandler.RequestStartRecordResult();
			}
		}
		if (EventURL
				.equals("FXStoreDomain.StoreBusiness.RequestStartRecordSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.RequestStartRecordSuccessResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RequestStopRecord")) {
			if (this.funhandler != null) {
				this.funhandler.RequestStopRecordResult();
			}
		}
		if (EventURL
				.equals("FXStoreDomain.StoreBusiness.RequestStopRecordSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.RequestStopRecordSuccessResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RequestMarkRecord")) {
			if (this.funhandler != null) {
				this.funhandler.RequestMarkRecordResult();
			}
		}
		if (EventURL
				.equals("FXStoreDomain.StoreBusiness.RequestMarkRecordSuccess")) {
			if (this.funhandler != null) {
				this.funhandler.RequestMarkRecordSuccessResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RecordServerLogin")) {
			if (this.funhandler != null) {
				this.funhandler.RecordServerLoginResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.PlanRefurbish")) {
			if (this.funhandler != null) {
				this.funhandler.PlanRefurbishResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.PlanStop")) {
			if (this.funhandler != null) {
				this.funhandler.PlanStopResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RequestSearch")) {
			if (this.funhandler != null) {
				this.funhandler.RequestSearchResult();
			}
		}
		if (EventURL.equals("FXStoreDomain.StoreBusiness.RequestSearchSucess")) {
			if (this.funhandler != null) {
				this.funhandler.RequestSearchSucessResult();
			}
		}
	}

	/**
	 * 客户端请求开始进行录像。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param devid
	 *            录像设备ID
	 * @param channel
	 *            录像设备的通道号
	 * @param bitRate
	 *            进行录像时候的码率
	 * @param recordMinute
	 *            进行录像时长(0,表示不限制时长)
	 * @param memo
	 *            附带信息
	 * */
	public void RequestStartRecord(java.lang.String ClientUserId,
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
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 存储服务器录像成功反馈给中心。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param devid
	 *            录像设备ID
	 * @param channel
	 *            录像设备的通道号
	 * @param memo
	 *            客户端调用所携带的附加信息。
	 * @param id
	 *            录像的句柄
	 * @param serverid
	 *            服务器ID
	 * */
	public void RequestStartRecordSuccess(java.lang.String ClientUserId,
			java.lang.String devid, java.lang.Integer channel,
			java.lang.String memo, java.lang.String id,
			java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RequestStartRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("devid", devid);
		Params.put("channel", channel);
		Params.put("memo", memo);
		Params.put("id", id);
		Params.put("serverid", serverid);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 客户端请求停止录像。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param serverid
	 *            录像所在服务器ID
	 * @param hand
	 *            录像的唯一标示 其中serverid和hand都是点播成功后，通过事件传送过来的。
	 * 
	 * */
	public void RequestStopRecord(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 存储服务器停止录像后反馈给中心。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param serverid
	 *            存储服务器ID
	 * @param hand
	 *            录像句柄ID
	 * @param flag
	 *            成功表示 true 成功 false 失败
	 * @param reason
	 *            失败原因
	 * 
	 * */
	public void RequestStopRecordSuccess(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand,
			java.lang.Boolean flag, java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestStopRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("flag", flag);
		Params.put("reason", reason);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 客户端请求对录像进行打标。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param serverid
	 *            存储服务器ID
	 * @param hand
	 *            录像句柄
	 * @param 打标相关内容
	 *            将需要记录的内容存储在该录像的时间轴上。
	 * 
	 * */
	public void RequestMarkRecord(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand,
			java.lang.String memo) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecord";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 存储服务器打标成功返回给中心。
	 * 
	 * @param ClientUserId
	 *            客户端会话ID
	 * @param serverid
	 *            存储服务器ID
	 * @param hand
	 *            录像句柄
	 * @param memo
	 *            打标内容
	 * @param flag
	 *            成功与否
	 * @param reason
	 *            失败的原因
	 * 
	 * 
	 * */
	public void RequestMarkRecordSuccess(java.lang.String ClientUserId,
			java.lang.String serverid, java.lang.String hand,
			java.lang.String memo, java.lang.Boolean flag,
			java.lang.String reason) {
		String url = "FXStoreDomain.StoreBusiness.RequestMarkRecordSuccess";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientUserId", ClientUserId);
		Params.put("serverid", serverid);
		Params.put("hand", hand);
		Params.put("memo", memo);
		Params.put("flag", flag);
		Params.put("reason", reason);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 存储服务器登录，通知中心
	 * 
	 * @param serverid
	 *            存储服务器ID
	 * 
	 * */
	public void RecordServerLogin(java.lang.String serverid) {
		String url = "FXStoreDomain.StoreBusiness.RecordServerLogin";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("serverid", serverid);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 客户端通知中心,修改或创建了新的录像计划。
	 * 
	 * @param client
	 *            客户端会话ID
	 * @param paln
	 *            计划ID
	 * 
	 * */
	public void PlanRefurbish(java.lang.String client, java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanRefurbish";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("paln", paln);
		run.Invoke(url, Params, null, null);
	}
   
	/**
	 * 客户端通知中心，录像计划停止。
	 * @param paln 计划ID
	 * @deprecated
	 * 该方案尚未实现，原因是没有这种需求。
	 * */
	public void PlanStop(java.lang.String paln) {
		String url = "FXStoreDomain.StoreBusiness.PlanStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("paln", paln);
		run.Invoke(url, Params, null, null);
	}

	/**
	 * 客户端请求根据条件查询相关录像。
	 * @param clientID 客户端会话ID
	 * @param uuid 唯一标示，由客户端提供
	 * @param startTime 查询条件之一：起始时间
	 * @param endTime 查询条件之一：结束时间
	 * @param storeServerID  查询条件之一：存储服务器ID
	 * @param centerID 查询条件之一： 中心ID
	 * @param departID 查询条件之一：部门ID
	 * @param deviceID 查询条件之一：查询设备ID
	 * @param channelIndex 查询条件之一：查询设备的通道
	 * @param searchType 查询条件之一： 录像开始存入的值
	 * @param storeRecordType 查询条件之一： 存储类型
	 * @param recordMark  查询条件之一：打标的内容
	 * 查询条件不需要全部填写，可供组装之用。
	 * 
	 * 
	 * */
	public void RequestSearch(java.lang.String clientID, java.lang.String uuid,
			java.lang.String startTime, java.lang.String endTime,
			java.lang.String storeServerID, java.lang.String centerID,
			java.lang.String departID, java.lang.String deviceID,
			java.lang.Integer channelIndex, java.lang.Integer searchType,
			java.lang.Integer storeRecordType, java.lang.String recordMark) {
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
		run.Invoke(url, Params, null, null);
	}
    
	/**
	 * 存储服务器向中心返回查询结果。
	 * @param clientID 客户端会话id
	 * @param szRealDeviceIds 设备ID
	 * @param nRealDeviceChannel 通道号
	 * @param searchType 查询条件之一： 录像开始存入的值
	 * @param sFile 录像文件的绝对路径。
	 * @param sStartTime 录像开始时间
	 * @param sEndTime 录像结束时间
	 * @param vodIp IP
	 * @param vodPort 端口
	 * 
	 * 
	 * */
	public void RequestSearchSucess(java.lang.String clientID,
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
		run.Invoke(url, Params, null, null);
	}
}
