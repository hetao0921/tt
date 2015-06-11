package corenet.netbase.Interface;

import java.nio.channels.SocketChannel;

import corenet.netbase.AsyncBuffer;

public interface IOHandler {
	public void OnSendData(AsyncBuffer ar);
	public void OnRecievedData(AsyncBuffer ar);
	public void OnConnect(SocketChannel d);
	public void OnDisConnect(SocketChannel d);
	 
}
