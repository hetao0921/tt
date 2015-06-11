package Runtime.impl;

import java.util.HashMap;
import java.util.Timer;

import org.misc.log.LogUtil;

import corenet.exchange.ExchangeClient;
import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseSession;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;

import Runtime.GrobalMessage;
import Runtime.IConnectOK;
import Runtime.IRunTime;
import Runtime.ReturnDo;

public class RunTime implements IRunTime, Clientlistener, BaseSessionListen {

	/**
	 * 用来放置调用前的环境，方便执行后，调用后续
	 * 
	 * */

	private HashMap<String, ReturnDo> proxyHp;

	private IChannel Channel;

	private IConnectOK co = null;

	public void setNewConnectOk(IConnectOK co) {
		this.co = co;
	}

	/**
	 * 用来接收全局信息的接口
	 * */
	private GrobalMessage gm;

	public GrobalMessage getGm() {
		return gm;
	}

	public void setGm(GrobalMessage gm) {
		this.gm = gm;
	}

	// 做一个标示，表示连接上与否。
	boolean connectFlag = false;

	public RunTime() {
		proxyHp = new HashMap<String, ReturnDo>();
	}

	public void registerProxy(String domainName, ReturnDo obj) {
		proxyHp.put(domainName, obj);
	}

	// static public RunTime getRunTime() {
	// if (rt == null) {
	// rt = new RunTime();
	// }
	// return rt;
	// }

	/**
	 * 执行前，先保存一次;
	 * 
	 * */
	public void Invoke(String ObjUrl, HashMap<String, Object> Param,
			ReturnDo AsyncResult, Object Context) {
		// InvokeContext aInvokeContext = new InvokeContext();
		// aInvokeContext.setAsyncResult(AsyncResult);
		// aInvokeContext.setObjUrl(ObjUrl);
		// if(Context == null ) Context = ObjUrl;
		// aInvokeContext.setContext(Context);
		//
		// InvokeList.add(aInvokeContext);

		BaseMessage Message = new BaseMessage();

		Message.AddParams(Param);
		Message.SetObjURL(ObjUrl);

		Channel.SendMessage(Message);

		return;
	}

	private int doing = 0;

	@Override
	public void OnAgainConnect() {
		if (doing == 1) {
			return;
		}
		doing = 1;
		connectFlag = false;
		System.out.println("===请求重新连接");

		// TODO Auto-generated method stub

		// 做一个新的连接 or 抛出去？
		/**
		 * 重新的sessionid啊，什么的
		 * */
		if (this.co != null) {
			this.co.onAgainConnect();
		} else {
			System.out.println("co is null");
		}

		doing = 0;

	}

	@Override
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] Body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnConnectExchange(IChannel Channel) {
		// TODO Auto-generated method stub
		System.out.println("连接成功！");
		// TODO Auto-generated method stub
		connectFlag = true;
		if (this.co != null) {
			co.connect();
		}

	}

	@Override
	public void OnNewConnection(IChannel Channel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTransChannel(IChannel aChannel) {

		Channel = aChannel;
		try {
			((ExchangeClient) Channel).setClientListen(this);
		} catch (Exception e) {
			LogUtil.error("弄来的不是客户端用的通道！！" + e.getMessage());
		}
	}

	/**
	 * 执行完毕，查找一次，看执行方式
	 * 
	 * */
	@Override
	public void OnRecvMessage(IMessage Message) {
		// TODO Auto-generated method stub
		BaseMessage aMessage = (BaseMessage) Message;

		// // System.out.println("\n\nRunTime RecvMessage Action = " +
		// Message.GetAction());
		// for(InvokeContext ic : InvokeList) {
		//
		// if(ic.getObjUrl().equals(aMessage.GetObjURL())){
		// //找到当时的环境ic.getAsyncResult(),然后进行处理
		// ic.getAsyncResult().ReturnValue(aMessage.GetParams(),ic.getContext().toString());
		// InvokeList.remove(ic);
		// break;
		// }
		//
		// }
		// 根据url判断 是哪个代理类 ，直接是方法调用方法的处理，是事件调用事件的，现在测试 ，我直接就用 代理类了。
		System.out.println(Message.Serilize());

		if (aMessage.GetAction() == null) {
			// 不处理
			// ReturnDo rd = this.proxyHp.get("proxyTest");
			// if (rd != null)
			// rd.ReturnEvent(aMessage.GetParams(), aMessage.GetObjURL());
		} else if (aMessage.GetAction().equals("Event")) {
			// 判断是事件，寻找对应的代理类，调用其 ReturnEvent
			String url = aMessage.GetObjURL();
			String domainName = url.substring(0, url.indexOf("."));

			System.out.println("domainName:" + domainName);
			ReturnDo rd = this.proxyHp.get(domainName);
			if (rd != null)
				rd.ReturnEvent(aMessage.GetParams(), url);

		} else if (aMessage.GetAction().equals("Function")) {
			// 判断是事件，寻找对应的代理类，调用其ReturnFunction
			String url = aMessage.GetObjURL();
			String domainName = url.substring(0, url.indexOf("."));
			ReturnDo rd = this.proxyHp.get(domainName);
			if (rd != null)
				rd.ReturnFunction(aMessage.GetParams(), url);

		}

	}

	@Override
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {
		// <Head><Action>null</Action><ObjURL>null</ObjURL></Head><SessionID>fea613d99d941329a3948ec55595@007</SessionID><OnLine>true</OnLine>
		if (gm != null) {
			String sessionid = message.GetParam("SessionID").toString();
			String flag = message.GetParam("OnLine").toString();
			gm.OnGlobalOnline(sessionid, flag.equals("true") ? true : false);
		} else {
			System.out.println("上下线" + message.Serilize());
		}
	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid) {
		// <Head><Action>null</Action><ObjURL>null</ObjURL></Head><SessionID>d5aa53ebbbdd4601b0f97780f1694b63</SessionID><GroupID>G_device_state</GroupID><GroupLeave>true</GroupLeave>
		if (gm != null) {
			String sessionid = message.GetParam("SessionID").toString();
			String groupname = message.GetParam("GroupID").toString();
			String flag = message.GetParam("GroupLeave").toString();
			gm.OnGlobalGroupLeave(sessionid, groupname,
					flag.equals("true") ? true : false);
		} else {
			System.out.println("进出组：" + message.Serilize());
		}
	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActiveFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnServerClinet(IMessage message) {
		System.out.println(message.Serilize());

	}

	private String uuid;
	public String getUUID() {
		return uuid;
	}
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

}
