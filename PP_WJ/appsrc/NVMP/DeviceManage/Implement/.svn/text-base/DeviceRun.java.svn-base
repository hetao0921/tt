package NVMP.DeviceManage.Implement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import com.fxdigital.EcDevice.ctrl.DeviceCtrl;
import com.fxdigital.EcDevice.ctrl.IDevNotify;
import com.sqlite.factory.DAOFactory;
import com.sqlite.impl.VideoDeviceImpl;

import com.sqlite.pojo.VideoDevCH;
import com.sqlite.pojo.VideoDevice;

import NVMP.AppService.util.GetDeviceId;
import NVMP.DeviceManage.Implement.devicectrl.DeviceCtrlManager;
import NVMP.Proxy.StateManageDomain;
import NVMP.Proxy.SystemMessageDomain;

//import NVMP.test.LoginCenter;
//import NVMP.test.Client.Implement.ClientView;
import Runtime.IConnectOK;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import corenet.exchange.ExchangeClient;

/**
 * 用来模拟设备管理器
 * 
 * */
public class DeviceRun implements IConnectOK, IDeviceRun, IDevNotify, Runnable {
	private PingDevice pd;
	private DeviceCtrlManager dc = null;
	private ExchangeClient ec;
	private Map<String, DeviceInstance> deviceHp = null;

	private String deviceId; // 设备ID
	private int channId; // 通道ID
	private int devCHType; // 通道类型，1移动侦测 2视频丢失 3报警点 4输出点
	private IRunTime run;
	private List<DeviceInstance> NotAutoConnectionList;

	// 这里直接记录登陆成功的设备和其登陆成功ID
	private java.util.concurrent.ConcurrentHashMap<String, Integer> loginDeviceHp;

	public void deviceLogin(String deviceid, Integer loginid) {
		loginDeviceHp.put(deviceid, loginid);
	}

	public void deviceLogout(String deviceid) {
		loginDeviceHp.remove(deviceid);
	}

	public void setTime(Integer year, Integer month, Integer day, Integer hour,
			Integer minute, Integer second) {

		for (Entry<String, Integer> hSession : loginDeviceHp.entrySet()) {
			System.out.println("======================");
			System.out.println(hSession.getKey() + "   " + hSession.getValue());
			DeviceInstance di = deviceHp.get(hSession.getKey());
			if (di != null)
				dc.setTime(di.getDeviceType(), di.getDeviceHandle(), year,
						month, day, hour, minute, second);
		}

	}

	// 在此处设置一个侦听服务器上的池，从此，以这个为主，进行对比。
	private Map<String, Boolean> serverStateMap;

	private String sessionid, addressIP, url;
	private int port;

	// public void againConnect() {
	// run.OnAgainConnect();
	// }

	public void setUrl(String url) {
		this.url = url;
	}

	public List<DeviceInstance> getNotAutoConnectionList() {
		return NotAutoConnectionList;
	}

	private StateManageDomain smd = null;

	public Map<String, DeviceInstance> getDeviceHp() {
		return deviceHp;
	}

	public DeviceCtrlManager getDc() {
		return dc;
	}

	public StateManageDomain getSmd() {
		return smd;
	}

	public void setConnectOK(IConnectOK ok) {

		System.out.println("返回了对话");
	}

	public DeviceRun(String sessionid, String addressIP, int port) {
		this.sessionid = sessionid;
		this.addressIP = addressIP;
		this.port = port;

		NotAutoConnectionList = new ArrayList<DeviceInstance>();
		serverStateMap = new java.util.concurrent.ConcurrentHashMap<String, Boolean>();
		this.loginDeviceHp = new ConcurrentHashMap<String, Integer>();
	}

	/**
	 * 初始化，包括 1、初始化登录工具，主要是放入回调处理类。 2、 获得所有管理设备 3、获得所有设备辖下端口
	 * 
	 * @throws Exception
	 * 
	 *             把数据库初始化和连接分开为2步
	 * 
	 * 
	 * 
	 * */

