package NVMP.CommandDomain;

import org.misc.log.LogUtil;

import corenet.exchange.Encoding;

import NVMP.AppService.Remoting;
import NVMP.AppService.Interface.*;
import NVMP.Command.Business.*;
import NVMP.Command.*;
import org.misc.RefObject;

public class ConferenceGroupBusiness {

	private SystemBusiness BusinessData;
	public ICommandEvent CommandEvent;  

	public ConferenceGroupBusiness(SystemBusiness _BusinessData) {
		BusinessData = _BusinessData;
	}
	

	// 创建一个新的会议
	@Remoting
	public final void CreateConference(String ConferenceId,
			String ConferenceName, String Decription, String ChainmanId,
			Integer aConferenceType, Integer aConferenceStatus) 
	{
		String say = "";
		try
		{
			say=String.format("****创建一个新的会议：会议编号=%s,会议名称==%s，会议描述=%s，指挥员（主席）=%s",ConferenceId,ConferenceName,Decription,ChainmanId);
			LogUtil.BusinessDebug(say);
			Commander aCommander = null;
			RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(
					aCommander);
			boolean tempVar = BusinessData.GetCommanderById(ChainmanId,
					tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
			if (tempVar) {
			   // newConference.Chairman =aCommander;
			}
			
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));		
			CommandEvent.OnCreateConference(ConferenceId, ConferenceName, Decription, ChainmanId, 1, 1);
			ConferenceGroup newConference = new ConferenceGroup(ConferenceId,
					ConferenceName, Decription, aCommander, "", false,
					ConferenceType.Temp, ConferenceStatus.Helding);
			// newConference.CurConferenceType = _ConferenceType;
			newConference.setChairman(aCommander);
			BusinessData.AddConferenceGroup(newConference);
			DBAccess.AddConference(ConferenceId,
					               ConferenceName,
					               Decription,
					               ChainmanId,
		                           1);
		}catch(Exception e)
		{
		  LogUtil.BusinessError("创建会议错误：" +say );
		  LogUtil.BusinessError(e);
		}
		
	}
	// 创建一个新的会议
	@Remoting
	public final void SendCreateConference(String DestId, String ConferenceId,
			String ConferenceName, String Decription, String ChainmanId,
			Integer aConferenceType, Integer aConferenceStatus) 
	{ 
		String say=String.format("****登陆系统时创建一个新的会议：会议编号=%s,会议名称==%s，会议描述=%s，指挥员（主席）=%s",ConferenceId,ConferenceName,Decription,ChainmanId);	
		LogUtil.BusinessDebug(say);
		try
		{
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToPointProtocol(DestId));		
			CommandEvent.OnCreateConference(ConferenceId, ConferenceName, Decription, ChainmanId, 1, 1);
		
		}catch(Exception e)
		{
		  LogUtil.BusinessError("发送创建会议错误：" +say );
		  LogUtil.BusinessError(e);
		}
		
	}
	
	@Remoting
	public final void ConferenceOver(String ConferenceId) 
	{
		String say=String.format("****会议结束：会议编号=%s,",ConferenceId);
		LogUtil.BusinessDebug(say);
		try
		{
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));		
		 CommandEvent.OnConferenceOver(ConferenceId);
		 DBAccess.ConferenceOver(ConferenceId);
		}catch(Exception e)
		{
			  LogUtil.BusinessError("会议结束错误：" +say );
			  LogUtil.BusinessError(e);
			}
		
	}
	
	
	
	//指挥员进入会场	
	@Remoting
	public final void EnterConference(String commenderId, String ConferenceId,
			Boolean IsEnter) 
	{
		String say=String.format("****指挥员进入会场：会议编号=%s,指挥员=%s",ConferenceId,commenderId);
		LogUtil.BusinessDebug(say);
		try
		{
			Commander aCommander = null;
			RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(
					aCommander);
			boolean tempVar = BusinessData.GetCommanderById(commenderId,
					tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
			if (tempVar) {
				aCommander.EnterConference(ConferenceId, IsEnter);
	
				
				
				//增加会议成员到会议组
				ConferenceGroup aConferenceGroup = null;
				RefObject<ConferenceGroup> tempRef_aConferenceGroup = new RefObject<ConferenceGroup>(
						aConferenceGroup);
				boolean tempVar2 = BusinessData.GetConferenceGroup(ConferenceId,
						tempRef_aConferenceGroup);
				aConferenceGroup = tempRef_aConferenceGroup.argvalue;
				if (tempVar2) {
					aConferenceGroup.AddCommander(aCommander);
				}
			}
			
			// 通知所有指挥终端状态改变 
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));		
			CommandEvent.OnCommanderStateChange(commenderId,ConferenceId ,CommanderWorkStatus.Conference.getValue());
		}catch(Exception e)
		{
			  LogUtil.BusinessError("指挥员进入会场错误：" +say );
			  LogUtil.BusinessError(e);
			}
		

	}

	// 开始指定发言
	@Remoting
	public final void OperteAppointSpokesman(String ConferenceId,String Spokeman, Boolean IsStart) 
	{
		String say=String.format("****会议主席指定发言：会议编号=%s，发言人编号=%s，是否开始=%s",ConferenceId,Spokeman,IsStart.toString());
		LogUtil.BusinessDebug(say);
		try
		{			
				ConferenceGroup aConferenceGroup = null;		
				RefObject<ConferenceGroup> tempRef_aConferenceGroup = new RefObject<ConferenceGroup>(aConferenceGroup);
				boolean tempVar = BusinessData.GetConferenceGroup(ConferenceId,tempRef_aConferenceGroup);
				aConferenceGroup = tempRef_aConferenceGroup.argvalue;
		   
		//	   for (Commander item : aConferenceGroup.getCommanderLists()) 
		//	   {
		//			if ( (!item.getCommanderId().equals(Spokeman))&& //不是发言者
		//				 (!(item.getCommanderId().equals(aConferenceGroup.getChairman().getCommanderId())))) //不是主席
		//			{
		//				(CommandDomain.AppRunTime()).SetCurChannel(item.getCommanderId());	
		//				CommandEvent.OnCommanderP2PCommunicate(Spokeman, IsStart);
		//				
		//				(CommandDomain.AppRunTime()).SetCurChannel(Spokeman);	
		//				CommandEvent.OnCommanderP2PCommunicate(item.getCommanderId(), IsStart);
		//				
		//			}
		//		}
			  
			   (CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));
			   CommandEvent.OnAppointSpokesman(ConferenceId, Spokeman, IsStart);
		
			if (tempVar) 
			{
				if (IsStart) 
				{
					aConferenceGroup.setSpokeman(Spokeman);
					DBAccess.SetSpokeman(ConferenceId, Spokeman);
				} else 
				{
					aConferenceGroup.setSpokeman ("");
					DBAccess.SetSpokeman(ConferenceId, "");
				}
			}
		}catch(Exception e)
		{
			LogUtil.BusinessError("指定发言错误：" + say);
			LogUtil.BusinessError(e);
		
	     }
	
}


	//开始或停止讨论
	@Remoting
	public final void OperateDiscuss(String ConferenceId, Boolean IsStart) 
	{
		String say=String.format("****开始讨论模式：会议编号=%s，是否开始=%s",ConferenceId,IsStart.toString());
		LogUtil.BusinessDebug(say);
		try
		{		
			ConferenceGroup aConferenceGroup = null;
			RefObject<ConferenceGroup> tempRef_aConferenceGroup = new RefObject<ConferenceGroup>(aConferenceGroup);
			boolean tempVar = BusinessData.GetConferenceGroup(ConferenceId,	tempRef_aConferenceGroup);
			aConferenceGroup = tempRef_aConferenceGroup.argvalue;
	//		if (tempVar) 
	//		{
	//			aConferenceGroup.setIsDiscuss(IsStart);
	//			
	//			for (Commander item : aConferenceGroup.getCommanderLists()) 
	//			{
	//				if (!item.getCommanderId().equals(aConferenceGroup.getChairman().getCommanderId())) 
	//				{
	//					(CommandDomain.AppRunTime()).SetCurChannel(item.getCommanderId());
	//					CommandEvent.OnOperateDiscuss(ConferenceId, IsStart);
	//				}
	//			}
	//		}
			
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));
			 CommandEvent.OnOperateDiscuss(ConferenceId, IsStart);
			 DBAccess.SetDiscuss(ConferenceId, IsStart);
		}catch(Exception e)
		{
			LogUtil.BusinessError("讨论开始错误：" + say);
			LogUtil.BusinessError(e);
		
	     }
	}

	//会议呼叫
	@Remoting
	public final void ConferenceCall(String ConferenceId,String SourceId,String DestId)
	{
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToPointProtocol(DestId));
		 CommandEvent.OnConferenceCall(ConferenceId, SourceId, DestId);
	}
	//申请发言
	@Remoting
	public final void RequestAppointSpokesman(String ConferenceId,String SourceId,String ChainmanId)
	{
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToPointProtocol(ChainmanId));
		 CommandEvent.OnRequestAppointSpokesman(ConferenceId, SourceId, ChainmanId);
	}
	
	//回复申请发言
	@Remoting
	public final void ResponeAppointSpokesman(String ConferenceId,String SourceId,String ChainmanId,Boolean IsAgreen)
	{
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToPointProtocol(ChainmanId));
		 CommandEvent.OnResponeAppointSpokesman(ConferenceId, SourceId, ChainmanId, IsAgreen);
	}
	
	
	//强制退出
	@Remoting
	public final void ForceQuit(String ConferenceId,String DestId)
	{	
		String say=String.format("****强制退出：会议编号=%s，退出会议成员=%s",ConferenceId,DestId);
		LogUtil.BusinessDebug(say);
		try
		{	
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToPointProtocol(DestId));;
		 CommandEvent.OnForceQuit(ConferenceId, DestId);
		 //DBAccess.SetForceQuitConference();//增加数据库的记忆功能
		}catch(Exception e)
		{
			LogUtil.BusinessError("强制退出错误：" + say);
			LogUtil.BusinessError(e);
		
	     } 
	
	}
	
}
