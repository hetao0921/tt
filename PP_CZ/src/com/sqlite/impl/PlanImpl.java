package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.PlanDao;
import com.sqlite.pojo.AlarmRecordPlan;

public class PlanImpl implements PlanDao {
	DBConne dbc = DBConne.getDbConne();
	BaseDaoImpl impl = new BaseDaoImpl();
	@Override
	public String getSessionId(String devId, int channelId) {
		String sql = "select SessionID from AlarmRecordPlan where DeviceID='"+devId
		+"' and DevCH="+channelId;
		String sessionId = null;
		Statement stat = null;
		ResultSet res = null; 
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res.next()){
				sessionId = res.getString("SessionID");
			}
		}catch(Exception e){
			System.out.println("ResultSet获取AlarmRecordPlan表中SessionId出错");
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
		
		return sessionId;
	}

	@Override
	public void updateSessionId(String deviceId, int channelId, String sessionId) {
		String sql = "update AlarmRecordPlan set SessionID='"+sessionId+"' where DeviceID='"
		+deviceId+"' and DevCH="+channelId;
		impl.runUpdateSql(sql);
	}


	@Override
	public List<AlarmRecordPlan> getAlarmRecordPlan() {
		List<AlarmRecordPlan> list = new ArrayList<AlarmRecordPlan>();
		String sql = "select * from AlarmRecordPlan where SessionID is null";
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String deviceId = rs.getString("DeviceID");
				int channelId = rs.getInt("DevCH");
				int chType = rs.getInt("CHType");
				int planType = rs.getInt("PlanType");
				String xml = rs.getString("PlanXml");
				String centerId = rs.getString("CenterID");
				String sessionId = rs.getString("SessionID");
				String planDesc = rs.getString("PlanDesc");
				AlarmRecordPlan p = new AlarmRecordPlan(id, deviceId,channelId,
						chType, planType, xml, centerId, sessionId,planDesc);
				list.add(p);
			}
		}catch(Exception e){
			System.out.println("ResultSet获取AlarmRecordPlan表中所有信息出错");
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
	public AlarmRecordPlan getAlarmRecordPlanById(Integer id) {
		AlarmRecordPlan p = null;
		String sql = "select * from AlarmRecordPlan where Id = "+id;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String deviceId = rs.getString("DeviceID");
				int channelId = rs.getInt("DevCH");
				int chType = rs.getInt("CHType");
				int planType = rs.getInt("PlanType");
				String xml = rs.getString("PlanXml");
				String centerId = rs.getString("CenterID");
				String sessionId = rs.getString("SessionID");
				String planDesc = rs.getString("PlanDesc");
				p = new AlarmRecordPlan(id, deviceId,channelId,
						chType, planType, xml, centerId, sessionId,planDesc);
			}
		}catch(Exception e){
			System.out.println("ResultSet获取AlarmRecordPlan表中所有信息出错");
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
		return p;
	}

	@Override
	public void addAlarmRecordPlan(Integer id, String sessionId) {
		String sql = "select count(*) from AlarmRecordPlan where ID="+id;
		if(getRecordNum(sql)>0){
			sql =String.format( "update AlarmRecordPlan set SessionId=%s where ID=%s"
					,changeObj(sessionId),changeObj(id));
		}else{
			sql =String.format( "insert into AlarmRecordPlan(ID,SessionId) values (%s,%s)"
					,changeObj(id),changeObj(sessionId));
		}
		impl.runUpdateSql(sql);
	}
    /**
     * 转化字符
     * @param a
     * @return
     */
    private Object changeObj(Object a){
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
     * 根据SQL语句，查询返回记录行数
     * @param sql
     * @return
     */
    private Integer getRecordNum(String sql){
    	Integer record = 0;

		Statement stat = null;
		ResultSet res = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			
    		if(res.next()){
    			record = res.getInt(1);
    		}
    	}catch(Exception e){
    		System.out.println("获取记录行数出错");
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
    	return record;
    }
}
