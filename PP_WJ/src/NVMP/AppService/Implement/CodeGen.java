package NVMP.AppService.Implement;



public class CodeGen
{
	
	/*
	//
	// 获取发射的参数类型
	//
	private static ParameterInfo[] GetDelegateParameterTypes(java.lang.Class type)
	{
		if (type.getSuperclass() != MulticastDelegate.class)
		{
			throw new IllegalArgumentException("Not a delegate.", type.getName());
		}

		java.lang.reflect.Method invoke = type.getMethod("Invoke");
		if (invoke == null)
		{
			throw new IllegalArgumentException("Not a delegate.", type.getName());
		}

		ParameterInfo[] parameters = invoke.GetParameters();

		return parameters;

	}
*/
	//
	// 根据事件对象生成，事件处理代码，并把生成的代码关联到事件上
	// 
	
//	public static boolean HookEvent(EventInfo aEventInfo, Object EventObject, java.lang.reflect.Method Method, Object o, Object Context)
//	{
//		try
//		{
//			//创建编译器实例。      
//			CSharpCodeProvider provider = new CSharpCodeProvider();
//			//设置编译参数。      
//			CompilerParameters paras = new CompilerParameters();
//			paras.GenerateExecutable = false;
//			paras.GenerateInMemory = true;
//
//			paras.ReferencedAssemblies.Add("System.dll");
//			paras.ReferencedAssemblies.Add("System.Core.dll");
//
//			//创建动态代码。      
//			StringBuilder classSource = new StringBuilder();
//			classSource.append("using System;\n");
//			classSource.append("using System.Reflection;\n");
//			classSource.append("using System.Collections.Generic;\n");
//
//
//			classSource.append("public class " + "EvntHandlerWrap" + " {\n");
//			classSource.append("public object o;\n");
//			classSource.append("public object Context;\n");
//			classSource.append("public MethodInfo Method;\n");
//
//
//			ParameterInfo[] Params = GetDelegateParameterTypes(aEventInfo.EventHandlerType);
//
//			classSource.append("public void EventHandler(");
//			boolean FirstParam = true;
//			for (ParameterInfo Param : Params)
//			{
//				if (FirstParam)
//				{
//					classSource.append(Param.ParameterType.toString() + " " + Param.getName());
//					FirstParam = false;
//				}
//				else
//				{
//					classSource.append("," + Param.ParameterType.toString() + " " + Param.getName());
//				}
//			}
//			classSource.append(")\n");
//
//			classSource.append("{\n");
//			classSource.append("Dictionary<string,object> EventParams = new Dictionary<string,object>();\n");
//			for (ParameterInfo Param : Params)
//			{
//				classSource.append("EventParams.Add(" + "\"" + Param.getName() + "\"" + "," + Param.getName() + ");\n");
//			}
//
//			classSource.append("object[] HandlerParams = { EventParams,Context};\n");
//			classSource.append("Method.Invoke(o, HandlerParams);\n");
//
//			classSource.append("}\n");
//
//			classSource.append("public void SetCaller(MethodInfo Method,object o,object Context)\n");
//			classSource.append("{this.Method = Method; this.o = o;this.Context = Context;}\n");
//
//
//			classSource.append("}");
//
//
//			//    Console.WriteLine(classSource.ToString());
//
//			//编译代码。      
//			CompilerResults result = provider.CompileAssemblyFromSource(paras, classSource.toString());
//
//			//获取编译后的程序集。      
//			Assembly assembly = result.CompiledAssembly;
//
//			java.lang.Class HandlerType = assembly.GetType("EvntHandlerWrap");
//
//			Object HandlerObject = Activator.CreateInstance(HandlerType);
//
//			java.lang.reflect.Method SetCalllerMethod = HandlerType.getMethod("SetCaller");
//
//			Object[] SetCallerParams = { Method, o,Context};
//			SetCalllerMethod.invoke(HandlerObject, SetCallerParams);
//
//			Delegate d = Delegate.CreateDelegate(aEventInfo.EventHandlerType, HandlerObject, "EventHandler");
//			aEventInfo.AddEventHandler(EventObject, d);
//
//		}
//		catch (RuntimeException e)
//		{
//			return false;
//		}
//
//		return true;
//		
//	}


}
