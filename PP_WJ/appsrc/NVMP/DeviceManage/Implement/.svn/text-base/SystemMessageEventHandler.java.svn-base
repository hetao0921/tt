package NVMP.DeviceManage.Implement;

import NVMP.DeviceManage.Implement.other.TestHandler;
import NVMP.Proxy.SystemMessageDomain.EventHandler;

public class SystemMessageEventHandler extends EventHandler {
	DeviceRun dr;
	@Override
	public Object SendMessageEvent(Integer flag, String message) {
		// TODO Auto-generated method stub
		if(flag==0) {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			dr.againConnect();
		}
		return null;
	}
 
	public void setDr(DeviceRun deviceRun) {
		// TODO Auto-generated method stub
		dr = deviceRun;
	}

//	@Override
//	public Object SendCenterIDEvent(String centerSessiodId, String devSessionId) {
//		// 初始化另外一个线程，用来做全网监控
//		
//		
////		System.out.println(")))============");
////		System.out.println("开始运行你的代码");
////		System.out.println(")))============");
////		TestHandler th = new TestHandler(dr.getDbip(),dr.getUser(),dr.getPasswd(),dr,centerSessiodId);
////		th.start();
//		
//		return null;
//	}
}
