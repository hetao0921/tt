package com.fxdigital.bean;

import java.util.List;

public class Index {

	private int id;
	private String pid;
	private String content;
	private String url;
	private int orderId;
	private List<Index> childIndexs;

	public Index() {
		super();
	}

	public Index(String content, String url, List<Index> childIndexs) {
		super();
		this.content = content;
		this.url = url;
		this.childIndexs = childIndexs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Index> getChildIndexs() {
		return childIndexs;
	}

	public void setChildIndexs(List<Index> childIndexs) {
		this.childIndexs = childIndexs;
	}
}
