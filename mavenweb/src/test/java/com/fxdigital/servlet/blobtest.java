package com.fxdigital.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

public class blobtest {

    public static void main(String[] args) {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
       String url = "jdbc:mysql://localhost:3306/nvmp";
        // MySQL配置时的用户名
        String user = "admin";
        // MySQL配置时的密码
        String password = "111";
        try {
         // 加载驱动程序
         Class.forName(driver);
         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed())
          System.out.println("Succeeded connecting to the Database!");

         getString();
     	PreparedStatement ps=null;
    	try {
    	    ps=(PreparedStatement) conn.prepareStatement("insert into incrementdata_infotab(incrementsql,businesstype) values(?,?)");
    	    File file = new File("C://blob.txt");
    	    FileInputStream fis = null; 
    	    fis = new FileInputStream(file); 
    	    
    	    
    	    byte[] prikey_dataBytes = getString().getBytes();
    	    ByteArrayInputStream bais2 = new ByteArrayInputStream(prikey_dataBytes);
    	    
    	    
//    	    ps.setBinaryStream(1, fis,fis.available());  
    	    
  	    ps.setBinaryStream(1, bais2, prikey_dataBytes.length);
    	    
 // 	    ps.setBytes(1, getbytefromobject(getString()));  
    	    ps.setString(2, "fdfd");  
    	    ps.executeUpdate();  
    

        } catch(Exception e){
        	System.out.println("ddd"+e);
        }
    	
 //    	readblob(conn);
    	List<Map<String, Object>> list=executeQueryBlobList2("select * from nvmp.incrementdata_infotab order by id asc",conn);
    	System.out.println("dddd  "+list.get(0).get("incrementsql"));
    } catch(Exception e){
    	System.out.println("aaa"+e);
    }
        
	}
    
    
    public static String getString(){
    	StringBuffer sb=new StringBuffer();
    	sb.append("");
    	for(int i=0;i<40*3;i++){
    		sb.append("春光fdsafsafd基需要safdsaf");
    	}
    	wirteXml("C://blob.txt", sb.toString());
    	return sb.toString();
    }
    
    
	public static List<Map<String, Object>> executeQueryBlobList(String hql,Connection conn) {
		Statement st = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(hql);
			System.out.println("rs  "+rs.getFetchSize());
			Map<String, Object> map = null;
			while (rs.next()) {
				map = new HashMap();
				Blob bb = rs.getBlob("incrementsql");
				InputStream is = bb.getBinaryStream();
				ByteArrayInputStream bais = (ByteArrayInputStream)is;
				try {
					byte[] byte_data = new byte[bais.available()];
				    bais.read(byte_data, 0,byte_data.length);
					map.put("id", rs.getInt("id"));
					map.put("incrementsql",new String(byte_data,"utf-8"));
					map.put("businesstype", rs.getString("businesstype"));
					map.put("inserttime", rs.getTimestamp("inserttime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	//				logger.info("select table IncrementdataInfotab error :"+e);
					System.exit(0);
				}
			}
			result.add(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}
    
	public static List<Map<String, Object>> executeQueryBlobList2(String hql,Connection conn) {
		Statement st = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(hql);
			System.out.println("rs  "+rs.getFetchSize());
			Map<String, Object> map = null;
			while (rs.next()) {
				map = new HashMap();
				Blob bb = rs.getBlob("incrementsql");
				InputStream is = bb.getBinaryStream();
				ByteArrayInputStream bais = (ByteArrayInputStream)is;
				try {
					byte[] byte_data = new byte[bais.available()];
				    bais.read(byte_data, 0,byte_data.length);
					map.put("id", rs.getInt("id"));
					String content = new String(bb.getBytes((long)1, (int)bb.length()));
					map.put("incrementsql",content);
					map.put("businesstype", rs.getString("businesstype"));
					map.put("inserttime", rs.getTimestamp("inserttime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	//				logger.info("select table IncrementdataInfotab error :"+e);
					System.exit(0);
				}
			}
			result.add(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}
	
	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public static void wirteXml(String address, String xml) {
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			System.out.println("aaa"+e);
			e.printStackTrace();
		}
	}
    
    private static void readblob(Connection conn){  
    	   java.sql.Statement st;  
    	   try {  
    	    st = conn.createStatement();  
    	    java.sql.ResultSet rs=st.executeQuery("select incrementsql from incrementdata_infotab");  
    	      while(rs.next())  
    	      {  
    	       Blob bb=rs.getBlob("incrementsql");  
    	       bb.getBinaryStream();  
    	       InputStream is= bb.getBinaryStream();  
    	       ObjectInputStream ois=new ObjectInputStream(is);  
    	       Object oo= ois.readObject();  
    	       //System.out.println(oo);
    	       wirteXml("C://blob1.txt", oo.toString());
    	      }  
    	   } catch (Exception e) {  
    	    // TODO Auto-generated catch block  
    	    e.printStackTrace();  
    	   }  
    	  
    	}  
    
    
    public static byte[] getbytefromobject(Object oo){  
        ByteArrayOutputStream bs=new ByteArrayOutputStream();  
        try {  
         ObjectOutputStream oos = new ObjectOutputStream(bs);  
         oos.writeObject(oo);  
        } catch (Exception e) {  
         // TODO Auto-generated catch block  
         e.printStackTrace();  
        }//create a objectoutputstream from bytearrayoutputstream  
         
        return bs.toByteArray();  
     }  
}
