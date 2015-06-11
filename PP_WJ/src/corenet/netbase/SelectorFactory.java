package corenet.netbase;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.IOHandler;

class RegElement {
	SelectableChannel sc;
	int keyOps;
	Object addObject;

	public RegElement(SelectableChannel sc, int n, Object obj) {
		this.sc = sc;
		this.keyOps = n;
		this.addObject = obj;
	}

}

/*
 * 解决通讯由于IO堵塞，导致的信息延迟。 暂定用三路复选器。
 */

// 所有的接收全在此线程
class AcceptThread extends Thread {
	private ConcurrentLinkedQueue<RegElement> regQueue;
	private ConcurrentLinkedQueue<RegElement> cencelQueue;
	private Selector selector;

	public void addKeyOpts(SelectableChannel sc, int n, Object o) {
		int m = regQueue.size();
		regQueue.add(new RegElement(sc, n, o));
		if (m == 0) {
			System.out.println("wakeup AcceptThread！！！！！！！！！！！！");
			// selector.wakeup();
		}
	}

	public void cenecl(SelectableChannel sc, int n, Object o) {
		cencelQueue.add(new RegElement(sc, n, o));
	}

	public void cancel(SelectableChannel sc) {
		try {
			sc.keyFor(selector).cancel();
		} catch (Exception e) {
		}

	}

	public AcceptThread() throws IOException {
		selector = SelectorProvider.provider().openSelector();
		regQueue = new ConcurrentLinkedQueue<RegElement>();
		cencelQueue = new ConcurrentLinkedQueue<RegElement>();
	}

	@Override
	public void run() {

		while (true) {
			try {
				// 取消各种需要注销的
				while (cencelQueue.peek() != null) {
					RegElement re = cencelQueue.poll();
					SelectionKey sk = re.sc.keyFor(selector);
					if (sk != null)
						sk.cancel();
				}

				// 添加各种注册事件。
				while (regQueue.peek() != null) {
					RegElement re = regQueue.poll();
					re.sc.configureBlocking(false);
					re.sc.register(selector, re.keyOps, re.addObject);

				}

				int n = selector.select(5000);
				if (n == 0)
					continue;
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey sk = it.next();

					it.remove();

					try {
						if (sk.isValid() && sk.isAcceptable()) {

							// 本身的session生成器
							ServerSocketChannel ssc = (ServerSocketChannel) sk
									.channel();
							SocketChannel sc = ssc.accept();
							LogUtil.SessionInfo("accept create in run"
									+ sc.socket().getRemoteSocketAddress());

							IOHandler ioHandler = ((IAsyncResult) sk
									.attachment()).getIoHandler();
							// System.out.println("========"+sc);
							ioHandler.OnConnect(sc);

						}
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.BusinessError("Accept处理错误,继续运行吧"
								+ e.getMessage());
						for (StackTraceElement s : e.getStackTrace()) {
							LogUtil.BusinessError("error by ==" + s.toString());
						}

					}

				}

			} catch (Exception e) {
				LogUtil.BusinessError("Accept selector出现错误!!!" + e.getCause());
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.BusinessError("error by ==" + s.toString());
				}

			}

		}

	}

}

// 所有的read全在此线程
class ReadThread extends Thread {
	private Selector selector;
	private ConcurrentLinkedQueue<RegElement> regQueue;
	private ConcurrentLinkedQueue<RegElement> cencelQueue;

	public void addKeyOpts(SelectableChannel sc, int n, Object o) {
		int m = regQueue.size();
		regQueue.add(new RegElement(sc, n, o));
		if (m == 0) {
			System.out.println("wakeup ReadThread！！！！！！！！！！！！");
			// selector.wakeup();
		}
	}

	public void cenecl(SelectableChannel sc, int n, Object o) {
		cencelQueue.add(new RegElement(sc, n, o));
	}

	public void cancel(SelectableChannel sc) {
		try {
			sc.keyFor(selector).cancel();
		} catch (Exception e) {
		}

	}

	public ReadThread() throws IOException {
		selector = SelectorProvider.provider().openSelector();
		regQueue = new ConcurrentLinkedQueue<RegElement>();
		cencelQueue = new ConcurrentLinkedQueue<RegElement>();

	}

