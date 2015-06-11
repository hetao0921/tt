package NVMP.DeviceManage.Implement;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import org.misc.log.LogUtil;

import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.DeviceStatus;

public class PingTimeTask extends TimerTask {

	/**
	 * 方法是： 循环判断，对本地的一个缓冲池进行循环登录， 如果登录失败，放到尾巴处，继续处理。 如果登录成功，调用发送信息，然后删除之。
	 * 
	 * 
	 * 
	 * */ 
	CopyOnWriteArraySet<QueueElement> cwa;
	private IDeviceRun run;
	boolean bflag;

	public PingTimeTask(IDeviceRun idr) {
		cwa = new CopyOnWriteArraySet<QueueElement>();
		run = idr;
		bflag = true;
	}

	public void putQueueElement(QueueElement qe) {
		cwa.add(qe);

	}

	public void removeQueueElement(QueueElement qe) {
		if (cwa.contains(qe))
			cwa.remove(qe);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (bflag) {
			Iterator<QueueElement> itor = cwa.iterator();
			bflag = false;
			while (itor.hasNext()) {

				QueueElement qe = itor.next();

				boolean online = qe.online;
				DeviceInstance di = qe.di;
				if (online && !di.isAutoflag()) {
					// System.out.println(di.getHostIP()+di.getPort()+di.getUsername()+di.getPasswd());
					//
					int n = run.getDc().login(di.getDeviceType(),di.getHostIP(), di.getPort(),
							di.getUsername(), di.getPasswd(), di);
					// System.out.println(n);
					if (n > -1) {
						cwa.remove(qe);
						LogUtil.DeviceManageInfo("附属线程：登录成功，我们告诉中心");
						di.setDeviceHandle(n);
						run.getDc()
								.startListen(di.getDeviceType(),n, di.getHostIP(), di.getPort());
						// run.getDc().AutoConnectionManage(n)
						di.setAutoflag(false);
						// System.out.println(di.isAutoflag());
						di.setOnlineFlag(true);
						
						//这里需要查询一次设备类型，
//						di.getDeviceid();
						DeviceStatus ds = DAOFactory.getDeviceStatusIntance()
						.getDeviceStatus(di.getDeviceid());
						run.getSmd().SetEncodeDeviceOnline(di.getDeviceid(),
								di.getHostIP(), true, ds.getDevType(),ds.getDevSubType(),
								ds.getUserName()+","+ds.getPassword()+","+ds.getDevPort());

					} else {

						// LogUtil.info("附属线程 :我们不进行操作");
						
						
						

					}

				}

			}

			bflag = true;
		}
	}

}
