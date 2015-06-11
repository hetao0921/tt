package corenet.exchange;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import com.route.UsersInfo;
import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.impl.CenterNetWorkImpl;
import com.sqlite.pojo.CenterNetWork;

import corenet.exchange.Group.GroupPoolTool;
import corenet.exchange.Interface.ExchangeServerMessage;
import corenet.exchange.link.ServerLink;
import corenet.netbase.BaseServer;
import corenet.netbase.BaseSession;
import corenet.netbase.PerformanceMonitor;
import corenet.netbase.Interface.BaseClientListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;

class SysnCenter {

	String centerId;
	long time; // 以秒为单位

	public SysnCenter(String centerId) {
		if (centerId == null) {
			LogUtil.BusinessError("now null center in here");
		}

		setCenterId(centerId);
		// reflushTime();
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	/**
	 * 刷新time
	 */
	public void reflushTime() {
		Calendar ca = Calendar.getInstance();
		time = ca.getTimeInMillis() / 1000;
	}

	/**
	 * 当前时间与time之间间隔多少毫秒
	 * 
	 * @return
	 */
	// public int getGapTime() {
	// Calendar ca = Calendar.getInstance();
	// return (int) (ca.getTimeInMillis() / 1000 - time);
	// }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		boolean b = false;
		try {
			b = (obj instanceof SysnCenter)
					&& this.centerId.equals(((SysnCenter) obj).centerId);
		} catch (Exception e) {
			return false;
		}
		return b;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return centerId.hashCode();
	}

}

class OtherCenterMessage {

	IMessage im;
	String centerId;

	public OtherCenterMessage(IMessage imessage, String centerid) {
		this.im = imessage;
		this.centerId = centerid;

	}

}

class OtherCenterClient {
	private String sessionId;
	private String groupName;
	private String centerId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof OtherCenterClient)
				&& ((OtherCenterClient) obj).getSessionId().equals(sessionId)
				&& ((OtherCenterClient) obj).getGroupName().equals(groupName);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return sessionId.hashCode() + groupName.hashCode();
	}

}

