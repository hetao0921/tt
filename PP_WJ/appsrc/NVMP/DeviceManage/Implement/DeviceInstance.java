package NVMP.DeviceManage.Implement;

import java.util.ArrayList;
import java.util.List;


/**
 * ÿ���豸������Ϣ�㼯
 * 
 * */
public class DeviceInstance {
    
	//�豸��Ϣ�� 
	/**
	 * IP
	 * �˿�
	 * �û���
	 * ����
	 * */
	 private int deviceHandle = -1;
	 private String hostIP;
	 private int port;
	 private String username;
	 private String passwd;
	 private String deviceid;
	 private int alrmPointNum;
	 private int canmelNum;
	 private int outPointNum;
	 private boolean autoflag = false;
	 private int deviceType;
	 
	 
	 
	 
	
	public int getDeviceType() {
		return deviceType;
	}


	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}


	public boolean isAutoflag() {
		return autoflag;
	}


	public void setAutoflag(boolean autoflag) {
		this.autoflag = autoflag;
	}


	public int getAlrmPointNum() {
		return alrmPointNum;
	}


	public void setAlrmPointNum(int alrmPointNum) {
		this.alrmPointNum = alrmPointNum;
	}


	public int getCanmelNum() {
		return canmelNum;
	}


	public void setCanmelNum(int canmelNum) {
		this.canmelNum = canmelNum;
	}


	public int getOutPointNum() {
		return outPointNum;
	}


	public void setOutPointNum(int outPointNum) {
		this.outPointNum = outPointNum;
	}

	//�豸����״̬
	 private boolean onlineFlag = false;
	 
	//�˿ڳ�
	 private List<BaseChannel> ChannelList;
	 
	 public DeviceInstance() {
		 ChannelList = new ArrayList<BaseChannel>();
	 }
	 

	public int getDeviceHandle() {
		return deviceHandle;
	}

	public void setDeviceHandle(int deviceHandle) {
		this.deviceHandle = deviceHandle;
	}

	public String getHostIP() {
		if (hostIP == null)
			return "";
		
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		if (username == null)
			return "";
		
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		if (passwd == null)
			return "";
		
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getDeviceid() {
		if (deviceid == null)
			return "";
		
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public boolean isOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(boolean onlineFlag) {
		this.onlineFlag = onlineFlag;
	}


	public List<BaseChannel> getChannelList() {
		return ChannelList;
	}
	 
	public boolean addChannel(BaseChannel bc){	
		return this.ChannelList.add(bc);
	}
	
	/**
	 *@param deviceid �豸id
	 *@param channelid ͨ��
	 *@param type ���� 
	 * 
	 * */
	public BaseChannel getChannel(String deviceid,int channelid,final String type) {
		BaseChannel bc = new BaseChannel(){ public String getType(){return type;}};
		bc.setDeviceid(deviceid);
		bc.setChannelId(channelid);
      	int index = this.ChannelList.indexOf(bc);
      	if(index>-1) {
      		return this.ChannelList.get(index);
      	}
		return null;
	}
	
	
}
