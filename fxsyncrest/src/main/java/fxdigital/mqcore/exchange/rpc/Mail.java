package fxdigital.mqcore.exchange.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;


/**
 * 邮件
 * 
 * @author fucz
 * @version 2014-6-12
 */
public class Mail implements IMessage{
	
	private String content;//邮件内容
	private String centerId;//发送者的ID，本中心的ID
	private String sendChannel;

	
	public Mail(){
	}
	
	public Mail(String content){
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getSendChannel() {
		return sendChannel;
	}

	public void setSendChannel(String sendChannel) {
		this.sendChannel = sendChannel;
	}
	
	
}
