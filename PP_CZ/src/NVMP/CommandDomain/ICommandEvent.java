package NVMP.CommandDomain;

import corenet.exchange.Encoding;
import NVMP.AppService.Remoting;
import NVMP.Command.Business.ConferenceStatus;
import NVMP.Command.Business.ConferenceType;

@Remoting
public class ICommandEvent 
{

//	public void OnEncodeDeviceLogin(String TerminalId, String TerminalIP, Boolean IsOnline){};

	public void OnCommanderLoginOk(String TerminalId, String CommanderId,Boolean IsOnline,Integer CameraNum,String UserName,
			String Camera1Name,String Camera2Name,String Camera3Name,String Camera4Name,String CenterId){};

	public void OnCommanderStateChange(String CommanderId, String CollectionId, Integer State){};	

	public void OnAppointSpokesman(String ConferenceId,String Spokeman, Boolean IsStart){};

	public void OnOperateDiscuss(String ConferenceId, Boolean IsStart){};
	public void OnCommanderP2PCommunicate(String CommanderId, Boolean IsStart){};
	public  void OnP2PVoice(String SendCommanderId, String DestCommanderId, Boolean IsStart){};
	 public  void OnRequestCooperate(String SendCommanderId, String DestCommanderId){};
	 public  void OnResponeCooperate(String SendCommanderId, String DestCommanderId,Boolean IsAgreen){};
	public void OnCommandCall(String SendCommanderId, String DestCommanderId){};
	public void OnConferenceCall(String ConferenceId,String SourceId,String DestId){};
	public void OnBroadcast(String SendCommanderId,String CommandGroupId,Boolean IsStart){};
	public  void OnVideoeAssign(String DevicerId, Integer Index, String DestCommander, Boolean IsStart){};
	public  void OnCommandReplacemente(String DownCommanderId,String UpCommanderId, Boolean IsStart,Boolean IsReplacemente){};
	public void OnConferenceOver(String ConferenceId) {};	
	public void OnCreateConference(String ConferenceId,
			String ConferenceName, String Decription, String ChainmanId,
			Integer aConferenceType, Integer aConferenceStatus) {};
	public  void OnRequestAppointSpokesman(String ConferenceId,String SourceId,String ChainmanId){};
	public  void OnResponeAppointSpokesman(String ConferenceId,String SourceId,String ChainmanId,Boolean IsAgreen){};
			
	public  void OnForceQuit(String ConferenceId,String DestId){};
	public void OnSendInfo(String SourceCommanderId,String info){};
	public void OnOperateDownCut(String SendCommanderId, String GroupId, Boolean IsStart){};
	public void OnOperateUpCut(String SendCommanderId, String GroupId, Boolean IsStart){};
	public void OnReciveCommanderState( String CommanderId, 
									    String DeviceId,
									    String GroupId,
									    String DeptId,
									    String WorkStatus,
									    Boolean IsOnline,
									    String WorkMode,
									    Boolean IsUpCut,
									    Boolean IsDownCut,
									    Integer CameraNum,
									    String UserName,
										String Camera1Name,
										String Camera2Name,
										String Camera3Name,
										String Camera4Name,
										String CenterId){}
	public void OnCommandServerOnline(String serverid, Boolean flag, String context) {
	}
	public void OnLoginError(String ueserid, String reason, String context) {
	}
	public void OnCenterServerOnLine(String serverid, Boolean flag) {
	}
	public void OnGetVersion(String type, String Context) {
	}
	public void OnUserState(String sessionid, String userid, String ip,
			String state, String centerid, Boolean online,String groupid,Boolean showflag) {}
	
}

