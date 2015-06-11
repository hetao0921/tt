package NVMP.IpMatrixManage.Implement;

import java.util.HashMap;
import java.util.Map;

import com.fxdigital.ipmatrix.IMatrix;
import com.fxdigital.ipmatrix.IpMatrixCtrl;
import com.fxdigital.ipmatrix.RtspMatrixCtrl;
import com.fxdigital.ipmatrix.RtspResult;
import com.fxdigital.ipmatrixvga.VgaIpMatrixCtrl;

import corenet.exchange.Encoding;

class TVCtrol {

	int tvindex;
	int status;
	PlayVideo pv;// 电视墙上的唯一点播
	int instance;// 唯一标示，播放成功后的点播句柄。
	String context;
	Integer linkedtype;
	IMatrix matrix;

	private IpMatrixManageRun save_ipmatrixrun;

	/**
	 * status 表明电视墙当前状态 0、无任何操作 1、发起点播 2、点播回馈 3、本地播放成功 4、等待重新点播 5、直接点播，不进行任何处理
	 * 
	 * 失败的都会进行重新点播。
	 * 
	 * @param imr
	 * */

	public TVCtrol(int index) {
		this.tvindex = index;
		status = 0;
		pv = null;
		instance = -1;
		context = null;
	}

	/**
	 * 远程进行点播
	 * */
	public void RequestPlayVideo(IpMatrixManageRun imr, String clientId,
			String ipMatrixId, Integer tVIndex, Integer pos, String deviceId,
			Integer cameraIndex) {
		String uuid = Encoding.getUuid() + "_";
		imr.PlayVideo(deviceId, cameraIndex, uuid);
		save_ipmatrixrun = imr;
		status = 1;
		instance = -1;
		pv = new PlayVideo(clientId, tVIndex, pos, deviceId, cameraIndex, uuid);
	}

	/**
	 * 进行关闭点播，分为2种情况，未点播的，清除数据。 点播的，发起一次关闭，然后停止。
	 * */
	public void StopPlayVideo() {

		System.out.println(" =========== in  close status" + status);

		if (status == 3) {
			System.out.println("close " + pv.getTVIndex() + "  " + instance);

			// RtspResult result = RtspMatrixCtrl.find(linkedtype.toString());
			// if (result.isEnable() || linkedtype ==221 ) {
			// System.out.println("close states "
			// + RtspMatrixCtrl.getInstance().MaxtrixStop(instance));
			// } else {
			// IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStop(instance);
			// }
			if (matrix != null)
				matrix.MaxtrixStop(instance);
			matrix = null;

		}
		if (status != 0) {
			save_ipmatrixrun.StopVideo(pv.getDeviceId(), pv.getCameraIndex(),
					pv.getUuid());
		}
		if (save_ipmatrixrun != null)
			save_ipmatrixrun.SendPalyVideoInfo(pv.getClientId(),
					pv.getTVIndex(), 1, true, "", pv.getCameraIndex());

		status = 0;
		pv = null;
		instance = -1;
		save_ipmatrixrun = null;

	}

	public void PsuhStopPlayVideo() {
		if (status == 5) {
			IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStop(instance);
		}
		status = 0;
		pv = null;
		instance = -1;
		save_ipmatrixrun = null;
	}

