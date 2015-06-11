/*    */ package NVMP.jms.ctrl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class BaseMessage
/*    */ {
/*    */   private HashMap<String, String> messageHp;
/*    */   private byte[] array;
/*    */ 
/*    */   public BaseMessage()
/*    */   {
/* 12 */     this.messageHp = new HashMap();
/*    */   }
/*    */ 
/*    */   public HashMap<String, String> getMessageHp() {
/* 16 */     return this.messageHp;
/*    */   }
/*    */ 
/*    */   public void setALlMessageProperty(HashMap<String, String> messageHp) {
/* 20 */     this.messageHp = messageHp;
/*    */   }
/*    */ 
/*    */   public void addMessageProperty(String keyname, String value) {
/* 24 */     this.messageHp.put(keyname, value);
/*    */   }
/*    */ 
/*    */   public byte[] getArray()
/*    */   {
/* 29 */     return this.array;
/*    */   }
/*    */ 
/*    */   public void setArray(byte[] array) {
/* 33 */     this.array = array;
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.ctrl.BaseMessage
 * JD-Core Version:    0.6.2
 */