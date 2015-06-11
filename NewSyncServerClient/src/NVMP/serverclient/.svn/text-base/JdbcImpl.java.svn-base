/*     */ package NVMP.serverclient;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ public class JdbcImpl
/*     */ {
/*     */   private static JdbcImpl jdbc;
/*     */ 
/*     */   public static JdbcImpl getJdbcImpl()
/*     */   {
/*  12 */     if (jdbc == null)
/*  13 */       jdbc = new JdbcImpl();
/*  14 */     return jdbc;
/*     */   }
/*     */ 
/*     */   public String getVersion(String centerid)
/*     */   {
/*  23 */     DBConn db = DBConn.getDBConn();
/*  24 */     String sql = "select * from data_version where centerid='" + centerid + "'";
/*     */ 
/*  26 */     List list = db.executeQuery(sql);
/*     */ 
/*  28 */     if (list.size() == 0) {
/*  29 */       return null;
/*     */     }
/*  31 */     HashMap map = (HashMap)list.get(0);
/*  32 */     String ver = (String)map.get("version");
/*  33 */     return ver;
/*     */   }
/*     */ 
/*     */   public List<HashMap<String, String>> getAllVersion()
/*     */   {
/*  43 */     DBConn db = DBConn.getDBConn();
/*  44 */     String sql = "select * from data_version";
/*  45 */     return db.executeQuery(sql);
/*     */   }
/*     */ 
/*     */   public void updateVersion(String centerid, int version)
/*     */   {
/*  54 */     DBConn db = DBConn.getDBConn();
/*  55 */     String sql = "";
/*  56 */     if (getVersion(centerid) == null)
/*  57 */       sql = "insert into data_version(centerid,version) values('" + centerid + "'," + version + ")";
/*     */     else
/*  59 */       sql = "update data_version set version=" + version + " where centerid='" + centerid + "'";
/*     */     try {
/*  61 */       db.updateInfo(sql);
/*     */     }
/*     */     catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void insertSource(String centerid, String uuid, String fileaddress, Integer version) {
/*  69 */     DBConn db = DBConn.getDBConn();
/*  70 */     System.out.println("fileaddress:" + fileaddress);
/*  71 */     String sql = "insert into data_source(centerid,uuid,fileaddress,version) values('" + 
/*  72 */       centerid + "','" + uuid + "','" + fileaddress + "'," + version + ")";
/*     */     try {
/*  74 */       db.updateInfo(sql);
/*     */     }
/*     */     catch (Exception e) {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] getSource(String centerid, Integer version) {
/*  82 */     DBConn db = DBConn.getDBConn();
/*  83 */     String sql = "select * from data_source where centerid='" + centerid + "' and version=" + version;
/*     */ 
/*  85 */     String[] ss = (String[])null;
/*  86 */     return ss;
/*     */   }
/*     */ 
/*     */   public void insertOperateRecord(String uuid, String sessionid, String operate, String centerid)
/*     */   {
/*  91 */     DBConn db = DBConn.getDBConn();
/*  92 */     System.out.println("插入data_operate_record记录。。。");
/*  93 */     String sql = "insert into data_operate_record(uuid,sessionid,operate,centerid) values('" + 
/*  94 */       uuid + "','" + sessionid + "','" + 
/*  95 */       operate + "','" + centerid + "')";
/*     */     try {
/*  97 */       db.updateInfo(sql);
/*     */     }
/*     */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startInit()
/*     */   {
/* 106 */     DBConn db = DBConn.getDBConn();
/* 107 */     System.out.println("初始化data_operate_record记录。。。");
/* 108 */     String sql = "update data_operate_record set operate = 0";
/*     */     try {
/* 110 */       db.updateInfo(sql);
/*     */     }
/*     */     catch (Exception e) {
/* 113 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void insertSyncVersion(String centerid, int version, String versions)
/*     */   {
/* 121 */     DBConn db = DBConn.getDBConn();
/* 122 */     String sql = "insert into sync_data_version (centerid,version,realversion) values ('" + centerid + "'," + "version" + "," + "versions)";
/*     */     try {
/* 124 */       db.updateInfo(sql);
/*     */     }
/*     */     catch (Exception e) {
/* 127 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.serverclient.JdbcImpl
 * JD-Core Version:    0.6.2
 */