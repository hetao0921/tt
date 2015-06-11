/*     */ package NVMP.jms.util;
/*     */ 
/*     */ import NVMP.service.impl.GetValue;
/*     */ import NVMP.service.impl.ServerIPConfig;
/*     */ import NVMP.service.impl.ServerIPConfigs;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueConnection;
/*     */ import javax.jms.QueueConnectionFactory;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicConnection;
/*     */ import javax.jms.TopicConnectionFactory;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.input.SAXBuilder;
/*     */ import org.misc.log.LogUtil;
/*     */ 
/*     */ public class JNDIUtil
/*     */ {
/*  31 */   static InitialContext ctx = null;
/*  32 */   static TopicConnection topConn = null;
/*  33 */   static QueueConnection queueConn = null;
/*  34 */   static ServerIPConfig sic = null;
/*     */ 
/*  36 */   static { init(); }
/*     */ 
/*     */ 
/*     */   public static void init()
/*     */   {
/*     */     try
/*     */     {
/*  45 */       Properties props = new Properties();
/*     */ 
/*  55 */       System.out.println("=======================");
/*     */ 
/*  59 */       props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
/*     */ 
/*  61 */       props.put("connectionFactoryNames", "connectionFactory, queueConnectionFactory, topicConnectionFactry ,connF");
/*  62 */       props.put("queue.clientQueue", "nvmp.clientQueue");
/*  63 */       props.put("queue.serverQueue", "nvmp.serverQueue");
/*     */ 
/*  84 */       if (sic == null) {
/*  85 */         //sic = getServerIPConfig();
/*  86 */         LogUtil.info("sync client:hahaha,we had got message!!!");
/*     */       }
/*  88 */       //LogUtil.info("sync client:hahaha,ip is " + sic.getDeviceIp() + ",port is " + sic.getDevicePort());

/*  89 */       String url = "tcp://192.168.1.55:61616";
/*     */ 
/*  91 */       props.put("java.naming.provider.url", url);
/*     */ 
/*  94 */       ctx = new InitialContext(props);
/*     */     } catch (NamingException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static TopicConnectionFactory lookupTopicConnectionFactory()
/*     */   {
/* 106 */     TopicConnectionFactory connf = null;
/*     */     try {
/* 108 */       connf = (TopicConnectionFactory)ctx.lookup("connectionFactory");
/*     */     } catch (NamingException e) {
/* 110 */       e.printStackTrace();
/*     */     }
/* 112 */     return connf;
/*     */   }
/*     */ 
/*     */   public static QueueConnectionFactory lookupQueueConnectionFactory() {
/* 116 */     QueueConnectionFactory connf = null;
/*     */     try {
/* 118 */       connf = (QueueConnectionFactory)ctx.lookup("connF");
/*     */     } catch (NamingException e) {
/* 120 */       e.printStackTrace();
/*     */     }
/* 122 */     return connf;
/*     */   }
/*     */ 
/*     */   public static TopicConnection getTopicConnection()
/*     */   {
/* 127 */     if (topConn == null) {
/*     */       try {
/* 129 */         topConn = lookupTopicConnectionFactory()
/* 130 */           .createTopicConnection();
/*     */       } catch (JMSException e) {
/* 132 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 135 */     return topConn;
/*     */   }
/*     */ 
/*     */   public static QueueConnection getQueueConnection()
/*     */   {
/* 140 */     if (queueConn == null) {
/*     */       try {
/* 142 */         queueConn = lookupQueueConnectionFactory()
/* 143 */           .createQueueConnection();
/*     */       } catch (JMSException e) {
/* 145 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 148 */     return queueConn;
/*     */   }
/*     */ 
/*     */   public static QueueConnection crateQueueConnection() {
/*     */     try {
/* 153 */       return lookupQueueConnectionFactory().createQueueConnection();
/*     */     } catch (JMSException e) {
/* 155 */       e.printStackTrace();
/*     */     }
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */   public static Topic lookupTopic(String topicName) {
/* 161 */     Topic topic = null;
/*     */     try {
/* 163 */       topic = (Topic)ctx.lookup(topicName);
/*     */     } catch (NamingException e) {
/* 165 */       e.printStackTrace();
/*     */     }
/* 167 */     return topic;
/*     */   }
/*     */ 
/*     */   public static Queue lookupQueue(String queueName) {
/*     */     try {
/* 172 */       return (Queue)ctx.lookup(queueName);
/*     */     } catch (NamingException e) {
/* 174 */       e.printStackTrace();
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   public static void again() {
/* 180 */     topConn = null;
/* 181 */     queueConn = null;
/* 182 */     init();
/*     */   }
/*     */ 
/*     */   public static void closeSession(Session session)
/*     */   {
/*     */     try {
/* 188 */       session.close();
/* 189 */       session = null;
/*     */     } catch (JMSException e) {
/* 191 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String getCenterIP()
/*     */   {
/* 250 */     String path = "";
/* 251 */     String ip = "";
/* 252 */     if (System.getProperty("os.name").equals("Linux"))
/*     */     {
/* 254 */       path = "/etc/fxconf/AppService/AppService.conf";
/*     */     }
/* 256 */     else path = "D:\\fxconf\\AppService\\AppService.conf";
/*     */ 
/* 258 */     File f = new File(path);
/* 259 */     if (f.exists()) {
/*     */       try {
/* 261 */         SAXBuilder builder = new SAXBuilder();
/* 262 */         Document doc = builder.build(new FileInputStream(new File(path)));
/* 263 */         Element root = doc.getRootElement();
/* 264 */         Element appE = root.getChild("AppServer");
/* 265 */         ip = appE.getAttributeValue("IP");
/*     */       } catch (Exception e) {
/* 267 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 270 */     return ip;
/*     */   }
/*     */ 
/*     */   private static ServerIPConfig getSync()
/*     */   {
/* 277 */     ServerIPConfig sip = null;
/* 278 */     String ip = getCenterIP();
/* 279 */     GetValue gv = new GetValue("http://" + ip + ":8080/webservice/services/web");
/* 280 */     if (ip.equals(""))
/* 281 */       return sip;
/* 282 */     LogUtil.info("sync client:we are getting message from center:" + ip);
/* 283 */     ServerIPConfigs sics = gv.GetAllInfo();
/* 284 */     if (sics == null)
/* 285 */       return sip;
/* 286 */     if (sics.getList() == null)
/* 287 */       return sip;
/* 288 */     for (ServerIPConfig s : sics.getList()) {
/* 289 */       if ((s.getDeviceType().equals("FXH3360")) && (s.getDeviceLevel().equals("remote"))) {
/* 290 */         sip = s;
/*     */       }
/*     */     }
/* 293 */     return sip;
/*     */   }
/*     */ 
/*     */   public static ServerIPConfig getServerIPConfig()
/*     */   {
/* 301 */     ServerIPConfig sic = null;
/* 302 */     while (sic == null) {
/* 303 */       LogUtil.info("sync client:searching message in while......");
/* 304 */       sic = getSync();
/* 305 */       if (sic != null) break;
/*     */       try {
/* 307 */         LogUtil.info("sync client:we haven't any message,wait 5 second ...");
/* 308 */         Thread.sleep(5000L);
/*     */       }
/*     */       catch (Exception e) {
/* 311 */         e.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 316 */     return sic;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.util.JNDIUtil
 * JD-Core Version:    0.6.2
 */