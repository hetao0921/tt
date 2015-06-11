package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.VideoServerDao;
import com.sqlite.pojo.VideoServer;
 
public class VideoServerImpl extends BaseDaoImpl implements VideoServerDao {

	private static VideoServerImpl videoServerIntance;
	DBConne dbc = DBConne.getDbConne();
	/**
	 * ʹ�õ���ģʽ��øö���
	 * @return
	 */
	public static synchronized VideoServerImpl getVideoServerInstance() {
		if (videoServerIntance == null) {
			videoServerIntance = new VideoServerImpl();
		}
		return videoServerIntance;
	}
	
	@Override
	public List<VideoServer> getVideoServersByClientId(String clientId) {
		String sql = "select * from VideoServer where ClientId = '"+clientId+"'";
		return getVideoServerBySql(sql);
	}

	@Override
	public List<VideoServer> getVideoServersByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		String sql = "select * from VideoServer where DeviceId = '"+deviceId+"'";
		return getVideoServerBySql(sql);
	}

	@Override
	public List<VideoServer> getVideoServersByVSId(String vsId) {
		// TODO Auto-generated method stub
		String sql = "select * from VideoServer where VideoServerId = '"+vsId+"'";
		return getVideoServerBySql(sql);
	}
	
	public List<VideoServer> getVideoServersByClientId(String clientId,String deviceId,int devChId){
		String sql = String.format("select * from VideoServer where ClientId=%s" +
				" and DeviceId = %s and DevChId=%d",changeObj(clientId),changeObj
				(deviceId),changeObj(devChId) );
		return getVideoServerBySql(sql);
	}

	@Override
	public boolean delVideoServer(VideoServer video) {
		String sql = "delete from VideoServer where vid = "+video.getVid();
		return this.updateInfo(sql);
	}
	
	@Override
	public List<VideoServer> getAllVideoServers() {
		String sql = "select * from VideoServer";
		return getVideoServerBySql(sql);
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
	public boolean insertVideoServer(VideoServer v) {
		// TODO Auto-generated method stub
		String sql =String.format("insert into VideoServer(ClientId,DeviceId,DevChId," +
				"VideoServerId,VSChId,Flag,ClientIp,DeviceIP,LinkedMode,Lev) values" +
				"(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(v.getClientId()),changeObj
				(v.getDeviceId()),changeObj(v.getDevChId()),changeObj(v.getVideoServerId()),
				changeObj(v.getvSChId()),changeObj(v.getFlag()),changeObj(v.getClientIp()),
				changeObj(v.getDeviceIp()),changeObj(v.getLinkedMode()),changeObj(v.getLev()));
		return this.updateInfo(sql);
	}
	
	public List<VideoServer> getVideoServerByLev(int lev){
		String sql = String.format("select * from VideoServer where Lev < %d order by Lev", lev);
		return this.getVideoServerBySql(sql);
	}
	
	/**
	 * ���SQL����ѯ�����ж�Ӧ���豸ת����Ϣ
	 * @param sql
	 * @return
	 */
	public List<VideoServer> getVideoServerBySql(String sql){
		List<VideoServer> list = new ArrayList<VideoServer>();
		Statement stat = null;
		ResultSet rs = null;
		try {
			Connection conn = dbc.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				int vid = rs.getInt("vid");
				String clientId = rs.getString("ClientId");
				String deviceId = rs.getString("DeviceId");
				int devchId = rs.getInt("DevChId");
				String vsId = rs.getString("VideoServerId");
				int vschId = rs.getInt("VSChId");
				int flag = rs.getInt("Flag");
				String clientIp = rs.getString("ClientIp");
				String deviceIp = rs.getString("DeviceIP");
				int linkMode = rs.getInt("LinkedMode");
				int lev = rs.getInt("Lev");
				VideoServer vs = new VideoServer(vid,clientId,deviceId,devchId,vsId,vschId,
						flag,clientIp,deviceIp,linkMode,lev);
				list.add(vs);
			}
		}catch(Exception e){
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



}
