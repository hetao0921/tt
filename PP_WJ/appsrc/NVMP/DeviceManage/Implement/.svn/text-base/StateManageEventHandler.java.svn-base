package NVMP.DeviceManage.Implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.misc.log.LogUtil;

//import com.fxdigital.EcDevice.ctrl.AlarmType;





import com.fxdigital.EcDevice.ctrl.VideoEffect;
import com.fxdigital.bean.NvmpOriginalbitrate;
import com.fxdigital.bean.NvmpOriginalbitrateId;
import com.fxdigital.onvif.ctrl.VideoCompress;


import com.mysql.impl.Hibernate;

import NVMP.DeviceManage.Implement.devicectrl.DeviceCtrlManager;
import NVMP.DeviceManage.Implement.devicectrl.ExecutorServiceSingleton;
import NVMP.DeviceManage.Implement.devicectrl.HttpRequest;
import NVMP.DeviceManage.Implement.devicectrl.RTSPClient;
import NVMP.Proxy.StateManageDomain.EventHandler;

//import NVMP.test.Client.Implement.ClientView;

public class StateManageEventHandler extends EventHandler {

	// 给予其上下文环境，免得跑来跑去
	private IDeviceRun dr;

	public void setDr(IDeviceRun dr) {
		this.dr = dr;

	}
	
	
	ExecutorServiceSingleton ess = ExecutorServiceSingleton.getInstace();
	
	/**
	 * 当获取上下线事件的时候，就向表格中插入一条数据
	 */
	@Override
	public Object EncodeDeviceOnlineEvent(java.lang.String TerminalId,
			java.lang.String TerminalIP, java.lang.Boolean IsOnline) {

		// 要判断是否为本级负责的
		if (dr.getDeviceHp().containsKey(TerminalId))
			dr.putOnline(TerminalIP, IsOnline);
		return null;
	}

	/**
	 * 当设备状态更改（布防、撤防、报警）时，向表格中插入一条数据 如果获取的设备和当前文本框中的设备信息是同一条信息，则更新文本框中的内容
	 */
	@Override
	public Object AlarmStateEvent(String TerminalId, Integer ChannelId,
			Integer AlarmType, Integer DeviceStatus) {

		dr.insertAlarm(TerminalId, ChannelId, AlarmType, DeviceStatus);

		return null;
	}

	@Override
	public Object ReturnEncodeDeviceAlarmEvent(java.lang.String terminalId,
			java.lang.Integer channelId, java.lang.Integer alarmType,
			java.lang.Integer states) {

		dr.showNodeMsg(terminalId, channelId, alarmType, states);

		return null;
	}

