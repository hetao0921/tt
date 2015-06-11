package fxdigital.postserver.separation;

import fxdigital.rpc.Mail;

/**
 * 邮件分发器接口
 * @author fucz
 * @version 2014-7-8
 */
public interface ISeparater {
	
	/**
	 * 接收邮件
	 * @param mail 邮件
	 * @return true：邮件处理成功
	 * 			false：邮件处理失败，返回MQ通道
	 */
	boolean handle(Mail mail);
	
	/**
	 * 获得发送模式
	 * @return 发送模式
	 */
	String getMode();
	
}
