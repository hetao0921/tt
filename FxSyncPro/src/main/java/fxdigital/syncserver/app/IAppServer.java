package fxdigital.syncserver.app;

import fxdigital.mqcore.exchange.rpc.IMessage;

public interface IAppServer {
	void send(IMessage message);
}
