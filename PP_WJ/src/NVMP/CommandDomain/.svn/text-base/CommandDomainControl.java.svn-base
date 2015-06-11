package NVMP.CommandDomain;



import NVMP.AppService.Remoting;
import corenet.netbase.BaseSession;

/*public interface BaseClientListen {
	public  void OnNewConnection(BaseSession Session, boolean Success);
}*/




   public class CommandDomainControl
	{


	   private CommandDomainControl _CommandDomainControl;
		
		

		public CommandDomainControl get_CommandDomainControl() {
			return _CommandDomainControl;
		}
	    
		@Remoting
		public void set_CommandDomainControl(CommandDomainControl _CommandDomainControl) 
		{
			this._CommandDomainControl = _CommandDomainControl;
		}
		

		//向设备管理模块发送指挥终端设备上线指令 
	    @Remoting
		public  void SendEncodeDeviceLogin(String TerminalId, String TerminalIP, boolean IsOnline)
	    {
//	    	(CommandBusiness.AppRunTime()).SetCurChannel(ReciverId);			
//	   	 
//    		if (_CommandDomainControl != null)
//    		{		
//    			
//    		}
	    	java.util.HashMap<String, Object> Params = new java.util.HashMap<String, Object>();
	    	Params.put("TerminalIP", TerminalIP);
	    	Params.put("TerminalId", TerminalId);
	    	Params.put("IsOnline",new Boolean(IsOnline));
	    	
	    	
	    	CommandDomain.AppRunTime().EventHandler(Params,"CommandDomain.CommandBusiness.OnCommanderLoginOk");
	    	
	    	
	    	
	    	
	    	//CommandDomain.AppRunTime().EventHandler(Params,this,"SendEncodeDeviceLogin");
	    }
		
		

		//向设备管理模块发送用户登录指令   
	    @Remoting   
		 public  void SendUserLogin(String ReciverId,String CommanderId, boolean IsOnline)
	    {
	    	
	    	
	    }
		
		//指挥员工作状态改变
	    @Remoting
		public  void SendCommanderStateChange(String ReviceCommanderId, String CommanderId, String CollectionId, String sCommanderWorkStatus)
	    {
	    	
	    }
		

		//声音的专向操作
	    @Remoting
		public  void SendOperateP2PVoice(String ReviceCommanderId, String SendCommanderId, String DestCommanderId, boolean IsStart)
	    {
	    	
	    }
		
		//开始或停止音视频点播
		public  void SendOperateP2PCommunicate(String ReviceCommanderId, String SendCommanderId, String DestCommanderId, boolean IsStart){
		}

		//开始或停止音频的点播
		public  void SendOperateVoiceCommunicate(String ReviceCommanderId, String SendCommanderId, String DestCommanderId, boolean IsStart)
		{
			
		}
		

		//开始或停止会议讨论模式
		public  void SendStartDiscuss(String ReviceCommanderId, String ConferenceId, boolean IsStart)
		{
			
		}
		


	}
   
