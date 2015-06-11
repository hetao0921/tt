package com.fxdigital.video.ctrl;

import java.io.File; 
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class VideoModule {

	private static VideoModule _this;

	// private static final String ClibName = "libcorevideo";

	private VideoModule() {

		String lib = "";
		if (System.getProperty("os.name").equals("Linux")) {
			lib = "FxEncMatrix";

		} else {
			lib = "libFxEncMatrix";
		}
		System.loadLibrary(lib);

		// System.loadLibrary(ClibName);
	}

	private long intance;

	/**
	 * 单例获得，同时加载C库
	 * 
	 * */
	synchronized public static VideoModule Instance() {
		if (_this == null) {
			_this = new VideoModule();
		}
		return _this;
	}

	/**
	 * 楼下开始进行设置静态变量，实际是为了保证回调不消失而已。
	 * */
	private static IViDeoModuleNotify ivdm;

	private static void setIvdm(IViDeoModuleNotify ivdm) {
		VideoModule.ivdm = ivdm;
	}

	// 初始化，在所有操作之前必须先调用此接口
	public native static boolean VideoStartup(IViDeoModuleNotify fnError,
			Object lpContext);

	/**
	 * 在初始化的同时，保证回调不消失。
	 * */
	public void JVideoStartup(IViDeoModuleNotify fnError, Object lpContext) {
		setIvdm(fnError);
		VideoStartup(fnError, lpContext);

		// 启动服务。

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
		int nLinkType = 100; // 连接类型
		int nSubLinkType = 1; // 子连接类型
		int nLinkMode = 0; // 网络连接类型
		String szAddress = "0.0.0.0"; // 设备IP地址
		int nPort = 8000; // 设备端口号
		String szUsrName = null; // 用户登录名称
		String szUsrPass = null; // 用户登录密码
		String realIP = null;
		List<Element> list = doc.getRootElement().element("CommandSystem")
				.element("System").elements("Message");
		for (Element e : list) {
			if (e.attributeValue("id").equals("EncodeType")) {
				nPort = Integer.parseInt(e.attributeValue("port"));
				szUsrName = e.attributeValue("user");
				szUsrPass = e.attributeValue("PW");
				nLinkType = Integer.parseInt(e.attributeValue("text"));
			}
			if (e.attributeValue("id").equals("ip")) {
				realIP = e.attributeValue("text");
			}

		}
		// <Message id="ip" text="192.168.1.100"/>

		System.out.println("==" + szAddress + " | " + nLinkType + " | "
				+ nSubLinkType + " | " + nLinkMode + "IP:" + realIP);
		ServerSetAddress(realIP);
		// 判断有多少个通道需要进行操作。
		int n = VmDeviceCtrl.Instance().GetChannelNumbers(
				VmDeviceCtrl.Instance().gethSession());

		System.out.println(n);
		for (int i = 1; i <= n; i++) {
			if (i != 1) {

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			intance = this.ServerStart(nLinkType, nSubLinkType, nLinkMode,
					szAddress, nPort, szUsrName, szUsrPass, i, i);

		}
	}

	// 反初始化，在所有操作之后必须先调用此接口
	public native static boolean VideoCleanup();

	// (单通道服务)功能：设置服务地址
	public native static boolean ServerSetAddress(String szAddress);

	// (单通道服务)功能：启动服务
	public native static long ServerStart(int nLinkType, // 连接类型
			int nSubLinkType, // 子连接类型
			int nLinkMode, // 网络连接类型
			String szAddress, // 设备IP地址
			int nPort, // 设备端口号
			String szUsrName, // 用户登录名称
			String szUsrPass, // 用户登录密码
			int nDeviceChannelId, // 设备通道序号
			int nServerChannelId // 服务通道序号
	);

	// (单通道服务)功能：停止服务
	public native static boolean ServerStop(long nInstance);

	@Override
	protected void finalize() throws Throwable {
		ServerStop(intance);
		VideoCleanup();
	}

}
