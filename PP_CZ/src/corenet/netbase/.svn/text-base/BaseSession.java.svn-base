package corenet.netbase;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

import corenet.netbase.Interface.IChannel;
import corenet.netbase.Interface.IEventHandler;
import corenet.netbase.Interface.IReactor;
import corenet.netbase.Interface.ITimer;
import org.misc.log.LogUtil;
import corenet.netbase.Interface.BaseSessionListen;
import java.util.LinkedList;

class FramedMessage {
	private BaseHeader header;
	private byte[] body;;

	FramedMessage(BaseHeader _header, byte[] _body) {
		this.header = _header;
		this.body = _body;
	}

	BaseHeader getHeader() {
		return header;
	}

	byte[] getBody() {
		return body;
	}

}

class WriteCount implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

public class BaseSession implements IEventHandler, Runnable {

	private final int limit = 64;

	private IReactor reactor = NIOReactor.defaultReactor();
	private ITimer keepAliveTimer = reactor.newTimer(this);
	private ConcurrentLinkedQueue<ByteBuffer> sendQueue = new ConcurrentLinkedQueue<ByteBuffer>();

	private ConcurrentLinkedQueue<FramedMessage> recvQueue = new ConcurrentLinkedQueue<FramedMessage>();
	boolean messageInDispatch = false;

	private boolean active;

	private SocketChannel socketChannel;
	BaseSessionListen bsl;

	private ByteBuffer readBuffer = ByteBuffer.allocate(4096);
	private long lastAliveTime;
	private boolean readActive;

	private static final long KeepAliveTimeout = 60L * 1000L * 1000L * 1000L;

	public BaseSession(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		active = true;
	}

	public BaseSession() {

	}

	public BaseSessionListen getBsl() {
		return bsl;
	}

	public void setBsl(BaseSessionListen bsl) {
		this.bsl = bsl;
	}

	public void setReadActive(boolean _readActive) {
		readActive = _readActive;
		if (readActive) {
			lastAliveTime = System.nanoTime();
			reactor.register(socketChannel, SelectionKey.OP_READ, this, null);
		} else {
			reactor.unregister(socketChannel, SelectionKey.OP_READ);
		}
	}

	public void setActive(boolean value, boolean readActive) {
		active = value;
		if (active) {
			lastAliveTime = System.nanoTime();
			keepAliveTimer.schedule(5000);
			if (readActive) {
				setReadActive(true);
			}
		}
	}

	// public void setActive(boolean value) {
	// active = value;
	// if (active) {
	// setReadActive(true);
	// lastAliveTime = System.nanoTime();
	// keepAliveTimer.schedule(5000);
	// }
	// }

	public void cannel() {
		if (active == false)
			return;

		LogUtil.SessionInfo("========session cannel" + this.getRemoteEndPoint());

		class CancelAction implements Runnable {
			public void run() {
				OnDisConnect(null);
			}
		}
		reactor.runInReactor(new CancelAction());
	}

