package NVMP.StateManageDomain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.misc.log.LogUtil;

import NVMP.AppService.Remoting;
import NVMP.Proxy.StateManageDomainProxy;
import NVMP.Proxy.VideoContrlDomainProxy;

import com.fxdigital.bean.NvmpVideodevrtsptab;
import com.mysql.impl.Hibernate;
import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.DeviceStatus;

import corenet.rpc.IMessage;

public class EncodeDeviceManage extends Thread {

	public EncodeDeviceManage() {
		hs = new HashSet<String>();
		deviceTImeSet = new ConcurrentHashMap<String, Long>();
		this.start();
		new ToClientMessage().start();
	}

	public IEncodeDeviceManage EncodeDeviceManage;

	// 上报设置设备上线或下线
	@Remoting
	public void SetEncodeDeviceOnline(String TerminalId, String TerminalIP,
			Boolean IsOnline, Integer DeviceType, Integer DevuceSubType,
			String xml) {

		// 先判断是否为xml消息
		// if (xml != null && !xml.equals("")) {

		// IMessage message = StateManageDomainProxy
		// .GrobalSetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
		// IsOnline, DeviceType, DevuceSubType, xml,
		// (StateManageDomain.AppRunTime()).getServerId());
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(message,
		// null, null, null);
		//
		// return;
		// }

		if (IsOnline && !TerminalId.contains("@006")) {
			if (xml == null || !xml.equals("008"))
				hs.add(TerminalId);
		}

		String ErrInfo = "设备上下线处理错误：";

		try {
			String s = String.format("****收到编码设备上下线：设备编号=%s. IP=%s. 是否上线=%s",
					TerminalId == null ? "" : TerminalId,
					TerminalIP == null ? "" : TerminalIP,
					(IsOnline == null ? "true" : IsOnline).toString());
			ErrInfo = ErrInfo + s;
			LogUtil.BusinessDebug(s);
			// 对本地数据库的设备信息进行更改。
			DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(TerminalId);
			if (ds == null)
				ds = new DeviceStatus();

			ds.setDevcieId(TerminalId);
			if(TerminalIP != null && !TerminalIP.trim().equals("") ) 
				 ds.setDevIp(TerminalIP);
			ds.setDeviceStatus(IsOnline == true ? 1 : 0);
			if (DeviceType != null && DeviceType != -1) {
				ds.setDevType(DeviceType);
			}
			if (DevuceSubType != null && DevuceSubType != -1) {
				ds.setDevSubType(DevuceSubType);
			}
			
		    ds.setCenterID(StateManageDomain.AppRunTime().getServerId());
			
			if (xml != null && !xml.equals("")) {
				String[] args = xml.split(",");
				if (args.length == 3) {
					ds.setUserName(args[0]);
					ds.setPassword(args[1]);
					ds.setDevPort(Integer.parseInt(args[2]));
				} else if (args.length == 4) {
					ds.setUserName(args[0]);
					ds.setPassword(args[1]);
					ds.setDevPort(Integer.parseInt(args[2]));
					ds.setSwitchSvrID(args[3]);
				}

			}

			LogUtil.BusinessInfo("See See " + ds.getDevIp() + " | "
					+ ds.getCenterID());

			DAOFactory.getPojoImpl().updateInfo(ds);

			// 通知状态服务器，我们已经知道该设备上线了。
			if (manageid != null) {
				(StateManageDomain.AppRunTime())
						.SetCurChannel("Local://Session://" + manageid);
				EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
						IsOnline);
			}

			// 通知组内成员状态改变
//			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
//					+ "G_device_state");
//			EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
//					IsOnline);
			
			
			toClientMessage(TerminalId, TerminalIP, IsOnline);

			// // 客服端发送一群
			// (StateManageDomain.AppRunTime())
			// .LocalChannelSendMessage(StateManageDomainProxy
			// .SetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
			// IsOnline, DeviceType, DevuceSubType),null,null);

			// DeviceStatus device = DAOFactory.getDeviceStatusIntance()
			// .getDeviceStatus(TerminalId);

			// if (device != null
			// && device.getCenterID().equals(
			// StateManageDomain.AppRunTime().getServerId())) {
			IMessage message = StateManageDomainProxy
					.GrobalSetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
							IsOnline, DeviceType, DevuceSubType, xml,
							StateManageDomain.AppRunTime().getServerId());
			(StateManageDomain.AppRunTime()).LocalChannelSendMessage(message, null,
					null,null);
			// }

			// 本地查询
			// DAOFactory.getPojoImpl().getChannel(devId, typeid);
			// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
			VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
					TerminalIP, IsOnline);

		} catch (Exception e) {
			LogUtil.BusinessError(e.toString());
			// LogUtil.BusinessError(ErrInfo);
		}

	}
	
	
	/**
	 * 指挥终端虚拟编码标准流登录
	 * */
	@Remoting
	public void SetRTSPCommandEncodeDeviceOnline(String DeviceID,
			Integer TypeID, Integer Channel, Integer DefaultFlag,
			Integer BitStream, String RtspUrl) {

		if (TypeID == 250) {
			NvmpVideodevrtsptab tab=new NvmpVideodevrtsptab();
			tab.setDeviceId(DeviceID);
			tab.setTypeId(TypeID.toString());
			tab.setChannel(Channel);
			tab.setDefaultFlag(DefaultFlag);
			tab.setRtspUrl(RtspUrl);
			tab.setBitStream(BitStream);
			tab.setCenterId(StateManageDomain.AppRunTime().getServerId());
			Hibernate.getHibernate().save(tab);
			/*
			String sql = String
					.format("insert into nvmp_videodevrtsptab (DeviceID,TypeID,Channel,DefaultFlag,RtspUrl,BitStream,CenterID) values('%s',%d,%d,%d,'%s',%d,'%s')",
							DeviceID, TypeID, Channel, DefaultFlag, RtspUrl,
							BitStream, StateManageDomain.AppRunTime()
									.getServerId());
			myDB.updateInfo(sql);
		*/}

	}
	


	// 指挥终端虚拟编码设备声明上线
	@Remoting
	public void SetCommandEncodeDeviceOnline(String TerminalId,
			String TerminalIP, Boolean IsOnline, Integer DeviceType,
			Integer DevuceSubType, String centerid) {

		String ErrInfo = "设备上下线处理错误：";

		try {

			String s = String.format("****收到编码设备上下线：设备编号=%s. IP=%s. 是否上线=%s",
					TerminalId == null ? "" : TerminalId,
					TerminalIP == null ? "" : TerminalIP,
					(IsOnline == null ? "true" : IsOnline).toString());
			ErrInfo = ErrInfo + s;
			LogUtil.BusinessDebug(s + "======" + IsOnline);
			LogUtil.BusinessDebug(s + "====== 中心" + centerid);
			// 对本地数据库的设备信息进行更改。

			// 通知组内成员状态改变
			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
					+ "G_device_state");
			EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
					IsOnline);

			if (!IsOnline) {
				// 在此处根据TerminalId,删除该设备
				// DAOFactory.getDeviceStatusIntance().delDeviceStatusByDevId(
				// TerminalId);

				// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
				VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
						TerminalIP, IsOnline);
				return;
			}
			
			/**
			 * 2014-08-04 by zzw 增加视指挥端标准流设置
			 * */

			String sql = String.format(
					"delete from  NvmpVideodevrtsptab where deviceId = '%s'",
					TerminalId);
			Hibernate.getHibernate().deleteOrUpdate(sql, null);
