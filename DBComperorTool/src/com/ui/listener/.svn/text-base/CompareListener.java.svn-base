package com.ui.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.db.base.CreatSql;
import com.db.base.JDBCExec;

public class CompareListener {
	
	private static final Logger logger = Logger.getLogger(CompareListener.class);
	public Connection conn1;
	public Connection conn2;
	public String database;
	public String datatable;
	public List<String> columnsName;

	List<HashMap<String, String>> list1;
	List<HashMap<String, String>> list2;

	public CompareListener(Connection conn1, Connection conn2, String database,
			String datatable, List<String> columnsName) {
		this.conn1 = conn1;
		this.conn2 = conn2;
		this.database = database;
		this.datatable = datatable;
		this.columnsName = columnsName;
	}

	public ResultSet getConn1List() {
		list1 = new ArrayList<HashMap<String, String>>();
		String sql = CreatSql.CreatQuerySql(database, datatable, columnsName);
		ResultSet rs = JDBCExec.getResultSet(conn1, sql);
		return rs;
	}

	public ResultSet getConn2List() {
		list1 = new ArrayList<HashMap<String, String>>();
		String sql = CreatSql.CreatQuerySql(database, datatable, columnsName);
		ResultSet rs = JDBCExec.getResultSet(conn2, sql);
		return rs;
	}

	public String getCompareResult() {
		String result = "";
		ResultSet rs1 = getConn1List();
		ResultSet rs2 = getConn2List();
		try {
			rs1.beforeFirst();
			rs2.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rs1Size = getResultSize(rs1);
		int rs2Size = getResultSize(rs2);
		logger.info("数据表一的长度: "+rs1Size+(rs1Size == 0));
		logger.info("数据表二的长度: "+rs2Size+(rs2Size == 0));
		if (rs1Size == 0 && rs2Size == 0) {
			result = "数据库一和数据库二均没有数据";
			logger.info("数据库一和数据库二均没有数据");
			return result;
		}
		// 先看长度，如果长度不相同
		if (rs1Size != rs2Size) {
			result = "长度不相同，数据库一共" + rs1Size + "条，数据库二共" + rs2Size + "条";
		} else {
			try {
				rs1.beforeFirst();
				rs2.beforeFirst();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result1 = getResultStr(rs1);
			String result2 = getResultStr(rs2);
			if (result1.equals(result2)) {
				result = "数据库一和数据库二完全相同,数据库数据为：\n";
				result+= result1;
			} else {
			  int i=0;
              while(i<result1.length()){
            	  char char1=result1.charAt(i);
            	  char char2=result2.charAt(i);
            	  if(char1==char2){
            		  i++;
            	  }
            	  if(char1!=char2){
            		  String diffStr1=result1.substring(i, result1.length()-1);
            		  String diffStr2=result2.substring(i, result2.length()-1);
            		  result = "不相同的位置在:\n";
            		  result+="数据库一："+diffStr1+"\n";
            		  result+="数据库二："+diffStr2+"\n";

            		  break;
            	  }
              }
			}
		}

		return result;
	}

	public int getResultSize(ResultSet rs) {
		int count = 0;
		try {
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	// 将resultset转化为String
	public String getResultStr(ResultSet rs) {
		String str = "";
		try {
			while (rs.next()) {
				for (String column : columnsName)
					str += rs.getString(column);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;

	}
}
