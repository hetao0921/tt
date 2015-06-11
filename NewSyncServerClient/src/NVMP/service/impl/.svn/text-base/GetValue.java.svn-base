/*    */ package NVMP.service.impl;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import org.codehaus.xfire.XFire;
/*    */ import org.codehaus.xfire.XFireFactory;
/*    */ import org.codehaus.xfire.client.XFireProxyFactory;
/*    */ import org.codehaus.xfire.service.Service;
/*    */ import org.codehaus.xfire.service.binding.ObjectServiceFactory;
/*    */ import org.misc.log.LogUtil;
/*    */ 
/*    */ public class GetValue
/*    */ {
/* 20 */   Service service = new ObjectServiceFactory().create(IWebservice.class);
/*    */ 
/* 22 */   XFire xFire = XFireFactory.newInstance().getXFire();
/* 23 */   XFireProxyFactory factory = new XFireProxyFactory(this.xFire);
/*    */ 
/* 25 */   String url = "";
/*    */ 
/*    */   public GetValue(String url)
/*    */   {
/* 18 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public ServerIPConfigs GetAllInfo()
/*    */   {
/* 29 */     ServerIPConfigs sic = null;
/*    */     try
/*    */     {
/* 32 */       IWebservice ig = (IWebservice)this.factory.create(this.service, this.url);
/* 33 */       sic = ig.GetAllInfo();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 37 */       sic = null;
/* 38 */       LogUtil.info("sync client:there is a error in webservice:" + e.getMessage());
/*    */     }
/* 40 */     return sic;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 45 */     GetValue gv = new GetValue("http://192.168.1.23:8080/webservice/services/web");
/* 46 */     ServerIPConfigs sics = gv.GetAllInfo();
/* 47 */     List<ServerIPConfig> list = sics.getList();
/* 48 */     for (ServerIPConfig sic : list)
/* 49 */       System.out.println(sic.getDeviceId() + "===" + sic.getDeviceIp() + "===" + 
/* 50 */         sic.getDeviceLevel() + "===" + sic.getDeviceName() + "===" + sic.getDevicePort() + 
/* 51 */         "===" + sic.getDeviceType() + "===" + sic.getId());
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.service.impl.GetValue
 * JD-Core Version:    0.6.2
 */