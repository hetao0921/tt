package com.fxdigital.ipmatrix;

public class IpMatrixCtrl implements IMatrix{

	private static IpMatrixCtrl _this;
	private static final String ClibName = "FxDecMatrix";
	private static final String NewClibName = "/usr/local/center/IpMatrix/so/libFxTvDecMatrixEx.so";

	// private IpMatrixCtrl() {
	// System.loadLibrary(ClibName);
	// MaxtrixStartup();
	// }

	private IpMatrixCtrl(String deviceClass) {

		if (deviceClass.equals("HIKHD6408")) {
//			System.load("/usr/local/center/IpMatrix/so/libliveMedia.so");
//			System.load("/usr/local/center/IpMatrix/so/libBasicUsageEnvironment.so");
//			System.load("/usr/local/center/IpMatrix/so/libgroupsock.so");
//			System.load("/usr/local/center/IpMatrix/so/libUsageEnvironment.so");
			System.load(NewClibName);
		} else {
			System.loadLibrary(ClibName);
		}
		MaxtrixStartup();
	}

	public static IpMatrixCtrl getIpMatrixCtrl(String deviceClass) {
		if (_this == null) {
			_this = new IpMatrixCtrl(deviceClass);
		}
		return _this;
	}

	public static IpMatrixCtrl getIpMatrixCtrl() {
		if(_this==null) {
			_this = new IpMatrixCtrl("test");
		}
		return _this;
	}

	@Override
	protected void finalize() throws Throwable {
		MaxtrixCleanup();
		super.finalize();

	}

	// 初始化设备
	public native boolean MaxtrixStartup();

	// 反初始化设备
	public native boolean MaxtrixCleanup();

	// 创建一个解码实例
	/*
	 * 参数说明： int nDeviceType -- 设备类型 int nSubDeviceType -- 设备子类型 const char *
	 * szAddress 设备地址 (设备登录参数) short nPort -- 设备端口 (设备登录参数) const char * szName
	 * -- 用户名称 (设备登录参数) const char * szPas -- 用户密码 (设备登录参数)
	 */
	public native int MaxtrixCreate(int nDeviceType, int nSubDeviceType,
			String szAddress, int nPort, String szName, String szPass);

	// 删除一个解码实例
	/*
	 * 参数说明: int nInstance -- MaxtrixCreate 的返回值
	 */
	public native boolean MaxtrixDelete(int nInstance);

	// 分屏操作
	/*
	 * 参数说明: int nInstance -- MaxtrixCreate 的返回值 int nShowChannel -- 显示通道号 int
	 * nSplitNumber -- 分屏数量 int nOutputMode -- 输出模式（参见 eDecMaxtrixChannelStatus
	 * 定义）
	 */
	public native boolean MaxtrixSplitScreen(int nInstance, int nShowChannel,
			int nSplitNumber, int nOutputMode);

	// 点播操作
	/*
	 * 参数说明: int nInstance -- MaxtrixCreate 的返回值 int nShowChannel -- 显示通道号 int
	 * nScreenRegion -- 分屏数量 const char * szAddress 设备地址 (设备登录参数) short nPort --
	 * 设备端口 (设备登录参数) const char * szName -- 用户名称 (设备登录参数) const char * szPas --
	 * 用户密码 (设备登录参数) long nChannel, long nLinkType, -- 数据连接类型 long nSubLinkType,
	 * -- 数据连接子类型 long nLinkMode -- 数据连接模式(参见eNetDataTranMode定义)
	 */
	public native int MaxtrixStart(int nInstance, int nShowChannel,
			int nScreenRegion, String szAddress, int nPort, String szName,
			String szPass, int nChannel, int nLinkType, int nSubLinkType,
			int nLinkMode);

	// 停止解码
	/*
	 * 参数说明: int nMaxtrixHandle -- MaxtrixStart 的返回值
	 */
	public native boolean MaxtrixStop(int nMaxtrixHandle);

	// /////////////////////////以下为新增代码，为显示解码信息/////////////////////////////
	// /////////////////////////gjj 2012.10.17 /////////////////////////////

	/**
	 * 获取设备初始化信息，返回结果是否成功，true则为获取HardwareInitInfo成功
	 */
	public native boolean getHardwareInitInfo(HardwareInitInfo hwInfo);

	/**
	 * 
	 * @param nDisplayIndex
	 *            电视索引 ,即显示通道索引 (索引从0开始)
	 * @param nAreaIndex
	 *            显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return 返回当前显示通道所分配的解码器索引(索引从0开始)
	 */
	public native int getDisplayRelatedDecodeIndex(int nDisplayIndex,
			int nAreaIndex);

	/**
	 * 
	 * @param nDecodeIndex
	 *            解码器索引(索引从0开始)
	 * @return -1：代表getIsDecode()接口调用失败； 0：代表当前解码器没有解码； 1：代表当前解码器正在解码；
	 */
	public native int getIsDecode(int nDecodeIndex);

	/**
	 * 
	 * @param nDisplayIndex
	 *            显示通道索引(即，对应电视索引)(索引从0开始)
	 * @param nAreaIndex
	 *            显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return -1：代表getIsTvAreaOut()接口调用失败； 0：代表当前解码通道的当前显示分区，没有显示输出
	 *         1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public native int getIsTvAreaOut(int nDisplayIndex, int nAreaIndex);

	/**
	 * 
	 * @param nDisplayIndex
	 *            显示通道索引(即，对应电视索引)(索引从0开始)
	 * @return -1：代表getIsTvOut()接口调用失败； 0：代表当前解码通道的当前显示分区，没有显示输出
	 *         1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	public native int getIsTvOut(int nDisplayIndex);

	/**
	 * 
	 * @param nDisplayIndex
	 *            解码通道索引(即，对应电视索引)(索引从0开始)
	 * @return -1:代表getTvAreaCount()接口调用失败； 其它数字代表当前显示通道的分屏数
	 */
	public native int getTvSplitCount(int nDisplayIndex);

	/**
	 * 
	 * @param nDisplayIndex
	 *            显示通道索引(索引从0开始)
	 * @param nArea
	 *            显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @param vSrc
	 *            显示通道对应的通道源信息
	 * @return
	 */
	public native boolean getVedioSource(int nDisplayIndex, int nArea,
			VedioSource vSrc);

	// ////////////////////////////根据显示通道，获取它的所有信息//////////////////////////////////
	public native int getDisplayChannelInfo(int nDisIndex,
			DisplayChannelInfo disInfo);// 0成功

	public static void main(String...args) throws InterruptedException {
		if(args.length!=6) {
			System.out.println("args nums must  is 6");
			System.out.println("nShowChannel nScreenRegion nChannel ip port nLinkType");
			System.exit(1);
		}
		int nShowChannel = Integer.parseInt(args[0]);
		int nScreenRegion = Integer.parseInt(args[1]);
		int nChannel = Integer.parseInt(args[2]);
		String ip = args[3];
		int port = Integer.parseInt(args[4]);
		int nLinkType = Integer.parseInt(args[5]);
		
		IpMatrixCtrl ic =	IpMatrixCtrl.getIpMatrixCtrl("HIKHD6405");

		int n = ic.MaxtrixCreate(100, 0, "192.168.1.60", 8000, "admin", "12345");
		System.out.println("  n=  "  + n);
		n = ic.MaxtrixStart(n, nShowChannel, nScreenRegion, ip, port,"admin", "12345", nChannel, nLinkType, 0, 0);
		System.out.println("  play =  "  + n);
		Thread.sleep(100000);
	}
	
	
	
}
