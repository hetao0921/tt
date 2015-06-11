package NVMP.VideoServerDomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.misc.log.LogUtil;

import NVMP.AppService.Remoting;

import com.fxdigital.ipmatrix.DisplayChannelInfo;
import com.fxdigital.ipmatrix.HardwareInitInfo;
import com.fxdigital.ipmatrix.IpMatrixCtrl;
import com.fxdigital.ipmatrix.SplitArea;
import com.fxdigital.ipmatrix.VedioSource;
import com.fxdigital.video.ctrl.DC_VIDEO_COMPRESS;
import com.fxdigital.video.ctrl.DC_VIDEO_EFFECT;
import com.fxdigital.video.ctrl.IViDeoModuleNotify;
import com.fxdigital.video.ctrl.PosXYName;
import com.fxdigital.video.ctrl.SerialPortParam;
import com.fxdigital.video.ctrl.VideoModule;
import com.fxdigital.video.ctrl.VmDeviceCtrl;

/**
 * 专门用来控制服务器开启和结束
 * */
@SuppressWarnings("static-access")
public class VideoServerCtrl implements IViDeoModuleNotify {

	public IVideoServerCtrl isc;

	private VideoModule vm;
	private VmDeviceCtrl vdc;
	
	
	private String valueStr = null;

	/**
	 * 初始化的时候，同时将本地方法调用初始化 
	 * */
	public VideoServerCtrl() {

		vdc = VmDeviceCtrl.Instance();
		vdc.JControlStartup(this, "");

		// 读取文件，判断是否进行服务初始化。
		BufferedReader f = null;
		try {
			if (System.getProperty("os.name").equals("Linux"))
			{
				f =  new BufferedReader(new FileReader(new File(
						"/etc/fxconf/WT_CONFIG/8060.ini")));
			}
			else
			{
				 f = new BufferedReader(new FileReader(new File(
					"D:\\WT_CONFIG\\8060.ini")));
			}
			
			
			while (true) {
				String str = null;
				try {
					str = f.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				if (str == null)
					break;
				System.out.println(str);
				if (str.contains("DS4004HCCardCount")) {
					valueStr = str.substring(str.indexOf("=") + 1);

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(f!=null)
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		
		if (valueStr!=null && !valueStr.equals("0")) {
			vm = VideoModule.Instance();
			vm.JVideoStartup(this, "");
		}

	}

	/**
	 * 错误的时候，异常抛出
	 * */
	@Override
	public void FnErrorMessage(String szName, String szError, Object lpContext) {

	}

	@Override
	public void FnAlarmCallBack(int hSession, int nAlarmType, int nValue,
			Object lpContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void FnConnectCallBack(int hSession, boolean bConnected,
			Object lpContext) {
		// TODO Auto-generated method stub

	}

	// (单通道服务)功能：设置服务地址
	@Remoting
	public void ServerSetAddress(String szAddress) {
		vm.ServerSetAddress(szAddress);
	}

	// (单通道服务)功能：启动服务。 成功后，返回对应的n值。
	@Remoting
	public void ServerStart(String client, int nLinkType, // 连接类型
			int nSubLinkType, // 子连接类型
			int nLinkMode, // 网络连接类型
			String szAddress, // 设备IP地址
			int nPort, // 设备端口号
			String szUsrName, // 用户登录名称
			String szUsrPass, // 用户登录密码
			int nDeviceChannelId, // 设备通道序号
			int nServerChannelId, // 服务通道序号
			String context // 上下文环境
	) {
		Long n = vm
				.ServerStart(nLinkType, nSubLinkType, nLinkMode, szAddress,
						nPort, szUsrName, szUsrPass, nDeviceChannelId,
						nServerChannelId);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);
		isc.onServerStart(n.toString(), context);

	}

	// (单通道服务)功能：停止服务
	@Remoting
	public void ServerStop(String client, String nInstance) {
		try {
			long l = Long.parseLong(nInstance);
			vm.ServerStop(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////
	// 下面是用来进行控制的。

	// 通道数量
	@Remoting
	public void GetChannelNumbers(String client, String context) {
		int nums ;
		
		LogUtil.info("valueStr:"+valueStr);
		
		if(valueStr==null || valueStr.equals("0")) {
			 nums = 0;
		} else {
			LogUtil.info("开始调用C++的接口GetChannelNumbers()");
			nums = vdc.GetChannelNumbers(vdc.gethSession());
			LogUtil.info("C++的接口GetChannelNumbers()调用完毕，得到的结果是:"+nums);
		}

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);
		isc.onGetChannelNumbers(nums, context);
	}

	// 设置通道信息
	@Remoting
	public void SetChannelName(String client, Integer nChannel, Integer nPosX,
			Integer nPosY, String szName) {
		vdc.SetChannelName(vdc.gethSession(), nChannel, nPosX, nPosY, szName);
	}

	// 获取通道信息的 X Y 信息。
	@Remoting
	public void GetChannelName(String client, Integer nChannel, String context) {

		PosXYName xyn = new PosXYName();
		if (vdc.GetChannelName(vdc.gethSession(), nChannel, xyn, 100)) {
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			isc.onGetChannelName(xyn.getPosX(), xyn.getnPosY(),
					xyn.getSzName(), nChannel, context);
		}

	}

	// 请求关键帧
	@Remoting
	public void MakeKeyFrame(String client, Integer nChannel,
			Integer nCodeStreamType) {
		vdc.MakeKeyFrame(vdc.gethSession(), nChannel, nCodeStreamType);

	}

	// 控制云台
	@Remoting
	public void ControlPTZ(String client, Integer nChannel, Integer nSpeed,
			Integer nAction, Integer nValue) {
		vdc.ControlPTZ(vdc.gethSession(), nChannel, nSpeed, nAction, nValue);
	}

	// 设置串行端口参数
	@Remoting
	public void SetSerialPortParam(String client, Integer nChannel,
			Integer nAddress, Integer nBaudRate, Integer nDataBit,
			Integer nStopBit, Integer nParity, Integer nPTZProtocol) {
		vdc.SetSerialPortParam(vdc.gethSession(), nChannel, nAddress,
				nBaudRate, nDataBit, nStopBit, nParity, nPTZProtocol);
	}

	// 获得串行端口参数
	@Remoting
	public void GetSerialPortParam(String client, Integer nChannel,
			String context) {
		SerialPortParam spp = new SerialPortParam();
		if (vdc.GetSerialPortParam(vdc.gethSession(), nChannel, spp)) {
			System.out.println("===== spp.getnAddress() " + spp.getnAddress());
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			isc.onGetSerialPortParam(spp.getnAddress(), spp.getnBaudRate(),
					spp.getnDataBit(), spp.getnParity(), spp.getnPTZProtocol(),
					spp.getnStopBit(), nChannel, context);
		} else {
			System.out.println("=== 结果是false ====");

		}

	}

	// 设置osd的详细信息
	@Remoting
	public void SetOSD(String client, Integer nChannel, Boolean bShowTime,
			Boolean bShowOSD, Integer nPosX, Integer nPosY, String szOSD) {
		// System.out.println("===_==="+szOSD);
		vdc.SetOSD(vdc.gethSession(), nChannel, bShowTime, bShowOSD, nPosX,
				nPosY, szOSD);
	}

	// 获得OSD的详细信息
	@Remoting
	public void GetOSD(String client, Integer nChannel, String context) {
		String path = System.getProperty("user.dir")
				+ "\\Device\\HIK\\HikEncControl.ini";
		System.out.println(path);
		BufferedReader fr = null;
		int PosX = 1024;
		int PosY = 2048;
		boolean ShowOSD = false;
		boolean ShowTime = false;
		String OSDContent = "123";

		try {
			// fr = new BufferedReader(new FileReader(path));
			File file = new File(path);
//			String line = "";
			if (file.exists()) {
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(file), "gb2312");
				fr = new BufferedReader(isr);

			}
			String temp = null;
			String m = "Enc" + (nChannel - 1);
			
			while (fr!=null && (temp = fr.readLine()) != null) {

				if (!temp.contains(m)) {
					continue;
				} else {
					String str = temp.substring(m.length());
					String[] strArry = str.split("=");
					if (strArry.length == 2) {
						if (strArry[0].equals("PosX")) {
							PosX = Integer.parseInt(strArry[1]);
						} else if (strArry[0].equals("PosY")) {
							PosY = Integer.parseInt(strArry[1]);
						} else if (strArry[0].equals("ShowOSD")) {
							ShowOSD = strArry[1].equals("0") ? false : true;
						} else if (strArry[0].equals("ShowTime")) {
							ShowTime = strArry[1].equals("0") ? false : true;
						} else if (strArry[0].equals("OSDContent")) {
							OSDContent = strArry[1];
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);
		isc.onGetOSD(PosX, PosY, ShowOSD, ShowTime, OSDContent, nChannel,
				context);
		// int framerate = 0; // 帧率
		// int bitrate = 0; // 码率
		// int quality = 0; // 图片质量
		// int resolution = 0; // 分辨率
		// int codecType = 0; // 编码类型
		// boolean constBitrate = false; // 是否为定码率或变码率，此属性不需要在界面上呈现
		// boolean stdCodec = false; // 是否为标准编码、或厂商私有

	}

	// 设置OSD的文本信息

	@Remoting
	public void SetText(String client, Integer nChannel, Integer nIndex,
			Integer nPosX, Integer nPosY, String szContent) {

		vdc.SetText(vdc.gethSession(), nChannel, nIndex, nPosX, nPosY,
				szContent);
	}

	/**
	 * 调节图像参数
	 * 
	 * @Param LoginHandle 登录句柄
	 * @Param Channel 通道号	
	 * @Param Type 类型
	 * @Param
	 * @Return 无
	 */
	@Remoting
	public void SetVideoEffect(String client, Integer nChannel, Integer nType,
			Integer nValue) {
		vdc.SetVideoEffect(vdc.gethSession(), nChannel, nType, nValue);
	}

	/**
	 * 获得图像参数
	 * */
	@Remoting
	public void GetVideoEffect(String client, Integer nChannel, String context) {
		DC_VIDEO_EFFECT dve = new DC_VIDEO_EFFECT();
		if (vdc.GetVideoEffect(vdc.gethSession(), nChannel, dve)) {
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			isc.onGetVideoEffect(dve.getM_nBirghtness(), dve.getM_nContrast(),
					dve.getM_nHue(), dve.getM_nSaturation(), nChannel, context);
		}
	}

	/**
	 * 编码参数
	 */
	@Remoting
	public void SetVideoCompress(String client, Integer nChannel,
			Integer m_nFramerate, Integer m_nBitrate, Integer m_nQuality,
			Integer m_nResolution, Integer m_nCodecType,
			Boolean m_bConstBitrate, Boolean m_bStdCodec) {
		DC_VIDEO_COMPRESS vCompress = new DC_VIDEO_COMPRESS();

		vCompress.setM_bConstBitrate(m_bConstBitrate);
		vCompress.setM_bStdCodec(m_bStdCodec);
		vCompress.setM_nBitrate(m_nBitrate);
		vCompress.setM_nCodecType(m_nCodecType);
		vCompress.setM_nFramerate(m_nFramerate);
		vCompress.setM_nQuality(m_nQuality);
		vCompress.setM_nResolution(m_nResolution);
		vdc.SetVideoCompress(vdc.gethSession(), nChannel, vCompress);

	}

	/**
	 * 获得编码参数的属性
	 * */
	@Remoting
	public void GetVideoCompress(String client, Integer nChannel, String context) {

		DC_VIDEO_COMPRESS vCompress = new DC_VIDEO_COMPRESS();

		if (vdc.GetVideoCompress(vdc.gethSession(), nChannel, vCompress)) {
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			isc.onGetVideoCompress(vCompress.getM_nBitrate(),
					vCompress.getM_nCodecType(), vCompress.getM_nFramerate(),
					vCompress.getM_nQuality(), vCompress.getM_nResolution(),
					nChannel, context);

		}
	}

	
	
	
	
	
	
	///////////////////////////////////////获取解码卡信息//////////////////////////////
	/////////////////////////////以下代码是高江江在2012.10.19添加///////////////////////////
	
	/**
	 * 获取设备初始化信息，返回结果是否成功，true则为获取HardwareInitInfo成功
	 */
	@Remoting
	public void GetHardwareInitInfo(String client,String context) {
		HardwareInitInfo hwInfo = new HardwareInitInfo();
		IpMatrixCtrl imc = IpMatrixCtrl.getIpMatrixCtrl();
		LogUtil.info("高江江高江江：GetHardwareInitInfo()开始被调用。。。");
		if (imc.getHardwareInitInfo(hwInfo)) {

			LogUtil.info("高江江高江江：GetHardwareInitInfo()获取设备信息成功，设备信息是：\n succeedDecodeChannelCount："
					+ hwInfo.get_succeedDecodeChannelCount()
					+ "\t succeedInitDspCount:"
					+ hwInfo.get_succeedInitDspCount()
					+ "\t succeedDisplayChannelCount:"
					+ hwInfo.get_succeedDisplayChannelCount()
					+ "\t decodeCardCount:" + hwInfo.get_decodeCardCount());

			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);

			isc.onGetHardwareInitInfo(hwInfo.get_succeedDecodeChannelCount(),
					hwInfo.get_succeedInitDspCount(),
					hwInfo.get_succeedDisplayChannelCount(),
					hwInfo.get_decodeCardCount(),context);

			LogUtil.info("高江江高江江：告知设备初始化信息完毕！！");
		}

	}

	/**
	 * 
	 * @param nDisplayIndex
	 *            电视索引 ,即显示通道索引 (索引从0开始)
	 * @param nAreaIndex
	 *            显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return 返回当前显示通道所分配的解码器索引(索引从0开始)
	 */
	@Remoting
	public void GetDisplayRelatedDecodeIndex(String client, int nDisplayIndex,
			int nAreaIndex,String context) {
		IpMatrixCtrl imc = IpMatrixCtrl.getIpMatrixCtrl();

		LogUtil.info("高江江高江江：GetDisplayRelatedDecodeIndex()开始被调用。。。");

		int index = imc.getDisplayRelatedDecodeIndex(nDisplayIndex, nAreaIndex);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);

		isc.onGetDisplayRelatedDecodeIndex(index,context);

		LogUtil.info("高江江高江江：GetDisplayRelatedDecodeIndex(nDisplayIndex:"
				+ nDisplayIndex + ",nAreaIndex:" + nAreaIndex + "):" + index);
	}

	/**
	 * 
	 * @param nDecodeIndex
	 *            解码器索引(索引从0开始)
	 * @return -1：代表getIsDecode()接口调用失败； 0：代表当前解码器没有解码； 1：代表当前解码器正在解码；
	 */
	@Remoting
	public void GetIsDecode(String client, int nDecodeIndex,String context) {
		LogUtil.info("高江江高江江：GetIsDecode()开始被调用。。。");
		int isDecode = IpMatrixCtrl.getIpMatrixCtrl().getIsDecode(nDecodeIndex);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);

		isc.onGetIsDecode(isDecode,context);

		LogUtil.info("高江江高江江：GetIsDecode(nDecodeIndex:" + nDecodeIndex + "):"
				+ isDecode);
	}

	/**
	 * 
	 * @param nDisplayIndex
	 *            显示通道索引(即，对应电视索引)(索引从0开始)
	 * @param nAreaIndex
	 *            显示通道中的分屏索引(即，对应当前电视的分屏索引，如9分屏)(索引从0开始)
	 * @return -1：代表getIsTvAreaOut()接口调用失败；
	 *          0：代表当前解码通道的当前显示分区，没有显示输出
	 *          1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	@Remoting
	public void GetIsTvAreaOut(String client, int nDisplayIndex, int nAreaIndex,String context) {
		LogUtil.info("高江江高江江：GetIsTvAreaOut()开始被调用。。。");

		int IsTvAreaOut = IpMatrixCtrl.getIpMatrixCtrl().getIsTvAreaOut(
				nDisplayIndex, nAreaIndex);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);

		isc.onGetIsTvAreaOut(IsTvAreaOut,context);

		LogUtil.info("高江江高江江：GetIsTvAreaOut(nDisplayIndex:" + nDisplayIndex
				+ ",nAreaIndex:" + nAreaIndex + "):" + IsTvAreaOut);
	}

	/**
	 * 
	 * @param nDisplayIndex
	 *            显示通道索引(即，对应电视索引)(索引从0开始)
	 * @return -1：代表getIsTvOut()接口调用失败； 0：代表当前解码通道的当前显示分区，没有显示输出
	 *         1：代表当前解码通道的当前显示分区，正在显示输出
	 */
	@Remoting
	public void GetIsTvOut(String client, int nDisplayIndex,String context) {
		LogUtil.info("高江江高江江：GetIsTvOut()开始被调用。。。");

		int IsTvOut = IpMatrixCtrl.getIpMatrixCtrl().getIsTvOut(nDisplayIndex);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);

		isc.onGetIsTvOut(IsTvOut,context);
		LogUtil.info("高江江高江江：GetIsTvAreaOut(nDisplayIndex:" + nDisplayIndex
				+ "):" + IsTvOut);
	}

	/**
	 * 
	 * @param nDisplayIndex
	 *            解码通道索引(即，对应电视索引)(索引从0开始)
	 * @return -1:代表getTvAreaCount()接口调用失败； 其它数字代表当前显示通道的分屏数
	 */
	@Remoting
	public void GetTvSplitCount(String client, int nDisplayIndex,String context) {
		LogUtil.info("高江江高江江：GetTvSplitCount()开始被调用。。。");

		int TvSplitCount = IpMatrixCtrl.getIpMatrixCtrl().getTvSplitCount(
				nDisplayIndex);

		VideoServerDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + client);

		isc.onGetTvSplitCount(TvSplitCount,context);

		LogUtil.info("高江江高江江：GetTvSplitCount(nDisplayIndex:" + nDisplayIndex
				+ "):" + TvSplitCount);
	}

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
	@Remoting
	public void GetVedioSource(String client, int nDisplayIndex, int nArea,String context) {
		
		VedioSource vSrc = new VedioSource();
		
//		IpMatrixCtrl.getIpMatrixCtrl().getVedioSource(nDisplayIndex, nArea,
//				vSrc);
		LogUtil.info("高江江高江江：GetVedioSource()开始被调用。。。");
		IpMatrixCtrl imc = IpMatrixCtrl.getIpMatrixCtrl();
		if (imc.getVedioSource(nDisplayIndex, nArea, vSrc)) {

			LogUtil.info("高江江高江江：GetVedioSource()获取数据源信息成功！\n sourceIp:"
					+ vSrc.get_sourceIp() + "\t sourcePort:"
					+ vSrc.get_sourcePort() + "\t sourceChannel:"
					+ vSrc.get_sourceChannel());
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			

			isc.onGetVedioSource(vSrc.get_sourceIp(), vSrc.get_sourcePort(),
					vSrc.get_sourceChannel(),context);

			LogUtil.info("高江江高江江：告知数据源信息完毕。。。");
		}
	}
	
	/**
	 * 获取显示通道的所有信息
	 * @param client
	 * @param nDisIndex
	 * @param context
	 */
	@Remoting
	public void GetDisplayChannelInfo(String client,Integer nDisIndex,String context){
		DisplayChannelInfo disInfo = new DisplayChannelInfo();
		LogUtil.info("高江江高江江：GetDisplayChannelInfo()开始被调用。。。");
		String log = "高江江高江江：";
		IpMatrixCtrl imc = IpMatrixCtrl.getIpMatrixCtrl();
		int result = imc.getDisplayChannelInfo(nDisIndex, disInfo);
		if(result==0){    //调用成功
			
			VideoServerDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + client);
			
			//String[] areas = new String[16];
			int areaCount = disInfo.getSplitNum();
			log += "SplitNum:"+areaCount+"\t IsDisplayChannelPlay:"+disInfo.getnIsDisplayChannelPlay()+"\t";
			String area1 = "";
			for(SplitArea sa :disInfo.getArrArea()){
				
				area1 += sa.getnIndex() + ";" + sa.getnDecoderIndex()
				+ ";" + sa.getnIsDecode() + ";"
				+ sa.getnIsAreaPlay() + ";" + sa.getSourceIp()
				+ ";" + sa.getSourcePort() + ";"
				+ sa.getSourceChannel();
				
				area1 += "#";
				//log +="area"+i+":"+area1+"\t";
			}
			
			LogUtil.info(log);
			LogUtil.info(area1);
			
			isc.onGetDisplayChannelInfo(disInfo.getnIsDisplayChannelPlay(),
					disInfo.getSplitNum(), area1,context);
			
			LogUtil.info("高江江高江江：onGetDisplayChannelInfo告知数据源信息完毕。。。");
		}else{
			LogUtil.info("高江江高江江：GetDisplayChannelInfo()调用失败，错误码是："+result);
		}
	}
}
