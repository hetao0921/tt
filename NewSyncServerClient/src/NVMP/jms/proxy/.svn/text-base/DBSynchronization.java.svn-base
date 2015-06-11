/*     */ package NVMP.jms.proxy;
/*     */ 
/*     */ import NVMP.jms.ctrl.MessageSendHandler;
/*     */ import NVMP.jms.impl.ProxyHandler;
/*     */ import NVMP.jms.impl.Servering;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ public class DBSynchronization
/*     */   implements ProxyHandler
/*     */ {
/*     */   private MessageSendHandler msh;
/*     */   Event e;
/*     */ 
/*     */   public void SetSendHandler(MessageSendHandler ms)
/*     */   {
/*  17 */     this.msh = ms;
/*     */   }
/*     */ 
/*     */   public void setE(Event e)
/*     */   {
/*  64 */     this.e = e;
/*     */   }
/*     */ 
/*     */   public void ReturnEvent(HashMap<String, Object> Params, String url, byte[] data)
/*     */   {
/*  73 */     System.out.println("url " + url);
/*     */ 
/*  75 */     if (url.equals("DBSynchronization.ServerUpLoadOver")) {
/*  76 */       String uuid = Params.get("uuid").toString();
/*  77 */       Integer verson = Integer.valueOf(Params.get("verson").toString());
/*  78 */       if (this.e != null)
/*  79 */         this.e.OnServerUpLoadOverEvent(uuid, verson, data);
/*     */     }
/*  81 */     else if (url.equals("DBSynchronization.ServerDownLoadOver"))
/*     */     {
/*  83 */       Integer verson = null;
/*  84 */       String uuid = Params.get("uuid").toString();
/*  85 */       String centerid = Params.get("centerid").toString();
/*     */       try {
/*  87 */         verson = Integer.valueOf(Integer.parseInt(Params.get("verson").toString()));
/*     */       } catch (Exception e) {
/*  89 */         e.printStackTrace();
/*     */       }
/*  91 */       if (this.e != null)
/*  92 */         this.e.OnServerDownLoadOverEvent(verson, centerid, uuid, data);
/*     */     }
/*  94 */     else if (url.equals("DBSynchronization.ServerQueueSendNowVerson")) {
/*  95 */       String versions = Params.get("versions").toString();
/*  96 */       String sessionid = Params.get("sessionid").toString();
/*  97 */       if (this.e != null)
/*  98 */         this.e.OnServerQueueSendNowVersonEvent(versions, sessionid, data);
/*     */     }
/* 100 */     else if (!url.equals("DBSynchronization.SendHeard"))
/*     */     {
/* 106 */       if (url.equals("DBSynchronization.ClientQueueSend"))
/*     */       {
/* 108 */         String centerid = Params.get("centerid").toString();
/* 109 */         String sessionid = Params.get("sessionid").toString();
/* 110 */         String ip = Params.get("ip").toString();
/* 111 */         String uuid = Params.get("uuid").toString();
/* 112 */         if (this.e != null) {
/* 113 */           this.e.OnClientQueueSendEvent(centerid, sessionid, ip, uuid, data);
/*     */         }
/*     */       }
/* 116 */       else if (url.equals("DBSynchronization.ClientQueueNowVerson"))
/*     */       {
/* 118 */         String sessionid = Params.get("sessionid").toString();
/* 119 */         String centerid = Params.get("centerid").toString();
/* 120 */         if (this.e != null) {
/* 121 */           this.e.OnClientQueueNowVersonEvent(sessionid, centerid, data);
/*     */         }
/*     */       }
/* 124 */       else if (url.equals("DBSynchronization.ClientQueueGetData")) {
/* 125 */         String sessionid = Params.get("sessionid").toString();
/* 126 */         String centerid = Params.get("centerid").toString();
/* 127 */         String ip = Params.get("ip").toString();
/* 128 */         String uuid = Params.get("uuid").toString();
/* 129 */         String versions = Params.get("versions").toString();
/*     */ 
/* 131 */         if (this.e != null)
/* 132 */           this.e.OnClientQueueGetDataEvent(sessionid, centerid, ip, uuid, 
/* 133 */             versions, data);
/*     */       }
/* 135 */       else if (url.equals("DBSynchronization.ClientServerQueueSend")) {
/* 136 */         String sessionid = Params.get("sessionid").toString();
/* 137 */         String centerid = Params.get("centerid").toString();
/* 138 */         String ip = Params.get("ip").toString();
/* 139 */         String uuid = Params.get("uuid").toString();
/* 140 */         String versions = Params.get("versions").toString();
/* 141 */         if (this.e != null)
/* 142 */           this.e.OnClientServerQueueSendEvent(sessionid, centerid, ip, uuid, 
/* 143 */             versions, data);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Servering
/*     */   public void ServerUpLoadOver(String reciveId, Integer verson, String uuid, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 156 */     String url = "DBSynchronization.ServerUpLoadOver";
/*     */ 
/* 158 */     HashMap hp = new HashMap();
/* 159 */     hp.put("verson", verson);
/* 160 */     hp.put("uuid", uuid);
/*     */ 
/* 162 */     this.msh.QueueSend(reciveId, url, hp, data);
/*     */   }
/*     */ 
/*     */   @Servering
/*     */   public void ServerDownLoadOver(String reciveId, Integer verson, String centerid, String uuid, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 173 */     String url = "DBSynchronization.ServerDownLoadOver";
/*     */ 
/* 175 */     HashMap hp = new HashMap();
/* 176 */     hp.put("verson", verson);
/* 177 */     hp.put("centerid", centerid);
/* 178 */     hp.put("uuid", uuid);
/*     */ 
/* 180 */     this.msh.QueueSend(reciveId, url, hp, data);
/*     */   }
/*     */ 
/*     */   @Servering
/*     */   public void ServerQueueSendNowVerson(String reciveId, String sessionid, String versions, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 191 */     String url = "DBSynchronization.ServerQueueSendNowVerson";
/*     */ 
/* 193 */     HashMap hp = new HashMap();
/* 194 */     hp.put("sessionid", sessionid);
/* 195 */     hp.put("versions", versions);
/*     */ 
/* 197 */     this.msh.QueueSend(reciveId, url, hp, data);
/*     */   }
/*     */ 
/*     */   public void ClientQueueSend(String sessionid, String centerid, String ip, String uuid, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 209 */     String url = "DBSynchronization.ClientQueueSend";
/*     */ 
/* 211 */     HashMap hp = new HashMap();
/* 212 */     hp.put("centerid", centerid);
/* 213 */     hp.put("sessionid", sessionid);
/* 214 */     hp.put("ip", ip);
/* 215 */     hp.put("uuid", uuid);
/* 216 */     this.msh.QueueSend(null, url, hp, data);
/*     */   }
/*     */ 
/*     */   public void SendHeard()
/*     */     throws JMSException
/*     */   {
/* 225 */     String url = "DBSynchronization.SendHeard";
/*     */ 
/* 227 */     HashMap hp = new HashMap();
/*     */ 
/* 229 */     this.msh.QueueSend(null, url, hp, null);
/*     */   }
/*     */ 
/*     */   public void ClientQueueNowVerson(String sessionid, String centerid, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 240 */     String url = "DBSynchronization.ClientQueueNowVerson";
/*     */ 
/* 242 */     HashMap hp = new HashMap();
/* 243 */     hp.put("sessionid", sessionid);
/* 244 */     hp.put("centerid", centerid);
/* 245 */     this.msh.QueueSend(null, url, hp, data);
/*     */   }
/*     */ 
/*     */   public void ClientQueueGetData(String sessionid, String centerid, String ip, String uuid, String versions, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 256 */     String url = "DBSynchronization.ClientQueueGetData";
/*     */ 
/* 258 */     HashMap hp = new HashMap();
/* 259 */     hp.put("sessionid", sessionid);
/* 260 */     hp.put("centerid", centerid);
/* 261 */     hp.put("ip", ip);
/* 262 */     hp.put("uuid", uuid);
/* 263 */     hp.put("versions", versions);
/* 264 */     this.msh.QueueSend(null, url, hp, data);
/*     */   }
/*     */ 
/*     */   public void ClientServerQueueSend(String sessionid, String centerid, String ip, String uuid, String versions, byte[] data)
/*     */     throws JMSException
/*     */   {
/* 275 */     String url = "DBSynchronization.ClientServerQueueSend";
/*     */ 
/* 277 */     HashMap hp = new HashMap();
/* 278 */     hp.put("centerid", centerid);
/* 279 */     hp.put("sessionid", sessionid);
/* 280 */     hp.put("ip", ip);
/* 281 */     hp.put("uuid", uuid);
/* 282 */     hp.put("versions", versions);
/* 283 */     this.msh.QueueSend(null, url, hp, data);
/*     */   }
/*     */ 
/*     */   public static class Event
/*     */   {
/*     */     public void OnServerUpLoadOverEvent(String uuid, Integer verson, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnServerDownLoadOverEvent(Integer verson, String centerid, String uuid, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnServerQueueSendNowVersonEvent(String versions, String sessionid, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnClientQueueSendEvent(String centerid, String sessionid, String ip, String uuid, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnClientQueueNowVersonEvent(String sessionid, String centerid, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnClientQueueGetDataEvent(String sessionid, String centerid, String ip, String uuid, String versions, byte[] data)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void OnClientServerQueueSendEvent(String sessionid, String centerid, String ip, String uuid, String versions, byte[] data)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.proxy.DBSynchronization
 * JD-Core Version:    0.6.2
 */