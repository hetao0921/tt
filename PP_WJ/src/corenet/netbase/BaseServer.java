package corenet.netbase;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;

import corenet.netbase.Interface.BaseClientListen;
import corenet.netbase.Interface.IEventHandler;
import corenet.netbase.Interface.IReactor;

public class BaseServer implements  IEventHandler{
	private ServerSocketChannel socketChannel;
	private IReactor reactor = NIOReactor.defaultReactor();
	
    private BaseClientListen bsl;
    
	public BaseClientListen getBsl() {
		return bsl;
	}

	public void setBsl(BaseClientListen bsl) {
		this.bsl = bsl;
	}

	public BaseServer() {
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
				if (bsl != null) {
					bsl.OnNewConnection(this.session, this.connected);
				}
				reactor.runInReactor(new ActiveReadAction(session));
			}
		}
		
		NIOReactor.defaultDispatcher().runInReactor(new NewConnectionAction(session, connected));
	}
 
	
	@Override
	public void handleIO(SelectionKey selectionKey, Object userContext) {
		try {
			if (selectionKey.isValid() && selectionKey.isAcceptable()) {
				SocketChannel clientSocketChannel = socketChannel.accept();
				clientSocketChannel.configureBlocking(false);
				BaseSession session = new BaseSession(clientSocketChannel);
				
				session.setActive(true, false);
				
				//if (bsl != null) {
				//	bsl.OnNewConnection(session, true);
				//}
				OnNewConnection(session, true);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public final boolean StartUp(String ip, int port)
	{
		try {
		
			if(ip == null || ip.equals("")) {
				ip = InetAddress.getLocalHost().getHostAddress();
			}
			
			socketChannel = ServerSocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.socket().bind(new InetSocketAddress(ip, port));

			reactor.register(socketChannel, SelectionKey.OP_ACCEPT, this, null);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	@Override
	public void handleError(Exception e, Object userContext) {
		
	}
	
	
	public static void main(String[] args) {
		BaseServer server = new BaseServer();
		server.StartUp("0.0.0.0", 9901);
	}
}
