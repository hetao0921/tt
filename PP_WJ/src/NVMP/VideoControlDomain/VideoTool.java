package NVMP.VideoControlDomain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.misc.log.LogUtil;

import corenet.exchange.Encoding;
import corenet.rpc.IMessage;

public class VideoTool {

	// 本级中心ID
	private String CenterID;

	// 本级中心限制的路数
	private int LimitNum = 10;

	// 向转发发起请求的记录
	private Map<String, PalyRecord> playMap;
	// 点播成功后的记录
	private Map<String, VideoRecord> playSuccessSet;

	// 原始点播记录
	private Map<String, String> playContextSet;

	// 路由中，进过本服务器，同时转发也要通过服务器的，给予记录池中
	private Map<String, PassRecord> PassRecordMap;

	public VideoTool(String serverId) {
		CenterID = serverId;
		playMap = new ConcurrentHashMap<String, PalyRecord>();
		playSuccessSet = new ConcurrentHashMap<String, VideoRecord>();
		playContextSet = new ConcurrentHashMap<String, String>();
		PassRecordMap = new ConcurrentHashMap<String, PassRecord>();
	}

	public String getCenterID() {
		return CenterID;
	}

	/**
	 * 获取设备的详细信息。
	 * 
	 * */
	public PalyRecord getDeviceInfo(String deviceId) {
		// TODO Auto-generated method stub
		IMessage img = NVMP.Proxy.StateManageDomainProxy
				.GetSingerEncodeDeviceOnline(null, deviceId);
		if (img == null)
			return null;

		int b;
		try {
			b = Integer.parseInt(img.GetParam("deviceStatus").toString());
		} catch (Exception e) {
			b = 0;
		}

		String VideoServerIP = (String) img.GetParam("devIp");
		String user = (String) img.GetParam("userName");
		String Password = (String) img.GetParam("password");
		Integer Port;
		try {
			Port = Integer.parseInt(img.GetParam("devPort").toString());
		} catch (Exception e) {
			Port = -1;
		}
		Integer DeviceType;
		try {
			DeviceType = Integer.parseInt(img.GetParam("devType").toString());
		} catch (Exception e) {
			DeviceType = -1;
		}
		Integer DeviceSubType;
		try {
			DeviceSubType = Integer.parseInt(img.GetParam("devSubType")
					.toString());
		} catch (Exception e) {
			e.printStackTrace();
			DeviceSubType = -1;
		}

		// System.out.println("DeviceSubType:"+DeviceSubType);
		Integer NetLinkType = DeviceType;
		// 在这里判断类型，如果是指挥段，我们认为是105，如果是设备，我们认为等同于 设备类型。
		if (deviceId.subSequence(deviceId.length() - 3, deviceId.length())
				.equals("006")) {
			NetLinkType = 105;
		}

		// 目前没地方取得值，直接让他等于0
		Integer NetLinkSubType = 0;

		PalyRecord pr = new PalyRecord(deviceId, VideoServerIP, Port, user,
				Password, b, DeviceType, DeviceSubType, NetLinkType,
				NetLinkSubType, null);

		return pr;
	}

	// 保存点播信息

	public void savePlayRecord(String uuid, PalyRecord pr) {
		playMap.put(uuid, pr);

	}

	// 删除点播信息
	public void removePlayRecord(String uuid) {

		playMap.remove(uuid);

	}

	// 获取点播信息。
	public PalyRecord getPlayRecord(String uuid) {
		return playMap.get(uuid);
	}

