package NVMP.DeviceManage.Implement.devicectrl;

import java.util.HashMap;
import java.util.Map.Entry;

import java.util.Map;

import org.misc.log.LogUtil;

import com.fxdigital.onvif.ctrl.DeviceCtrl;
import com.fxdigital.onvif.ctrl.VideoCompress;
import com.fxdigital.onvif.ctrl.VideoEffect;

@SuppressWarnings("static-access")
public class OnvifDeviceCtrl {
	private Map<Integer, OnvifObject> hp;

	private int handle;

	private OnvifDeviceCtrl() {
		hp = new HashMap<Integer, OnvifObject>();
		handle = 50000;
	}

	static public OnvifDeviceCtrl getINSTANCE() {
		return _OnvifDeviceCtrl.INSTANCE;
	}

	static private class _OnvifDeviceCtrl {
		static private OnvifDeviceCtrl INSTANCE = new OnvifDeviceCtrl();
	}

	// 获取登录句柄
	public int login(String sAddress, int Port, String sUsrName,
			String sUsrPass, Object Context) {

		int n = (int) DeviceCtrl.Instance().controlCreate(sAddress, null, Port,
				sUsrName, sUsrPass);
		if (n < 0) {
			handle++;
			n = handle;
		}

		hp.put(n, new OnvifObject(sAddress, null, Port, sUsrName, sUsrPass, n));

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

		
		OnvifObject o = hp.get(hSession);
		if(o==null) {
			LogUtil.DeviceManageInfo("setTime :"+hSession);
			for(Entry<Integer, OnvifObject> e:hp.entrySet()){
				LogUtil.DeviceManageInfo(e.getKey()+"  "+e.getValue().getsAddress()+" "+e.getValue().getHandle());
			}
		}
		
		if (o!=null && o.isCtrl()) {
			LogUtil.DeviceManageInfo("setTime :"+hSession);
			LogUtil.DeviceManageInfo("Handle :"+o.getHandle());
			LogUtil.DeviceManageInfo("hour :"+hour);
			LogUtil.DeviceManageInfo("minute :"+minute);
			LogUtil.DeviceManageInfo("second :"+second);
			LogUtil.DeviceManageInfo("day :"+day);
			int n = DeviceCtrl.Instance().SetTime(o.getHandle(), year, month,
					day, hour, minute, second);
			if (n > -1)
				b = true;
		}
		return b;
	}
	
	/** 
	 * 调节OSD
	 * */
	public boolean setOSD(int hSession, int nChannel, boolean bShowTime, boolean bShowOSD, 
			int nPosX, int nPosY, String szOSD) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().SetOSD(o.getHandle(), nChannel - 1, bShowTime, 
					bShowOSD, nPosX, nPosY, szOSD);
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 调节图像参数
	 * */

	public boolean setVideoEffect(int hSession, int nChannel, int nType,
			int nValue) {
		boolean b = false;
		// System.out.println("********************");
		// System.out.println(hSession);
		// System.out.println(nChannel);
		// System.out.println(nType);
		// System.out.println(nValue);

		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().SetVideoEffect(o.getHandle(),
					nChannel - 1, nType, nValue);
			System.out.println("n= " + n);
			if (n > -1)
				b = true;
		}
		return b;
	}

	/**
	 * 设置编码参数
	 * */
	public boolean setVideoCompress(int hSession, int nChannel, VideoCompress vc) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().SetVideoCompress(o.getHandle(),
					nChannel - 1, vc);
			b = (n > -1);
		}
		return b;
	}
	
	/** 
	 * 控制报警布防、撤防
	 * */
	public boolean controlAlarm(int hSession, int nChannel, int nType, int nValue) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().ControlAlarm(o.getHandle(), nChannel - 1, nType, nValue);
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 控制云台
	 * */
	public boolean controlPTZ(int hSession, int nChannel, int nSpeed,
			int nAction, int nValue) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().ControlPTZ(o.getHandle(),
					nChannel - 1, nSpeed, nAction, nValue);
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 选择预置点
	 * */
	public boolean setPrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().SetPrePoint(o.getHandle(),
					nChannel - 1, String.valueOf(nIndex));
			b = (n > -1);
		}
		return b;
	}

	public boolean deletePrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().DeletePrePoint(o.getHandle(),
					nChannel - 1, String.valueOf(nIndex));
			b = (n > -1);
		}
		return b;
	}

	public boolean selectPrePoint(int hSession, int nChannel, int nIndex) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().SelectPrePoint(o.getHandle(),
					nChannel - 1, String.valueOf(nIndex));
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 获取图像参数
	 * */
	public boolean getVideoEffect(int hSession, int nChannel,
			VideoEffect vEffect) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().GetVideoEffect(o.getHandle(),
					nChannel - 1, vEffect);
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 获取编码参数
	 * */
	public boolean getVideoCompress(int hSession, int nChannel,
			VideoCompress vCompress) {
		boolean b = false;
		OnvifObject o = hp.get(hSession);
		if (o!=null && o.isCtrl()) {
			int n = DeviceCtrl.Instance().GetVideoCompress(o.getHandle(),
					nChannel - 1, vCompress);
			b = (n > -1);
		}
		return b;
	}

	/**
	 * 激活关键帧
	 * */
	public void MakeKeyFrame(int deviceHandle, Integer index, int i) {
		OnvifObject o = hp.get(deviceHandle);
		if (o!=null && o.isCtrl()) {

			DeviceCtrl.Instance().makeKeyFrame(o.getHandle(), index - 1, i);

		}
	}

}
