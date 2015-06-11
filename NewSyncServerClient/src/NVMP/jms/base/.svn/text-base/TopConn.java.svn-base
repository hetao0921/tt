/*    */ package NVMP.jms.base;
/*    */ 
/*    */ import NVMP.jms.util.JNDIUtil;
/*    */ import javax.jms.BytesMessage;
/*    */ import javax.jms.JMSException;
/*    */ import javax.jms.MessageConsumer;
/*    */ import javax.jms.MessageListener;
/*    */ import javax.jms.Topic;
/*    */ import javax.jms.TopicConnection;
/*    */ import javax.jms.TopicPublisher;
/*    */ import javax.jms.TopicSession;
/*    */ 
/*    */ public class TopConn
/*    */ {
/*    */   private TopicConnection conn;
/*    */   private TopicSession topSession;
/*    */   private TopicPublisher publisher;
/*    */   private Topic topic;
/*    */   private MessageConsumer subConsumer;
/*    */   private String clientID;
/*    */ 
/*    */   public TopConn(String clientid, String topname)
/*    */     throws JMSException
/*    */   {
/* 36 */     this.clientID = clientid;
/* 37 */     this.conn = JNDIUtil.getTopicConnection();
/* 38 */     this.conn.setClientID(this.clientID);
/* 39 */     this.topic = JNDIUtil.lookupTopic(topname);
/* 40 */     this.topSession = this.conn.createTopicSession(false, 1);
/* 41 */     this.publisher = this.topSession.createPublisher(this.topic);
/*    */   }
/*    */ 
/*    */   public void publishMsg(byte[] array, String receiveId)
/*    */     throws JMSException
/*    */   {
/* 53 */     BytesMessage bmg = this.topSession.createBytesMessage();
/* 54 */     bmg.writeBytes(array);
/* 55 */     bmg.setIntProperty("Length", array.length);
/* 56 */     bmg.setStringProperty("receiveId", receiveId);
/*    */ 
/* 58 */     this.publisher.publish(bmg, 2, 4, 0L);
/*    */   }
/*    */ 
/*    */   public void regListen(String name, MessageListener ml)
/*    */     throws JMSException
/*    */   {
/* 68 */     this.subConsumer = this.topSession.createDurableSubscriber(this.topic, name);
/* 69 */     this.subConsumer.setMessageListener(ml);
/* 70 */     start();
/*    */   }
/*    */ 
/*    */   public void start() throws JMSException {
/* 74 */     this.conn.start();
/*    */   }
/*    */ 
/*    */   public void stop() throws JMSException {
/* 78 */     this.conn.stop();
/*    */   }
/*    */ 
/*    */   public void Close()
/*    */   {
/*    */     try {
/* 84 */       this.conn.close();
/*    */     }
/*    */     catch (JMSException e) {
/* 87 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.base.TopConn
 * JD-Core Version:    0.6.2
 */