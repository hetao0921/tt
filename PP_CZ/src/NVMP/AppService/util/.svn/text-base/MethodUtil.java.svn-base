package NVMP.AppService.util;

import java.util.Arrays;

//import NVMP.AppService.CommandDomain.ICommandEvent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class MethodUtil {
 
	public static void main(String[] args) throws Exception {
		// Æ¥ÅäŸ²Ì¬·œ·š
//		String[] paramNames = getMethodParamNames(MethodUtil.class, "main",
//				String[].class);
//		System.out.println(Arrays.toString(paramNames));
//		// Æ¥ÅäÊµÀý·œ·š
//		paramNames = getMethodParamNames(MethodUtil.class, "foo", String.class);
//		System.out.println(Arrays.toString(paramNames));
//		// ×ÔÓÉÆ¥ÅäÈÎÒ»žöÖØÃû·œ·š
//		paramNames = getMethodParamNames(MethodUtil.class, "getMethodParamNames");
//		System.out.println(Arrays.toString(paramNames));
//		// Æ¥ÅäÌØ¶šÇ©ÃûµÄ·œ·š
//		paramNames = getMethodParamNames(MethodUtil.class, "getMethodParamNames",
//				Class.class, String.class);
//		System.out.println(Arrays.toString(paramNames));
//		NVMP.AppService.CommandDomain.ICommandEvent
//		 ParamInfo[] ps =  getMethodParamInfoToArray(ICommandEvent.class, "OnSendEncodeDeviceLogin");
//		 for(ParamInfo pi: ps) {
//			 System.out.println(pi.getName()+"=="+pi.getType());
//			 
//		 }
		
		
		
		
	}

	/**
	 * »ñÈ¡·œ·š²ÎÊýÃû³Æ£¬°Žžø¶šµÄ²ÎÊýÀàÐÍÆ¥Åä·œ·š
	 * 
	 * @param clazz
	 * @param method
	 * @param paramTypes
	 * @return
	 * @throws NotFoundException
	 *             Èç¹ûÀà»òÕß·œ·š²»ŽæÔÚ
	 * @throws MissingLVException
	 *             Èç¹û×îÖÕ±àÒëµÄclassÎÄŒþ²»°üº¬ŸÖ²¿±äÁ¿±íÐÅÏ¢
	 */
	@SuppressWarnings("rawtypes")
	public static String[] getMethodParamNames(Class clazz, String method,
			Class... paramTypes) throws NotFoundException, MissingLVException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get(clazz.getName());
		String[] paramTypeNames = new String[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++)
			paramTypeNames[i] = paramTypes[i].getName();
		CtMethod cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
		return getMethodParamNames(cm);
	}

	/**
	 * »ñÈ¡·œ·š²ÎÊýÃû³Æ£¬Æ¥ÅäÍ¬ÃûµÄÄ³Ò»žö·œ·š
	 * 
	 * @param clazz
	 * @param method
	 * @return
	 * @throws NotFoundException
	 *             Èç¹ûÀà»òÕß·œ·š²»ŽæÔÚ
	 * @throws MissingLVException
	 *             Èç¹û×îÖÕ±àÒëµÄclassÎÄŒþ²»°üº¬ŸÖ²¿±äÁ¿±íÐÅÏ¢
	 */
	@SuppressWarnings("rawtypes")
	public static String[] getMethodParamNames(Class clazz, String method)
			throws NotFoundException, MissingLVException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get(clazz.getName());
		CtMethod cm = cc.getDeclaredMethod(method);
		return getMethodParamNames(cm);
	}

	/**
	 * »ñÈ¡·œ·š²ÎÊýÃû³Æ
	 * 
	 * @param cm
	 * @return
	 * @throws NotFoundException
	 * @throws MissingLVException
	 *             Èç¹û×îÖÕ±àÒëµÄclassÎÄŒþ²»°üº¬ŸÖ²¿±äÁ¿±íÐÅÏ¢
	 */
	protected static String[] getMethodParamNames(CtMethod cm)
			throws NotFoundException, MissingLVException {
		CtClass cc = cm.getDeclaringClass();
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
				.getAttribute(LocalVariableAttribute.tag);
		if (attr == null)
			throw new MissingLVException(cc.getName());

		String[] paramNames = new String[cm.getParameterTypes().length];
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < paramNames.length; i++)
			paramNames[i] = attr.variableName(i + pos);
		return paramNames;
	}

	/**
	 * ÔÚclassÖÐÎŽÕÒµœŸÖ²¿±äÁ¿±íÐÅÏ¢<br>
	 * Ê¹ÓÃ±àÒëÆ÷Ñ¡Ïî javac -g:{vars}ÀŽ±àÒëÔŽÎÄŒþ
	 * 
	 * @author Administrator
	 * 
	 */
	public static class MissingLVException extends Exception {
		static String msg = "class:%s ²»°üº¬ŸÖ²¿±äÁ¿±íÐÅÏ¢£¬ÇëÊ¹ÓÃ±àÒëÆ÷Ñ¡Ïî javac -g:{vars}ÀŽ±àÒëÔŽÎÄŒþ¡£";

		public MissingLVException(String clazzName) {
			super(String.format(msg, clazzName));
		}
	}

	
	@SuppressWarnings("rawtypes")
	public static ParamInfo[] getMethodParamInfoToArray(Class clazz, String method){

		
		
		try {
			
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get(clazz.getName());
			CtMethod cm = cc.getDeclaredMethod(method);
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);
			CtClass[] cs = cm.getParameterTypes();

			//String[] paramNames = new String[cs.length];
			ParamInfo[] ps = new ParamInfo[cs.length]; 

			int pos = 0;
			
			pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			

			for (int i = 0; i < cs.length; i++) {
				ps[i] = new ParamInfo();
				ps[i].setName(attr.variableName(i + pos));
			    ps[i].setType(cs[i].getName());

			// paramNames[i] = attr.variableName(i + pos);
			}

			return ps;

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("+++++++++++++ getMethodParamInfoToArray exception: " + e.getMessage() + " ++++++++++++++");
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}
	
	
	void cc(String c,int b,boolean c1,String d) {
		
		
	}
	
	static void foo() {
	}

	void foo(String bar) {
	}
}