	@Override
	public void run() {
		// 对selector进行监控
		while (true) {
			try {
				// 取消各种需要注销的
				while (cencelQueue.peek() != null) {
					RegElement re = cencelQueue.poll();
					SelectionKey sk = re.sc.keyFor(selector);
					if (sk != null)
						sk.cancel();
				}
				// 添加各种注册事件。
				while (regQueue.peek() != null) {
					RegElement re = regQueue.poll();
					re.sc.configureBlocking(false);
					re.sc.register(selector, re.keyOps, re.addObject);

				}

				int n = selector.select(100);
				if (n == 0)
					continue;
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					it.remove();
					try {
						if (sk.isValid() && sk.isReadable()) {

							IAsyncResult iar = (IAsyncResult) sk.attachment();
							IOHandler ioHandler = iar.getIoHandler();
							ioHandler.OnRecievedData(iar.getAsyncBuffer());

						}

						if (sk.isValid() && !sk.isReadable()) {
							System.out.println("掉网线了没有？");
						}
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.error("处理错误,继续运行吧" + e.getMessage());
					}

				}

			} catch (Exception e) {
				LogUtil.BusinessError("Read selector出现错误!!!" + e.getCause());
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.BusinessError("error by ==" + s.toString());
				}
			}

		}

	}

}

// 所有的write全在此线程
class WriteThread extends Thread {
	private Selector selector;
	private ConcurrentLinkedQueue<RegElement> regQueue;
	private ConcurrentLinkedQueue<RegElement> cencelQueue;

	public void addKeyOpts(SelectableChannel sc, int n, Object o) {
		int m = regQueue.size();
		regQueue.add(new RegElement(sc, n, o));
		if (m == 0) {
			System.out.println("wakeup WriteThread！！！！！！！！！！！！");
			// selector.wakeup();
		}
	}

	// public void cenecl(SelectableChannel sc, int n, Object o) {
	// cencelQueue.add(new RegElement(sc, n, o));
	// }
	//
	// public void cancel(SelectableChannel sc) {
	// try {
	// sc.keyFor(selector).cancel();
	// } catch (Exception e) {
	// }
	//
	// }

	public WriteThread() throws IOException {
		selector = SelectorProvider.provider().openSelector();
		regQueue = new ConcurrentLinkedQueue<RegElement>();
		cencelQueue = new ConcurrentLinkedQueue<RegElement>();
	}

