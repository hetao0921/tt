package NVMP.VideoControlDomain;

/**
 * 转发服务器列表。 可扩展，楼下我只是保留需要的部分。
 * */
public class VideoServerInfo {
	private String DeviceId; // 希望点播的输入视频源Ids
	// public Integer DeviceType; //设备类型
	// public Integer DeviceSubType; //设备子类型
	// public Integer Index; //希望点播的输入视频源的摄像机序号
	private String SourceIP; // 实际点播的视频源IP
	private Integer NetPort; // 网络连接端口
	private String UserName;
	private String PassWord;
	private Integer ChannelNums; //转发服务器的通道总数
	private int count;
	
 
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public void addCount() {
		count++;
	}
	public void minusCount() {
		count--;
	}
	

	public Integer getChannelNums() {
		return ChannelNums;
	}

	public void setChannelNums(Integer channelNums) {
		ChannelNums = channelNums;
	}

	// public Integer LinkType ; //网络传输类型
	// public int ServerVideoCH; //输出的视频服务通道编号
	// public Boolean IsOnline; //是否在线
	public VideoServerInfo(String DeviceId) {
		this.DeviceId = DeviceId.trim();
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId.trim();
	}

	public String getSourceIP() {
		return SourceIP;
	}

	public void setSourceIP(String sourceIP) {
		SourceIP = sourceIP;
	}

	public Integer getNetPort() {
		return NetPort;
	}

	public void setNetPort(Integer netPort) {
		NetPort = netPort;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean b = false;
		try {
			VideoServerInfo vs = (VideoServerInfo) obj;
			if (this.getDeviceId().trim().equals(vs.getDeviceId().trim()))
				b = true;

		} catch (Exception e) {
			b = false;
		}

		return b;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getDeviceId().hashCode();
	}
	

}
