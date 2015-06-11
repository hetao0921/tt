/**
 * 
 */
package NVMP.RemoteCloseDomain;

import java.util.HashMap;
import java.util.List;

import org.misc.log.LogUtil;

import com.alibaba.fastjson.JSONObject;

import NVMP.AppService.Remoting;

/**
 * @author lizehua
 *
 */
public class RemoteClose {
	public RemoteCloseEvent net;

	/**
	 * 遥闭
	 */
	@Remoting
	public void remoteClose(String json,String context,String uuID){
		try {
			
	
		LogUtil.TestInfo("开始执行遥闭操作,json="+json);

		HashMap<String,String> hp= JSONObject.parseObject(json, HashMap.class);
		String deviceID=hp.get("deviceID");
		String flag=hp.get("flag");
		String fClientID=hp.get("clientID");
		JdbcImpl jdbc=JdbcImpl.getJdbcImpl();
		String count=jdbc.juadgeRemoteClose(deviceID, flag);
		
		String returnCode=null;
		String desc=null;
		if("0".equals(count)){
			LogUtil.TestInfo("开始遥闭");
			//表示可以进行遥闭操作
		boolean status=	jdbc.updateRemoteClose(deviceID, flag);
		if(status){
			LogUtil.TestInfo("遥闭跟新成功");
			//跟新成功
			returnCode="0000";
			desc="操作成功";
		String returnMsg=	packageReturnMsg(hp, returnCode, desc);
		
			List<HashMap<String, String>> list=jdbc.queryLocalCenterDevices();
			for (HashMap<String, String> hps : list) {
				String clientID=hps.get("deviceid");
				LogUtil.TestInfo("通知:DeviceID:"+clientID);
				toNoticeClientMsg(clientID, returnMsg, context, uuID);
				
			}
		}else{
			//跟新失败
			returnCode="0001";
			desc="操作失败";
			String returnMsg=	packageReturnMsg(hp, returnCode, desc);
			toNoticeClientMsg(fClientID, returnMsg, context, uuID);
		}
		}else{
			LogUtil.info("重复操作");
			returnCode="0002";
			desc="不能再次操作";
			String returnMsg=	packageReturnMsg(hp, returnCode, desc);
			
			List<HashMap<String, String>> list=jdbc.queryLocalCenterDevices();
			for (HashMap<String, String> strings : list) {
				String clientID=strings.get("deviceid");
				
				toNoticeClientMsg(clientID, returnMsg, context, uuID);
				
			}
			
		}
		
		} catch (Exception e) {
			LogUtil.info(e.getMessage());
			LogUtil.sessionException(e);
		}
		
	
	}
	/**
	 * 通知其他用户
	 * @param clientID
	 * @param returnMsg
	 * @param context
	 * @param uuID
	 */
	public void toNoticeClientMsg(String clientID,String returnMsg,String context,String uuID){
		LogUtil.TestInfo("遥闭通知客户端,id="+clientID+"returnMsg="+returnMsg);
		(RemoteCloseDomain.AppRunTime()).SetCurChannel("Session://" + clientID);
		net.onNoticeClient(returnMsg, context, uuID);
		
	}
	
	public String  packageReturnMsg(HashMap<String,String> hp,String returnCode,String desc ){
		hp.put("returnCode", returnCode);
		hp.put("desc", desc);
		String returnMsg=JSONObject.toJSONString(hp);
		return returnMsg;
	}
	/**
	 * 判断遥闭
	 * @param clientID
	 * @param context
	 * @param uuID
	 */
	@Remoting
	public void toVerifyRemoteClose(String clientID, String context,String uuID){
		try {
		LogUtil.TestInfo("根据ID获取遥闭状态:"+clientID);
		JdbcImpl jdbc=JdbcImpl.getJdbcImpl();
		String status =jdbc.getClientStatus(clientID);
		LogUtil.TestInfo("返回ID获取遥闭状态:"+clientID);
		(RemoteCloseDomain.AppRunTime()).SetCurChannel("Session://" + clientID);
		net.onReturnStauts(status, context, uuID);
		} catch (Exception e) {
			LogUtil.sessionException(e);
		}
	}
	
	

}
