/*     */ package NVMP.jms.ctrl;
/*     */ 
/*     */ import NVMP.jms.base.BaseHeader;
/*     */ import NVMP.jms.base.QueueConn;
/*     */ import NVMP.jms.rpc.Message;
/*     */ import NVMP.jms.util.ByteArrayUtil;
/*     */ import NVMP.jms.util.Encoding;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ public class QueueConsole
/*     */ {
/*     */   private QueueConn qc;
/*     */   private String clientID;
/*     */ 
/*     */   public QueueConsole(String clientid, String queuename)
/*     */     throws JMSException
/*     */   {
/*  17 */     this.qc = new QueueConn(queuename);
/*  18 */     this.clientID = clientid;
/*     */   }
/*     */ 
/*     */   private void SendMessage(byte[] array, String receiveId)
/*     */     throws JMSException
/*     */   {
/*  24 */     this.qc.sendMsg(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void SendMessage(String receiveId, int Type, int Priority, short Option, byte[] body, byte[] data)
/*     */     throws JMSException
/*     */   {
/*  40 */     BaseHeader Header = new BaseHeader();
/*  41 */     Header.setType((short)Type);
/*  42 */     Header.setPriority((byte)Priority);
/*  43 */     Header.setBodyLength(body == null ? 0 : body.length);
/*     */ 
/*  46 */     byte[] array = ByteArrayUtil.Sum(new byte[][] { Header.getData(), body, data });
/*  47 */     SendMessage(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void SendMessage(String receiveId, Message m, byte[] data)
/*     */     throws JMSException
/*     */   {
/*  56 */     byte[] body = Encoding.StringToByte(m.Serilize());
/*     */ 
/*  58 */     BaseHeader Header = new BaseHeader();
/*  59 */     Header.setType((short)1);
/*  60 */     Header.setPriority((byte)4);
/*  61 */     Header.setBodyLength(body == null ? 0 : body.length);
/*     */ 
/*  64 */     byte[] array = ByteArrayUtil.Sum(new byte[][] { Header.getData(), body, data });
/*  65 */     SendMessage(array, receiveId);
/*     */   }
/*     */ 
/*     */   public void addFilterListener(IMessageHander im)
/*     */   {
/*  71 */     MessageListener tml = new MessageListener();
/*  72 */     tml.setIh(im);
/*     */     try {
/*  74 */       this.qc.regListen("receiveId='" + this.clientID + "'", tml);
/*     */     }
/*     */     catch (JMSException e) {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addNoFilterListener(IMessageHander im) {
/*  82 */     MessageListener tml = new MessageListener();
/*  83 */     tml.setIh(im);
/*     */     try {
/*  85 */       this.qc.regListen(null, tml);
/*     */     }
/*     */     catch (JMSException e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void start() throws JMSException {
/*  93 */     this.qc.start();
/*     */   }
/*     */   public void stop() throws JMSException {
/*  96 */     this.qc.stop();
/*     */   }
/*     */ 
/*     */   public void close() {
/* 100 */     this.qc.Close();
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.ctrl.QueueConsole
 * JD-Core Version:    0.6.2
 */