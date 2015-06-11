//
//
//
package corenet.netbase;

//    
//     string s =  Encoding.ASCII.GetString( m_byBuff, 0, nBytesRec )
//     Byte[] byteDateLine = Encoding.ASCII.GetBytes( m_tbMessage.Text.ToCharArray() ); 
//
//     head 
//     magic     头标识固定    4字节 0x4d, 0x42,0x36,0x97
//     ver       版本号       1字节 1
//     length    长度         4字节 
//     type      类型         2字节 
//     option    消息选项     2字节 
//     priority  优先级       1字节 
//     

public class BaseProtocol
{
	public static final byte[] Magic = { 0x4d, 0x42, 0x36, (byte) 0x97 };

//	public static final int MagicSize = sizeof(int);
//	public static final int VerSize = sizeof(byte);
//	public static final int LengthSize = sizeof(int);
//	public static final int TypeSize = sizeof(short);
//	public static final int OptionSize = sizeof(short);
//	public static final int PrioritySize = sizeof(byte);
			/**
			 * by zzw
			 * 
			 * java中这些都是定长
			 * 
			 *        public static readonly int MagicSize = sizeof(UInt32);
        public static readonly int VerSize = sizeof(byte);
        public static readonly int LengthSize = sizeof(UInt32);
        public static readonly int TypeSize = sizeof(UInt16);
        public static readonly int OptionSize = sizeof(UInt16);
        public static readonly int PrioritySize = sizeof(byte);
			 * **/
				
	public static final int MagicSize = 4;
    public static final int VerSize = 1;
	public static final int LengthSize = 4;
	public static final int TypeSize = 2;
	public static final int OptionSize = 2;
	public static final int PrioritySize = 1;
	
	//添加目标  、来源  2个信息。
	public static final int TargetLengthSize = 1;
	public static final int TargetSize = 30;
	public static final int SourceLengthSize = 1;
	public static final int SourceSize = 30;
	
	//再次添加 ID 信息，长度32
	public static final int IDSize = 32;
    
	//再次添加偏移量，表明头和包体之间的额外数据
	public static final int OtherSize = 4;
	
	
	
	
	public static final int MagicOffset = 0;
	public static final int VerOffset = MagicOffset + MagicSize;
	public static final int LengthOffset = VerOffset + VerSize;
	public static final int TypeOffset = LengthOffset + LengthSize;
	public static final int OptionOffset = TypeOffset + TypeSize;
	public static final int PriorityOffset = OptionOffset + OptionSize;
	
	
	public static final int TargetLengthOffset = PriorityOffset + PrioritySize;
	public static final int TargetOffset = TargetLengthOffset + TargetLengthSize;
	
	public static final int SourceLengthOffset = TargetOffset + TargetSize;
	public static final int SourceOffset = SourceLengthOffset + SourceLengthSize;
	 
	//添加ID的偏移值。
	public static final int IDOffset = SourceOffset+SourceSize;
	
	//添加Other的偏移值
	public static final int OtherOffset = IDOffset+IDSize;
	
	

	public static final int HeadSize = MagicSize + VerSize + LengthSize + TypeSize + OptionSize + PrioritySize +TargetLengthSize+TargetSize + SourceLengthSize+SourceSize+IDSize+OtherSize;

	public static final byte BeatAlive = 0x10;
	public static final byte CurVer = 0x03;

	public static final byte LowPriority = 1;
	public static final byte NormalyPriority = 32;
	public static final byte HightPriority = 64;

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 OptionNone = 0;
	public static final short OptionNone = 0;

}
//C# TO JAVA CONVERTER TODO TASK: Delegates are not available in Java:
//public delegate void OnReadMessageEvent(BaseSession Session, BaseHeader Header, byte[] Body);
//C# TO JAVA CONVERTER TODO TASK: Delegates are not available in Java:
//public delegate void OnConnectionLostEvent(BaseSession Session);
//C# TO JAVA CONVERTER TODO TASK: Delegates are not available in Java:
//public delegate void OnNewConnectionEvent(BaseSession Session, bool Success);





