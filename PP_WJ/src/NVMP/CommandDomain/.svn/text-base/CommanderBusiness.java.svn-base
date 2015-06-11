package NVMP.CommandDomain;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;
import org.misc.RefObject;
import corenet.exchange.Encoding;

import NVMP.AppService.Remoting;

import NVMP.Command.Business.*;

import NVMP.Proxy.*;

class Version {
	String serverversion;// 服务器版本
	String clientversion;// 客户端版本
	String errorInfo;// 错误信息
	String worningInfo;// 警告信息
	Set<String> wornSet;// 警告的版本池

	public Version() {
		wornSet = new HashSet<String>();
	}

}

public class CommanderBusiness {

	public ICommandEvent CommandEvent;

	private CommandDomain commandDomain;

	private SystemBusiness BusinessData;

	private Map<String, String> IPPools;

	private Set<String> ClientSet;

	private Version instanceVersion;
	
	
	private CommandStateManage aCommandStateManage;//用来进行调用心跳声明

	public CommanderBusiness(SystemBusiness _BusinessData,
			CommandDomain commandDomain1,CommandStateManage ctm) {
		BusinessData = _BusinessData;
		aCommandStateManage = ctm;
		IPPools = new ConcurrentHashMap<String, String>();
		ClientSet = new HashSet<String>();
		// 读取配置文件，获得相关版本等信息。
		instanceVersion = new Version();
		try {
			String path;

			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/version/version.xml";
			else
				path = "d:\\nvmpdata\\version\\version.xml";

			// 读一下配置文件中的配置。
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(new File(path));
			instanceVersion.serverversion = doc.getRootElement()
					.element("serverversion").element("version").getTextTrim();
			instanceVersion.clientversion = doc.getRootElement()
					.element("clientVersion").element("validVersion")
					.element("version").getTextTrim();
			instanceVersion.errorInfo = doc.getRootElement()
					.element("clientVersion").element("invalidInfo")
					.element("Info").getTextTrim();
			instanceVersion.worningInfo = doc.getRootElement()
					.element("clientVersion").element("worningInfo")
					.element("Info").getTextTrim();
			// 获取警告的清单
			@SuppressWarnings("unchecked")
			List<Element> list = doc.getRootElement().element("clientVersion")
					.element("worningInfo").elements("version");
			for (Element ele : list) {
				instanceVersion.wornSet.add(ele.getTextTrim());
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.BusinessError("版本文件出错，系统自动退出。");
			LogUtil.BusinessError("version error,system auto exit");
			System.exit(1);
		}

		this.commandDomain = commandDomain1;
	}

	@Remoting
	public void GetInitStatus(String TerminalId) {
		InitCommanderAccredit(TerminalId);
	}

	private final void InitCommanderAccredit(String CommandDeviceId) {
		for (Accredit item : BusinessData.getAccreditList()) {
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(CommandDeviceId));
			if (item.type == "1") {
				CommandEvent.OnCommandReplacemente(item.Acception,
						item.Licensor, true, false);
			} else {
				CommandEvent.OnCommandReplacemente(item.Acception,
						item.Licensor, true, true);
			}
		}
	}

	private void AddCommander(String TerminalId, String CommanderId) {
		LogUtil.BusinessDebug("@@@@@@@@@@@@@@@Add a Commander id ="
				+ TerminalId);
		if (CommanderId == null)
			CommanderId = "";
		IPPools.put(TerminalId, CommanderId);
	}

	private void RemoveCommander(String TerminalId) {
		IPPools.remove(TerminalId);
	}

	public void OperateCommanderQuit(String TerminalId) {
		ClientSet.remove(TerminalId);

		String CommanderId = "";
		// 通过TerminalId找到对应的CommanderId

		if (IPPools.containsKey(TerminalId)) {
			LogUtil.BusinessDebug("@@@@@@@@@@@@@@@Commander offline id ="
					+ TerminalId);
			CommanderId = IPPools.get(TerminalId);
			BusinessData.SetCommanderLogin(TerminalId, CommanderId, "", false);
			RemoveCommander(TerminalId);
		}
		// RemoveCommandDevice(TerminalId);

	}

