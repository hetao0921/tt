package com.fxdigital.syncclient.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class VideoConverter {
	private static final Logger logger = Logger.getLogger(VideoConverter.class);

	/**
	 * Convert Video for hint
	 * 
	 * @param filePath
	 *            like /usr/local/movies/001.mp4
	 * @return convert message
	 */
	public static String Convert(String filePath) {
		String cmd = "/usr/local/bin/MP4Box -hint " + filePath;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(cmd);
		return execStr(cmd);
	}

	private static String exec(String cmd) {
		logger.info("开始执行" + cmd);
		StringBuffer sb = new StringBuffer();
		try {
			String[] cmdA = { "/bin/sh", "-c", cmd };
			Process process = Runtime.getRuntime().exec(cmdA);
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				logger.info(line);
				logger.info(line);
				sb.append(line).append("\n");
			}
			logger.info(sb.toString());
		} catch (Exception e) {
			logger.info("file convert exception :" + e);
			e.printStackTrace();
		}
		logger.info("结束执行" + cmd);
		return sb.toString();
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
				logger.info(line);
				logger.info(line);
				sb.append(line).append("\n");
			}
		} catch (Exception e) {
			logger.info("file convert exception :" + e);
			e.printStackTrace();
		}
		logger.info("结束执行" + str);
		return sb.toString();

	}
	
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> execStrList(String str) {
		logger.info("开始执行" + str);
		StringBuffer sb = new StringBuffer();
		List<String> result=null;
		try {
			ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c", str);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			result=new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				logger.info(line);
				sb.append(line).append("\n");
				result.add(line);
			}
		} catch (Exception e) {
			logger.info("file convert exception :" + e);
			e.printStackTrace();
		}
		logger.info("结束执行" + str);
		return result;

	}

	/**
	 * 获取文件名称
	 * 
	 * @return
	 */
	public static String getFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = "/usr/local/movies/storage/" + sdf.format(new Date())
				+ ".mp4";
		return fileName;
	}
}
