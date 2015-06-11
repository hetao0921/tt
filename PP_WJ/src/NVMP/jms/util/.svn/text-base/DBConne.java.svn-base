package NVMP.jms.util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * 产生一个session，剩下就是jdbc的事情了
 * */
public class DBConne {
	// private final static String DBDRIVER = "org.sqlite.JDBC";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";
	static private DBConne dbconne;
	static private Connection conn;
	static private String url;

	private DBConne() {

		try {

			if (url == null) {
				System.out.println("数据库路径尚未初始化");
			}

			Class.forName(DBDRIVER);
			/**
			 * url = memory:destate.db 表示内存数据库 url = c:/temp.db 表示文件数据库
			 * */

			// conn = DriverManager.getConnection("jdbc:sqlite:/" + url);
			conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/"
							+ url
							+ "?user=admin&password=111&characterEncoding=gbk&autoReconnect=true");

		} catch (Exception e) {
			System.out.println("数据库初始化失败..");
			e.printStackTrace();
		}
	}

	static public DBConne getDBConne() {
		if (dbconne == null)
			dbconne = new DBConne();
		return dbconne;

	}

	static public void init(String dir) {
		url = dir;
		dbconne = new DBConne();
	}

	public void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private int seq;
//	private long totalTime;
//	private java.io.RandomAccessFile raf;

//	private java.io.RandomAccessFile getRafSend() {
//		if (raf == null) {
//			try {
//				raf = new java.io.RandomAccessFile(Common.getPath()
//						+ "jms_count_sqlite_update.txt", "rwd");
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return raf;
//	}
//
//	private void writeTime(String str) {
//		try {
//			raf = getRafSend();
//			raf.seek(raf.length());
//			byte[] b = str.getBytes("utf-8");
//			raf.write(b);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * by zzw
	 * 
	 * 返回数量就是执行后，改变的数量
	 * 
	 * */
	public int executeUpdate(String aim) throws SQLException {
//		Calendar ca = Calendar.getInstance();
//		long t1 = ca.getTimeInMillis();

		int num;
		java.sql.Statement st = conn.createStatement();
		num = st.executeUpdate(aim);
		st.close();

//		ca = Calendar.getInstance();
//		long t2 = ca.getTimeInMillis();
//		long t = t2 - t1;
//		totalTime += t;
//		seq++;
//		String time = Common.getTimeNow() + "：第" + seq + "次操作sqlite数据库，"
//				+ "耗时：" + t + "毫秒，累计耗时：" + totalTime + "毫秒。\n";
//		writeTime(time);
		return num;

	}

	public List<HashMap<String, String>> executeQuery(String aim)
			throws SQLException {
		LinkedList<HashMap<String, String>> lnkd = new LinkedList<HashMap<String, String>>();
		java.sql.Statement st = conn.createStatement();
		ResultSet resultSet = st.executeQuery(aim);
		ResultSetMetaData remd = resultSet.getMetaData();
		while (resultSet.next()) {
			HashMap<String, String> hm = new HashMap<String, String>();
			for (int i = 1; i <= remd.getColumnCount(); i++) {
				hm.put(remd.getColumnName(i), resultSet.getString(i));
			}
			lnkd.add(hm);
		}
		resultSet.close();
		st.close();
		return lnkd;
	}
	
	
	//尝试在此处放置各种预bianyi
	
	

	public boolean IsExistsTable(String tableName) {

		// String sqlStr =
		// "SELECT COUNT(*) as ac FROM sqlite_master where type='table' and name='"
		// + tableName + "'";

		String sqlStr = "select COUNT(*) as AC FROM  information_schema.tables where table_schema  = '"
				+ url + "' and table_name  = '" + tableName + "'";

		try {
			List<HashMap<String, String>> l = executeQuery(sqlStr);
			if (l.size() > 0) {
				HashMap<String, String> h = l.get(0);
				if (Integer.parseInt(h.get("AC").toString()) > 0)
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 清空数据库。
	public void restart() {

		// String sqlStr =
		// "SELECT tbl_name FROM sqlite_master WHERE type = 'table'";
		String sqlStr = "select table_name FROM  information_schema.tables where table_schema  = '"
				+ url + "' ";
//        System.out.println(sqlStr);
		try {
			List<HashMap<String, String>> l = executeQuery(sqlStr);
			for (HashMap<String, String> hp : l) {
				String name = hp.get("TABLE_NAME").toString();
				sqlStr = "DELETE from " + name;
				executeUpdate(sqlStr);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	static public void main(String...args) {
//		
		DBConne.init("jms_client");
		DBConne.getDBConne().restart();
//		int i;
//		HashMap<String, String> h = null;
//		String aim_max = "select ID from VERSION where ID = (select min(ID) from VERSION)";
//		java.util.List<HashMap<String, String>> l;
//		try {
//			l = DBConne.getDBConne().executeQuery(aim_max);
//			if (l.size() == 0)
//				return;
//			h = l.get(l.size() - 1);
//			i = Integer.parseInt(h.get("ID"));
//			System.out.println(i);
//			aim_max = "insert into INITRECORD (record) values (" + i + ")";
//			DBConne.getDBConne().executeUpdate(aim_max);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("没有找到最小接收行");
//
//		}
		
	}
	
	
	

}
