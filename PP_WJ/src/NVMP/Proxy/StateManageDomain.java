package NVMP.Proxy;

import java.util.HashMap;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

public class StateManageDomain implements ReturnDo {
	static public class EventHandler {
		public Object EncodeDeviceOnlineEvent(java.lang.String TerminalId,
				java.lang.String TerminalIP, java.lang.Boolean IsOnline) {
			return null;
		}

		public Object AlarmStateEvent(java.lang.String TerminalId,
				java.lang.Integer ChannelId, java.lang.Integer AlarmType,
				java.lang.Integer DeviceStatus) {
			return null;
		}

		public Object ControlAlarmStateEvent(java.lang.String terminalId,
				java.lang.Integer index, java.lang.Integer alarmType,
				java.lang.Boolean isStart) {
			return null;
		}

		public Object ControlCameraPTZEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer direction,
				java.lang.Integer speed, java.lang.Boolean isStart) {
			return null;
		}

		public Object ControlVideoQualityEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer type,
				java.lang.Integer value) {
			return null;
		}

		public Object ControlVideoOSDEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer x,
				java.lang.Integer y, java.lang.String oSDName,
				java.lang.Boolean isDisplyDatetime) {
			return null;
		}

		public Object EncodeDeviceInfoEvent(java.lang.String clientId,
				java.lang.String deviceId) {
			return null;
		}

		public Object RetrunEncodeDeviceInfoResultEvent(
				java.lang.String clientId, java.lang.String deviceId,
				java.lang.String produce, java.lang.String type,
				java.lang.String iP) {
			return null;
		}

		public Object ReturnEncodeDeviceAlarmEvent(java.lang.String terminalId,
				java.lang.Integer channelId, java.lang.Integer alarmType,
				java.lang.Integer states) {
			return null;
		}

		public Object selectPrePointEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer index) {
			return null;
		}

		public Object RetrunGetVideoCompressResultEvent(
				java.lang.String clientId, java.lang.String deviceId,
				java.lang.Integer channl, java.lang.Integer brightness,
				java.lang.Integer saturation, java.lang.Integer hue,
				java.lang.Integer contrast) {
			return null;
		}

		public Object GetVideoCompressEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl) {
			return null;
		}

		public Object FireDeviceShowEvent(java.lang.String deviceId,
				java.lang.String deviceSource, java.lang.Integer index) {
			return null;
		}

		public Object SetPrePointEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer index) {
			return null;
		}

		public Object DeletePrePointEvent(java.lang.String terminalId,
				java.lang.Integer cameraindex, java.lang.Integer index) {
			return null;
		}

		public Object SetDeviceTimeEvent(java.lang.Integer year,
				java.lang.Integer month, java.lang.Integer day,
				java.lang.Integer hour, java.lang.Integer minute,
				java.lang.Integer scond) {
			return null;
		}

		public Object ControlOtherSourceEvent(java.lang.String centerid,
				java.lang.Boolean flag) {
			return null;
		}

		public Object GetResolutionEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl) {
			return null;
		}

		public Object SetResolutionEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nResolutionX, java.lang.Integer nResolutionY) {
			return null;
		}

		public Object onReturnResolutionResultEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nResolutionX, java.lang.Integer nResolutionY) {
			return null;
		}

		public Object onGetFrameRateEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl) {
			return null;
		}

		public Object onSetFrameRateEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nFramerate) {
			return null;
		}

		public Object onReturnFrameRateResultEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nFramerate) {
			return null;
		}

		public Object onGetBitRateEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl) {
			return null;
		}

		public Object onSetBitRateEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nBitrate) {
			return null;
		}

		public Object onReturnBitRateResultEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nBitrate) {
			return null;
		}

		public Object onGetFrameIntervalEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl) {
			return null;
		}

		public Object onSetFrameIntervalEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nGovLength) {
			return null;
		}

		public Object onReturnFrameIntervalEvent(java.lang.String clientId,
				java.lang.String deviceId, java.lang.Integer channl,
				java.lang.Integer nGovLength) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object setResolutionResult() {
			return null;
		}

		public Object returnResolutionResultResult() {
			return null;
		}

		public Object returnFrameRateResultResult() {
			return null;
		}

		public Object returnBitRateResultResult() {
			return null;
		}

		public Object getFrameIntervalResult() {
			return null;
		}

		public Object setFrameIntervalResult() {
			return null;
		}

		public Object returnFrameIntervalResult() {
			return null;
		}

		public Object GrobalSetDeviceOutLineResult() {
			return null;
		}

		public Object SetRTSPCommandEncodeDeviceOnlineResult() {
			return null;
		}

		public Object GrobalSetEncodeDeviceOnline_SyncResult() {
			return null;
		}

		public Object setPrePointResult() {
			return null;
		}

		public Object LoginStateResult() {
			return null;
		}

		public Object getFrameRateResult() {
			return null;
		}

		public Object setFrameRateResult() {
			return null;
		}

		public Object getBitRateResult() {
			return null;
		}

		public Object setBitRateResult() {
			return null;
		}

		public Object SetCommandEncodeDeviceOnlineResult() {
			return null;
		}

		public Object SetEncodeDeviceOnlineResult() {
			return null;
		}

		public Object SetAlarmStateResult() {
			return null;
		}

		public Object ControlAlarmStateResult() {
			return null;
		}

		public Object ControlCameraPTZResult() {
			return null;
		}

		public Object ControlVideoQualityResult() {
			return null;
		}

		public Object selectPrePointResult() {
			return null;
		}

		public Object deletePrePointResult() {
			return null;
		}

		public Object ControlVideoOSDResult() {
			return null;
		}

		public Object FireDeviceShowResult() {
			return null;
		}

		public Object GrobalFireDeviceShowResult() {
			return null;
		}

		public Object GetEncodeDeviceInfoResult() {
			return null;
		}

		public Object RetrunEncodeDeviceInfoResultResult() {
			return null;
		}

		public Object GetVideoCompressResult() {
			return null;
		}

		public Object RetrunGetVideoCompressResultResult() {
			return null;
		}

		public Object GetEncodeDeviceAlarmResult(java.lang.Integer Result) {
			return null;
		}

		public Object GetSingerEncodeDeviceOnlineResult(int did,
				java.lang.String devcieId, java.lang.String devName,
				java.lang.String devIp, java.lang.String devMask,
				java.lang.String devGate, java.lang.String devMAC, int devPort,
				java.lang.String userName, java.lang.String password,
				int devType, int devSubType, java.lang.String devModel,
				java.lang.String devSN, java.lang.String devVer,
				java.lang.String areaID, int cameraNum, int alarmPointNum,
				int outPutPointNum, int isDisable,
				java.lang.String registerTime, java.lang.String switchSvrID,
				java.lang.String centerID, int deviceStatus) {
			return null;
		}

		public Object GetEncodeDeviceOnlineResult() {
			return null;
		}

		public Object AddAlarmGroupResult() {
			return null;
		}

		public Object GrobalSetEncodeDeviceOnlineResult() {
			return null;
		}

		public Object SetDeviceTimeResult() {
			return null;
		}

		public Object SetDeviceOutLineResult() {
			return null;
		}

		public Object getResolutionResult() {
			return null;
		}
	}

	private IRunTime run;

	public StateManageDomain(IRunTime run) {
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
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnEncodeDeviceOnline")) {
			String TerminalId = retValue.get("TerminalId").toString();
			String TerminalIP = retValue.get("TerminalIP").toString();
			Boolean IsOnline;
			if (retValue.get("IsOnline").toString().equals("")) {
				IsOnline = null;
			} else {
				IsOnline = Boolean.valueOf(retValue.get("IsOnline").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.EncodeDeviceOnlineEvent(TerminalId,
						TerminalIP, IsOnline);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnAlarmState")) {
			String TerminalId = retValue.get("TerminalId").toString();
			Integer ChannelId;
			if (retValue.get("ChannelId").toString().equals("")) {
				ChannelId = null;
			} else {
				ChannelId = Integer.valueOf(retValue.get("ChannelId")
						.toString());
			}
			Integer AlarmType;
			if (retValue.get("AlarmType").toString().equals("")) {
				AlarmType = null;
			} else {
				AlarmType = Integer.valueOf(retValue.get("AlarmType")
						.toString());
			}
			Integer DeviceStatus;
			if (retValue.get("DeviceStatus").toString().equals("")) {
				DeviceStatus = null;
			} else {
				DeviceStatus = Integer.valueOf(retValue.get("DeviceStatus")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.AlarmStateEvent(TerminalId, ChannelId,
						AlarmType, DeviceStatus);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnControlAlarmState")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			Integer alarmType;
			if (retValue.get("alarmType").toString().equals("")) {
				alarmType = null;
			} else {
				alarmType = Integer.valueOf(retValue.get("alarmType")
						.toString());
			}
			Boolean isStart;
			if (retValue.get("isStart").toString().equals("")) {
				isStart = null;
			} else {
				isStart = Boolean.valueOf(retValue.get("isStart").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlAlarmStateEvent(terminalId, index,
						alarmType, isStart);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnControlCameraPTZ")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer direction;
			if (retValue.get("direction").toString().equals("")) {
				direction = null;
			} else {
				direction = Integer.valueOf(retValue.get("direction")
						.toString());
			}
			Integer speed;
			if (retValue.get("speed").toString().equals("")) {
				speed = null;
			} else {
				speed = Integer.valueOf(retValue.get("speed").toString());
			}
			Boolean isStart;
			if (retValue.get("isStart").toString().equals("")) {
				isStart = null;
			} else {
				isStart = Boolean.valueOf(retValue.get("isStart").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlCameraPTZEvent(terminalId,
						cameraindex, direction, speed, isStart);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnControlVideoQuality")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer type;
			if (retValue.get("type").toString().equals("")) {
				type = null;
			} else {
				type = Integer.valueOf(retValue.get("type").toString());
			}
			Integer value;
			if (retValue.get("value").toString().equals("")) {
				value = null;
			} else {
				value = Integer.valueOf(retValue.get("value").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlVideoQualityEvent(terminalId,
						cameraindex, type, value);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnControlVideoOSD")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer x;
			if (retValue.get("x").toString().equals("")) {
				x = null;
			} else {
				x = Integer.valueOf(retValue.get("x").toString());
			}
			Integer y;
			if (retValue.get("y").toString().equals("")) {
				y = null;
			} else {
				y = Integer.valueOf(retValue.get("y").toString());
			}
			String oSDName = retValue.get("oSDName").toString();
			Boolean isDisplyDatetime;
			if (retValue.get("isDisplyDatetime").toString().equals("")) {
				isDisplyDatetime = null;
			} else {
				isDisplyDatetime = Boolean.valueOf(retValue.get(
						"isDisplyDatetime").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlVideoOSDEvent(terminalId, cameraindex,
						x, y, oSDName, isDisplyDatetime);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnEncodeDeviceInfo")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			if (this.eventhandler != null) {
				this.eventhandler.EncodeDeviceInfoEvent(clientId, deviceId);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnRetrunEncodeDeviceInfoResult")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			String produce = retValue.get("produce").toString();
			String type = retValue.get("type").toString();
			String iP = retValue.get("iP").toString();
			if (this.eventhandler != null) {
				this.eventhandler.RetrunEncodeDeviceInfoResultEvent(clientId,
						deviceId, produce, type, iP);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnReturnEncodeDeviceAlarm")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer channelId;
			if (retValue.get("channelId").toString().equals("")) {
				channelId = null;
			} else {
				channelId = Integer.valueOf(retValue.get("channelId")
						.toString());
			}
			Integer alarmType;
			if (retValue.get("alarmType").toString().equals("")) {
				alarmType = null;
			} else {
				alarmType = Integer.valueOf(retValue.get("alarmType")
						.toString());
			}
			Integer states;
			if (retValue.get("states").toString().equals("")) {
				states = null;
			} else {
				states = Integer.valueOf(retValue.get("states").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ReturnEncodeDeviceAlarmEvent(terminalId,
						channelId, alarmType, states);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnselectPrePoint")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.selectPrePointEvent(terminalId, cameraindex,
						index);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnRetrunGetVideoCompressResult")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer brightness;
			if (retValue.get("brightness").toString().equals("")) {
				brightness = null;
			} else {
				brightness = Integer.valueOf(retValue.get("brightness")
						.toString());
			}
			Integer saturation;
			if (retValue.get("saturation").toString().equals("")) {
				saturation = null;
			} else {
				saturation = Integer.valueOf(retValue.get("saturation")
						.toString());
			}
			Integer hue;
			if (retValue.get("hue").toString().equals("")) {
				hue = null;
			} else {
				hue = Integer.valueOf(retValue.get("hue").toString());
			}
			Integer contrast;
			if (retValue.get("contrast").toString().equals("")) {
				contrast = null;
			} else {
				contrast = Integer.valueOf(retValue.get("contrast").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler
						.RetrunGetVideoCompressResultEvent(clientId, deviceId,
								channl, brightness, saturation, hue, contrast);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnGetVideoCompress")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.GetVideoCompressEvent(clientId, deviceId,
						channl);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnFireDeviceShow")) {
			String deviceId = retValue.get("deviceId").toString();
			String deviceSource = retValue.get("deviceSource").toString();
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.FireDeviceShowEvent(deviceId, deviceSource,
						index);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnSetPrePoint")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.SetPrePointEvent(terminalId, cameraindex,
						index);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnDeletePrePoint")) {
			String terminalId = retValue.get("terminalId").toString();
			Integer cameraindex;
			if (retValue.get("cameraindex").toString().equals("")) {
				cameraindex = null;
			} else {
				cameraindex = Integer.valueOf(retValue.get("cameraindex")
						.toString());
			}
			Integer index;
			if (retValue.get("index").toString().equals("")) {
				index = null;
			} else {
				index = Integer.valueOf(retValue.get("index").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.DeletePrePointEvent(terminalId, cameraindex,
						index);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnSetDeviceTime")) {
			Integer year;
			if (retValue.get("year").toString().equals("")) {
				year = null;
			} else {
				year = Integer.valueOf(retValue.get("year").toString());
			}
			Integer month;
			if (retValue.get("month").toString().equals("")) {
				month = null;
			} else {
				month = Integer.valueOf(retValue.get("month").toString());
			}
			Integer day;
			if (retValue.get("day").toString().equals("")) {
				day = null;
			} else {
				day = Integer.valueOf(retValue.get("day").toString());
			}
			Integer hour;
			if (retValue.get("hour").toString().equals("")) {
				hour = null;
			} else {
				hour = Integer.valueOf(retValue.get("hour").toString());
			}
			Integer minute;
			if (retValue.get("minute").toString().equals("")) {
				minute = null;
			} else {
				minute = Integer.valueOf(retValue.get("minute").toString());
			}
			Integer scond;
			if (retValue.get("scond").toString().equals("")) {
				scond = null;
			} else {
				scond = Integer.valueOf(retValue.get("scond").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.SetDeviceTimeEvent(year, month, day, hour,
						minute, scond);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnControlOtherSource")) {
			String centerid = retValue.get("centerid").toString();
			Boolean flag;
			if (retValue.get("flag").toString().equals("")) {
				flag = null;
			} else {
				flag = Boolean.valueOf(retValue.get("flag").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.ControlOtherSourceEvent(centerid, flag);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnGetResolution")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler
						.GetResolutionEvent(clientId, deviceId, channl);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.OnSetResolution")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nResolutionX;
			if (retValue.get("nResolutionX").toString().equals("")) {
				nResolutionX = null;
			} else {
				nResolutionX = Integer.valueOf(retValue.get("nResolutionX")
						.toString());
			}
			Integer nResolutionY;
			if (retValue.get("nResolutionY").toString().equals("")) {
				nResolutionY = null;
			} else {
				nResolutionY = Integer.valueOf(retValue.get("nResolutionY")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.SetResolutionEvent(clientId, deviceId,
						channl, nResolutionX, nResolutionY);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onReturnResolutionResult")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nResolutionX;
			if (retValue.get("nResolutionX").toString().equals("")) {
				nResolutionX = null;
			} else {
				nResolutionX = Integer.valueOf(retValue.get("nResolutionX")
						.toString());
			}
			Integer nResolutionY;
			if (retValue.get("nResolutionY").toString().equals("")) {
				nResolutionY = null;
			} else {
				nResolutionY = Integer.valueOf(retValue.get("nResolutionY")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onReturnResolutionResultEvent(clientId,
						deviceId, channl, nResolutionX, nResolutionY);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onGetFrameRate")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onGetFrameRateEvent(clientId, deviceId,
						channl);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onSetFrameRate")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nFramerate;
			if (retValue.get("nFramerate").toString().equals("")) {
				nFramerate = null;
			} else {
				nFramerate = Integer.valueOf(retValue.get("nFramerate")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onSetFrameRateEvent(clientId, deviceId,
						channl, nFramerate);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onReturnFrameRateResult")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nFramerate;
			if (retValue.get("nFramerate").toString().equals("")) {
				nFramerate = null;
			} else {
				nFramerate = Integer.valueOf(retValue.get("nFramerate")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onReturnFrameRateResultEvent(clientId,
						deviceId, channl, nFramerate);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onGetBitRate")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onGetBitRateEvent(clientId, deviceId, channl);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onSetBitRate")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nBitrate;
			if (retValue.get("nBitrate").toString().equals("")) {
				nBitrate = null;
			} else {
				nBitrate = Integer.valueOf(retValue.get("nBitrate").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onSetBitRateEvent(clientId, deviceId, channl,
						nBitrate);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onReturnBitRateResult")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nBitrate;
			if (retValue.get("nBitrate").toString().equals("")) {
				nBitrate = null;
			} else {
				nBitrate = Integer.valueOf(retValue.get("nBitrate").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onReturnBitRateResultEvent(clientId,
						deviceId, channl, nBitrate);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onGetFrameInterval")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onGetFrameIntervalEvent(clientId, deviceId,
						channl);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onSetFrameInterval")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nGovLength;
			if (retValue.get("nGovLength").toString().equals("")) {
				nGovLength = null;
			} else {
				nGovLength = Integer.valueOf(retValue.get("nGovLength")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onSetFrameIntervalEvent(clientId, deviceId,
						channl, nGovLength);
			}
		}
		if (EventURL
				.equals("StateManageDomain.IEncodeDeviceManage.onReturnFrameInterval")) {
			String clientId = retValue.get("clientId").toString();
			String deviceId = retValue.get("deviceId").toString();
			Integer channl;
			if (retValue.get("channl").toString().equals("")) {
				channl = null;
			} else {
				channl = Integer.valueOf(retValue.get("channl").toString());
			}
			Integer nGovLength;
			if (retValue.get("nGovLength").toString().equals("")) {
				nGovLength = null;
			} else {
				nGovLength = Integer.valueOf(retValue.get("nGovLength")
						.toString());
			}
			if (this.eventhandler != null) {
				this.eventhandler.onReturnFrameIntervalEvent(clientId,
						deviceId, channl, nGovLength);
			}
		}
	}

	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.setResolution")) {
			if (this.funhandler != null) {
				this.funhandler.setResolutionResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.returnResolutionResult")) {
			if (this.funhandler != null) {
				this.funhandler.returnResolutionResultResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.returnFrameRateResult")) {
			if (this.funhandler != null) {
				this.funhandler.returnFrameRateResultResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.returnBitRateResult")) {
			if (this.funhandler != null) {
				this.funhandler.returnBitRateResultResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.getFrameInterval")) {
			if (this.funhandler != null) {
				this.funhandler.getFrameIntervalResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.setFrameInterval")) {
			if (this.funhandler != null) {
				this.funhandler.setFrameIntervalResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.returnFrameInterval")) {
			if (this.funhandler != null) {
				this.funhandler.returnFrameIntervalResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GrobalSetDeviceOutLine")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalSetDeviceOutLineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetRTSPCommandEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.SetRTSPCommandEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline_Sync")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalSetEncodeDeviceOnline_SyncResult();
			}
		}
		if (EventURL.equals("StateManageDomain.EncodeDeviceManage.setPrePoint")) {
			if (this.funhandler != null) {
				this.funhandler.setPrePointResult();
			}
		}
		if (EventURL.equals("StateManageDomain.EncodeDeviceManage.LoginState")) {
			if (this.funhandler != null) {
				this.funhandler.LoginStateResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.getFrameRate")) {
			if (this.funhandler != null) {
				this.funhandler.getFrameRateResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.setFrameRate")) {
			if (this.funhandler != null) {
				this.funhandler.setFrameRateResult();
			}
		}
		if (EventURL.equals("StateManageDomain.EncodeDeviceManage.getBitRate")) {
			if (this.funhandler != null) {
				this.funhandler.getBitRateResult();
			}
		}
		if (EventURL.equals("StateManageDomain.EncodeDeviceManage.setBitRate")) {
			if (this.funhandler != null) {
				this.funhandler.setBitRateResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetCommandEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.SetCommandEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.SetEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetAlarmState")) {
			if (this.funhandler != null) {
				this.funhandler.SetAlarmStateResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.ControlAlarmState")) {
			if (this.funhandler != null) {
				this.funhandler.ControlAlarmStateResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.ControlCameraPTZ")) {
			if (this.funhandler != null) {
				this.funhandler.ControlCameraPTZResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.ControlVideoQuality")) {
			if (this.funhandler != null) {
				this.funhandler.ControlVideoQualityResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.selectPrePoint")) {
			if (this.funhandler != null) {
				this.funhandler.selectPrePointResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.deletePrePoint")) {
			if (this.funhandler != null) {
				this.funhandler.deletePrePointResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.ControlVideoOSD")) {
			if (this.funhandler != null) {
				this.funhandler.ControlVideoOSDResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.FireDeviceShow")) {
			if (this.funhandler != null) {
				this.funhandler.FireDeviceShowResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GrobalFireDeviceShow")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalFireDeviceShowResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GetEncodeDeviceInfo")) {
			if (this.funhandler != null) {
				this.funhandler.GetEncodeDeviceInfoResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.RetrunEncodeDeviceInfoResult")) {
			if (this.funhandler != null) {
				this.funhandler.RetrunEncodeDeviceInfoResultResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GetVideoCompress")) {
			if (this.funhandler != null) {
				this.funhandler.GetVideoCompressResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.RetrunGetVideoCompressResult")) {
			if (this.funhandler != null) {
				this.funhandler.RetrunGetVideoCompressResultResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GetEncodeDeviceAlarm")) {
			Integer Result;
			if (retValue.get("Result").toString().equals("")) {
				Result = null;
			} else {
				Result = Integer.valueOf(retValue.get("Result").toString());
			}
			if (this.funhandler != null) {
				this.funhandler.GetEncodeDeviceAlarmResult(Result);
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GetSingerEncodeDeviceOnline")) {
			String devcieId = retValue.get("devcieId").toString();
			String devName = retValue.get("devName").toString();
			String devIp = retValue.get("devIp").toString();
			String devMask = retValue.get("devMask").toString();
			String devGate = retValue.get("devGate").toString();
			String devMAC = retValue.get("devMAC").toString();
			String userName = retValue.get("userName").toString();
			String password = retValue.get("password").toString();
			String devModel = retValue.get("devModel").toString();
			String devSN = retValue.get("devSN").toString();
			String devVer = retValue.get("devVer").toString();
			String areaID = retValue.get("areaID").toString();
			String registerTime = retValue.get("registerTime").toString();
			String switchSvrID = retValue.get("switchSvrID").toString();
			String centerID = retValue.get("centerID").toString();
			if (this.funhandler != null) {
				// this.funhandler.GetSingerEncodeDeviceOnlineResult(did,devcieId,devName,devIp,devMask,devGate,devMAC,devPort,userName,password,devType,devSubType,devModel,devSN,devVer,areaID,cameraNum,alarmPointNum,outPutPointNum,isDisable,registerTime,switchSvrID,centerID,deviceStatus
				// );
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GetEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.GetEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.AddAlarmGroup")) {
			if (this.funhandler != null) {
				this.funhandler.AddAlarmGroupResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline")) {
			if (this.funhandler != null) {
				this.funhandler.GrobalSetEncodeDeviceOnlineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetDeviceTime")) {
			if (this.funhandler != null) {
				this.funhandler.SetDeviceTimeResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.SetDeviceOutLine")) {
			if (this.funhandler != null) {
				this.funhandler.SetDeviceOutLineResult();
			}
		}
		if (EventURL
				.equals("StateManageDomain.EncodeDeviceManage.getResolution")) {
			if (this.funhandler != null) {
				this.funhandler.getResolutionResult();
			}
		}
	}

	public void setResolution(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nResolutionX, java.lang.Integer nResolutionY) {
		String url = "StateManageDomain.EncodeDeviceManage.setResolution";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nResolutionX", nResolutionX);
		Params.put("nResolutionY", nResolutionY);
		run.Invoke(url, Params, null, null);
	}

	public void returnResolutionResult(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nResolutionX, java.lang.Integer nResolutionY) {
		String url = "StateManageDomain.EncodeDeviceManage.returnResolutionResult";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nResolutionX", nResolutionX);
		Params.put("nResolutionY", nResolutionY);
		run.Invoke(url, Params, null, null);
	}

	public void returnFrameRateResult(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nFramerate) {
		String url = "StateManageDomain.EncodeDeviceManage.returnFrameRateResult";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nFramerate", nFramerate);
		run.Invoke(url, Params, null, null);
	}

	public void returnBitRateResult(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nBitrate) {
		String url = "StateManageDomain.EncodeDeviceManage.returnBitRateResult";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nBitrate", nBitrate);
		run.Invoke(url, Params, null, null);
	}

	public void getFrameInterval(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl) {
		String url = "StateManageDomain.EncodeDeviceManage.getFrameInterval";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		run.Invoke(url, Params, null, null);
	}

	public void setFrameInterval(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nGovLength) {
		String url = "StateManageDomain.EncodeDeviceManage.setFrameInterval";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nGovLength", nGovLength);
		run.Invoke(url, Params, null, null);
	}

	public void returnFrameInterval(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nGovLength) {
		String url = "StateManageDomain.EncodeDeviceManage.returnFrameInterval";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nGovLength", nGovLength);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalSetDeviceOutLine(java.lang.String Client,
			java.lang.String Deviceid, java.lang.String center) {
		String url = "StateManageDomain.EncodeDeviceManage.GrobalSetDeviceOutLine";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("Client", Client);
		Params.put("Deviceid", Deviceid);
		Params.put("center", center);
		run.Invoke(url, Params, null, null);
	}

	public void SetRTSPCommandEncodeDeviceOnline(java.lang.String DeviceID,
			java.lang.Integer TypeID, java.lang.Integer Channel,
			java.lang.Integer DefaultFlag, java.lang.Integer BitStream,
			java.lang.String RtspUrl) {
		String url = "StateManageDomain.EncodeDeviceManage.SetRTSPCommandEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceID", DeviceID);
		Params.put("TypeID", TypeID);
		Params.put("Channel", Channel);
		Params.put("DefaultFlag", DefaultFlag);
		Params.put("BitStream", BitStream);
		Params.put("RtspUrl", RtspUrl);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalSetEncodeDeviceOnline_Sync(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline,
			java.lang.Integer DeviceType, java.lang.Integer DevuceSubType,
			java.lang.String xml, java.lang.String centerid) {
		String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline_Sync";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		Params.put("DeviceType", DeviceType);
		Params.put("DevuceSubType", DevuceSubType);
		Params.put("xml", xml);
		Params.put("centerid", centerid);
		run.Invoke(url, Params, null, null);
	}

	public void setPrePoint(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer index) {
		String url = "StateManageDomain.EncodeDeviceManage.setPrePoint";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("index", index);
		run.Invoke(url, Params, null, null);
	}

	public void LoginState(java.lang.String sessionid) {
		String url = "StateManageDomain.EncodeDeviceManage.LoginState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("sessionid", sessionid);
		run.Invoke(url, Params, null, null);
	}

	public void getFrameRate(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl) {
		String url = "StateManageDomain.EncodeDeviceManage.getFrameRate";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		run.Invoke(url, Params, null, null);
	}

	public void setFrameRate(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nFramerate) {
		String url = "StateManageDomain.EncodeDeviceManage.setFrameRate";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nFramerate", nFramerate);
		run.Invoke(url, Params, null, null);
	}

	public void getBitRate(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl) {
		String url = "StateManageDomain.EncodeDeviceManage.getBitRate";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		run.Invoke(url, Params, null, null);
	}

	public void setBitRate(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer nBitrate) {
		String url = "StateManageDomain.EncodeDeviceManage.setBitRate";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("nBitrate", nBitrate);
		run.Invoke(url, Params, null, null);
	}

	public void SetCommandEncodeDeviceOnline(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline,
			java.lang.Integer DeviceType, java.lang.Integer DevuceSubType,
			java.lang.String centerid) {
		String url = "StateManageDomain.EncodeDeviceManage.SetCommandEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		Params.put("DeviceType", DeviceType);
		Params.put("DevuceSubType", DevuceSubType);
		Params.put("centerid", centerid);
		run.Invoke(url, Params, null, null);
	}

	public void SetEncodeDeviceOnline(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline,
			java.lang.Integer DeviceType, java.lang.Integer DevuceSubType,
			java.lang.String xml) {
		String url = "StateManageDomain.EncodeDeviceManage.SetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		Params.put("DeviceType", DeviceType);
		Params.put("DevuceSubType", DevuceSubType);
		Params.put("xml", xml);
		run.Invoke(url, Params, null, null);
	}

	public void SetAlarmState(java.lang.String TerminalId,
			java.lang.Integer ChannelId, java.lang.Integer AlarmType,
			java.lang.Integer DeviceStatus) {
		String url = "StateManageDomain.EncodeDeviceManage.SetAlarmState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("ChannelId", ChannelId);
		Params.put("AlarmType", AlarmType);
		Params.put("DeviceStatus", DeviceStatus);
		run.Invoke(url, Params, null, null);
	}

	public void ControlAlarmState(java.lang.String TerminalId,
			java.lang.Integer index, java.lang.Integer AlarmType,
			java.lang.Boolean IsStart) {
		String url = "StateManageDomain.EncodeDeviceManage.ControlAlarmState";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("index", index);
		Params.put("AlarmType", AlarmType);
		Params.put("IsStart", IsStart);
		run.Invoke(url, Params, null, null);
	}

	public void ControlCameraPTZ(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer Direction,
			java.lang.Integer Speed, java.lang.Boolean IsStart) {
		String url = "StateManageDomain.EncodeDeviceManage.ControlCameraPTZ";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("Direction", Direction);
		Params.put("Speed", Speed);
		Params.put("IsStart", IsStart);
		run.Invoke(url, Params, null, null);
	}

	public void ControlVideoQuality(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer Type,
			java.lang.Integer Value) {
		String url = "StateManageDomain.EncodeDeviceManage.ControlVideoQuality";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("Type", Type);
		Params.put("Value", Value);
		run.Invoke(url, Params, null, null);
	}

	public void selectPrePoint(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer index) {
		String url = "StateManageDomain.EncodeDeviceManage.selectPrePoint";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("index", index);
		run.Invoke(url, Params, null, null);
	}

	public void deletePrePoint(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer index) {
		String url = "StateManageDomain.EncodeDeviceManage.deletePrePoint";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("index", index);
		run.Invoke(url, Params, null, null);
	}

	public void ControlVideoOSD(java.lang.String TerminalId,
			java.lang.Integer Cameraindex, java.lang.Integer X,
			java.lang.Integer Y, java.lang.String OSDName,
			java.lang.Boolean IsDisplyDatetime) {
		String url = "StateManageDomain.EncodeDeviceManage.ControlVideoOSD";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("Cameraindex", Cameraindex);
		Params.put("X", X);
		Params.put("Y", Y);
		Params.put("OSDName", OSDName);
		Params.put("IsDisplyDatetime", IsDisplyDatetime);
		run.Invoke(url, Params, null, null);
	}

	public void FireDeviceShow(java.lang.String DeviceId,
			java.lang.Integer index) {
		String url = "StateManageDomain.EncodeDeviceManage.FireDeviceShow";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("index", index);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalFireDeviceShow(java.lang.String DeviceId,
			java.lang.Integer index, java.lang.String CenterID) {
		String url = "StateManageDomain.EncodeDeviceManage.GrobalFireDeviceShow";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("DeviceId", DeviceId);
		Params.put("index", index);
		Params.put("CenterID", CenterID);
		run.Invoke(url, Params, null, null);
	}

	public void GetEncodeDeviceInfo(java.lang.String ClientId,
			java.lang.String DeviceId) {
		String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		run.Invoke(url, Params, null, null);
	}

	public void RetrunEncodeDeviceInfoResult(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.String Produce,
			java.lang.String type, java.lang.String IP) {
		String url = "StateManageDomain.EncodeDeviceManage.RetrunEncodeDeviceInfoResult";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("Produce", Produce);
		Params.put("type", type);
		Params.put("IP", IP);
		run.Invoke(url, Params, null, null);
	}

	public void GetVideoCompress(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl) {
		String url = "StateManageDomain.EncodeDeviceManage.GetVideoCompress";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		run.Invoke(url, Params, null, null);
	}

	public void RetrunGetVideoCompressResult(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl,
			java.lang.Integer Brightness, java.lang.Integer Saturation,
			java.lang.Integer Hue, java.lang.Integer Contrast) {
		String url = "StateManageDomain.EncodeDeviceManage.RetrunGetVideoCompressResult";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		Params.put("Brightness", Brightness);
		Params.put("Saturation", Saturation);
		Params.put("Hue", Hue);
		Params.put("Contrast", Contrast);
		run.Invoke(url, Params, null, null);
	}

	public void GetEncodeDeviceAlarm(java.lang.String sessionid,
			java.lang.String TerminalId, java.lang.Integer ChannelId,
			java.lang.Integer AlarmType) {
		String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceAlarm";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("sessionid", sessionid);
		Params.put("TerminalId", TerminalId);
		Params.put("ChannelId", ChannelId);
		Params.put("AlarmType", AlarmType);
		run.Invoke(url, Params, null, null);
	}

	public void GetSingerEncodeDeviceOnline(java.lang.String sessionid,
			java.lang.String TerminalId) {
		String url = "StateManageDomain.EncodeDeviceManage.GetSingerEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("sessionid", sessionid);
		Params.put("TerminalId", TerminalId);
		run.Invoke(url, Params, null, null);
	}

	public void GetEncodeDeviceOnline(java.lang.String UserId) {
		String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("UserId", UserId);
		run.Invoke(url, Params, null, null);
	}

	public void AddAlarmGroup(java.lang.String UserId) {
		String url = "StateManageDomain.EncodeDeviceManage.AddAlarmGroup";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("UserId", UserId);
		run.Invoke(url, Params, null, null);
	}

	public void GrobalSetEncodeDeviceOnline(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline,
			java.lang.Integer DeviceType, java.lang.Integer DevuceSubType,
			java.lang.String xml, java.lang.String centerid) {
		String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("TerminalId", TerminalId);
		Params.put("TerminalIP", TerminalIP);
		Params.put("IsOnline", IsOnline);
		Params.put("DeviceType", DeviceType);
		Params.put("DevuceSubType", DevuceSubType);
		Params.put("xml", xml);
		Params.put("centerid", centerid);
		run.Invoke(url, Params, null, null);
	}

	public void SetDeviceTime(java.lang.Integer year, java.lang.Integer month,
			java.lang.Integer day, java.lang.Integer hour,
			java.lang.Integer minute, java.lang.Integer scond) {
		String url = "StateManageDomain.EncodeDeviceManage.SetDeviceTime";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("year", year);
		Params.put("month", month);
		Params.put("day", day);
		Params.put("hour", hour);
		Params.put("minute", minute);
		Params.put("scond", scond);
		run.Invoke(url, Params, null, null);
	}

	public void SetDeviceOutLine(java.lang.String Client,
			java.lang.String Deviceid, java.lang.String center) {
		String url = "StateManageDomain.EncodeDeviceManage.SetDeviceOutLine";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("Client", Client);
		Params.put("Deviceid", Deviceid);
		Params.put("center", center);
		run.Invoke(url, Params, null, null);
	}

	public void getResolution(java.lang.String ClientId,
			java.lang.String DeviceId, java.lang.Integer channl) {
		String url = "StateManageDomain.EncodeDeviceManage.getResolution";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("ClientId", ClientId);
		Params.put("DeviceId", DeviceId);
		Params.put("channl", channl);
		run.Invoke(url, Params, null, null);
	}
}
