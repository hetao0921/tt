package corenet.exchange.Group;
 
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import corenet.exchange.Encoding;
import corenet.exchange.Interface.ExchangeServerMessage;
import corenet.netbase.Interface.IChannel;


public class GroupPoolTool {


	
	private GroupPool groupPool;

	private GroupPoolTool() {
		groupPool = new GroupPool();
	}

	static public GroupPoolTool Instance() {
		return GroupPoolToolHolder.INSTANCE;
	}

	static class GroupPoolToolHolder {
		static final GroupPoolTool INSTANCE = new GroupPoolTool(); 
	}
	
	/**
	 * 
	 * */
	public IChannel[] foundChannel(String name) {

		IChannel[] channelArray = null;
		if (name.equals("ALL")) {
			channelArray = groupPool.getAllChannel();

		} else if (name.substring(0, 8).equals("Group://")) {
			String str = Encoding.GroupProtocoToAds(name);
			
			channelArray = groupPool.getChannelsByGroup(str);
		} else if (name.substring(0, 10).equals("Session://")) {
			String str = Encoding.PointProtocoToAds(name);
			channelArray = new IChannel[1];
			channelArray[0] = groupPool.getChannelByName(str);
			if (channelArray[0] == null) return null;

		}

		return channelArray;
	}

	synchronized public boolean CreateSession(IChannel Channel) {
		return this.groupPool.jionGroup(Channel.GetSessionId(), Channel);

	}

	synchronized public boolean DestroySession(IChannel Channel) {

		return this.groupPool.LeaveSystem(Channel);
	}
	
	synchronized public boolean DestroyServerSession(IChannel Channel) {

		return this.groupPool.LeaveSystem(Channel);
	}
	
	

	synchronized public boolean JoinGroup(IChannel Channel, String GroupName) {
		
		System.out.println("==========="+GroupName);
		return this.groupPool.jionGroup(Channel, GroupName);

	}

	synchronized public boolean LeaveGroup(IChannel Channel, String GroupName) {
//		esm.get_ExchangeServer().GobalGroupLeave(false, null, Channel.GetSessionId(), GroupName,null);
//		this.esm.OnDomainMessage(Channel.GetSessionId(), GroupName, "0", "G");
		return this.groupPool.LeaveGroup(Channel, GroupName);
	}

    public boolean CreateGroup(String GroupName) {
    	return true;
	}

	public void setEsm(ExchangeServerMessage esm) {
	}
 
	public List<HashMap<String,String>> getAllGroupInfo() {

		return this.groupPool.getAllGroupInfo();
	}
	
	
	public boolean isActiveSession(String sessionid) {
		return this.groupPool.isActiveSession(sessionid);
	}

	public IChannel getChannel(String sessionId) {
		return this.groupPool.getChannelByName(sessionId);
	}

	public Set<String> getSessionGroupName(IChannel channel) {
		// TODO Auto-generated method stub
		return this.groupPool.getGroupName(channel);
	}

	public List<String> ShowGroupList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