	public void MaxtrixStart(IpMatrixManageRun imr, String videoServerIP,
			Integer port, String user, String password,
			Integer videoServerChannel, Integer netLinkType,
			Integer deviceType, Integer netLinkMode) {
		status = 2;
		// //配合涛测试用。
		// if(port==0) {
		// port = 5050;
		// }
		// 自定义设备类型
		String deviceClass = imr.getMatrixObject().getDeviceClass();

		System.out.println("linketype :" + netLinkType);
		System.out.println("deviceType :" + deviceType);
		System.out.println("DeviceClass :"
				+ MatrixObject.getMatrixObject().getDeviceClass());
		System.out.println("Outmodel : "
				+ MatrixObject.getMatrixObject().getMatrixResult()
						.getOutmodel());
		RtspResult result = RtspMatrixCtrl.find(netLinkType.toString());
		if (deviceClass.equals("DS4004MD")
				&& MatrixObject.getMatrixObject().getMatrixResult()
						.getOutmodel() == 1) {

			// 判断输出通道为零，直接返回
			if (tvindex != 0) {
				instance = -2;
			} else {

				// 判断是否为8060VGA输出
				instance = VgaIpMatrixCtrl.MaxtrixStart(imr.getnInstance(), 0,
						0, videoServerIP, port, user, password,
						videoServerChannel, netLinkType, deviceType,
						netLinkMode);
				matrix = VgaIpMatrixCtrl.getInstance();
			}
		} else if (result.isEnable()
				&& !MatrixObject.getMatrixObject().getDeviceClass()
						.equals("HIKHD6408")) {
			System.out.println("===============look rtsp============");

			MatrixResult mr = MatrixObject.getMatrixObject().getMatrixResult();
			instance = RtspMatrixCtrl.MaxtrixStart(netLinkType, deviceType,
					videoServerIP, 0, user, password, mr.getAddress(),
					mr.getPort(), mr.getUser(), mr.getPasswd(),
					pv.getTVIndex(), pv.getPos());

			matrix = RtspMatrixCtrl.getInstance();

		} else {

			System.out.println("===============look pt============");
			System.out.println(videoServerIP);
			System.out.println(port);

			if (netLinkType == 221
					&& MatrixObject.getMatrixObject().getDeviceClass()
							.equals("Tiandy001")) {
				MatrixResult mr = MatrixObject.getMatrixObject()
						.getMatrixResult();
				videoServerIP = "@" + videoServerIP + "@" + port + "@"
						+ (user.equals("") ? "admin" : user) + "@"
						+ (password.equals("") ? "pass" : password) + "@"
						+ videoServerChannel + "@";
				System.out.println("==== in rtpsmatrixctrl==== "
						+ videoServerIP);
				instance = RtspMatrixCtrl.MaxtrixStart(netLinkType, deviceType,
						videoServerIP, 0, user, password, mr.getAddress(),
						mr.getPort(), mr.getUser(), mr.getPasswd(),
						pv.getTVIndex(), pv.getPos());

				matrix = RtspMatrixCtrl.getInstance();

			} else {
				if ((deviceClass.equals("DS4004MD")
						|| deviceClass.equals("FXH6410") || deviceClass
							.equals("FXH6450"))
						&& videoServerIP.contains("rtsp:")) {
					instance = -1;
					imr.SendPalyVideoInfo(pv.getClientId(), pv.getTVIndex(), 1,
							false, pv.getDeviceId(), pv.getCameraIndex());

				} else {
					instance = IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStart(
							imr.getnInstance(), pv.getTVIndex(), pv.getPos(),
							videoServerIP, port, user, password,
							videoServerChannel, netLinkType, deviceType,
							netLinkMode);
					matrix = IpMatrixCtrl.getIpMatrixCtrl();
				}

			}

		}
		System.out.println("opne " + pv.getTVIndex() + "  " + instance);
		if (instance > -1) {
			status = 3;
			linkedtype = netLinkType;
			imr.SendPalyVideoInfo(pv.getClientId(), pv.getTVIndex(), 1, true,
					pv.getDeviceId(), pv.getCameraIndex());
		} else {
			imr.StopVideo(pv.getDeviceId(), pv.getCameraIndex(), pv.getUuid());
			status = 4;
		}
		save_ipmatrixrun = imr;

	}

	public void PsuhPlayVideo(IpMatrixManageRun imr, String client,
			String deviceid, Integer index, String videoServerIP, Integer port,
			String user, String password, Integer videoServerChannel,
			Integer netLinkType, Integer netLinkSubType, Integer netLinkMode) {
		instance = IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStart(
				imr.getnInstance(), this.tvindex, 1, videoServerIP, port, user,
				password, videoServerChannel, netLinkType, netLinkSubType,
				netLinkMode);
		status = 5;
		save_ipmatrixrun = imr;
		pv = new PlayVideo(client, this.tvindex, 1, deviceid, index, "");

		imr.SendPalyVideoInfo(client, this.tvindex, 1, true, deviceid, index);

		save_ipmatrixrun = imr;
	}

	public void PlayFailedEvent() {
		if (status == 3) {
			if (matrix != null)
				matrix.MaxtrixStop(instance);
			matrix = null;
			// IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStop(instance);
			// 此处点播设备异常呈现的字幕
			// String rtspURL = DBConnNvmp.getDBConn().getRtspValue("1");
			// if (rtspURL != null) {
			// MatrixResult mr = MatrixObject.getMatrixObject()
			// .getMatrixResult();
			// RtspMatrixCtrl.MaxtrixStart(300, 300, rtspURL, 0, "", "",
			// mr.getAddress(), mr.getPort(), mr.getUser(),
			// mr.getPasswd(), pv.getTVIndex(), pv.getPos());
			//
			// }

		}
		status = 4;
	}

