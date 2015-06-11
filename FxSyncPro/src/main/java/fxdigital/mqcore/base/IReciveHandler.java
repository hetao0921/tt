package fxdigital.mqcore.base;

import javax.jms.Message;

import fxdigital.mqcore.exchange.rpc.IMessage;


/**
 * MQ消息接收接口
 * 
 * @author fucz
 * @version 2014-7-25
 */
public interface IReciveHandler {
	
	/**
	 * 接收MQ消息
	 * @param message MQ消息
	 * @return boolean true：消息成功消费
	 * 					false：消息消费失败，返回MQ通道
	 */
	boolean handler(Message message);
}
