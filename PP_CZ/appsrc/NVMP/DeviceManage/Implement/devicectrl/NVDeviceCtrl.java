package NVMP.DeviceManage.Implement.devicectrl;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sun.misc.BASE64Encoder;

import com.fxdigital.EcDevice.ctrl.VideoEffect;
import com.fxdigital.video.ctrl.DC_VIDEO_COMPRESS;
import com.fxdigital.video.ctrl.DC_VIDEO_EFFECT;

public class NVDeviceCtrl {

	private int handle;
	private Map<Integer, NVObject> hp;

	private NVDeviceCtrl() {
		handle = 0;
		hp = new HashMap<Integer, NVObject>();
	}

	static public NVDeviceCtrl getINSTANCE() {
		return _NVDeviceCtrl.INSTANCE;
	}

	static private class _NVDeviceCtrl {
		static private NVDeviceCtrl INSTANCE = new NVDeviceCtrl();
	}

	// 获取登录句柄
	public int login(String sAddress, int Port, String sUsrName,
			String sUsrPass, Object Context) {
		int n = handle;
		handle++;
		hp.put(n, new NVObject(sAddress, n, Context));
		return n;
	}

	// 删除登录句柄
	public void logout(int handle) {
		hp.remove(handle);
	}

	/**
	 * 设置设备时间
	 * */
	public boolean setTime(int hSession, int year, int month, int day,
			int hour, int minute, int second) {
		boolean b = false;

		NVObject nv = hp.get(hSession);

		if (nv != null) {

			b = nv.setTime(year, month, day, hour, minute, second);
		}
		return b;
	}

	/**
	 * 调节图像参数
	 * */
	public boolean setVideoEffect(int hSession, int nChannel, int nType,
			int nValue) {

		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.setVideoEffect(nChannel, nType, nValue);
		}
		return b;
	}

	/**
	 * 设置编码参数
	 * 
	 * 帧频率
	 * 
	 * 码流设置
	 * */

	public boolean setVideoCompress(int hSession, int nChannel, int nType,
			int nValue) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {

			b = nv.setVideoCompress(nChannel, nType, nValue);
		}
		return b;

	}

	/**
	 * 
	 * 时间戳设置
	 * */
	public boolean setOSD(int LoginHandle, int Channel, boolean ShowTime,
			boolean ShowOsd, int X, int Y, String OSD) {

		boolean b = false;
		NVObject nv = hp.get(LoginHandle);
		if (nv != null) {

			b = nv.setOSD(Channel, ShowTime, ShowOsd, X, Y, OSD);
		}
		return b;
	}

	/**
	 * 控制云台
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Speed 速度 1～10
	 * @Param Action 云台动作：上、下、左、右等
	 * @Param Value 0开始控制 1停止， 其他值暂未定义
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public boolean controlPTZ(int hSession, int nChannel, int nSpeed,
			int nAction, int nValue) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {

			b = nv.controlPTZ(nChannel, nSpeed, nAction, nValue);
		}
		return b;

	}

	/**
	 * 选择预置点
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Index 预置点索引号，从1开始
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */

	public boolean setPrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.setPrePoint(nChannel, nIndex);
		}
		return b;
	};

	public boolean deletePrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.deletePrePoint(nChannel, nIndex);
		}
		return b;

	};

	public boolean selectPrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.selectPrePoint(nChannel, nIndex);
		}
		return b;
	};

	/**
	 * 获取图像参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoEffect 输出参数，视频参数值对象（亮度、对比度、色度、饱和度），参见VideoEffect定义
	 */
	public boolean getVideoEffect(int hSession, int nChannel,
			VideoEffect vEffect) {

		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.getVideoEffect(nChannel, vEffect);
		}
		return b;

	}

	/**
	 * 获取编码参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoCompress 输出参数，编码参数值对象(幀率、码率、图像质量)，参见VideoCompress定义
	 * 
	 * 
	 */

	public boolean getVideoCompress(int hSession, int nChannel,
			DC_VIDEO_COMPRESS vCompress) {
		boolean b = false;
		NVObject nv = hp.get(hSession);
		if (nv != null) {
			b = nv.getVideoCompress_fps(nChannel, vCompress);
			if (b)
				nv.getVideoCompress_BitRate(nChannel, vCompress);
		}
		return b;

	}

	static public void main(String... args) {

		int n = NVDeviceCtrl.getINSTANCE().login("192.168.1.145", 80, null,
				null, null);
		// System.out.println(n);
		// NVDeviceCtrl.getINSTANCE().setOSD(n, 0, true, true, 1, 1,
		// "asdasdasdsad修正");
		// NVDeviceCtrl.getINSTANCE().setVideoEffect(n, 1, 4, 10);
//		NVDeviceCtrl.getINSTANCE().setTime(n, 1983, 3, 13, 1, 1, 1);
		NVDeviceCtrl.getINSTANCE().MakeKeyFrame(n, 1, 2);
	
	}

	public void MakeKeyFrame(int deviceHandle, Integer index, int i) {
		System.out.println(deviceHandle);
		NVObject nv = hp.get(deviceHandle);
		String str = String.format("http://%s/vb.htm?forceiframe1=1",
				nv.getIp());
//		System.out.println(str);
		try {
			URL url = new URL(str);
			URLConnection connection = url.openConnection();
			connection.setReadTimeout(2000);
			String strap = "admin:9999";
			connection
					.setRequestProperty(
							"Authorization",
							"Basic "
									+ (new BASE64Encoder()).encode(strap
											.getBytes("utf-8")));
	
			
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream(), "gbk");
			while (in.hasNext()) {
				String temp = in.next();
				System.out.println(temp);
			}
		
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
