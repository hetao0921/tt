/*    */ package NVMP.jms.base;
/*    */ 
/*    */ public class BaseProtocol
/*    */ {
/* 12 */   public static final byte[] Magic = { 77, 66, 54, -105 };
/*    */   public static final int MagicSize = 4;
/*    */   public static final int VerSize = 1;
/*    */   public static final int LengthSize = 4;
/*    */   public static final int TypeSize = 2;
/*    */   public static final int OptionSize = 2;
/*    */   public static final int PrioritySize = 1;
/*    */   public static final int MagicOffset = 0;
/*    */   public static final int VerOffset = 4;
/*    */   public static final int LengthOffset = 5;
/*    */   public static final int TypeOffset = 9;
/*    */   public static final int OptionOffset = 11;
/*    */   public static final int PriorityOffset = 11;
/*    */   public static final int HeadSize = 14;
/*    */   public static final byte BeatAlive = 16;
/*    */   public static final byte CurVer = 1;
/*    */   public static final byte LowPriority = 1;
/*    */   public static final byte NormalyPriority = 32;
/*    */   public static final byte HightPriority = 64;
/*    */   public static final short OptionNone = 0;
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.base.BaseProtocol
 * JD-Core Version:    0.6.2
 */