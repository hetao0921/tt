package NVMP.DeviceManage.Implement.devicectrl;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import sun.misc.BASE64Encoder;

import com.fxdigital.EcDevice.ctrl.DeviceCtrl;
import com.fxdigital.EcDevice.ctrl.IDevNotify;
import com.fxdigital.EcDevice.ctrl.VideoEffect;
import com.fxdigital.onvif.ctrl.VideoCompress;

/**
 * 管理所有的设备操作
 * */
public class DeviceCtrlManager {
	private final int HIK = 100;
	private final int NV = 221;
	private final int BLANC = 222;

	private DeviceCtrlManager() {
	}

	static public DeviceCtrlManager getINSTANCE() {
		return _DeviceCtrlManager.INSTANCE;
	}

	static private class _DeviceCtrlManager {
		static private DeviceCtrlManager INSTANCE = new DeviceCtrlManager();
	}

	public int login(int type, String sAddress, int Port, String sUsrName,
			String sUsrPass, Object Context) {
		int n = -1;

		switch (type) {
		case HIK:
			n = DeviceCtrl.Instance().login(sAddress, Port, sUsrName, sUsrPass,
					Context);
			break;
		case NV:
			n = NVDeviceCtrl.getINSTANCE().login(sAddress, Port, sUsrName,
					sUsrPass, Context);
			break;
//		case BLANC:
//			n = NVDeviceCtrl.getINSTANCE().login(sAddress, Port, sUsrName,
//					sUsrPass, Context);
//			break;
		default:
			n = OnvifDeviceCtrl.getINSTANCE().login(sAddress, Port, sUsrName, sUsrPass, Context);
			break;
		}

		return n;
	}

	@SuppressWarnings("static-access")
	public void setIdn(IDevNotify deviceRun) {
		DeviceCtrl.Instance().setIdn(deviceRun);
	}

	public void startup() {
		DeviceCtrl.Instance().startup();
	}

	public void startListen(int type, int n, String hostIP, int port) {
		switch (type) {
		case HIK:
			DeviceCtrl.Instance().startListen(n, hostIP, port);
			break;
		default:
			break;
		}

	}

