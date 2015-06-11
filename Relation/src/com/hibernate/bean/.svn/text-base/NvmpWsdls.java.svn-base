package com.hibernate.bean;

/**
 * NvmpWsdls entity. @author MyEclipse Persistence Tools
 */

public class NvmpWsdls implements java.io.Serializable,IArrayPoJo {

	// Fields

	private Integer wsdlId;
	private String wsdlUri;
	private String wsdlDesc;

	public String[] toStringArray() {
 		String[] result = new String[3];
		result[0] = wsdlId.toString();
		result[1] = wsdlUri.toString();
		result[2] = wsdlDesc.toString();
		return result;
 	}
	
	// Constructors

	/** default constructor */
	public NvmpWsdls() {
	}

	/** minimal constructor */
	public NvmpWsdls(String wsdlUri) {
		this.wsdlUri = wsdlUri;
	}

	/** full constructor */
	public NvmpWsdls(String wsdlUri, String wsdlDesc) {
		this.wsdlUri = wsdlUri;
		this.wsdlDesc = wsdlDesc;
	}

	// Property accessors

	public Integer getWsdlId() {
		return this.wsdlId;
	}

	public void setWsdlId(Integer wsdlId) {
		this.wsdlId = wsdlId;
	}

	public String getWsdlUri() {
		return this.wsdlUri;
	}

	public void setWsdlUri(String wsdlUri) {
		this.wsdlUri = wsdlUri;
	}

	public String getWsdlDesc() {
		return this.wsdlDesc;
	}

	public void setWsdlDesc(String wsdlDesc) {
		this.wsdlDesc = wsdlDesc;
	}

}