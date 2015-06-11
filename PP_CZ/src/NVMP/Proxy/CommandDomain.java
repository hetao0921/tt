package NVMP.Proxy; 
import java.util.HashMap; 
import Runtime.IRunTime; 
import Runtime.impl.RunTime; 
import Runtime.ReturnDo;
public class CommandDomain  implements ReturnDo { 
static public class EventHandler {public Object CommanderLoginOkEvent(java.lang.String TerminalId,java.lang.String CommanderId,java.lang.Boolean IsOnline,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 {return null; 
}
public Object CommanderStateChangeEvent(java.lang.String CommanderId,java.lang.String CollectionId,java.lang.Integer State) 
 {return null; 
}
public Object AppointSpokesmanEvent(java.lang.String ConferenceId,java.lang.String Spokeman,java.lang.Boolean IsStart) 
 {return null; 
}
public Object OperateDiscussEvent(java.lang.String ConferenceId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object CommanderP2PCommunicateEvent(java.lang.String CommanderId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object P2PVoiceEvent(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object RequestCooperateEvent(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 {return null; 
}
public Object ResponeCooperateEvent(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsAgreen) 
 {return null; 
}
public Object CommandCallEvent(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 {return null; 
}
public Object ConferenceCallEvent(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String DestId) 
 {return null; 
}
public Object BroadcastEvent(java.lang.String SendCommanderId,java.lang.String CommandGroupId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object VideoeAssignEvent(java.lang.String DevicerId,java.lang.Integer Index,java.lang.String DestCommander,java.lang.Boolean IsStart) 
 {return null; 
}
public Object CommandReplacementeEvent(java.lang.String DownCommanderId,java.lang.String UpCommanderId,java.lang.Boolean IsStart,java.lang.Boolean IsReplacemente) 
 {return null; 
}
public Object ConferenceOverEvent(java.lang.String ConferenceId) 
 {return null; 
}
public Object CreateConferenceEvent(java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 {return null; 
}
public Object RequestAppointSpokesmanEvent(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId) 
 {return null; 
}
public Object ResponeAppointSpokesmanEvent(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId,java.lang.Boolean IsAgreen) 
 {return null; 
}
public Object ForceQuitEvent(java.lang.String ConferenceId,java.lang.String DestId) 
 {return null; 
}
public Object SendInfoEvent(java.lang.String SourceCommanderId,java.lang.String info) 
 {return null; 
}
public Object OperateDownCutEvent(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object OperateUpCutEvent(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 {return null; 
}
public Object ReciveCommanderStateEvent(java.lang.String CommanderId,java.lang.String DeviceId,java.lang.String GroupId,java.lang.String DeptId,java.lang.String WorkStatus,java.lang.Boolean IsOnline,java.lang.String WorkMode,java.lang.Boolean IsUpCut,java.lang.Boolean IsDownCut,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 {return null; 
}
public Object CommandServerOnlineEvent(java.lang.String serverid,java.lang.Boolean flag,java.lang.String context) 
 {return null; 
}
public Object LoginErrorEvent(java.lang.String ueserid,java.lang.String reason,java.lang.String context) 
 {return null; 
}
public Object CenterServerOnLineEvent(java.lang.String serverid,java.lang.Boolean flag) 
 {return null; 
}
public Object GetVersionEvent(java.lang.String type,java.lang.String Context) 
 {return null; 
}
public Object UserStateEvent(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.String centerid,java.lang.Boolean online,java.lang.String groupid,java.lang.Boolean showflag) 
 {return null; 
}
}
static public class FunResultHandler {public Object SendInfoResult() 
 {return null; 
}
public Object GetVersionResult() 
 {return null; 
}
public Object EnterFreeResult() 
 {return null; 
}
public Object GetInitStatusResult() 
 {return null; 
}
public Object SetCommanderLoginOkResult() 
 {return null; 
}
public Object SendCommanderStateResult() 
 {return null; 
}
public Object getCommandServerOnlineResult() 
 {return null; 
}
public Object OperateUpCutResult() 
 {return null; 
}
public Object OperateCommanderP2PCommunicateResult() 
 {return null; 
}
public Object OperateP2PVoiceResult() 
 {return null; 
}
public Object EnterCommandGroupResult() 
 {return null; 
}
public Object ResponeCooperateResult() 
 {return null; 
}
public Object OperateCommandCallResult() 
 {return null; 
}
public Object RequestCooperateResult() 
 {return null; 
}
public Object OperateDownCutResult() 
 {return null; 
}
public Object OperateVideoeAssignResult() 
 {return null; 
}
public Object OperateBroadcastResult() 
 {return null; 
}
public Object OperateCommandReplacementeResult() 
 {return null; 
}
public Object OperateCommandAccreditResult() 
 {return null; 
}
public Object ForceQuitResult() 
 {return null; 
}
public Object ConferenceOverResult() 
 {return null; 
}
public Object EnterConferenceResult() 
 {return null; 
}
public Object CreateConferenceResult() 
 {return null; 
}
public Object SendCreateConferenceResult() 
 {return null; 
}
public Object OperteAppointSpokesmanResult() 
 {return null; 
}
public Object OperateDiscussResult() 
 {return null; 
}
public Object ConferenceCallResult() 
 {return null; 
}
public Object RequestAppointSpokesmanResult() 
 {return null; 
}
public Object ResponeAppointSpokesmanResult() 
 {return null; 
}
public Object EnterConsultationResult() 
 {return null; 
}
public Object SetUserOnlineResult() 
 {return null; 
}
public Object GetUserOnlineResult() 
 {return null; 
}
public Object GrobalSetUserOnlineResult() 
 {return null; 
}
}
private IRunTime run;
public CommandDomain(IRunTime run)
 { 
this.run = run; 
((RunTime)run).registerProxy(this.getClass().getSimpleName(), this);
}
public EventHandler eventhandler;
public FunResultHandler funhandler;
public void setEventhand(EventHandler eventhandler) {
this.eventhandler = eventhandler;
}
 public void setFunhand(FunResultHandler funhandler) {
this.funhandler = funhandler;
}
public void ReturnEvent(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommanderLoginOk")) 
 { 
  String TerminalId =  retValue.get("TerminalId").toString(); 
 String CommanderId =  retValue.get("CommanderId").toString(); 
 Boolean IsOnline;
if(retValue.get("IsOnline").toString().equals("")) { 
IsOnline = null ; 
 } else { 
IsOnline = Boolean.valueOf(retValue.get("IsOnline").toString());
}
 Integer CameraNum;
if(retValue.get("CameraNum").toString().equals("")) { 
CameraNum = null;  
 } else { 
CameraNum = Integer.valueOf(retValue.get("CameraNum").toString()); 
 }
 String UserName =  retValue.get("UserName").toString(); 
 String Camera1Name =  retValue.get("Camera1Name").toString(); 
 String Camera2Name =  retValue.get("Camera2Name").toString(); 
 String Camera3Name =  retValue.get("Camera3Name").toString(); 
 String Camera4Name =  retValue.get("Camera4Name").toString(); 
 String CenterId =  retValue.get("CenterId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.CommanderLoginOkEvent(TerminalId,CommanderId,IsOnline,CameraNum,UserName,Camera1Name,Camera2Name,Camera3Name,Camera4Name,CenterId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommanderStateChange")) 
 { 
  String CommanderId =  retValue.get("CommanderId").toString(); 
 String CollectionId =  retValue.get("CollectionId").toString(); 
 Integer State;
if(retValue.get("State").toString().equals("")) { 
State = null;  
 } else { 
State = Integer.valueOf(retValue.get("State").toString()); 
 }
if(this.eventhandler!=null) {
 this.eventhandler.CommanderStateChangeEvent(CommanderId,CollectionId,State ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnAppointSpokesman")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String Spokeman =  retValue.get("Spokeman").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.AppointSpokesmanEvent(ConferenceId,Spokeman,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnOperateDiscuss")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.OperateDiscussEvent(ConferenceId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommanderP2PCommunicate")) 
 { 
  String CommanderId =  retValue.get("CommanderId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.CommanderP2PCommunicateEvent(CommanderId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnP2PVoice")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String DestCommanderId =  retValue.get("DestCommanderId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.P2PVoiceEvent(SendCommanderId,DestCommanderId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnRequestCooperate")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String DestCommanderId =  retValue.get("DestCommanderId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.RequestCooperateEvent(SendCommanderId,DestCommanderId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnResponeCooperate")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String DestCommanderId =  retValue.get("DestCommanderId").toString(); 
 Boolean IsAgreen;
if(retValue.get("IsAgreen").toString().equals("")) { 
IsAgreen = null ; 
 } else { 
IsAgreen = Boolean.valueOf(retValue.get("IsAgreen").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.ResponeCooperateEvent(SendCommanderId,DestCommanderId,IsAgreen ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommandCall")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String DestCommanderId =  retValue.get("DestCommanderId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.CommandCallEvent(SendCommanderId,DestCommanderId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnConferenceCall")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 String DestId =  retValue.get("DestId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.ConferenceCallEvent(ConferenceId,SourceId,DestId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnBroadcast")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String CommandGroupId =  retValue.get("CommandGroupId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.BroadcastEvent(SendCommanderId,CommandGroupId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnVideoeAssign")) 
 { 
  String DevicerId =  retValue.get("DevicerId").toString(); 
 Integer Index;
if(retValue.get("Index").toString().equals("")) { 
Index = null;  
 } else { 
Index = Integer.valueOf(retValue.get("Index").toString()); 
 }
 String DestCommander =  retValue.get("DestCommander").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.VideoeAssignEvent(DevicerId,Index,DestCommander,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommandReplacemente")) 
 { 
  String DownCommanderId =  retValue.get("DownCommanderId").toString(); 
 String UpCommanderId =  retValue.get("UpCommanderId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
 Boolean IsReplacemente;
if(retValue.get("IsReplacemente").toString().equals("")) { 
IsReplacemente = null ; 
 } else { 
IsReplacemente = Boolean.valueOf(retValue.get("IsReplacemente").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.CommandReplacementeEvent(DownCommanderId,UpCommanderId,IsStart,IsReplacemente ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnConferenceOver")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.ConferenceOverEvent(ConferenceId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCreateConference")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String ConferenceName =  retValue.get("ConferenceName").toString(); 
 String Decription =  retValue.get("Decription").toString(); 
 String ChainmanId =  retValue.get("ChainmanId").toString(); 
 Integer aConferenceType;
if(retValue.get("aConferenceType").toString().equals("")) { 
aConferenceType = null;  
 } else { 
aConferenceType = Integer.valueOf(retValue.get("aConferenceType").toString()); 
 }
 Integer aConferenceStatus;
if(retValue.get("aConferenceStatus").toString().equals("")) { 
aConferenceStatus = null;  
 } else { 
aConferenceStatus = Integer.valueOf(retValue.get("aConferenceStatus").toString()); 
 }
if(this.eventhandler!=null) {
 this.eventhandler.CreateConferenceEvent(ConferenceId,ConferenceName,Decription,ChainmanId,aConferenceType,aConferenceStatus ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnRequestAppointSpokesman")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 String ChainmanId =  retValue.get("ChainmanId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.RequestAppointSpokesmanEvent(ConferenceId,SourceId,ChainmanId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnResponeAppointSpokesman")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String SourceId =  retValue.get("SourceId").toString(); 
 String ChainmanId =  retValue.get("ChainmanId").toString(); 
 Boolean IsAgreen;
if(retValue.get("IsAgreen").toString().equals("")) { 
IsAgreen = null ; 
 } else { 
IsAgreen = Boolean.valueOf(retValue.get("IsAgreen").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.ResponeAppointSpokesmanEvent(ConferenceId,SourceId,ChainmanId,IsAgreen ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnForceQuit")) 
 { 
  String ConferenceId =  retValue.get("ConferenceId").toString(); 
 String DestId =  retValue.get("DestId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.ForceQuitEvent(ConferenceId,DestId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnSendInfo")) 
 { 
  String SourceCommanderId =  retValue.get("SourceCommanderId").toString(); 
 String info =  retValue.get("info").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.SendInfoEvent(SourceCommanderId,info ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnOperateDownCut")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String GroupId =  retValue.get("GroupId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.OperateDownCutEvent(SendCommanderId,GroupId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnOperateUpCut")) 
 { 
  String SendCommanderId =  retValue.get("SendCommanderId").toString(); 
 String GroupId =  retValue.get("GroupId").toString(); 
 Boolean IsStart;
if(retValue.get("IsStart").toString().equals("")) { 
IsStart = null ; 
 } else { 
IsStart = Boolean.valueOf(retValue.get("IsStart").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.OperateUpCutEvent(SendCommanderId,GroupId,IsStart ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnReciveCommanderState")) 
 { 
  String CommanderId =  retValue.get("CommanderId").toString(); 
 String DeviceId =  retValue.get("DeviceId").toString(); 
 String GroupId =  retValue.get("GroupId").toString(); 
 String DeptId =  retValue.get("DeptId").toString(); 
 String WorkStatus =  retValue.get("WorkStatus").toString(); 
 Boolean IsOnline;
if(retValue.get("IsOnline").toString().equals("")) { 
IsOnline = null ; 
 } else { 
IsOnline = Boolean.valueOf(retValue.get("IsOnline").toString());
}
 String WorkMode =  retValue.get("WorkMode").toString(); 
 Boolean IsUpCut;
if(retValue.get("IsUpCut").toString().equals("")) { 
IsUpCut = null ; 
 } else { 
IsUpCut = Boolean.valueOf(retValue.get("IsUpCut").toString());
}
 Boolean IsDownCut;
if(retValue.get("IsDownCut").toString().equals("")) { 
IsDownCut = null ; 
 } else { 
IsDownCut = Boolean.valueOf(retValue.get("IsDownCut").toString());
}
 Integer CameraNum;
if(retValue.get("CameraNum").toString().equals("")) { 
CameraNum = null;  
 } else { 
CameraNum = Integer.valueOf(retValue.get("CameraNum").toString()); 
 }
 String UserName =  retValue.get("UserName").toString(); 
 String Camera1Name =  retValue.get("Camera1Name").toString(); 
 String Camera2Name =  retValue.get("Camera2Name").toString(); 
 String Camera3Name =  retValue.get("Camera3Name").toString(); 
 String Camera4Name =  retValue.get("Camera4Name").toString(); 
 String CenterId =  retValue.get("CenterId").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.ReciveCommanderStateEvent(CommanderId,DeviceId,GroupId,DeptId,WorkStatus,IsOnline,WorkMode,IsUpCut,IsDownCut,CameraNum,UserName,Camera1Name,Camera2Name,Camera3Name,Camera4Name,CenterId ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCommandServerOnline")) 
 { 
  String serverid =  retValue.get("serverid").toString(); 
 Boolean flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null ; 
 } else { 
flag = Boolean.valueOf(retValue.get("flag").toString());
}
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.CommandServerOnlineEvent(serverid,flag,context ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnLoginError")) 
 { 
  String ueserid =  retValue.get("ueserid").toString(); 
 String reason =  retValue.get("reason").toString(); 
 String context =  retValue.get("context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.LoginErrorEvent(ueserid,reason,context ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnCenterServerOnLine")) 
 { 
  String serverid =  retValue.get("serverid").toString(); 
 Boolean flag;
if(retValue.get("flag").toString().equals("")) { 
flag = null ; 
 } else { 
flag = Boolean.valueOf(retValue.get("flag").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.CenterServerOnLineEvent(serverid,flag ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnGetVersion")) 
 { 
  String type =  retValue.get("type").toString(); 
 String Context =  retValue.get("Context").toString(); 
if(this.eventhandler!=null) {
 this.eventhandler.GetVersionEvent(type,Context ); 
 } 
}
if (EventURL.equals( "CommandDomain.ICommandEvent.OnUserState")) 
 { 
  String sessionid =  retValue.get("sessionid").toString(); 
 String userid =  retValue.get("userid").toString(); 
 String ip =  retValue.get("ip").toString(); 
 String state =  retValue.get("state").toString(); 
 String centerid =  retValue.get("centerid").toString(); 
 Boolean online;
if(retValue.get("online").toString().equals("")) { 
online = null ; 
 } else { 
online = Boolean.valueOf(retValue.get("online").toString());
}
 String groupid =  retValue.get("groupid").toString(); 
 Boolean showflag;
if(retValue.get("showflag").toString().equals("")) { 
showflag = null ; 
 } else { 
showflag = Boolean.valueOf(retValue.get("showflag").toString());
}
if(this.eventhandler!=null) {
 this.eventhandler.UserStateEvent(sessionid,userid,ip,state,centerid,online,groupid,showflag ); 
 } 
}
}
public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) 
 { 
if (EventURL.equals( "CommandDomain.CommanderBusiness.SendInfo")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SendInfoResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.GetVersion")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GetVersionResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.EnterFree")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.EnterFreeResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.GetInitStatus")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GetInitStatusResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.SetCommanderLoginOk")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetCommanderLoginOkResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.SendCommanderState")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SendCommanderStateResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommanderBusiness.getCommandServerOnline")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.getCommandServerOnlineResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateUpCut")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateUpCutResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateCommanderP2PCommunicate")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateCommanderP2PCommunicateResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateP2PVoice")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateP2PVoiceResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.EnterCommandGroup")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.EnterCommandGroupResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.ResponeCooperate")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ResponeCooperateResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateCommandCall")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateCommandCallResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.RequestCooperate")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.RequestCooperateResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateDownCut")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateDownCutResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateVideoeAssign")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateVideoeAssignResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateBroadcast")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateBroadcastResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateCommandReplacemente")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateCommandReplacementeResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandGroupBusiness.OperateCommandAccredit")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateCommandAccreditResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.ForceQuit")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ForceQuitResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.ConferenceOver")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ConferenceOverResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.EnterConference")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.EnterConferenceResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.CreateConference")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.CreateConferenceResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.SendCreateConference")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SendCreateConferenceResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.OperteAppointSpokesman")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperteAppointSpokesmanResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.OperateDiscuss")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.OperateDiscussResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.ConferenceCall")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ConferenceCallResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.RequestAppointSpokesman")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.RequestAppointSpokesmanResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConferenceGroupBusiness.ResponeAppointSpokesman")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.ResponeAppointSpokesmanResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.ConsultationGroupBusiness.EnterConsultation")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.EnterConsultationResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandStateManage.SetUserOnline")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.SetUserOnlineResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandStateManage.GetUserOnline")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GetUserOnlineResult( ); 
 } 
}
if (EventURL.equals( "CommandDomain.CommandStateManage.GrobalSetUserOnline")) 
 { 
  if(this.funhandler!=null) { 
 this.funhandler.GrobalSetUserOnlineResult( ); 
 } 
}
}
  public void SendInfo(java.lang.String SourceCommanderId,java.lang.String DestCommanderId,java.lang.String info) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendInfo"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SourceCommanderId", SourceCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("info", info); 
run.Invoke(url, Params, null, null); 
 } 
   public void GetVersion(java.lang.String clientid,java.lang.String version) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetVersion"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("clientid", clientid); 
Params.put("version", version); 
run.Invoke(url, Params, null, null); 
 } 
   public void EnterFree(java.lang.String CommanderId) 
 { 
  String url = "CommandDomain.CommanderBusiness.EnterFree"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("CommanderId", CommanderId); 
run.Invoke(url, Params, null, null); 
 } 
   public void GetInitStatus(java.lang.String TerminalId) 
 { 
  String url = "CommandDomain.CommanderBusiness.GetInitStatus"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("TerminalId", TerminalId); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetCommanderLoginOk(java.lang.String TerminalId,java.lang.String CommanderId,java.lang.String TerminalIP,java.lang.Boolean IsOnline,java.lang.Integer DeviceType,java.lang.Integer DevuceSubType,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SetCommanderLoginOk"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
   public void SendCommanderState(java.lang.String DestDeviceId,java.lang.String CommanderId,java.lang.String DeviceId,java.lang.String GroupId,java.lang.String DeptId,java.lang.String WorkStatus,java.lang.Boolean IsOnline,java.lang.String WorkMode,java.lang.Boolean IsUpCut,java.lang.Boolean IsDownCut,java.lang.Integer CameraNum,java.lang.String UserName,java.lang.String Camera1Name,java.lang.String Camera2Name,java.lang.String Camera3Name,java.lang.String Camera4Name,java.lang.String CenterId) 
 { 
  String url = "CommandDomain.CommanderBusiness.SendCommanderState"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
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
run.Invoke(url, Params, null, null); 
 } 
   public void getCommandServerOnline(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommanderBusiness.getCommandServerOnline"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("clientid", clientid); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateUpCut(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateUpCut"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateCommanderP2PCommunicate(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommanderP2PCommunicate"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateP2PVoice(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateP2PVoice"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void EnterCommandGroup(java.lang.String CommandGroupId,java.lang.String CommanderId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.EnterCommandGroup"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("CommanderId", CommanderId); 
Params.put("IsEnter", IsEnter); 
run.Invoke(url, Params, null, null); 
 } 
   public void ResponeCooperate(java.lang.String SendCommanderId,java.lang.String DestCommanderId,java.lang.Boolean IsAgree) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.ResponeCooperate"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
Params.put("IsAgree", IsAgree); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateCommandCall(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandCall"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
run.Invoke(url, Params, null, null); 
 } 
   public void RequestCooperate(java.lang.String SendCommanderId,java.lang.String DestCommanderId) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.RequestCooperate"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("DestCommanderId", DestCommanderId); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateDownCut(java.lang.String SendCommanderId,java.lang.String GroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateDownCut"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("GroupId", GroupId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateVideoeAssign(java.lang.String DevicerId,java.lang.Integer Index,java.lang.String DestCommander,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateVideoeAssign"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("DevicerId", DevicerId); 
Params.put("Index", Index); 
Params.put("DestCommander", DestCommander); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateBroadcast(java.lang.String SendCommanderId,java.lang.String CommandGroupId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateBroadcast"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("SendCommanderId", SendCommanderId); 
Params.put("CommandGroupId", CommandGroupId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateCommandReplacemente(java.lang.String DownCommanderId,java.lang.String UpCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandReplacemente"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateCommandAccredit(java.lang.String UpCommanderId,java.lang.String DownCommanderId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.CommandGroupBusiness.OperateCommandAccredit"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("UpCommanderId", UpCommanderId); 
Params.put("DownCommanderId", DownCommanderId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void ForceQuit(java.lang.String ConferenceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ForceQuit"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("DestId", DestId); 
run.Invoke(url, Params, null, null); 
 } 
   public void ConferenceOver(java.lang.String ConferenceId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceOver"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
run.Invoke(url, Params, null, null); 
 } 
   public void EnterConference(java.lang.String commenderId,java.lang.String ConferenceId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.EnterConference"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("commenderId", commenderId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsEnter", IsEnter); 
run.Invoke(url, Params, null, null); 
 } 
   public void CreateConference(java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.CreateConference"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
run.Invoke(url, Params, null, null); 
 } 
   public void SendCreateConference(java.lang.String DestId,java.lang.String ConferenceId,java.lang.String ConferenceName,java.lang.String Decription,java.lang.String ChainmanId,java.lang.Integer aConferenceType,java.lang.Integer aConferenceStatus) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.SendCreateConference"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("DestId", DestId); 
Params.put("ConferenceId", ConferenceId); 
Params.put("ConferenceName", ConferenceName); 
Params.put("Decription", Decription); 
Params.put("ChainmanId", ChainmanId); 
Params.put("aConferenceType", aConferenceType); 
Params.put("aConferenceStatus", aConferenceStatus); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperteAppointSpokesman(java.lang.String ConferenceId,java.lang.String Spokeman,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperteAppointSpokesman"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("Spokeman", Spokeman); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void OperateDiscuss(java.lang.String ConferenceId,java.lang.Boolean IsStart) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.OperateDiscuss"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("IsStart", IsStart); 
run.Invoke(url, Params, null, null); 
 } 
   public void ConferenceCall(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String DestId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ConferenceCall"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("DestId", DestId); 
run.Invoke(url, Params, null, null); 
 } 
   public void RequestAppointSpokesman(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.RequestAppointSpokesman"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
run.Invoke(url, Params, null, null); 
 } 
   public void ResponeAppointSpokesman(java.lang.String ConferenceId,java.lang.String SourceId,java.lang.String ChainmanId,java.lang.Boolean IsAgreen) 
 { 
  String url = "CommandDomain.ConferenceGroupBusiness.ResponeAppointSpokesman"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("ConferenceId", ConferenceId); 
Params.put("SourceId", SourceId); 
Params.put("ChainmanId", ChainmanId); 
Params.put("IsAgreen", IsAgreen); 
run.Invoke(url, Params, null, null); 
 } 
   public void EnterConsultation(java.lang.String CommanderId,java.lang.String ConsultationId,java.lang.Boolean IsEnter) 
 { 
  String url = "CommandDomain.ConsultationGroupBusiness.EnterConsultation"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("CommanderId", CommanderId); 
Params.put("ConsultationId", ConsultationId); 
Params.put("IsEnter", IsEnter); 
run.Invoke(url, Params, null, null); 
 } 
   public void SetUserOnline(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String groupid) 
 { 
  String url = "CommandDomain.CommandStateManage.SetUserOnline"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("groupid", groupid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GetUserOnline(java.lang.String clientid) 
 { 
  String url = "CommandDomain.CommandStateManage.GetUserOnline"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("clientid", clientid); 
run.Invoke(url, Params, null, null); 
 } 
   public void GrobalSetUserOnline(java.lang.String sessionid,java.lang.String userid,java.lang.String ip,java.lang.String state,java.lang.Boolean online,java.lang.String centerid,java.lang.String groupid,java.lang.Boolean flag) 
 { 
  String url = "CommandDomain.CommandStateManage.GrobalSetUserOnline"; 
HashMap<String,Object> Params = new HashMap<String,Object>(); 
Params.put("sessionid", sessionid); 
Params.put("userid", userid); 
Params.put("ip", ip); 
Params.put("state", state); 
Params.put("online", online); 
Params.put("centerid", centerid); 
Params.put("groupid", groupid); 
Params.put("flag", flag); 
run.Invoke(url, Params, null, null); 
 } 
 } 
 