/*    */ package NVMP.jms.ctrl;
/*    */ 
/*    */ import NVMP.jms.base.BaseHeader;
/*    */ import NVMP.jms.util.ByteArrayUtil;
/*    */ import NVMP.jms.util.Encoding;
/*    */ import java.io.PrintStream;
/*    */ import javax.jms.BytesMessage;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ public class MessageListener
/*    */   implements javax.jms.MessageListener
/*    */ {
/*    */   private IMessageHander imh;
/*    */ 
/*    */   public void setIh(IMessageHander imh)
/*    */   {
/* 22 */     this.imh = imh;
/*    */   }
/*    */ 
/*    */   public void onMessage(javax.jms.Message message)
/*    */   {
/* 49 */     int n = 0;
/*    */ 
/* 51 */     if ((message instanceof BytesMessage))
/*    */       try {
/* 53 */         BytesMessage bmg = (BytesMessage)message;
/*    */ 
/* 58 */         n = bmg.getIntProperty("Length");
/*    */ 
/* 62 */         byte[] array = new byte[n];
/* 63 */         bmg.readBytes(array);
/*    */ 
/* 65 */         byte[] h = ByteArrayUtil.Trim(array, 0, 14);
/* 66 */         if (h == null)
/*    */         {
/* 68 */           System.out.println("头文件解析错误，此次操作失败");
/* 69 */           return;
/*    */         }
/*    */ 
/* 72 */         BaseHeader header = new BaseHeader(h);
/* 73 */         if (!header.ValidateMagic()) {
/* 74 */           System.out.println("头文件校验错误，此次操作失败");
/* 75 */           return;
/*    */         }
/*    */ 
/* 80 */         if (header.getType() == 1) {
/* 81 */           byte[] body = ByteArrayUtil.Trim(array, 14, header.getBodyLength());
/*    */ 
/* 83 */           NVMP.jms.rpc.Message mg = new NVMP.jms.rpc.Message(Encoding.byteToString(body));
/* 84 */           byte[] data = ByteArrayUtil.Trim(array, 14 + header.getBodyLength(), array.length - 14 - header.getBodyLength());
/*    */ 
/* 86 */           this.imh.ReceiveMessage(mg, data);
/*    */         }
/*    */ 
/*    */       }
/*    */       catch (JMSException e)
/*    */       {
/* 95 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.ctrl.MessageListener
 * JD-Core Version:    0.6.2
 */