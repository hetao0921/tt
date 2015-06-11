package app.handler;

import org.misc.log.LogUtil;

import app.ConnectChannel;
import NVMP.Proxy.VideoContrlDomain.EventHandler;

/**
 * 
 * @author hxht
 * @version 2014-9-17
 */
public class VideoControlHandler extends EventHandler{

	ConnectChannel channel;

	public VideoControlHandler(ConnectChannel connectChannel) {
		channel = connectChannel;
	}
	
	/**
	 * 点播成功
	 */
	@Override
	public Object ControlDisplayVideoEvent(String DeviceId,
			Integer CameraIndex, String VideoServerIP,
			Integer VideoServerChannel, String user, String Password,
			Integer Port, Integer DeviceType, Integer DeviceSubType,
			Integer NetLinkType, Integer NetLinkSubType, Integer NetLinkMode,
			String Context, Integer flag) {
		String padIP = channel.getPad().getBean().getSinglePadIP();
		LogUtil.info("单兵系统：单兵（"+padIP+"）收到视频流------");
		LogUtil.info("DeviceId："+DeviceId);
		LogUtil.info("CameraIndex："+CameraIndex);
		LogUtil.info("VideoServerIP："+VideoServerIP);
		LogUtil.info("VideoServerChannel："+VideoServerChannel);
		LogUtil.info("user："+user);
		LogUtil.info("Password："+Password);
		LogUtil.info("Port："+Port);
		LogUtil.info("DeviceType："+DeviceType);
		LogUtil.info("DeviceSubType："+DeviceSubType);
		LogUtil.info("NetLinkType："+NetLinkType);
		LogUtil.info("NetLinkSubType："+NetLinkSubType);
		LogUtil.info("NetLinkMode："+NetLinkMode);
		LogUtil.info("Context："+Context);
		LogUtil.info("flag："+flag);
		channel.sendVideoToSP(DeviceId, CameraIndex, VideoServerIP);
		
		return null;
	}
	
}
