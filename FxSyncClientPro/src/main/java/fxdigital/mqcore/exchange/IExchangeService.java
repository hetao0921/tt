package fxdigital.mqcore.exchange;

import javax.jms.Message;

public interface IExchangeService {
	public boolean handler(String msgFlag,Message message);
	public boolean oldHandler(String msgFlag,Message message);

}
