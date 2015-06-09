package com.fxdigital.filesync.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.fxdigital.filesync.bean.FileSync;
import com.fxdigital.filesync.service.FilesyncService;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

public class DBConn {
	private static final Logger logger = Logger
			.getLogger(FilesyncService.class);

	private Connection con = null;
	private Connection con2 = null;
	private static DBConn db = null;

	private DBConn() {
		con = getConn();
	}

	public static DBConn getDBConn() {
		if (db == null)
			db = new DBConn();
		return db;
	}

	public String getLocalUrl() {
		String ip = "localhost";
		String url = "jdbc:mysql://" + ip
				+ ":3306/nvmp?user=admin&password=111&characterEncoding="
				+ "gbk&autoReconnect=true";
		return url;
	}

	/**
	 * ��ȡһ����ݿ�����
	 * 
	 * @return
	 */
	public Connection getConn() {
		// WebService w = new WebService();
		// String [][] ss = w.GetSyncServerInfo();
		String url = getLocalUrl();

		while (con == null) {
			try {
				if (con == null || con.isClosed() == true) {

					Class.forName("com.mysql.jdbc.Driver");
					// logger.info(url);
					con = DriverManager.getConnection(url);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con = null;
				logger.info("数据库驱动没有添加");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return con;
	}

	public Connection getConn2() {
		String ip = "";
		if (getDBIP() != null && !getDBIP().equals("")) {
			ip = getDBIP();
		} else {
			ip = "127.0.0.1";
		}
		String url = "jdbc:mysql://" + ip
				+ ":3306/nvmp?user=admin&password=111&characterEncoding="
				+ "gbk&autoReconnect=true";
		// Connection conn = null;
		try {
			if (con2 == null || con2.isClosed() == true) {

				Class.forName("com.mysql.jdbc.Driver");
				// logger.info(url);
				con2 = DriverManager.getConnection(url);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con2;
	}

	public String getUrl() {
		String ip = "";
		if (getDBIP() != null && !getDBIP().equals("")) {
			ip = getDBIP();
		} else {
			ip = "127.0.0.1";
		}
		String url = "jdbc:mysql://" + ip
				+ ":3306/jms_client?user=admin&password=111&characterEncoding="
				+ "gbk&autoReconnect=true";
		return url;
	}

	public static void main(String[] args) {
		// DBConn d = new DBConn();
		// String ip = d.getDBIP();
		// logger.info("ip:" + ip);

		DBConn.getDBConn().getConn();

		// DBConn d = new DBConn();
		// List<String[]> list =
		// d.executeQuery("select * from data_operate where operate = '上传'");
		// for(String[]ss :list){
		// for(String s :ss){
		// System.out.print(s+"\t");
		// }
		// logger.info();
		// }
		// logger.info("Connection:"+d.getConn());
	}

	public void updateInfo(String sql) throws Exception {
		Connection conn = getConn();
		Statement stat = conn.createStatement();
		stat.execute(sql);
		stat.close();
	}

	public List<String[]> executeQuery(String aim) {
		Connection conn = getConn();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		try {
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				// HashMap<String, String> hm = new HashMap<String, String>();
				String[] ss = new String[remd.getColumnCount()];
				for (int i = 1; i <= remd.getColumnCount(); i++) {
					ss[i - 1] = resultSet.getString(i);
					// hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(ss);
			}
			resultSet.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lnkd;
	}

	public List<FileSync> executeFileQuery(String aim) {
		Connection conn = getConn();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		LinkedList<FileSync> lnkd = new LinkedList<FileSync>();
		try {
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				// HashMap<String, String> hm = new HashMap<String, String>();
				FileSync ss = new FileSync();

				ss.setModifiedTime(resultSet.getTimestamp(2));
				ss.setFileName(resultSet.getString(4));
				ss.setFilePath(resultSet.getString(3));
				java.sql.Blob blob = (java.sql.Blob) resultSet.getBlob(5);
				ss.setBlob(blob);
				
			    ss.setMainFlag(resultSet.getInt(6));
			    ss.setBackupFlag(resultSet.getInt(7));
				//logger.info("blob" + blob);
				// ss.setVersion(resultSet.getInt(5));

				lnkd.add(ss);
			}
			resultSet.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lnkd;
	}

	public int executeFlagQuery(String aim) {
		Connection conn = getConn();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		int flag = 0;
		try {
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				flag = resultSet.getInt(1);
			}
			resultSet.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public List<String[]> executeQuery2(String aim) {
		Connection conn = getConn2();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		try {
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				// HashMap<String, String> hm = new HashMap<String, String>();
				String[] ss = new String[remd.getColumnCount()];
				for (int i = 1; i <= remd.getColumnCount(); i++) {
					ss[i - 1] = resultSet.getString(i);
					// hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(ss);
			}
			resultSet.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lnkd;
	}

	public List<String[]> executeQueryXml(String aim) throws SQLException {
		Connection conn = getConn2();
		// LinkedList<HashMap<String, String>> lnkd = new
		// LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		java.sql.Statement st = conn.createStatement();
		ResultSet resultSet = st.executeQuery(aim);
		ResultSetMetaData remd = resultSet.getMetaData();
		String[] sss = new String[remd.getColumnCount()];
		for (int i = 1; i <= remd.getColumnCount(); i++) {
			sss[i - 1] = remd.getColumnName(i);
		}
		lnkd.add(sss);
		sss = new String[remd.getColumnCount()];
		for (int i = 1; i <= remd.getColumnCount(); i++) {
			sss[i - 1] = remd.getColumnClassName(i);
		}
		lnkd.add(sss);
		while (resultSet.next()) {
			// HashMap<String, String> hm = new HashMap<String, String>();
			String[] ss = new String[remd.getColumnCount()];
			for (int i = 1; i <= remd.getColumnCount(); i++) {
				ss[i - 1] = resultSet.getString(i);
				// hm.put(remd.getColumnName(i), resultSet.getString(i));
			}
			lnkd.add(ss);
		}
		resultSet.close();
		st.close();
		return lnkd;
	}

	// public void executeProc(String name) throws Exception{
	// CallableStatement stmt = getConn().prepareCall("{call "+name+"}");
	// stmt.setString(1, "");
	// stmt.registerOutParameter(2,Type.INTERNAL);
	// stmt.setInt(2, 0);
	// stmt.execute();
	// stmt.close();
	// }
	public boolean updateInfo(String[] sql) {
		boolean result = false;
		PreparedStatement st = null;
		Connection conn = getConn2();
		try {
			// 设置回滚点
			conn.setAutoCommit(false);
			if (sql.length > 0) {
				for (int i = 0; i < sql.length; i++) {
					st = conn.prepareStatement(sql[i]);
					st.execute();
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
			st.close();
			result = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			result = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<HashMap<String, String>> executeQuerySql(String aim) {
		Connection conn = getConn();
		LinkedList lnkd = new LinkedList();
		try {
			Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				HashMap hm = new HashMap();

				for (int i = 1; i <= remd.getColumnCount(); i++) {
					// logger.info(remd.getColumnName(i));
					hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(hm);
			}
			resultSet.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lnkd;
	}

	/**
	 * 获取mysql数据库IP
	 * 
	 * @return
	 */
	private String getDBIP() {
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

}
