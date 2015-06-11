package com.fxdigital.ipmatrixvga;



public class IpMatrixVgaCtrl {
	
	private static IpMatrixVgaCtrl _this;
	private static final String ClibLeftName = "fxplayerwrappervga";
	private static final String ClibRightName = "fxplayerwrapperdvi";
	private static String name;
	
	
	private IpMatrixVgaCtrl(String name) {
		if(name.equals("0"))
			  System.loadLibrary(ClibLeftName);
			else 
			  System.loadLibrary(ClibRightName);
		MaxtrixStartup();
		
	}

	public static IpMatrixVgaCtrl getIpMatrixVgaCtrl(String name) {
		if (_this == null)
			_this = new IpMatrixVgaCtrl(name);
		return _this;
	}

	@Override
	protected void finalize() throws Throwable {
		MaxtrixCleanup();
		super.finalize();

	}

	
	
	
//	public IpMatrixVgaCtrl(String name){
//		if(name.equals("0"))
//		  System.loadLibrary("streamplayerwrap");
//		else 
//		  System.loadLibrary("streamplayerwrap1");
//	}
	
	static public native boolean MaxtrixCleanup();

	static public native boolean MaxtrixStartup();

	static public native boolean MaxtrixDelete(int nInstance);

	static public native int MaxtrixCreate(int nDeviceType, int nDeviceSubType,
			String szAddress, int nPort, String szName, String szPass);

	static public native boolean MaxtrixStop(int nMaxtrixHandle);

	static public native int MaxtrixStart(int nInstance, int nShowChannel,
			int nScreenRegion, String szAddress, int nPort, String szName,
			String szPass, int nChannel, int nLinkType, int nSubLinkType,
			int nLinkMode);



	public static void main(String[] args) {
		// 到时候测试用方法现在用不上
		//new IpMatrixCtrl().MaxtrixCleanup();
//		new IpMatrixCtrl("0").MaxtrixStartup();
//		//new IpMatrixCtrl().MaxtrixDelete(0);
//		new IpMatrixCtrl(").MaxtrixCreate(0, 0, null, 0, null, null);
//		//new IpMatrixCtrl().MaxtrixStop(0);
//		new IpMatrixCtrl().MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
//		System.out.println("aaaaaaaaaaaa");
		
//		//new IpMatrixCtrl().MaxtrixCleanup();
//		new IpMatrixCtrl1().MaxtrixStartup();
//		//new IpMatrixCtrl().MaxtrixDelete(0);
//		new IpMatrixCtrl1().MaxtrixCreate(1, 0, null, 0, null, null);
//		//new IpMatrixCtrl().MaxtrixStop(0);
//		new IpMatrixCtrl1().MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
//		System.out.println("aaaaaaaaaaaa");
		
		//测试1
		IpMatrixVgaCtrl ic = new IpMatrixVgaCtrl("0");
		//ic.MaxtrixStartup();
		ic.MaxtrixCreate(0, 0, null, 0, null, null);
		ic.MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
		System.out.println("aaaaaaaaaaaa");
		
	}


}
