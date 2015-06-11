/*     */ package NVMP.serverclient;
/*     */ 
/*     */ import NVMP.jms.impl.ClientRun;
/*     */ import NVMP.jms.proxy.DBSynchronization;
/*     */ import NVMP.jms.util.Encoding;
/*     */ import NVMP.jms.util.JNDIUtil;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ public class ServerClient
/*     */ {
/*     */   private String ClientId;
/*     */   private boolean activeflag;
/*     */   private boolean initflag;
/*     */   private DBSynchronization dbs;
/*     */   private ClientRun cr;
/*     */   private ConcurrentHashMap<String, String> centerVersions;
/*     */   private ConcurrentHashMap<String, String> requestVersions;
/*     */ 
/*     */   public void putVersions(String centerid, String versions)
/*     */   {
/*  38 */     if ((centerid != null) && (versions != null))
/*  39 */       this.centerVersions.put(centerid, versions);
/*     */   }
/*     */ 
/*     */   public Map<String, String> getVersionList()
/*     */   {
/*  49 */     return this.centerVersions;
/*     */   }
/*     */ 
/*     */   public void putRequest(String centerid, String versions)
/*     */   {
/*  57 */     if ((centerid != null) && (versions != null))
/*  58 */       this.requestVersions.put(centerid, versions);
/*     */   }
/*     */ 
/*     */   public boolean isRequest(String centerid, String versions)
/*     */   {
/*  66 */     if ((centerid == null) || (versions == null)) {
/*  67 */       return false;
/*     */     }
/*  69 */     String s = (String)this.requestVersions.get(centerid);
/*  70 */     if (s == null)
/*  71 */       return false;
/*  72 */     if (s.equals(versions))
/*  73 */       return true;
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   public void removeRequest(String centerid)
/*     */   {
/*  83 */     if (centerid != null)
/*  84 */       this.requestVersions.remove(centerid);
/*     */   }
/*     */ 
/*     */   public ServerClient(String sessionid)
/*     */   {
/*  90 */     this.ClientId = sessionid;
/*  91 */     this.activeflag = false;
/*  92 */     this.initflag = false;
/*     */ 
/*  94 */     this.centerVersions = new ConcurrentHashMap();
/*     */ 
/*  97 */     this.requestVersions = new ConcurrentHashMap();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */     throws JMSException
/*     */   {
/* 104 */     this.dbs = new DBSynchronization();
/* 105 */     ServerClientHandler sch = new ServerClientHandler();
/* 106 */     this.dbs.setE(sch);
/* 107 */     sch.setServerClient(this);
/* 108 */     this.cr = new ClientRun(this.ClientId);
/* 109 */     this.cr.registerProxy(this.dbs);
/* 110 */     this.activeflag = true;
/*     */   }
/*     */ 
/*     */   public void connect() throws Exception
/*     */   {
/* 115 */     if (!this.activeflag) {
/* 116 */       JNDIUtil.again();
/* 117 */       init();
/*     */     }
/* 119 */     if (!this.activeflag)
/* 120 */       throw new Exception("error");
/*     */   }
/*     */ 
/*     */   public boolean syncUpLoad(String sessionid, String centerid, String ip, String versions, String uuid, String xml)
/*     */   {
/*     */     try
/*     */     {
	            System.out.println("给上级发送本级数据：centerid"+centerid+" versions "+versions);
/* 128 */       connect();
/* 129 */       byte[] data = Encoding.StringToByte(xml);
/* 130 */       this.dbs.ClientServerQueueSend(sessionid, centerid, ip, uuid, versions, 
/* 131 */         data);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 135 */       this.activeflag = false;
/* 136 */       e.printStackTrace();
/* 137 */       return false;
/*     */     }
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean syncRequestVersion(String sessionid, String centerid)
/*     */   {
/*     */     try {
/* 145 */       connect();
/* 146 */       this.dbs.ClientQueueNowVerson(sessionid, centerid, null);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 150 */       this.activeflag = false;
/* 151 */       e.printStackTrace();
/* 152 */       return false;
/*     */     }
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean syncDownLoad(String sessionid, String ip, String uuid, String versions)
/*     */   {
/*     */     try
/*     */     {
/* 163 */       connect();
/* 164 */       this.dbs.ClientQueueGetData(sessionid, this.ClientId, ip, uuid, 
/* 165 */         versions, null);
/*     */     }
/*     */     catch (Exception e) {
/* 168 */       this.activeflag = false;
/* 169 */       e.printStackTrace();
/* 170 */       return false;
/*     */     }
/* 172 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.serverclient.ServerClient
 * JD-Core Version:    0.6.2
 */