	public void Attach(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public final boolean getActive() {
		return active;
	}

	private final BaseHeader decodeHeader(ByteBuffer buffer) {
		int startPosition = buffer.position();
		int currentPosition = buffer.position();
		byte[] array = buffer.array();

		while (buffer.remaining() >= BaseProtocol.HeadSize) {
			if (BaseHeader.validateMagic(array, currentPosition)
					&& BaseHeader.validateVer(array, currentPosition)) {
				buffer.position(currentPosition + BaseProtocol.HeadSize);
				BaseHeader header = new BaseHeader();
				System.arraycopy(array, currentPosition, header.getData(), 0,
						BaseProtocol.HeadSize);
				return header;
			}
			LogUtil.sessionException(new Exception(
					"receive data error, read next"));
			buffer.position(++currentPosition);
		}

		// buffer.position(startPosition);
		return null;
	}

	private void dispatchMessage(BaseHeader header, byte[] body) {
		class OnRead implements Runnable {
			BaseSession session;
			BaseHeader header;
			byte[] body;

			public OnRead(BaseSession session) {
				this.session = session;
			}

			public void run() {
				int count = 128;

				while (recvQueue.size() > 0 && count > 0) {
					--count;
					FramedMessage message = recvQueue.poll();

					if (session.bsl != null && session.active) {
						session.bsl.OnReadMessage(session, message.getHeader(),
								message.getBody());
					} else {
						break;
					}
				}

				boolean needReRun = false;
				synchronized (recvQueue) {
					if (recvQueue.size() > 0 && session.active)
						needReRun = true;
					else
						session.messageInDispatch = false;
				}

				if (needReRun) {
					// NIOReactor.defaultDispatcher().runInReactor(new
					// OnRead(session));
					NIOReactor.defaultDispatcher().runInReactor(this);
				}
			}
		}

		boolean needNotify = false;
		FramedMessage message = new FramedMessage(header, body);
		recvQueue.add(message);

		synchronized (recvQueue) {
			if (!messageInDispatch) {
				needNotify = true;
				messageInDispatch = true;
			}
		}

		if (needNotify) {
			NIOReactor.defaultDispatcher().runInReactor(new OnRead(this));
		}

	}

	private final void parseMessage() {
		BufferHelper.flipToRead(readBuffer);
		for (;;) {
			byte[] body;
			BaseHeader header = decodeHeader(readBuffer);
			if (header != null) {
				int bodyLength = header.getBodyLength() + header.getOther();
				if (bodyLength <= readBuffer.remaining()) {
					body = new byte[bodyLength];
					readBuffer.get(body, 0, bodyLength);

					// if (false) {
					// System.out.println("parse message " + bodyLength);
					// for (int i = 0; i < body.length; i++) {
					// System.out.print(body[i] + " ");
					// }
					// System.out.println("");
					// }

					if (bsl != null) {
						// bsl.OnReadMessage(this, header, body);
						dispatchMessage(header, body);
					}
					continue;
				} else {
					readBuffer.position(readBuffer.position()
							- BaseProtocol.HeadSize);
					readBuffer = BufferHelper.realloc(readBuffer, bodyLength
							+ BaseProtocol.HeadSize);
					break;
				}

			} else {
				if (readBuffer.remaining() == 0)
					readBuffer.clear();
				else
					BufferHelper.crunch(readBuffer);
				break;
			}
		}
	}

	private void testParseMessage() {
		byte[] body, wrong;
		System.out.println("HeadSize " + BaseProtocol.HeadSize);
		BaseHeader header = new BaseHeader();
		header.setBodyLength(3);

		readBuffer = ByteBuffer.allocate(BaseProtocol.HeadSize * 2 + 3 + 4 + 5
				+ 7);

		readBuffer.put(header.getData(), 0, BaseProtocol.HeadSize);
		body = new byte[] { 1, 2, 3 };
		readBuffer.put(body, 0, body.length);

		wrong = new byte[] { (byte) 'x', (byte) 'x', (byte) 'x', (byte) 'x',
				(byte) 'x' };
		readBuffer.put(wrong, 0, wrong.length);

		header.setBodyLength(4);
		readBuffer.put(header.getData(), 0, BaseProtocol.HeadSize);
		body = new byte[] { 4, 5, 6, 7 };
		readBuffer.put(body, 0, body.length);

		header.setBodyLength(4);
		readBuffer.put(header.getData(), 0, 7);

		parseMessage();

		readBuffer.put(header.getData(), 7, BaseProtocol.HeadSize - 7);
		body = new byte[] { 8, 9, 10, 11 };
		readBuffer.put(body, 0, body.length);

		header.setBodyLength(32);
		readBuffer.put(header.getData(), 0, BaseProtocol.HeadSize);
		body = new byte[] { 12, 13, 14, 15 };
		readBuffer.put(body, 0, body.length);

		parseMessage();

		body = new byte[32 - 4];

		body[25] = 10;
		body[26] = 20;
		body[27] = 30;
		readBuffer.put(body, 0, body.length);

		parseMessage();

		header.setBodyLength(0);
		readBuffer.put(header.getData(), 0, BaseProtocol.HeadSize);

		header.setBodyLength(3);
		readBuffer.put(header.getData(), 0, BaseProtocol.HeadSize);
		body = new byte[] { 1, 2, 3 };
		readBuffer.put(body, 0, body.length);

		parseMessage();

	}

	private void OnAgainConnect() {
		NIOReactor.defaultDispatcher().runInReactor(new Runnable() {
			public void run() {
				if (bsl != null) {
					bsl.OnAgainConnect();
				}
			}
		});
	}

	private void doRead() {
		try {
			int n = -1;
			int m = 0;

			while ((n = socketChannel.read(readBuffer)) > 0) {
				parseMessage();
				m = m + n;

			}
			if (n == -1) {
				OnDisConnect(null);
				OnAgainConnect();
			} else {
				this.lastAliveTime = System.nanoTime();
			}

			PerformanceMonitor.getInstance().addReciveBytes(m);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.BusinessError("read error" + e.getMessage() + " | "
					+ this.getRemoteEndPoint());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}
			OnDisConnect(null);
			OnAgainConnect();
		}
	}

