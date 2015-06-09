package fxdigital.mqcore.exchange;

import fxdigital.mqcore.exchange.impl.PostConfig;
import fxdigital.mqcore.exchange.rpc.IMessage;

public interface IServiceListener {

	boolean onhandler(String msgFlag,IMessage message);
	boolean onOldHandler(String msgFlag,IMessage message);
	PostConfig getProperties();
	
}
