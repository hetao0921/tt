package NVMP.VideoControlDomain;

import NVMP.AppService.Remoting;

@Remoting
public class IVideoControl {
	// 告诉客户端转发失败
	/**
	 * @param type
	 *            0 设备不存在 1 设备下线 2 转发服务器下线 3 转接服务器连接失败 4连接受到限制,5请求中未看见路由信息。
	 * */
	public void OnPlayFailed(String DevicId, Integer CameraIndex, Integer type,
			String Context, String Reason) {
	}; 

	// 告诉转发服务器转发停止（计数减一 、直接停止）
	/**
	 * @param type
	 *            0 全删  -1 强制    1、引用计数减一;
	 * */
	public void StopStreamFoward(String ClientId, String DeviceId,String DeviceIP,
			Integer CameraIndex, String VideoServerId,
			Integer VideoServerChannel, Integer type, String Reason) {
	};

	public void StartStreamFoward(String ClientId, String DeviceId,
			Integer CameraIndex, String VideoServerIP,
			Integer VideoServerChannel, String user, String Password,
			Integer Port, Integer DeviceType, // 设备厂商
			Integer DeviceSubType,// 设备型号
			Integer NetLinkType, // 网络连接类型
			Integer NetLinkSubType,// 网络连接子类型
			Integer NetLinkMode,// 网络连接模式
			String Context, Integer flag) {
	};
	

	public void OnControlDisplayVideo(String DeviceId, Integer CameraIndex,
			String VideoServerIP, Integer VideoServerChannel, String user,
			String Password, Integer Port, Integer DeviceType, // 设备厂商
			Integer DeviceSubType,// 设备型号
			Integer NetLinkType, // 网络连接类型
			Integer NetLinkSubType,// 网络连接子类型
			Integer NetLinkMode,// 网络连接模式
			String Context, Integer flag) {
	}

	public void onSearchVidoPlay(String clientID, String clientIP,
			String deviceId, String deviceIP, Integer linkedMode,
			Integer cameraIndex, String sourceIP, Integer netPort,
			Integer videoServerChannel, String context) {
		// TODO Auto-generated method stub
		
	}

	public void onGetALlRoute(String type, String clientid, String clientip,
			String deviceid, String deviceip, Integer devicechannel,
			String routemap, Integer lev, String sourceip,
			Integer sourcechannel, String forwardid, String sourceuuid,
			String realroutemap, String uuid, Integer sendflag, String sendid,
			String context) {}

	public void OnGetClientPlay(String client, String deviceid,
			Integer channel, String context) {
	}
	
	

	public void StartPreFoward(String deviceId, Integer index, String context) {
	}

	public void StopPreFoward(String deviceId, Integer deviceIdChannel,String context,Integer flag) {
	}
	
	

	public void refreshPreFoward(String deviceid) {
	}
}
