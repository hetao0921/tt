package corenet.exchange;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.LinkedList;

import org.misc.log.LogUtil;

import corenet.exchange.Interface.RecvMessage;
import corenet.netbase.*;
import corenet.netbase.Interface.*;
import corenet.rpc.*;

public class ExchangeChannel implements IChannel, BaseSessionListen {
	private BaseSession _Base;
	private String _SessionId;
	private RecvMessage rm;

	public void cannel() {
		_Base.cannel();
	}

	public RecvMessage getRm() {
		return rm;
	}

	public void setRm(RecvMessage rm) {
		this.rm = rm;
	}

	private IExchangeServer ExchangeServer;

	public IExchangeServer getExchangeServer() {
		return ExchangeServer;
	}

	public ExchangeChannel(BaseSession Base, IExchangeServer ExchangeServer) {
		isError = false;
		this._Base = Base;
		this.ExchangeServer = ExchangeServer;

		// C# TO JAVA CONVERTER TODO TASK: Java has no equivalent to C#-style
		// event wireups:
		/**
		 * by zzw 事件绑定，这个C#和java的 编写模式不同。 在这里将接口的实现 实例化，并且传送进去。
		 * */
		// _Base.OnReadMessage += OnReadMessageEvent;

		_Base.setBsl(this);

		// System.out.println("============"+this.getRemoteIP());
	}

