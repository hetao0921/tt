package NVMP.IpMatrixDomain;

import java.util.List;

import org.misc.log.LogUtil;

import corenet.exchange.Encoding;
import corenet.rpc.IMessage;

import NVMP.AppService.Remoting;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;
import NVMP.Command.Business.DBAccess;
import NVMP.Command.Business.SystemBusiness;
import NVMP.CommandDomain.CommandDomain;
import NVMP.CommandDomain.CommandGroupBusiness;
import NVMP.CommandDomain.CommanderBusiness;
import NVMP.CommandDomain.ConferenceGroupBusiness;
import NVMP.CommandDomain.ConsultationGroupBusiness;

public class MatrixDomain implements IBusinessDomain
{
	private static IAppRuntime _AppRuntime = null;
	private IpMatrixBusiness _IPMatirx;
	
    

   public static IAppRuntime AppRunTime()
   {
	   return _AppRuntime;
   }
   	   
   
	public final boolean DomainEntry(IAppRuntime AppRuntime)
	{ 
		_AppRuntime = AppRuntime;		
		_IPMatirx = new IpMatrixBusiness(); 	
        AppRuntime.RegisterObject(this.GetDomainName(),_IPMatirx, "");
        _AppRuntime.CreateGroup("G_Matrix");
		return true;
	}
	
	public final void DomainUnload()
	{
      
	}

	public final String GetDescription()
	{
		return "MatrixDomain";
	}
	

	/** 
	 获取应用域名称，该组件注册的对象都在改命名空间下
	 
	*/
	public final String GetDomainName()
	{
		return "MatrixDomain";
	}

	
	@Override
	public void OnDomainMessage(String Sessionid, 
			String Groupid,
			String state, 
			String type) 
	{
	    
		if (Groupid.equals("G_Matrix"))
	    {
			
	    	if (state.equals("1")) //在线
	    	{
	    		
	    		_IPMatirx.SendMatrixOnline(Sessionid, true);
	    	}
	    	else//不在线
	    	{	    		
	    		_IPMatirx.SendMatrixOnline(Sessionid, false);
	    	}
	    	
	    }
	}


	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