	// private void RemoveCommandDevice(String DeviceId)
	// {
	// NVMP.Proxy.StateManageDomainProxy.SetCommandEncodeDeviceOnline(DeviceId,
	// "", false, -1, -1);
	// }

	private boolean IsUserLogin(String TerminalId, String CommanderId) {
		boolean b = true;
		if (IPPools.containsValue(CommanderId)) {
			b = false;
			for (Entry<String, String> s : IPPools.entrySet()) {
				if (s.getValue().equals(CommanderId)) {
					if (s.getKey().equals(TerminalId))
						b = true;
					break;
				}
			}

			if (!b) {
				(CommandDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(TerminalId));
				String reason = "指挥员用户重复登录";
				String ueserid = CommanderId;
				String context = "";
				CommandEvent.OnLoginError(ueserid, reason, context);
			}
		}
		return b;
	}

	public void SendVersionError(String Deviceid) {
		// 做一个判断，如果不在询问8060组中，则认为需提示升级
//		LogUtil.TestInfo("come in" + Deviceid);
//		LogUtil.TestInfo("see set");
//		for (String str : ClientSet) {
//			LogUtil.TestInfo("====" + str);
//		}
//		LogUtil.TestInfo("end set");
		if (!ClientSet.contains(Deviceid)) {
			(CommandDomain.AppRunTime()).SetCurChannel("Local://"
					+ Encoding.AdsToPointProtocol(Deviceid));
			CommandEvent.OnSendInfo(Deviceid, instanceVersion.errorInfo);
		}
	}

	@Remoting("yes")
	public void SetCommanderLoginOk(String TerminalId, String CommanderId,
			String TerminalIP, Boolean IsOnline, Integer DeviceType,
			Integer DevuceSubType, Integer CameraNum, String UserName,
			String Camera1Name, String Camera2Name, String Camera3Name,
			String Camera4Name, String CenterId) {
//		SendVersionError(TerminalId);
		// 修改指挥员的登陆状
		String say = String.format(
				"****指挥员登陆：终端编号=%s,指挥员编号=%s,设备IP==%s,是否上线=%s", TerminalId,
				CommanderId, TerminalIP, IsOnline.toString());
		LogUtil.BusinessDebug(say);
		// 修改指挥员的登陆状态信息
		try {
			if (IsOnline) {
				// 如果有用户已经绑定，那么通知对方退出吧。
				if (!IsUserLogin(TerminalId, CommanderId))
					return;
				AddCommander(TerminalId, CommanderId);

			}

			BusinessData.SetCommanderLogin(TerminalId, CommanderId, TerminalIP,
					IsOnline);
			for (Accredit item : BusinessData.getAccreditList()) {
				(CommandDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(TerminalId));
				if (item.toString() == "1") {
					CommandEvent.OnCommandReplacemente(item.Acception,
							item.Licensor, true, false);
				}
				if (item.toString() == "2") {
					CommandEvent.OnCommandReplacemente(item.Acception,
							item.Licensor, true, false);
				}
			}

			// 通知所有指挥员有一个指挥员上线

			(CommandDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToGroupProtocol(CommandDomain.AllCommander));

			CommandEvent.OnCommanderLoginOk(TerminalId, CommanderId, IsOnline,
					CameraNum, UserName, Camera1Name, Camera2Name, Camera3Name,
					Camera4Name, CenterId);

			// 通知设备管理模块指挥终端编码设备上线
			StateManageDomainProxy.SetCommandEncodeDeviceOnline(TerminalId,
					TerminalIP, IsOnline, DeviceType, DevuceSubType, CenterId);
	
			//调用 传入 false；
			if(IsOnline) {
				
				aCommandStateManage.SetUserOnline_proxy(TerminalId, CommanderId, TerminalIP, IsOnline);
			}
			
		
		} catch (Exception e) {
			LogUtil.BusinessError("指挥员登陆错误：" + say);
			LogUtil.BusinessError(e);

		}
		
		


	}

