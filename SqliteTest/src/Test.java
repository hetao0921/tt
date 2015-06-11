import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Test {
	
	public static void main(String[] args) throws Exception {
		Class.forName("SQLite.JDBCDriver");
		String fileName="//etc//videostore.db";
	    Connection conn =
	      DriverManager.getConnection("jdbc:sqlite:"+fileName);
	    Statement stat = conn.createStatement();
	    String insertSql="INSERT INTO `nvmp_record_storage_info` VALUES ('8', '192.168.1.55', '8001', 'rtsp://192.168.1.55:8001/storage');";
//	    stat.execute(insertSql);
	    ResultSet rs = stat.executeQuery("select * from nvmp_record_storage_info;");
	    while (rs.next()) {
	      System.out.println("name = " + rs.getString("storageip"));
	    }
	    rs.close();
	    conn.close();
	}
	  
}
