package corenet.exchange.Group;

import java.util.HashSet;
import java.util.Set;

import corenet.netbase.Interface.IChannel;

/**
 * 通道和组的相关性
 * */
public class Channel {

	private IChannel channel; // 管道
	private Set<String> groups; // 组群

	public Channel(IChannel channel) {
		this.channel = channel;
		groups = new HashSet<String>();
	}

	// 获得通道的实际管道
	public IChannel getChannel() {
		return channel;
	}

	// 获得组群
	public Set<String> getGroups() {
		return groups;
	}

	public void joinGroup(String groupName) {
		groups.add(groupName);
	}

	public void leaveGroup(String groupName) {
		groups.remove(groupName);
	}

	/**
	 * 判断通道是否在该组中存在。
	 * 
	 * @return true 在 false 不在
	 * */
	public boolean isInGroups(String groupName) {
		return groups.contains(groupName);
	}

	static public void main(String[] args) {
	}

}
