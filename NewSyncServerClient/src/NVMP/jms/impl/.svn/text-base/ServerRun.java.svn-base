/*    */ package NVMP.jms.impl;
/*    */ 
/*    */ import NVMP.jms.ctrl.IMessageHander;
/*    */ import NVMP.jms.ctrl.MessageSendHandler;
/*    */ import NVMP.jms.rpc.Message;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ public class ServerRun
/*    */   implements IMessageHander, MessageSendHandler
/*    */ {
/*    */   private ServerImpl si;
/*    */   HashMap<String, ProxyHandler> proxyHp;
/*    */ 
/*    */   public void registerProxy(ProxyHandler obj)
/*    */   {
/* 18 */     this.proxyHp.put(obj.getClass().getSimpleName(), obj);
/* 19 */     obj.SetSendHandler(this);
/*    */   }
/*    */ 
/*    */   public ServerRun(String name) throws JMSException {
/* 23 */     this.proxyHp = new HashMap();
/*    */ 
/* 25 */     this.si = new ServerImpl(name);
/* 26 */     this.si.AddListener(this);
/*    */   }
/*    */ 
/*    */   public void ReceiveMessage(Message mg, byte[] data)
/*    */   {
/* 33 */     String url = mg.get_Url();
/* 34 */     String domainName = url.substring(0, url.indexOf("."));
/*    */ 
/* 37 */     ProxyHandler rd = (ProxyHandler)this.proxyHp.get(domainName);
/* 38 */     if (rd != null)
/* 39 */       rd.ReturnEvent(mg.GetParams(), url, data);
/*    */   }
/*    */ 
/*    */   public void QueueSend(String userid, String url, HashMap<String, Object> hp, byte[] data)
/*    */   {
/*    */     try
/*    */     {
/* 46 */       Message m = new Message();
/* 47 */       m.set_Url(url);
/* 48 */       m.AddParams(hp);
/* 49 */       this.si.QueueSend(userid, m, data);
/*    */     }
/*    */     catch (JMSException e) {
/* 52 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void start()
/*    */     throws JMSException
/*    */   {
/* 59 */     this.si.start();
/*    */   }
/*    */ 
/*    */   public void stop()
/*    */     throws JMSException
/*    */   {
/* 65 */     this.si.stop();
/*    */   }
/*    */ 
/*    */   public void TopPush(String url, HashMap<String, Object> hp, byte[] data)
/*    */   {
/* 70 */     System.out.println("很遗憾，服务器目前也放弃了这个功能。");
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.impl.ServerRun
 * JD-Core Version:    0.6.2
 */