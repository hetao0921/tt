/*    */ package NVMP.service.impl;
/*    */ 
/*    */ public class ServerIPConfig
/*    */ {
/*    */   private int id;
/*    */   private String deviceType;
/*    */   private String deviceName;
/*    */   private String deviceIp;
/*    */   private int devicePort;
/*    */   private String deviceId;
/*    */   private String deviceLevel;
/*    */   private String oldIp;
/*    */ 
/*    */   public ServerIPConfig()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ServerIPConfig(int id, String deviceType, String deviceName, String deviceIp, int devicePort, String deviceId, String deviceLevel)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     this.deviceType = deviceType;
/* 20 */     this.deviceName = deviceName;
/* 21 */     this.deviceIp = deviceIp;
/* 22 */     this.devicePort = devicePort;
/* 23 */     this.deviceId = deviceId;
/* 24 */     this.deviceLevel = deviceLevel;
/*    */   }
/*    */   public int getId() {
/* 27 */     return this.id;
/*    */   }
/*    */   public void setId(int id) {
/* 30 */     this.id = id;
/*    */   }
/*    */   public String getDeviceType() {
/* 33 */     return this.deviceType;
/*    */   }
/*    */   public void setDeviceType(String deviceType) {
/* 36 */     this.deviceType = deviceType;
/*    */   }
/*    */   public String getDeviceName() {
/* 39 */     return this.deviceName;
/*    */   }
/*    */   public void setDeviceName(String deviceName) {
/* 42 */     this.deviceName = deviceName;
/*    */   }
/*    */   public String getDeviceIp() {
/* 45 */     return this.deviceIp;
/*    */   }
/*    */   public void setDeviceIp(String deviceIp) {
/* 48 */     this.deviceIp = deviceIp;
/*    */   }
/*    */   public int getDevicePort() {
/* 51 */     return this.devicePort;
/*    */   }
/*    */   public void setDevicePort(int devicePort) {
/* 54 */     this.devicePort = devicePort;
/*    */   }
/*    */   public String getDeviceId() {
/* 57 */     return this.deviceId;
/*    */   }
/*    */   public void setDeviceId(String deviceId) {
/* 60 */     this.deviceId = deviceId;
/*    */   }
/*    */   public String getDeviceLevel() {
/* 63 */     return this.deviceLevel;
/*    */   }
/*    */   public void setDeviceLevel(String deviceLevel) {
/* 66 */     this.deviceLevel = deviceLevel;
/*    */   }
/*    */   public String getOldIp() {
/* 69 */     return this.oldIp;
/*    */   }
/*    */   public void setOldIp(String oldIp) {
/* 72 */     this.oldIp = oldIp;
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.service.impl.ServerIPConfig
 * JD-Core Version:    0.6.2
 */