	public void logout(int deviceType, int deviceHandle) {
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().logout(deviceHandle);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().logout(deviceHandle);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().logout(deviceHandle);
			break;
		}
	}

	public void setOSD(int deviceType, int deviceHandle, Integer cameraindex,
			Boolean isDisplyDatetime, boolean b, Integer x, Integer y,
			String oSDName) {
		
		Integer newChannel = changeChannel(cameraindex);
		
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().setOSD(deviceHandle, newChannel,
					isDisplyDatetime, b, x, y, oSDName);

			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().setOSD(deviceHandle, newChannel,
					isDisplyDatetime, b, x, y, oSDName);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().setOSD(deviceHandle, newChannel, isDisplyDatetime, 
					b, x, y, oSDName);
			break;
		}

	}

	private Integer changeChannel(Integer cameraindex) {
		if(cameraindex>9999) {
			return cameraindex-10000;
		}
		return cameraindex;
	}

	public boolean ctrlAlarm(int deviceType, int deviceHandle, Integer index,
			Integer alarmType, int i) {
		boolean b = false;
		
		Integer newChannel = changeChannel(index);
		
		switch (deviceType) {
		case HIK:
			b = DeviceCtrl.Instance().ctrlAlarm(deviceHandle, newChannel, alarmType,
					i);

			break;
		case NV:
			break;
		default:
			b = OnvifDeviceCtrl.getINSTANCE().controlAlarm(deviceHandle, newChannel, alarmType, i);
			break;
		}
		return b;
	}

	public void ctrlPTZ(int deviceType, int deviceHandle, Integer cameraindex,
			Integer speed, Integer direction, int i) {
		
		Integer newChannel = changeChannel(cameraindex);
		
		switch (deviceType) {
		case HIK:
//			DeviceCtrl.Instance().ctrlPTZ(deviceHandle, newChannel, speed*7/100,
//					direction, i);\
			DeviceCtrl.Instance().ctrlPTZ(deviceHandle, newChannel, speed,
					direction, i);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().controlPTZ(deviceHandle, newChannel,
					speed, direction, i);
			break;
		default:
//			OnvifDeviceCtrl.getINSTANCE().controlPTZ(deviceHandle, newChannel, speed, direction, i);
			OnvifDeviceCtrl.getINSTANCE().controlPTZ(deviceHandle, newChannel, speed * 100 / 7, direction, i);

			break;
		}

	}

	public void ctrlVideoEffect(int deviceType, int deviceHandle,
			Integer cameraindex, Integer type, Integer value) {
		
		Integer newChannel = changeChannel(cameraindex);
		
		System.out.println("================ctrlVideoEffect==============");
		System.out.println("type = " + type);
		System.out.println("value = " + value);
		
		switch (deviceType) {
		case HIK:

			DeviceCtrl.Instance().ctrlVideoEffect(deviceHandle, newChannel,
					type, value);
			
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().setVideoEffect(deviceHandle,
					newChannel, type, 10 * value);

			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().setVideoEffect(deviceHandle, newChannel,
					type, 10*value);

			break;
		}

	}

	public void selectPrePoint(int deviceType, int deviceHandle,
			Integer cameraindex, Integer index) {
		
		Integer newChannel = changeChannel(cameraindex);
		
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().selectPrePoint(deviceHandle, newChannel,
					index);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().selectPrePoint(deviceHandle,
					newChannel, index);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().selectPrePoint(deviceHandle, newChannel,
					index);
			break;
		}

	}

	public void setPrePoint(int deviceType, int deviceHandle,
			Integer cameraindex, Integer index) {
		
		Integer newChannel = changeChannel(cameraindex);
		
		
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().setPrePoint(deviceHandle, newChannel, index);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().setPrePoint(deviceHandle, newChannel,
					index);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().setPrePoint(deviceHandle, newChannel, index);
			break;
		}

	}

	public void deletePrePoint(int deviceType, int deviceHandle,
			Integer cameraindex, Integer index) {
		Integer newChannel = changeChannel(cameraindex);
		
		
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().deletePrePoint(deviceHandle, newChannel,
					index);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().deletePrePoint(deviceHandle,
					newChannel, index);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().deletePrePoint(deviceHandle, newChannel,
					index);
			break;
		}

	}

	public boolean getVideoEffect(int deviceType, int deviceHandle,
			Integer channl, VideoEffect a) {
		
		Integer newChannel = changeChannel(channl);
		
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().getVideoEffect(deviceHandle, newChannel, a);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().getVideoEffect(deviceHandle, newChannel, a);
			break;
		default:
			com.fxdigital.onvif.ctrl.VideoEffect vc = new com.fxdigital.onvif.ctrl.VideoEffect();
			OnvifDeviceCtrl.getINSTANCE().getVideoEffect(deviceHandle, newChannel, vc);

			a.setBrightness(vc.getM_nBirghtness()/10);
			a.setContrast(vc.getM_nContrast()/10);
			a.setHue(vc.getM_nHue()/10);
			a.setSaturation(vc.getM_nSaturation()/10);
			
			System.out.println("10 * Hue = " + vc.getM_nHue());

			break;
		}
		return true;
	}

	public void MakeKeyFrame(int deviceType, int deviceHandle, Integer index,
			int i) {

		Integer newChannel = changeChannel(index);
		// URL url;
		// try {
		// url = new URL(
		// "http://192.168.1.161/command/camera.cgi?H264InsertIFrame=On");
		//
		// URLConnection connection = url.openConnection();
		// connection.setReadTimeout(5000);
		// String strap = "admin:admin";
		// connection.setRequestProperty("Authorization", "Basic "
		// + (new BASE64Encoder()).encode(strap.getBytes("utf-8")));
		// connection.connect();
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		//
		// e.printStackTrace();
		// }

		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().MakeKeyFrame(deviceHandle, newChannel, i);
			break;
		case BLANC:
			NVDeviceCtrl.getINSTANCE().MakeKeyFrame(deviceHandle, newChannel, i);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().MakeKeyFrame(deviceHandle, newChannel, i);
			break;
		}

	}

	@SuppressWarnings("static-access")
	public void setTime(int deviceType, int deviceHandle, Integer year,
			Integer month, Integer day, Integer hour, Integer minute,
			Integer second) {
	
		switch (deviceType) {
		case HIK:
			DeviceCtrl.Instance().setTime(deviceHandle, year, month, day, hour,
					minute, second);
			break;
		case NV:
			NVDeviceCtrl.getINSTANCE().setTime(deviceHandle, year, month, day,
					hour, minute, second);
			break;
		default:
			OnvifDeviceCtrl.getINSTANCE().setTime(deviceHandle, year, month, day, hour,
					minute, second);
			break;
		}

	}

	public static void main(String[] args) {
		URL url;
		while (true) {
			try {
				Thread.sleep(500);
				url = new URL(
						"http://192.168.1.161/command/camera.cgi?H264InsertIFrame=On");

				URLConnection connection = url.openConnection();
				connection.setReadTimeout(5000);
				String strap = "admin:admin";
				connection
						.setRequestProperty(
								"Authorization",
								"Basic "
										+ (new BASE64Encoder()).encode(strap
												.getBytes("utf-8")));
				connection.connect();

				Scanner in = new Scanner(connection.getInputStream(), "gbk");
				while (in.hasNext()) {
					String str = in.next();
					System.out.println(str);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getVideoCompress(int deviceType, int deviceHandle,
			Integer channl, VideoCompress vc) {
		
		Integer newChannel = changeChannel(channl);
		
		switch (deviceType) {
		case HIK:
			com.fxdigital.EcDevice.ctrl.VideoCompress vc2 = new com.fxdigital.EcDevice.ctrl.VideoCompress();
			boolean ret = DeviceCtrl.Instance().getVideoCompress(deviceHandle, newChannel, vc2);
			
			if (!ret) {
				return false;
			}
			
			vc.setM_bConstBitrate(vc2.getConstBitrate());
			vc.setM_bStdCodec(vc2.getStdCodec());
			vc.setM_nBitrate(vc2.getBitrate());
			vc.setM_nCodecType(vc2.getCodec());
			vc.setM_nFramerate(vc2.getFramerate());
			vc.setM_nQuality(vc2.getQuality());
			vc.setM_nGovLength(vc2.getIFrameInterval());
			
			int pictureSize = vc2.getPictureSize();
			
			ResolutionData data = ResolutionMap.getDataByResolutionType(pictureSize);
			if (data == null) {
				return false;
			}
			
			vc.setM_nResolution(data.getX(), data.getY());
			return ret;
		case NV:
			break;
		default:
			System.out.println("==========   cry   =============");
			System.out.println(deviceHandle);
			System.out.println(channl);
			boolean b = OnvifDeviceCtrl.getINSTANCE().getVideoCompress(deviceHandle,
					newChannel, vc);
			System.out.println(vc.getM_nGovLength());
			return b;
		}
		return true;
	}

	public boolean setVideoCompress(int deviceType, int deviceHandle,
			Integer channl, VideoCompress vc) {
		Integer newChannel = changeChannel(channl);
		
		switch (deviceType) {
		case HIK:
			int nType = 0;
			int bitrate = vc.getM_nBitrate();
			int framerate = vc.getM_nFramerate();
			int x = vc.getM_nResolutionX();
			int y = vc.getM_nResolutionY();
			int interval = vc.getM_nGovLength();
			
			if (bitrate != -1) {
				nType = 1;
				return DeviceCtrl.Instance().ctrlVideoCompress(deviceHandle, newChannel, nType, bitrate);
			} else if (framerate != -1) {
				nType = 2;
				return DeviceCtrl.Instance().ctrlVideoCompress(deviceHandle, newChannel, nType, framerate);
			} else if (x != -1 && y != -1) {
				nType = 3;
				ResolutionData data = new ResolutionData();
				data.setX(x);
				data.setY(y);
				int resolutionType = ResolutionMap.getResolutionTypeByData(data);
				
				if (resolutionType == -1) {
					return false;
				}
				
				return DeviceCtrl.Instance().ctrlVideoCompress(deviceHandle, newChannel, nType, resolutionType);
			} else if (interval != -1) {
				nType = 4;
				return DeviceCtrl.Instance().ctrlVideoCompress(deviceHandle, newChannel, nType, interval);
			} else {
				System.out.println("setVideoCompress方法调用时，视频压缩参数不正确");
				return false;
			}
		case NV:
			break;
		default:
			return OnvifDeviceCtrl.getINSTANCE().setVideoCompress(deviceHandle,
					newChannel, vc);
		}
		return true;
	}

}
