package com.fxdigital.onvif.ctrl;
public class DeviceCtrl  implements IDeviceCallBack{
	private static DeviceCtrl _this;

	private DeviceCtrl() {
		System.load("/usr/lib/libOnvifForJava.so");
		libOnvifInit();
//		registerCallBack(this);
	}

	/**
	 * 单例获得，同时加载C库
	 * 
	 * */
	public static DeviceCtrl Instance() {
		if (_this == null) {
			_this = new DeviceCtrl();
		}
		return _this;
	}

	/**
	 * 注册回调专用接口
	 * 
	 * @param idc
	 *            回调接口，所有设备登录句柄的回调出口
	 */

	public native static void registerCallBack(IDeviceCallBack idc);
	
	/**
	* 判断设置搜索正忙
	* 
	* @Return 1，忙；0，空闲
	**/
	public native int libOnvifDiscoveryIsBusy();
	
	/**
	* 初始化库
	* 
	* @Return 错误码
	**/
	public native static int libOnvifInit();

	/**
	* 反初始化库
	* 
	* @Return 错误码
	**/
	public native static int libOnvifUnInit();
	
	/**
	 * 设置连接超时时间
	 * @param t
	 * @return
	 */
	public native static int setConnectTimeOut(long t);

	

	/**
	 * 设置账号密码
	 * 
	 * @param hSession
	 *            登录句柄
	 * @param user
	 * @param passwd
	 * @return 登录是否成功
	 */
	public native static int setLogin(long hSession, String user, String passwd);

	/**
	 * 指定设备进行登录
	 * 
	 * @param hostIP
	 * @param uri
	 *            如果带描述，则要和hostIP进行拼接合成为真是URI
	 * @param port
	 * @param user
	 * @param passwd
	 * @return
	 */
	public native static long controlCreate(String hostIP, String uri,
			int port, String user, String passwd);

	/**
	 * 释放资源
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Return 无
	 */
	public native static int controlDelete(long hSession);

	/**
	 * 对所有的句柄对象进行清空
	 * 
	 * @return 成功 true 失败 false
	 */
	public native static int controlCleanup();

	/**
	 * 获取设备信息
	 * 
	 * @param hSession
	 *            登录句柄
	 * @param di
	 *            设备的基础描述，包括设备名称 型号 IP uri port 等等
	 * @return
	 */
	public native static int getDeviceInfo(long hSession, DeviceInfo di);

	/**
	 * 获取网络信息
	 * 
	 * @param hSession
	 * @param ni
	 *            包括ip 子网掩码 等等
	 * @return
	 */
	public native static int getNetInfo(long hSession, NetInfo ni);

	/**
	 * 设置IP and 子网掩码
	 * 
	 * @param hSession
	 * @param ip
	 * @return
	 */
	public native static int setNetIP(long hSession, String Ip,String subMask);


	/**
	 * 设置网关
	 * 
	 * @param hSession
	 * @param gate
	 * @return
	 */
	public native static int setNetGate(long hSession, String gate);

	/**
	 * 设置dns
	 * 
	 * @param hSession
	 * @param dns
	 * @return
	 */
	public native static int setNetDNS(long hSession, String dns);

	/**
	 * RTSP 信息获得
	 * 
	 * @param hSession
	 * @param rtsp
	 *            包含 端口 和rtsp URL信息
	 * @return 返回值为通道
	 */
	public native static int getRtspInfo(long hSession, RtspInfo rtsp);

	/**
	 * 设置rtsp流的端口。
	 * 
	 * @param hSession
	 * @param port
	 * @return
	 */
	public native static int setRtspPort(long hSession, int port);

	/**
	 * 获取
	 * 
	 * @param hSession
	 * @param ui
	 *            包括用户名 密码 和等级
	 * @return
	 */
	public native static int getUserSet(long hSession, UserSet ui);

