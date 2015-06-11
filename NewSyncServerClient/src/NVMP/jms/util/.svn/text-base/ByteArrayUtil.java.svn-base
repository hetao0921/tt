/*    */ package NVMP.jms.util;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public class ByteArrayUtil
/*    */ {
/*    */   public static byte[] Sum(byte[][] array)
/*    */   {
/*  9 */     int n = 0;
/* 10 */     if (array == null)
/* 11 */       return null;
/* 12 */     byte[][] arrayOfByte1 = array; int j = array.length; for (int i = 0; i < j; i++) { byte[] b = arrayOfByte1[i];
/* 13 */       if (b != null) n += b.length;
/*    */     }
/* 15 */     ByteBuffer bf = ByteBuffer.allocate(n);
/* 16 */     byte[][] arrayOfByte2 = array; int k = array.length; for (j = 0; j < k; j++) { byte[] b = arrayOfByte2[j];
/* 17 */       if (b != null) bf.put(b);
/*    */     }
/* 19 */     return bf.array();
/*    */   }
/*    */ 
/*    */   public static byte[] Trim(byte[] array, int start, int len)
/*    */   {
/* 25 */     if (array == null)
/* 26 */       return null;
/* 27 */     if (array.length < start + len)
/* 28 */       return null;
/* 29 */     ByteBuffer bf = ByteBuffer.allocate(len);
/* 30 */     bf.put(array, start, len);
/*    */ 
/* 32 */     return bf.array();
/*    */   }
/*    */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.util.ByteArrayUtil
 * JD-Core Version:    0.6.2
 */