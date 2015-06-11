package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.DeviceStatusDao;
import com.sqlite.pojo.DeviceStatus;

public class DeviceStatusImpl extends BaseDaoImpl implements DeviceStatusDao {
 
	DBConne dbc = DBConne.getDbConne();
	@Override
	public String getCenterID(String DevID) {
		return this.getStringMsg(DevID);
	}

	@Override
	public boolean updateDeviceStatus(String DID, int satus) {
		String sql = "update DeviceStatus set DeviceStatus=" + satus
				+ " where DeviceID='" + DID + "'";
		String[] sqls = { sql };
		return this.updateInfo(sqls);
	}

	/**
	 * 转化字符
	 * 
	 * @param a
	 * @return
	 */
	public Object changeObj(Object a) {
		if (a == null) {
			return null;
		} else {
			if (a.getClass().getSimpleName().equals("String")) {
				return "'" + a + "'";
			} else {
				return a;
			}
		}
	}

	/**
	 * 如果参数是-1，则查询所有的设备信息
	 * 
	 * @param status
	 * @return
	 */
	public List<DeviceStatus> getAllDeviceStatus(int status) {
//		Calendar ca = Calendar.getInstance();
//		long start = ca.getTimeInMillis();
		String sql = "";
		if (status == -1) {
			sql = "select * from DeviceStatus";
		} else if (status == 1 || status == 0) {
			sql = "select * from DeviceStatus where DeviceStatus=" + status;
		} else {
			System.out.println("传入无效的设备状态");
		}
		List<DeviceStatus> list = new ArrayList<DeviceStatus>();
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
//			long t1 = Common.getNowTime();
			res = stat.executeQuery(sql);
//			long t2 = Common.getNowTime();
			while (res.next()) {
				int id = res.getInt("ID");
				String DeviceID = res.getString("DeviceID");
				String Devname = res.getString("Devname");
				String DevIP = res.getString("DevIP");
				String DevMask = res.getString("DevMask");
				String DevGate = res.getString("DevGate");
				String DevMAC = res.getString("DevMAC");
				int DevPort = res.getInt("DevPort");
				String UserName = res.getString("UserName");
				String Password = res.getString("Password");
				int DevType = res.getInt("DevType");
				int DevSubType = res.getInt("DevSubType");
				String DevModel = res.getString("DevModel");
				String DevSN = res.getString("DevSN");
				String DevVer = res.getString("DevVer");
				String AreaID = res.getString("AreaID");
				int CameraNum = res.getInt("CameraNum");
				int AlarmPointNum = res.getInt("AlarmPointNum");
				int OutPutPointNum = res.getInt("OutPutPointNum");
				int IsDisable = res.getInt("IsDisable");
				String RegisterTime = res.getString("RegisterTime");
				String SwitchSvrID = res.getString("SwitchSvrID");
				String CenterID = res.getString("CenterID");
				int DeviceStatus = res.getInt("DeviceStatus");

				// int id = rs.getInt(1);
				// String devId = rs.getString(2);
				// String devIp = rs.getString(4);
				// status = rs.getInt("DeviceStatus");
				DeviceStatus d = new DeviceStatus(id, DeviceID, Devname, DevIP,
						DevMask, DevGate, DevMAC, DevPort, UserName, Password,
						DevType, DevSubType, DevModel, DevSN, DevVer, AreaID,
						CameraNum, AlarmPointNum, OutPutPointNum, IsDisable,
						RegisterTime, SwitchSvrID, CenterID, DeviceStatus);
				list.add(d);
			}
//			long t3 = Common.getNowTime();
//			String str = Common.getDateTime()+":   执行SQL语句共耗时"+(t2-t1)+"毫秒，遍历集合共耗时"+(t3-t2)+"毫秒。\n";
//			Common.writeText("E://zouzw_time_test.txt", str);
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}finally {
			try {
				if (res != null) {
					System.out.println("close is!!!");
					res.close();
					
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if(status==-1){
//			ca = Calendar.getInstance();
//			long end = ca.getTimeInMillis();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String time = format.format(ca.getTime());
//			String str = time+"    : 共耗时"+(end-start)+"毫秒。\n";
//			try {
//				java.io.RandomAccessFile raf = new java.io.RandomAccessFile("e://zouzw_time_test.txt","rwd");
//				raf.seek(raf.length());
//				raf.write(str.getBytes("utf-8"));
//				raf.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} 
		return list;
	}

	@Override
	public boolean updateDeviceServer(DeviceStatus d) {
		DeviceStatus dev = getDeviceStatus(d.getDevcieId());
		String sql = "";
		if (dev == null) {
			sql = String
					.format("insert into DeviceStatus(DeviceID,Devname,DevIP,DevMask,DevGate,"
							+ "DevMAC,DevPort,UserName,Password,DevType,DevSubType,DevModel,DevSN,DevVer"
							+ ",AreaID,CameraNum,AlarmPointNum,OutPutPointNum,IsDisable,RegisterTime,"
							+ "SwitchSvrID,CenterID,DeviceStatus) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,"
							+ "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
							changeObj(d.getDevcieId()),
							changeObj(d.getDevName()), changeObj(d.getDevIp()),
							changeObj(d.getDevMask()),
							changeObj(d.getDevGate()),
							changeObj(d.getDevMAC()),
							changeObj(d.getDevPort()),
							changeObj(d.getUserName()),
							changeObj(d.getPassword()),
							changeObj(d.getDevType()),
							changeObj(d.getDevSubType()),
							changeObj(d.getDevModel()),
							changeObj(d.getDevSN()), changeObj(d.getDevVer()),
							changeObj(d.getAreaID()),
							changeObj(d.getCameraNum()),
							changeObj(d.getAlarmPointNum()),
							changeObj(d.getOutPutPointNum()),
							changeObj(d.getIsDisable()),
							changeObj(d.getRegisterTime()),
							changeObj(d.getSwitchSvrID()),
							changeObj(d.getCenterID()), changeObj(d.getDeviceStatus()));
			return this.updateInfo(sql);
		} else {
			sql = String
					.format("update DeviceStatus set Devname=%s,DevIP=%s,DevMask=%s,DevGate=%s,DevMAC=%s,"
							+ "DevPort=%s,UserName=%s,Password=%s,DevType=%s,DevSubType=%s,DevModel=%s,DevSN=%s"
							+ ",DevVer=%s,AreaID=%s,CameraNum=%s,AlarmPointNum=%s,OutPutPointNum=%s,IsDisable"
							+ "=%s,RegisterTime=%s,SwitchSvrID=%s,CenterID=%s,DeviceStatus=%s  where DeviceID"
							+ "=%s", changeObj(d.getDevName()),
							changeObj(d.getDevIp()), changeObj(d.getDevMask()),
							changeObj(d.getDevGate()),
							changeObj(d.getDevMAC()),
							changeObj(d.getDevPort()),
							changeObj(d.getUserName()),
							changeObj(d.getPassword()),
							changeObj(d.getDevType()),
							changeObj(d.getDevSubType()),
							changeObj(d.getDevModel()),
							changeObj(d.getDevSN()), changeObj(d.getDevVer()),
							changeObj(d.getAreaID()),
							changeObj(d.getCameraNum()),
							changeObj(d.getAlarmPointNum()),
							changeObj(d.getOutPutPointNum()),
							changeObj(d.getIsDisable()),
							changeObj(d.getRegisterTime()),
							changeObj(d.getSwitchSvrID()),
							changeObj(d.getCenterID()),
							changeObj(d.getDeviceStatus()),
							changeObj(d.getDevcieId()));
			return this.updateInfo(sql);
		}

	}

	/**
	 * ��ݽ���������������ѯ�����豸��Ϣ
	 * 
	 * @return
	 */
	public List<Map<String, String>> getDeviceBySwitchSvrID(String switchSvrId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String sql = String.format(
				"select * from DeviceStatus where SwitchSvrID = %s",
				changeObj(switchSvrId));
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				int id = res.getInt("ID");
				String DeviceID = res.getString("DeviceID");
				String Devname = res.getString("Devname");
				String DevIP = res.getString("DevIP");
				String DevMask = res.getString("DevMask");
				String DevGate = res.getString("DevGate");
				String DevMAC = res.getString("DevMAC");
				int DevPort = res.getInt("DevPort");
				String UserName = res.getString("UserName");
				String Password = res.getString("Password");
				int DevType = res.getInt("DevType");
				int DevSubType = res.getInt("DevSubType");
				String DevModel = res.getString("DevModel");
				String DevSN = res.getString("DevSN");
				String DevVer = res.getString("DevVer");
				String AreaID = res.getString("AreaID");
				int CameraNum = res.getInt("CameraNum");
				int AlarmPointNum = res.getInt("AlarmPointNum");
				int OutPutPointNum = res.getInt("OutPutPointNum");
				int IsDisable = res.getInt("IsDisable");
				String RegisterTime = res.getString("RegisterTime");
				String SwitchSvrID = res.getString("SwitchSvrID");
				String CenterID = res.getString("CenterID");
				int DeviceStatus = res.getInt("DeviceStatus");
				Map<String, String> map = new HashMap<String, String>();
				map.put("ID", new Integer(id).toString());
				map.put("DeviceID", DeviceID);
				map.put("Devname", Devname);
				map.put("DevIP", DevIP);
				map.put("DevMask", DevMask);
				map.put("DevGate", DevGate);
				map.put("DevMAC", DevMAC);
				map.put("DevPort", new Integer(DevPort).toString());
				map.put("UserName", UserName);
				map.put("Password", Password);
				map.put("DevType", new Integer(DevType).toString());
				map.put("DevSubType", new Integer(DevSubType).toString());
				map.put("DevModel", DevModel);
				map.put("DevSN", DevSN);
				map.put("DevVer", DevVer);
				map.put("AreaID", AreaID);
				map.put("CameraNum", new Integer(CameraNum).toString());
				map.put("AlarmPointNum", new Integer(AlarmPointNum).toString());
				map.put("OutPutPointNum",
						new Integer(OutPutPointNum).toString());
				map.put("IsDisable", new Integer(IsDisable).toString());
				map.put("RegisterTime", RegisterTime);
				map.put("SwitchSvrID", SwitchSvrID);
				map.put("CenterID", CenterID);
				map.put("DeviceStatus", new Integer(DeviceStatus).toString());
				list.add(map);
			}
		} catch (Exception e) {
			// System.out.println("��ݽ���������������ѯ�����豸��Ϣʱ����");
			// e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Map<String, String>> getDeviceByCenterID(String centerID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String sql = String.format(
				"select * from DeviceStatus where CenterID = %s",
				changeObj(centerID));
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				int id = res.getInt("ID");
				String DeviceID = res.getString("DeviceID");
				String Devname = res.getString("Devname");
				String DevIP = res.getString("DevIP");
				String DevMask = res.getString("DevMask");
				String DevGate = res.getString("DevGate");
				String DevMAC = res.getString("DevMAC");
				int DevPort = res.getInt("DevPort");
				String UserName = res.getString("UserName");
				String Password = res.getString("Password");
				int DevType = res.getInt("DevType");
				int DevSubType = res.getInt("DevSubType");
				String DevModel = res.getString("DevModel");
				String DevSN = res.getString("DevSN");
				String DevVer = res.getString("DevVer");
				String AreaID = res.getString("AreaID");
				int CameraNum = res.getInt("CameraNum");
				int AlarmPointNum = res.getInt("AlarmPointNum");
				int OutPutPointNum = res.getInt("OutPutPointNum");
				int IsDisable = res.getInt("IsDisable");
				String RegisterTime = res.getString("RegisterTime");
				String SwitchSvrID = res.getString("SwitchSvrID");
				String CenterID = res.getString("CenterID");
				int DeviceStatus = res.getInt("DeviceStatus");
				Map<String, String> map = new HashMap<String, String>();
				map.put("ID", new Integer(id).toString());
				map.put("DeviceID", DeviceID);
				map.put("Devname", Devname);
				map.put("DevIP", DevIP);
				map.put("DevMask", DevMask);
				map.put("DevGate", DevGate);
				map.put("DevMAC", DevMAC);
				map.put("DevPort", new Integer(DevPort).toString());
				map.put("UserName", UserName);
				map.put("Password", Password);
				map.put("DevType", new Integer(DevType).toString());
				map.put("DevSubType", new Integer(DevSubType).toString());
				map.put("DevModel", DevModel);
				map.put("DevSN", DevSN);
				map.put("DevVer", DevVer);
				map.put("AreaID", AreaID);
				map.put("CameraNum", new Integer(CameraNum).toString());
				map.put("AlarmPointNum", new Integer(AlarmPointNum).toString());
				map.put("OutPutPointNum",
						new Integer(OutPutPointNum).toString());
				map.put("IsDisable", new Integer(IsDisable).toString());
				map.put("RegisterTime", RegisterTime);
				map.put("SwitchSvrID", SwitchSvrID);
				map.put("CenterID", CenterID);
				map.put("DeviceStatus", new Integer(DeviceStatus).toString());
				list.add(map);
			}
		} catch (Exception e) {
			// System.out.println("��ݽ���������������ѯ�����豸��Ϣʱ����");
			// e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}	
	
	
	
	/**
	 * ����豸ID����ѯһ����Ӧ���豸����
	 */
	public DeviceStatus getDeviceStatus(String devId) {
		String sql = String.format(
				"select * from DeviceStatus where DeviceID = %s",
				changeObj(devId));
		DeviceStatus b = null;
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if (res.next()) {
				b = getDevice(res);
			} else {
				System.out.println("根据设备ID，查询的对象为空........");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return b;
	}

	@Override
	public List<DeviceStatus> getDeviceStatusByCenterId(String centerId) {
		String sql = String.format(
				"select * from DeviceStatus where CenterID = %s",
				changeObj(centerId));
		
		List<DeviceStatus> list = new ArrayList<DeviceStatus>();
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				DeviceStatus d = getDevice(res);
				list.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (res != null) {
					res.close();
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private DeviceStatus getDevice(ResultSet res) {
		DeviceStatus b = null;
		try {
			int id = res.getInt("ID");
			String DeviceID = res.getString("DeviceID");
			String Devname = res.getString("Devname");
			String DevIP = res.getString("DevIP");
			String DevMask = res.getString("DevMask");
			String DevGate = res.getString("DevGate");
			String DevMAC = res.getString("DevMAC");
			int DevPort = res.getInt("DevPort");
			String UserName = res.getString("UserName");
			String Password = res.getString("Password");
			int DevType = res.getInt("DevType");
			int DevSubType = res.getInt("DevSubType");
			String DevModel = res.getString("DevModel");
			String DevSN = res.getString("DevSN");
			String DevVer = res.getString("DevVer");
			String AreaID = res.getString("AreaID");
			int CameraNum = res.getInt("CameraNum");
			int AlarmPointNum = res.getInt("AlarmPointNum");
			int OutPutPointNum = res.getInt("OutPutPointNum");
			int IsDisable = res.getInt("IsDisable");
			String RegisterTime = res.getString("RegisterTime");
			String SwitchSvrID = res.getString("SwitchSvrID");
			String CenterID = res.getString("CenterID");
			int DeviceStatus = res.getInt("DeviceStatus");
			b = new DeviceStatus(id, DeviceID, Devname, DevIP, DevMask,
					DevGate, DevMAC, DevPort, UserName, Password, DevType,
					DevSubType, DevModel, DevSN, DevVer, AreaID, CameraNum,
					AlarmPointNum, OutPutPointNum, IsDisable, RegisterTime,
					SwitchSvrID, CenterID, DeviceStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public DeviceStatus getDeviceStatus(String DID, String centerId) {
		String sql = String.format(
				"select * from DeviceStatus where DeviceID=%s and CenterID=%s",
				changeObj(DID), changeObj(centerId));
		DeviceStatus b = null;
		
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if (res.next()) {
				b = getDevice(res);
			} else {
				System.out.println("根据设备ID和中心ID，查询的对象为空........");
			}
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (res != null) {
					res.close();
				}
				if(stat!=null){
					stat.close();
				}
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
		return b;
	}

	@Override
	public boolean delDeviceStatusByDevId(String devId) {
		String sql =String.format("delete from DeviceStatus where DeviceID = %s", changeObj(devId)); 
		boolean result = false;
		try{
			Connection con = dbc.getConn();
			Statement stat = con.createStatement();
			result = stat.execute(sql);
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String[] getDevIpByDevIdName(String devId) {
		DeviceStatus ds = getDeviceStatus(devId);
		if(ds!=null){
			String[] ipname = new String[2];
			ipname[0] = ds.getDevIp();
			ipname[1] = ds.getDevName();
			return ipname;
		}else
			return null;
	}

}
