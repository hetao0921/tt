package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.Canmel;

public class CanmelImpl extends BaseDaoImpl implements com.sqlite.dao.CanmelDao {
	DBConne dbc = DBConne.getDbConne();
	@Override
	public int getCanmelStatus(String DID, int TID,int typeid) {
		String sql=""; 
		if(typeid==3){
			sql = String.format("select MotionStatus from Canmel where DeviceID=%s and " +
					"ChannelID=%s",changeObj(DID),changeObj(TID));
		}else if(typeid==4){
			sql = String.format("select VideoLost from Canmel where DeviceID=%s and " +
					"ChannelID=%s",changeObj(DID),changeObj(TID));
		}
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
	public boolean updateCanmel(Canmel c) {
		String sql=String.format("update Canmel set channelID=%s,motionStatus=%s,videoLost" +
				"=%s where DeviceID=%s",changeObj(c.getChannelID()),changeObj(c.getMotionStatus
						()),changeObj(c.getMotionStatus()),changeObj(c.getVideoLost()));
		return updateInfo(sql);
	}

	/**
	 * ����豸ID��ѯһ������ͷ����
	 * @param DID
	 * @param TID
	 * @return
	 */
	public List<BasePO> getCanmel(String DID){
		List<BasePO> list = new ArrayList<BasePO>();
		String sql= String.format("select * from Canmel where DeviceID=%s",changeObj(DID));
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				BasePO b = getCanmel(res);
				list.add(b);
			}
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
		return list;
	}
	
	@Override
	public Canmel getCanmelById(int id) {
		// TODO Auto-generated method stub
		Canmel b = null;
		String sql = String.format("select * from Canmel where cid = %s",changeObj(id));
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if (res.next()) {
				b = getCanmel(res);
			}
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
	public List<Canmel> getAllCanmels() {
		List<Canmel> list = new ArrayList<Canmel>();
		String sql = "select * from Canmel";
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				Canmel b = getCanmel(res);
				list.add(b);
			}
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
		return list;
	}
	
	private Canmel getCanmel(ResultSet rs){
		Canmel b = null;
		try{
			int id = rs.getInt(1);
			String did = rs.getString(2);
			String dname = rs.getString(3);
			int tid = rs.getInt(4);
			int status = rs.getInt(5);
			int vediolost = rs.getInt(6);
			b = new Canmel(id, did,dname, tid, status, vediolost);
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Canmel getCanmel(String devId, Integer channelId) {
		String sql = String.format("select * from Canmel where DeviceID = %s and ChannelID = %s"
				, changeObj(devId),changeObj(channelId));
		Canmel c = null;
		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
			if(res.next())
				c = getCanmel(res); 

		}catch(Exception e){
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
		return c;
	}
}
