package com.fxdigital.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author hxht
 * @version 2014-9-10
 */
public class UserLogBean {
	
	private String type;
	private String time;
	private String content;
	private String userName;
	private String clientId;
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String toString(){
		return JSONObject.toJSONString(this);
	}
	
}
