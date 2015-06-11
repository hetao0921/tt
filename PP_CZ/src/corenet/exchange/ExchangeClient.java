package corenet.exchange;

import java.net.SocketAddress;
import java.util.Arrays;

import org.misc.log.LogUtil;

import corenet.exchange.Interface.Clientlistener;
import corenet.netbase.AsyncBuffer;
import corenet.netbase.BaseClient;
import corenet.netbase.BaseHeader;
import corenet.netbase.BaseProtocol;
import corenet.netbase.BaseSession;
import corenet.netbase.Interface.BaseClientListen;
import corenet.netbase.Interface.BaseSessionListen;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.BaseMessage;
import corenet.rpc.IMessage;
 
//C# TO JAVA CONVERTER TODO TASK: Delegates are not available in Java:
//public delegate void OnConnectExchangeEvent(IChannel Channel);

public class ExchangeClient implements IChannel, BaseClientListen,
		BaseSessionListen {

	private BaseClient _Base;
	private String _SessionId;
	private boolean _IsConnectExchange;
	// private RecvMessage rm;
	//
	// private ConnectExchange ce;

	private Clientlistener clientListen;

	// C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
	// public event OnConnectExchangeEvent OnConnectExchange;

	public BaseClient get_Base() {
		return _Base;
	}

	public Clientlistener getClientListen() {
		return clientListen;
	}

	public void setClientListen(Clientlistener clientListen) {
		this.clientListen = clientListen;
	}

	// public RecvMessage getRm() {
	// return rm;
	// }
	// public void setRm(RecvMessage rm) {
	// this.rm = rm;
	// }
	// public ConnectExchange getCe() {
	// return ce;
	// }
	// public void setCe(ConnectExchange ce) {
	// this.ce = ce;
	// }
	public ExchangeClient() {
		_IsConnectExchange = false;

		_Base = new BaseClient();

		/*
		 * by zzw
		 * 
		 * 事件绑定
		 */

		// C# TO JAVA CONVERTER TODO TASK: Java has no equivalent to C#-style
		// event wireups:
		_Base.setBcl(this);
		_Base.setBsl(this);
		// C# TO JAVA CONVERTER TODO TASK: Java has no equivalent to C#-style
		// event wireups:

		_IsConnectExchange = false;
	}

	/**
	 * by zzw OnConnect 改为OnNewConnection
	 * */
	public void OnNewConnection(BaseSession Session, boolean Success) {
		if (Success) {
			System.out.println("Connect ok, Send create session request");

			if (_SessionId.length() == 0) {
				_Base.SendMessage(null, ExchangeProtocol.CreateSession,
						BaseProtocol.HightPriority, BaseProtocol.OptionNone,
						null, null, null, null);
			} else {
				// _Base.SendMessage(Encoding.ASCII.GetBytes(_SessionId.toCharArray()),
				// ExchangeProtocol.CreateSession, BaseProtocol.HightPriority,
				// ExchangeProtocol.WithSessionId);
				_Base.SendMessage(Encoding.StringToByte(_SessionId),
						ExchangeProtocol.CreateSession,
						BaseProtocol.HightPriority,
						ExchangeProtocol.WithSessionId, null, null, null, null);
				// 刚刚我们发送了一段带着session的值过去
				System.out.println("我们发送了自己的sessionId过去:" + _SessionId);
				if (this.clientListen != null) {
					// 表示连接成功，我们将连接标示弄成可发送
					this.clientListen.OnNewConnection(this);
				}

			}

		} else {

		}
	}

	/**
	 * by zzw this.OnReadMessageEvent 改为 OnReadMessage
	 * */

	public void OnRecievedData(AsyncBuffer ar) {

		System.out.print(ar);

	}

	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] aBytes) {
		
		byte[] Body = null;
		byte[] Other = null;
		if (aBytes != null) {
			if (Header.getBodyLength() > 0)
				Body = Arrays.copyOfRange(aBytes, 0, Header.getBodyLength());
			if (Header.getOther() > 0)
				Other = Arrays.copyOfRange(aBytes, Header.getBodyLength(),
						Header.getBodyLength() + Header.getOther());
		}

		if (Header.getType() == ExchangeProtocol.ServerClinet) {
			if (Body != null && Body.length != 0) {
				BaseMessage Message = new BaseMessage(
						Encoding.byteToString(Body));
	
					if (this.clientListen != null) {
						clientListen.OnServerClinet(Message);
					}


			}
		} else 
		
		if (Header.getType() == ExchangeProtocol.CreateSession) {
			if (Header.IsOptionSet(ExchangeProtocol.WithSessionId)) {
				if (Body == null || Body.length == 0) {
					throw new RuntimeException("没有指定回话ID");
				}

				System.out
						.println("rece create session reply 服务分配回话id sessionId = "
								+ Encoding.byteToString(Body));
				// this._SessionId = Encoding.ASCII.GetString(Body, 0,
				// Body.length);
				this._SessionId = Encoding.byteToString(Body);
			} else {
				System.out.println("rece create session reply");
			}

			_IsConnectExchange = true;

			/**
			 * by zzw
			 * 
			 * */
			LogUtil.debug("第一次获取回话");

			if (clientListen != null) {
				clientListen.OnConnectExchange(this);
			}

		} else if (Header.getType() == ExchangeProtocol.Online) {
			if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {
				if (this.clientListen != null) {
					BaseMessage Message = new BaseMessage(
							Encoding.byteToString(Body));
					clientListen.OnGlobalOnline(Header.getTarget(),
							Header.getSource(), Message, Header.getID());
				}
			}
		} else if (Header.getType() == ExchangeProtocol.GroupLeave) {
			if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {
				if (this.clientListen != null) {
					BaseMessage Message = new BaseMessage(
							Encoding.byteToString(Body));
					clientListen.OnGlobalGroupLeave(Header.getTarget(),
							Header.getSource(), Message, Header.getID());
				}
			}
		} else if (Header.getType() == ExchangeProtocol.CenterMessage) {
			if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {

				clientListen.OnCenterMessage(
						Header.getTarget(),
						Header.getSource(),
						Body != null ? (new BaseMessage(Encoding
								.byteToString(Body))) : null,
						Header.getID(),
						Other != null ? (new BaseMessage(Encoding
								.byteToString(Other))) : null);

			}
		} else if (Header.getType() == ExchangeProtocol.RpcMessage) {
			if (Body == null || Body.length == 0) {
				return;
			}
			System.out.println(Encoding.byteToString(Body));
			BaseMessage Message = new BaseMessage(Encoding.byteToString(Body));
			/**
			 * by zzw
			 * 
			 * */

			// System.out.println("是Message信息，抛出去接受吧");
			if (this.clientListen != null) {

				if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {
					clientListen.OnGlobalRecvMessage(Header.getTarget(),
							Header.getSource(), Message, Header.getID());
				} else {
					clientListen.OnRecvMessage(Message);
				}
			}
		}

	}

	public final boolean ConnectExchange(String Ip, int port) throws Exception {
		return _Base.Connect(Ip, port);
	}

	// // 咱们这里抛到外面比较方便，重新连接
	// public boolean ConnectAgain() {
	// // 为了防止意外，我们注销一次
	// try {
	// this._Base.OnDisConnect(null);
	// } catch (Exception e) {
	// }
	// clientListen.OnAgainConnect();
	// return true;
	// }

	/**
	 * 发送咱们是传说的服务器版客服端标示
	 * */
	public boolean CreateClient(String targetSessionid) {
		if (!_IsConnectExchange) {
			return false;
		}

		_Base.SendMessage(Encoding.StringToByte(_SessionId+targetSessionid),
				ExchangeProtocol.NewServerClinet, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);

		return true;
	}

	public boolean CreateClient() {
		if (!_IsConnectExchange) {
			return false;
		}

		_Base.SendMessage(Encoding.StringToByte(_SessionId),
				ExchangeProtocol.ServerClinet, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);

		return true;
	}

	
	
	public final boolean CreateGroup(String GroupName) {
		if (!_IsConnectExchange) {
			return false;
		}

		// _Base.SendMessage(Encoding.ASCII.GetBytes(GroupName.toCharArray()),
		// ExchangeProtocol.CreateGroup, BaseProtocol.HightPriority,
		// BaseProtocol.OptionNone);
		_Base.SendMessage(Encoding.StringToByte(GroupName),
				ExchangeProtocol.CreateGroup, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);

		return true;
	}

	public final boolean LeaveGroup(String GroupName) {
		if (!_IsConnectExchange) {
			return false;
		}

		// _Base.SendMessage(Encoding.ASCII.GetBytes(GroupName.toCharArray()),
		// ExchangeProtocol.LeaveGroup, BaseProtocol.HightPriority,
		// BaseProtocol.OptionNone);
		_Base.SendMessage(Encoding.StringToByte(GroupName),
				ExchangeProtocol.LeaveGroup, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);

		return true;
	}

	public final boolean JoinGroup(String GroupName) {
		if (!_IsConnectExchange) {
			return false;
		}

		// _Base.SendMessage(Encoding.ASCII.GetBytes(GroupName.toCharArray()),
		// ExchangeProtocol.JoinGroup, BaseProtocol.HightPriority,
		// BaseProtocol.OptionNone);
		_Base.SendMessage(Encoding.StringToByte(GroupName),
				ExchangeProtocol.JoinGroup, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);

		return true;
	}

	public final String GetSessionId() {
		return _SessionId;
	}

	public final void SetSessionId(String SessionId) {
		_SessionId = SessionId;
	}

	public final SocketAddress GetPeerEndPoint() {
		return _Base.getRemoteEndPoint();
	}

	public final void SendMessage(byte[] message) {
		if (!_IsConnectExchange) {
			return;
		}

		if (!_Base.getActive()) {
			return;
		}

		if (message == null || message.length == 0) {
			return;
		}

		_Base.SendMessage(message, ExchangeProtocol.TransParentData,
				BaseProtocol.NormalyPriority, BaseProtocol.OptionNone, null,
				null, null, null);
	}

	public final boolean SendMessage(IMessage message) {
		if (!_IsConnectExchange) {
			return false;
		}

		if (!_Base.getActive()) {
			return false;
		}

		String Str = message.Serilize();
		// byte[] Data = Encoding.ASCII.GetBytes(Str.toCharArray());
		byte[] Data = Encoding.StringToByte(Str);

		System.out.println("SendMessage Message:" + Str + " Length:"
				+ Data.length);

		_Base.SendMessage(Data, ExchangeProtocol.RpcMessage,
				BaseProtocol.NormalyPriority, BaseProtocol.OptionNone, null,
				null, null, null);

		return true;
	}

	public final IMessage RecvMessage() {
		return null;
	}

	@Override
	public IExchangeServer getExchangeServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void OnAgainConnect() {
		// TODO Auto-generated method stub
		if (clientListen != null) {

			clientListen.OnAgainConnect();
		}
	}

	@Override
	public boolean GobalSendMessage(IMessage message, String target,
			String source, String uid) {
		if (source == null)
			source = this._SessionId;
		try {
			System.out.println("============我发往父节点之前的数据是：=============");
			System.out.println("target=" + target);
			System.out.println("内容=" + message.Serilize());
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.RpcMessage, BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup, target, source, uid, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 中心和中心之间的通话
	@Override
	public void CenterSendMessage(IMessage bodyMessage, String target,
			String source, String uid, IMessage otherMessage) {
		if (source == null)
			source = this._SessionId;
		if(uid==null || uid.equals("")) {
			
			uid = Encoding.getUuid();
			
//			DuplicateCheck.getDuplicateCheck().centerMessageAdd(uid);
		}
		// System.out.println("本服务器中心向上级发送:" + source + " :"
		// + otherMessage.Serilize() + "目标" + target);
		try {
			_Base.SendMessage(
					bodyMessage != null ? Encoding.StringToByte(bodyMessage
							.Serilize()) : null,
					ExchangeProtocol.CenterMessage,
					BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup,
					target,
					source,
					uid,
					otherMessage != null ? Encoding.StringToByte(otherMessage
							.Serilize()) : null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean GobalOnline(Boolean onflag, String source, String userid,
			String uid) {
		BaseMessage message = new BaseMessage();
		message.AddParam("OnLine", onflag);
		message.AddParam("SessionID", userid);
		String target = "ALL";
		if (source == null)
			source = this.GetSessionId();
		try {
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.Online, BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup, target, source, uid, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void GobalGroupLeave(Boolean onflag, String source, String userid,
			String groupname, String uid) {

		BaseMessage message = new BaseMessage();
		message.AddParam("GroupLeave", onflag);
		message.AddParam("SessionID", userid);
		message.AddParam("GroupID", groupname);
		System.out.println("@@@通知上级" + message.Serilize());

		String target = "ALL";
		if (source == null)
			source = this._SessionId;
		try {
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.GroupLeave, BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup, target, source, uid, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onActiveFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRemoteIP() {
		String tr = null;
		String str = this.GetPeerEndPoint().toString();
		if (str != null) {
			String[] as = str.split(":");
			if (as != null && as.length > 1)
				tr = as[0];
			if (tr != null && tr.length() > 1)
				tr = tr.substring(1);
		}
		return tr;
	}

	@Override
	public void SendBeatAlive() {
		// TODO Auto-generated method stub
		this._Base.SendBeatAlive();
	}

	@Override
	public void ServerClinet(IMessage message) {
		
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public void setError() {
	}

	// C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
	// public event OnRecvMessageEvent OnRecvMessage;
}
