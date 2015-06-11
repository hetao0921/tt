/*     */ package NVMP.service.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ServerIPConfigs
/*     */ {
/*     */   private List<ServerIPConfig> list;
/*     */ 
/*     */   public List<ServerIPConfig> getList()
/*     */   {
/*  10 */     return this.list;
/*     */   }
/*     */ 
/*     */   public void setList(List<ServerIPConfig> list) {
/*  14 */     this.list = list;
/*     */   }
/*     */ 
/*     */   public ServerIPConfig GetCenter()
/*     */   {
/*  22 */     for (ServerIPConfig sic : this.list) {
/*  23 */       if ((sic.getDeviceLevel().equals("本级服务")) && (sic.getDeviceType().equals("FXH3120")))
/*  24 */         return sic;
/*     */     }
/*  26 */     ServerIPConfig sic = new ServerIPConfig();
/*  27 */     sic.setDeviceIp("");
/*  28 */     sic.setDeviceLevel("本级服务");
/*  29 */     sic.setDeviceType("FXH3120");
/*  30 */     sic.setDeviceId("");
/*  31 */     sic.setDeviceName("集中管理服务（中心管理服务）");
/*  32 */     sic.setDevicePort(1900);
/*  33 */     return sic;
/*     */   }
/*     */ 
/*     */   public ServerIPConfig GetParentCenter()
/*     */   {
/*  41 */     for (ServerIPConfig sic : this.list) {
/*  42 */       if ((sic.getDeviceLevel().equals("remote")) && (sic.getDeviceType().equals("FXH3120")))
/*  43 */         return sic;
/*     */     }
/*  45 */     ServerIPConfig sic = new ServerIPConfig();
/*  46 */     sic.setDeviceIp("");
/*  47 */     sic.setDeviceLevel("remote");
/*  48 */     sic.setDeviceType("FXH3120");
/*  49 */     sic.setDeviceId("");
/*  50 */     sic.setDeviceName("集中管理服务（中心管理服务）");
/*  51 */     sic.setDevicePort(1900);
/*  52 */     return sic;
/*     */   }
/*     */ 
/*     */   public ServerIPConfig GetParentSync()
/*     */   {
/*  60 */     for (ServerIPConfig sic : this.list) {
/*  61 */       if ((sic.getDeviceLevel().equals("remote")) && (sic.getDeviceType().equals("FXH3360")))
/*  62 */         return sic;
/*     */     }
/*  64 */     ServerIPConfig sic = new ServerIPConfig();
/*  65 */     sic.setDeviceIp("");
/*  66 */     sic.setDeviceLevel("remote");
/*  67 */     sic.setDeviceType("FXH3360");
/*  68 */     sic.setDeviceId("");
/*  69 */     sic.setDeviceName("集中配置管理服务（数据同步服务）");
/*  70 */     sic.setDevicePort(5050);
/*  71 */     return sic;
/*     */   }
/*     */ 
/*     */   public ServerIPConfig GetSync()
/*     */   {
/*  79 */     for (ServerIPConfig sic : this.list) {
/*  80 */       if ((sic.getDeviceLevel().equals("本级服务")) && (sic.getDeviceType().equals("FXH3360")))
/*  81 */         return sic;
/*     */     }
/*  83 */     ServerIPConfig sic = new ServerIPConfig();
/*  84 */     sic.setDeviceIp("");
/*  85 */     sic.setDeviceLevel("本级服务");
/*  86 */     sic.setDeviceType("FXH3360");
/*  87 */     sic.setDeviceId("");
/*  88 */     sic.setDeviceName("集中配置管理服务（数据同步服务）");
/*  89 */     sic.setDevicePort(5050);
/*  90 */     return sic;
/*     */   }
/*     */ 
/*     */   public ServerIPConfig GetServer8060()
/*     */   {
/*  98 */     for (ServerIPConfig sic : this.list) {
/*  99 */       if ((sic.getDeviceLevel().equals("本级服务")) && (sic.getDeviceType().equals("FXH8060")))
/* 100 */         return sic;
/*     */     }
/* 102 */     ServerIPConfig sic = new ServerIPConfig();
/* 103 */     sic.setDeviceIp("");
/* 104 */     sic.setDeviceLevel("本级服务");
/* 105 */     sic.setDeviceType("FXH8060");
/* 106 */     sic.setDeviceId("");
/* 107 */     sic.setDeviceName("指挥调度服务器");
/* 108 */     sic.setDevicePort(0);
/* 109 */     return sic;
/*     */   }
/*     */ 
/*     */   public List<ServerIPConfig> GetStream()
/*     */   {
/* 117 */     List ll = new ArrayList();
/* 118 */     for (ServerIPConfig sic : this.list) {
/* 119 */       if ((sic.getDeviceLevel().equals("本级服务")) && (sic.getDeviceType().equals("FXH3320")))
/* 120 */         ll.add(sic);
/*     */     }
/* 122 */     int temp = 8 - ll.size();
/* 123 */     for (int i = 0; i < temp; i++) {
/* 124 */       ServerIPConfig sic = new ServerIPConfig();
/* 125 */       sic.setDeviceIp("");
/* 126 */       sic.setDeviceLevel("本级服务");
/* 127 */       sic.setDeviceType("FXH3320");
/* 128 */       sic.setDeviceId("");
/* 129 */       sic.setDeviceName("音视频交换（流媒体服务）");
/* 130 */       sic.setDevicePort(5050);
/* 131 */       ll.add(sic);
/*     */     }
/* 133 */     return ll;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.service.impl.ServerIPConfigs
 * JD-Core Version:    0.6.2
 */