	public void ConnectToServer() {
		if (run == null) {

			ec = new ExchangeClient();
			run = new RunTime();
			run.setTransChannel(ec);
			run.setNewConnectOk(this);

			// 设备管理业务
			smd = new StateManageDomain(run);
			StateManageEventHandler sneh = new StateManageEventHandler();
			sneh.setDr(this);
			smd.setEventhand(sneh);

			SystemMessageDomain sysMg = new SystemMessageDomain(run);
			SystemMessageEventHandler smeh = new SystemMessageEventHandler();
			smeh.setDr(this);
			sysMg.setEventhand(smeh);
		}

		boolean flag = true;
		while (flag) {
			try {
				connectflag = false;
				ec = new ExchangeClient();
				ec.SetSessionId(sessionid);
				run.setTransChannel(ec);
				ec.ConnectExchange(addressIP, port);
				flag = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				flag = true;
				ec.get_Base().cannel();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
				}

			}
		}
		int n = 0;
		while (!connectflag) {
			try {
				Thread.sleep(1000);
				n++;
				if (n > 15) {
					// this.onAgainConnect();
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connectflag) {
			System.out.println(333);
			// 开始初始化
			init();
		} else {

			System.out.println(222);
			connectflag = true;
			ec.get_Base().cannel();
			this.onAgainConnect();

		}

	}

	private boolean initflag = false;

	public void putOnline(String deviceip, Boolean b) {
		if (deviceip != null && serverStateMap.containsKey(deviceip))
			serverStateMap.put(deviceip, b);

	}
	public void putNewDevice(DeviceInstance newDI){
		deviceHp.put(newDI.getDeviceid(), newDI);
		serverStateMap.put(newDI.getHostIP(),false);
	}
	

	public boolean isOnline(String deviceip) {
		if (deviceip == null)
			return false;

		if (this.serverStateMap.containsKey(deviceip)) {

			return serverStateMap.get(deviceip);
		} else {

			return false;
		}

	}

	@SuppressWarnings("static-access")
	public void init() {
		// 不管是什么，先清空一次数据。
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("先声明我是设备管理服务器");
		smd.LoginState(sessionid);
		if (deviceHp == null)
			this.deviceHp = new java.util.concurrent.ConcurrentHashMap<String, DeviceInstance>();

		// smd.GetEncodeDeviceOnline(this.sessionid);

		if (pd != null) {
			pd.clearResult();
			serverStateMap = new java.util.concurrent.ConcurrentHashMap<String, Boolean>();
		}
		if (!initflag) {
			initflag = true;
		} else {
			return;
		}
		// 系统消息业务，比如说同sessionid登录
		// SystemMessageDomain sysMg = new SystemMessageDomain(run);
		// SystemMessageEventHandler smeh = new SystemMessageEventHandler();
		// smeh.setDr(this);
		// sysMg.setEventhand(smeh);
		//
		// ec.SetSessionId(sessionid);
		//
		// ec.ConnectExchange(addressIP, port);
		// connectflag = false;
		// int n = 0;
		// while (!connectflag) {
		// Thread.sleep(1000);
		// if (++n > 15) {
		// throw new Exception();
		// }
		// }
		// sysMg.GetCenterID(sessionid);

		this.dc = DeviceCtrlManager.getINSTANCE();

		// 先从webservice读取数据
		LogUtil.DeviceManageInfo("now initdata 开始初始化本地数据");
		// DAOFactory.getPojoImpl().init("http://192.168.1.101:8080/webservice/services/web");
		while (true) {
			try {
				DAOFactory.getPojoImpl().init(url, "/DevDB");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		LogUtil.DeviceManageInfo("initdata over 初始化本地数据完毕");

		String sessionid = ec.GetSessionId();

		dc.setIdn(this);

		// 初始化得到
		List<Map<String, String>> l = DAOFactory.getPojoImpl()
				.getDeviceBySwitchSvrID(sessionid);

		// 开始记录下面的端口

		// public static int AlarmPoint = 1;
		// public static int OutPoint = 2;
		// public static int Motion = 3;
		// public static int VideoLost = 4;

		for (Map<String, String> m : l) {

			DeviceInstance newDi = new DeviceInstance();
			newDi.setDeviceid(m.get("DeviceID"));
			newDi.setHostIP(m.get("DevIP"));
			newDi.setPort(Integer.parseInt(m.get("DevPort")));
			newDi.setUsername(m.get("UserName"));
			newDi.setPasswd(m.get("Password"));
			newDi.setOnlineFlag(false);
			newDi.setDeviceType(Integer.parseInt(m.get("DevType")));

			this.deviceHp.put(newDi.getDeviceid(), newDi);
			serverStateMap.put(newDi.getHostIP(), false);

		}

		// 先ping，凡是上线的，开始进行处理，连接得到句柄，开始 判断是否自动回复
		// new Thread(this).start();
		NotAutoConnectionList.addAll(this.deviceHp.values());

//		System.out
//				.println("start " + NotAutoConnectionList.size() + "  gogogo");
//		pd = new PingDevice(this, 2000, 50000);
//		this.dc.startup();
//		pd.start();
		
		int waitTime = 2000;
		int outTime = 50000;
		pd = new PingDevice(this, waitTime, outTime);
		
		DeviceRunAssist deviceRunAssist = new DeviceRunAssist(NotAutoConnectionList, outTime,this);
		pd.setDeviceAssist(deviceRunAssist);
		
		this.dc.startup();
		pd.start();
	}

	@Override
	public void addCenterSource(String centerid) {
		// 初始化得到
		List<Map<String, String>> l = DAOFactory.getPojoImpl()
				.getDeviceByCenterID(centerid);

		for (Map<String, String> m : l) {
			DeviceInstance newDi = new DeviceInstance();
			newDi.setDeviceid(m.get("DeviceID"));
			newDi.setHostIP(m.get("DevIP"));
			newDi.setPort(Integer.parseInt(m.get("DevPort")));
			newDi.setUsername(m.get("UserName"));
			newDi.setPasswd(m.get("Password"));
			newDi.setOnlineFlag(false);
			newDi.setDeviceType(Integer.parseInt(m.get("DevType")));

			LogUtil.DeviceManageInfo("newDI " + newDi.getDeviceType()
					+ "   |   " + newDi.getHostIP());
			deviceHp.put(newDi.getDeviceid(), newDi);
			serverStateMap.put(m.get("DevIP"), false);
			pd.addHost(newDi.getHostIP(), newDi);

		}

	}

	@Override
	public void removeCenterSource(String centerid) {

		List<Map<String, String>> l = DAOFactory.getPojoImpl()
				.getDeviceByCenterID(centerid);

		for (Map<String, String> m : l) {

			LogUtil.DeviceManageInfo("Delete  newDI " + m.get("DevIP"));
			deviceHp.remove(m.get("DeviceID"));
			serverStateMap.remove(m.get("DevIP"));
			pd.delHost(m.get("DevIP"), m.get("DeviceID"));

		}

	}

	@Override
	public void handleAlarm(int LoginHandle, int AlarmType, int Value,
			Object Context) {
		// TODO Auto-generated method stub
		try {
			DeviceInstance di = (DeviceInstance) Context;
			this.smd.SetAlarmState(di.getDeviceid(), Value, AlarmType, 2);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void handleConnect(int LoginHandle, boolean Connected, Object Context) {
		// TODO Auto-generated method stub
		try {
			DeviceInstance di = (DeviceInstance) Context;
			this.smd.SetEncodeDeviceOnline(di.getDeviceid(), di.getHostIP(),
					Connected, -1, -1, null);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 获取设备服务器下所有的设备信息
	 * 
	 * @return
	 */
	public MutableTreeNode getTreeModel() {
		MutableTreeNode root = new DefaultMutableTreeNode("所有设备信息");
		List<VideoDevice> list = new VideoDeviceImpl().getAllVideoDev();
		for (int i = 0; i < list.size(); i++) {
			// 添加设备节点信息
			MutableTreeNode device = new DefaultMutableTreeNode(list.get(i)
					.getDeviceStatus().getDevName());
			root.insert(device, i);
			// 添加设备类型信息
			MutableTreeNode canmel = new DefaultMutableTreeNode("摄像机");
			MutableTreeNode alrmpoint = new DefaultMutableTreeNode("报警点");
			MutableTreeNode outpoint = new DefaultMutableTreeNode("输出点");
			device.insert(canmel, 0);
			device.insert(alrmpoint, 1);
			device.insert(outpoint, 2);
			for (int j = 0; j < list.get(i).getChannels().size(); j++) {
				VideoDevCH cc = list.get(i).getChannels().get(j);
				MutableTreeNode channle = new DefaultMutableTreeNode(
						cc.getChName() + "_" + cc.getId());
				int a = 0, b = 0, c = 0;
				if (cc.getChType() == 3) {
					canmel.insert(channle, a);
					a++;
				} else if (cc.getChType() == 4) {
					alrmpoint.insert(channle, b);
					b++;
				} else if (cc.getChType() == 5) {
					outpoint.insert(channle, c);
					c++;
				}
			}
		}
		return root;
	}

	/**
	 * 实现树状视图节点点击时的操作
	 */
	public void treeSelected(String type, int id) {
		// //获取父节点的名称，判断是摄像头、报警点还是输出点
		// TreePath tp = e.getNewLeadSelectionPath();
		// String[] parents = tp.getParentPath().toString().split(", ");
		// String type = parents[2].substring(0, 3);
		// String path = tp.getLastPathComponent().toString();
		// System.out.println("path:"+path);
		//
		// String id = path.substring(path.indexOf("_") + 1);

		// 根据type属性，查询不同的表格
		// if (type.equals("摄像头")) {
		// Canmel c = DAOFactory.getSheXiangIntance().getCanmelById(id);
		// smd.GetEncodeDeviceAlarm(LoginCenter.sessionId, c.getDeviceId(),
		// c.getChannelID(), 3);
		// smd.GetEncodeDeviceAlarm(LoginCenter.sessionId, c.getDeviceId(),
		// c.getChannelID(), 4);
		// } else if (type.equals("报警点")) {
		// AlrmPoint a = DAOFactory.getBaoJingIntance().getAlrmPoint(id);
		// smd.GetEncodeDeviceAlarm(LoginCenter.sessionId, a.getDeviceId(),
		// a.getChannelID(), 1);
		// } else if (type.equals("输出点")) {
		// OutPoint o = DAOFactory.getShuChuIntance().getOutPoint(id);
		// smd.GetEncodeDeviceAlarm(LoginCenter.sessionId, o.getDeviceId(),
		// o.getChannelID(), 2);
		// }
	}

	/**
	 * 实现布防按钮点击时的操作
	 */
	public void getAlarm() {
		smd.ControlAlarmState(deviceId, channId, devCHType, true);
	}

	/**
	 * 实现撤防按钮点击时的操作
	 * 
	 * @param deviceId
	 * @param channId
	 * @param devCHType
	 */
	public void removeAlarm() {
		smd.ControlAlarmState(deviceId, channId, devCHType, false);
	}

	/**
	 * 向本地硬盘中写入一条数据
	 * 
	 * @param str
	 */
	public void writeFile(String str) {
		PrintStream ps = null;
		OutputStream os = null;
		try {
			File f = new File("D://1.txt");
			os = new FileOutputStream(f, true);
			ps = new PrintStream(os);
			ps.println(str);
			ps.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 当点击节点时，显示对应的信息到文本框中
	 * 
	 * @param terminalId
	 * @param channelId
	 * @param alarmType
	 * @param states
	 * @param jTextField
	 */
	public void showNodeMsg(String terminalId, Integer channelId,
			Integer alarmType, Integer states) {
		// 若alarmType为1，则是移动侦测
		// 若alarmType为2，则是视频丢失
		// 若alarmType为3，则是报警点
		// 若alarmType为4，则是输出点
		String text = DeviceView.getAreaText();
		String sta = "";
		if (states == 0)
			sta = "未布防";
		else if (states == 1)
			sta = "布防";
		else if (states == 2)
			sta = "报警";
		else
			System.out.println("获得设备状态错误！");

		String str = "设备ID:" + terminalId + "  通道ID:" + channelId + "    通道类型:"
				+ alarmType + "    状态:" + sta;

		if (!text.equals("")) { // 如果当前文本框内有内容
			// 以截取字符串的形式获得当前文本框内的内容
			int a = text.indexOf("通道ID");
			int b = text.indexOf("通道类型");
			String devId = text.substring(5, a - 2);
			String chanId = text.substring(a + 5, a + 6);
			String chtype = text.substring(b + 5, b + 6);

			// 判断前后两条信息是否都是同一个通道类型和通道ID，即判断是否是摄像机，如果是，则追加
			if (devId.equals(terminalId) && chanId.equals(channelId.toString())
					&& (Integer.parseInt(chtype) == 3 && alarmType == 4))
				DeviceView.showTextA("\n" + str);
			else
				// 如果不是摄像机，则重置
				DeviceView.showText(str);
		} else { // 如果当前文本框内没有内容，则添加内容
			DeviceView.showText(str);
		}
		deviceId = terminalId;
		channId = channelId;
		devCHType = alarmType;
	}

	/**
	 * 当设备状态更改（布防、撤防、报警）时，向表格中插入一条数据
	 */
	public void insertAlarm(String TerminalId, Integer ChannelId,
			Integer AlarmType, Integer DeviceStatus) {
		String sta = "";
		if (DeviceStatus == 0)
			sta = "撤防";
		else if (DeviceStatus == 1)
			sta = "布防";
		else if (DeviceStatus == 2)
			sta = "报警";
		else
			System.out.println("获得设备状态错误！");

		// 如果获取到的状态改变的信息和当前文本框内显示的是同一条信息，则重置当前文本框的内容
		if (TerminalId.equals(deviceId) && ChannelId == channId
				&& ((AlarmType == devCHType))
				|| (AlarmType == 2 && devCHType == 1)) {
			if (AlarmType == 3 || AlarmType == 4) {
				DeviceView
						.showText("设备ID:" + TerminalId + "  通道ID:" + ChannelId
								+ "    通道类型:" + AlarmType + "    状态:" + sta);
			} else {
				if (AlarmType == 1) {
					String msg = DeviceView.getAreaText();
					int index = msg.lastIndexOf("状态:") + 3;
					String mm = msg.substring(0, index) + sta;
					DeviceView.showText(mm);
				} else if (AlarmType == 2) {
					// 通过拼凑字符串，替换当前的文本
					String msg = DeviceView.getAreaText();
					int index1 = msg.indexOf("状态:") + 3;
					String s1 = msg.substring(0, index1);
					int index2 = msg.lastIndexOf("设备ID");
					String s2 = msg.substring(index2);
					String mm = s1 + sta + "\n" + s2;
					DeviceView.showText(mm);
				}
			}
		}

		// 设置显示的时间格式
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 向表格中添加事由
		// ClientView.insertTableClient(new Object[] {
		// tempDate.format(new Date()), "设备" + TerminalId + sta });
	}

	/**
	 * 当获取上下线事件的时候，就向表格中插入一条数据
	 * 
	 * @param TerminalId
	 * @param TerminalIP
	 * @param IsOnline
	 * @param model
	 */
	public void isOnLine(String TerminalId, String TerminalIP, Boolean IsOnline) {

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String shang = "";
		if (IsOnline)
			shang = "上线!";
		else
			shang = "下线!";

		String str = "设备ID：" + TerminalId + "接入服务器：" + TerminalIP + shang;
		writeFile(str);
		// System.out.println("DeviceRun类的IsOnline方法被调用。。。。。。。。");
		// 向表格中添加事由
		// ClientView.insertTableClient(new Object[] {
		// tempDate.format(new Date()),
		// "设备" + TerminalId + shang + "    IP:" + TerminalIP });
	}

	/**
	 * 向表格2中添加数据
	 * 
	 * @param msg
	 */
	public void insertTableServer(String[] msg) {
		try {
			// 设置显示的时间格式
			SimpleDateFormat tempDate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			// 向表格中添加事由
			DeviceView.insertTableServer(new Object[] {
					tempDate.format(new Date()), msg[0], msg[1] });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	boolean result = false;

	public void sendXML(String xml) {
		if (xml == null || xml.equals(""))
			return;

		LogUtil.DeviceManageInfo(" ===XML===" + xml);

		this.smd.SetEncodeDeviceOnline("", "", true, -1, -1, xml);

	}

	private String dbip;
	private String user;
	private String passwd;

	public void setDbip(String dbip) {
		this.dbip = dbip;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getDbip() {
		return dbip;
	}

	public String getUser() {
		return user;
	}

	public String getPasswd() {
		return passwd;
	}

	public static void main(String[] args) {
		String path;

		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/AppService/AppService.conf";
		else
			path = "d:\\fxconf\\AppService\\AppService.conf";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String hostip = doc.getRootElement().element("AppServer")
				.attributeValue("IP");
		String webip = doc.getRootElement().element("AppServer")
				.attributeValue("WEBIP");

		String dbip = doc.getRootElement().element("AppServer")
				.attributeValue("DBIP");
		String user = doc.getRootElement().element("AppServer")
				.attributeValue("USER");
		String passwd = doc.getRootElement().element("AppServer")
				.attributeValue("PASSWD");

		Integer port = Integer.valueOf(doc.getRootElement()
				.element("AppServer").attributeValue("PORT"));

		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/config/Device.xml";
		else
			path = "C:\\Windows\\System32\\config\\Device.xml";
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sessiodId = null;
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = doc.getRootElement().elementIterator();
		while (iter.hasNext()) {
			Element e = iter.next();
			if (e.attributeValue("Type").equals("002")) {

				sessiodId = e.attributeValue("DeviceSN");
				break;
			}

		}
		// sessiodId = "s244";
		// hostip = "192.168.1.244";
		// 从这里开始初始化管理器。
		DeviceRun dCLient;
		boolean a = true;

		System.out.println("sessionId: " + sessiodId);

		String sessiodIdFromDB = GetDeviceId.getInstance().getDeviceId("002");

		if (sessiodIdFromDB != null && sessiodIdFromDB.length() > 0)
			sessiodId = sessiodIdFromDB;

		System.out.println("sessionId: " + sessiodId);

		dCLient = new DeviceRun(sessiodId, hostip, port);
		dCLient.setUrl("http://" + webip + ":8080/webservice/services/web");
		dCLient.setDbip(dbip);
		dCLient.setPasswd(passwd);
		dCLient.setUser(user);
		dCLient.ConnectToServer();
		System.out.println("完成");
	}

	boolean connectflag;

	@Override
	public boolean isConnectflag() {
		return connectflag;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		connectflag = true;
	}

	// Timer a;

	@Override
	public ExchangeClient onAgainConnect() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (connectflag) {
			connectflag = false;
			new Thread(this).start();

		}
		return null;
	}

	// int n = 5000;

	@Override
	public void run() {
		ConnectToServer();
		// while (true) {
		// try {
		// Thread.sleep(n);
		// if (n < 60000)
		// n = n + 5000;
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// for (DeviceInstance di : NotAutoConnectionList) {
		// getSmd().SetEncodeDeviceOnline(di.getDeviceid(),
		// di.getHostIP(), di.isOnlineFlag(), -1, -1, null);
		// }
		//
		// }
	}
}
