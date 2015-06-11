package NVMP.AppService.util;

import java.lang.reflect.Method;
import java.util.Map;

import NVMP.AppService.Interface.IBusinessDomain;
import NVMP.AppService.Interface.IBusinessObject;
import org.misc.log.LogUtil;

/**
 * »ñÈ¡ÏµÍ³ÖÐž÷ÖÖÔËÐÐÊ±ÐÅÏ¢¡£
 * */
public class HelpUtil {
 
	/**
	 * ²ÎÊýÎªµ±Ç°ÔËÐÐµÄ»·Ÿ³ Íš¹ýŽ«ÈëµÄÊµÀý£¬Ö±œÓÕÒµœÈ«²¿ŽúÀíÀà¡£
	 * */
	public static void showProxyClass(Object obj) {
		try {
			Method method = obj.getClass().getMethod("getBusinessDomainList");
			java.util.HashMap<String, IBusinessDomain> hp = (java.util.HashMap<String, IBusinessDomain>) method
					.invoke(obj);
			for (Map.Entry me : hp.entrySet()) {
				System.out.println(me.getKey());
			}

		} catch (Exception e) {
			LogUtil.error("»ñÈ¡È«²¿ŽúÀíÀàµÄÊ±ºò³öÏÖÒì³££º " + e.getMessage());
		}

	}

	/**
	 * ²ÎÊýÎªµ±Ç°ÔËÐÐµÄ»·Ÿ³ ºÍÒ»žö±ä³€µÄ×Ö·ûŽ®Êý×é¡£ »ñµÃËùÓÐŽúÀíÀà¶ÔÓŠµÄÈ«²¿·œ·š
	 * */
	public static void showProxyFunction(Object obj, String... str) {
		try {
			Method method = obj.getClass().getMethod("getBusinessDomainList");
			java.util.HashMap<String, IBusinessDomain> hp = (java.util.HashMap<String, IBusinessDomain>) method
					.invoke(obj);

			if (str.length == 0) {

				for (Map.Entry me : hp.entrySet()) {

					Object no = me.getValue();
					method = obj.getClass().getMethod("getBusinessObjectList");
					java.util.HashMap<String, IBusinessObject> hp2 = (java.util.HashMap<String, IBusinessObject>) method
							.invoke(no);
					for (Map.Entry me2 : hp2.entrySet()) {
						System.out.println(me2.getKey());
					}
				}
			} else {
				for (String s : str) {
					Object no = hp.get(s);
					method = obj.getClass().getMethod("getBusinessObjectList");
					java.util.HashMap<String, IBusinessObject> hp2 = (java.util.HashMap<String, IBusinessObject>) method
							.invoke(no);
					for (Map.Entry me : hp2.entrySet()) {
						System.out.println(me.getKey());
					}

				}

			}

		} catch (Exception e) {
			LogUtil.error("»ñÈ¡ŽúÀí·œ·šµÄÊ±ºò³öÏÖÒì³££º " + e.getMessage());
		}

	}

}
