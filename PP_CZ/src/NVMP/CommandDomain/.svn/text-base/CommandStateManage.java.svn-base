package NVMP.CommandDomain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.misc.log.LogUtil;

import com.route.UsersInfo;

import corenet.exchange.Encoding;
import corenet.rpc.IMessage;
import NVMP.AppService.Remoting;
import NVMP.Proxy.CommandDomainProxy;

class User {
	String CenterID;
	String SessionID;
	String UserID;
	String IP;
	String State;
	Boolean OnLine;
	String GroupID;
	Boolean Flag;// 标示是否进行广播通知。

	User(String aCenterID, String aSessionID, String aUserID, String aIP,
			String aState, Boolean aOnLine, String aGroupID, Boolean aFlag) {
		CenterID = aCenterID;
		SessionID = aSessionID;
		UserID = aUserID;
		IP = aIP;
		State = aState;
		OnLine = aOnLine;
		GroupID = aGroupID;
		Flag = aFlag;
	}
}

class Tools {
	Map<String, User> UserMap;
	String CenterID;
	Map<String, Integer> UserCount;

	Tools(String centerid) {
		UserMap = new java.util.HashMap<String, User>();
		UserCount = new java.util.HashMap<String, Integer>();
		CenterID = centerid;
	}

	synchronized void centerDrop(String aCenterID) {
		for (User u : UserMap.values()) {
			if (aCenterID.equals(u.CenterID)) {
				u.OnLine = false;
			}
		}
	}

	// 放入相关数据，进行分析判断，直接生成，或是个别处理.UserID是关键
	synchronized boolean put(String aCenterID, String aSessionID,
			String aUserID, String aIP, String aState, Boolean aOnLine,
			String groupid, Boolean aFlag) {
		boolean b = false;
		User u = UserMap.get(aUserID);
		if (u == null) {
			u = new User(aCenterID, aSessionID, aUserID, aIP, aState, aOnLine,
					groupid, aFlag);
			UserMap.put(aUserID, u);
			b = true;
		} else if (!(u.SessionID.equals(aSessionID) && u.IP.equals(aIP)
				&& u.State.equals(aState) && u.OnLine.equals(aOnLine)
				&& u.GroupID.equals(groupid) && u.Flag.equals(aFlag))) {
			u.CenterID = aCenterID;
			u.SessionID = aSessionID;
			u.UserID = aUserID;
			u.IP = aIP;
			u.State = aState;
			u.OnLine = aOnLine;
			u.GroupID = groupid;
			u.Flag = aFlag;
			b = true;
		}
		if (aCenterID.equals(CenterID)) {
			if (aOnLine) {
				if (b) {
					UserCount.put(aUserID, 1);
				} else {
					Integer a = UserCount.get(aUserID);
					a++;
					UserCount.put(aUserID, a);
				}
			} else {
				UserCount.remove(aUserID);
			}
		}
		
//		if(!b){
//			LogUtil.SessionInfo("aCenterID :"+aCenterID);
//			LogUtil.SessionInfo("aSessionID :"+aSessionID);
//			LogUtil.SessionInfo("aUserID :"+aUserID);
//			LogUtil.SessionInfo("aIP :"+aIP);
//			LogUtil.SessionInfo("aState :"+aState);
//			LogUtil.SessionInfo("aOnLine :"+aOnLine);
//			LogUtil.SessionInfo("groupid :"+groupid);
//			LogUtil.SessionInfo("aFlag :"+aFlag);
//		}
		
		return b;
	}

	/**
	 * 判断目前活动的，当计数为-1的时候，表明死掉了。
	 * */
	synchronized void count() {
		for (Entry<String, Integer> en : UserCount.entrySet()) {
			// LogUtil.TestInfo("==== "+ en.getKey() + "  " + en.getValue());
			if (en.getValue() < 0)
				continue;
			if (en.getValue() > 2) {
				en.setValue(1);
			} else {
				en.setValue(en.getValue() - 1);
			}
			if (en.getValue() == -1) {
				set(en.getKey(), false);
			}
		}

	}

