package corenet.exchange.Interface;

import corenet.netbase.Interface.IChannel;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;

public interface Clientlistener { 
	 public  void OnRecvMessage(IMessage Message);
	 public  void OnConnectExchange(IChannel Channel);
	public void OnNewConnection(IChannel Channel);
	//ÎªÁËÖØÐÂÁ¬œÓ¡£
	public void OnAgainConnect();
	public void OnGlobalRecvMessage(String target, String source,
			IMessage message,String uid);
	public void OnGlobalOnline(String target, String source, IMessage message,String uid);
	public void OnGlobalGroupLeave(String target, String source,
			IMessage message,String uid);
	public void OnCenterMessage(String target, String source,
			IMessage Body, String id, IMessage Other);
	public void OnServerClinet(IMessage message);
	
	
	
}
