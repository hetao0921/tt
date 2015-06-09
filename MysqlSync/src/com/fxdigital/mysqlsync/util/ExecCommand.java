package com.fxdigital.mysqlsync.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * 命令行执行函数类
 *
 */
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
				//logger.info(inline);
				// logger.info("哈哈");

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 logger.info("当前错误"+e+getTrace(e));
			// e.printStackTrace();

			logger.info(getTrace(e));
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
			 logger.info("当前错误"+e+getTrace(e));
			// e.printStackTrace();

			logger.info(getTrace(e));
		}
		return result;
	}
	
	public static String excuteStrCommand(String[] command) {
		logger.info("当前命令:" + command);
		Runtime r = Runtime.getRuntime();
		Process p;
		String inline = "";
		logger.info("命令输出:");
		try {
			BufferedReader br = null;
			p = r.exec(command);
			p.waitFor();
			br = new BufferedReader(
					new InputStreamReader((p.exitValue() != 0 ? p
							.getErrorStream() : p.getInputStream()), "GBK"));

			while ((inline = br.readLine()) != null) {
				logger.info(inline);
				logger.info(inline);
				// logger.info("哈哈");

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 logger.info("当前错误"+e+getTrace(e));
			// e.printStackTrace();

			logger.info(getTrace(e));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inline;
	}

	
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	public static void excuteCommand(String[] command) {

		Runtime r = Runtime.getRuntime();
		Process p;
		try {

			p = r.exec(command);
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// p.getInputStream(),"GBK"));
			// String inline;
			// while ((inline = br.readLine()) != null) {
			// logger.info(inline);
			// logger.info("哈哈");
			//
			// }
			// br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getExcuteResult(String command) {
		
		List<String> list = new ArrayList<String>();
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
			while ((inline = br.readLine()) != null) {
				
				logger.info(inline+";"+i);
				list.add(inline);
				str += inline+";";
				i++;
			}
			br.close();
		} catch (IOException e) {
			str = getTrace(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
			str = getTrace(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static boolean setCommand(String command){
		boolean flag=true;
		logger.info("执行命令开始:"+command);
		Process process = null;  
        List<String> processList = new ArrayList<String>();  
        try {
        	process = Runtime.getRuntime().exec("/bin/sh -c sudo su");
            process = Runtime.getRuntime().exec("/bin/sh -c "+command);  
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));  
            String line = "";  
            while ((line = input.readLine()) != null) {  
                processList.add(line);  
            }  
            input.close();  
        } catch (IOException e) {  
        	flag=false;
        	logger.info("执行命令有误:"+command);
        	logger.error(e);
            e.printStackTrace();  
        }  
  
        for (String line : processList) {  
        	logger.info("命令反馈:"+line);
            System.out.println(line);  
        }  
        return flag;
	
	}

	public static void getLocalIP() throws Exception {
		Enumeration e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();
		while (e1.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) e1.nextElement();
			System.out.print(ni.getName());
			System.out.print(": ");
			Enumeration e2 = ni.getInetAddresses();
			while (e2.hasMoreElements()) {
				InetAddress ia = (InetAddress) e2.nextElement();
				if (ia instanceof Inet6Address)
					continue; // omit IPv6 address
				System.out.print(ia.getHostAddress());
				if (e2.hasMoreElements()) {
					System.out.print(", ");
				}
			}
			System.out.print("\n");
		}
	}
}
