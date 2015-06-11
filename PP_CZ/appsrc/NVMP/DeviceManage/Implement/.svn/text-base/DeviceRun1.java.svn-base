package NVMP.DeviceManage.Implement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.misc.log.LogUtil;



import com.fxdigital.EcDevice.ctrl.DeviceCtrl;
import com.fxdigital.EcDevice.ctrl.IDevNotify;
import com.sqlite.factory.DAOFactory;
import com.sqlite.impl.VideoDeviceImpl;
import com.sqlite.pojo.AlrmPoint;
import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.Canmel;
import com.sqlite.pojo.OutPoint;
import com.sqlite.pojo.VideoDevCH;
import com.sqlite.pojo.VideoDevice;

import NVMP.DeviceManage.Implement.devicectrl.DeviceCtrlManager;
import NVMP.Proxy.StateManageDomain;
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
public class DeviceRun1 implements IDeviceRun, IDevNotify {

	private DeviceCtrl dc = null;
    private ExchangeClient ec;
	private HashMap<String, DeviceInstance> deviceHp = null;
	
	private String deviceId;             //设备ID
	private int channId;                 //通道ID
	private int devCHType;               //通道类型，1移动侦测  2视频丢失  3报警点  4输出点
	private IRunTime run;
	private List<DeviceInstance> NotAutoConnectionList;
	
	public List<DeviceInstance> getNotAutoConnectionList() {
		return NotAutoConnectionList;
	}

	private StateManageDomain smd = null;

	public HashMap<String, DeviceInstance> getDeviceHp() {
		return deviceHp;
	}

	public DeviceCtrlManager getDc() {
		return null;
	}

	public StateManageDomain getSmd() {
		return smd;
	}

	public void setConnectOK(IConnectOK ok) {
		
		run.setNewConnectOk(ok);
	}
	
