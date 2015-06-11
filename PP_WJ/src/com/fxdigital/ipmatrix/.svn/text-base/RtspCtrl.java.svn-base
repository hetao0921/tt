package com.fxdigital.ipmatrix;

public class RtspCtrl {

	private static final String ClibName = "rtspcltrl";

	static {
		System.loadLibrary(ClibName);
//		init();
	}

	/**
	 * 静态初始化
	 * */
	static public native void init() ; 
	
	
	/**
	 * 进行点播
	 * 
	 * 
	 * */
	
	static public native int MaxtrixStart(String rtsp,
			 String szAddress, int nPort, String szName,
			String szPass, int nChannel,int nScreenRegion );
	
	
	// 停止解码
	/*
	 * 参数说明: int nMaxtrixHandle -- MaxtrixStart 的返回值
	 */
	static public native boolean MaxtrixStop(int nMaxtrixHandle);
	
	
	static public void main(String...args) {
		
//		System.out.println(RtspCtrl.getRtspCtrl().MaxtrixStart(rtsp, szAddress, nPort, szName, szPass, nChannel, nScreenRegion));
//	System.out.println(String.format("aa%3$saaaaaaa,bb%1$sbbbbbbbbbbb,cccc%2$scccccc","1","2","3"));
	
	}
	
	
}
