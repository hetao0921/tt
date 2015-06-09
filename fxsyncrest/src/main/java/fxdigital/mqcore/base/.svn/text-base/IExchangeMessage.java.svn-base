package fxdigital.mqcore.base;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.Msg;

public interface IExchangeMessage {
      public BytesMessage exchange(BytesMessage message,Msg m,byte[] data,String receiveId) throws JMSException;
      public TextMessage exchange(TextMessage message,Mail mail) throws JMSException;
}
