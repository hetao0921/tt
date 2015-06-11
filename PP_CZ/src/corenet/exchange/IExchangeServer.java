package corenet.exchange;


import corenet.netbase.Interface.IChannel;

import corenet.rpc.IMessage;

public interface IExchangeServer {
	boolean CreateGroup(IChannel Channel, String GroupName);
 
	boolean JoinGroup(IChannel Channel, String GroupName);

	boolean LeaveGroup(IChannel Channel, String GroupName);

	boolean CreateSession(IChannel Channel);

	boolean DestroySession(IChannel Channel);

	void ProcessMessage(IChannel Channel, IMessage Message);

	void ServerClinet(IChannel Channel, String sessionid);
	void newServerClinet(IChannel Channel, String sessionid);

	String getServerID();

	void setServerID(String serverID,IConnectRule rule);

	void OnGlobalOnline(String target, String source, IMessage baseMessage,String uid);

	void OnGlobalGroupLeave(String target, String source, IMessage baseMessage,String uid,IChannel channel);

	void GobalSendMessage(IMessage message, String target, String source,String uid,String nosend);

	void OnGobalRecive(String target, String source, IMessage message,String uid,IChannel channel);

	void OnCenterMessage(String target, String source, IMessage Body,
			String id, IMessage Other,IChannel channel);

	void OnAgainConnect(IChannel Channel);

	void onActiveFlag(IChannel Channel);

	void ProcessData(IChannel exchangeChannel, byte[] body);

}