	public final SocketAddress getRemoteEndPoint() {
		return socketChannel.socket().getRemoteSocketAddress();
	}

	private void doWrite() {

		ByteBuffer buffer = null;
		try {
			int writeNum = 0;

			while ((buffer = sendQueue.peek()) != null) {

				int n = this.socketChannel.write(buffer);
				writeNum = writeNum + n;

				// LogUtil.DebugInfo("socket send  " + n +
				// "ip = "+this.getRemoteEndPoint());

				if (!buffer.hasRemaining()) {
					sendQueue.poll();
				} else {
					// LogUtil.BusinessError("=error==wait=now wirte 0:   " +
					// n);
					return;
				}
			}
			PerformanceMonitor.getInstance().addWriteBytes(writeNum);
			synchronized (sendQueue) {
				if (sendQueue.isEmpty())
					reactor.unregister(socketChannel, SelectionKey.OP_WRITE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.BusinessError("send error" + e.getMessage() + " | "
					+ this.getRemoteEndPoint());
			for (StackTraceElement s : e.getStackTrace()) {
				LogUtil.BusinessError("error by ==" + s.toString());
			}
			OnDisConnect(null);
			if (bsl != null) {
				bsl.OnAgainConnect();
			}
		}
	}

	public final void SendBeatAlive() {
		if (this.sendQueue.size() > 0) {
			return;
		}
		SendMessage(null, BaseProtocol.BeatAlive, BaseProtocol.NormalyPriority,
				BaseProtocol.OptionNone, null, null, null, null);
	}

	public final void SendMessage(byte[] Data, int Type, int Priority,
			short Option, String target, String source, String uid, byte[] Other) {

		BaseHeader Header = new BaseHeader();
		Header.setType((short) Type);
		Header.setPriority((byte) Priority);
		Header.setBodyLength(Data == null ? 0 : Data.length);
		Header.setOther(Other == null ? 0 : Other.length);
		Header.AddOption(Option);
		if (target != null) {
			Header.setTarget(target);
		}
		if (source != null) {
			Header.setSource(source);
		}
		if (uid != null) {
			Header.setID(uid);
		}

		SendMessage(Header, Data, Other);
	}

	/**
	 * 通锟斤拷send锟斤拷锟斤拷锟斤拷菁锟斤拷锟斤拷锟斤拷锟叫ｏ拷然锟斤拷锟酵★拷
	 * */
	public final void SendMessage(BaseHeader Header, byte[] Data, byte[] Other) {
		int n = Header.getData().length;
		if (Data != null)
			n = n + Data.length;
		if (Other != null)
			n = n + Other.length;

		ByteBuffer tempBuffer = ByteBuffer.allocate(n);
		tempBuffer.put(Header.getData());
		if (Data != null)
			tempBuffer.put(Data);
		if (Other != null)
			tempBuffer.put(Other);
		tempBuffer.flip();
		send(tempBuffer);
	}

	public void send(ByteBuffer buffer) {
		if (buffer == null) {
			return;
		}

		try {
			if (active) {
				synchronized (sendQueue) {
					sendQueue.add(buffer);
					if (sendQueue.size() == 1) {
						reactor.register(socketChannel, SelectionKey.OP_WRITE,
								(BaseSession) this, null);
					}
				}
			} else {
				return;
			}
		} catch (Exception e) {
			OnDisConnect(null);
			LogUtil.sessionException(e);
			if (bsl != null) {
				bsl.OnAgainConnect();
			}
		}
	};

	private void clearInOut() {
		this.sendQueue.clear();
		this.readBuffer.clear();
	}

	private void exchangeDestroySession() {
		class DestroySessionAction implements Runnable {
			public void run() {
				try {
					IChannel iChannel = (IChannel) getBsl();
					iChannel.getExchangeServer().DestroySession(iChannel);

				} catch (Exception e) {
					LogUtil.SessionInfo("DestroySession Exception!"
							+ e.getCause());
					LogUtil.sessionException(e);
				}
			}
		}
		NIOReactor.defaultDispatcher().runInReactor(new DestroySessionAction());
	}

	private void closeSocket() {
		try {
			SelectionKey selectionKey = socketChannel.keyFor(reactor
					.getSelector());
			if (selectionKey != null) {
				selectionKey.cancel();
			}
		} catch (Exception e) {
			LogUtil.sessionException(e);
		}

		try {
			this.socketChannel.close();
		} catch (Exception e) {
			LogUtil.sessionException(e);
		}
	}

	public void OnDisConnect(SocketChannel d) {
		try {
			if (!active)
				return;
			active = false;

			// 鍙栨秷淇濇椿瀹氭椂鍣�
			keepAliveTimer.cancel();

			// 娓呴櫎杈撳叆銆佽緭鍑烘暟鎹�
			clearInOut();

			// exchange閿�瘉浼氳瘽
			exchangeDestroySession();

			// 鏈�悗锛屽叧闂鎺ュ瓧
			closeSocket();

		} catch (Exception e) {
			LogUtil.SessionInfo("session disconnect !" + e.getCause());
			LogUtil.sessionException(e);
		}
	}

	@Override
	public void handleIO(SelectionKey selectionKey, Object userContext) {
		if (selectionKey.isValid() && selectionKey.isReadable()) {
			doRead();
		}
		if (selectionKey.isValid() && selectionKey.isWritable()) {
			doWrite();
		}
	}

	@Override
	public void handleError(Exception e, Object userContext) {
		LogUtil.error("BaseSession handleError");
		for (StackTraceElement s : e.getStackTrace()) {
			LogUtil.error("error by ==" + s.toString());
		}
		OnDisConnect(null);
		OnAgainConnect();
	}

	@Override
	public void run() {
		if (!active)
			return;

		// 连接是否存活
		boolean isAlive = true;

		// 只有读操作激活，才检测心跳
		if (readActive) {
			long currentTime = System.nanoTime();
			long durTime = currentTime - this.lastAliveTime;

			// 保活检测超时关闭连接，并通知重连
			if (durTime > KeepAliveTimeout) {
				isAlive = false;
				LogUtil.warn("keep alive timeout");
				OnDisConnect(null);
				OnAgainConnect();
			}
		}

		// 如果连接已死，在上面OnDisConnect中被关闭，此时不用发送心跳
		if (isAlive) {
			this.SendBeatAlive();
			this.keepAliveTimer.schedule(5000);
		}
	}

	public static void main(String[] args) {
		BaseSession session = new BaseSession();
		session.testParseMessage();
	}

}
