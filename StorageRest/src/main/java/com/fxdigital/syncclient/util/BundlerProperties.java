package com.fxdigital.syncclient.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BundlerProperties {
	private static final Logger logger = Logger.getLogger(BundlerProperties.class);

	
	public  Properties getProperties(String fileName){
		Properties pps = new Properties();
		try {
			InputStream is = this.getClass().getResourceAsStream("/"+fileName); 
			pps.load(is); 
		} catch (FileNotFoundException e) {
			System.out.println(fileName +" not find.");
		} catch (IOException e) {
			System.out.println(fileName +" load error.");
		}
		return pps;
	}
	
	public Properties getPropertie(String fileName){
		Properties pps = new Properties();
		try {
			File directory = new File(".");
			String path=directory.getCanonicalPath(); //得到的是C:/test  
			System.out.println("当前路径 :" +path);
			String filePath=path+"/resources/"+fileName;
			
			 InputStream is = new BufferedInputStream (new FileInputStream(filePath)); 
			pps.load(is); 
		} catch (FileNotFoundException e) {
			System.out.println(fileName +" not find.");
		} catch (IOException e) {
			System.out.println(fileName +" load error.");
		}
		return pps;
	}
	
	public static void main(String[] args) {
		BundlerProperties bundlerProperties=new BundlerProperties();
		Properties pps= bundlerProperties.getPropertie("ontime.properties");
		System.out.println(pps.get("uptatefile.interval"));
		System.out.println(pps.get("videofile.coderate"));
		System.out.println(pps.get("videofile.duraltime"));
	}
}
