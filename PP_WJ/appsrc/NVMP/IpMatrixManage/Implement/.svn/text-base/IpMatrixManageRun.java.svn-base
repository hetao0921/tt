package NVMP.IpMatrixManage.Implement;

import org.misc.log.LogUtil;

import NVMP.Proxy.MatrixDomain;
import NVMP.Proxy.StateManageDomain;
import NVMP.Proxy.VideoContrlDomain;
import Runtime.GrobalMessage;
import Runtime.IConnectOK;
import Runtime.IRunTime;
import Runtime.impl.RunTime;

import com.fxdigital.ipmatrix.eMaxtrixCategory;

import corenet.exchange.Encoding;
import corenet.exchange.ExchangeClient;

public class IpMatrixManageRun implements IConnectOK, GrobalMessage, Runnable {
	private String hostip;
	private Integer port;
	private String sessionid;

	private ExchangeClient ec;
	private IRunTime run; 

	private MatrixDomain md;
	private VideoContrlDomain vcd;
	private StateManageDomain smd;

	private int nDeviceType, nSubDeviceType, nPort, decMaxtrixStatus;
	private String szName, szPass, szAddress;

	private boolean b;
	
	
	//整个矩阵管理对象
	private MatrixObject matrix;
	
	public MatrixObject getMatrixObject() {
		return matrix;
	}
	
	

	private int nInstance;
	private Integer splitNum;
	private String uuid;
	private String deviceClass;
	
	public String getUUID() {
		return uuid;
	}

	public IpMatrixManageRun(String hostip, Integer port, String sessionid,
			int nDeviceType, int nSubDeviceType, int nPort, String szName,
			String szPass, String szAddress, int decMaxtrixStatus,String deviceClass) {
		uuid = Encoding.getUuid();
		
		matrix = MatrixObject.getMatrixObject();
		matrix.savaMatrix(szAddress, nPort, szName, szPass,nSubDeviceType,decMaxtrixStatus);
		this.hostip = hostip;
		this.port = port;
		this.sessionid = sessionid;
		this.nDeviceType = nDeviceType;
		this.nSubDeviceType = nSubDeviceType;
		this.nPort = nPort;
		this.szName = szName;
		this.szPass = szPass;
		this.szAddress = szAddress;
		this.decMaxtrixStatus = decMaxtrixStatus;
		this.deviceClass = deviceClass;

		LogUtil.TestInfo("now "+ hostip);
//		TimeCount tc = new TimeCount(this);

	}

