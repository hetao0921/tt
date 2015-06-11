package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.CenterInfoSyncDao;
import com.sqlite.pojo.CenterInfoSync;

public class CenterInfoSyncImpl implements CenterInfoSyncDao {
	 
	private static CenterInfoSyncImpl cisi;
	
	public static CenterInfoSyncImpl getCenterInfoSyncImpl(){
		if(cisi==null)
			cisi = new CenterInfoSyncImpl();
		return cisi;
	}
	
	private CenterInfoSyncImpl(){}
	
	DBConne dbc = DBConne.getDbConne();

    /**
     * 转化字符
     * @param a
     * @return
     */
    public Object c(Object a){
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
	
	private CenterInfoSync getCenterInfoSync(ResultSet res){
		CenterInfoSync cis = new CenterInfoSync();
		try{
			cis.setCenterDesc(res.getString("CenterDesc"));
			cis.setCenterGate(res.getString("CenterGate"));
			cis.setCenterId(res.getString("CenterID"));
			cis.setCenterIp(res.getString("CenterIP"));
			cis.setCenterMask(res.getString("CenterMask"));
			cis.setCenterName(res.getString("CenterName"));
			cis.setCenterPort(res.getInt("CenterPort"));
			cis.setCenterVar(res.getString("CenterVar"));
			cis.setDataBaseIp(res.getString("DataBaseIP"));
			cis.setDataBasePwd(res.getString("DataBasePwd"));
			cis.setDataBaseUser(res.getString("DataBaseUser"));
			cis.setDnsIp(res.getString("DNSIP"));
			cis.setDomainName(res.getString("DomainName"));
			cis.setId(res.getInt("ID"));
			cis.setLinkMode(res.getInt("LinkMode"));
			cis.setLoginPwd(res.getString("LoginPwd"));
			cis.setLoginUserName(res.getString("LoginUserName"));
			cis.setParentCenterIP(res.getString("ParentCenterIP"));
			cis.setParentCenterPort(res.getInt("ParentCenterPort"));
			cis.setRouteMode(res.getString("RouteMode"));
			cis.setSyncServerIP(res.getString("SyncServerIP"));
			cis.setSyncServerPort(res.getInt("SyncServerPort"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return cis;
	}

	@Override
	public CenterInfoSync getCenterInfoSync(String centerid) {
		String sql = String.format("select * from CenterInfoSync where CenterID=%s", c(centerid));
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		CenterInfoSync center = null;
		try{
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			center = getCenterInfoSync(rst);
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return center;
	}
}
