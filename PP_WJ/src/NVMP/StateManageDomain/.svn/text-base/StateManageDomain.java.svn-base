package NVMP.StateManageDomain;

import java.util.LinkedList;
import java.util.List;

import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.DeviceStatus;

import corenet.rpc.IMessage;
import NVMP.AppService.Interface.IAppRuntime;
import NVMP.AppService.Interface.IBusinessDomain;
import NVMP.Proxy.StateManageDomainProxy;

public class StateManageDomain implements IBusinessDomain {

	private static IAppRuntime _AppRuntime = null;
	private EncodeDeviceManage edm;

	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	public final boolean DomainEntry(IAppRuntime AppRuntime) {
		_AppRuntime = AppRuntime;
		try {
			edm = new EncodeDeviceManage();
			AppRuntime.RegisterObject(this.GetDomainName(), edm, "");

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * ҵ�������ж��ʱ����
	 */
	public final void DomainUnload() {

	}

	public final String GetDescription() {
		return "�����豸����ķ������";
	}

	/**
	 * ��ȡӦ������ƣ������ע��Ķ����ڸ�����ռ���
	 */
	public final String GetDomainName() {
		return "StateManageDomain";
	}

	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {
		// TODO Auto-generated method stub
		if (type.equals("S") && state.equals("0")
				&& Sessionid.matches(".*@006$")) {

			edm.SetCommandEncodeDeviceOnline(Sessionid, "", false, -1, -1, null);

		} else if (type.equals("S") && state.equals("0")) {

			edm.logoutState(Sessionid);

		} else if (type.equals("C") && state.equals("0")) {
			// 中心下线通知。
			edm.setCenterOnLine(Sessionid);

		} else

		if (type.equals("BreakPoint")) {
			edm.controlOtherSource(Sessionid, state.equals("1") ? true : false);
		}

	}

	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub

		List<IMessage> list = new LinkedList<IMessage>();

		// TODO Auto-generated method stub
		List<DeviceStatus> l = DAOFactory.getDeviceStatusIntance()
				.getDeviceStatusByCenterId(this.AppRunTime().getServerId());
		for (DeviceStatus ds : l) {
			// if(ds.getDeviceStatus() == 1 ) {
			IMessage message = StateManageDomainProxy
					.GrobalSetEncodeDeviceOnline_Copy(ds.getDevcieId(), ds
							.getDevIp(), ds.getDeviceStatus() == 1 ? true
							: false, ds.getDevType(), ds.getDevSubType(), null,
							this.AppRunTime().getServerId());
			// this.LocalChannelSendMessage(message, null,
			// _ExchangeServer.getServerID());
			list.add(message);
			// }

		}
		// System.out.println("来这里了没？  "+list.size());
		return list;
	}

}
