/*     */ package NVMP.jms.rpc;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
import java.util.Map;
/*     */ import java.util.Map.Entry;
import java.util.Set;
/*     */ 
/*     */ public class Message
/*     */ {
/*     */   private HashMap<String, Object> _Params;
/*     */   private String _Operator;
/*     */   private String _Url;
/*     */   private static final String HeadStartTag = "<Head>";
/*     */   private static final String HeadEndTag = "</Head>";
/*     */   private static final int HeadStartTagLen = 6;
/*     */   private static final int HeadEndTagLen = 7;
/*     */ 
/*     */   public Message()
/*     */   {
/*  18 */     this._Params = new HashMap();
/*     */   }
/*     */ 
/*     */   private boolean Decode(String Message)
/*     */   {
/*  23 */     String Head = "";
/*  24 */     String Body = "";
/*     */     try
/*     */     {
/*  28 */       if (!"<Head>".equals(Message.substring(0, 6)))
/*     */       {
/*  30 */         return false;
/*     */       }
/*     */ 
/*  33 */       int Pos = Message.indexOf("</Head>", 6);
/*  34 */       if (Pos < 0)
/*     */       {
/*  36 */         return false;
/*     */       }
/*     */ 
/*  39 */       Head = Message.substring(6, Pos);
/*  40 */       Body = Message.substring(Pos + 7, Pos + 7 + Message.length() - (Pos + 7));
/*     */ 
/*  42 */       DecodeHead(Head);
/*  43 */       DecodeBody(Body);
/*     */     }
/*     */     catch (RuntimeException e)
/*     */     {
/*  48 */       return false;
/*     */     }
/*     */ 
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   public Message(String Message)
/*     */   {
/*  57 */     Decode(Message);
/*     */   }
/*     */ 
/*     */   public final void DecodeHead(String Message)
/*     */   {
/*  62 */     int Start = 0;
/*     */ 
/*  65 */     this._Params = new HashMap();
/*     */     try
/*     */     {
/*  69 */       while (Start < Message.length())
/*     */       {
/*  71 */         int Pos1 = Message.indexOf('<', Start);
/*  72 */         if (Pos1 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/*  77 */         int Pos2 = Message.indexOf('>', Pos1 + 1);
/*  78 */         if (Pos2 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/*  83 */         String Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);
/*     */ 
/*  85 */         int Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);
/*     */ 
/*  87 */         if (Pos3 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/*  92 */         String Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);
/*     */ 
/*  94 */         Start = Pos3 + Name.length() + 3;
/*     */ 
/*  96 */         if (Name.equals("Url"))
/*     */         {
/*  98 */           this._Url = Value;
/*     */         }
/* 100 */         else if (Name.equals("Operator"))
/*     */         {
/* 102 */           this._Operator = Value;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (RuntimeException localRuntimeException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void DecodeBody(String Message)
/*     */   {
/* 115 */     int Start = 0;
/*     */ 
/* 118 */     this._Params = new HashMap();
/*     */     try
/*     */     {
/* 122 */       while (Start < Message.length())
/*     */       {
/* 124 */         int Pos1 = Message.indexOf('<', Start);
/* 125 */         if (Pos1 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/* 130 */         int Pos2 = Message.indexOf('>', Pos1 + 1);
/* 131 */         if (Pos2 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/* 136 */         String Name = Message.substring(Pos1 + 1, Pos1 + 1 + Pos2 - Pos1 - 1);
/*     */ 
/* 138 */         int Pos3 = Message.indexOf("</" + Name + ">", Pos2 + 1);
/*     */ 
/* 140 */         if (Pos3 < 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/* 145 */         String Value = Message.substring(Pos2 + 1, Pos2 + 1 + Pos3 - Pos2 - 1);
/*     */ 
/* 147 */         Start = Pos3 + Name.length() + 3;
/* 148 */         this._Params.put(Name, Value);
/*     */       }
/*     */     }
/*     */     catch (RuntimeException localRuntimeException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void AddParams(HashMap<String, Object> Params)
/*     */   {
/* 161 */     this._Params = Params;
/*     */   }
/*     */ 
/*     */   public final Object GetParam(String name)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       return this._Params.get(name);
/*     */     }
/*     */     catch (RuntimeException localRuntimeException)
/*     */     {
/*     */     }
/*     */ 
/* 174 */     return "";
/*     */   }
/*     */ 
/*     */   public final void AddParam(String Name, Object Value)
/*     */   {
/* 181 */     this._Params.put(Name, Value);
/*     */   }
/*     */ 
/*     */   public final String MessageSrc()
/*     */   {
/* 186 */     return "";
/*     */   }
/*     */ 
/*     */   public final HashMap<String, Object> GetParams()
/*     */   {
/* 191 */     return this._Params;
/*     */   }
/*     */ 
/*     */   public final String Serilize()
/*     */   {
/* 196 */     String Message = "";
/*     */ 
/* 198 */     Message = Message + "<Head>";
/* 199 */     Message = Message + "<Url>" + this._Url + "</Url>";
/* 200 */     Message = Message + "<Operator>" + this._Operator + "</Operator>";
/* 201 */     Message = Message + "</Head>";
/*     */ 
/* 206 */     Iterator iterator = this._Params.entrySet().iterator();
/*     */ 
/* 208 */     while (iterator.hasNext())
/*     */     {
/* 210 */       Map.Entry Pair = (Map.Entry)iterator.next();
/*     */       String str;
/* 212 */       if (Pair.getValue() == null) str = ""; else {
/* 213 */         str = Pair.getValue().toString();
/*     */       }
/* 215 */       Message = Message + "<" + (String)Pair.getKey() + ">" + str + "</" + (String)Pair.getKey() + ">";
/*     */     }
/*     */ 
/* 218 */     return Message;
/*     */   }
/*     */ 
/*     */   public String get_Url()
/*     */   {
/* 225 */     return this._Url;
/*     */   }
/*     */ 
/*     */   public void set_Url(String _Url) {
/* 229 */     this._Url = _Url;
/*     */   }
/*     */ 
/*     */   public String get_Operator() {
/* 233 */     return this._Operator;
/*     */   }
/*     */ 
/*     */   public void set_Operator(String _Operator) {
/* 237 */     this._Operator = _Operator;
/*     */   }
/*     */ }

/* Location:           C:\Users\hxht\Desktop\SyncServerClient.jar
 * Qualified Name:     NVMP.jms.rpc.Message
 * JD-Core Version:    0.6.2
 */