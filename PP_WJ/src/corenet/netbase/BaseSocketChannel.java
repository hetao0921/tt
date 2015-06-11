package corenet.netbase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.IOHandler;

/**
 * by zzw ÓÃÀŽ¶ÔÍšµÀœøÐÐÊÂŒþ×¢²á£šseverºÍclientµÄÔÚÕâÀï¿ªÊŒ¶ŒÒ»ÑùµÄÁË £©
 * */ 
public class BaseSocketChannel {

	private SocketChannel sc;

	public BaseSocketChannel(SocketChannel sc2) {
		// TODO Auto-generated constructor stub
		this.sc = sc2;
	}

	public BaseSocketChannel() {
		// TODO Auto-generated constructor stub
		try {
			this.sc = SocketChannel.open();
//			this.sk = SelectorFactory.getFactory().getSelector();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSc(SocketChannel sc) {
		this.sc = sc;
	}


	public SocketChannel getSc() {
		return sc;
	}



	// ÍùÍšµÀÉÏ×¢²áreceiveÊÂŒþ
	public void BeginReceive(IOHandler ih, AsyncBuffer ab) {
		try {
			
			
//			System.out.println("========================||"+ab.getSize());
//			LogUtil.debug("ÕâºóÃæ»á·¢ÉúÒ»ŽÎread²Ù×÷");
//			this.sc.configureBlocking(false);
//			
//			this.sc.register(sk, SelectionKey.OP_READ,
//					new IAsyncResult(ih, ab));
//			sk.wakeup();
			SelectorFactory.getFactory().addKeyOpts(sc, SelectionKey.OP_READ,new IAsyncResult(ih, ab));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("beginreceive"+e.getMessage());
		}
	}

	public void BeginSend(IOHandler ih, AsyncBuffer ab) {
		// TODO Auto-generated method stub
		try {
//			this.sc.configureBlocking(false);
//			
//			this.sc.register(sk, SelectionKey.OP_WRITE,
//					new IAsyncResult(ih, ab));
//			sk.wakeup();
			SelectorFactory.getFactory().addKeyOpts(sc, SelectionKey.OP_WRITE,new IAsyncResult(ih, ab));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("beginSend"+e.getMessage());
		}

	}

	public void BeginConnect(String host, int port,IOHandler ih,AsyncBuffer ab) {
		// TODO Auto-generated method stub
		try {
//			System.out.println("!!!!!!!!!"+host +port);
//		sc.configureBlocking(false);
//		sc.register(sk, SelectionKey.OP_CONNECT,new IAsyncResult(ih,ab));
//		sc.connect(new InetSocketAddress(host,port));	
			SelectorFactory.getFactory().addKeyOpts(sc, SelectionKey.OP_CONNECT,new IAsyncResult(ih, ab));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("beginConnect"+e.getMessage());
		}
		
	}

	public void EndConnect(IOHandler ih,AsyncBuffer ab) {
		// TODO Auto-generated method stub
		try {
//           sc.close();
//           sc.keyFor(sk).cancel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("EndConnect"+e.getMessage());
		}
		;
	}
	

	// ÍùÍšµÀÉÏ×¢²á sendÊÂŒþ

}
