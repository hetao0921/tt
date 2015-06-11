package NVMP.Proxy;
import java.util.HashMap;
import corenet.rpc.BaseMessage;
import NVMP.AppService.Interface.IAppRuntime;
import corenet.rpc.IMessage;
public class CommandDomainProxy { 
private static IAppRuntime _AppRuntime = null; 
public void load(IAppRuntime AppRuntime) { 
	_AppRuntime = AppRuntime; 
 } 
  static public IMessage SendInfo(java.lang.String SourceCommanderId,java.lang.String DestCommanderId,java.lang.String info) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SourceCommanderId", SourceCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("info", info); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetVersion(java.lang.String clientid,java.lang.String version) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetVersion"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
Params.put("version", version); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage EnterFree(java.lang.String CommanderId) 
 { 
  String url = "CommandDomain.CommanderBusiness.EnterFree"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommanderId", CommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetInitStatus(java.lang.String TerminalId) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetInitStatus"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetCommanderLoginOk(java.lang.String TerminalId,java.lang.String CommanderId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SetCommanderLoginOk"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("CommanderId", CommanderId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("CameraNum", CameraNum); 
Params.put("UserName", UserName); 
Params.put("Camera1Name", Camera1Name); 
Params.put("Camera2Name", Camera2Name); 
Params.put("Camera3Name", Camera3Name); 
Params.put("Camera4Name", Camera4Name); 
Params.put("CenterId", CenterId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SendCommanderState(java.lang.String DestDeviceId,java.lang.String CommanderId,java.lang.String DeviceId,java.lang.String GroupId,java.lang.String DeptId,java.lang.String WorkStatus,java.lang.Boolean IsOnline,java.lang.String WorkMode,java.lang.Boolean IsUpCut,java.lang.Boolean IsDownCut,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendCommanderState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestDeviceId", DestDeviceId); 
Params.put("CommanderId", CommanderId); 
Params.put("DeviceId", DeviceId); 
Params.put("GroupId", GroupId); 
Params.put("DeptId", DeptId); 
Params.put("WorkStatus", WorkStatus); 
Params.put("IsOnline", IsOnline); 
Params.put("WorkMode", WorkMode); 
Params.put("IsUpCut", IsUpCut); 
Params.put("IsDownCut", IsDownCut); 
Params.put("CameraNum", CameraNum); 
Params.put("UserName", UserName); 
Params.put("Camera1Name", Camera1Name); 
Params.put("Camera2Name", Camera2Name); 
Params.put("Camera3Name", Camera3Name); 
Params.put("Camera4Name", Camera4Name); 
Params.put("CenterId", CenterId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage getCommandServerOnline(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommanderBusiness.getCommandServerOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateUpCut(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateUpCut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateCommanderP2PCommunicate(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommanderP2PCommunicate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateP2PVoice(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateP2PVoice"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage EnterCommandGroup(java.lang.String CommandGroupId,java.lang.String CommanderId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.EnterCommandGroup"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("CommanderId", CommanderId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ResponeCooperate(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsAgree) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.ResponeCooperate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsAgree", IsAgree); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateCommandCall(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandCall"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RequestCooperate(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.RequestCooperate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateDownCut(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateDownCut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateVideoeAssign(java.lang.String DevicerId,java.lang.Integer Index,java.lang.String DestCommander,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateVideoeAssign"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DevicerId", DevicerId); 
Params.put("Index", Index); 
Params.put("DestCommander", DestCommander); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateBroadcast(java.lang.String SendCommanderId,java.lang.String CommandGroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateBroadcast"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateCommandReplacemente(java.lang.String DownCommanderId,java.lang.String UpCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandReplacemente"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateCommandAccredit(java.lang.String UpCommanderId,java.lang.String DownCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandAccredit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ForceQuit(java.lang.String ConferenceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ForceQuit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("DestId", DestId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ConferenceOver(java.lang.String ConferenceId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceOver"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage EnterConference(java.lang.String commenderId,java.lang.String ConferenceId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.EnterConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("commenderId", commenderId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage CreateConference(java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.CreateConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SendCreateConference(java.lang.String DestId,java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.SendCreateConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestId", DestId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperteAppointSpokesman(java.lang.String ConferenceId,java.lang.String Spokeman,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperteAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("Spokeman", Spokeman); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage OperateDiscuss(java.lang.String ConferenceId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperateDiscuss"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ConferenceCall(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceCall"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("DestId", DestId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage RequestAppointSpokesman(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.RequestAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage ResponeAppointSpokesman(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId,java.lang.Boolean IsAgreen) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ResponeAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
Params.put("IsAgreen", IsAgreen); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage EnterConsultation(java.lang.String CommanderId,java.lang.String ConsultationId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConsultationGroupBusiness.EnterConsultation"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommanderId", CommanderId); 
Params.put("ConsultationId", ConsultationId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SetUserOnline(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String groupid) 
 { 
  String url = "CommandDomain.CommandStateManage.SetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("groupid", groupid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GetUserOnline(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommandStateManage.GetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage GrobalSetUserOnline(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String centerid,java.lang.String groupid,java.lang.Boolean flag) 
 { 
  String url = "CommandDomain.CommandStateManage.GrobalSetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("centerid", centerid); 
Params.put("groupid", groupid); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return _AppRuntime.Dispatch(Message); 
 } 
   static public IMessage SendInfo_Copy(java.lang.String SourceCommanderId,java.lang.String DestCommanderId,java.lang.String info) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendInfo"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SourceCommanderId", SourceCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("info", info); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetVersion_Copy(java.lang.String clientid,java.lang.String version) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetVersion"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
Params.put("version", version); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage EnterFree_Copy(java.lang.String CommanderId) 
 { 
  String url = "CommandDomain.CommanderBusiness.EnterFree"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommanderId", CommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetInitStatus_Copy(java.lang.String TerminalId) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetInitStatus"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetCommanderLoginOk_Copy(java.lang.String TerminalId,java.lang.String CommanderId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SetCommanderLoginOk"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("TerminalId", TerminalId); 
Params.put("CommanderId", CommanderId); 
Params.put("TerminalIP", TerminalIP); 
Params.put("IsOnline", IsOnline); 
Params.put("DeviceType", DeviceType); 
Params.put("DevuceSubType", DevuceSubType); 
Params.put("CameraNum", CameraNum); 
Params.put("UserName", UserName); 
Params.put("Camera1Name", Camera1Name); 
Params.put("Camera2Name", Camera2Name); 
Params.put("Camera3Name", Camera3Name); 
Params.put("Camera4Name", Camera4Name); 
Params.put("CenterId", CenterId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SendCommanderState_Copy(java.lang.String DestDeviceId,java.lang.String CommanderId,java.lang.String DeviceId,java.lang.String GroupId,java.lang.String DeptId,java.lang.String WorkStatus,java.lang.Boolean IsOnline,java.lang.String WorkMode,java.lang.Boolean IsUpCut,java.lang.Boolean IsDownCut,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendCommanderState"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestDeviceId", DestDeviceId); 
Params.put("CommanderId", CommanderId); 
Params.put("DeviceId", DeviceId); 
Params.put("GroupId", GroupId); 
Params.put("DeptId", DeptId); 
Params.put("WorkStatus", WorkStatus); 
Params.put("IsOnline", IsOnline); 
Params.put("WorkMode", WorkMode); 
Params.put("IsUpCut", IsUpCut); 
Params.put("IsDownCut", IsDownCut); 
Params.put("CameraNum", CameraNum); 
Params.put("UserName", UserName); 
Params.put("Camera1Name", Camera1Name); 
Params.put("Camera2Name", Camera2Name); 
Params.put("Camera3Name", Camera3Name); 
Params.put("Camera4Name", Camera4Name); 
Params.put("CenterId", CenterId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage getCommandServerOnline_Copy(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommanderBusiness.getCommandServerOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateUpCut_Copy(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateUpCut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateCommanderP2PCommunicate_Copy(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommanderP2PCommunicate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateP2PVoice_Copy(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateP2PVoice"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage EnterCommandGroup_Copy(java.lang.String CommandGroupId,java.lang.String CommanderId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.EnterCommandGroup"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("CommanderId", CommanderId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ResponeCooperate_Copy(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsAgree) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.ResponeCooperate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsAgree", IsAgree); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateCommandCall_Copy(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandCall"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RequestCooperate_Copy(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.RequestCooperate"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateDownCut_Copy(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateDownCut"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateVideoeAssign_Copy(java.lang.String DevicerId,java.lang.Integer Index,java.lang.String DestCommander,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateVideoeAssign"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DevicerId", DevicerId); 
Params.put("Index", Index); 
Params.put("DestCommander", DestCommander); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateBroadcast_Copy(java.lang.String SendCommanderId,java.lang.String CommandGroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateBroadcast"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateCommandReplacemente_Copy(java.lang.String DownCommanderId,java.lang.String UpCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandReplacemente"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateCommandAccredit_Copy(java.lang.String UpCommanderId,java.lang.String DownCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandAccredit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ForceQuit_Copy(java.lang.String ConferenceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ForceQuit"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("DestId", DestId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ConferenceOver_Copy(java.lang.String ConferenceId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceOver"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage EnterConference_Copy(java.lang.String commenderId,java.lang.String ConferenceId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.EnterConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("commenderId", commenderId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage CreateConference_Copy(java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.CreateConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SendCreateConference_Copy(java.lang.String DestId,java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.SendCreateConference"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("DestId", DestId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperteAppointSpokesman_Copy(java.lang.String ConferenceId,java.lang.String Spokeman,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperteAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("Spokeman", Spokeman); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage OperateDiscuss_Copy(java.lang.String ConferenceId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperateDiscuss"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsStart", IsStart); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ConferenceCall_Copy(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceCall"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("DestId", DestId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage RequestAppointSpokesman_Copy(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.RequestAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage ResponeAppointSpokesman_Copy(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId,java.lang.Boolean IsAgreen) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ResponeAppointSpokesman"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
Params.put("IsAgreen", IsAgreen); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage EnterConsultation_Copy(java.lang.String CommanderId,java.lang.String ConsultationId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConsultationGroupBusiness.EnterConsultation"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("CommanderId", CommanderId); 
Params.put("ConsultationId", ConsultationId); 
Params.put("IsEnter", IsEnter); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage SetUserOnline_Copy(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String groupid) 
 { 
  String url = "CommandDomain.CommandStateManage.SetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("groupid", groupid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GetUserOnline_Copy(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommandStateManage.GetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("clientid", clientid); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
   static public IMessage GrobalSetUserOnline_Copy(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String centerid,java.lang.String groupid,java.lang.Boolean flag) 
 { 
  String url = "CommandDomain.CommandStateManage.GrobalSetUserOnline"; 
HashMap<String, Object> Params = new HashMap<String, Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("centerid", centerid); 
Params.put("groupid", groupid); 
Params.put("flag", flag); 
BaseMessage Message = new BaseMessage();
Message.AddParams(Params);
Message.SetAction("Event");
Message.SetObjURL(url);
return Message; 
 } 
 }