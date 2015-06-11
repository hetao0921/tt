package NVMP.CommandDomain;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;


import corenet.rpc.IMessage;

import NVMP.AppService.Interface.*;

import NVMP.Command.Business.DBAccess;
import NVMP.Command.Business.SystemBusiness;

public class CommandDomain implements IBusinessDomain, DomainLock {

	public static final String AllCommander = "G_Cmd";

	private static IAppRuntime _AppRuntime = null;

	private SystemBusiness SysData;
	private CommanderBusiness aCommanderBusiness;
	private CommandGroupBusiness aCommandGroupBusiness;
	private CommandStateManage aCommandStateManage;
	private boolean flag;
	private boolean ruleflag;
	
	private Set<String> flagSet;

	public static IAppRuntime AppRunTime() {
		return _AppRuntime;
	}

	public final boolean DomainEntry(IAppRuntime AppRuntime) {
		//对取配置文件，如果存在文件，则表明可以
		//判断当前系统。windows下路径：  D:/fxconf/command.conf
		// linux下路径： /etc/fxconf/command.conf
		String path = "";
		
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/command.conf";
		else
			path = "d:\\fxconf\\command.conf";
		
		java.io.File file = new java.io.File(path);
		ruleflag = false;
		flag = false;
		
		if(file.isFile()) {
			ruleflag = true;
			
			try {
				SAXReader saxReader = new SAXReader();
				Document doc = saxReader.read(file);
				String v = doc.getRootElement().element("isopen").getTextTrim();
				if(v.equals("1")) flag = true;
				
			} catch (DocumentException e) {
				flag = false;
			}
			
			
			
			
			
			
		} 

		flagSet = new HashSet<String>();
		_AppRuntime = AppRuntime;
		try {
			_AppRuntime.CreateGroup(AllCommander);

			SysData = new SystemBusiness();
			DBAccess.InitDB();

			DBAccess.GetAllCameraGroupFromDB(SysData);
			DBAccess.GetAllCommanderFromDB(SysData);
			DBAccess.GetAllCommandGroupFromDB(SysData);
			DBAccess.GetAllCameraGroupFromDB(SysData);
			DBAccess.GetAllConferenceGroupFromDB(SysData);
			DBAccess.GetAllSulatationGroupFromDB(SysData);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.sessionException(e);
			LogUtil.BusinessError("初始化数据加载错误：" + e.toString());
		}

		
		
		aCommandStateManage = new CommandStateManage();
		aCommanderBusiness = new CommanderBusiness(SysData,this,aCommandStateManage);
		aCommandGroupBusiness = new CommandGroupBusiness(SysData,aCommanderBusiness);
		
		ConferenceGroupBusiness aConferenceGroupBusiness = new ConferenceGroupBusiness(
				SysData);
		ConsultationGroupBusiness aConsultationGroupBusiness = new ConsultationGroupBusiness(
				SysData);

		AppRuntime.RegisterObject(this.GetDomainName(), aCommanderBusiness, "");
		AppRuntime.RegisterObject(this.GetDomainName(), aCommandGroupBusiness,
				"");
		AppRuntime.RegisterObject(this.GetDomainName(),
				aConferenceGroupBusiness, "");
		AppRuntime.RegisterObject(this.GetDomainName(),
				aConsultationGroupBusiness, "");
		AppRuntime.RegisterObject(this.GetDomainName(),
				aCommandStateManage, "");
		aCommandStateManage.start();
		// AppRuntime.RegisterObject(this.GetDomainName(),new
		// IpMatrixBusiness(), "");

		return true;
	}

	public final void RequestPlayVideo(String ClientUserId, String DeviceId,
			Integer Index, String Context) {

		String s = String.format("收到图像点播请求：客户端Id=%s. 设备编号=%s.服务序号=%d.",
				ClientUserId, DeviceId, Index);
		LogUtil.BusinessDebug(s);
	}

	/**
	 * 业务组件被卸载时调用
	 */
	public final void DomainUnload() {

	}

	public final String GetDescription() {
		return "CommandDomain";
	}

