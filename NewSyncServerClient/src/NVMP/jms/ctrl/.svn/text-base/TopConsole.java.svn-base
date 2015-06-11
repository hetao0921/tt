/*     */ package NVMP.jms.ctrl;
/*     */ 
/*     */ import NVMP.jms.base.BaseHeader;
/*     */ import NVMP.jms.base.TopConn;
/*     */ import NVMP.jms.rpc.Message;
/*     */ import NVMP.jms.util.ByteArrayUtil;
/*     */ import NVMP.jms.util.Encoding;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ public class TopConsole
/*     */ {
/*     */   private TopConn tc;
/*     */ 
/*     */   public TopConsole(String clientid, String topname)
/*     */     throws JMSException
/*     */   {
/*  25 */     this.tc = new TopConn(clientid, topname);
/*     */   }
/*     */ 
/*     */   private void SendMessage(byte[] array, String receiveId)
/*     */     throws JMSException
/*     */   {
/*  33 */     this.tc.publishMsg(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void SendMessage(String receiveId, int Type, int Priority, short Option, byte[] body, byte[] data)
/*     */     throws JMSException
/*     */   {
/*  51 */     BaseHeader Header = new BaseHeader();
/*  52 */     Header.setType((short)Type);
/*  53 */     Header.setPriority((byte)Priority);
/*  54 */     Header.setBodyLength(body == null ? 0 : body.length);
/*     */ 
/*  57 */     byte[] array = ByteArrayUtil.Sum(new byte[][] { Header.getData(), body, data });
/*  58 */     SendMessage(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void SendMessage(String receiveId, Message m, byte[] data)
/*     */     throws JMSException
/*     */   {
/*  67 */     byte[] body = Encoding.StringToByte(m.Serilize());
/*     */ 
/*  69 */     BaseHeader Header = new BaseHeader();
/*  70 */     Header.setType((short)1);
/*  71 */     Header.setPriority((byte)4);
/*  72 */     Header.setBodyLength(body == null ? 0 : body.length);
/*     */ 
/*  75 */     byte[] array = ByteArrayUtil.Sum(new byte[][] { Header.getData(), body, data });
/*  76 */     SendMessage(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void addListener(String name, IMessageHander im) {
/*  80 */     MessageListener tml = new MessageListener();
/*  81 */     tml.setIh(im);
/*     */     try {
/*  83 */       this.tc.regListen(name, tml);
/*     */     }
/*     */     catch (JMSException e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void start() throws JMSException {
/*  91 */     this.tc.start();
/*     */   }
/*     */ 
/*     */   public void stop() throws JMSException {
/*  95 */     this.tc.stop();
/*     */   }
/*     */ 
/*     */   public void Close()
/*     */   {
/* 100 */     this.tc.Close();
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.ctrl.TopConsole
 * JD-Core Version:    0.6.2
 */