package NVMP.CommandDomain;

import corenet.exchange.Encoding;
import NVMP.AppService.Remoting;
import NVMP.AppService.Interface.*;
import NVMP.Command.Business.*;
import NVMP.Command.*;
import org.misc.RefObject;

public class ConsultationGroupBusiness
{



	private SystemBusiness BusinessData;
	public ICommandEvent CommandEvent;  


	public ConsultationGroupBusiness(SystemBusiness _BusinessData)
	{
		BusinessData = _BusinessData;
	}
	
	@Remoting
	public final void EnterConsultation(String CommanderId, String ConsultationId, Boolean IsEnter)
	{

		Commander aCommander = null;
		RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(aCommander);
		boolean tempVar = BusinessData.GetCommanderById(CommanderId, tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
		if (tempVar)
		{
			aCommander.EnterConsultation(ConsultationId, IsEnter);			
			
			ConsultationGroup aConsultationGroup = null;
			RefObject<ConsultationGroup> tempRef_aConsultationGroup = new RefObject<ConsultationGroup>(aConsultationGroup);
			boolean tempVar2 = BusinessData.GetConsultationGroup(ConsultationId, tempRef_aConsultationGroup);
				aConsultationGroup = tempRef_aConsultationGroup.argvalue;
			if (tempVar2)
			{
				aConsultationGroup.AddCommander(aCommander);
			}
		}
		
		//通知所有指挥终端状态改变 
		
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));		
		CommandEvent.OnCommanderStateChange(CommanderId,ConsultationId ,CommanderWorkStatus.Consultation.getValue());
		

//		DataService ds = new DataService();
//		ds.EnterConsultation(CommanderId, ConsultationId, IsEnter);


	}


}
