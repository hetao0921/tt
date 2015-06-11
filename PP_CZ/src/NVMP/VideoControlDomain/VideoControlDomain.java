package NVMP.VideoControlDomain;

import java.util.List;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;

public class VideoControlDomain implements IBusinessDomain {
	private static IAppRuntime _AppRuntime = null;

	private VideoControl vc;

	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	public final boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		try {
			vc = new VideoControl();
			AppRuntime.RegisterObject(this.GetDomainName(), vc, "");
			new UDPServer(vc).start();

		} catch (Exception e) {

		}
		return true;
	}

	/**
	 * ҵ�������ж��ʱ����
	 */
	public final void DomainUnload() {

	}

	public final String GetDescription() {
		return "VideoContrlDomain";
	}

	/**
	 * ��ȡӦ������ƣ������ע��Ķ����ڸ�����ռ���
	 */
	public final String GetDomainName() {
		return "VideoContrlDomain";
	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		// TODO Auto-generated method stub
		if (type.equals("S")) {
			vc.SetClientOnLine(Sessionid, state.equals("1") ? true : false);
		} else if (type.equals("C")) {

			vc.setCenter(Sessionid, state.equals("1") ? true : false);
		} else if (type.equals("BreakPoint")) {
			RouteImpl.getRouteImpl().changeCenter(Sessionid,
					state.equals("1") ? true : false);
		}
	}

	@Override
	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
