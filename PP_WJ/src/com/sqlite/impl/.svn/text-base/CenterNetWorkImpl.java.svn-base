package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.pojo.CenterNetWork;

public class CenterNetWorkImpl implements CenterNetWorkDao {
	DBConne dbc = DBConne.getDbConne();
	
	/** 
	 * 获取所有中心联网信息
	 */
	@Override
	public List<CenterNetWork> getAllCenterNetWork() {
		// TODO Auto-generated method stub
		String sql = "select * from CenterNetWorkInfoTab";
		List<CenterNetWork> list = new ArrayList<CenterNetWork>();
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		try{
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			while(rst.next()){
				int id = rst.getInt("ID");
				String NetWorkNodeID = rst.getString("NetWorkNodeID");
				String NetWorkNodeIP = rst.getString("NetWorkNodeIP");
				int NetWorkNodePort = rst.getInt("NetWorkNodePort");
				int IsParentFlag = rst.getInt("IsParentFlag");
				int IsControlFlag = rst.getInt("IsControlFlag");
				String centerId = rst.getString("CenterID");
				CenterNetWork cen = new CenterNetWork(id,NetWorkNodeID,NetWorkNodeIP,
						NetWorkNodePort,IsParentFlag,IsControlFlag,centerId);
				list.add(cen);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
