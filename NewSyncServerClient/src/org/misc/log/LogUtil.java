/*     */ package org.misc.log;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.Properties;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.log4j.PropertyConfigurator;
/*     */ 
/*     */ public class LogUtil
/*     */ {
/*  31 */   private static Logger filelog = Logger.getLogger("File");
/*  32 */   private static Logger Consolelog = Logger.getLogger("Console");
/*  33 */   private static Logger Businesslog = Logger.getLogger("Console_Business");
/*  34 */   private static Logger ERRORlog = Logger.getLogger("ERRORlog");
/*  35 */   private static Logger VideoInfo = Logger.getLogger("VideoInfo");
/*  36 */   private static Logger SessionInfo = Logger.getLogger("SessionInfo");
/*  37 */   private static Logger DebugInfo = Logger.getLogger("DebugInfo");
/*  38 */   private static Logger DebugInfoRecive = Logger.getLogger("DebugInfoRecive");
/*  39 */   private static Logger DeviceManage = Logger.getLogger("DeviceManage");
/*     */ 
/*     */   static
/*     */   {
/*     */     String confPath;
/*  14 */     if (System.getProperty("os.name").equals("Linux"))
/*     */     {
/*  16 */       confPath = "/etc/fxconf/log/log4j.properties";
/*     */     }
/*     */     else
/*     */     {
/*  21 */       confPath = "d:\\fxconf\\log\\log4j.properties";
/*     */     }
/*     */     try
/*     */     {
/*  25 */       PropertyConfigurator.configure(confPath);
/*     */     } catch (Exception e) {
/*  27 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void DeviceManageInfo(Object message)
/*     */   {
/*  54 */     DeviceManage.debug(message);
/*     */   }
/*     */ 
/*     */   public static void ReciveDebugInfo(Object message)
/*     */   {
/*  59 */     DebugInfoRecive.debug(message);
/*     */   }
/*     */ 
/*     */   public static void DebugInfo(Object message) {
/*  63 */     DebugInfo.debug(message);
/*     */   }
/*     */ 
/*     */   public static void VideoInfo(Object message)
/*     */   {
/*  68 */     filelog.info(message);
/*  69 */     VideoInfo.info(message);
/*     */   }
/*     */   public static void SessionInfo(Object message) {
/*  72 */     SessionInfo.info(message);
/*     */   }
/*     */ 
/*     */   public static void BusinessDebug(Object message)
/*     */   {
/*  77 */     Businesslog.debug(message);
/*  78 */     filelog.debug(message);
/*     */   }
/*     */ 
/*     */   public static void BusinessInfo(Object message)
/*     */   {
/*  84 */     filelog.info(message);
/*  85 */     Businesslog.info(message);
/*     */   }
/*     */   public static void BusinessError(Object message) {
/*  88 */     filelog.error(message);
/*  89 */     Businesslog.error(message);
/*  90 */     ERRORlog.error(message);
/*     */   }
/*     */ 
/*     */   public static void debug(Object message)
/*     */   {
/*  96 */     filelog.debug(message);
/*  97 */     Consolelog.debug(message);
/*     */   }
/*     */ 
/*     */   public static void info(Object message)
/*     */   {
/* 102 */     filelog.info(message);
/* 103 */     Consolelog.info(message);
/*     */   }
/*     */ 
/*     */   public static void error(Object message)
/*     */   {
/* 108 */     filelog.error(message);
/* 109 */     Consolelog.error(message);
/* 110 */     ERRORlog.error(message);
/*     */   }
/*     */ 
/*     */   public static void warn(Object message) {
/* 114 */     filelog.warn(message);
/* 115 */     Consolelog.warn(message);
/*     */   }
/*     */ 
/*     */   public static void fatal(Object message) {
/* 119 */     filelog.fatal(message);
/* 120 */     Consolelog.fatal(message);
/*     */   }
/*     */ 
/*     */   public static boolean changeLevForFileLog(String message) {
/* 124 */     boolean b = false;
/*     */     try {
/* 126 */       Level le = Level.toLevel(message);
/* 127 */       filelog.setLevel(le);
/*     */     } catch (Exception e) {
/* 129 */       b = false;
/*     */     }
/* 131 */     return b;
/*     */   }
/*     */ 
/*     */   public static boolean changeLevForConsolelog(String message) {
/* 135 */     boolean b = false;
/*     */     try {
/* 137 */       Level le = Level.toLevel(message);
/* 138 */       Consolelog.setLevel(le);
/*     */     } catch (Exception e) {
/* 140 */       b = false;
/*     */     }
/* 142 */     return b;
/*     */   }
/*     */ 
/*     */   public static boolean save() {
/* 146 */     boolean b = false;
/*     */     try
/*     */     {
/* 149 */       Properties property = new Properties();
/* 150 */       property.load(new FileInputStream("E:\\log4j.properties"));
/*     */ 
/* 154 */       String str = property.getProperty("log4j.logger.Console");
/* 155 */       String[] ss = str.split(",");
/* 156 */       property.setProperty("log4j.logger.Console", str.replace(ss[0], Consolelog.getLevel().toString()));
/*     */ 
/* 158 */       str = property.getProperty("log4j.logger.File");
/* 159 */       ss = str.split(",");
/* 160 */       property.setProperty("log4j.logger.File", str.replace(ss[0], filelog.getLevel().toString()));
/*     */ 
/* 162 */       property.store(new FileOutputStream("E:\\log4j.properties"), "log4j.properties");
/*     */     }
/*     */     catch (Exception e) {
/* 165 */       b = false;
/*     */     }
/* 167 */     return b;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     org.misc.log.LogUtil
 * JD-Core Version:    0.6.2
 */