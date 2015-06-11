package fxdigital.syncserver.app;

import java.util.List;

import javax.jms.Message;

import fxdigital.mqcore.exchange.rpc.IMessage;

public interface IDispatcher {

	void dispatcher(String strFlag,IMessage message);
	void setHandler(HandlerChain handlerChain);

}
