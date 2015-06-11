package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class StateManageDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage SetCommandEncodeDeviceOnline(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetCommandEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetEncodeDeviceOnline(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetAlarmState(java.lang.String TerminalId,java.lang.Integer ChannelId,java.lang.Integer AlarmType,java.lang.Integer DeviceStatus) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetAlarmState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("ChannelId", ChannelId); 
Params.put("AlarmType", AlarmType); 
Params.put("DeviceStatus", DeviceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ControlAlarmState(java.lang.String TerminalId,java.lang.Integer index,java.lang.Integer AlarmType,java.lang.Boolean IsStart) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlAlarmState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("index", index); 
Params.put("AlarmType", AlarmType); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ControlCameraPTZ(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer Direction,java.lang.Integer Speed,java.lang.Boolean IsStart) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlCameraPTZ"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("Direction", Direction); 
Params.put("Speed", Speed); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ControlVideoQuality(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer Type,java.lang.Integer Value) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlVideoQuality"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("Type", Type); 
Params.put("Value", Value); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage selectPrePoint(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.selectPrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage setPrePoint(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setPrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage deletePrePoint(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.deletePrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ControlVideoOSD(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer X,java.lang.Integer Y,java.lang.String OSDName,java.lang.Boolean IsDisplyDatetime) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlVideoOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("X", X); 
Params.put("Y", Y); 
Params.put("OSDName", OSDName); 
Params.put("IsDisplyDatetime", IsDisplyDatetime); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage FireDeviceShow(java.lang.String DeviceId,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.FireDeviceShow"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalFireDeviceShow(java.lang.String DeviceId,java.lang.Integer index,java.lang.String CenterID) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalFireDeviceShow"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
Params.put("CenterID", CenterID); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetEncodeDeviceInfo(java.lang.String ClientId,java.lang.String DeviceId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RetrunEncodeDeviceInfoResult(java.lang.String ClientId,java.lang.String DeviceId,java.lang.String Produce,java.lang.String type,java.lang.String IP) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.RetrunEncodeDeviceInfoResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("Produce", Produce); 
Params.put("type", type); 
Params.put("IP", IP); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetVideoCompress(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetVideoCompress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RetrunGetVideoCompressResult(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer Brightness,java.lang.Integer Saturation,java.lang.Integer Hue,java.lang.Integer Contrast) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.RetrunGetVideoCompressResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("Brightness", Brightness); 
Params.put("Saturation", Saturation); 
Params.put("Hue", Hue); 
Params.put("Contrast", Contrast); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetEncodeDeviceAlarm(java.lang.String sessionid,java.lang.String TerminalId,java.lang.Integer ChannelId,java.lang.Integer AlarmType) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceAlarm"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("TerminalId", TerminalId); 
Params.put("ChannelId", ChannelId); 
Params.put("AlarmType", AlarmType); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetSingerEncodeDeviceOnline(java.lang.String sessionid,java.lang.String TerminalId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetSingerEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("TerminalId", TerminalId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetEncodeDeviceOnline(java.lang.String UserId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UserId", UserId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage AddAlarmGroup(java.lang.String UserId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.AddAlarmGroup"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UserId", UserId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalSetEncodeDeviceOnline(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalSetEncodeDeviceOnline_Sync(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline_Sync"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetDeviceTime(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer scond) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetDeviceTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("scond", scond); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage LoginState(java.lang.String sessionid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.LoginState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetDeviceOutLine(java.lang.String Client,java.lang.String Deviceid,java.lang.String center) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetDeviceOutLine"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Client", Client); 
Params.put("Deviceid", Deviceid); 
Params.put("center", center); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getResolution(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getResolution"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage setResolution(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nResolutionX,java.lang.Integer nResolutionY) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setResolution"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nResolutionX", nResolutionX); 
Params.put("nResolutionY", nResolutionY); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage returnResolutionResult(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nResolutionX,java.lang.Integer nResolutionY) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnResolutionResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nResolutionX", nResolutionX); 
Params.put("nResolutionY", nResolutionY); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getFrameRate(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getFrameRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage setFrameRate(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nFramerate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setFrameRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nFramerate", nFramerate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage returnFrameRateResult(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nFramerate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnFrameRateResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nFramerate", nFramerate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getBitRate(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getBitRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage setBitRate(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nBitrate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setBitRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nBitrate", nBitrate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage returnBitRateResult(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nBitrate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnBitRateResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nBitrate", nBitrate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getFrameInterval(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage setFrameInterval(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nGovLength) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nGovLength", nGovLength); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage returnFrameInterval(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nGovLength) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nGovLength", nGovLength); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalSetDeviceOutLine(java.lang.String Client,java.lang.String Deviceid,java.lang.String center) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetDeviceOutLine"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Client", Client); 
Params.put("Deviceid", Deviceid); 
Params.put("center", center); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetCommandEncodeDeviceOnline_Copy(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetCommandEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetEncodeDeviceOnline_Copy(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetAlarmState_Copy(java.lang.String TerminalId,java.lang.Integer ChannelId,java.lang.Integer AlarmType,java.lang.Integer DeviceStatus) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetAlarmState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("ChannelId", ChannelId); 
Params.put("AlarmType", AlarmType); 
Params.put("DeviceStatus", DeviceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ControlAlarmState_Copy(java.lang.String TerminalId,java.lang.Integer index,java.lang.Integer AlarmType,java.lang.Boolean IsStart) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlAlarmState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("index", index); 
Params.put("AlarmType", AlarmType); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ControlCameraPTZ_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer Direction,java.lang.Integer Speed,java.lang.Boolean IsStart) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlCameraPTZ"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("Direction", Direction); 
Params.put("Speed", Speed); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ControlVideoQuality_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer Type,java.lang.Integer Value) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlVideoQuality"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("Type", Type); 
Params.put("Value", Value); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage selectPrePoint_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.selectPrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage setPrePoint_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setPrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage deletePrePoint_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.deletePrePoint"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ControlVideoOSD_Copy(java.lang.String TerminalId,java.lang.Integer Cameraindex,java.lang.Integer X,java.lang.Integer Y,java.lang.String OSDName,java.lang.Boolean IsDisplyDatetime) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.ControlVideoOSD"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("Cameraindex", Cameraindex); 
Params.put("X", X); 
Params.put("Y", Y); 
Params.put("OSDName", OSDName); 
Params.put("IsDisplyDatetime", IsDisplyDatetime); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage FireDeviceShow_Copy(java.lang.String DeviceId,java.lang.Integer index) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.FireDeviceShow"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalFireDeviceShow_Copy(java.lang.String DeviceId,java.lang.Integer index,java.lang.String CenterID) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalFireDeviceShow"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DeviceId", DeviceId); 
Params.put("index", index); 
Params.put("CenterID", CenterID); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetEncodeDeviceInfo_Copy(java.lang.String ClientId,java.lang.String DeviceId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RetrunEncodeDeviceInfoResult_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.String Produce,java.lang.String type,java.lang.String IP) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.RetrunEncodeDeviceInfoResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("Produce", Produce); 
Params.put("type", type); 
Params.put("IP", IP); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetVideoCompress_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetVideoCompress"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RetrunGetVideoCompressResult_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer Brightness,java.lang.Integer Saturation,java.lang.Integer Hue,java.lang.Integer Contrast) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.RetrunGetVideoCompressResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("Brightness", Brightness); 
Params.put("Saturation", Saturation); 
Params.put("Hue", Hue); 
Params.put("Contrast", Contrast); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetEncodeDeviceAlarm_Copy(java.lang.String sessionid,java.lang.String TerminalId,java.lang.Integer ChannelId,java.lang.Integer AlarmType) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceAlarm"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("TerminalId", TerminalId); 
Params.put("ChannelId", ChannelId); 
Params.put("AlarmType", AlarmType); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetSingerEncodeDeviceOnline_Copy(java.lang.String sessionid,java.lang.String TerminalId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetSingerEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("TerminalId", TerminalId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetEncodeDeviceOnline_Copy(java.lang.String UserId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UserId", UserId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage AddAlarmGroup_Copy(java.lang.String UserId) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.AddAlarmGroup"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UserId", UserId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalSetEncodeDeviceOnline_Copy(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalSetEncodeDeviceOnline_Sync_Copy(java.lang.String TerminalId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.String xml,java.lang.String centerid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetEncodeDeviceOnline_Sync"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("xml", xml); 
Params.put("centerid", centerid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetDeviceTime_Copy(java.lang.Integer year,java.lang.Integer month,java.lang.Integer day,java.lang.Integer hour,java.lang.Integer minute,java.lang.Integer scond) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetDeviceTime"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("year", year); 
Params.put("month", month); 
Params.put("day", day); 
Params.put("hour", hour); 
Params.put("minute", minute); 
Params.put("scond", scond); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage LoginState_Copy(java.lang.String sessionid) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.LoginState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetDeviceOutLine_Copy(java.lang.String Client,java.lang.String Deviceid,java.lang.String center) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.SetDeviceOutLine"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Client", Client); 
Params.put("Deviceid", Deviceid); 
Params.put("center", center); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getResolution_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getResolution"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage setResolution_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nResolutionX,java.lang.Integer nResolutionY) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setResolution"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nResolutionX", nResolutionX); 
Params.put("nResolutionY", nResolutionY); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage returnResolutionResult_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nResolutionX,java.lang.Integer nResolutionY) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnResolutionResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nResolutionX", nResolutionX); 
Params.put("nResolutionY", nResolutionY); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getFrameRate_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getFrameRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage setFrameRate_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nFramerate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setFrameRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nFramerate", nFramerate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage returnFrameRateResult_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nFramerate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnFrameRateResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nFramerate", nFramerate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getBitRate_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getBitRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage setBitRate_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nBitrate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setBitRate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nBitrate", nBitrate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage returnBitRateResult_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nBitrate) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnBitRateResult"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nBitrate", nBitrate); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getFrameInterval_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.getFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage setFrameInterval_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nGovLength) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.setFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nGovLength", nGovLength); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage returnFrameInterval_Copy(java.lang.String ClientId,java.lang.String DeviceId,java.lang.Integer channl,java.lang.Integer nGovLength) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.returnFrameInterval"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ClientId", ClientId); 
Params.put("DeviceId", DeviceId); 
Params.put("channl", channl); 
Params.put("nGovLength", nGovLength); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalSetDeviceOutLine_Copy(java.lang.String Client,java.lang.String Deviceid,java.lang.String center) 
 { 
  String url = "StateManageDomain.EncodeDeviceManage.GrobalSetDeviceOutLine"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("Client", Client); 
Params.put("Deviceid", Deviceid); 
Params.put("center", center); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }