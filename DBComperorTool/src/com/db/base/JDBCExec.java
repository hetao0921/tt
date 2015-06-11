package com.db.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class JDBCExec {

	private static final Logger logger = Logger.getLogger(JDBCExec.class);

	public static List<String> getTables(Connection conn) {
		List<String> list = new ArrayList<String>();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getTables(null, null, null,
					new String[] { "TABLE" });
			while (rs.next()) {
				list.add(rs.getString(3));
				System.out.println("表名：" + rs.getString(3));
				System.out.println("表所属用户名：" + rs.getString(2));
				System.out.println("------------------------------");
			}
			// conn.close();
			// rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("得到所有数据表失败:" + e);
		}
		logger.info("所有数据表:" + list);
		return list;

	}

	public static List<String> getColumns(Connection conn, String dataTableName) {
		List<String> list = new ArrayList<String>();
		String sql = "select * from " + dataTableName + "";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();

			for (int i = 1; i <= data.getColumnCount(); i++) {
				// 获得所有列的数目及实际列数
				int columnCount = data.getColumnCount();
				// 获得指定列的列名
				String columnName = data.getColumnName(i);
				logger.info("列名： " + columnName);
				list.add(columnName);
			}

		} catch (Exception e) {
		}
		return list;
	}
	
	
	public static ResultSet getResultSet(Connection conn,String sql){
		 Statement stmt;
		 ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public static int compareSize(Connection conn,String sql){
		return 0;
	}

}
