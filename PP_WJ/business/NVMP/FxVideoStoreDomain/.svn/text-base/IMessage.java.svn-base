package NVMP.FxVideoStoreDomain;

import NVMP.AppService.Remoting;
/**
 * 视频存储的通知事件信息
 * */
@Remoting
public class IMessage {

	
	/**
	 * 反馈录像成功或失败情况
	 * @param context  发起者的上下文环境
	 * @param result   ture 成功  false 失败
	 * @param reason	原因
	 */
	public void onStartRecordResult(String context, Boolean result,
			String reason) {
	}

	/**
	 * 查询返回的结果集
	 * @param context  查询发送的上下文
	 * @param json 查询的结果集
	 */
	public void onFoundResult(String context, String json) {
	}

	/**
	 * 发送给服务器，进行录像请求
	 * @param deviceID 设备ID
	 * @param channel	通道号	
	 * @param carID	发起录像ID
	 * @param carName	录像者名称
	 * @param context	录像发起的上下文
	 */
	public void onServerStartRecord(String deviceID, Integer channel,
			String carID, String carName, String context) {
	}

	/**
	 * 发送给服务器，进行设备点播停止。
	 * @param carID
	 * @param context
	 */
	public void onServerStopRecord(String carID, String context) {
	}

	/***
	 * 发送给服务器，进行录像查询
	 * @param carID
	 * @param deviceID
	 * @param channel
	 * @param startTime
	 * @param endTime
	 * @param context
	 */
	public void onServerFound(String carID, String deviceID, Integer channel,
			String startTime, String endTime, String context) {
	}

	/**
	 * 人员下线通知
	 * @param clientID
	 */
	public void onClientOffLine(String clientID) {
		
	}

	/**
	 * 存储服务下线通知。
	 */
	public void onServerOffLine() {
	}
	
	
	
	
}
