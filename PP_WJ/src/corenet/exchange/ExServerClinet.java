package corenet.exchange;

import java.util.HashMap;

import org.misc.log.LogUtil;

import corenet.exchange.Group.GroupPoolTool;
import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseSession;
import corenet.netbase.NIOReactor;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.netbase.Interface.ITimer;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;

public class ExServerClinet extends Thread implements Clientlistener,
		BaseSessionListen {
	private ExchangeClient ec;
	private ExchangeServer es; 
	private String TargetSessionid;
	private String Sessionid;
	private String IP;
	private int port;
	
	private String uuid;//客户端的唯一标示
	
	
	private ITimer time; // 计时器
	private boolean tiemStartflag; //任务是否存在标示

	public String getTargetSessionid() {
		return TargetSessionid;
	}

	public void setTargetSessionid(String targetSessionid) {
		TargetSessionid = targetSessionid;
	}

	public ExchangeClient getEc() {
		return ec;
	}

	public ExchangeServer getEs() {
		return es;
	}

	public void setEs(ExchangeServer es) {
		this.es = es;
	}

	public ExServerClinet(String TargetSessionid, String sessionid, String IP,
			int port, String serverClinetUUID) {
		// super();
		this.TargetSessionid = TargetSessionid;
		this.Sessionid = sessionid;
		this.IP = IP;
		this.port = port;
		uuid = serverClinetUUID;
		
		
		NIOReactor r = (NIOReactor)NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
	}

	
	//服务器建立连接，并建立延迟任务，10秒后判断连接是否成功
	public void  serverConnect(){
        go = false;
			ec = new ExchangeClient();
			uuid = Encoding.getUuid();
			ExServerClinetEx eex = new ExServerClinetEx(this, uuid);
			ec.setClientListen(eex);
			ec.SetSessionId(Sessionid);
			
			LogUtil.SessionInfo("creat new server conncet , uuid:"+uuid);
			time.schedule(10000);
			tiemStartflag = true;
			
			
			try {
				ec.ConnectExchange(IP, port);
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	
	
	
	
	
	
	
	
	@Override
	public void OnRecvMessage(IMessage Message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void OnConnectExchange(IChannel Channel) {
		// TODO Auto-generated method stub
		LogUtil.SessionInfo(" connect server = true");
		go = true;

	}

	boolean go = false;
	
	
	
	

	@Override
	public void run() {
		
		
		LogUtil.SessionInfo("task run： connect state :"+go);
		
		if(go){
			//允许异常后，进行重新连接
			LogUtil.SessionInfo("task run：connect ok,now init");	
			tiemStartflag = false;
			
			ec.CreateClient();
			ec.CreateClient(TargetSessionid);
			es.addExServerClinet(this);
			
			sendflag = true;
			sendCenterInitMessage();
			sendflag = false;
			
		} else {
			//继续重新连接
			LogUtil.SessionInfo("task run：again connect");
			ec.get_Base().cannel();
			serverConnect();
		}
	


	}

	private boolean sendflag = false;
	
	@Override
	public void OnNewConnection(IChannel Channel) {
		// 第一次发送信息
	}

	@Override
	public void OnAgainConnect() {

		LogUtil.SessionInfo("OnAgainConnect hava a task : "+tiemStartflag);
		if(tiemStartflag){
			LogUtil.SessionInfo("OnAgainConnect connecting error");
			go = false;
		} else {
			LogUtil.SessionInfo("OnAgainConnect error  after connect ok ,start new task");
			tiemStartflag = true;
			go = false;
			time.schedule(5000);
			//			connect();
		}
	}


	@Override
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid) {
		es.OnGobalRecive(target, source, message, uid,ec);
	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {
		// TODO Auto-generated method stub
		es.OnGlobalOnline(target, source, message, uid);

	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid) {
		// TODO Auto-generated method stub
		es.OnGlobalGroupLeave(target, source, message, uid,ec);
	}

	@Override
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] Body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {
		// TODO Auto-generated method stub
		es.OnCenterMessage(target, source, Body, id, Other,ec);

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (obj instanceof ExServerClinet)
				&& this.TargetSessionid.equals(((ExServerClinet) obj)
						.getTargetSessionid());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.TargetSessionid.hashCode();
	}

	@Override
	public void onActiveFlag() {
	}

	@Override
	public void OnServerClinet(IMessage basemessage) {
		
		boolean conncetok = false;

		if (basemessage.GetAction().equals("reply")) {
			if (basemessage.GetParam("connect").equals("true")) {
				conncetok = true;
			}
		}
		
		
		if(!conncetok) {
			LogUtil.SessionInfo("connect reply false , fail");
			try {
			while(sendflag){
				Thread.sleep(2000);
			}
			
			if (ec != null) {
				ec.get_Base().cannel();
				ec = null;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		}
		
		
		

	
		
	}
	
	private void sendCenterInitMessage() {
		
		
		// 收到服务器发送相关消息（可以连接 、 不可连接）
		LogUtil.BusinessInfo(" connect ok @@@@@@@@@@@@ now after 0s send data");
	
		/**
		 * Flag:0 还没进过 TO这个点， 1 进过此点。
		 * */
		IMessage initMessage = new BaseMessage();
		initMessage.AddParam("Form", es.getServerID());
		initMessage.AddParam("To", this.TargetSessionid);
		initMessage.AddParam("Flag", "0");
		initMessage.SetAction("initDec");
		initMessage.AddParam("RealCenter", es.getServerID());

		es.CenterMessage("ALL", es.getServerID(), new BaseMessage(), null,
				initMessage, null);

		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/**
		 * FormFlag:0 还没进过Form这个点， 1 进过此点。 ToFlag:0 还没进过To这个点， 1 进过此点。
		 * */
		initMessage = new BaseMessage();
		initMessage.AddParam("Form", es.getServerID());
		initMessage.AddParam("To", this.TargetSessionid);
		initMessage.AddParam("FormFlag", "1");
		initMessage.AddParam("ToFlag", "0");
		initMessage.SetAction("initData");
		initMessage.AddParam("RealCenter", es.getServerID());

		for (IMessage message : es.getEsm().getCenterMessage()) {

			System.out.println("===传送出去的信息" + message.Serilize());
			ec.CenterSendMessage(message, this.TargetSessionid,
					es.getServerID(), null, initMessage);
		}

		// 这里把本机的同步组信息发布过去。
		initMessage = new BaseMessage();
		initMessage.AddParam("Form", es.getServerID());
		initMessage.AddParam("To", this.TargetSessionid);
		initMessage.AddParam("FormFlag", "1");
		initMessage.AddParam("ToFlag", "0");
		initMessage.SetAction("initGroup");
		initMessage.AddParam("RealCenter", es.getServerID());

		for (HashMap<String, String> hp : GroupPoolTool.Instance()
				.getAllGroupInfo()) {
			IMessage im = new BaseMessage();
			im.AddParam("SessionId", hp.get("SessionId"));
			im.AddParam("GroupName", hp.get("GroupName"));
			im.AddParam("CenterId", es.getServerID());
			ec.CenterSendMessage(im, this.TargetSessionid, es.getServerID(),
					null, initMessage);
		}

		// IMessage other = new BaseMessage();
		// other.SetAction("SysncMessage");
		// other.AddParam("CenterId",this.Sessionid);
		// es.CenterMessage("ALL", es.getServerID(), new BaseMessage(), null,
		// other,null);
		//
		//

		// es.GobalSendMessage(message, null, es.getServerID(), null);
		// es.getEsm().onTellDeviceALL();
		LogUtil.BusinessInfo(" connect over @@@@@@@@@@");
	}

	//此处扩展处理OnAgainConnect
	public void OnAgainConnect(String uuid2) {
		LogUtil.SessionInfo("Ex call : "+ uuid2);
		if(uuid2.equals(uuid)){
			LogUtil.SessionInfo("yes");
			this.OnAgainConnect();
		}
	}

	public void OnConnectExchange(String uuid2, IChannel channel) {
		LogUtil.SessionInfo("Ex  OnConnectExchange: "+ uuid2);
		if(uuid2.equals(uuid)){
			LogUtil.SessionInfo("yes");
			this.OnConnectExchange(channel);
		}
	}

	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid, String uuid2) {
//		LogUtil.SessionInfo("Ex  OnGlobalRecvMessage: "+ uuid2);
		if(uuid2.equals(uuid)){
			this.OnGlobalRecvMessage(target, source, message, uid);
		}
	}

	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid, String uuid2) {
//		LogUtil.SessionInfo("Ex  OnGlobalOnline: "+ uuid2);
		if(uuid2.equals(uuid)){
			this.OnGlobalOnline(target, source, message, uid);
		}
	}

	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid, String uuid2) {
		if(uuid2.equals(uuid)){
			this.OnGlobalGroupLeave(target, source, message, uid);
		}
	}

	public void OnCenterMessage(String target, String source, IMessage body,
			String id, IMessage other, String uuid2) {
		if(uuid2.equals(uuid)){
			this.OnCenterMessage(target, source, body, id, other);
		}
		
	}

	public void OnServerClinet(IMessage message, String uuid2) {
		if(uuid2.equals(uuid)){
			this.OnServerClinet(message);
		}
	}
	
	
	
	

}
