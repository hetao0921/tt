package NVMP.AppService.Implement;

import org.misc.log.LogUtil;

import NVMP.AppService.Interface.*;
import corenet.rpc.*;

public class BaseBusinessObject implements NVMP.AppService.Interface.IBusinessObject
{

	private Object aObject;

	private java.util.HashMap<String, NVMPMethodInfo> NVMPMethodInfoList;


	public final void AddMethod(String aName, NVMPMethodInfo aMethodInfo)
	{
		NVMPMethodInfoList.put(aName, aMethodInfo);
	}
 
	public BaseBusinessObject(Object aObject)
	{
		this.aObject = aObject;
		NVMPMethodInfoList = new java.util.HashMap<String, NVMPMethodInfo>();
	}



	@SuppressWarnings("rawtypes")
	public final IMessage CallMethod(String MethodName, IMessage Message)
	{   
		IMessage aMessage = null;
		try
		{
			NVMPMethodInfo aMethod = NVMPMethodInfoList.get(MethodName);


		int len = aMethod.getParamInfo().length;
		Object[] Params = new Object[len];

		for (int i = 0; i < len; ++i)
		{
			
			/**
			 * by zzw
			 * 
			 * **/
			String type = aMethod.getParamInfo()[i].getType();
			String Name = aMethod.getParamInfo()[i].getName();

			if (type.equals("int"))
			{  
				Params[i] = Integer.parseInt(Message.GetParam(Name).toString());
		
			}
			if (type.equals("java.lang.Integer"))
			{  
				String str = Message.GetParam(Name).toString();
				if(str.equals(""))  Params[i] = null;
			    else
				Params[i] = Integer.valueOf(str);		
			}
			else if (type.equals("java.lang.String"))
			{
				Params[i] = String.valueOf(Message.GetParam(Name));
			}
			else if (type.equals("boolean"))
			{
				Params[i] = Boolean.parseBoolean(Message.GetParam(Name).toString());
			}
			else if(type.equals("java.lang.Boolean")) {
				
				
				String str = Message.GetParam(Name).toString();
				Params[i] = Boolean.valueOf(str);
				
				
				
			}
		}
		Object RetVal = null;
		
		
        try {
        	LogUtil.info("方法调用"+aMethod.getName());
     		RetVal = aMethod.getMethodInfo().invoke(aObject, Params);
     		
     		if (RetVal == null) return null;
     		
        } catch(Exception e) {
        	e.printStackTrace();
        	
        	LogUtil.error("方法调用，结果报错:"+MethodName+" "+Message.Serilize()+" "+e.toString());
			
        	for(StackTraceElement a:e.getStackTrace()) {
				LogUtil.error(a.toString());
			}
        	
        	
        	return null;	
        }
		if (aMethod.getRetParam() == null)
		{
			return null;
		}

		aMessage = new BaseMessage();
		
		
		//临时先丢个空的再说
		aMessage.SetAction("Function");
		aMessage.SetObjURL(Message.GetObjURL());
		
		

		
		//处理方法执行返回结果，按照结果的RetVal的类型情况来进行
		
		if (aMethod.getRetParam().length == 1)
		{
			aMessage.AddParam("Result", RetVal);
		}
		else
		{
			ReturnParam[] rpArray = aMethod.getRetParam();
			for (int i = 0; i < rpArray.length; i++)
			{
				
				java.lang.Class type = RetVal.getClass();
				Object o= null;
				try {
					o = type.getField(rpArray[i].RetName).get(RetVal);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/**
				 * by zzw
				 * 
				 * */
				
				//Object o = type.InvokeMember(aMethod.getRetParam()[i].RetName, BindingFlags.DeclaredOnly | BindingFlags.Public | BindingFlags.Instance | BindingFlags.GetProperty, null, RetVal, null);
				aMessage.AddParam(rpArray[i].RetName, o);
				
				
			}

		}


		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			LogUtil.error("调用远程方法出错 ：方法名称为 "+MethodName+e.getMessage());
			
		}
		
		return aMessage;
	}
}
