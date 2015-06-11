/*     */ package NVMP.serverclient;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DBConn
/*     */ {
/*  15 */   private Connection con = null;
/*  16 */   private static DBConn db = null;
/*     */ 
/*     */   private DBConn() {
/*  19 */     this.con = getConn();
/*     */   }
/*     */ 
/*     */   public static DBConn getDBConn() {
/*  23 */     if (db == null)
/*  24 */       db = new DBConn();
/*  25 */     return db;
/*     */   }
/*     */ 
/*     */   public Connection getConn()
/*     */   {
/*  36 */     String url = getUrl();
/*     */ 
/*  38 */     while (this.con == null) {
/*     */       try {
/*  40 */         if ((this.con == null) || (this.con.isClosed()))
/*     */         {
/*  42 */           Class.forName("com.mysql.jdbc.Driver");
/*     */ 
/*  44 */           this.con = DriverManager.getConnection(url);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/*  48 */         e.printStackTrace();
/*  49 */         this.con = null;
/*  50 */         System.out.println("�����ݿ�����ʧ��,���¿�ʼ");
/*     */         try {
/*  52 */           Thread.sleep(1000L);
/*     */         }
/*     */         catch (InterruptedException e1) {
/*  55 */           e1.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*  59 */     return this.con;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/*  63 */     String url = "jdbc:mysql://127.0.0.1:3306/jms_server?user=admin&password=111&characterEncoding=gbk&autoReconnect=true&procedurecache=0";
/*     */ 
/*  65 */     return url;
/*     */   }
/*     */ 
/*     */   public void updateInfo(String sql) {
/*  69 */     Connection conn = getConn();
/*     */     try
/*     */     {
/*  72 */       Statement stat = conn.createStatement();
/*  73 */       stat.execute(sql);
/*  74 */       stat.close();
/*     */     }
/*     */     catch (SQLException e) {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<HashMap<String, String>> executeQuery(String aim)
/*     */   {
/*  83 */     Connection conn = getConn();
/*  84 */     LinkedList lnkd = new LinkedList();
/*     */     try
/*     */     {
/*  87 */       Statement st = conn.createStatement();
/*  88 */       ResultSet resultSet = st.executeQuery(aim);
/*  89 */       ResultSetMetaData remd = resultSet.getMetaData();
/*  90 */       while (resultSet.next()) {
/*  91 */         HashMap hm = new HashMap();
/*     */ 
/*  93 */         for (int i = 1; i <= remd.getColumnCount(); i++)
/*     */         {
/*  95 */           hm.put(remd.getColumnName(i), resultSet.getString(i));
/*     */         }
/*  97 */         lnkd.add(hm);
/*     */       }
/*  99 */       resultSet.close();
/* 100 */       st.close();
/*     */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 105 */     return lnkd;
/*     */   }
/*     */ 
/*     */   public boolean updateInfo(String[] sql) {
/* 109 */     boolean result = false;
/* 110 */     PreparedStatement pst = null;
/* 111 */     Connection conn = getConn();
/*     */     try
/*     */     {
/* 114 */       conn.setAutoCommit(false);
/* 115 */       if (sql.length > 0) {
/* 116 */         for (int i = 0; i < sql.length; i++) {
/* 117 */           pst = conn.prepareStatement(sql[i]);
/* 118 */           pst.execute();
/*     */         }
/*     */       }
/* 121 */       conn.commit();
/* 122 */       conn.setAutoCommit(true);
/* 123 */       pst.close();
/* 124 */       result = true;
/*     */     }
/*     */     catch (SQLException e) {
/*     */       try {
/* 128 */         conn.rollback();
/*     */       }
/*     */       catch (SQLException e1) {
/* 131 */         e1.printStackTrace();
/*     */       }
/* 133 */       result = false;
/*     */ 
/* 135 */       e.printStackTrace();
/*     */     }
/* 137 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.serverclient.DBConn
 * JD-Core Version:    0.6.2
 */