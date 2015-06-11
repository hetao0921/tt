package com.sqlite.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sqlite.conn.DBConne;

public class test1 {

	// public static void main(String[] args) {
	// // BaseDaoImpl impl = new BaseDaoImpl();
	//
	// System.out.println("请输入要初始化的内存数据库的IP地址：");
	// Scanner input = new Scanner(System.in);
	// String ip = input.next();
	//
	//
	// DAOFactory.getPojoImpl().init(
	// "http://"+ip+":8080/webservice/services/web","/test");
	//
	// CenterRouteImpl cri = new CenterRouteImpl();
	// String path = cri.getRouteById("A1c09999a58@001","A1c0999b4c7@001");
	// List<CenterRoute> list = cri.getAllCenterRoute();
	// CenterRoute cr = cri.getCenterRoute("A1c09999a58@001","A1c0999b4c7@001");
	// System.out.println(path);
	// System.out.println("总数："+list.size()+":"+cr.getRoutePath());

	// RouteImpl cisi = RouteImpl.getRouteImpl();
	// String [] ipname =
	// cisi.getRouteIp("A1c03554aa2@001,A1c0355987f@001,A1c03490a5c@001");
	// System.out.println("ip:"+ipname[0]);
	// System.out.println("name:"+ipname[1]);

	// DeviceStatusImpl ds = new DeviceStatusImpl();
	// String s = ds.getCenterID("64d971a8497c46f299a4f7db6a6b3780");
	// String s =
	// DAOFactory.getDeviceStatusIntance().getDeviceStatus("c065cdd7d6da49e29882039467dd501a").getSwitchSvrID();
	// System.out.println(s);

	// CenterRouteImpl cen = new CenterRouteImpl();

	// //中心一往上下级查找
	// CenterRoute center = cen.getCenterRoute("eace222c@001",
	// "eace176c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace222c@001", "eace444c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// CenterRoute center = cen.getCenterRoute("eace176c@001", "eace166c@001");
	// System.out.println("desc:" + center.getRoutePath());
	// center = cen.getCenterRoute("eace166c@001", "eace176c@001");
	// System.out.println("desc:" + center.getRoutePath());
	// center = cen.getCenterRoute("eace176c@001", "eace444c@001");
	// System.out.println("desc:" + center.getRoutePath());
	// center = cen.getCenterRoute("eace444c@001", "eace176c@001");
	// System.out.println("desc:" + center.getRoutePath());
	// center = cen.getCenterRoute("eace244c@001", "eace888c@001");
	// System.out.println("desc:"+center.getRoutePath());
	//
	// center = cen.getCenterRoute("eace888c@001", "eace166c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace166c@001", "eace888c@001");
	// System.out.println("desc:"+center.getRoutePath());
	//
	// center = cen.getCenterRoute("eace888c@001", "eace666c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace666c@001", "eace888c@001");
	// System.out.println("desc:"+center.getRoutePath());
	//
	// //中心二往上下级查找
	// center = cen.getCenterRoute("eace244c@001", "eace166c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace166c@001", "eace244c@001");
	// System.out.println("desc:"+center.getRoutePath());
	//
	// center = cen.getCenterRoute("eace244c@001", "eace666c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace666c@001", "eace244c@001");
	// System.out.println("desc:"+center.getRoutePath());
	//
	// //中心三往上下级查找
	// center = cen.getCenterRoute("eace166c@001", "eace666c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// center = cen.getCenterRoute("eace666c@001", "eace166c@001");
	// System.out.println("desc:"+center.getRoutePath());
	// int size = cen.getAllCenterRoute().size();
	// System.out.println("size:"+size);
	// List<DeviceStatus> d =
	// DAOFactory.getDeviceStatusIntance().getDeviceStatusByCenterId("eace244c@001");
	// for(DeviceStatus s :d){
	// System.out.println(s.getDevcieId()+"====="+s.getDevIp()+"======"+s.getDevType()+"======"+s.getDevSubType());
	// }
	// String center =
	// DAOFactory.getCommandDeviceIntance().getCommandDeviceByDevId("zhangsheng@006").getCenterID();
	// System.out.println("centerId:"+center);
	// String centerId =
	// DAOFactory.getDeviceStatusIntance().getDeviceStatus("ab6beaf66eed40a5bcae8db22f124eb9").getCenterID();
	// CenterInfoDao cen = new CenterInfoImpl();
	// System.out.println("数目："+cen.getCenterInfoById("eace888c@001").getCenterIp());
	//
	// System.out.println("centerId:"+centerId);
	// RouteImpl im = RouteImpl.getRouteImpl();
	// Route r = im.getRoute("eace244c@001",
	// "ca69c4e2eed94a8a8ec307265488cc4e");
	// System.out.println(r.getRouteDesc());
	// System.out.println("next:"+r.getNextRoute());
	// String str = "{Route}{type}1{/type}{desc}abc001{/desc}{/Route}";
	// Route r = Route.strToRoute(str);
	// System.out.println(r.getType()+"=="+r.getRouteDesc());
	// VideoServer vs = new VideoServer();
	//
	//
	//
	// VideoServer v = new VideoServer();
	// v.setClientId("s001");
	// v.setDevChId(2);
	// v.setDeviceId("sss");
	// v.setClientIp("192.168.2.2");
	// v.setLinkedMode(1);
	//
	// v.setDeviceIp("192.168.2.23");
	// v.setFlag(4);
	// v.setLev(7);
	// v.setVideoServerId("ss009");
	// v.setvSChId(3);
	//
	// DAOFactory.getVideoServerIntance().insertVideoServer(v);
	//
	// int i =
	// DAOFactory.getVideoServerIntance().getVideoServerByLev(9999).size();
	// System.out.println("i:"+i);

