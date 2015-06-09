package com.fxdigital;
public class SysAuth
{
	static {
		try {
		    System.loadLibrary("sysauth");
		} catch(Exception e) {
			System.out.println("加载sysauth库出错。。 ");
	         e.printStackTrace();		
		}
	}
	
	
	public static native boolean importLicense(String licenseCode);
	public static native String[] getMacAddr();
	
	
	public static void main(String[] args) {
//		String[] macaddr = getMacAddr();
//		for (int i = 0; i < macaddr.length; i++) {
//			System.out.println(macaddr[i]);
//		}
	
		
		System.out.println(importLicense("P2JFL-EK4V7-VWS5J-RGA4U-AMB2="));
	}
}