//			myDB.updateInfo(sql);

			// if (centerid == null) {
			// centerid = (StateManageDomain.AppRunTime()).getServerId();
			// }

			// // 客服端发送一群
			// (StateManageDomain.AppRunTime())
			// .LocalChannelSendMessage(StateManageDomainProxy
			// .SetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
			// IsOnline, DeviceType, DevuceSubType),null,null);

			DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(TerminalId);
			if (ds == null)
				ds = new DeviceStatus();

			ds.setDevcieId(TerminalId);
			ds.setDevIp(TerminalIP);
			ds.setDeviceStatus((IsOnline == null || IsOnline) ? 1 : 0);
			
			//20130716 zzw    add port 5050
			ds.setDevPort(5050);
			
			
			if (DeviceType != null && DeviceType != -1) {
				ds.setDevType(DeviceType);
			}
			if (DevuceSubType != null && DevuceSubType != -1) {
				ds.setDevSubType(DevuceSubType);
			}
			ds.setCenterID(StateManageDomain.AppRunTime().getServerId());
			// ds.setCenterID(centerid);

			DAOFactory.getPojoImpl().updateInfo(ds);

			// 通知其它中心，该设备上线了。：
			// this.SetEncodeDeviceOnline(TerminalId, TerminalIP, IsOnline,
			// DeviceType, DevuceSubType, null);
			IMessage message = StateManageDomainProxy
					.GrobalSetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
							IsOnline, DeviceType, DevuceSubType, null,
							(StateManageDomain.AppRunTime()).getServerId());
			(StateManageDomain.AppRunTime()).LocalChannelSendMessage(message,
					null, null, null);

			// 本地查询
			// DAOFactory.getPojoImpl().getChannel(devId, typeid);
			// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
			VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
					TerminalIP, IsOnline);

		} catch (Exception e) {
			LogUtil.BusinessError(e.toString());
			// LogUtil.BusinessError(ErrInfo);
		}

	}

	// 上报设置设备报警状态
	/**
	 * @param AlarmType
	 *            public static int AlarmPoint = 1; public static int OutPoint =
	 *            2; public static int Motion = 3; public static int VideoLost =
	 *            4;
	 * 
	 * */

	@Remoting
	public void SetAlarmState(String TerminalId, Integer ChannelId,
			Integer AlarmType, Integer DeviceStatus) {

		// 对本地数据库的设备信息进行更改。

		DAOFactory.getPojoImpl().updateInfo(TerminalId, ChannelId,
				DeviceStatus, AlarmType);
		// 通知组内成员状态改变

		(StateManageDomain.AppRunTime()).SetCurChannel("Group://"
				+ "G_device_alarm");
		// EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
		// IsOnline);
		EncodeDeviceManage.OnAlarmState(TerminalId, ChannelId, AlarmType,
				DeviceStatus);

		// 客服端发送一群
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(
		// StateManageDomainProxy.SetAlarmState_Copy(TerminalId,
		// ChannelId, AlarmType, DeviceStatus), null, null);

	}

	// 设置设备布防和拆防
	@Remoting
	// 数据库找到用 接入事件， 客服端转发
	public void ControlAlarmState(String TerminalId, Integer index,
			Integer AlarmType, Boolean IsStart) {

		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);
		System.out.println("==============" + sessionid);
		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnControlAlarmState(TerminalId, index,
					AlarmType, IsStart);

		}

		// else {
		// // 本地 没这个设备，发给上级吧
		//
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(
		// StateManageDomainProxy.ControlAlarmState_Copy(TerminalId,
		// index, AlarmType, IsStart), null, null);
		// }

	}

	// 控制云台
	@Remoting
	// 数据库找到用 接入事件， 客服端转发
	public void ControlCameraPTZ(String TerminalId, Integer Cameraindex,
			Integer Direction, Integer Speed, Boolean IsStart) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);
		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnControlCameraPTZ(TerminalId, Cameraindex,
					Direction, Speed, IsStart);

		}

		// else {
		// // 本地 没这个设备，发给上级吧
		//
		// (StateManageDomain.AppRunTime())
		// .LocalChannelSendMessage(StateManageDomainProxy
		// .ControlCameraPTZ_Copy(TerminalId, Cameraindex,
		// Direction, Speed, IsStart), null, null);
		// }

	}

	// 控制图像质量 //增加图像质量的枚举定义******
	@Remoting
	public void ControlVideoQuality(String TerminalId, Integer Cameraindex,
			Integer Type, Integer Value) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);
		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnControlVideoQuality(TerminalId, Cameraindex,
					Type, Value);
		}

		// else {
		// // 本地 没这个设备，发给上级吧
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(
		// StateManageDomainProxy.ControlVideoQuality_Copy(TerminalId,
		// Cameraindex, Type, Value), null, null);
		// }
	}

	// public native boolean selectPrePoint(int LoginHandle, int Channel, int
	// Index);
	// 调用预置点：
	@Remoting
	public void selectPrePoint(String TerminalId, Integer Cameraindex,
			Integer index) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);

		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnselectPrePoint(TerminalId, Cameraindex, index);

		}
		// else {
		// // 本地 没这个设备，发给上级吧
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(
		// StateManageDomainProxy.selectPrePoint_Copy(TerminalId,
		// Cameraindex, index), null, null);
		// }

	}

	@Remoting
	public void setPrePoint(String TerminalId, Integer Cameraindex,
			Integer index) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);

		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnSetPrePoint(TerminalId, Cameraindex, index);

		} else {

		}

	}

	@Remoting
	public void deletePrePoint(String TerminalId, Integer Cameraindex,
			Integer index) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);

		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnDeletePrePoint(TerminalId, Cameraindex, index);

		} else {

		}

	}

	// 设置图像OSD
	@Remoting
	public void ControlVideoOSD(String TerminalId, Integer Cameraindex,
			Integer X, Integer Y, String OSDName, Boolean IsDisplyDatetime) {

		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				TerminalId);
		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnControlVideoOSD(TerminalId, Cameraindex, X, Y,
					OSDName, IsDisplyDatetime);

		}
		// else {
		// // 本地 没这个设备，发给上级吧
		// (StateManageDomain.AppRunTime()).LocalChannelSendMessage(
		// StateManageDomainProxy.ControlVideoOSD_Copy(TerminalId,
		// Cameraindex, X, Y, OSDName, IsDisplyDatetime),
		// null, null);
		// }

	}

	/**
	 * 要求设备服务器对设备的端口发送请求发送帧。
	 * */
	@Remoting
	public void FireDeviceShow(String DeviceId, Integer index) {

		// 根据点播源和通道，计算实际设备ID
		IMessage mg = NVMP.Proxy.VideoContrlDomainProxy.getURLFormDevice(
				DeviceId, index);
		if (mg != null) {
			String url = (String) mg.GetParam("formIP");
			DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(DeviceId);
			
			System.out.println("*****************ds.centerID = " + ds.centerID + "************");
			if (manageid == null) {
				System.out.println("**************manageid == null******************");
			}
			
			if (ds.centerID
					.equals(StateManageDomain.AppRunTime().getServerId())) {
				// 通知状态服务器。
				if (manageid != null) {
					(StateManageDomain.AppRunTime())
							.SetCurChannel("Local://Session://" + manageid);
					EncodeDeviceManage.OnFireDeviceShow(DeviceId, url,
							index);
				}

			} else {

				IMessage message = StateManageDomainProxy
						.GrobalFireDeviceShow_Copy(DeviceId, index,
								ds.getCenterID());

				(StateManageDomain.AppRunTime()).LocalChannelSendMessage(
						message, null, null, null);
			}

		}

	}

	@Remoting
	public void GrobalFireDeviceShow(String DeviceId, Integer index,
			String CenterID) {
		// 判断是否为指定的centerid，如果不是，不关之事，停止。
		if (!StateManageDomain.AppRunTime().getServerId().equals(CenterID)) {
			// 进行转发，考虑网状结构的存在。
			StateManageDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		if (manageid != null) {

			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Session://"
					+ manageid);

			// I帧跨级，查询对应的url
			IMessage mg = NVMP.Proxy.VideoContrlDomainProxy.getURLFormDevice(
					DeviceId, index);
			if (mg != null) {
				String url = (String) mg.GetParam("formIP");
				EncodeDeviceManage.OnFireDeviceShow(DeviceId, url, index);

			}

		}

	}

	// 接入服务器获取管理的编码设备：编号、厂家、型号、IP
	// 事件
	@Remoting
	public void GetEncodeDeviceInfo(String ClientId, String DeviceId) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);

		if (!sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnEncodeDeviceInfo(ClientId, DeviceId);

		}

	}

	/**
	 * 楼上的请求事件发送出去，那边处理好了，给我回来调用这个
	 * 
	 * */
	@Remoting
	public void RetrunEncodeDeviceInfoResult(String ClientId, String DeviceId,
			String Produce, String type, String IP) {

		(StateManageDomain.AppRunTime()).SetCurChannel("Session://" + ClientId);

		EncodeDeviceManage.OnRetrunEncodeDeviceInfoResult(ClientId, DeviceId,
				Produce, type, IP);

	}

	/**
	 * 获得图像的质量
	 * 
	 * */
	@Remoting
	public void GetVideoCompress(String ClientId, String DeviceId,
			Integer channl) {

		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);

		if (sessionid != null && !sessionid.equals("")) {
			// 本地有这个设备，直接触发事件。
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnGetVideoCompress(ClientId, DeviceId, channl);

		}

	}

	/**
	 * 获得图像质量后，调用该方法进行反馈。
	 * 
	 * */
	@Remoting
	public void RetrunGetVideoCompressResult(String ClientId, String DeviceId,
			Integer channl, Integer Brightness, Integer Saturation,
			Integer Hue, Integer Contrast) {

		(StateManageDomain.AppRunTime()).SetCurChannel("Session://" + ClientId);

		EncodeDeviceManage.OnRetrunGetVideoCompressResult(ClientId, DeviceId,
				channl, Brightness, Saturation, Hue, Contrast);

	}

	/**
	 * 本方法是用来进行对该设备当前的报警状态的查询
	 * 
	 * @param TerminalId
	 *            设备id
	 * @param ChannelId
	 *            通道id
	 * @param AlarmType
	 *            类型
	 * */
	@Remoting
	public Integer GetEncodeDeviceAlarm(String sessionid, String TerminalId,
			Integer ChannelId, Integer AlarmType) {

		int states = DAOFactory.getPojoImpl().getStatus(TerminalId, ChannelId,
				AlarmType);
		// System.out.println("states============"+states);
		if (states != -1 && sessionid != null) {
			(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
					+ sessionid);
			EncodeDeviceManage.OnReturnEncodeDeviceAlarm(TerminalId, ChannelId,
					AlarmType, states);
		} else {
			// 广播出去 ,暂时空在此处

		}
		return states;
	}

	/**
	 * 本方法是为了获得设备的上下线情况。
	 * 
	 * */
	@Remoting
	public DeviceStatus GetSingerEncodeDeviceOnline(String sessionid,
			String TerminalId) {

		DeviceStatus ds;
		try {
			// ds = DAOFactory.getDeviceStatusIntance().getDeviceStatus(
			// TerminalId, StateManageDomain.AppRunTime().getServerId());
			ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(TerminalId);

			// System.out.println(ds.getDevcieId() + " " + ds.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			ds = null;

		}

		return ds;
	}

	// 获取所有上线设备
	@Remoting
	public void GetEncodeDeviceOnline(String UserId) {
		// 将用户增加到编码设备状态订阅组，设备状态改变时主动发送
		(StateManageDomain.AppRunTime()).jionGroup(UserId, "G_device_state");
		// 通知已注册设备的上线状态

		// EncodeDeviceManage.OnEncodeDeviceOnline("test1", "0.0.0.0", true);
		// EncodeDeviceManage.OnEncodeDeviceOnline("test1", "0.0.0.0", true);

		// for (int i = 0; i < 1000; i++) {
		// (StateManageDomain.AppRunTime()).SetCurChannel("Session://" +
		// UserId);
		// EncodeDeviceManage.OnEncodeDeviceOnline("zzw", "zzw", false);
		// }

		List<DeviceStatus> l = DAOFactory.getPojoImpl().getAllDeivceStatus(-1);

		for (DeviceStatus ds : l) {
			try {
				(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
						+ UserId);
				EncodeDeviceManage
						.OnEncodeDeviceOnline(ds.getDevcieId(), ds.getDevIp(),
								ds.getDeviceStatus() == 1 ? true : false);

				// System.out.println(ds.getDevcieId()+ds.getDevIp()+ds.getDeviceStatus()
				// );
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 增加报警组
	@Remoting
	public void AddAlarmGroup(String UserId) {
		(StateManageDomain.AppRunTime()).jionGroup(UserId, "G_device_alarm");

	}

	// 其它中心发过来的设备登录通知；
	@Remoting
	public void GrobalSetEncodeDeviceOnline(String TerminalId,
			String TerminalIP, Boolean IsOnline, Integer DeviceType,
			Integer DevuceSubType, String xml, String centerid) {
		// // 先判断是否为xml消息
		// if (xml != null && !xml.equals("")) {
		//
		// // 此处用代理类处理。
		//
		// XmlToInsert xmlToInsert = XmlToInsert.getXmlToInsert();
		// xmlToInsert.xmlToInsert(xml);
		//
		// return;
		// }

		String ErrInfo = "设备上下线处理错误：";

		try {
			String s = String.format(
					"****从其它中心收到编码设备上下线：设备编号=%s. IP=%s. 是否上线=%s",
					TerminalId == null ? "" : TerminalId,
					TerminalIP == null ? "" : TerminalIP,
					(IsOnline == null ? "true" : IsOnline).toString());
			ErrInfo = ErrInfo + s;
			LogUtil.BusinessDebug(s);
			// 对本地数据库的设备信息进行更改。

			DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(TerminalId);
			if (ds == null)
				ds = new DeviceStatus();

			ds.setDevcieId(TerminalId);
			ds.setDevIp(TerminalIP);
			ds.setDeviceStatus(IsOnline == true ? 1 : 0);
			if (DevuceSubType != null && DeviceType != -1) {
				ds.setDevType(DeviceType);
			}
			if (DevuceSubType != null && DevuceSubType != -1) {
				ds.setDevSubType(DevuceSubType);
			}

			// 认为数据来源就是该中心ID
			// String centerid = StateManageDomain.AppRunTime().getSource();

			LogUtil.BusinessDebug("((((((((((()))))))))))) 来源是" + TerminalId
					+ "  |   " + centerid);

			ds.setCenterID(centerid);

			DAOFactory.getPojoImpl().updateInfo(ds);

			// // 客服端发送一群
			// (StateManageDomain.AppRunTime())
			// .LocalChannelSendMessage(StateManageDomainProxy
			// .SetEncodeDeviceOnline_Copy(TerminalId, TerminalIP,
			// IsOnline, DeviceType, DevuceSubType),null,null);

			// 记录上下线情况，以后，凡是上下线的问题，都可以在此处询问。

			// 通知组内成员状态改变

//			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
//					+ "G_device_state");
//			EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
//					IsOnline);

			
			toClientMessage(TerminalId, TerminalIP,
					IsOnline);
			
			// 本地查询
			// DAOFactory.getPojoImpl().getChannel(devId, typeid);
			// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
			VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
					TerminalIP, IsOnline);
			// if (IsOnline) {
			// VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
			// TerminalIP, IsOnline);
			// }

		} catch (Exception e) {
			LogUtil.BusinessError("wo cao" + e.toString());
			// LogUtil.BusinessError(ErrInfo);
		}
		StateManageDomain.AppRunTime().setContinueFlag(true);

	}

	// 上报设置设备上线或下线
	@Remoting
	public void GrobalSetEncodeDeviceOnline_Sync(String TerminalId,
			String TerminalIP, Boolean IsOnline, Integer DeviceType,
			Integer DevuceSubType, String xml, String centerid) {
		if (xml != null && !xml.equals("")) {
			return;
		}

		try {
			// 对本地数据库的设备信息进行更改。
			DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatus(TerminalId);
			if (ds == null)
				ds = new DeviceStatus();

			ds.setDevcieId(TerminalId);
			ds.setDevIp(TerminalIP);
			ds.setDeviceStatus(0);
			if (DevuceSubType != null && DeviceType != -1) {
				ds.setDevType(DeviceType);
			}
			if (DevuceSubType != null && DevuceSubType != -1) {
				ds.setDevSubType(DevuceSubType);
			}
			DAOFactory.getPojoImpl().updateInfo(ds);

			// 本地查询
			// DAOFactory.getPojoImpl().getChannel(devId, typeid);
			// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
			VideoContrlDomainProxy.SetEncodeDeviceOnline(TerminalId,
					TerminalIP, false);

			// 通知组内成员状态改变
			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
					+ "G_device_state");
			EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
					false);

		} catch (Exception e) {
			LogUtil.BusinessError(e.toString());
			// LogUtil.BusinessError(ErrInfo);
		}

	}

	public void setCenterOnLine(String sessionid) {

		LogUtil.BusinessInfo("===*** I See Center drop" + sessionid);
		// 找出所有上线的
		List<DeviceStatus> l = DAOFactory.getDeviceStatusIntance()
				.getDeviceStatusByCenterId(sessionid);
		for (DeviceStatus ds : l) {
			if (ds.getDeviceStatus() != 1) {
				continue;
			}
			ds.setDeviceStatus(0);
			DAOFactory.getPojoImpl().updateInfo(ds);

			// 通知视频交换模块设备上线或下线，为了视频交换模块恢复初始状态
			VideoContrlDomainProxy.SetEncodeDeviceOnline(ds.getDevcieId(),
					ds.getDevIp(), false);

			// 通知组内成员状态改变
			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
					+ "G_device_state");
			EncodeDeviceManage.OnEncodeDeviceOnline(ds.getDevcieId(),
					ds.getDevIp(), false);

		}

	}

	// 设置通知管理设置设备的各种时间。
	@Remoting
	public void SetDeviceTime(Integer year, Integer month, Integer day,
			Integer hour, Integer minute, Integer scond) {
		// 通知状态服务器。
		if (manageid != null) {
			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Session://"
					+ manageid);
			EncodeDeviceManage.OnSetDeviceTime(year, month, day, hour, minute,
					scond);
		}

	}

	// 每过10分钟，通知一次全局状态。
	@Override
	public void run() {
		while (true) {
			int n = 10;
			try {
				Thread.sleep(n * 60 * 1000);
				// Thread.sleep(10* 1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			List<DeviceStatus> l = DAOFactory.getDeviceStatusIntance()
					.getDeviceStatusByCenterId(
							StateManageDomain.AppRunTime().getServerId());
			for (DeviceStatus ds : l) {
				// if (ds.getDeviceStatus() == 1) {
				IMessage message = StateManageDomainProxy
						.GrobalSetEncodeDeviceOnline_Copy(ds.getDevcieId(), ds
								.getDevIp(), ds.getDeviceStatus() == 1 ? true
								: false, ds.getDevType(), ds.getDevSubType(),
								null, StateManageDomain.AppRunTime()
										.getServerId());
				StateManageDomain.AppRunTime().LocalUpSendMessage(message,
						null, null);

				// StateManageDomain.AppRunTime().LocalChannelSendMessage(message,
				// null, StateManageDomain.AppRunTime().getServerId(),
				// null);
				// }

			}

			for (String centerid : OtherCenterSet) {
				l = DAOFactory.getDeviceStatusIntance()
						.getDeviceStatusByCenterId(centerid);
				for (DeviceStatus ds : l) {
					// if (ds.getDeviceStatus() == 1) {
					IMessage message = StateManageDomainProxy
							.GrobalSetEncodeDeviceOnline_Copy(ds.getDevcieId(),
									ds.getDevIp(),
									ds.getDeviceStatus() == 1 ? true : false,
									ds.getDevType(), ds.getDevSubType(), null,
									StateManageDomain.AppRunTime()
											.getServerId());
					StateManageDomain.AppRunTime().LocalUpSendMessage(message,
							null, null);

					// StateManageDomain.AppRunTime().LocalChannelSendMessage(message,
					// null, StateManageDomain.AppRunTime().getServerId(),
					// null);
					// }

				}

			}

		}

	}

	HashSet<String> hs;
	String manageid;

	// 声明自己是设备管理服务器
	@Remoting
	public void LoginState(String sessionid) {
		// 目前我们就认为只有一个设备管理服务器。
		System.out.println("==========设备管理器登陆" + sessionid);
		manageid = sessionid;
	}

	public void logoutState(String sessionid) {
		if (manageid != null && manageid.equals(sessionid)) {
			System.out.println("==========设备管理器下线" + sessionid);
			manageid = null;
			for (String str : hs) {
				// 模拟下线：
				this.SetEncodeDeviceOnline(str, "", false, -1, -1, null);
			}
			hs.clear();
		}

	}

	// 模拟设备下线
	@Remoting
	public void SetDeviceOutLine(String Client, String Deviceid, String center) {

		if (StateManageDomain.AppRunTime().getServerId().equals(center) || OtherCenterSet.contains(center)) {

			DeviceStatus ds = GetSingerEncodeDeviceOnline("", Deviceid);
			if (ds != null) {
				this.SetEncodeDeviceOnline(ds.getDevcieId(), ds.getDevIp(),
						false, -1, -1, null);

				// 添加的刷新前置服务器
				VideoContrlDomainProxy.refreshPreFoward(Deviceid);
			}

		} else {
			// 发送出去了。
			IMessage message = StateManageDomainProxy
					.GrobalSetDeviceOutLine_Copy(Client, Deviceid, center);
			(StateManageDomain.AppRunTime()).LocalChannelSendMessage(message,
					center, null, null);

		}
	}

	//分辨率
	@Remoting
	public void getResolution(String ClientId, String DeviceId,
			Integer channl) {
		//XXX
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		EncodeDeviceManage.OnGetResolution(ClientId, DeviceId, channl);

		
	}
	
	
	@Remoting
	public void setResolution(String ClientId, String DeviceId,
			Integer channl,Integer  nResolutionX,Integer  nResolutionY) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		EncodeDeviceManage.OnSetResolution(ClientId, DeviceId, channl,nResolutionX,nResolutionY);
		
		
	}
	
	@Remoting
	public void returnResolutionResult(String ClientId, String DeviceId,
			Integer channl,Integer  nResolutionX,Integer nResolutionY) {
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ ClientId);
		EncodeDeviceManage.onReturnResolutionResult(ClientId, DeviceId, channl,nResolutionX,nResolutionY);

	} 
	
	
	
	
	//帧率
	@Remoting
	public void getFrameRate(String ClientId, String DeviceId,
			Integer channl) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		
		EncodeDeviceManage.onGetFrameRate(ClientId,DeviceId,channl);
		
		
	}
	@Remoting
	public void setFrameRate(String ClientId, String DeviceId,
			Integer channl,Integer nFramerate) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		
		EncodeDeviceManage.onSetFrameRate(ClientId,DeviceId,channl,nFramerate);
		
	}
	@Remoting
	public void returnFrameRateResult(String ClientId, String DeviceId,
			Integer channl,Integer nFramerate){
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ ClientId);
		
		EncodeDeviceManage.onReturnFrameRateResult(ClientId,DeviceId,channl,nFramerate);
	}
	
	//码率
	@Remoting
	public void getBitRate(String ClientId, String DeviceId,
			Integer channl) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		
		EncodeDeviceManage.onGetBitRate(ClientId,DeviceId,channl);
		
	}
	@Remoting
	public void setBitRate(String ClientId, String DeviceId,
			Integer channl,Integer nBitrate) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		EncodeDeviceManage.onSetBitRate(ClientId,DeviceId,channl,nBitrate);
		
	}
	
	@Remoting
	public void returnBitRateResult(String ClientId, String DeviceId,
			Integer channl,Integer nBitrate){
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ ClientId);
		EncodeDeviceManage.onReturnBitRateResult(ClientId,DeviceId,channl,nBitrate);
		
	}
	
	
	//I帧间隔
	@Remoting
	public void getFrameInterval(String ClientId, String DeviceId,
			Integer channl) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		EncodeDeviceManage.onGetFrameInterval(ClientId, DeviceId,
				channl);
		
	}
	
	@Remoting
	public void setFrameInterval(String ClientId, String DeviceId,
			Integer channl,Integer nGovLength) {
		String sessionid = DAOFactory.getDeviceStatusIntance().getCenterID(
				DeviceId);
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ sessionid);
		
		EncodeDeviceManage.onSetFrameInterval(ClientId, DeviceId,
				channl,nGovLength);
	}
	@Remoting
	public void returnFrameInterval(String ClientId, String DeviceId,
			Integer channl,Integer nGovLength){
		(StateManageDomain.AppRunTime()).SetCurChannel("Session://"
				+ ClientId);
		EncodeDeviceManage.onReturnFrameInterval(ClientId, DeviceId,
				channl,nGovLength);
	}
	
	
	
	
	// 跨级模拟设备下线
	@Remoting
	public void GrobalSetDeviceOutLine(String Client, String Deviceid,
			String center) {
		if (!StateManageDomain.AppRunTime().getServerId().equals(center)  && !OtherCenterSet.contains(center)) {
			// 进行转发，考虑网状结构的存在。
			StateManageDomain.AppRunTime().setContinueFlag(true);
			return;
		}

		// 模拟下线。
		DeviceStatus ds = GetSingerEncodeDeviceOnline("", Deviceid);
		if (ds != null) {
			this.SetEncodeDeviceOnline(ds.getDevcieId(), ds.getDevIp(), false,
					-1, -1, null);

			// 添加的刷新前置服务器
			VideoContrlDomainProxy.refreshPreFoward(Deviceid);

		}
	}

	private Set<String> OtherCenterSet = new java.util.concurrent.CopyOnWriteArraySet<String>();

	// 通知资源管理服务器，对center中心的数据进行接管,2210功能，2P禁止
	public void controlOtherSource(String centerid, Boolean flag) {
//		LogUtil.error("manageid :" + manageid);
//		if (flag) {
//			OtherCenterSet.add(centerid);
//		} else {
//			OtherCenterSet.remove(centerid);
//			List<DeviceStatus> l = DAOFactory.getDeviceStatusIntance()
//					.getDeviceStatusByCenterId(centerid);
//			for (DeviceStatus ds : l) {
//				// if (ds.getDeviceStatus() == 1) {
//				IMessage message = StateManageDomainProxy
//						.GrobalSetEncodeDeviceOnline_Copy(ds.getDevcieId(),
//								ds.getDevIp(), false, ds.getDevType(),
//								ds.getDevSubType(), null, centerid);
//				StateManageDomain.AppRunTime().LocalUpSendMessage(message,
//						null, null);
//
//				// StateManageDomain.AppRunTime().LocalChannelSendMessage(message,
//				// null, StateManageDomain.AppRunTime().getServerId(),
//				// null);
//				// }
//
//			}
//
//		}
//
//		if (manageid != null) {
//			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Session://"
//					+ manageid);
//			EncodeDeviceManage.OnControlOtherSource(centerid, flag);
//		}

	}

	Map<String, Long> deviceTImeSet;

	final long waitTime = 10000000000l;

	// 建立通知所有客户端方法, 掉线则立刻通知，否则进行延迟
	public void toClientMessage(String TerminalId, String TerminalIP,
			Boolean IsOnline) {
		// 判断时间差，如果时间过短，则进行延迟发送。采用队列进行发送.
		long now = System.nanoTime();
		// long old = deviceTImeSet.get(TerminalId);
		boolean b = false;
		if (!IsOnline) {
			b = true;
			deviceTImeSet.put(TerminalId, now);
		} else {

			Long old = deviceTImeSet.get(TerminalId);
			if (old == null) {
				b = true;
				deviceTImeSet.put(TerminalId, now);
			} else {
				if ((now - old) >= waitTime) {
					b = true;
					deviceTImeSet.put(TerminalId, now);
				} else {
					deviceTImeSet.put(TerminalId, now + waitTime);
				}
			}

		}

		if (b) {
			deviceTImeSet.remove(TerminalId);
			(StateManageDomain.AppRunTime()).SetCurChannel("Local://Group://"
					+ "G_device_state");
			EncodeDeviceManage.OnEncodeDeviceOnline(TerminalId, TerminalIP,
					IsOnline);
		}
	}

	class ToClientMessage extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(waitTime / 2000000);
					long now = System.nanoTime();
					for (Entry<String, Long> en : deviceTImeSet.entrySet()) {
						if (en.getValue() < now
								&& (now - (waitTime / 2)) <= en.getValue()) {
							DeviceStatus ds = DAOFactory
									.getDeviceStatusIntance().getDeviceStatus(
											en.getKey());
							(StateManageDomain.AppRunTime())
									.SetCurChannel("Local://Group://"
											+ "G_device_state");
							EncodeDeviceManage.OnEncodeDeviceOnline(
									ds.getDevcieId(), ds.getDevIp(),
									ds.getDeviceStatus() == 1 ? true : false);
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

}
