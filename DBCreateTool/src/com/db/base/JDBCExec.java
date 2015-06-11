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
				logger.info("表名：" + rs.getString(3));
				logger.info("表所属用户名：" + rs.getString(2));
				logger.info("------------------------------");
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

	public static ResultSet getResultSet(Connection conn, String sql) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean getResultFlag(Connection conn, String sql) {
		boolean flag = false;
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				flag = true;
			}
			rs.close();
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void executeSql(Connection conn, String[] sqlStr) {
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for(String sql:sqlStr){
			stmt.executeUpdate(sql);
			logger.info("当前执行sql "+sql);
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    if (stmt != null) {
	        // 如果stat一开始就是null的话，如果不判断就一直出SQL异常，所以应该先判断一下
	        try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
		
	}

	public static int compareSize(Connection conn, String sql) {
		return 0;
	}

}
