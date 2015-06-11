package com.db.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;



public class ExecCommand {
	private static final Logger logger = Logger.getLogger(ExecCommand.class);
	
	public static String excuteStrCommand(String command) {
		logger.info("当前命令:" + command);
		Runtime r = Runtime.getRuntime();
		Process p;
		String inline = "";
		logger.info("命令输出:");
		try {
			BufferedReader br = null;
			p = r.exec(command);

			br = new BufferedReader(
					new InputStreamReader((null == p.getInputStream()
							|| "".equals(p.getInputStream()) ? p
							.getErrorStream() : p.getInputStream()), "GBK"));

			while ((inline = br.readLine()) != null) {
				logger.info(inline);
			}
			br.close();
		} catch (IOException e) {
			logger.info("当前错误"+e);
		}
		return inline;
	}
	
	
	public static int excuteGrantCommand(String command) {
		logger.info("当前命令:" + command);
		Runtime r = Runtime.getRuntime();
		int result=0;
		Process p;
		String inline = "";
		logger.info("命令输出:");
		try {
			BufferedReader br = null;
			p = r.exec(command);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result=p.exitValue();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("当前错误"+e);
		}
		return result;
	}
	
	
	public static int getConfirmResult(String command) {
		
		int result=0;
		String str = "";
		Runtime r = Runtime.getRuntime();
		Process p;
		try {
			BufferedReader br = null;
			p = r.exec(command);
			p.waitFor();
			br = new BufferedReader(
					new InputStreamReader( ((p.exitValue() != 0) ? p
							.getErrorStream() : p.getInputStream()), "GBK"));

			String inline;
			int i=0;
			result=p.exitValue();
			while ((inline = br.readLine()) != null) {
				
				logger.info(inline+";"+i);
				
				str += inline+";";
				i++;
			}
			br.close();
		} catch (IOException e) {
			str = e.toString();
			result=-1;
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			result=-1;
			e.printStackTrace();
		}
		return result;
	}
}
