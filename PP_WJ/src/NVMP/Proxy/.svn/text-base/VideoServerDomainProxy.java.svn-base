package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class VideoServerDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage ServerSetAddress(java.lang.String szAddress) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ServerSetAddress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("szAddress", szAddress); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ServerStart(java.lang.String client,int nLinkType,int nSubLinkType,int nLinkMode,java.lang.String szAddress,int nPort,java.lang.String szUsrName,java.lang.String szUsrPass,int nDeviceChannelId,int nServerChannelId,java.lang.String context) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ServerStop(java.lang.String client,java.lang.String nInstance) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ServerStop"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nInstance", nInstance); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetChannelNumbers(java.lang.String client,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetChannelNumbers"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetChannelName(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szName) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetChannelName"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szName", szName); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetChannelName(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetChannelName"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage MakeKeyFrame(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nCodeStreamType) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.MakeKeyFrame"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nCodeStreamType", nCodeStreamType); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ControlPTZ(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nSpeed,java.lang.Integer nAction,java.lang.Integer nValue) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ControlPTZ"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nSpeed", nSpeed); 
Params.put("nAction", nAction); 
Params.put("nValue", nValue); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetSerialPortParam(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nAddress,java.lang.Integer nBaudRate,java.lang.Integer nDataBit,java.lang.Integer nStopBit,java.lang.Integer nParity,java.lang.Integer nPTZProtocol) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetSerialPortParam(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetSerialPortParam"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetOSD(java.lang.String client,java.lang.Integer nChannel,java.lang.Boolean bShowTime,java.lang.Boolean bShowOSD,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szOSD) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("bShowTime", bShowTime); 
Params.put("bShowOSD", bShowOSD); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szOSD", szOSD); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetOSD(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetText(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nIndex,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szContent) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetText"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nIndex", nIndex); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szContent", szContent); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetVideoEffect(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nType,java.lang.Integer nValue) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetVideoEffect"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nType", nType); 
Params.put("nValue", nValue); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetVideoEffect(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetVideoEffect"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetVideoCompress(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer m_nFramerate,java.lang.Integer m_nBitrate,java.lang.Integer m_nQuality,java.lang.Integer m_nResolution,java.lang.Integer m_nCodecType,java.lang.Boolean m_bConstBitrate,java.lang.Boolean m_bStdCodec) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetVideoCompress(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetVideoCompress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ServerSetAddress_Copy(java.lang.String szAddress) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ServerSetAddress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("szAddress", szAddress); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ServerStart_Copy(java.lang.String client,int nLinkType,int nSubLinkType,int nLinkMode,java.lang.String szAddress,int nPort,java.lang.String szUsrName,java.lang.String szUsrPass,int nDeviceChannelId,int nServerChannelId,java.lang.String context) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ServerStop_Copy(java.lang.String client,java.lang.String nInstance) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ServerStop"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nInstance", nInstance); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetChannelNumbers_Copy(java.lang.String client,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetChannelNumbers"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetChannelName_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szName) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetChannelName"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szName", szName); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetChannelName_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetChannelName"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage MakeKeyFrame_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nCodeStreamType) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.MakeKeyFrame"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nCodeStreamType", nCodeStreamType); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ControlPTZ_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nSpeed,java.lang.Integer nAction,java.lang.Integer nValue) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.ControlPTZ"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nSpeed", nSpeed); 
Params.put("nAction", nAction); 
Params.put("nValue", nValue); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetSerialPortParam_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nAddress,java.lang.Integer nBaudRate,java.lang.Integer nDataBit,java.lang.Integer nStopBit,java.lang.Integer nParity,java.lang.Integer nPTZProtocol) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetSerialPortParam_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetSerialPortParam"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetOSD_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Boolean bShowTime,java.lang.Boolean bShowOSD,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szOSD) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("bShowTime", bShowTime); 
Params.put("bShowOSD", bShowOSD); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szOSD", szOSD); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetOSD_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetText_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nIndex,java.lang.Integer nPosX,java.lang.Integer nPosY,java.lang.String szContent) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetText"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nIndex", nIndex); 
Params.put("nPosX", nPosX); 
Params.put("nPosY", nPosY); 
Params.put("szContent", szContent); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetVideoEffect_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer nType,java.lang.Integer nValue) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.SetVideoEffect"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("nType", nType); 
Params.put("nValue", nValue); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetVideoEffect_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetVideoEffect"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetVideoCompress_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.Integer m_nFramerate,java.lang.Integer m_nBitrate,java.lang.Integer m_nQuality,java.lang.Integer m_nResolution,java.lang.Integer m_nCodecType,java.lang.Boolean m_bConstBitrate,java.lang.Boolean m_bStdCodec) 
 { 
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
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetVideoCompress_Copy(java.lang.String client,java.lang.Integer nChannel,java.lang.String context) 
 { 
  String url = "VideoServerDomain.VideoServerCtrl.GetVideoCompress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("client", client); 
Params.put("nChannel", nChannel); 
Params.put("context", context); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }