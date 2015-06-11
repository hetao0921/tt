package NVMP.IpMatrixManage.Implement;

import NVMP.Proxy.VideoContrlDomain.EventHandler;

public class VideoContrlEvenetHander extends EventHandler {
	private IpMatrixManageRun imr;

	public void setFXStoreRun(IpMatrixManageRun ipMatrixManageRun) {
		// TODO Auto-generated method stub
		this.imr = ipMatrixManageRun;
	}

	// 点播失败
	public Object PlayFailedEvent(String DevicId, Integer CameraIndex,
			Integer type, String Context, String Reason) {
		
		 
		imr.getMatrixObject().PlayFailedEvent(DevicId, CameraIndex, type, Context, Reason);
	
	
		return null;
	}

	@Override
	public Object GetClientPlayEvent(String client, String deviceid,
			Integer channel, String context) {
//		imr.onGetClientPlayEvent(client, deviceid, channel, context);
		return null;
	}

	// 点播成功
	public Object ControlDisplayVideoEvent(String DeviceId,
			Integer CameraIndex, String VideoServerIP,
			Integer VideoServerChannel, String user, String Password,
			Integer Port, Integer DeviceType, Integer DeviceSubType,
			Integer NetLinkType, Integer NetLinkSubType, Integer NetLinkMode,
			String Context, Integer flag) {
		
		imr.getMatrixObject().ControlDisplayVideoEvent(imr,DeviceId, CameraIndex, VideoServerIP, VideoServerChannel, user, Password, Port, DeviceType, DeviceSubType, NetLinkType, NetLinkSubType, NetLinkMode, Context, flag);
		//I帧捕获
		imr.fireDeviceShow(DeviceId, CameraIndex);

//		System.out.println("0===)====================>");
//		System.out.println(imr.getnInstance());
//		System.out.println(pv.getTVIndex());
//		System.out.println(pv.getPos());
//		System.out.println(VideoServerIP);
//		System.out.println(Port);
//
//		if (user == null || user.trim().equals(""))
//			user = "admin";
//		System.out.println(user);
//		if (Password == null || Password.trim().equals(""))
//			Password = "12345";
//		System.out.println(Password);
//		System.out.println(VideoServerChannel);
//		System.out.println(NetLinkType);
//		System.out.println(NetLinkSubType);
//		System.out.println(NetLinkMode);
//		System.out.println("0===)====================>");

		return null;
	}

}