	@Remoting
	public final void EnterFree(String CommanderId) {
		String say = String.format("****指挥员退出到自由态：指挥员编号=%s", CommanderId);
		LogUtil.BusinessDebug(say);
		try {
			
			Commander aCommander = null;
			RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(
					aCommander);
			boolean tempVar = BusinessData.GetCommanderById(CommanderId,
					tempRef_aCommander);
			aCommander = tempRef_aCommander.argvalue;
			if (tempVar) {
				aCommander.EnterFree();
				SendVersionError(aCommander.getDeviceID());
			}

			// 通知所有指挥终端状态改变 Add
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToGroupProtocol(CommandDomain.AllCommander));
			CommandEvent.OnCommanderStateChange(CommanderId, "",
					CommanderWorkStatus.Free.getValue());

		} catch (Exception e) {
			LogUtil.BusinessError("指挥员退出自由态错误：" + say);
			LogUtil.BusinessError(e);

		}
	}

	@Remoting
	public final void SendCommanderState(String DestDeviceId,
			String CommanderId, String DeviceId, String GroupId, String DeptId,
			String WorkStatus, Boolean IsOnline, String WorkMode,
			Boolean IsUpCut, Boolean IsDownCut, Integer CameraNum,
			String UserName, String Camera1Name, String Camera2Name,
			String Camera3Name, String Camera4Name, String CenterId) {
//		SendVersionError(DeviceId);
//		if (!IsUserLogin(DeviceId, CommanderId))
//			return;

		if(true) return;
		(CommandDomain.AppRunTime()).SetCurChannel(Encoding
				.AdsToPointProtocol(DestDeviceId));
		CommandEvent.OnReciveCommanderState(CommanderId, DeviceId, GroupId,
				DeptId, WorkStatus, IsOnline, WorkMode, IsUpCut, IsDownCut,
				CameraNum, UserName, Camera1Name, Camera2Name, Camera3Name,
				Camera4Name, CenterId);
	}

	@Remoting
	public final void SendInfo(String SourceCommanderId,
			String DestCommanderId, String info) {
		String say = String.format("****指挥员发送指令：发送方编号=%s，接收方编号=%s",
				SourceCommanderId, DestCommanderId);
		LogUtil.BusinessDebug(say);
		try {
			(CommandDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(DestCommanderId));
			CommandEvent.OnSendInfo(SourceCommanderId, info);

		} catch (Exception e) {
			LogUtil.BusinessError("指挥员发送指令错误：" + say);
			LogUtil.BusinessError(e);

		}
	}

	// String say=String.format("****指挥员退出到自由态：指挥员编号=%s",CommanderId);
	// LogUtil.debug(say);
	// try
	// {
	//
	//
	// }catch(Exception e)
	// {
	// LogUtil.error("指挥员退出自由态错误：" + say);
	// LogUtil.error(e);
	//
	// }
	/**
	 * 通知所有的组内成员，
	 * */
	public void CommandServerOnline(String serverid, boolean flag) {

		(CommandDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));
		CommandEvent.OnCommandServerOnline(serverid, flag, null);
	}

	/**
	 * 主动获取当前8060服务器状态。
	 * */
	@Remoting("yes")
	public void getCommandServerOnline(String clientid) {

		// 做一个记录，记录进行此操作的设备。
		ClientSet.add(clientid);

		(CommandDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(clientid));
		CommandEvent.OnCommandServerOnline(null, commandDomain.isOpen(), null);
	}

	/**
	 * 通知所有的组内成员，
	 * */
	public void CenterServerOnLine(String serverid, boolean flag) {

		(CommandDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));
		CommandEvent.OnCenterServerOnLine(serverid, flag);
	}

	@Remoting("yes")
	public void GetVersion(String clientid, String version) {

		if (!instanceVersion.clientversion.equals(version)) {
			(CommandDomain.AppRunTime()).SetCurChannel("Local://"
					+ Encoding.AdsToPointProtocol(clientid));
			if (instanceVersion.wornSet.contains(version)) {
				CommandEvent.OnGetVersion("worn", instanceVersion.worningInfo);
			} else {
				CommandEvent.OnGetVersion("error", instanceVersion.errorInfo);
			}

		}

	}

}
