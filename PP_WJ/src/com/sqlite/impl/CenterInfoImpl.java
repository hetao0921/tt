package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.CenterInfoDao;
import com.sqlite.pojo.CenterInfo;

public class CenterInfoImpl implements CenterInfoDao {
	DBConne dbc = DBConne.getDbConne(); 
	@Override
	public CenterInfo getCenterInfoById(String centerId) {
		// TODO Auto-generated method stub
		String sql = String.format("select * from CenterInfoTab where CenterID=%s",changeObj(centerId));
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		CenterInfo center = null;
		try{
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			if(rst.next()){
				int id = rst.getInt("ID");
				String CenterID = rst.getString("CenterID");
				String CenterName = rst.getString("CenterName");
				String CenterDesc = rst.getString("CenterDesc");
				String CenterIP = rst.getString("CenterIP");
				String CenterMask = rst.getString("CenterMask");
				String CenterGate = rst.getString("CenterGate");
				int CenterPort = rst.getInt("CenterPort");
				String LoginUserName = rst.getString("LoginUserName");
				String LoginPwd = rst.getString("LoginPwd");
				String DataBaseIP = rst.getString("DataBaseIP");
				String DataBaseUser = rst.getString("DataBaseUser");
				String DataBasePwd = rst.getString("DataBasePwd");
				String ParentCenterIP = rst.getString("ParentCenterIP");
				int ParentCenterPort = rst.getInt("ParentCenterPort");
				String DomainName = rst.getString("DomainName");
				String DNSIP = rst.getString("DNSIP");
				String CenterVar = rst.getString("CenterVar");
				int LinkMode = rst.getInt("LinkMode");
				String SyncServerIP = rst.getString("SyncServerIP");
				int SyncServerPort = rst.getInt("SyncServerPort");
				String RouteMode = rst.getString("RouteMode");
				center = new CenterInfo(id,CenterID,CenterName,CenterDesc,CenterIP,
						CenterMask,CenterGate,CenterPort,LoginUserName,LoginPwd,DataBaseIP,
						DataBaseUser,DataBasePwd,ParentCenterIP,ParentCenterPort,DomainName,
						DNSIP,CenterVar,LinkMode,SyncServerIP,SyncServerPort,RouteMode);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return center;
	}
    /**
     * 转化字符
     * @param a
     * @return
     */
    public Object changeObj(Object a){
    	if(a==null){
    		return null;
    	}else{
    		if(a.getClass().getSimpleName().equals("String")){
    			return "'"+a+"'";
    		}else{
    			return a;
    		}
    	}
    }
    
    public CenterInfo getCenterInfo(){
		String sql = "select * from CenterInfoTab";
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		CenterInfo center = null;
		try{
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			if(rst.next()){
				int id = rst.getInt("ID");
				String CenterID = rst.getString("CenterID");
				String CenterName = rst.getString("CenterName");
				String CenterDesc = rst.getString("CenterDesc");
				String CenterIP = rst.getString("CenterIP");
				String CenterMask = rst.getString("CenterMask");
				String CenterGate = rst.getString("CenterGate");
				int CenterPort = rst.getInt("CenterPort");
				String LoginUserName = rst.getString("LoginUserName");
				String LoginPwd = rst.getString("LoginPwd");
				String DataBaseIP = rst.getString("DataBaseIP");
				String DataBaseUser = rst.getString("DataBaseUser");
				String DataBasePwd = rst.getString("DataBasePwd");
				String ParentCenterIP = rst.getString("ParentCenterIP");
				int ParentCenterPort = rst.getInt("ParentCenterPort");
				String DomainName = rst.getString("DomainName");
				String DNSIP = rst.getString("DNSIP");
				String CenterVar = rst.getString("CenterVar");
				int LinkMode = rst.getInt("LinkMode");
				String SyncServerIP = rst.getString("SyncServerIP");
				int SyncServerPort = rst.getInt("SyncServerPort");
				String RouteMode = rst.getString("RouteMode");
				center = new CenterInfo(id,CenterID,CenterName,CenterDesc,CenterIP,
						CenterMask,CenterGate,CenterPort,LoginUserName,LoginPwd,DataBaseIP,
						DataBaseUser,DataBasePwd,ParentCenterIP,ParentCenterPort,DomainName,
						DNSIP,CenterVar,LinkMode,SyncServerIP,SyncServerPort,RouteMode);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return center;
    }
	@Override
	public String getCenterIpByCenterId(String centerId) {
		// TODO Auto-generated method stub
		CenterInfo ci = getCenterInfoById(centerId);
		if(ci!=null)
			return ci.getCenterIp();
		else
			return null;
	}
}
