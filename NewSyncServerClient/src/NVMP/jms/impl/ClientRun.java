/*    */ package NVMP.jms.impl;
/*    */ 
/*    */ import NVMP.jms.ctrl.IMessageHander;
/*    */ import NVMP.jms.ctrl.MessageSendHandler;
/*    */ import NVMP.jms.rpc.Message;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ public class ClientRun
/*    */   implements IMessageHander, MessageSendHandler
/*    */ {
/*    */   private ClientImpl ci;
/*    */   HashMap<String, ProxyHandler> proxyHp;
/*    */ 
/*    */   public void registerProxy(ProxyHandler obj)
/*    */   {
/* 19 */     this.proxyHp.put(obj.getClass().getSimpleName(), obj);
/* 20 */     obj.SetSendHandler(this);
/*    */   }
/*    */ 
/*    */   public ClientRun(String name) throws JMSException
/*    */   {
/* 25 */     this.proxyHp = new HashMap();
/*    */ 
/* 27 */     this.ci = new ClientImpl(name);
/* 28 */     this.ci.AddListener(this);
/*    */   }
/*    */ 
/*    */   public void QueueSend(String userid, String url, HashMap<String, Object> hp, byte[] data)
/*    */     throws JMSException
/*    */   {
/* 38 */     Message m = new Message();
/* 39 */     m.set_Url(url);
/* 40 */     m.AddParams(hp);
/* 41 */     this.ci.SendMessage(m, data);
/*    */   }
/*    */ 
/*    */   public void TopPush(String url, HashMap<String, Object> hp, byte[] data)
/*    */   {
/* 48 */     System.out.println("很遗憾，客户端没这个功能。");
/*    */   }
/*    */ 
/*    */   public void ReceiveMessage(Message mg, byte[] data)
/*    */   {
/* 56 */     String url = mg.get_Url();
/* 57 */     String domainName = url.substring(0, url.indexOf("."));
/*    */ 
/* 60 */     ProxyHandler rd = (ProxyHandler)this.proxyHp.get(domainName);
/* 61 */     if (rd != null)
/* 62 */       rd.ReturnEvent(mg.GetParams(), url, data);
/*    */   }
/*    */ 
/*    */   public void start()
/*    */     throws JMSException
/*    */   {
/* 68 */     this.ci.start();
/*    */   }
/*    */ 
/*    */   public void stop()
/*    */     throws JMSException
/*    */   {
/* 74 */     this.ci.stop();
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.impl.ClientRun
 * JD-Core Version:    0.6.2
 */