	/**
	 * 重新发起点播
	 * */
	public void again(IpMatrixManageRun imr) {
		if (save_ipmatrixrun == null || imr == null
				|| !imr.getUUID().equals(save_ipmatrixrun.getUUID())) {
			return;
		}

		if (status == 4) {

			System.out.println("again " + pv.getTVIndex());
			String uuid = Encoding.getUuid() + "_";
			pv.setUuid(uuid);
			save_ipmatrixrun.PlayVideo(pv.getDeviceId(), pv.getCameraIndex(),
					uuid);
			status = 1;
			instance = -1;
		} else if (status == 1) {
			// 处理无反馈重新连接
			save_ipmatrixrun.StopVideo(pv.getDeviceId(), pv.getCameraIndex(),
					pv.getUuid());
			String uuid = Encoding.getUuid() + "_";
			pv.setUuid(uuid);
			save_ipmatrixrun.PlayVideo(pv.getDeviceId(), pv.getCameraIndex(),
					uuid);
			status = 1;
			instance = -1;
		}
	}

	/**
	 * 重连进行断线
	 * 
	 * @param imr
	 * */
	public void onAgainConnect(IpMatrixManageRun imr) {
		if (save_ipmatrixrun == null || imr == null
				|| !imr.getUUID().equals(save_ipmatrixrun.getUUID())) {
			return;
		}

		switch (status) {
		case 0:
			break;
		case 3:
			IpMatrixCtrl.getIpMatrixCtrl().MaxtrixStop(instance);
		default:
			status = 4;
			break;
		}

	}

}

/**
 * 矩阵对象，一个矩阵服务器启动后，包含了多个通道，也就是tvindex。 一个通道对应一个电视墙点播
 * */
public class MatrixObject {

	Map<Integer, TVCtrol> TVPools;
	// private IpMatrixManageRun imr;

	private static MatrixObject matrix;
	private MatrixResult mr;
	private String deviceClass;

	public String getDeviceClass() {
		return deviceClass;
	}

	public void setDeviceClass(String deviceClass) {
		this.deviceClass = deviceClass;
	}

	public void savaMatrix(String _Address, int _port, String _user,
			String _passwd, int _matrixType, int _outmodel) {
		mr = new MatrixResult(_Address, _port, _user, _passwd, _outmodel,
				_matrixType);
	}

	public MatrixResult getMatrixResult() {

		return mr;
	}

	private MatrixObject() {
		// imr = run;
		TVPools = new HashMap<Integer, TVCtrol>();
	}

	synchronized static public MatrixObject getMatrixObject() {
		if (matrix == null) {
			matrix = new MatrixObject();
		}
		return matrix;
	}

	// 获取电视墙，如果没有，则创建一个，否则给原值。
	private TVCtrol getTvCtrol(final int index) {
		TVCtrol iTvCtrol = null;

		if (TVPools.containsKey(index)) {
			iTvCtrol = TVPools.get(index);
		} else {
			iTvCtrol = new TVCtrol(index);
			TVPools.put(index, iTvCtrol);
		}
		return iTvCtrol;
	}

	/**
	 * 客户端远程控制电视墙
	 * */
	synchronized public void IpMatrixPlayVideoEvent(IpMatrixManageRun imr,
			String clientId, String ipMatrixId, Integer tVIndex, Integer pos,
			String deviceId, Integer cameraIndex, Boolean isStart) {
		// 先获得对应的电视墙
		TVCtrol iTVCtrol = getTvCtrol(tVIndex);
		if (isStart) {
			// 发起点播到电视墙上
			iTVCtrol.StopPlayVideo();
			iTVCtrol.RequestPlayVideo(imr, clientId, ipMatrixId, tVIndex, pos,
					deviceId, cameraIndex);
		} else {
			// 关闭电视墙
			iTVCtrol.StopPlayVideo();
		}

	}

	/**
	 * 客户端远程控制电视墙
	 * */
	synchronized public void PsuhIpMatrixPlayVideoEvent(IpMatrixManageRun imr,
			String clientId, String ipMatrixId, Integer tVIndex, Integer pos,
			String deviceId, Integer cameraIndex, Boolean isStart,
			String videoIP, String user, String password, Integer port,
			Integer deviceType, Integer deviceSubType, Integer netLinkType,
			Integer netLinkSubType, Integer netLinkMode) {

		// 先获得对应的电视墙
		TVCtrol iTVCtrol = getTvCtrol(tVIndex);
		if (isStart) {
			// 发起点播到电视墙上
			iTVCtrol.PsuhStopPlayVideo();

			iTVCtrol.PsuhPlayVideo(imr, clientId, deviceId, cameraIndex,
					videoIP, port, user, password, cameraIndex, netLinkType,
					netLinkSubType, netLinkMode);

		} else {
			// 关闭电视墙
			iTVCtrol.PsuhStopPlayVideo();
		}

	}

