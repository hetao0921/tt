package corenet.exchange;

import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseSession;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.IMessage;

public class ExServerClinetEx implements Clientlistener, BaseSessionListen {

	
	private String uuid;
	private ExServerClinet exServerClinet;

	public ExServerClinetEx(ExServerClinet obj,String uuid) {
		exServerClinet = obj;
		this.uuid = uuid;
	}

	@Override
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] Body) {
		exServerClinet.OnReadMessage(Session, Header, Body);
	}

	@Override
	public void onActiveFlag() {
		exServerClinet.onActiveFlag();
	}

	@Override
	public void OnRecvMessage(IMessage Message) {
		exServerClinet.OnRecvMessage(Message);
	}

	@Override
	public void OnConnectExchange(IChannel Channel) {
		
		exServerClinet.OnConnectExchange(uuid,Channel);
	}

	@Override
	public void OnNewConnection(IChannel Channel) {
		exServerClinet.OnNewConnection(Channel);
	}

	@Override
	public void OnAgainConnect() {
		exServerClinet.OnAgainConnect(uuid);
	}

	@Override
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message, String uid) {
		exServerClinet.OnGlobalRecvMessage(target, source, message, uid,uuid);

	}

	@Override
	public void OnGlobalOnline(String target, String source, IMessage message,
			String uid) {
		exServerClinet.OnGlobalOnline(target, source, message, uid,uuid);
	}

	@Override
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message, String uid) {
		exServerClinet.OnGlobalGroupLeave(target, source, message, uid,uuid);

	}

	@Override
	public void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other) {
		exServerClinet.OnCenterMessage(target, source, Body, id, Other,uuid);

	}

	@Override
	public void OnServerClinet(IMessage message) {
		exServerClinet.OnServerClinet(message,uuid);

	}

}