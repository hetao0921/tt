package com.fxdigital.mqcore.util;

public class Encoding {
	public static String byteToString(byte[] a) {
		
		
		
		String str = "";
		try {
			 if(a!=null)	str = new String(a, "UTF-8");
		} catch (Exception e) {
              e.printStackTrace();
              
              if(a!=null) {
            	  
            	  for(byte m :a){
            		  System.out.println(m);
            		  
            	  }
            	  
              }
              
              
		}
		return str;
	}
	
	public static byte[] StringToByte(String str){
		try {
		return str.getBytes("UTF-8");
		} catch (Exception e) {
		e.printStackTrace();
		return null;	
		}
	} 
	

	
	
	
}