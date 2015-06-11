package com.fxdigital.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import org.apache.log4j.Logger;

public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public String readFileByLines(String path) {
		if (("").equals(path) || null == path) {
			return null;
		}
		File file = new File(path);
		BufferedReader reader = null;
		String result = "";
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				result = result + tempString+getNewLine();
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return result;
	}

	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public static void wirteXml(String address, String xml) {
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getNewLine(){
		String newLinePoint="\r\n";
		if(System.getProperty("os.name").contains("Windows")){
			newLinePoint="\r\n";
		}else{
			newLinePoint="\r";
		}
		return newLinePoint;
	}
	
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String execStr(String str) {
		logger.info("开始执行" + str);
		StringBuffer sb = new StringBuffer();
		try {
			ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c", str);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				logger.info(line);
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			logger.info("file copy exception :" + e);
			e.printStackTrace();
		}
		logger.info("结束执行" + str);
		return sb.toString();

	}

}
