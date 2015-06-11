package NVMP.DeviceManage.Implement;

import java.util.HashMap;
import java.util.List;

import org.misc.log.LogUtil;

import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.DeviceStatus;

class QueueElement {
	public String ip;
	public boolean online;
	public DeviceInstance di;

	public QueueElement(DeviceInstance di, String ip, boolean online) {
		this.di = di;
		this.ip = ip;
		this.online = online;
	}

}

/**
 * 对于所有的不能自动的处理。
 * */
public class PingDevice implements FastPingWrapper.PingNotify, Runnable {

	// 按DeviceInstance 寻找 list中的真实DI
	public DeviceInstance FoundInstance(Object o) {
		if (!(o instanceof DeviceInstance))
			return null;
		DeviceInstance di = (DeviceInstance) o;
		DeviceMap.put(di.getHostIP(), di);
		return di;
	}

	java.util.concurrent.ConcurrentLinkedQueue<QueueElement> queue;
	java.util.concurrent.ConcurrentHashMap<String, Boolean> result;
	HashMap<String, DeviceInstance> DeviceMap;

	HashMap<String, Integer> hpnum;
	HashMap<String, Boolean> hpboolean;
	
	HashMap<String, Long> timeRecord;

	// 清空result;
	public void clearResult() {
		result.clear();
	}

