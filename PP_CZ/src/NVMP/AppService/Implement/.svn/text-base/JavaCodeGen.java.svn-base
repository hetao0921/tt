package NVMP.AppService.Implement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import org.misc.log.LogUtil;
import NVMP.AppService.util.MethodUtil;
import NVMP.AppService.util.ParamInfo;
import NVMP.AppService.util.ProxyObj;
import NVMP.AppService.util.ProxyObj.typeSelect;
import NVMP.AppService.util.ProxyUtil;

import NVMP.AppService.Interface.IAppRuntime;

import javassist.ClassPool;
import javassist.CtClass;

import javassist.CtField;
import javassist.CtMethod;

import javassist.NotFoundException;

public class JavaCodeGen {

	private static boolean isFirst = true;
	private static final CtClass[] NO_ARGS = {}; 
	// ÓÃÕâžö±êÊŸ±êŒÇÊÇ·ñÐèÒªÖØÐÂÉú³ÉÐÂµÄÀà¡£
	// Ò»°ãÇé¿öÏÂ£¬ÎÒÃÇÃ»±ØÒª¶¯Ì¬Éú³ÉÀà£¬Èç¹ûÓÐÕâžö±ØÒª
	// ŸÍËµÃ÷ÀàÐèÒª¶¯Ì¬žÄ¶¯²¢Ê¹ÓÃ£¬ËùÒÔ£¬Õâžö±äÁ¿ÊÇºÜÓÐÓÃµÄ
	private static boolean needReload = true;
	private static DirectLoader s_classLoader = new DirectLoader();

	static CtClass[] GetDelegateParameterTypes(Method type, ClassPool pool)
			throws NotFoundException {
		// if (type.BaseType != typeof(MulticastDelegate))
		// throw new ArgumentException("Not a delegate.", type.Name);
		//
		// MethodInfo invoke = type.GetMethod("Invoke");
		// if (invoke == null)
		// throw new ArgumentException("Not a delegate.", type.Name);
		Class<?>[] ca = type.getParameterTypes();
		List<CtClass> list = new ArrayList<CtClass>();
		for (Class<?> c : ca) {
			list.add(pool.get(c.getName()));
		}
		return (CtClass[]) list.toArray();
		// Method invoke;
		// try {
		// invoke = type.getMethod("Invoke");
		//
		// Class[] parameters = invoke.getParameterTypes();
		//
		// return parameters;
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return null;
		// }

	}
   public static int i=0;
/*
	public static void HookEvent(Method aEventInfo, Object EventObject,
			java.lang.reflect.Method Method, Object o, Object Context) {
		i++;
		try {
			// TODO Auto-generated method stub
			ClassPool pool = ClassPool.getDefault();
			CtClass clas = null;
//			if (!isFirst) {
//				pool = ClassPool.getDefault();
//				clas = pool.get("EvntHandlerWrap"+i);
//				if (clas.isFrozen()) {
//					System.out
//							.println("Class ExecVar is frozen,Please looking for something that can make it unfrozen!");
//					// clas.defrost();
//					// ŽÓpoolÖÐ²ðÀëÕâžöÀà
//					clas.detach();
//				}
//			}
//			isFirst = false;

			// ¶¯Ì¬Éú³ÉÒ»žöÀà
			clas = pool.makeClass("EvntHandlerWrap"+i);
			CtClass cObject = pool.get("java.lang.Object");
			CtClass cMethod = pool.get("java.lang.reflect.Method");
			CtField cf = new CtField(cObject, "o", clas);
			clas.addField(cf);
			cf = new CtField(cObject, "Context", clas);
			clas.addField(cf);
			cf = new CtField(cMethod, "Method", clas);
			clas.addField(cf);

			// ÏÈ×ö SetCaller ·œ·š£¬ÕâžöÃ²ËÆÊÇÐŽËÀµÄ
			CtClass[] ca = new CtClass[] { cMethod, cObject, cObject };
			CtMethod meth = new CtMethod(CtClass.voidType, "SetCaller", ca,
					clas);
			meth.setBody("this.Method = $1;");
			meth.insertAfter("this.o = $2;");
			meth.insertAfter("this.Context = $3;");
			clas.addMethod(meth);

			// ÔÙ×öEventHandler·œ·š¡£
			CtClass[] ca1 = GetDelegateParameterTypes(aEventInfo, pool);

			ParamInfo[] ps = MethodUtil.getMethodParamInfoToArray(
					EventObject.getClass(), aEventInfo.getName());
			meth = new CtMethod(CtClass.voidType, "EventHandler", ca1, clas);
			meth.setBody("try {");
			meth.insertAfter("Hashtable<String, Object> EventParams = new Hashtable<String, Object>();");
			for (int i = 1; i <= ca1.length; i++) {
				meth.insertAfter("EventParams.put(\"$" + ps[i].getName()
						+ "\", $" + i + ")");
			}
			meth.insertAfter("Object[] HandlerParams = { EventParams, Context };");
			meth.insertAfter("Method.invoke(o, HandlerParams);");
			meth.insertAfter("} catch (Exception e){e.printStackTrace(); }");

			//s_classLoader = new DirectLoader();
			Class HandlerType = clas.toClass();

//			Class HandlerType = s_classLoader.load("EvntHandlerWrap",
//					clas.toBytecode());

			Object HandlerObject = HandlerType.newInstance();
			Method SetCalllerMethod = HandlerType.getMethod("SetCaller");
			Object[] SetCallerParams = { Method, o, Context };
			SetCalllerMethod.invoke(HandlerObject, SetCallerParams);
			// Delegate d = Delegate.CreateDelegate(aEventInfo.EventHandlerType,
			// HandlerObject, "EventHandler");
			// aEventInfo.AddEventHandler(EventObject, d);

		} catch (Exception e) {

		}

	}
*/