	public void startUp() {
		if (run == null) {
			run = new RunTime();

			run.setNewConnectOk(this);

			// 两个业务，一个是矩阵点播业务
			md = new MatrixDomain(run);

			MatrixDomainEvenetHander mde = new MatrixDomainEvenetHander(
					nDeviceType, nSubDeviceType, szAddress, nPort, szName,
					szPass, decMaxtrixStatus,deviceClass);
			mde.setIpMatrixManageRun(this);
			md.setEventhand(mde);

			// 两个业务，一个是视频点播业务。
			vcd = new VideoContrlDomain(run);
			VideoContrlEvenetHander vceh = new VideoContrlEvenetHander();
			vceh.setFXStoreRun(this);
			vcd.setEventhand(vceh);

			// 附带一个业务，用来监控设备下线。
			smd = new StateManageDomain(run);
			StateManageEvenetHander smeh = new StateManageEvenetHander();
			smeh.setFXStoreRun(this);
			smd.setEventhand(smeh);
		}
		// 重新连接
		boolean flag = true;
		while (flag) {
			try {
				b = true;
				ec = new ExchangeClient();
				ec.SetSessionId(sessionid);
				run.setTransChannel(ec);
				ec.ConnectExchange(hostip, port);
				flag = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				flag = true;
				ec.get_Base().cannel();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
				}

			}
		}
		int n = 0;
		while (b) {
			try {
				Thread.sleep(1000);
				n++;
				if (n > 15) {
					// this.onAgainConnect();
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (b) {
			System.out.println(222);
			b = false;
			ec.get_Base().cannel();
			onAgainConnect();

		} else {
			LogUtil.TestInfo("connect ok  "+ this.hostip );
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			System.out.println("========楼下是加入组,没被我屏蔽掉了============");
			ec.JoinGroup("G_device_state");
			smd.GetEncodeDeviceOnline(this.sessionid);	
			ec.JoinGroup("G_Matrix");
		
		}
	}

	/**
	 * 点播图像
	 * 
	 * */
	public void PlayVideo(String DeviceId, int Index, String uuid) {
		// 设置级别为该中心最高的点播。
		vcd.RequestPlayVideo(this.sessionid, DeviceId, Index, uuid, -1, 999);
	}

	/**
	 * 停止图像
	 * 
	 * */
	public void StopVideo(String DeviceId, Integer Index, String uuid) {
		vcd.RequestStopVideo(this.sessionid, DeviceId, Index, uuid);
	}
	
	/**
	 * I帧捕获
	 * */
	public void fireDeviceShow(String DeviceId, Integer Index) {
		smd.FireDeviceShow(DeviceId, Index);
	}

	/**
	 * 告诉客户端，关于点播图像的情况。
	 * 
	 * */
	public void ResponeIpMatrixPlayVideo(String ClientId, Integer TVIndex,
			Integer pos, String DeviceId, Integer CameraIndex, Boolean IsOK,
			String sInfo) {
		this.md.ResponeIpMatrixPlayVideo(ClientId, this.sessionid, TVIndex,
				pos, DeviceId, CameraIndex, IsOK, sInfo);
	}




	public void SendScreenInfo(String ClientId, Integer TVIndex,
			Integer SplitNum) {
		md.SendScreenInfo(ClientId, this.sessionid, TVIndex, SplitNum);
	}

	// IP矩阵的显示图像状态
	public void SendPalyVideoInfo(String ClientId,Integer tvIndex,Integer pos,boolean flag,String Deviceid,Integer CameraIndex) {
		
		md.SendPalyVideoInfo(ClientId, this.sessionid, tvIndex,
				 pos, flag, Deviceid,CameraIndex);
//		int[] bs = new int[SplitNum];
//		Arrays.fill(bs, 0);
//		for (PlayVideo pv : successVideos.keySet()) {
//			md.SendPalyVideoInfo(ClientId, this.sessionid, pv.getTVIndex(),
//					pv.getPos(), true, pv.getDeviceId(), pv.getCameraIndex());
//			if (pv.getPos() - 1 < SplitNum && pv.getPos() - 1 >= 0)
//				bs[pv.getPos() - 1] = 1;
//		}
//		for (int i = 0; i < SplitNum; i++) {
//
//			if (bs[i] == 0) {
//
//				md.SendPalyVideoInfo(ClientId, this.sessionid, TVIndex, i + 1,
//						false, "", 0);
//
//			}
//
//		}
     
	}

	public static void main(String... args) {

//		String path;
//
//		if (System.getProperty("os.name").equals("Linux"))
//			path = "/etc/fxconf/AppService/AppService.conf";
//		else
//			path = "d:\\fxconf\\AppService\\AppService.conf";
//
//		// 读一下配置文件中的配置。
//		SAXReader saxReader = new SAXReader();
//		Document doc = null;
//		try {
//			doc = saxReader.read(new File(path));
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String hostip = doc.getRootElement().element("AppServer")
//				.attributeValue("IP");
//
//		Integer port = Integer.valueOf(doc.getRootElement()
//				.element("AppServer").attributeValue("PORT"));

//		if (args.length != 6 && args.length != 7) {
//			System.out.println("参数不一致，挂掉");
//			return;
//		}
		if (args.length != 11) {
			System.out.println("参数不一致，挂掉");
			return;
		}
		String szAddress;
		String sessionid;
		int nPort;
		String szName;
		String szPass;
		int decMaxtrixStatus;
		int nDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;
		
		int nSubDeviceType = 0;
		
		String deviceClass="";
		
		String hostip;
		int port;
		
		
		try {
			szAddress = args[0];
			sessionid = args[1];
			nPort = Integer.parseInt(args[2]);
			szName = args[3];
			szPass = args[4];
			decMaxtrixStatus = Integer.parseInt(args[5]);

//			if (args.length != 7) {
//				nDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;
//			} else {
//				nDeviceType = Integer.parseInt(args[6]);
//			}
			nDeviceType = Integer.parseInt(args[6]);	
			nSubDeviceType =  Integer.parseInt(args[7]);
			deviceClass = args[8];
			hostip = args[9];
			port =  Integer.parseInt(args[10]);
			System.out.println("=============== nDeviceType:" + nDeviceType);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("参数不对，挂掉");
			return;
		}

		// String sessionid = "test1";

		
		// int nPort = 8000;
		// String szName = "admin";
		// String szPass = "12345";
		// String szAddress = "192.168.2.81";
		// int decMaxtrixStatus = 0x32;
        LogUtil.BusinessInfo("==================szPass   :"+szPass);
		IpMatrixManageRun imr = new IpMatrixManageRun(hostip, port, sessionid,
				nDeviceType, nSubDeviceType, nPort, szName, szPass, szAddress,
				decMaxtrixStatus,deviceClass);
		imr.startUp();
	}

//	public static void main(String... args) {
//		try {
//			String path;
//
//			if (System.getProperty("os.name").equals("Linux"))
//				path = "/etc/fxconf/hikmatrix/hikmatrix.xml";
//			else
//				path = "d:\\nvmpdata\\hikmatrix\\hikmatrix.xml";
//
//			// 读一下配置文件中的配置。
//			SAXReader saxReader = new SAXReader();
//			Document doc = null;
//			try {
//				doc = saxReader.read(new File(path));
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			String hostip = doc.getRootElement().element("Maxtrix")
//					.attributeValue("IP");
//
//			Integer port = Integer.valueOf(doc.getRootElement()
//					.element("Maxtrix").attributeValue("PORT"));
//
//			String szAddress;
//			String sessionid;
//			int nPort;
//			String szName;
//			String szPass;
//			int decMaxtrixStatus;
//			int nDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;
//			int nSubDeviceType = 0;
//
//			@SuppressWarnings("unchecked")
//			Iterator<Element> iter = doc.getRootElement().element("Maxtrix")
//					.elementIterator();
//			while (iter.hasNext()) {
//				try {
//					Element e = iter.next();
//					szAddress = e.attributeValue("Address");
//					sessionid = e.attributeValue("DeviceId");
//					nPort = Integer.parseInt(e.attributeValue("Port"));
//					szName = e.attributeValue("Admin");
//					szPass = e.attributeValue("Pass");
//
//					decMaxtrixStatus = Integer.parseInt(e
//							.attributeValue("OutModel"));
//
//					IpMatrixManageRun imr = new IpMatrixManageRun(hostip, port,
//							sessionid, nDeviceType, nSubDeviceType, -1,
//							szName, szPass, szAddress, decMaxtrixStatus);
//					imr.startUp();
//				} catch (Exception e) {
//					e.printStackTrace();
//
//				}
//			}
//
//		} catch (Exception e) {
//
//		}
//		
//		
////		String hostip = "192.168.1.202";
////		int port = 1900;
////		String szAddress = "192.168.1.219";
////		String sessionid = "192.168.1.219";
////		int nPort = 80;
////		String szName = "admin";
////		String szPass = "123";
////		int decMaxtrixStatus = 100;
////		int nDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;	
////		int nSubDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;	
//		
////		IpMatrixManageRun imr = new IpMatrixManageRun(hostip, port, sessionid,
////		nDeviceType, nSubDeviceType, -1, szName, szPass, szAddress,
////		decMaxtrixStatus);
////		imr.startUp();
//	}
//	
	
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		System.out.println(1111111);
		this.b = false;
	}

	@Override
	public ExchangeClient onAgainConnect() {
		// 先关闭所有正在点播的图像。

		// for (Entry<PlayVideo, Integer> entry : successVideos.entrySet()) {
		//
		// boolean b = IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStop(
		// entry.getValue());
		// System.out.println("close :" + b + "  | "
		// + entry.getKey().getDeviceId() + " | "
		// + entry.getKey().getCameraIndex());
		// successVideos.remove(entry.getKey());
		//
		// }
		
		this.matrix.onAgainConnect(this);
		

		// 开始进行重连,考虑线程堵塞，采用新线程处理，并且建立一个防止多次执行机制。
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!b) {
			b = true;
			new Thread(this).start();

		}
		return null;
	}

	public int getnInstance() {
		return nInstance;
	}

	public void setnInstance(int nInstance) {
		this.nInstance = nInstance;
	}

	public void setSplitNum(Integer splitNum) {
		// TODO Auto-generated method stub
		this.splitNum = splitNum;
	}

	public Integer getSplitNum() {
		return splitNum;
	}

	@Override
	public void OnGlobalGroupLeave(String sessionid, String groupname,
			Boolean flag) {
		// TODO Auto-generated method stub

	}
	@Override
	public void run() {
		startUp();
	}


	@Override
	public void OnGlobalOnline(String sessionid, Boolean flag) {
		// TODO Auto-generated method stub
		
	}


}
