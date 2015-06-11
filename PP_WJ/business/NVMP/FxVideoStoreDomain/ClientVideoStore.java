package NVMP.FxVideoStoreDomain;

import NVMP.AppService.Remoting;

/**
 * 客户端视频存储业务
 * 
 * @author fx-zzw
 * 
 */
public class ClientVideoStore {

	public IMessage message;

	private String videoStoreServerID;

	/**
	 * 发起录像
	 * 
	 * @param deviceID
	 *            录像设备ID
	 * @param channel
	 *            录像设备通道
	 * @param carID
	 * @param carName
	 * @param context
	 * @return 返回一个上下文，该值表示了一个录像操作的开始。
	 */
	@Remoting
	public void startRecord(String deviceID, Integer channel, String carID,
			String carName, String context) {
		// 根据存储服务器在线情况进行处理。
		if (videoStoreServerID == null) {
			FxVideoStoreDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + carID);
			message.onStartRecordResult(context, false, "存储服务器未上线");
		} else {
			FxVideoStoreDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + videoStoreServerID);
			message.onServerStartRecord(deviceID, channel, carID, carName,
					context);
		}

	}

	/**
	 * 关闭录像
	 * 
	 * @param handleValue
	 *            根据录像成功时，得到的操作值，进行处理
	 */
	@Remoting
	public void stopRecord(String carID, String context) {
		if (videoStoreServerID != null) {
			FxVideoStoreDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + videoStoreServerID);
			message.onServerStopRecord(carID, context);
		}
	}

	/**
	 * 车辆下线和管理者下线。
	 * 
	 * @param carID
	 */
	public void offLineCar(String clientID) {
		if (videoStoreServerID != null) {
			if (videoStoreServerID.equals(clientID)) {
				//管理下线，通知所有客户端。
				FxVideoStoreDomain.AppRunTime().SetCurChannel(
						"Local://ALL");
				message.onServerOffLine();
			} else {
				FxVideoStoreDomain.AppRunTime().SetCurChannel(
						"Local://Session://" + videoStoreServerID);
				message.onClientOffLine(clientID);
			}
		}
	}

	/***
	 * 查询录像信息
	 * 
	 * @param carID
	 *            查询的客户端ID
	 * @param deviceID
	 *            查询设备ID
	 * @param channel
	 *            查询通道号
	 * @param startTime
	 *            查询起始时间
	 * @param endTime
	 *            查询结束时间
	 */
	@Remoting
	public void foundRecord(String carID, String deviceID, Integer channel,
			String startTime, String endTime, String context) {

		if (videoStoreServerID != null) {
			FxVideoStoreDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + videoStoreServerID);
			message.onServerFound(carID, deviceID, channel, startTime, endTime,
					context);
		} else {
			FxVideoStoreDomain.AppRunTime().SetCurChannel(
					"Local://Session://" + carID);
			message.onFoundResult(context, "");
		}

	}

	/**
	 * 存储服务器登陆
	 * 
	 * @param sessionID
	 *            服务器ID
	 */
	@Remoting
	public void videoStoreLogin(String sessionID) {
		videoStoreServerID = sessionID;
	}

}
