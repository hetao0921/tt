/*     */ package NVMP.serverclient;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.UUID;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   private ServerClient sc;
/*     */   private String sessionid;
/*     */ 
/*     */   public Main(String sessionid)
/*     */   {
/*  28 */     this.sc = new ServerClient(sessionid);
/*  29 */     this.sessionid = sessionid;
/*     */   }
/*     */ 
/*     */   private List<HashMap<String, String>> getVersion()
/*     */   {
/*  40 */     String sql = "select * from data_version where id not in (SELECT a.id FROM data_version  a,   sync_data_send b  where a.centerid = b.centerid and a.version = b.version)";
/*     */ 
/*  44 */     DBConn db = DBConn.getDBConn();
/*  45 */     List list = db.executeQuery(sql);
/*  46 */     System.out.println("list size: " + list.size());
/*  47 */     return list;
/*     */   }
/*     */ 
/*     */   private Object c(Object a)
/*     */   {
/*  57 */     if (a == null) {
/*  58 */       return null;
/*     */     }
/*  60 */     if (a.getClass().getSimpleName().equals("String")) {
/*  61 */       return "'" + a + "'";
/*     */     }
/*  63 */     return a;
/*     */   }
/*     */ 
/*     */   private String getXml(String address)
/*     */   {
/*  69 */     String xml = null;
/*     */     try {
/*  71 */       RandomAccessFile raf = new RandomAccessFile(address, "r");
/*  72 */       raf.seek(0L);
/*  73 */       byte[] b = new byte[(int)raf.length()];
/*  74 */       raf.read(b);
/*  75 */       xml = new String(b, "utf-8");
/*     */ 
/*  77 */       raf.close();
/*     */     } catch (Exception e) {
/*  79 */       e.printStackTrace();
/*     */     }
/*  81 */     return xml;
/*     */   }
/*     */ 
/*     */   private boolean insertDataSend(String centerid, int version) {
/*  85 */     String sql1 = String.format(
/*  86 */       "delete from sync_data_send where centerid=%s", new Object[] { c(centerid) });
/*  87 */     String sql2 = String.format(
/*  88 */       "insert into sync_data_send(centerid,version) values(%s,%s)", new Object[] { 
/*  89 */       c(centerid), c(Integer.valueOf(version)) });
/*  90 */     String[] sqls = { sql1, sql2 };
/*  91 */     DBConn db = DBConn.getDBConn();
/*  92 */     return db.updateInfo(sqls);
/*     */   }
/*     */ 
/*     */   public void Run()
/*     */   {
/* 100 */     DBConn db = DBConn.getDBConn();
/* 101 */     String sql = null;
/*     */    System.out.println("开始。。。。");
/* 103 */    List<HashMap<String, String>>  list = getVersion();
/*     */     HashMap ma;
              System.out.println("开始2。。。。"+list);
/* 104 */     for (HashMap map : list)
/*     */     {
	           System.out.println("开始21。。。。"+map);
/* 106 */       sql = String.format(
/* 107 */         "select * from data_source where centerid=%s and version=%s", new Object[] { 
/* 108 */         c(map.get("centerid")), 
/* 109 */         c(Integer.valueOf(Integer.parseInt((String)map.get("version")))) });
/* 110 */       List l = db.executeQuery(sql);
                System.out.println("开始3。。。。"+l.size());
/* 111 */       if (l.size() > 0) {
/* 112 */         ma = (HashMap)l.get(0);
/* 113 */         String centerid = (String)ma.get("centerid");
/* 114 */         String versions = (String)ma.get("version");
/* 115 */         String uuid = (String)ma.get("uuid");
/* 116 */         String address = (String)ma.get("fileaddress");
/*     */ 
/* 118 */         String xml = getXml(address);
/*     */         System.out.println("当前中心: centerid "+centerid+" versions: "+versions);
                  System.out.println(this.sc.isRequest(centerid, versions));
/* 120 */         if (!this.sc.isRequest(centerid, versions))
/*     */         {
/* 122 */           boolean flag = this.sc.syncUpLoad(this.sessionid, centerid, null, 
/* 123 */             versions, uuid, xml);
/*     */ 
/* 125 */           if (flag) {
/* 126 */             insertDataSend(centerid, Integer.parseInt(versions));
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 133 */     this.sc.syncRequestVersion(this.sessionid, this.sessionid);
/*     */ 
/* 136 */     Map<String, String> map = this.sc.getVersionList();
/*     */ 
/* 138 */     JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
/* 139 */     list = jdbc.getAllVersion();
/*     */ 
/* 141 */     for (Map.Entry it : map.entrySet()) {
/* 142 */       String centerid = (String)it.getKey();
/* 143 */       String version = (String)it.getValue();
/* 144 */       if ((!isExistLocal(centerid, version, list)) && 
/* 145 */         (!this.sc.isRequest(centerid, version))) {
/* 146 */         UUID uuid = UUID.randomUUID();
/*     */ 
/* 154 */         System.out
/* 155 */           .println("now download " + centerid + " | " + version);
/* 156 */         this.sc.syncDownLoad("SyncServerClinet", null, uuid.toString(), "{" + 
/* 157 */           centerid + ":" + version + "}");
/* 158 */         this.sc.putRequest(centerid, version);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isExistLocal(String centerid, String version, List<HashMap<String, String>> list)
/*     */   {
/* 187 */     for (Map map : list)
/*     */     {
/* 193 */       if (((String)map.get("centerid")).equals(centerid))
/*     */       {
/* 195 */         if (Integer.parseInt((String)map.get("version")) >= 
/* 195 */           Integer.parseInt(version)) {
/* 196 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   public void circle()
/*     */   {
/* 205 */     int n = 1;
/* 206 */     System.out.println("等待1分钟后开始............");
/*     */     while (true)
/*     */       try {
/* 209 */         Thread.sleep(n * 60 * 1000);
/* 210 */         System.out.println("开始同步给上级");
/* 211 */         Run();
/*     */       }
/*     */       catch (InterruptedException e) {
/* 214 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 225 */     String sessionid = null;
/*     */     String path;
/* 226 */     if (System.getProperty("os.name").equals("Linux"))
/* 227 */       path = "/etc/fxconf/config/Device.xml";
/*     */     else
/* 229 */       path = "C:\\Windows\\System32\\config\\Device.xml";
/* 230 */     SAXReader saxReader = new SAXReader();
/* 231 */     Document doc = null;
/*     */     try {
/* 233 */       doc = saxReader.read(new File(path));
/*     */     }
/*     */     catch (DocumentException e1) {
/* 236 */       e1.printStackTrace();
/* 237 */       System.out.println("big error 错误，失败，退出");
/* 238 */       System.exit(1);
/*     */     }
/*     */ 
/* 242 */     Iterator iter2 = doc.getRootElement().elementIterator();
/* 243 */     while (iter2.hasNext()) {
/* 244 */       Element e = (Element)iter2.next();
/* 245 */       if (e.attributeValue("Type").equals("001")) {
/* 246 */         sessionid = e.attributeValue("DeviceSN");
/* 247 */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 252 */     Main m = new Main(sessionid);
/* 253 */     m.circle();
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.serverclient.Main
 * JD-Core Version:    0.6.2
 */