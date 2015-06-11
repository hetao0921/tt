package corenet.netbase;

import java.io.IOException;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import java.util.Iterator;
import java.util.Set;


import corenet.netbase.Interface.IOHandler;
 
public class BaseClientReadThread  {

	private BaseSocketChannel baseSocketChannel = null;

	public BaseClientReadThread(BaseSocketChannel bsc, IOHandler ih)
			throws Exception {
		this.baseSocketChannel = bsc;
		try {

			// SelectorFactory.getFactory().addKeyOpts(baseSocketChannel.getSc(),
			// SelectionKey.OP_CONNECT,ih);
//			baseSocketChannel.getSc().configureBlocking(false);
			int n = 0;
			while (!baseSocketChannel.getSc().finishConnect()) {
				Thread.sleep(200);
				n++;
				if(n>20) throw new Exception();
			}
				;
			ih.OnConnect(baseSocketChannel.getSc());
		} catch (Exception e) {
			// e.printStackTrace();
			baseSocketChannel.getSc().close();
			// this.baseSocketChannel = null;
			throw e;
		}
	}



}