	/**
	 * 获取应用域名称，该组件注册的对象都在改命名空间下
	 */
	public final String GetDomainName() {
		return "CommandDomain";
	}

	//
	// Sessionid ：客户端编号
	// Groupid :分组编号
	// state: 0, 下线 ，1上线
	// type: S,客户端上下线信息，G，表示组发来的信息
	@Override
	public void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type) {

		LogUtil.SessionInfo("test command info :Sessionid " + Sessionid+" Groupid:"+Groupid+" state:"+state+" type:"+type);

		String str = "用户已登录";
		if (type.equals("S") && DBAccess.IsCommandDevice(Sessionid)) {
			setOpen(Sessionid, state.equals("1"));
		}

		if (Groupid.equals(AllCommander)) {
			if (state.equals("1")) {

			} else {
//				LogUtil.SessionInfo("test command group out:" + Sessionid);
                aCommandStateManage.LoginOut(Sessionid);
			}
		}
		
		if(type.equals("C") && state.equals("0")) {
			//该中心下线，通知一下
			aCommanderBusiness.CenterServerOnLine(Sessionid, false);
			
			aCommandStateManage.CenterServerOut(Sessionid);
		}

		if (state.equals("0")) {
			String say = String.format(
					"****User online 01：DeviceId=%s ,userId=%s ,分组编号=%s",
					Sessionid, str, Groupid);
			LogUtil.BusinessDebug(say);

			aCommanderBusiness.OperateCommanderQuit(Sessionid);
			return; // hsj 20111118 下面代码不执行了

			// if (Groupid.equals(CommandDomain.AllCommander))
			// {
			// say=String.format("****User online 02：DeviceId=%s ,userId=%s ,分组编号=%s",Sessionid,str,Groupid);
			// LogUtil.BusinessDebug(say);
			// Commander aCommander = null;
			// RefObject<Commander> tempRef_aCommander = new
			// RefObject<Commander>(aCommander);
			// boolean tempVar = SysData.GetCommanderByDeviceId(Sessionid,
			// tempRef_aCommander);
			// aCommander = tempRef_aCommander.argvalue;
			// if (tempVar)
			// {
			//
			// say=String.format("****User online 03：DeviceId=%s ,userId=%s ,分组编号=%s",Sessionid,str,Groupid);
			// //if (aCommander.getIsOnline())
			// {
			// aCommanderBusiness.SetCommanderLoginOk(Sessionid,
			// aCommander.getCommanderId(), "", false,-1,-1,1);
			// str = aCommander.getCommanderId();
			//
			// aCommandGroupBusiness.StopAccredit(aCommander.getCommanderId());//停止指挥授权
			// //say=String.format("****指挥终端用户下线：设备编号=%s ,用户编号=%s ,分组编号=%s",Sessionid,str,Groupid);
			// //LogUtil.BusinessDebug(say);
			// }
			// }
			//
			// }

		}
		if (state.equals("1") && type.equals("S")) {
			// if (Groupid.equals(CommandDomain.AllCommander))
			{

//				String say = String.format("****用户登录：设备编号=%s ,分组编号=%s",
//						Sessionid, Groupid);
//				LogUtil.BusinessDebug(say);
//				// 清除指挥终端已经登录的用户
				//20140308 ZZW say ClearCommmandDevUser 导致MySQL的性能瓶颈，取消该代码
				//20140308 Hsj 认同
//				NVMP.Command.Business.DBAccess.ClearCommmandDevUser(Sessionid);
//				// 不处理业务
			}
		}

	}

	public List<IMessage> getCenterMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
//		return flag;
		if(!ruleflag) {
			return true;
		}
		return flag;
	}

	private void setOpen(String clientid, boolean b) {
		if (b) {
			flagSet.add(clientid);
			if (flagSet.size() == 1) {
				flag = true;
				this.aCommanderBusiness.CommandServerOnline(clientid, true);
			}
		} else {
			if (flagSet.remove(clientid) && flagSet.size() == 0) {
				flag = false;
				this.aCommanderBusiness.CommandServerOnline(clientid, false);
			}
		}
	}
}
// : IBusinessDomain
