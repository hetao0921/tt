package com.sqlite.conn;

import java.util.Calendar;

public class Common {
	
	static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static long getNowTime(){
		Calendar ca = Calendar.getInstance();
		return ca.getTimeInMillis(); 
	}
	
	public static void writeText(String path,String str){
		java.io.RandomAccessFile raf = null;
		try{
			raf = new java.io.RandomAccessFile(path,"rwd");
			raf.seek(raf.length());
			raf.write(str.getBytes("utf-8"));
			raf.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getDateTime(){
		Calendar ca = Calendar.getInstance();
		String str = sdf.format(ca.getTime());
		return str;
	}
}