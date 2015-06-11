/*    */ package NVMP.jms.impl;
/*    */ 
/*    */ import NVMP.jms.ctrl.IMessageHander;
/*    */ import NVMP.jms.ctrl.QueueConsole;
/*    */ import NVMP.jms.rpc.Message;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ public class ClientImpl
/*    */ {
/*    */   private QueueConsole recive_QueueConsole;
/*    */   private QueueConsole sned_QueueConsole;
/*    */   private String clientid;
/*    */ 
/*    */   public ClientImpl(String userid)
/*    */     throws JMSException
/*    */   {
/* 20 */     this.clientid = userid;
/* 21 */     this.recive_QueueConsole = new QueueConsole(userid, "serverQueue");
/* 22 */     this.sned_QueueConsole = new QueueConsole(userid, "clientQueue");
/*    */   }
/*    */ 
/*    */   public void AddListener(IMessageHander im) {
/* 26 */     this.recive_QueueConsole.addFilterListener(im);
/*    */   }
/*    */ 
/*    */   public void SendMessage(Message mg, byte[] data) throws JMSException
/*    */   {
/* 31 */     this.sned_QueueConsole.SendMessage(null, mg, data);
/*    */   }
/*    */ 
/*    */   public void stop() throws JMSException {
/* 35 */     this.recive_QueueConsole.stop();
/* 36 */     this.sned_QueueConsole.stop();
/*    */   }
/*    */ 
/*    */   public void start() throws JMSException {
/* 40 */     this.recive_QueueConsole.start();
/* 41 */     this.sned_QueueConsole.start();
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.impl.ClientImpl
 * JD-Core Version:    0.6.2
 */