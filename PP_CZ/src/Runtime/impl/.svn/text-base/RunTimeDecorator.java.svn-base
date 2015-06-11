package Runtime.impl;

import java.util.HashMap;

import org.misc.log.LogUtil;

import Runtime.IConnectOK;
import Runtime.IRunTime;
import Runtime.ReturnDo;
import corenet.exchange.Encoding;
import corenet.exchange.ExchangeClient;
import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseSession;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.IMessage;

/**
 * 为了解决重连过程中，错误的消息投递，加入消息通道uuid
 * */
public class RunTimeDecorator implements IRunTime, Clientlistener,
		BaseSessionListen {

	private RunTime runTime;
	private String uuid;

	public RunTimeDecorator(RunTime runTime) {
		this.runTime = runTime;
		uuid = Encoding.getUuid();
		this.runTime.setUUID(uuid);
	}

	@Override
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] Body) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnReadMessage(Session, Header, Body);
		}
	}

	@Override
	public void onActiveFlag() {
		if (uuid.equals(runTime.getUUID())) {
			runTime.onActiveFlag();
		}
	}

	@Override
	public void OnConnectExchange(IChannel Channel) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnConnectExchange(Channel);
		}

	}

	@Override
	public void OnNewConnection(IChannel Channel) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnNewConnection(Channel);
		}

	}

	@Override
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnGlobalRecvMessage(target, source, message, uid);
		}

	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnGlobalOnline(target, source, message, uid);
		}

	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnGlobalGroupLeave(target, source, message, uid);
		}

	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnCenterMessage(target, source, Body, id, Other);
		}
	}

	@Override
	public void OnServerClinet(IMessage message) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnServerClinet(message);
		}
	}

	// 此处修改为本对象作为监控
	@Override
	public void setTransChannel(IChannel aChannel) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.setTransChannel(aChannel);
			try {
				((ExchangeClient) aChannel).setClientListen(this);
			} catch (Exception e) {
				LogUtil.error("弄来的不是客户端用的通道！！" + e.getMessage());
			}
		}
	}

	@Override
	public void Invoke(String ObjUrl, HashMap<String, Object> Param,
			ReturnDo AsyncResult, Object Context) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.Invoke(ObjUrl, Param, AsyncResult, Context);
		}

	}

	@Override
	public void OnRecvMessage(IMessage Message) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnRecvMessage(Message);
		}

	}

	@Override
	public void setNewConnectOk(IConnectOK co) {
		if (uuid.equals(runTime.getUUID())) {
			runTime.setNewConnectOk(co);
		}
	}

	@Override
	public void OnAgainConnect() {
		if (uuid.equals(runTime.getUUID())) {
			runTime.OnAgainConnect();
		}

	}

}
