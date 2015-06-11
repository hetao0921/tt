package NVMP.StateManageDomain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class UpdateDBConne {

	private Connection conn = null;
//	private String url = "jdbc:mysql://192.168.2.44:3306/nvmpmonitor?user=admin&password=111";
	private static UpdateDBConne dbConne;
	
//	private UpdateDBConne(String u){
//		url = u;
//	} 
	
	private UpdateDBConne(){}
	
//	public static synchronized UpdateDBConne getDbConne(String u) {
//		if (dbConne == null) {
//			dbConne = new UpdateDBConne(u);
//		}
//		return dbConne;
//	}
	
	public static synchronized UpdateDBConne getDbConne() {
		if (dbConne == null) {
			dbConne = new UpdateDBConne();
		}
		return dbConne;
	}
	
	public Connection getConne(){
		if(conn==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(getUrl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * 修改sql语句
	 * @param sql
	 * @return
	 */
	public boolean updateSql(String sql){
		boolean result = false;
		Connection con = getConne();
		try{
			Statement stat = con.createStatement();
			result = stat.execute(sql);
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
			exceptionHandler();
		}
		return result;
	}
	
	/**
	 * 修改SQL语句集合
	 * @param sqls
	 * @return
	 */
	public boolean updateSqls(String []sqls){
		for(int i=0;i<sqls.length;i++){
			if(!updateSql(sqls[i])){
				return false;
			}
		}
		return true;
	}
	
	public String getUrl(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/state/url.xml";
		else
			path = "d:\\fxconf\\state\\url.xml";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String  dbip = 	doc.getRootElement().element("IP").getText().trim();
		String  user = 	doc.getRootElement().element("USER").getTextTrim();
		String  passwd = 	doc.getRootElement().element("PASS").getTextTrim();
		String port = doc.getRootElement().element("PORT").getTextTrim();
		String url = "jdbc:mysql://"+dbip+":"+port+"/nvmpmonitor?user="+user+"&password="+passwd+"&characterEncoding=gbk&autoReconnect=true";
		return url;
	}
	
	public static void main(String[] args){
		UpdateDBConne u = new UpdateDBConne();
		System.out.println(u.getUrl());
	}
	
	public int getTableNum(String sql){
		int num = -1;
		try{
			Connection con = getConne();
			Statement stat = con.createStatement();
			ResultSet rst  = stat.executeQuery(sql);
			if(rst.next()){
				num = rst.getInt(1);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
			exceptionHandler();
		}
		return num;
	}
	
	/**
	 * 异常处理，将对象置null，以便下次重连
	 */
	private void exceptionHandler(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		conn = null;
		dbConne = null;
	}
}
