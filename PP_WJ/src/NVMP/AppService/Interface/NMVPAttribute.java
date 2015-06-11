package NVMP.AppService.Interface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.Remote;

import NVMP.AppService.Remoting;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[AttributeUsage(AttributeTargets.All, AllowMultiple = false, Inherited = false)]
public class NMVPAttribute // extends Attribute
{
	public NMVPAttribute(String Description_in) {
		this.description = Description_in;
	} 

	protected String description;

	public final String getDescription() {
		return this.description;
	}
	
	/**
	 * by zzw 用注解的方式来判断是否为永久工作。
	 * **/
	public static boolean IsAlwaysWork(Method Info) {
		try {
			
			Annotation annotation = Info.getAnnotation(Remoting.class);

			if(annotation!=null && ((Remoting)annotation).value().equals("yes")) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean IsRemoting(Method Info) {
		try {
			/**
			 * by zzw 用注解的方式来判断是否为远程调用
			 * **/

			Annotation[] as = Info.getDeclaredAnnotations();
			for (Annotation a : as) {
				if (a.annotationType().getSimpleName().equals("Remoting")) {
					return true;
				}
			}
		} catch (RuntimeException e) {
		}

		return false;
	}

	// 判断属性filed是不是事件接口实现。

	public static boolean IsEventRemoting(Field Info) {
		try {
			/**
			 * by zzw 用注解的方式来判断是否为事件接口
			 * **/

			Class<?> cls = Info.getType();

			Annotation[] as = cls.getDeclaredAnnotations();
			for (Annotation a : as) {
				if (a.annotationType().getSimpleName().equals("Remoting")) {
					return true;
				}
			}
		} catch (RuntimeException e) {
		}

		return false;
	}

}
