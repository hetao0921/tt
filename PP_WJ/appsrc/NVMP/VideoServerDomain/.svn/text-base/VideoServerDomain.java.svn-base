package NVMP.VideoServerDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

public class VideoServerDomain implements IBusinessDomain {

	private static IAppRuntime _AppRuntime = null;

	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	@Override
	public final boolean DomainEntry(IAppRuntime AppRuntime) {
		System.out.println("=================================");
		_AppRuntime = AppRuntime;

		try { 
			// 8050linux设备视频服务控制。
			AppRuntime.RegisterObject(this.GetDomainName(),
					new VideoServerCtrl(), "");

			// 8050linux作为上墙所用，单独进行连接中心等所用。
			new IpMatrixManage().start();

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DomainUnload() {
		// TODO Auto-generated method stub

	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return "VideoServerDomain";
	}

	@Override
	public String GetDomainName() {
		// TODO Auto-generated method stub
		return "VideoServerDomain";
	}

	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