	/**
	 * 创建用户
	 * 
	 * @param hSession
	 * @param userName
	 * @param password
	 * @param level
	 * @return
	 */
	public native static int createUser(long hSession, String userName,
			String password, int level);

	/**
	 * 删除用户
	 * 
	 * @param hSession
	 * @param userName
	 *            用户名
	 * @return
	 */
	public native static int deleteUser(long hSession, String userName);

	/**
	 * 设置用户
	 * 
	 * @param hSession
	 * @param userName
	 *            用户
	 * @param password
	 *            修改的密码
	 * @param level
	 *            用户等级
	 * @return
	 */
	public native static int setUser(long hSession, String userName,
			String password, int level);

	/**
	 * 获取当前时间
	 * 
	 * @param hSession
	 * @param ti
	 *            包含时分秒
	 * @return
	 */
	public native static int getCurrentTime(long hSession, TimeInfo ti);

	/**
	 * 设置设备时间
	 * */
	public native static int SetTime(long hSession, int year, int month,
			int day, int hour, int minute, int second);

	/**
	 * 请求关键帧
	 * 
	 * @param hSession
	 * @param nChannel
	 * @param nCodeStreamType
	 * @return
	 */
	public native static int makeKeyFrame(long hSession, int nChannel,
			int nCodeStreamType);

