package com.hibernate.bean;

/**
 * NvmpWsdlinfo entity. @author MyEclipse Persistence Tools
 */

public class NvmpWsdlinfo implements java.io.Serializable,IArrayPoJo {

	// Fields

	private Integer wsdlInfoId;
	private String wsdlUri;
	private String functionName;
	private String functionDesc;
	private String arguments;
	private String results;

	public String[] toStringArray() {
 		String[] result = new String[6];
		result[0] = wsdlInfoId.toString();
		result[1] = wsdlUri.toString();
		result[2] = functionName.toString();
		result[3] = functionDesc.toString();
		result[4] = arguments.toString();
		result[5] = results.toString();
		return result;
 	}
	
	// Constructors

	/** default constructor */
	public NvmpWsdlinfo() {
	}

	/** minimal constructor */
	public NvmpWsdlinfo(String wsdlUri) {
		this.wsdlUri = wsdlUri;
	}

	/** full constructor */
	public NvmpWsdlinfo(String wsdlUri, String functionName,
			String functionDesc, String arguments, String results) {
		this.wsdlUri = wsdlUri;
		this.functionName = functionName;
		this.functionDesc = functionDesc;
		this.arguments = arguments;
		this.results = results;
	}

	// Property accessors

	public Integer getWsdlInfoId() {
		return this.wsdlInfoId;
	}

	public void setWsdlInfoId(Integer wsdlInfoId) {
		this.wsdlInfoId = wsdlInfoId;
	}

	public String getWsdlUri() {
		return this.wsdlUri;
	}

	public void setWsdlUri(String wsdlUri) {
		this.wsdlUri = wsdlUri;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionDesc() {
		return this.functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getArguments() {
		return this.arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

}