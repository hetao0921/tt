package NVMP.SyncDomain;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;


public class DBConn {
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

	/**
	 * ��ȡһ����ݿ�����
	 * 
	 * @return
	 */
	public Connection getConn() {
//		WebService w = new WebService();
//		String [][] ss = w.GetSyncServerInfo();
		String url = getUrl();
		
		while (con == null) {
			try {
				if (con == null || con.isClosed() == true) {

					Class.forName("com.mysql.jdbc.Driver");
//					System.out.println(url);
					con = DriverManager.getConnection(url);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con = null;
				System.out.println("�����ݿ�����ʧ��,���¿�ʼ");
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
	
	public Connection getConn2(){
		String ip = "";
		if(getDBIP()!=null&&!getDBIP().equals("")){
			ip = getDBIP();
		}else{
			ip = "127.0.0.1";
		}
		String url ="jdbc:mysql://"+ip+":3306/nvmp?user=admin&password=111&characterEncoding=" +
		"gbk&autoReconnect=true";
//		Connection conn = null;
		try {
			if (con2 == null || con2.isClosed() == true) {

				Class.forName("com.mysql.jdbc.Driver");
//				System.out.println(url);
				con2 = DriverManager.getConnection(url);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con2;
	}
	
	public String getUrl(){
		String ip = "";
		if(getDBIP()!=null&&!getDBIP().equals("")){
			ip = getDBIP();
		}else{
			ip = "127.0.0.1";
		}
		String url = "jdbc:mysql://"+ip+":3306/jms_client?user=admin&password=111&characterEncoding=" +
		"gbk&autoReconnect=true";
		return url;
	}
	
	
	public static void main(String[]args){
		DBConn d = new DBConn();
		String ip = d.getDBIP();
		System.out.println("ip:"+ip);
		
//		DBConn d = new DBConn();
//		List<String[]> list = d.executeQuery("select * from data_operate where operate = '上传'");
//		for(String[]ss :list){
//			for(String s :ss){
//				System.out.print(s+"\t");
//			}
//			System.out.println();
//		}
//		System.out.println("Connection:"+d.getConn());
	}
	
	public void updateInfo(String sql) throws Exception{
		Connection conn = getConn();
		Statement stat =conn.createStatement();
		stat.execute(sql);
		stat.close();
	}
	
	
	
	public List<String[]> executeQuery(String aim){
		Connection conn = getConn();
//		LinkedList<HashMap<String, String>> lnkd = new LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		try{
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
//				HashMap<String, String> hm = new HashMap<String, String>();
				String[] ss = new String[remd.getColumnCount()];
				for (int i = 1; i <= remd.getColumnCount(); i++) {
					ss[i-1] = resultSet.getString(i);
//					hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(ss);
			}
			resultSet.close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lnkd;
	}
	
	public List<String[]> executeQuery2(String aim){
		Connection conn = getConn2();
//		LinkedList<HashMap<String, String>> lnkd = new LinkedList<HashMap<String, String>>();
		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		try{
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
//				HashMap<String, String> hm = new HashMap<String, String>();
				String[] ss = new String[remd.getColumnCount()];
				for (int i = 1; i <= remd.getColumnCount(); i++) {
					ss[i-1] = resultSet.getString(i);
//					hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(ss);
			}
			resultSet.close();
			st.close();
		}catch(Exception e){
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
	
	public void executeProc(String name) throws Exception{
		CallableStatement stmt = getConn().prepareCall("{call "+name+"}");
		stmt.setString(1, "");
		stmt.registerOutParameter(2,Type.INTERNAL);
		stmt.setInt(2, 0);
		stmt.execute();
		stmt.close();
	}
	public boolean updateInfo(String []sql) {
		boolean result = false;
		PreparedStatement pst = null;
		Connection conn = getConn2();
		try {
			// 设置回滚点
			conn.setAutoCommit(false);
			if(sql.length>0){
				for(int i=0;i<sql.length;i++){
					pst = conn.prepareStatement(sql[i]);
					pst.execute();
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
			pst.close();
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
	
	/**
	 * 获取mysql数据库IP
	 * @return
	 */
	private String getDBIP(){
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if(f.exists()){
			try{
				SAXBuilder builder = new SAXBuilder();
				Document doc =  builder.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ip;
	}
}
 