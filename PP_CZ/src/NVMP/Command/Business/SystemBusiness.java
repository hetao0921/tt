package NVMP.Command.Business;


import org.misc.RefObject;
import org.misc.log.LogUtil;

import NVMP.Proxy.StateManageDomainProxy;

public class SystemBusiness extends SystemData
{

	public SystemBusiness()
	{
		_AccreditList = new java.util.ArrayList<Accredit>();
	}

	protected void finalize() throws Throwable
	{

	}

	@Override
	public void dispose()
	{

	}
	
	private java.util.ArrayList<Accredit> _AccreditList;

	public final java.util.ArrayList<Accredit> getAccreditList()
	{
		return _AccreditList;
	}
	public final void setAccreditList(java.util.ArrayList<Accredit> value)
	{
		_AccreditList = value;
	}
	
	public void AddAccredit( String Licensor, String Acception, String type)  //1,表示指挥授权，2表示指挥接替
     {
		for (Accredit item :_AccreditList)
		{
			if (item.Acception.toString() == Acception.toString()  && 
			   (item.Licensor.toString() == Licensor.toString()))
			{
				return;
			}
		}
		Accredit acc = new Accredit();
		acc.Acception = Acception;
		acc.Licensor = Licensor;
		acc.type = type;
		
		_AccreditList.add(acc);
     }
	
	public void DelAccredit( String Licensor, String Acception)  //1,表示指挥授权，2表示指挥接替
    {
		for (Accredit item :_AccreditList)
		{
			if (item.Acception.toString() == Acception.toString()  && 
			   (item.Licensor.toString() == Licensor.toString()))
			{
				_AccreditList.remove(item);
				return;
			}
		}	
    }
	
	public void DelAccredit( String CommanderId)  //1,表示指挥授权，2表示指挥接替
    {
		for ( int i = _AccreditList.size() -1 ; i >= 0;i--)
		{
			 Accredit item = _AccreditList.get(i);
			if (item.Acception.toString() == CommanderId.toString()  || 
			   (item.Licensor.toString() == CommanderId.toString()))
			{
				_AccreditList.remove(item);
				return;
			}
		}	
    }



	public final void SetCommanderLogin(String TerminalId, String CommanderId,String TerminalIP, Boolean isOnline)
	{
		Commander aCommander = null;
		RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(aCommander);
		boolean tempVar = GetCommanderById(CommanderId, tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
		if (tempVar)
		{
			aCommander.SetLogin(TerminalId, isOnline);
		}
		//保存信息到数据库
		if (!isOnline)
		{
			String say=String.format("****修改数据库指挥员下线：终端编号=%s,指挥员编号=%s,设备IP==%s,是否上线=%s",TerminalId,CommanderId,TerminalIP,isOnline);
			LogUtil.BusinessDebug(say);
		}
		DBAccess.SetCommanderLogin(TerminalId, CommanderId, TerminalIP, isOnline);
		
	}
	



	public final void InitCommandeState(String CoammanderId)
	{
		Commander aCommander = null;
		RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(aCommander);
		boolean tempVar = GetCommanderById(CoammanderId, tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
		if (tempVar)
		{
			aCommander.InitState();
		}
	}

	public final void CommanderEnterFree(String CommanderId)
	{
			for (ConferenceGroup item : getConferenceGroupList())
			{
				item.RemoveCommander(CommanderId);
			}

			for (ConsultationGroup item : getConsultationGroupList())
			{
				item.RemoveCommander(CommanderId);
			}
	}

	public void OperateP2PVoice(String SendCommanderId, String DestCommanderId, boolean IsStart)
	{
		Commander aCommander = null;
		
		if (IsStart)
		{

			RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(aCommander);
			boolean tempVar = GetCommanderById(SendCommanderId, tempRef_aCommander);
				aCommander = tempRef_aCommander.argvalue;
			if (tempVar)
			{
				aCommander.setWorkMode(CommanderWorkMode.P2P);
			}

			RefObject<Commander> tempRef_aCommander2 = new RefObject<Commander>(aCommander);
			boolean tempVar2 = GetCommanderById(DestCommanderId, tempRef_aCommander2);
				aCommander = tempRef_aCommander2.argvalue;
			if (tempVar2)
			{
				aCommander.setWorkMode(CommanderWorkMode.P2P);
			}
			DBAccess.SetCommanderWordMode(SendCommanderId,DestCommanderId,CommanderWorkMode.P2P);
		}
		else
		{
			RefObject<Commander> tempRef_aCommander3 = new RefObject<Commander>(aCommander);
			boolean tempVar3 = GetCommanderById(SendCommanderId, tempRef_aCommander3);
				aCommander = tempRef_aCommander3.argvalue;
			if (tempVar3)
			{
				aCommander.setWorkMode(CommanderWorkMode.General);
			}

			RefObject<Commander> tempRef_aCommander4 = new RefObject<Commander>(aCommander);
			boolean tempVar4 = GetCommanderById(DestCommanderId, tempRef_aCommander4);
				aCommander = tempRef_aCommander4.argvalue;
			if (tempVar4)
			{
				aCommander.setWorkMode(CommanderWorkMode.General);
			}

			DBAccess.SetCommanderWordMode(SendCommanderId,DestCommanderId,CommanderWorkMode.General);
		}


	}








	//指挥终端使用的本机IP



	///// 
	///// <param name="TerminalId"></param>
	///// <param name="CommanderId"></param>
	//public void CommanderLoginOkEvent(string TerminalId, string CommanderId)
	//{

	//}

	///// 
	///// <param name="CommandGroupId"></param>
	///// <param name="CommanderId"></param>
	//public void EnterCommandGroupEvent(string CommandGroupId, string CommanderId)
	//{

	//}

	///// 
	///// <param name="TerminalId"></param>
	///// <param name="CommanderId"></param>
	//public void SetCommanderLoginOk(string TerminalId, string CommanderId)
	//{

	//}

} //end SystemBusiness