	/**
	 * aState 为 -1 则表明存在设备在线，但是未发送相关消息
	 * */
	synchronized void set(String aUserID, Boolean aOnLine) {
		User u = UserMap.get(aUserID);
		if (u != null) {
			u.OnLine = aOnLine;
		}
	}

	/**
	 * 根据ID的值进行判断，如果为ALL，则反馈全部，否则按centerid进行匹配。
	 * */
	synchronized List<User> getUserList(String centerid) {
		List<User> list = new LinkedList<User>();
		for (User u : UserMap.values()) {
			if (centerid.equals("ALL") || centerid.equals(u.CenterID)) {
				list.add(u);
			}
		}
		return list;
	}

	synchronized public void LoginOut(String sessionid) {
		for (Entry<String, User> en : UserMap.entrySet()) {
			if (en.getValue().SessionID.equals(sessionid)) {
				en.getValue().OnLine = false;
			}
		}

	}

}

/**
 * 管理指挥终端上下线情况
 * */
public class CommandStateManage extends Thread {

	public ICommandEvent CommandEvent;

	private Tools CommandTool;

	CommandStateManage() {
		CommandTool = new Tools(CommandDomain.AppRunTime().getServerId());
		userOnlineMap = new HashMap<String, Integer>();
	}

	// loginok后调用该函数，
	public void SetUserOnline_proxy(String sessionid, String userid, String ip,
			Boolean online) {
		boolean b = CommandTool.put(CommandTool.CenterID, sessionid, userid,
				ip, "", online, "", false);
		if (b) {
			IMessage message = CommandDomainProxy.GrobalSetUserOnline_Copy(
					sessionid, userid, ip, "", online, CommandTool.CenterID,
					"", false);
			(CommandDomain.AppRunTime()).LocalChannelSendMessage(message, null,
					null, null);
		}
	}

	private Map<String, Integer> userOnlineMap;

	private boolean isSendTime(String sessionid) {
		boolean isSendFlag = false;
		;
		Integer count = userOnlineMap.get(sessionid);
		int n = 0;
		if (count != null) {
			n = count.intValue() + 1;
		}
		if (n % 10 == 0) {
			isSendFlag = true;
		}
		userOnlineMap.put(sessionid, n);

		return isSendFlag;
	}

	/**
	 * 指挥员当前状态声明
	 * */
	@Remoting("yes")
	public void SetUserOnline(String sessionid, String userid, String ip,
			String state, Boolean online, String groupid) {
		// 根据当前值是否发生变化确定是否发送给群。
		boolean b = CommandTool.put(CommandTool.CenterID, sessionid, userid,
				ip, state, online, groupid, true);
		if (b) {
			if (isSendTime(sessionid)) {
				(CommandDomain.AppRunTime())
						.SetCurChannel("Local://"
								+ Encoding
										.AdsToGroupProtocol(CommandDomain.AllCommander));
				CommandEvent.OnUserState(sessionid, userid, ip, state,
						CommandTool.CenterID, online, groupid, true);
				// 对其它中心传递
				IMessage message = CommandDomainProxy.GrobalSetUserOnline_Copy(
						sessionid, userid, ip, state, online,
						CommandTool.CenterID, groupid, true);
				(CommandDomain.AppRunTime()).LocalChannelSendMessage(message,
						null, null, null);
			}

		}
	}

	@Remoting("yes")
	public void GetUserOnline(String clientid) {
		List<User> list = CommandTool.getUserList("ALL");

		(CommandDomain.AppRunTime()).SetCurChannel("Local://"
				+ Encoding.AdsToPointProtocol(clientid));
		for (User u : list) {
			CommandEvent.OnUserState(u.SessionID, u.UserID, u.IP, u.State,
					u.CenterID, u.OnLine, u.GroupID, u.Flag);
		}
	}

