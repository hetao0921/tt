/*    */ package NVMP.jms.base;
/*    */ 
/*    */ import NVMP.jms.util.JNDIUtil;
/*    */ import javax.jms.BytesMessage;
/*    */ import javax.jms.JMSException;
/*    */ import javax.jms.MessageListener;
/*    */ import javax.jms.Queue;
/*    */ import javax.jms.QueueConnection;
/*    */ import javax.jms.QueueReceiver;
/*    */ import javax.jms.QueueSender;
/*    */ import javax.jms.QueueSession;
/*    */ 
/*    */ public class QueueConn
/*    */ {
/*    */   private QueueConnection conn;
/*    */   private QueueSession queuesession;
/*    */   private Queue queue;
/*    */   private QueueSender queueSender;
/*    */   private QueueReceiver queueReceiver;
/*    */ 
/*    */   public QueueConn(String queuename)
/*    */     throws JMSException
/*    */   {
/* 29 */     this.conn = JNDIUtil.getQueueConnection();
/* 30 */     this.queue = JNDIUtil.lookupQueue(queuename);
/* 31 */     this.queuesession = this.conn.createQueueSession(false, 1);
/* 32 */     this.queueSender = this.queuesession.createSender(this.queue);
/*    */   }
/*    */ 
/*    */   public void sendMsg(byte[] array, String receiveId)
/*    */     throws JMSException
/*    */   {
/* 44 */     BytesMessage bmg = this.queuesession.createBytesMessage();
/*    */ 
/* 47 */     bmg.setStringProperty("receiveId", receiveId);
/* 48 */     bmg.setIntProperty("Length", array.length);
/* 49 */     bmg.writeBytes(array);
/*    */ 
/* 52 */     this.queueSender.send(bmg, 2, 4, 0L);
/*    */   }
/*    */ 
/*    */   public void regListen(String filter, MessageListener ml)
/*    */     throws JMSException
/*    */   {
/* 67 */     if (filter == null)
/* 68 */       this.queueReceiver = this.queuesession.createReceiver(this.queue);
/*    */     else
/* 70 */       this.queueReceiver = this.queuesession.createReceiver(this.queue, filter);
/* 71 */     this.queueReceiver.setMessageListener(ml);
/* 72 */     start();
/*    */   }
/*    */ 
/*    */   public void start() throws JMSException {
/* 76 */     this.conn.start();
/*    */   }
/*    */ 
/*    */   public void stop() throws JMSException {
/* 80 */     this.conn.stop();
/*    */   }
/*    */ 
/*    */   public void Close()
/*    */   {
/*    */     try
/*    */     {
/* 88 */       this.conn.close();
/* 89 */       JNDIUtil.again();
/*    */     }
/*    */     catch (JMSException e) {
/* 92 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.base.QueueConn
 * JD-Core Version:    0.6.2
 */