	/**
	 * by zzw 为了剧情需要，OnReadMessageEvent 改名成为 OnReadMessage
	 * */
	public void OnReadMessage(BaseSession Session, BaseHeader Header,
			byte[] aBytes) {
		// 这里的body是2个信息
		
		byte[] Body = null;
		byte[] Other = null;
		if (aBytes != null) {
			if (Header.getBodyLength() > 0)
				Body = Arrays.copyOfRange(aBytes, 0, Header.getBodyLength());
			if (Header.getOther() > 0) {
				Other = Arrays.copyOfRange(aBytes, Header.getBodyLength(),
						Header.getBodyLength() + Header.getOther());
			}
		}

		if (Header.getType() == ExchangeProtocol.CreateSession) {
			LogUtil.SessionInfo("  U can see @@@create :  now");
			if (Header.IsOptionSet(ExchangeProtocol.WithSessionId)) {
				if (Body == null || Body.length == 0) {
					throw new RuntimeException("没有指定回话id");
				}
				/**
				 * by zzw
				 * 
				 * 将字节组，用 US-ASCII 转换成字符串。
				 * */
				// _SessionId = Encoding.ASCII.GetString(Body, 0, Body.length);
				try {
					_SessionId = Encoding.byteToString(Body);
					LogUtil.SessionInfo("@@@create :" + _SessionId
							+ "  带sessionid,回复通知");
					_Base.SendMessage(null, ExchangeProtocol.CreateSession,
							BaseProtocol.HightPriority,
							BaseProtocol.OptionNone, null, null, null, null);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				_SessionId = SessionIdGen.GetSessionId(this);

				/**
				 * by zzw
				 * 
				 * 将字符串再转换成byte[]
				 * 
				 * */

				// _Base.SendMessage(Encoding.ASCII.GetBytes(_SessionId.toCharArray()),
				// ExchangeProtocol.CreateSession, BaseProtocol.HightPriority,
				// ExchangeProtocol.WithSessionId);
				try {
					LogUtil.SessionInfo("@@@create :" + _SessionId
							+ "  无sessionid,回复通知");
					_Base.SendMessage(Encoding.StringToByte(_SessionId),
							ExchangeProtocol.CreateSession,
							BaseProtocol.HightPriority,
							ExchangeProtocol.WithSessionId, null, null, null,
							null);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if(!ExchangeServer.CreateSession(this)){
				this.cannel();
			};

		} else if (Header.getType() == ExchangeProtocol.DestroySession) {
			System.out.println("destroy by read");
			ExchangeServer.DestroySession(this);
		} else {
			if (Body == null && Other == null) {
				return;
			}
			/*
			 * by zzw
			 * 
			 * 所有的 Encoding.ASCII.GetString(Body, 0, Body.length) 用 new
			 * String(Body,"US-ASCII")替换
			 */

			try {
				if (Header.getType() == ExchangeProtocol.CreateGroup) {
					ExchangeServer.CreateGroup(this,
							Encoding.byteToString(Body));
				} else if (Header.getType() == ExchangeProtocol.JoinGroup) {
					ExchangeServer.JoinGroup(this, Encoding.byteToString(Body));
				} else if (Header.getType() == ExchangeProtocol.LeaveGroup) {
					ExchangeServer
							.LeaveGroup(this, Encoding.byteToString(Body));
				} else if (Header.getType() == ExchangeProtocol.ServerClinet) {
					ExchangeServer.ServerClinet(this,
							Encoding.byteToString(Body));
				} else if (Header.getType() == ExchangeProtocol.NewServerClinet) {
					ExchangeServer.newServerClinet(this,
							Encoding.byteToString(Body));
				} else if (Header.getType() == ExchangeProtocol.Online) {

					if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {

						ExchangeServer.OnGlobalOnline(Header.getTarget(),
								Header.getSource(),
								new BaseMessage(Encoding.byteToString(Body)),
								Header.getID());

					}

				} else if (Header.getType() == ExchangeProtocol.GroupLeave) {
					if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {

						ExchangeServer.OnGlobalGroupLeave(Header.getTarget(),
								Header.getSource(),
								new BaseMessage(Encoding.byteToString(Body)),
								Header.getID(), this);

					}
				} else if (Header.getType() == ExchangeProtocol.CenterMessage) {
					if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {
						// System.out.println("相关消息 :"+Encoding.byteToString(Other));
						ExchangeServer.OnCenterMessage(
								Header.getTarget(),
								Header.getSource(),
								Body != null ? (new BaseMessage(Encoding
										.byteToString(Body))) : null,
								Header.getID(),
								Other != null ? (new BaseMessage(Encoding
										.byteToString(Other))) : null, this);

					}
				}

				else if (Header.getType() == ExchangeProtocol.RpcMessage) {
					BaseMessage Message = null;
					try {
						LogUtil.debug(Encoding.byteToString(Body));
						Message = new BaseMessage(Encoding.byteToString(Body));
					} catch (RuntimeException e) {
						System.out.println("New BaseMesage exception: "
								+ e.getMessage());
					}

					// 这里使用的就是开始远程调用方法

					try {
						/**
						 * 先判断一下，是否为全局发送，如果是，这里就负责转发出去完毕。
						 * */

						if (Header.IsOptionSet(ExchangeProtocol.GlobalGroup)) {
							ExchangeServer.OnGobalRecive(Header.getTarget(),
									Header.getSource(), Message,
									Header.getID(), this);
						} else {
							ExchangeServer.ProcessMessage(this, Message);
						}
					} catch (Exception e) {
						
						e.printStackTrace();
						System.out.println(Body.length);
						System.out.println(Encoding.byteToString(Body));
						LogUtil.error("==服务器业务调用执行失败。" + e.toString());

					}

					/**
					 * by zzw
					 * 
					 * 事件侦听
					 * 
					 * */
					if (this.rm != null) {
						rm.OnRecvMessage(new BaseMessage(Encoding
								.byteToString(Body)));
					}
				} else if (Header.getType() == ExchangeProtocol.TransParentData) {
					ExchangeServer.ProcessData(this, Body);
				}
				
				
				
			} catch (Exception e) {

			}

		}
	}

	public final String GetSessionId() {
		return _SessionId;
	}

	public final SocketAddress GetPeerEndPoint() {
		return _Base.getRemoteEndPoint();
	}

	public final void SendMessage(byte[] message) {
		
		if(isError) {
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

		// _Base.SendMessage(Encoding.ASCII.GetBytes(message.Serilize().toCharArray()),
		// ExchangeProtocol.RpcMessage, BaseProtocol.NormalyPriority,
		// BaseProtocol.OptionNone);
		if (isError) {
			return true;
		}
		try {
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.RpcMessage, BaseProtocol.NormalyPriority,
					BaseProtocol.OptionNone, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 全网发送消息的方法:
	public final boolean GobalSendMessage(IMessage message, String target,
			String source, String uid) {
		if(isError) {
			return true;
		}
		
		if (source == null)
			source = ExchangeServer.getServerID();

		// System.out.println("本服务器发送:" + source + " :" + message.Serilize()
		// + "目标" + target);

		try {
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.RpcMessage, BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup, target, source, uid, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 全网通知上下线情况
	public final boolean GobalOnline(Boolean onflag, String source,
			String userid, String uid) {
		if (isError) {
			return true;
		}
		// System.out.println("如果没进来，表明没更新的说");

		BaseMessage message = new BaseMessage();
		message.AddParam("OnLine", onflag);
		message.AddParam("SessionID", userid);
		String target = "ALL";
		if (source == null)
			source = ExchangeServer.getServerID();
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

	// 全网通知组离开和进入消息
	public final void GobalGroupLeave(Boolean onflag, String source,
			String userid, String groupname, String uid) {
		if(isError) {
			return;
		}
		
		if(this._SessionId.contains("@006") && userid.contains("@006")) {
			return;
		}else {
			System.out.println(this._SessionId);
			System.out.println(userid);
		}
		
		BaseMessage message = new BaseMessage();
		message.AddParam("GroupLeave", onflag);
		message.AddParam("SessionID", userid);
		message.AddParam("GroupID", groupname);
		// System.out.println("@@@通知下级"+message.Serilize());
		String target = "ALL";
		if (source == null)
			source = ExchangeServer.getServerID();
		try {
			_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
					ExchangeProtocol.GroupLeave, BaseProtocol.NormalyPriority,
					ExchangeProtocol.GlobalGroup, target, source, uid, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 中心和中心之间的通话
	public void CenterSendMessage(IMessage bodyMessage, String target,
			String source, String uid, IMessage otherMessage) {
		if (isError) {
			return;
		}

		if (source == null)
			source = ExchangeServer.getServerID();

		// System.out.println("本服务器中心向下发送:" + source + " :"
		// + otherMessage.Serilize() + "目标" + target);
		if (uid == null || uid.trim().equals("")) {
			uid = Encoding.getUuid();
			// DuplicateCheck.getDuplicateCheck().centerMessageAdd(uid);
		}

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

	}

	public final IMessage RecvMessage() {
		return null;
	}

	@Override
	public void OnAgainConnect() {
		// TODO Auto-generated method stub
		// 跑送出去，说明这个家伙断线了
		if (ExchangeServer != null) {
			ExchangeServer.OnAgainConnect(this);
		}
	}

	@Override
	public void onActiveFlag() {
		// TODO Auto-generated method stub
		if (ExchangeServer != null) {
			ExchangeServer.onActiveFlag(this);
		}
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
		if(isError) {
			return;
		}
		this._Base.SendBeatAlive();
	}

	@Override
	public void ServerClinet(IMessage message) {
		_Base.SendMessage(Encoding.StringToByte(message.Serilize()),
				ExchangeProtocol.ServerClinet, BaseProtocol.HightPriority,
				BaseProtocol.OptionNone, null, null, null, null);
	}

	/**
	 * 询问该通道是否接收到错误信息。
	 * 
	 * @return ture 表明该通道已经不可信。
	 * */

	private boolean isError;

	public boolean isError() {
		return isError;
	}

	public void setError() {
		isError = true;
	}

	// C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
	// public event OnRecvMessageEvent OnRecvMessage;
}
