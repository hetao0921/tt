package corenet.exchange.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.IChannel;

/**
 * 
 * 
 * @author hxht;
 * */
public class GroupPool {

	// 采用线程安全的Map保存所有信息
	Map<String, Channel> channelMap;

	public GroupPool() {
		channelMap = new ConcurrentHashMap<String, Channel>();
	}

	/**
	 * 
	 * */
	public boolean jionGroup(String sessionid, IChannel channel,
			String... groupnames) {

		Channel aChannel = channelMap.get(channel.GetSessionId());
		if (aChannel == null || aChannel.getChannel() != channel) {
			aChannel = new Channel(channel);
			channelMap.put(channel.GetSessionId(), aChannel);

		}
		for (String item : groupnames) {
			aChannel.joinGroup(item);
		}

		return true;
	}

	public boolean jionGroup(IChannel Channel, String GroupName) {
		boolean b = true;
		jionGroup(Channel.GetSessionId(), Channel, GroupName);
		return b;
	}

	/**
	 * 
	 * */
	public boolean LeaveSystem(IChannel Channel) {
		Channel aChannel = channelMap.get(Channel.GetSessionId());

		boolean b = false;

		if (aChannel != null && aChannel.getChannel() == Channel) {
			channelMap.remove(Channel.GetSessionId());
			b = true;
		}

		return b;
	}

	/**
	 * 
	 * */
	public boolean LeaveGroup(IChannel Channel, String GroupName) {
		boolean b = false;

		try {
			Channel aChannel = channelMap.get(Channel.GetSessionId());

			if (aChannel != null && aChannel.getChannel() == Channel) {
				aChannel.leaveGroup(GroupName);
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("LeaveGroup错误" + e.getMessage());
			b = false;

		}
		return b;
	}

	public IChannel[] getAllChannel() {
		if (channelMap.size() == 0)
			return new IChannel[0];
		IChannel[] arrays = new IChannel[channelMap.size()];
		int i = 0;
		for (Entry<String, Channel> item : channelMap.entrySet()) {
			arrays[i] = item.getValue().getChannel();
			++i;
		}
		return arrays;
	}

	public IChannel[] getChannelsByGroup(String groupName) {
		if (channelMap.size() == 0)
			return new IChannel[0];
		ArrayList<IChannel> list = new ArrayList<IChannel>();
		for (Entry<String, Channel> item : channelMap.entrySet()) {
			if (item.getValue().isInGroups(groupName)) {
				list.add(item.getValue().getChannel());
			}
		}
		return list.toArray(new IChannel[list.size()]);
	}

	public IChannel getChannelByName(String name) {
		Channel aChannel = channelMap.get(name);
		return aChannel != null ? aChannel.getChannel() : null;
	}

	public boolean isActiveSession(String sessionid) {
		// TODO Auto-generated method stub
		return channelMap.containsKey(sessionid);
	}

	public List<HashMap<String, String>> getAllGroupInfo() {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
		Channel channel = null;
		for (Entry<String, Channel> item : channelMap.entrySet()) {
			channel = item.getValue();
			for (String groupName : channel.getGroups()) {
				HashMap<String, String> hp = new HashMap<String, String>();
				hp.put("SessionId", channel.getChannel().GetSessionId());
				hp.put("GroupName", groupName);
				list.add(hp);
			}
		}
		return list;
	}

	public Set<String> getGroupName(IChannel channel) {
		Channel aChannel = this.channelMap.get(channel.GetSessionId());
		Set<String> groupSet = new HashSet<String>();
		if (aChannel != null && aChannel.getChannel() == channel) {
			for(String item: aChannel.getGroups()){
				groupSet.add(item);
			}
			
		} 
		return groupSet;
	}

}
