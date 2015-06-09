package fxdigital.mqcore.exchange;

import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.PostConfig;

public interface IServiceListener {

	boolean onhandler(String msgFlag,IMessage message);
	boolean onOldHandler(String msgFlag,IMessage message);
	PostConfig getProperties();
	
}
