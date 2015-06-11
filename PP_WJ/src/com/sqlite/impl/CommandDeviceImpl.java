package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.CommandDeviceDao;
import com.sqlite.pojo.CommandDevice;
 
public class CommandDeviceImpl implements CommandDeviceDao {

	@Override
	public CommandDevice getCommandDeviceByDevId(String devId) {
		// TODO Auto-generated method stub
		DBConne dbc = DBConne.getDbConne();
		String sql = String.format("select * from CommandDevice where DeviceID=%s", changeObj(devId));
		CommandDevice cd = null;
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		try{
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			if(rst.next()){
				int id = rst.getInt("ID");
				String DeviceID = rst.getString("DeviceID");
				String DeviceName = rst.getString("DeviceName");
				String DeviceDesc = rst.getString("DeviceDesc");
				String DevIP = rst.getString("DevIP");
				String DevMAC = rst.getString("DevMAC");
				int WorkStatus = rst.getInt("WorkStatus");
				int IsOnline = rst.getInt("IsOnline");
				String ClientUserID = rst.getString("ClientUserID");
				int CommandStatus = rst.getInt("CommandStatus");
				int ConferenceStatus = rst.getInt("ConferenceStatus");
				int ConsultationStatus = rst.getInt("ConsultationStatus");
				int DevModal = rst.getInt("DevModal");
				String DevVer = rst.getString("DevVer");
				int CameraNum = rst.getInt("CameraNum");
				String CommandTeamID = rst.getString("CommandTeamID");
				String CenterID = rst.getString("CenterID");
				System.out.println("CnetId:"+CenterID);
				cd = new CommandDevice(id,DeviceID,DeviceName,DeviceDesc,DevIP,DevMAC,
						WorkStatus,IsOnline,ClientUserID,CommandStatus,ConferenceStatus,
						ConsultationStatus,DevModal,DevVer,CameraNum,CommandTeamID,CenterID);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cd;
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
}
