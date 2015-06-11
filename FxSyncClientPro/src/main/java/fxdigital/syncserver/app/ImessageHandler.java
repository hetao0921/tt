package fxdigital.syncserver.app;

import fxdigital.mqcore.exchange.rpc.IMessage;

public interface ImessageHandler {
	public boolean handler(String strFlag,IMessage message);

}
