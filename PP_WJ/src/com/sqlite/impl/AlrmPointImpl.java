package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.AlrmPointDao;
import com.sqlite.pojo.AlrmPoint;
import com.sqlite.pojo.BasePO;
 
public class AlrmPointImpl extends BaseDaoImpl implements AlrmPointDao {
	DBConne dbc = DBConne.getDbConne();
	@Override
	public int getAlrmPointStatus(String DID, int TID) {
		String sql="select DeviceStatus from AlrmPoint where DeviceID='"+DID+"' and ChannelID="+TID;
		return this.getIntMsg(sql);
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
    
    
	@Override
	public boolean updateAlrmPoint(AlrmPoint a) {
		String sql=String.format("update AlrmPoint set DeviceStatus=%s,channelID=%s " +
				"where DeviceID=%s",changeObj(a.getDeviceStatus()),changeObj(a.getChannelID())
				,changeObj(a.getDeviceId()));
		return this.updateInfo(sql);
	}
	
	/**
	 * 根据设备ID，查询对应的所有报警点信息
	 * @param DID
	 * @param TID
	 * @return
	 */
	public List<BasePO> getAlrmPoint(String DID){
		List<BasePO> list = new ArrayList<BasePO>();
		String sql=String.format("select * from AlrmPoint where DeviceID=%s",changeObj(DID));
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String did = rs.getString(2);
				String dname = rs.getString(3);
				int tid = rs.getInt(3);
				int status = rs.getInt(4);
				BasePO b=new AlrmPoint(id,did,dname,tid,status);
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs!= null) {
					rs.close();
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

	@Override
	public AlrmPoint getAlrmPoint(int aid) {
		// TODO Auto-generated method stub
		AlrmPoint b = null;
		String sql="select * from AlrmPoint where AID="+aid;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String did = rs.getString(2);
				String dname = rs.getString(3);
				int tid = rs.getInt(4);
				int status = rs.getInt(5);
				b=new AlrmPoint(id,did,dname,tid,status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
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
	public AlrmPoint getAlarmPoint(String devId, int channelId) {
		AlrmPoint b = null;
		String sql = String.format("select * from AlrmPoint where DeviceID=%s and DevCH=%s",
				changeObj(devId),changeObj(channelId));
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			while(rs.next()){
				int id=rs.getInt(1);
				String did = rs.getString(2);
				String dname = rs.getString(3);
				int tid = rs.getInt(4);
				int status = rs.getInt(5);
				b=new AlrmPoint(id,did,dname,tid,status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
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
	
}
