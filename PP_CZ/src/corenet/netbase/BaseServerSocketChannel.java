package corenet.netbase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import corenet.netbase.Interface.IOHandler;

public class BaseServerSocketChannel {

	private ServerSocketChannel ssc;
	private Selector se;
 
	public Selector getSe() {
		return se;
	}

	public ServerSocketChannel getSsc() {
		return ssc;
	}

//	public BaseServerSocketChannel() {
//		try {
//			ssc = ServerSocketChannel.open();
//			se = Selector.open();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public BaseServerSocketChannel(InetSocketAddress inetSocketAddress) {
		// TODO Auto-generated constructor stub

		try {
			ssc = ServerSocketChannel.open();
			se = Selector.open();
//			System.out.println("!!!!!!!!!!!!!!!!!!====================");
//			se = SelectorFactory.getFactory().getSelector();
			ssc.configureBlocking(false);
			ssc.socket().bind(inetSocketAddress);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	 public void BeginAccept(IOHandler ioHandler, Object listener) {
		// TODO Auto-generated method stub
		// œøÐÐ×¢²á£¬±íÊŸÕâžöÕìÌýžøÓè×¢²á.
		try {
//			this.ssc.configureBlocking(false);	
//			this.ssc.register(this.se, SelectionKey.OP_ACCEPT,);
            SelectorFactory.getFactory().addKeyOpts(ssc,SelectionKey.OP_ACCEPT,new IAsyncResult(ioHandler,null));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 
	 
	 
	 
	 
}