	// List<DeviceStatus> list =
	// DAOFactory.getDeviceStatusIntance().getAllDeviceStatus(-1);

	// System.out.println("集合大小："+list.size());
	// for(DeviceStatus d :list){
	// System.out.println("DevType:"+d.getDevType());
	// System.out.println("DevSubType:"+d.getDevSubType());
	// System.out.println("======================================");
	// }
	// DeviceStatus list =
	// DAOFactory.getDeviceStatusIntance().getDeviceStatus("e68709ff0238471b9d7537d00eebc383");
	// System.out.println("类型："+list.getDevType()+"   子类型："+list.getPassword());
	// List<Map<String,String>> d =
	// DAOFactory.getDeviceStatusIntance().getDeviceBySwitchSvrID("2011-08-16 14:19:55.0");
	// System.out.println("密码："+d.get(0).get("Password"));

	// String s = "abc";
	// Object o = s;
	// int i = 12;
	// Object ii = i;
	// String b = String.format("我是高江'%s'  %s", o,ii);
	// // System.out.println(ii.getClass().getSimpleName());
	// System.out.println(b);

	// //查看输出点表格中的数据
	// List<BasePO> list = DAOFactory.getShuChuIntance().getOutPoint();
	// System.out.println(list.size());

	// BaseDaoImpl impl = new BaseDaoImpl();
	// String sql = "select * from canmel";
	// ResultSet rs = impl.getInfo(sql);
	// // System.out.println("AlrmPoint表大小："+rs.hashCode());
	// try{
	// int i= 0;
	// while(rs.next()){
	// System.out.println(i+":"+rs.getInt(1)+":"+rs.getString(2));
	// i++;
	// }
	// }catch(Exception e){
	// e.printStackTrace();
	// }

	// DeviceStatus d = new
	// DeviceStatus(1,"sss1","摄像头3","192.168.1.1","devMask",null,null,0,null,null,1,1,null,null,null,null,3,3,3,1,"2011-08-22 17:55:00","s001",null,0);
	// boolean r =
	// DAOFactory.getDeviceStatusIntance().updateDeviceServer(d);
	// System.out.println(r);

	// DeviceStatus d =
	// DAOFactory.getDeviceStatusIntance().getDeviceStatus("614c7474bc5149b994d7b4c513f44261");
	// System.out.println(d.getUserName()+"==="+d.getPassword());

	// // if(d.getDevGate()==null)
	// // System.out.println("值为null");
	// // else
	// // System.out.println("值为‘null’");
	// System.out.println(d.getDevName());

	// List<Canmel> list = DAOFactory.getSheXiangIntance().getAllCanmels();
	// for(int i = 0;i<list.size();i++){
	// Canmel c = list.get(i);
	// System.out.println("ID:"+c.getBid());
	// System.out.println("DeviceId:"+c.getDeviceId());
	// System.out.println("=====================================");
	// }
	// System.out.println(list.size());

	// Canmel c = DAOFactory.getSheXiangIntance().getCanmelById(24);
	// if (c.getDeviceId() == null)
	// System.out.println("对象为null");
	// else
	// System.out.println("对象为‘null’");

	// DAOFactory.getPojoImpl().init("http://192.168.1.244:8080/webservice/services/web");

	// Canmel c = new Canmel(25,null,1,1,1);
	// String sql =
	// "insert into Canmel(DeviceId,ChannelId,MotionStatus,VideoLost) " +
	// "values(?,?,?,?)";
	// Object []params ={null,1,1,1};
	// BaseDaoImpl impl = new BaseDaoImpl();
	// boolean r = impl.runUpdateSql(sql, params);
	// System.out.println(r);

	// if(d.getDevGate()==null)
	// System.out.println("对象为null");
	// else
	// System.out.println("对象为‘null’");

	// boolean s =
	// DAOFactory.getDeviceStatusIntance().updateDeviceServer(d);
	// System.out.println(s);
	// }

//	public static boolean updateInfo(String sql, Canmel c) {
//		Connection conn = null;
//		PreparedStatement pst = null;
//		boolean result = false;
//		try {
//			conn = DBConne.getDbConne().getConn();
//			pst = conn.prepareStatement(sql);
//			// pst.setInt(1, c.getBid());
//			pst.setString(1, c.getDeviceId());
//			pst.setInt(2, c.getChannelID());
//			pst.setInt(3, c.getMotionStatus());
//			pst.setInt(4, c.getVideoLost());
//			result = pst.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pst.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return result;
//	}
}
