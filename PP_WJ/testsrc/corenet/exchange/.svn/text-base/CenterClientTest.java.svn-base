package corenet.exchange;

import java.util.HashMap;

import org.misc.log.LogUtil;

import corenet.exchange.Group.GroupPoolTool;
import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseSession;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;

public class CenterClientTest extends Thread implements Clientlistener,
BaseSessionListen {
	
	private ExchangeClient ec;
	private ExchangeServer es; 
	private String TargetSessionid;
	private String Sessionid;
	private String IP;
	private int port;
	
	private String uuid;//客户端的唯一标示

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

	public CenterClientTest(String TargetSessionid, String sessionid, String IP,
			int port, String serverClinetUUID) {
		// super();
		this.TargetSessionid = TargetSessionid;
		this.Sessionid = sessionid;
		this.IP = IP;
		this.port = port;
		uuid = serverClinetUUID;

	}

	@Override
	public void OnRecvMessage(IMessage Message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void OnConnectExchange(IChannel Channel) {
		// TODO Auto-generated method stub
		LogUtil.BusinessInfo(" connect go = true");
		go = true;

	}

	boolean go = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		LogUtil.BusinessInfo(" connect start @@@@@@@@@@" + TargetSessionid + " | me=" +Sessionid + "IP = "+IP);
		boolean b = true;
		while (b) {

			try {
				go = false;
				ec = new ExchangeClient();
				ec.setClientListen(this);
				ec.SetSessionId(Sessionid);
				LogUtil.BusinessInfo(" connect start 22 @@@@@@@@@@");
				ec.ConnectExchange(IP, port);
				b = false;
				LogUtil.BusinessInfo(" connect ok @@@@@@@@@@@@");
				System.out.println(go);
				int n = 1;
				while (!go) {
					Thread.sleep(1000);
					n++;
					if (n > 15) {
						b = true;
						break;
					}
				}
				if (!go) {
					// 无回馈哦。进行重新连接。
					b = true;
					ec.get_Base().cannel();
				}

			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.BusinessInfo(" connect fail @@@@@@@@@@@@  wait 5 s");
				if (ec != null) {
					ec.get_Base().cannel();
					ec = null;
				}
				// TODO Auto-generated catch block
				b = true;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		ec.CreateClient();
//		ec.CreateClient(TargetSessionid);
//		es.addExServerClinet(null);
		
		sendflag = true;
//		sendCenterInitMessage();
		sendflag = false;

	}

	private boolean sendflag = false;
	
	@Override
	public void OnNewConnection(IChannel Channel) {
		// 第一次发送信息
	}

	@Override
	public void OnAgainConnect() {
		// TODO Auto-generated method stub
		LogUtil.BusinessInfo("connect error, again @@@");
		// if (!es.removeExServerClinet(this)) {
		// return
		// new Thread(this).start();
		// ec.setC lientListen(null);
	}


	@Override
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid) {
		System.out.println(message.Serilize());
	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {
		// TODO Auto-generated method stub
		System.out.println(message.Serilize());

	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid) {
		// TODO Auto-generated method stub
		System.out.println(message.Serilize());
	}

	@Override
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] Body) {
	

	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {
		// TODO Auto-generated method stub
//		es.OnCenterMessage(target, source, Body, id, Other,ec);
//		System.out.println(Other.Serilize());
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
			System.out.println("connect reply false , fail");
			try {
			while(sendflag){
				Thread.sleep(2000);
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
	
	
	//发送错误的中心下线信息
	
	// 发布此中心下线通知
	public IMessage createSysncMessageLose(String sessionid,String form) {
		IMessage other = new BaseMessage();
		other.SetAction("SysncMessageLose");
		other.AddParam("CenterId", sessionid);
		other.AddParam("RealCenter", form);
		sendMessage(other,null);
		
		return other;
	}
	
	
	
	public void sendMessage(IMessage message,IMessage body){
		
		System.out.println("send  " + message.Serilize());
		ec.CenterSendMessage(body, this.TargetSessionid,
				this.Sessionid, null, message);
	}
		

	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		/**
		 * 发送中心下线信息，
		 * 主要包括 （存在 ：  在network中心表中存在该中心，有 z0 z1 z2 z3
		 * 			不存在：在netwirk中心表中不存在该中心）
		 * 
		 * 1、 本级存在、 发送存在中心下线，该中心不该本级通知。
		 * 3、本级存在、 发送存在中心下线，上级中心下线
		 * 6、本级存在，发送本级下线信息
		 * 2、本级存在、 发送不存在中心下线
	 
		 * 4、本级不存在、发送存在中心下线消息
		 * 5、本级不存在、发送不存在中心下线
		 * 7、本级不存在，发送本级下线消息
		 * 
		 */
		

		// 1、 本级存在、 发送非本级关联中心下线
		// 结论：进入消息判断，上级判断该信息报告错误。不予处理
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z3", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z1", "z3");
		
		//2、本级存在、发送不存在中心下线
		//结论：进入消息判断，上级判断该信息报告错误。不予处理
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z3", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z6", "z3");
		
		//3、本级存在、发送上级中心下线
		//结论：上级判断发送的中心为本中心，不予处理。
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z3", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z2", "z3");		
		
		//4、本级不存在、发送存在中心下线消息
		//结论：进入消息判断，上级判断该信息报告错误。不予处理
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z4", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z1", "z3");			
		
		//5、本级不存在、发送不存在中心下线
		//结论：进入消息判断，上级判断该信息报告错误。不予处理
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z4", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z8", "z4");			
		
		// 6、本级存在，发送本级下线信息
		//结论：进入消息判断，上级接收该信息，正常处理该级下线
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z3", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z3", "z4");		
		
		//7、本级不存在，发送本级下线消息
		//结论： 进入消息判断，上级接收该信息，正常处理该级下线
//		CenterClientTest centerClientTest = new CenterClientTest("z2", "z3", "0.0.0.0", 1800, "zw21");
//		centerClientTest.run();
//		centerClientTest.createSysncMessageLose("z3", "z4");			
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
