package com.fxdigital.analysis.bean;

import java.util.List;

public class Equality {
	private String equalityCenterId;//平级中心ID
	private List<String> mainCenters;//主中心ID
	private String operate;//1：添加，2：修改3：删除
	private String setId;
	private String type;//third,equality;
	public String getEqualityCenterId() {
		return equalityCenterId;
	}
	public void setEqualityCenterId(String equalityCenterId) {
		this.equalityCenterId = equalityCenterId;
	}
	public List<String> getMainCenters() {
		return mainCenters;
	}
	public void setMainCenters(List<String> mainCenters) {
		this.mainCenters = mainCenters;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getSetId() {
		return setId;
	}
	public void setSetId(String setId) {
		this.setId = setId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
