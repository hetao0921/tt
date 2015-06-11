package NVMP.IpMatrixManage.Implement;

import org.misc.log.LogUtil;

import NVMP.Proxy.MatrixDomain.EventHandler;

import com.fxdigital.ipmatrix.IpMatrixCtrl;
import com.fxdigital.ipmatrix.RtspMatrixCtrl;
import com.fxdigital.ipmatrixvga.VgaIpMatrixCtrl;

public class MatrixDomainEvenetHander extends EventHandler {
	private IpMatrixManageRun imr;
	private int nInstance;
	private int nOutputMode;
	private int TVIndex;
	private int SplitNum;
	private String deviceClass;

	public void setIpMatrixManageRun(IpMatrixManageRun ipMatrixManageRun) {
		this.imr = ipMatrixManageRun;
		imr.setnInstance(nInstance);
		imr.setSplitNum(1);
	} 

	
	/**
	 * 在此处对解码设备进行初始化
	 * @param nDeviceType 设备原始类型
	 * @param nSubDeviceType
	 * @param szAddress
	 * @param nPort
	 * @param szName
	 * @param szPass
	 * @param decMaxtrixStatus
	 * @param deviceClass 对外设备类型
	 */
	public MatrixDomainEvenetHander(int nDeviceType, int nSubDeviceType,
			String szAddress, int nPort, String szName, String szPass,
			int decMaxtrixStatus,String deviceClass) {
			this.deviceClass = deviceClass;
		nInstance = -1;
        LogUtil.BusinessInfo("=== start init Matrix");
        
        MatrixObject.getMatrixObject().setDeviceClass(deviceClass);
        
		// 此处做一个判断，如果 nPort == -1， 我们认为是测试版本，不考虑nInstance初始获得了。
        if(nDeviceType != 100 && nDeviceType != 101) {
        	LogUtil.BusinessInfo("rtsp start!");
        	RtspMatrixCtrl.init();
        }else if(deviceClass.equals("DS4004MD") && decMaxtrixStatus == 1){
        	
			while (nInstance == -1) {
				VgaIpMatrixCtrl.getVgaIpMatrixCtrl("0");
				
				nInstance =VgaIpMatrixCtrl.MaxtrixCreate(0, nSubDeviceType, szAddress, nPort, szName, szPass);
//				MatrixObject matrix = MatrixObject.getMatrixObject();
//				matrix.savaMatrix(szAddress, nPort, szName, szPass,nSubDeviceType,nDeviceType);
				
				LogUtil.BusinessInfo("==========nDeviceType :" + nDeviceType
						+ " ||  n=" + nInstance);
				if (nInstance == -1) {
					LogUtil.BusinessInfo("初始化左墙失败，5秒后重新尝试");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
        	
        } else if (nPort != -1) {
        	LogUtil.BusinessInfo("MaxtrixCreate start!");
        	LogUtil.BusinessInfo("nDeviceType : "+nDeviceType);
        	LogUtil.BusinessInfo("nSubDeviceType : "+nSubDeviceType);
			while (nInstance == -1) {
				nInstance = IpMatrixCtrl.getIpMatrixCtrl(deviceClass).MaxtrixCreate(
						nDeviceType, nSubDeviceType, szAddress, nPort, szName,
						szPass);

				LogUtil.BusinessInfo("==========nDeviceType :" + nDeviceType
						+ " ||  n=" + nInstance);
				if (nInstance == -1) {
					LogUtil.BusinessInfo("登陆失败，5秒后重新尝试");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		this.nOutputMode = decMaxtrixStatus;
	}

	/**
	 * 客户端控制矩阵分屏。
	 * 
	 * @param TVIndex
	 *            TV输出序号
	 * @param SplitNum
	 *            分屏数量
	 * 
	 * */
	@Override
	public Object SetScreenSplitNumEvent(Integer TVIndex, Integer SplitNum) {

		System.out.println("ok" + TVIndex + "  " + SplitNum + "  handler"
				+ nInstance);
		// TODO Auto-generated method stub
		this.TVIndex = TVIndex;
		this.SplitNum = SplitNum;
		imr.setSplitNum(SplitNum);

		boolean b = IpMatrixCtrl.getIpMatrixCtrl(this.deviceClass).MaxtrixSplitScreen(
				nInstance, TVIndex, SplitNum, nOutputMode);
		System.out.println(b ? "分屏成功" : "分屏失败");
		return null;
	}

	/**
	 * 客户端向控制矩阵点播图像
	 * 
	 * @param IsStart
	 *            true开始点播 false 停止点播
	 * */
	@Override
	public Object IpMatrixPlayVideoEvent(String ClientId, String IpMatrixId,
			Integer TVIndex, Integer pos, String DeviceId, Integer CameraIndex,
			Boolean IsStart) {

		imr.getMatrixObject().IpMatrixPlayVideoEvent(imr,ClientId, IpMatrixId,
				TVIndex, pos, DeviceId, CameraIndex, IsStart);

		return null;
	}

	@Override
	public Object GetMatrixStateEvent(String ClientId) {
		imr.SendScreenInfo(ClientId, TVIndex, SplitNum);
		imr.getMatrixObject().SendTvCtrolInfo(imr,ClientId);
		return null;
	}

	
	@Override
	public Object PsuhIpMatrixPlayVideoEvent(String clientId,
			String ipMatrixId, Integer tVIndex, Integer pos, String deviceId,
			Integer cameraIndex, Boolean isStart, String videoIP, String user,
			String password, Integer port, Integer deviceType,
			Integer deviceSubType, Integer netLinkType, Integer netLinkSubType,
			Integer netLinkMode) {
		imr.getMatrixObject().PsuhIpMatrixPlayVideoEvent(imr,clientId, ipMatrixId, tVIndex, pos, deviceId, cameraIndex, isStart, videoIP, user, password, port, deviceType, deviceSubType, netLinkType, netLinkSubType, netLinkMode);
		
		return null;
	}
}
