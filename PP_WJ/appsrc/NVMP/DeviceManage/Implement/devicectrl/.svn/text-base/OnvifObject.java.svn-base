package NVMP.DeviceManage.Implement.devicectrl;

import com.fxdigital.onvif.ctrl.DeviceCtrl;

public class OnvifObject {
	private String sAddress, uri, sUsrName, sUsrPass;
	private int port, handle;

	public OnvifObject(String sAddress, String uri, int port, String sUsrName,
			String sUsrPass, int handle) {
		this.sAddress = sAddress;
		this.port = port;
		this.sUsrName = sUsrName;
		this.sUsrPass = sUsrPass;
		this.uri = uri;
		this.handle = handle;
	}

	public String getsAddress() {
		return sAddress;
	}

	public String getUri() {
		return uri;
	}

	public String getsUsrName() {
		return sUsrName;
	}

	public String getsUsrPass() {
		return sUsrPass;
	}

	public int getPort() {
		return port;
	}

	public int getHandle() {
		return handle;
	}

	public boolean isCtrl() {
		boolean b = false;
		if (handle < 50000) {
			b = true;
		} else {

			@SuppressWarnings("static-access")
			int n = (int) DeviceCtrl.Instance().controlCreate(sAddress, uri,
					port, sUsrName, sUsrPass);
			if (n >= 0) {
				handle = n;
				b = true;
			}
		}

		return b;
	}

}
