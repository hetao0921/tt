package NVMP.IpMatrixDomain;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import NVMP.AppService.Remoting;
import NVMP.CommandDomain.CommandDomain;
import NVMP.CommandDomain.ICommandEvent;
import corenet.exchange.Encoding;
import corenet.rpc.IMessage;

public class IpMatrixBusiness {
	private int n_start;
	private int n_stop;
	private int model;// 1 为正常模式，0为直接点播设备。
 
	public IpMatrixBusiness() {
		n_start = 0;
		n_stop = 0;
		try {

			String path = null;
			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/hikmatrix/HikmatrixModel.xml";
			else
				path = "d:\\nvmpdata\\hikmatrix\\HikmatrixModel.xml";

			// 读一下配置文件中的配置。
			SAXReader saxReader = new SAXReader();
			Document doc = null;

			doc = saxReader.read(new File(path));

			String tempString = doc.getRootElement().element("Maxtrix")
					.getTextTrim();
			model = Integer.parseInt(tempString);

		} catch (Exception e) {
			model = 1;
		}

	}

	private boolean getModel() {
		return model == 1;
	}

	public IMatrixEvent MatrixEvent;

	/**
	 * 客户端控制矩阵分屏。
	 * 
	 * @param IpMatrixId
	 *            矩阵编号
	 * @param TVIndex
	 *            TV输出序号
	 * @param SplitNum
	 *            分屏数量
	 * 
	 * */

	@Remoting
	public void SetScreenSplitNum(String IpMatrixId, Integer TVIndex,
			Integer SplitNum) {
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(IpMatrixId));
		MatrixEvent.OnSetScreenSplitNum(TVIndex, SplitNum);
	}

	/**
	 * 客户端向控制矩阵点播图像
	 * 
	 * 
	 * 
	 * */

	@Remoting
	public void IpMatrixPlayVideo(String ClientId, String IpMatrixId,
			Integer TVIndex, Integer pos, String DeviceId, Integer CameraIndex,
			Boolean IsStart) {
		if (IsStart)
			n_start++;
		else
			n_stop++;

		LogUtil.BusinessInfo("#$%#$% start: " + n_start + "   |   stop: "
				+ n_stop);
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(IpMatrixId));
		if (getModel()) {
			// 原方案不变
			MatrixEvent.OnIpMatrixPlayVideo(ClientId, IpMatrixId, TVIndex, pos,
					DeviceId, CameraIndex, IsStart);
		} else {
			// 查询设备，然后将值直接发过去的。
			if (IsStart) {
				IMessage img = NVMP.Proxy.StateManageDomainProxy
						.GetSingerEncodeDeviceOnline(null, DeviceId);

				if (img != null) {

					String VideoIP = (String) img.GetParam("devIp");
					String User = (String) img.GetParam("userName");
					String Password = (String) img.GetParam("password");
					Integer Port;
					try {
						Port = Integer.parseInt(img.GetParam("devPort")
								.toString());
					} catch (Exception e) {
						Port = -1;
					}
					Integer DeviceType;
					try {
						DeviceType = Integer.parseInt(img.GetParam("devType")
								.toString());
					} catch (Exception e) {
						DeviceType = -1;
					}
					Integer DeviceSubType;
					try {
						DeviceSubType = Integer.parseInt(img.GetParam(
								"devSubType").toString());
					} catch (Exception e) {
						e.printStackTrace();
						DeviceSubType = -1;
					}
					Integer NetLinkType = DeviceType;
					Integer NetLinkSubType = 0;
					Integer NetLinkMode = null;
					// 要得到的信息。
					MatrixEvent.OnPsuhIpMatrixPlayVideo(ClientId, IpMatrixId,
							TVIndex, pos, DeviceId, CameraIndex, IsStart,
							VideoIP, User, Password, Port, DeviceType,
							DeviceSubType, NetLinkType, NetLinkSubType,
							NetLinkMode);
				} else {
					MatrixEvent.OnPsuhIpMatrixPlayVideo(ClientId, IpMatrixId,
							TVIndex, pos, DeviceId, CameraIndex, IsStart, null,
							null, null, null, null, null, null, null, null);
				}

			}

		}
	}

	/**
	 * 矩阵点播成功后，返回成功信息。
	 * 
	 * */
	@Remoting
	public void ResponeIpMatrixPlayVideo(String ClientId, String IpMatrixId,
			Integer TVIndex, Integer pos, String DeviceId, Integer CameraIndex,
			Boolean IsOK, String sInfo) {
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(ClientId));
		MatrixEvent.OnResponeIpMatrixPlayVideo(ClientId, IpMatrixId, TVIndex,
				pos, DeviceId, CameraIndex, IsOK, sInfo);
	}

	/**
	 * 客户端请求矩阵发送本身状态。
	 * */
	@Remoting
	public void GetMatrixState(String ClientId, String IpMatrixId) {
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(IpMatrixId));
		MatrixEvent.OnGetMatrixState(ClientId);
	}

	/**
	 * 
	 * 矩阵回复客户端本身状态。
	 * 
	 * 
	 * */
	// IP矩阵的分屏幕状态
	@Remoting
	public void SendScreenInfo(String ClientId, String IpMatrixId,
			Integer TVIndex, Integer SplitNum) {
		// 修改指挥员的登陆状
		String say = String.format(
				"****发送分屏信息：ClientId=%s,IpMatrixId=%s,TVIndex==%s,SplitNum=%s",
				ClientId, IpMatrixId, TVIndex.toString(), SplitNum.toString());
		LogUtil.BusinessDebug(say);
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(ClientId));
		MatrixEvent.OnScreenInfo(IpMatrixId, TVIndex, SplitNum);
	}

	/**
	 * 矩阵回复客户端本身点播图像情况。
	 * 
	 * */

	// IP矩阵的显示图像状态
	@Remoting
	public void SendPalyVideoInfo(String ClientId, String IpMatrixId,
			Integer TVIndex, Integer pos, Boolean PlayVido, String DeviceId,
			Integer CameraIndx) {
//		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
//				+ Encoding.AdsToPointProtocol(ClientId));
//		MatrixEvent.OnPalyVideoInfo(IpMatrixId, TVIndex, pos, PlayVido,
//				DeviceId, CameraIndx);
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToGroupProtocol("G_Video"));
		MatrixEvent.OnPalyVideoInfo(IpMatrixId, TVIndex, pos, PlayVido,
				DeviceId, CameraIndx);
		
	}

	@Remoting
	public void SendMatrixOnline(String MatrixId, Boolean IsOnline) {
		String say = String.format("****解码矩阵上下线通知：MatrixId=%s,IsOnline=%s",
				MatrixId, IsOnline.toString());
		LogUtil.BusinessDebug(say);
		(MatrixDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToGroupProtocol("G_Video"));
		MatrixEvent.OnIpMatrixOnline(MatrixId, IsOnline);
	}

	static public void main(String... args) {
		System.out.println(new IpMatrixBusiness().getModel());
	}

}
