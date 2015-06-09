package com.fxdigital.storage.app;

import java.nio.channels.SocketChannel;

import com.fxdigital.storage.app.AsyncBuffer;

public interface IOHandler {
	public void OnSendData(AsyncBuffer ar);
	public void OnRecievedData(AsyncBuffer ar);
	public void OnConnect(SocketChannel d);
	public void OnDisConnect(SocketChannel d);
	 
}
