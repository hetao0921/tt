package fxdigital.client;

import fxdigital.rpc.IContent;


/**
 * 同步服务器的客户端接口
 * @author fucz
 * @version 2014-7-16
 */
public interface IBasicTelecom {
	
	/**
	 * 设置消息监听器
	 * @param listener 消息监听器
	 */
	void setListener(IMessageListener listener);
	
	/**
	 * 通知消息监听器
	 * @param content 接收到的消息体
	 * @return true：消息消费成功
	 * 			false：消息消费失败，返回MQ通道
	 */
	boolean notifyListener(IContent content);
	
	/**
	 * 发送消息体
	 * @param sendMode 发送模式
	 * @param content 发送的消息体
	 * @return true：消息发送成功
	 * 			false：消息发送失败
	 */
	boolean send(String sendMode,IContent content);
	
}
