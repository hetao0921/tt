package NVMP.IpMatrixDomain;

import NVMP.AppService.Remoting;

import corenet.exchange.Encoding;

@Remoting
public class IMatrixEvent 
{
	//控制IP矩阵分屏
	public void OnSetScreenSplitNum(Integer TVIndex, Integer SplitNum){};
	//控制IP矩阵显示图像
	public void OnIpMatrixPlayVideo(String ClientId, String IpMatrixId, Integer TVIndex,Integer pos,String DeviceId, Integer CameraIndex,Boolean IsStart){};
	//IP矩阵显示图像成功	 
	public void OnResponeIpMatrixPlayVideo(String ClientId,String IpMatrixId, Integer TVIndex,Integer pos,String DeviceId, Integer CameraIndex,Boolean IsOK,String sInfo){};
	//IP矩阵上线线通知
	public void OnIpMatrixOnline(String IpMatrixId,Boolean IsOnline){};
	//矩阵工作状态
	public void OnGetMatrixState(String ClientId){};
	//IP矩阵的分屏幕状态
	public void OnScreenInfo(String IpMatrixId,Integer TVIndex,Integer SplitNum){};
	//IP矩阵的显示图像状态
	public void OnPalyVideoInfo(String IpMatrixId,Integer TVIndex,Integer pos,Boolean PlayVido,String DeviceId,Integer CameraIndx){}
	public void OnPsuhIpMatrixPlayVideo(String clientId, String ipMatrixId,
			Integer tVIndex, Integer pos, String deviceId, Integer cameraIndex,
			Boolean isStart, String videoIP, String user, String password,
			Integer port, Integer deviceType, Integer deviceSubType,
			Integer netLinkType, Integer netLinkSubType, Integer netLinkMode) {
		// TODO Auto-generated method stub
		
	};
	
}
