package NVMP.DeviceManage.Implement.devicectrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fxdigital.EcDevice.ctrl.VideoEffect;
import com.fxdigital.video.ctrl.DC_VIDEO_COMPRESS;
import com.fxdigital.video.ctrl.DC_VIDEO_EFFECT;

public class NVObject {
	private String ip;
	private int handle;
	private Object context;

	public String getIp() {
		return ip;
	}

	public int getHandle() {
		return handle;
	}

	public Object getContext() {
		return context;
	}

	public NVObject(String ip, int handle, Object Context) {
		this.ip = ip;
		this.handle = handle;
		this.context = Context;
	}

	public boolean setTime(int year, int month, int day, int hour, int minute,
			int second) {
		boolean b = false;

		ICtrl iCtrl = new TimeCtrl(ip, year, month, day, hour, minute, second);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean setVideoEffect(int nChannel, int nType, int nValue) {

		boolean b = false;

		ICtrl iCtrl = new VideoEffectCtrl(ip, nChannel, nType, nValue);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean setVideoCompress(int nChannel, int nType, int nValue) {
		// TODO Auto-generated method stub
		boolean b = false;

		ICtrl iCtrl = new VideoEffectCtrl(ip, nChannel, nType, nValue);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean setOSD(int channel, boolean showTime, boolean showOsd,
			int x, int y, String oSD) {
		boolean b = false;

		ICtrl iCtrl = new OSDCtrl(ip, channel, showTime, showOsd, x, y, oSD);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean controlPTZ(int nChannel, int nSpeed, int nAction, int nValue) {
		boolean b = false;

		ICtrl iCtrl = new PTZCtrl(ip, nChannel, nSpeed, nAction, nValue);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean selectPrePoint(int nChannel, int nIndex) {
		// TODO Auto-generated method stub
		boolean b = false;

		ICtrl iCtrl = new PrePointCtrl(ip, nChannel, nIndex, "select");
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean deletePrePoint(int nChannel, int nIndex) {
		boolean b = false;

		ICtrl iCtrl = new PrePointCtrl(ip, nChannel, nIndex, "delete");
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	public boolean setPrePoint(int nChannel, int nIndex) {
		boolean b = false;
		ICtrl iCtrl = new PrePointCtrl(ip, nChannel, nIndex, "set");
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty())
				b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public boolean getVideoEffect(int nChannel, VideoEffect vEffect) {
		// TODO Auto-generated method stub
		boolean b = false;
		ICtrl iCtrl = new VideoEffectCtrl(ip, nChannel);
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty()) {
				b = true;
				vEffect.setBrightness(Integer.parseInt(result.get("bright"))/10);
				vEffect.setContrast(Integer.parseInt(result.get("contrast"))/10);
				vEffect.setHue(Integer.parseInt(result.get("hue"))/10);
				vEffect.setSaturation(Integer.parseInt(result.get("saturation"))/10);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}


	public boolean getVideoCompress_fps(int nChannel,
			DC_VIDEO_COMPRESS vCompress) {
		boolean b = false;
		ICtrl iCtrl = new VideoCompressCtrl(ip, nChannel,"fps");
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty()) {
				b = true;
				vCompress.setM_nFramerate(Integer.parseInt(result.get("fps")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public boolean getVideoCompress_BitRate(int nChannel,
			DC_VIDEO_COMPRESS vCompress) {
		boolean b = false;
		ICtrl iCtrl = new VideoCompressCtrl(ip, nChannel,"BitRate");
		try {
			Map<String, String> result = URLCtrl.getInstance().getCtrlResult(
					iCtrl);
			if (!result.isEmpty()) {
				b = true;
				vCompress.setM_nFramerate(Integer.parseInt(result.get("single")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	
}

class TimeCtrl implements ICtrl {

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public TimeCtrl(String ip, int year, int month, int day, int hour,
			int minute, int second) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();
		cmd.append("http://");
		cmd.append(ip);
		cmd.append("/setclock");
		hp.put("Time", hour + ":" + minute + ":" + second);
		hp.put("Date", year + "/" + month + "/" + day + "/");
	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}
}

class VideoEffectCtrl implements ICtrl {
	public static final int BRIGHTNESS = 1; // 亮度
	public static final int SATURATION = 2; // 饱和度
	public static final int HUE = 3; // 色度
	public static final int CONTRAST = 4; // 对比度

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public VideoEffectCtrl(String ip, int nChannel, int nType, int nValue) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();
		cmd.append("http://");
		cmd.append(ip);
		cmd.append("/camctrl");

		hp.put("cmd", "set");
		hp.put("camid", String.valueOf(nChannel));

		switch (nType) {
		case BRIGHTNESS:
			hp.put("bright", String.valueOf(nValue));
			break;
		case SATURATION:
			hp.put("saturation", String.valueOf(nValue));
			break;
		case HUE:
			hp.put("hue", String.valueOf(nValue));
			break;
		default:
			hp.put("contrast", String.valueOf(nValue));
			break;
		}

	}

	public VideoEffectCtrl(String ip, int nChannel) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();
		cmd.append("http://");
		cmd.append(ip);
		cmd.append("/camctrl");
		hp.put("cmd", "get");
		hp.put("camid", String.valueOf(nChannel));
	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}

}

class VideoCompressCtrl implements ICtrl {
	public static final int FRAMERATE = 5; // 帧频率
	public static final int CONST_BITRATE = 6; // 码流

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public VideoCompressCtrl(String ip, int nChannel, int nType, int nValue) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();
		cmd.append("http://");
		cmd.append(ip);

		hp.put("cmd", "set");

		switch (nType) {
		case FRAMERATE:
			cmd.append("/camctrl");
			hp.put("cmd", "set");
			hp.put("camid", String.valueOf(nChannel));
			hp.put("fps", String.valueOf(nValue));
			break;

		default:
			cmd.append("/imgbitrate");
			hp.put("cmd", "set");
			hp.put("VideoID", String.valueOf(nChannel));
			hp.put("BitRate", String.valueOf(nValue));

			break;
		}

	}

	public VideoCompressCtrl(String ip, int nChannel,String type) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();
		cmd.append("http://");
		cmd.append(ip);
		hp.put("cmd", "get");
		
		if(type.equals("fps")) {
			cmd.append("/camctrl");
			hp.put("camid", String.valueOf(nChannel));
		} else if (type.equals("BitRate")) {
			cmd.append("/imgbitrate");
			hp.put("VideoID", String.valueOf(nChannel));
		}
		
	
		
	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}
}

class OSDCtrl implements ICtrl {

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public OSDCtrl(String ip, int channel, boolean showTime, boolean showOsd,
			int x, int y, String oSD) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();

		cmd.append("http://");
		cmd.append(ip);
		cmd.append("/setvideo");

		hp.put("save", "1");
		hp.put("VideoID", String.valueOf(channel));
		hp.put("BitmapX", String.valueOf(x));
		hp.put("BitmapY", String.valueOf(y));

		hp.put("BitmapTime", showTime ? "1" : "0");
		hp.put("BitmapTextEnable", showOsd ? "1" : "0");
		try {
			hp.put("BitmapText", URLEncoder.encode(oSD, "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}

}

class PTZCtrl implements ICtrl {
	final static int Up = 1;
	final static int Down = 2;
	final static int Left = 3;
	final static int Right = 4;

	final static int LeftUp = 5;
	final static int RightUp = 6;
	final static int LeftDown = 7;
	final static int RightDown = 8;

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public PTZCtrl(String ip, int nChannel, int nSpeed, int nAction, int nValue) {

		cmd = new StringBuffer();
		hp = new HashMap<String, String>();

		cmd.append("http://");
		cmd.append(ip);
		cmd.append("/ptz");

		hp.put("camid", String.valueOf(nChannel));
		switch (nAction) {
		case Up:
			hp.put("cmd", "V01_TU_03");
			break;
		case Down:
			hp.put("cmd", "V01_TD_03");
			break;
		case Left:
			hp.put("cmd", "V01_PL_03");
			break;
		case Right:
			hp.put("cmd", "V01_PR_03");
			break;
		case LeftUp:
			hp.put("cmd", "V01_TUPL_03");
			break;
		case RightUp:
			hp.put("cmd", "V01_TUPR_03");
			break;
		case LeftDown:
			hp.put("cmd", "V01_TDPL_03");
			break;
		case RightDown:
			hp.put("cmd", "V01_TDPR_03");
			break;
		}

	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}

}

class PrePointCtrl implements ICtrl {
	static final int V01_GOPRESET_14 = 0;
	static final int V01_GOPRESET_1 = 1;
	static final int V01_GOPRESET_2 = 2;
	static final int V01_GOPRESET_3 = 3;
	static final int V01_GOPRESET_4 = 4;
	static final int V01_GOPRESET_5 = 5;
	static final int V01_GOPRESET_6 = 6;
	static final int V01_GOPRESET_7 = 7;
	static final int V01_GOPRESET_8 = 8;
	static final int V01_GOPRESET_9 = 9;
	static final int V01_GOPRESET_0A = 10;
	static final int V01_GOPRESET_0B = 11;
	static final int V01_GOPRESET_0C = 12;
	static final int V01_GOPRESET_0D = 13;
	static final int V01_GOPRESET_0E = 14;
	static final int V01_GOPRESET_0F = 15;
	static final int V01_GOPRESET_10 = 16;
	static final int V01_GOPRESET_11 = 17;
	static final int V01_GOPRESET_12 = 18;
	static final int V01_GOPRESET_13 = 19;

	private StringBuffer cmd;
	private HashMap<String, String> hp;

	public PrePointCtrl(String ip, int nChannel, int nIndex, String type) {
		cmd = new StringBuffer();
		hp = new HashMap<String, String>();

		cmd.append("http://");
		cmd.append(ip);
		if (type.equals("select")) {
			cmd.append("/ptz");
			hp.put("camid", String.valueOf(nChannel));
			switch (nIndex) {
			case V01_GOPRESET_14:
				hp.put("cmd", "V01_GOPRESET_14");
				break;
			case V01_GOPRESET_1:
				hp.put("cmd", "V01_GOPRESET_1");
				break;
			case V01_GOPRESET_2:
				hp.put("cmd", "V01_GOPRESET_2");
				break;
			case V01_GOPRESET_3:
				hp.put("cmd", "V01_GOPRESET_3");
				break;
			case V01_GOPRESET_4:
				hp.put("cmd", "V01_GOPRESET_4");
				break;
			case V01_GOPRESET_5:
				hp.put("cmd", "V01_GOPRESET_5");
				break;
			case V01_GOPRESET_6:
				hp.put("cmd", "V01_GOPRESET_6");
				break;
			case V01_GOPRESET_7:
				hp.put("cmd", "V01_GOPRESET_7");
				break;
			case V01_GOPRESET_8:
				hp.put("cmd", "V01_GOPRESET_8");
				break;
			case V01_GOPRESET_9:
				hp.put("cmd", "V01_GOPRESET_9");
				break;
			case V01_GOPRESET_0A:
				hp.put("cmd", "V01_GOPRESET_0A");
				break;
			case V01_GOPRESET_0B:
				hp.put("cmd", "V01_GOPRESET_0B");
				break;
			case V01_GOPRESET_0C:
				hp.put("cmd", "V01_GOPRESET_0C");
				break;
			case V01_GOPRESET_0D:
				hp.put("cmd", "V01_GOPRESET_0D");
				break;
			case V01_GOPRESET_0E:
				hp.put("cmd", "V01_GOPRESET_0E");
				break;
			case V01_GOPRESET_0F:
				hp.put("cmd", "V01_GOPRESET_0F");
				break;
			case V01_GOPRESET_10:
				hp.put("cmd", "V01_GOPRESET_10");
				break;
			case V01_GOPRESET_11:
				hp.put("cmd", "V01_GOPRESET_11");
				break;
			case V01_GOPRESET_12:
				hp.put("cmd", "V01_GOPRESET_12");
				break;
			case V01_GOPRESET_13:
				hp.put("cmd", "V01_GOPRESET_13");
				break;

			}

		} else if (type.equals("delete")) {

			cmd.append("/ptzpreset");
			hp.put("camid", String.valueOf(nChannel));
			hp.put("rem_preset", String.valueOf(nIndex));

		} else if (type.equals("set")) {
			cmd.append("/ptzpreset");
			hp.put("camid", String.valueOf(nChannel));
			hp.put("set_preset", String.valueOf(nIndex));
			hp.put("set_presetname", String.valueOf(nIndex));

		}

	}

	@Override
	public String getCMD() {
		// TODO Auto-generated method stub
		return cmd.toString();
	}

	@Override
	public Map<String, String> getMap() {
		// TODO Auto-generated method stub
		return hp;
	}

}
