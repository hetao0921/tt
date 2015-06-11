package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.OutPointDao;
import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.OutPoint;
 
public class OutPointImpl extends BaseDaoImpl implements OutPointDao {
	DBConne dbc = DBConne.getDbConne();
	@Override
	public int getOutPointStatus(String DID, int TID) {
		String sql="select DeviceStatus from OutPoint where DeviceID='"+DID+"' and ChannelID="+TID;
		return this.getIntMsg(sql);
	}

	@Override
	public boolean updateOutPoint(OutPoint a) {
		String sql=String.format("update OutPoint set DeviceStatus=%s,channelID=%s where " +
				"DeviceID=%s",changeObj(a.getDeviceStatus()),changeObj(a.getChannelID()),
				changeObj(a.getDeviceId()));
		return this.updateInfo(sql);
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
    
	
	/**
	 * 根据设备ID，查询对应的所有的报警点信息
	 * @param DID
	 * @param TID
	 * @return
	 */
	public List<BasePO> getOutPoint(String DID){
		List<BasePO> list = new ArrayList<BasePO>();
		String sql="select * from OutPoint where DeviceID='"+DID+"'";
		BasePO b=null;
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
				b=new OutPoint(id,did,dname,tid,status);
				list.add(b);
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
		return list;
	}
	
	
	/**
	 * 查询所有的报警点信息
	 * @param DID
	 * @param TID
	 * @return
	 */
	public List<BasePO> getOutPoint(){
		List<BasePO> list = new ArrayList<BasePO>();
		String sql="select * from OutPoint";
		BasePO b=null;
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
				b=new OutPoint(id,did,dname,tid,status);
				list.add(b);
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
		return list;
	}

	@Override
	public OutPoint getOutPoint(int id) {
		// TODO Auto-generated method stub
		String sql="select * from OutPoint where oid = "+id;
		OutPoint b=null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				String did = rs.getString(2);
				String dname = rs.getString(3);
				int tid = rs.getInt(4);
				int status = rs.getInt(5);
				b=new OutPoint(id,did,dname,tid,status);
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
