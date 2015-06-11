package fxdigital.rpc;

/**
 * 邮件内容接口
 * 
 * @author fucz
 * @version 2014-6-12
 */
public interface IContent {
	/**
	 * 获得内容
	 * @return 内容
	 */
	String getContent();
	
	/**
	 * 获得发送中心ID
	 * @return 发送者
	 */
	String getSender();
	
	/**
	 * 获得目的中心ID
	 * @return 目的地
	 */
	String getReceiver();
	
	/**
	 * 设置内容
	 * @param content 内容
	 */
	void setContent(String content);
	
	/**
	 * 设置发送中心ID
	 * @param sender 发送者
	 */
	void setSender(String sender);
	
	/**
	 * 设置目的中心ID
	 * @param receiver 目的地
	 */
	void setReceiver(String receiver);
	
	/**
	 * 获得内容类型
	 * @return 内容类型
	 */
	String getType();
}