	//如果是true,记录上次传递进来时间是否超过5秒，可以才放过
	private boolean isTimeToDeal(String host){
		if (!timeRecord.containsKey(host)) {
			timeRecord.put(host, System.nanoTime());
			return false;
		} else {
			long lastTime = timeRecord.get(host);
			long currentTime = System.nanoTime();
			timeRecord.put(host, currentTime);
			if ((currentTime - lastTime) / 1000000000 > 5) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	@Override
	public void OnPingResult(String host, boolean online, Object obj) {
		// TODO Auto-generated method stub
		// 下面是要做的事情。
		// System.out.println(host + (online?" online":" offline"));
		// LogUtil.info(host + (online ? " online" : " offline"));
		// System.out.println("===================="+host);

//		System.out.println("fastping tell: " + host + " " + online);

		if (!run.isConnectflag()) {
			return;
		}
		
		//如果是true,记录上次传递进来时间是否超过5秒，可以才放过
		if(online && isTimeToDeal(host)){
			return;
		}		
		
		if (run.isOnline(host) == online) {

			return;
		}


		DeviceInstance di = FoundInstance(obj);

		System.out.println("放入ip" + host + "   " + online + " user="
				+ di.getUsername() + " passwd " + di.getPasswd() + " port "
				+ di.getPort());
		result.put(host, online);

		boolean b = true;
		while (b) {
			if (queue.size() < 500) {
				boolean flag = true;
				for (QueueElement qe : queue) {
					if (qe.ip.equals(host))
						flag = false;
				}
				if (flag)
					queue.add(new QueueElement(di, host, online));
				b = false;
			} else {
				b = true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// run.getSmd().SetEncodeDeviceOnline(TerminalId, TerminalIP, IsOnline);
		// run.getDc().logout(LoginHandle);
		// run.getDc().login(sAddress, Port, sUsrName, sUsrPass, Context)
		// run.getDc().startListen(LoginHandle, sAddress, Port)

	}

	private IDeviceRun run;
	private int waiting;
	private int outtime;

	private FastPing Ping;
	
	private DeviceRunAssist deviceRunAssist;
	
	void setDeviceAssist(DeviceRunAssist deviceRunAssist) {
		this.deviceRunAssist = deviceRunAssist;
	}

	// PingTimeTask pingTimeTask;

	public PingDevice(IDeviceRun idr, int waittime, int outtime) {
		run = idr;
		waiting = waittime;
		this.outtime = outtime;

		queue = new java.util.concurrent.ConcurrentLinkedQueue<QueueElement>();
		result = new java.util.concurrent.ConcurrentHashMap<String, Boolean>();
		DeviceMap = new HashMap<String, DeviceInstance>();
		// pingTimeTask = new PingTimeTask(idr);
		// TODO Auto-generated constructor stub
		hpnum = new HashMap<String, Integer>();
		hpboolean = new HashMap<String, Boolean>();
		
		timeRecord = new HashMap<String, Long>();
	}

	public void addHost(String ip, Object obj) {
		Ping.addHost(ip, outtime, obj);
	}

	public void delHost(String ip, String device) {
		Ping.delHost(ip);
		result.put(ip, false);
		DeviceInstance di = DeviceMap.get(ip);
		if (di == null) {
			return;
		}
		if (di.getDeviceHandle() > -1)
			run.getDc().logout(di.getDeviceType(), di.getDeviceHandle());
		run.getSmd().SetEncodeDeviceOnline(di.getDeviceid(), di.getHostIP(),
				false, -1, -1, null);
	}

	public void start() {

		try {
			Ping = FastPing.create(waiting, this);
			deviceRunAssist.setFastPing(Ping);
			List<DeviceInstance> list = run.getNotAutoConnectionList();
			if (list.size() == 0)
				throw new Exception() {
					private static final long serialVersionUID = 1L;

					public void printStackTrace() {
						System.out.println("数量为0，停止");
					}
				};
			for (DeviceInstance di : list) {
				Ping.addHost(di.getHostIP(), outtime, di);
			}
			Ping.start();
			deviceRunAssist.start();
			// 开始进行线程循环
			new Thread(this).start();
			// 同时也开始了30秒一次的循环。

			// Timer t = new Timer();
			// t.schedule(pingTimeTask, 30000, 30000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			QueueElement e;
			if ((e = queue.poll()) != null) {
				System.out.println("开始进行操作了。 ip=" + e.ip + " user="
						+ e.di.getUsername() + " passwd " + e.di.getPasswd()
						+ " port " + e.di.getPort());
				boolean online = e.online;
				DeviceInstance di = e.di;
				if (online && !di.isAutoflag()) {
					// System.out.println(di.getHostIP()+di.getPort()+di.getUsername()+di.getPasswd());
					//
					int n = run.getDc().login(di.getDeviceType(),
							di.getHostIP(), di.getPort(), di.getUsername(),
							di.getPasswd(), di);
					// System.out.println(n);
					if (n > -1) {
						LogUtil.DeviceManageInfo("login success !");
						di.setDeviceHandle(n);
						run.getDc().startListen(di.getDeviceType(), n,
								di.getHostIP(), di.getPort());
						// run.getDc().AutoConnectionManage(n)
						di.setAutoflag(false);
						// System.out.println(di.isAutoflag());
						di.setOnlineFlag(true);
						// 这里也有一个
						DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
								.getDeviceStatus(di.getDeviceid());
						run.deviceLogin(di.getDeviceid(), n);
						run.getSmd().SetEncodeDeviceOnline(
								di.getDeviceid(),
								di.getHostIP(),
								true,
								ds.getDevType(),
								ds.getDevSubType(),
								ds.getUserName() + "," + ds.getPassword() + ","
										+ ds.getDevPort() + "," + ds.getSwitchSvrID());

					} else {
						// pingTimeTask.putQueueElement(e);
						run.deviceLogout(di.getDeviceid());
						if (di.getDeviceHandle() > -1)
							run.getDc().logout(di.getDeviceType(),
									di.getDeviceHandle());
						di.setOnlineFlag(false);
//						run.getSmd().SetEncodeDeviceOnline(di.getDeviceid(),
//								di.getHostIP(), false, -1, -1, null);
						LogUtil.DeviceManageInfo("login fail");

					}

				} else {

					// pingTimeTask.removeQueueElement(e);
					run.deviceLogout(di.getDeviceid());
					if (di.getDeviceHandle() > -1)
						run.getDc().logout(di.getDeviceType(),
								di.getDeviceHandle());
					di.setOnlineFlag(false);
					run.getSmd().SetEncodeDeviceOnline(di.getDeviceid(),
							di.getHostIP(), false, -1, -1, null);

				}

			} else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException el) {
					// TODO Auto-generated catch block
					el.printStackTrace();
				}

			}

		}

	}

}