	/**
	 * 获取点播池中， 引用某一个uuid的相关数据
	 * */
	public List<String> getPlayRecordWithUUid(String uuid) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> et : playMap.entrySet()) {
			if (et.getValue().getUuid().equals(uuid))
				list.add(et.getKey());

		}
		return list;
	}

	/**
	 * 删除点播池中，应用uuid的相关数据
	 * */
	public void RemovePlayRecordWithUUid(String uuid) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> pr : playMap.entrySet()) {
			if (pr.getValue().getUuid().equals(uuid))
				list.add(pr.getKey());

		}
		for (String str : list) {
			this.removePlayRecord(str);

		}

	}

	// /**
	// * 根据路由获取目标中心ID
	// *
	// * */
	// private String getDevCenterIdFromMap(Route centerMap) {
	// if (centerMap != null) {
	// String[] centerArray = centerMap.split(",");
	// if (centerArray != null) {
	// return centerArray[centerArray.length - 1];
	// }
	// }
	// return "";
	// }

	/**
	 * 比对当前是否为目标中心
	 * */
	public boolean isDeviceCenterid(Route centerMap) {
		centerMap.setLocalId(this.getCenterID());
		return centerMap.isLast();
	}

	// 点播的下一步
	public String getNextPlayCenter(Route centerMap) {

		if (centerMap != null) {
			centerMap.setLocalId(this.getCenterID());
			return centerMap.getNextRoute();
		}

		return "";
	}

	// // 获取点播发起中心
	// private String getClientCenterIdFromMap(Route centerMap) {
	//
	// if (centerMap != null) {
	//
	// String[] centerArray = centerMap.split(",");
	// if (centerArray != null && centerArray.length >= 1) {
	//
	// return centerArray[0];
	//
	// }
	// }
	//
	// return "";
	// }

	// 比对当前中心是否为点播发起中心
	public boolean isClientCenterid(Route centerMap) {
		centerMap.setLocalId(this.getCenterID());
		return centerMap.isFist();
	}

	// 成功返回的下一步
	public String getNextSuccessCenter(Route centerMap) {
		if (centerMap != null) {
			centerMap.setLocalId(this.getCenterID());
			return centerMap.getLastRoute();
		}
		return "";
	}

	/**
	 * 保存点播成功后的记录，同时对原点播信息进行删除。
	 * */
	public void saveVideoRecord(String uuid, String ClientId, Integer sendFlag,
			String sendId, Integer fowardFlag, String fowardId) {

		PalyRecord pr = getPlayRecord(uuid);

		removePlayRecord(uuid);

		VideoRecord vr = new VideoRecord(ClientId, pr.getDevid(),
				pr.getChannel(), sendFlag, sendId, fowardFlag, fowardId,
				pr.getCenterMap(), pr, uuid);
		LogUtil.BusinessInfo("==put ==== uuid" + vr.getUuid() + "  |  " + uuid
				+ "  |  " + (vr.getPr().getLev() == null));
		if (sendFlag.intValue() == 0) {
			vr.setUserLev(9999999);// 7个9，默认等级超高了。
		} else {
			vr.setUserLev(pr.getLev());
		}

		playSuccessSet.put(uuid, vr);

	}

	// 判断点播成功中是否存在
	public boolean isSuccessVideo(String context) {
		return playSuccessSet.containsKey(context);

	}

	/***
	 * 获得指定客户端点播的指定视频记录
	 * 
	 * */
	public List<VideoRecord> getVideoRecord(String clientUserId,
			String deviceId, Integer index) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getClientId().equals(clientUserId)
					&& vr.getDeviceId().equals(deviceId)
					&& vr.getCameraIndex().intValue() == index.intValue()) {
				LogUtil.BusinessInfo("==get==== uuid" + vr.getUuid() + "  |  "
						+ vr.getPr().getUuid());

				list.add(vr);

			}

		}

		return list;

	}

	public VideoRecord getVideoRecord(String uuid) {

		return playSuccessSet.get(uuid);

	}

	/**
	 * 获取本中心其它相同视频点播
	 * */
	public List<VideoRecord> getVideoRecord(String deviceId, Integer index) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getDeviceId().equals(deviceId)
					&& vr.getCameraIndex().intValue() == index.intValue()) {
				list.add(vr);
			}

		}

		return list;

	}

	/**
	 * 获取相关设备的全部视频点播
	 * */

	public List<VideoRecord> getVideoRecordWithDevice(String deviceId) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getDeviceId().equals(deviceId)) {
				list.add(vr);
			}

		}
		return list;
	}

	/**
	 * 查询所有的点播成功
	 * */
	public List<VideoRecord> getVideoRecord() {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			list.add(vr);
		}
		return list;
	}

	// 获得相关发送到本地的客户端 或者 中心的数据
	/**
	 * 
	 * */
	public List<VideoRecord> getVideoRecordWithSend(String sendid,
			Integer sendflag) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getSendFlag().intValue() == sendflag.intValue()
					&& vr.getSendId().equals(sendid)) {
				list.add(vr);
			}

		}
		return list;
	}

	/**
	 * 这里是要计算下一个点播路由节点是指定中心的时候，做的相关处理
	 * */
	public List<VideoRecord> getVideoRecordWithSend2(String sendid,
			Integer sendflag) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();

		for (VideoRecord vr : playSuccessSet.values()) {

			if (this.isDeviceCenterid(vr.getPr().getCenterMap()))
				continue;

			if (getNextPlayCenter(vr.getPr().getCenterMap()).equals(sendid)) {
				list.add(vr);

			}

		}
		return list;
	}

	/**
	 * 获取相关客户端的全部视频点播
	 * */
	public List<VideoRecord> getVideoRecordWithClinet(String ClinetId) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getClientId().equals(ClinetId)) {
				list.add(vr);
			}

		}
		return list;
	}

	/**
	 * 获得相关转发服务器的全部视频点播
	 * */
	public List<VideoRecord> getVideoRecordWithForward(String ForwardId) {
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getFowardFlag().intValue() == 1
					&& vr.getFowardId().equals(ForwardId)) {
				list.add(vr);
			}

		}
		return list;
	}

	public void removeVideoRecord(String uuid) {

		playSuccessSet.remove(uuid);
	}

	// 保存一个唯一标示，同时生成上下文环境。
	public String saveContext(String client, String deviceid, Integer channel,
			String context) {
		// 生成一个唯一标示，让我们去寻找
		String uuid = Encoding.getUuid();
		playContextSet.put(uuid, client + "$" + context + "$" + deviceid + "$"
				+ channel.intValue());
		return uuid;
	}

	public String getContext(String uuid) {
		// 生成一个唯一标示，让我们去寻找

		String str = playContextSet.get(uuid);
		// playContextSet.remove(uuid);
		if (str == null)
			return null;
		int n = str.indexOf("$");
		if (n != -1) {
			str = str.substring(n + 1);
		}
		int m = str.indexOf("$");
		if (m != -1) {
			str = str.substring(0, m);
		}

		return str;
	}

	public String getUuidFromContext(String client, String deviceid,
			Integer channel, String context) {
		String str = null;
		for (Entry<String, String> s : playContextSet.entrySet()) {
			if (s.getValue().equals(
					client + "$" + context + "$" + deviceid + "$"
							+ channel.intValue())) {
				str = s.getKey();
			}

		}

		return str;
	}

	/**
	 * 根据客户端，得到所有的原始请求（未关闭的）
	 * */
	public List<String> getUuidFromClient(String client) {
		List<String> list = new LinkedList<String>();
		for (String tempStr : playContextSet.values()) {
			String s = tempStr.substring(0, tempStr.indexOf("$"));
			if (s.equals(client)) {
				list.add(tempStr);
			}
		}
		return list;
	}

	public void removeContext(String uuid) {

		playContextSet.remove(uuid);
	}

	public void removeContextWith(String deviceid) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, String> e : playContextSet.entrySet()) {
			String allStr = e.getValue();
			int n = allStr.indexOf("$");
			String client = allStr.substring(0, n);
			allStr = allStr.substring(n + 1);
			n = allStr.indexOf("$");
			String context = allStr.substring(0, n);
			allStr = allStr.substring(n + 1);
			n = allStr.indexOf("$");
			String device = allStr.substring(0, n);

			if (device.equals(deviceid))
				list.add(e.getKey());

		}
		for (String str : list) {
			playContextSet.remove(str);
		}

	}

	/**
	 * 为了避免多次循环，我决定在此处
	 * 
	 * */
	public Result getLimit(Integer lev) {
		// 判断当前的连接数量: 1、点播池 2、请求点播 3、路过。
		// 只考虑是外网的。
		int templev = lev.intValue();
		// 用来计算本地（也就是7个9的）之外的总数
		int num = 0;

		// 这个用来记录该剔除是那个池中的哪一个记录
		String uuid = null;
		// Map<String, ?> map = null;
		String strFlag = null;

		// 判断点播成功的池中情况
		for (VideoRecord vr : playSuccessSet.values()) {
			LogUtil.BusinessInfo("******limit****vr****" + "deviceid: "
					+ vr.getDeviceId() + "index :" + vr.getCameraIndex()
					+ "client :" + vr.getClientId() + "lev :" + vr.getUserLev());

			if (vr.getSendFlag() == 1) {
				num++;
				if (vr.getUserLev().intValue() < templev) {

					templev = vr.getUserLev().intValue();
					uuid = vr.getUuid();
					strFlag = "1";

				}
			}

		}

		// 判断点播请求中
		for (Entry<String, PalyRecord> et : playMap.entrySet()) {
			LogUtil.BusinessInfo("******limit***pr*****" + "deviceid: "
					+ et.getValue().getDevid() + "index :"
					+ et.getValue().getChannel() + "client :"
					+ et.getValue().getClientId() + "lev :"
					+ et.getValue().getLev());

			if (!this.isClientCenterid(et.getValue().getCenterMap())) {
				num++;
				if (et.getValue().getLev().intValue() < templev) {
					templev = et.getValue().getLev().intValue();
					uuid = et.getKey();
					// map = playMap;
					strFlag = "2";

				}
			}
		}

		// 判断路由过的时
		for (PassRecord prs : PassRecordMap.values()) {
			LogUtil.BusinessInfo("******limit***no*****" + "deviceid: "
					+ prs.getDeviceId() + "index :" + prs.getCameraIndex()
					+ "client :" + prs.getClientId() + "lev :" + prs.getLev());

			if (!this.isClientCenterid(prs.getRoute())) {
				num++;
				if (prs.getLev().intValue() < templev) {
					templev = prs.getLev().intValue();
					uuid = prs.getUuid();
					// map = PassRecordMap;
					strFlag = "3";

				}
			}
		}
		Result rs = new Result(num, uuid, strFlag);
		return rs;
	}

	public void savePass(String uuid, Integer userLev, String clientUserId,
			String deviceId, Integer index, Route route, Boolean sendflag,
			String clientIP) {
		// TODO Auto-generated method stub
		PassRecord pr = new PassRecord(uuid, clientUserId, deviceId, index,
				userLev, route);
		pr.setSendFlag(sendflag);
		pr.setClientIP(clientIP);
		PassRecordMap.put(uuid, pr);
	}

	/**
	 * 删除该pass记录
	 * */
	public void removPassRecord(String uuid) {
		// TODO Auto-generated method stub
		PassRecordMap.remove(uuid);
	}

	/**
	 * 得到pass记录
	 * */
	public PassRecord getPassRecord(String uuid) {
		return PassRecordMap.get(uuid);
	}

	/**
	 * 得到相关点播痕迹
	 * */
	public List<PassRecord> getPassRecordWithDevice(String deviceid,
			Integer channel) {
		List<PassRecord> list = new LinkedList<PassRecord>();
		for (PassRecord pr : PassRecordMap.values()) {
			if (pr.getDeviceId().equals(deviceid)
					&& pr.getCameraIndex().intValue() == channel.intValue()) {

				list.add(pr);

			}

		}

		return list;
	}

	/**
	 * 获得设备相关点播痕迹
	 * 
	 * */
	public List<PassRecord> getPassRecordWithDevice(String deviceid) {
		List<PassRecord> list = new LinkedList<PassRecord>();
		for (PassRecord pr : PassRecordMap.values()) {
			if (pr.getDeviceId().equals(deviceid)) {

				list.add(pr);

			}

		}

		return list;
	}

	/**
	 * 得到相关的点播记录
	 * */
	public List<PalyRecord> getPalyRecordWithDevice(String deviceid,
			Integer channel) {
		List<PalyRecord> list = new LinkedList<PalyRecord>();
		for (PalyRecord pr : playMap.values()) {
			if (pr.getDevid().equals(deviceid)
					&& pr.getChannel().intValue() == channel.intValue()) {
				list.add(pr);
			}

		}
		return list;

	}

	/**
	 * 根据数据源，分析来自多少该源头的点播在本级
	 * */
	public List<VideoRecord> getVideoRecordFormPr(String uuid) {
		// TODO Auto-generated method stub
		List<VideoRecord> list = new LinkedList<VideoRecord>();
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getPr().getUuid().equals(uuid)) {

				list.add(vr);
			}

		}

		return list;
	}

	// 根据设备删除点播记录
	public void RemovePlayRecordWithDevice(String terminalId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> pr : playMap.entrySet()) {
			if (pr.getValue().getDevid().equals(terminalId))
				list.add(pr.getKey());

		}
		for (String str : list) {
			this.removePlayRecord(str);
		}

	}

	// 根据设备得到点播记录
	public List<String> getPlayRecordWithDevice(String terminalId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> pr : playMap.entrySet()) {
			if (pr.getValue().getDevid().equals(terminalId))
				list.add(pr.getKey());

		}
		return list;
	}

	// 根据设备删除痕迹
	public void removPassRecordWithDevice(String terminalId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PassRecord> pr : PassRecordMap.entrySet()) {
			if (pr.getValue().getDeviceId().equals(terminalId))
				list.add(pr.getKey());
		}
		for (String str : list) {
			this.removPassRecord(str);
		}

	}

	public void RemovePlayRecordWithClinet(String clientId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> pr : playMap.entrySet()) {
			if (pr.getValue().getClientId().equals(clientId))
				list.add(pr.getKey());

		}
		for (String str : list) {
			this.removePlayRecord(str);
		}

	}

	public void removPassRecordWithClinet(String clientId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PassRecord> pr : PassRecordMap.entrySet()) {
			if (pr.getValue().getClientId().equals(clientId))
				list.add(pr.getKey());
		}
		for (String str : list) {
			this.removPassRecord(str);
		}
	}

	public void removeVideoRecordwithDevice(String terminalId) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, VideoRecord> vr : playSuccessSet.entrySet()) {
			if (vr.getValue().getDeviceId().equals(terminalId))
				list.add(vr.getKey());
		}
		for (String str : list) {
			this.removeVideoRecord(str);
		}
	}

	static public void main(String... args) {
		VideoTool vt = new VideoTool("zzw");
		String a = vt.saveContext("123", "456", 7, "89");
		System.out.println(a);
		System.out.println(vt.getContext(a));
		System.out.println(vt.getUuidFromContext("123", "456", 7, "89"));

	}

	/**
	 * 根据转发服务器ID，寻找所有点播记录中相关的。
	 * */
	public List<String> getPlayRecordWithForward(String fowardServerId) {
		List<String> list = new LinkedList<String>();

		for (Entry<String, PalyRecord> pr : playMap.entrySet()) {
			if (pr.getValue().getForwardflag() == 1
					&& pr.getValue().getForwardid().equals(fowardServerId))
				list.add(pr.getKey());

		}
		return list;

	}

	/**
	 * 根据转发服务器ID，寻找所有点播痕迹 ps
	 * */
	public List<PassRecord> getPassRecordWithCenter(String sessionid) {
		List<PassRecord> list = new LinkedList<PassRecord>();

		try {
			for (PassRecord pr : PassRecordMap.values()) {
				// 先判断不是第一个哦
				if (this.isClientCenterid(pr.getRoute()))
					continue;
				if (getNextSuccessCenter(pr.getRoute()).equals(sessionid)) {
					list.add(pr);
				}
			}
		} catch (Exception e) {

			LogUtil.BusinessError(e.getMessage() + " |BIG ERROR "
					+ e.getCause());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}
		}

		return list;
	}

	/**
	 * 根据转发服务器ID，寻找所有点播痕迹 ps,这个功能是查询下一个的点播的服务器节点挂了
	 * */
	public List<PassRecord> getPassRecordWithCenter2(String sessionid) {
		List<PassRecord> list = new LinkedList<PassRecord>();

		try {
			for (PassRecord pr : PassRecordMap.values()) {
				// 先判断不是最后一个节点.
				if (this.isDeviceCenterid(pr.getRoute()))
					continue;
				if (getNextPlayCenter(pr.getRoute()).equals(sessionid)) {
					list.add(pr);
				}
			}
		} catch (Exception e) {

			LogUtil.BusinessError(e.getMessage() + " |BIG ERROR 2 "
					+ e.getCause());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}
		}

		return list;
	}

	/**
	 * 根据转发服务器ID，得到所有点播记录 pr
	 * */

	public List<String> getPalyRecordWithCenter(String sessionid) {
		List<String> list = new LinkedList<String>();

		for (Entry<String, PalyRecord> e : playMap.entrySet()) {

			if (this.isClientCenterid(e.getValue().getCenterMap()))
				continue;
			if (getNextSuccessCenter(e.getValue().getCenterMap()).equals(
					sessionid)) {
				list.add(e.getKey());

			}
		}
		return list;
	}

	/**
	 * 根据转发服务器ID，得到所有点播记录 pr,上一个方法是为了查询上一个312掉线。 这个是为了查询下一个312掉线
	 * */

	public List<String> getPalyRecordWithCenter2(String sessionid) {
		List<String> list = new LinkedList<String>();

		for (Entry<String, PalyRecord> e : playMap.entrySet()) {
			if (this.isDeviceCenterid(e.getValue().getCenterMap()))
				continue;
			if (getNextPlayCenter(e.getValue().getCenterMap())
					.equals(sessionid)) {
				list.add(e.getKey());

			}
		}
		return list;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * private Map<String, PalyRecord> playMap; // 点播成功后的记录 private Map<String,
	 * VideoRecord> playSuccessSet;
	 * 
	 * 
	 * // 路由中，进过本服务器，同时转发也要通过服务器的，给予记录池中 private Map<String, PassRecord>
	 * PassRecordMap;
	 */
	/**
	 * 获得所有的痕迹
	 * */
	public List<VideoRoute> getAllRoute() {
		List<VideoRoute> list = new LinkedList<VideoRoute>();
		for (PassRecord passRecord : PassRecordMap.values()) {
			VideoRoute videoRoute = new VideoRoute();
			videoRoute.setType("requestmark");
			videoRoute.setClientid(passRecord.getClientId());
			videoRoute.setDevicechannel(passRecord.getCameraIndex());
			videoRoute.setDeviceid(passRecord.getDeviceId());
			videoRoute.setLev(passRecord.getLev());
			videoRoute.setRoutemap(passRecord.getRoute().routeToStr());
			videoRoute.setUuid(passRecord.getUuid());
			videoRoute.setClientip(passRecord.getClientIP());
			list.add(videoRoute);
		}

		for (Entry<String, PalyRecord> e : playMap.entrySet()) {
			PalyRecord pr = e.getValue();
			VideoRoute videoRoute = new VideoRoute();
			videoRoute.setType("fowardmark");
			videoRoute.setUuid(e.getKey());
			videoRoute.setClientid(pr.getClientId());
			videoRoute.setDeviceid(pr.getDevid());
			videoRoute.setDevicechannel(pr.getChannel());
			videoRoute.setSourceip(pr.getFormIP());
			videoRoute.setSourcechannel(pr.getFormChannel());
			videoRoute.setRoutemap(pr.getCenterMap().routeToStr());
			videoRoute.setRealroutemap(pr.getRealCenterMap().routeToStr());
			videoRoute.setSourceuuid(pr.getUuid());
			videoRoute.setForwardid(pr.getForwardid());
			videoRoute.setLev(pr.getLev());
			videoRoute.setClientip(pr.getClientIP());

			list.add(videoRoute);

		}

		for (VideoRecord vr : playSuccessSet.values()) {
			VideoRoute videoRoute = new VideoRoute();
			videoRoute.setType("videoplay");

			videoRoute.setUuid(vr.getUuid());
			videoRoute.setLev(vr.getUserLev());

			videoRoute.setSendflag(vr.getSendFlag());
			videoRoute.setSendid(vr.getSendId());

			videoRoute.setClientid(vr.getPr().getClientId());
			videoRoute.setDeviceid(vr.getPr().getDevid());
			videoRoute.setDevicechannel(vr.getPr().getChannel());
			videoRoute.setSourceip(vr.getPr().getFormIP());
			videoRoute.setSourcechannel(vr.getPr().getFormChannel());
			videoRoute.setRoutemap(vr.getPr().getCenterMap().routeToStr());
			videoRoute.setRealroutemap(vr.getPr().getRealCenterMap()
					.routeToStr());
			videoRoute.setSourceuuid(vr.getPr().getUuid());
			videoRoute.setForwardid(vr.getPr().getForwardid());
			videoRoute.setClientip(vr.getPr().getClientIP());

			list.add(videoRoute);

		}

		return list;
	}

	// 获取所有的点播成功uuid
	public HashSet<String> getPlaySuccessSet() throws Exception {
		HashSet<String> hs = new HashSet<String>();
		for (String s : playSuccessSet.keySet()) {
			hs.add(s);
		}
		return hs;
	}

	// 获取所有的点播记录
	public HashSet<String> getPlayContextSet() throws Exception {
		HashSet<String> hs = new HashSet<String>();
		for (String s : playContextSet.keySet()) {
			hs.add(s);
		}
		return hs;
	}

	// 获取所有的点播痕迹请求
	public Set<String> getALLPassRecord() {
		HashSet<String> hs = new HashSet<String>();

		for (String s : PassRecordMap.keySet()) {
			hs.add(s);
		}

		return hs;
	}

	/**
	 * playContextSet.put(uuid, client + "$" + context + "$" + deviceid + "$" +
	 * channel.intValue());
	 * 
	 * */

	public String[] getPlayContextvalue(String uuid) {
		String[] sarray = null;
		String str = playContextSet.get(uuid);
		if (str == null)
			return null;
		sarray = str.split("\\$");
		return sarray;
	}

	/**
	 * 为了避免多次循环，我决定在此处
	 * 
	 * */
	public void getLimit() {

		for (VideoRecord vr : playSuccessSet.values()) {
			LogUtil.BusinessInfo("******limit****vr****" + "deviceid: "
					+ vr.getDeviceId() + "index :" + vr.getCameraIndex()
					+ "client :" + vr.getClientId() + "lev :" + vr.getUserLev());
		}

		// 判断点播请求中
		for (Entry<String, PalyRecord> et : playMap.entrySet()) {
			LogUtil.BusinessInfo("******limit***pr*****" + "deviceid: "
					+ et.getValue().getDevid() + "index :"
					+ et.getValue().getChannel() + "client :"
					+ et.getValue().getClientId() + "lev :"
					+ et.getValue().getLev());
		}

		// 判断路由过的时
		for (PassRecord prs : PassRecordMap.values()) {
			LogUtil.BusinessInfo("******limit***no*****" + "deviceid: "
					+ prs.getDeviceId() + "index :" + prs.getCameraIndex()
					+ "client :" + prs.getClientId() + "lev :" + prs.getLev());

		}

		// 判断点播数据
		for (Entry<String, String> s : playContextSet.entrySet()) {
			LogUtil.BusinessInfo("uuid  " + s.getKey() + "  context :"
					+ s.getValue());
		}
	}

	public void setLimitNum(int num) {
		// TODO Auto-generated method stub
		LimitNum = num;
	}

	public int getLimitNum() {
		// TODO Auto-generated method stub
		return LimitNum;
	}

	public boolean isLiveVideoForUUID(String UUID) {
		boolean b = false;
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getPr().getUuid().equals(UUID)) {
				b = true;
				break;
			}
		}

		return b;
	}

	public Set<VideoRecord> getSendToCenter() {
		HashSet<VideoRecord> hs = new HashSet<VideoRecord>();
		for (VideoRecord s : playSuccessSet.values()) {
			if (s != null && s.getSendFlag() == 1) {
				hs.add(s);
			}
		}
		return hs;
	}

	/**
	 * 为前置服务器服务。计算通道的最高点播等级 获取该设备通道的最高等级
	 * */
	public int getLev(PreSource ps) {
		// 本地等级为9999999的时候，就读取pr的lev；
		int lev = 0;

		// 判断点播成功的池中情况
		for (VideoRecord vr : playSuccessSet.values()) {
			if (vr.getDeviceId().equals(ps.getDeviceid())
					&& (vr.getPr().getChannel().intValue() == ps.getChannel())) {
				if (vr.getSendFlag() == 1) {

					if (vr.getUserLev().intValue() > lev) {
						lev = vr.getUserLev().intValue();

					}
				} else {
					if (vr.getPr().getLev() > lev) {
						lev = vr.getPr().getLev();
					}

				}
			}

		}

		// 判断点播请求中
		for (PalyRecord pr : playMap.values()) {
			if (pr.getDevid().equals(ps.getDeviceid())
					&& (pr.getChannel().intValue() == ps.getChannel())) {
				if (pr.getLev() > lev) {
					lev = pr.getLev();
				}
			}
		}

		return lev;
	}

	/**
	 * 获得所有的点播记录list<String>;
	 * */
	public List<String> getPalyRecordUUIDWithDevice(String deviceid, int channel) {
		List<String> list = new LinkedList<String>();
		for (Entry<String, PalyRecord> et : playMap.entrySet()) {
			if (et.getValue().getDevid().equals(deviceid)
					&& (et.getValue().getChannel().intValue() == channel)) {
				list.add(et.getKey());
			}

		}

		return list;

	}

	/**
	 * 从点播源（就是转发用来点播的）取得设备ID和通道
	 * */
	public VideoRecord getDevice(String videoURL, int channel) {
		VideoRecord vr = null;
		for (VideoRecord vrd : playSuccessSet.values()) {
			if (vrd.getPr().getFormIP().equals(videoURL)) {
				vr = vrd;
				break;
			}
		}

		return vr;
	}

	/**
	 * 从设备ID和通道 取得 点播源（就是转发用来点播的）
	 * */
	public PalyRecord getUrl(String deviceid, int channel) {
		PalyRecord pr = null;
		for (VideoRecord vrd : playSuccessSet.values()) {
			if (vrd.getPr().getDevid().equals(deviceid) && vrd.getPr().getChannel() == channel) {
				pr = vrd.getPr();
				break;
			}
		}

		return pr;
	}

	
	
}
