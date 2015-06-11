package NVMP.DeviceManage.Implement.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteDBConne {
	private Connection conn = null;
	private String url = "jdbc:sqlite:/memory:tese.db";
	private String driver = "org.sqlite.JDBC";
	private static SqliteDBConne dbConne;
	
	private SqliteDBConne(){
		createTable();
	}
	
	public static synchronized SqliteDBConne getDbConne() {
		if (dbConne == null) {
			dbConne = new SqliteDBConne();
		}
		return dbConne;
	}
	
	public Connection getConne(){
		if(conn==null){
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * 创建内存数据库的表
	 * @return
	 */
	private boolean createTable(){
		String sql = "create table TimeRecord(Id integer primary key autoincrement,DeviceId " +
				"varchar(64),UpdateTime varchar(64))";
		if(tableIsExist("TimeRecord")){
			updateSql("drop table TimeRecord");
		}
		
		return updateSql(sql);
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
	
	/**
	 * 判断内存数据库中某个表是否存在
	 * @param tablename
	 * @return
	 */
	private boolean tableIsExist(String tablename){
		String sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='" +tablename+"'";
		boolean result = false;
		Connection con = getConne();
		try{
			Statement stat = con.createStatement();
			ResultSet rst = stat.executeQuery(sql);
			if(rst.next()){
				if(rst.getInt(1)==1){
					result = true;
				}
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查询出时间记录表的值
	 * @return
	 */
	public List<Map<String,String>> getTimeRecord(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String sql = "select * from TimeRecord";
		Connection con = getConne();
		try{
			Statement stat = con.createStatement();
			ResultSet rst = stat.executeQuery(sql);
			while(rst.next()){
				Map<String,String> map = new HashMap<String,String>();
				String DeviceId = rst.getString("DeviceId");
				String UpdateTime = rst.getString("UpdateTime");
				map.put("DeviceId", DeviceId);
				map.put("UpdateTime", UpdateTime);
				list.add(map);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}