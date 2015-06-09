package com.fxdigital.syncclient.util;

import java.lang.reflect.Field;


public class ConvertMapUtil {
	public static String map (Class cals){
		String ret="select new Map(";
		try {
			Class cal=cals;
			 Field[] fieldlist = cal.getDeclaredFields();
			 
			 for (Field field : fieldlist) {
//				System.out.println(field.getName());
				 ret=ret+field.getName()+" as "+field.getName().toLowerCase()+","; 
			}
			ret=ret.substring(0,ret.length()-1)+")";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public static String assemblyMap (Class cals){
		String ret=" new Map(";
		try {
			Class cal=cals;
			 Field[] fieldlist = cal.getDeclaredFields();
			 
			 for (Field field : fieldlist) {
//				System.out.println(field.getName());
				 ret=ret+field.getName()+" as "+field.getName().toLowerCase()+","; 
			}
			ret=ret.substring(0,ret.length()-1)+")";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

}
