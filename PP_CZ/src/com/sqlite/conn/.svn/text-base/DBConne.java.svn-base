package com.sqlite.conn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConne {
	// ������ݿ�����
	private final String DBDRIVER_linux =  "org.sqlite.JDBC"; 
	private final String DBDRIVER_windows = "org.sqlite.JDBC"; 

	private static DBConne dbConne;
	
	private static String dbname;

	
	/**
	 * ʹ�õ���ģʽ��øö���
	 * 
	 * @return
	 */
	public static synchronized DBConne getDbConne() {
		if (dbConne == null) {
			dbConne = new DBConne();
			
		}
		return dbConne;
	}
	
	/**
	 * 设置文件数据库名称
	 * @param name
	 */
	public void setDbName(String name){
		dbname = name;
	}

	private Connection conn = null;
	private Connection conn2 = null;
	private DBConne() {
	}
	
	/**
	 * 删除硬盘上的文件数据库
	 * @param name
	 * @return
	 */
	private boolean deleteDb(String name){
		if(name!=null){
			try{
		        File file = new File(name);     
		        if(file.isFile() && file.exists()){     
		            file.delete();     
		            System.out.println("删除单个文件"+name+"成功！");     
		            return true;     
		        }else{     
		            System.out.println("删除单个文件"+name+"失败！"+name+"不是文件或者不存在！");     
		            return false;     
		        }   
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
	}

//	/**
//	 * 读取数据，保证每次的随机数据都不会错误。
//	 * */
//	private int getOnlyNumber() {
//		int n = 0;
//		try {
//			RandomAccessFile fos = new RandomAccessFile(
//					new File("lockfile.txt"), "rw");
//			// 获取文件锁 FileLock 对象
//			FileChannel fchannel = fos.getChannel();
//			FileLock fl = fchannel.lock();
//			// tryLock是尝试获取锁，有可能为空，所以要判断
//
//			if (fl != null) {
//				// System.out.println("Locked File");
//				// Thread.sleep(10000);
//				ByteBuffer bb = ByteBuffer.allocate(1024);
//				if (fchannel.read(bb) > -1) {
//					bb.flip();
//					if (bb.limit() > 0) {
//						byte[] bs = new byte[bb.limit()];
//						bb.get(bs);
//
//						if (bs != null) {
//							String str;
//							str = new String(bs, "UTF-8");
//							n = Integer.parseInt(str);
//							n++;
//						}
//
//					}
//				}
//
//				bb.clear();
//				bb.put(String.valueOf(n).getBytes("UTF-8"));
//				bb.flip();
//				fchannel.write(bb, 0);
//				fl.release();// 释放锁
//				// System.out.println("Released Lock");
//			}
//			fchannel.close();
//			fos.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return n;
//	}

	/**
	 * ��ȡ��ݿ�����
	 * 
	 * @return
	 */
	public Connection getConn() {
//		if (conn == null) {
//			int a = getOnlyNumber();
//			System.out.println(a);
//			try {
//				if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
//				{
//					this.conn = DriverManager
//							.getConnection("jdbc:sqlite:/memory:destate" + a
//									+ ".db");
//				} else {
//					deleteDb(dbname);
//					this.conn = DriverManager.getConnection("jdbc:sqlite:"+dbname);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		if (conn == null) {
			try {
				
				if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
				{
					System.out.println("add linux sqlite");
					Class.forName(DBDRIVER_linux);
//					deleteDb(dbname);
//					this.conn = DriverManager.getConnection("jdbc:sqlite:"
//							+ dbname);
					this.conn = DriverManager.getConnection("jdbc:sqlite:");
				} else {
//					deleteDb(dbname);
//					this.conn = DriverManager.getConnection("jdbc:sqlite:"
//							+ dbname);
					Class.forName(DBDRIVER_windows);
					this.conn = DriverManager.getConnection("jdbc:sqlite:");
				}
				
				conn.setAutoCommit(false);
				
			} catch (Exception e) {
				System.out.println("获取数据库连接出错...");
				e.printStackTrace();
			}
		}
		
		return conn;
	} 
	
	/**
	 * 特地在事务中使用
	 * @return
	 */
	public Connection getConn2() {
		
		
		return getConn();
//		if (conn2 == null) {
//			try {
//				Class.forName(DBDRIVER);
//				if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
//				{
////					this.conn2 = DriverManager
////							.getConnection("jdbc:sqlite:/memory:destate" + lock
////									+ ".db");
//					
//					
//					this.conn2 = DriverManager
//					.getConnection("jdbc:sqlite:"+dbname);
//				} else {
//					this.conn2 = DriverManager
//					.getConnection("jdbc:sqlite:"+dbname);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return conn2;
	} 
	
	public void setConn2Null(){
//		try{
//			if(conn2!=null){
//				conn2.close();
//				conn2 = null;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	/**
	 * �ر���ݿ�����
	 */
	public void closeConn() {
//		try {
//			if (conn != null && !conn.isClosed())
//				conn.close();
//			conn = null;
//		} catch (Exception e) {
//			System.out.println("关闭数据库连接出错...");
//		}
	}

	public static void main(String[] args) {
		// System.out.println((char)(((int)(Math.random()*1000))%53+65));
		// System.out.println((char)108);
		// System.out.println((char)122);
		// String s =null;
		// int i = Integer.parseInt(s);
		// System.out.println(i);
		// File f = new File("D:/NumberDate.txt");
		// // writeFile(f);
		// for(int i = 0;i<1000;i++){
		// System.out.println(getName(f));
		// writeFile(f);
		// }

		// System.out.println(readFile());
		
//		DBConne d = DBConne.getDbConne("D:\\destate3.db");
//		d.deleteDb("1.txt");
	}
}
