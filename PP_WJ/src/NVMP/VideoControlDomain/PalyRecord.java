package NVMP.VideoControlDomain;

/**
 * 需要记录的信息
 * 
 * 1、uuid 2、原始请求信息 devid channel 3、路由信息 CenterMap 4、点播发起，用的来源信息 formFlag formIP
 * formChannel formFowardid 还有账号密码等等信息
 * 
 * */
public class PalyRecord implements Cloneable {
	public String uuid;// 每一次原始点播，产生请求的uuid
	public String devid;
	public Integer channel;
	public String clientId;

	public Route CenterMap; // 中心路由表

	public Route RealCenterMap; // 第一次的路由信息

	public Integer formFlag; // 来源标示 0 设备 1外网 2已经存在，再次提取而已
	public String formIP; // 来源的IP地址
	public Integer formChannel; // 来源的通道
	public String formFowardid; // 来源如果是转发，加上转发的id

	public Integer Port; // 数据来源的端口
	public String user;
	public String Password;
	public Integer DeviceType; // 设备厂商
	public Integer DeviceSubType;// 设备型号
	public Integer NetLinkType;// 网络连接类型
	public Integer NetLinkSubType;// 网络连接子类型

	public Integer NetLinkMode;// 网络连接模式

	public Integer lev; // 该请求点播的级别

	public boolean delfalg;

	public String clientIP;

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	// 点播发送的转发服务器ID
	private String forwardid;

	/**
	 * 点播发送的标示 0 未用转发服务器， 1，使用了转发服务器。
	 * */
	private Integer forwardflag = 0;

	public String getForwardid() {
		return forwardid;
	}

	/**
	 * 设置目前调用的转发服务器ID
	 * 
	 * */
	public void setForwardid(String forwardid) {
		this.forwardflag = 1;
		if (forwardid != null) {
			this.forwardid = forwardid.trim();
		}
	}

	public Integer getForwardflag() {
		return forwardflag;
	}

	public void setForwardflag(Integer forwardflag) {
		this.forwardflag = forwardflag;
	}

	public Integer getLev() {
		return lev;
	}

	public void setLev(Integer lev) {
		this.lev = lev;
	}

	public Route getRealCenterMap() {
		return RealCenterMap;
	}

	public void setRealCenterMap(Route realCenterMap) {
		RealCenterMap = realCenterMap;
	}

	private Integer state;

	public PalyRecord(String DeviceId, String VideoFromIP, Integer Port,
			String user, String Password, Integer flag, Integer DeviceType,
			Integer DeviceSubType, Integer NetLinkType, Integer NetLinkSubType,
			Integer NetLinkMode) {
		this.devid = DeviceId;
		this.formIP = VideoFromIP;
		this.Port = Port;
		this.user = user;
		this.Password = Password;
		this.state = flag;
		this.DeviceType = DeviceType;
		this.DeviceSubType = DeviceSubType;
		this.NetLinkType = NetLinkType;
		this.NetLinkSubType = NetLinkSubType;
		this.NetLinkMode = NetLinkMode;
	}

