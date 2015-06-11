package fxdigital.postserver.outertransmition;

import fxdigital.rpc.Mail;

/**
 * 非本级MQ发送接口
 * @author fucz
 * @version 2014-7-9
 */
public interface ITransmitter {
	
	/**
	 * 发送
	 * @param mail 邮件
	 * @return true：发送成功
	 * 			false：发送失败
	 */
	boolean sendOut(Mail mail);
	
	/**
	 * 获得发送模式
	 * @return 发送模式
	 */
	String getMode();
	
}
