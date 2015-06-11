package corenet.netbase;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.IOHandler;

public class BaseSelectorThreed extends Thread {

	/**
	 * @param args
	 */

	private BaseServerSocketChannel bssc = null;

	public BaseSelectorThreed(BaseServerSocketChannel bssc) {
		this.bssc = bssc;
		this.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Ñ­»·£¬¿ªÊŒŽŠÀíž÷ÖÖ×¢²áÊÂŒþ¡£
		try {
			while (true) {
				Selector se = bssc.getSe();
			    int n =	se.select();
			    if(n<=0) continue ;
				Set<SelectionKey> set = se.selectedKeys();
				Iterator<SelectionKey> it = set.iterator();
				while (it.hasNext()) {
					try {
						SelectionKey sk = it.next();
						it.remove();
						if (sk.isAcceptable()) {
							// œšÁ¢ÐÂµÄÁ¬œÓ£¬×öÊ²ÃŽŽŠÀí£¿
							// System.out.println("ÓÐÐÂÁ¬œÓ");

							SocketChannel sc;
							sc = bssc.getSsc().accept();
							sc.configureBlocking(false);

							LogUtil.debug("连接请求" + sc);

		                 IOHandler ioHandler = ((IAsyncResult) sk
									.attachment()).getIoHandler();
							// d.attach(sk.attachment());							
							SelectionKey d = sc.register(se,
									SelectionKey.OP_READ);
							ioHandler.OnConnect(sc);
							
						}

						if (sk.isValid() && sk.isReadable()) {
							// ÓÐÊýŸÝŽ«ËÍ¹ýÀŽ£¬×öÊ²ÃŽŽŠÀí£¿
//							 System.out.println("有个新的读取" + sk.channel());
							// SocketChannel sc = (SocketChannel) sk.channel();
							// ByteBuffer buffer = ByteBuffer.allocate(1024);
							// long lflg = -1 ;
							// if(sc!=null && sc.isConnected()) {
							// lflg = sc.read(buffer);
							// }
							// if(lflg > 0) {
							//
							// buffer.flip();
							// System.out.println(new String(buffer.array()));
							// sc.register(se,SelectionKey.OP_WRITE);
							// }
							IAsyncResult iar = (IAsyncResult) sk.attachment();

							IOHandler ioHandler = iar.getIoHandler();
							ioHandler.OnRecievedData(iar.getAsyncBuffer());

						}
						if (sk.isValid() && sk.isWritable()) {
							// ·¢ËÍ
							 System.out.println("=================可以写了"+sk.channel());

							IAsyncResult iar = (IAsyncResult) sk.attachment();
							IOHandler ioHandler = iar.getIoHandler();
							ioHandler.OnSendData(iar.getAsyncBuffer());

							// SocketChannel sc = (SocketChannel) sk.channel();
							// ByteBuffer buffer = ByteBuffer.allocate(1024);
							//
							// if(sc!=null && sc.isConnected()) {
							// buffer.put("asdgfgssgsdgsd".getBytes());
							// buffer.flip();
							// sc.write(buffer);
							// }

						}
						// if(!sk.isValid()) {
						// //Èç¹ûÎÞÐ§£¬ÔòÔÚselctorÖÐÊ§Ð§¡£
						// IAsyncResult iar = (IAsyncResult)sk.attachment();
						// IOHandler ioHandler = iar.getIoHandler();
						// ioHandler.OnDisConnect(sk);
						// }

					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.error("监听错误,继续运行吧" + e.getMessage());
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("监听错误,没拦截到" + e.getMessage());

		}

	}

}
