package corenet.exchange.Interface;

import java.util.List;

import corenet.exchange.ExchangeServer;
import corenet.netbase.Interface.IChannel;
import corenet.rpc.IMessage;

public interface ExchangeServerMessage {
	void OnExchangeServerMessage(IChannel Channel, IMessage Message);

	void OnDomainMessage(String Sessionid, String Groupid, String state,
			String type);

	void setTarget(String target); 

	void setSource(String source);

	boolean getContinueFlag();

	List<IMessage> getCenterMessage();

	ExchangeServer get_ExchangeServer();
}
