package com.hibernate.bean;

/**
 * DataOperate entity. @author MyEclipse Persistence Tools
 */

public class DataOperate implements java.io.Serializable,IArrayPoJo{

	// Fields

	private Integer id;
	private String operate;
	private Integer flag;
	
	@Override
	public String[] toStringArray() {
 		String[] result = new String[3];
		result[0] = id.toString();
		result[1] = operate.toString();
		result[2] = flag.toString();
		return result;
 	}

	// Constructors

	/** default constructor */
	public DataOperate() {
	}

	/** full constructor */
	public DataOperate(String operate, Integer flag) {
		this.operate = operate;
		this.flag = flag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperate() {
		return this.operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}



}