	/**
	 * 控制布防和撤防
	 * 
	 * */
	public Object ControlAlarmStateEvent(String terminalId, Integer index,
			Integer alarmType, Boolean isStart) {
		//

		// 根据terminalId 找到 DeviceInstance

		boolean b = false;
		System.out.println(terminalId);
		DeviceInstance di = null;
		Map<String, DeviceInstance> hp = dr.getDeviceHp();
		DeviceCtrlManager dc = dr.getDc();
		try {
			di = hp.get(terminalId);
			// // 对其进行操作
			// System.out.println(di.getDeviceHandle()+"  "+index+"  "+alarmType+" "+isStart);
			b = dc.ctrlAlarm(di.getDeviceType(), di.getDeviceHandle(), index,
					alarmType, isStart == true ? 1 : 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 将操作结果上 报应用服务器,同时改变自己对应的设备通道状态。
		if (b) {
			dr.getSmd().SetAlarmState(terminalId, index, alarmType,
					isStart == true ? 1 : 0);
			// 1 移动侦测 2 视屏丢失 3 报警点 4 输出点

			// if (alarmType == AlarmType.AlarmPoint) {
			// AlrmPointChannel ap = (AlrmPointChannel) di.getChannel(
			// di.getDeviceid(), index, "AlrmPoint");
			// ap.setDeviceStatus(isStart == true ? 1 : 0);
			//
			// } else if (alarmType == AlarmType.OutPoint) {
			// OutPointChannel oc = (OutPointChannel) di.getChannel(
			// di.getDeviceid(), index, "OutPoint");
			// oc.setDeviceStatus(isStart == true ? 1 : 0);
			//
			// } else if (alarmType == AlarmType.Motion) {
			// CanmelChannel cc = (CanmelChannel) di.getChannel(
			// di.getDeviceid(), index, "Canmel");
			// cc.setMotionStaus(isStart == true ? 1 : 0);
			//
			// } else if (alarmType == AlarmType.VideoLost) {
			// CanmelChannel cc2 = (CanmelChannel) di.getChannel(
			// di.getDeviceid(), index, "Canmel");
			// cc2.setVideoLost(isStart == true ? 1 : 0);
			//
			// }

		}

		return null;
	}

	/**
	 * 控制云台
	 * 
	 * 
	 * */
	public Object ControlCameraPTZEvent(String terminalId, Integer cameraindex,
			Integer direction, Integer speed, Boolean isStart) {
		//

		// 根据terminalId 找到 DeviceInstance
		DeviceInstance di = dr.getDeviceHp().get(terminalId);

		// 对其进行操作 注意：这里面，如果是true，我们是传0，如果是false ，我们传1.
		System.out.println("=====================kkkkkkkkkkkkkkkkkkkkk==================");
		System.out.println("speed = " + speed);
		System.out.println("direction = " + direction);
		
		dr.getDc().ctrlPTZ(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, speed, direction, isStart == true ? 0 : 1);

		return null;
	}

	/**
	 * 设置图像设置
	 * */
	public Object ControlVideoQualityEvent(String terminalId,
			Integer cameraindex, Integer type, Integer value) {
		//
		// 根据terminalId 找到 DeviceInstance
		DeviceInstance di = dr.getDeviceHp().get(terminalId);

		// 对其进行操作 注意：这里面，如果是true，我们是传0，如果是false ，我们传1.
		dr.getDc().ctrlVideoEffect(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, type, value);

		return null;
	}

	/**
	 * 设置OSD
	 */
	public Object ControlVideoOSDEvent(String terminalId, Integer cameraindex,
			Integer x, Integer y, String oSDName, Boolean isDisplyDatetime) {
		// 根据terminalId 找到 DeviceInstance
		DeviceInstance di = dr.getDeviceHp().get(terminalId);

		// 对其进行操作 注意：这里面，如果是true，我们是传0，如果是false ，我们传1.
		dr.getDc().setOSD(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, isDisplyDatetime, true, x, y, oSDName);

		return null;
	}

	@Override
	public Object EncodeDeviceInfoEvent(String clientId, String deviceId) {

		// 查询该相关信息，调用方法，把结果返回。

		return super.EncodeDeviceInfoEvent(clientId, deviceId);
	}

	@Override
	public Object selectPrePointEvent(String terminalId, Integer cameraindex,
			Integer index) {
		DeviceInstance di = dr.getDeviceHp().get(terminalId);
		dr.getDc().selectPrePoint(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, index);
		return null;
	}

	@Override
	public Object SetPrePointEvent(String terminalId, Integer cameraindex,
			Integer index) {
		DeviceInstance di = dr.getDeviceHp().get(terminalId);
		dr.getDc().setPrePoint(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, index);
		return null;
	}

	@Override
	public Object DeletePrePointEvent(String terminalId, Integer cameraindex,
			Integer index) {
		DeviceInstance di = dr.getDeviceHp().get(terminalId);
		dr.getDc().deletePrePoint(di.getDeviceType(), di.getDeviceHandle(),
				cameraindex, index);
		return null;
	}

	@Override
	public Object GetVideoCompressEvent(String clientId, String deviceId,
			Integer channl) {
		try {
			DeviceInstance di = dr.getDeviceHp().get(deviceId);
			VideoEffect a = new VideoEffect();
			boolean b = dr.getDc().getVideoEffect(di.getDeviceType(),
					di.getDeviceHandle(), channl, a);
			if (b) {

				System.out.println("Brightness = " + a.Brightness);
				System.out.println("Contrast = " + a.Contrast);
				System.out.println("Hue = " + a.Hue);
				System.out.println("Saturation = " + a.Saturation);

				dr.getSmd().RetrunGetVideoCompressResult(clientId, deviceId,
						channl, a.Brightness, a.Saturation, a.Hue, a.Contrast);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Object FireDeviceShowEvent(String deviceId, String deviceSource,
			Integer index) {

		System.out.println("=============要求设备发送I帧 " + deviceId + " index:"
				+ index + " deviceSource :" + deviceSource);
		try {
			DeviceInstance di = dr.getDeviceHp().get(deviceId);
			System.out.println(di.getHostIP());
			// need to check rtspclient is null or not in future
			// need to handle with forward more then once situation
//			if (deviceSource.startsWith("rtsp://")) {
//				// && di.getDeviceType() != 222
//				new RTSPClient(deviceSource).start();
//				if (di.getDeviceType() != 222)
//					return null;
//			}
			
			if (deviceSource.startsWith("rtsp://")) {
				int deviceType = di.getDeviceType();
				System.out.println("===========deviceType is " + deviceType + "===========");
				if (deviceType == 222) {
					new RTSPClient(deviceSource).start();
				} else if (deviceType == 224) {
//					new HttpRequest(deviceSource).sendGetRequest();
					
					ess.sendGetRequest(deviceSource);
				} else {
					return null;
				}
			}
			
			//
			dr.getDc().MakeKeyFrame(di.getDeviceType(), di.getDeviceHandle(),
					index, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object SetDeviceTimeEvent(Integer year, Integer month, Integer day,
			Integer hour, Integer minute, Integer scond) {
		// 要进行时间同步。
		dr.setTime(year, month, day, hour, minute, scond);
		return null;
	}

	// 通知控制/释放某个中心下的资源,2210特殊功能，本初不使用
	@Override
	public Object ControlOtherSourceEvent(String centerid, Boolean flag) {
//		LogUtil.DeviceManageInfo("======+ in Control  OtherSource  Event  "
//				+ centerid + " " + flag);
//
//		if (flag) {
//			dr.addCenterSource(centerid);
//		} else {
//			dr.removeCenterSource(centerid);
//		}
		return null;
	}

	// 分辨率
	@Override
	public Object GetResolutionEvent(String clientId, String deviceId,
			Integer channl) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);

		VideoCompress vc = new VideoCompress();

		boolean b = dr.getDc().getVideoCompress(di.getDeviceType(),
				di.getDeviceHandle(), channl, vc);
		if (b) {
			dr.getSmd().returnResolutionResult(clientId, deviceId, channl,
					vc.getM_nResolutionX(), vc.getM_nResolutionY());
		}

		return null;
	}

	@Override
	public Object SetResolutionEvent(String clientId, String deviceId,
			Integer channl, Integer nResolutionX, Integer nResolutionY) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);
		VideoCompress vc = new VideoCompress();
		vc.setM_nBitrate(-1);
		vc.setM_nCodecType(-1);
		vc.setM_nFramerate(-1);
		vc.setM_nQuality(-1);
		vc.setM_nResolution(nResolutionX, nResolutionY);
		vc.setM_nGovLength(-1);

		dr.getDc().setVideoCompress(di.getDeviceType(), di.getDeviceHandle(),
				channl, vc);

		return null;
	}

	// 帧率
	@Override
	public Object onGetFrameRateEvent(String clientId, String deviceId,
			Integer channl) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);

		VideoCompress vc = new VideoCompress();
		boolean b = dr.getDc().getVideoCompress(di.getDeviceType(),
				di.getDeviceHandle(), channl, vc);
		if (b) {
			dr.getSmd().returnFrameRateResult(clientId, deviceId, channl,
					vc.getM_nFramerate());
		}

		return null;
	}

	@Override
	public Object onSetFrameRateEvent(String clientId, String deviceId,
			Integer channl, Integer nFramerate) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);
		VideoCompress vc = new VideoCompress();
		vc.setM_nBitrate(-1);
		vc.setM_nCodecType(-1);
		vc.setM_nQuality(-1);
		vc.setM_nResolution(-1, -1);
		vc.setM_nFramerate(nFramerate);
		vc.setM_nGovLength(-1);
		dr.getDc().setVideoCompress(di.getDeviceType(), di.getDeviceHandle(),
				channl, vc);
		return null;
	}