	/**
	 * 通知客户端，当前电视墙的各种信息。
	 * */
	synchronized public void SendTvCtrolInfo(IpMatrixManageRun imr,
			String clientId) {
		for (TVCtrol iTVCtrol : TVPools.values()) {
			if (iTVCtrol.status == 3 || iTVCtrol.status == 5) {
				imr.SendPalyVideoInfo(clientId, iTVCtrol.tvindex, 1, true,
						iTVCtrol.pv.getDeviceId(), iTVCtrol.pv.getCameraIndex());
			} else {
				imr.SendPalyVideoInfo(clientId, iTVCtrol.tvindex, 1, false, "",
						0);
			}
		}
	}

	/**
	 * 点播回馈
	 * */
	synchronized public void ControlDisplayVideoEvent(IpMatrixManageRun imr,
			String DeviceId, Integer CameraIndex, String VideoServerIP,
			Integer VideoServerChannel, String user, String Password,
			Integer Port, Integer DeviceType, Integer DeviceSubType,
			Integer NetLinkType, Integer NetLinkSubType, Integer NetLinkMode,
			String Context, Integer flag) {

		// 先确定是哪个电视墙发出的,该墙状态为1，且uuid能对上。
		TVCtrol iTVCtrol = null;
		for (TVCtrol tc : TVPools.values()) {
			if (tc.status == 1 && tc.pv.getUuid().equals(Context)) {
				iTVCtrol = tc;
			}
		}
		if (iTVCtrol != null) {

			iTVCtrol.MaxtrixStart(imr, VideoServerIP, Port, user, Password,
					VideoServerChannel, NetLinkType, NetLinkSubType,
					NetLinkMode);

		} else {
			// 发送关闭请求
			imr.StopVideo(DeviceId, CameraIndex, Context);
		}

	}

	/**
	 * 点播失败后的处理
	 * */
	synchronized public void PlayFailedEvent(String DevicId,
			Integer CameraIndex, Integer type, String Context, String Reason) {
		TVCtrol iTVCtrol = null;
		for (TVCtrol tc : TVPools.values()) {
			if (tc.status != 0 && tc.pv.getUuid().equals(Context)) {
				iTVCtrol = tc;
			}
		}

		if (iTVCtrol != null) {

			iTVCtrol.PlayFailedEvent();
		}

	}

	/**
	 * 设备上线，判断有没有要进行重新连接的. 这个可能存在多个，也就是循环处理。
	 * */
	synchronized public void EncodeDeviceOnlineEvent(IpMatrixManageRun imr,
			String TerminalId, String TerminalIP, Boolean IsOnline) {

		for (TVCtrol tc : TVPools.values()) {
			// System.out.println(" deviceid: " + tc.pv.getDeviceId()
			// + "  TerminalId: " + TerminalId + " tvStatus : "
			// + tc.status);
			if (tc.status == 4 && tc.pv.getDeviceId().equals(TerminalId)) {
				tc.again(imr);
				continue;
			}
			if (tc.status == 1 && tc.pv.getDeviceId().equals(TerminalId)) {
				if (tc.context != null) {
					// 判断是否和当前值相同，如果相同，表明已经间隔2次了，可以进行模拟重连。
					if (tc.context.equals(tc.pv.getUuid())) {
						tc.again(imr);
					} else {
						tc.context = tc.pv.getUuid();
					}

				} else {
					tc.context = tc.pv.getUuid();
				}

			}

		}

	}

	/**
	 * 服务器进行重新连接，关闭所有点播,置为等待重新点播4状态
	 * */
	synchronized public void onAgainConnect(IpMatrixManageRun imr) {

		for (TVCtrol tc : TVPools.values()) {
			tc.onAgainConnect(imr);
		}

	}

	static public void main(String... args) {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		m.put(1, 1);
		m.put(1, 3);
		System.out.println(m.containsKey(1));
		switch (m.get(1)) {
		case 3:
			m.put(1, 1);
			System.out.println(1);
			break;
		case 1:
			System.out.println(2);
		default:
			break;
		}

	}

}
