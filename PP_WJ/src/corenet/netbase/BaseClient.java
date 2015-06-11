package corenet.netbase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.BaseClientListen;
import corenet.netbase.Interface.IEventHandler;
import corenet.netbase.Interface.IReactor;
 
public class BaseClient extends BaseSession implements IEventHandler {

	private SocketChannel socketChannel;

	BaseClientListen bcl;
	IReactor reactor = NIOReactor.defaultReactor();

	public BaseClientListen getBcl() {
		return bcl;
	}

	public void setBcl(BaseClientListen bcl) {
		this.bcl = bcl;
	}


	public BaseClient() {
		try{
			socketChannel = SocketChannel.open();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleError(Exception e, Object userContext) {
		
	}
	
	@Override
	public final void handleIO(SelectionKey selectionKey, Object userContext) {
		if (selectionKey.isValid() && selectionKey.isConnectable()) {
			if(socketChannel.isConnectionPending()) {
				try {
					socketChannel.finishConnect();
					OnConnect(null);
				} catch (IOException e) {
					LogUtil.sessionException(e);
				}
			}
			reactor.unregister(socketChannel, SelectionKey.OP_CONNECT);
		} else {
			super.handleIO(selectionKey, userContext);
		}
	}
	
	void sendTest(String msg) {
		byte[] body = msg.getBytes();
		
		BaseHeader header = new BaseHeader();
		header.setBodyLength(body.length);
		
		this.SendMessage(header, body, null);
	}
	
	public void OnNewConnection(BaseSession session, boolean connected) {
		class ActiveReadAction implements Runnable {
			private BaseSession session;
			ActiveReadAction(BaseSession _session) {
				this.session = _session;
			}
			@Override
			public void run() {
				if (session.getActive()) {
					session.setReadActive(true);
				}
			}
		}
		
		class NewConnectionAction implements Runnable {
			private BaseSession session;
			private boolean connected;
			public NewConnectionAction(BaseSession _session, boolean _connected) {
				this.session   = _session;
				this.connected = _connected;
			}
			
			@Override
			public void run() {
				if (bcl != null) {
					bcl.OnNewConnection(this.session, this.connected);
				}
				reactor.runInReactor(new ActiveReadAction(session));
			}
		}
		
		NIOReactor.defaultDispatcher().runInReactor(new NewConnectionAction(session, connected));
	}

	private void OnConnect(SocketChannel sk) {
		super.Attach(socketChannel);
		this.setActive(true, false);
		System.out.println("client Active");
		
		OnNewConnection(this, true);
		
		//if (bcl != null) {
		//	bcl.OnNewConnection(this, true);
		//}
		//sendTest("aha");
	}

	public final boolean Connect(String Host, int Port) throws Exception {
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(Host, Port));
		reactor.register(socketChannel, SelectionKey.OP_CONNECT, this, null);
		return true;
	}
	
	public static void main(String[] args) {
		BaseClient client = new BaseClient();
		try {
			client.Connect("192.168.1.118", 9901);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