	@Remoting("yes")
	public void GrobalSetUserOnline(String sessionid, String userid, String ip,
			String state, Boolean online, String centerid, String groupid,
			Boolean flag) {

		LogUtil.SessionInfo("GrobalSetUserOnline : "+ sessionid +" ip" +ip+"  online:"+online);
		
		// 在缓存中增加sessionid和centerid的关系。
		UsersInfo.getInstance().insertUser(sessionid, centerid);

		// 根据当前值是否发生变化确定是否发送给群。
		boolean b = CommandTool.put(centerid, sessionid, userid, ip, state,
				online, groupid, flag);
		if (flag && b) {
			(CommandDomain.AppRunTime()).SetCurChannel("Local://"
					+ Encoding.AdsToGroupProtocol(CommandDomain.AllCommander));
			CommandEvent.OnUserState(sessionid, userid, ip, state, centerid,
					online, groupid, flag);
		}
		CommandDomain.AppRunTime().setContinueFlag(true);
	}

	// 按时间将自己的信息广播出去。
	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(60000);
				CommandTool.count();

				List<User> list = CommandTool.getUserList("ALL");
				for (User u : list) {
					if (u.Flag) {
						(CommandDomain.AppRunTime())
								.SetCurChannel("Local://"
										+ Encoding
												.AdsToGroupProtocol(CommandDomain.AllCommander));
						CommandEvent.OnUserState(u.SessionID, u.UserID, u.IP,
								u.State, u.CenterID, u.OnLine, u.GroupID,
								u.Flag);
					}
					if (u.CenterID.equals(CommandTool.CenterID)) {
						IMessage message = CommandDomainProxy
								.GrobalSetUserOnline_Copy(u.SessionID,
										u.UserID, u.IP, u.State, u.OnLine,
										u.CenterID, u.GroupID, u.Flag);
//						if(u.IP.equals("192.168.1.133")) {
//							LogUtil.SessionInfo(" send info : sessionid "+u.SessionID+" online"+u.OnLine);
//						}
						(CommandDomain.AppRunTime()).LocalChannelSendMessage(
								message, null, null, null);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void LoginOut(String sessionid) {
		CommandTool.LoginOut(sessionid);
		
		/**
		 * 添加下线后，setuseronline立刻向其他服务器外广播下线消息
		 * */
		List<User> list = CommandTool.getUserList(CommandTool.CenterID);
		for(User u:list) {
			if(u.SessionID.equals(sessionid)) {
				IMessage message = CommandDomainProxy
						.GrobalSetUserOnline_Copy(u.SessionID,
								u.UserID, u.IP, u.State, u.OnLine,
								u.CenterID, u.GroupID, u.Flag);
//				if(u.IP.equals("192.168.1.133")) {
//					LogUtil.SessionInfo(" now send info : sessionid "+u.SessionID+" online"+u.OnLine);
//				}
				(CommandDomain.AppRunTime()).LocalChannelSendMessage(
						message, null, null, null);
			}
		}

		
	}

	static public void main(String... args) {

		HashMap<String, User> hp = new HashMap<String, User>();
		// hp.put("1", new User("1","1","1","1","1",false,"1"));
		// hp.put("2", new User("2","2","2","2","2",false,"2"));
		// hp.put("3", new User("3","3","3","3","3",false,"2"));
		for (Entry<String, User> en : hp.entrySet()) {

			if (en.getValue().IP.equals("2")) {
				en.getValue().OnLine = true;
			}

		}

		System.out.println(hp.get("2").OnLine);
		Integer a = 3;
		System.out.println(a == 3);
	}

	// 处理中心下线。
	public void CenterServerOut(String centerid) {
		LogUtil.BusinessInfo("commandstatemanage center drop " + centerid);
		CommandTool.centerDrop(centerid);
	}

}
