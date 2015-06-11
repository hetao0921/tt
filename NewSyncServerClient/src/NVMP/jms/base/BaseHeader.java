/*     */ package NVMP.jms.base;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class BaseHeader
/*     */ {
/*     */   private byte[] Buffer;
/*     */ 
/*     */   public BaseHeader()
/*     */   {
/*   7 */     this.Buffer = new byte[14];
/*   8 */     this.Buffer[0] = BaseProtocol.Magic[0];
/*   9 */     this.Buffer[1] = BaseProtocol.Magic[1];
/*  10 */     this.Buffer[2] = BaseProtocol.Magic[2];
/*  11 */     this.Buffer[3] = BaseProtocol.Magic[3];
/*  12 */     SetVer((byte)1);
/*  13 */     ClearOption();
/*     */   }
/*     */ 
/*     */   public BaseHeader(byte[] Header) {
/*  17 */     this.Buffer = Header;
/*     */   }
/*     */ 
/*     */   public final boolean ValidateMagic()
/*     */   {
/*  24 */     return (this.Buffer[0] == BaseProtocol.Magic[0]) && 
/*  22 */       (this.Buffer[1] == BaseProtocol.Magic[1]) && 
/*  23 */       (this.Buffer[2] == BaseProtocol.Magic[2]) && 
/*  24 */       (this.Buffer[3] == BaseProtocol.Magic[3]);
/*     */   }
/*     */ 
/*     */   public final boolean ValidateVer() {
/*  28 */     return GetVer() == 1;
/*     */   }
/*     */ 
/*     */   private void SetLength(int Length) {
/*  32 */     int Offset = 5;
/*  33 */     this.Buffer[Offset] = ((byte)Length);
/*  34 */     this.Buffer[(Offset + 1)] = ((byte)(Length >> 8));
/*  35 */     this.Buffer[(Offset + 2)] = ((byte)(Length >> 16));
/*  36 */     this.Buffer[(Offset + 3)] = ((byte)(Length >> 24));
/*     */   }
/*     */ 
/*     */   private int Byte2Int(byte b) {
/*  40 */     if (b >= 0) {
/*  41 */       return b;
/*     */     }
/*  43 */     return (b & 0x7F) + 128;
/*     */   }
/*     */ 
/*     */   private int GetLegnth()
/*     */   {
/*  48 */     int Offset = 5;
/*     */ 
/*  50 */     int Length = Byte2Int(this.Buffer[Offset]) | 
/*  51 */       Byte2Int(this.Buffer[(Offset + 1)]) << 8 & 0xFF00 | 
/*  52 */       Byte2Int(this.Buffer[(Offset + 2)]) << 16 & 0xFF0000 | 
/*  53 */       Byte2Int(this.Buffer[(Offset + 3)]) << 24 & 0xFF000000;
/*  54 */     if (Length < 0) {
/*  55 */       System.out.println(Length);
/*     */     }
/*  57 */     return Length;
/*     */   }
/*     */ 
/*     */   private void SetMessageType(short Type) {
/*  61 */     int Offset = 9;
/*     */ 
/*  63 */     this.Buffer[Offset] = ((byte)Type);
/*  64 */     this.Buffer[(Offset + 1)] = ((byte)(Type >> 8));
/*     */   }
/*     */ 
/*     */   private short GetMessageType() {
/*  68 */     int Offset = 9;
/*     */ 
/*  70 */     short Type = this.Buffer[Offset];
/*  71 */     Type = (short)(Type + (short)(this.Buffer[(Offset + 1)] << 8 & 0xFF00));
/*     */ 
/*  73 */     return Type;
/*     */   }
/*     */ 
/*     */   private byte GetPriority() {
/*  77 */     return this.Buffer[11];
/*     */   }
/*     */ 
/*     */   private void SetPriority(byte Priority) {
/*  81 */     this.Buffer[11] = Priority;
/*     */   }
/*     */ 
/*     */   private byte GetVer() {
/*  85 */     return this.Buffer[4];
/*     */   }
/*     */ 
/*     */   private void SetVer(byte ver) {
/*  89 */     this.Buffer[4] = ver;
/*     */   }
/*     */ 
/*     */   public final void ClearOption() {
/*  93 */     this.Buffer[11] = 0;
/*  94 */     this.Buffer[12] = 0;
/*     */   }
/*     */ 
/*     */   public final void DelOption(short Option) {
/*  98 */     Option = (short)(Option ^ 0xFFFFFFFF);
/*     */     byte[] tmp11_6 = this.Buffer; tmp11_6[11] = ((byte)(tmp11_6[11] & (byte)Option));
/*     */     byte[] tmp24_19 = this.Buffer; tmp24_19[12] = ((byte)(tmp24_19[12] & (byte)(Option >> 8)));
/*     */   }
/*     */ 
/*     */   public final void AddOption(short Option) {
/* 104 */     if (Option == 0)
/*     */       return;
/*     */     byte[] tmp11_6 = this.Buffer; tmp11_6[11] = ((byte)(tmp11_6[11] | (byte)Option));
/*     */     byte[] tmp24_19 = this.Buffer; tmp24_19[12] = ((byte)(tmp24_19[12] | (byte)(Option >> 8)));
/*     */   }
/*     */ 
/*     */   public final short GetOption()
/*     */   {
/* 115 */     short Option = (short)this.Buffer[11];
/* 116 */     Option = (short)(Option + (short)((short)this.Buffer[12] << 8 & 0xFF00));
/* 117 */     return Option;
/*     */   }
/*     */ 
/*     */   public final int getBodyLength() {
/* 121 */     return GetLegnth();
/*     */   }
/*     */ 
/*     */   public final void setBodyLength(int value) {
/* 125 */     SetLength(value);
/*     */   }
/*     */ 
/*     */   public final short getType() {
/* 129 */     return GetMessageType();
/*     */   }
/*     */ 
/*     */   public final void setType(short value) {
/* 133 */     SetMessageType(value);
/*     */   }
/*     */ 
/*     */   public final byte getPriority() {
/* 137 */     return GetPriority();
/*     */   }
/*     */ 
/*     */   public final void setPriority(byte value) {
/* 141 */     SetPriority(value);
/*     */   }
/*     */ 
/*     */   public final byte getVer() {
/* 145 */     return GetVer();
/*     */   }
/*     */ 
/*     */   public final void setVer(byte value) {
/* 149 */     SetVer(value);
/*     */   }
/*     */ 
/*     */   public final byte[] getData() {
/* 153 */     return this.Buffer;
/*     */   }
/*     */ 
/*     */   public final void setData(byte[] value) {
/* 157 */     this.Buffer = value;
/*     */   }
/*     */ 
/*     */   public final short getOption() {
/* 161 */     return GetOption();
/*     */   }
/*     */ 
/*     */   public final boolean IsOptionSet(short Option) {
/* 165 */     return (GetOption() & Option) != 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.base.BaseHeader
 * JD-Core Version:    0.6.2
 */