	// 码率
	@Override
	public Object onGetBitRateEvent(String clientId, String deviceId,
			Integer channl) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);

		VideoCompress vc = new VideoCompress();
		boolean b = dr.getDc().getVideoCompress(di.getDeviceType(),
				di.getDeviceHandle(), channl, vc);
		if (b) {
			dr.getSmd().returnBitRateResult(clientId, deviceId, channl,
					vc.getM_nBitrate());
			
			String tableName = "NvmpOriginalbitrate";
			String columnName = "new Map(originalBitrate as originalBitrate)";
			String sql = "select " + columnName + " from " + tableName + " where id.deviceId = '" + deviceId + "' and id.channel = " + channl;
			List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(sql);
			if (list.isEmpty()) {
				NvmpOriginalbitrate nv=new NvmpOriginalbitrate();
				NvmpOriginalbitrateId id=new NvmpOriginalbitrateId();
				id.setDeviceId(deviceId);
				id.setChannel(channl);
				nv.setId(id);
				nv.setOriginalBitrate(vc.getM_nBitrate());
//				sql = "insert into " + tableName + " values ('" + deviceId + "', " + channl + ", " + vc.getM_nBitrate() + ")";
//				db.updateInfo(sql);
				Hibernate.getHibernate().save(nv);
			}
		}

		return null;
	}
	

	public Object onSetBitRateEvent(String clientId, String deviceId,
			Integer channl, Integer nBitrate) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);
		VideoCompress vc = new VideoCompress();

		vc.setM_nCodecType(-1);
		vc.setM_nQuality(-1);
		vc.setM_nResolution(-1, -1);
		vc.setM_nFramerate(-1);
		vc.setM_nBitrate(nBitrate);
		vc.setM_nGovLength(-1);

		dr.getDc().setVideoCompress(di.getDeviceType(), di.getDeviceHandle(),
				channl, vc);
		
		String tableName = "NvmpDeviceplayinginfo";
		String columnName = "new Map(bitrate as bitrate)";
		String sql = "select " + columnName + " from " + tableName + " where id.deviceId = '" + deviceId + "' and id.channel = " + channl;
		List<HashMap<String, String>> list =Hibernate.getHibernate().createQuery(sql);
		
		if (!list.isEmpty()) {
			sql = "update " + tableName + " set bitrate = " + vc.getM_nBitrate() + " where id.deviceId = '" + deviceId + "' and id.channel = " + channl;
			Hibernate.getHibernate().deleteOrUpdate(sql, null);
		}
		
		return null;
	}
	


	// I帧间隔

	public Object onGetFrameIntervalEvent(String clientId, String deviceId,
			Integer channl) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);

		VideoCompress vc = new VideoCompress();
		boolean b = dr.getDc().getVideoCompress(di.getDeviceType(),
				di.getDeviceHandle(), channl, vc);
		System.out.println(vc.getM_nGovLength());
		if (b) {
			dr.getSmd().returnFrameInterval(clientId, deviceId, channl, vc.getM_nGovLength());
		}

		return null;
	}

	public Object onSetFrameIntervalEvent(String clientId, String deviceId,
			Integer channl, Integer nGovLength) {
		DeviceInstance di = dr.getDeviceHp().get(deviceId);
		VideoCompress vc = new VideoCompress();

		vc.setM_nCodecType(-1);
		vc.setM_nQuality(-1);
		vc.setM_nResolution(-1, -1);
		vc.setM_nFramerate(-1);
		vc.setM_nBitrate(-1);
		vc.setM_nGovLength(nGovLength);

		dr.getDc().setVideoCompress(di.getDeviceType(), di.getDeviceHandle(),
				channl, vc);
		return null;
	}
}
