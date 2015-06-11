package NVMP.jms.base;


/**
 * 规则：基本上就是设置下 版本啊 类型啊  包体长度啊 什么的
 * 整个数据，由包头 包体  和 数据，3部分组成。
 * 总体来说，这个就是为了包头弄的。
 *  
 * */
public class BaseProtocol {
	
	public static final byte[] Magic = { 0x4d, 0x42, 0x36, (byte) 0x97 };
	
	public static final int MagicSize = 4;
	
	public static final int VerSize = 1;
	
	public static final int LengthSize = 4;
	
	public static final int TypeSize = 2;
	
	//下面2个都是copy过来的，不保证一定能用到。
	public static final int OptionSize = 2;
	
	public static final int PrioritySize = 1;
	
	
	
	
	//在数组中的每个起始位置
	public static final int MagicOffset = 0;
	public static final int VerOffset = MagicOffset + MagicSize;
	public static final int LengthOffset = VerOffset + VerSize;
	public static final int TypeOffset = LengthOffset + LengthSize;
	public static final int OptionOffset = TypeOffset + TypeSize;
	public static final int PriorityOffset = TypeOffset + TypeSize;
	
	
	//总长度
	public static final int HeadSize = MagicSize + VerSize + LengthSize + TypeSize + OptionSize + PrioritySize;

	public static final byte BeatAlive = 0x10;
	public static final byte CurVer = 0x01;
	public static final byte LowPriority = 1;
	public static final byte NormalyPriority = 32;
	public static final byte HightPriority = 64;
	public static final short OptionNone = 0;
	
	
}
