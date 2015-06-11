package corenet.exchange;

public class ExchangeProtocol
{ 
	// type 字段
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 SessionId = 0x01;
	public static final short SessionId = 0x01; // 分配会话ID server -> client
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 CreateGroup = 0x02;
	public static final short CreateGroup = 0x02; // 建立一个组 client -> server,或有应用服务器业务组件建立
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 JoinGroup = 0x03;
	public static final short JoinGroup = 0x03; // 加入一个组 client -> server
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 LeaveGroup = 0x04;
	public static final short LeaveGroup = 0x04; // 加入一个组 client -> server
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 CreateSession = 0x05;
	public static final short CreateSession = 0x05; // 创建会话 client -> server
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 DestroySession = 0x06;
	public static final short DestroySession = 0x06; // 结束会话 client -> server
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 RouteTrace = 0x07;
	public static final short RouteTrace = 0x07; // 路由探测, server -> 到全网
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 TransParentData = 0x08;
	public static final short TransParentData = 0x08; // 透明数据
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 RpcMessage = 0x09;
	public static final short RpcMessage = 0x09; // Rpc消息
	
	public static final short ServerClinet = 0x0a; // 服务器之间相互交流
	
	public static final short NewServerClinet = 0x1a;//服务器新的交互
	
	public static final short Online =  0x0b; // 客户上下线通知
	 
	public static final short GroupLeave =  0x0c; // 组内通告全局，离开or进入
	
	public static final short CenterMessage =  0x0d; // 组内通告全局，离开or进入
	
	
	// option 选项
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 RoutineMessage = 1 << 0;
	public static final short RoutineMessage = 1 << 0; // 带路由选项的消息，在消息头后
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 GlobalGroup = 1 << 1;
	public static final short GlobalGroup = 1 << 1; // 全网组，没有指定该选项，创建的组在一个exchange内
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly UInt16 WithSessionId = 1 << 2;
	public static final short WithSessionId = 1 << 2;

	// 会话标识大小
	public static final int SessionIdSize = 20;

}
