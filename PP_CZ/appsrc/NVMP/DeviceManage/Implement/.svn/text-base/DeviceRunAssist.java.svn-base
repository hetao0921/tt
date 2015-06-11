package NVMP.DeviceManage.Implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fxdigital.bean.NvmpVideodevtab;
import com.mysql.impl.ConvertMapUtil;
import com.mysql.impl.Hibernate;
import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.DeviceStatus;

import corenet.netbase.NIOReactor;
import corenet.netbase.Interface.ITimer;

public class DeviceRunAssist implements Runnable {
	private ITimer time;
	
	private DeviceRun deviceRun;
	
//	private List<DeviceInstance> notAutoConnectionList;
	
	private FastPing fastPing;
	
	private int outTime;
	
	
	public DeviceRunAssist(List<DeviceInstance> notAutoConnectionList, int outTime, DeviceRun deviceRun) {
		// TODO Auto-generated constructor stub
//		this.notAutoConnectionList = notAutoConnectionList;
		this.deviceRun = deviceRun;
		this.outTime = outTime;
		
	}


	public void start() {
		NIOReactor r = (NIOReactor)NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
		time.schedule(5000);
	}
	
	public void setFastPing(FastPing fastPing) {
		this.fastPing = fastPing;
	}
	
	@Override
	public void run() {
		time.schedule(5000);
		
		// list为读取数据库所得的内容，将list和notAutoConnectionList比较，确认数据库是否修改
		String tableName = "NvmpVideodevtab";
		String map=ConvertMapUtil.map(NvmpVideodevtab.class);
		String sql = "select "+map+" from " + tableName;
		List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(sql);
		
		if (list.size() == 0) {
			System.out.println("数量为0，停止");
			return;
		}
		
		List<DeviceInstance> addedElems = getAddedElem(deviceRun.getNotAutoConnectionList(), list);
		
		deviceRun.getNotAutoConnectionList().addAll(addedElems);

		for (DeviceInstance di : addedElems) {
			System.out.println("have new device");
			deviceRun.putNewDevice(di);
			fastPing.addHost(di.getHostIP(), outTime, di);
		}
	}
	
	private void updateDS(HashMap<String, String> hm) {
		DeviceStatus ds = new DeviceStatus();
		
		ds.setAlarmPointNum(Integer.parseInt(hm.get("AlarmPointNum".toLowerCase())));
		ds.setCameraNum(Integer.parseInt(hm.get("CameraNum".toLowerCase())));
		ds.setDevcieId(hm.get("DeviceID".toLowerCase()));
		ds.setDevIp(hm.get("DevIP".toLowerCase()));
		ds.setUserName(hm.get("UserName".toLowerCase()));
		ds.setPassword(hm.get("Password".toLowerCase()));
		ds.setDevPort(Integer.parseInt(hm.get("DevPort".toLowerCase())));
		ds.setDevType(Integer.parseInt(hm.get("DevType".toLowerCase())));
		ds.setOutPutPointNum(Integer.parseInt(hm.get("OutPutPointNum".toLowerCase())));
		ds.setDevSubType(Integer.parseInt(hm.get("DevSubType".toLowerCase())));
		ds.setSwitchSvrID(hm.get("SwitchSvrID".toLowerCase()));
		
		DAOFactory.getPojoImpl().updateInfo(ds);
	}
	
	private boolean isInCenter(String deviceID) {
		String tableName = "NvmpCenterinfotab";
		String columnName = "new Map (centerId as CenterID )";
		String sql = "select " + columnName + " from " + tableName;
		List<HashMap<String, String>> list =Hibernate.getHibernate().createQuery(sql);
		
		if (list.isEmpty()) {
			return false;
		}
		
		String centerID = list.get(0).get("CenterID");
		
		tableName = "NvmpVideodevtab";
		sql = "select " + columnName + " from " + tableName + " where deviceId = '" + deviceID + "'";
		list = Hibernate.getHibernate().createQuery(sql);
		
		if (list.isEmpty()) {
			return false;
		}
		
		return centerID.equals(list.get(0).get("CenterID"));
	}
	
	private List<DeviceInstance> getAddedElem(List<DeviceInstance> list1, List<HashMap<String, String>> list2) {
		List<DeviceInstance> list = new ArrayList<DeviceInstance>();
		
		for (int i = 0; i < list2.size(); ++i) {
			HashMap<String, String> hm = list2.get(i);
			boolean shouldBeAdded = true;

			for (int j = 0; j < list1.size(); ++j) {
				if (hm.get("DeviceID".toLowerCase()).equalsIgnoreCase(
						list1.get(j).getDeviceid())) {
					shouldBeAdded = false;
					break;
				}
			}

			if (shouldBeAdded && isInCenter(hm.get("DeviceID".toLowerCase()))) {
				DeviceInstance di = new DeviceInstance();
				di.setHostIP(hm.get("DevIP".toLowerCase()));
				di.setPort(Integer.parseInt(hm.get("DevPort".toLowerCase())));
				di.setUsername(hm.get("UserName".toLowerCase()));
				di.setPasswd(hm.get("Password".toLowerCase()));
				di.setDeviceid(hm.get("DeviceID".toLowerCase()));
				di.setAlrmPointNum(Integer.parseInt(hm.get("AlarmPointNum".toLowerCase())));
				di.setCanmelNum(Integer.parseInt(hm.get("CameraNum".toLowerCase())));
				di.setOutPointNum(Integer.parseInt(hm.get("OutPutPointNum".toLowerCase())));
				di.setDeviceType(Integer.parseInt(hm.get("DevType".toLowerCase())));

				list.add(di);
				
				updateDS(hm);
			}
		}
		
		return list;
	}
}
