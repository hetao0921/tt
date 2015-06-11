package com.fxdigital.ipmatrixvga;

import com.fxdigital.ipmatrix.IMatrix;

public class VgaIpMatrixCtrl  implements IMatrix{
	static private VgaIpMatrixCtrl vgaCtrl = new VgaIpMatrixCtrl();
	static public boolean getVgaIpMatrixCtrl(String name){
		IpMatrixVgaCtrl.getIpMatrixVgaCtrl(name);
		return true;
	}
	
	static public IMatrix getInstance() {
		return vgaCtrl;
	}


	static public boolean MaxtrixCleanup() {
		return IpMatrixVgaCtrl.MaxtrixCleanup();
	}

	static public boolean MaxtrixStartup() {
		return IpMatrixVgaCtrl.MaxtrixStartup();
	}

	static public boolean MaxtrixDelete(int nInstance) {
		return IpMatrixVgaCtrl.MaxtrixDelete(nInstance);
	}

	static public int MaxtrixCreate(int nDeviceType, int nDeviceSubType,
			String szAddress, int nPort, String szName, String szPass) {
		return IpMatrixVgaCtrl.MaxtrixCreate(nDeviceType, nDeviceSubType,
				szAddress, nPort, szName, szPass);
	}

	public boolean MaxtrixStop(int nMaxtrixHandle) {
		return IpMatrixVgaCtrl.MaxtrixStop(nMaxtrixHandle);
	}

	static public int MaxtrixStart(int nInstance, int nShowChannel,
			int nScreenRegion, String szAddress, int nPort, String szName,
			String szPass, int nChannel, int nLinkType, int nSubLinkType,
			int nLinkMode) {
		return IpMatrixVgaCtrl.MaxtrixStart(nInstance, nShowChannel,
				nScreenRegion, szAddress, nPort, szName, szPass, nChannel,
				nLinkType, nSubLinkType, nLinkMode);

	}

	static public void main(String[] args){
		getVgaIpMatrixCtrl("0");
		MaxtrixCreate(0, 0, null, 0, null, null);
		MaxtrixStart(0, 0, 0, "rtsp://192.168.1.78:554/0", 0, null, null, 0, 0, 0, 0);
	}


}