	/**
	 * 设置报警输出延时
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param nValue 输出保持时间
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static int SetAlarmOutDelay(long hSession, int nChannel,
			int nValue);

	/**
	 * 设置报警输出名称
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param szName 报警输出名称
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static int SetAlarmOutName(long hSession, int nChannel,
			String szName);

	/**
	 * 控制报警布防、撤防
	 * 
	 * @Param notify 监听器对象，要求实现IDevNotify接口
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */
	public native static int ControlAlarm(long hSession, int nChannel,
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
	public native static int ControlPTZ(long hSession, int nChannel,
			int nSpeed, int nAction, int nValue);

	// 是否有云台
	public native static int HasPTZ(long hSession);

	
	/**
	 * 设置串行端口参数
	 * */
	public native static int SetSerialPortParam(long hSession,
			int nChannel, int nAddress, int nBaudRate, int nDataBit,
			int nStopBit, int nParity, int nPTZProtocol);

	/**
	 * 获得串行端口参数
	 * */
	public native static int GetSerialPortParam(long hSession,
			int nChannel, SerialPortParam spp);

	

	/**
	 * 获取所有预置点
	 * */
	static native public int getPrePoint(long hSession,int nChannel,PrePointSet set);
	
	
	/**
	 * 创建预置点
	 * */
	static native public int createPrePoint(long hSession,int nChannel, PrePoint p);
	
	/**
	 * 选择预置点
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Index 预置点索引号，从1开始
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */

	public native static int SetPrePoint(long hSession, int nChannel,
			String handle);

	public native static int DeletePrePoint(long hSession, int nChannel,
			String handle);

	public native static int SelectPrePoint(long hSession, int nChannel,
			String handle);

	/**
	 * OSD相关
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param Index 预置点索引号，从1开始
	 * @Return 成功失败，失败原因通常为没有登录该设备
	 */

	public native static int SetOSD(long hSession, int nChannel,
			boolean bShowTime, boolean bShowOSD, int nPosX, int nPosY,
			String szOSD);

	public native static int SetText(long hSession, int nChannel,
			int nIndex, int nPosX, int nPosY, String szContent);

	// 通道名称
	public native static int SetChannelName(long hSession, int nChannel,
			int nPosX, int nPosY, String szName);

	// 获取通道信息的 X Y 信息。
	public native static int GetChannelName(long hSession, int nChannel,
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
	public native static int SetVideoEffect(long hSession, int nChannel,
			int nType, int nValue);

	/**
	 * 获取图像参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoEffect 输出参数，视频参数值对象（亮度、对比度、色度、饱和度），参见VideoEffect定义
	 */
	public native static int GetVideoEffect(long hSession, int nChannel,
			VideoEffect vEffect);

	/**
	 * 获取编码参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号，从0开始
	 * @Param VideoCompress 输出参数，编码参数值对象(幀率、码率、图像质量)，参见VideoCompress定义
	 */

	public native static int GetVideoCompress(long hSession, int nChannel,
			VideoCompress vCompress);

	public native static int SetVideoCompress(long hSession, int nChannel,
			VideoCompress vCompress);

	public void onSearchDevice(long deviceHandle) {
		System.out.println(deviceHandle);
	}
	
	static public void main(String[] args) {
	
		long n = DeviceCtrl.Instance().controlCreate("192.168.1.165", null, 0, "admin", "12345");
		System.out.println(n);
//		long hSession = DeviceCtrl.Instance().controlCreate("192.168.1.209", null, 8080, "admin", "1111");
//		System.out.println("hSession = " + hSession);
//		if(hSession < 0)
//			return;
//		System.out.println("-----------------------设备信息-----------------------");	
//		DeviceInfo di = new DeviceInfo();
//		DeviceCtrl.Instance().getDeviceInfo(hSession, di);
//		System.out.println("DeviceName: "+di.getDeviceName().toString());
//		System.out.println("Manufacturer: "+di.getDeviceManufacturer().toString());
//		System.out.println("Location: "+di.getDeviceLocation().toString());
//		System.out.println("Model: "+di.getDeviceModel().toString());
//		System.out.println("URI: "+di.getDeviceURI().toString());
//		System.out.println("FirmwareVersion: "+di.getFirmwareVersion().toString());
//		System.out.println("MacAddr: "+di.getMacAddr().toString());	
//		System.out.println("SerialNumber: "+di.getSerialNumber().toString());
//		System.out.println("-----------------------网络信息-----------------------");	
//		NetInfo ni = new NetInfo();
//		DeviceCtrl.Instance().getNetInfo(hSession, ni);
//		System.out.println("IP: "+ni.getIp().toString());
//		System.out.println("SubMask: "+ni.getSubMask().toString());
//		System.out.println("GateWay: "+ni.getGateWay().toString());
//		System.out.println("DNS: "+ni.getDns().toString());
//		
//		
//		UserSet ui = new UserSet();
//		DeviceCtrl.Instance().getUserSet(hSession, ui);
//
//		System.out.println("userset size: "+ui.getUserSet().size());
//		int i = 0;
//		for(UserInfo uio:ui.getUserSet()){
//			System.out.println("UserName["+ i + "]: "+uio.getUserName().toString());
//			System.out.println("UserLevel["+ i + "]: "+uio.getLevel());
//			i++;
//		}
//		
//		RtspInfo rtsp = new RtspInfo();
//		DeviceCtrl.Instance().getRtspInfo(hSession, rtsp);
//		System.out.println("rtsp port: ");
//		System.out.println("rtsp port: "+rtsp.getPort());
//		for(String s:rtsp.getRtspInfo()){
//			System.out.println("rtsp Uri: " + s);
//		}		
//		
//		TimeInfo t = new TimeInfo();
//		DeviceCtrl.Instance().getCurrentTime(hSession, t);
//		System.out.println("year:" + t.getYear() + " month:" + t.getMonth() + " day:" + t.getHour() + " hour:" + t.getHour() + " minute:" + t.getMinute() + " second:" + t.getSecond());
//
//		
//		VideoCompress v = new VideoCompress();
//		v.setM_nBitrate(-1);
//		v.setM_nCodecType(-1);
//		v.setM_nFramerate(25);
//		v.setM_nResolution(-1, -1);
//		v.setM_nQuality(-1);
//		v.m_nEncodingInterval = -1;
//		v.m_nGovLength = -1;
//		DeviceCtrl.Instance().SetVideoCompress(hSession, 0, v);
		
//		DeviceCtrl.Instance().libOnvifUnInit();
//		while(true);
	}


}
