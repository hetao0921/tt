package NVMP.IpMatrixManage.Implement;

import NVMP.Proxy.StateManageDomain.EventHandler;

public class StateManageEvenetHander extends EventHandler {
	private IpMatrixManageRun imr;

	public void setFXStoreRun(IpMatrixManageRun ipMatrixManageRun) {
		imr = ipMatrixManageRun;
	}

	@Override
	public Object EncodeDeviceOnlineEvent(String TerminalId, String TerminalIP,
			Boolean IsOnline) { 
		
      //如果上线，我们判断设备
		if (!IsOnline) return null;
		
		imr.getMatrixObject().EncodeDeviceOnlineEvent(imr,TerminalId, TerminalIP, IsOnline);		
		return null;
	}

}
