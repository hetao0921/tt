package mavenweb;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fxdigital.analysis.annotation.Message;

public class GenerateClass {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class clazz=GenerateClass.class;
		System.out.println(clazz.getCanonicalName());
		Method[] methods=	clazz.getDeclaredMethods();
		for (Method method : methods) {
		Class<?> returnType=	method.getReturnType();
		if(!"void".equals(returnType.getName())){
			System.out.println(returnType.getCanonicalName());
//			System.out.println(Message[].class.getCanonicalName());
			
		}
	
		}
	
		String str =String.format("{he}", "aa");
		
		String he="com.fxdigital.analysis.web.CenterNetController";
		String[] sp=he.split("\\.");
		he=sp[sp.length-1];
/*		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(42));
		List<?> lu = li;
		System.out.println(lu.get(0));	
		int i=0;
		int j=1;
	int t=	new GenerateClass().ifThenElse(true,i ,j );
	System.out.println(t);*/
	}
	
	public List<Message> getURl(){
		return null;
	}
/*	public <T> T ifThenElse(boolean b, T first, T second) {
		return b ? first : second;
		}*/
}