	@SuppressWarnings("rawtypes")
	private static Class createExecClass() throws Exception {
		// ClassPoolÊÇjavasssitÖÐµÄÀà£¬×÷ÓÃÂï£¬×ÔÃèÊöµÄ
		ClassPool pool = ClassPool.getDefault();
		CtClass clas = null;
		if (!isFirst) {
			// Èç¹û²»ÊÇµÚÒ»ŽÎ£¬ŸÍ±ØÐëÒªœ«Ô­ÀŽµÄÀàÔÚpoolÖÐ×÷·Ï£¬
			// ·ñÔòÎÞ·šÔÚÍ¬Ò»žöpoolÖÐÉú³ÉÐÂµÄÀà
			// ŸßÌå×ö·šÊÇ·ñÕýÈ·£¬ÓÐŽýœøÒ»²œ¿ŒÖ€
			pool = ClassPool.getDefault();
			clas = pool.get("EvntHandlerWrap");
			if (clas.isFrozen()) {
				System.out
						.println("Class ExecVar is frozen,Please looking for something that can make it unfrozen!");
				// clas.defrost();
				// ŽÓpoolÖÐ²ðÀëÕâžöÀà
				clas.detach();
			}
		}
		isFirst = false;

		// ¶¯Ì¬Éú³ÉÒ»žöÀà

		clas = pool.makeClass("EvntHandlerWrap");

		// clas.addInterface(pool.get("NVMP.AppService.IAccess"));
		// // ÔöŒÓÄ¬ÈÏµÄ¹¹Ôì·œ·š
		// CtConstructor cons = new CtConstructor(NO_ARGS, clas);
		// cons.setBody(";");
		// clas.addConstructor(cons);

		CtClass cObject = pool.get("java.lang.Object");
		CtClass cMethod = pool.get("java.lang.reflect.Method");
		CtField cf = new CtField(cObject, "o", clas);
		clas.addField(cf);
		cf = new CtField(cObject, "Context", clas);
		clas.addField(cf);
		cf = new CtField(cMethod, "Method", clas);
		clas.addField(cf);

		// public void SetCaller(Method Method, Object o, Object Context) {
		// this.Method = Method;
		// this.o = o;
		// this.Context = Context;
		// }

		// ÏÈ×ö SetCaller ·œ·š£¬ÕâžöÃ²ËÆÊÇÐŽËÀµÄ
		CtClass[] ca = new CtClass[] { cMethod, cObject, cObject };
		CtMethod meth = new CtMethod(CtClass.voidType, "SetCaller", ca, clas);
		meth.setBody("this.Method = $1;");
		meth.insertAfter("this.o = $2;");
		meth.insertAfter("this.Context = $3;");
		clas.addMethod(meth);

		// ÔÙ×öEventHandler·œ·š¡£

		// // ÔöŒÓÒ»žö·œ·š£¬²ÎÊýÎªjava.util.List£¬·µ»ØÖµÊÇ¿Õ£¬Ãû×ÖœÐ×öCalculate£¬ËùÊôÀàÊÇclas
		// CtMethod meth = new CtMethod(CtClass.voidType, "Calculate",
		// new CtClass[] { pool.get("java.util.List") }, clas);
		// // ÓÃÕâžöËæ»úÊýŒì²âÊÇ·ñÊÇÐÂÉú³ÉµÄÀà
		// double random = Math.random();
		// //meth.setBody("System.out.println(\"ÕâÊÇÒ»žöreload²âÊÔ:\""+$1.get(0)+"\",Ëæ»úÊý:\""+
		// random + "\");");
		// meth.setBody("System.out.println(\"ÕâÊÇÒ»žöreload²âÊÔ\"+$1.get(0));");
		// // œ«·œ·šŒÓÈëÆäÖÐ
		// clas.addMethod(meth);
		// // ŽŽœšÒ»žöÐÂµÄClassLoader£¬ÒÔŒÓÔØÐÂµÄÉú³ÉµÄÀà£¬ŸÉµÄs_classLoaderÁ¬Í¬ŸÉµÄ
		// // ExecVarÀàÒ»Æð±»·ÏÆú
		s_classLoader = new DirectLoader();
		return s_classLoader.load("EvntHandlerWrap", clas.toBytecode());
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		Class cl = JavaCodeGen.createExecClass();
		Field[] fs = cl.getDeclaredFields();
		System.out.println(cl.getSimpleName());
		for (Field f : fs) {
			System.out.println(f.getName());
		}
		Method[] ms = cl.getMethods();
		for (Method m : ms) {
			if (m.getName().equals("SetCaller")) {
				for (Class s : m.getParameterTypes()) {
					System.out.println(s.getName());

				}
			}
		}

	}