	@Override
	public void run() {
		// 对selector进行监控
		while (true) {
			try {
				// 取消各种需要注销的
				// while (cencelQueue.peek() != null) {
				// RegElement re = cencelQueue.poll();
				// SelectionKey sk = re.sc.keyFor(selector);
				// if (sk != null)
				// sk.cancel();
				// }
				// 添加各种注册事件。

				List<RegElement> temp = new LinkedList<RegElement>();
				while (regQueue.peek() != null) {
					RegElement re = regQueue.poll();
					IAsyncResult iar = (IAsyncResult) re.addObject;
					IOHandler ioHandler = iar.getIoHandler();
					BaseSession a = (BaseSession) ioHandler;
					SocketAddress aocketAddress = a.getRemoteEndPoint();
					LogUtil.BusinessError("$$$Write reg" + aocketAddress);
					if (aocketAddress == null) {
						// 新增功能，如果为空，进行cannel；
						a.cannel();
						LogUtil.BusinessError("continue");
						continue;
					}
					try {
						if (re.sc.isBlocking())
							re.sc.configureBlocking(false);
						if (re.sc.isOpen())
							re.sc.register(selector, re.keyOps, re.addObject);

						// SelectionKey tk = re.sc.keyFor(selector);
						// if (tk == null) {
						// LogUtil.BusinessError("$$$Write tk == null");
						// re.sc.configureBlocking(false);
						// if (re.sc.isOpen())
						// re.sc.register(selector, re.keyOps,
						// re.addObject);
						// } else {
						// LogUtil.BusinessError("$$$Write tk != null");
						// temp.add(re);
						// //加一个取消方案
						// }
					} catch (Exception e) {

						/**
						 * 新增，在写的时候，如果注册失败 2012.04.29
						 * */
						// e.printStackTrace();
						// try {
						// IAsyncResult iar = (IAsyncResult) re.addObject;
						// IOHandler ioHandler = iar.getIoHandler();
						// ioHandler.OnSendData(iar.getAsyncBuffer());
						// } catch (Exception e1) {
						// LogUtil.BusinessError("Write error2"
						// + e1.getMessage());
						// for (StackTraceElement s : e1.getStackTrace()) {
						// LogUtil.BusinessError("error by ==" + s.toString());
						// }
						// }
						try {
							re.sc.close();
						} catch (Exception sce) {
							LogUtil.BusinessError(sce);
						}
						// temp.add(re);
						LogUtil.BusinessError("Write regQueue selector出现错误!!!"
								+ e.getCause());
						for (StackTraceElement s : e.getStackTrace()) {
							LogUtil.BusinessError("error by ==" + s.toString());
						}

					}
				}

				// 把temp中的数据添加进入regQueue，等待下次循环。
				for (RegElement re : temp) {
					IAsyncResult iar = (IAsyncResult) re.addObject;
					IOHandler ioHandler = iar.getIoHandler();
					BaseSession a = (BaseSession) ioHandler;
					LogUtil.BusinessError("$$$Write regQueue again"
							+ a.getRemoteEndPoint());
					regQueue.add(re);
				}

				int n = selector.select(100);
				if (n == 0)
					continue;
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					it.remove();
					try {
						if (sk.isValid() && sk.isWritable()) {
							// 注销写监听。
							IAsyncResult iar = (IAsyncResult) sk.attachment();
							IOHandler ioHandler = iar.getIoHandler();

							sk.cancel();
							// 写个消息，说明，我已经取消之。
							BaseSession a = (BaseSession) ioHandler;
							LogUtil.BusinessError("$$$Write cancel "
									+ a.getRemoteEndPoint());
							ioHandler.OnSendData(iar.getAsyncBuffer());

						}
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.error("处理错误,继续运行吧" + e.getMessage());
					}

				}

			} catch (Exception e) {
				LogUtil.BusinessError("Write selector出现错误!!!" + e.getCause());
				for (StackTraceElement s : e.getStackTrace()) {
					LogUtil.BusinessError("error by ==" + s.toString());
				}
			}

		}

	}

}

public class SelectorFactory {
	private static SelectorFactory sf;
	private AcceptThread acceptThread;
	private ReadThread readThread;
	private WriteThread writeThread;

	public void addKeyOpts(SelectableChannel sc, int n, Object o) {
		if ((SelectionKey.OP_ACCEPT & n) != 0) {
			acceptThread.addKeyOpts(sc, SelectionKey.OP_ACCEPT, o);
		}

		if ((SelectionKey.OP_READ & n) != 0) {
			readThread.addKeyOpts(sc, SelectionKey.OP_READ, o);
		}
		if ((SelectionKey.OP_WRITE & n) != 0) {
			writeThread.addKeyOpts(sc, SelectionKey.OP_WRITE, o);
		}
	}

	public void cancelKeyOpts(SelectableChannel sc, int n, Object o) {
		// if ((SelectionKey.OP_ACCEPT & n) != 0) {
		// acceptThread.cenecl(sc, SelectionKey.OP_ACCEPT, o);
		// }

		// if ((SelectionKey.OP_READ & n) != 0) {
		// readThread.cenecl(sc, SelectionKey.OP_READ, o);
		// }
		// if ((SelectionKey.OP_WRITE & n) != 0) {
		// writeThread.cenecl(sc, SelectionKey.OP_WRITE, o);
		// }
	}

	/**
	 * 对所有将要消费的通道对各个selector都进行注销
	 * 
	 * */
	public void cancel(SelectableChannel sc, int n) {
		// if ((SelectionKey.OP_ACCEPT & n) != 0) {
		// acceptThread.cancel(sc);
		// }
		// if ((SelectionKey.OP_READ & n) != 0) {
		// readThread.cancel(sc);
		// }
		// if ((SelectionKey.OP_WRITE & n) != 0) {
		// writeThread.cancel(sc);
		// }
	}

	private SelectorFactory() {
		try {
			acceptThread = new AcceptThread();
			readThread = new ReadThread();
			writeThread = new WriteThread();
			acceptThread.start();
			readThread.start();
			writeThread.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static public SelectorFactory getFactory() {
		if (sf == null) {
			sf = new SelectorFactory();
		}
		return sf;
	}

}