	public PalyRecord(String clientId, String uuid, String devid,
			Integer channel, Route CenterMap, Integer formFlag, String formIP,
			Integer formChannel, String formFowardid, PalyRecord find) {
		this.clientId = clientId;
		this.uuid = uuid;
		this.devid = devid;
		this.channel = channel;
		this.CenterMap = CenterMap;
		this.formFlag = formFlag;
		this.formIP = formIP;
		this.formChannel = formChannel;
		this.formFowardid = formFowardid;

		this.Port = find.getPort();
		this.user = find.getUser();
		this.Password = find.getPassword();

		this.DeviceType = find.getDeviceType();
		this.DeviceSubType = find.getDeviceSubType();
		this.NetLinkType = find.getNetLinkType();
		this.NetLinkSubType = find.getNetLinkSubType();
		this.NetLinkMode = find.getNetLinkMode();

	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getPort() {
		return Port;
	}

	public void setPort(Integer port) {
		Port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(Integer deviceType) {
		DeviceType = deviceType;
	}

	public Integer getDeviceSubType() {
		return DeviceSubType;
	}

	public void setDeviceSubType(Integer deviceSubType) {
		DeviceSubType = deviceSubType;
	}

	public Integer getNetLinkType() {
		return NetLinkType;
	}

	public void setNetLinkType(Integer netLinkType) {
		NetLinkType = netLinkType;
	}

	public Integer getNetLinkSubType() {
		return NetLinkSubType;
	}

	public void setNetLinkSubType(Integer netLinkSubType) {
		NetLinkSubType = netLinkSubType;
	}

	public Integer getNetLinkMode() {
		return NetLinkMode;
	}

	public void setNetLinkMode(Integer netLinkMode) {
		NetLinkMode = netLinkMode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUuid() {
		return uuid;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Route getCenterMap() {
		return CenterMap;
	}

	public void setCenterMap(Route centerMap) {
		CenterMap = centerMap;
	}

	public Integer getFormFlag() {
		return formFlag;
	}

	/**
	 * formFlag; // 来源标示 0 设备 1外网 2已经存在，再次提取而已
	 * */
	public void setFormFlag(Integer formFlag) {
		this.formFlag = formFlag;
	}

	public String getFormIP() {
		return formIP;
	}

	public void setFormIP(String formIP) {
		this.formIP = formIP;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getFormChannel() {
		return formChannel;
	}

	public void setFormChannel(Integer formChannel) {
		this.formChannel = formChannel;
	}

	public String getFormFowardid() {
		return formFowardid;
	}

	public void setFormFowardid(String formFowardid) {
		this.formFowardid = formFowardid;
	}

	// 写个克隆方法，用来返回该对象的副本
	public PalyRecord getClone() {
		try {
			return (PalyRecord) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	static public void main(String... args) {

		PalyRecord pr1 = new PalyRecord("1", "2", 3, "4", "5", 6, 7, 8, 9, 10,
				11);

		PalyRecord pr2 = pr1.getClone();
		pr1.setDevid("111");

		System.out.println(pr2.getDevid());

	}

	public void setDelete(boolean b) {
		// TODO Auto-generated method stub
		delfalg = b;
	}

	public boolean isdelete() {
		return delfalg;

	}

	@Override
	public PalyRecord clone() throws CloneNotSupportedException {
		PalyRecord cp = new PalyRecord(devid, formIP, Port, user, Password,
				state, DeviceType, DeviceSubType, NetLinkType, NetLinkSubType,
				NetLinkMode);
		cp.setUuid(uuid);
		cp.setCenterMap(CenterMap);
		cp.setChannel(channel);
		cp.setClientId(clientId);
		cp.setClientIP(clientIP);
		cp.setDelete(delfalg);
		cp.setDeviceSubType(DeviceSubType);
		cp.setDeviceType(DeviceType);
		cp.setFormChannel(formChannel);
		cp.setFormFlag(formFlag);
		cp.setFormFowardid(formFowardid);
		;
		cp.setForwardflag(forwardflag);
		cp.setForwardid(forwardid);
		cp.setLev(lev);
		cp.setRealCenterMap(RealCenterMap);

		return cp;
	}

	// @Override
	// protected Object clone() throws CloneNotSupportedException {
	// // TODO Auto-generated method stub
	// PalyRecord cp = new PalyRecord(devid, formIP, Port, user, Password,
	// state, DeviceType, DeviceSubType, NetLinkType, NetLinkSubType,
	// NetLinkMode);
	// cp.setUuid(uuid);
	// cp.setCenterMap(CenterMap);
	// cp.setChannel(channel);
	// cp.setClientId(clientId);
	// cp.setClientIP(clientIP);
	// cp.setDelete(delfalg);
	// cp.setDeviceSubType(DeviceSubType);
	// cp.setDeviceType(DeviceType);
	// cp.setFormChannel(formChannel);
	// cp.setFormFlag(formFlag);
	// cp.setFormFowardid(formFowardid);;
	// cp.setForwardflag(forwardflag);
	// cp.setForwardid(forwardid);
	// cp.setLev(lev);
	// cp.setRealCenterMap(RealCenterMap);
	//
	// return cp;
	// }

}
