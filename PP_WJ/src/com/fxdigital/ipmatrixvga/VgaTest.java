package com.fxdigital.ipmatrixvga;



public class VgaTest {
	
	static public  void main(String[] args) throws InterruptedException{		
		VgaIpMatrixCtrl.getVgaIpMatrixCtrl("0");
		VgaIpMatrixCtrl.MaxtrixCreate(0, 0, null, 0, null, null);
		Thread.sleep(500);
		VgaIpMatrixCtrl.MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
		System.out.println("aaaaaaaaaaaa");
		
//		DviIpMatrixCtrl.getDviIpMatrixCtrl("1");
//		DviIpMatrixCtrl.MaxtrixCreate(1, 0, null, 0, null, null);
//		DviIpMatrixCtrl.MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
//		System.out.println("aaaaaaaaaaaa");
	}
}