public class ExchangeServer extends Thread implements IExchangeServer,
		BaseClientListen {
	private BaseServer _Base;
	private ExchangeServerMessage esm;
	private String ServerID;
	
	private IConnectRule connectRule;

	public String getServerID() {
		return ServerID;
	}

	public void setServerID(String serverID,IConnectRule rule) {
		ServerID = serverID;
		connectRule = rule;
	}

	/**
	 * 对外提供当前连接情况,上级连接池,格式 sessionid IP
	 * */

	public List<String> getLinkedServers() {
		List<String> list = new LinkedList<String>();

		return list;
	}

	/**
	 * 对外提供当前连接情况，下级连接池。格式 sessionid IP
	 * */
	public List<String> getLinkedClinets() {
		List<String> list = new LinkedList<String>();
		for (IChannel aChannel : serverChannelPool.values()) {
			list.add(aChannel.GetSessionId() + "|" + aChannel.getRemoteIP());
		}
		return list;
	}

	public ExchangeServerMessage getEsm() {
		return esm;
	}

	public void setEsm(ExchangeServerMessage esm) {
		this.esm = esm;
	}

	private GroupPoolTool groupTool;
	private Set<OtherCenterClient> otherCenterClients;
	// private Set<SysnCenter> sysnCenterSet;

	/**
	 * 为判断客户端来源先后写的map
	 * */
	private Map<String, String> clientMap;

	/**
	 * by zzw HandleNewConnection改名 OnNewConnection
	 * 
	 * */

	public final void OnNewConnection(BaseSession Session, boolean Success) {
		// 建立连接，同时别的事情不处理？第一次read的时候，再放入session池中。
		
		LogUtil.SessionInfo("OnNewAcceptConnection " + Session.getRemoteEndPoint());
		
		
		new ExchangeChannel(Session, this);
		
		
	}

	/**
	 * by zzw C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
	 * 
	 * 所以 events，屏蔽了
	 * */
	int n = 0;

	public final void ProcessMessage(IChannel Channel, IMessage Message) {

		if (esm != null) {
			// System.out.println("=============== n:" + n++);
			esm.OnExchangeServerMessage(Channel, Message);
		}

		PerformanceMonitor.getInstance().addDisposeNum(1);
	}

	private boolean routeInitFlag;

	public ExchangeServer() {
		serverChannelPool = new ConcurrentHashMap<String, IChannel>();

		clientPool = new ConcurrentHashMap<String, ExServerClinet>();

		groupTool = GroupPoolTool.Instance();
		otherCenterClients = new CopyOnWriteArraySet<OtherCenterClient>();
		//
		clientMap = new ConcurrentHashMap<String, String>();

		connectRule = new IConnectRule() {
			@Override
			public boolean isAllowLogin(String sessionID, String sessionIP) {
				// TODO Auto-generated method stub
				return true;
			}
		};
		
		/**
		 * 读取配置文件,根据配置文件来设置最高的心跳记录数量，如果读取的值错误或者小于3 则默认为3；
		 * */

		this.session_num = 6;

		try {
			// 开始初始化本地数据
			String path = null;
			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/AppService/BeatAlive.conf";
			else
				path = "d:\\fxconf\\AppService\\BeatAlive.conf";

			// 读一下配置文件中的配置。
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(new File(path));
			String tempString = doc.getRootElement().element("BeatNum")
					.getTextTrim();
			int m = Integer.parseInt(tempString);
			if (m > 6)
				session_num = m;

		} catch (Exception e) {
			LogUtil.error("error init BeatAlive");
			LogUtil.error(e.getCause().toString());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.error(s.toString());
			}
			this.session_num = 6;
		}
		LogUtil.BusinessInfo("BeatAlive nums = " + session_num);
		_Base = new BaseServer();

		_Base.setBsl(this);

		routeInitFlag = false;

		this.start();

	}

	public final boolean StartUp(String ip, int port) {
		groupTool.setEsm(esm);

		/***
		 * @author zzw by 20130923 建立路由表，进行初始化。
		 * 
		 * */
		initRoute();

		return _Base.StartUp(ip, port);
	}

	private Map<String, CenterNetWork> centerRouteMap;
	private String rootCenterID;

	/**
	 * 路由判断初始化
	 * */
	private void initRoute() {

		if (routeInitFlag) {
			return;
		} else {
			routeInitFlag = true;
		}
		LogUtil.BusinessInfo("init route");
		centerRouteMap = new HashMap<String, CenterNetWork>();
		CenterNetWorkDao cnwd = new CenterNetWorkImpl();
		List<CenterNetWork> list = cnwd.getAllCenterNetWork();
		ServerLink.getInstance().init();
		for (CenterNetWork cnw : list) {
			LogUtil.BusinessInfo(cnw.getCenterId() + " to "
					+ cnw.getIsControlFlag() + "  " + cnw.getNetWorkNodeID());
			if (cnw.getIsControlFlag() == 2) {
				ServerLink.getInstance().insert(cnw.getCenterId(),
						cnw.getNetWorkNodeID());
				centerRouteMap.put(cnw.getCenterId(), cnw);
			}
		}
		rootCenterID = this.ServerID;
		while (true) {
			CenterNetWork center = centerRouteMap.get(rootCenterID);
			if (center == null) {
				break;
			} else {
				rootCenterID = center.getNetWorkNodeID();
			}
		}

		ServerLink.getInstance().createLinkMap(this.ServerID);
		LogUtil.BusinessInfo("init ok");

	}

	public IChannel[] FindChannel(String Ids) {

		return this.groupTool.foundChannel(Ids);

	}

	public final boolean CreateGroup(IChannel Channel, String GroupName) {
		GobalGroupLeave(true, null, Channel.GetSessionId(), GroupName, null,
				null);
		esm.OnDomainMessage(Channel.GetSessionId(), GroupName, "1", "G");
		return this.groupTool.JoinGroup(Channel, GroupName);
	}

	public final boolean JoinGroup(IChannel Channel, String GroupName) {
		// System.out.println("=============join group ==========");
		// System.out.println("进来的Sessionid = " + Channel.GetSessionId());
		// System.out.println("加入的GroupName = " + GroupName);

		GobalGroupLeave(true, this.ServerID, Channel.GetSessionId(), GroupName,
				null, null);
		esm.OnDomainMessage(Channel.GetSessionId(), GroupName, "1", "G");
		// 告诉这个频道，目前该组的成员情况。
		sendClientGroupMessage(Channel, GroupName);
		return this.groupTool.JoinGroup(Channel, GroupName);
	}

	public final boolean LeaveGroup(IChannel Channel, String GroupName) {
		boolean b = this.groupTool.LeaveGroup(Channel, GroupName);
		try {
			esm.OnDomainMessage(Channel.GetSessionId(), GroupName, "0", "G");
			GobalGroupLeave(false, null, Channel.GetSessionId(), GroupName,
					null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public final boolean CreateSession(IChannel Channel) {

		boolean b = connectRule.isAllowLogin(Channel.GetSessionId() , Channel.getRemoteIP());
		
		LogUtil.SessionInfo("CreateSession :" + Channel.GetSessionId() + " : "
				+ Channel.getRemoteIP()+" isAllowLogin : "+b);

		if(!b){
			return false;
		}
		// 做出判断，
		IChannel ic = this.groupTool.getChannel(Channel.GetSessionId());

		if (ic != null && ic != Channel) {
			/**
			 * 判断这两个session的ip地址时候是否相同，如果相同，则认为是同一台机器重新进行连接， 用旧的替换新的。
			 * */

			LogUtil.SessionInfo("有相同的 same sessionid出现，开始处理，方式：后来的替换前面的");
			LogUtil.SessionInfo("new channel is: "+Channel.GetPeerEndPoint());
			LogUtil.SessionInfo("old channel is: "+ic.GetPeerEndPoint());
			try {

				ExchangeChannel ec = (ExchangeChannel) ic;

				LogUtil.SessionInfo("same session drop :" + ec.GetSessionId());

				ec.cannel();
				esm.OnDomainMessage(Channel.GetSessionId(), "", "0", "S");
				this.GobalOnline(false, null, Channel.GetSessionId(), null,
						null);
			} catch (Exception e) {
				LogUtil.sessionException(e);
			}
		}

		esm.OnDomainMessage(Channel.GetSessionId(), "", "1", "S");
		// 广告全局，
		this.GobalOnline(true, null, Channel.GetSessionId(), null, null);
		return this.groupTool.CreateSession(Channel);

	}

	public void sendMessage(Integer flag, String message, IChannel Channel) {
		try {
			BaseMessage m = new BaseMessage();
			m.SetAction("Event");
			m.SetObjURL("SystemMessageDomain.IMessageManage.OnSendMessage");
			m.AddParam("flag", flag);
			m.AddParam("message", message);
			Channel.SendMessage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final boolean DestroySession(IChannel Channel) {

		if (Channel == null || Channel.GetSessionId() == null) {
			return false;
		}

		System.out.println("DestroySession " + Channel.GetSessionId());
		checkSyncCenter(Channel);

		IChannel channel = this.groupTool.getChannel(Channel.GetSessionId());
		if (Channel != channel) {
			return false;
		}

		// 离开所有的组！
		Set<String> groupSet = this.groupTool.getSessionGroupName(Channel);
		for (String item : groupSet) {
			this.LeaveGroup(channel, item);
		}

		this.groupTool.DestroySession(Channel);

		esm.OnDomainMessage(Channel.GetSessionId(), "", "0", "S");

		// 广告全局，
		this.GobalOnline(false, null, Channel.GetSessionId(), null, null);

		return true;
	}

	/**
	 * 这里接受各个服务器发送过来的通道。 和对各个服务器的连接池。
	 * 
	 * */

	private ConcurrentHashMap<String, IChannel> serverChannelPool;

	private ConcurrentHashMap<String, ExServerClinet> clientPool;

	/**
	 * 建立客户端。并且放入池中。
	 * 
	 * @throws IOException
	 * */

	private String ServerClinetUUID = ""; // 唯一的标示

	public IChannel CreateLocalChannel(String TargetSessionid, String IP,
			int port) {
		initRoute();
		LogUtil.SessionInfo("create server connect : TargetSessionid "
				+ TargetSessionid + " IP :" + IP + "  port:" + port);
		return CreateLocalChannel(TargetSessionid, IP, port, Encoding.getUuid());
	}

	public IChannel CreateLocalChannel(String TargetSessionid, String IP,
			int port, String uuid) {
		// 两种情况，一是 ServerClinetUUID 为空表明第一次。

		if (ServerClinetUUID.equals("")) {
			ServerClinetUUID = uuid;
		} else if (ServerClinetUUID.equals(uuid)) {
			ServerClinetUUID = Encoding.getUuid();
		} else {
			return null;
		}

		SysnCenter sc = new SysnCenter(TargetSessionid);
		removeSysncCenter(sc);

		ExServerClinet ec = new ExServerClinet(TargetSessionid,
				this.getServerID(), IP, port, ServerClinetUUID);
		ec.setEs(this);

		System.out.println("开始建立内置客户端了!!!!");

		ec.serverConnect();
		return null;
	}

	public void addExServerClinet(ExServerClinet ec) {
		/**
		 * 先放入心跳包,然后放入池中 2012.04.24
		 * */
		/**
		 * 模拟一次该点下线。 2012.04.24 新增
		 * */

		clientPool.put(ec.getTargetSessionid(), ec);
	}

	public boolean removeExServerClinet(ExServerClinet ec) {

		// 同时删除掉同步中心中的该中心。

		SysnCenter sc = new SysnCenter(ec.getTargetSessionid());

		removeSysncCenter(sc);

		clientPool.remove(ec.getTargetSessionid());

		sendSysncMessageLose(ec.getTargetSessionid(), ec.getTargetSessionid());
		return true;
	}

	private boolean isSonConnect(String targetID, String sonID) {
		boolean b = false;
		if (targetID.equals(this.ServerID)) {
			b = isNetWork(targetID, sonID);
		}
		return b;
	}

	private boolean isNetWork(String targetID, String sonID) {
		return ServerLink.getInstance().isLink(targetID, sonID);
	}

	/**
	 * 当centerID离线的时，导致与本中心不通的中心
	 * */
	private List<String> findLoseCenter(String centerID) {

		// List<String> listBefore = ServerLink.getInstance().getOfflineList();
		// ServerLink.getInstance().update(centerID, this.ServerID, false);
		// ServerLink.getInstance().createLinkMap(this.ServerID);
		// List<String> listAfter = ServerLink.getInstance().getOfflineList();
		//
		// if (listBefore != null && listAfter != null) {
		// listAfter.removeAll(listBefore);
		// }
		//
		// ServerLink.getInstance().update(centerID, this.ServerID, true);
		// ServerLink.getInstance().createLinkMap(this.ServerID);

		return ServerLink.getInstance().getOfflineList(centerID);
	}

	/**
	 * @param centerId
	 *            发生中心,null 为本中心
	 * @param desc
	 *            错误描述
	 * @param errorMsg
	 *            代码异常 null ，自动生成。
	 * @param errorType
	 *            错误分类
	 * 
	 * */
	private void errDataLog(String centerId, String desc, Exception errorMsg,
			String errorType) {
		if (centerId == null)
			centerId = this.ServerID;
		if (errorMsg == null)
			errorMsg = new Exception(desc);
		ServerLink.writeLog(centerId, desc, errorMsg, errorType);
		sendSysncErrDataLog(centerId, desc, errorMsg.getMessage(), errorType);
	}

	// 向上级发送异常日志
	private void sendSysncErrDataLog(String centerId, String desc,
			String errorMsg, String errorType) {

		IMessage other = new BaseMessage();
		other.SetAction("errDataLog");
		other.AddParam("centerId", centerId);
		other.AddParam("desc", centerId);
		other.AddParam("errorMsg", centerId);
		other.AddParam("errorType", errorType);
		other.AddParam("RealCenter", this.ServerID);

		for (ExServerClinet ic : clientPool.values()) {
			ic.getEc()
					.CenterSendMessage(null, null, this.ServerID, null, other);
		}

	}

	// 接收下级的异常日志
	private void reciveSyncErrDataLog(IMessage other) {
		errDataLog(other.GetParam("centerId").toString(), other
				.GetParam("desc").toString(),
				new Exception(other.GetParam("errorMsg").toString()), other
						.GetParam("errorType").toString());
	}

	private IMessage createMessage(Boolean flag) {
		IMessage message = new BaseMessage();
		message.SetAction("reply");
		message.AddParam("connect", flag.toString());
		return message;
	}

	// 814协议，下级中心验证上级ID是否正确。
	@Override
	public void newServerClinet(IChannel Channel, String sessionid) {

		if (!Channel.GetSessionId().equals(sessionid)) {
			String targetID = sessionid.substring(Channel.GetSessionId()
					.length());
			if (!isSonConnect(targetID, Channel.GetSessionId())) {

				Channel.ServerClinet(createMessage(false));

				Channel.setError();

				errDataLog(null, Channel.GetSessionId() + " 与本中心不存在连接关系", null,
						"路由错误");

				return;
			}

			Channel.ServerClinet(createMessage(true));

		}
	}

	public void ServerClinet(IChannel Channel, String sessionid) {
		// TODO Auto-generated method stub
		// serverChannelPool.remove(sessionid);

		sessionid = Channel.GetSessionId();

		// 这里寻找一下新的池
		IChannel old = serverChannelPool.get(sessionid);
		if (old != null && old != Channel) {
			try {
				LogUtil.SessionInfo("ServerClinet :" + old.GetSessionId()
						+ " : " + old.getRemoteIP());
				ExchangeChannel oldec = (ExchangeChannel) old;
				oldec.cannel();
			} catch (Exception e) {
				LogUtil.SessionInfo("ServerClinet error:" + e.getMessage());
			}
		}
		LogUtil.SessionInfo("new ServerClinet in " + Channel.GetPeerEndPoint());
		serverChannelPool.put(sessionid, Channel);

		// BaseMessage message = new BaseMessage();
		// message.AddParam("GroupLeave", true);
		// message.AddParam("SessionID", 1231241241);
		// message.AddParam("GroupID", "sdasdasdas");
		// Channel.SendMessage(message);

		// 告诉该客户端，我这边所有的设备信息。 从本地内存数据库中读取

		// esm.onTellDeviceALL();
		// for (IMessage im : esm.getCenterMessage()) {
		//
		// Channel.GobalSendMessage(im, null, this.ServerID, null);
		//
		// }

		LogUtil.BusinessInfo("==============center 是服务器发送来的客户端 :" + sessionid);

	}

	private boolean checkcenter(String userid) {
		boolean b = false;
		try {
			if (userid.contains("@001"))
				b = true;
		} catch (Exception e) {
			return false;
		}
		return b;
	}

	// 全局上下线通报
	/**
	 * 发送的时候，剔除掉不发送的数据 凡是中心里面有的数据，不进行通报
	 * */
	public void GobalOnline(Boolean onflag, String source, String userid,
			String uid, String nosendid) {

		// 上下线，不进行全网广播了。

		if (checkcenter(userid))
			return;

		if (uid == null || uid.equals("")) {
			uid = Encoding.getUuid();
		}

		LogUtil.BusinessInfo("form：" + source + " GobalOnline " + userid
				+ "  uuid" + uid);

		/***
		 * 在此处进行本网全局通报
		 * 
		 * */
		for (IChannel ic : this.groupTool.foundChannel("ALL")) {
			if (nosendid != null && nosendid.equals(ic.GetSessionId()))
				continue;
			ic.GobalOnline(onflag, source, userid, uid);
		}

		try {
			for (IChannel ic : serverChannelPool.values()) {
				System.out.println("===============" + ic.GetSessionId());
				if (nosendid != null && nosendid.equals(ic.GetSessionId()))
					continue;
				ic.GobalOnline(onflag, source, userid, uid);
				// 发出成功也是心跳包。
				// isLiveSessionid(ic.GetSessionId());
			}

		} catch (Exception e) {

			LogUtil.error("全局广播，我自己的连接池错误" + e.getMessage());
		}

		try {

			for (ExServerClinet ic : clientPool.values()) {
				if (nosendid != null
						&& nosendid.equals(ic.getTargetSessionid()))
					continue;
				ic.getEc().GobalOnline(onflag, source, userid, uid);
				// 发出成功也是心跳包。
				// isLiveSessionid(ic.getTargetSessionid());
			}

		} catch (Exception e) {

			LogUtil.error("全局广播，网络 的连接池错误" + e.getMessage());

		}

	}

	// 全局进出组通报
	public void GobalGroupLeave(Boolean onflag, String source, String userid,
			String groupname, String uid, String nosendid) {
		if (uid == null || uid.equals("")) {
			uid = Encoding.getUuid();
		}

		// 屏蔽掉所有userid 包含006的通报
		if (userid.contains("@006")) {
			return;
		}

		LogUtil.BusinessInfo("form：" + source + " GobalGroupLeave " + userid
				+ " " + groupname + "  uuid" + uid);

		for (IChannel ic : this.groupTool.foundChannel("ALL")) {
			if (nosendid != null && nosendid.equals(ic.GetSessionId()))
				continue;
			ic.GobalGroupLeave(onflag, source, userid, groupname, uid);
		}

		try {
			for (IChannel ic : serverChannelPool.values()) {
				if (nosendid != null && nosendid.equals(ic.GetSessionId()))
					continue;
				ic.GobalGroupLeave(onflag, source, userid, groupname, uid);
				// 发出成功也是心跳包。
				// isLiveSessionid(ic.GetSessionId());
			}

		} catch (Exception e) {

			LogUtil.error("全局广播，我自己的连接池错误" + e.getMessage());
		}

		try {

			for (ExServerClinet ic : clientPool.values()) {
				if (nosendid != null
						&& nosendid.equals(ic.getTargetSessionid()))
					continue;
				ic.getEc().GobalGroupLeave(onflag, source, userid, groupname,
						uid);
				// 发出成功也是心跳包。
				// isLiveSessionid(ic.getTargetSessionid());
			}

		} catch (Exception e) {

			LogUtil.error("全局广播，网络 的连接池错误" + e.getMessage());

		}

	}

	// 通知上级相关信息
	public void GobalUpSendMessage(IMessage message, String target,
			String source, String uid, String nosend) {
		if (uid == null || uid.equals("")) {
			uid = Encoding.getUuid();
		}

		try {
			message.AddParam("RealCenter", this.ServerID);

			for (ExServerClinet ic : clientPool.values()) {

				if (nosend != null && ic.getTargetSessionid().equals(nosend)) {
					continue;
				}

				// System.out.println("我发送的目标有：" + ic.getTargetSessionid());
				ic.getEc().GobalSendMessage(message, target, source, uid);
				// 发出成功也是心跳包。
				// isLiveSessionid(ic.getTargetSessionid());
			}
		} catch (Exception e) {

			LogUtil.error("全局广播，通知上级相关信息 连接池错误" + e.getMessage());

		}

	}

	// 通知下级相关信息
	public void GobalDownSendMessage(IMessage message, String target,
			String source, String uid, String nosend) {
		if (uid == null || uid.equals("")) {
			uid = Encoding.getUuid();
		}
		try {
			message.AddParam("RealCenter", this.ServerID);

			for (IChannel ic : serverChannelPool.values()) {
				if (nosend != null && ic.GetSessionId().equals(nosend)) {
					continue;
				}

				ic.GobalSendMessage(message, target, source, uid);

				// 发出成功也是心跳包。
				// isLiveSessionid(ic.GetSessionId());
			}

		} catch (Exception e) {

			LogUtil.error("全局广播，通知下级相关信息错误" + e.getMessage());
		}

	}

	/**
	 * 2014-2-24 修改，指定中心，发送消息
	 * 
	 * @param targetCenterID
	 *            指最终发送目的
	 * @return false：发送失败 true：发送成功
	 * */

	private boolean sendTargetMessage(String targetCenterID, IMessage message,
			String target, String source, String uid, String noSendID) {

		boolean sendFlag = false;
		// 根据目标targetCenterID，计算下一步路径
		try {
			String nextCenterID = ServerLink.getInstance().getNextDot(
					targetCenterID);
			if (nextCenterID == null) {
				// 记录未找到下一步中心异常
				// LogUtil.SessionInfo("route error no found route :target is "+
				// targetCenterID);

				return false;
			}
			if (nextCenterID.equals(noSendID)) {
				return true;
			}

			ExServerClinet exServerClinet = clientPool.get(nextCenterID);

			if (exServerClinet != null) {
				exServerClinet.getEc().GobalSendMessage(message, target,
						source, uid);
				sendFlag = true;
			}
			IChannel iChannel = serverChannelPool.get(nextCenterID);
			if (iChannel != null) {
				iChannel.GobalSendMessage(message, target, source, uid);
				sendFlag = true;
			}
		} catch (Exception e) {
			LogUtil.sessionException(e);
			sendFlag = false;
		}
		return sendFlag;
	}

	// 通知下级相关信息

	public void GobalSendMessage(IMessage message, String target,
			String source, String uid, String nosend) {
		// 两个循环，一个自己的连接池，一个是服务器发送过来的通道池。

		if (uid == null || uid.equals("")) {
			uid = Encoding.getUuid();
		}

		LogUtil.BusinessInfo("GobalSendMessage :" + source + " to " + target
				+ " context: " + message.Serilize() + "  uuid" + uid);

		message.AddParam("RealCenter", this.ServerID);

		/*
		 * target 包含 Session:// 为ture 表示为服务器对客户端通讯 false 表示服务器和服务器通讯。
		 */
		if (target != null && target.contains("Session://")) {
			String targetCenterID = UsersInfo.getInstance().getCenterID(
					target.substring(10));
			if (targetCenterID != null) {
				if (sendTargetMessage(targetCenterID, message, target, source,
						uid, nosend))
					return;
			} else {
				// 记录该用户未找到中心归属
				// LogUtil.SessionInfo("route error : no found user :"+target);
			}

		} else if (target != null && !target.equals("")
				&& !target.equals("ALL") && !target.contains("Group://")) {
			if (sendTargetMessage(target, message, target, source, uid, nosend))
				return;
		}

		GobalUpSendMessage(message, target, source, uid, nosend);
		GobalDownSendMessage(message, target, source, uid, nosend);

	}

	/**
	 * 清除后发送消息的服务器的错误数据包
	 * */
	private boolean insertClient(String client, String center) {
		boolean b = false;
		if (clientMap.containsKey(client)) {
			if (center.equals(clientMap.get(client))) {
				b = true;
			}
		} else {
			clientMap.put(client, center);
			b = true;
		}

		return b;
	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {

		// 上下线，不进行全网广播了。

		System.out.println("我知道有人上下" + message.Serilize());
		// 先判断来源是否为自己，如果是，咱们停住
		if (source.equals(this.getServerID())) {
			return;
		}

		// 判断这个信息是否已经进来过，如果未，则加入链表，如果有，则停住
		// if (PacketRecord.getPacketRecord().check2(uid))
		// return;
		// LogUtil.TestInfo(uid);
		if (DuplicateCheck.getDuplicateCheck().check(source, uid)) {
			return;
		}

		String userid = message.GetParam("SessionID").toString();

		if (isLocalClient(userid))
			return;

		if (!insertClient(userid, source)) {
			return;
		}

		Boolean onflag = Boolean.valueOf(message.GetParam("OnLine").toString());

		// 在此处告诉所有组件一次。
		esm.OnDomainMessage(userid, "", onflag ? "1" : "0", "S");

		IChannel[] ics = GroupPoolTool.Instance().foundChannel(target);

		if (ics != null) {
			for (IChannel Channel : ics) {
				Channel.GobalOnline(onflag, source, userid, uid);
			}
		}

		// 基本上不用考虑了，全局通知完毕进行转发。
		GobalOnline(onflag, this.getServerID(), userid, uid, source);
	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid, IChannel channel) {

		// 814的判断，如果该通道是否为无效读取通道。
		if (channel.isError()) {
			return;
		}

		// 先判断来源是否为自己，如果是，咱们停住
		if (source.equals(this.getServerID())) {
			return;
		}

		// 判断这个信息是否已经进来过，如果未，则加入链表，如果有，则停住
		// if (PacketRecord.getPacketRecord().check2(uid))
		// return;
		// LogUtil.TestInfo(uid);
		if (DuplicateCheck.getDuplicateCheck().check(source, uid)) {
			return;
		}

		// System.out.println("我知道有组员进出" + message.Serilize());

		String userid = message.GetParam("SessionID").toString();

		if (isLocalClient(userid))
			return;

		if (!insertClient(userid, source)) {
			return;
		}

		String groupname = (String) message.GetParam("GroupID");
		Boolean onflag = Boolean.valueOf((String) message
				.GetParam("GroupLeave"));

		OtherCenterClient occ = new OtherCenterClient();
		occ.setCenterId(source);
		occ.setSessionId(userid);
		occ.setGroupName(groupname);
		if (onflag) {
			otherCenterClients.add(occ);
		} else {
			otherCenterClients.remove(occ);
		}

		// 在此处告诉所有组件一次。
		esm.OnDomainMessage(userid, groupname, onflag ? "1" : "0", "G");
		// es.getEsm().OnDomainMessage(userid, groupname, onflag ? "1" :
		// "0","G");

		// IChannel[] ics = GroupPoolTool.Instance().foundChannel(target);
		//
		// if (ics != null) {
		// for (IChannel Channel : ics) {
		// Channel.GobalGroupLeave(onflag, source, userid, groupname, uid);
		// }
		// }

		GobalGroupLeave(onflag, this.getServerID(), userid, groupname, uid,
				source);
	}

	// 判断target目标是否为客户端。

	private boolean CheckTarget(String target) {
		if (target.equals("ALL"))
			return true;
		if (target.length() >= 8 && target.substring(0, 8).equals("Group://"))
			return true;
		if (target.length() >= 10
				&& target.substring(0, 10).equals("Session://"))
			return true;
		return false;
	}

	@Override
	synchronized public void OnGobalRecive(String target, String source,
			IMessage message, String uid, IChannel channel) {

		try {

			// 814 判断通道是否无效
			if (channel.isError()) {
				return;
			}

			// 如果来源是自己，我们就停止，不再管理
			if (source.equals(this.getServerID())) {
				return;
			}
			// 判断这个信息是否已经进来过，如果未，则加入链表，如果有，则停住
			// if (PacketRecord.getPacketRecord().check(uid))
			// return;
			// LogUtil.TestInfo(uid);
			if (DuplicateCheck.getDuplicateCheck().check(source, uid)) {
				return;
			}

			// 开始进行本服务器进行处理
			LogUtil.BusinessInfo(" ===============target == " + target
					+ " source :" + source);
			if (!CheckTarget(target)) {
				LogUtil.BusinessInfo("target == " + target);
				esm.setTarget(target);
				esm.setSource(source);
				esm.OnExchangeServerMessage(null, message);
				System.out.println("是否转发" + esm.getContinueFlag());
				if (!target.equals(this.getServerID()) && esm.getContinueFlag()) {
					GobalSendMessage(message, target, this.ServerID, uid,
							source);
				}
				// if (!target.equals(this.getServerID())) {
				// GobalSendMessage(message, target, source, uid);
				// }
				return;
			}

			// 如果不是自己，判断其类型，是组 or all 还是 个人
			// 如果是个人，寻找本人是否在本服务器上。如果有。 发送给其消息。并停止发送

			LogUtil.BusinessInfo("recive：" + source + " 发送目标 " + target
					+ " 发送内容 " + message.Serilize() + "  uuid" + uid);

			if (target.length() >= 10
					&& target.substring(0, 10).equals("Session://")) {
				IChannel[] ics = GroupPoolTool.Instance().foundChannel(target);

				if (ics != null && ics.length == 1) {
					ics[0].SendMessage(message);
					LogUtil.debug("全局信息，转发到本网个人" + message.Serilize());
					return;
				}

			} else {

				IChannel[] ics = GroupPoolTool.Instance().foundChannel(target);

				if (ics != null) {

					for (IChannel Channel : ics) {
						Channel.SendMessage(message);
					}
					LogUtil.debug("全局信息，转发到本网" + message.Serilize());
				}

			}

			// 继续全局转发。

			GobalSendMessage(message, target, this.ServerID, uid, source);
		} catch (Exception e) {

			LogUtil.BusinessError("error by 2" + e.getCause());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}

			LogUtil.BusinessError("全局信息，转发到本网接收错误，来自 error form" + source);

		}

	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other, IChannel channel) {

		// 814 判断通道是否无效
		if (channel.isError()) {
			return;
		}

		// 判断是否为我们建立的通讯池数据
		if (!serverChannelPool.containsKey(source)
				&& !clientPool.containsKey(source))
			return;

		// 如果来源是自己，我们就停止，不再管理
		if (source.equals(this.getServerID())) {
			return;
		}

		// 如果是心跳包，不处理。
		if (Other.GetAction().equals("SysncMessage")) {
			return;
		}

		// 判断这个信息是否已经进来过，如果未，则加入链表，如果有，则停住
		// if (PacketRecord.getPacketRecord().check2(id))
		// return;
		// LogUtil.TestInfo(id);
		if (DuplicateCheck.getDuplicateCheck().centerMessagecheck(id)) {
			return;
		}

		// ServerLink.getInstance().writeLog("OnCenterMessage",
		// Body==null?"":Body.Serilize(), new Exception("ok"),
		// "OnCenterMessage");

		// esm.setTarget(target);
		// esm.setSource(source);

		/**
		 * 
		 * 收到该信息，表明了该中心活着 2012.04.24
		 * 
		 * */
		try {

			String sessionid = (String) Other.GetParam("RealCenter");
			if (sessionid != null) {

				/**
				 * @author zzw 20130924 如果该消息是本中心产生的，则停止。
				 * */

				if (sessionid.equals(this.ServerID)) {

					errDataLog(null, source + " 发送了一个本中心产生的信息", null, "信息回投");
					return;

				}

			}

		} catch (Exception e) {
			LogUtil.sessionException(e);
		}

		if (Other.GetAction().equals("initDec")) {
			/**
			 * 
			 * initMessage.AddParam("Form", es.getServerID());
			 * initMessage.AddParam("To", this.Sessionid);
			 * initMessage.AddParam("Flag", "0");
			 * initMessage.SetAction("initDec");
			 * 
			 * */

			String form = Other.GetParam("Form").toString();
			String to = Other.GetParam("To").toString();

			// 在此处加入上下级判断。

			boolean b = isNetWork(to, form);
			if (!b) {

				errDataLog(null, to + " initDec " + form + " 两个中心不存在连接关系",
						null, "路由错误");
				// channel.setError();
				return;
			}

			// 判断我是否是进过的那个连接的点，如果是，将值改为1.

			if (to.equals(this.getServerID())) {
				Other.AddParam("Flag", "1");
			}

			LogUtil.BusinessInfo("@@@@@@@@@@ server initflag :" + form + " to "
					+ to);

			CenterMessage(target, this.ServerID, Body, id, Other, source);

			/**
			 * 判断该如何处理这个初始化得信息。回信息回去。 如果是To 这边网络的，要往Form 那边发送初始化数据
			 * 如果是Form这边的网络的，要往To那边发送初始化数据 Flag = 1 表示 在TO这边 Flag = 0 表示在Form这边
			 * 
			 * 以上设计都无视吧，咱们只要简单的记住两道门就可以了。
			 */
			IMessage initMessage = new BaseMessage();
			initMessage.AddParam("Form", form);
			initMessage.AddParam("To", to);
			initMessage.AddParam("FormFlag", "0");
			initMessage.AddParam("ToFlag", "0");
			initMessage.SetAction("initData");
			initMessage.AddParam("RealCenter", this.ServerID);

			if (form.equals(ServerID)) {
				initMessage.AddParam("FormFlag", "1");
			}

			if (to.equals(ServerID)) {
				initMessage.AddParam("ToFlag", "1");
			}

			// 各个服务器组件的东东
			for (IMessage message : esm.getCenterMessage()) {
				CenterMessage(source, this.ServerID, message, null,
						initMessage, null);
			}

			/***
			 * 下面是进行中心和中心之间的组内容交换
			 * 
			 * */

			initMessage = new BaseMessage();
			initMessage.AddParam("Form", form);
			initMessage.AddParam("To", to);
			initMessage.AddParam("FormFlag", "0");
			initMessage.AddParam("ToFlag", "0");
			initMessage.SetAction("initGroup");
			initMessage.AddParam("RealCenter", this.ServerID);

			if (form.equals(ServerID)) {
				initMessage.AddParam("FormFlag", "1");
			}

			if (to.equals(ServerID)) {
				initMessage.AddParam("ToFlag", "1");
			}

			for (HashMap<String, String> hp : this.groupTool.getAllGroupInfo()) {
				IMessage im = new BaseMessage();
				im.AddParam("SessionId", hp.get("SessionId"));
				im.AddParam("GroupName", hp.get("GroupName"));
				im.AddParam("CenterId", ServerID);
				CenterMessage(source, this.ServerID, im, null, initMessage,
						null);
			}

			/**
			 * 把自己有的组信息也发送出去。
			 * */

		} else if (Other.GetAction().equals("initData")) {

			// 判断我是否是进过的两个连接中其中一个的点，如果是，将值改为1.

			LogUtil.BusinessInfo("server recive initData :"
					+ Other.GetParam("Form").toString());
			LogUtil.BusinessInfo(Other.GetParam("To").toString());

			String form = Other.GetParam("Form").toString();
			String to = Other.GetParam("To").toString();

			boolean b = isNetWork(to, form);

			if (!b) {
				errDataLog(null, to + " initData " + form + " 两个中心不存在连接关系",
						null, "路由错误");
				return;
			}

			if (form.equals(ServerID)) {
				Other.AddParam("FormFlag", "1");
			}

			if (to.equals(ServerID)) {
				Other.AddParam("ToFlag", "1");
			}

			// 继续广播消息

			// CenterMessage(target, source, Body, id, Other, null);
			// 这个继续广播，改为只是发送给我们的除了来源以外的.
			CenterMessage(target, this.ServerID, Body, id, Other, source);

			// 判断是否都为1，如果是，则将里面的数据进行初始化

			if (Other.GetParam("FormFlag").equals("1")
					&& Other.GetParam("ToFlag").equals("1")) {

				// otherCenterMessageSet.add(new OtherCenterMessage(Body,
				// source));
				esm.OnExchangeServerMessage(null, Body);

			}

		} else if (Other.GetAction().equals("initGroup")) {

			// 判断我是否是进过的两个连接中其中一个的点，如果是，将值改为1.

			String form = Other.GetParam("Form").toString();
			String to = Other.GetParam("To").toString();

			boolean b = isNetWork(to, form);

			if (!b) {
				errDataLog(null, to + " initData " + form + " 两个中心不存在连接关系",
						null, "路由错误");
				return;
			}

			if (form.equals(ServerID)) {
				Other.AddParam("FormFlag", "1");
			}

			if (to.equals(ServerID)) {
				Other.AddParam("ToFlag", "1");
			}

			// 继续广播消息

			// CenterMessage(target, source, Body, id, Other, null);

			// 这个继续广播，改为只是发送给我们的除了来源以外的.
			CenterMessage(target, this.ServerID, Body, id, Other, source);

			// 判断是否都为1，如果是，则将里面的数据进行初始化

			if (Other.GetParam("FormFlag").equals("1")
					&& Other.GetParam("ToFlag").equals("1")) {

				/**
				 * im.AddParam("SessionId", hp.get("SessionId"));
				 * im.AddParam("GroupName", hp.get("GroupName"));
				 * im.AddParam("CenterId", ServerID);
				 * 
				 * */
				OtherCenterClient o = new OtherCenterClient();
				o.setCenterId(Body.GetParam("CenterId").toString());
				o.setGroupName(Body.GetParam("GroupName").toString());
				o.setSessionId(Body.GetParam("SessionId").toString());

				otherCenterClients.add(o);
				clientMap.put(o.getSessionId(), o.getCenterId());

				IChannel[] icArray = null;
				if (groupTool == null) {
					System.out.println("................");
				} else {
					icArray = groupTool.foundChannel("ALL");
				}

				if (icArray != null) {
					for (IChannel ic : icArray) {
						ic.GobalGroupLeave(true, Body.GetParam("CenterId")
								.toString(), Body.GetParam("SessionId")
								.toString(), Body.GetParam("GroupName")
								.toString(), null);
					}
				}

			}

		} else if (Other.GetAction().equals("SysncMessageLose")) {

			String centerID = Other.GetParam("CenterId").toString();

			// 814 判断下线信息是否归该中心通知
			if (this.ServerID.equals(centerID)) {
				errDataLog(null,
						"错误的报告本中心" + centerID + "下线信息  form " + source,
						new Exception("SysncMessageLose error"), "路由错误");
				return;
			}
			if (!isReciveMessageRight(source, centerID)) {
				errDataLog(null, "错误的报告中心" + centerID + "下线信息  form " + source,
						new Exception("SysncMessageLose error"), "路由错误");
				return;
			}

			SysnCenter sc = new SysnCenter(centerID);
			removeSysncCenter(sc);
			// 这个继续广播，改为只是发送给我们的除了来源以外的.

			/**
			 * 2014-01-20 北京停掉所有的相关信息。
			 * */
			if (!clientPool.isEmpty()) {
				CenterMessage(target, this.ServerID, Body, id, Other, source);
			}

		} else if (Other.GetAction().equals("errDataLog")) {
			reciveSyncErrDataLog(Other);
		}

	}

	public void CenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other, String notSend) {

		// ServerLink.getInstance().writeLog("centermessage", Other.Serilize(),
		// new Exception("|"+target+"|"), "centerMSG");
		if (target != null && !target.equals("") && !target.equals("ALL")) {
			// 目标确定，只发向该方向。 但是之后的路径依然是广播。
			try {
				for (IChannel ic : serverChannelPool.values()) {
					if (ic.GetSessionId().equals(target)) {
						ic.CenterSendMessage(Body, null, source, id, Other);

						break;
					}
				}
				for (ExServerClinet ic : clientPool.values()) {
					if (ic.getTargetSessionid().equals(target)) {
						ic.getEc().CenterSendMessage(Body, null, source, id,
								Other);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		// 对所有中心发送该数据,不包括notSend那个家伙

		for (IChannel ic : serverChannelPool.values()) {
			if (notSend != null && ic.GetSessionId().equals(notSend)) {
				continue;
			}
			try {

				ic.CenterSendMessage(Body, target, source, id, Other);
				// isLiveSessionid(ic.GetSessionId());

				// ServerLink.getInstance().writeLog("centermessage",
				// ic.GetPeerEndPoint().toString(), new
				// Exception("|"+ic.GetSessionId()+"|"), "centerMSG");
			} catch (Exception e) {
				e.printStackTrace();
				// LogUtil.error("全局广播，下级中有人和我断线" + e.getMessage());
				// serverChannelPool.remove(ic.GetSessionId());
				// SysnCenter a = new SysnCenter(ic.GetSessionId());
				// this.sysnCenterSet.remove(a);
				// removeSysncCenter(a);

			}

		}

		for (ExServerClinet ic : clientPool.values()) {
			if (notSend != null && ic.getTargetSessionid().equals(notSend)) {
				continue;
			}
			try {
				ic.getEc().CenterSendMessage(Body, target, source, id, Other);
				// ServerLink.getInstance().writeLog("centermessage",
				// ic.getEc().GetPeerEndPoint().toString(), new
				// Exception("|"+ic.getTargetSessionid()+"|"), "centerMSG");
				// isLiveSessionid(ic.getTargetSessionid());
			} catch (Exception e) {
				e.printStackTrace();
				// LogUtil.error("全局广播，我和上级断线" + e.getMessage());
				// SysnCenter a = new SysnCenter(ic.getTargetSessionid());
				// this.sysnCenterSet.remove(a);
				// removeSysncCenter(a);
			}

		}

	}

	public void sendClientGroupMessage(IChannel Channel, String groupName) {

		IChannel[] ics = groupTool.foundChannel("Group://" + groupName);
		if (ics != null) {
			for (IChannel ic : ics) {
				// System.out.println("告诉新来的，有这些信息是本网的+" + ic.GetSessionId());
				Channel.GobalGroupLeave(true, this.ServerID, ic.GetSessionId(),
						groupName, null);
			}
		}
		for (OtherCenterClient o : otherCenterClients) {
			if (o.getGroupName().equals(groupName)) {
				// System.out.println("告诉新来的，有这些信息是外网的+" + o.getSessionId());
				Channel.GobalGroupLeave(true, this.ServerID, o.getSessionId(),
						groupName, null);
			}
		}

	}

	/**
	 * 临时解决方案： 1、5秒同步一次 2、10秒以上进行删除
	 * 
	 * */

	// 发送同步数据
	public void sendSysncMessage() {

		IMessage other = new BaseMessage();
		other.SetAction("SysncMessage");
		other.AddParam("CenterId", this.ServerID);
		other.AddParam("RealCenter", this.ServerID);
		// CenterMessage("ALL", this.ServerID, new BaseMessage(), null, other,
		// null);

		CenterMessage(null, this.ServerID, null, null, other, null);
		// LogUtil.BusinessError("===sned  center sysn message");
	}

	// 发布此中心下线通知
	public void sendSysncMessageLose(String sessionid, String nosendid) {

		IMessage other = new BaseMessage();
		other.SetAction("SysncMessageLose");
		other.AddParam("CenterId", sessionid);
		other.AddParam("RealCenter", this.ServerID);
		CenterMessage(null, this.ServerID, null, null, other, nosendid);
	}

	// 接收同步数据
	public void reciveSysncMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {

	}

	/**
	 * 2014-01-19 解决814问题中客户端是否为本地的连接
	 * */

	private boolean isLocalClient(String clientID) {
		boolean b = false;
		IChannel[] channelArray = this.groupTool.foundChannel("ALL");
		if (channelArray != null) {
			for (IChannel channel : channelArray) {
				if (channel.GetSessionId().equals(clientID)) {
					b = true;
					break;
				}
			}
		}

		return b;
	}

	/**
	 * 判断那些中心属于断线了，发送指令进行操作。
	 * */
	public boolean removeSysncCenter(SysnCenter sc) {

		LogUtil.SessionInfo("remove center" + sc.centerId);

		// 如果是本中心下线，则认为不正常。
		if (sc.centerId.equals(this.ServerID)) {
			errDataLog(null, "中心认为自己下线", new Exception("中心认为自己下线"), "逻辑判断异常");
			return false;
		}

		// 超标时间

		// LogUtil.BusinessInfo("@@@@remove  other center :" + sc.centerId +
		// "  "
		// + otherCenterMessageSet.size());

		// if (!sysnCenterSet.remove(sc))
		// return false;
		esm.OnDomainMessage(sc.centerId, "", "0", "C");

		// 头大的地方，不过哥们有办法。让设备下线 _Sync
		// try {
		// Iterator<OtherCenterMessage> sd = otherCenterMessageSet.iterator();
		// while (sd.hasNext()) {
		// OtherCenterMessage om = sd.next();
		// if (om.centerId.equals(sc.centerId)) {
		//
		// String temp = om.im.GetObjURL();
		// om.im.SetObjURL(temp + "_Sync");
		// esm.OnExchangeServerMessage(null, om.im);
		// om.im.SetObjURL(temp);
		// LogUtil.BusinessInfo("@@@@use " + om.im.GetObjURL());
		// otherCenterMessageSet.remove(om);
		// }
		//
		// }
		// } catch (Exception e) {
		//
		// LogUtil.BusinessError("big error5");
		// LogUtil.BusinessError(e.getMessage());
		// }

		// 将组里面的人员全部模拟一次退组
		// 安全第一，先获得全部，然后删除，不在一个容器里面进行操作。
		Set<String> clientSet = new HashSet<String>();

		Iterator<OtherCenterClient> itor = otherCenterClients.iterator();
		while (itor.hasNext()) {
			OtherCenterClient oc = itor.next();
			try {
				if (!oc.getCenterId().equals(sc.centerId))
					continue;

				// 如果是本地，不进行处理。
				if (isLocalClient(oc.getSessionId()))
					continue;

				esm.OnDomainMessage(oc.getSessionId(), oc.getGroupName(), "0",
						"G");
				clientSet.add(oc.getSessionId());
			} catch (Exception e) {

				LogUtil.BusinessError("big error4");
				LogUtil.BusinessError(e.getCause());
				for (StackTraceElement x : e.getStackTrace()) {
					LogUtil.BusinessError(x.toString());
				}
			}

			for (IChannel ic : this.groupTool.foundChannel("ALL")) {
				try {
					ic.GobalGroupLeave(false, this.ServerID, oc.getSessionId(),
							oc.getGroupName(), null);

				} catch (Exception e) {

					LogUtil.BusinessError("big error4.1");
					LogUtil.BusinessError(e.getCause());
					for (StackTraceElement x : e.getStackTrace()) {
						LogUtil.BusinessError(x.toString());
					}
				}
			}
			try {
				otherCenterClients.remove(oc);
			} catch (Exception e) {

				LogUtil.BusinessError("big error4.2");
				LogUtil.BusinessError(e.getCause());
				for (StackTraceElement x : e.getStackTrace()) {
					LogUtil.BusinessError(x.toString());
				}
			}
		}

		// 统一发布一次下线：
		for (String str : clientSet) {

			esm.OnDomainMessage(str, "", "0", "S");

			for (IChannel ic : this.groupTool.foundChannel("ALL")) {
				try {
					ic.GobalOnline(false, this.ServerID, str, null);
				} catch (Exception e) {
					LogUtil.BusinessError("big error4.33");
					LogUtil.BusinessError(e.getCause());
					for (StackTraceElement x : e.getStackTrace()) {
						LogUtil.BusinessError(x.toString());
					}
				}
			}
		}

		return true;

	}

	private boolean checkSyncCenter(IChannel channel) {
		String centerID = null;

		for (IChannel ic : serverChannelPool.values()) {
			if (ic == channel)
				centerID = ic.GetSessionId();
		}

		for (ExServerClinet ic : clientPool.values()) {
			if (ic.getEc() == channel)
				centerID = ic.getTargetSessionid();
		}

		if (centerID == null)
			return false;

		if (centerID.equals(this.ServerID)) {
			return true;
		}

		SysnCenter sc = new SysnCenter(centerID);
		removeSysncCenter(sc);
		sendSysncMessageLose(sc.centerId, sc.centerId);

		List<String> listLose = findLoseCenter(sc.centerId);
		if (listLose != null) {
			for (String s : listLose) {
				removeSysncCenter(new SysnCenter(s));
				sendSysncMessageLose(s, sc.centerId);
			}
		}

		serverChannelPool.remove(centerID);
		clientPool.remove(centerID);

		return true;
	}

	// public void checkSysncCenter() {
	// // Iterator<SysnCenter> itor = sysnCenterSet.iterator();
	// // while (itor.hasNext()) {
	//
	// // 这里不再判断池中的，而是只判断上下级
	// Set<String> set = new HashSet<String>();
	//
	// for (IChannel ic : serverChannelPool.values()) {
	// set.add(ic.GetSessionId());
	// }
	//
	// for (ExServerClinet ic : clientPool.values()) {
	// set.add(ic.getTargetSessionid());
	// }
	//
	// Iterator<String> itor = set.iterator();
	//
	// while (itor.hasNext()) {
	// String str = itor.next();
	// SysnCenter sc = new SysnCenter(str);
	//
	// // SysnCenter sc = itor.next();
	// // System.out.println("====" + sc.centerId + sc.time);
	// if (check(sc.centerId)) {
	// // System.out.println(sc.centerId + "掉线了");
	//
	// LogUtil.BusinessInfo("中心长时间未发送心跳包。 long time" + sc.centerId);
	// // LogUtil.BusinessInfo("中心长时间未发送心跳包。 long time" + str);
	//
	// /**
	// * 4.5号处理 如果是自己中心,不做任何处理
	// *
	// * */
	// if (str.equals(this.ServerID)) {
	// LogUtil.BusinessInfo("is me return");
	// return;
	// }
	// if (sysnCenterSet.remove(sc)) {
	// removeSysncCenter(sc);
	// sendSysncMessageLose(sc.centerId, sc.centerId);
	// // //此处删除该中心所有的通知数据。
	// // if (this.centerHearPool.containsKey(sc.centerId)) {
	// // for (String s : centerHearPool.get(sc.centerId)) {
	// // if (s.equals(sc.centerId) || s.equals(this.ServerID))
	// // continue;
	// // removeSysncCenter(new SysnCenter(s));
	// // sendSysncMessageLose(s, sc.centerId);
	// // }
	// // centerHearPool.remove(sc.centerId);
	// // }
	//
	// // 该中心与本级连接断开后，导致间接断开的中心
	// List<String> listLose = findLoseCenter(sc.centerId);
	// if (listLose != null) {
	// for (String s : listLose) {
	// removeSysncCenter(new SysnCenter(s));
	// sendSysncMessageLose(s, sc.centerId);
	// }
	// }
	//
	// // if (removeSysncCenter(sc)) {
	// try {
	//
	// if (serverChannelPool.containsKey(sc.centerId)) {
	// // 干掉该channcel
	// ExchangeChannel ecc = (ExchangeChannel) serverChannelPool
	// .get(sc.centerId);
	// ecc.cannel();
	// LogUtil.SessionInfo("serverChannelPool remove 1480"
	// + ecc.GetPeerEndPoint());
	// serverChannelPool.remove(sc.centerId);
	//
	// }
	//
	// if (clientPool.containsKey(sc.centerId)) {
	// // 先干掉channcel，然后连接。
	// clientPool.get(sc.centerId).OnAgainConnectCommand();
	// }
	//
	// } catch (Exception e) {
	// LogUtil.BusinessError("big error6");
	// LogUtil.BusinessError(e.getMessage());
	//
	// }
	// }
	// }
	// }
	//
	// }

	boolean f = true;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// System.out.println("时间到，看看有没有掉线的啦");

			try {
				sendSysncMessage();
			} catch (Exception e) {
				LogUtil.BusinessError("big error1" + e.getCause());
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.BusinessError("error by ==" + s.toString());
				}
			}
			// try {
			// checkSysncCenter();
			//
			// } catch (Exception e) {
			//
			// LogUtil.BusinessError("big error2" + e.getCause());
			// for (StackTraceElement s : e.getStackTrace()) {
			// LogUtil.BusinessError("error by ==" + s.toString());
			// }
			//
			// }
			// try {
			// checkActiveChannel();
			// } catch (Exception e) {
			// LogUtil.BusinessError("big error3" + e.getCause());
			// for (StackTraceElement s : e.getStackTrace()) {
			// LogUtil.BusinessError("error by ==" + s.toString());
			// }
			// }

		}

	}

	@Override
	public void OnAgainConnect(IChannel channel) {
		// 断线通知,先判断是否为中心断开，否则判断是否为本地kehud
		// LogUtil.SessionInfo("channel onagainConnect ");
		// checkSyncCenter(channel);
		// checkActiveChannel(channel);
	}

	static public void main(String[] args) {

		// SysnCenter a = new SysnCenter("aaaa");
		// System.out.println(a.time);
		// HashSet<SysnCenter> m = new HashSet<SysnCenter>();
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// SysnCenter b = new SysnCenter("aaa");
		// m.add(b);
		//
		// System.out.println(m.size());
		//
		// for (SysnCenter d : m) {
		// System.out.println("==" + d.time);
		// }
		String temp = "Session://2233";
		System.out.println(temp.substring(10));

	}

	private int session_num;

	// 不再工作，废弃
	@Override
	@Deprecated
	public void onActiveFlag(IChannel Channel) {
	}

	/**
	 * 判断心跳消息和中心下线消息，来源是否和消息相符。
	 * 
	 * @param sourceCenterID
	 *            消息的来源
	 * @param centerID
	 *            消息中的指定的ID
	 * 
	 * */
	public boolean isReciveMessageRight(String sourceCenterID, String centerID) {
		boolean isRight = false;
		if (sourceCenterID.equals(centerID)) {
			return true;
		}

		CenterNetWork center = centerRouteMap.get(centerID);
		if (center == null && !centerID.equals(this.rootCenterID)) {
			return false;
		}

		while (center != null) {

			if (center.getNetWorkNodeID().equals(sourceCenterID)) {
				isRight = true;
				break;
			}

			if (center.getNetWorkNodeID().equals(this.ServerID)) {
				isRight = false;
				break;
			}
			center = centerRouteMap.get(center.getNetWorkNodeID());
		}

		if (center == null) {
			center = centerRouteMap.get(sourceCenterID);
			if (center != null
					&& !center.getNetWorkNodeID().equals(this.ServerID)) {
				isRight = true;
			}
		}

		return isRight;
	}

	/**
	 * 处理压缩后的数据
	 * */
	@Override
	public void ProcessData(IChannel exchangeChannel, byte[] body) {
		// TODO Auto-generated method stub

	}

}
