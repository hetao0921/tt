package NVMP.VideoControlDomain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import com.sqlite.factory.DAOFactory;

import corenet.exchange.Encoding;
import corenet.rpc.IMessage;
import NVMP.AppService.Remoting;
import NVMP.Proxy.VideoContrlDomainProxy;

/**
 * 本类是为了处理级联点播而作。
 * */
public class VideoControl extends Thread {



	public IVideoControl VideoControl;

	private VideoTool vt;

	private PreVideoTool pvt;

	private HashSet<VideoServerInfo> VideoServers;

	private List<Set<String>> dropList;

	private List<Set<String>> passDropList;

	private List<Set<String>> videoDropList;

	// 用来记录那些设备不走转发服务器。
	private Set<String> noFrawdDeviceSet;

	/**
	 * 5秒一循环，做4个池，当到第4个池的时候，认为是超时，进行相关处理。
	 * 
	 * 
	 * */

	public void run() {

		int timeCount = 0;

		while (true) {

			try {
				Thread.sleep(5000);

				// vt.getLimit();

				Set<String> playSet = vt.getPlaySuccessSet();
				Set<String> contextSet = vt.getPlayContextSet();

				Set<String> passSet = vt.getALLPassRecord();

				for (int i = dropList.size() - 1; i > -1; i--) {

					Set<String> set = dropList.get(i);
					// LogUtil.BusinessInfo("===  "+i+"  Start");
					// for(String s:set) {
					// LogUtil.BusinessInfo(s);
					// }
					// LogUtil.BusinessInfo("===  "+i+"  End");

					if (set.size() > 0) {
						// 删除点播成功的
						for (String s : playSet) {
							set.remove(s);
						}
						// 删除当前记录中没有的。
						Set<String> ls = new HashSet<String>();
						for (String s : set) {
							if (!contextSet.contains(s))
								ls.add(s);
						}
						set.removeAll(ls);
					}

					if (i == dropList.size() - 1) {
						// 最后一个池，进行删除
						for (String s : set) {
							String[] array = vt.getPlayContextvalue(s);
							if (array != null && array.length == 4) {
								/**
								 * playContextSet.put(uuid, client + "$" +
								 * context + "$" + deviceid + "$" +
								 * channel.intValue());
								 * 
								 * */
								LogUtil.BusinessError("delete client "
										+ array[0] + "  dev" + array[2]);
								this.RequestStopVideo(array[0], array[2],
										Integer.parseInt(array[3]), array[1]);

							}
						}

					} else {
						// 每个池都往后移动一位。
						// Set<String> setold = dropList.get(i + 1);
						Set<String> setold = new HashSet<String>();
						for (String s : set) {
							setold.add(s);
						}
						dropList.set(i + 1, setold);
					}

				}

				// 第一个池，录入新的数据
				Set<String> setnew = new HashSet<String>();
				for (String s : contextSet) {
					setnew.add(s);
				}
				if (setnew.size() > 0) {
					// 删除点播成功的
					for (String s : playSet) {
						setnew.remove(s);
					}
				}
				dropList.set(0, setnew);

				/**
				 * 此处开始删除所有的超过1分钟的痕迹
				 * 
				 * */

				for (int i = passDropList.size() - 1; i > -1; i--) {

					Set<String> set = passDropList.get(i);

					if (set.size() > 0) {
						// 保存还在痕迹里面的数据

						Set<String> ls = new HashSet<String>();
						for (String s : set) {
							if (!passSet.contains(s))
								ls.add(s);
						}
						set.removeAll(ls);
					}

					if (i == passDropList.size() - 1) {
						// 最后一个池，进行删除
						for (String s : set) {
							LogUtil.BusinessError("delete pass " + s);
							vt.removPassRecord(s);
						}

					} else {
						// 每个池都往后移动一位。
						Set<String> setold = new HashSet<String>();
						for (String s : set) {
							setold.add(s);
						}
						passDropList.set(i + 1, setold);
					}

				}

				// 第一个池，录入新的数据
				Set<String> setpassnew = new HashSet<String>();
				for (String s : passSet) {
					setpassnew.add(s);
				}

				passDropList.set(0, setpassnew);

				/**
				 * 这里开始清除视频点播
				 * 
				 * */
				// 1、每过1分钟，清理一次激活池
				if (timeCount >= 7) {
					videoLiveSet.clear();
					timeCount = 0;
				}

				// 1、每5秒，询问一次，尚未激活的
				Set<VideoRecord> tempList = vt.getSendToCenter();
				for (VideoRecord vr : tempList) {
					if (!videoLiveSet.contains(vr.getUuid())) {

						IMessage message = VideoContrlDomainProxy
								.GobalVideoLive_Copy(vt.getCenterID(),
										vr.getSendId(), vr.getUuid());
						(VideoControlDomain.AppRunTime())
								.LocalChannelSendMessage(message,
										vr.getSendId(), null, null);
					}

				}

				// 每当timeCount>0的时候，删除相关痕迹，并且往前发送。
				if (timeCount > 0) {
					for (int i = videoDropList.size() - 1; i > -1; i--) {

						Set<String> set = videoDropList.get(i);
						// 清除活动的和不存在的数据
						set.removeAll(videoLiveSet);

						// 如果是最后一个，将所有还存在的干掉。

						if (i == videoDropList.size() - 1) {
							// 最后一个池，进行删除
							for (VideoRecord s : tempList) {
								if (set.contains(s.getUuid())) {
									LogUtil.BusinessError("delete video "
											+ s.getUuid());
									vt.removeVideoRecord(s.getUuid());
									// 通知服务器关闭

									if (s.getFowardFlag() == 1) {
										(VideoControlDomain.AppRunTime())
												.SetCurChannel("Local://Session://"
														+ s.getFowardId());
										VideoControl.StopStreamFoward(s
												.getClientId(),
												s.getDeviceId(), s.getPr()
														.getFormIP(), s.getPr()
														.getFormChannel(), s
														.getFowardId(), s
														.getPr()
														.getFormChannel(), 1,
												"服务器 请求停止!");

										// 关闭前置。
										stopPreFoward(s.getPr().getDevid(), s
												.getPr().getChannel(), 1,
												s.getUuid());

										LogUtil.VideoInfo("$$StopStreamFoward 1 234|"
												+ s.getPr().getFormIP()
												+ " | "
												+ s.getPr().getFormChannel()
												+ " | " + s.getFowardId());

									}

								}

							}

						} else {
							// 每个池都往后移动一位。
							videoDropList.set(i + 1, set);
						}

					}

					// 第一个池，录入新的数据
					Set<String> setvideonew = new HashSet<String>();
					for (VideoRecord s : tempList) {
						setvideonew.add(s.getUuid());
					}
					videoDropList.set(0, setvideonew);

				}

				timeCount++;
			} catch (Exception e) {
				LogUtil.BusinessError("long time out video" + e.getMessage());
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.BusinessError(s.toString());
				}
			}

		}
	}

	public VideoControl() {

		LogUtil.BusinessInfo("VideoControlDomain serverID : "
				+ VideoControlDomain.AppRunTime().getServerId());
		vt = new VideoTool(VideoControlDomain.AppRunTime().getServerId());

		pvt = new PreVideoTool();

		VideoServers = new HashSet<VideoServerInfo>();
		videoLiveSet = new CopyOnWriteArraySet<String>();

		/**
		 * 在此处初始化所有的视频转发模块的路数限制
		 * 
		 * */

		int lev = 0;
		vt.setLimitNum(lev);
		vt.getLimitNum();

		String path = null;
		String devicePath = null;

		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/AppService/AppService.conf";

			devicePath = "/etc/fxconf/AppService/video.conf";
			;

		} else {
			path = "d:\\fxconf\\AppService\\AppService.conf";
			devicePath = "d:\\fxconf\\AppService\\video.conf";
		}
		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		String tempString = doc.getRootElement().element("AppServer")
				.attributeValue("NUMS");
		if (tempString == null) {
			vt.setLimitNum(0);
		} else {
			vt.setLimitNum(Integer.parseInt(tempString));
		}

		try {
			noFrawdDeviceSet = new HashSet<String>();
			doc = saxReader.read(new File(devicePath));
			@SuppressWarnings({ "unchecked" })
			Iterator<Element> itor = doc.getRootElement().elementIterator(
					"devicetype");

			while (itor.hasNext()) {
				Element e = itor.next();
				noFrawdDeviceSet.add(e.attributeValue("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 定义一个超时停止链。每次关闭最后一池中的记录。继续模拟关闭。 1、每次按照5秒进行读数 2、最后一个池，判断后进行删除。
		 * */
		int n = 6;

		dropList = new ArrayList<Set<String>>();

		for (int i = 0; i < n; i++) {
			dropList.add(new HashSet<String>());
		}

		int m = 9;
		passDropList = new ArrayList<Set<String>>();
		for (int i = 0; i < m; i++) {
			passDropList.add(new HashSet<String>());
		}

		int x = 9;

		videoDropList = new ArrayList<Set<String>>();
		for (int i = 0; i < x; i++) {
			videoDropList.add(new HashSet<String>());
		}

		this.start();

	}

	/**
	 * 5.1 设计2个方案 。 1、通知客户端，在本机点播的相关痕迹 2、服务器和服务器之间相互询问，该痕迹是否可删除。 收到要求查看相关的
	 * */

	/**
	 * 接收方要查询相关的点播UUID是否活着。
	 * */
	@Remoting
	public void GobalVideoLive(String CenterIDFrom, String CenterIDTo,
			String UUID) {
		// 先判断是否为本中心，不是就跳过。
		if (!vt.getCenterID().equals(CenterIDTo)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		if (vt.isLiveVideoForUUID(UUID)) {
			IMessage message = VideoContrlDomainProxy.GobalVideoLiveBack_Copy(
					CenterIDFrom, CenterIDTo, UUID);
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
					CenterIDFrom, null, null);

		}

	}

	/**
	 * 点播UUID的心跳包。
	 * */
	@Remoting
	public void GobalVideoLiveBack(String CenterIDFrom, String CenterIDTo,
			String UUID) {
		// 先判断是否为本中心，不是就跳过。
		if (!vt.getCenterID().equals(CenterIDFrom)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		regLive(UUID);
	}

	/**
	 * 方法，写入心跳包。
	 * 
	 * */

	Set<String> videoLiveSet;

	private void regLive(String uuid) {
		if (uuid != null)
			videoLiveSet.add(uuid);
	}

	/**
	 * 获得该客户在本机上的点播
	 * */
	@Remoting
	public void getClientPlay(String ClientID) {
		List<String> list = vt.getUuidFromClient(ClientID);
		for (String allStr : list) {
			System.out.println(allStr);
			int n = allStr.indexOf("$");
			String client = allStr.substring(0, n);
			allStr = allStr.substring(n + 1);
			n = allStr.indexOf("$");
			String context = allStr.substring(0, n);
			allStr = allStr.substring(n + 1);
			n = allStr.indexOf("$");
			String deviceid = allStr.substring(0, n);
			allStr = allStr.substring(n + 1);
			Integer channel = Integer.parseInt(allStr);
			(VideoControlDomain.AppRunTime())
					.SetCurChannel("Local://Session://" + ClientID);
			VideoControl.OnGetClientPlay(client, deviceid, channel, context);
		}
	}

	/**
	 * 远程设置视频路数
	 * 
	 * */
	@Remoting
	public void SetLimitNum(Integer num) {

		LogUtil.BusinessInfo("********* Set Video LimitNum :" + num);
		vt.setLimitNum(num);

	}

	@Remoting
	public void RequestPlayVideo_bk_test(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer NetLinkMode, Integer userLev) {
		try {
			if (!Context.contains("_")) {
				LogUtil.BusinessInfo(" clinet verson wrong");
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://" + ClientUserId);
				VideoControl.OnPlayFailed(DeviceId, Index, 0, Context,
						"客户端版本太低!");
				return;
			}

			// 得到客户端的ip
			String clientIP = VideoControlDomain.AppRunTime().getIP(
					ClientUserId);

			userLev = Integer.valueOf(VideoControlDomain.AppRunTime().getLev())
					* 1000 + userLev;

			// 将点播内容进行保存，然后生成一个唯一ID,防止出意外。
			Context = vt.saveContext(ClientUserId, DeviceId, Index, Context);
			// 获取对应路由

			LogUtil.BusinessInfo("方法调入查询路由，传入的值是：" + vt.getCenterID() + " |  "
					+ DeviceId);
			Route centerMap = RouteImpl.getRouteImpl().getRoute(
					vt.getCenterID(), DeviceId);
			LogUtil.BusinessInfo("方法调入查询路由结束：" + (centerMap != null));

			// 开始进行转发。

			if (NetLinkMode == null || NetLinkMode.equals(""))
				NetLinkMode = -1;

			// 判断当前中心与设备中心是否一致，如果一致
			if (vt.isDeviceCenterid(centerMap)) {

				System.out.println("========" + centerMap.getRouteDesc());
				// 获得该设备的详细信息。包括
				PalyRecord pr = vt.getDeviceInfo(DeviceId);
				if (pr == null) {
					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 0, context,
							"未找到该设备!");
					return;

				}

				if (pr.getState().intValue() == 0) {

					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 1, context,
							"设备下线!");
					return;

				}

				pr.setChannel(Index);

				VideoServerInfo vs = FindVideoServer(DeviceId, Index);
				pr.setChannel(Index);
				// pr.setNetLinkType(pr.getDeviceType());
				pr.setFormChannel(pr.getChannel());
				pr.setNetLinkMode(NetLinkMode);
				pr.setFormFlag(0);
				pr.setLev(userLev);// 7个9，彰显地位之高
				// 进行记录
				String uuid = Context;
				pr.setCenterMap(centerMap);
				pr.setRealCenterMap(centerMap);
				pr.setUuid(uuid);
				pr.setClientId(ClientUserId);
				if (vs != null) {
					pr.setForwardid(vs.getDeviceId());
				}
				// 保存IP信息
				pr.setClientIP(clientIP);

				vt.savePlayRecord(uuid, pr);

				if (vs != null) {
					// 进行转发点播：
					LogUtil.BusinessDebug("==to FowardServer =="
							+ vs.getDeviceId() + " netlinktype:"
							+ pr.getNetLinkType());
					(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
							+ Encoding.AdsToPointProtocol(vs.getDeviceId()));

					VideoControl.StartStreamFoward(ClientUserId, DeviceId,
							pr.getFormChannel(), "192.168.1.219", 1,
							pr.getUser(), pr.getPassword(), 5050, 100,
							pr.getDeviceSubType(), 105, pr.getNetLinkSubType(),
							pr.getNetLinkMode(), uuid, 1);

					LogUtil.VideoInfo("**StartStreamFoward  695 |"
							+ pr.getFormIP() + " | " + pr.getFormChannel()
							+ " | " + vs.getDeviceId());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 点播图像，判断是否能本地直接点播，如果不能就进行广播出去。
	 * 
	 * */
	@Remoting
	public void RequestPlayVideo(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer NetLinkMode, Integer userLev) {
		try {
			if (!Context.contains("_")) {
				LogUtil.BusinessInfo(" clinet verson wrong");
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://" + ClientUserId);
				VideoControl.OnPlayFailed(DeviceId, Index, 0, Context,
						"客户端版本太低!");
				return;
			}

			/**
			 * by zzw 2013-03-06 增加判断，如果userlev为-1，则认为客户端企图直连。
			 * 
			 * */
			if (userLev.intValue() == -1) {

				LogUtil.BusinessInfo("userLev   =    -1");
				PalyRecord pr = vt.getDeviceInfo(DeviceId);
				if (pr == null) {
					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 0, context,
							"未找到该设备!");
					return;

				}
				if (pr.getState().intValue() == 0) {

					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 1, context,
							"设备下线!");
					return;

				}

				// 判断是否有rtsp数据。
				String rtspurl = DBConnNvmp.getDBConn().getRtspValue(DeviceId,
						null, -1, Index);

				if (rtspurl != null) {
					pr.setFormIP(rtspurl);
					LogUtil.BusinessInfo("rtspurl   =: " + rtspurl);
					pr.setDeviceType(300);
					pr.setDeviceSubType(0);
					pr.setNetLinkType(300);
					pr.setNetLinkSubType(0);
				} else {

					LogUtil.BusinessInfo("userLev   =    -1   error ");
				}

				pr.setChannel(Index);
				// pr.setNetLinkType(pr.getDeviceType());
				pr.setFormChannel(pr.getChannel());
				pr.setNetLinkMode(NetLinkMode);
				pr.setFormFlag(0);
				pr.setLev(userLev);// 7个9，彰显地位之高
				// 进行记录
				String uuid = Context;
				pr.setUuid(uuid);
				pr.setClientId(ClientUserId);

				// 无转发就直接过了。

				this.StreamFowardSuccess(ClientUserId, DeviceId,
						pr.getFormChannel(), "", pr.getFormIP(),
						pr.getFormChannel(), pr.getUser(), pr.getPassword(),
						pr.getPort(), pr.getDeviceType(),
						pr.getDeviceSubType(), pr.getNetLinkType(),
						pr.getNetLinkSubType(), pr.getNetLinkMode(), Context, 3);

				return;
			}

			// 得到客户端的ip
			String clientIP = VideoControlDomain.AppRunTime().getIP(
					ClientUserId);

			userLev = Integer.valueOf(VideoControlDomain.AppRunTime().getLev())
					* 1000 + userLev;

			// 将点播内容进行保存，然后生成一个唯一ID,防止出意外。
			Context = vt.saveContext(ClientUserId, DeviceId, Index, Context);
			// 获取对应路由

			LogUtil.BusinessInfo("方法调入查询路由，传入的值是：" + vt.getCenterID() + " |  "
					+ DeviceId);
			Route centerMap = RouteImpl.getRouteImpl().getRoute(
					vt.getCenterID(), DeviceId);
			LogUtil.BusinessInfo("方法调入查询路由结束：" + (centerMap != null));

			// 先判断本中心的点播中是否已经存在该点播了。
			List<VideoRecord> list = vt.getVideoRecord(DeviceId, Index);
			VideoRecord videoRecord = null;
			if (list.size() > 0) {
				videoRecord = list.get(0);
			}

			// 点播池中的记录
			if (videoRecord != null
					&& videoRecord.getFowardFlag().intValue() == 1) {
				// 获得数据源
				PalyRecord pr = videoRecord.getPr().getClone();
				// 进行记录
				String uuid = Context;
				pr.setFormFlag(2);
				pr.setLev(userLev);// 7个9，彰显等级最高了
				pr.setCenterMap(centerMap);
				pr.setClientId(ClientUserId);
				LogUtil.BusinessInfo("=======1=======");

				// 保存IP信息
				pr.setClientIP(clientIP);

				pr.setForwardid(videoRecord.getFowardId());

				// 提前找到未加入前的数据
				List<VideoRecord> listVr = vt.getVideoRecordFormPr(videoRecord
						.getPr().getUuid());
				List<String> listPr = vt.getPlayRecordWithUUid(videoRecord
						.getPr().getUuid());

				vt.savePlayRecord(uuid, pr);

				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://"
								+ Encoding.AdsToPointProtocol(videoRecord
										.getFowardId()));
				VideoControl.StartStreamFoward(ClientUserId, DeviceId,
						pr.getFormChannel(), pr.getFormIP(),
						pr.getFormChannel(), pr.getUser(), pr.getPassword(),
						pr.getPort(), pr.getDeviceType(),
						pr.getDeviceSubType(), pr.getNetLinkType(),
						pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid, 1);

	

				LogUtil.VideoInfo("**StartStreamFoward  516 |" + pr.getFormIP()
						+ " | " + pr.getFormChannel() + " | "
						+ videoRecord.getFowardId());

				/**
				 * 这里缺少一个等级提升： 判断当前记录中，最高级的为多少。
				 * 
				 * */
				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord tempPr = vt.getPlayRecord(str);
					if (tempPr.getLev() > maxLev) {
						maxLev = tempPr.getLev();
					}
				}
				// 如果当前点播级别大于原级别，进行传递。
				if (pr.getLev() > maxLev) {
					this.ChangeUserLev(pr.getUuid(), pr.getRealCenterMap(),
							pr.getLev(), true);
				}

				return;
			}

			/**
			 * 看看在已回馈的视频点播中，是否有发给转发服务器请求的。
			 * */
			List<PalyRecord> list2 = vt
					.getPalyRecordWithDevice(DeviceId, Index);
			PalyRecord playRecord = null;
			if (list2.size() > 0) {
				playRecord = list2.get(0);
			}
			// 点播池中的记录
			if (playRecord != null && playRecord.getForwardflag() == 1) {
				// 获得数据源
				PalyRecord pr = playRecord.getClone();
				// 进行记录
				String uuid = Context;
				pr.setFormFlag(2);
				pr.setLev(userLev);// 7个9，彰显等级最高了
				pr.setCenterMap(centerMap);
				pr.setClientId(ClientUserId);
				LogUtil.BusinessInfo("=======2=======");

				pr.setForwardid(playRecord.getForwardid());

				// 保存IP信息
				pr.setClientIP(clientIP);

				// 提前找到未加入前的数据
				List<VideoRecord> listVr = vt.getVideoRecordFormPr(playRecord
						.getUuid());
				List<String> listPr = vt.getPlayRecordWithUUid(playRecord
						.getUuid());

				vt.savePlayRecord(uuid, pr);
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://"
								+ Encoding.AdsToPointProtocol(playRecord
										.getForwardid()));
				VideoControl.StartStreamFoward(ClientUserId, DeviceId,
						pr.getFormChannel(), pr.getFormIP(),
						pr.getFormChannel(), pr.getUser(), pr.getPassword(),
						pr.getPort(), pr.getDeviceType(),
						pr.getDeviceSubType(), pr.getNetLinkType(),
						pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid, 1);



				LogUtil.VideoInfo("**StartStreamFoward 596 |" + pr.getFormIP()
						+ " | " + pr.getFormChannel() + " | "
						+ playRecord.getForwardid());

				/**
				 * 这里缺少一个等级提升： 判断当前记录中，最高级的为多少。
				 * 
				 * */
				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord tempPr = vt.getPlayRecord(str);
					if (tempPr.getLev() > maxLev) {
						maxLev = tempPr.getLev();
					}
				}
				// 如果当前点播级别大于原级别，进行传递。
				if (pr.getLev() > maxLev) {
					this.ChangeUserLev(pr.getUuid(), pr.getRealCenterMap(),
							pr.getLev(), true);
				}

				return;
			}

			/**
			 * 这里判断一次，是否有兄台进行点播了。如果有，只需要进行一次记录即可。
			 * 
			 * */
			List<PassRecord> list3 = vt
					.getPassRecordWithDevice(DeviceId, Index);
			if (list3.size() > 0) {
				vt.savePass(Context, userLev, ClientUserId, DeviceId, Index,
						centerMap, false, clientIP);
				LogUtil.BusinessInfo("=======3=======");
				return;
			}



			// 开始进行转发。

			if (NetLinkMode == null || NetLinkMode.equals(""))
				NetLinkMode = -1;

			// 判断当前中心与设备中心是否一致，如果一致
			if (vt.isDeviceCenterid(centerMap)) {

				LogUtil.BusinessInfo("=====isDeviceCenterid === "
						+ centerMap.getRouteDesc());
				// 获得该设备的详细信息。包括
				PalyRecord pr = vt.getDeviceInfo(DeviceId);
				LogUtil.BusinessInfo("=====getDeviceInfo === pv is null"
						+ (pr == null));

				if (pr == null) {
					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 0, context,
							"未找到该设备!");
					return;

				}
				LogUtil.BusinessInfo("=====getDeviceInfo === " + pr.getDevid()
						+ pr.getPassword());

				if (pr.getState().intValue() == 0) {

					String context = vt.getContext(Context);
					vt.removeContext(Context);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + ClientUserId);
					VideoControl.OnPlayFailed(DeviceId, Index, 1, context,
							"设备下线!");
					return;

				}

				String rtspurl = DBConnNvmp.getDBConn().getRtspValue(DeviceId,
						null, -1, Index);
				System.out.println(rtspurl+"  is "+(rtspurl==null));
				if (rtspurl != null) {
					pr.setFormIP(rtspurl);
					pr.setDeviceType(300);
					pr.setDeviceSubType(0);
					pr.setNetLinkType(300);
					pr.setNetLinkSubType(0);
				}


				pr.setChannel(Index);

				VideoServerInfo vs = FindVideoServer(DeviceId, Index);
				pr.setChannel(Index);
				// pr.setNetLinkType(pr.getDeviceType());
				pr.setFormChannel(pr.getChannel());
				pr.setNetLinkMode(NetLinkMode);
				pr.setFormFlag(0);
				pr.setLev(userLev);// 7个9，彰显地位之高
				// 进行记录
				String uuid = Context;
				pr.setCenterMap(centerMap);
				pr.setRealCenterMap(centerMap);
				pr.setUuid(uuid);
				pr.setClientId(ClientUserId);
				// 保存IP信息
				pr.setClientIP(clientIP);

				vt.savePlayRecord(uuid, pr);
				LogUtil.VideoInfo(pr.getDeviceType()
						+ " =============================  "
						+ !this.noFrawdDeviceSet.contains(pr.getDeviceType()
								.toString()));
				if (vs != null
						&& !this.noFrawdDeviceSet.contains(pr.getDeviceType()
								.toString())) {
					// 进行转发点播：
					for (String str : noFrawdDeviceSet) {
						LogUtil.VideoInfo("============================= "
								+ str);
					}
					// LogUtil.VideoInfo(!this.noFrawdDeviceSet.contains(pr.getNetLinkType()));
					pr.setForwardid(vs.getDeviceId());

					LogUtil.BusinessDebug("==to FowardServer =="
							+ vs.getDeviceId() + " netlinktype:"
							+ pr.getNetLinkType());
					(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
							+ Encoding.AdsToPointProtocol(vs.getDeviceId()));

					VideoControl.StartStreamFoward(ClientUserId, DeviceId,
							pr.getFormChannel(), pr.getFormIP(),
							pr.getFormChannel(), pr.getUser(),
							pr.getPassword(), pr.getPort(), pr.getDeviceType(),
							pr.getDeviceSubType(), pr.getNetLinkType(),
							pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid,
							1);

					LogUtil.VideoInfo("**StartStreamFoward  695 |"
							+ pr.getFormIP() + " | " + pr.getFormChannel()
							+ " | " + vs.getDeviceId());

				} else {
					// 无转发就直接过了。
					this.StreamFowardSuccess(ClientUserId, DeviceId,
							pr.getFormChannel(), "", pr.getFormIP(),
							pr.getFormChannel(), pr.getUser(),
							pr.getPassword(), pr.getPort(), pr.getDeviceType(),
							pr.getDeviceSubType(), pr.getNetLinkType(),
							pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid,
							0);
				}

				return;
			}

			String next = vt.getNextPlayCenter(centerMap);

			// 关键时刻，进行级别的控制，我们采用两种级别用 ","隔开，其中组织架构在前，个人在后。
			// System.out.println("====lev in here ============");
			// System.out.println();
			// System.out.println(userLev);

			// 记着，要路过的。
			// 这里记下路过的。
			vt.savePass(Context, userLev, ClientUserId, DeviceId, Index,
					centerMap, true, clientIP);
			IMessage message = VideoContrlDomainProxy
					.GrobalRequestPlayVideo_Copy(ClientUserId, DeviceId, Index,
							Context, NetLinkMode, userLev,
							centerMap.routeToStr(), next, clientIP);
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
					next, null, null);
		} catch (Exception e) {
			LogUtil.BusinessError("requestplayvido" + e.getMessage());
			if (e.getCause() != null) {
				LogUtil.BusinessError(e.getCause().toString());
			}
			for (StackTraceElement a : e.getStackTrace()) {
				LogUtil.BusinessError(a.toString());

			}

		}
	}

	/**
	 * Grobal进行级联转发处理
	 * 
	 * */
	@Remoting
	public void GrobalRequestPlayVideo(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer NetLinkMode,
			Integer userLev, String CenterMap, String OCenterid, String clientIP) {

		// 获得路由，顺手定位
		Route CenterRoute = Route.strToRoute(CenterMap);

		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!vt.getCenterID().equals(OCenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}
		
		/**
		 * 
		 * 特殊处理，根据来源，判断是否需要进行前置转换。
		 * 
		 * */
		 if(pvt.isPreVideoProcess(CenterRoute,vt.getCenterID(),DeviceId)) {
			 
			 PreStartPlay(ClientUserId, DeviceId, Index, Context, NetLinkMode, userLev, CenterMap, OCenterid, clientIP);
			 
			 return;
		 }
		

		// 判断当前的中心的外网点播限制。按级别进行踢人。
		Result rs = vt.getLimit(userLev);

		if (rs.getNum() >= vt.getLimitNum()) {

			if (rs.getUuid() == null) {
				// 表明没有能替换的
				String next = vt.getNextSuccessCenter(CenterRoute);

				// 逆向通知关闭点播 通知各个服务器进行删除pass记录
				IMessage message2 = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(Context, next, 0, 4,
								vt.getCenterID() + "," + vt.getLimitNum());
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message2, next, null, null);
				return;

			}

			if (rs.getStrFlag().equals("1")) {
				// 如果是点播成功了的一个图像，如何处理
				VideoRecord vr = vt.getVideoRecord(rs.getUuid());
				String next = vt.getNextSuccessCenter(vr.getCenterMap());
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(vr.getUuid(), next, 2, 5,
								vt.getCenterID() + "," + vt.getLimitNum());
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);
				// 直接调用本级模拟关闭的方案
				this.GrobalRequestStopVideo(vr.getUuid(), vt.getCenterID());

			}
			if (rs.getStrFlag().equals("2")) {
				// 如果是正在转发服务器处的图像如何处理。
				PalyRecord pr = vt.getPlayRecord(rs.getUuid());
				String next = vt.getNextSuccessCenter(pr.getCenterMap());
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(rs.getUuid(), next, 1, 5,
								vt.getCenterID() + "," + vt.getLimitNum());
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);
				// 直接模拟本级关闭的方案
				this.GrobalRequestStopVideo(rs.getUuid(), vt.getCenterID());

			}
			if (rs.getStrFlag().equals("3")) {
				// 如果是路过的一个请求，还未返回的，如何处理。
				// vt.removPassRecord(rs.getUuid());
				PassRecord pr = vt.getPassRecord(rs.getUuid());
				String next = vt.getNextSuccessCenter(pr.getRoute());
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(rs.getUuid(), next, 0, 5,
								vt.getCenterID() + "," + vt.getLimitNum());
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);
				// 直接模拟本级关闭的方案
				this.GrobalRequestStopVideo(rs.getUuid(), vt.getCenterID());
			}

		}

		// 判断是否在点播池中有该点播，如果有取得该数据，进行二次点播。
		List<VideoRecord> list = vt.getVideoRecord(DeviceId, Index);
		VideoRecord videoRecord = null;
		if (list.size() > 0) {
			videoRecord = list.get(0);
		}

		if (videoRecord != null && videoRecord.getFowardFlag().intValue() == 1) {
			// 获得数据源
			PalyRecord pr = videoRecord.getPr().getClone();
			// 进行记录
			String uuid = Context;
			pr.setFormFlag(2);
			pr.setCenterMap(CenterRoute);
			pr.setLev(userLev);
			pr.setClientId(ClientUserId);

			pr.setForwardid(videoRecord.getFowardId());

			// 提前找到未加入前的数据

			List<VideoRecord> listVr = vt.getVideoRecordFormPr(videoRecord
					.getPr().getUuid());
			List<String> listPr = vt.getPlayRecordWithUUid(videoRecord.getPr()
					.getUuid());

			// 设置点播的IP
			pr.setClientIP(clientIP);

			vt.savePlayRecord(uuid, pr);
			(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
					+ Encoding.AdsToPointProtocol(videoRecord.getFowardId()));

			VideoControl.StartStreamFoward(ClientUserId, DeviceId,
					pr.getFormChannel(), pr.getFormIP(), pr.getFormChannel(),
					pr.getUser(), pr.getPassword(), pr.getPort(),
					pr.getDeviceType(), pr.getDeviceSubType(),
					pr.getNetLinkType(), pr.getNetLinkSubType(),
					pr.getNetLinkMode(), uuid, 1);



			LogUtil.VideoInfo("**StartStreamFoward 934 |" + pr.getFormIP()
					+ " | " + pr.getFormChannel() + " | "
					+ videoRecord.getFowardId());

			/**
			 * 这里缺少一个等级提升： 判断当前记录中，最高级的为多少。
			 * 
			 * */
			int maxLev = 0;
			for (VideoRecord vr : listVr) {
				if (vr.getPr().getLev() > maxLev) {
					maxLev = vr.getPr().getLev();
				}
			}
			for (String str : listPr) {
				PalyRecord tempPr = vt.getPlayRecord(str);
				if (tempPr.getLev() > maxLev) {
					maxLev = tempPr.getLev();
				}
			}
			// 如果当前点播级别大于原级别，进行传递。
			if (pr.getLev() > maxLev) {
				this.ChangeUserLev(pr.getUuid(), pr.getRealCenterMap(),
						pr.getLev(), true);
			}

			return;
		}

		/**
		 * 看看在已回馈的视频点播中，是否有发给转发服务器请求的。
		 * */
		List<PalyRecord> list2 = vt.getPalyRecordWithDevice(DeviceId, Index);
		PalyRecord playRecord = null;
		if (list2.size() > 0) {
			playRecord = list2.get(0);
		}

		// 点播池中的记录
		if (playRecord != null) {
			// 获得数据源
			PalyRecord pr = playRecord.getClone();
			// 进行记录
			String uuid = Context;
			pr.setFormFlag(2);
			pr.setLev(userLev);// 7个9，彰显等级最高了
			pr.setCenterMap(CenterRoute);
			pr.setClientId(ClientUserId);

			pr.setForwardid(playRecord.getForwardid());

			// 设置客户端IP
			pr.setClientIP(clientIP);

			// 提前找到未加入前的数据

			List<VideoRecord> listVr = vt.getVideoRecordFormPr(playRecord
					.getUuid());
			List<String> listPr = vt
					.getPlayRecordWithUUid(playRecord.getUuid());

			vt.savePlayRecord(uuid, pr);
			(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
					+ Encoding.AdsToPointProtocol(playRecord.getForwardid()));

			VideoControl.StartStreamFoward(ClientUserId, DeviceId,
					pr.getFormChannel(), pr.getFormIP(), pr.getFormChannel(),
					pr.getUser(), pr.getPassword(), pr.getPort(),
					pr.getDeviceType(), pr.getDeviceSubType(),
					pr.getNetLinkType(), pr.getNetLinkSubType(),
					pr.getNetLinkMode(), uuid, 1);



			LogUtil.VideoInfo("**StartStreamFoward  1009 |" + pr.getFormIP()
					+ " | " + pr.getFormChannel() + " | "
					+ playRecord.getForwardid());

			/**
			 * 这里缺少一个等级提升： 判断当前记录中，最高级的为多少。
			 * 
			 * */
			int maxLev = 0;
			for (VideoRecord vr : listVr) {
				if (vr.getPr().getLev() > maxLev) {
					maxLev = vr.getPr().getLev();
				}
			}
			for (String str : listPr) {
				PalyRecord tempPr = vt.getPlayRecord(str);
				if (tempPr.getLev() > maxLev) {
					maxLev = tempPr.getLev();
				}
			}
			// 如果当前点播级别大于原级别，进行传递。
			if (pr.getLev() > maxLev) {
				this.ChangeUserLev(pr.getUuid(), pr.getRealCenterMap(),
						pr.getLev(), true);
			}
			return;
		}

		/**
		 * 这里判断一次，是否有兄台进行点播了。如果有，只需要进行一次记录即可。
		 * 
		 * */
		List<PassRecord> list3 = vt.getPassRecordWithDevice(DeviceId, Index);
		if (list3.size() > 0) {
			vt.savePass(Context, userLev, ClientUserId, DeviceId, Index,
					CenterRoute, false, clientIP);
			return;
		}


		// 判断是否为最后一个中心，如果不是，继续转发。然后停止
		// 判断当前中心与设备中心是否一致，如果一致
		if (vt.isDeviceCenterid(CenterRoute)) {

			// 获得该设备的详细信息。包括
			PalyRecord pr = vt.getDeviceInfo(DeviceId);

			if (pr == null) {
				// 逆向通知关闭吧。
				String next = vt.getNextSuccessCenter(CenterRoute);
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(Context, next, 0, 0,
								"设备不存在");
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);
				return;

			}

			/***
			 * 
			 * 进行rtsp标准保存
			 * */

			String rtspurl = DBConnNvmp.getDBConn().getRtspValue(DeviceId,
					null, -1, Index);
			if (rtspurl != null) {
				pr.setFormIP(rtspurl);
				pr.setDeviceType(300);
				pr.setDeviceSubType(0);
				pr.setNetLinkType(300);
				pr.setNetLinkSubType(0);
			}



			if (pr.getState().intValue() == 0) {
				// 逆向通知关闭吧。
				String next = vt.getNextSuccessCenter(CenterRoute);
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(Context, next, 1, 0,
								"设备未上线");
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);
				return;
			}

			pr.setChannel(Index);

			// 进行转发点播：
			VideoServerInfo vs = FindVideoServer(DeviceId, Index);
			pr.setChannel(Index);
			// pr.setNetLinkType(pr.getDeviceType());
			pr.setFormChannel(pr.getChannel());
			pr.setNetLinkMode(NetLinkMode);
			pr.setFormFlag(0);
			pr.setLev(userLev);

			// 进行记录
			String uuid = Context;
			pr.setUuid(uuid);
			pr.setClientId(ClientUserId);
			pr.setCenterMap(CenterRoute);
			pr.setRealCenterMap(CenterRoute);
			pr.setClientIP(clientIP);

			vt.savePlayRecord(uuid, pr);

			if (vs != null
					&& !this.noFrawdDeviceSet.contains(pr.getDeviceType()
							.toString())) {
				pr.setForwardid(vs.getDeviceId());
				LogUtil.BusinessDebug("==to Grobal FowardServer =="
						+ vs.getDeviceId());

				(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
						+ Encoding.AdsToPointProtocol(vs.getDeviceId()));

				VideoControl.StartStreamFoward(ClientUserId, DeviceId,
						pr.getFormChannel(), pr.getFormIP(),
						pr.getFormChannel(), pr.getUser(), pr.getPassword(),
						pr.getPort(), pr.getDeviceType(),
						pr.getDeviceSubType(), pr.getNetLinkType(),
						pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid, 0);


				LogUtil.VideoInfo("**StartStreamFoward 1121 |" + pr.getFormIP()
						+ " | " + pr.getFormChannel() + " | "
						+ vs.getDeviceId());

			} else {

				// 无转发就直接过了。
				this.StreamFowardSuccess(ClientUserId, DeviceId,
						pr.getFormChannel(), "", pr.getFormIP(),
						pr.getFormChannel(), pr.getUser(), pr.getPassword(),
						pr.getPort(), pr.getDeviceType(),
						pr.getDeviceSubType(), pr.getNetLinkType(),
						pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid, 0);
			}

			return;
		}

		// 否者继续转发。

		String next = vt.getNextPlayCenter(CenterRoute);
		// String nosend = vt.getNextSuccessCenter(CenterRoute);
		String nosend = null;
		// System.out.println("CenterRoute:" + CenterRoute.getRouteDesc());
		// System.out.println("next:" + next);
		// 这里记下路过的。
		vt.savePass(Context, userLev, ClientUserId, DeviceId, Index,
				CenterRoute, true, clientIP);

		IMessage message = VideoContrlDomainProxy.GrobalRequestPlayVideo_Copy(
				ClientUserId, DeviceId, Index, Context, NetLinkMode, userLev,
				CenterMap, next, clientIP);
		(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
				next, null, nosend);
	}

	/**
	 * Grobal进行级联转发成功后回波处理 其中 Context 包含 uuid
	 * */
	@Remoting
	public void GrobalRequestPlayVideoSuccess(String ClientId, String DeviceId,
			Integer CameraIndex, String Centerid, String FowardId,
			Integer flag, String VideoServerIP, Integer VideoServerChannel,
			String user, String Password, Integer Port, Integer DeviceType, // 设备厂商
			Integer DeviceSubType,// 设备型号
			Integer NetLinkType, // 网络连接类型
			Integer NetLinkSubType,// 网络连接子类型
			Integer NetLinkMode,// 网络连接模式
			String Context, String CenterMap, String oCenterid) {

		Route CenterRoute = Route.strToRoute(CenterMap);
		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!vt.getCenterID().equals(oCenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		// 如果在，是否已经点播发送过了，也就是uuid已经存在于本中心了。重复发送，停止。
		if (vt.isSuccessVideo(Context)) {
			return;
		}

		/**
		 * 如果是没有pass的痕迹,表明已经提前进行删除了。 那么就无需做任何操作，直接停止。
		 * */

		PassRecord passr = vt.getPassRecord(Context);
		if (passr == null) {
			return;
		}

		// 寻找在本级中，有相关点播的数量。

		List<PassRecord> passrList = vt.getPassRecordWithDevice(
				passr.getDeviceId(), passr.getCameraIndex());

		// 进行转发抓取，然后进行判断，是进行继续级联转发还是通知用户。
		CameraIndex = passr.getCameraIndex();

		VideoServerInfo vs = FindVideoServer(DeviceId, CameraIndex);

		if (passrList != null) {
			int maxLev = 0;
			for (PassRecord passrtemp : passrList) {
				if (passrtemp.getLev() > maxLev) {
					maxLev = passrtemp.getLev();
				}

				// 清空标记
				vt.removPassRecord(passrtemp.getUuid());

				PalyRecord pr = new PalyRecord(DeviceId, VideoServerIP, Port,
						user, Password, 1, DeviceType, DeviceSubType,
						NetLinkType, NetLinkSubType, NetLinkMode);

				pr.setChannel(CameraIndex);
				// pr.setNetLinkType(NetLinkType);
				pr.setFormChannel(VideoServerChannel);
				pr.setNetLinkMode(NetLinkMode);
				pr.setFormFlag(flag);
				// 进行记录
				String uuid = passrtemp.getUuid();
				pr.setCenterMap(passrtemp.getRoute());
				pr.setRealCenterMap(CenterRoute);
				// 这个是来源ID
				pr.setUuid(Context);
				pr.setClientId(passrtemp.getClientId());
				pr.setLev(passrtemp.getLev());

				// 保存IP
				pr.setClientIP(passrtemp.getClientIP());

				vt.savePlayRecord(uuid, pr);
				if (vs != null
						&& DBConnNvmp.getDBConn().isRouteCtrl()
						&& !this.noFrawdDeviceSet.contains(pr.getDeviceType()
								.toString())) {

					// System.out.println("==========================   "+noFrawdDeviceSet.size());
					// System.out.println("==========================   "+pr.getDeviceType());
					pr.setForwardid(vs.getDeviceId());

					LogUtil.BusinessDebug("==to FowardServer =="
							+ vs.getDeviceId());

					ClientId = pr.getClientId();

					(VideoControlDomain.AppRunTime()).SetCurChannel("Local://"
							+ Encoding.AdsToPointProtocol(vs.getDeviceId()));

					VideoControl.StartStreamFoward(ClientId, DeviceId,
							pr.getChannel(), pr.getFormIP(),
							pr.getFormChannel(), pr.getUser(),
							pr.getPassword(), pr.getPort(), pr.getDeviceType(),
							pr.getDeviceSubType(), pr.getNetLinkType(),
							pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid,
							flag);


					LogUtil.VideoInfo("**StartStreamFoward 1176 |"
							+ pr.getFormIP() + " | " + pr.getFormChannel()
							+ " | " + vs.getDeviceId());

				} else {
					// 无转发就直接过了。
					ClientId = pr.getClientId();

					// 增加前置操作数量
					// pvt.addPlay(DeviceId, pr.getChannel());

					this.StreamFowardSuccess(ClientId, DeviceId,
							pr.getChannel(), "", pr.getFormIP(),
							pr.getFormChannel(), pr.getUser(),
							pr.getPassword(), pr.getPort(), pr.getDeviceType(),
							pr.getDeviceSubType(), pr.getNetLinkType(),
							pr.getNetLinkSubType(), pr.getNetLinkMode(), uuid,
							0);
				}
			}

			if (passr.getLev() < maxLev) {
				// 传递过去，进行升级
				ChangeUserLev(passr.getUuid(), CenterRoute, maxLev, true);
			}

		}
	}

	// 转发服务器失败，告诉偶们可怜的客户端
	@Remoting
	public void StreamFowardError(String ClientId, String DeviceId,
			Integer CameraIndex, String Reason, String Context) {

		LogUtil.BusinessError("error" + ClientId + "|DeviceId " + DeviceId);

		// 删除该记录
		PalyRecord pr = vt.getPlayRecord(Context);
		if (pr == null) {
			return;
		}
		vt.removePlayRecord(Context);

		// 首先判断是本地的还是要传递的，
		if (vt.isClientCenterid(pr.getCenterMap())) {
			// 通知客户端，关闭点播吧
			// 获取原来的上下文环境。
			String objStr = vt.getContext(Context);
			// 删除原来的这个上下文。
			vt.removeContext(Context);
			(VideoControlDomain.AppRunTime())
					.SetCurChannel("Local://Session://" + pr.getClientId());
			VideoControl.OnPlayFailed(pr.getDevid(), pr.getChannel(), 3,
					objStr, "转发服务器故障");

		} else {
			// 进行逆推吧。
			// 如果是路过的，通知上一级继续进行关闭
			String next = vt.getNextSuccessCenter(pr.getCenterMap());

			// 逆向通知关闭点播 通知各个服务器进行删除pass记录

			IMessage message2 = VideoContrlDomainProxy
					.GrobalBackwardStopVideo_Copy(Context, next, 1, 3,
							"转发服务器故障");
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message2,
					next, null, null);

		}

		// 开始往下发送停止，本级的那个就不要了。
		// 如果本级不是终点，那么也要往下发展。

		if (!vt.isDeviceCenterid(pr.getCenterMap())) {
			// 本级不能停，直接通知下一级

			// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
			List<VideoRecord> listVr = vt.getVideoRecordFormPr(pr.getUuid());
			List<String> listPr = vt.getPlayRecordWithUUid(pr.getUuid());
			if (listVr.size() + listPr.size() == 0) {
				// 可以通知下一个中心可以进行关闭。
				if (!vt.isDeviceCenterid(pr.getCenterMap())) {
					String next = vt.getNextPlayCenter(pr.getCenterMap());
					// by zzw 10.08 修正，向下进行关闭，需要采用pr的uuid

					// IMessage message2 = VideoContrlDomainProxy
					// .GrobalRequestStopVideo_Copy(Context, next);
					IMessage message2 = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(pr.getUuid(), next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);
				}
			}

		}

	}

	// 全局发送关于点播失败处理
	@Remoting
	public void GrobalStreamFowardError(String ClientId, String DeviceId,
			Integer CameraIndex, String Reason, String Context,
			String OCenterid, Integer type) {

		// 判断是否到达，然后告诉我们可爱的客户端，同时删除数据。
		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!vt.getCenterID().equals(OCenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}
		try {
			String Context2 = vt.getContext(Context);

			vt.removeContext(Context);

			(VideoControlDomain.AppRunTime())
					.SetCurChannel("Local://Session://" + ClientId);
			VideoControl.OnPlayFailed(DeviceId, CameraIndex, type, Context2,
					"转接服务器连接失败!");

		} catch (Exception e) {
			System.out.println("注意：楼下问题已经解决");
			e.printStackTrace();
		}
	}

	// 请求连接成功，转发服务器回调，让客服端连接转发服务器。 flag = 1
	// 或者是连接设备成功，将设备相关信息直接发给客户端，flag = 0
	@Remoting
	public void StreamFowardSuccess(String ClientId, String DeviceId,
			Integer CameraIndex, String FowardId, String VideoServerIP,
			Integer VideoServerChannel, String user, String Password,
			Integer Port, Integer DeviceType, // 设备厂商
			Integer DeviceSubType,// 设备型号
			Integer NetLinkType, // 网络连接类型
			Integer NetLinkSubType,// 网络连接子类型
			Integer NetLinkMode,// 网络连接模式
			String Context, Integer flag) {

		// 现在认为点播一定在点播池中存在，如果没有，就进行判断，如果是转发的，提醒转发进行关闭
		PalyRecord pr = vt.getPlayRecord(Context);

		if (pr == null) {

			if (flag == 3) {

				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://" + ClientId);
				VideoControl.OnControlDisplayVideo(DeviceId, CameraIndex,
						VideoServerIP, VideoServerChannel, user, Password,
						Port, DeviceType, DeviceSubType, NetLinkType,
						NetLinkSubType, NetLinkMode, Context, flag);

			}

			return;
		}

		// 根据路由信息确定，下一步发送给谁，如果是本中心，发送给对应客户端。
		if (vt.isClientCenterid(pr.getCenterMap())) {

			// 获取原来的上下文环境
			String Context2 = vt.getContext(Context);

			(VideoControlDomain.AppRunTime())
					.SetCurChannel("Local://Session://" + ClientId);
			VideoControl.OnControlDisplayVideo(pr.getDevid(), pr.getChannel(),
					VideoServerIP, VideoServerChannel, user, Password, Port,
					DeviceType, DeviceSubType, NetLinkType, NetLinkSubType,
					NetLinkMode, Context2, flag);

			// 在本地保存这次点播成功的详细信息。
			vt.saveVideoRecord(Context, ClientId, 0, ClientId, flag, FowardId);

			return;

		} else {
			// vt.removePlayRecord(Context);
			// 如果是非本中心，则开始通过全局转发。
			String next = vt.getNextSuccessCenter(pr.getCenterMap());

			/**
			 * 这里做一个IP转换，将IP转换成映射表对应IP 如果无映射，则改为本IP
			 * */
			VideoServerIP = StreamIpMaps.getVIP(VideoServerIP);
			IMessage message = VideoContrlDomainProxy
					.GrobalRequestPlayVideoSuccess_Copy(ClientId, DeviceId,
							CameraIndex, vt.getCenterID(), FowardId, flag,
							VideoServerIP, VideoServerChannel, user, Password,
							Port, DeviceType, DeviceSubType, NetLinkType,
							NetLinkSubType, NetLinkMode, Context, pr
									.getCenterMap().routeToStr(), next);
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
					next, null, null);
			vt.saveVideoRecord(Context, ClientId, 1, next, flag, FowardId);

			/**
			 * 需要考虑到等级传递问题。 不管进，只管出。所以直接询告诉下一个。
			 * 
			 * */

			return;

		}

	}

	// 转发服务器上报自己上线
	@Remoting
	public void FowardServerLogin(String FowardServerId, String FowardServerIP,
			String user, String Password, Integer Port, Integer ChannelNums) {
		// 记录在案
		// LogUtil.error("FowardServerLogin id:  |"+FowardServerId+"|  "+FowardServerId.length());
		// for(byte b :FowardServerId.getBytes()){
		// LogUtil.error((int)b);
		// }

		VideoServerInfo vsi = new VideoServerInfo(FowardServerId);
		vsi.setDeviceId(FowardServerId);
		vsi.setNetPort(Port);
		vsi.setPassWord(Password);
		vsi.setSourceIP(FowardServerIP);
		vsi.setUserName(user);
		vsi.setChannelNums(ChannelNums);
		vsi.setCount(0);

		if (VideoServers.contains(vsi)) {
			// VideoServers.remove(vsi);
			FowardServerlogout(FowardServerId, FowardServerIP);
		}
		LogUtil.BusinessInfo("foward server login :" + FowardServerIP + " ID:"
				+ FowardServerId);

		VideoServers.add(vsi);

	}

	// 转发服务器 logout
	@Remoting
	public void FowardServerlogout(String FowardServerId, String FowardServerIP) {

		LogUtil.BusinessError("FowardServerlogout  :" + FowardServerId);

		// 转发服务器下线
		VideoServerInfo vsi = new VideoServerInfo(FowardServerId);
		VideoServers.remove(vsi);

		// 找到所有点播相关的记录
		List<String> listPr = vt.getPlayRecordWithForward(FowardServerId);

		for (String tempStr : listPr) {
			PalyRecord playRecord = vt.getPlayRecord(tempStr);
			// 删除记录
			vt.removePlayRecord(tempStr);

			// 判断是否是当前中心的点播
			if (vt.isClientCenterid(playRecord.getCenterMap())) {
				// 通知当前客户端，关闭
				// 获取原来的上下文环境。
				String objStr = vt.getContext(tempStr);
				// 删除原来的这个上下文。
				vt.removeContext(tempStr);
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ playRecord.getClientId());
				VideoControl.OnPlayFailed(playRecord.getDevid(),
						playRecord.getChannel(), 3, objStr, "转发服务器故障");

			} else {
				// 进行逆推吧。
				// 如果是路过的，通知上一级继续进行关闭
				String next = vt
						.getNextSuccessCenter(playRecord.getCenterMap());

				// 逆向通知关闭点播 通知各个服务器进行删除pass记录
				IMessage message2 = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(tempStr, next, 1, 3,
								"转发服务器故障");
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message2, next, null, null);

			}

			// 判断是否为终点，否则通知下级

			if (!vt.isDeviceCenterid(playRecord.getCenterMap())) {
				// 本级不能停，直接通知下一级

				String next = vt.getNextPlayCenter(playRecord
						.getRealCenterMap());
				IMessage message2 = VideoContrlDomainProxy
						.GrobalRequestStopVideo_Copy(playRecord.getUuid(), next);
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message2, next, null, null);

			}

		}

		// 找到所有成功点播相关记录

		List<VideoRecord> listVr = vt.getVideoRecordWithForward(FowardServerId);

		for (VideoRecord vr : listVr) {

			vt.removeVideoRecord(vr.getUuid());

			// 判断是否是当前中心的点播
			if (vt.isClientCenterid(vr.getCenterMap())) {
				// 通知当前客户端，关闭
				// 获取原来的上下文环境。
				String objStr = vt.getContext(vr.getUuid());
				// 删除原来的这个上下文。
				vt.removeContext(vr.getUuid());
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ vr.getPr().getClientId());
				VideoControl.OnPlayFailed(vr.getPr().getDevid(), vr.getPr()
						.getChannel(), 3, objStr, "转发服务器故障");

			} else {
				// 进行逆推吧。
				// 如果是路过的，通知上一级继续进行关闭
				String next = vt.getNextSuccessCenter(vr.getCenterMap());

				// 逆向通知关闭点播 通知各个服务器进行删除pass记录
				IMessage message2 = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(vr.getUuid(), next, 2, 3,
								"转发服务器故障");
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message2, next, null, null);

			}

			// 判断是否为终点，否则通知下级

			if (!vt.isDeviceCenterid(vr.getCenterMap())) {
				// 本级不能停，直接通知下一级

				String next = vt.getNextPlayCenter(vr.getPr()
						.getRealCenterMap());
				IMessage message2 = VideoContrlDomainProxy
						.GrobalRequestStopVideo_Copy(vr.getPr().getUuid(), next);
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message2, next, null, null);

			}

		}

	}

	/**
	 * 用来寻找中转服务器的方案 ,每次点播成功，我们计数将该转发服务器计数+1，每次选择最小得转发服务器。
	 * 
	 * */
	private VideoServerInfo FindVideoServer(String DeviceId, Integer Index) {
		VideoServerInfo vs = null;
		int n = -99;
		int m = 0;
		LogUtil.VideoInfo("find video Servers "+(VideoServers!=null?VideoServers.size():0));
		
		for (VideoServerInfo item : VideoServers) {
			m = vt.getVideoRecordWithForward(item.getDeviceId()).size();
			// LogUtil.BusinessInfo("find foward server :" + item.getSourceIP()
			// + " num:"+m);

			if (n == -99) {
				if (m < item.getChannelNums()) {
					vs = item;
					n = m;
				}
			} else {

				if (m < n && m < item.getChannelNums()) {
					vs = item;
					n = m;
				}

			}
		}
		
		LogUtil.VideoInfo("find video Servers result: "+ (vs!=null?vs.getDeviceId():"no"));
		return vs;
	}

	// 设置设备上线或下线..这个是设备管理服务器管理的那种设备。
	@Remoting
	public void SetEncodeDeviceOnline(String TerminalId, String TerminalIP,
			Boolean IsOnline) {

		if (!IsOnline) {
			PreDeviceDrop(TerminalId);
			
			

			// 先获得，通知后再删除
			List<String> listpr = vt.getPlayRecordWithDevice(TerminalId);
			for (String prStr : listpr) {
				PalyRecord pr = vt.getPlayRecord(prStr);

				/**
				 * 通知上级进行关闭
				 * */

				if (!vt.isClientCenterid(pr.getCenterMap())) {
					String next = vt.getNextSuccessCenter(pr.getCenterMap());
					// 逆向通知关闭点播
					IMessage message = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(prStr, next, 1, 0,
									vt.getCenterID() + "," + "设备下线");
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);

				}
				vt.removePlayRecord(prStr);

				if (pr.getForwardflag() == 1) {
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getForwardid());
					VideoControl.StopStreamFoward(pr.getClientId(),
							pr.getDevid(), pr.getFormIP(), pr.getFormChannel(),
							pr.getForwardid(), pr.getFormChannel(), -1, "设备下线");

					// 关闭前置。
					stopPreFoward(pr.getDevid(), pr.getChannel(), -1, null);

					LogUtil.VideoInfo("$$StopStreamFoward 1  1649 |"
							+ pr.getFormIP() + " | " + pr.getFormChannel()
							+ " | " + pr.getForwardid());

				}

				if (vt.isClientCenterid(pr.getCenterMap())) {

					String context = vt.getContext(prStr);
					vt.removeContext(prStr);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getClientId());
					VideoControl.OnPlayFailed(pr.getDevid(), pr.getChannel(),
							1, context, "设备下线");

				}

			}

			// 获取本地相关点播
			List<VideoRecord> list = vt.getVideoRecordWithDevice(TerminalId);

			for (VideoRecord vr : list) {
				/**
				 * 通知上级进行关闭
				 * */
				if (!vt.isClientCenterid(vr.getCenterMap())) {
					String next = vt.getNextSuccessCenter(vr.getCenterMap());
					// 逆向通知关闭点播
					IMessage message = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(vr.getUuid(), next,
									2, 0, vt.getCenterID() + "," + "设备下线");
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);
				}

				// 删除记录
				vt.removeVideoRecordwithDevice(TerminalId);
				// 对于凡是通过转发服务器转发的，进行关闭通知。
				if (vr.getFowardFlag() == 1) {
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ vr.getFowardId());
					VideoControl.StopStreamFoward(vr.getClientId(), vr
							.getDeviceId(), vr.getPr().getFormIP(), vr.getPr()
							.getFormChannel(), vr.getFowardId(), vr.getPr()
							.getFormChannel(), -1, "设备下线");

					// 关闭前置。
					stopPreFoward(vr.getPr().getDevid(), vr.getPr()
							.getChannel(), -1, null);

					LogUtil.VideoInfo("$$StopStreamFoward -1 1700 |"
							+ vr.getPr().getFormIP() + " | "
							+ vr.getPr().getFormChannel() + " | "
							+ vr.getFowardId());

				}
				// 如果是本机点播请求的，通知下客户端，关闭之。
				if (vt.isClientCenterid(vr.getCenterMap())) {
					String context = vt.getContext(vr.getUuid());
					vt.removeContext(vr.getUuid());
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ vr.getClientId());
					VideoControl.OnPlayFailed(vr.getDeviceId(),
							vr.getCameraIndex(), 1, context, "设备下线");
				}

			}

			// 获得所有的点播痕迹
			List<PassRecord> passRecordList = vt
					.getPassRecordWithDevice(TerminalId);

			for (PassRecord pr : passRecordList) {

				/**
				 * 通知上级进行关闭
				 * */
				String next = vt.getNextSuccessCenter(pr.getRoute());
				// 逆向通知关闭点播
				IMessage message = VideoContrlDomainProxy
						.GrobalBackwardStopVideo_Copy(pr.getUuid(), next, 0, 0,
								vt.getCenterID() + "," + "设备下线");
				(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
						message, next, null, null);

				// 删除痕迹
				vt.removPassRecord(pr.getUuid());
			}

			// 删除本级原始点播与该设备相关的
			vt.removeContextWith(TerminalId);



			// // 删除相关的过程。
			// vt.removPassRecordWithDevice(TerminalId);

		}
	}

	// 请求客服端下线。
	@Remoting
	public void SetClientOnLine(String ClientId, Boolean isOnline) {
		if (isOnline)
			return;

		// LogUtil.error("videoControl |" + ClientId + "|  " + isOnline+ "  "+
		// ClientId.length());
		//
		// for(VideoServerInfo vi : VideoServers) {
		//
		// LogUtil.error("VideoServerInfo |" + vi.getDeviceId() + "|  " +
		// isOnline +"  "+ vi.getDeviceId().length());
		// }

		// 如果是转发服务器，调用楼下的方法。
		if (VideoServers.contains(new VideoServerInfo(ClientId))) {
			this.FowardServerlogout(ClientId, null);
			return;
		}

		/***
		 * by 2ps 
		 * 如果是前置服务器，则进行转发操作。 
		 * */
		if(ClientId.equals(pvt.getPreVideoServerID())) {
			pvt.setPreServerID(null);
			return;
		}
		

		// 上线的不管。
		if (!isOnline) {
			
			PreClientDrop(ClientId);
			
			// 获取本地相关点播
			/**
			 * 修改方案 2012-03-02 客户端下线，判断本地发起的有多少个，发起停止的指令
			 * 
			 * 然后增加中心下线操作。
			 * 
			 * 
			 * */

			// 根据记录，查询发起的原始点播
			List<String> list = vt.getUuidFromClient(ClientId);

			/**
			 * allStr的格式为 client + "$" + context + "$" + deviceid + "$" +
			 * channel.intValue()
			 * 
			 * 拆分为原始请求，模拟关闭
			 * */
			for (String allStr : list) {
				System.out.println(allStr);
				int n = allStr.indexOf("$");
				String client = allStr.substring(0, n);
				allStr = allStr.substring(n + 1);
				n = allStr.indexOf("$");
				String context = allStr.substring(0, n);
				allStr = allStr.substring(n + 1);
				n = allStr.indexOf("$");
				String deviceid = allStr.substring(0, n);
				allStr = allStr.substring(n + 1);
				Integer channel = Integer.parseInt(allStr);
				this.RequestStopVideo(client, deviceid, channel, context);

			}

		}

	}

	// 客户端请求停止点播
	@Remoting
	public final void RequestStopVideo(String ClientUserId, String DeviceId,
			Integer Index, String Context) {
		// 按照目前设计，关闭是唯一的。
		/***
		 * 想关闭，首先要进行获得uuid，如果未获得，可认为已经逆向关闭
		 * 
		 * */
		String uuid = vt.getUuidFromContext(ClientUserId, DeviceId, Index,
				Context);

		if (uuid == null) {
			LogUtil.BusinessError("********RequestStop fail");
			return;
		}

		// 删除点播原始记录
		vt.removeContext(uuid);

		// 判断痕迹中是否存在该点播请求
		PassRecord passRecord = vt.getPassRecord(uuid);
		if (passRecord != null) {

			vt.removPassRecord(uuid);
			/*
			 * 在痕迹中，那么进行一次点播关闭.其中，如果是等待的痕迹，就进行删除， 如果是发送的，则需要传递关闭. 同时进行再次发送。
			 */
			if (passRecord.isSendFlag()) {
				// 发送的痕迹
				if (!vt.isDeviceCenterid(passRecord.getRoute())) {
					String next = vt.getNextPlayCenter(passRecord.getRoute());
					IMessage message2 = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(uuid, next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);
				}
				// 开始再次发送请求
				AgainPassPlay(DeviceId, Index);
			}

		}

		// 判断点播中有该记录
		PalyRecord palyRecord = vt.getPlayRecord(uuid);
		if (palyRecord != null) {
			// 删除该记录
			vt.removePlayRecord(uuid);

			// 通知转发服务器进行一次关闭
			if (palyRecord.getForwardflag() == 1) {
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ palyRecord.getForwardid());
				VideoControl.StopStreamFoward(ClientUserId, DeviceId,
						palyRecord.getFormIP(), palyRecord.getFormChannel(),
						palyRecord.getForwardid(), palyRecord.getFormChannel(),
						1, "客户端 请求停止!");

				// // 关闭前置。
				// stopPreFoward(DeviceId, Index, 1, uuid);

				LogUtil.VideoInfo("$$StopStreamFoward 1 1772 |"
						+ palyRecord.getFormIP() + " | "
						+ palyRecord.getFormChannel() + " | "
						+ palyRecord.getForwardid());

			}

			// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
			List<VideoRecord> listVr = vt.getVideoRecordFormPr(palyRecord
					.getUuid());
			List<String> listPr = vt
					.getPlayRecordWithUUid(palyRecord.getUuid());
			if (listVr.size() + listPr.size() == 0) {
				// 可以通知下一个中心可以进行关闭。
				if (!vt.isDeviceCenterid(palyRecord.getRealCenterMap())) {
					String next = vt.getNextPlayCenter(palyRecord
							.getRealCenterMap());
					IMessage message = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(palyRecord.getUuid(),
									next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);
				}
			} else {
				/**
				 * 进行降级别判断
				 * */
				// 找到当前最高的级别。

				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord pr = vt.getPlayRecord(str);
					if (pr.getLev() > maxLev) {
						maxLev = pr.getLev();
					}
				}

				// 如果最高级别小于关闭的级别，通知降低级别。
				if (maxLev < palyRecord.getLev()) {
					ChangeUserLev(palyRecord.getUuid(),
							palyRecord.getRealCenterMap(), maxLev, false);
				}

			}

		}

		// 判断成功返回给客户端的点播中有该记录

		VideoRecord videoRecord = vt.getVideoRecord(uuid);
		if (videoRecord != null) {
			vt.removeVideoRecord(uuid);

			if (videoRecord.getFowardFlag() == 1) {
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ videoRecord.getFowardId().trim());

				VideoControl.StopStreamFoward(ClientUserId, DeviceId,
						videoRecord.getPr().getFormIP(), videoRecord.getPr()
								.getFormChannel(), videoRecord.getFowardId(),
						videoRecord.getPr().getFormChannel(), 1, "客户端 请求停止!");

				// // 关闭前置。
				// stopPreFoward(DeviceId, Index, 1, uuid);
				// LogUtil.VideoInfo("$$StopStreamFoward 1 1922  fowardid |"+
				// videoRecord.getFowardId().trim()+"| "+
				// videoRecord.getFowardId().trim().length());
				LogUtil.VideoInfo("$$StopStreamFoward 1 1922 |"
						+ videoRecord.getPr().getFormIP() + " | "
						+ videoRecord.getPr().getFormChannel() + " | "
						+ videoRecord.getFowardId());

			}

			// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
			List<VideoRecord> listVr = vt.getVideoRecordFormPr(videoRecord
					.getPr().getUuid());
			List<String> listPr = vt.getPlayRecordWithUUid(videoRecord.getPr()
					.getUuid());
			if (listVr.size() + listPr.size() == 0) {
				// 可以通知下一个中心可以进行关闭。
				if (!vt.isDeviceCenterid(videoRecord.getPr().getRealCenterMap())) {
					String next = vt.getNextPlayCenter(videoRecord.getPr()
							.getRealCenterMap());
					IMessage message = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(videoRecord.getPr()
									.getUuid(), next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);
				}
			} else {

				/**
				 * 进行降级别判断
				 * */
				// 找到当前最高的级别。

				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord pr = vt.getPlayRecord(str);
					if (pr.getLev() > maxLev) {
						maxLev = pr.getLev();
					}
				}

				// 如果最高级别小于关闭的级别，通知降低级别。
				if (maxLev < videoRecord.getPr().getLev()) {
					ChangeUserLev(videoRecord.getPr().getUuid(), videoRecord
							.getPr().getRealCenterMap(), maxLev, false);
				}

			}

		}

		// 关闭前置。
		stopPreFoward(DeviceId, Index, 1, uuid);

	}

	@Remoting
	public void GrobalRequestStopVideo(String uuid, String ocenterid) {

		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!vt.getCenterID().equals(ocenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		
		/***
		 * 判断前置点播中，是否存在该点播。
		 * */
		PreVideo pv;
		if(( pv =  pvt.getPreVideoByContext(uuid))!=null) {
			LogUtil.TestInfo("prevideo close "+pv.getClientUserId()+"  "+pv.getDeviceID()+" "+pv.getChannel()+ " context"+uuid);
			PreStopPlay(pv);
			return;
		 }
		
		
		
		
		
		// 停止包括3种

		// 判断痕迹中是否存在该点播请求
		PassRecord passRecord = vt.getPassRecord(uuid);
		if (passRecord != null) {

			vt.removPassRecord(uuid);
			/*
			 * 在痕迹中，那么进行一次点播关闭.其中，如果是等待的痕迹，就进行删除， 如果是发送的，则需要传递关闭. 同时进行再次发送。
			 */
			if (passRecord.isSendFlag()) {
				// 发送的痕迹
				if (!vt.isDeviceCenterid(passRecord.getRoute())) {
					String next = vt.getNextPlayCenter(passRecord.getRoute());
					IMessage message2 = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(uuid, next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);
				}
				// 开始再次发送请求
				AgainPassPlay(passRecord.getClientId(),
						passRecord.getCameraIndex());
			}

		}

		// 判断点播中有该记录
		PalyRecord palyRecord = vt.getPlayRecord(uuid);
		if (palyRecord != null) {
			// 删除该记录
			vt.removePlayRecord(uuid);

			// 通知转发服务器进行一次关闭
			if (palyRecord.getForwardflag() == 1) {
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ palyRecord.getForwardid());
				VideoControl.StopStreamFoward(palyRecord.getClientId(),
						palyRecord.getDevid(), palyRecord.getFormIP(),
						palyRecord.getFormChannel(), palyRecord.getForwardid(),
						palyRecord.getFormChannel(), 1, "客户端 请求停止!");

				// 关闭前置。
				stopPreFoward(palyRecord.getDevid(), palyRecord.getChannel(),
						1, uuid);

				LogUtil.VideoInfo("$$StopStreamFoward 1 2033 |"
						+ palyRecord.getFormIP() + " | "
						+ palyRecord.getFormChannel() + " | "
						+ palyRecord.getForwardid());

			}

			// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
			List<VideoRecord> listVr = vt.getVideoRecordFormPr(palyRecord
					.getUuid());
			List<String> listPr = vt
					.getPlayRecordWithUUid(palyRecord.getUuid());
			if (listVr.size() + listPr.size() == 0) {
				// 可以通知下一个中心可以进行关闭。
				if (!vt.isDeviceCenterid(palyRecord.getRealCenterMap())) {
					String next = vt.getNextPlayCenter(palyRecord
							.getRealCenterMap());
					IMessage message = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(palyRecord.getUuid(),
									next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);
				}
			} else {
				/**
				 * 进行降级别判断
				 * */
				// 找到当前最高的级别。

				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord pr = vt.getPlayRecord(str);
					if (pr.getLev() > maxLev) {
						maxLev = pr.getLev();
					}
				}

				// 如果最高级别小于关闭的级别，通知降低级别。
				if (maxLev < palyRecord.getLev()) {
					ChangeUserLev(palyRecord.getUuid(),
							palyRecord.getRealCenterMap(), maxLev, false);
				}

			}

		}

		// 判断成功返回给客户端的点播中有该记录

		VideoRecord videoRecord = vt.getVideoRecord(uuid);
		if (videoRecord != null) {
			vt.removeVideoRecord(uuid);

			if (videoRecord.getFowardFlag() == 1) {
				(VideoControlDomain.AppRunTime())
						.SetCurChannel("Local://Session://"
								+ videoRecord.getFowardId());
				VideoControl.StopStreamFoward(videoRecord.getClientId(),
						videoRecord.getDeviceId(), videoRecord.getPr()
								.getFormIP(), videoRecord.getPr()
								.getFormChannel(), videoRecord.getFowardId(),
						videoRecord.getPr().getFormChannel(), 1, "客户端 请求停止!");

				// 关闭前置。
				stopPreFoward(videoRecord.getPr().getDevid(), videoRecord
						.getPr().getChannel(), 1, uuid);

				LogUtil.VideoInfo("$$StopStreamFoward 1 2104 |"
						+ videoRecord.getPr().getFormIP() + " | "
						+ videoRecord.getPr().getFormChannel() + " | "
						+ videoRecord.getFowardId());

			}

			// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
			List<VideoRecord> listVr = vt.getVideoRecordFormPr(videoRecord
					.getPr().getUuid());
			List<String> listPr = vt.getPlayRecordWithUUid(videoRecord.getPr()
					.getUuid());
			if (listVr.size() + listPr.size() == 0) {
				// 可以通知下一个中心可以进行关闭。
				if (!vt.isDeviceCenterid(videoRecord.getPr().getRealCenterMap())) {
					String next = vt.getNextPlayCenter(videoRecord.getPr()
							.getRealCenterMap());
					IMessage message = VideoContrlDomainProxy
							.GrobalRequestStopVideo_Copy(videoRecord.getPr()
									.getUuid(), next);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message, next, null, null);
				}
			} else {

				/**
				 * 进行降级别判断
				 * */
				// 找到当前最高的级别。

				int maxLev = 0;
				for (VideoRecord vr : listVr) {
					if (vr.getPr().getLev() > maxLev) {
						maxLev = vr.getPr().getLev();
					}
				}
				for (String str : listPr) {
					PalyRecord pr = vt.getPlayRecord(str);
					if (pr.getLev() > maxLev) {
						maxLev = pr.getLev();
					}
				}

				// 如果最高级别小于关闭的级别，通知降低级别。
				if (maxLev < videoRecord.getPr().getLev()) {
					ChangeUserLev(videoRecord.getPr().getUuid(), videoRecord
							.getPr().getRealCenterMap(), maxLev, false);
				}

			}

		}

	}

	/**
	 * 
	 * 逆向关闭：
	 * 
	 * @param uuid
	 *            唯一标示
	 * @param ocenterid
	 *            目标对象
	 * @param state
	 *            上一级过来的时候，处于何种状态 0 处于 ps 状态，也就是点播痕迹 1
	 *            处于pr状态，也就是点播发起给转发服务器了，但未从转发服务器给客户端 2 处于vr状态，也就是发给客户端了的。
	 * @param errflag
	 *            异常标示
	 * @param errStr
	 *            异常原因
	 * 
	 * */
	@Remoting
	public void GrobalBackwardStopVideo(String uuid, String ocenterid,
			Integer state, Integer errflag, String errStr) {
		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!vt.getCenterID().equals(ocenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;

		}
		if (state.intValue() == 0 || state.intValue() == 1
				|| state.intValue() == 2) {
			// 这个表明下一步还处于点播状态，那么只需要考虑本级的点播痕迹等等信息。

			// 查询本级的痕迹
			PassRecord passRecord = vt.getPassRecord(uuid);

			// 清除本级的记录，然后判断是本级发起的还是路过的。
			if (passRecord != null) {

				vt.removPassRecord(uuid);
				// 如果是本级发起的，通知客户端

				if (vt.isClientCenterid(passRecord.getRoute())) {
					// 获取原来的上下文环境。
					String objStr = vt.getContext(uuid);
					// 删除原来的这个上下文。
					vt.removeContext(uuid);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ passRecord.getClientId());
					VideoControl.OnPlayFailed(passRecord.getDeviceId(),
							passRecord.getCameraIndex(), errflag, objStr,
							errStr);

				} else {
					// 如果是路过的，通知上一级继续进行关闭
					String next = vt
							.getNextSuccessCenter(passRecord.getRoute());

					// 逆向通知关闭点播 通知各个服务器进行删除pass记录
					IMessage message2 = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(uuid, next, 0,
									errflag, errStr);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);
				}

				// 判断当前是否还有等待中的痕迹，如果有，则进行再次发起。
				this.AgainPassPlay(passRecord.getDeviceId(),
						passRecord.getCameraIndex());

			}

		}

		if (state.intValue() == 2) {
			// 如果下一级已经点播成功，那么本级可能还存在点播中和点播成功两种情况。
			List<String> playRecordList = vt.getPlayRecordWithUUid(uuid);
			for (String str : playRecordList) {
				PalyRecord pr = vt.getPlayRecord(str);
				// 删除痕迹
				vt.removePlayRecord(str);

				// 通知转发服务器进行关闭
				if (pr.getForwardflag().intValue() == 1) {
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getForwardid());
					VideoControl.StopStreamFoward(pr.getClientId(),
							pr.getDevid(), pr.getFormIP(), pr.getFormChannel(),
							pr.getForwardid(), pr.getFormChannel(), 1,
							"客户端 请求停止!");

					// 关闭前置。
					stopPreFoward(pr.getDevid(), pr.getChannel(), 1, uuid);

					LogUtil.VideoInfo("$$StopStreamFoward 1 2252 |"
							+ pr.getFormIP() + " | " + pr.getFormChannel()
							+ " | " + pr.getForwardid());

				}

				// 判断是否为本级发起点播，如果是，发送通知给客户端
				if (vt.isClientCenterid(pr.getCenterMap())) {
					// 获取原来的上下文环境。
					String objStr = vt.getContext(str);
					// 删除原来的这个上下文。
					vt.removeContext(str);
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getClientId());
					VideoControl.OnPlayFailed(pr.getDevid(), pr.getChannel(),
							errflag, objStr, errStr);
				} else {
					String next = vt.getNextSuccessCenter(pr.getCenterMap());
					// 逆向通知关闭点播 通知各个服务器进行删除pass记录
					IMessage message2 = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(str, next, 1,
									errflag, errStr);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);

				}

			}

			List<VideoRecord> videoRecordList = vt.getVideoRecordFormPr(uuid);
			for (VideoRecord vr : videoRecordList) {
				// 删除痕迹
				vt.removeVideoRecord(vr.getUuid());

				// 通知转发服务器进行关闭
				if (vr.getFowardFlag().intValue() == 1) {
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ vr.getFowardId());
					VideoControl.StopStreamFoward(vr.getClientId(), vr
							.getDeviceId(), vr.getPr().getFormIP(), vr.getPr()
							.getFormChannel(), vr.getFowardId(), vr.getPr()
							.getFormChannel(), 1, "客户端 请求停止!");

					// 关闭前置。
					stopPreFoward(vr.getPr().getDevid(), vr.getPr()
							.getChannel(), 1, uuid);

					LogUtil.VideoInfo("$$StopStreamFoward 1 2300 |"
							+ vr.getPr().getFormIP() + " | "
							+ vr.getPr().getFormChannel() + " | "
							+ vr.getFowardId());

				}

				// 分析是否要通知客户端
				if (vt.isClientCenterid(vr.getPr().getCenterMap())) {
					// 获取原来的上下文环境。
					String objStr = vt.getContext(vr.getUuid());
					// 删除原来的这个上下文。
					vt.removeContext(vr.getUuid());
					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ vr.getClientId());
					VideoControl.OnPlayFailed(vr.getDeviceId(),
							vr.getCameraIndex(), errflag, objStr, errStr);
				} else {
					String next = vt.getNextSuccessCenter(vr.getPr()
							.getCenterMap());
					// 逆向通知关闭点播 通知各个服务器进行删除pass记录
					IMessage message2 = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(vr.getUuid(), next,
									2, errflag, errStr);
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);
				}

			}

		}

	}

	// 为了保证原来的版本可用
	@Remoting
	public void RequestPlayVideoVer2(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer userLev) {

		this.RequestPlayVideo(ClientUserId, DeviceId, Index, Context, -1,
				userLev);

	}

	/**
	 * 专门用来处理上线的。
	 * */
	public void setCenter(String sessionid, boolean b) {
		// if (b)
		// return;

		LogUtil.VideoInfo("backwardstopvideo center:" + sessionid);

		// 删除一下 ps痕迹
		List<PassRecord> listPs = vt.getPassRecordWithCenter(sessionid);
		for (PassRecord pr : listPs) {
			this.GrobalRequestStopVideo(pr.getUuid(), vt.getCenterID());
		}

		// 删除点播记录 pr
		List<String> listPr = vt.getPalyRecordWithCenter(sessionid);
		for (String temp : listPr) {
			this.GrobalRequestStopVideo(temp, vt.getCenterID());
		}

		List<VideoRecord> list = vt.getVideoRecordWithSend(sessionid, 1);

		for (VideoRecord vr : list) {

			// 对于凡是通过转发服务器转发的，进行外网关闭通知。
			// 模拟主动停止点播
			this.GrobalRequestStopVideo(vr.getUuid(), vt.getCenterID());
		}

		/**
		 * 上面都是判断上一个路由节点下线情况。
		 * 
		 * 以下是判断下一个路由节点下线情况。
		 * 
		 * 以前是上级都是靠设备下线来保证各种信息的。
		 * 
		 * 修改为如果下级下线，我们也做相应处理。模拟下级发送的停止过来。
		 * 
		 * 
		 * 
		 * */
		// 删除一下 ps痕迹
		listPs = vt.getPassRecordWithCenter2(sessionid);
		for (PassRecord pr : listPs) {
			this.GrobalBackwardStopVideo(pr.getUuid(), vt.getCenterID(), 0, 3,
					"点播路由中断");
		}

		// 删除点播记录 pr
		listPr = vt.getPalyRecordWithCenter2(sessionid);
		for (String temp : listPr) {
			this.GrobalBackwardStopVideo(temp, vt.getCenterID(), 1, 3, "点播路由中断");
		}

		// 删除点播记录 pr
		list = vt.getVideoRecordWithSend2(sessionid, 1);

		for (VideoRecord vr : list) {

			// 对于凡是通过转发服务器转发的，进行外网关闭通知。
			// 模拟主动停止点播
			LogUtil.VideoInfo("backwardstopvideo :" + vr.getUuid());
			this.GrobalBackwardStopVideo(vr.getUuid(), vt.getCenterID(), 2, 3,
					"点播路由中断");
		}

	}

	private void ChangeUserLev(String uuid, Route realCenterMap, Integer lev,
			Boolean changflag) {

		IMessage message = VideoContrlDomainProxy.GrobalChangeUserLev_Copy(
				uuid, realCenterMap.routeToStr(), lev, changflag,
				vt.getNextPlayCenter(realCenterMap));

		String nosend = null;
		if (!vt.isClientCenterid(realCenterMap)) {
			nosend = vt.getNextSuccessCenter(realCenterMap);
		}

		(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
				vt.getNextPlayCenter(realCenterMap), null, nosend);
	}

	/**
	 * 修改级别，提升or下降所用
	 * */
	@Remoting
	public void GrobalChangeUserLev(String uuid, String CenterMap, Integer lev,
			Boolean changflag, String OCenterid) {
		if (!vt.getCenterID().equals(OCenterid)) {
			// 进行转发，考虑网状结构的存在。
			VideoControlDomain.AppRunTime().setContinueFlag(true);
			return;
		}
		// 进行等级修改，然后判断是否要进行下一个节点的等级提升。
		VideoRecord vr = vt.getVideoRecord(uuid);
		if (vr != null && changflag) {
			List<VideoRecord> videoList = vt.getVideoRecordFormPr(vr.getPr()
					.getUuid());
			int maxLev = 0;
			for (VideoRecord vre : videoList) {
				if (vre.getPr().getLev() > maxLev)
					maxLev = vre.getPr().getLev();
			}

			vr.setUserLev(lev);
			vr.getPr().setLev(lev);

			if (lev > maxLev) {
				// 通知一下，下面的出口可以升级了。
				// 参数包括： uuid 路由信息 lev levflag升级还是降级
				ChangeUserLev(vr.getPr().getUuid(), vr.getPr()
						.getRealCenterMap(), lev, changflag);
			}

		} else if (vr != null && !changflag) {
			vr.setUserLev(lev);
			vr.getPr().setLev(lev);

			List<VideoRecord> videoList = vt.getVideoRecordFormPr(vr.getPr()
					.getUuid());
			int maxLev = 0;
			for (VideoRecord vre : videoList) {
				if (vre.getPr().getLev() > maxLev)
					maxLev = vre.getPr().getLev();
			}

			if (lev >= maxLev) {
				ChangeUserLev(vr.getPr().getUuid(), vr.getPr()
						.getRealCenterMap(), maxLev, changflag);
			}
		}

	}

	/**
	 * 用来如果出问题了，判断我们这里的痕迹中是否还有等待该点播的，如果有，找出等级最高的，发起点播
	 * 
	 * */
	private void AgainPassPlay(String deviceId, Integer cameraIndex) {
		// 查询当前所有该点播痕迹
		List<PassRecord> list = vt.getPassRecordWithDevice(deviceId,
				cameraIndex);

		PassRecord passRecord = null;
		int temp = 0;
		for (PassRecord pr : list) {
			if (pr.getLev() > temp) {
				temp = pr.getLev();
				passRecord = pr;
			}
		}

		if (passRecord != null) {
			passRecord.setSendFlag(true);

			String next = vt.getNextPlayCenter(passRecord.getRoute());
			IMessage message = VideoContrlDomainProxy
					.GrobalRequestPlayVideo_Copy(passRecord.getClientId(),
							passRecord.getDeviceId(), passRecord
									.getCameraIndex(), passRecord.getUuid(),
							-1, passRecord.getLev(), passRecord.getRoute()
									.routeToStr(), next, passRecord
									.getClientIP());
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
					next, null, null);
		}

	}

	/**
	 * 获得所有痕迹信息
	 * */
	public String getALlRoute() {
		String context = Encoding.getUuid();

		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("monitor");
		root.addAttribute("class", "videoctrl");

		Element plays = root.addElement("plays");
		plays.addAttribute("context", context);

		String centerid = VideoControlDomain.AppRunTime().getServerId();

		List<VideoRoute> list = vt.getAllRoute();
		for (VideoRoute vr : list) {
			try {
				Element play = plays.addElement("play");

				play.addAttribute("type", vr.getType());

				play.addElement("clientid").addText(vr.getClientid());
				play.addElement("clientip").addText(vr.getClientip());
				play.addElement("deviceid").addText(vr.getDeviceid());
				String[] strArg = DAOFactory.getDeviceStatusIntance()
						.getDevIpByDevIdName(vr.getDeviceid());
				play.addElement("deviceip").addText(strArg[0]);

				// add
				play.addElement("devicename").addText(
						strArg[1] != null ? strArg[1] : strArg[0]);

				play.addElement("devicechannel").addText(
						vr.getDevicechannel().toString());

				strArg = RouteImpl.getRouteIp(vr.getRoutemap());

				play.addElement("routemap").addText(strArg[0]);
				// add
				play.addElement("routemapbyname").addText(strArg[1]);

				play.addElement("lev").addText(vr.getLev().toString());

				play.addElement("sourceip").addText(vr.getSourceip());

				play.addElement("sourcechannel").addText(
						vr.getSourcechannel().toString());

				play.addElement("forwardid").addText(vr.getForwardid());

				play.addElement("sourceuuid").addText(vr.getSourceuuid());

				// LogUtil.BusinessError("begin" + vr.getRealroutemap());
				// LogUtil.BusinessError("end "+RouteImpl.getRouteIp(vr.getRealroutemap()));
				strArg = RouteImpl.getRouteIp(vr.getRealroutemap());

				play.addElement("realroutemap").addText(strArg[0]);
				// add
				play.addElement("realroutemapbyname").addText(strArg[1]);

				play.addElement("uuid").addText(vr.getUuid());

				play.addElement("sendflag")
						.addText(vr.getSendflag().toString());
				play.addElement("sendid").addText(vr.getSendid());
				play.addElement("centerid").addText(centerid);
			} catch (Exception e) {
				LogUtil.BusinessError("getAllRoute" + e.getMessage());
				root.addElement("error").addText(vr.getUuid());
				LogUtil.BusinessError("getAllRoute" + vr.getDeviceid()
						+ "  |  " + vr.getDevicechannel()
						+ " |  to sourceid : " + vr.getSourceuuid() + "  | id:"
						+ vr.getUuid());
				if (e.getCause() != null) {
					LogUtil.BusinessError(e.getCause().toString());
				}
				for (StackTraceElement a : e.getStackTrace()) {
					LogUtil.BusinessError(a.toString());

				}

			}
		}

		return root.asXML();
	}

	// 以下接口是前置服务器所用。
	/**
	 * 前置服务器登录声明
	 * */
	@Remoting
	public final void PreLogin(String ServerID, Integer nums, Integer usenums) {
		
		//要重置以前的点播
		
		List<PreVideo> list = pvt.all();
		
		for(PreVideo pv : list){
			PreFowardError(pv.getDeviceID(), pv.getChannel(), pv.getContext(), "前置服务器初始化");
		}
		
		
		pvt.setPreServerID(ServerID);
	}
	
	/**
	 * 发起点播的客户端下线
	 * */
	private void PreClientDrop(String clientID){
		
		//查询相关客户端发起的点播，并且进行关闭
		List<PreVideo> list = pvt.getPreVideosByClient(clientID);
		for(PreVideo pv:list){
			PreStopPlay(pv);
		}
		
	}
	
	/**
	 * 发起点播的设备下线。
	 * */
	private void PreDeviceDrop(String deviceID){
		
		//查询相关设备的点播，并且进行关闭
		List<PreVideo> list = pvt.getPreVideosByDevice(deviceID);
		for(PreVideo pv:list){
			PreStopPlay(pv);
		}

	}
	
	
	
	/**
	 * 进行前置点播
	 * */
	private void PreStartPlay(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer NetLinkMode,
			Integer userLev, String CenterMap, String OCenterid, String clientIP) {
		//记录前置服务器
		pvt.saveStartPlay(ClientUserId, DeviceId, Index, Context, NetLinkMode, userLev, CenterMap, OCenterid, clientIP);
		
		LogUtil.TestInfo("pre play " +ClientUserId+" "+DeviceId+" "+Index+ "  context"+Context);
		//判断转发服务器是否已经登录
		if(pvt.getPreVideoServerID()!=null) {
			
			(VideoControlDomain.AppRunTime())
			.SetCurChannel("Local://Session://"
					+ pvt.getPreVideoServerID());
	         VideoControl.StartPreFoward(DeviceId, Index, Context);
			
		} else {
			
			PreFowardError(DeviceId, Index, Context, "前置服务器未上线！");
			
		}
		
	}
	
	
	/**
	 * 前置服务器关闭点播
	 * @param pv 
	 * */
	private  void PreStopPlay(PreVideo pv) {
		
		pvt.removePreVideo(pv);
		
		//判断转发服务器是否已经登录
		if(pvt.getPreVideoServerID()!=null) {
			
			(VideoControlDomain.AppRunTime())
			.SetCurChannel("Local://Session://"
					+ pvt.getPreVideoServerID());
	         VideoControl.StopPreFoward(pv.getDeviceID(), pv.getChannel(),pv.getContext(),1);
			
		}
	}
	
	
	

	/**
	 * 前置服务器点播成功
	 * */
	@Remoting
	public void PreFowardSuccess(String DeviceId, Integer DeviceIdChannel,
			String FowardId, String VideoServerIP, Integer VideoServerChannel,
			String user, String Password, Integer Port, Integer DeviceType, // 设备厂商
			Integer DeviceSubType,// 设备型号
			Integer NetLinkType, // 网络连接类型
			Integer NetLinkSubType,// 网络连接子类型
			Integer NetLinkMode,// 网络连接模式
			String Context) {
		
		System.out.println("成功"+DeviceId+"   "+ VideoServerIP+"  "+VideoServerChannel);
		
		//通过跨级成功接口，对数据进行反馈。
		PreVideo pv = pvt.getPreVideoByContext(Context);
		
		if(pv!=null) {
			
			String next = vt.getNextSuccessCenter(Route.strToRoute(pv.getCenterMap()));


			IMessage message = VideoContrlDomainProxy
					.GrobalRequestPlayVideoSuccess_Copy(pv.getClientUserId(), DeviceId,
							DeviceIdChannel, vt.getCenterID(), FowardId, 1,
							VideoServerIP, VideoServerChannel, user, Password,
							Port, DeviceType, DeviceSubType, NetLinkType,
							NetLinkSubType, NetLinkMode, Context, pv.getCenterMap(), next);
			(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(message,
					next, null, null);
			
		} else {
			
			//通知前置服务器进行关闭
			pv = new PreVideo("", DeviceId, DeviceIdChannel, Context, NetLinkMode, 1, "", "", "");
			PreStopPlay(pv);
			
		}
		

	}

	/**
	 * 前置服务器点播失败
	 * */
	@Remoting
	public void PreFowardError(String DeviceId, Integer DeviceIdChannel,
			String Context, String Reason) {
		PreVideo pv = pvt.getPreVideoByContext(Context);
		pvt.removePreVideo(pv);
		
		pvt.all();
		
		
		
		// 逆向通知关闭吧。
    	String next = vt.getNextSuccessCenter(Route.strToRoute(pv.getCenterMap()));
		// 逆向通知关闭点播
		IMessage message = VideoContrlDomainProxy
				.GrobalBackwardStopVideo_Copy(Context, next, 0,4,
						Reason);
		(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
				message, next, null, null);
		return;
	
	
	}

	/**
	 * 关闭判断，凡是调用关闭操作的，都需在此进行一次判断。
	 * 
	 * @param type
	 *            0表示全关 ，1表示计数
	 */
	public void stopPreFoward(String deviceid, int channel, int type,
			String uuid) {

	}



	/**
	 * 声明重启这个。
	 * */
	@Remoting
	public Boolean refreshPreFoward(String Deviceid) {
		boolean b = false;

		return b;
	}

//	/**
//	 * 关闭前置点播的某一通道相关所有服务。
//	 * */
//	private void preKickUserPlay(PreSource ps) {
//
//	}

	/**
	 * 转发服务器停止某一路图像，进行通知。
	 * */
	@Remoting
	public void StreamFowardStop(String fowardid, String ip, Integer channel,
			String context) {

		LogUtil.VideoInfo("Test StreamFowardStop " + ip + " : " + channel
				+ " context :" + context);

		// 查询相关记录，对点播成功的有效
		List<String> playList = vt.getPlayRecordWithForward(fowardid);
		for (String uuid : playList) {
			PalyRecord pr = vt.getPlayRecord(uuid);
			if (pr.getFormIP().equals(ip)
					&& pr.getFormChannel().intValue() == channel.intValue()) {
				// 符合删除条件，进行删除
				vt.removePlayRecord(uuid);
				// 通知客户端，进行关闭
				if (vt.isClientCenterid(pr.getCenterMap())) {
					// 通知客户端，关闭点播吧
					// 获取原来的上下文环境。
					String objStr = vt.getContext(pr.getUuid());
					// 删除原来的这个上下文。
					vt.removeContext(pr.getUuid());

					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getClientId());
					VideoControl.OnPlayFailed(pr.getDevid(), pr.getChannel(),
							3, objStr, "转发服务器主动停止");

				} else {
					// 进行逆推吧。
					// 如果是路过的，通知上一级继续进行关闭
					String next = vt.getNextSuccessCenter(pr.getCenterMap());

					// 逆向通知关闭点播 通知各个服务器进行删除pass记录
					IMessage message2 = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(pr.getUuid(), next,
									1, 3, "转发服务器主动停止");
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);

				}

				// 开始往下发送停止，本级的那个就不要了。
				// 如果本级不是终点，那么也要往下发展。

				if (!vt.isDeviceCenterid(pr.getCenterMap())) {
					// 本级不能停，直接通知下一级

					// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
					List<VideoRecord> listVr = vt.getVideoRecordFormPr(pr
							.getUuid());
					List<String> listPr = vt
							.getPlayRecordWithUUid(pr.getUuid());
					if (listVr.size() + listPr.size() == 0) {
						// 可以通知下一个中心可以进行关闭。
						if (!vt.isDeviceCenterid(pr.getCenterMap())) {
							String next = vt.getNextPlayCenter(pr
									.getCenterMap());

							// by zzw 10.09 修改，停止应该用pr的uuid。
							// IMessage message2 = VideoContrlDomainProxy
							// .GrobalRequestStopVideo_Copy(vr.getUuid(),
							// next);
							IMessage message2 = VideoContrlDomainProxy
									.GrobalRequestStopVideo_Copy(pr.getUuid(),
											next);
							(VideoControlDomain.AppRunTime())
									.LocalChannelSendMessage(message2, next,
											null, null);
						}
					}

				}
			}
		}

		// 查询相关记录，对点播成功的有效
		List<VideoRecord> list = vt.getVideoRecord();
		for (VideoRecord vr : list) {
			// 进行退出说明
			if (!fowardid.equals(vr.getFowardId()))
				continue;
			if (vr.getPr().getFormChannel().intValue() == channel.intValue()
					&& vr.getPr().getFormIP().equals(ip)) {
				// 1、删除相关记录
				vt.removeVideoRecord(vr.getUuid());
				// 2、通知客户端
				PalyRecord pr = vr.getPr();
				// 首先判断是本地的还是要传递的，
				if (vt.isClientCenterid(pr.getCenterMap())) {
					// 通知客户端，关闭点播吧
					// 获取原来的上下文环境。
					String objStr = vt.getContext(vr.getUuid());
					// 删除原来的这个上下文。
					vt.removeContext(vr.getUuid());

					(VideoControlDomain.AppRunTime())
							.SetCurChannel("Local://Session://"
									+ pr.getClientId());
					VideoControl.OnPlayFailed(pr.getDevid(), pr.getChannel(),
							3, objStr, "转发服务器主动停止");

				} else {
					// 进行逆推吧。
					// 如果是路过的，通知上一级继续进行关闭
					String next = vt.getNextSuccessCenter(pr.getCenterMap());

					// 逆向通知关闭点播 通知各个服务器进行删除pass记录
					IMessage message2 = VideoContrlDomainProxy
							.GrobalBackwardStopVideo_Copy(vr.getUuid(), next,
									1, 3, "转发服务器主动停止");
					(VideoControlDomain.AppRunTime()).LocalChannelSendMessage(
							message2, next, null, null);

				}

				// 开始往下发送停止，本级的那个就不要了。
				// 如果本级不是终点，那么也要往下发展。

				if (!vt.isDeviceCenterid(pr.getCenterMap())) {
					// 本级不能停，直接通知下一级

					// 判断目前引用该记录的点播是否为0，如果为0，通知下一步进行关闭。
					List<VideoRecord> listVr = vt.getVideoRecordFormPr(pr
							.getUuid());
					List<String> listPr = vt
							.getPlayRecordWithUUid(pr.getUuid());
					if (listVr.size() + listPr.size() == 0) {
						// 可以通知下一个中心可以进行关闭。
						if (!vt.isDeviceCenterid(pr.getCenterMap())) {
							String next = vt.getNextPlayCenter(pr
									.getCenterMap());

							// by zzw 10.09 修改，停止应该用pr的uuid。
							// IMessage message2 = VideoContrlDomainProxy
							// .GrobalRequestStopVideo_Copy(vr.getUuid(),
							// next);
							IMessage message2 = VideoContrlDomainProxy
									.GrobalRequestStopVideo_Copy(pr.getUuid(),
											next);
							(VideoControlDomain.AppRunTime())
									.LocalChannelSendMessage(message2, next,
											null, null);
						}
					}

				}

			}

		}

	}

	/**
	 * 转发服务器与服务器进行同步用。
	 * */
	@Remoting
	public void StreamFowardSyncMessage(String fowardid, String context) {
		LogUtil.VideoInfo("Test StreamFowardSyncMessage " + context);
		// 解析context，并且将所有IP和通道放入set
		Set<String> hpSet = new HashSet<String>(); // String 格式 ：
													// IP：CHannel/serverChannel
													// |
		if (context.contains(":")) {
			String[] strArray = context.split("\\|");
			for (String s : strArray) {
				// find last "/"
				int m = 0;
				if (s.contains("rtsp")) {
					m = s.lastIndexOf(":");
				} else {
					m = s.lastIndexOf("/");
				}
				hpSet.add(s.substring(0, m));
			}

		}

		List<VideoRecord> list = vt.getVideoRecord();
		for (VideoRecord vr : list) {
			if (!fowardid.equals(vr.getFowardId()))
				continue;
			StringBuffer sb = new StringBuffer();
			sb.append(vr.getPr().getFormIP());
			if (!vr.getPr().getFormIP().contains("rtsp")) {
				sb.append(":").append(vr.getPr().getFormChannel());
			}

			if (!hpSet.contains(sb.toString())) {
				LogUtil.VideoInfo("Test StreamFowardSyncMessage find "
						+ vr.getPr().getFormIP() + " "
						+ vr.getPr().getFormChannel());
				LogUtil.VideoInfo(sb.toString());
				this.StreamFowardStop(fowardid, vr.getPr().getFormIP(), vr
						.getPr().getFormChannel(), "");
			}

		}

	}

	/**
	 * 从点播源（就是转发用来点播的）取得设备ID和通道
	 * */
	@Remoting
	public VideoRecord getDeviceFormURL(String url, Integer channel) {
		
		VideoRecord vr =  vt.getDevice(url, channel);
		LogUtil.BusinessInfo("getdeviceformurl result is"+ (vr!=null));
		return vr;
	}

	/**
	 * 从设备ID和通道 取得 点播源（就是转发用来点播的）
	 * */
	@Remoting
	public PalyRecord getURLFormDevice(String deviceid, Integer channel) {
		return vt.getUrl(deviceid, channel);
	}
	
	
	
	
	
	
	static public void main(String... args) {
		// String context = "192.168.1.206:0/0|192.168.1.117:1/1";
		// Set<String> hpSet = new HashSet<String>(); // String 格式 ：
		// // IP：CHannel/serverChannel
		// // |
		// if (context.contains(":")) {
		// String[] strArray = context.split("\\|");
		// System.out.println(strArray.length);
		// for (String s : strArray) {
		// hpSet.add(s.replaceAll("/\\S*", ""));
		// }
		//
		// }
		// for (String s : hpSet) {
		// System.out.println(s);
		// }
		// System.out.println(hpSet.contains("192.168.1.117:1"));

		String context = "rtsp://admin:12345@192.168.1.196:554/h264/4/main/av_stream:4/";

		int n = context.lastIndexOf(":");
		System.out.println(context.substring(0, n));

	}

}
