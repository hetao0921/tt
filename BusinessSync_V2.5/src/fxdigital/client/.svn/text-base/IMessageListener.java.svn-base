package fxdigital.client;

import fxdigital.rpc.IContent;


/**
 * 消息接收接口
 * @author fucz
 * @version 2014-7-16
 */
public interface IMessageListener {
	
	/**
	 * 接收消息
	 * @param content 消息体
	 * @return true：消息消费成功
	 * 			false：消息消费失败，返回MQ通道
	 */
	boolean handle(IContent content);
	
	/**
	 * 获得消息体样本
	 * @return IContent 消息体接口
	 */
	IContent getContentSimple();
}
