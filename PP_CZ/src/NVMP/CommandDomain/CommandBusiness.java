package NVMP.CommandDomain;


import NVMP.AppService.Interface.*;
import NVMP.Command.Business.*;







public class CommandBusiness extends SystemBusiness //implements IBusinessDomain //, ICommand
{




	
	private static IAppRuntime _AppRuntime = null;

	public static IAppRuntime AppRunTime()
	{
		return _AppRuntime;
	}

	public final boolean DomainEntry(IAppRuntime AppRuntime)
	{
		
		return true;
	}

	/** 
	
	 
	*/
	public final void DomainUnload()
	{

	}


	public final String GetDescription()
	{
		return "";
	}

	/** 
	
	 
	*/
	public final String GetDomainName()
	{
		return "CommandDomain";
	}


	public CommandBusiness()
	{

	

	}

	protected void finalize() throws Throwable
	{

	}

	public void dispose()
	{

	}

}