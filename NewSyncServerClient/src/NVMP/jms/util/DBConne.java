/*     */ package NVMP.jms.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DBConne
/*     */ {
/*     */   private static final String DBDRIVER = "com.mysql.jdbc.Driver";
/*     */   private static DBConne dbconne;
/*     */   private static Connection conn;
/*     */   private static String url;
/*     */ 
/*     */   private DBConne()
/*     */   {
/*     */     try
/*     */     {
/*  30 */       if (url == null) {
/*  31 */         System.out.println("数据库路径尚未初始化");
/*     */       }
/*     */ 
/*  34 */       Class.forName("com.mysql.jdbc.Driver");
/*     */ 
/*  40 */       conn = 
/*  41 */         DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + 
/*  42 */         url + 
/*  43 */         "?user=admin&password=111&characterEncoding=gbk&autoReconnect=true");
/*     */     }
/*     */     catch (Exception e) {
/*  46 */       System.out.println("数据库初始化失败..");
/*  47 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static DBConne getDBConne() {
/*  52 */     if (dbconne == null)
/*  53 */       dbconne = new DBConne();
/*  54 */     return dbconne;
/*     */   }
/*     */ 
/*     */   public static void init(String dir)
/*     */   {
/*  59 */     url = dir;
/*  60 */     dbconne = new DBConne();
/*     */   }
/*     */ 
/*     */   public void close() {
/*     */     try {
/*  65 */       if (conn != null)
/*  66 */         conn.close();
/*     */     }
/*     */     catch (SQLException e) {
/*  69 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int executeUpdate(String aim)
/*     */     throws SQLException
/*     */   {
/* 112 */     Statement st = conn.createStatement();
/* 113 */     int num = st.executeUpdate(aim);
/* 114 */     st.close();
/*     */ 
/* 124 */     return num;
/*     */   }
/*     */ 
/*     */   public List<HashMap<String, String>> executeQuery(String aim)
/*     */     throws SQLException
/*     */   {
/* 130 */     LinkedList lnkd = new LinkedList();
/* 131 */     Statement st = conn.createStatement();
/* 132 */     ResultSet resultSet = st.executeQuery(aim);
/* 133 */     ResultSetMetaData remd = resultSet.getMetaData();
/* 134 */     while (resultSet.next()) {
/* 135 */       HashMap hm = new HashMap();
/* 136 */       for (int i = 1; i <= remd.getColumnCount(); i++) {
/* 137 */         hm.put(remd.getColumnName(i), resultSet.getString(i));
/*     */       }
/* 139 */       lnkd.add(hm);
/*     */     }
/* 141 */     resultSet.close();
/* 142 */     st.close();
/* 143 */     return lnkd;
/*     */   }
/*     */ 
/*     */   public boolean IsExistsTable(String tableName)
/*     */   {
/* 157 */     String sqlStr = "select COUNT(*) as AC FROM  information_schema.tables where table_schema  = '" + 
/* 158 */       url + "' and table_name  = '" + tableName + "'";
/*     */     try
/*     */     {
/* 161 */       List l = executeQuery(sqlStr);
/* 162 */       if (l.size() > 0) {
/* 163 */         HashMap h = (HashMap)l.get(0);
/* 164 */         if (Integer.parseInt(((String)h.get("AC")).toString()) > 0)
/* 165 */           return true;
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/* 169 */       e.printStackTrace();
/*     */     }
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   public void restart()
/*     */   {
/* 179 */     String sqlStr = "select table_name FROM  information_schema.tables where table_schema  = '" + 
/* 180 */       url + "' ";
/*     */     try
/*     */     {
/* 183 */       List<HashMap<String, String>> l = executeQuery(sqlStr);
/* 184 */       for (HashMap hp : l) {
/* 185 */         String name = ((String)hp.get("TABLE_NAME")).toString();
/* 186 */         sqlStr = "DELETE from " + name;
/* 187 */         executeUpdate(sqlStr);
/*     */       }
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 192 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 201 */     init("jms_client");
/* 202 */     getDBConne().restart();
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.util.DBConne
 * JD-Core Version:    0.6.2
 */