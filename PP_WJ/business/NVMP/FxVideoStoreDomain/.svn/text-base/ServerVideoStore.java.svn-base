package NVMP.FxVideoStoreDomain;

import NVMP.AppService.Remoting;

/**
 * 服务端视频存储业务
 * @author fx-zzw
 *
 */
public class ServerVideoStore {

	public IMessage message;
	
	/**
	 * 发起录像的结果,包括点播成功、失败、中间异常
	 * @param carID 发起录像ID
	 * @param context 客户端点播的上下文
	 * @param result	   点播结果
	 * @param reson	   失败原因
	 */
	@Remoting
	public void  startRecordResult( String carID,String context,Boolean  result,String reason){
		FxVideoStoreDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + carID);
		message.onStartRecordResult(context, result, reason);
	}
	/**
	 * 返回查询结果
	 * @param carID 查询者ID
	 * @param context	查询上下文
	 * @param json	查询结果
	 */
	@Remoting
	public void foundRecordResult(String carID,String context,String json){
		FxVideoStoreDomain.AppRunTime().SetCurChannel(
				"Local://Session://" + carID);
		message.onFoundResult(context, json);
	}
	
	
	
	
}
