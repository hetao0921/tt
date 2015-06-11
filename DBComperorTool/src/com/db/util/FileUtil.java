package com.db.util;

import java.io.RandomAccessFile;

public class FileUtil {
	
	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public static int wirteXml(String address, String xml) {
		int flag=-1;
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
			flag=0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static int execCommand(){
		int result=-1;
		
		
		return result;
	}

}
