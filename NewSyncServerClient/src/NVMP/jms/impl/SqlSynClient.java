/*    */ package NVMP.jms.impl;
/*    */ 
/*    */ import NVMP.jms.ctrl.QueueConsole;
/*    */ import NVMP.jms.rpc.Message;
/*    */ import NVMP.jms.util.Encoding;
/*    */ import java.io.PrintStream;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ public class SqlSynClient
/*    */ {
/*    */   private QueueConsole sned_QueueConsole;
/*    */   private String userid;
/*    */ 
/*    */   public String getUserid()
/*    */   {
/* 18 */     return this.userid;
/*    */   }
/*    */ 
/*    */   public SqlSynClient(String userid) throws JMSException
/*    */   {
/* 23 */     this.userid = userid;
/* 24 */     boolean flag = true;
/* 25 */     while (flag)
/*    */       try {
/* 27 */         this.sned_QueueConsole = new QueueConsole(userid, "clientQueue");
/* 28 */         flag = false;
/*    */       } catch (Exception e) {
/* 30 */         System.out.println("====与jms服务器连接失败，3秒重新来过");
/* 31 */         flag = true;
/*    */         try {
/* 33 */           Thread.sleep(3000L);
/*    */         }
/*    */         catch (Exception localException1)
/*    */         {
/*    */         }
/*    */       }
/*    */   }
/*    */ 
/*    */   private void SendMessage(String receiveId, Message m, byte[] data)
/*    */     throws JMSException
/*    */   {
/* 44 */     this.sned_QueueConsole.SendMessage(receiveId, m, data);
/*    */   }
/*    */ 
/*    */   public void SendSqlMessage(Integer seq, String sql) throws JMSException
/*    */   {
/* 49 */     Message m = new Message();
/* 50 */     m.AddParam("userid", this.userid);
/* 51 */     m.AddParam("seq", seq);
/* 52 */     m.set_Operator(this.userid);
/* 53 */     m.set_Url("DBSynchronization.ClientQueueSend");
/*    */ 
/* 55 */     SendMessage(null, m, Encoding.StringToByte(sql));
/*    */   }
/*    */ 
/*    */   public void SendCommand(String cmd, String args) throws JMSException
/*    */   {
/* 60 */     Message m = new Message();
/* 61 */     m.AddParam("cmd", cmd);
/* 62 */     m.AddParam("userid", this.userid);
/* 63 */     m.set_Operator(this.userid);
/* 64 */     m.set_Url("DBSynchronization.ClientCommand");
/*    */ 
/* 66 */     SendMessage(null, m, Encoding.StringToByte(args));
/*    */   }
/*    */ 
/*    */   public void Close()
/*    */   {
/* 71 */     this.sned_QueueConsole.close();
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.impl.SqlSynClient
 * JD-Core Version:    0.6.2
 */