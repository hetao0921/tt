package fxdigital.syncserver.app;

import java.util.List;

import javax.jms.Message;

import fxdigital.mqcore.exchange.rpc.IMessage;

public interface IBusiness {

	void OnMessage(String strFlag,IMessage message);
	List<String> getHandler();
	void regApp(IAppServer appServer);
}