	@SuppressWarnings("rawtypes")
	public static void HookEvent(Field info, Object o,
			IAppRuntime applicationServer, String string,java.lang.reflect.Method method) {
		// TODO Auto-generated method stub
		// ÕâÀï¿ªÊŒ²ð·ÖinterfaceÖÐÓÐ¶àžö·œ·š£¬ÒªÈ«²¿ÊµÏÖ¡£
		// ParamInfo[] ps = MethodUtil.getMethodParamInfoToArray(info.getType(),
		// "OnSendEncodeDeviceLogin");
		// for(ParamInfo pi: ps) {
		// System.out.println(pi.getName()+"=="+pi.getType());
		//
		// }
        i++;
		try {
			ClassPool pool = ClassPool.getDefault();
			try{
	            CtClass as = pool.get("NVMP.CommandDomain.ICommandEvent");					
			}catch(Exception e)
			{
				System.out.println("**************** " + e.getMessage());
			}
			CtClass clas = null;
//			if (!isFirst) {
//				pool = ClassPool.getDefault();
//
//				clas = pool.get("EvntHandlerWrap");
//
//				if (clas.isFrozen()) {
//					System.out
//							.println("Class ExecVar is frozen,Please looking for something that can make it unfrozen!");
//					// clas.defrost();
//					// ŽÓpoolÖÐ²ðÀëÕâžöÀà
//					clas.detach();
//				}
//			}
//			isFirst = false;

			CtClass cinterface = pool.get(info.getType().getName());

			clas = pool.makeClass("EvntHandlerWrap"+i, cinterface);

			// ÏÈÉùÃ÷ÊôÐÔ

			CtClass cObject = pool.get("java.lang.Object");
			CtClass cmethod = pool.get("java.lang.reflect.Method");
//			CtClass cIAppRuntime = pool
//					.get("NVMP.AppService.Interface.IAppRuntime");
			CtField cf = new CtField(cObject, "o", clas);
			clas.addField(cf);
			cf = new CtField(cObject, "appRuntime", clas);
			clas.addField(cf);
			cf = new CtField(cmethod, "method", clas);
			clas.addField(cf);		
			

			// ÏÈ×ö SetCaller ·œ·š
			CtClass[] ca = new CtClass[] { cObject, cObject, cmethod};
			CtMethod meth = new CtMethod(CtClass.voidType, "SetCaller", ca,
					clas);
			meth.setBody("this.o = $1;");
			meth.insertAfter("this.appRuntime = $2;");
			meth.insertAfter("this.method = $3;");
			clas.addMethod(meth);

			// Ñ­»·ÊµÏÖ,ÕâžöÒªÑ­»·ÉÏÃæÄÇžöinterfaceµÄËùÓÐ·œ·š¡£
			for (CtMethod cm : cinterface.getDeclaredMethods()) {

				ParamInfo[] ps = MethodUtil.getMethodParamInfoToArray(
						info.getType(), cm.getName());
				meth = new CtMethod(cm.getReturnType(), cm.getName(),
						cm.getParameterTypes(), clas);
				// meth = new CtMethod(cm,clas,null);
				String s = "";
//				meth.setBody("{}");
				// meth.addLocalVariable("EventParams",pool.get("java.util.HashMap"));
				// meth.addLocalVariable("contxt",pool.get("java.lang.String"));
				s = s + " try {java.util.HashMap EventParams = new java.util.HashMap();";
				s = s + "java.lang.String contxt;";

				// cm.setBody("try {");
				// meth.setBody("java.util.HashMap EventParams = new java.util.HashMap();");
				// ParamInfo[] ps =
				// MethodUtil.getMethodParamInfoToArray(info.getType(),
				// cm.getName());
				int i = 0;
				
				//¿ªÊŒÉú³ÉŽúÀíÎÄŒþÖÐµÄÊÂŒþÀà
				ProxyObj poj = new ProxyObj();
				poj.setDomainName(string+"."+cm.getName());
				poj.setName(cm.getName());
				poj.setParamInfoArray(ps);
				poj.setType(typeSelect.event);
				
				List li = ProxyUtil.getProxyFile(string.substring(0, string.indexOf("."))).getEventList();
				if (!li.contains(poj)) {li.add(poj);				
				 LogUtil.debug("文件加入event已成功，全名："+poj.getDomainName());
				}
				for (ParamInfo pi : ps) {
					// meth.insertAfter("EventParams.put(\""
					// + pi.getName() + "\",$"
					// + (++i)+ ");");
					s = s + "EventParams.put(\"" + pi.getName() + "\",$"
							+ (++i) + ");";
				}

				// meth.insertAfter("contxt = \"" + string + cm.getName()
				// + "\";");
				s = s + "contxt = \"" + string + "."+ cm.getName() + "\";";

				s = s + "java.lang.Object[] bv = {EventParams,contxt};";
				s = s + "this.method.invoke(this.appRuntime,bv);" ;
				s = s+ "} catch (Exception e){e.printStackTrace(); }";
				// meth.insertAfter("this.appRuntime=null;");
				// meth.insertAfter("this.appRuntime.EventHandler(EventParams, contxt);");
//				s = s + "this.appRuntime.EventHandler(EventParams, contxt);";
				
//				LogUtil.debug(s);
				meth.setBody(s);
//				meth.addCatch(s, pool.get("java.lang.Exception"));
				
				// meth.insertAfter("} catch (Exception e){e.printStackTrace(); }");
				
				clas.addMethod(meth);
			}

			//s_classLoader = new DirectLoader();

			Class HandlerType = clas.toClass();


			Object HandlerObject = HandlerType.newInstance();
			Method SetCalllerMethod = HandlerType.getMethod("SetCaller",
					Object.class, Object.class,java.lang.reflect.Method.class);
			Object[] SetCallerParams = { o, applicationServer, method};
			SetCalllerMethod.invoke(HandlerObject, SetCallerParams);
			
			info.set(o, HandlerObject);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
