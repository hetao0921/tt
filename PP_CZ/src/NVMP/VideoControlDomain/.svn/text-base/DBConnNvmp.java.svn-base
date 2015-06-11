package NVMP.VideoControlDomain;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.misc.log.LogUtil;

import com.mysql.impl.Hibernate;


public class DBConnNvmp {
	private static DBConnNvmp db = null;

	private DBConnNvmp() {
	}

	public static DBConnNvmp getDBConn() {
		if (db == null)
			db = new DBConnNvmp();
		return db;
	}





	public boolean isDeviceId(String deviceId) {
		String sql = "select new Map(deviceId as deviceid) from NvmpVideodevattributetab where deviceId='"
				+ deviceId + "'";
		List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(sql);
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
/*	public boolean isDeviceId(String deviceId) {
		String sql = "select * from nvmp_videodevattributetab where DeviceID='"
				+ deviceId + "'";
		List<HashMap<String, String>> list = executeQuery(sql);
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
*/
	public List<String> getAllDeviceId() {
		String sql = "select new Map(deviceId as deviceid) from NvmpVideodevattributetab";
		List<String> list = new ArrayList<String>();
		List<HashMap<String, String>> listd = Hibernate.getHibernate().createQuery(sql);
		for (HashMap<String, String> map : listd) {
			String deviceId = map.get("deviceid");
			list.add(deviceId);
		}
		return list;
	}

	/**
	 * nvmp_centerroutectrltab 获得路由开关标示
	 * 
	 * true 可以用转发 false 不用转发
	 * */
	public boolean isRouteCtrl() {
		boolean b = false;

		String sql = "select new Map(routeopen as routeopen) from NvmpCenterroutectrltab";
		List<HashMap<String, String>> listd = Hibernate.getHibernate().createQuery(sql);
		for (HashMap<String, String> map : listd) {
			String flag = map.get("routeopen");
			if (flag.equals("1"))
				b = true;
		}
		return b;
	}
	
	public String  getFatherServerID() {
		String fatherServerID = null;
		String sql = "SELECT new Map(b.netWorkNodeId as networknodeid) FROM NvmpCenterinfotab a, NvmpCenternetworkinfotab b WHERE a.centerId =  b.centerId AND b.isControlFlag =  '2'";
		List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(sql);
		if (list.size() == 1) {
			fatherServerID = list.get(0).get("networknodeid");
		}
		return fatherServerID;
	}
	
//	public static void main(String[] args) {
//		DBConnNvmp nvmp=new DBConnNvmp();
//		System.out.println(nvmp.getRtspValue("f556eba80ad348a99668eb7959876e48", "250", 1, 1));
////		String hql="SELECT new Map (vb.rtspUrl as rtspurl ) FROM NvmpVideodevrtsptab vb, NvmpCenterinfotab cb where vb.centerId = cb.centerId and deviceId='f556eba80ad348a99668eb7959876e48' and channel=1  and typeId='250' ";
////		List<HashMap<String, String>> list=	Hibernate.getHibernate().createQuery(hql);
//		
//		System.out.println("--");
//	}
	
/*	public String  getFatherServerID() {
		String fatherServerID = null;
		String sql = "SELECT networknodeid FROM nvmp_centerinfotab a, nvmp_centernetworkinfotab b WHERE a.CenterID =  b.CenterID AND b.IsControlFlag =  '2'";
		List<HashMap<String, String>> list = MySqlManager.getInstance()
				.executeQuery(sql);
		if (list.size() == 1) {
			fatherServerID = list.get(0).get("networknodeid");
		}
		return fatherServerID;
	}
*/	
	

	// 在此处获取rtsp的相关数据
	public String getRtspValue(String deviceid, String typeid, int bitstream,
			int channel) {

		//获取真实通道，和判断是主or辅
		bitstream = 1;
		
		if(channel>9999) {
			channel = channel - 10000;
			bitstream = 0;
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT new Map (vb.rtspUrl as rtspurl ) FROM NvmpVideodevrtsptab vb, NvmpCenterinfotab cb where vb.centerId = cb.centerId and deviceId='");
		sql.append(deviceid);
		sql.append("' and channel=");
		sql.append(channel);

		if (typeid == null) {
			sql.append(" and defaultFlag=1 ");
			if (bitstream != -1) {
				sql.append("  and bitStream=");
				sql.append(bitstream);
			}
		} else {
			sql.append("  and typeId='");
			sql.append(typeid);
			sql.append("' ");

		}
		String str = null;
		try {
			LogUtil.BusinessInfo(sql.toString());
			List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(sql.toString());

			// LogUtil.BusinessInfo("rtsp list size  :" + list.size());
			if (list.size() > 0) {
				str = list.get(0).get("rtspurl");
				// System.out.println("str :  "+str);
			} else if(bitstream==0) {
				
				sql = new StringBuffer();
				sql.append("SELECT new Map(rtspUrl as rtspurl) FROM NvmpVideodevrtsptab where deviceId='");
				sql.append(deviceid);
				sql.append("' and defaultFlag=1 and channel=");
				sql.append(channel);
				list = Hibernate.getHibernate().createQuery(sql.toString());
				
				if (list.size() > 0) {
					str = list.get(0).get("rtspurl");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return str==null?str:str.replaceAll("&amp;", "&");
	}
/*	int channel) {
		
		//获取真实通道，和判断是主or辅
		bitstream = 1;
		
		if(channel>9999) {
			channel = channel - 10000;
			bitstream = 0;
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rtspurl FROM nvmp_videodevrtsptab Inner Join nvmp_centerinfotab ON nvmp_videodevrtsptab.CenterID = nvmp_centerinfotab.CenterID where deviceid='");
		sql.append(deviceid);
		sql.append("' and channel=");
		sql.append(channel);
		
		if (typeid == null) {
			sql.append(" and defaultflag=1 ");
			if (bitstream != -1) {
				sql.append("  and bitstream=");
				sql.append(bitstream);
			}
		} else {
			sql.append("  and typeid='");
			sql.append(typeid);
			sql.append("' ");
			
		}
		String str = null;
		try {
			LogUtil.BusinessInfo(sql.toString());
			List<HashMap<String, String>> list = executeQuery(sql.toString());
			
			// LogUtil.BusinessInfo("rtsp list size  :" + list.size());
			if (list.size() > 0) {
				str = list.get(0).get("rtspurl");
				// System.out.println("str :  "+str);
			} else if(bitstream==0) {
				
				sql = new StringBuffer();
				sql.append("SELECT rtspurl FROM nvmp_videodevrtsptab where deviceid='");
				sql.append(deviceid);
				sql.append("' and defaultflag=1 and channel=");
				sql.append(channel);
				list = executeQuery(sql.toString());
				
				if (list.size() > 0) {
					str = list.get(0).get("rtspurl");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return str==null?str:str.replaceAll("&amp;", "&");
	}
*/
}
