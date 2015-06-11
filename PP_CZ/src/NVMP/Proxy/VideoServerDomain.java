package NVMP.Proxy;

import java.util.HashMap;

import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.ReturnDo;

public class VideoServerDomain implements ReturnDo {
	static public class EventHandler {
		public Object onServerStartEvent(java.lang.String nInstance,
				java.lang.String context) {
			return null;
		}

		public Object onGetChannelNumbersEvent(java.lang.Integer nums,
				java.lang.String context) {
			return null;
		}

		public Object onGetChannelNameEvent(java.lang.Integer posX,
				java.lang.Integer getnPosY, java.lang.String szName,
				java.lang.Integer channel, java.lang.String context) {
			return null;
		}

		public Object onGetSerialPortParamEvent(java.lang.Integer getnAddress,
				java.lang.Integer getnBaudRate, java.lang.Integer getnDataBit,
				java.lang.Integer getnParity,
				java.lang.Integer getnPTZProtocol,
				java.lang.Integer getnStopBit, java.lang.Integer channel,
				java.lang.String context) {
			return null;
		}

		public Object onGetVideoEffectEvent(java.lang.Integer m_nBirghtness,
				java.lang.Integer m_nContrast, java.lang.Integer m_nHue,
				java.lang.Integer m_nSaturation, java.lang.Integer channel,
				java.lang.String context) {
			return null;
		}

		public Object onGetVideoCompressEvent(java.lang.Integer m_nBitrate,
				java.lang.Integer m_nCodecType, java.lang.Integer m_nFramerate,
				java.lang.Integer m_nQuality, java.lang.Integer m_nResolution,
				java.lang.Integer channel, java.lang.String context) {
			return null;
		}

		public Object onGetOSDEvent(java.lang.Integer posX,
				java.lang.Integer posY, java.lang.Boolean showOSD,
				java.lang.Boolean showTime, java.lang.String oSDContent,
				java.lang.Integer nChannel, java.lang.String context) {
			return null;
		}
		
		
		
		
		
		
		
		
		
		
		//////////////////////////获取解码卡信息//////////////////////////
		//////////////////////////gjj 2012.10.18/////////////////////////
		public Object onGetHardwareInitInfo(Integer _succeedDecodeChannelCount,
				Integer _succeedInitDspCount,
				Integer _succeedDisplayChannelCount, Integer _decodeCardCount,
				String context) {
			return null;
		}

		public Object onGetDisplayRelatedDecodeIndex(
				Integer DisplayRelatedDecodeIndex, String context) {
			return null;
		}

		public Object onGetIsDecode(Integer IsDecode, String context) {
			return null;
		}

		public Object onGetIsTvAreaOut(Integer IsTvAreaOut, String context) {
			return null;
		}

		public Object onGetIsTvOut(Integer IsTvOut, String context) {
			return null;
		}

		public Object onGetTvSplitCount(Integer TvSplitCount, String context) {
			return null;
		}

		public Object onGetVedioSource(String _sourceIp, Integer _sourcePort,
				Integer _sourceChannel, String context) {
			return null;
		}
		
		
		
		public Object onGetDisplayChannelInfo(Integer nIsDisplayChannelPlay,
				Integer splitNum, String area0,String context ) {
			return null;
		}
	}

	static public class FunResultHandler {
		public Object ServerSetAddressResult() {
			return null;
		}

		public Object ServerStartResult() {
			return null;
		}

		public Object ServerStopResult() {
			return null;
		}

		public Object GetChannelNumbersResult() {
			return null;
		}

		public Object SetChannelNameResult() {
			return null;
		}

		public Object GetChannelNameResult() {
			return null;
		}

		public Object MakeKeyFrameResult() {
			return null;
		}

		public Object ControlPTZResult() {
			return null;
		}

		public Object SetSerialPortParamResult() {
			return null;
		}

		public Object GetSerialPortParamResult() {
			return null;
		}

		public Object SetOSDResult() {
			return null;
		}

		public Object GetOSDResult() {
			return null;
		}

		public Object SetTextResult() {
			return null;
		}

		public Object SetVideoEffectResult() {
			return null;
		}

		public Object GetVideoEffectResult() {
			return null;
		}

		public Object SetVideoCompressResult() {
			return null;
		}

		public Object GetVideoCompressResult() {
			return null;
		}
		
		
		
		
		
		
		
