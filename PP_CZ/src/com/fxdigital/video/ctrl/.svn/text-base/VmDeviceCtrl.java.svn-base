package com.fxdigital.video.ctrl;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class VmDeviceCtrl {
	private static VmDeviceCtrl _this;

//	private static final String ClibName = "libcorevideo";

	private VmDeviceCtrl() {
		String lib = ""; 
		if(System.getProperty("os.name").equals("Linux")) {
			lib = "corevideo";
			
		}else {
			lib = "libcorevideo";
		}
		System.loadLibrary(lib);
	}

	/**
	 * 单例获得，同时加载C库
	 * 
	 * */
	synchronized public static VmDeviceCtrl Instance() {
		if (_this == null) {
			_this = new VmDeviceCtrl();
		}
		return _this;
	}

	/**
	 * 楼下开始进行设置静态变量，实际是为了保证回调不消失而已。
	 * */
	private static IViDeoModuleNotify ivdm;

	private long hSession;

	public long gethSession() {
		return hSession;
	}

	private static void setIvdm(IViDeoModuleNotify ivdm) {
		_this.ivdm = ivdm;
	}

	// 初始化，在所有操作之前必须先调用此接口
	public native static boolean ControlStartup(IViDeoModuleNotify fnError,
			Object lpContext);

	public void JControlStartup(IViDeoModuleNotify fnError, Object lpContext) {
		setIvdm(fnError);
		ControlStartup(fnError, lpContext);

		// 进行初始化，读取文件而得
		String path;

		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/DeviceConfig/DeviceConfig.xml";
		else
			path = "D:\\fxconf\\DeviceConfig\\DeviceConfig.xml";
		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String hostip = doc.getRootElement().element("Maxtrix")
		// .attributeValue("IP");
		String szAddress = "0.0.0.0";
		int nPort = 0;
		String szName = null;
		String szPass = null;
		int nDeviceType = 0;
		int nSubDeviceType = 1;
		List<Element> list = doc.getRootElement().element("CommandSystem")
				.element("System").elements("Message");
		for (Element e : list) {
			if (e.attributeValue("id").equals("EncodeType")) {
				nPort = Integer.parseInt(e.attributeValue("port"));
				szName = e.attributeValue("user");
				szPass = e.attributeValue("PW");
				nDeviceType = Integer.parseInt(e.attributeValue("text"));
				break;
			}

		}
//       System.out.println("=="+szAddress+" | "+nSubDeviceType);
		hSession = ControlCreate(szAddress, nPort, szName, szPass, nDeviceType,
				nSubDeviceType, fnError, fnError, lpContext);
		StartListen(hSession, szAddress, nPort);

	}

	// 反初始化，在所有操作之后必须先调用此接口
	public native static boolean ControlCleanup();

	/**
	 * 创建资源
	 * 
	 * @Param DeviceType 设备类型，表示某一厂商的一类设备，如海康编码器
	 * @Param DeviceSubType 设备子类型，表示某一类设备的一个具体类型，通常情况下可以忽略，给0
	 * @Param DeviceIp 设备IP地址
	 * @Param Port 设备端口
	 * @Param Context 上下文，在通知接口中会带入该参数
	 * @Return 登录句柄，< 0 登录失败 注意登录成功并不表示设备在线，在线通过在线通知给出，对某些设备longin始终返回成功。
	 */
	public native static long ControlCreate(String szAddress, int nPort,
			String szName, String szPass, int nDeviceType, int nSubDeviceType,
			IViDeoModuleNotify fnAlarm, IViDeoModuleNotify fnConnect,
			Object lpContext);

	/**
	 * 释放资源
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Return 无
	 */
	public native static boolean ControlDelete(long hSession);

	/**
	 * 开始监听
	 * 
	 * @Param 　LoginHandle 登录句柄
	 * @Return 无
	 */
	public native static boolean StartListen(long hSession, String szAddress,
			int nPort);

	// 请求关键帧
	public native static boolean MakeKeyFrame(long hSession, int nChannel,
			int nCodeStreamType);

	/**
	 * 设置报警输出延时
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param nValue 输出保持时间
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static boolean SetAlarmOutDelay(long hSession, int nChannel,
			int nValue);

	/**
	 * 设置设备时间
	 * */
	public native static boolean SetTime(long hSession,int year,int month,int day,int hour,int minute,int second);
	
	
	/**
	 * 设置报警输出名称
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param szName 报警输出名称
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static boolean SetAlarmOutName(long hSession, int nChannel,
			String szName);

	/**
	 * 控制报警布防、撤防
	 * 
	 * @Param notify 监听器对象，要求实现IDevNotify接口
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static boolean ControlAlarm(long hSession, int nChannel,
			int nType, int nValue);

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
	public native static boolean ControlPTZ(long hSession, int nChannel,
			int nSpeed, int nAction, int nValue);

	// 是否有云台
	public native static boolean HasPTZ(long hSession);

	/**
	 * 设置串行端口参数
	 * */
	public native static boolean SetSerialPortParam(long hSession,
			int nChannel, int nAddress, int nBaudRate, int nDataBit,
			int nStopBit, int nParity, int nPTZProtocol);

	/**
	 * 获得串行端口参数
	 * */
	public native static boolean GetSerialPortParam(long hSession,
			int nChannel, SerialPortParam spp);

	/**
	 * 选择预置点
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Index 预置点索引号，从1开始
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */

	public native static boolean SetPrePoint(long hSession, int nChannel,
			int nIndex);

	public native static boolean DeletePrePoint(long hSession, int nChannel,
			int nIndex);

	public native static boolean SelectPrePoint(long hSession, int nChannel,
			int nIndex);

	/**
	 * OSD相关
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Index 预置点索引号，从1开始
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */

	public native static boolean SetOSD(long hSession, int nChannel,
			boolean bShowTime, boolean bShowOSD, int nPosX, int nPosY,
			String szOSD);

	public native static boolean SetText(long hSession, int nChannel,
			int nIndex, int nPosX, int nPosY, String szContent);

	public native static short GetChannelNumbers(long hSession);

	// 通道名称
	public native static boolean SetChannelName(long hSession, int nChannel,
			int nPosX, int nPosY, String szName);

	// 获取通道信息的 X Y 信息。
	public native static boolean GetChannelName(long hSession, int nChannel,
			PosXYName xy, int nSize);

	/**
	 * 调节图像参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号
	 * @Param Type 类型
	 * @Param
	 * @Return 无
	 */
	public native static boolean SetVideoEffect(long hSession, int nChannel,
			int nType, int nValue);

	/**
	 * 获取图像参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoEffect 输出参数，视频参数值对象（亮度、对比度、色度、饱和度），参见VideoEffect定义
	 */
	public native static boolean GetVideoEffect(long hSession, int nChannel,
			DC_VIDEO_EFFECT vEffect);

	/**
	 * 获取编码参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoCompress 输出参数，编码参数值对象(幀率、码率、图像质量)，参见VideoCompress定义
	 */

	public native static boolean GetVideoCompress(long hSession, int nChannel,
			DC_VIDEO_COMPRESS vCompress);

	public native static boolean SetVideoCompress(long hSession, int nChannel,
			DC_VIDEO_COMPRESS vCompress);

	@Override
	protected void finalize() throws Throwable {
		ControlDelete(this.hSession);
		ControlCleanup();
	}

	static public void main(String[] args) throws InterruptedException {
//		VmDeviceCtrl.Instance().JControlStartup(new Object, "");	
		
//		public native static long ControlCreate(String szAddress, int nPort,
//				String szName, String szPass, int nDeviceType, int nSubDeviceType,
//				IViDeoModuleNotify fnAlarm, IViDeoModuleNotify fnConnect,
//				Object lpContext);
		IViDeoModuleNotify imn = new IViDeoModuleNotify() {
			public void FnErrorMessage(String szName, String szError,
					Object lpContext) {
			}
			public void FnAlarmCallBack(int hSession, int nAlarmType,
					int nValue, Object lpContext) {
			}
			public void FnConnectCallBack(int hSession, boolean bConnected,
					Object lpContext) {
			}
			
		};
		VmDeviceCtrl.Instance().ControlStartup(imn, "123");
		long i = VmDeviceCtrl.Instance().ControlCreate("192.168.1.197", 8000, "admin", "12345", 100, 0, imn, imn, "1234");
		
		boolean b = VmDeviceCtrl.Instance().SetOSD(i, 1, false, false, 30, 30, "123abcsaddasdasd");
//		boolean b = VmDeviceCtrl.Instance().SetTime(i, 2001, 3, 12, 1, 1, 1);
		System.out.println(b);

	}
	
	
	
}
