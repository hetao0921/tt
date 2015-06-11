package NVMP.AppService.Interface;

import java.lang.reflect.TypeVariable;

import NVMP.AppService.util.ParamInfo;

public class NVMPMethodInfo
			{ 
				private String _Name;

				public final String getName()
				{
					return _Name;
				}
				public final void setName(String value)
				{
					_Name = value;
				}
				private java.lang.reflect.Method _MethodInfo;

				public final java.lang.reflect.Method getMethodInfo()
				{
					return _MethodInfo;
				}
				public final void setMethodInfo(java.lang.reflect.Method value)
				{
					_MethodInfo = value;
					
					
				}
				
				/*
				 * zzw
				 * 反射的参数信息
				 * */
				private ParamInfo[] _ParamInfo;

				public final ParamInfo[] getParamInfo()
				{
					return _ParamInfo;
				}
				public final void setParamInfo(ParamInfo[] value)
				{
					_ParamInfo = value;
				}
				
				
				
				
				
				
				
				
				
				private ReturnParam[] _RetParam;

				public final ReturnParam[] getRetParam()
				{
					return _RetParam;
				}
				public final void setRetParam(ReturnParam[] value)
				{
					_RetParam = value;
				}
}