		//////////////////////////获取解码卡信息//////////////////////////
		//////////////////////////gjj 2012.10.18/////////////////////////
		public Object GetHardwareInitInfoResult() {
			return null;
		}
		
		public Object GetDisplayRelatedDecodeIndexResult(){
			return null;
		}
		
		public Object GetIsDecodeResult(){
			return null;
		}
		
		public Object GetIsTvAreaOutResult(){
			return null;
		}
		
		public Object GetIsTvOutResult(){
			return null;
		}
		
		public Object GetTvSplitCountResult(){
			return null;
		}
		
		public Object GetVedioSourceResult() {
			return null;
		}
		
		
		public Object GetDisplayChannelInfoResult() {
			return null;
		}
	}

	private IRunTime run;

	public VideoServerDomain(IRunTime run) {
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
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onServerStart")) {
			String nInstance = retValue.get("nInstance").toString();
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onServerStartEvent(nInstance, context);
			}
		}
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetChannelNumbers")) {
			Integer nums;
			if (retValue.get("nums").toString().equals("")) {
				nums = null;
			} else {
				nums = Integer.valueOf(retValue.get("nums").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetChannelNumbersEvent(nums, context);
			}
		}
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetChannelName")) {
			Integer posX;
			if (retValue.get("posX").toString().equals("")) {
				posX = null;
			} else {
				posX = Integer.valueOf(retValue.get("posX").toString());
			}
			Integer getnPosY;
			if (retValue.get("getnPosY").toString().equals("")) {
				getnPosY = null;
			} else {
				getnPosY = Integer.valueOf(retValue.get("getnPosY").toString());
			}
			String szName = retValue.get("szName").toString();
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetChannelNameEvent(posX, getnPosY, szName,
						channel, context);
			}
		}
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetSerialPortParam")) {
			Integer getnAddress;
			if (retValue.get("getnAddress").toString().equals("")) {
				getnAddress = null;
			} else {
				getnAddress = Integer.valueOf(retValue.get("getnAddress")
						.toString());
			}
			Integer getnBaudRate;
			if (retValue.get("getnBaudRate").toString().equals("")) {
				getnBaudRate = null;
			} else {
				getnBaudRate = Integer.valueOf(retValue.get("getnBaudRate")
						.toString());
			}
			Integer getnDataBit;
			if (retValue.get("getnDataBit").toString().equals("")) {
				getnDataBit = null;
			} else {
				getnDataBit = Integer.valueOf(retValue.get("getnDataBit")
						.toString());
			}
			Integer getnParity;
			if (retValue.get("getnParity").toString().equals("")) {
				getnParity = null;
			} else {
				getnParity = Integer.valueOf(retValue.get("getnParity")
						.toString());
			}
			Integer getnPTZProtocol;
			if (retValue.get("getnPTZProtocol").toString().equals("")) {
				getnPTZProtocol = null;
			} else {
				getnPTZProtocol = Integer.valueOf(retValue.get(
						"getnPTZProtocol").toString());
			}
			Integer getnStopBit;
			if (retValue.get("getnStopBit").toString().equals("")) {
				getnStopBit = null;
			} else {
				getnStopBit = Integer.valueOf(retValue.get("getnStopBit")
						.toString());
			}
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetSerialPortParamEvent(getnAddress,
						getnBaudRate, getnDataBit, getnParity, getnPTZProtocol,
						getnStopBit, channel, context);
			}
		}
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetVideoEffect")) {
			Integer m_nBirghtness;
			if (retValue.get("m_nBirghtness").toString().equals("")) {
				m_nBirghtness = null;
			} else {
				m_nBirghtness = Integer.valueOf(retValue.get("m_nBirghtness")
						.toString());
			}
			Integer m_nContrast;
			if (retValue.get("m_nContrast").toString().equals("")) {
				m_nContrast = null;
			} else {
				m_nContrast = Integer.valueOf(retValue.get("m_nContrast")
						.toString());
			}
			Integer m_nHue;
			if (retValue.get("m_nHue").toString().equals("")) {
				m_nHue = null;
			} else {
				m_nHue = Integer.valueOf(retValue.get("m_nHue").toString());
			}
			Integer m_nSaturation;
			if (retValue.get("m_nSaturation").toString().equals("")) {
				m_nSaturation = null;
			} else {
				m_nSaturation = Integer.valueOf(retValue.get("m_nSaturation")
						.toString());
			}
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetVideoEffectEvent(m_nBirghtness,
						m_nContrast, m_nHue, m_nSaturation, channel, context);
			}
		}
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetVideoCompress")) {
			Integer m_nBitrate;
			if (retValue.get("m_nBitrate").toString().equals("")) {
				m_nBitrate = null;
			} else {
				m_nBitrate = Integer.valueOf(retValue.get("m_nBitrate")
						.toString());
			}
			Integer m_nCodecType;
			if (retValue.get("m_nCodecType").toString().equals("")) {
				m_nCodecType = null;
			} else {
				m_nCodecType = Integer.valueOf(retValue.get("m_nCodecType")
						.toString());
			}
			Integer m_nFramerate;
			if (retValue.get("m_nFramerate").toString().equals("")) {
				m_nFramerate = null;
			} else {
				m_nFramerate = Integer.valueOf(retValue.get("m_nFramerate")
						.toString());
			}
			Integer m_nQuality;
			if (retValue.get("m_nQuality").toString().equals("")) {
				m_nQuality = null;
			} else {
				m_nQuality = Integer.valueOf(retValue.get("m_nQuality")
						.toString());
			}
			Integer m_nResolution;
			if (retValue.get("m_nResolution").toString().equals("")) {
				m_nResolution = null;
			} else {
				m_nResolution = Integer.valueOf(retValue.get("m_nResolution")
						.toString());
			}
			Integer channel;
			if (retValue.get("channel").toString().equals("")) {
				channel = null;
			} else {
				channel = Integer.valueOf(retValue.get("channel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetVideoCompressEvent(m_nBitrate,
						m_nCodecType, m_nFramerate, m_nQuality, m_nResolution,
						channel, context);
			}
		}
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetOSD")) {
			Integer posX;
			if (retValue.get("posX").toString().equals("")) {
				posX = null;
			} else {
				posX = Integer.valueOf(retValue.get("posX").toString());
			}
			Integer posY;
			if (retValue.get("posY").toString().equals("")) {
				posY = null;
			} else {
				posY = Integer.valueOf(retValue.get("posY").toString());
			}
			Boolean showOSD;
			if (retValue.get("showOSD").toString().equals("")) {
				showOSD = null;
			} else {
				showOSD = Boolean.valueOf(retValue.get("showOSD").toString());
			}
			Boolean showTime;
			if (retValue.get("showTime").toString().equals("")) {
				showTime = null;
			} else {
				showTime = Boolean.valueOf(retValue.get("showTime").toString());
			}
			String oSDContent = retValue.get("oSDContent").toString();
			Integer nChannel;
			if (retValue.get("nChannel").toString().equals("")) {
				nChannel = null;
			} else {
				nChannel = Integer.valueOf(retValue.get("nChannel").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetOSDEvent(posX, posY, showOSD, showTime,
						oSDContent, nChannel, context);
			}
		}
		
		
		
		
		
		
		
		
		
		//////////////////////////获取解码卡信息//////////////////////////
		//////////////////////////gjj 2012.10.29/////////////////////////
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetHardwareInitInfo")) {
			Integer _succeedDecodeChannelCount;
			if (retValue.get("_succeedDecodeChannelCount").toString()
					.equals("")) {
				_succeedDecodeChannelCount = null;
			} else {
				_succeedDecodeChannelCount = Integer.valueOf(retValue.get(
						"_succeedDecodeChannelCount").toString());
			}
			Integer _succeedInitDspCount;
			if (retValue.get("_succeedInitDspCount").toString().equals("")) {
				_succeedInitDspCount = null;
			} else {
				_succeedInitDspCount = Integer.valueOf(retValue.get(
						"_succeedInitDspCount").toString());
			}
			Integer _succeedDisplayChannelCount;
			if (retValue.get("_succeedDisplayChannelCount").toString()
					.equals("")) {
				_succeedDisplayChannelCount = null;
			} else {
				_succeedDisplayChannelCount = Integer.valueOf(retValue.get(
						"_succeedDisplayChannelCount").toString());
			}
			Integer _decodeCardCount;
			if (retValue.get("_decodeCardCount").toString().equals("")) {
				_decodeCardCount = null;
			} else {
				_decodeCardCount = Integer.valueOf(retValue.get(
						"_decodeCardCount").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetHardwareInitInfo(
						_succeedDecodeChannelCount, _succeedInitDspCount,
						_succeedDisplayChannelCount, _decodeCardCount, context);
			}
		}

		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetDisplayRelatedDecodeIndex")) {
			Integer DisplayRelatedDecodeIndex;
			if (retValue.get("DisplayRelatedDecodeIndex").toString().equals("")) {
				DisplayRelatedDecodeIndex = null;
			} else {
				DisplayRelatedDecodeIndex = Integer.valueOf(retValue
						.get("DisplayRelatedDecodeIndex").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetDisplayRelatedDecodeIndex(
						DisplayRelatedDecodeIndex, context);
			}
		}

		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetIsDecode")) {
			Integer IsDecode;
			if (retValue.get("IsDecode").toString().equals("")) {
				IsDecode = null;
			} else {
				IsDecode = Integer.valueOf(retValue.get("IsDecode").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetIsDecode(IsDecode, context);
			}
		}

		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetIsTvAreaOut")) {
			Integer IsTvAreaOut;
			if (retValue.get("IsTvAreaOut").toString().equals("")) {
				IsTvAreaOut = null;
			} else {
				IsTvAreaOut = Integer.valueOf(retValue.get("IsTvAreaOut")
						.toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetIsTvAreaOut(IsTvAreaOut, context);
			}
		}

		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetIsTvOut")) {
			Integer IsTvOut;
			if (retValue.get("IsTvOut").toString().equals("")) {
				IsTvOut = null;
			} else {
				IsTvOut = Integer.valueOf(retValue.get("IsTvOut").toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetIsTvOut(IsTvOut, context);
			}
		}

		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetTvSplitCount")) {
			Integer TvSplitCount;
			if (retValue.get("TvSplitCount").toString().equals("")) {
				TvSplitCount = null;
			} else {
				TvSplitCount = Integer.valueOf(retValue.get("TvSplitCount")
						.toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetTvSplitCount(TvSplitCount, context);
			}
		}

		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetVedioSource")) {
			String _sourceIp = retValue.get("_sourceIp").toString();
			Integer _sourcePort;
			if (retValue.get("_sourcePort").toString().equals("")) {
				_sourcePort = null;
			} else {
				_sourcePort = Integer.valueOf(retValue.get("_sourcePort")
						.toString());
			}
			Integer _sourceChannel;
			if (retValue.get("_sourceChannel").toString().equals("")) {
				_sourceChannel = null;
			} else {
				_sourceChannel = Integer.valueOf(retValue.get("_sourceChannel")
						.toString());
			}
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler.onGetVedioSource(_sourceIp, _sourcePort,
						_sourceChannel, context);
			}
		}
		
		
		
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetDisplayChannelInfo")) {
			Integer nIsDisplayChannelPlay;
			if (retValue.get("nIsDisplayChannelPlay").toString()
					.equals("")) {
				nIsDisplayChannelPlay = null;
			} else {
				nIsDisplayChannelPlay = Integer.valueOf(retValue.get(
						"nIsDisplayChannelPlay").toString());
			}
			Integer splitNum;
			if (retValue.get("splitNum").toString().equals("")) {
				splitNum = null;
			} else {
				splitNum = Integer.valueOf(retValue.get(
						"splitNum").toString());
			}
			String area0 = retValue.get("area0").toString();
			
			String context = retValue.get("context").toString();
			if (this.eventhandler != null) {
				this.eventhandler
						.onGetDisplayChannelInfo(nIsDisplayChannelPlay,
								splitNum, area0, context);
			}
		}
	}

	public void ReturnFunction(HashMap<String, Object> retValue, String EventURL) {
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.ServerSetAddress")) {
			if (this.funhandler != null) {
				this.funhandler.ServerSetAddressResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.ServerStart")) {
			if (this.funhandler != null) {
				this.funhandler.ServerStartResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.ServerStop")) {
			if (this.funhandler != null) {
				this.funhandler.ServerStopResult();
			}
		}
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.GetChannelNumbers")) {
			if (this.funhandler != null) {
				this.funhandler.GetChannelNumbersResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.SetChannelName")) {
			if (this.funhandler != null) {
				this.funhandler.SetChannelNameResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.GetChannelName")) {
			if (this.funhandler != null) {
				this.funhandler.GetChannelNameResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.MakeKeyFrame")) {
			if (this.funhandler != null) {
				this.funhandler.MakeKeyFrameResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.ControlPTZ")) {
			if (this.funhandler != null) {
				this.funhandler.ControlPTZResult();
			}
		}
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.SetSerialPortParam")) {
			if (this.funhandler != null) {
				this.funhandler.SetSerialPortParamResult();
			}
		}
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.GetSerialPortParam")) {
			if (this.funhandler != null) {
				this.funhandler.GetSerialPortParamResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.SetOSD")) {
			if (this.funhandler != null) {
				this.funhandler.SetOSDResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.GetOSD")) {
			if (this.funhandler != null) {
				this.funhandler.GetOSDResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.SetText")) {
			if (this.funhandler != null) {
				this.funhandler.SetTextResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.SetVideoEffect")) {
			if (this.funhandler != null) {
				this.funhandler.SetVideoEffectResult();
			}
		}
		if (EventURL.equals("VideoServerDomain.VideoServerCtrl.GetVideoEffect")) {
			if (this.funhandler != null) {
				this.funhandler.GetVideoEffectResult();
			}
		}
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.SetVideoCompress")) {
			if (this.funhandler != null) {
				this.funhandler.SetVideoCompressResult();
			}
		}
		if (EventURL
				.equals("VideoServerDomain.VideoServerCtrl.GetVideoCompress")) {
			if (this.funhandler != null) {
				this.funhandler.GetVideoCompressResult();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		//////////////////////////获取解码卡信息//////////////////////////
		//////////////////////////gjj 2012.10.29/////////////////////////
		if (EventURL
				.equals("VideoServerDomain.IVideoServerCtrl.onGetHardwareInitInfo")) {
			if (this.funhandler != null) {
				this.funhandler.GetHardwareInitInfoResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetDisplayRelatedDecodeIndex")) {
			if (this.funhandler != null) {
				this.funhandler.GetDisplayRelatedDecodeIndexResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetIsDecode")) {
			if (this.funhandler != null) {
				this.funhandler.GetIsDecodeResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetIsTvAreaOut")) {
			if (this.funhandler != null) {
				this.funhandler.GetIsTvAreaOutResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetIsTvOut")) {
			if (this.funhandler != null) {
				this.funhandler.GetIsTvOutResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetTvSplitCount")) {
			if (this.funhandler != null) {
				this.funhandler.GetTvSplitCountResult();
			}
		}
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetVedioSource")) {
			if (this.funhandler != null) {
				this.funhandler.GetVedioSourceResult();
			}
		}
		
		
		if (EventURL.equals("VideoServerDomain.IVideoServerCtrl.onGetDisplayChannelInfo")) {
			if (this.funhandler != null) {
				this.funhandler.GetDisplayChannelInfoResult();
			}
		}
	}

	public void ServerSetAddress(java.lang.String szAddress) {
		String url = "VideoServerDomain.VideoServerCtrl.ServerSetAddress";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("szAddress", szAddress);
		run.Invoke(url, Params, null, null);
	}

	public void ServerStart(java.lang.String client, int nLinkType,
			int nSubLinkType, int nLinkMode, java.lang.String szAddress,
			int nPort, java.lang.String szUsrName, java.lang.String szUsrPass,
			int nDeviceChannelId, int nServerChannelId, java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.ServerStart";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nLinkType", nLinkType);
		Params.put("nSubLinkType", nSubLinkType);
		Params.put("nLinkMode", nLinkMode);
		Params.put("szAddress", szAddress);
		Params.put("nPort", nPort);
		Params.put("szUsrName", szUsrName);
		Params.put("szUsrPass", szUsrPass);
		Params.put("nDeviceChannelId", nDeviceChannelId);
		Params.put("nServerChannelId", nServerChannelId);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void ServerStop(java.lang.String client, java.lang.String nInstance) {
		String url = "VideoServerDomain.VideoServerCtrl.ServerStop";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nInstance", nInstance);
		run.Invoke(url, Params, null, null);
	}

	public void GetChannelNumbers(java.lang.String client,
			java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetChannelNumbers";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void SetChannelName(java.lang.String client,
			java.lang.Integer nChannel, java.lang.Integer nPosX,
			java.lang.Integer nPosY, java.lang.String szName) {
		String url = "VideoServerDomain.VideoServerCtrl.SetChannelName";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nPosX", nPosX);
		Params.put("nPosY", nPosY);
		Params.put("szName", szName);
		run.Invoke(url, Params, null, null);
	}

	public void GetChannelName(java.lang.String client,
			java.lang.Integer nChannel, java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetChannelName";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void MakeKeyFrame(java.lang.String client,
			java.lang.Integer nChannel, java.lang.Integer nCodeStreamType) {
		String url = "VideoServerDomain.VideoServerCtrl.MakeKeyFrame";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nCodeStreamType", nCodeStreamType);
		run.Invoke(url, Params, null, null);
	}

	public void ControlPTZ(java.lang.String client, java.lang.Integer nChannel,
			java.lang.Integer nSpeed, java.lang.Integer nAction,
			java.lang.Integer nValue) {
		String url = "VideoServerDomain.VideoServerCtrl.ControlPTZ";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nSpeed", nSpeed);
		Params.put("nAction", nAction);
		Params.put("nValue", nValue);
		run.Invoke(url, Params, null, null);
	}

	public void SetSerialPortParam(java.lang.String client,
			java.lang.Integer nChannel, java.lang.Integer nAddress,
			java.lang.Integer nBaudRate, java.lang.Integer nDataBit,
			java.lang.Integer nStopBit, java.lang.Integer nParity,
			java.lang.Integer nPTZProtocol) {
		String url = "VideoServerDomain.VideoServerCtrl.SetSerialPortParam";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nAddress", nAddress);
		Params.put("nBaudRate", nBaudRate);
		Params.put("nDataBit", nDataBit);
		Params.put("nStopBit", nStopBit);
		Params.put("nParity", nParity);
		Params.put("nPTZProtocol", nPTZProtocol);
		run.Invoke(url, Params, null, null);
	}

	public void GetSerialPortParam(java.lang.String client,
			java.lang.Integer nChannel, java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetSerialPortParam";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void SetOSD(java.lang.String client, java.lang.Integer nChannel,
			java.lang.Boolean bShowTime, java.lang.Boolean bShowOSD,
			java.lang.Integer nPosX, java.lang.Integer nPosY,
			java.lang.String szOSD) {
		String url = "VideoServerDomain.VideoServerCtrl.SetOSD";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("bShowTime", bShowTime);
		Params.put("bShowOSD", bShowOSD);
		Params.put("nPosX", nPosX);
		Params.put("nPosY", nPosY);
		Params.put("szOSD", szOSD);
		run.Invoke(url, Params, null, null);
	}

	public void GetOSD(java.lang.String client, java.lang.Integer nChannel,
			java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetOSD";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void SetText(java.lang.String client, java.lang.Integer nChannel,
			java.lang.Integer nIndex, java.lang.Integer nPosX,
			java.lang.Integer nPosY, java.lang.String szContent) {
		String url = "VideoServerDomain.VideoServerCtrl.SetText";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nIndex", nIndex);
		Params.put("nPosX", nPosX);
		Params.put("nPosY", nPosY);
		Params.put("szContent", szContent);
		run.Invoke(url, Params, null, null);
	}

	public void SetVideoEffect(java.lang.String client,
			java.lang.Integer nChannel, java.lang.Integer nType,
			java.lang.Integer nValue) {
		String url = "VideoServerDomain.VideoServerCtrl.SetVideoEffect";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("nType", nType);
		Params.put("nValue", nValue);
		run.Invoke(url, Params, null, null);
	}

	public void GetVideoEffect(java.lang.String client,
			java.lang.Integer nChannel, java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetVideoEffect";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}

	public void SetVideoCompress(java.lang.String client,
			java.lang.Integer nChannel, java.lang.Integer m_nFramerate,
			java.lang.Integer m_nBitrate, java.lang.Integer m_nQuality,
			java.lang.Integer m_nResolution, java.lang.Integer m_nCodecType,
			java.lang.Boolean m_bConstBitrate, java.lang.Boolean m_bStdCodec) {
		String url = "VideoServerDomain.VideoServerCtrl.SetVideoCompress";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("m_nFramerate", m_nFramerate);
		Params.put("m_nBitrate", m_nBitrate);
		Params.put("m_nQuality", m_nQuality);
		Params.put("m_nResolution", m_nResolution);
		Params.put("m_nCodecType", m_nCodecType);
		Params.put("m_bConstBitrate", m_bConstBitrate);
		Params.put("m_bStdCodec", m_bStdCodec);
		run.Invoke(url, Params, null, null);
	}

	public void GetVideoCompress(java.lang.String client,
			java.lang.Integer nChannel, java.lang.String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetVideoCompress";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nChannel", nChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////获取解码卡信息//////////////////////////
	//////////////////////////gjj 2012.10.18/////////////////////////
	/**
	 * 获取设备初始化信息，返回结果是否成功，true则为获取HardwareInitInfo成功
	 */
	public void GetHardwareInitInfo(String client,String context
//			,Integer _succeedDecodeChannelCount,
//			Integer _succeedInitDspCount, Integer _succeedDisplayChannelCount,
//			Integer _decodeCardCount
			){
		String url = "VideoServerDomain.VideoServerCtrl.GetHardwareInitInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
//		Params.put("_succeedDecodeChannelCount", _succeedDecodeChannelCount);
//		Params.put("_succeedInitDspCount", _succeedInitDspCount);
//		Params.put("_succeedDisplayChannelCount", _succeedDisplayChannelCount);
//		Params.put("_decodeCardCount", _decodeCardCount);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDisplayIndex  电视索引 ,即显示通道索引 (索引从0开始)
	 * @param nAreaIndex     显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return               返回当前显示通道所分配的解码器索引(索引从0开始)
	 */
	public void GetDisplayRelatedDecodeIndex(String client,
			Integer nDisplayIndex, Integer nAreaIndex, String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetDisplayRelatedDecodeIndex";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisplayIndex", nDisplayIndex);
		Params.put("nAreaIndex", nAreaIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDecodeIndex  解码器索引(索引从0开始)
	 * @return  -1：代表getIsDecode()接口调用失败；
	 *           0：代表当前解码器没有解码；
	 *           1：代表当前解码器正在解码；
	 */
	public void GetIsDecode(String client,Integer nDecodeIndex,String context){
		String url = "VideoServerDomain.VideoServerCtrl.GetIsDecode";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDecodeIndex", nDecodeIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(即，对应电视索引)(索引从0开始)
	 * @param nAreaIndex     显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return   -1：代表getIsTvAreaOut()接口调用失败；
	 *            0：代表当前解码通道的当前显示分区，没有显示输出
	 *            1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public void GetIsTvAreaOut(String client, Integer nDisplayIndex,
			Integer nAreaIndex, String context) {
		String url = "VideoServerDomain.VideoServerCtrl.GetIsTvAreaOut";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisplayIndex", nDisplayIndex);
		Params.put("nAreaIndex", nAreaIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(即，对应电视索引)(索引从0开始)
	 * @return  -1：代表getIsTvOut()接口调用失败；
	 *           0：代表当前解码通道的当前显示分区，没有显示输出
	 *           1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public void GetIsTvOut(String client,Integer nDisplayIndex,String context){
		String url = "VideoServerDomain.VideoServerCtrl.GetIsTvOut";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisplayIndex", nDisplayIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDisplayIndex  解码通道索引(即，对应电视索引)(索引从0开始)
	 * @return   -1:代表getTvAreaCount()接口调用失败；
	 *              其它数字代表当前显示通道的分屏数
	 */
	public void GetTvSplitCount(String client,Integer nDisplayIndex,String context){
		String url = "VideoServerDomain.VideoServerCtrl.GetTvSplitCount";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisplayIndex", nDisplayIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	/**
	 * 
	 * @param nDisplayIndex  显示通道索引(索引从0开始)
	 * @param nArea          显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @param vSrc           显示通道对应的通道源信息
	 * @return
	 */
	public void GetVedioSource(String client,Integer nDisplayIndex, Integer nArea,String context
//			, String _sourceIp,
//			Integer _sourcePort, Integer _sourceChannel
			) {
		String url = "VideoServerDomain.VideoServerCtrl.GetVedioSource";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisplayIndex", nDisplayIndex);
		Params.put("nArea", nArea);
//		Params.put("_sourceIp", _sourceIp);
//		Params.put("_sourcePort", _sourcePort);
//		Params.put("_sourceChannel", _sourceChannel);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
	
	
	
	
	
	public void GetDisplayChannelInfo(String client,Integer nDisIndex,String context){
		String url = "VideoServerDomain.VideoServerCtrl.GetDisplayChannelInfo";
		HashMap<String, Object> Params = new HashMap<String, Object>();
		Params.put("client", client);
		Params.put("nDisIndex", nDisIndex);
		Params.put("context", context);
		run.Invoke(url, Params, null, null);
	}
}
