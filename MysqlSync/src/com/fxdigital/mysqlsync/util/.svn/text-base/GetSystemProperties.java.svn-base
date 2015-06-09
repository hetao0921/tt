package com.fxdigital.mysqlsync.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class GetSystemProperties {
	public static void main(String[] args) {
		InetAddress netAddress = getInetAddress();
		System.out.println("host ip:" + getHostIp(netAddress));
		System.out.println("host name:" + getHostName(netAddress));
		Properties properties = System.getProperties();
		Set<String> set = properties.stringPropertyNames(); // 获取java虚拟机和系统的信息。
		for (String name : set) {
			System.out.println(name + ":" + properties.getProperty(name));
		}
		getLocalIp();
	}

	public static InetAddress getInetAddress() {

		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;

	}

	public static String getHostIp(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress(); // get the ip address
		return ip;
	}

	public static String getHostName(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String name = netAddress.getHostName(); // get the host address
		return name;
	}

	public static String getLocalIp() {
		String localIp="";
		Enumeration<NetworkInterface> allNetInterfaces;  //定义网络接口枚举类
	try {
		allNetInterfaces = NetworkInterface.getNetworkInterfaces();  //获得网络接口
       
		InetAddress ip = null; //声明一个InetAddress类型ip地址
		while (allNetInterfaces.hasMoreElements()) //遍历所有的网络接口
		{
			NetworkInterface netInterface = allNetInterfaces.nextElement();
			//System.out.println(netInterface.getName());  //打印网端名字
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses(); //同样再定义网络地址枚举类
			while (addresses.hasMoreElements())
			{
				ip = addresses.nextElement();
				if (ip != null && (ip instanceof Inet4Address)&&(!ip.getHostAddress().equals("127.0.0.1"))) //InetAddress类包括Inet4Address和Inet6Address
				{  
					System.out.println("本机的IP = " + ip.getHostAddress());
					localIp=ip.getHostAddress();
				} 
			}
		}
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return localIp;

}
}
