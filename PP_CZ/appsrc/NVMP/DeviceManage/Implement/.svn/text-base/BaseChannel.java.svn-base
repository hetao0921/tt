package NVMP.DeviceManage.Implement;

/**
 * ����ͨ���Ļ���
 * */
public abstract class BaseChannel {
	private String deviceid;
	private String channelName;
	private int channelId;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	// ����ʵ�֣�˵���Լ�������
	abstract public String getType();

	@Override
	public boolean equals(Object obj) {
		boolean b = true;
		try {
			BaseChannel in = (BaseChannel) obj;
			if (in.getChannelId() != this.getChannelId()) {
				b = false;
				return b;
			}
			if (!in.getDeviceid().equals(this.getDeviceid())) {
				b = false;
				return b;
			}
			if (!in.getType().equals(this.getType())) {
				b = false;
				return b;
			}
		} catch (Exception e) {

			b = false;
		}
		// TODO Auto-generated method stub
		return b;
	}

}