	public DeviceRun1(String sessionid, String addressIP, int port) throws Exception {
		
		ec = new ExchangeClient();
		run = new RunTime();
		run.setTransChannel(ec);
		smd = new StateManageDomain(run);
		StateManageEventHandler sneh = new StateManageEventHandler();
		smd.setEventhand(sneh);
		
		sneh.setDr(this);
		ec.SetSessionId(sessionid);
		try {
			ec.ConnectExchange(addressIP, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.deviceHp = new HashMap<String, DeviceInstance>();
		this.dc = DeviceCtrl.Instance();
		NotAutoConnectionList = new ArrayList<DeviceInstance>();
	}

	
	
	/**
	 * 初始化，包括 1、初始化登录工具，主要是放入回调处理类。 2、 获得所有管理设备 3、获得所有设备辖下端口
	 * 
	 * */
	@SuppressWarnings("static-access")
	public void init(String url) {
		//先从webservice读取数据
		LogUtil.DeviceManageInfo("开始初始化本地数据");
		//DAOFactory.getPojoImpl().init("http://192.168.1.101:8080/webservice/services/web");
		DAOFactory.getPojoImpl().init(url,"/DevDB");
		LogUtil.DeviceManageInfo("初始化本地数据完毕");
		
		
		
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

			// 查询摄像机
			if (Integer.parseInt(m.get("CameraNum")) > 0) {

				List<BasePO> li = DAOFactory.getSheXiangIntance().getCanmel(
						newDi.getDeviceid());
				for (BasePO bp : li) {
					Canmel canmel = (Canmel) bp;
					CanmelChannel cc = new CanmelChannel();
					cc.setChannelId(canmel.getChannelID());
					cc.setDeviceid(canmel.getDeviceId());
					cc.setMotionStaus(canmel.getMotionStatus());
					cc.setVideoLost(canmel.getVideoLost());
					newDi.addChannel(cc);
				}
			}

			// 查询报警点
			if (Integer.parseInt(m.get("AlarmPointNum")) > 0) {

				List<BasePO> li = DAOFactory.getBaoJingIntance().getAlrmPoint(
						newDi.getDeviceid());
				for (BasePO bp : li) {
					AlrmPoint  alrm = (AlrmPoint) bp;
					AlrmPointChannel ac = new AlrmPointChannel();
					ac.setChannelId(alrm.getChannelID());
					ac.setDeviceid(alrm.getDeviceId());
                    ac.setDeviceStatus(alrm.getDeviceStatus());
					newDi.addChannel(ac);
				}
			}
			
			// 查询输出点
			if(Integer.parseInt(m.get("OutPutPointNum")) > 0){
				List<BasePO> li = DAOFactory.getShuChuIntance().getOutPoint(
						newDi.getDeviceid());
				
				for (BasePO bp : li) {
					OutPoint  op = (OutPoint) bp;
					OutPointChannel ac = new OutPointChannel();
					ac.setChannelId(op.getChannelID());
					ac.setDeviceid(op.getDeviceId());
                    ac.setDeviceStatus(op.getDeviceStatus());
					newDi.addChannel(ac);
				}
				
				
			}
			
           this.deviceHp.put(newDi.getDeviceid(), newDi); 
		}

		// 先ping，凡是上线的，开始进行处理，连接得到句柄，开始 判断是否自动回复
		
		NotAutoConnectionList.addAll(this.deviceHp.values());
		
		
		PingDevice pd = new PingDevice(this,2000,50000);
		this.dc.startup();
		pd.start();
		
		
		
		
	
	
		
		
		
		

	}

	@Override
	public void handleAlarm(int LoginHandle, int AlarmType, int Value,
			Object Context) {
		// TODO Auto-generated method stub
		try {
			DeviceInstance di = (DeviceInstance) Context;
			this.smd.SetAlarmState(di.getDeviceid(), Value, AlarmType, 2);
		}catch(Exception e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void handleConnect(int LoginHandle, boolean Connected, Object Context) {
		// TODO Auto-generated method stub
		try {
			DeviceInstance di = (DeviceInstance) Context;
			this.smd.SetEncodeDeviceOnline(di.getDeviceid(), di.getHostIP(), Connected,-1,-1,null);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取设备服务器下所有的设备信息
	 * @return
	 */
	public MutableTreeNode getTreeModel(){
		MutableTreeNode root  = new DefaultMutableTreeNode("所有设备信息");
		  List<VideoDevice> list = new VideoDeviceImpl().getAllVideoDev();
		  for(int i=0;i<list.size();i++){
			  //添加设备节点信息
			  MutableTreeNode device = new DefaultMutableTreeNode(list.get(i).getDeviceStatus().getDevName());
			  root.insert(device, i);
			  //添加设备类型信息
			  MutableTreeNode canmel = new DefaultMutableTreeNode("摄像机");
			  MutableTreeNode alrmpoint = new DefaultMutableTreeNode("报警点");
			  MutableTreeNode outpoint = new DefaultMutableTreeNode("输出点");
			  device.insert(canmel, 0);
			  device.insert(alrmpoint, 1);
			  device.insert(outpoint, 2);
			  for(int j = 0 ;j<list.get(i).getChannels().size();j++){
				  VideoDevCH cc = list.get(i).getChannels().get(j);
				  MutableTreeNode channle = new DefaultMutableTreeNode(cc.getChName()+"_"+cc.getId());
				  int a = 0 , b = 0, c = 0;
				  if(cc.getChType()==3){
					  canmel.insert(channle, a);
					  a++;
				  }else if(cc.getChType()==4){
					  alrmpoint.insert(channle,b);
					  b++;
				  }else if(cc.getChType()==5){
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
	public void treeSelected(String type,int id) {
//		//获取父节点的名称，判断是摄像头、报警点还是输出点
//		TreePath tp = e.getNewLeadSelectionPath();
//		String[] parents = tp.getParentPath().toString().split(", ");
//		String type = parents[2].substring(0, 3);
//		String path = tp.getLastPathComponent().toString();
//		System.out.println("path:"+path);
//		
//		String id = path.substring(path.indexOf("_") + 1);
		
		//根据type属性，查询不同的表格
//		if(type.equals("摄像机")){
//			Canmel c = DAOFactory.getSheXiangIntance().getCanmelById(id);
//			smd.GetEncodeDeviceAlarm(LoginCenter.sessionId,c.getDeviceId(),c.getChannelID(),3);
//			smd.GetEncodeDeviceAlarm(LoginCenter.sessionId,c.getDeviceId(),c.getChannelID(),4);
//		}else if(type.equals("报警点")){
//			AlrmPoint a = DAOFactory.getBaoJingIntance().getAlrmPoint(id);
//			smd.GetEncodeDeviceAlarm(LoginCenter.sessionId,a.getDeviceId(),a.getChannelID(),1);
//		}else if(type.equals("输出点")){
//			OutPoint o = DAOFactory.getShuChuIntance().getOutPoint(id);
//			smd.GetEncodeDeviceAlarm(LoginCenter.sessionId,o.getDeviceId(),o.getChannelID(),2);
//		}
	}
	
	/**
	 * 实现布防按钮点击时的操作
	 */
	public void getAlarm(){
		smd.ControlAlarmState(deviceId, channId, devCHType, true);
	}
	
	/**
	 * 实现撤防按钮点击时的操作
	 * @param deviceId
	 * @param channId
	 * @param devCHType
	 */
	public void removeAlarm(){
		smd.ControlAlarmState(deviceId, channId,devCHType, false);
	}
	
	/**
	 * 向本地硬盘中写入一条数据
	 * @param str
	 */
	public void writeFile(String str){
		PrintStream ps=null;
		OutputStream os=null;
		try{
		File f=new File("D://1.txt");
		os=new FileOutputStream(f,true);
		ps=new PrintStream(os);
		ps.println(str);
		ps.flush();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
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
		
		String str = "设备ID:" + terminalId + "  通道ID:" + channelId
		+ "    通道类型:" + alarmType + "    状态:" + sta;
		
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
				DeviceView.showTextA("\n"+str);
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
				DeviceView.showText("设备ID:" + TerminalId + "  通道ID:" + ChannelId
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
//		ClientView.insertTableClient( new Object[] { tempDate.format(new Date()),
//				"设备" + TerminalId + sta });
	}

	/**
	 * 当获取上下线事件的时候，就向表格中插入一条数据
	 * 
	 * @param TerminalId
	 * @param TerminalIP
	 * @param IsOnline
	 * @param model
	 */
	public void isOnLine(String TerminalId, String TerminalIP,
			Boolean IsOnline) {
		
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String shang = "";
		if (IsOnline)
			shang = "上线!";
		else
			shang = "下线!";

		String str = "设备ID：" + TerminalId + "接入服务器：" + TerminalIP + shang;
		writeFile(str);
//		System.out.println("DeviceRun类的IsOnline方法被调用。。。。。。。。");
		// 向表格中添加事由
//		ClientView.insertTableClient(new Object[] {tempDate.format(new Date()),
//				"设备" + TerminalId + shang  + "    IP:" + TerminalIP});
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

	@Override
	public boolean isConnectflag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putOnline(String deviceid, Boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOnline(String deviceid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deviceLogin(String deviceid, Integer loginid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceLogout(String deviceid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTime(Integer year, Integer month, Integer day, Integer hour,
			Integer minute, Integer scond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCenterSource(String centerid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCenterSource(String centerid) {
		// TODO Auto-generated method stub
		
	}
	
	


}
