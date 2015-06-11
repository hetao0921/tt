package NVMP.AppService.Implement;

import java.util.List;
import org.misc.log.LogUtil;

import corenet.rpc.IMessage;

import NVMP.AppService.Interface.*;

 public class ServerSideDomain implements IBusinessDomain
{
	private java.util.HashMap<String, IBusinessObject> BusinessObjectList;
	

	public java.util.HashMap<String, IBusinessObject> getBusinessObjectList() {
		return BusinessObjectList;
	}

	public IBusinessDomain _Domain;
  

	public final IBusinessObject FindObject(String ObjectName)
	{
		IBusinessObject aObject = null;
		try
		{
			aObject = BusinessObjectList.get(ObjectName);
//			try { 
//			LogUtil.debug("寻找对象"+ObjectName);
//			} catch(Exception e) {
//				
//				e.printStackTrace();
//			}
//			Iterator i = BusinessObjectList.entrySet().iterator();
//			while (i.hasNext()) {
//				
//				Map.Entry entry = (Map.Entry) i.next();
//				LogUtil.debug("存在对象"+entry.getKey());
////				 System.out.println(entry.getKey());
////				 System.out.println(entry.getValue());
//				 
//				 
//			}
			
			
			
		}
		catch (Exception e)
		{
			 LogUtil.error("找寻处理方法时候异常："+e.toString());
		}
		return aObject;
	}


	public final void AddObject(String name, IBusinessObject Obj)
	{
		try
		{
			if (BusinessObjectList.get(name) != null)
			{
				// warning ... 
			}
		}
		catch (RuntimeException e)
		{

		}

		BusinessObjectList.put(name, Obj);
	}


	public ServerSideDomain(IBusinessDomain Domain)
	{
		 BusinessObjectList = new java.util.HashMap<String, IBusinessObject>();
		_Domain = Domain;
	}

	public final boolean DomainEntry(IAppRuntime AppRuntime)
	{
		return _Domain.DomainEntry(AppRuntime);
	}

	/** 
	 业务组件被卸载时调用
	 
	*/
	public final void DomainUnload()
	{

	}

	public final String GetDescription()
	{
		return _Domain.GetDescription();
	}

	/** 
	 获取应用域名称，该组件注册的对象都在改命名空间下
	 
	*/
	public final String GetDomainName()
	{
		return _Domain.GetDomainName();
	}


	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}



}
