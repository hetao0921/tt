package fxdigital.postserver.contentdispose;

import fxdigital.rpc.ModeAndType;


/**
 * 消息处理接口
 * @author fucz
 * @version 2014-7-8
 */
public interface IHandler {

	/**
	 * 接收消息
	 * @param strContent 消息字符串
	 * @return true：消息处理成功
	 * 			false：消息处理失败，返回MQ通道
	 */
	boolean handle(String strContent);
	
	/**
	 * 获得消息的类型和接收模式
	 * @return 消息的类型和接收模式
	 */
	ModeAndType getModeAndType();
	
}
